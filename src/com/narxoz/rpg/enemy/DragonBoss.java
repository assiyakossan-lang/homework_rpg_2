package com.narxoz.rpg.enemy;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.loot.LootTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DragonBoss implements Enemy {

    private String name;
    private int health;
    private int damage;
    private int defense;
    private int speed;

    private String element;
    private List<Ability> abilities;
    private Map<Integer, Integer> phases;

    private LootTable lootTable;
    private String aiBehavior;

    private boolean canFly;
    private boolean hasBreathAttack;
    private int wingspan;

    public DragonBoss(String name,
                      int health,
                      int damage,
                      int defense,
                      int speed,
                      String element,
                      List<Ability> abilities,
                      int phase1Threshold,
                      int phase2Threshold,
                      int phase3Threshold,
                      LootTable lootTable,
                      String aiBehavior,
                      boolean canFly,
                      boolean hasBreathAttack,
                      int wingspan) {

        this.name = name;
        this.health = health;
        this.damage = damage;
        this.defense = defense;
        this.speed = speed;
        this.element = element;
        this.abilities = (abilities == null) ? new ArrayList<>() : abilities;

        this.phases = new HashMap<>();
        this.phases.put(1, phase1Threshold);
        this.phases.put(2, phase2Threshold);
        this.phases.put(3, phase3Threshold);

        this.lootTable = lootTable;
        this.aiBehavior = aiBehavior;
        this.canFly = canFly;
        this.hasBreathAttack = hasBreathAttack;
        this.wingspan = wingspan;
    }

   

    @Override
    public String getName() { return name; }

    @Override
    public int getHealth() { return health; }

    @Override
    public int getDamage() { return damage; }

    @Override
    public int getDefense() { return defense; }

    @Override
    public int getSpeed() { return speed; }

    @Override
    public String getElement() { return element; }

    @Override
    public String getAIBehavior() { return aiBehavior; }

    @Override
    public List<Ability> getAbilities() {
        return new ArrayList<>(abilities);
    }

    @Override
    public LootTable getLootTable() {
        return lootTable;
    }

    @Override
    public Map<Integer, Integer> getPhases() {
        return new HashMap<>(phases);
    }

    

    @Override
    public void displayInfo() {
        System.out.println("=== " + name + " (Dragon Boss) ===");
        System.out.println("HP: " + health +
                " | DMG: " + damage +
                " | DEF: " + defense +
                " | SPD: " + speed);
        System.out.println("Element: " + element +
                " | AI: " + aiBehavior);

        System.out.println("Abilities:");
        for (Ability a : abilities) {
            System.out.println(" - " + a.getName() +
                    " (" + a.getDamage() + "): " +
                    a.getDescription());
        }

        System.out.println("Phases:");
        for (Map.Entry<Integer, Integer> entry : phases.entrySet()) {
            System.out.println(" Phase " + entry.getKey()
                    + " at HP " + entry.getValue());
        }

        if (lootTable != null) {
            System.out.println("Loot: " + lootTable.getLootInfo());
        }
    }

   

    @Override
    public Enemy clone() {

        List<Ability> abilitiesCopy = new ArrayList<>();
        for (Ability a : abilities) {
            abilitiesCopy.add(a.clone());
        }

        LootTable lootCopy = (lootTable == null)
                ? null
                : lootTable.clone();

        DragonBoss copy = new DragonBoss(
                name,
                health,
                damage,
                defense,
                speed,
                element,
                abilitiesCopy,
                phases.getOrDefault(1, health),
                phases.getOrDefault(2, health / 2),
                phases.getOrDefault(3, health / 4),
                lootCopy,
                aiBehavior,
                canFly,
                hasBreathAttack,
                wingspan
        );

        return copy;
    }

    

    @Override
    public void multiplyStats(double multiplier) {
        health = (int) Math.round(health * multiplier);
        damage = (int) Math.round(damage * multiplier);
        defense = (int) Math.round(defense * multiplier);
        speed = (int) Math.round(speed * multiplier);
    }
}

