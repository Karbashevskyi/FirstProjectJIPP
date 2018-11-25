package main;

class Client extends Hotel {

	private int id;
	private String full_name;
	private String passport;
	private String phone;
	
	Client(int id, String full_name, String passport, String phone) {

		this.id = id;
		this.full_name = full_name;
		this.passport = passport;
		this.phone = phone;
		
	}

	public int getId() {
		
		return id;
		
	}
	
	public String getFullName() {

		return full_name;

	}

	public String getPassport() {

		return passport;

	}

	public String getPhone() {

		return phone;

	}

	@Override
	public String toString() {

		return getFullName();

	}
}
