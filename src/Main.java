
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class Main extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static String[] arrAlgo = {"First Come First Serve","Shortest Job First","Shortest Remaining Time First","Non-preemptive Priority","Preemptive Priority"};
	
	private JPanel pnlContent = new JPanel();
	private JLabel lblTop = new JLabel("CPU SCHEDULING");
	private JSeparator separatorTop = new JSeparator();
	private JTextField txtJobs = new JTextField(12);
	private JLabel lblInputJobs = new JLabel("Input number of jobs: ");
	private JButton btnSubmit = new JButton("Submit");
	private JLabel[] lblLabel = new JLabel[arrLabel.length];
	static JTextField[] txtArrivalTime, txtBurstTime, txtPriority;
	private JLabel[] lblJobs;
	private JSeparator separatorMiddle = new JSeparator();
	private JSeparator separatorVerticalJobs = new JSeparator();
	private JSeparator separatorVerticalAT = new JSeparator();
	private JSeparator separatorVerticalBT = new JSeparator();
	private JComboBox cboAlgo = new JComboBox(arrAlgo);
	private JButton btnCompute = new JButton("Compute");
	private JButton btnReset = new JButton("Reset");
	
	private int intJobs;

	static String[] arrLetters = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O"};
	static String[] arrLabel = {"Jobs","Arrival Time","Burst Time","Priority"};
	
	public Main(){
		super("CPU SCHEDULING");
		setContentPane(pnlContent);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(400,100);
		
		lblTop.setFont(new Font("Courier New",Font.BOLD,20));
		lblTop.setBounds(10,5,200,20);
		pnlContent.add(lblTop);
		
		separatorTop.setBounds(10,30,370,20);
		pnlContent.add(separatorTop);
		
		lblInputJobs.setFont(new Font("Courier New",Font.PLAIN,14));
		lblInputJobs.setBounds(10,38,200,20);
		pnlContent.add(lblInputJobs);
		
		txtJobs.setBounds(185,38,70,20);
		pnlContent.add(txtJobs);
		
		btnSubmit.setFont(new Font("Courier New",Font.PLAIN,14));
		btnSubmit.setBounds(260,38,90,20);
		btnSubmit.addActionListener(this);
		pnlContent.add(btnSubmit);
		
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	public Main(int intJobs){
		
		super("CPU SCHEDULING");
		setContentPane(pnlContent);
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.intJobs = intJobs;
		
		lblTop.setFont(new Font("Courier New",Font.BOLD,20));
		lblTop.setBounds(10,5,200,20);
		pnlContent.add(lblTop);
		
		separatorTop.setBounds(10,30,370,20);
		pnlContent.add(separatorTop);
		
		lblInputJobs.setFont(new Font("Courier New",Font.PLAIN,14));
		lblInputJobs.setBounds(10,40,200,20);
		pnlContent.add(lblInputJobs);
		
		txtJobs.setBounds(185,40,70,20);
		pnlContent.add(txtJobs);
		
		btnReset.setFont(new Font("Courier New",Font.PLAIN,14));
		btnReset.setBounds(260,40,90,20);
		btnReset.addActionListener(this);
		pnlContent.add(btnReset);
		
		separatorMiddle.setBounds(10,70,370,20);
		pnlContent.add(separatorMiddle);
		
		txtArrivalTime = new JTextField[intJobs];
		txtBurstTime = new JTextField[intJobs];
		txtPriority = new JTextField[intJobs];
		lblJobs = new JLabel[intJobs];
		
		txtJobs.setText(intJobs + "");
		txtJobs.setEditable(false);
		
		int xDim = 10;
		
		// LABELS ... JOB, ARRIVAL TIME, BURST TIME, PRIORITY
		for(int intCounter = 0; intCounter < arrLabel.length; intCounter++){
			lblLabel[intCounter] = new JLabel(arrLabel[intCounter]);
			lblLabel[intCounter].setFont(new Font("Courier New",Font.PLAIN,13));
			lblLabel[intCounter].setBounds(xDim,75,100,20);
			pnlContent.add(lblLabel[intCounter]);
			if(intCounter == 0){
				xDim += 80;
			} else if(intCounter == 1) {
				xDim += 120;
			} else if(intCounter == 2){
				xDim += 100;
			}
		}
		
		int yDim = 100;
		
		// JOB + "LETTER"
		for(int intCounter = 0; intCounter < intJobs; intCounter++){
			lblJobs[intCounter] = new JLabel("Job " + arrLetters[intCounter]);
			lblJobs[intCounter].setFont(new Font("Courier New",Font.PLAIN,16));
			lblJobs[intCounter].setBounds(10,yDim,100,20);
			yDim += 20;
			pnlContent.add(lblJobs[intCounter]);
		}
		
		yDim = 100;
		
		// TEXTFIELD ARRIVAL TIME
		for(int intCounter = 0; intCounter < intJobs; intCounter++){
			txtArrivalTime[intCounter] = new JTextField(12);
			txtArrivalTime[intCounter].setBounds(90,yDim,100,20);
			yDim += 20;
			pnlContent.add(txtArrivalTime[intCounter]);
		}
		
		yDim = 100;
		
		// BURST TIME TEXTFIELD
		for(int intCounter = 0; intCounter < intJobs; intCounter++){
			txtBurstTime[intCounter] = new JTextField(12);
			txtBurstTime[intCounter].setBounds(210,yDim,80,20);
			yDim += 20;
			pnlContent.add(txtBurstTime[intCounter]);
		}
		
		yDim = 100;
		
		//PRIORITY
		for(int intCounter = 0; intCounter < intJobs; intCounter++){
			txtPriority[intCounter] = new JTextField(12);
			txtPriority[intCounter].setBounds(310,yDim,70,20);
			yDim += 20;
			pnlContent.add(txtPriority[intCounter]);
		}
		
		separatorVerticalJobs.setOrientation(SwingConstants.VERTICAL);
		separatorVerticalJobs.setBounds(75,75,20,yDim - 75);
		pnlContent.add(separatorVerticalJobs);
		
		separatorVerticalAT.setOrientation(SwingConstants.VERTICAL);
		separatorVerticalAT.setBounds(200,75,20,yDim - 75);
		pnlContent.add(separatorVerticalAT);
		
		separatorVerticalBT.setOrientation(SwingConstants.VERTICAL);
		separatorVerticalBT.setBounds(300,75,20,yDim - 75);
		pnlContent.add(separatorVerticalBT);
		
		cboAlgo.setFont(new Font("Courier New",Font.PLAIN,14));
		cboAlgo.setBounds(10,yDim + 20,250,20);
		pnlContent.add(cboAlgo);
		
		btnCompute.setFont(new Font("Courier New",Font.PLAIN,14));
		btnCompute.setBounds(280,yDim + 20,100,20);
		btnCompute.addActionListener(this);
		pnlContent.add(btnCompute);
		
		setSize(400,yDim + 100);
		setVisible(true);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		Main main = new Main();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == btnSubmit){
			try{
				intJobs = Integer.parseInt(txtJobs.getText());
				setVisible(false);
				Main main = new Main(intJobs);
			} catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null,"Invalid input");
			}
		}
		if(arg0.getSource() == btnReset){
			setVisible(false);
			Main main = new Main();
		}
		if(arg0.getSource() == btnCompute){
			String strChoice = (String) cboAlgo.getSelectedItem();
			
			int[] intArrivalTime = new int[100], intBurstTime = new int[100], intPriority = new int[100];
			String[] strLetters = new String[100];
			
			int intCond = 0;
			
			for(int intCounter = 0; intCounter < intJobs; intCounter++){
				try{
					intArrivalTime[intCounter] = Integer.parseInt(txtArrivalTime[intCounter].getText());
					intBurstTime[intCounter] = Integer.parseInt(txtBurstTime[intCounter].getText());
					intPriority[intCounter] = Integer.parseInt(txtPriority[intCounter].getText());
					intCond = 1;
				}catch(NumberFormatException e){
					intCond = 0;
					break;
				}
			}	
			
			Sort srt = new Sort();
			srt.sort(arrLetters, intArrivalTime, intBurstTime, intPriority, intJobs);
			
			intArrivalTime = srt.getArrivalTime();
			intBurstTime = srt.getBurstTime();
			strLetters = srt.getLetters();
			
			if(intCond == 1){
				setVisible(false);
				if(strChoice.equalsIgnoreCase(arrAlgo[0])){		
					FCFS fcfs = new FCFS();
					fcfs.FCFSCompute(strLetters, intArrivalTime, intBurstTime, intPriority, intJobs);
				} else if(strChoice.equalsIgnoreCase(arrAlgo[1])){
					SJF sjf = new SJF();
					sjf.SJFCompute(strLetters, intArrivalTime, intBurstTime, intPriority, intJobs);
				} else if(strChoice.equalsIgnoreCase(arrAlgo[2])){
					SRTF srtf = new SRTF();
					srtf.SRTFCompute(strLetters, intArrivalTime, intBurstTime, intPriority, intJobs);
				} else if(strChoice.equalsIgnoreCase(arrAlgo[3])){
					NPP npp = new NPP();
					npp.NPPCompute(strLetters, intArrivalTime, intBurstTime, intPriority, intJobs);
				} else if(strChoice.equalsIgnoreCase(arrAlgo[4])){
					PP pp = new PP();
					pp.PPCompute(strLetters, intArrivalTime, intBurstTime, intPriority, intJobs);
				}
			} else {
				JOptionPane.showMessageDialog(null,"Error on inputs.");
			}
		}
	}

}
