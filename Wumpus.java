import java.util.ArrayList;
import java.util.HashMap;

public class Wumpus extends Creature {

    public Wumpus(Level.Room startRoom) {
        this.currentRoom = startRoom;
        name = "wumpus";
    }
    @Override
    public boolean move(Level.Room playerRoom) {
        Level.Room randomNeighbor = playerRoom.getRandomNeighbor();
        if (!randomNeighbor.equals(playerRoom)) {
            currentRoom.removeCreature(name);
            currentRoom = randomNeighbor;
            return true;
        }
        return false;
    }

    public void hunt(String response) {
        if (response.equals("yes")) {
            System.out.println("YOU HAVE SLAYED ME! I AM NOW DEAD -" + name);
            currentRoom.removeCreature(name);
        } else {
            System.out.println("thank you for not killing me -" + name);
        }
    }

    @Override
    public void interact() {
        System.out.println("wump, wump. Don't hunt me, please!");
    }
}
