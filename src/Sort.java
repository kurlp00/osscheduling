
public class Sort{
	
	private static int intArrivalTime[];
	private static int intBurstTime[];
	private static int intPrio[];
	private static String arrLetters[];
	
	
	public void sort(String[] strLetters, int intAT[], int intBT[], int intPriority[], int intJobs){
		
		int intTempAT = 0, intTempBT = 0, intTempPrio = 0;
		String strTempLetters = "";
		
		for(int intCounter = 0; intCounter < intJobs; intCounter++){
			for(int intCounter2 = 0;  intCounter2 < intJobs - 1; intCounter2++){
				if(intAT[intCounter2] > intAT[intCounter2 + 1]){
					
					strTempLetters = strLetters[intCounter2];
					strLetters[intCounter2] = strLetters[intCounter2 + 1];
					strLetters[intCounter2 + 1] = strTempLetters;
					
					intTempAT = intAT[intCounter2];
					intAT[intCounter2] = intAT[intCounter2 + 1];
					intAT[intCounter2 + 1] = intTempAT;
				
					intTempBT = intBT[intCounter2];
					intBT[intCounter2] = intBT[intCounter2 + 1];
					intBT[intCounter2 + 1] = intTempBT;
					
					intTempPrio = intPriority[intCounter2];
					intPriority[intCounter2] = intPriority[intCounter2 + 1];
					intPriority[intCounter2 + 1] = intTempPrio;
					break;
				}
			}
		}
		intArrivalTime = intAT;
		intBurstTime = intBT;
		intPrio = intPriority;
		arrLetters = strLetters;
	}
	
	public String[] getLetters(){
		return arrLetters;
	}
	
	public int[] getArrivalTime(){
		return intArrivalTime;
	}
	
	public int[] getBurstTime(){
		return intBurstTime;
	}
	
	public int[] getPriority(){
		return intPrio;
	}
}
	
