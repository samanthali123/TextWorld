public class QuitCommand implements Command {
    String username;

    @Override
    public void init(String username) {
        this.username = username;
    }

    @Override
    public boolean execute(Player p) {
        if (username.equals("quit")) {
            System.out.println("See you soon!");
            Player.isPlaying = false;
            return true;
        }
        return false;
    }
}
