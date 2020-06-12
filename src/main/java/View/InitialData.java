package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class InitialData extends JFrame {
	String[] option = {"Max No. Clients", "Interval between arriving time"};
	private JTextField minArrivingTime = new JTextField(30);
	private JTextField maxArrivingTime = new JTextField(30);
	private JTextField minServiceTime = new JTextField(30);
	private JTextField maxServiceTime = new JTextField(30);
	private JTextField noOfClients = new JTextField(30);
	private JTextField noOfQueues = new JTextField(30);
	private JTextField simulationInterval = new JTextField(30);
	private JComboBox<String> optionsList = new JComboBox<String>(option);
	private JButton okBtn = new JButton("OK");

	boolean btnPressed;
	private int nrCozi;
	private int nrClienti;
	private int simInterval;
	private int minServ;
	private int maxServ;
	private int minArriv;
	private int optionSelected = 1;
	private int cnt = 0;

	public int getCnt() {
		return cnt;
	}

	public boolean isBtnPressed() {
		return btnPressed;
	}

	public void setBtnPressed(boolean btnPressed) {
		this.btnPressed = btnPressed;
	}

	public int getNrCozi() {
		return nrCozi;
	}

	public void setNrCozi(int nrCozi) {
		this.nrCozi = nrCozi;
	}

	public int getNrClienti() {
		return nrClienti;
	}

	public void setNrClienti(int nrClienti) {
		this.nrClienti = nrClienti;
	}

	public int getSimInterval() {
		return simInterval;
	}

	public void setSimInterval(int simInterval) {
		this.simInterval = simInterval;
	}

	public int getMinServ() {
		return minServ;
	}

	public void setMinServ(int minServ) {
		this.minServ = minServ;
	}

	public int getMaxServ() {
		return maxServ;
	}

	public void setMaxServ(int maxServ) {
		this.maxServ = maxServ;
	}

	public int getMinArriv() {
		return minArriv;
	}

	public void setMinArriv(int minArriv) {
		this.minArriv = minArriv;
	}

	public int getMaxArriv() {
		return maxArriv;
	}

	public void setMaxArriv(int maxArriv) {
		this.maxArriv = maxArriv;
	}

	private int maxArriv;

	public InitialData() {

		this.setSize(450, 700);
		this.setTitle("Input data");

		btnPressed = false;
		JPanel content = new JPanel();
		content.setLayout(new GridLayout(11, 0));

		JPanel arrTimeLabel = new JPanel();
		JLabel arrTime = new JLabel("Minimum and maximum interval of arriving time between customers");
		arrTimeLabel.add(arrTime);
		content.add(arrTimeLabel);

		JPanel minArrTimePanel = new JPanel();
		minArrTimePanel.setLayout(new FlowLayout());
		JLabel minArrTimeLabel = new JLabel("Minimum:");
		minArrTimePanel.add(minArrTimeLabel);
		minArrTimePanel.add(minArrivingTime);
		content.add(minArrTimePanel);

		JPanel maxArrTimePanel = new JPanel();
		maxArrTimePanel.setLayout(new FlowLayout());
		JLabel maxArrTimeLabel = new JLabel("Maximum:");
		maxArrTimePanel.add(maxArrTimeLabel);
		maxArrTimePanel.add(maxArrivingTime);
		content.add(maxArrTimePanel);

		JPanel serviceTimePanel = new JPanel();
		JLabel serviceTime = new JLabel("Minimum and maximum service time");
		serviceTimePanel.add(serviceTime);
		content.add(serviceTimePanel);

		JPanel minServiceTimePanel = new JPanel();
		minServiceTimePanel.setLayout(new FlowLayout());
		JLabel minServiceTimeLabel = new JLabel("Minimum:");
		minServiceTimePanel.add(minServiceTimeLabel);
		minServiceTimePanel.add(minServiceTime);
		content.add(minServiceTimePanel);

		JPanel maxServiceTimePanel = new JPanel();
		maxServiceTimePanel.setLayout(new FlowLayout());
		JLabel maxServiceTimeLabel = new JLabel("Maximum:");
		maxServiceTimePanel.add(maxServiceTimeLabel);
		maxServiceTimePanel.add(maxServiceTime);
		content.add(maxServiceTimePanel);

		JPanel clients = new JPanel();
		clients.setLayout(new FlowLayout());
		JLabel clientsLabel = new JLabel("Maximum number of clients:");
		clients.add(clientsLabel);
		clients.add(noOfClients);
		content.add(clients);

		JPanel queues = new JPanel();
		queues.setLayout(new FlowLayout());
		JLabel queuesLabel = new JLabel("Number of queues:");
		queues.add(queuesLabel);
		queues.add(noOfQueues);
		content.add(queues);

		JPanel symInterval = new JPanel();
		symInterval.setLayout(new FlowLayout());
		JLabel symLabel = new JLabel("Simulation Interval:");
		symInterval.add(symLabel);
		symInterval.add(simulationInterval);
		content.add(symInterval);
		
		JPanel optionPanel = new JPanel();
		optionPanel.setLayout(new FlowLayout());
		optionPanel.add(new JLabel("Choose the option:"));
		optionPanel.add(optionsList);
		
		content.add(optionPanel);
		
		content.add(okBtn);

		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnPressed = true;
				cnt = 1;
				setValues();
			}
		});
		
		optionsList.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String s = (String) optionsList.getSelectedItem();
				if(s.equals("Max No. Clients"))
					setOptionSelected(1);
				else
					setOptionSelected(2);
			}
			
		});
		this.setContentPane(content);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void setValues() {
		if (this.isBtnPressed() == true) {
			try {
				this.setNrCozi(Integer.parseInt(noOfQueues.getText()));
				this.setNrClienti(Integer.parseInt(noOfClients.getText()));
				this.setMinServ(Integer.parseInt(minServiceTime.getText()));
				this.setMaxServ(Integer.parseInt(maxServiceTime.getText()));
				this.setMinArriv(Integer.parseInt(minArrivingTime.getText()));
				this.setMaxArriv(Integer.parseInt(maxArrivingTime.getText()));
				this.setSimInterval(Integer.parseInt(simulationInterval.getText()));

				/*System.out.println("Datele introduse sunt :");
				System.out.println("Interval simulare: " + simInterval);
				System.out.println("Numar cozi: " + nrCozi);
				System.out.println("Numar clienti: " + nrClienti);
				System.out.println("Timp minim procesare: " + minServ);
				System.out.println("Timp maxim procesare: " + maxServ);
				System.out.println("Intervalul intre venirile clientilor:[" + minArriv + ";" + maxArriv + "]");*/
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Corrupted data", "Error", JOptionPane.ERROR_MESSAGE);
				System.out.println(e.getMessage());
			}

			this.setBtnPressed(false);
		}
	}


	public int getOptionSelected() {
		return optionSelected;
	}

	public void setOptionSelected(int optionSelected) {
		this.optionSelected = optionSelected;
	}
	
	public void printValues(){
		System.out.println("cozi:"+nrCozi+", clienti:"+nrClienti+", simulare: "+simInterval+" option:"+optionSelected);
	}
}
