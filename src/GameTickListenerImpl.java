import org.dreambot.api.script.listener.GameTickListener;

public class GameTickListenerImpl implements GameTickListener {

    private final PrayerFlickingScript script;

    public GameTickListenerImpl(PrayerFlickingScript script) {
        this.script = script;
    }

    @Override
    public void onGameTick() {
        if (script.getEnemyTracker().isSynced() && script.getEnemyTracker().isEnemyStillAttacking()) {
            script.getPrayerFlicker().incrementTicksSinceLastAttack();

            if (script.getPrayerFlicker().getTicksSinceLastAttack() >= script.getEnemyTracker().getEnemyAttackSpeed().getTicks()) {
                script.getPrayerFlicker().setShouldFlickPrayer(true);
                script.getPrayerFlicker().resetTicksSinceLastAttack();
            }
        }
    }
}
