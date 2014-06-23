package com.nodejs.thrift.java;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class UserServiceCliemt {

	public static final String SERVER_IP = "localhost";
	public static final int SERVER_PORT = 3000;
	public static final int TIMEOUT = 30000;

	/**
	 *
	 * @param userName
	 */
	public void startClient(String userName) {
		TTransport transport = null;
		try {
			transport = new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT);
			// 协议要和服务端一致
			TProtocol protocol = new TBinaryProtocol(transport);
			UserService.Client client = new UserService.Client(
					protocol);
			transport.open();
			client.add(new User("1", "xukai", true, (short) 1));
		    User user = client.get("1");
			System.out.println("Thrify client result =: " + user.getUname());
		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		} finally {
			if (null != transport) {
				transport.close();
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UserServiceCliemt client = new UserServiceCliemt();
		client.startClient("Michael");

	}

}
