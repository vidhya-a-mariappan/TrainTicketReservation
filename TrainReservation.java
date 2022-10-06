package zsm.console.train;

import java.util.Scanner;

import static zsm.console.train.TicketProcess.bookingStatus;

public class TrainReservation {
    public static void main(String[] args) {
        TrainReservation trainReservation = new TrainReservation();
        trainReservation.mainDisplay();
    }

    private void mainDisplay() {
        int option = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to IRCTC");
        do {
            System.out.println("1.Check Availability\n2.Book Ticket\n3.Cancel Ticket\n4.Ticket Status\n5.Booking History\n6.Quit\n");
            System.out.println("Select your option");
            option = scan.nextInt();
            switch (option) {
                case 1:
                    TicketProcess ticketProcess = new TicketProcess();
                    ticketProcess.checkAvailability();
                    break;
                case 2:
                    PassengerInputProcess passengerInputProcess = new PassengerInputProcess();
                    passengerInputProcess.passengerInfo();
                    break;
                case 3:
                    TicketProcess ticketProcessOne = new TicketProcess();
                    System.out.println("Enter the passenger id");
                    int id = scan.nextInt();
                    ticketProcessOne.cancelTicket(id);
                    System.out.println("Ticket Cancelled!. Amount will be refunded to your bank account within 3 - 4 working days");
                    break;
                case 4:
                    TicketProcess ticketProcessTwo = new TicketProcess();
                    System.out.println("Enter the PNR");
                    long PNR = scan.nextLong();
                    ticketProcessTwo.ticketStatus(PNR, bookingStatus);
                    break;
                case 5:
                    TicketProcess ticketProcessThree = new TicketProcess();
                    ticketProcessThree.bookingHistory();
                    break;
                case 6:
                    break;
            }
        } while (option != 6);
    }
}
