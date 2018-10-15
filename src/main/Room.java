package main;

public class Room {
	
	public int id;
	public StatusRoom status;
	
	public Room (int id, StatusRoom status) {
		
            this.id = id;
            this.status = status;
		
	}
	
	public void setStatus(StatusRoom status) {

            this.status = status;
		
	}

}
