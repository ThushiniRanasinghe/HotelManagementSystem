package Coursework1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class Main {
	//declaring variables as private static
	private static String[] hotel = new String[11];
	private static int roomNum;
	private static String roomName;
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		//displaying the welcome page
		System.out.println();
		System.out.println("       WELCOME TO HOTEL QUEENS");
		System.out.println("------------------------------------");
		System.out.println();
		
		//calling the method initialize
		initialize(hotel);

		//calling the method coverPage
		coverPage();

		//calling the method menu
		menu();
	}

	//method where the array hotel is initialized
	private static void initialize(String hotel[] ){
		for (int i = 1; i < hotel.length; i++) {
			hotel[i] = "empty";
		}
	}

	//method where the inputs for the procedures of menu is being checked
	public static void menu() {
		
		while (true) {
			//prompting the user to enter a input 
			System.out.print("Enter you command: \n");
			String selection = sc.next().toLowerCase();
			if (selection.equals("a")) {
				//calling the method addDetails
				addDetails();
			} else if (selection.equals("v")) {
				//calling the method view
				view();
			} else if (selection.equals("e")) {
				//calling the method  emptyRooms
				emptyRooms();
			} else if (selection.equals("d")) {
				//calling the method deleteCustomer
				deleteCustomer();
			} else if (selection.equals("f")) {
				//calling the method find
				find();
			} else if (selection.equals("s")) {
				//calling the method  storeDetails
				storeDetails();
			} else if (selection.equals("l")) {
				//calling the method load
				load();
			} else if (selection.equals("o")) {
				//calling the method displayOrderedRoom
				displayOrderedRoom();
			} else if (selection.equals("q")) {
				//terminating the whole program
				System.exit(0);
			} else {
				/*displaying error message for the invalid inputs*/
				System.out.println("Invaild input!!");
			}
			
		}
	}
	
	//method where the menu of the hotel is displayed 
	public static void coverPage() {
		System.out.println("Menu \n");
		System.out.println("Enter A : Add a customer to a room ");
		System.out.println("Enter V : View all rooms");
		System.out.println("Enter E : Display empty rooms");
		System.out.println("Enter D : Delete customer from room");
		System.out.println("Enter F : Find room from customer");
		System.out.println("Enter S : Store program array data into a text file");
		System.out.println("Enter L : Load program data back to the array from the text file");
		System.out.println("Enter O : View romms ordered alphabetically by name");
		System.out.println("Enter Q : Exit the menu");
	}

	//method where the details of the customer is added to the hotel array
	public static void addDetails() {
	String wrong="";
		try{
			/*prompting user to enter a room number between 1 and 10*/
			System.out.print("Enter room number (1-10): ");

			/*validating to check whether the entered value is an integer or not*/
			if (sc.hasNextInt()) {
				//initializing roomNum
				roomNum = sc.nextInt();
			} else {
				//displaying error message for invalid inputs
				wrong=sc.next();
				System.out.println("Invalid room number!!");
			}
		
			if((roomNum>0)&& (roomNum<11)){
				//prompting user to enter a room name
				System.out.print("Enter name for room " + roomNum + " : ");
				/*validating to check whether a value is entered or not*/
				if (sc.hasNext()) {
					//initializing roomName
					roomName = sc.next().toLowerCase();
				} 
				/*initializing hotel array at index roomNum*/
				hotel[roomNum] = roomName;
			}
			
		}catch(Exception e){
			//displaying error message for invalid inputs
			System.out.println("Invalid Input!");
		}
	}

	/*method that is used  to view all the rooms in the hotel room and view customer name if occupied*/
	public static void view() {
		for (int i = 1; i < hotel.length; i++) {
			if (hotel[i].equals("empty")) {
				//displaying empty rooms
				System.out.println("Room " + (i) + " is Empty");
			} else {
				//displaying occupied rooms with the customer name
				System.out.println("Room " + (i) + " is occupied by "+ hotel[i]);
			}
		}
	}

	//method that is used to display empty rooms of the hotel
	public static void emptyRooms() {
		for (int i = 1; i < hotel.length; i++) {
			if (hotel[i].equals("empty")) {
				//displaying empty rooms
				System.out.println("Room " + (i) + " is Empty");
			}
		}
	}


	/*method that is used to delete customer details if the room number is given*/
	public static void deleteCustomer() {
		String wrong="";
		int answer=0;
		try{
			//prompting user to enter a room number 
			System.out.println("Enter room number: ");
			answer=sc.nextInt();
			if((answer>0)&& (answer<11)){
				roomNum = answer;
			}
			if((answer>0)&& (answer<11)){
				if (answer< hotel.length){
					/*set hotel array at index roomNum to empty*/
					hotel[roomNum] = "empty";
					/*display message if the customer details are successfuly deleted*/
					System.out.println("Customer successfully deleted!!");
				}
			}
		} catch (Exception e) {
			wrong=sc.next();
			//displaying error message for invalid inputs
			System.out.println("room number you entered is unavailable!! ");
		}
	}

	/*method where we can find which room the customer is occupying by giving the name of the customer*/
	public static void find() {
		//prompting user to enter a room name
		System.out.print("Enter the room name: ");
		//declaring and initializing variable answer
		String answer = sc.next().toLowerCase();
		//declaring and initializing variable i
		int i = 1;
		try{
			for (;i < hotel.length; i++) {
				if (hotel[i].equals(answer)) {
					break;
				} 
			}
			if (hotel[i].equals(answer)) {
				/*display message saying which room the customer is occupying*/
				System.out.println("The customer "+answer+" is occupying room no."+i);
			
			} 
			
		} catch (Exception e){
			/*displaying error message if customer is not available*/
			System.out.println("The customer you are looking for is unavailable");

		}
		
	}

	//method where the room details are being stored in a text file
	public static void storeDetails() {
		try {
			//creating a new text file
			File room = new File("Room Details.txt");
			room.createNewFile();
			
			//new FileWriter object is created
			FileWriter roomDetails = new FileWriter(room, true);

			//new BufferedWriter object is created
			BufferedWriter bWriter = new BufferedWriter(roomDetails);
			
			//writing values to the text file
			bWriter.write("Room Number \tRoom Name");
			bWriter.newLine();
			for(int x=1;x<hotel.length;x++){
			bWriter.write("\n" + x + "\t\t" + hotel[x]);
			bWriter.newLine();
			}

			bWriter.flush();
			//BufferedWriter object is being closed
			bWriter.close();
			
			/*displaying message for if the the details are successfully added*/
			System.out.println("All details are successfully added to Room Details.txt");

		} catch (IOException e) {
			/*displaying error message if room details.txt is not found*/
			System.out.println("Room Details.txt not found ");
		}
	}

	//method where the text file is read and the necessity details are being replaced with the data that has been already stored in the hotel array
	public static void load(){
			BufferedReader bufferreader = null;
			
			try{
				/*new BufferedReader object is being created*/
				bufferreader = new BufferedReader(new InputStreamReader(new FileInputStream("Room Details.txt")));
				String delims = "\t"+"\t";
				int tempNum = 0;
				String tempName = "";
				
				bufferreader.readLine();
				bufferreader.readLine();
				
				for (String line = bufferreader.readLine(); line != null;
						line = bufferreader.readLine()){
					StringTokenizer st = new StringTokenizer(line, delims);
					
					while (st.hasMoreElements()){
						tempNum = Integer.valueOf((String) st.nextElement());
						tempName = (String) st.nextElement();
						
						if (hotel.length > tempNum){
							hotel[tempNum] = tempName;
						}
					}
				}
				
			} catch (Exception e){
				/*error message that is being displayed if the text file is not been found*/
				System.err.println("Room Details.txt not found");
				
			} 
			
	}
	
	/*method where all the customers names are being arranged in an alphabetical order*/
	public static void arrangeOrderedRoom(String array[]) {
		//initializing and declaring flag variable as true
		boolean flag = true;
		String temp;

		while (flag) {
			flag = false;
			for (int j = 1; j < (array.length - 1); j++) {
				if (array[j].compareToIgnoreCase(array[j + 1])> 0 ) {
					temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
					flag = true;
				}
			}
		}
	}
	
	//method where the alphabetically arranged data is being displayed
	public static void displayOrderedRoom(){
		arrangeOrderedRoom(hotel);
		for (int i = 1; i < hotel.length; i++) {
			if (!(hotel[i].equals("empty"))) {
				//displaying the room names in an alphabetical order
				System.out.println("Room Name is: " + hotel[i]);
			}
		}
	}


}
