import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.event.WalkingEvent;
import org.osbot.rs07.event.WebWalkEvent;
import org.osbot.rs07.utility.Condition;

public class WalkToFountain extends Node {

    Position HellSafeSpot = new Position(3179, 3961, 0);
    Position NorthWeb = new Position(3158, 3963, 0);
    Position TopWildyHill = new Position(3242, 3958, 0);
    Position TopWildyHill2 = new Position(3252, 3963, 0);
    Position House1 = new Position(3275, 3954, 0);
    Position House2 = new Position(3292, 3950, 0);
    Position RuneFountain = new Position(3374, 3891, 0);
    Position Level40 = new Position(3344, 3839, 0);
    Position Level35 = new Position(3345, 3798, 0);
    Position Level30 = new Position(3344, 3758, 0);
    Position[] WildyPath = new Position[]{NorthWeb, HellSafeSpot, TopWildyHill, TopWildyHill2, House1, House2, RuneFountain, Level40, Level35, Level30};
    String[] PathText = new String[]{"North Web", "Hell Hound Safe Spot", "Hill Position 1", "Hill Position 2", "House Position 1", "Hill Position 2", "Rune Fountain", "Level 40", "Level 35", "Level 30"};
    Boolean[] Walked = new Boolean[]{false, false, false, false, false, false, false, false, false, false};
    Interactions interactions = new Interactions(m);
    Position ClosestPosition = new Position(0, 0, 0);
    int CurrentPosCount = 0;

    public WalkToFountain(main m) {
        super(m);
    }

    @Override
    public boolean validate() {
        return m.myPosition().getY() > Level30.getY() + 1;
    }

    @Override
    public int execute() throws InterruptedException {
        m.log("Running WalkToFountain");
        for (int i = 0; i < WildyPath.length; i++) {
            if (WildyPath[i].distance(m.myPosition()) < ClosestPosition.distance(m.myPosition())) {
                ClosestPosition = WildyPath[i];
                CurrentPosCount = i;
            }
        }


        for (int i = CurrentPosCount + 1; i < WildyPath.length; i++) {
            m.log("Walking To: " + PathText[i]);
                WebWalkEvent WalkthePath = new WebWalkEvent(WildyPath[i]);
                WalkthePath.setBreakCondition(new Condition() {

                    @Override
                    public boolean evaluate() {
                        return (interactions.CheckStamina == true);
                    }
                });
                if(i > 7){
                    WalkthePath.useSimplePath();
                }
                m.execute(WalkthePath);
                if(interactions.CheckStamina == true) {
                    interactions.DrinkPotion();
                }
        }


        return 0;
    }
}
