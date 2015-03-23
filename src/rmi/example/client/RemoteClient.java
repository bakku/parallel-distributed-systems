package rmi.example.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.ZonedDateTime;

import rmi.example.server.RemoteUtility;

public class RemoteClient {
	public static void main(String[] args) {
		SecurityManager secManager = System.getSecurityManager();
		
		if (secManager == null) {
			System.setSecurityManager(new SecurityManager());
		}
		
		try {
			// locate the registry
			Registry registry = LocateRegistry.getRegistry("localhost", 1099);
					
			String name = "MyRemoteUtility";
			RemoteUtility remoteUtilStub = (RemoteUtility) registry.lookup(name);
			
			// echo a message from the server
			String msg = "Hello";
			String reply = remoteUtilStub.echo(msg);
			System.out.println("echo message: " + msg + ", echo reply: " + reply);
			
			// get the server time and date
			ZonedDateTime serverTime = remoteUtilStub.getServerTime();
			System.out.println("server time: " + serverTime);
			
			// add two integers
			int n1 = 101;
			int n2 = 207;
			int sum = remoteUtilStub.add(n1, n2);
			System.out.println("n1 + n2 = " + sum);
			
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
}
