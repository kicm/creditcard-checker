package creditcard;
/**
 * 
 * @author marco
 *
 */
public class CreditCard {
	private String number;
	private static final int MIN_LENGTH = 13;
	private static final int MAX_LENGTH = 19;
	
	/**
	 * Construct a credit card
	 * @param number the number of the credit card
	 */
	public CreditCard(String number) {
		this.number = number;
	}
	/**
	 * 
	 * @return true if creditcard passes Luhn Algorithm
	 */
	public boolean luhnTest() {
		// http://rosettacode.org/wiki/Luhn_test_of_credit_card_numbers#Java
		int s1 = 0;
		int s2 = 0;

		String reverse = new StringBuffer(this.number).reverse().toString();

		for (int i = 0; i < reverse.length(); i++) {

			int digit = Character.digit(reverse.charAt(i), 10);

			if (i % 2 == 0) {
				// this is for odd digits, they are 1-indexed in the algorithm
				s1 += digit;

			} else {
				// add 2 * digit for 0-4, add 2 * digit - 9 for 5-9
				s2 += 2 * digit;
				if (digit >= 5) {
					s2 -= 9;
				}
			}
		}
		return (s1 + s2) % 10 == 0;
	}
	
	
	/**
	 * 
	 * @return the credit card issuer as a String
	 */
	public String getCardType() {

			if (this.isVisa())
				return "VISA";

			if (this.isMasterCard())
				return "MasterCard";

			if (this.isDiscover())
				return "Discover";

			if (this.isDinersInternational())
				return "Diners Club - International";

			if (this.isDinersCarteBlanche())
				return "Diners Club - Carte Blanche";

			if (this.isDinersUSAandCanada())
				return "Diners Club - USA & Canada";

			if (this.isAmericanExpress())
				return "American Express";

			if (this.isEnRoute())
				return "en Route";

			if (this.isJCB())
				return "JCB";

			if (this.isMaestro())
				return "Maestro";
			
			return null;

	}
	/**
	 * 
	 * @return
	 */
	public String checkValidity() {
		if(this.number.length() < MIN_LENGTH){
			return "Error: not enough digits";
		}
		if(this.number.length() > MAX_LENGTH){
			return "Error: too many digits";
		}
		if (this.luhnTest()) {
			return "Test passed";
		} else {
			return "Test failed";
		}

	}
	
	public boolean isVisa() {
		// VISA Check; starts with 4 ; length 13,16 ; eg. 4111 1111 1111 1111
		if (this.number.length() > 0) {
			if (this.number.charAt(0) == '4')
				return true;
		}
		return false;
	}

	public boolean isMasterCard() {
		// MasterCard Check ; starts with 51,52,53,54,55
		// length 16; eg. 5500 0000 0000 0004

		if (this.number.length() >= 2) {
			String prefix2 = this.number.substring(0, 2);

			if (prefix2.equals("51"))
				return true;
			if (prefix2.equals("52"))
				return true;
			if (prefix2.equals("53"))
				return true;
			if (prefix2.equals("54"))
				return true;
			if (prefix2.equals("55"))
				return true;
		}
		return false;

	}

	public boolean isDiscover() {
		// Discover Card Check ; starts with 6011 ; length 16 ; eg. 6011 0000
		// 0000 0004
		// 6011, 622126 to 622925, 644, 645, 646, 647, 648, 649, 65
		if (this.number.length() >= 2) {
			String prefix2 = this.number.substring(0, 2);
			if (prefix2.equals("65"))
				return true;
		}

		if (this.number.length() >= 3) {
			String prefix3 = this.number.substring(0, 3);
			if (prefix3.equals("644"))
				return true;
			if (prefix3.equals("645"))
				return true;
			if (prefix3.equals("646"))
				return true;
			if (prefix3.equals("647"))
				return true;
			if (prefix3.equals("648"))
				return true;
			if (prefix3.equals("649"))
				return true;
		}

		if (this.number.length() >= 4) {
			String prefix4 = this.number.substring(0, 4);
			if (prefix4.equals("6011"))
				return true;
		}

		if (this.number.length() >= 6) {
			String prefix6 = this.number.substring(0, 6);
			for (int i = 622126; i < 622925; i++) {
				if (prefix6.equals(Integer.toString(i)))
					return true;
			}
		}

		return false;
	}

	public boolean isDinersCarteBlanche() {
		// Diner’s Club11 ; starts with 30,36,38 ; length 14 ; eg. 3000 0000
		// 0000 04
		if (this.number.length() >= 3) {
			String prefix3 = this.number.substring(0, 3);

			if (prefix3.equals("300"))
				return true;
			if (prefix3.equals("301"))
				return true;
			if (prefix3.equals("302"))
				return true;
			if (prefix3.equals("303"))
				return true;
			if (prefix3.equals("304"))
				return true;
			if (prefix3.equals("305"))
				return true;
		}
		return false;
	}

	public boolean isDinersInternational() {
		//
		if (this.number.length() >= 2) {
			if (this.number.substring(0, 2).equals("36"))
				return true;
		}
		return false;
	}

	public boolean isDinersUSAandCanada() {
		//
		if (this.number.length() >= 2) {
			if (this.number.substring(0, 2).equals("54"))
				return true;
		}
		return false;
	}

	public boolean isAmericanExpress() {
		// American Express12 34,37 15 3400 0000 0000 009
		if (this.number.length() >= 2) {
			if (this.number.substring(0, 2).equals("34"))
				return true;
			if (this.number.substring(0, 2).equals("37"))
				return true;
		}
		return false;
	}

	public boolean isEnRoute() {
		// en Route 2014,21 15 2014 0000 0000 009
		if (this.number.length() >= 2) {
			if (this.number.substring(0, 2).equals("21"))
				return true;
		}
		if (this.number.length() >= 4) {
			if (this.number.substring(0, 4).equals("2014"))
				return true;
		}
		return false;
	}

	public boolean isJCB() {
		// JCB 3088,3096,3112,3158,3337,3528 16 3088 0000 0000 0009
		if (this.number.length() >= 4) {
			String prefix4 = this.number.substring(0, 4);

			if (prefix4.equals("3088"))
				return true;
			if (prefix4.equals("3096"))
				return true;
			if (prefix4.equals("3112"))
				return true;
			if (prefix4.equals("3158"))
				return true;
			if (prefix4.equals("3337"))
				return true;
			if (prefix4.equals("3528"))
				return true;
		}
		return false;
	}

	/**
	 * Check 
	 * @return true if card is a Maestro Card
	 */
	public boolean isMaestro() {
		// Maestro 5018, 5020, 5038, 5893, 6304, 6759, 6761, 6762, 6763 ;
		// length 16-19
		if (this.number.length() >= 4) {
			String prefix4 = this.number.substring(0, 4);

			if (prefix4.equals("5018"))
				return true;
			if (prefix4.equals("5020"))
				return true;
			if (prefix4.equals("5038"))
				return true;
			if (prefix4.equals("5893"))
				return true;
			if (prefix4.equals("6304"))
				return true;
			if (prefix4.equals("6759"))
				return true;
			if (prefix4.equals("6761"))
				return true;
			if (prefix4.equals("6762"))
				return true;
			if (prefix4.equals("6763"))
				return true;
		}
		return false;
	}
	
	
	
}
