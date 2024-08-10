import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.script.listener.GameTickListener;
import org.dreambot.api.wrappers.interactive.NPC;

@ScriptManifest(author = "Deep Slayer", name = "Prayer Flicking Script", version = 1.0, description = "Flicks prayer based on enemy attack speed", category = Category.COMBAT)
public class PrayerFlickingScript extends AbstractScript implements GameTickListener {

    private NPC enemy;
    private EnemyAttackSpeed enemyAttackSpeed;
    private boolean isSynced;
    boolean shouldFlickPrayer;
    private int ticksSinceLastAttack;

    private PrayerFlicker prayerFlicker;
    private EnemyTracker enemyTracker;

    @Override
    public void onStart() {
        prayerFlicker = new PrayerFlicker(this);
        enemyTracker = new EnemyTracker(this);
        enemyTracker.detectEnemy();
    }

    @Override
    public int onLoop() {
        enemyTracker.updateEnemyIfNecessary();
        prayerFlicker.handlePrayerFlick();
        return 50;
    }

    @Override
    public void onGameTick() {
        enemyTracker.synchronizeWithEnemyAttack();
    }

    // Getters and Setters

    public NPC getEnemy() {
        return enemy;
    }

    public void setEnemy(NPC enemy) {
        this.enemy = enemy;
    }

    public EnemyAttackSpeed getEnemyAttackSpeed() {
        return enemyAttackSpeed;
    }

    public void setEnemyAttackSpeed(EnemyAttackSpeed enemyAttackSpeed) {
        this.enemyAttackSpeed = enemyAttackSpeed;
    }

    public boolean isSynced() {
        return isSynced;
    }

    public void setSynced(boolean synced) {
        isSynced = synced;
    }

    public boolean shouldFlickPrayer() {
        return shouldFlickPrayer;
    }

    public void setShouldFlickPrayer(boolean shouldFlickPrayer) {
        this.shouldFlickPrayer = shouldFlickPrayer;
    }

    public int getTicksSinceLastAttack() {
        return ticksSinceLastAttack;
    }

    public void setTicksSinceLastAttack(int ticksSinceLastAttack) {
        this.ticksSinceLastAttack = ticksSinceLastAttack;
    }
}
