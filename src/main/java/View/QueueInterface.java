package View;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class QueueInterface extends JFrame {
	private JLabel[] qLabels;
	private JTextField[] qTextFields;
	private JLabel[] busyLabels;
	private JLabel currentTime;
	private JTextArea logger = new JTextArea();
	private JScrollPane loggerScroll = new JScrollPane(logger);

	public QueueInterface(int n) {
		this.setSize(600,1000);
		
		JPanel content = new JPanel();
		content.setLayout(new GridLayout(n + 2, 0));
		currentTime = new JLabel("Current time: ");
		content.add(currentTime);

		qLabels = new JLabel[n];
		qTextFields = new JTextField[n];
		busyLabels = new JLabel[n];
		for (int i = 0; i < n; i++) {
			JPanel cont = new JPanel();
			cont.setLayout(new FlowLayout());
			qLabels[i] = new JLabel(new String("Q"+(i+1)));
			qTextFields[i]=new JTextField(40);
			busyLabels[i]=new JLabel(new String("Busy for:"));
			
			cont.add(qLabels[i]);
			cont.add(qTextFields[i]);
			cont.add(busyLabels[i]);
			
			content.add(cont);
		}
		loggerScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		content.add(loggerScroll);
		this.setContentPane(content);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public JTextArea getLogger() {
		return logger;
	}

	public void setLogger(JTextArea logger) {
		this.logger = logger;
	}

	public JLabel getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(JLabel currentTime) {
		this.currentTime = currentTime;
	}

	public static void main(String[] args) {
		QueueInterface view = new QueueInterface(5);
	}

	public JLabel[] getqLabels() {
		return qLabels;
	}

	public void setqLabels(JLabel[] qLabels) {
		this.qLabels = qLabels;
	}

	public JTextField[] getqTextFields() {
		return qTextFields;
	}

	public void setqTextFields(JTextField[] qTextFields) {
		this.qTextFields = qTextFields;
	}

	public JLabel[] getBusyLabels() {
		return busyLabels;
	}

	public void setBusyLabels(JLabel[] busyLabels) {
		this.busyLabels = busyLabels;
	}
	
}
