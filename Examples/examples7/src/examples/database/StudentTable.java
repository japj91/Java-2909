/**
 * 
 */
package examples.database;

/**
 * @author scirka
 *
 */
public enum StudentTable {

	STUDENT_ID("studentId", "VARCHAR", 9, 1), //
	FIRST_NAME("firstName", "VARCHAR", 20, 2), //
	MI("mi", "VARCHAR", 1, 3), //
	LAST_NAME("lastName", "VARCHAR", 20, 4), //
	PHONE("phone", "VARCHAR", 10, 5), //
	BIRTHDATE("birthDate", "DATE", -1, 6), //
	STREET("street", "VARCHAR", 40, 7), //
	ZIP_CODE("zipCode", "VARCHAR", 10, 8), //
	DEPT_ID("deptID", "VARCHAR", 4, 9); //

	private final String name;
	private final String type;
	private final int length;
	private final int column;

	StudentTable(String name, String type, int length, int column) {
		this.name = name;
		this.type = type;
		this.length = length;
		this.column = column;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public int getLength() {
		return length;
	}

	public int getColumn() {
		return column;
	}
}
