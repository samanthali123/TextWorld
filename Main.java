import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static Player p;
    static Level l = new Level();
    static HashMap<String, Command> commands = new HashMap<>();

    public static void main(String[] args) {

        initCommands();

        String answer = JOptionPane.showInputDialog("Please enter your name and a description of yourself (\"name, description\")");

        int comma = answer.indexOf(",");
        p = new Player(answer.substring(0, comma), answer.substring(comma + 1));

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

            Command command = lookUpCommand(response);
            command.execute();

        } while (Player.isPlaying);
    }

    private static void initCommands() {
        commands.put("take", new TakeCommand(l));
        commands.put("peek", new PeekCommand(l));
        commands.put("go", new GoCommand(l));
        commands.put("add-room", new AddRoom(l));
        commands.put("drop", new DropCommand(l));
        commands.put("look", new LookCommand(l));
        commands.put("quit", new QuitCommand());
    }

    private static Command lookUpCommand(String response) {
        String commandWord = getFirstWorldIn(response);

        Command c = commands.get(commandWord);
        if (c == null) return new EmptyCommand();

        c.init(response);

        return c;
    }

    private static String getFirstWorldIn(String response) {
        String[] words = response.split(" ");
        return words[0];
    }
}