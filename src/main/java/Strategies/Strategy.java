package Strategies;
import java.util.ArrayList;

import Simulation.Client;
import Simulation.Queue;

public interface Strategy {
	public void addClient(ArrayList<Queue> servers, Client customer);

}
