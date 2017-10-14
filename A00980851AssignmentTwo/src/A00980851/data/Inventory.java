package A00980851.data;

/**
 * Name Japneet Johal A00 980 851 Project Name AssignmentOne Class Name
 * Inventory Date 2017-05-28 Invenotry class stores inventory attributes
 */
public class Inventory {
	private String motorCyleID;
	private String description;
	private String partNumber;
	private String price;
	private String quantity;
	private String total;

	public Inventory() {
	}

	/**
	 *
	 * @param motorCyleID
	 * @param description
	 * @param partNumber
	 * @param price
	 * @param quantity
	 */
	public Inventory(String motorCyleID, String description, String partNumber, String price, String quantity) {
		this.motorCyleID = motorCyleID;
		this.description = description;
		this.partNumber = partNumber;
		this.price = price;
		this.quantity = quantity;
	}

	/**
	 *
	 * @return total
	 */
	public String getTotal() {
		return total;
	}

	/**
	 *
	 * @param total
	 */
	public void setTotal(String total) {
		this.total = total;
	}

	/**
	 *
	 * @return motorcycleID
	 */
	public String getMotorCyleID() {
		return motorCyleID;
	}

	public void setMotorCyleID(String motorCyleID) {
		this.motorCyleID = motorCyleID;
	}

	/**
	 *
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 *
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 *
	 * @return partNumber
	 */
	public String getPartNumber() {
		return partNumber;
	}

	/**
	 *
	 * @param partNumber
	 */
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	/**
	 *
	 * @return price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 *
	 * @param price
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 *
	 * @return quantity
	 */
	public String getQuantity() {
		return quantity;
	}

	/**
	 *
	 * @param quantity
	 */
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	/**
	 *
	 * @return String
	 */
	@Override
	public String toString() {
		return "Inventory{" + "motorCyleID='" + motorCyleID + '\'' + ", description='" + description + '\''
				+ ", partNumber='" + partNumber + '\'' + ", price='" + price + '\'' + ", quantity='" + quantity + '\''
				+ '}';
	}
}
