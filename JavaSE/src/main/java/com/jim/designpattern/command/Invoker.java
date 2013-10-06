package com.jim.designpattern.command;

/**
 * @category 命令模式：将一个请求封装为一个对象，从而使你可用不同的请求对客户进行参数化；对 请求排队或记录请求日志，以及支持可撤销的操作。
 */
public class Invoker {
	private Command command;

	private void setCommand(Command command) {
		this.command = command;
	}

	public void executeCommand() {
		command.execute();
	}

	public static void main(String[] args) {
		Receiver receiver = new Receiver();

		Invoker invoker = new Invoker();
		invoker.setCommand(new ConcreteCommandA(receiver));
		invoker.executeCommand();

		invoker.setCommand(new ConcreteCommandB(receiver));
		invoker.executeCommand();

	}
}