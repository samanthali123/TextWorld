public class PeekCommand implements Command {
    Level level;

    public PeekCommand (Level level) {
        this.level = level;
    }

    @Override
    public void init(String username) {}

    @Override
    public boolean execute() {
        Player p = level.getPlayer();
        System.out.println("These are the creatures in your current room: " + p.getCurrentRoom().getCreaturesNames());
        return true;
    }
}