package Strategies;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import Simulation.Client;
import Simulation.Queue;

public class ConcreteStrategyTime implements Strategy {

	public void addClient(ArrayList<Queue> servers, Client customer) {
		// TODO Auto-generated method stub
		if (servers.size() < 1) {
			return;
		}
		AtomicInteger minTime = servers.get(0).getWaitingPeriod();
		Queue whichQueue = servers.get(0);
		for (Queue q : servers) {
			if (q.getWaitingPeriod().intValue() < minTime.intValue()) {
				minTime = q.getWaitingPeriod();
				whichQueue = q;
			}
		}
		int waiting = whichQueue.getWaitingPeriod().intValue();
		customer.calculateFinishTime(waiting);
		whichQueue.addClient(customer);
	}
}
