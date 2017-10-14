package A00980851.data;

/**
 * Name Japneet Johal A00 980 851 Project Name lab1 Class Name MusicMedia Date
 * 2017-04-26
 */

public abstract class MusicMedia {
	private String artist;

	private String title;

	public MusicMedia() {
	}

	public MusicMedia(String title, String artist) {
		// in all classes we use the set method to check if the values are
		// present
		setTitle(title);
		setArtist(artist);
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		if (artist != null) {
			this.artist = artist;
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if (title != null) {
			this.title = title;
		}
	}

	public abstract void play();

	@Override
	public String toString() {
		return " MusicMedia{" + "artist='" + artist + '\'' + ", title='" + title + '\'' + '}';
	}
}
