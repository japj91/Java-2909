package A00980851.data.music;

import A00980851.data.MusicMedia;
import A00980851.io.FileManager;

/**
 * Name Japneet Johal A00 980 851 
 * Project Name lab1 
 * Class Name AudioFile 
 * Date 2017-04-26
 */
public class AudioFile extends MusicMedia implements FileManager {

	private double fileSize;
	private String fileName;

	public AudioFile() {
	}

	public AudioFile(String title, String artist, String fileName, double fileSize) {
		super(title, artist);

		setFileName(fileName);

		setFileSize(fileSize);
	}

	public void delete(String path, String fileName) {
		System.out.printf("Audio file %s is being deleted", getFileName());
	}

	public String getFileName() {
		return fileName;
	}

	public double getFileSize() {
		return fileSize;
	}

	@Override
	public void play() {
		System.out.printf("Audio file \"%s\" is being played\n", getFileName());
	}

	public void save(String path, String fileName) {
		System.out.printf("Audio File \"%s\" to %s saved\n", fileName, path);
	}

	public void setFileName(String fileName) {
		if (fileName != null) {
			this.fileName = fileName;
		}
	}

	public void setFileSize(double fileSize) {
		// making the double a string to test if its null
		// could use if numberOfTracks != 0
		String temp = String.valueOf(fileSize);

		if (temp != null) {
			this.fileSize = fileSize;
		}
	}

	@Override
	public String toString() {
		return "AudioFile{" + "fileSize=" + fileSize + ", fileName='" + fileName + '\'' + '}';
	}
}
