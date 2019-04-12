import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class PeekCommand implements Command {
    Level level;

    public PeekCommand (Level level) {
        this.level = level;
    }

    @Override
    public void init(String username) {}

    @Override
    public boolean execute(Player p) {
        System.out.println("These are the creatures in your current room: " + p.getCurrentRoom().getCreaturesNames(p.getCurrentRoom()));
        Level.Room.creatureInteract(p.getCurrentRoom());
        return true;
    }


}