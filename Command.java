public interface Command {
    public void init (String username);
    public boolean execute(Player p);
}
