import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.event.WebWalkEvent;

public class EdgevileBankNode extends Node{

    public boolean InventorySetup;
    public Area EdgevilleBankZone = new Area(3087, 3502, 3099, 3465);
    public EdgevileBankNode(main m) {
        super(m);
    }

    @Override
    public boolean validate() {
        return (EdgevilleBankZone.contains(m.myPlayer()) && !InventorySetup);
    }

    @Override
    public int execute() throws InterruptedException {

        m.log("Running EdgevileBankNode");

        // Equipment Check.

        if (!((m.getEquipment().contains(i -> i != null && i.getName().contains("glory(")))
                && m.getEquipment().contains("Bronze dagger"))) {

            m.getBank().open();
            m.sleep(1000);
            m.getBank().depositAll();
            m.getBank().withdraw("Bronze dagger", 1);
            m.sleep(500);
            if (m.getBank().contains(i -> i != null && i.getName().contains("glory("))) {
                m.getBank().withdraw(i -> i != null && i.getName().contains("glory("), 1);
            }

            m.sleep(500);
            m.getBank().close();
            m.log("equipping dagger");
            m.getInventory().interact("Wield", "Bronze dagger");
            m.log("equipping glory");
            m.sleep(500);

            if (m.getInventory().contains(i -> i != null && i.getName().contains("glory"))) {
                m.getInventory().interactWithNameThatContains("Wear", "glory");
            }

        } else {


            if ((m.getInventory().contains(i -> i != null && i.getName().contains("wealth") && !(i.getName().contains("(5)"))))
                    && m.getInventory().contains(i -> i != null && i.getName().contains("Stamina"))) {
                InventorySetup = true;
            } else {

                // Withdraw Items For Run.

                m.log("Getting Items");
                m.getBank().open();
                m.sleep(1000);
                m.getBank().depositAll();

                if (m.getBank().contains(i -> i != null && i.getName().contains("Stamina"))) {
                    m.log("Withdrawing Potion");
                    m.getBank().withdraw(i -> i != null && i.getName().contains("Stamina") && !(i.getName().contains("potion(1)")), 1);
                    m.sleep(250);


                } else {
                    m.log("Error, cannot find any potions");
                }

                InventorySetup = true;
            }
            m.sleep(1000);
            m.log("Withdrawing Rings");
            m.getBank().withdraw("Ring of Wealth", 1);
            m.sleep(500);
            m.getBank().close();
        }
        return 0;
    }
}