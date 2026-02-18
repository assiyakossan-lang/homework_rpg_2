package com.narxoz.rpg.combat.fire;
import com.narxoz.rpg.combat.Ability;
public class FlameBreath implements Ability {

    private final String name = "Flame Breath";
    private final int damage = 120;
    private final String description = "Deals fire damage to all enemies.";

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
        return new FlameBreath();
    }
}

