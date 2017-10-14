package examples.files;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/** Demonstrate use of Serialization. */
public class SerialDemo {
	protected static final String FILENAME = "serial.dat";

	public static void main(String[] s) throws IOException, ClassNotFoundException {
		new SerialDemo().save();
		new SerialDemo().dump();
	}

	/** The save method in an appliction */
	public void save() throws IOException {
		ArrayList<Object> v = new ArrayList<Object>();
		// Gather the data
		MyData u1 = new MyData("Ian Darwin", "secret_java_cook");
		v.add(new Date());
		v.add(u1);
		v.add(new MyData("Abby Brant", "dujordian"));
		write(v);
	}

	/** Does the actual serialization */
	public void write(Object theGraph) throws IOException {
		// Save the data to disk.
		ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(FILENAME)));
		os.writeObject(theGraph);
		os.close();
	}

	public void dump() throws IOException, ClassNotFoundException {
		ObjectInputStream is = new ObjectInputStream(new FileInputStream(FILENAME));
		System.out.println(is.readObject());
		is.close();
	}
}

/** Simple data class to be serialized. */
@SuppressWarnings("serial")
class MyData implements Serializable {
	String userName;
	String passwordCypher;
	transient String passwordClear;

	public MyData(String name, String clear) {
		userName = name;
		// Save the clear text p/w in the object, it won't get serialized
		passwordClear = clear;
		// So we must save the encryption! Encryption not shown here.
		passwordCypher = DES.encrypt(passwordClear);
	}
}

/** More of the demo; this just generates a String; Strings are serializable */
class DES {
	// Obviously just a placeholder.
	public static String encrypt(String s) {
		return s;
	}
}
