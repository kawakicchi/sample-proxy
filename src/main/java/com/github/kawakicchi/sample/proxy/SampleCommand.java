package com.github.kawakicchi.sample.proxy;

public class SampleCommand implements Command {

	@Override
	public void initialize() {
		System.out.println("SampleCommand.initialize()");
	}

	@Override
	public void destroy() {
		System.out.println("SampleCommand.destroy()");
	}

	@Override
	public void execute() {
		System.out.println("SampleCommand.execute()");
	}

}
