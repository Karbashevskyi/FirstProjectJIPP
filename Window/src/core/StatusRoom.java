package core;

public enum StatusRoom {

    FREE("Free"),
    BUSY("Busy"),
    NEED_CLEANING("Need cleaning"),
    LOCKED("Locked"),
    RESERVATION("Reservation");

    private final String value;

    StatusRoom(String value) {

        this.value = value;

    }

    @Override
    public String toString() {

        return value;

    }

}