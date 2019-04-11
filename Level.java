import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Level {
    public static Player p = Main.p;
    private HashMap<String, Room> nodes;
    public static ArrayList<Creature> creatures;

    public Level() {
        nodes = new HashMap<>();
        addRoom("hall", "long narrow hallway");
        addRoom("closet", "small room for storing clothes");
        addRoom("dungeon", "dark musty dungeon home to a dragon");
        addRoom("stable", "home for horses");

        addDirectedEdge("hall", "dungeon");
        addUndirectedEdge("hall", "closet");
        addUndirectedEdge("closet", "stable");
        addDirectedEdge("stable", "dungeon");
        addUndirectedEdge("dungeon", "closet");

        getRoom("hall").addItem(new Item("frame", "to decorate your pictures"));
        getRoom("hall").addItem(new Item("flowers", "pretty and fragrant"));
        getRoom("dungeon").addItem(new Item("knife", "to defend yourself against the dragon"));
        getRoom("closet").addItem(new Item("pants", "so your legs don't get cold"));
        getRoom("closet").addItem(new Item("fanny pack", "to store small things on your adventure"));


        creatures = new ArrayList<>();
        Creature c = new Chicken(getRoom("hall"));
        getRoom("hall").addCreature(c);
        creatures.add(c);

        Creature w = new Wumpus(getRoom("closet"));
        getRoom("closet").addCreature(w);
        creatures.add(w);

        Creature s = new Popstar(getRoom("dungeon"));
        getRoom("dungeon").addCreature(s);
        creatures.add(s);
    }

    public void addRoom(String name, String desc) {
        Room n = new Room(name, desc);
        nodes.put(name, n);
    }

    public boolean addNewRoom(String roomName, String roomDescription) {
        for (String key : nodes.keySet()) {
            if (nodes.get(key).getName().equals(roomName)) return false;
        }
        addRoom(roomName, roomDescription);
        return true;
    }

    public void addDirectedEdge(String name1, String name2) {
        Room n1 = getRoom(name1);
        Room n2 = getRoom(name2);
        n1.addNeighbor(n2);
    }

    public void addUndirectedEdge(String name1, String name2) {
        Room n1 = getRoom(name1);
        Room n2 = getRoom(name2);

        n1.addNeighbor(n2);
        n2.addNeighbor(n1);
    }

    public Room getRoom(String name) {
        return nodes.get(name);
    }

    public void destroyRoom(String name) {
        nodes.remove(name);
        for (String s : nodes.keySet()) {
            if (nodes.get(s).getNeighbor(s).equals(name)) {
                nodes.get(s).removeNeighbor(name);
            }

            if (nodes.get(s).getNeighbors().size() == 1 && nodes.get(s).getNeighbor(name) != null) {
                nodes.remove(s);
            }
        }
    }

    public Player getPlayer() {
        return p;
    }



    public static class Room {
        private String name;
        private String description;
        private HashMap<String, Room> neighbors;
        private ArrayList<Item> items;
        private ArrayList<Creature> creatures;

        public Room(String name, String description) {
            neighbors = new HashMap<>();
            this.name = name;
            this.description = description;
            items = new ArrayList<>();
            creatures = new ArrayList<>();
        }

        @Override
        public String toString() {
            return "Room " + name + ", " + description;
        }

        public void addNeighbor(Room n) {
            String name = n.getName();
            neighbors.put(name, n);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getNeighborNames() {
            String out = "";

            if (neighbors != null) {
                for (String n : neighbors.keySet()) {
                    out += "\n" + n + "-> " + neighbors.get(n).getDescription() + " ";
                }
            }
            return out;
        }

        public HashMap<String, Room> getNeighbors() {
            return neighbors;
        }

        public Room getNeighbor(String name) {
            return neighbors.get(name);
        }

        public Room getRandomNeighbor(){
            ArrayList<Level.Room> rooms = new ArrayList<>(neighbors.values());
            int num = (int)(Math.random()*rooms.size());
            Room next = rooms.get(num);
            return next;
        }

        public void removeNeighbor(String name) {
            neighbors.remove(name);
        }

        public ArrayList<Item> getItems() {
            return items;
        }

        public void addItem(Item item) {
            items.add(item);
        }

        public Item removeItem(String name) {
            for (Item i : items) {
                if (i.getName().equals(name)) {
                    Item out = i;
                    items.remove(i);
                    return out;
                }
            }
            return null;
        }

        public void addCreature(Creature c) {
            creatures.add(c);
        }

        public ArrayList<Creature> getCreatures() {
            return creatures;
        }


        public Creature removeCreature(String name) {
            for (Creature c :
                    creatures) {
                if (c.getName().equals(name)) {
                    creatures.remove(c);
                    return c;
                }
            }
            return null;
        }

        public String getCreaturesNames() {
            int chickenCount = 0;
            int wumpusCount = 0;

            String out = "";

            for (Creature c : creatures) {
                if (c.getName().equals("chicken")) chickenCount++;
                else if (c.getName().equals("wumpus")) wumpusCount++;
            }

            out = chickenCount + " chickens and " + wumpusCount + " wumpi";
            return out;
        }

        public boolean hasNeighbor(Room playerRoom) {
            for (String key : neighbors.keySet()) {
                if (neighbors.get(key).equals(playerRoom)) return true;
            }
            return false;
        }
    }
}