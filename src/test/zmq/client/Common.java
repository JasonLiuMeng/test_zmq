package test.zmq.client;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.scene.chart.PieChart.Data;

public class Common {
	
	private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	public static Object ByteToObject(byte[] bytes) {
		Object obj = null;
		try {
			// bytearray to object
			ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
			ObjectInputStream oi = new ObjectInputStream(bi);

			obj = oi.readObject();
			bi.close();
			oi.close();
		} catch (Exception e) {
			System.out.println("translation" + e.getMessage());
			e.printStackTrace();
		}
		return obj;
	}
	
	public static byte[] ObjectToByte(java.lang.Object obj) {  
	    byte[] bytes = null;  
	    try {  
	      ByteArrayOutputStream bo = new ByteArrayOutputStream();
	      ObjectOutputStream oo = new ObjectOutputStream(bo);
	      oo.writeObject(obj);
	      bytes = bo.toByteArray();
	      oo.close();
	      bo.close();
	    } catch (Exception e) {  
	        System.out.println("translation" + e.getMessage());  
	        e.printStackTrace();  
	    }  
	    return bytes;  
	}  
	
	public static String getCurrentTime(){
		Date date = new Date();
		return DATE_FORMAT.format(date);
	}
}
