public class Scheduler {
	
	public void schedule(String currencyPair,double targetRate,int choice,String url) throws InterruptedException
	{
		try
  		{
	  		boolean target_reached = false;
	  		while(!target_reached)
	  		{
				target_reached = Parser.parseXML(currencyPair,targetRate,choice,url);
				if(NotifyUser.currencyPairFound == false)
				{
					System.out.println("Sorry currency pair you provided is not found!");
					break;
				}
				if(!target_reached){
					System.out.println("Updating the rate! Please wait...");
					Thread.sleep(4000);
				}
	  		}
  		}
  		catch(Exception e)
  		{
  			System.out.println("Error in Scheduler class");
  			System.out.println("Exception"+e);
  		}
	}

}
