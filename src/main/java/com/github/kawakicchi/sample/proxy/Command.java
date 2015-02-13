package com.github.kawakicchi.sample.proxy;

public interface Command {

	public void initialize();

	public void destroy();

	@Logging
	public void execute();

}
