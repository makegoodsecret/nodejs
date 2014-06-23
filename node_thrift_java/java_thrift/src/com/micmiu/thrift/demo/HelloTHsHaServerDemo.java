package com.micmiu.thrift.demo;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;

/**
 * 半同步半异步的服务端模型，需要指定为： TFramedTransport 数据传输的方式。
 * @author Administrator
 *客户端代码和上面 4 中的类似，只要注意传输协议一致以及指定传输方式为TFramedTransport。
 */
public class HelloTHsHaServerDemo {
	public static final int SERVER_PORT = 8090;

	public void startServer() {
		try {
			System.out.println("HelloWorld THsHaServer start ....");

			TProcessor tprocessor = new HelloWorldService.Processor<HelloWorldService.Iface>(
					new HelloWorldImpl());

			TNonblockingServerSocket tnbSocketTransport = new TNonblockingServerSocket(
					SERVER_PORT);
			THsHaServer.Args thhsArgs = new THsHaServer.Args(tnbSocketTransport);
			thhsArgs.processor(tprocessor);
			thhsArgs.transportFactory(new TFramedTransport.Factory());
			thhsArgs.protocolFactory(new TBinaryProtocol.Factory());

			//半同步半异步的服务模型
			TServer server = new THsHaServer(thhsArgs);
			server.serve();
  
		} catch (Exception e) {
			System.out.println("Server start error!!!");
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HelloServerDemo server = new HelloServerDemo();
		server.startServer();
	}

}
