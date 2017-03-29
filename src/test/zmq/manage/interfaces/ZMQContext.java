package test.zmq.manage.interfaces;

import java.util.HashMap;
import java.util.Map;

import test.zmq.msg.impl.MessageHandleAbstract;
import test.zmq.msg.interfaces.MessageHandle;

public interface ZMQContext {
	
	public Map<String, MessageHandle> MSG_REGISTER = new HashMap<String, MessageHandle>();
	
	public int initZMQContext();
	
	public void setServerIP(String ip);
	public void setServerPort(String port);
	public void setServerType(int type);
	public void close();
	
	public boolean registerMsgHandler(String msgKey, Class<? extends MessageHandleAbstract> cla);
	public boolean unregisterMsgHandler(String msgKey);
	public MessageHandle getMsgHandle(String msgKey);
}
