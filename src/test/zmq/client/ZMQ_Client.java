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
	        logger.info("Connecting to hello world server��");

	        ZMQ.Socket requester = context.socket(ZMQ.REQ);
	        requester.connect("tcp://127.0.0.1:5570");

	        for (int requestNbr = 0; requestNbr != 10; requestNbr++) {
	        	
	        	Message message = new Message();
	        	message.setKey("myTestZMQHandle");
	        	message.setMessage("���Ƿ��͵�Message time : " + System.currentTimeMillis());
	        	message.setMessageId(UUID.randomUUID().toString()); //ʹ��UUID����Ψһ����ϢID
	        	
	            requester.send(Common.ObjectToByte(message), 0); //�˴�Ϊ�˼�㣬ֱ��ʹ��IO��ת����������ʵ��Ŀ�п���ʹ��Google��ProtoBuf������Ϣ���󴫵�
	            
	            byte[] reply = requester.recv(0);
	            Message rep = (Message)Common.ByteToObject(reply);
	    		logger.info("ZMQ Repley info�� id : " + rep.getMessageId() + ";  msg : " + rep.getMessage());
	        }
	        requester.close();
	        context.term();
	    }
	 
}
