import org.osbot.rs07.api.ui.EquipmentSlot;
import org.osbot.rs07.event.WebWalkEvent;

public class TeleportSpotNode extends Node {
    public TeleportSpotNode(main m) {
        super(m);
    }
WalkToFountain walkToFountain = new WalkToFountain(m);
    @Override
    public boolean validate() {
        return m.myPosition().getY() < walkToFountain.Level30.getY() + 1 && m.myPosition().getY() > 3523;
    }

    @Override
    public int execute() throws InterruptedException {
        m.log("Running TeleportSpotNode");
        m.log("Teleporting With Glory");
            m.getEquipment().openTab();
            m.sleep(500);
            m.getEquipment().interact(EquipmentSlot.AMULET, "Edgeville");
            m.sleep(10000);
        return 0;
    }
}
