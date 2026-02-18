package com.narxoz.rpg.combat.shadow;
import com.narxoz.rpg.combat.Ability;
public class ShadowStrike implements Ability {

    private final String name = "Shadow Strike";
    private final int damage = 140;
    private final String description = "High shadow damage to one enemy.";

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
        return new ShadowStrike();
    }
}

