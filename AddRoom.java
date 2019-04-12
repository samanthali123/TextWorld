public class AddRoom implements Command {
    Level level;
    String roomName;
    String roomDescription;

    public AddRoom (Level level) {
        this.level = level;
    }

    @Override
    public void init(String username) {
        this.roomName = getRoomName(username);
        this.roomDescription = getRoomDescription(username);
    }

    private String getRoomName(String username) {
        int index1 = username.indexOf("<");
        int index2 = username.indexOf(">");

        if (index1 != -1 && index2 != -1) {
            return username.substring(index1 + 1, index2);
        }
        return null;
    }

    private String getRoomDescription(String username) {
        int index1 = username.indexOf("(");
        int index2 = username.indexOf(")");

        if (index1 != -1 && index2 != -1) {
            return username.substring(index1 + 1, index2);
        }
        return null;
    }

    @Override
    public boolean execute(Player p) {
        boolean success = level.addNewRoom(roomName, roomDescription, p.getCurrentRoom());
        if (success) System.out.println("Room " + roomName + ", " + roomDescription + " just added!");
        else System.out.println("the room you want to add could not be added :(");
        return success;
    }
}