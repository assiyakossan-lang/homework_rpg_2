package com.narxoz.rpg.combat.shadow;
import com.narxoz.rpg.combat.Ability;
public class Vanish implements Ability {

    private final String name = "Vanish";
    private final int damage = 0;
    private final String description = "Enemy becomes invisible.";

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
        return new Vanish();
    }
}

