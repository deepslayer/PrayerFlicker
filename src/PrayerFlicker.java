import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.prayer.Prayer;
import org.dreambot.api.methods.prayer.Prayers;

public class PrayerFlicker {

    private final PrayerFlickingScript script;
    private boolean shouldFlickPrayer;
    private int ticksSinceLastAttack;

    public PrayerFlicker(PrayerFlickingScript script) {
        this.script = script;
        this.shouldFlickPrayer = false;
        this.ticksSinceLastAttack = 0;
        Prayers.toggle(false, Prayer.PROTECT_FROM_MELEE);
    }

    public void handlePrayerFlick() {
        if (shouldFlickPrayer && script.getEnemyTracker().isSynced()) {
            int overheadIcon = Players.getLocal().getOverheadIcon();

            if (overheadIcon == -1) {
                togglePrayer(true);
            } else if (overheadIcon == 0) {
                togglePrayer(false);
                shouldFlickPrayer = false;
            }
        }
    }

    public void stopFlicking() {
        if (shouldFlickPrayer) {
            script.log("Stopping prayer flicking.");
            shouldFlickPrayer = false;
            Prayers.toggle(false, Prayer.PROTECT_FROM_MELEE);
        }
    }

    public void setShouldFlickPrayer(boolean shouldFlickPrayer) {
        this.shouldFlickPrayer = shouldFlickPrayer;
    }

    public void incrementTicksSinceLastAttack() {
        ticksSinceLastAttack++;
    }

    public void resetTicksSinceLastAttack() {
        ticksSinceLastAttack = 0;
    }

    public int getTicksSinceLastAttack() {
        return ticksSinceLastAttack;
    }

    private void togglePrayer(boolean enable) {
        if (enable && !Prayers.isActive(Prayer.PROTECT_FROM_MELEE)) {
            Prayers.toggle(true, Prayer.PROTECT_FROM_MELEE);
        } else if (!enable && Prayers.isActive(Prayer.PROTECT_FROM_MELEE)) {
            Prayers.toggle(false, Prayer.PROTECT_FROM_MELEE);
        }
    }
}
