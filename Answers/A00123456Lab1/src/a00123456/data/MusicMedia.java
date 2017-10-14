/**
 * abstract class used to store common fields for music media subtypes
 */
package a00123456.data;

/**
 * 
 * @author Bullwinkle Moose
 *
 */

public abstract class MusicMedia {

	private String title;
	private String artist;

	/**
	 * default constructor
	 */
	public MusicMedia() {
		super();
	}

	/**
	 * @param title
	 *            used to set the title field
	 * @param artist
	 *            used to set the artist field
	 */
	public MusicMedia(String title, String artist) {
		super();
		setTitle(title);
		setArtist(artist);
	}

	/**
	 * abstract method to be implemented by sub classes. Used to play a music media type.
	 */
	public abstract void play();

	/**
	 * @return the title as a String
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		if (title != null && title.length() > 0) {
			this.title = title;
		}
	}

	/**
	 * @return the artist as a String
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * @param artist
	 *            the artist to set
	 */
	public void setArtist(String artist) {
		if (artist != null && artist.length() > 0) {
			this.artist = artist;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MusicMedia [title=" + title + ", artist=" + artist + "]";
	}

}
