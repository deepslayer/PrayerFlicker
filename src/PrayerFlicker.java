import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.prayer.Prayer;
import org.dreambot.api.methods.prayer.Prayers;

public class PrayerFlicker {

    private PrayerFlickingScript script;

    public PrayerFlicker(PrayerFlickingScript script) {
        this.script = script;
    }

    public void handlePrayerFlick() {
        if (script.shouldFlickPrayer) {
            int overheadIcon = Players.getLocal().getOverheadIcon();

            if (overheadIcon == -1) {
                togglePrayer(true);
            } else if (overheadIcon == 0) {
                togglePrayer(false);
                script.shouldFlickPrayer = false;
            }
        }
    }

    private void togglePrayer(boolean enable) {
        if (enable && !Prayers.isActive(Prayer.PROTECT_FROM_MELEE)) {
            Prayers.toggle(true, Prayer.PROTECT_FROM_MELEE);
        } else if (!enable && Prayers.isActive(Prayer.PROTECT_FROM_MELEE)) {
            Prayers.toggle(false, Prayer.PROTECT_FROM_MELEE);
        }
    }
}
