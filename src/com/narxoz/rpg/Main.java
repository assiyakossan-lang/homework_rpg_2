package com.narxoz.rpg;

import com.narxoz.rpg.builder.BasicEnemyBuilder;
import com.narxoz.rpg.builder.BossEnemyBuilder;
import com.narxoz.rpg.builder.EnemyDirector;
import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.combat.shadow.DarkNova;
import com.narxoz.rpg.enemy.Enemy;
import com.narxoz.rpg.factory.EnemyComponentFactory;
import com.narxoz.rpg.factory.FireComponentFactory;
import com.narxoz.rpg.factory.IceComponentFactory;
import com.narxoz.rpg.factory.ShadowComponentFactory;
import com.narxoz.rpg.loot.LootTable;
import com.narxoz.rpg.prototype.EnemyRegistry;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== RPG Enemy System - Creational Patterns Capstone ===\n");

        System.out.println("============================================");
        System.out.println("PART 1: ABSTRACT FACTORY - Themed Components");
        System.out.println("============================================\n");

        EnemyComponentFactory fireFactory = new FireComponentFactory();
        EnemyComponentFactory iceFactory = new IceComponentFactory();
        EnemyComponentFactory shadowFactory = new ShadowComponentFactory();

        showFactoryDemo("FIRE", fireFactory);
        showFactoryDemo("ICE", iceFactory);
        showFactoryDemo("SHADOW", shadowFactory);

        System.out.println("\n(Consistency idea) If you use ONE factory, abilities + loot + AI are always matching.\n");

        System.out.println("============================================");
        System.out.println("PART 2: BUILDER - Complex Enemy Construction");
        System.out.println("============================================\n");

       
        Enemy dragon = new BossEnemyBuilder()
                .setName("Ancient Fire Dragon")
                .setHealth(50000)
                .setDamage(500)
                .setDefense(200)
                .setSpeed(50)
                .setElement("FIRE")
                .setAbilities(fireFactory.createAbilities())
                .setLootTable(fireFactory.createLootTable())
                .setAIBehavior(fireFactory.createAIBehavior())
                .addPhase(1, 50000)
                .addPhase(2, 30000)
                .addPhase(3, 15000)
                .build(); 

        System.out.println("[BossEnemyBuilder -> build() created DragonBoss]");
        dragon.displayInfo();

        
        Enemy goblin = new BasicEnemyBuilder()
                .setName("Cave Goblin")
                .setHealth(120)
                .setDamage(18)
                .setDefense(7)
                .setSpeed(40)
                .setElement("NEUTRAL")
                .setAIBehavior("BASIC")
                .build(); 

        System.out.println("\n[BasicEnemyBuilder -> build() created Goblin]");
        goblin.displayInfo();

        
        EnemyDirector directorBoss = new EnemyDirector(new BossEnemyBuilder());
        Enemy raidBoss = directorBoss.createRaidBoss(shadowFactory);

        System.out.println("\n[Director -> createRaidBoss() using ShadowComponentFactory]");
        raidBoss.displayInfo();

        
        System.out.println("============================================");
        System.out.println("PART 3: PROTOTYPE - Enemy Cloning & Variants");
        System.out.println("============================================\n");

        EnemyRegistry registry = new EnemyRegistry();

       
        registry.registerTemplate("goblin", goblin);
        registry.registerTemplate("dragon", dragon);
        registry.registerTemplate("raidBoss", raidBoss);

        registry.listTemplates();

        
        Enemy eliteGoblin = registry.createFromTemplate("goblin");
        eliteGoblin.multiplyStats(2.0);
        System.out.println("\n[Elite Goblin = clone(goblin) + multiplyStats(2.0)]");
        eliteGoblin.displayInfo();

        
        Enemy iceDragon = registry.createFromTemplate("dragon");
        
        System.out.println("\n[Ice Dragon Variant = clone(dragon) (then change theme in your own implementation)]");
        iceDragon.displayInfo();

        Enemy clonedDragon = registry.createFromTemplate("dragon");
        tryAddAbility(clonedDragon, new DarkNova());

        System.out.println("\n[Clone Dragon after adding ability]");
        clonedDragon.displayInfo();

        System.out.println("\n[Original Dragon template should be unchanged]");
        dragon.displayInfo();

        
        System.out.println("============================================");
        System.out.println("PART 4: ALL PATTERNS WORKING TOGETHER");
        System.out.println("============================================\n");

        
        EnemyComponentFactory sf = new ShadowComponentFactory();

        
        Enemy demonLord = new BossEnemyBuilder()
                .setName("Demon Lord")
                .setHealth(60000)
                .setDamage(650)
                .setDefense(260)
                .setSpeed(55)
                .setElement("SHADOW")
                .setAbilities(sf.createAbilities())
                .setLootTable(sf.createLootTable())
                .setAIBehavior(sf.createAIBehavior())
                .addPhase(1, 60000)
                .addPhase(2, 35000)
                .addPhase(3, 18000)
                .build();

        System.out.println("[Built Demon Lord]");
        demonLord.displayInfo();

        
        registry.registerTemplate("demon-lord", demonLord);

       
        Enemy greaterDemon = registry.createFromTemplate("demon-lord");
        greaterDemon.multiplyStats(2.0);

        System.out.println("\n[Greater Demon = clone(demon-lord) + 2x stats]");
        greaterDemon.displayInfo();


        System.out.println("============================================");
        System.out.println("PATTERN SUMMARY");
        System.out.println("============================================");
        System.out.println();
        System.out.println("Abstract Factory: Themed component families (Fire, Ice, Shadow)");
        System.out.println("Builder: Complex step-by-step enemy construction (BossEnemyBuilder, BasicEnemyBuilder)");
        System.out.println("Factory Method: build() in builders creates Enemy objects");
        System.out.println("Prototype: EnemyRegistry clones templates (deep copy) to create variants");

        System.out.println("\n=== Demo Complete ===");
    }

    private static void showFactoryDemo(String themeName, EnemyComponentFactory factory) {
        List<Ability> abilities = factory.createAbilities();
        LootTable loot = factory.createLootTable();
        String ai = factory.createAIBehavior();

        System.out.println("[" + themeName + " FACTORY]");
        System.out.println("AI: " + ai);
        System.out.println("Abilities (" + abilities.size() + "):");
        for (Ability a : abilities) {
            System.out.println(" - " + a.getName() + " (" + a.getDamage() + "): " + a.getDescription());
        }
        System.out.println("Loot: " + (loot == null ? "none" : loot.getLootInfo()));
        System.out.println();
    }

    
    private static void tryAddAbility(Enemy enemy, Ability ability) {
        try {
        
            enemy.getClass().getMethod("addAbility", Ability.class).invoke(enemy, ability);
        } catch (Exception e) {
            System.out.println("(Deep copy test skipped) Enemy has no addAbility(Ability) method.");
        }
    }
}

