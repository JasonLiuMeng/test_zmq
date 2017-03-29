package test.zmq;

import test.zmq.manage.ZMQ_Manage;

public class TestMain {
	
	public static void testReq(){
		ZMQ_Manage manage = new ZMQ_Manage();
		manage.initRepContext();
	}

	public static void main(String[] args) {
		TestMain.testReq();
	}
}
