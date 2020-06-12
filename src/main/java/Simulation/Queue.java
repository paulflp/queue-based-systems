package Simulation;
import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Queue implements Runnable {
	private BlockingQueue<Client> tasks;
	private AtomicInteger waitingPeriod;
	public Thread threadOnThisQueue;

	public Queue() {
		tasks = new ArrayBlockingQueue<Client>(500);
		waitingPeriod = new AtomicInteger(0);
		threadOnThisQueue = new Thread(this);
		threadOnThisQueue.start();
	}

	public void addClient(Client newClient) {
		tasks.add(newClient);
		waitingPeriod.addAndGet(newClient.getProcessingPeriod());
	}

	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			Client nextClient = this.getTasks().peek();
			if (nextClient != null) {
				try {
					threadOnThisQueue.sleep(nextClient.getProcessingPeriod() * 1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tasks.remove();
			}
		}
	}

	public LinkedList<Client> getTasks() {
		LinkedList<Client> allTasks = new LinkedList<Client>();
		for(Client customer : tasks) {
			allTasks.add(customer);
		}
		return allTasks;
	}

	public AtomicInteger getWaitingPeriod() {
		return waitingPeriod;
	}

	public Thread getThreadOnThisQueue() {
		return threadOnThisQueue;
	}

	public void setTasks(BlockingQueue<Client> tasks) {
		this.tasks = tasks;
	}

	public void setWaitingPeriod(AtomicInteger waitingPeriod) {
		this.waitingPeriod = waitingPeriod;
	}

	public void setThreadOnThisQueue(Thread threadOnThisQueue) {
		this.threadOnThisQueue = threadOnThisQueue;
	}

}
