/**
 * sub class of MusicMedia to hold fields distinct to compact disks
 */
package a00123456.data.music;

import a00123456.data.MusicMedia;

/**
 * A Compact Disk.
 * 
 * @author Bullwinkle Moose
 *
 */
public class CompactDisk extends MusicMedia {

	private int numberOfTracks;

	/**
	 * Default constructor
	 */
	public CompactDisk() {
		super();
	}

	/**
	 * Constructor allowing all the attributes to be set.
	 * 
	 * @param title
	 *            to set the title
	 * @param artist
	 *            to set the artist
	 * @param numberOfTracks
	 *            to set the number of tracks
	 */
	public CompactDisk(String title, String artist, int numberOfTracks) {
		super(title, artist);
		setNumberOfTracks(numberOfTracks);
	}

	/**
	 * Get the number of tracks.
	 * 
	 * @return the numberOfTracks as an int
	 */
	public int getNumberOfTracks() {
		return numberOfTracks;
	}

	/**
	 * Set the number of tracks.
	 * 
	 * @param numberOfTracks
	 *            the numberOfTracks to set
	 */
	public void setNumberOfTracks(int numberOfTracks) {
		if (numberOfTracks > 0) {
			this.numberOfTracks = numberOfTracks;
		}
	}

	/**
	 * Plays a CD. For now, it needs only to output a message to the console indicating that the CD is being played.
	 */
	@Override
	public void play() {
		System.out.println("Playing the CD even though I know vinyl sounds better.");
	}

	/*
	 * Print the attribute values.
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CompactDisk [numberOfTracks=" + numberOfTracks + ", toString()=" + super.toString() + "]";
	}

}
