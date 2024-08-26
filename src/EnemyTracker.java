import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.wrappers.interactive.NPC;

public class EnemyTracker {

    private final PrayerFlickingScript script;
    private NPC enemy;
    private EnemyAttackSpeed enemyAttackSpeed;
    private boolean isSynced;

    public EnemyTracker(PrayerFlickingScript script) {
        this.script = script;
        this.isSynced = false;
    }

    public void detectEnemy() {
        NPC detectedEnemy = (NPC) Players.getLocal().getCharacterInteractingWithMe();
        if (detectedEnemy != null) {
            enemy = detectedEnemy;
            enemyAttackSpeed = EnemyAttackSpeedMapping.getAttackSpeedForEnemy(enemy.getName());
            script.log("Enemy detected: " + enemy.getName() + ", Attack speed: " + enemyAttackSpeed.getTicks() + " ticks.");
            isSynced = false;
        } else {
            script.log("No enemy detected.");
            isSynced = false;
        }
    }

    public void updateEnemyIfNecessary() {
        NPC newEnemy = (NPC) Players.getLocal().getCharacterInteractingWithMe();
        if (newEnemy != null && !newEnemy.equals(enemy)) {
            enemy = newEnemy;
            enemyAttackSpeed = EnemyAttackSpeedMapping.getAttackSpeedForEnemy(newEnemy.getName());
            script.log("Enemy changed to: " + newEnemy.getName() + ", Attack speed: " + enemyAttackSpeed.getTicks() + " ticks.");
            isSynced = false;
        }
    }

    public boolean isEnemyStillAttacking() {
        if (enemy == null || enemy.getHealthPercent() == 0 || !Players.getLocal().isInCombat() || !enemy.exists()) {
            return false;
        }
        return enemy.equals(Players.getLocal().getCharacterInteractingWithMe());
    }

    public NPC getEnemy() {
        return enemy;
    }

    public EnemyAttackSpeed getEnemyAttackSpeed() {
        return enemyAttackSpeed;
    }

    public boolean isSynced() {
        return isSynced;
    }

    public void setSynced(boolean synced) {
        this.isSynced = synced;
    }
}
