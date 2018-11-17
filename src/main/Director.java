package main;

public class Director extends Hotel{

    private String full_name;

    Director (String full_name) {

        this.full_name = full_name;

    }

    public String getFullName() {

        return full_name;

    }

    public void addNewRoom(int id) {

        theRoomsList.add(new Room(id));

    }

    public void goLocked(int id) {

        for (Room room : theRoomsList) {

            if (room.getId() == id) {

                if (room.getStatus() != StatusRoom.BUSY) {

                    room.goLocked();
                    break;

                }

            }

        }

    }

    public void goCancelLocked(int id) {

        for (Room room : theRoomsList) {

            if (room.getId() == id) {

                if (room.getStatus() == StatusRoom.LOCKED) {

                    room.goCancelLocked();
                    break;

                }

            }

        }

    }

}
