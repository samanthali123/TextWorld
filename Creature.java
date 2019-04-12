import java.util.ArrayList;

public abstract class Creature {
    protected String name;
    protected Level.Room currentRoom;
    //protected Level l = Main.l;
    protected ArrayList<Creature> creatures = Level.creatures;

    public abstract boolean move(Level.Room playerRoom);
    public abstract void interact();

    public Level.Room getCurrentRoom() {
        return currentRoom;
    }

    public String getName(){
       return name;
   }

   public void setName(String name){
       this.name = name;
   }

   public String toString() {
       return name;
   }

}
