package com.narxoz.rpg.combat.ice;
import com.narxoz.rpg.combat.Ability;
public class FrostBreath implements Ability {

    private final String name = "Frost Breath";
    private final int damage = 100;
    private final String description = "Deals ice damage and slows enemies.";

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
        return new FrostBreath();
    }
}

