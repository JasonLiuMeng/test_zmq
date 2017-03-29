package test.zmq.manage.impl;

import org.apache.log4j.Logger;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Socket;

import test.zmq.client.Common;
import test.zmq.manage.interfaces.ZMQContext;
import test.zmq.msg.impl.MessageHandleAbstract;
import test.zmq.msg.interfaces.MessageHandle;
import test.zmq.msg.msgobj.Message;

public class ZMQContextImpl implements ZMQContext {

	private static Logger logger = Logger.getLogger(ZMQContextImpl.class);  
	
	private String ip;
	private String port;
	private int type;
	
	private boolean THREAD_RUN = true;
	private ZMQ.Context context = null;
	private Socket socket;

	public ZMQContextImpl(String ip, String port, int type) {
		// TODO Auto-generated constructor stub
		this.ip = ip;
		this.port = port;
		this.type = type;
	}
	
	/**
	 * 
	 * @return 0 初始化成功, -1初始化失败，context不为null
	 */
	public int initZMQContext(){
		logger.info("init ZMQ. IP:" + ip + " Port:" + port + " Type : " + type);
		this.THREAD_RUN = true;
		if( null == context ){
			this.context = ZMQ.context(1);
		}else{
			return 1;
		}
		socket = context.socket(type);
		socket.bind("tcp://" + ip + ":" + port);
		return this.ZMQStart(socket);
	}
	
	private int ZMQStart(final Socket socket){
		Thread thread = new Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while ( THREAD_RUN && !this.isInterrupted() ) {
					 byte[] request = socket.recv(0);
					 logger.info("receive message.");
					 Message msgObj = (Message)Common.ByteToObject(request);
					 MessageHandle messageHandle = getMsgHandle(msgObj.getKey());
					 Message repMsg = messageHandle.doMessageHandle(msgObj, socket);
					socket.send(Common.ObjectToByte(repMsg), 0);
					logger.info("reply message.");
				}
			}
		};
		logger.info("ZMQ Thread start...");
		thread.start();
		return 0;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		if( null != socket ){
			socket.close();
			socket = null;
		}
		if( null != context ){
			context.close();
			context = null;
		}
	}
	

	@Override
	public MessageHandle getMsgHandle(String msgKey) {
		// TODO Auto-generated method stub
		return MSG_REGISTER.get(msgKey);
	}
	
	@Override
	public boolean registerMsgHandler(String msgKey, Class<? extends MessageHandleAbstract> clas) {
		// TODO Auto-generated method stub
		if( msgKey == null ){
			return false;
		}
		MessageHandle handle = null;
		try {
			handle = (MessageHandle)clas.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			logger.error(clas.getName() + " must have a no arguments constructor.");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		MSG_REGISTER.put(msgKey, handle);
		return true;
	}

	@Override
	public boolean unregisterMsgHandler(String msgKey) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void setServerIP(String ip) {
		// TODO Auto-generated method stub
		this.ip = ip;
	}
	@Override
	public void setServerPort(String port) {
		// TODO Auto-generated method stub
		this.port = port;
	}

	@Override
	public void setServerType(int type) {
		// TODO Auto-generated method stub
		this.type = type;
	}

}
