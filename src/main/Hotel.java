package main;

import java.util.ArrayList;

public class Hotel {

	public ArrayList<Client> theClientsList = new ArrayList<Client>();
	public ArrayList<Room> theRoomsList = new ArrayList<Room>();
	
	Hotel() {

		initRooms();
		getAllRooms();
		initClients();
		
		if (makeBookingForClient(1)) {

			getAllRooms();
			
		}
		
	}
	
	public void initRooms() {
		
		addNewRoom(1);
		addNewRoom(2);
		addNewRoom(3);
		
	}
	
	public void addNewRoom(int id) {

		theRoomsList.add(new Room(id));
		
	}
	
	public void getAllRooms() {

		for (Room room: theRoomsList) {
		
			System.out.println(room.getAllInformation());
			
		}
		
		System.out.println("-------------------------");
		
	}
	
	public void goBooking(int id, String booking_from, String booking_to, Client client) {
		
		if (id != 0 && booking_from != null && booking_to != null && client != null) {
			
			for (Room room: theRoomsList) {
				
				if (room.getId() == id) {
					
					room.goBooking(booking_from, booking_to, client);
					
				}
				
			}
			
		}
		
	}
	
	public void initClients() {
		
		addNewClient(1, "Ivan", "Karbashevskyi", "+473053834", "ivankarbashevskyi@gmail.com", "ER 231254");
		
	}
	
	public void addNewClient(int id, String name, String last_name, String phone, String email, String passport_number) {
		

		theClientsList.add(new Client(id, name, last_name, phone, email, passport_number));
		
	}
	
	public void getAllClients() {
		
		for (Client client: theClientsList) {
			
			System.out.println(client.getFullname());
			
		}
		
	}
	
	public boolean makeBookingForClient(int id) {
		
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
