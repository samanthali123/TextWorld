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

    private String getItemName(String username) {
        int index1 = username.indexOf("<");
        int index2 = username.indexOf(">");

        if (index1 != -1 && index2 != -1) return username.substring(index1 + 1, index2);

        return null;
    }


    @Override
    public boolean execute() {
        Player p = level.getPlayer();
        boolean success = p.takeItem(itemName);
        if (success) System.out.println("You just grabbed the " + itemName);
        else System.out.println("That item does not exist, please choose another");
        return success;
    }
}
