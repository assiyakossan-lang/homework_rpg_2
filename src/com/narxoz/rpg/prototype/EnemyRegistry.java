package com.narxoz.rpg.prototype;

import com.narxoz.rpg.enemy.Enemy;

import java.util.HashMap;
import java.util.Map;

public class EnemyRegistry {

    private final Map<String, Enemy> templates = new HashMap<>();

    
    public void registerTemplate(String key, Enemy prototype) {
        if (key == null || prototype == null) {
            throw new IllegalArgumentException("Key and prototype must not be null");
        }
        templates.put(key, prototype);
    }


    public Enemy createFromTemplate(String key) {
        Enemy prototype = templates.get(key);

        if (prototype == null) {
            throw new IllegalArgumentException("No template registered with key: " + key);
        }

        return prototype.clone(); 
    }


    public void listTemplates() {
        System.out.println("=== Registered Templates ===");
        for (String key : templates.keySet()) {
            System.out.println("- " + key);
        }
    }
}

