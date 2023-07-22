/*
	Object class handling the calculations and output.

	Cody Henderson July 18th 2022

	Updated July 25th, With Validation and Arrays
 */

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BasketBuildWithArrays {
    private char iBasketType;
    private String iAccType;
    private String iState;
    private int iCustomerType;
    private String basketName;
    private String accName;
    private String custName;
    private double basketAmount = 0.00;
    private double discount = 0.00;
    private double subtotal = 0.00;
    private double tax = 0.00;
    private double total = 0.00;

    private static LocalDate today = LocalDate.now();
    DateTimeFormatter todayFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    String todayDate = today.format(todayFormat);

    private char basket_Type[] = {'C','W','K','M','U'};
    private String basket_Name[]={"CRACKER","WILDFLOWER","KEY","MAGAZINE","UMBRELLA"};
    private double basket_Cost[] = {15.00,53.25,23.15,34.20,112.77};
    private String acc_Type[] = {"A1","A2","A3","A4"};
    private String acc_Name[]={"PROTECTOR","LINER","COMBO","NONE"};
    private double acc_Cost[] = {4.75,8.00,10.55,0.00};
    private int cust_Type[] = {1,2,3};
    private String cust_Name[] = {"DEALER","WALK-IN","BUS"};
    private double disc_Perc[] = {0.50,0.00,0.10};
    private final double IA_TAX = 0.06;
    private final double IL_TAX = 0.0625;
    private final double MO_TAX = 0.04225;

    static DecimalFormat currency = new DecimalFormat("$###,###,##0.00");

    public BasketBuildWithArrays(){
        iBasketType = 'U';
        iAccType = "A4";
        iCustomerType = 1;
        iState = "IA";
    }

    public BasketBuildWithArrays(char iBasketType, String iAccType, String iState, int iCustomerType){
        setBType(iBasketType);
        setAType(iAccType);
        setState(iState);
        setCType(iCustomerType);
    }

    public void calcs(){
        double basketCost = 0.00;
        double accCost = 0.00;
        double custDiscountRate = 0.00;
        double taxRate = 0.00;
        int i = 0;
        for(i = 0;i<basket_Type.length;i++){
            if(iBasketType==basket_Type[i]){
                basketCost = basket_Cost[i];
                basketName = basket_Name[i];
            }
        }
        for(i=0;i<acc_Type.length;i++){
            if(iAccType.equals(acc_Type[i])){
                accCost = acc_Cost[i];
                accName = acc_Name[i];
            }
        }

        basketAmount = (basketCost + accCost);

        for(i=0;i<cust_Type.length;i++){
            if(iCustomerType==cust_Type[i]){
                custDiscountRate = disc_Perc[i];
                custName = cust_Name[i];
            }
        }

        discount = Math.round((basketAmount * custDiscountRate)*100.00) / 100.00 ;
        subtotal = (basketAmount - discount);

        if(iState.equals("IA")){
            taxRate = IA_TAX;
        }
        if(iState.equals("IL")){
            taxRate = IL_TAX;
        }
        if(iState.equals("MO")){
            taxRate = MO_TAX;
        }
        if(iCustomerType != 1) {
            tax = Math.round((subtotal * taxRate) * 100.00) / 100.00;
        }
        total = (subtotal + tax);
    }
    //SETTERS/MUTATORS
    public void setCType(int iCustomerType) {
        this.iCustomerType=iCustomerType;
    }

    public void setState(String iState) {
        this.iState=iState;
    }

    public void setAType(String iAccType) {
        this.iAccType=iAccType;
    }

    public void setBType(char iBasketType) {
        this.iBasketType=iBasketType;
    }

    //GETTERS/ACCESSORS
    public int getiCustomerType(){
        return this.iCustomerType;
    }

    public String getiState(){
        return this.iState;
    }

    public String getiAccType(){
        return this.iAccType;
    }

    public char getiBasketType(){
        return this.iBasketType;
    }

    public String output(){
        String msgOutput = String.format("%-20s%5s%17s\n%-20s%5s%17s\n%-20s%5s%17s\n"+
                        "%-20s%5s%17s\n%-20s%5s%17s\n%-20s%5s%17s\n%-20s%5s%17s\n%-20s%5s%17s\n"+
                        "%-20s%5s%17s\n%-20s%5s%17s\n",
                "Todays Date: "," ", todayDate,
                "Basket Type: "+getiBasketType(), " ", basketName,
                "Accessory Type: "+getiAccType(), " ", accName,
                "Customer Type: "+getiCustomerType(), " ", custName,
                "State: ", " ", getiState(),
                "Basket Amount: ", " ", currency.format(basketAmount),
                "Discount: ", " ", currency.format(discount),
                "Subtotal: ", " ", currency.format(subtotal),
                "Tax: ", " ", currency.format(tax),
                "Total: ", " ", currency.format(total));
        return msgOutput;
    }
}