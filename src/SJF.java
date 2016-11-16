import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;


public class SJF implements ActionListener{
	
	String[] arrLetters = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O"};
	String[] arrResultsTop = {"Jobs","Arrival Time","Burst Time","Turn Around Time","Waiting Time","Final Time"};
	
	private JFrame resultsFrame = new JFrame("Shortest Job First Results");
	private JPanel pnlContent = new JPanel();
	private JLabel[] lblTop;
	private JLabel[][] lblData;
	private JSeparator separatorTop = new JSeparator();
	private JSeparator[] separatorData;
	private JLabel lblWTAve, lblTATAve;
	private JLabel lblGantt;
	private JLabel lblTime;
	private JButton btnGantt = new JButton("Show Gantt Chart");
	private JButton btnBack = new JButton("Back");
	private JButton btnBackTo = new JButton("Back");
	private JFrame ganttFrame = new JFrame("Shortest Job First Gantt Chart");
	
	int[] intFinalTime, intWaitingTime, intTurnAroundTime;
	int intJobs;
	double dblWTSum = 0, dblTATSum = 0;
	double dblWaitingTimeAve = 0, dblTurnAroundTimeAve = 0;
	String[] strLetters;
	int[] intFinalTimeCopy;
	String strJobs = "|    ";
	
	public void SJFCompute(String[] strLetters, int[] intArrivalTime, int intBurstTime[], int intPriority[], int intJobs){
		
		resultsFrame.setContentPane(pnlContent);
		resultsFrame.setLayout(null);
		
		lblTop = new JLabel[arrResultsTop.length];
		lblData = new JLabel[intJobs][arrResultsTop.length];
		separatorData = new JSeparator[arrResultsTop.length - 1];
		intFinalTime = new int[intJobs];
		intWaitingTime = new int[intJobs];
		intTurnAroundTime = new int[intJobs];
		intFinalTimeCopy = new int[intJobs];
		this.strLetters = strLetters;
		this.intJobs = intJobs;
		
		int intCounter3 = 0;
		
		int intBurstTimeCopy[] = new int[intJobs]; 
		
		//SJF
		int intLimit = intArrivalTime[0];
		int intNextVal = 0;
		int intM = 0;
		int intMinimum = 0;
		
		for(int intCounter = 0; intCounter < intJobs; intCounter++){
			intLimit += intBurstTime[intCounter];
		}
		
		for(int intCounter = 0; intCounter < intJobs; intCounter++){
			intBurstTimeCopy[intCounter] = intBurstTime[intCounter];
		}
		System.out.print("\n\nThe gantt chart is as follows: ");
		System.out.print(intArrivalTime[0]);
		intNextVal = intArrivalTime[0];
		intWaitingTime[0] = intTurnAroundTime[0] = 0;
		intM = 1;
		do{
			intMinimum = 9999;
			for(int intCounter = 0; intArrivalTime[intCounter] <= intNextVal && intCounter < intJobs; intCounter++){
				if(intBurstTime[intCounter] < intMinimum && intBurstTime[intCounter] > 0){
					intM = intCounter;
					intMinimum = intBurstTime[intCounter];
				}
			}
			intNextVal += intBurstTime[intM];
			intBurstTime[intM] = 0;
			System.out.print("->" + strLetters[intM] + "->" + intNextVal);
			
			strJobs += strLetters[intM] + "    |     ";
			
			if(intBurstTime[intM] == 0){
				intFinalTime[intM] = intNextVal;
				intFinalTimeCopy[intCounter3] = intFinalTime[intM];
				intTurnAroundTime[intM] = intFinalTime[intM] - intArrivalTime[intM];
				intWaitingTime[intM] = intTurnAroundTime[intM] - intBurstTimeCopy[intM];
				dblWTSum += intWaitingTime[intM];
				dblTATSum += intTurnAroundTime[intM];
			}
			intCounter3++;
		}while(intNextVal < intLimit);
		
		dblWaitingTimeAve = dblWTSum / intJobs;
		dblTurnAroundTimeAve = dblTATSum / intJobs;
		//SJF
		
		
		lblWTAve = new JLabel("Waiting Time Average: " + dblWaitingTimeAve);
		lblTATAve = new JLabel("Turn Around Time Average: " + dblTurnAroundTimeAve);
		
		int xDim = 10;
		
		// TOP PART
		for(int intCounter = 0; intCounter < arrResultsTop.length; intCounter++){
			lblTop[intCounter] = new JLabel(arrResultsTop[intCounter]);
			lblTop[intCounter].setFont(new Font("Courier New",Font.PLAIN,14));
			lblTop[intCounter].setBounds(xDim,10,200,20);
			pnlContent.add(lblTop[intCounter]);
			if(intCounter == 0){
				xDim += 60;
			} else if(intCounter == 1) {
				xDim += 120;
			} else if(intCounter == 2){
				xDim += 100;
			} else if(intCounter == 3){
				xDim += 150;
			} else if(intCounter == 4){
				xDim += 120;
			}
		}
		
		separatorTop.setBounds(10,40,630,20);
		pnlContent.add(separatorTop);
		
		xDim = 10;
		int yDim = 50;
		
		// DATA
		for(int intCounter = 0; intCounter < intJobs; intCounter++){
			for(int intCounter2 = 0; intCounter2 < 1; intCounter2++){
				lblData[intCounter][0] = new JLabel("Job " + strLetters[intCounter]);
				lblData[intCounter][1] = new JLabel("" + intArrivalTime[intCounter]);
				lblData[intCounter][2] = new JLabel("" + intBurstTime[intCounter]);
				lblData[intCounter][3] = new JLabel("" + intTurnAroundTime[intCounter]);
				lblData[intCounter][4] = new JLabel("" + intWaitingTime[intCounter]);
				lblData[intCounter][5] = new JLabel("" + intFinalTime[intCounter]);
				
				lblData[intCounter][0].setFont(new Font("Courier New",Font.PLAIN,14));
				lblData[intCounter][1].setFont(new Font("Courier New",Font.PLAIN,14));
				lblData[intCounter][2].setFont(new Font("Courier New",Font.PLAIN,14));
				lblData[intCounter][3].setFont(new Font("Courier New",Font.PLAIN,14));
				lblData[intCounter][4].setFont(new Font("Courier New",Font.PLAIN,14));
				lblData[intCounter][5].setFont(new Font("Courier New",Font.PLAIN,14));
				
				lblData[intCounter][0].setBounds(xDim,yDim,200,20);
				xDim += 60;
				lblData[intCounter][1].setBounds(xDim,yDim,250,20);
				xDim += 120;
				lblData[intCounter][2].setBounds(xDim,yDim,340,20);
				xDim += 100;
				lblData[intCounter][3].setBounds(xDim,yDim,250,20);
				xDim += 150;
				lblData[intCounter][4].setBounds(xDim,yDim,250,20);
				xDim += 120;
				lblData[intCounter][5].setBounds(xDim ,yDim,250,20);
				
				pnlContent.add(lblData[intCounter][0]);
				pnlContent.add(lblData[intCounter][1]);
				pnlContent.add(lblData[intCounter][2]);
				pnlContent.add(lblData[intCounter][3]);
				pnlContent.add(lblData[intCounter][4]);
				pnlContent.add(lblData[intCounter][5]);
			}
			xDim = 10;
			yDim += 20;
		}
		
		xDim = 60;
		
		// SEPARATOR
		for(int intCounter = 0; intCounter < separatorData.length; intCounter++){
			separatorData[intCounter] = new JSeparator();
			separatorData[intCounter].setOrientation(SwingConstants.VERTICAL);
			separatorData[intCounter].setBounds(xDim,50,20,yDim - 50);
			pnlContent.add(separatorData[intCounter]);
			if(intCounter == 0){
				xDim += 120;
			} else if(intCounter == 1) {
				xDim += 100;
			} else if(intCounter == 2){
				xDim += 150;
			} else if(intCounter == 3){
				xDim += 120;
			}
		}
		
		lblWTAve.setFont(new Font("Courier New",Font.PLAIN,14));
		lblTATAve.setFont(new Font("Courier New",Font.PLAIN,14));
		lblWTAve.setBounds(10,yDim + 40,400,20);
		lblTATAve.setBounds(10,yDim + 20,400,20);
		pnlContent.add(lblWTAve);
		pnlContent.add(lblTATAve);

		btnGantt.setFont(new Font("Courier New",Font.PLAIN,14));
		btnGantt.setBounds(10,yDim + 70,200,20);
		btnGantt.addActionListener(this);
		pnlContent.add(btnGantt);
		
		btnBackTo.setFont(new Font("Courier New",Font.PLAIN,14));
		btnBackTo.setBounds(220,yDim + 70,100,20);
		btnBackTo.addActionListener(this);
		pnlContent.add(btnBackTo);

		resultsFrame.setSize(660,yDim + 140);
		resultsFrame.setResizable(false);
		resultsFrame.setVisible(true);
		resultsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		resultsFrame.setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource() == btnGantt){
			
			resultsFrame.setVisible(false);
			
//			String strJobs = "|    ";
			String strTime = "0---------";
			
			for(int intCounter = 0; intCounter < intJobs; intCounter++){
				strTime += Integer.valueOf(intFinalTimeCopy[intCounter]);
				if(intCounter != intJobs - 1){
					if(intFinalTime[intCounter] > 9){
						strTime.substring(0,strTime.length() - 1);
						strTime += "---------";
					} else {
						strTime += "----------";
					}
				}
			}
	
			JPanel pnlGantt = new JPanel();
			ganttFrame.setContentPane(pnlGantt);
			ganttFrame.setLayout(null);
			
			lblGantt = new JLabel(strJobs);
			lblGantt.setFont(new Font("Courier New",Font.PLAIN,14));
			lblGantt.setBounds(10,10,800,20);
			pnlGantt.add(lblGantt);
			
			lblTime = new JLabel(strTime);
			lblTime.setFont(new Font("Courier New",Font.PLAIN,14));
			lblTime.setBounds(10,30,800,20);
			pnlGantt.add(lblTime);
			
			btnBack.setFont(new Font("Courier New",Font.PLAIN,14));
			btnBack.setBounds(10,60,100,20);
			btnBack.addActionListener(this);
			pnlGantt.add(btnBack);
			
			ganttFrame.setSize(1000,130);
			ganttFrame.setResizable(false);
			ganttFrame.setVisible(true);
			ganttFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			ganttFrame.setLocationRelativeTo(null);
		}
		if(arg0.getSource() == btnBack){
			ganttFrame.setVisible(false);
			resultsFrame.setVisible(true);
		}
		if(arg0.getSource() == btnBackTo){
			resultsFrame.setVisible(false);
			Main main = new Main(intJobs);
		}
		
	}

}
