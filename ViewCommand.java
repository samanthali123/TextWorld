import javax.swing.text.View;

public class ViewCommand implements Command {
    Level l;

    public ViewCommand (Level l) {
        this.l = l;
    }

    @Override
    public void init(String username) {
    }

    @Override
    public boolean execute(Player p) {
        System.out.println("In your backpack you currently have these items: " + p.getItems().toString());
        return true;
    }
}
