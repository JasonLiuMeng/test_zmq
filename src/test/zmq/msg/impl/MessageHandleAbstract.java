package test.zmq.msg.impl;

import org.zeromq.ZMQ.Socket;

import test.zmq.msg.interfaces.MessageHandle;
import test.zmq.msg.msgobj.Message;

public abstract class MessageHandleAbstract implements MessageHandle{

	private String msgKey = null;
	
	public MessageHandleAbstract(){
		
	}
	
	public MessageHandleAbstract(String msgKey) {
		// TODO Auto-generated constructor stub
		this.msgKey = msgKey;
	}
	
	@Override
	public abstract Message doMessageHandle(Message msg, Socket req);
	
	@Override
	public String getMessageKey(){
		return this.msgKey;
	}
	
	@Override
	public void setMessageKey(String msgKey) {
		// TODO Auto-generated method stub
		this.msgKey = msgKey;
	}
}
