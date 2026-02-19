package com.narxoz.rpg.loot;

import java.util.List;

public class IceLootTable implements LootTable {

    @Override
    public List<String> getItems() {
        return List.of("Ice Gem", "Frost Crystal", "Frozen Rune");
    }

    @Override
    public int getGoldDrop() {
        return 400;
    }

    @Override
    public int getExperienceDrop() {
        return 900;
    }

    @Override
    public String getLootInfo() {
        return "Items: " + getItems()
                + " | Gold: " + getGoldDrop()
                + " | EXP: " + getExperienceDrop();
    }

    @Override
    public LootTable clone() {
        return new IceLootTable();
    }
}


