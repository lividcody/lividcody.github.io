/*
Asks user for input on a basket order.
Takes the input and calculates amounts tax subtotal and total.
Displays the date, user input and calculations.

Cody Henderson July 18th 2022

Updated July 25th, With Validation and Arrays
 */

import java.util.Scanner;

public class LongabergerWithArrays {
    static Scanner keyboard;

    static char basket_Type[] = {'C','W','K','M','U'};
    static String acc_Type[] = {"A1","A2","A3","A4"};

    public static void main(String[] args) {
        init();
        boolean moreBaskets = true;
        while (moreBaskets) {
            System.out.println(" ");
            char iOrderType = inputOrderType();
            char iBasketType = ' ';
            char anotherBasket = ' ';
            String iAccType = "";
            String iState = "";
            int iCustomerType = 0;

            BasketBuildWithArrays myBasket;

            if (iOrderType == 'S') {
                myBasket = new BasketBuildWithArrays();
                System.out.println("Standard\n");
            }
            else {
                System.out.println("Custom\n");
                iBasketType = inputBasketType();
                iAccType = inputAccType();
                iState = inputState();
                iCustomerType = inputCustomerType();
                myBasket = new BasketBuildWithArrays(iBasketType, iAccType, iState, iCustomerType);
            }
            myBasket.calcs();
            System.out.printf(myBasket.output());
            boolean basketAnswerLoop = true;
            while(basketAnswerLoop) {
                System.out.println("\nDo you have another basket?(Y/N)");
                String iString = keyboard.nextLine();
                iString = iString.toUpperCase();
                anotherBasket = iString.charAt(0);
                if (anotherBasket == 'Y') {
                    moreBaskets = true;
                    basketAnswerLoop = false;
                }
                else
                    if (anotherBasket == 'N') {
                        moreBaskets = false;
                        basketAnswerLoop = false;
                    }
                    else {
                        System.out.println("\nNot a valid answer, try again.");
                    }
            }
        }
    }

    private static void init() {
        keyboard = new Scanner(System.in);
    }

    private static char inputOrderType() {
        boolean orderValid = false;
        String iData = "";
        char iData1 = ' ';
        while(!orderValid) {
            System.out.println("Please enter order type((S)tandard, (C)ustom): ");
            iData = keyboard.nextLine();
            iData = iData.toUpperCase();
            iData1 = iData.charAt(0);
            if (iData1 == 'S') {
                break;
            }
            else
                if(iData1=='C'){
                    break;
                }
                else{
                    System.out.println("Invalid entry.  Try again.");
                }
        }
        return iData1;
    }

    private static char inputBasketType() {
        boolean basketValid = false;
        String iData = "";
        char iData1 = ' ';
        int i = 0;
        while(!basketValid) {
            System.out.println("Please enter basket type((C)racker, (W)ildflower, (K)ey, (M)agazine, (U)mbrella): ");
            iData = keyboard.nextLine();
            iData = iData.toUpperCase();

            for (i = 0; i <(basket_Type.length); i++) {
                iData1 = iData.charAt(0);
                if (iData1 == basket_Type[i]) {
                    basketValid=true;
                    break;
                }
            }
            if(i==basket_Type.length){
                System.out.println("Invalid Entry.  Try again.");
            }
        }
        return iData1;
    }

    private static String inputAccType() {
        boolean accValid = false;
        String iData = "";
        while(!accValid) {
            System.out.println("Please enter Accessory Type(A1-Protector, A2-Liner, A3-Combo, A4-None): ");
            iData = keyboard.nextLine();
            iData = iData.toUpperCase();
            int i = 0;

            for (i = 0; i < (acc_Type.length); i++) {
                if (iData.equals(acc_Type[i])) {
                    accValid = true;
                    break;
                }
            }
            if (i == acc_Type.length) {
                System.out.println("Invalid Entry.  Try again.");
            }
        }
        return iData;
    }

    private static String inputState() {
        boolean stateValid = false;
        String iData = "";
        while(!stateValid) {
            System.out.println("Please enter State(IA, IL, MO): ");
            iData = keyboard.nextLine();
            iData = iData.toUpperCase();
            if (!iData.equals("IA") && !iData.equals("IL") && !iData.equals("MO")) {
                System.out.println("Invalid State.  Try again.");
            }
            else{
                stateValid=true;
            }
        }
        return iData;
    }

    private static int inputCustomerType() {
        boolean custValid = false;
        String iData = "";
        int iData1 = 0;
        while(!custValid) {
            System.out.println("Please enter customer type(1-Dealer, 2-Walk-in, 3-Bus): ");
            iData = keyboard.nextLine();
            try {
                iData1 = Integer.parseInt(iData);
                if (iData1 > 3 || iData1 < 1) {
                    System.out.println("Invalid type, Try again.");
                }
                else{
                    custValid = true;
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid type, Try again.");
            }
        }
        return iData1;
    }
}
