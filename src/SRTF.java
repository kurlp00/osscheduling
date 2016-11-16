
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class SRTF implements ActionListener{

	String[] arrLetters = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O"};
	String[] arrResultsTop = {"Jobs","Arrival Time","Burst Time","Turn Around Time","Waiting Time","Final Time"};
	
	private JFrame resultsFrame = new JFrame("Shortest Remaining Time First Results");
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
	private JFrame ganttFrame = new JFrame("Shortest Remaining Time First Gantt Chart");
	
	int[] intFinalTime, intWaitingTime, intTurnAroundTime, intBurstTimeCopy;
	int intJobs;
	double dblWTSum = 0, dblTATSum = 0;
	double dblWaitingTimeAve = 0, dblTurnAroundTimeAve = 0;
	String[] strLetters;
	
	String strJobs = "| ";
	
	public void SRTFCompute(String[] strLetters, int[] intArrivalTime, int intBurstTime[], int intPriority[], int intJobs){
		
		resultsFrame.setContentPane(pnlContent);
		resultsFrame.setLayout(null);
		
		lblTop = new JLabel[arrResultsTop.length];
		lblData = new JLabel[intJobs][arrResultsTop.length];
		separatorData = new JSeparator[arrResultsTop.length - 1];
		intFinalTime = new int[intJobs];
		intWaitingTime = new int[intJobs];
		intTurnAroundTime = new int[intJobs];
		intBurstTimeCopy = new int[intJobs];
		this.strLetters = strLetters;
		this.intJobs = intJobs;
		
		String strSeq = "";
		
		//SRTF
		int intTotalTime = 0;
		
		for(int intCounter = 0; intCounter < intJobs; intCounter++){
			intTotalTime += intBurstTime[intCounter];
			intBurstTimeCopy[intCounter] = intBurstTime[intCounter];
		}
		
		int intTimeChart[] = new int[intTotalTime];
		
		for(int intCounter = 0; intCounter < intTotalTime; intCounter++){
			int intSelectedProcess = 0;
			int intMinimum = 99999;
			
			for(int intCounter2 = 0; intCounter2 < intJobs; intCounter2++){
				if(intArrivalTime[intCounter2] <= intCounter){
					if(intBurstTime[intCounter2] < intMinimum && intBurstTime[intCounter2] != 0){
						intMinimum = intBurstTime[intCounter2];
						intSelectedProcess = intCounter2;
					}
				}
			}
			
			intTimeChart[intCounter] = intSelectedProcess;
			intBurstTime[intSelectedProcess]--;

			for(int intCounter2 = 0; intCounter2 < intJobs; intCounter2++){
				if(intArrivalTime[intCounter2] <= intCounter){
					if(intBurstTime[intCounter2] != 0){
						intTurnAroundTime[intCounter2]++;
						if(intCounter2 != intSelectedProcess)
							intWaitingTime[intCounter2]++;
					} else if(intCounter2 == intSelectedProcess)
						intTurnAroundTime[intCounter2]++;
				}
			}
			
			if(intCounter != 0){
				if(intSelectedProcess != intTimeChart[intCounter - 1]){
					intFinalTime[intSelectedProcess] = intCounter + 1;
				}else{
					intFinalTime[intSelectedProcess] = intCounter + 1;
				}
			}
			strSeq += strLetters[intSelectedProcess];
		}
	
		for(int intCounter = 0; intCounter < intJobs; intCounter++){
			dblWTSum += intWaitingTime[intCounter];
			dblTATSum += intTurnAroundTime[intCounter];
		}
		
		dblWaitingTimeAve = dblWTSum / intJobs;
		dblTurnAroundTimeAve = dblTATSum / intJobs;
		
		strJobs += strSeq.charAt(0) + " | ";
		
		for(int intCounter = 1; intCounter < strSeq.length(); intCounter++){
	    	if(strSeq.charAt(intCounter - 1) != strSeq.charAt(intCounter)){
	    		strJobs += strSeq.charAt(intCounter) + " | ";
	    	}
		}
		//SRTF
		
		
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
				lblData[intCounter][2] = new JLabel("" + intBurstTimeCopy[intCounter]);
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
			
//			String strTime = "0---------";
			
//			for(int intCounter = 0; intCounter < intJobs; intCounter++){
//				strTime += Integer.valueOf(intFinalTime[intCounter]);
//				if(intCounter != intJobs - 1){
//					if(intFinalTime[intCounter] > 9){
//						strTime.substring(0,strTime.length() - 1);
//						strTime += "---------";
//					} else {
//						strTime += "----------";
//					}
//				}
//			}
	
			JPanel pnlGantt = new JPanel();
			ganttFrame.setContentPane(pnlGantt);
			ganttFrame.setLayout(null);
			
			lblGantt = new JLabel(strJobs);
			lblGantt.setFont(new Font("Courier New",Font.PLAIN,14));
			lblGantt.setBounds(10,10,800,20);
			pnlGantt.add(lblGantt);
			
//			lblTime = new JLabel(strTime);
//			lblTime.setFont(new Font("Courier New",Font.PLAIN,14));
//			lblTime.setBounds(10,30,800,20);
//			pnlGantt.add(lblTime);
			
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
