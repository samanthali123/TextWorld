public class LookCommand implements Command {
    Level level;
    Level.Room room;


    public LookCommand (Level level) {
        this.level = level;
    }

    @Override
    public void init(String username) {
        Player p = level.getPlayer();
        room = p.getCurrentRoom();
    }

    @Override
    public boolean execute() {
        if (room.getItems() != null) System.out.println(room.getItems().toString());
        return false;
    }
}