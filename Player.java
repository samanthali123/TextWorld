import java.util.ArrayList;

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
                items.remove(items.get(i));
                currentRoom.addItem(items.get(i));
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean move(Level.Room playerRoom) {

        for (Creature c : creatures) {
            c.move(currentRoom);
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

    }
}