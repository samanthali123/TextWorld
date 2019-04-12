import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Chicken extends Creature {


    public Chicken(Level.Room startRoom) {
        this.currentRoom = startRoom;
        name = "chicken";
    }

    @Override
    public boolean move(Level.Room playerRoom) {
        HashMap<String, Level.Room> availRooms = currentRoom.getNeighbors();
        ArrayList<Level.Room> rooms = new ArrayList<>(availRooms.values());
        currentRoom.removeCreature(name);
        currentRoom = rooms.get((int)(Math.random()*rooms.size()));
        return true;
    }

    @Override
    public void interact() {
        System.out.println("chicken: 'BOK! BOK! FEED ME! PLEASE DON'T EAT ME!'");
    }

    public void eat(String response, ArrayList<Creature> creatures) {
        if (response.equals("yes")) {
            System.out.println("I hope I was tasty because now I am dead -" + name);
            currentRoom.removeCreature(name);
            creatures.remove(name);
            this.creatures.remove(name);
        }
        else System.out.println("Thank you for not eating me!!! -" + name);
    }

    public Level.Room getCurrentRoom() {
        return currentRoom;
    }


}
