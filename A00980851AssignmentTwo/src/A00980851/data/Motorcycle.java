package A00980851.data;

/**
 * Name Japneet Johal A00 980 851 Project Name AssignmentOne Class Name
 * Motorcycle Date 2017-05-28 MotorCycle class that stores motorcycle attributes
 */
public class Motorcycle {
	private String motorcycle_ID;
	private String make;
	private String model;
	private String year;
	private String serialNumber;
	private String mileage;

	public Motorcycle() {
	}

	/**
	 *
	 * @param motorcycle_ID
	 * @param make
	 * @param model
	 * @param year
	 * @param serialNumber
	 * @param mileage
	 */
	public Motorcycle(String motorcycle_ID, String make, String model, String year, String serialNumber,
			String mileage) {
		this.motorcycle_ID = motorcycle_ID;
		this.make = make;
		this.model = model;
		this.year = year;
		this.serialNumber = serialNumber;
		this.mileage = mileage;
	}

	/**
	 *
	 * @return motorcycle_ID
	 */
	public String getMotorcycle_ID() {
		return motorcycle_ID;
	}

	/**
	 *
	 * @param motorcycle_ID
	 */
	public void setMotorcycle_ID(String motorcycle_ID) {
		this.motorcycle_ID = motorcycle_ID;
	}

	/**
	 *
	 * @return make
	 */
	public String getMake() {
		return make;
	}

	/**
	 *
	 * @param make
	 */
	public void setMake(String make) {
		this.make = make;
	}

	/**
	 *
	 * @return year
	 */
	public String getYear() {
		return year;
	}

	/**
	 *
	 * @param year
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 *
	 * @return serialNumber
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 *
	 * @param serialNumber
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 *
	 * @return mileage
	 */
	public String getMileage() {
		return mileage;
	}

	/**
	 *
	 * @param mileage
	 */
	public void setMileage(String mileage) {
		this.mileage = mileage;
	}

	/**
	 *
	 * @return model
	 */
	public String getModel() {
		return model;
	}

	/**
	 *
	 * @param model
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 *
	 * @return String
	 */
	@Override
	public String toString() {
		return "Motorcycle{" + "motorcycle_ID='" + motorcycle_ID + '\'' + ", make='" + make + '\'' + ", year='" + year
				+ '\'' + ", serialNumber='" + serialNumber + '\'' + ", mileage='" + mileage + '\'' + '}';
	}
}
