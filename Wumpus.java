import java.util.ArrayList;
import java.util.HashMap;

public class Wumpus extends Creature {

    public Wumpus(Level.Room startRoom) {
        this.currentRoom = startRoom;
        name = "wumpus";
    }
    @Override
    public boolean move(Level.Room moveRoom) {
        for (String key : moveRoom.getNeighbors().keySet()) {
            Level.Room neighbor = moveRoom.getNeighbors().get(key);
            if (!neighbor.equals(moveRoom)) {
                currentRoom.removeCreature(name);
                currentRoom = neighbor;
                return true;
            }
        }
        return false;
    }

    public void hunt(String response, ArrayList<Creature> creatures) {
        if (response.equals("yes")) {
            System.out.println("YOU HAVE SLAYED ME! I AM NOW DEAD -" + name);
            currentRoom.removeCreature(name);
            creatures.remove(name);
            this.creatures.remove(name);
        } else {
            System.out.println("thank you for not killing me -" + name);
        }
    }

    @Override
    public void interact() {
        System.out.println("wumpus: 'wump, wump. Don't hunt me, please!'");
    }
}
