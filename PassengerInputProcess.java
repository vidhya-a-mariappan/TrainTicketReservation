package zsm.console.train;

import java.util.Random;
import java.util.Scanner;

public class PassengerInputProcess {
    TicketProcess ticketProcess = new TicketProcess();

    public void passengerInfo() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Number of passenger");
        int numberOfPassenger = input.nextInt();
        Random random = new Random();
        long pnr = random.nextInt(999999999);
        for (int passengerIndex = 1; passengerIndex <= numberOfPassenger; passengerIndex++) {
            System.out.println("Enter the name of Passenger " + i);
            String name = input.next();
            System.out.println("Enter Age of Passenger");
            int age = input.nextInt();
            System.out.println("Enter Gender of Passenger(M,F)");
            char gender = input.next().charAt(0);
            System.out.println("Enter Birth Preference(L/M/U/SU)");
            String berthPreference = input.next();
            PassengerInfo passengerInfo = new PassengerInfo(name, age, gender, berthPreference, pnr);
            checkForTicketBooking(passengerInfo);
        }
    }

    public void checkForTicketBooking(PassengerInfo passengerInfo) {

        if (TicketProcess.availableWL == 0) {
            System.out.println("Ticket not available");
            return;
        }

        if ((passengerInfo.getBerthPreference().equals("L") && TicketProcess.availableLowerBerth > 0) ||
                (passengerInfo.getBerthPreference().equals("M") && TicketProcess.availableMiddleBerth > 0) ||
                (passengerInfo.getBerthPreference().equals("U") && TicketProcess.availableUpperBerth > 0) ||
                (passengerInfo.getBerthPreference().equals("SU") && TicketProcess.availableLowerBerth > 0)) {

            if (passengerInfo.getBerthPreference().equals("L")) {
                System.out.println("Lower Berth Alloted");
                ticketProcess.bookTicket(passengerInfo, (TicketProcess.lowerBerthSeats.get(0)), "L");
                TicketProcess.lowerBerthSeats.remove(0);
                TicketProcess.availableLowerBerth--;
            } else if (passengerInfo.getBerthPreference().equals("M")) {
                System.out.println("Middle Berth Alloted");
                ticketProcess.bookTicket(passengerInfo, (TicketProcess.middleBerthSeats.get(0)), "M");
                TicketProcess.middleBerthSeats.remove(0);
                TicketProcess.availableMiddleBerth--;
            } else if (passengerInfo.getBerthPreference().equals("U")) {
                System.out.println("Upper Berth Alloted");
                ticketProcess.bookTicket(passengerInfo, (TicketProcess.upperBerthSeats.get(0)), "U");
                TicketProcess.upperBerthSeats.remove(0);
                TicketProcess.availableUpperBerth--;
            } else if (passengerInfo.getBerthPreference().equals("SU")) {
                System.out.println("Side Upper Berth Alloted");
                ticketProcess.bookTicket(passengerInfo, (TicketProcess.sideUpperBerthSeats.get(0)), "SU");
                TicketProcess.sideUpperBerthSeats.remove(0);
                TicketProcess.availableSideUpperBerth--;
            }
        } else if (TicketProcess.availableLowerBerth > 0) {
            System.out.println("Lower Berth Alloted");
            ticketProcess.bookTicket(passengerInfo, (TicketProcess.lowerBerthSeats.get(0)), "L");
            TicketProcess.lowerBerthSeats.remove(0);
            TicketProcess.availableLowerBerth--;
        } else if (TicketProcess.availableMiddleBerth > 0) {
            System.out.println("Middle Berth Alloted");
            ticketProcess.bookTicket(passengerInfo, (TicketProcess.middleBerthSeats.get(0)), "M");
            TicketProcess.middleBerthSeats.remove(0);
            TicketProcess.availableMiddleBerth--;
        } else if (TicketProcess.availableUpperBerth > 0) {
            System.out.println("Upper Berth Alloted");
            ticketProcess.bookTicket(passengerInfo, (TicketProcess.upperBerthSeats.get(0)), "U");
            TicketProcess.upperBerthSeats.remove(0);
            TicketProcess.availableUpperBerth--;
        } else if (TicketProcess.availableSideUpperBerth > 0) {
            System.out.println("Side Upper Berth Alloted");
            ticketProcess.bookTicket(passengerInfo, (TicketProcess.sideUpperBerthSeats.get(0)), "SU");
            TicketProcess.sideUpperBerthSeats.remove(0);
            TicketProcess.availableSideUpperBerth--;
        } else if (TicketProcess.availableRAC > 0) {
            System.out.println("RAC,Yet to confirm Berth");
            ticketProcess.addRACList(passengerInfo, (TicketProcess.RACSeats.get(0)), "RAC");
            TicketProcess.RACSeats.remove(0);
            TicketProcess.availableRAC--;
        } else if (TicketProcess.availableWL > 0) {
            System.out.println("WL,Yet to Confirm Berth");
            ticketProcess.addWLList(passengerInfo, (TicketProcess.WLSeats.get(0)), "WL");
            TicketProcess.WLSeats.remove(0);
            TicketProcess.availableWL--;
        }
    }
}




