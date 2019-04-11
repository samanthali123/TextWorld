public class DropCommand implements Command {
    Level level;
    String itemName;

    public DropCommand (Level level) {
        this.level = level;
    }

    @Override
    public void init(String username) {
        this.itemName = getItemName(username);
    }

    private String getItemName(String username) {
        int index1 = username.indexOf("<");
        int index2 = username.indexOf(">");

        if (index1 != -1 && index2 != -1) return username.substring(index1 + 1, index2);

        return null;
    }

    @Override
    public boolean execute() {
        Player p = level.getPlayer();
        boolean success = p.dropItem(itemName);

        if (success) System.out.println("You just dropped the " + itemName);
        else System.out.println("You don't have this item");

        return false;
    }
}
