package Strategies;
import java.util.ArrayList;

import Simulation.Client;
import Simulation.Queue;

public class ConcreteStrategyQueue implements Strategy {

	public void addClient(ArrayList<Queue> servers, Client customer) {
		// TODO Auto-generated method stub
		if(servers.size() < 1) {
			return ;
		}
		int minQueueSize = servers.get(0).getTasks().size();
		Queue whichQueue = servers.get(0);
		for(Queue q : servers){
			if(q.getTasks().size() < minQueueSize){
				minQueueSize = q.getTasks().size();
				whichQueue = q;
			}
		}
		int waiting = whichQueue.getWaitingPeriod().intValue();
		customer.calculateFinishTime(waiting);
		whichQueue.addClient(customer);
		
	}

}
