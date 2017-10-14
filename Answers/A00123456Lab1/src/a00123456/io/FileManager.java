/**
 * Interface that defines methods for saving and deleting files from the hard disk
 */
package a00123456.io;

/**
 * Manage files.
 * 
 * @author Bullwinkle Moose
 *
 */
public interface FileManager {

	/**
	 * Save a file.
	 * 
	 * @param path
	 *            the directory to save to
	 * @param fileName
	 *            the name of the file being saved
	 */
	void save(String path, String fileName);

	/**
	 * Delete a file.
	 * 
	 * @param path
	 *            the directory where the file is located
	 * @param fileName
	 *            the name of the file to be deleted
	 */
	void delete(String path, String fileName);

}
