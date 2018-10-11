package main;

import java.util.ArrayList;

public class Booking {

	public int id;
	public int room_id;
	public int client_id;
	public StatusBooking status;
	public String booking_from;
	public String booking_to;
	
	public Booking (int id, int room_id, int client_id, StatusBooking status, String booking_from, String booking_to) {

		this.id = id;
		this.room_id = room_id;
		this.client_id = client_id;
		this.status = status;
		this.booking_from = booking_from;
		this.booking_to = booking_to;
		
	}
	
	public Room room(ArrayList<Room> theRoomsList) {
		
		for (Room room: theRoomsList) {
			
			if (this.room_id == room.id) {
				
				return room;
				
			}
			
		}
		
		return null;
		
	}
	
	public Client client(ArrayList<Client> theClientsList) {
		
		for (Client client: theClientsList) {
			
			if (this.client_id == client.id) {
				
				return client;
				
			}
			
		}
		
		return null;
		
	}
	
	public void setStatus(StatusBooking status) {
		
		this.status = status;
		
	}
	
	public void setReservationOd(String booking_from) {
		
		this.booking_from = booking_from;
		
	}
	
	public void setReservationDo(String booking_to) {
		
		this.booking_to = booking_to;
		
	}

}
