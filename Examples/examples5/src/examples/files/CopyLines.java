package examples.files;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.Instant;
import java.io.IOException;

public class CopyLines {
    public static void main(String[] args) throws IOException {
		Instant startTime = Instant.now();

        BufferedReader inputStream = null;
        PrintWriter outputStream = null;

        try {
            inputStream = new BufferedReader(new FileReader("dc.txt"));
            outputStream = new PrintWriter(new FileWriter("dca.txt"));

            String l;
            while ((l = inputStream.readLine()) != null) {
                outputStream.println(l);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
        
		Instant endTime = Instant.now();
		System.out.println(String.format("Duration: %d ms", Duration.between(startTime, endTime).toMillis()));
    }
}
