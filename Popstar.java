public class Popstar extends Creature {

    public Popstar(Level.Room startRoom) {
        this.currentRoom = startRoom;
        name = "popstar";
    }

    @Override
    public boolean move(Level.Room moveRoom) {
        if (currentRoom.hasNeighbor(moveRoom)) {
            currentRoom = moveRoom;
            return true;
        } else {
            for (String key : moveRoom.getNeighbors().keySet()) {
                if (moveRoom.getNeighbors().get(key).equals(currentRoom.getNeighbor(key)))
                    currentRoom = moveRoom.getNeighbor(key);
            }
        }
        return false;
    }

        @Override
        public void interact () {
            System.out.println("popstar: 'Howdy! Would you like to get my autograph??? Please get my autograph!!! :))'");
        }
    }
