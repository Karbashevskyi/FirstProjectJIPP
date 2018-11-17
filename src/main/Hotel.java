package main;

import java.util.ArrayList;

class Hotel {

    protected ArrayList<Client> theClientsList;
    protected ArrayList<Room> theRoomsList;
	
	Hotel() {

        theClientsList = new ArrayList<>();
        theRoomsList = new ArrayList<>();

//		initRooms();
//		getAllRooms();
//		initClients();
//
//		if (makeBookingForClient(1)) {
//
//			getAllRooms();
//            getAllClients();
//
//		}

	}
//
//	private void initRooms() {
//
//		addNewRoom(1);
//		addNewRoom(2);
//		addNewRoom(3);
//
//	}
//
//    /**
//     * @param id
//     */
//    private void addNewRoom(int id) {
//
//		theRoomsList.add(new Room(id));
//
//	}
//
//    /**
//     *
//     */
//    private void getAllRooms() {
//
//		for (Room room: theRoomsList) {
//
//			System.out.println(room.toString());
//
//		}
//
//		System.out.println("-------------------------");
//
//	}
//
//    /**
//     * @param id
//     * @param booking_from
//     * @param booking_to
//     * @param gosc
//     */
//    private void goBooking(int id, String booking_from, String booking_to, Client gosc) {
//
//		if (id != 0 && booking_from != null && booking_to != null && gosc != null) {
//
//			for (Room room: theRoomsList) {
//
//				if (room.getId() == id) {
//
//					room.goReservation(gosc);
//
//				}
//
//			}
//
//		}
//
//	}
//
//    /**
//     *
//     */
//    private void initClients() {
//
//		addNewClient(1, "Ivan", "Karbashevskyi", "+473053834", "ivankarbashevskyi@gmail.com", "ER 231254");
//
//	}
//
//    /**
//     * @param id
//     * @param name
//     * @param last_name
//     * @param phone
//     * @param email
//     * @param passport_number
//     */
//	private void addNewClient(int id, String name, String last_name, String phone, String email, String passport_number) {
//
//
//		theClientsList.add(new Client(id, name, last_name, phone, email, passport_number));
//
//	}
//
//    /**
//     * @param client_id
//     */
//    private void getAllBookingClient(int client_id) {
//
//        if (client_id != 0) {
//
//            System.out.println("\n Booking:");
//
//            for (Room room: theRoomsList) {
//
//                if (room.getStatus() == StatusRoom.BUSY) {
//
//                    if (room.getClient().getId() == client_id) {
//
//                        System.out.println(room.toString());
//
//                    }
//
//                }
//
//            }
//
//            System.out.println("--- \n");
//
//        }
//
//    }
//
//    /**
//     *
//     */
//    private void getAllClients() {
//
//		System.out.println("Clients:");
//
//		for (Client gosc : theClientsList) {
//
//			System.out.println(gosc.getFullname());
//            getAllBookingClient(gosc.getId());
//
//		}
//		System.out.println("---");
//
//	}
//
//    /**
//     * @param id
//     * @return
//     */
//    boolean makeBookingForClient(int id) {
//
//		if (id != 0) {
//
//			for (Client gosc : theClientsList) {
//
//				if (gosc.getId() == id) {
//
//					goBooking(2, "12.12.2018", "15.12.2018", gosc);
//					return true;
//
//				}
//
//			}
//
//		}
//
//		return false;
//
//	}

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

//    Can use: Receptionist, Client.

    public boolean goReservation(int id, int client_id) {

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

    public boolean goCancelReservation(int id) {

        System.out.println("Cancel reservation room id: " + id + ", is ");

        if (checkTheRoomsList()) {

            for (Room room : theRoomsList) {

                if (room.getId() == id) {

                    if (room.getStatus() == StatusRoom.RESERVATION) {

                        if (room.goCancelReservation()) {

                            System.out.print("cancel.");
                            return true;

                        }

                    }

                }

            }

        }

        System.out.print("no cancel.");
        System.out.println();
        return false;

    }

//    Can use: Receptionist, Client.

    public void showAllFreeRooms() {

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

    public void showAllBusyRooms() {

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

//    Can use: Receptionist, Director, Client.

//    public void showAllRooms() {
//
//        System.out.println("---------------------");
//        System.out.println("- All ROOMS");
//        System.out.println("---------------------");
//
//        if (checkTheRoomsList()) {
//
//            int counеRooms = 0;
//
//            for (Room room: theRoomsList) {
//
//                counеRooms += 1;
//                System.out.println(room);
//
//            }
//
//            if (counеRooms == 0) {
//
//                System.out.println("Sorry. We no have rooms.");
//
//            } else {
//
//                System.out.println("We have: " + counеRooms + " rooms.");
//
//            }
//
//        }
//
//        System.out.println();
//
//    }

    public void initRooms() {

        addNewRoom(1);
        addNewRoom(2);
        addNewRoom(3);

    }

    private void addNewRoom(int id) {

	    theRoomsList.add(new Room(id));

    }

    public void initClients() {

	    addNewClient(1, "Ivan Karbashevskyi", "ER 000000", "+48 111 11 11 11");

    }

    protected void addNewClient(int id, String full_name, String passport, String phone) {

	    theClientsList.add(new Client(id, full_name, passport, phone));

    }

}
