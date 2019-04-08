import java.util.ArrayList;

public class Player {
    private String name;
    private String description;
    private static ArrayList<Item> items;
    private static Level.Room currentRoom;

    public Player(String name, String description) {
        this.name = name;
        this.description = description;
        items = new ArrayList<>();
        currentRoom = getCurrentRoom();
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

    public boolean takeItem() {
        for (int i = 0; i < currentRoom.getItems().size(); i++) {
            if (currentRoom.getItems().get(i).getName().equals())
        }
    }
}