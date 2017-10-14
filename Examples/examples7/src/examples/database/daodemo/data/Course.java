package examples.database.daodemo.data;

// courseId VARCHAR(10), subjectId VARCHAR(4), courseNumber VARCHAR(4), title VARCHAR(64), numOfCredits INTEGER, primary key (courseId) )";

public class Course {

	private String courseId;
	private String subjectId;
	private String courseNumber;
	private String title;
	private Integer numOfCredits;

	public Course(String courseId, String subjectId, String courseNumber, String title, int numOfCredits) {
		this.courseId = courseId;
		this.subjectId = subjectId;
		this.courseNumber = courseNumber;
		this.title = title;
		this.numOfCredits = numOfCredits;
	}

	/**
	 * @return the courseId
	 */
	public String getCourseId() {
		return courseId;
	}

	/**
	 * @param courseId
	 *            the courseId to set
	 */
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	/**
	 * @return the subjectId
	 */
	public String getSubjectId() {
		return subjectId;
	}

	/**
	 * @param subjectId
	 *            the subjectId to set
	 */
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	/**
	 * @return the courseNumber
	 */
	public String getCourseNumber() {
		return courseNumber;
	}

	/**
	 * @param courseNumber
	 *            the courseNumber to set
	 */
	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the numOfCredits
	 */
	public Integer getNumOfCredits() {
		return numOfCredits;
	}

	/**
	 * @param numOfCredits
	 *            the numOfCredits to set
	 */
	public void setNumOfCredits(Integer numOfCredits) {
		this.numOfCredits = numOfCredits;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", subjectId=" + subjectId + ", courseNumber=" + courseNumber
		        + ", title=" + title + ", numOfCredits=" + numOfCredits + "]";
	}
}
