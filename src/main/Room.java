package main;

class Room {

	private int id;
	private Client client;
	private StatusRoom status = StatusRoom.FREE;
    private String booking_from;
    private String booking_to;

    Room (int id) {

		this.id = id;

	}

	/**
	 * @param booking_from
	 * @param booking_to
	 * @param client
	 */
    void goBooking(String booking_from, String booking_to, Client client) {

		if (this.status != StatusRoom.NEED_CLEANING) {

			if (booking_from != null && booking_to != null && client != null) {

				this.status = StatusRoom.BUSY;
				this.booking_from = booking_from;
				this.booking_to = booking_to;
				this.client = client;

			}

		}

	}

	public void goCleaning() {

		if (this.status != StatusRoom.NEED_CLEANING) {

			this.status = StatusRoom.NEED_CLEANING;

		}


	}

	public void goFree() {

		if (this.status != StatusRoom.FREE) {

			this.status = StatusRoom.FREE;

		}

	}

	int getId() {

		return this.id;

	}

	Client getClient() {

		return this.client;

	}


	StatusRoom getStatus() {

		return this.status;

	}

	public String getBookingFrom() {

		return this.booking_from;

	}

	public String getBookingTo() {

		return this.booking_to;

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
