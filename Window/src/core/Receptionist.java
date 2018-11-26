package core;

class Receptionist extends Hotel {

    private String full_name;
    private int idSelectedRoom;

    Receptionist (String full_name) {

        this.full_name = full_name;

    }

    public String getFullName() {

        return full_name;

    }

    protected int getLastClientId() {

        return theClientsList.get(theClientsList.size() - 1).getId();

    }

    public void showAllClients() {

        System.out.println("---------------------");
        System.out.println("- All CLIENTS");
        System.out.println("---------------------");

        if (checkTheClientsList()) {

            int countClients = 0;

            for (Client client: theClientsList) {

                countClients += 1;
                System.out.println(client.getId() + " " +client.getFullName());

            }

            if (countClients != 0) {

                System.out.println("We have: " + countClients + " clients.");

            }

        }

        System.out.println();

    }

    public boolean goCheckIn(int id) {

        System.out.print("Check in room id: " + id + ", is ");

        if (checkTheRoomsList()) {

            for (Room room : theRoomsList) {

                if (room.getId() == id) {

                    if (room.getStatus() == StatusRoom.FREE || room.getStatus() == StatusRoom.RESERVATION) {

                        if (room.goCheckIn()) {

                            System.out.println("confirmed.");
                            return true;

                        }

                    }

                }

            }

        }

        System.out.println("no confirmed.");
        System.out.println();
        return false;

    }

    /**
     * @param id
     * @return
     */
    public boolean goCheckOut(int id) {

        System.out.print("Check out room id: " + id + ", is ");

        if (checkTheRoomsList()) {

            for (Room room : theRoomsList) {

                if (room.getId() == id) {

                    if (room.getStatus() == StatusRoom.BUSY) {

                        if (room.goCheckOut()) {

                            System.out.println("confirmed.");
                            return true;

                        }

                    }

                }

            }

        }

        System.out.println("no confirmed.");
        System.out.println();
        return false;

    }

    public int getIdSelectedRoom() {

        return idSelectedRoom;

    }

    public boolean setIdSelectedRoom(int room_id) {

        if (room_id != 0) {

            idSelectedRoom = room_id;
            return true;

        }

        return false;

    }

}
