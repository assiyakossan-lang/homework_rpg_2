package com.narxoz.rpg.builder;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.enemy.Enemy;
import com.narxoz.rpg.enemy.Goblin;
import com.narxoz.rpg.loot.LootTable;

import java.util.ArrayList;
import java.util.List;

public class BasicEnemyBuilder implements EnemyBuilder {

    private String name;
    private int health = 100;
    private int damage = 10;
    private int defense = 5;
    private int speed = 30;

    private String element = "NEUTRAL";
    private String aiBehavior = "BASIC";

    private final List<Ability> abilities = new ArrayList<>();
    private LootTable lootTable;

    

    @Override
    public EnemyBuilder setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public EnemyBuilder setHealth(int health) {
        this.health = health;
        return this;
    }

    @Override
    public EnemyBuilder setDamage(int damage) {
        this.damage = damage;
        return this;
    }

    @Override
    public EnemyBuilder setDefense(int defense) {
        this.defense = defense;
        return this;
    }

    @Override
    public EnemyBuilder setSpeed(int speed) {
        this.speed = speed;
        return this;
    }

    @Override
    public EnemyBuilder setElement(String element) {
        this.element = element;
        return this;
    }

    @Override
    public EnemyBuilder setAIBehavior(String aiBehavior) {
        this.aiBehavior = aiBehavior;
        return this;
    }

    @Override
    public EnemyBuilder addAbility(Ability ability) {
        if (ability != null) abilities.add(ability);
        return this;
    }

    @Override
    public EnemyBuilder setAbilities(List<Ability> abilities) {
        this.abilities.clear();
        if (abilities != null) this.abilities.addAll(abilities);
        return this;
    }

    @Override
    public EnemyBuilder setLootTable(LootTable lootTable) {
        this.lootTable = lootTable;
        return this;
    }

    @Override
    public EnemyBuilder addPhase(int phaseNumber, int healthThreshold) {
        // BasicEnemyBuilder builds Goblins which don't use phases
        // This is a no-op for basic enemies
        return this;
    }

    @Override
    public Enemy build() {
        if (name == null || name.isBlank()) {
            throw new IllegalStateException("Enemy name is required");
        }
        if (health <= 0) {
            throw new IllegalStateException("Enemy health must be positive");
        }

        Goblin g = new Goblin(name);

        g.setElement(element);
        g.setAIBehavior(aiBehavior);
        g.setLootTable(lootTable);

        for (Ability a : abilities) {
            g.addAbility(a);
        }

        // Calculate multiplier based on average of all stat ratios
        double healthMult = health / 100.0;
        double damageMult = damage / 10.0;
        double defenseMult = defense / 5.0;
        double speedMult = speed / 30.0;
        double avgMult = (healthMult + damageMult + defenseMult + speedMult) / 4.0;

        g.multiplyStats(avgMult);

        return g;
    }

    public BasicEnemyBuilder reset() {
        name = null;
        health = 100;
        damage = 10;
        defense = 5;
        speed = 30;
        element = "NEUTRAL";
        aiBehavior = "BASIC";
        abilities.clear();
        lootTable = null;
        return this;
    }
}

