package course.oop.storage;
import java.util.*;
import java.io.*;

public class RecordManager {
	//private ArrayList<Records> records = new ArrayList<Records>();
	
	private HashMap<String, Records> records = new HashMap<>();
	public RecordManager() {
		
		 try
	      {
	         FileInputStream fis = new FileInputStream("records.ser");
	         ObjectInputStream ois = new ObjectInputStream(fis);
	         records = (HashMap) ois.readObject();
	         ois.close();
	         fis.close();
	      }catch(IOException ioe)
	      {
	         ioe.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("Class not found");
	         c.printStackTrace();
	         return;
	      }

	}
	

	public HashMap<String, Records> getRecords() {
		return records;
	}
	
	public void writeBack() {
		try
        {
               FileOutputStream fos =
                  new FileOutputStream("records.ser");
               ObjectOutputStream oos = new ObjectOutputStream(fos);
               oos.writeObject(records);
               oos.close();
               fos.close();
               System.out.printf("Serialized HashMap data is saved in records.ser");
        }catch(IOException ioe)
         {
               ioe.printStackTrace();
         }
	}
	
}
