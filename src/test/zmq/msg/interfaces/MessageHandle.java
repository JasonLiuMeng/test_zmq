package test.zmq.msg.interfaces;

import org.zeromq.ZMQ.Socket;

import test.zmq.msg.msgobj.Message;

public interface MessageHandle {

	public Message doMessageHandle(Message msg, Socket req);
	
	public String getMessageKey();
	public void setMessageKey(String msgKey);
}
