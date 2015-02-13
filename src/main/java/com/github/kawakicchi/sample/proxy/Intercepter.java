package com.github.kawakicchi.sample.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Intercepter implements InvocationHandler {

	private Object target;

	public Intercepter(final Object target) {
		this.target = target;
	}

	public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
		if (null != method.getAnnotation(Logging.class)) {
			System.out.println("Logging");
		}

		Object result = method.invoke(target, args);

		return result;
	}
}
