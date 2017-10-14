/**
 * Driver class to test the 2613 Lab1 solution
 */
package a00123456;

import a00123456.data.music.AudioFile;
import a00123456.data.music.CompactDisk;
import a00123456.data.music.VinylRecordAlbum;

/**
 * This program demonstrates an understanding of the purpose and use object oriented design, including the use of interfaces, abstract parent classes,
 * and inheritance. It also demonstrates the use of code commenting, and the purpose and correct use oftoString().
 * The Lab1 class tests the validation process, including values that are out of range. Finally, all of the code demonstrates proper coding practices.
 * 
 * @author Bullwinkle Moose
 *
 */
public class Lab1 {

	/**
	 * The main driver for Lab1. It creates a Lab1
	 * 
	 * @param args
	 *            not used in this application
	 */
	public static void main(String[] args) {
		new Lab1().test();
	}

	/**
	 * Tests MusicMedia objects.
	 */
	public void test() {

		System.out.println("> create a vinyl record and test weight validation...");
		VinylRecordAlbum album = new VinylRecordAlbum("Spin Me", "The Spinners", 12);
		System.out.println(album);
		album.setWeight(110);
		System.out.println(album);
		album.setWeight(180);
		System.out.println(album);
		album.play();

		System.out.println("> create and play a compact disk");
		CompactDisk cd = new CompactDisk("Turnabout Intruder", "Jim Kirk", 9);
		System.out.println(cd);
		cd.play();

		System.out.println("> create, play, and delete an audio file");
		AudioFile audioFile = new AudioFile("Buzz-Click", "Cyber Punks", "Wish I Bought Vinyl.m4a", 3.46);
		System.out.println(audioFile);
		audioFile.save("C:/My Music/iTunes", "Wish I Bought Vinyl.m4a");
		audioFile.play();
		audioFile.delete("C:/My Music/iTunes", "Wish I Bought Vinyl.m4a");
	}

}
