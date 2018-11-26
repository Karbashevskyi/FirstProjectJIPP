package core;

public enum Role {

    CLIENT("Client"),
    CLEANERS("Cleaners"),
    DIRECTOR("Director"),
    RECEPTIONIST("Receptionist");

    private final String value;

    Role(String value) {

        this.value = value;

    }

    @Override
    public String toString() {

        return value;

    }

}
