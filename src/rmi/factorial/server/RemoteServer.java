package rmi.factorial.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RemoteServer {
	public static void main(String[] args) {
		SecurityManager secManager = System.getSecurityManager();
		
		if (secManager == null) {
			System.setSecurityManager(new SecurityManager());
		}
		
		try {
			RemoteUtilityImpl remoteUtility = new RemoteUtilityImpl();
			
			// locate the registry
			Registry registry = LocateRegistry.getRegistry("localhost", 1099);
			
			// bind the exported remote reference in the registry
			String name = "MyRemoteUtility";
			registry.rebind(name, remoteUtility);
			
			System.out.println("remote server is ready...");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
