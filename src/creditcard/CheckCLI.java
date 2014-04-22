package creditcard;

import java.util.Scanner;

public class CheckCLI {

<<<<<<< HEAD
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		CreditCard card2 = new CreditCard();
		
		do {
			System.out.println("Please enter card number:");
			card2.setNumber(in.nextLine());
			System.out.println("Credit Card Issuer:   " +card2.getCardType());
			System.out.println("Luhn Algorithm Check: " +card2.checkValidity());
			System.out.println("Continue? (y/N)");
		} while (in.nextLine().toUpperCase().equals("Y"));
		
		in.close();
	}
	
=======
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

>>>>>>> 8858450a49c7807d9852f621ead2ce7152edd934
}
