package com.narxoz.rpg.builder;

import com.narxoz.rpg.enemy.Enemy;
import com.narxoz.rpg.factory.EnemyComponentFactory;

public class EnemyDirector {

    private final EnemyBuilder builder;

    public EnemyDirector(EnemyBuilder builder) {
        this.builder = builder;
    }

    public Enemy createMinion(EnemyComponentFactory factory) {

        return builder
                .setName("Minion")
                .setHealth(60)
                .setDamage(8)
                .setDefense(3)
                .setSpeed(30)
                .setElement("NEUTRAL")
                .setAIBehavior("BASIC")
                .setAbilities(factory.createAbilities())
                .setLootTable(factory.createLootTable())
                .build();
    }

    public Enemy createElite(EnemyComponentFactory factory) {
       
        return builder
                .setName("Elite")
                .setHealth(150)
                .setDamage(25)
                .setDefense(10)
                .setSpeed(40)
                .setElement("ELITE")
                .setAIBehavior(factory.createAIBehavior())
                .setAbilities(factory.createAbilities())
                .setLootTable(factory.createLootTable())
                .build();
    }

    public Enemy createMiniBoss(EnemyComponentFactory factory) {
        return builder
                .setName("Mini Boss")
                .setHealth(500)
                .setDamage(60)
                .setDefense(25)
                .setSpeed(35)
                .setElement("BOSS")
                .setAIBehavior(factory.createAIBehavior())
                .setAbilities(factory.createAbilities())
                .setLootTable(factory.createLootTable())
                .build();
    }

    public Enemy createRaidBoss(EnemyComponentFactory factory) {
        return builder
                .setName("Raid Boss")
                .setHealth(2000)
                .setDamage(150)
                .setDefense(70)
                .setSpeed(40)
                .setElement("RAID")
                .setAIBehavior(factory.createAIBehavior())
                .setAbilities(factory.createAbilities())
                .setLootTable(factory.createLootTable())
                .build();
    }
}

