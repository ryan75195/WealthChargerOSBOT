import java.awt.Graphics2D;

import org.osbot.rs07.script.*;
import org.osbot.rs07.script.ScriptManifest;

@ScriptManifest(author = "Ryan", info = "Wealth Charger Port", name = "Wealth Charger Testing Build", version = 1, logo = "")


public class main extends Script {

    private Node[] nodes;

    @Override

    public void onStart() {
        nodes = new Node[]{

                new EdgevileBankNode(this),
                new PullLever(this),
                new CutWebNode(this),
                new WalkToFountain(this),
                new FountainNode(this),
                new TeleportSpotNode(this),

        };


    }

    @Override

    public int onLoop() throws InterruptedException {
        for (Node node : nodes) {
            if (node.validate()) {
                return node.execute();
            }
        }

       log("No node valid");
        return random(200, 300);
    }

    @Override

    public void onExit() {
    }

    @Override

    public void onPaint(Graphics2D g) {

    }

}