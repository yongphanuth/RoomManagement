/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package roommanagerment;

/**
 *
 * @author User
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean continueProgram = true;
        while (continueProgram) {
            System.out.println("Select mode:");
            System.out.println("1. User Mode");
            System.out.println("2. Admin Mode");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int modeChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (modeChoice) {
                case 1:
                    runUserMode(scanner);
                    break;
                case 2:
                    runAdminMode(scanner);
                    break;
                case 3:
                    System.out.println("Exiting program.");
                    continueProgram = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select again.");
            }
        }

        scanner.close();
    }

    private static void runUserMode(Scanner scanner) {
        System.out.println("User Mode Activated.");

        RoomManagement roomManager = new RoomManagement("admin123"); // Admin code assumed to be "admin123"

        // Example available rooms
        roomManager.addRoom(new Room(101, "2024-02-27 10:00", "Morning"));
        roomManager.addRoom(new Room(102, "2024-02-27 14:00", "Afternoon"));

        boolean userModeActive = true;
        while (userModeActive) {
            System.out.println("\nUser Options:");
            System.out.println("1. Show Available Rooms");
            System.out.println("2. Show Unavailable Rooms");
            System.out.println("3. Book a Room");
            System.out.println("4. Cancel Booking");
            System.out.println("5. Exit User Mode");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    roomManager.displayAvailableRooms();
                    break;
                case 2:
                    roomManager.displayUnavailableRooms();
                    break;
                case 3:
                    System.out.println("Enter the room number you want to book:");
                    int selectedRoomNumber = scanner.nextInt();
                    Room selectedRoom = roomManager.findRoom(selectedRoomNumber);
                    if (selectedRoom != null) {
                User user = new User("John"); // Creating a user with name "John"
                Booking booking = new Booking(user, selectedRoom); // Creating a booking with the user and selected room

                        roomManager.addBooking(booking);
                    } else {
                        System.out.println("Invalid room number.");
                    }
                    break;
                case 4:
                    System.out.println("Enter the room number you want to cancel booking for:");
                    int cancelRoomNumber = scanner.nextInt();
                    if (roomManager.cancelBooking(new User("John"), cancelRoomNumber)) { // Assume user's name is "John"
                        System.out.println("Booking canceled successfully.");
                    } else {
                        System.out.println("Failed to cancel booking.");
                    }
                    break;
                case 5:
                    userModeActive = false;
                    System.out.println("Exiting user mode.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

   private static void runAdminMode(Scanner scanner) {
    System.out.println("Admin Mode Activated.");

    RoomManagement roomManager = new RoomManagement("admin123"); // Admin code assumed to be "admin123"

    boolean isAdminLoggedIn = false;
    while (!isAdminLoggedIn) {
        System.out.print("Enter admin code to login: ");
        String adminCode = scanner.nextLine().trim();

        if (roomManager.verifyAdmin(adminCode)) {
            System.out.println("Admin login successful.");
            isAdminLoggedIn = true;
        } else {
            System.out.println("Invalid admin code. Please try again.");
        }
    }

    // Example available rooms
    roomManager.addRoom(new Room(101, "2024-02-27 10:00", "Morning"));
    roomManager.addRoom(new Room(102, "2024-02-27 14:00", "Afternoon"));

    boolean adminModeActive = true;
    while (adminModeActive) {
        System.out.println("\nAdmin Options:");
        System.out.println("1. Show Available Rooms");
        System.out.println("2. Show Unavailable Rooms");
        System.out.println("3. Add Room");
        System.out.println("4. Delete Room");
        System.out.println("5. Exit Admin Mode");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                roomManager.displayAvailableRooms();
                break;
            case 2:
                roomManager.displayUnavailableRooms();
                break;
            case 3:
                System.out.println("Enter room number:");
                int roomNumber = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.println("Enter date time:");
                String dateTime = scanner.nextLine().trim();
                System.out.println("Enter shift:");
                String shift = scanner.nextLine().trim();
                Room room = new Room(roomNumber, dateTime, shift);
                roomManager.addRoom(room);
                break;
            case 4:
                System.out.println("Enter room number to delete:");
                int roomNumberToDelete = scanner.nextInt();
                roomManager.deleteRoom(roomNumberToDelete);
                break;
            case 5:
                adminModeActive = !roomManager.exitAdminMode(); // Verify admin code before exiting admin mode
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
}

}
