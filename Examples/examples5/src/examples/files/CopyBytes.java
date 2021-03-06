package examples.files;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class CopyBytes {
	public static void main(String[] args) {
		Instant startTime = Instant.now();

		FileInputStream in = null;
		FileOutputStream out = null;

		try {
			in = new FileInputStream("dcart10.txt");
			out = new FileOutputStream("dcart10.bak");
			int c;

			while ((c = in.read()) != -1) {
				out.write(c);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		Instant endTime = Instant.now();
		System.out.println(String.format("Duration: %d ms", Duration.between(startTime, endTime).toMillis()));
	}
}
