package core;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import static core.StatusRoom.*;

public class Room {

    private SimpleIntegerProperty id;
    private SimpleIntegerProperty client_id = new SimpleIntegerProperty(0);
    private StatusRoom status = FREE;

    public Room(int id) {

        this.id = new SimpleIntegerProperty(id);

    }

    /**
     * @param client_id
     */
    public boolean goReservation(int client_id) {

        if (status == FREE) {

            if (client_id != 0) {

                status = RESERVATION;
                this.client_id = new SimpleIntegerProperty(client_id);
                return true;

            }

            return false;

        }

        return false;

    }

    /**
     * @return
     */
    public boolean goCancelReservation() {

        if (status == RESERVATION) {

            status = FREE;
            client_id = new SimpleIntegerProperty(0);
            return true;

        }

        return false;

    }

    /**
     * @return
     */
    public boolean goCheckOut() {

        if (status == BUSY) {

            status = NEED_CLEANING;
            return true;

        }

        return false;

    }

    /**
     * @return
     */
    public boolean goCheckIn() {

        if (status == FREE || status == RESERVATION) {

            status = BUSY;
            return true;

        }

        return false;

    }

    /**
     * @return
     */
    public boolean goCleaning() {

        if (status == NEED_CLEANING) {

            status = FREE;
            return true;

        }

        return false;

    }

    /**
     * @return
     */
    public boolean goLocked() {

        if (status != BUSY) {

            status = LOCKED;
            return true;

        }

        return false;

    }

    /**
     * @return
     */
    public boolean goCancelLocked() {

        if (status == LOCKED) {

            status = FREE;
            return true;

        }

        return false;

    }

    /**
     * @return
     */
    public int getId() {

        return id.get();

    }

    /**
     * @return
     */
    public int getClientId() {

        return client_id.get();

    }


    /**
     * @return
     */
    public StatusRoom getStatus() {

        return status;

    }

    /**
     * @return
     */
    @Override
    public String toString() {

//        String string = "";
//
//        if (status == FREE) {
//
//            string = "is free";
//
//        }
//
//        if (status == BUSY) {
//
//            string = "is busy. Client: " + client_id;
//
//        }
//
//        if (status == NEED_CLEANING) {
//
//            string = "need cleaning";
//
//        }
//
//        if (status == LOCKED) {
//
//            string = "is locked";
//
//        }
//
//        if (status == RESERVATION) {
//
//            string = "is reservation. Client id: " + client_id;
//
//        }
//
//        return "Room " + id + " " + string;
        return id.toString();

    }
}
