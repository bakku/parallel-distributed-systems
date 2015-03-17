package rmi.example.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.ZonedDateTime;

public interface RemoteUtility extends Remote {
	// echoes a string message back to the client
	String echo(String msg) throws RemoteException;
	
	// returns the current time and date to the client
	ZonedDateTime getServerTime() throws RemoteException;
	
	// adds two integers and returns the result to the client
	int add(int n1, int n2) throws RemoteException;
}
