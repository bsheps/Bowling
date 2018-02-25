import java.io.IOException;
import java.util.Scanner;

public class ATM {

	public static void main(String[] args) throws IOException {
		// startup atm
		initialize();
		start();
	}
	public static void initialize() {
		// TODO Auto-generated method stub
		Bank b1 = new Bank();		
		b1.createAccount(1234, 6789, 80);
		b1.createAccount(6789, 4321, 60);
	}
/** Turns the ATM on
 * @throws IOException 
 * @note: We setup the accounts at the begining of start() per the lab4 instructions.
 * @category main while loop---
 * main loop for ATM 
 * all validation for account/ pin, withdraw, deposit, check balance functions happen with this loop
 */
	public static void start() throws IOException {
		//setup bank per lab4 pdf

		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		ATMprinter p = new ATMprinter();
		String entry= "";
		CardReader cardReader = new CardReader();
		cashDispenser dispenser = new cashDispenser();

		while (!entry.equals("exit")) {	// 
			try {	
				System.out.println("Welcome to the ATM.");
				int accountNum =Integer.parseInt(cardReader.cardRead());
				p.printThis("CARDREAD "+ Integer.toString(accountNum), true);
				p.printThis("ENTER PIN", false);
				int pinEntered =  Integer.parseInt(input.nextLine());
				if (Bank.validate(accountNum, pinEntered)) {
					Account customer = Bank.getAcc(accountNum);					
						while (!entry.equals("CANCEL")) {
							p.printThis(("Choose transaction (W - withdraw, CB - check balance, CANCEL - return card"), false);
							entry = input.nextLine();
							p.printThis("BUTTON " + entry, true);
							switch(entry) {
								case	"CANCEL" :
									p.printThis("ENJECT CARD", false);
									break;

								case 	"CB" :
									p.printThis(Double.toString(customer.getBalance()), true);
									break;
								case	"W"	:
									p.printThis("Amount?", false);
									String withdraw = input.nextLine();
									if(customer.withdrawl(Double.parseDouble(withdraw)))
										if(!dispenser.dispense(Integer.parseInt(withdraw))){	// if atm is out of money, redeposit the amount back to the account
											customer.deposit(Double.parseDouble(withdraw));	
											p.printThis("ATM out of cash", true);
										}
										else 
											p.printThis("NEW BALANCE: "+ Double.toString(customer.getBalance()), true);
							default:
								break;
							}
							
						}
				}
				else {
					System.out.println("Invalid account number or pin."); 				
				}
			}
			catch(NumberFormatException e) { 
				System.out.println("ERROR! INVALID:" +e.getMessage());	
			}
		}
	}
}