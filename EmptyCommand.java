public class EmptyCommand implements Command {

    @Override
    public void init(String username) {
    }

    @Override
    public boolean execute(Player p) {
        System.out.println(
                "you can choose to:" +
                        "\ngo to another room (type \"go <roomname>\")" +
                        "\nview the items in the room you are currently in (type \"look\")" +
                        "\nview the creatures in the room you are currently in (type \"peek\")" +
                        "\nview the items you are carrying (type \"view\")" +
                        "\nadd a room (type \"add-room <roomname> (description)\")" +
                        "\npick up an item (type \"take <itemname>\")" +
                        "\ndrop an item (type \"drop <itemname>\")" +
                        "\nor quit (type quit)"
        );
        return true;
    }
}
