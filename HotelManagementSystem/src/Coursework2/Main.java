package Coursework2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

class Customer {
	private String name;
	private int room;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	public int getRoom() {
		return this.room;
	}
}

class Hotel {

	// method where the array hotel is being initialized
	public static void initialize(Customer RoomList[]) {
		for (int i = 1; i < RoomList.length; i++) {
			RoomList[i] = new Customer();
			RoomList[i].setName("EMPTY");
			RoomList[i].setRoom(i);
		}
	}

	/*
	 * method that is used to view all the rooms in the hotel room and view
	 * customer name if occupied
	 */
	public static void viewList(Customer RoomList[]) {
		for (int i = 1; i < RoomList.length; i++) {
			if (RoomList[i].getName() == "EMPTY") {
				// displaying empty rooms
				System.out.println("Room number " + RoomList[i].getRoom() + " is empty");
			} else {
				// displaying occupied rooms with the customer name
				System.out.println(
						"Room number " + RoomList[i].getRoom() + " is ocupied by " + RoomList[i].getName() + ".");
			}
		}
		System.out.println();
	}

	// method where the details of the customer is added to the hotel array
	public static void addCustomer(Customer RoomList[], String name, int no) {
		Scanner sc = new Scanner(System.in);
		String wrong = "";
		try {
			/* prompting user to enter a room number between 1 and 10 */
			System.out.print("Enter room Number(1-10): ");
			/*
			 * validating to check whether the entered value is an integer or
			 * not
			 */
			if (sc.hasNextInt()) {
				no = sc.nextInt();

			} else {
				// displaying error message for invalid inputs
				wrong = sc.next();
				// displaying error message for invalid inputs
				System.out.println("Invalid room number!!");
			}

			if ((no > 0) && (no < 11)) {
				// prompting user to enter the name
				System.out.print("Enter Customer's name: ");
				/* validating to check whether a value is entered or not */
				if (sc.hasNext()) {
					// initializing name
					name = sc.next().toLowerCase();
				}

				if (RoomList[no].getName().equals("EMPTY")) {
					RoomList[no].setName(name);
					Queue.insert(name);
					System.out.println("Customer was added!!");

				} else {
					System.out.println("Room is occupied!!");
				}
			} else {
				// displaying error message for invalid room numbers
				System.out.println("Invalid room number!!");
			}
		} catch (Exception e) {
			// displaying error message for invalid inputs
			System.out.println("Invalid Input!!!");
		}

	}

	// method that is used to display empty rooms of the hotel
	public static void showEmptyRooms(Customer RoomList[]) {
		System.out.println("Available rooms are:");
		for (int i = 1; i < RoomList.length; i++)
			if (RoomList[i].getName() == "EMPTY")
				// displaying empty rooms
				System.out.println("Room " + RoomList[i].getRoom() + " is empty");
		System.out.println();
	}

	/*
	 * method that is used to delete customer details if the room number is
	 * given
	 */
	public static void deleteCustomer(Customer RoomList[], String name) {
		Scanner sc = new Scanner(System.in);
		int count = 0;
		// prompting user to enter a customer name to delete
		System.out.print("Enter Customer's name: ");

		name = sc.next();
		System.out.println();
		int i = 1;
		for (; i < RoomList.length; i++) {
			try {
				if (RoomList[i].getName().equals(name)) {
					RoomList[i].setName("EMPTY");
					/*
					 * display message if the customer details are successfully
					 * deleted
					 */
					System.out.println("Deletion successful");
					count++;
				}

			} catch (Exception e) {
				// displaying error message for invalid inputs
				System.out.println("Invalid Input");

			}

		}
		if (count != 1) {
			// displaying error message for invalid inputs
			System.out.println("Customer name you entered is unavailable");
		}
	}

	public static void getIndex(Customer RoomList[], String name) {
		Scanner sc = new Scanner(System.in);
		// declaring and initializing variable count
		int count = 0;
		// prompting user to enter a room name
		System.out.print("Enter Customer's name: ");
		name = sc.next();
		System.out.println();
		for (int i = 1; i < RoomList.length; i++) {
			try {
				if (RoomList[i].getName().equals(name)) {
					/*
					 * display message saying which room the customer is
					 * occupying
					 */
					System.out.println("Customer " + name + " is occuping room " + RoomList[i].getRoom());
					count++;
				}
			} catch (Exception e) {
				// displaying error message for invalid inputs
				System.out.println("Customer you are looking for is unavailable1");
			}
		}

		if (count != 1) {
			/* displaying error message if customer is not available */
			System.out.println("Customer you are looking for is unavailable");
		}

	}

	// method where the room details are being stored in a text file
	public static void storeDetails(Customer RoomList[]) {
		try {
			// creating a new text file
			File room = new File("Room Details.txt");
			room.createNewFile();

			// new FileWriter object is created
			FileWriter roomDetails = new FileWriter(room, true);

			// new BufferedWriter object is created
			BufferedWriter bWriter = new BufferedWriter(roomDetails);

			// writing values to the text file
			bWriter.write("Room Number \tRoom Name");
			bWriter.newLine();
			for (int x = 1; x < RoomList.length; x++) {
				bWriter.write("\n" + x + "\t\t" + RoomList[x].getName());
				bWriter.newLine();
			}

			bWriter.flush();
			// BufferedWriter object is being closed
			bWriter.close();

			/*
			 * displaying message for if the the details are successfully added
			 */
			System.out.println("All details are successfully added to Room Details.txt");

		} catch (IOException e) {
			/* displaying error message if room details.txt is not found */
			System.out.println("Room Details.txt not found ");
		}
	}

	// method where the text file is read and the necessity details are being
	// replaced with the data that has been already stored in the hotel array
	public static void load(Customer RoomList[]) {
		BufferedReader bufferreader = null;

		try {
			/* new BufferedReader object is being created */
			bufferreader = new BufferedReader(new InputStreamReader(new FileInputStream("Room Details.txt")));
			String delims = "\t" + "\t";
			int tempNum = 0;
			String tempName = "";

			bufferreader.readLine();
			bufferreader.readLine();

			for (String line = bufferreader.readLine(); line != null; line = bufferreader.readLine()) {
				StringTokenizer st = new StringTokenizer(line, delims);

				while (st.hasMoreElements()) {
					tempNum = Integer.valueOf((String) st.nextElement());
					tempName = (String) st.nextElement();

					if (RoomList.length > tempNum) {
						RoomList[tempNum].setName(tempName);
					}
				}
			}

		} catch (Exception e) {
			/*
			 * error message that is being displayed if the text file is not
			 * been found
			 */
			System.err.println("Room Details.txt not found");

		}

	}

	private static void queue(Customer RoomList[], String name) {

		BufferedReader br = null;
		System.out.println("File Read Completed");
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader("Room Details.txt"));
			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}

public class Main {

	static Customer[] RoomList = new Customer[11];
	static int no = 0;
	static String name;

	public static void main(String[] args) {

		Hotel.initialize(RoomList);

		// displaying the welcome page
		System.out.println();
		System.out.println("       WELCOME TO HOTEL QUEENS");
		System.out.println("------------------------------------");
		System.out.println();
		System.out.println("Menu \n");
		System.out.println("Enter A : Add a customer to a room ");
		System.out.println("Enter V : View all rooms");
		System.out.println("Enter E : Display empty rooms");
		System.out.println("Enter D : Delete customer from room");
		System.out.println("Enter F : Find room from customer");
		System.out.println("Enter S : Store program array data into a text file");
		System.out.println("Enter L : Load program data back to the array from the text file");
		System.out.println("Enter G : Display names of first 3 customers who have been allocated to a room");
		System.out.println("Enter I : Display Queue");
		System.out.println("Enter Q : Exit the menu");

		menu();
	}

	public static void menu() {
		Scanner input = new Scanner(System.in);
		String option = "";

		do {
			System.out.print("\nEnter your choice: ");
			option = input.next().toLowerCase();
			System.out.println();

			switch (option) {
			case "v": {
				Hotel.viewList(RoomList);
				break;
			}
			case "a": {

				Hotel.addCustomer(RoomList, name, no);
				break;
			}
			case "e": {
				Hotel.showEmptyRooms(RoomList);
				break;
			}
			case "d": {

				Hotel.deleteCustomer(RoomList, name);
				break;
			}
			case "f": {

				Hotel.getIndex(RoomList, name);
				break;
			}
			case "s": {
				Hotel.storeDetails(RoomList);
				break;
			}
			case "l": {
				Hotel.load(RoomList);
				break;
			
			}
			case "g": {
				Queue.Display_And_Remove_From_Queue();
				break;
			}
			case "i": {
				Queue.viewQueue();
				break;
			}
			case "q": {
				System.out.println("\nThank you!\n");
				break;
			}
			default: {
				System.out.println("Invalid option!\n");
				break;
			}
			}

		} while (!(option.equals("q")));
	}
}

class Queue {
	//declaring and initializing variables
	static private Object[] theArray = new Object[8];
	static private int currentSize;

	/* Insert a new item into the queue */
	public static void insert(String name) {
		if (currentSize > 6) {
			System.out.println("");
			System.out.println("Queue is full");
			System.out.println("");
			currentSize++;
			System.out.println(theArray[7] + " was removed from the queue");
			theArray[7] = theArray[6];
			theArray[6] = theArray[5];
			theArray[5] = theArray[4];
			theArray[4] = theArray[3];
			theArray[3] = theArray[2];
			theArray[2] = theArray[1];
			theArray[1] = name;
		} else {

			currentSize++;

			if (currentSize == 1) {
				theArray[1] = name;

			} else if (currentSize == 2) {
				theArray[2] = theArray[1];
				theArray[1] = name;

			} else if (currentSize == 3) {
				theArray[3] = theArray[2];
				theArray[2] = theArray[1];
				theArray[1] = name;

			} else if (currentSize == 4) {
				theArray[4] = theArray[3];
				theArray[3] = theArray[2];
				theArray[2] = theArray[1];
				theArray[1] = name;
			} else if (currentSize == 5) {
				theArray[5] = theArray[4];
				theArray[4] = theArray[3];
				theArray[3] = theArray[2];
				theArray[2] = theArray[1];
				theArray[1] = name;
			} else if (currentSize == 6) {
				theArray[6] = theArray[5];
				theArray[5] = theArray[4];
				theArray[4] = theArray[3];
				theArray[3] = theArray[2];
				theArray[2] = theArray[1];
				theArray[1] = name;
			} else if (currentSize == 7) {
				theArray[7] = theArray[6];
				theArray[6] = theArray[5];
				theArray[5] = theArray[4];
				theArray[4] = theArray[3];
				theArray[3] = theArray[2];
				theArray[2] = theArray[1];
				theArray[1] = name;
			}
		}
	}
	
	//method where names of first 3 customers who have been allocated to a room are being displayed
	static void Display_And_Remove_From_Queue() {
		int count = 1;
		for (int y = 7; y >= 1; --y) {
			if (!(theArray[y] == null) && count < 4) {
				count++;
				System.out.print(theArray[y] + " has been removed \n");
				theArray[y] = null;

			}

		}

	}
	//method where the queue details are being displayed
	static void viewQueue() {
		for (int i = 1; i <= 7; i++) {

			System.out.println("Queue Number " + (i) + " is " + theArray[i]);

		}
	}
}
