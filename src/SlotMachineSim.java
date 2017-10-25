import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class SlotMachineSim {

    private static String[] _items = {"Cherries", "Oranges", "Plumbs", "Bells", "Melons", "Bars"};
    private static List<String> _results = new ArrayList<String>();
    private static Scanner keyboard = new Scanner(System.in);
    private static Random r = new Random();
    private static double _dollars;
    private static double _payout;
    private static int _multiplier;
    private static boolean keepRunning;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Welcome to the Slot Machine SIM!");

        do{
            InsertMoney();
            DisplaySpin();
            _payout = DetermineResults(_results, _dollars);
            DisplayResults();
            KeepRunning();
            Reset(_results, _multiplier, _dollars, _payout);
        } while(keepRunning);

    }


    private static void DisplayResults(){
        if(_payout > 0){
            System.out.println("Congratulations, you won: ");
            System.out.printf("$%.2f", _payout);
        }
        else
            System.out.println("You lost it all! HAHAHAA! Try again! ");
    }

    private static double DetermineResults(List<String> results,double dollars) {

        if(results.get(0) == results.get(1) && results.get(1) == results.get(2) && results.get(0) == results.get(2)) {
            _multiplier = 3;
        }
        else if(results.get(1) == results.get(2)) {
            _multiplier = 2 ;
        }
        else if(results.get(0) == results.get(2)) {
            _multiplier = 2;
        }
        else if(results.get(0) == results.get(1)){
            _multiplier = 2;
        }
        else
            _multiplier = 0;

        return _multiplier * dollars;
    }

    private static void DisplaySpin() throws InterruptedException {
        System.out.println("Spinning...");
        for(int i = 0; i < 3; i++){
            _results.add(ChooseItem(_items));
        }

        for(String i: _results){
            TimeUnit.SECONDS.sleep(2);
            System.out.print(i + " + ");
        }
        System.out.println();
    }

    private static void InsertMoney() {
        System.out.println("Please enter the amount of money you would like to bet: ");
        _dollars = keyboard.nextDouble();
    }

    private static String ChooseItem(String[] items) {
        return items[r.nextInt(5)];
    }

    private static void KeepRunning()
    {
        System.out.println("\nDo you want to try again? \n(Y/N)");
        String response = keyboard.next();
        if(response.equalsIgnoreCase("y"))
            keepRunning = true;
        else if(response.equalsIgnoreCase("n")) {
            keepRunning = false;
            System.out.println("Shutting down...");
        }

    }


    private static void Reset(List<String> results, int multiplier, double dollars, double payout) {
        results.clear();
        multiplier = 0;
        dollars = 0;
        payout = 0;
    }

}
