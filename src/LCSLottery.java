import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class LCSLottery {

    private static ArrayList<Player> inputPlayersList;  // data for users from .csv input file
    private static ArrayList<Player> finalPlayerList;   // resulted list
    private static String winningNumber;                // winning number, with which we will compare the ticket numbers of users

    public static void main(String[] args) throws IOException {

        if(args.length == 2) {
            winningNumber = args[1];  // read winning number
            readInputDataFromCSV(args[0]);
            findLongCommSubs();
            formResultLongCommSubs();
            multipleSortOfFinalPlayerList();
        } else{
            System.out.println("Not valid enter! Enter two variables!");
        }
    }



    /**
     * Method for finding the longest common subsequence in ArrayList
     */
    public static void findLongCommSubs() {

        for(Player player : inputPlayersList){
            player.setLongCommSubsCounter(LongCommSubsForTwoStrings(player.getTicketNumber(), winningNumber).length());
        }
    }



    /**
     * Method for reading data from .csv file
     */
    public static void readInputDataFromCSV(String pathToCSVfile) throws IOException {

        // open the file
        BufferedReader reader = new BufferedReader(new FileReader(pathToCSVfile));
        // read line by line
        String line = null;
        Scanner scanner = null;
        int index = 0;
        inputPlayersList = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            Player pl = new Player();
            scanner = new Scanner(line);
            scanner.useDelimiter(",");
            while (scanner.hasNext()) {
                String data = scanner.next();
                if (index == 0)
                    pl.setLastName(data);
                else if (index == 1)
                    pl.setFirstName(data);
                else if (index == 2)
                    pl.setCountry(data);
                else if (index == 3)
                    pl.setTicketNumber(data);
                else
                    System.out.println("Incorrect data: " + data);
                index++;
            }
            index = 0;
            inputPlayersList.add(pl);

        }
        //close reader
        reader.close();
    }



    /**
     * Method for finding the longest common subsequence in two Strings
     * Realization with using recursive algorithm
     */
    public static String LongCommSubsForTwoStrings(String playerNumb, String winningNumb){
        int playerNameLen = playerNumb.length();
        int bLen = winningNumb.length();
        if(playerNameLen == 0 || bLen == 0){
            return "";
        }else if(playerNumb.charAt(playerNameLen-1) == winningNumb.charAt(bLen-1)){
            return LongCommSubsForTwoStrings(playerNumb.substring(0,playerNameLen-1),winningNumb.substring(0,bLen-1))
                    + playerNumb.charAt(playerNameLen-1);
        }else{
            String x = LongCommSubsForTwoStrings(playerNumb, winningNumb.substring(0,bLen-1));
            String y = LongCommSubsForTwoStrings(playerNumb.substring(0,playerNameLen-1), winningNumb);
            return (x.length() > y.length()) ? x : y;
        }
    }



    /**
     * Method for sorting first by last name ascending, then first name ascending, then country ascending
     */
    public static void multipleSortOfFinalPlayerList() {

        Comparator<Player> byLastName = (e1, e2) -> e1
                .getLastName().compareTo(e2.getLastName());

        Comparator<Player> byFirstName = (e1, e2) -> e1
                .getFirstName().compareTo(e2.getFirstName());

        Comparator<Player> byCountry = (e1, e2) -> e1
                .getCountry().compareTo(e2.getCountry());

        finalPlayerList.stream().sorted(byLastName.thenComparing(byFirstName).thenComparing(byCountry))
                .forEach(e -> System.out.println(e.getLastName()+","+e.getFirstName()+","+e.getCountry()+","+e.getLongCommSubsCounter()));
    }



    /**
     * Method for forming the result for output
     */
    public static void formResultLongCommSubs() {

        int counterCredits = 0;
        finalPlayerList = new ArrayList<>();
        for (int i=0; i < inputPlayersList.size(); i++) {

            //check if we have seen the player
            if(inputPlayersList.get(i).isChecked() == false) {
                // first credit of player
                counterCredits = inputPlayersList.get(i).getLongCommSubsCounter();

                // check in the List for matching the players
                for (int j = i + 1; j < inputPlayersList.size(); j++) {
                    if (i < inputPlayersList.size() - 1) {
                        if (inputPlayersList.get(i).getLastName().equals(inputPlayersList.get(j).getLastName())
                                && inputPlayersList.get(i).getFirstName().equals(inputPlayersList.get(j).getFirstName())
                                && inputPlayersList.get(i).getCountry().equals(inputPlayersList.get(j).getCountry())
                                && inputPlayersList.get(j).isChecked() == false) {

                            counterCredits += inputPlayersList.get(j).getLongCommSubsCounter();
                            inputPlayersList.get(j).setChecked(true);
                        }
                    }else {
                        continue;
                    }
                }
                inputPlayersList.get(i).setLongCommSubsCounter(counterCredits);
                // entry the user with updated lcs value in Final List
                if(inputPlayersList.get(i).getLongCommSubsCounter() != 0)
                    finalPlayerList.add(inputPlayersList.get(i));
                counterCredits = 0;
            }else {
                continue;
            }
        }
    }


}
