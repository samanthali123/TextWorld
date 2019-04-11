public class Popstar extends Creature {

    public Popstar(Level.Room startRoom) {
        this.currentRoom = startRoom;
        name = "popstar";
    }

    @Override
    public boolean move(Level.Room playerRoom) {
        if (currentRoom.hasNeighbor(playerRoom)) {
            currentRoom = playerRoom;
            return true;
        } else {
            for (String key : playerRoom.getNeighbors().keySet()) {
                if (playerRoom.getNeighbors().get(key).equals(currentRoom.getNeighbor(key)))
                    currentRoom = playerRoom.getNeighbor(key);
            }
        }
        return false;
    }

        @Override
        public void interact () {
            System.out.println("Howdy! Would you like to get my autograph???");
        }
    }
