package main;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Role role;

    public static Receptionist receptionist = new Receptionist("Ivan karbashevskyi");
    public static Director director = new Director("Aleksander Golovin");
    public static Cleaners cleaners = new Cleaners();
    public static Client client;
	
	public static void main(String args[]) {

//        authorisation("Hello, this is authorization form, please write value to input.");
        selectRole();

	}

//    private static void authorisation(String message) {
//
//	    getInformationAboutAccounts();
//        System.out.println(message);
//	    System.out.print("Login: ");
//        String login = scanner.nextLine();
//	    System.out.println();
//	    System.out.print("Password: ");
//        String password = scanner.nextLine();
//
//        if (login.isEmpty() || password.isEmpty()) {
//
//            authorisation("Login or password is empty!");
//
//        }
//
//        if (password.equals("123")) {
//
//            switch (login) {
//
//                case "receptionist":
//                    role = Role.RECEPTIONIST;
//                    receptionist.initClients();
//                    receptionist.initRooms();
//                    break;
//                case "director":
//                    role = Role.DIRECTOR;
//                    director.initClients();
//                    director.initRooms();
//                    break;
//                case "cleaners":
//                    role = Role.CLEANERS;
//                    cleaners.initClients();
//                    cleaners.initRooms();
//                    break;
//                case "ivan":
//                    role = Role.CLIENT;
//                    client = new Client(1, "Ivan Karbashevskyi", "ER 000000", "+48 000 00 00 00");
//                    client.initClients();
//                    client.initRooms();
//                    break;
//                case "alex":
//                    role = Role.CLIENT;
//                    client = new Client(2, "Aleksander Golovin", "AK 111111", "+48 111 11 11 11");
//                    client.initClients();
//                    client.initRooms();
//                    break;
//                default:
//                    authorisation("Login is no good. Please try again!");
//                    break;
//
//            }
//
//            menuForRole();
//
//        } else {
//
//            authorisation("Password is no good. Please try again!");
//
//        }
//
//    }
//
//    private static void getInformationAboutAccounts() {
//
//        System.out.println("***********************************************************************");
//        System.out.println("* INFORMATION ABOUT ACCOUNTS *");
//        System.out.println("***********************************************************************");
//        System.out.println("* Receptionist *");
//        System.out.println("* Login: receptionist");
//        System.out.println("* Password: 123");
//        System.out.println("***********************************************************************");
//        System.out.println("* Director *");
//        System.out.println("* Login: director");
//        System.out.println("* Password: 123");
//        System.out.println("***********************************************************************");
//        System.out.println("* Cleaners *");
//        System.out.println("* Login: cleaners");
//        System.out.println("* Password: 123");
//        System.out.println("***********************************************************************");
//        System.out.println("* Client 1 *");
//        System.out.println("* Login: ivan");
//        System.out.println("* Password: 123");
//        System.out.println("***********************************************************************");
//        System.out.println("* Client 2 *");
//        System.out.println("* Login: alex");
//        System.out.println("* Password: 123");
//        System.out.println("***********************************************************************");
//        System.out.println();
//
//    }

    private static void selectRole() {

	    br();
        System.out.println("Hello, select role: ");
        System.out.println("1." + Role.CLIENT + ".");
        System.out.println("2." + Role.CLEANERS + ".");
        System.out.println("3." + Role.RECEPTIONIST + ".");
        System.out.println("4." + Role.DIRECTOR + ".");
        System.out.print("Write here number: ");

        try {

            int id_role = scanner.nextInt();

            switch (id_role) {
                case 1:
                    role = Role.CLIENT;
                    client = new Client(1, "Name Surname", "ER 000000", "+48 000 00 00 00");
                    if (client.theRoomsList.isEmpty()) {
                        client.initClients();
                        client.initRooms();
                    }
                    break;
                case 2:
                    role = Role.CLEANERS;
                    if (cleaners.theRoomsList.isEmpty()) {
                        cleaners.initClients();
                        cleaners.initRooms();
                    }
                    break;
                case 3:
                    role = Role.RECEPTIONIST;
                    if (receptionist.theRoomsList.isEmpty()) {
                        receptionist.initClients();
                        receptionist.initRooms();
                    }
                    break;
                case 4:
                    role = Role.DIRECTOR;
                    if (director.theRoomsList.isEmpty()) {
                        director.initClients();
                        director.initRooms();
                    }
                    break;
                default:
                    selectRole();
                    break;
            }

            menuForRole();

        } catch (InputMismatchException e) {

            System.out.println("Please, write integer value.");

        }

    }

    private static void menuForRole() {

	    br();
        System.out.println("----------------------------------------");
        System.out.println("- Menu for " + role);
        System.out.println("----------------------------------------");
        System.out.println("0. Change role.");
        System.out.println();

        switch (role) {

            case CLIENT:
                System.out.println("My full name: " + client.getFullName());
                System.out.println("1. Show all free rooms");
                System.out.println("2. Reservation room.");
                System.out.println("3. Cancel my reservation.");
                break;
            case CLEANERS:
                System.out.println("1. Show all need cleaning rooms.");
                System.out.println("2. Cleaning.");
                break;
            case DIRECTOR:
                System.out.println("My full name: " + director.getFullName());
                System.out.println("1. Add new room.");
                System.out.println("2. Locked room.");
                System.out.println("3. Cancel locked room.");
                break;
            case RECEPTIONIST:
                System.out.println("My full name: " + receptionist.getFullName());
                System.out.println("1. Check in room.");
                System.out.println("2. Check out room.");
                System.out.println("3. Reservation room.");
                System.out.println("4. Cancel reservation room.");
                System.out.println("5. Show all free rooms.");
                break;
            default:
                selectRole();
                break;

        }

        System.out.println();
        System.out.print("Write here number from menu: ");
        int selected_item_from_menu = scanner.nextInt();
        doSelectedFunction(selected_item_from_menu);

        System.out.println();

    }

    private static void br() {

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

    }

    /**
     * @param selectedFunction
     */
    private static void doSelectedFunction(int selectedFunction) {

	    if (selectedFunction == 0) {

//	        System.out.println("Exit.");
            selectRole();

        } else {

	        br();
            int room_id;

	        switch (role) {

                case CLIENT:
                    switch (selectedFunction) {
                        case 1:
//                            Show all free rooms
                            client.showAllFreeRooms();

                            break;
                        case 2:
//                            Reservation free room.
                            client.showAllFreeRooms();
                            System.out.print("Please, write id room (0 - back to menu): ");
                            room_id = scanner.nextInt();
                            if (room_id != 0) {
                                client.goReservation(room_id, client.getId());
                            } else {
                                menuForRole();
                            }
                            break;
                        case 3:
//                            Cancel reservation
                            client.showAllReservationRooms(client.getId());
                            System.out.print("Please, write id room (0 - back to menu): ");
                            room_id = scanner.nextInt();
                            if (room_id != 0) {
                                client.goCancelReservation(room_id);
                            } else {
                                menuForRole();
                            }
                            break;
                        default:
                            menuForRole();
                            break;
                    }
                    break;
                case CLEANERS:
                    switch (selectedFunction) {
                        case 1:
//                            Show all need cleaning room
                            cleaners.showAllNeedCleaningRooms();
                            break;
                        case 2:
//                            Cleaning room
                            cleaners.showAllNeedCleaningRooms();
                            System.out.print("Please, write id room (0 - back to menu): ");
                            room_id = scanner.nextInt();
                            if (room_id != 0) {
                                cleaners.goCleaning(room_id);
                            } else {
                                menuForRole();
                            }
                            break;
                        default:
                            menuForRole();
                            break;
                    }
                case DIRECTOR:
                    switch (selectedFunction) {
                        case 1:
//                            Add new room
                            System.out.print("Please, write id new room (id last room - " + director.getLastRoomId() + ") (0 - back to menu): ");
                            room_id = scanner.nextInt();
                            if (room_id == 0) {
                                menuForRole();
                            } else {
                                if (director.addNewRoom(room_id)) {
                                    System.out.println("Great, you add new room with id - " + room_id);
                                }
                            }
                            break;
                        case 2:
//                           Locked room
                            director.showAllNoLockedRooms();
                            System.out.print("Please, write id room (0 - back to menu): ");
                            room_id = scanner.nextInt();
                            if (room_id == 0) {
                                menuForRole();
                            } else {
                                director.goLocked(room_id);
                            }
                            break;
                        case 3:
//                            Cancel locked room
                            director.showAllLockedRooms();
                            System.out.print("Please, write id room (0 - back to menu): ");
                            room_id = scanner.nextInt();
                            if (room_id == 0) {
                                menuForRole();
                            } else {
                                director.goCancelLocked(room_id);
                            }
                            break;
                        default:
                            menuForRole();
                            break;
                    }
                    break;
                case RECEPTIONIST:
                    switch (selectedFunction) {
                        case 1:
//                            Check in
                            receptionist.showAllReservationRooms(0);
//                            hotel.showAllRooms();
                            System.out.print("Please, write id room (0 - back to menu): ");
                            room_id = scanner.nextInt();
                            receptionist.goCheckIn(room_id);
                            break;
                        case 2:
//                            Check out
                            receptionist.showAllBusyRooms();

                            System.out.print("Write id room (0 - Cancel): ");
                            room_id = scanner.nextInt();
                            if (room_id != 0) {

                                receptionist.goCheckOut(room_id);

                            }

                            break;
                        case 3:
//                            Reservation
                            receptionist.showAllFreeRooms();

                            System.out.print("Write id room (0 - Cancel): ");
                            room_id = scanner.nextInt();
                            if (room_id != 0) {

                                System.out.print("\n Write: \n 0 - If you want to change selected room. \n 1 - if you want to select client from list. \n 2 - if you want to create new client. \n You choice: ");
                                int local_choice = scanner.nextInt();
                                if (local_choice == 1) {

                                    System.out.println();
                                    receptionist.showAllClients();
                                    System.out.print("Write here client id: ");
                                    int client_id = scanner.nextInt();
                                    receptionist.goReservation(room_id, client_id);

                                } else {

                                    System.out.println("Please, write full name new client: ");
                                    String full_name = scanner.nextLine();
                                    System.out.println("Please, write passport new client: ");
                                    String passport = scanner.nextLine();
                                    System.out.println("Please, write phone new client: ");
                                    String phone = scanner.nextLine();
                                    int new_id = receptionist.getLastClientId() + 1;

                                    receptionist.addNewClient(new_id, full_name, passport, phone);

                                    System.out.println("Great, we added new client to list, this client have id: " + new_id);
                                    System.out.print("Do you want to make reservation for this new client? (1 - yes, 0 - no) : ");
                                    int make_reservation_for_new_client = scanner.nextInt();

                                    if (make_reservation_for_new_client == 1) {

                                        receptionist.goReservation(room_id, new_id);

                                    }

                                }

                            }
                            break;
                        case 4:
//                            Cancel reservation
                            receptionist.showAllReservationRooms(0);
                            System.out.print("Please, write room id for cancel reservation: ");
                            room_id = scanner.nextInt();
                            if (room_id != 0) {
                                receptionist.goCancelReservation(room_id);
                            }
                            break;
                        case 5:
//                            Show all free rooms
                            receptionist.showAllFreeRooms();
                            break;
                        default:
//                            Back to menu
                            menuForRole();
                            break;
                    }
                    break;
                default:
//                    If this user no have role.
//                    authorisation("Please, write login and password!");
                    selectRole();
                    break;

            }

            System.out.print("Back to menu (1 - yes, 0 - exit): ");
            int backToMenu = scanner.nextInt();
            if (backToMenu == 1) {

                menuForRole();

            }

        }

    }

}
