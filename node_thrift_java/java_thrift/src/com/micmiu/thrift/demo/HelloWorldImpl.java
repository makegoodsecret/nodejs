package com.micmiu.thrift.demo;

import org.apache.thrift.TException;

public class HelloWorldImpl implements HelloWorldService.Iface {

	@Override
	public String sayHello(String username) throws TException {
		return "Hi," + username + " welcome to my blog www.micmiu.com";
	}

}
