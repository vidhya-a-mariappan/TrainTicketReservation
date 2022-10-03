package zsm.console.train;

import java.util.Random;

public class PassengerInfo {
    static int ID = 1;
    String name;
    int age;
    char gender;
    String berthPreference;
    int passengerId;
    int seatNumber;
    String alloted;
    long PNR;

    public PassengerInfo(String name, int age, char gender, String berthPreference,long pnr) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.berthPreference = berthPreference;
        this.passengerId = ID++;
        String alloted = "";
        int seatNumber = -1;
        PNR=pnr;
    }

    public PassengerInfo(int id) {
        this.passengerId=id;
    }
}
