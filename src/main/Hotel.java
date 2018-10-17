package main;

import java.util.ArrayList;

class Hotel {

    private ArrayList<Client> theClientsList;
    private ArrayList<Room> theRoomsList;
	
	Hotel() {

        theClientsList = new ArrayList<>();
        theRoomsList = new ArrayList<>();

		initRooms();
		getAllRooms();
		initClients();
		
		if (makeBookingForClient(1)) {

			getAllRooms();
            getAllClients();
			
		}

	}
	
	private void initRooms() {
		
		addNewRoom(1);
		addNewRoom(2);
		addNewRoom(3);
		
	}

    /**
     * @param id
     */
    private void addNewRoom(int id) {

		theRoomsList.add(new Room(id));
		
	}

    /**
     *
     */
    private void getAllRooms() {

		for (Room room: theRoomsList) {
		
			System.out.println(room.toString());
			
		}
		
		System.out.println("-------------------------");
		
	}

    /**
     * @param id
     * @param booking_from
     * @param booking_to
     * @param client
     */
    private void goBooking(int id, String booking_from, String booking_to, Client client) {
		
		if (id != 0 && booking_from != null && booking_to != null && client != null) {
			
			for (Room room: theRoomsList) {
				
				if (room.getId() == id) {
					
					room.goBooking(booking_from, booking_to, client);
					
				}
				
			}
			
		}
		
	}

    /**
     *
     */
    private void initClients() {
		
		addNewClient(1, "Ivan", "Karbashevskyi", "+473053834", "ivankarbashevskyi@gmail.com", "ER 231254");
		
	}

    /**
     * @param id
     * @param name
     * @param last_name
     * @param phone
     * @param email
     * @param passport_number
     */
	private void addNewClient(int id, String name, String last_name, String phone, String email, String passport_number) {


		theClientsList.add(new Client(id, name, last_name, phone, email, passport_number));

	}

    /**
     * @param client_id
     */
    private void getAllBookingClient(int client_id) {

        if (client_id != 0) {

            System.out.println("\n Booking:");

            for (Room room: theRoomsList) {

                if (room.getStatus() == StatusRoom.BUSY) {

                    if (room.getClient().getId() == client_id) {

                        System.out.println(room.toString());

                    }

                }

            }

            System.out.println("--- \n");

        }

    }

    /**
     *
     */
    private void getAllClients() {

		System.out.println("Clients:");
		
		for (Client client: theClientsList) {
			
			System.out.println(client.getFullname());
            getAllBookingClient(client.getId());
			
		}
		System.out.println("---");
		
	}

    /**
     * @param id
     * @return
     */
    boolean makeBookingForClient(int id) {
		
		if (id != 0) {
			
			for (Client client: theClientsList) {
				
				if (client.getId() == id) {

					goBooking(2, "12.12.2018", "15.12.2018", client);
					return true;
					
				}
				
			}
			
		}
		
		return false;
		
	}

}
