package rmi.factorial.server;

import java.rmi.Remote;

public interface RemoteUtility extends Remote {
	// calculates the factorial of n
	long factorial(long n);
}
