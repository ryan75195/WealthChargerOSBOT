import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.event.WebWalkEvent;

public class CutWebNode extends Node {

    public Position WebTile = new Position(3158, 3950, 0);
    public Area WildyLeverZone = new Area(3142, 3950, 3169, 3913);
    //   RS2Object SlashedWeb = m.getObjects().closest("Slashed web");
    //   PullLever pullLever = new PullLever(m);
    WalkToFountain walkToFountian = new WalkToFountain(m);

    public CutWebNode(main m) {
        super(m);
    }

    @Override
    public boolean validate() {

        return WildyLeverZone.contains(m.myPosition()) /*&& pullLever.LeverPulled*/;
    }

    @Override
    public int execute() {
        PullLever pullLever = new PullLever(m);
        pullLever.LeverPulled = true;
        m.log("Running CutWebNode");
        do {
            m.getWalking().walk(WebTile);
        } while (WebTile.distance(m.myPosition()) > 1);
        RS2Object web = m.getObjects().closest("Web");
        if (web == null) {
            m.log("No Web, Walking North");
            m.getWalking().walk(walkToFountian.NorthWeb);

        } else if(web != null){
            m.log("Cutting web");
            web.interact();
            m.getWalking().walk(walkToFountian.NorthWeb);
        }else{
            m.log("Web Broken");
        }
        return 0;
    }
}