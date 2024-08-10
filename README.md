# Prayer Flicking Script

## Overview

**Prayer Flicking Script** is an Old School RuneScape (OSRS) script that automatically flicks protection prayers based on the attack speed of enemies. The script is designed to maximize prayer efficiency by toggling the prayer at the exact time it's needed during combat, preventing the drainage of prayer points

## Features

- **Automatic Prayer Flicking:** The script detects the attack speed of enemies and flicks prayers accordingly. (Please note it is only for the Melee prayer)
- **Enemy Detection:** Automatically detects the enemy that the player is currently interacting with and adjusts the prayer flicking based on that specific enemy.
- **Customizable Attack Speeds:** Support for different enemies with configurable attack speeds.
- **Optimized Prayer Usage:** Ensures that protection prayers are only active during critical moments, reducing unnecessary prayer drain.

## How It Works

The script is composed of several key classes:

- **`PrayerFlickingScript`:** The main class that extends `AbstractScript` and implements `GameTickListener`. This class orchestrates the overall flow of the script, including starting and looping the main logic.
- **`EnemyTracker`:** Handles detection and tracking of the enemy that the player is interacting with. It synchronizes with the enemy's attack animation and determines when to flick the prayer.
- **`PrayerFlicker`:** Manages the actual toggling of the protection prayer based on the game's overhead icon.
- **`EnemyAttackSpeed`:** An enum defining various attack speeds (FAST, MEDIUM, SLOW, VERY_SLOW) and the corresponding tick values.
- **`EnemyAttackSpeedMapping`:** A utility class that maps enemy names to their respective attack speeds.

## Script Flow

1. **Initialization:** 
   - The script initializes by detecting the enemy the player is interacting with.
   - It determines the attack speed of the enemy using `EnemyAttackSpeedMapping`.

2. **Main Loop (`onLoop`)**:
   - The script continuously checks if the player has switched to a different enemy.
   - It handles the flicking of the prayer based on the game's overhead icon.

3. **Tick Synchronization (`onGameTick`)**:
   - The script listens for game ticks and synchronizes with the enemy's attack animation.
   - It triggers the prayer flick at the right time based on the number of ticks elapsed since the last attack.

## Configuration

### Enemy Attack Speed Mapping

The `EnemyAttackSpeedMapping` class maps each enemy name to a predefined attack speed:

```java
public static EnemyAttackSpeed getAttackSpeedForEnemy(String enemyName) {
    switch (enemyName.toLowerCase()) {
        case "hill giant":
            return EnemyAttackSpeed.SLOW;
        case "green dragon":
            return EnemyAttackSpeed.MEDIUM;
        case "guard":
            return EnemyAttackSpeed.SLOW;
        // Add more NPCs as needed
        default:
            return EnemyAttackSpeed.MEDIUM;
    }
}
