import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Level l = new Level();

        l.addRoom("hall", "long narrow hallway");
        l.addRoom("closet", "small room for storing clothes");
        l.addRoom("dungeon", "dark musty dungeon home to a dragon");
        l.addRoom("stable", "home for horses");

        l.addDirectedEdge("hall", "dungeon");
        l.addUndirectedEdge("hall", "closet");
        l.addUndirectedEdge("closet", "stable");
        l.addDirectedEdge("stable", "dungeon");
        l.addUndirectedEdge("dungeon", "closet");

        l.getRoom("hall").addItem(new Item("frame", "to decorate your pictures"));
        l.getRoom("hall").addItem(new Item("flowers", "pretty and fragrant"));
        l.getRoom("dungeon").addItem(new Item("knife", "to defend yourself against the dragon"));
        l.getRoom("closet").addItem(new Item("pants", "so your legs don't get cold"));
        l.getRoom("closet").addItem(new Item("fanny pack", "to store small things on your adventure"));

        Creature c = new Chicken(l.getRoom("hall"));
        l.getRoom("hall").addCreature(c);

        String answer = JOptionPane.showInputDialog("Please enter your name and a description of yourself (\"name, description\")");

        int comma = answer.indexOf(",");
        Player p = new Player(answer.substring(0, comma), answer.substring(comma + 1));

        p.setCurrentRoom(l.getRoom("hall"));

        String response = "";
        Scanner in = new Scanner(System.in);

        System.out.println(
                answer.substring(0, comma) + ", you can choose to:" +
                        "\ngo to another room (type \"go <roomname>\")" +
                        "\nview the items in the room you are currently in (type \"look\")" +
                        "\nview the creatuers in the room you are currently in (type \"peek\")" +
                        "\nadd a room (type \"add room <roomname> (description)\")" +
                        "\npick up an item (type \"take <itemname>\")" +
                        "\ndrop an item (type \"drop <itemname>\")" +
                        "\nor quit (type quit)"
        );

        do {
            System.out.println("You are currently in the " + p.getCurrentRoom().toString());
            System.out.println("What do you want to do? >");
            response = in.nextLine();

            if (response.substring(0, 2).equals("go")) {
                int index1 = response.indexOf("<");
                int index2 = response.indexOf(">");

                if (l.getRoom(response.substring(index1 + 1, index2)) != null)
                    p.setCurrentRoom(l.getRoom(response.substring(index1 + 1, index2)));
                else System.out.println("That is not a valid room, please pick another room.");

                c.move();
                if (((Chicken) c).getCurrentRoom().equals(p.getCurrentRoom())) {
                    c.interact();
                }

            } else if (response.substring(0, 4).equals("look")) {
                if (p.getCurrentRoom().getItems() != null) System.out.println("avaliable items: " + p.getCurrentRoom().getItems().toString());
                else System.out.println("This rooom has no items");

            } else if (response.substring(0, 4).equals("peek")) {
                System.out.println("These are the creatures in your current room: " + p.getCurrentRoom().getCreatures().toString());

            }else if (response.substring(0, 8).equals("add room")) {
                int index1 = response.indexOf("<");
                int index2 = response.indexOf(">");

                int index3 = response.indexOf("(");
                int index4 = response.indexOf(")");

                if (index1 != -1 && index2 != -1 && index3 != -1 && index4 != -1) {
                    Level.Room newRoom = new Level.Room(response.substring(index1 + 1, index2), response.substring(index3 + 1, index4));
                    l.addRoom(newRoom.getName(), newRoom.getDescription());
                    l.addDirectedEdge(p.getCurrentRoom().getName(), newRoom.getName());
                    System.out.println("New room just added!");
                }

            } else if (response.substring(0, 4).equals("take")) {
                int index1 = response.indexOf("<");
                int index2 = response.indexOf(">");

                if (index1 != -1 && index2 != -1 && p.getCurrentRoom().getItems().contains(response.substring(index1 + 1, index2))) {
                    p.addItem(p.getCurrentRoom().removeItem(response.substring(index1 + 1, index2)));
                    System.out.println("You just grabbed the " + response.substring(index1 + 1, index2));
                } else {
                    System.out.println("That item does not exist, please choose another");
                }

            } else if (response.substring(0, 4).equals("drop")) {
                int index1 = response.indexOf("<");
                int index2 = response.indexOf(">");

                if (index1 != -1 && index2 != -1 && p.getItems().contains(response.substring(index1 + 1, index2))) {
                    Item i = new Item(null, null);

                    for (Item item : p.getItems()) {
                        if (item.getName().equals(response.substring(index1 + 1, index2))) {
                            i = item;
                            p.getCurrentRoom().addItem(p.removeItem(item));
                        }
                    }

                    if (!i.getName().equals(null)) System.out.println("You just dropped the " + response.substring(index1 + 1, index2));
                    else System.out.println("You don't have this item");

                }
            } else if (response.equals("quit")) {
                System.out.println("See you soon!");

            } else {
                System.out.println("You can choose to:\n-go to another room (type \"go <roomname>\")\n-view the rooms you can enter (type \"look\")\n-add a room (type \"add room <roomname>\")\n-quit (type quit)");
            }

        } while (!response.equals("quit"));
    }
}