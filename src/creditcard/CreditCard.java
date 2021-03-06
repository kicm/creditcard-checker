package creditcard;

/**
 * CreditCard Object
 * Stores a credit card number, perform luhn algorithm check, return card issuer
 * some numbers to check:
 * http://www.freeformatter.com/credit-card-number-generator-validator.html
 *
 * @author marco, marco, manuel
 */
public class CreditCard {
	private String number;
	private static final int MIN_LENGTH = 13;
	private static final int MAX_LENGTH = 19;
	
	/**
	 * Default constructor
	 * Construct a blank credit card (null)
	 */
	public CreditCard() {
		this.number = null;
	}
	/**
	 * Constructs a credit card with the given card number
	 * @param numberStr the number of the credit card
	 */
	public CreditCard(String numberStr) {
		this.number = numberStr;
	}
	
	/**
	 * Constructs a credit card with the given card number
	 * @param numberInt
	 * 			The number of the credit card as Integer
	 */
	public CreditCard(int numberInt) {
		this.number = String.valueOf(numberInt);
	}
	
	/**
	 * Set the number of the credit card 
	 * @param numberStr
	 * 			The number of the credit card as a string.
	 */
	public void setNumber(String numberStr) {
		this.number = numberStr;
	}
	
	/**
	 * Set the number of the credit card 
	 * @param numberInt
	 * 			The number of the credit card as Integer.
	 */
	public void setNumber(int numberInt) {
		this.number = String.valueOf(numberInt);
	}
	
	/**
	 * Get the number of the credit card 
	 * @return The number of the credit card as String.
	 */
	public String getNumber() {
		return this.number;
	}
	
	/**
	 * Performs a luhn algorithm check.
	 * additional information: http://de.wikipedia.org/wiki/Luhn-Algorithmus#Java
	 * 
	 * @return true if creditcard passes Luhn Algorithm
	 */
	public boolean luhnTest() {
		int sum = 0;
		int length = this.number.length();
		
		for(int i = 0; i < length; i++){
			
			// get digits in reverse order
			// use Character.digit to provide '-1' if no numeric value
			int digit = Character.digit(this.number.charAt(length - i - 1), 10);
			
			// every 2nd number multiply with 2
			if (i % 2 == 1) {
	            digit = digit * 2;
	        }
			
			if (digit > 9) {
				sum = sum + digit - 9;
			} else{
				sum = sum + digit;
			}
			
		}

		return sum % 10 == 0;
	}
	
	
	/**
	 * Checks if the number matches a known card issuer.
	 * @return The credit card issuer as a String.
	 *  		"Unknown Issuer" if no issuer prefix matches.
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

			if (this.isAmericanExpress())
				return "American Express";

			if (this.isJCB())
				return "JCB";

			if (this.isMaestro())
				return "Maestro";
			
			return "Unknown Issuer";

	}
	/**
	 * Checks the validity of the credit card number.
	 * @return the validity of the credit card number.
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
	
	/**
	 * Check whether card number matches the VISA prefix or not.
	 * @return true if card is a VISA
	 */
	public boolean isVisa() {
		// VISA Check; starts with 4 ; length 13,16 ; eg. 4111 1111 1111 1111
		return this.number.startsWith("4");
	}

	/**
	 * Check whether card number matches the Master Card prefix or not.
	 * @return true if card is a Master Card
	 */
	public boolean isMasterCard() {
		// MasterCard Check ; starts with 51,52,53,54,55
		// length 16; eg. 5500 0000 0000 0004

		return  this.number.startsWith("51") || 
				this.number.startsWith("52") || 
				this.number.startsWith("53") || 
				this.number.startsWith("54") || 
				this.number.startsWith("55");
	}

	/**
	 * Check whether card number matches the Discover Card prefix or not.
	 * @return true if card is a Discover Card
	 */
	public boolean isDiscover() {
		// Discover Card Check ; starts with 6011 ; 
		// 6011, 622126 to 622925, 644, 645, 646, 647, 648, 649, 65 
		// eg. 6011 0000 0000 0004; length 16
		
		
		if ( this.number.startsWith("65")  || 
			 this.number.startsWith("644") || 
			 this.number.startsWith("645") || 
			 this.number.startsWith("646") || 
			 this.number.startsWith("647") || 
			 this.number.startsWith("648") || 
			 this.number.startsWith("649") || 
			 this.number.startsWith("6011") )
			 	return true;
		
		for (int i = 622126; i <= 622925; i++) {
			if (this.number.startsWith(Integer.toString(i)))
				return true;
		}

		return false;
	}

	/**
	 * Check whether card number matches the Diners Club - Carte Blanche prefix or not.
	 * @return true if card is a Diners Club - Carte Blanche
	 */
	public boolean isDinersCarteBlanche() {
		// Diners Club11 ; starts with 30,36,38 ; length 14 ; eg. 3000 0000
		// 0000 04
		
		return  this.number.startsWith("300") || 
				this.number.startsWith("301") || 
				this.number.startsWith("302") || 
				this.number.startsWith("303") || 
				this.number.startsWith("304") || 
				this.number.startsWith("305");
	}

	/**
	 * Check whether card number matches the Diners Club - International prefix or not.
	 * @return true if card is a Diners Club - International
	 */
	public boolean isDinersInternational() {
		//
		return this.number.startsWith("36");
	}


	/**
	 * Check whether card number matches the AmEx prefix or not.
	 * @return true if card is an AmEx
	 */
	public boolean isAmericanExpress() {
		// American Express12 34,37 15 3400 0000 0000 009
		// Length 15
		return  this.number.startsWith("34") || 
				this.number.startsWith("37");
	}

	/**
	 * Check whether card number matches the JCB prefix or not.
	 * @return true if card is a JCB Card
	 */
	public boolean isJCB() {
		// JCB 3088,3096,3112,3158,3337,3528 16 3088 0000 0000 0009
		return  this.number.startsWith("3088") || 
				this.number.startsWith("3096") || 
				this.number.startsWith("3112") || 
				this.number.startsWith("3158") || 
				this.number.startsWith("3337") || 
				this.number.startsWith("3528");
	}

	/**
	 * Check whether card number matches the Maestro Card prefix or not.
	 * @return true if card is a Maestro Card
	 */
	public boolean isMaestro() {
		// Maestro 5018, 5020, 5038, 5893, 6304, 6759, 6761, 6762, 6763 ;
		// length 16-19
		return  this.number.startsWith("5018") || 
				this.number.startsWith("5020") || 
				this.number.startsWith("5038") || 
				this.number.startsWith("5893") || 
				this.number.startsWith("6304") || 
				this.number.startsWith("6759") || 
				this.number.startsWith("6761") || 
				this.number.startsWith("6762") ||
				this.number.startsWith("6763");
	}

}
