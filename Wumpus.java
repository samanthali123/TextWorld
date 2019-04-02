import java.util.ArrayList;
import java.util.HashMap;

public class Wumpus implements Creature {
    private Level.Room currentRoom;

    @Override
    public void move(Level.Room playerRoom) {
        HashMap<String, Level.Room> availRooms = currentRoom.getNeighbors();
        ArrayList<Level.Room> rooms = new ArrayList<>(availRooms.values());
        if (!rooms.get((int)(Math.random()*rooms.size())).equals(playerRoom)) {
            currentRoom = rooms.get((int)(Math.random()*rooms.size()));
        }
    }

    @Override
    public void interact() {

    }
}
