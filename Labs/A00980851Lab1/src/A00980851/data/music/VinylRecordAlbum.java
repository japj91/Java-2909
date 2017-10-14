package A00980851.data.music;

import A00980851.data.MusicMedia;

/**
 * Name Japneet Johal A00 980 851 
 * Project Name lab1 
 * Class Name VinylRecordAlbum
 * Date 2017-04-26
 */
public class VinylRecordAlbum extends MusicMedia {

	static final int STANDARD_WEIGHT = 120;

	private int weight;

	private int numberOfTracks;

	public VinylRecordAlbum() {
	}

	public VinylRecordAlbum(String title, String artist, int numberOfTracks) {
		super(title, artist);

		setNumberOfTracks(numberOfTracks);

		weight = STANDARD_WEIGHT; // if weight is not defined we give it the
									// standard weight
	}

	public VinylRecordAlbum(String title, String artist, int numberOfTracks, int weight) {
		super(title, artist);

		this.numberOfTracks = numberOfTracks;

		this.weight = weight;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		// To change the weight it has to be above the standard weight

		if (weight > STANDARD_WEIGHT) {
			this.weight = weight;
		}
	}

	public int getNumberOfTracks() {
		return numberOfTracks;
	}

	public void setNumberOfTracks(int numberOfTracks) {
		// making the int a string to test if its null
		// could use if numberOfTracks != 0
		String temp = String.valueOf(numberOfTracks);

		if (temp != null) {
			this.numberOfTracks = numberOfTracks;
		}
	}

	@Override
	public void play() {
		System.out.printf("Record \"%s\" is being played\n", getTitle());
	}

	@Override
	public String toString() {
		return "VinylRecordAlbum{" + "weight=" + weight + ", numberOfTracks=" + numberOfTracks + super.toString() + '}';
	}

}
