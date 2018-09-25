import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.event.WebWalkEvent;

public class PullLever extends Node {


    public PullLever(main m) {
        super(m);
    }

    EdgevileBankNode edgevileBankNode = new EdgevileBankNode(m);
    CutWebNode cutWebNode = new CutWebNode(m);

    public Position LeverTile = new Position(3090, 3475, 0);
    Interactions interactions = new Interactions(m);
    public boolean LeverPulled = false;

    @Override
    public boolean validate() {
        return edgevileBankNode.EdgevilleBankZone.contains(m.myPosition()) && LeverPulled == false;
    }

    @Override
    public int execute() throws InterruptedException {
        m.log("Running PullLever");

        // Pull lever if Equipment and items are correct for player.

            m.log("walking To Lever");
            do {
                m.getWalking().walk(LeverTile);
            } while (LeverTile.distance(m.myPosition()) > 1);
            if (LeverTile.distance(m.myPosition()) < 3) {
                interactions.PullLever();
                do {
                    m.sleep(500);
                    LeverPulled = true;
                }  while(!cutWebNode.WildyLeverZone.contains(m.myPosition()));
//                m.sleep(10000);
            }

        return 0;
    }


}
