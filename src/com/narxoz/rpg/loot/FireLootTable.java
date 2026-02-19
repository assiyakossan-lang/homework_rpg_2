package com.narxoz.rpg.loot;

import java.util.List;

public class FireLootTable implements LootTable {

    @Override
    public List<String> getItems() {
        return List.of("Fire Gem", "Dragon Scale", "Flame Rune");
    }

    @Override
    public int getGoldDrop() {
        return 500;
    }

    @Override
    public int getExperienceDrop() {
        return 1000;
    }

    @Override
    public String getLootInfo() {
        return "Items: " + getItems()
                + " | Gold: " + getGoldDrop()
                + " | EXP: " + getExperienceDrop();
    }

    @Override
    public LootTable clone() {
        return new FireLootTable();
    }
}

