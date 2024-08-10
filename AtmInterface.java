package oasisInfoByte_tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

class Account{
	private String userId;
	private String userPin;
	private double balance;
	private List<String> transcationHistory;
	public Account(String userId,String userPin,double balance) {
		this.userId=userId;
		this.userPin=userPin;
		this.balance=balance;
		this.transcationHistory=new ArrayList<>();
	}
	public String getUserId() {
		return userId;
	}
	public String getUserPin() {
		return userPin;
	}
	public double getBalance() {
		return balance;
	}
	public List<String> getTranscationHistory() {
		return transcationHistory;
	}
	public void deposit(double amount) {
		balance+=amount;
		System.out.println("Amount Deposited:");
	}
	public void withDraw(double amount) {
		balance-=amount;
		System.out.println("WithDraw Successfully:");
	}
	
}
class ATM{
	private HashMap<String,Account> atm;
	Account ac;
	public ATM() {
		atm=new HashMap<>();
		atm.put("user1",new Account("user1","1234",2000));
		atm.put("user2",new Account("user2","0987",3000));
		atm.put("user3",new Account("user3","5678",6000));
	}
	public boolean signIn(String userId,String userPin) {
		Account a1=atm.get(userId);
		if(a1!=null && a1.getUserPin().equals(userPin)) {
			ac=a1;
			return true;
		}
	return false;
}
	public void signOut() {
		ac=null;
	}
	public void showTransactionData() {
		if(ac!=null) {
			List<String> data=ac.getTranscationHistory();
			if(data.isEmpty()) {
				System.out.println("No Records Found!!");
			}
			else {
				for(String a:data) {
					System.out.println(a);
				}
			}
		}
	}
	public void depoist(double amount) {
		if(ac!=null && amount>0) {
			ac.deposit(amount);
		}
		else {
			System.out.println("Invalid Depoist");
		}
	}
	public void withdraw(double amount) {
		if(ac!=null && amount>0 && amount <=ac.getBalance()) {
			ac.withDraw(amount);
		}
		else {
			System.out.println("Invalid Withdraw Amount");
		}
	}
	public void transferMoney(String userId1,double amount) {
		if(ac!=null && amount>0 && amount<=ac.getBalance()) {
			Account a1=atm.get(userId1);
			if(a1!=null) {
				ac.withDraw(amount);
				a1.deposit(amount);
				ac.getTranscationHistory().add("Transfered "+amount+" to "+userId1);
				a1.getTranscationHistory().add("Received amount from "+amount+" "+ac.getUserId());
				System.out.println(amount+" Successfully Trasnfered to " +userId1);
			}
			else {
				System.out.println("No user Found!!!");
			}
		}
		else {
			System.out.println("Innvalid transfer Amount or Balance not Sufficient!!!");
		}
	}
	public void checkAccountBalance() {
		if(ac!=null) {
			System.out.println("Current Balance "+ac.getBalance());
		}
	}
	public int getVerify(Scanner sc) {
		int choice=1;
		boolean v=false;
		while(!v) {
			try {
				choice=sc.nextInt();
				v=true;
			}
			catch(InputMismatchException e) {
				System.out.println("Invalid ....Enter only number");
				sc.next();
			}
		}
		return choice;
	}
	public double getDouble(Scanner sc) {
		double amount=1;
		boolean v=false;
		while(!v) {
			try {
				amount=sc.nextDouble();
				v=true;
			}
			catch(Exception e) {
				System.out.println("Enter the ONLY VALID Amount");
				sc.next();
			}
		}
		return amount;
	}
}
public class AtmInterface {

	public static void main(String[] args) {
	ATM cus=new ATM();
	Scanner sc=new Scanner(System.in);
	System.out.println("\t\t---WELCOME TO HAPPY ATM---");
	System.out.println("Enter your UserId:");
	String userId=sc.nextLine();
	System.out.println("Enter Your UserPin:");
	String userPin=sc.nextLine();
	if(cus.signIn(userId, userPin)) {
		boolean valid=true;
		while(valid) {
			System.out.println("\t\tMenu");
			System.out.println("1.Transcation History");
			System.out.println("2.Deposit");
			System.out.println("3.Withdraw");
			System.out.println("4.Transfer");
			System.out.println("5.Check Balance");
			System.out.println("6.Quit");
			int choice=cus.getVerify(sc);
			switch(choice) {
			case 1:
				cus.showTransactionData();
				break;
			case 2:
				System.out.println("Enter the amount to Deposit:");
				double amount=cus.getDouble(sc);
				cus.depoist(amount);
				break;
			case 3:
				System.out.println("Enter the Amount to Withdraw:");
				double amount1=cus.getDouble(sc);
				cus.withdraw(amount1);
				break;
			case 4:
				System.out.println("Enter the UserId:");
				String userId1=sc.next();
				System.out.println("Enter the Amount:");
				double amount2=cus.getDouble(sc);
				cus.transferMoney(userId1, amount2);
				break;
			case 5:
				cus.checkAccountBalance();
				break;
			case 6:
				cus.signOut();
				System.out.println("Signed Out!!!!!!");
				valid=false;
				break;
			default:
				System.out.println("Invalid Choose Try Again!!!");
				break;
			}
		}
	}
	else {
		System.out.println("Invalid UserId Or UserPin........Try again");
	}
	sc.close();
	}

}
