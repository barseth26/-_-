package oasisInfoByte_tasks;

import java.util.HashMap;
import java.util.Scanner;
public class OnlineReservationSystem {
	private HashMap<String,String> u=new HashMap<>();
	private HashMap<String,TicketReservation>book=new HashMap<>();
	static Scanner sc=new Scanner(System.in);
	public OnlineReservationSystem(){
		u=new HashMap<>();
		u.put("user1","1234");
		u.put("user2","5678");
	}
	public boolean login() {
		System.out.println("Enter the UserName:");
		String username=sc.next();
		System.out.println("Enter the PassWord:");
		String userpassword=sc.next();
		if(u.containsKey(username) && u.get(username).equals(userpassword)) {
			System.out.println("Login SuccessFully!!!");
			return true;
		}
		else {
			System.out.println("UserName or UserPassword is Invalid.....Try Again");
			return false;
		}
	}
	public void bookReservation() {
		System.out.println("Enter Train Number;");
		String trainNumber=sc.next();
		System.out.println("Enter Train Name:");
		sc.nextLine();
		String trainName=sc.nextLine();
		System.out.println("Enter Class type");
		String trainClass=sc.next();
		System.out.println("Enter Date Of Journey (dd-mm-yyyy");
		String date=sc.next();
		System.out.println("Enter from Station:");
		String fromPlace=sc.next();
		System.out.println("Enter To Station");
		String toPlace=sc.next();
		String pnrNumber="PNR"+(book.size()+1);
		TicketReservation r=new TicketReservation(pnrNumber,trainNumber,trainName,trainClass,date,fromPlace,toPlace);
		book.put(pnrNumber, r);
		System.out.println("Ticket Is SuccessFully Reserved \n"+"PNR number:"+pnrNumber);		
	}
	public void cancelReservation() {
		System.out.println("Enter the Ticket PNR Number:");
		sc.nextLine();
		String pnrNumber=sc.nextLine();
		if(book.containsKey(pnrNumber)) {
			book.remove(pnrNumber);
			System.out.println("Ticket Cancelled ("+pnrNumber+")!!!!");
		}
		else {
			System.out.println("Invalid PNR number !!!! Try Again");
		}
	}
	public int getVerify(Scanner sc) {
		int choice=1;
		boolean va=false;
		while(!va) {
			try {
				choice=sc.nextInt();
				va=true;
			}
			catch(Exception e){
				System.out.println("Invalid Selection!!!");
			}
		}
		return choice;
	}
	

	public static void main(String[] args) {
		OnlineReservationSystem or=new OnlineReservationSystem();
		if(or.login()) {
			boolean valid=true;
			while(valid) {
				System.out.println("\tMENU:");
				System.out.println("1.Book Ticket:");
				System.out.println("2.Cancel Ticket");
				System.out.println("3.Exit");
				int choice=or.getVerify(sc);
				switch(choice) {
				case 1:
					or.bookReservation();
					break;
				case 2:
					or.cancelReservation();
					break;
				case 3:
					System.out.println("Thanking You!!! ......Exiting");
					valid=false;
					break;
				default:
					System.out.println("Invalid Menu Choice");
					break;
				}
			}
		}

	}

}
class TicketReservation{
	private String pnrNumber;
	private String trainNumber;
	private String trainName;
	private String trainClass;
	private String date;
	private String fromPlace;
	private String toPlace;
	public TicketReservation(String pnrNumber,String trainNumber,String trainName,String trainClass,String date,String fromPlace,String toPlace) {
		this.pnrNumber=pnrNumber;
		this.trainNumber=trainNumber;
		this.trainName=trainName;
		this.trainClass=trainClass;
		this.date=date;
		this.fromPlace=fromPlace;
		this.toPlace=toPlace;
	}
	public String toString() {
		return "Reservation:PNR Number="+pnrNumber+"\n"+"Train Number=:"+trainNumber+"\n"+"Train Name="+trainName+"\n+"+"Class Type="+trainClass+"\n"+"Date Of jounerny="+date+"\n"+"From="+fromPlace+"\n"+"To="+toPlace;
	}
}
