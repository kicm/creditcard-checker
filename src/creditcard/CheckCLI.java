package creditcard;

import java.util.Scanner;

public class CheckCLI {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		do {
		System.out.println("Please enter card number:");
		CreditCard card2 = new CreditCard(in.nextLine());
		System.out.println(card2.getCardType());
		System.out.println("Continue? (Y)");
		} while (in.nextLine().toUpperCase().equals("Y"));
		
		in.close();
	}
	
}
