package creditcard;

import java.util.Scanner;

public class CheckCLI {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        CreditCard card2 = new CreditCard();

        do {
            System.out.println("Please enter card number:");
            card2.setNumber(in.nextLine());
            System.out.println("Credit Card Issuer:   " + card2.getCardType());
            System.out.println("Luhn Algorithm Check: " + card2.checkValidity());
            System.out.println("Continue? (y/N)");
        } while (in.nextLine().toUpperCase().equals("Y"));

        in.close();
    }

}
