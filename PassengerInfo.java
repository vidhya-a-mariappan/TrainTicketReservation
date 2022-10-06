package zsm.console.train;

public class PassengerInfo {
    static long pnr = 0;
    static int ID = 1;
    private String name;
    private int age;
    private char gender;
    private String berthPreference;
    private int passengerId;
    private int seatNumber;
    private String alloted;
    private long PNR;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getBerthPreference() {
        return berthPreference;
    }

    public void setBerthPreference(String berthPreference) {
        this.berthPreference = berthPreference;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getAlloted() {
        return alloted;
    }

    public void setAlloted(String alloted) {
        this.alloted = alloted;
    }

    public long getPNR() {
        return PNR;
    }

    public void setPNR() {
        this.PNR = PNR;
    }

    public PassengerInfo(String name, int age, char gender, String berthPreference, long pnr) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.berthPreference = berthPreference;
        this.passengerId = ID++;
        String alloted = "";
        int seatNumber = -1;
        PNR = pnr;
    }
}
