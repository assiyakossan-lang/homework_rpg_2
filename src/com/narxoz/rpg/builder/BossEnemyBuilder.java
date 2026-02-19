package com.narxoz.rpg.builder;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.enemy.DragonBoss;
import com.narxoz.rpg.enemy.Enemy;
import com.narxoz.rpg.loot.LootTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BossEnemyBuilder implements EnemyBuilder {

    private String name;
    private int health;
    private int damage;
    private int defense;
    private int speed;

    private String element = "NEUTRAL";
    private String aiBehavior = "BASIC";

    private List<Ability> abilities = new ArrayList<>();
    private Map<Integer, Integer> phases = new HashMap<>();

    private LootTable lootTable;

    private boolean canFly = true;
    private boolean hasBreathAttack = true;
    private int wingspan = 20;

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
        if (ability != null) this.abilities.add(ability);
        return this;
    }

    @Override
    public EnemyBuilder setAbilities(List<Ability> abilities) {
        this.abilities = (abilities == null) ? new ArrayList<>() : new ArrayList<>(abilities);
        return this;
    }

    @Override
    public EnemyBuilder addPhase(int phaseNumber, int healthThreshold) {
        this.phases.put(phaseNumber, healthThreshold);
        return this;
    }

    @Override
    public EnemyBuilder setLootTable(LootTable lootTable) {
        this.lootTable = lootTable;
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

    
        int p1 = phases.getOrDefault(1, health);
        int p2 = phases.getOrDefault(2, Math.max(1, health / 2));
        int p3 = phases.getOrDefault(3, Math.max(1, health / 4));

        return new DragonBoss(
                name,
                health,
                damage,
                defense,
                speed,
                element,
                new ArrayList<>(abilities),
                p1, p2, p3,
                lootTable,
                aiBehavior,
                canFly,
                hasBreathAttack,
                wingspan
        );
    }
}

