import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.prayer.Prayer;
import org.dreambot.api.script.listener.HitSplatListener;
import org.dreambot.api.wrappers.interactive.Entity;
import org.dreambot.api.methods.prayer.Prayers;

public class HitSplatListenerImpl implements HitSplatListener {

    private final PrayerFlickingScript script;

    public HitSplatListenerImpl(PrayerFlickingScript script) {
        this.script = script;
    }

    @Override
    public void onHitSplatAdded(Entity entity, int type, int damage, int id, int special, int gameCycle) {
        if (entity != null && entity.equals(Players.getLocal())) {
            if (!Prayers.isActive(Prayer.PROTECT_FROM_MELEE)) {
                script.log("Hitsplat detected without prayer active! Re-synchronizing.");
                script.getPrayerFlicker().resetTicksSinceLastAttack();
                script.getEnemyTracker().setSynced(true);
                script.getPrayerFlicker().setShouldFlickPrayer(false);
            } else if (!script.getEnemyTracker().isSynced()) {
                script.log("First hitsplat detected! Synchronizing tick counter.");
                script.getPrayerFlicker().resetTicksSinceLastAttack();
                script.getEnemyTracker().setSynced(true);
                script.getPrayerFlicker().setShouldFlickPrayer(false);
            }
        }
    }
}
