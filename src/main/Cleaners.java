package main;

import java.util.ArrayList;

public class Cleaners extends Hotel {

    private ArrayList<String> theFullNamesList;

    Cleaners() {

    }

    public void addNewCleaner(String full_name) {

        theFullNamesList.add(full_name);

    }

    public void getFullNames() {

        for (String full_name: theFullNamesList) {

            System.out.println(full_name);

        }

    }

    public void showAllNeedCleaningRooms() {

        for (Room room : theRoomsList) {

            if (room.getStatus() == StatusRoom.NEED_CLEANING) {

                System.out.println(room);

            }

        }

    }

    public void goCleaning(int id) {

        for (Room room : theRoomsList) {

            if (room.getStatus() == StatusRoom.NEED_CLEANING && room.getId() == id) {

                room.goCleaning();

            }

        }

    }

}
