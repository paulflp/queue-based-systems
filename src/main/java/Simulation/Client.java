package Simulation;

public class Client implements Comparable<Client> {
	private int arrivalTime;
	private int finishTime;
	private int processingPeriod;
	private int index = 0;

	public Client(int arrivalTime, int processingPeriod) {
		this.arrivalTime = arrivalTime;
		this.processingPeriod = processingPeriod;
		this.finishTime = 0;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public int getProcessingPeriod() {
		return processingPeriod;
	}

	public int getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;
	}

	public void calculateFinishTime(int waitingPeriodOnChosenServer) {
		int finishTime = this.getProcessingPeriod() + this.getArrivalTime() + waitingPeriodOnChosenServer;
		this.setFinishTime(finishTime);
	}

	public int compareTo(Client arg0) {
		// TODO Auto-generated method stub
		if (this.getArrivalTime() == arg0.getArrivalTime())
			return 0;
		if (this.getArrivalTime() < arg0.getArrivalTime())
			return 1;
		if (this.getArrivalTime() > arg0.getArrivalTime())
			return -1;
		return 0;
	}
	
	public String toString() {
		return new String("Clientul are timpul de sosire " + arrivalTime + " si timpul de procesare " + processingPeriod);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
