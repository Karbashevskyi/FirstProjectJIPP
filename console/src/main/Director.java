package main;

public class Director extends Hotel{

    private String full_name;

    Director (String full_name) {

        this.full_name = full_name;

    }

    public String getFullName() {

        return full_name;

    }

    /**
     * @param id
     * @return
     */
    public boolean goLocked(int id) {

        System.out.print("Locked for room id: " + id + ", is ");

        for (Room room : theRoomsList) {

            if (room.getId() == id) {

                if (room.getStatus() != StatusRoom.BUSY) {

                    room.goLocked();
                    System.out.println("confirmed.");
                    return true;

                }

            }

        }

        System.out.println("nod confirmed.");
        System.out.println();
        return false;

    }

    /**
     * @param id
     * @return
     */
    public boolean goCancelLocked(int id) {

        System.out.print("Cancel locked for room id: " + id + ", is ");

        for (Room room : theRoomsList) {

            if (room.getId() == id) {

                if (room.getStatus() == StatusRoom.LOCKED) {

                    room.goCancelLocked();
                    System.out.println("confirmed.");
                    return true;

                }

            }

        }

        System.out.println("nod confirmed.");
        System.out.println();
        return false;

    }

}
