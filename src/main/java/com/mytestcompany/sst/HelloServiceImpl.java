package com.mytestcompany.sst;

public class HelloServiceImpl implements HelloService {

	@Override
	public String sayHello(String name) {
		return "Hello " + name;
	}

	@Override
	public String sayBye(String name) {
		return "Bye " + name;
	}
}
