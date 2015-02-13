package com.github.kawakicchi.sample.proxy;

import java.lang.reflect.Proxy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ProxyTest {

	@Test
	public void test() {
		Command command = new SampleCommand();

		Command proxy = (Command) Proxy
				.newProxyInstance(Command.class.getClassLoader(), command.getClass().getInterfaces(), new Intercepter(command));

		proxy.initialize();
		proxy.execute();
		proxy.destroy();
	}
}
