package rmi.factorial.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteUtilityImpl extends UnicastRemoteObject implements RemoteUtility {

	protected RemoteUtilityImpl() throws RemoteException {
	}

	@Override
	public long factorial(long n) {
		long result = 1;
		
		for (; n > 0; n--) {
			result *= n;
		}
		
		return result;
	}
}
