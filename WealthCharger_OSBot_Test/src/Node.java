import org.osbot.rs07.event.WebWalkEvent;

public abstract class Node {

protected final main m;

    public Node(main m){
            this.m = m;
    }
    public abstract boolean validate() throws InterruptedException;
    public abstract int execute() throws InterruptedException;

}

