package test.zmq.msg.handle;

import org.apache.log4j.Logger;
import org.zeromq.ZMQ.Socket;

import test.zmq.msg.impl.MessageHandleAbstract;
import test.zmq.msg.msgobj.Message;

public class MyTestMessageHandle extends MessageHandleAbstract {

	private static Logger logger = Logger.getLogger(MyTestMessageHandle.class);  
	
	public MyTestMessageHandle(){
		
	}
	
	public MyTestMessageHandle(String msgKey) {
		// TODO Auto-generated constructor stub
		super(msgKey);
	}
	
	@Override
	public Message doMessageHandle(Message msg, Socket req) {
		// TODO Auto-generated method stub
		logger.info("接收到了服务器端的ZMQ消息： id : " + msg.getMessageId() + ";  msg : " + msg.getMessage());
		msg.setMessage("我已经收到了你的消息！");
		return msg;
	}
}
