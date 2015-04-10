package rmi.factorial.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmi.factorial.server.RemoteUtility;

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
			
			long start = System.currentTimeMillis();
			long fac = remoteUtilStub.factorial(10);
			long end = System.currentTimeMillis();
			
			System.out.println("10! = " + fac);
			System.out.println("Time spend: " + (end - start) + " ms");
			
			start = System.currentTimeMillis();
			fac = remoteUtilStub.factorial(15);
			end = System.currentTimeMillis();
			
			System.out.println("15! = " + fac);
			System.out.println("Time spend: " + (end - start) + " ms");
			
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
}
