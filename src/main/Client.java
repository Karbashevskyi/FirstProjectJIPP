package main;

class Client {

	private int id;
	private String name;
	private String last_name;
	private String phone;
	private String email;
	private String passport_number;
	
	Client (int id, String name, String last_name, String phone, String email, String passport_number) {

		this.id = id;
		this.name = name;
		this.last_name = last_name;
		this.phone = phone;
		this.email = email;
		this.passport_number = passport_number;
		
	}

	int getId() {
		
		return this.id;
		
	}
	
	public String getName() {

		return this.name;

	}

	public String getLastName() {

		return this.last_name;

	}

	public String getPhone() {

		return this.phone;

	}

	public String getEmail() {

		return this.email;

	}

	public String getPassportNumber() {

		return this.passport_number;

	}

	String getFullname() {

		return this.name + " " + this.last_name;

	}

	@Override
	public String toString() {
		return this.getFullname();
	}
}
