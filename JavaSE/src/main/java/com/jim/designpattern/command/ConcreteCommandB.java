package com.jim.designpattern.command;

//ConcreteCommandB.java文件

public class ConcreteCommandB extends Command {
	public ConcreteCommandB(Receiver receiver) {
		super(receiver);
	}

	@Override
	public void execute() {
		receiver.actionB();
	}
}