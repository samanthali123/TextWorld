import java.util.ArrayList;
import java.util.Scanner;

public class Player extends Creature {
    private String name;
    private String description;
    private static ArrayList<Item> items;
    private static Level.Room currentRoom;
    static boolean isPlaying;

    public Player(String name, String description) {
        this.name = name;
        this.description = description;
        items = new ArrayList<>();
        currentRoom = getCurrentRoom();
        isPlaying = true;
    }

    public void addItem (Item item) {
        items.add(item);
    }

    public Item removeItem (Item item) {
        Item out = item;
        items.remove(item);
        return out;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public Level.Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Level.Room newRoom) {
        currentRoom = newRoom;
    }

    public boolean takeItem(String itemName) {
        for (int i = 0; i < currentRoom.getItems().size(); i++) {
            if (currentRoom.getItems().get(i).getName().equals(itemName)) {
                items.add(currentRoom.getItems().get(i));
                currentRoom.removeItem(currentRoom.getItems().get(i).getName());
                return true;
            }
        }
        return false;
    }

    public boolean dropItem(String itemName) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equals(itemName)) {
                currentRoom.addItem(items.get(i));
                items.remove(items.get(i));
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean move(Level.Room playerRoom) {

        for (Creature c : creatures) {

            c.move(playerRoom);
//            if (c.getCurrentRoom().equals(playerRoom) && c.getName().equals("wumpus")) {
//                //c = (Wumpus)c;
//                System.out.println("Would you like to hunt this wumpus");
//                Scanner scanner = new Scanner(System.in);
//                String response = scanner.nextLine();
//                ((Wumpus) c).hunt(response);
//            }
//
//            if (c.getCurrentRoom().equals(playerRoom) && c.getName().equals("chicken")) {
//                System.out.println("Would you like to eat this chicken");
//                Scanner scanner = new Scanner(System.in);
//                String response = scanner.nextLine();
//                ((Chicken) c).eat(response);
//            }
        }

        for (String key : currentRoom.getNeighbors().keySet()) {
            if (currentRoom.getNeighbors().get(key).equals(playerRoom)) {
                currentRoom = playerRoom;
                return true;
            }
        }

        return false;
    }

    @Override
    public void interact() {
        interact(currentRoom, creatures);
    }

    public void interact(Level.Room playerRoom, ArrayList<Creature> creatures) {

        for (int i = 0; i < creatures.size(); i++) {
            //if (creatures.get(i).getCurrentRoom().equals(playerRoom)) creatures.get(i).interact();

            if (creatures.get(i).getCurrentRoom().equals(playerRoom) && this.creatures.get(i).getName().equals("wumpus") && creatures.get(i).getName().equals("wumpus")) {
                System.out.println("Would you like to hunt this wumpus");
                Scanner scanner = new Scanner(System.in);
                String response = scanner.nextLine();
                ((Wumpus) creatures.get(i)).hunt(response, creatures);
            }

            if (creatures.get(i).getCurrentRoom().equals(playerRoom) && this.creatures.get(i).getName().equals("chicken") && creatures.get(i).getName().equals("chicken")) {
                System.out.println("Would you like to eat this chicken");
                Scanner scanner = new Scanner(System.in);
                String response = scanner.nextLine();
                ((Chicken) creatures.get(i)).eat(response, creatures);

            }
        }
    }

}