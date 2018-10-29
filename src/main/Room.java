package main;

class Room {

	private int id;
	private Client client;
	private StatusRoom status = StatusRoom.FREE;

    Room (int id) {

		this.id = id;

	}

	/**
	 * @param client_reservation
	 */
    void goReservation(Client client_reservation) {

		if (status == StatusRoom.FREE) {

			if (client != null) {

				status = StatusRoom.BUSY;
				client = client_reservation;

			}

		}

	}

	void goFinishReservation() {

		if (status != StatusRoom.BUSY) {

			status = StatusRoom.NEED_CLEANING;

		}

	}

	void goCleaning() {

		if (status == StatusRoom.NEED_CLEANING) {

			status = StatusRoom.FREE;

		}


	}

	int getId() {

		return id;

	}

	/**
	 * @return
	 */
	Client getClient() {

		return client;

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

        String status = "";

		if (this.status == StatusRoom.FREE) {

			status = "is free";

		}

		if (this.status == StatusRoom.BUSY) {

			status = "is busy. Client: " + this.client.toString();

		}

		if (this.status == StatusRoom.NEED_CLEANING) {

			status = "need cleaning";

		}

        return "Room " + status;

	}
}
