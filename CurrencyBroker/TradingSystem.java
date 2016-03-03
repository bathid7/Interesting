import java.util.Scanner;

public class TradingSystem {
	
	public static String currencyPair = null;
	public static double targetRate = 0.0;
	public static int choice = 0;
	public static int mode = 0;
	public static String url = "http://rates.fxcm.com/RatesXML";
	
	public static void main(String args[]) throws InterruptedException {
		  
		  boolean checkAgain = true;
		  boolean continueFurther = true;
		  int option = 0;
		  Scheduler sch = new Scheduler();
		  Scanner cp = new Scanner(System.in);
		  
		  while(checkAgain)
		  {
			  System.out.println("***Please enter the Currency Pair***");
			  currencyPair  = cp.nextLine();
			  System.out.println("Entered Currency Pair: "+currencyPair);
			  
			  System.out.println("Please enter the Target rate");
			  targetRate  = cp.nextDouble();
			  System.out.println("Entered Target rate: "+targetRate);
			  
			  System.out.println("***Enter the Alert type***");
		      System.out.println("1. Less than the target rate\n2. Greater than the target rate\n3. Exactly equal to target rate\n4. Less than or Equal to target rate\n5. Greater than or Equal to target rate");
		      choice = cp.nextInt();
		      cp.nextLine();

			  sch.schedule(currencyPair,targetRate,choice,url);
			  
			  System.out.println("Do you like to continue or exit. Please enter an option");
			  System.out.println("1.Continue");
			  System.out.println("2.Exit");
			  option = cp.nextInt();
			  cp.nextLine();

			  switch(option)
			  {
			  	case 1: 	
			  		checkAgain = true;
			  		break;
			  	case 2: 	
			  		checkAgain = false;
			  		System.out.println("Thank you!");
			  		break;
			  	default : 	
			  		do{
			  			System.out.println("Invalid choice. Please enter a valid option");
			  			option = cp.nextInt();
			  			cp.nextLine();
			  			if(option == 1 || option == 2)
			  			{
			  			  	continueFurther = false;
			  			  	if(option == 2)
			  			  		checkAgain = false;
			  			}
			  		}while(continueFurther);
			  		break;
			  }
			  
		  }
		  cp.close();
	}
	
}

