import java.util.ArrayList;
import java.util.Scanner;

class Camera {
    int sno; // Serial number
    String brand;
    String model;
    double perDayRental;

    public Camera(int sno, String brand, String model, double perDayRental) {
        this.sno = sno;
        this.brand = brand;
        this.model = model;
        this.perDayRental = perDayRental;
    }
}

class User {
    double walletAmount;

    public User(double walletAmount) {
        this.walletAmount = walletAmount;
    }
}

public class CameraRentalApplication {
    static ArrayList<Camera> cameraList = new ArrayList<>();
    static User user = new User(1000.0);

    public static void main(String[] args) {
        initializeCameras();

        displayWelcomeScreen();

        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            displayMainMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    displayCameraList();
                    break;
                case 2:
                    rentCamera(scanner);
                    break;
                case 3:
                    viewWalletAmount();
                    break;
                case 4:
                    System.out.println("Exiting the application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        scanner.close();
    }

    private static void initializeCameras() {
        cameraList.add(new Camera(1, "Canon", "EOS Rebel T6", 200.0));
        cameraList.add(new Camera(2, "Nikon", "D3500", 120.0));
        cameraList.add(new Camera(3, "Sony", "Alpha A6000", 150.0));
        // Add more cameras as needed
    }

    private static void displayWelcomeScreen() {
        System.out.println("Welcome to CamRent - Camera Rental Application");
    }

    private static void displayMainMenu() {
        System.out.println("Main Menu:");
        System.out.println("1. View Camera List");
        System.out.println("2. Rent a Camera");
        System.out.println("3. View Wallet Amount");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void displayCameraList() {
        System.out.println("Camera List:");
        System.out.println("SNO\tBrand\t\tModel\t\tPer Day Rental");
        for (Camera camera : cameraList) {
            System.out
                    .println(
                            camera.sno + "\t" + camera.brand + "\t\t" + camera.model + "\t\tRs." + camera.perDayRental);
        }
        System.out.println();
    }

    private static void rentCamera(Scanner scanner) {
        displayCameraList();
        System.out.print("Enter the serial number of the camera you want to rent: ");
        int cameraSno = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Camera selectedCamera = findCameraBySno(cameraSno);

        if (selectedCamera != null) {
            System.out.print("Enter rental duration (in days): ");
            int rentalDuration = scanner.nextInt();
            double totalCost = rentalDuration * selectedCamera.perDayRental;

            if (totalCost > user.walletAmount) {
                System.out.println("Insufficient funds. Add more money to your wallet.");
            } else {
                user.walletAmount -= totalCost;
                System.out.println("Rental successful!");
                System.out.println("Total Cost: Rs." + totalCost);
                System.out.println("Remaining Wallet Amount: Rs." + user.walletAmount);
            }
        } else {
            System.out.println("Invalid camera serial number. Please try again.");
        }
    }

    private static Camera findCameraBySno(int sno) {
        for (Camera camera : cameraList) {
            if (camera.sno == sno) {
                return camera;
            }
        }
        return null;
    }

    private static void viewWalletAmount() {
        System.out.println("Wallet Amount: Rs." + user.walletAmount);
        System.out.println();
    }
}
