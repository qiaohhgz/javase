package com.jim.designpattern.command;

//ConcreteCommandA.java文件

public class ConcreteCommandA extends Command {
	public ConcreteCommandA(Receiver receiver) {
		super(receiver);
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		receiver.actionA();
	}
}