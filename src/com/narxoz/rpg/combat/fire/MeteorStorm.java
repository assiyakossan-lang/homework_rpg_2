package com.narxoz.rpg.combat.fire;

import com.narxoz.rpg.combat.Ability;

public class MeteorStorm implements Ability {

    private final String name = "Meteor Storm";
    private final int damage = 250;
    private final String description = "Ultimate fire attack raining meteors from the sky.";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Ability clone() {
        return new MeteorStorm();
    }
}

