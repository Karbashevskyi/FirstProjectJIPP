package main;

import java.util.ArrayList;

public class Main {
	
//	ArrayList

    static ArrayList<Room> theRoomsList = new ArrayList<Room>();
    static ArrayList<Client> theClientsList = new ArrayList<Client>();
    static ArrayList<Booking> theBookingsList = new ArrayList<Booking>();
	
//	Room
	
    private static boolean addNewRoom(int id, StatusRoom status) {

        theRoomsList.add(new Room(id, status));
        return true;

    }
	
    private static boolean initRoom() {

        addNewRoom(1, StatusRoom.FREE);
        addNewRoom(2, StatusRoom.BUSY);
        addNewRoom(3, StatusRoom.NEED_CLEANING);
        addNewRoom(4, StatusRoom.FREE);
        return true;

    }

    private static void getAllRooms() {

        for (Room room: theRoomsList) {

            System.out.println(room.status);

        }

        System.out.println("------------------------");

    }
	
    private static boolean changeStatusInRoom(int id, StatusRoom status) {

        if (id == 0) {

            return false;

        }

        for (Room room: theRoomsList) {

            if (room.id == id) {

                room.setStatus(status);
                return true;

            }

        }

        return false;

    }
	
//  Client
	
    private static boolean addNewClient(int id, String name, String last_name, String phone, String email, String passport_number) {

        theClientsList.add(new Client(id, name, last_name, phone, email, passport_number));
        return true;

    }

    private static boolean initClient() {

        addNewClient(1, "Dorota", "Towacz", "650053856", "DorotaTow@gmail.com", "EE 020306");
        addNewClient(2, "Ivan", "Karbaszewski", "730053834", "ivankarbashevskyi@gmail.com", "ER 872354");
        return true;

    }

    private static void getAllClients() {

        for (Client client: theClientsList) {

            System.out.println(client.name + " " + client.last_name);

        }

        System.out.println("------------------------");

    }
	
//  Booking
	
    private static boolean initBooking() {

        theBookingsList.add(new Booking(1, 2, 1, StatusBooking.UNAVAILABLE, "10.11.2018", "12.11.2018"));
        return true;

    }

    private static void getAllBookings() {

        for (Booking booking: theBookingsList) {

            System.out.println("Booking: Number room: " + booking.room_id + ", Client: " + booking.client(theClientsList).name + " " + booking.client(theClientsList).last_name + ", Status: " + booking.status);

        }

        System.out.println("------------------------");

    }

    private static void getAllBookingsInTheRoom(int room_id) {

        if (room_id == 0) {

            System.out.println("Warning: room_id is 0, please get good room_id, thanks.");

        } else {

            for (Booking booking: theBookingsList) {

                if (room_id == booking.room_id) {

                    System.out.println("Booking: Number room: " + room_id + ", Client: " + booking.client(theClientsList).name + " " + booking.client(theClientsList).last_name + ", Status: " + booking.status);

                }

            }

            System.out.println("------------------------");

        }

    }

    private static void getAllBookingsInTheClient(int client_id) {

        if (client_id == 0) {

            System.out.println("Warning: client_id is 0, please get good client_id, thanks.");

        } else {

            for (Booking booking: theBookingsList) {

                if (client_id == booking.client_id) {

                    System.out.println("Booking: Number room: " + booking.room_id + ", Client: " + booking.client(theClientsList).name + " " + booking.client(theClientsList).last_name + ", Status: " + booking.status);

                }

            }

            System.out.println("------------------------");

        }

    }

    private static boolean addNewBooking() {

        theBookingsList.add(new Booking(2, 1, 2, StatusBooking.ACTIVE, "12.12.2018", "15.12.2018"));
        return true;

    }

    private static boolean changeStatusInBooking(int id, StatusBooking status) {

        if (id == 0) {

            return false;

        }

        for (Booking booking: theBookingsList) {

            if (booking.status != status && booking.id == id) {

                booking.setStatus(status);
                return true;

            }

        }

        return false;

    }
	
//	Main
	
    public static void main(String args[]) {
		
//      Room
        System.out.println("\n Room \n");
        initRoom();
        getAllRooms();
        addNewRoom(5, StatusRoom.FREE);
        getAllRooms();
        changeStatusInRoom(3, StatusRoom.FREE);
        getAllRooms();

//       Client
        System.out.println("\n Client \n");
        initClient();
        getAllClients();
        addNewClient(3, "Jan", "Kowalski", "730067856", "JanKow@gmail.com", "WW 092034");
        getAllClients();

//       Booking
        System.out.println("\n Booking \n");
        initBooking();
        getAllBookings();
        addNewBooking();
        getAllBookings();
        getAllBookingsInTheRoom(2);
        changeStatusInBooking(1, StatusBooking.ACTIVE);
        getAllBookingsInTheRoom(2);
        getAllBookingsInTheClient(2);
		
    }
	
}
