package test.zmq.client;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.zeromq.ZMQ;

import test.zmq.manage.impl.ZMQContextImpl;
import test.zmq.msg.msgobj.Message;

public class ZMQ_Client {
	
	private static Logger logger = Logger.getLogger(ZMQ_Client.class);  
	
	 public static void main(String[] args) {
	        ZMQ.Context context = ZMQ.context(1);

	        //  Socket to talk to server
	        logger.info("Connecting to hello world server…");

	        ZMQ.Socket requester = context.socket(ZMQ.REQ);
	        requester.connect("tcp://127.0.0.1:5570");

	        for (int requestNbr = 0; requestNbr != 10; requestNbr++) {
	        	
	        	Message message = new Message();
	        	message.setKey("myTestZMQHandle");
	        	message.setMessage("我是发送的Message time : " + System.currentTimeMillis());
	        	message.setMessageId(UUID.randomUUID().toString()); //使用UUID生产唯一的消息ID
	        	
	            requester.send(Common.ObjectToByte(message), 0); //此处为了简便，直接使用IO流转换对象，在真实项目中可以使用Google的ProtoBuf进行消息对象传递
	            
	            byte[] reply = requester.recv(0);
	            Message rep = (Message)Common.ByteToObject(reply);
	    		logger.info("ZMQ Repley info： id : " + rep.getMessageId() + ";  msg : " + rep.getMessage());
	        }
	        requester.close();
	        context.term();
	    }
	 
}
