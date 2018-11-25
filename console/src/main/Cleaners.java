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

    /**
     * @param id
     */
    public boolean goCleaning(int id) {

        System.out.print("Cleaning room id: " + id + ", is ");

        if (checkTheRoomsList()) {

            for (Room room : theRoomsList) {

                if (room.getId() == id) {

                    if (room.getStatus() == StatusRoom.NEED_CLEANING) {

                        if (room.goCleaning()) {

                            System.out.println("confirmed.");
                            return true;

                        }

                    }

                }

            }

        }

        System.out.println("no confirmed.");
        System.out.println();
        return false;

    }

}
