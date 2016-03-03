public class NotifyUser {
	
	static boolean currencyPairFound = false;
		
	public boolean notificaton(String currencyPair,String currencyPairfromParser,double targetRate,double bidRatefromParserDouble,int choice)
	{
		try
		{
			if(currencyPair.equalsIgnoreCase(currencyPairfromParser))
			{
				currencyPairFound = true;
				System.out.println("Current Rate: "+bidRatefromParserDouble);
				switch (choice) 
				{
					case 1: 
						if(bidRatefromParserDouble < targetRate)
						{	
							System.out.println("The Target price has reached.The current rate is less than the target rate.");
							System.out.println("CURRENCY PAIR: "+currencyPair);
							System.out.println("CURRENT RATE: "+bidRatefromParserDouble);
							System.out.println("TARGET RATE: "+targetRate);
							return true;
						}
						break;
					case 2:
						if(bidRatefromParserDouble > targetRate)
						{
							System.out.println("The Target price has reached. The current rate is greater than the target rate.");
							System.out.println("CURRENCY PAIR: "+currencyPair);
							System.out.println("CURRENT RATE: "+bidRatefromParserDouble);
							System.out.println("TARGET RATE: "+targetRate);
							return true;
						}
						break;
					case 3:
						if(bidRatefromParserDouble == targetRate)
						{
							System.out.println("The Target price has reached. The current rate is exactly equal to the target rate.");
							System.out.println("CURRENCY PAIR: "+currencyPair);
							System.out.println("CURRENT RATE: "+bidRatefromParserDouble);
							System.out.println("TARGET RATE: "+targetRate);
							return true;
						}
						break;
					case 4:
						if(bidRatefromParserDouble <= targetRate)
						{
							if (bidRatefromParserDouble < targetRate)
								System.out.println("The Target price has reached. The current rate is less than the target rate.");
							else
								System.out.println("The Target price has reached. The current rate is equal to the target rate.");
							
							System.out.println("CURRENCY PAIR: "+currencyPair);
							System.out.println("CURRENT RATE: "+bidRatefromParserDouble);
							System.out.println("TARGET RATE: "+targetRate);
							return true;
						}
						break;
					case 5:
						if(bidRatefromParserDouble >= targetRate)
						{
							if (bidRatefromParserDouble > targetRate)
								System.out.println("The Target price has reached...The current rate is greater than the target rate...");
							else
								System.out.println("The Target price has reached...The current rate is equal to the target rate...");
							
							System.out.println("CURRENCY PAIR: "+currencyPair);
							System.out.println("CURRENT RATE: "+bidRatefromParserDouble);
							System.out.println("TARGET RATE: "+targetRate);
							return true;
						}
						break;	
				}
			}
		}
	
		catch (Exception e){
			System.out.println("Error in NotifyUser class.");
			System.out.println("Exception"+e);
		}
		return false;
	}
}
