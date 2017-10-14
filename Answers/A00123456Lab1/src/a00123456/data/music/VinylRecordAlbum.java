/**
 * sub class of MusicMedia to hold fields distinct to vinyl records
 */
package a00123456.data.music;

import a00123456.data.MusicMedia;

/**
 * A vinyl record album.
 * 
 * @author Bullwinkle Moose
 *
 */
public class VinylRecordAlbum extends MusicMedia {

	public static final int STANDARD_WEIGHT = 120;

	private int numberOfTracks;
	private int weight;

	/**
	 * Default constructor
	 */
	public VinylRecordAlbum() {
		super();
	}

	/**
	 * Constructor allowing all the attributes to be set, except for the weight. The weight is set to STANDARD_WEIGHT.
	 * 
	 * @param title
	 *            the title to set
	 * @param artist
	 *            the artist to set
	 * @param numberOfTracks
	 *            the number of tracks to set
	 */
	public VinylRecordAlbum(String title, String artist, int numberOfTracks) {
		super(title, artist);
		this.numberOfTracks = numberOfTracks;
		weight = STANDARD_WEIGHT;
	}

	/**
	 * Constructor allowing all the attributes to be set.
	 * 
	 * @param title
	 *            the title to set
	 * @param artist
	 *            the artist to set
	 * @param numberOfTracks
	 *            the number of tracks to set
	 * @param weight
	 *            the weight to set
	 */
	public VinylRecordAlbum(String title, String artist, int numberOfTracks, int weight) {
		super(title, artist);
		setNumberOfTracks(numberOfTracks);
		setWeight(weight);
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
	 * Get the weight.
	 * 
	 * @return the weight as an int
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * Sets the weight. Only weight greater than STANDARD_WEIGHT are allowed.
	 * 
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(int weight) {
		if (weight >= STANDARD_WEIGHT) {
			this.weight = weight;
		}
	}

	/**
	 * Plays a vinyl record album.
	 * For now, it needs only to output a message to the console indicating that a record is being played.
	 */
	@Override
	public void play() {
		System.out.println("Playing the record on my dad's old truntable.");
	}

	/*
	 * Print the attribute values.
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VinylRecordAlbum [numberOfTracks=" + numberOfTracks + ", weight=" + weight + ", toString()=" + super.toString() + "]";
	}

}
