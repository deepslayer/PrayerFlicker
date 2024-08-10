import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.wrappers.interactive.NPC;

public class EnemyTracker {

    private final PrayerFlickingScript script;
    private int lastAnimation = -1;

    public EnemyTracker(PrayerFlickingScript script) {
        this.script = script;
    }

    public void detectEnemy() {
        NPC enemy = (NPC) Players.getLocal().getCharacterInteractingWithMe();
        if (enemy != null) {
            script.setEnemy(enemy);
            script.setEnemyAttackSpeed(EnemyAttackSpeedMapping.getAttackSpeedForEnemy(enemy.getName()));
            script.log("Enemy detected: " + enemy.getName() + ", Attack speed: " + script.getEnemyAttackSpeed().getTicks() + " ticks.");
        } else {
            script.log("No enemy detected.");

        }
    }

    public void updateEnemyIfNecessary() {
        NPC newEnemy = (NPC) Players.getLocal().getCharacterInteractingWithMe();
        if (newEnemy != null && !newEnemy.equals(script.getEnemy())) {
            script.setEnemy(newEnemy);
            script.setEnemyAttackSpeed(EnemyAttackSpeedMapping.getAttackSpeedForEnemy(newEnemy.getName()));
            script.log("Enemy changed to: " + newEnemy.getName() + ", Attack speed: " + script.getEnemyAttackSpeed().getTicks() + " ticks.");
            script.setSynced(false); // Reset synchronization when the enemy changes
        }
    }

    public void synchronizeWithEnemyAttack() {
        NPC enemy = script.getEnemy();
        if (enemy != null && Players.getLocal().isInCombat()) {
            int currentAnimation = enemy.getAnimation();

            if (!script.isSynced() && currentAnimation != lastAnimation && isAttackAnimation(currentAnimation)) {
                script.log("Initial attack animation detected! Synchronizing tick counter.");
                script.setTicksSinceLastAttack(0);
                script.setSynced(true);
            }

            if (script.isSynced()) {
                script.setTicksSinceLastAttack(script.getTicksSinceLastAttack() + 1);

                if (script.getTicksSinceLastAttack() == script.getEnemyAttackSpeed().getTicks()) {
                    script.setShouldFlickPrayer(true);
                    script.setTicksSinceLastAttack(0);
                }
            }

            lastAnimation = currentAnimation;
        }
    }

    private boolean isAttackAnimation(int animation) {
        return animation != -1;
    }
}
