package Simulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JOptionPane;

import Strategies.SelectionPolicy;
import View.InitialData;
import View.QueueInterface;

public class SimulationManager implements Runnable {
	public int timeLimit = 15;
	public int maxProcessingTime = 3;
	public int minProcessingTime = 1;
	public int numberOfServers = 3;
	public int numberOfClients = 20;
	public int minArrivalTime = 0;
	public int maxArrivalTime = 2;
	public int option = 2;
	public SelectionPolicy selPolicy = SelectionPolicy.SHORTEST_TIME;

	private Scheduler scheduler;
	private ArrayList<Client> generatedTasks;
	private QueueInterface frame;
	private InitialData initialview;

	public SimulationManager(InitialData data) {
		this.setInitialview(data);
		while (initialview.getCnt() == 0) {
			// System.out.println("waiting for data");
			System.out.println(initialview.getCnt());
		}
		data.printValues();
		// initialview.printValues();
		option = initialview.getOptionSelected();
		timeLimit = initialview.getSimInterval();
		numberOfServers = initialview.getNrCozi();
		numberOfClients = initialview.getNrClienti();
		minProcessingTime = initialview.getMinServ();
		maxProcessingTime = initialview.getMaxServ();
		minArrivalTime = initialview.getMinArriv();
		maxArrivalTime = initialview.getMaxArriv();
		scheduler = new Scheduler(numberOfServers, 100);
		scheduler.changeStrategy(selPolicy);
		frame = new QueueInterface(numberOfServers);
		generateNRandomTasks(numberOfClients);

	}

	private void generateNRandomTasks(int n) {
		generatedTasks = new ArrayList<Client>(n);
		int j = 0;
		int randomProcessingTime = -1;
		int randomArrivalTime = -1;
		if (option == 1) {
			System.out.println("Se genereaza " + n + " clienti");
			for (int i = 0; i < n; i++) {
				boolean ok = true;
				while (ok) {
					randomProcessingTime = (int) (Math.random() * 10);
					if (j == 0)
						randomArrivalTime = 0;
					else
						randomArrivalTime = (int) (Math.random() * 10);

					System.out.println("proces aleatoriu : " + randomProcessingTime);
					System.out.println("timp aleatoriu: " + randomArrivalTime);

					if (randomProcessingTime < minProcessingTime || maxProcessingTime < randomProcessingTime
							|| randomArrivalTime + randomProcessingTime >= timeLimit)
						ok = true;
					else {
						ok = false;
					}
				}
				Client newClient = new Client(randomArrivalTime, randomProcessingTime);
				generatedTasks.add(newClient);
				System.out.println("S-au generat " + (j + 1) + " clienti");
				j++;
			}
		} else if (option == 2) {
			int prevArrivalTime = 0;
			boolean ok = true;
			int nr = (int) (Math.random() * 100);
			if (nr > n || nr == 0)
				nr = n;
			System.out.println("se vor genera maxim " + nr + " numere");
			while (nr > 0) {
				ok = true;
				while (ok) {
					System.out.println("prev: " + prevArrivalTime);
					if (j == 0)
						randomArrivalTime = 0;
					else
						randomArrivalTime = (int) (prevArrivalTime + Math.random() * 10);

					randomProcessingTime = (int) (Math.random() * 10);

					System.out.println("proces aleatoriu : " + randomProcessingTime);
					System.out.println("timp aleatoriu: " + randomArrivalTime);

					if (randomProcessingTime < minProcessingTime || maxProcessingTime < randomProcessingTime
							|| randomArrivalTime - prevArrivalTime < minArrivalTime
							|| randomArrivalTime - prevArrivalTime > maxArrivalTime) {
						ok = true;
						if (j == 0) {
							if (randomProcessingTime < minProcessingTime || maxProcessingTime < randomProcessingTime)
								ok = true;
							else {
								ok = false;
							}
						}
					} else {
						ok = false;
						prevArrivalTime = randomArrivalTime;
					}
					System.out.println(ok);

				}
				System.out.println(randomArrivalTime + " si " + randomProcessingTime);
				Client newClient = new Client(randomArrivalTime, randomProcessingTime);
				generatedTasks.add(newClient);
				System.out.println("S-au generat " + (j + 1) + " clienti");
				j++;
				nr--;
			}
		}
		Collections.sort(generatedTasks);

	}

	@SuppressWarnings("deprecation")
	public void run() {
		int nrClient = 0;
		// TODO Auto-generated method stub
		int currentTime = 0;
		int avgWaitingTime = 0;
		int avgProccesingTime = 0;
		int peakHour = -1;
		int maxPeakHour = Integer.MIN_VALUE;
		while (currentTime < timeLimit) {
			for (Client customer : generatedTasks) {
				if (customer.getArrivalTime() == currentTime) {
					scheduler.dispatchTask(customer);
					avgProccesingTime += customer.getProcessingPeriod();
					customer.setIndex(nrClient + 1);
					int poz = -1;
					for (Queue a : scheduler.getServers()) {
						if (a.getTasks().contains(customer)) {
							poz = scheduler.getServers().indexOf(a);
							avgWaitingTime += a.getWaitingPeriod().intValue() - customer.getProcessingPeriod();
							// customer.calculateFinishTime(a.getWaitingPeriod().intValue());
						}
					}
					System.out.println("#" + nrClient + ":" + customer.toString() + " repartizat la coada " + (poz + 1)
							+ "termina la timpul" + customer.getFinishTime());
					frame.getLogger()
							.append("C" + customer.getIndex() + " cu timpul de sosire " + customer.getArrivalTime()
									+ " si timpul de procesare " + customer.getProcessingPeriod()
									+ " a fost repartizat la coada " + (poz + 1) + "\n");
					nrClient++;
				}
				if (customer.getFinishTime() == currentTime) {
					if (currentTime != 0)
						frame.getLogger().append(
								"C" + customer.getIndex() + " a parasit coada la momentul t=" + currentTime + "\n");
				}
			}

			System.out.println("Current time:" + currentTime);
			frame.getLogger().append("Current time:" + currentTime+"\n");
			frame.getCurrentTime().setText("Current Time:" + currentTime);
			int cnt = 0;
			int peakHr = 0;
			for (Queue q : scheduler.getServers()) {
				String s = new String("");
				for (Client clt : q.getTasks()) {
					s += "c" + clt.getIndex();
				}
				peakHr += q.getTasks().size();
				frame.getqTextFields()[cnt].setText(s);
				frame.getBusyLabels()[cnt].setText("Busy for " + q.getWaitingPeriod());
				cnt++;
			}
			if (peakHr > maxPeakHour) {
				maxPeakHour = peakHr;
				peakHour = currentTime;
			}
			System.out.println(scheduler.printQueues());

			currentTime++;
			for (Queue coada : scheduler.getServers()) {
				if (coada.getWaitingPeriod().intValue() > 0) {
					coada.setWaitingPeriod(new AtomicInteger(coada.getWaitingPeriod().decrementAndGet()));
				}
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (Queue q : scheduler.getServers()) {
			q.getThreadOnThisQueue().stop();
		}
		JOptionPane.showMessageDialog(null,
				"Average Waiting time: " + (avgWaitingTime / (float) nrClient) + "\n" + "Avergae Service time: "
						+ (avgProccesingTime / (float) nrClient) + "\n" + "Peak Hour: " + peakHour + " with "
						+ maxPeakHour + " clients\n");
	}

	public static void main(String[] args) {
		InitialData view = new InitialData();
		SimulationManager sim = new SimulationManager(view);
		Thread t = new Thread(sim);
		t.start();
	}

	public void setInitialview(InitialData initialview) {
		this.initialview = initialview;
	}
}
