import org.osbot.rs07.api.model.RS2Object;

public class Interactions{
    main m;
    public Interactions(main m) {
        this.m = m;

    }

public boolean CheckStamina = m.getSettings().getRunEnergy() < 25 &&
        m.getInventory().contains(x -> x != null && x.getName().contains("Stamina"));



    public void ChargeRing() throws InterruptedException {

        m.log("Charging Ring");
        RS2Object Fountainofrune = m.getObjects().closest("Fountain of Rune");
        m.getInventory().getItem("Ring of wealth").interact("Use");
        Fountainofrune.interact();
        m.sleep(1000);
    }

    public void DrinkPotion(){
       // if (CheckStamina == true) {
            m.getInventory().interactWithNameThatContains("Drink", "Stamina");
      //  }
    }

    public void PullLever() {
        m.log("pulling");
        PullLever pullLever = new PullLever(m);
        RS2Object lever = m.getObjects().closest("Lever");
        lever.interact("Pull");
        pullLever.LeverPulled = true;
    }
}
