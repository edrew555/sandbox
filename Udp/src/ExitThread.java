
import java.net.*;  // for Socket, ServerSocket, and InetAddress
import java.io.*;   // for IOException and Input/OutputStream
import java.util.Scanner;


public class ExitThread {

   static Runnable exitRunnable = new Runnable() {
	   public void run() {
			//
			// Stay looping, exit on "99" entered from keyboard
			//
			System.out.print("Enter 99 to Exit cleanly: \n"); 
			Scanner input = new Scanner(System.in); 	//opens a scanner, keyboard
			int myInt;					//store the input from the user
			myInt = 0;
			while(myInt != 99) {	
				System.out.flush();
				try 
				{
					myInt = input.nextInt();
					if(myInt == 99) { 
						System.out.printf("Terminating\n");	
						System.exit(0);
					}
					myInt = 0;
				} 
				catch (Exception e) {
					input.nextLine();
					myInt = 0;
				}
			}
	  }
	};
}

