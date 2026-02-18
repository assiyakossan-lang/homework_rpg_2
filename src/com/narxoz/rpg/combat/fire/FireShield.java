package com.narxoz.rpg.combat.fire;
import com.narxoz.rpg.combat.Ability;
public class FireShield implements Ability {

    private final String name = "Fire Shield";
    private final int damage = 0;
    private final String description = "Provides fire protection.";

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
        return new FireShield();
    }
}

