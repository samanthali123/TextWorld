public class TakeCommand implements Command {
    Level level;
    String itemName;

    public TakeCommand(Level level) {
        this.level = level;
    }

    @Override
    public void init(String username) {
        this.itemName = getItemName(username);
    }

    @Override
    public boolean execute() {
        Player p = level.getPlayer();
        boolean success = p.takeItem();
        return success;
    }
}
