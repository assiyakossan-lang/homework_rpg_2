package com.narxoz.rpg.loot;

import java.util.List;

public class ShadowLootTable implements LootTable {

    @Override
    public List<String> getItems() {
        return List.of("Shadow Gem", "Dark Essence", "Night Rune");
    }

    @Override
    public int getGoldDrop() {
        return 600;
    }

    @Override
    public int getExperienceDrop() {
        return 1200;
    }

    @Override
    public String getLootInfo() {
        return "Items: " + getItems()
                + " | Gold: " + getGoldDrop()
                + " | EXP: " + getExperienceDrop();
    }

    @Override
    public LootTable clone() {
        return new ShadowLootTable();
    }
}


