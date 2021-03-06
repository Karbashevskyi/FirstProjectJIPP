package main;

import java.util.ArrayList;

class Hotel {

    protected ArrayList<Client> theClientsList;
    protected ArrayList<Room> theRoomsList;
	
	Hotel() {

        theClientsList = new ArrayList<>();
        theRoomsList = new ArrayList<>();

	}

    protected int getLastRoomId() {

	    if (checkTheRoomsList()) {

            return theRoomsList.get(theRoomsList.size() - 1).getId();

        }

        return 0;

    }

    protected boolean checkTheRoomsList() {

        if (theRoomsList.isEmpty()) {

            System.out.println("We no have rooms.");
            return false;

        } else {

            return true;

        }

    }

    protected boolean checkTheClientsList() {

        if (theClientsList.isEmpty()) {

            System.out.println("We no have clients.");
            return false;

        } else {

            return true;

        }

    }

    protected void showAllNeedCleaningRooms() {

        System.out.println("---------------------");
        System.out.println("- All NEED CLEANING ROOMS");
        System.out.println("---------------------");

        if (checkTheRoomsList()) {

            int countNeedCleaningRooms = 0;

            for (Room room: theRoomsList) {

                if (room.getStatus() == StatusRoom.NEED_CLEANING) {

                    countNeedCleaningRooms += 1;
                    System.out.println(room);

                }

            }

            if (countNeedCleaningRooms == 0) {

                System.out.println("Sorry. We no have need cleaning rooms.");

            } else {

                System.out.println("We have need cleaning: " + countNeedCleaningRooms + " rooms.");

            }

        }

        System.out.println();

    }

//    Can use: Receptionist, Client.

    /**
     * @param id
     * @param client_id
     * @return
     */
    protected boolean goReservation(int id, int client_id) {

        System.out.print("Reservation for client id: " + client_id + ", room id: " + id + ", is ");

        if (checkTheRoomsList()) {

            for (Room room : theRoomsList) {

                if (room.getId() == id) {

                    if (room.getStatus() == StatusRoom.FREE) {

                        if (room.goReservation(client_id)) {

                            System.out.println("confirmed.");
                            return true;

                        }

                    }

                }

            }

        }

        System.out.println("nod confirmed.");
        System.out.println();
        return false;

    }

//    Can use: Receptionist, Client.

    /**
     * @param id
     * @return
     */
    protected boolean goCancelReservation(int id) {

        System.out.print("Cancel reservation room id: " + id + ", is ");

        if (checkTheRoomsList()) {

            for (Room room : theRoomsList) {

                if (room.getId() == id) {

                    if (room.getStatus() == StatusRoom.RESERVATION) {

                        if (room.goCancelReservation()) {

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

//    Can use: Receptionist, Client.

    protected void showAllFreeRooms() {

        System.out.println("---------------------");
        System.out.println("- All FREE ROOMS");
        System.out.println("---------------------");

        if (checkTheRoomsList()) {

            int countFreeRooms = 0;

            for (Room room: theRoomsList) {

                if (room.getStatus() == StatusRoom.FREE) {

                    countFreeRooms += 1;
                    System.out.println(room);

                }

            }

            if (countFreeRooms == 0) {

                System.out.println("Sorry. We no have free rooms.");

            } else {

                System.out.println("We have free: " + countFreeRooms + " rooms.");

            }

        }

        System.out.println();

    }

//    Can use: Receptionist.

    protected void showAllBusyRooms() {

        System.out.println("---------------------");
        System.out.println("- All BUSY ROOMS");
        System.out.println("---------------------");

        if (checkTheRoomsList()) {

            int countBusyRooms = 0;

            for (Room room: theRoomsList) {

                if (room.getStatus() == StatusRoom.BUSY) {

                    countBusyRooms += 1;
                    System.out.println(room);

                }

            }

            if (countBusyRooms == 0) {

                System.out.println("Sorry. We no have busy rooms.");

            } else {

                System.out.println("We have busy: " + countBusyRooms + " rooms.");

            }

        }

        System.out.println();

    }

//    Can use: Receptionist.

    /**
     * @param client_id
     */
    protected void showAllReservationRooms(int client_id) {

        System.out.println("---------------------");
        System.out.println("- All RESERVATION ROOMS");
        System.out.println("---------------------");

        if (checkTheRoomsList()) {

            int countReservationRooms = 0;

            for (Room room: theRoomsList) {

                if (client_id == 0) {

                    if (room.getStatus() == StatusRoom.RESERVATION) {

                        countReservationRooms += 1;
                        System.out.println(room);

                    }

                } else {

                    if (room.getStatus() == StatusRoom.RESERVATION && room.getClientId() == client_id) {

                        countReservationRooms += 1;
                        System.out.println(room);

                    }

                }

            }

            if (countReservationRooms == 0) {

                System.out.println("Sorry. We no have reservation rooms.");

            } else {

                System.out.println("We have reservation: " + countReservationRooms + " rooms.");

            }

        }

        System.out.println();

    }

//    Can use: Director.

    protected void showAllNoLockedRooms() {

        System.out.println("---------------------");
        System.out.println("- All NO LOCKED ROOMS");
        System.out.println("---------------------");

        if (checkTheRoomsList()) {

            int countNoLockedRoom = 0;

            for (Room room: theRoomsList) {

                if (room.getStatus() != StatusRoom.LOCKED) {

                    countNoLockedRoom += 1;
                    System.out.println(room);

                }

            }

            if (countNoLockedRoom == 0) {

                System.out.println("Sorry. We no have no locked rooms.");

            } else {

                System.out.println("We have no locked: " + countNoLockedRoom + " rooms.");

            }

        }

        System.out.println();

    }

//    Can use: Director.

    protected void showAllLockedRooms() {

        System.out.println("---------------------");
        System.out.println("- All LOCKED ROOMS");
        System.out.println("---------------------");

        if (checkTheRoomsList()) {

            int countLockedRoom = 0;

            for (Room room: theRoomsList) {

                if (room.getStatus() == StatusRoom.LOCKED) {

                    countLockedRoom += 1;
                    System.out.println(room);

                }

            }

            if (countLockedRoom == 0) {

                System.out.println("Sorry. We have no locked rooms.");

            } else {

                System.out.println("We have locked: " + countLockedRoom + " rooms.");

            }

        }

        System.out.println();

    }

    protected void initRooms() {

        addNewRoom(1);
        addNewRoom(2);
        addNewRoom(3);

    }

    /**
     * @param id
     * @return
     */
    protected boolean addNewRoom(int id) {

        if (id != 0) {

            theRoomsList.add(new Room(id));
            return true;

        }

        return false;

    }

    protected void initClients() {

	    addNewClient(1, "Ivan Karbashevskyi", "ER 000000", "+48 111 11 11 11");

    }

    /**
     * @param id
     * @param full_name
     * @param passport
     * @param phone
     */
    protected void addNewClient(int id, String full_name, String passport, String phone) {

	    theClientsList.add(new Client(id, full_name, passport, phone));

    }

}
