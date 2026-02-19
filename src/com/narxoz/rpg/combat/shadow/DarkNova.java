package com.narxoz.rpg.combat.shadow;

import com.narxoz.rpg.combat.Ability;

public class DarkNova implements Ability {

    private final String name = "Dark Nova";
    private final int damage = 280;
    private final String description = "Explosive burst of shadow energy.";

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
        return new DarkNova();
    }
}
  