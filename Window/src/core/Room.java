package core;

import static core.StatusRoom.*;

public class Room extends Hotel {

    private int id;
    private int client_id = 0;
    private String client_full_name = null;
    private StatusRoom status = FREE;

    public Room(int id) {

        this.id = id;

    }

    /**
     * @param client_id
     */
    public boolean goReservation(int client_id, String client_full_name) {

        if (status == FREE) {

            if (client_id != 0) {

                status = RESERVATION;
                this.client_id = client_id;
                this.client_full_name = client_full_name;
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
            client_id = 0;
            client_full_name = null;
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

        return id;

    }

    public String getClient_full_name() {

        return client_full_name;

    }

    /**
     * @return
     */
    public int getClient_id() {

        return client_id;

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
        return "Room id: " + id + ", client id:" + client_id + ".";

    }
}
