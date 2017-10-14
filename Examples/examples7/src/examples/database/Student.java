package examples.database;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Student {
	private String studentId;
	private String firstName;
	private String mi;
	private String lastName;
	private String phone;
	private LocalDate birthDate;
	private String street;
	private String zipCode;
	private String deptID;

	public Student(String studentId, String firstName, String mi, String lastName, String phone, LocalDate birthDate, String street, String zipCode,
			String deptID) {
		this.studentId = studentId;
		this.firstName = firstName;
		this.mi = mi;
		this.lastName = lastName;
		this.phone = phone;
		this.birthDate = birthDate;
		this.street = street;
		this.zipCode = zipCode;
		this.deptID = deptID;
	}

	public Student(String studentId, String firstName, String mi, String lastName, String phone, String birthDate, String street, String zipCode,
			String deptID) {
		this.studentId = studentId;
		this.firstName = firstName;
		this.mi = mi;
		this.lastName = lastName;
		this.phone = phone;
		setBirthDate(birthDate);
		this.street = street;
		this.zipCode = zipCode;
		this.deptID = deptID;
	}

	public Student() {
	}

	/**
	 * @return the studentId
	 */
	public String getStudentId() {
		return studentId;
	}

	/**
	 * @param ssn
	 *            the ssn to set
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the mi
	 */
	public String getMi() {
		return mi;
	}

	/**
	 * @param mi
	 *            the mi to set
	 */
	public void setMi(String mi) {
		this.mi = mi;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the birthDate
	 */
	public LocalDate getBirthDate() {
		return birthDate;
	}

	public String getBirthDateString() {
		if (birthDate == null) {
			return null;
		}

		// MS SQL Server // String result = birthDate.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
		String result = birthDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		return result;
	}

	/**
	 * @param birthDate
	 *            the birthDate to set
	 */
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * 
	 * @param birthDate
	 */
	public void setBirthDate(String birthDate) {
		if (birthDate == null || birthDate.trim().length() == 0 || birthDate.equalsIgnoreCase("null")) {
			// don't set the birthdate
		} else {
			this.birthDate = LocalDate.parse(birthDate);
		}
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street
	 *            the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode
	 *            the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @return the deptID
	 */
	public String getDeptID() {
		return deptID;
	}

	/**
	 * @param deptID
	 *            the deptID to set
	 */
	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", firstName=" + firstName + ", mi=" + mi + ", lastName=" + lastName + ", phone=" + phone
				+ ", birthDate=" + birthDate + ", street=" + street + ", zipCode=" + zipCode + ", deptID=" + deptID + "]";
	}

}
