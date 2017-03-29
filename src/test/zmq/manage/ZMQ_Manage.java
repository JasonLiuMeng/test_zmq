package test.zmq.manage;

import org.zeromq.ZMQ;

import test.zmq.manage.impl.ZMQContextImpl;
import test.zmq.manage.interfaces.ZMQContext;
import test.zmq.msg.handle.MyTestMessageHandle;

public class ZMQ_Manage {
	
	private static final String IP = "127.0.0.1";
	private static final String PORT = "5570";
	
	public void initRepContext(){
		ZMQContext context = new ZMQContextImpl(IP, PORT, ZMQ.REP);
		context.initZMQContext();
		context.registerMsgHandler("myTestZMQHandle", MyTestMessageHandle.class);
	}
	
	public void initSubContext(){
		ZMQContext context = new ZMQContextImpl(IP, PORT, ZMQ.SUB);
		context.initZMQContext();
	}
	
	
}
