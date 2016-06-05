/**
 * Java class, that represent the columns of csv file for players
 */


public class Player {

    private String ticketNumber;
    private String firstName;
    private String lastName;
    private String country;
    private int longCommSubsCounter;
    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getLongCommSubsCounter() {
        return longCommSubsCounter;
    }
    public void setLongCommSubsCounter(int longCommSubsCounter) {
        this.longCommSubsCounter = longCommSubsCounter;
    }
    public String getTicketNumber() {
        return ticketNumber;
    }
    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString(){
        return "\n" + getLastName() + ", " + getFirstName() + ", " + getCountry() + ", " + getLongCommSubsCounter();
    }


}
