package main;

public class Receptionist extends Hotel {

    private String full_name;

    public String getFullName() {

        return full_name;

    }

    public void goCheckIn(int id) {

        for (Room room : theRoomsList) {

            if (room.getId() == id && (room.getStatus() == StatusRoom.FREE || room.getStatus() == StatusRoom.RESERVATION)) {

                room.goCheckIn();
                break;

            }

        }

    }

    public void goCheckOut(int id) {

        for (Room room : theRoomsList) {

            if (room.getId() == id && room.getStatus() == StatusRoom.BUSY) {

                room.goCheckOut();
                break;

            }

        }

    }

    public void goReservation(int id, Client client_reservation) {

        for (Room room : theRoomsList) {

            if (room.getId() == id && room.getStatus() == StatusRoom.FREE) {

                room.goReservation(client_reservation);
                break;

            }

        }

    }

    public void searchFreeRooms() {

        for (Room room: theRoomsList) {

            if (room.getStatus() == StatusRoom.FREE) {

                System.out.println(room);

            }

        }

    }

    public void goCancelReservation(int id) {

        for (Room room: theRoomsList) {

            if (room.getId() == id && room.getStatus() == StatusRoom.RESERVATION) {

                room.goCancelReservation();

            }

        }

    }

}
