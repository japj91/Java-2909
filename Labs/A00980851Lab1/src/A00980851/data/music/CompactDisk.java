package A00980851.data.music;

import A00980851.data.MusicMedia;

/**
 * Name Japneet Johal A00 980 851 
 * Project Name lab1 
 * Class Name CompactDisk 
 * Date 2017-04-26
 */
public class CompactDisk extends MusicMedia {
	int numberOfTracks;

	public CompactDisk() {
	}

	public CompactDisk(String title, String artist, int numberOfTracks) {
		super(title, artist);

		setNumberOfTracks(numberOfTracks);
	}

	public int getNumberOfTracks() {
		return numberOfTracks;
	}

	public void setNumberOfTracks(int numberOfTracks) {
		String temp = String.valueOf(numberOfTracks);

		if (temp != null) {
			this.numberOfTracks = numberOfTracks;
		}
	}

	@Override
	public void play() {
		System.out.printf("CD \"%s\" being played\n", getTitle());
	}

	@Override
	public String toString() {
		return "CompactDisk{" + "numberOfTracks=" + numberOfTracks + super.toString() + '}';
	}

}
