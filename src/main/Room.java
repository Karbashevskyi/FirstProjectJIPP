package main;

class Room {

	private int id;
	private int client_id;
	private StatusRoom status = StatusRoom.FREE;

    Room(int id) {

		this.id = id;

	}

	/**
	 * @param client_id
	 */
    boolean goReservation(int client_id) {

		if (status == StatusRoom.FREE) {

			if (client_id != 0) {

				status = StatusRoom.RESERVATION;
				this.client_id = client_id;
				return true;

			}

			return false;

		}

		return false;

	}

	boolean goCancelReservation() {

    	if (status == StatusRoom.RESERVATION) {

    		status = StatusRoom.FREE;
    		return true;

		}

		return false;

	}

	boolean goCheckOut() {

		if (status == StatusRoom.BUSY) {

			status = StatusRoom.NEED_CLEANING;
			return true;

		}

		return false;

	}

	boolean goCheckIn() {

    	if (status == StatusRoom.FREE || status == StatusRoom.RESERVATION) {

    		status = StatusRoom.BUSY;
    		return true;

		}

		return false;

	}

	boolean goCleaning() {

		if (status == StatusRoom.NEED_CLEANING) {

			status = StatusRoom.FREE;
			return true;

		}

		return false;

	}

	boolean goLocked() {

		if (status != StatusRoom.BUSY) {

			status = StatusRoom.LOCKED;
			return true;

		}

		return false;

	}

	boolean goCancelLocked() {

    	if (status == StatusRoom.LOCKED) {

    		status = StatusRoom.FREE;
    		return true;

		}

		return false;

	}

	/**
	 * @return
	 */
	int getId() {

		return id;

	}

	/**
	 * @return
	 */
	int getClientId() {

		return client_id;

	}


	/**
	 * @return
	 */
	StatusRoom getStatus() {

		return status;

	}

	/**
	 * @return
	 */
	@Override
	public String toString() {

        String string = "";

		if (status == StatusRoom.FREE) {

			string = "is free";

		}

		if (status == StatusRoom.BUSY) {

			string = "is busy. Client: " + client_id;

		}

		if (status == StatusRoom.NEED_CLEANING) {

			string = "need cleaning";

		}

		if (status == StatusRoom.LOCKED) {

			string = "is locked";

		}

		if (status == StatusRoom.RESERVATION) {

			string = "is reservation. Client: " + client_id;

		}

        return "Room " + id + " " + string;

	}
}
