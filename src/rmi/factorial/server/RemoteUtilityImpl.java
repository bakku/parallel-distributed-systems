package rmi.factorial.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.ZonedDateTime;

public class RemoteUtilityImpl extends UnicastRemoteObject implements RemoteUtility {

	protected RemoteUtilityImpl() throws RemoteException {
	}

	@Override
	public String echo(String msg) {
		return msg;
	}

	@Override
	public ZonedDateTime getServerTime() {
		return ZonedDateTime.now();
	}

	@Override
	public int add(int n1, int n2) {
		return n1 + n2;
	}
}
