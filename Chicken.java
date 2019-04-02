import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Chicken implements Creature {
    private Level.Room currentRoom;
    String name;

    public Chicken(Level.Room startRoom) {
        this.currentRoom = startRoom;
        name = "chicken";
    }

    @Override
    public void move(Level.Room playerRoom) {
        HashMap<String, Level.Room> availRooms = currentRoom.getNeighbors();
        ArrayList<Level.Room> rooms = new ArrayList<>(availRooms.values());
        currentRoom = rooms.get((int)(Math.random()*rooms.size()));
    }

    @Override
    public void interact() {
        //Talk to chicken
        System.out.println("BOK! BOK! FEED ME! PLEASE DON'T EAT ME!");
        //name chicken
        //set name to whateve is typed in
        //follow chicken
        //move chicken and display room that yours is in
    }

    public Level.Room getCurrentRoom() {
        return currentRoom;
    }


}
