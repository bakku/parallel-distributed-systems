package rmi.factorial.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteUtility extends Remote {
	// calculates the factorial of n
	long factorial(long n) throws RemoteException;
}
