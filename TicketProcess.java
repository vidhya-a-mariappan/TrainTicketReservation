package zsm.console.train;

import java.util.*;

public class TicketProcess {

    static int availableLowerBerth = 16;
    static int availableMiddleBerth = 16;
    static int availableUpperBerth = 16;
    static int availableSideUpperBerth = 8;
    static int availableRAC = 16;
    static int availableWL = 10;

    static Queue<Integer> waitingList = new LinkedList<>();
    static Queue<Integer> racList = new LinkedList<>();
    static List<Integer> bookedTicketList = new ArrayList<>();

    static List<Integer> lowerBerthSeats = new ArrayList<>(Arrays.asList(1, 4, 9, 12, 17, 20, 25, 28, 33, 36, 41, 44, 49, 52, 57, 60));
    static List<Integer> middleBerthSeats = new ArrayList<>(Arrays.asList(2, 5, 10, 13, 18, 21, 26, 29, 34, 37, 42, 45, 50, 53, 58, 61));
    static List<Integer> upperBerthSeats = new ArrayList<>(Arrays.asList(3, 6, 11, 14, 19, 22, 27, 30, 35, 38, 43, 46, 51, 54, 59, 62));
    static List<Integer> sideUpperBerthSeats = new ArrayList<>(Arrays.asList(8, 16, 24, 32, 40, 48, 56, 64));
    static List<Integer> RACSeats = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16));
    static List<Integer> WLSeats = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

    static Map<Integer, PassengerInfo> passengers = new HashMap<>();
    static Map<Long, Map<Integer, PassengerInfo>> bookingStatus = new HashMap<>();

    public void bookTicket(PassengerInfo passengerInfo, Integer no, String berth) {
        passengerInfo.setSeatNumber(no);
        passengerInfo.setAlloted(berth);
        passengers.put(passengerInfo.getPassengerId(), passengerInfo);
        bookedTicketList.add(passengerInfo.getPassengerId());
        System.out.println("---------------------------------Ticket Booked Successfully--------------------------------");
        bookingStatus.put(passengerInfo.getPNR(), passengers);
    }

    public void cancelTicket(int ID) {
        {
            for (Map.Entry<Integer, PassengerInfo> passenger : passengers.entrySet()) {
                if (!(passenger.getKey() ==ID))
                    System.out.println("Enter valid ID");
                else {
                    PassengerInfo passengerInfo = passenger.getValue();
                    int seatAlloted = passengerInfo.getSeatNumber();
                    if (passengerInfo.getAlloted().equals("L")) {
                        availableLowerBerth++;
                        lowerBerthSeats.add(seatAlloted);
                    } else if (passengerInfo.getAlloted().equals("M")) {
                        availableMiddleBerth++;
                        middleBerthSeats.add(seatAlloted);
                    } else if (passengerInfo.getAlloted().equals("U")) {
                        availableUpperBerth++;
                        upperBerthSeats.add(seatAlloted);
                    } else if (passengerInfo.getAlloted().equals("SU")) {
                        availableSideUpperBerth++;
                        sideUpperBerthSeats.add(seatAlloted);
                    } else if (passengerInfo.getAlloted().equals("RAC")) {
                        availableRAC++;
                        RACSeats.add(seatAlloted);
                        racList.remove(seatAlloted);
                        racList.add(waitingList.poll());
                    } else if (passengerInfo.getAlloted().equals("WL")) {
                        availableWL++;
                        WLSeats.add(seatAlloted);
                        waitingList.remove(seatAlloted);
                    }
                    if (racList.size() > 0) {
                        PassengerInfo RACPassenger = passengers.get(racList.poll());
                        int posRAC = RACPassenger.getSeatNumber();
                        RACSeats.add(posRAC);
                        racList.remove(RACPassenger.getPassengerId());
                        availableRAC++;
                        if (waitingList.size() > 0) {
                            PassengerInfo WLPassenger = passengers.get(waitingList.poll());
                            int posWL = WLPassenger.getSeatNumber();
                            WLSeats.add(posWL);
                            waitingList.remove(WLPassenger.getPassengerId());
                            WLPassenger.setSeatNumber(RACSeats.get(0));
                            WLPassenger.setAlloted("RAC");
                            RACSeats.remove(0);
                            racList.add(WLPassenger.getPassengerId());
                            availableWL++;
                            availableRAC--;
                        }
                        PassengerInputProcess passengerInputProcess = new PassengerInputProcess();
                        passengerInputProcess.checkForTicketBooking(RACPassenger);
                    }
                    bookedTicketList.remove(Integer.valueOf(passengerInfo.getPassengerId()));
                    passengers.remove(passengerInfo.getPassengerId());
                }
            }
        }
    }

    public void addRACList(PassengerInfo passengerInfo, Integer no, String rac) {
        passengerInfo.setSeatNumber(no);
        passengerInfo.setAlloted(rac);
        passengers.put(passengerInfo.getPassengerId(), passengerInfo);
        racList.add(passengerInfo.getPassengerId());
        availableRAC--;
        RACSeats.remove(0);
        System.out.println("--------------------------Ticket Booked(RAC) Successfully---------------------------------");
    }

    public void addWLList(PassengerInfo passengerInfo, Integer no, String wl) {
        passengerInfo.setSeatNumber(no);
        passengerInfo.setAlloted(wl);
        passengers.put(passengerInfo.getPassengerId(), passengerInfo);
        waitingList.add(passengerInfo.getPassengerId());
        availableWL--;
        WLSeats.remove(0);
        System.out.println("-------------------------- Ticket Booked(WL) Successfully----------------------------------");
    }

    public void bookingHistory() {
        if (passengers.size() == 0) {
            System.out.println("---------------------No Booking History Found-------------------------------------");
            return;
        }
        for (PassengerInfo passengerInfo : passengers.values()) {
            System.out.println("PNR " + passengerInfo.getPNR());
            System.out.println("PASSENGER ID " + passengerInfo.getPassengerId());
            System.out.println("Name " + passengerInfo.getName());
            System.out.println("Age " + passengerInfo.getAge());
            System.out.println("Gender " + passengerInfo.getGender());
            System.out.println("Status " + passengerInfo.getSeatNumber() + passengerInfo.getAlloted());
            System.out.println("----------------------------------------------------------------------------------");
        }
    }

    public void checkAvailability() {
        if ((availableLowerBerth + availableMiddleBerth + availableUpperBerth + availableSideUpperBerth) == 0)
            System.out.println("RAC " + availableRAC);
        if (availableRAC == 0)
            System.out.println("WL " + availableWL);
        System.out.println("Available " + (availableLowerBerth + availableMiddleBerth + availableUpperBerth + availableSideUpperBerth));

    }

    public void ticketStatus(long pnr, Map<Long, Map<Integer, PassengerInfo>> bookingStatus) {
        if (bookingStatus.containsKey(pnr)) {
            System.out.println("Ticket Status for " + pnr);
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println("                                  Train Number:12631");
            System.out.println("                               Train Name:Nellai Express");
            System.out.printf("%-50s %-50s\n", "Departure Station", "Arrival Station");
            System.out.printf("%-50s %-50s\n", "Chennai Egmore", "Tirunelveli");
            System.out.printf("%-50s %-50s\n", "Departure Date and Time", "Arrival Date and Time");
            System.out.printf("%-50s %-50s\n", "01-10-2022 19:50", "02-10-2022 6:40");
            for (Map.Entry<Long, Map<Integer, PassengerInfo>> pair : bookingStatus.entrySet()) {
                if (pair.getKey() == pnr) {
                    for (Map.Entry<Integer, PassengerInfo> pass : pair.getValue().entrySet()) {
                        if (pass.getValue().getPNR() == pnr) {
                            System.out.println("PNR " + pass.getValue().getPNR());
                            System.out.println("PASSENGER ID " + pass.getValue().getPassengerId());
                            System.out.println("Name " + pass.getValue().getName());
                            System.out.println("Age " + pass.getValue().getAge());
                            System.out.println("Gender " + pass.getValue().getGender());
                            System.out.println("Status " + pass.getValue().getSeatNumber() + pass.getValue().getAlloted());
                            System.out.println("----------------------------------------------------------------------------------");
                        }
                    }
                }
            }
        } else
            System.out.println("Enter valid PNR");
    }
}

