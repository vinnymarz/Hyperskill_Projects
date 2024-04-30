package Projects;
import java.util.Scanner;

public class CinemaTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Collect row and seat capacities
        System.out.println("Enter the number of rows:");
        int rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = sc.nextInt();

        // Create array for seating chart
        int[][] cinema = new int[rows][seats];

        // Create menu using Switch statement
        boolean quit = false; // Create flag to escape loop
        while (!quit) { // Create loop
            printMenu();
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    showSeating(cinema);
                    break;
                case 2:
                    buyTicket(cinema);
                    break;
                case 3:
                    displayStats(cinema);
                    break;
                case 0:
                    quit = true; // Exit the loop
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
                    break;
            }
        }
    }

    public static void printMenu() {
        System.out.println();
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    public static void buyTicket(int[][] seatingArray) {
        Scanner sc = new Scanner(System.in);
        int row, seat;

        do {
            System.out.println("Enter a row number:");
            row = sc.nextInt();
            System.out.println("Enter a seat number in that row:");
            seat = sc.nextInt();

            if (row <= 0 || row > seatingArray.length || seat <= 0 || seat > seatingArray[0].length) {
                System.out.println("\nwrong input!");
            } else if (seatingArray[row - 1][seat - 1] == 1) {
                System.out.println("\nThat ticket has already been purchased!");
            } else {
                seatingArray[row - 1][seat - 1] = 1;
                break; // Exit the loop if the seat is available
            }
        } while (true);



        // Conditional statements to print correct ticket price
        int price;
        if (seatingArray.length * seatingArray[0].length <= 60) {
            price = 10;
        } else {
            int frontHalfRows = seatingArray.length / 2;
            price = row <= frontHalfRows ? 10 : 8;
        }
        System.out.println("Ticket price: $" + price);
    }

    public static void showSeating(int[][] seatingArray) {
        // Create header
        System.out.println();
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 1; i <= seatingArray[0].length; i++) {
            System.out.print(" " + i);
        }
        System.out.println();

        // Iterate over array and print
        for (int i = 0; i < seatingArray.length; i++ ) {
            System.out.print((i + 1) + " "); // Print row header at start of each iteration
            for (int j = 0; j < seatingArray[i].length; j++) {
                // Check to see if array elements have been previously assigned
                System.out.print(seatingArray[i][j] == 0 ? "S " : "B ");
            }
            System.out.println(); // Print new line at end of each iteration
        }

    }

    public static void displayStats(int[][] seatingArray) {
        int purchasedTickets = 0;
        int totalTickets = 0;
        int actualIncome ;
        int potentialIncome;

        // Calculate the total number of tickets and purchased tickets
        for (int i = 0; i < seatingArray.length; i++) {
            for (int j = 0; j < seatingArray[i].length; j++) {
                totalTickets++;
                if (seatingArray[i][j] == 1) {
                    purchasedTickets++;
                }
            }
        }

        // Calculate actualIncome based on size of cinema
        if (seatingArray.length * seatingArray[0].length <= 60) {
            actualIncome = purchasedTickets * 10;
        } else {
            // Divide the array into front and back halves

            int halfLength = seatingArray.length / 2;
            int frontHalfTickets = 0;
            int backHalfTickets = 0;
            // Calculate amlount of tickets sold in front and back
            for (int i = 0; i < seatingArray.length; i++) {
                for (int j = 0; j < seatingArray[i].length; j++) {
                    if (i < halfLength && seatingArray[i][j] == 1) {
                        frontHalfTickets++;
                    } else if (i >= halfLength && seatingArray[i][j] == 1) {
                        backHalfTickets++;
                    }
                }
            }

            actualIncome = (frontHalfTickets * 10) + (backHalfTickets * 8);
        }

        // Calculate potentialIncome
        if (seatingArray.length * seatingArray[0].length <= 60) {
            potentialIncome = (seatingArray.length * seatingArray[0].length) * 10;
        } else {
            int frontSeats = ((seatingArray.length * seatingArray[0].length) / 2) * 10;
            int backSeats = ((seatingArray.length * seatingArray[0].length) / 2) * 8;
            potentialIncome = frontSeats + backSeats;
        }

        // Print results
        System.out.printf("Number of purchased tickets: %d%n", purchasedTickets);
        System.out.printf("Percentage: %.2f%%%n", (float) purchasedTickets / totalTickets * 100);
        System.out.printf("Current income: $%d%n", actualIncome);
        System.out.printf("Total income: $%d%n", potentialIncome);
    }
}
