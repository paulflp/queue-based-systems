package Simulation;
import java.util.ArrayList;

import Strategies.*;

public class Scheduler {
	private ArrayList<Queue> servers;
	private int maxNoQueues;
	private int maxClientsPerQueue;
	private Strategy strategy;
	
	public Scheduler(int maxNoQueues, int maxClientsPerQueue){
		this.maxNoQueues = maxNoQueues;
		this.maxClientsPerQueue = maxClientsPerQueue;
		servers = new ArrayList<Queue>(maxNoQueues);
		for(int i=0; i<maxNoQueues;i++){
			servers.add(new Queue());
		}
	}
	
	public void changeStrategy(SelectionPolicy policy){
		if(policy == SelectionPolicy.SHORTEST_QUEUE){
			strategy = new ConcreteStrategyQueue();
		}
		if(policy == SelectionPolicy.SHORTEST_TIME){
			strategy = new ConcreteStrategyTime();
		}
	}
	
	public void addQueue(Queue server) {
		servers.add(server);
	}
	
	public void dispatchTask(Client customer){
		strategy.addClient(servers, customer);
		
	}

	public ArrayList<Queue> getServers() {
		return servers;
	}

	public int getMaxNoQueues() {
		return maxNoQueues;
	}

	public int getMaxClientsPerQueue() {
		return maxClientsPerQueue;
	}

	public Strategy getStrategy() {
		return strategy;
	}
	
	public String printQueues() {
		String result = new String("");
		int count = 1;
		for(Queue q : servers) {
			result+="Coada "+count+":";
			for(Client cst : q.getTasks()) {
				result += "c["+cst.getArrivalTime()+";"+cst.getProcessingPeriod()+"] ";
			}
			result += "_Busy time : "+q.getWaitingPeriod().intValue()+"\n";
			count++;
		}
		result+="\n";
		return result;
	}
	
}
