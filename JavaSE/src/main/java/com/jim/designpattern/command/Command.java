package com.jim.designpattern.command;

/**
 * @category 命令模式：将一个请求封装为一个对象，从而使你可用不同的请求对客户进行参数化；对 请求排队或记录请求日志，以及支持可撤销的操作。
 */
public abstract class Command {
	protected Receiver receiver;

	public Command(Receiver receiver) {
		this.receiver = receiver;
	}

	public abstract void execute();
}