package core;

public class Client extends Hotel {

    private int id;
    private String full_name;
    private String passport;
    private String phone;
    private String email;
    private String password;

    Client(int id, String full_name, String passport, String phone, String email, String password) {

        this.id = id;
        this.full_name = full_name;
        this.passport = passport;
        this.phone = phone;
        this.email = email;
        this.password = password;

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

    public String getEmail() {

        return email;

    }

    public String getPassword() {

        return password;

    }

    @Override
    public String toString() {

        return getFullName();

    }
}
