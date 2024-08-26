import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.script.ScriptManager;
import org.dreambot.api.wrappers.interactive.NPC;

@ScriptManifest(author = "Deep Slayer", name = "Prayer Flicking Script", version = 1.6, description = "Flicks prayer based on enemy attack speed", category = Category.COMBAT)
public class PrayerFlickingScript extends AbstractScript {

    private EnemyTracker enemyTracker;
    private PrayerFlicker prayerFlicker;

    @Override
    public void onStart() {
        enemyTracker = new EnemyTracker(this);
        prayerFlicker = new PrayerFlicker(this);

        // Detect the initial enemy
        enemyTracker.detectEnemy();

        // Register listeners
        ScriptManager scriptManager = getScriptManager();
        scriptManager.addListener(new HitSplatListenerImpl(this));
        scriptManager.addListener(new GameTickListenerImpl(this));
    }

    @Override
    public int onLoop() {
        enemyTracker.updateEnemyIfNecessary();
        if (enemyTracker.isEnemyStillAttacking()) {
            prayerFlicker.handlePrayerFlick();
        } else {
            prayerFlicker.stopFlicking();
        }
        return 50;
    }

    // Expose methods to other classes if necessary
    public NPC getEnemy() {
        return enemyTracker.getEnemy();
    }

    public EnemyAttackSpeed getEnemyAttackSpeed() {
        return enemyTracker.getEnemyAttackSpeed();
    }

    public EnemyTracker getEnemyTracker() {
        return enemyTracker;
    }

    public PrayerFlicker getPrayerFlicker() {
        return prayerFlicker;
    }
}
