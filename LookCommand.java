public class LookCommand implements Command {
    Level level;
    Level.Room room;


    public LookCommand (Level level) {
        this.level = level;
    }

    @Override
    public void init(String username) {
    }

    @Override
    public boolean execute(Player p) {
        room = p.getCurrentRoom();
        if (room.getItems() != null) System.out.println("These are the items in your current room: " + room.getItems().toString());
        return false;
    }
}