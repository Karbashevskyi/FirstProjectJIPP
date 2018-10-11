package main;

public class Client {

	public int id;
	public String name;
	public String last_name;
	public String phone;
	public String email;
	public String passport_number;
	
	public Client (int id, String name, String last_name, String phone, String email, String passport_number) {

		this.id = id;
		this.name = name;
		this.last_name = last_name;
		this.phone = phone;
		this.email = email;
		this.passport_number = passport_number;
		
	}
	
	public void setName(String name) {
		
		this.name = name;
		
	}
	
	public void setLastName(String last_name) {
		
		this.last_name = last_name;
		
	}
	
	public void setPhone(String phone) {
		
		this.phone = phone;
		
	}
	
	public void setEmail(String email) {
		
		this.email = email;
		
	}
	
	public void setPassportNumber(String passport_number) {
		
		this.passport_number = passport_number;
		
	}

}
