public class GoCommand implements Command {
    Level level;
    String roomName;

    public GoCommand(Level l) {
        this.level = l;
    }

    @Override
    public void init(String username) {
        this.roomName = getRoom(username);
    }

    private String getRoom(String username) {
        int index1 = username.indexOf("<");
        int index2 = username.indexOf(">");

        if (index1 != -1 && index2 != -1) return username.substring(index1 + 1, index2);

        return null;
    }

    @Override
    public boolean execute() {
        Player p = level.getPlayer();
        Level.Room room = level.getRoom(roomName);
        if (p.move(room)) System.out.println("You just moved to the " + roomName);
        else System.out.println("That room does not exist, please choose another");
        return false;
    }
}
