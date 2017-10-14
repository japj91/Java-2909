package A00980851.data;

import java.time.LocalDate;

/**
 * Name            Japneet Johal A00 980 851
 * Project Name    AssignmentOne
 * Class  Name     Customer
 * Date            2017-05-28
 * Customer objects holds various pieces of information on a customer
 */
public class Customer {
    private String id;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String postalCode;
    private String phone;
    private String email;
    private LocalDate joinDate;

    public Customer(){}

    /**
     * The method where all attributes are assigned
     * @param id
     * @param firstName
     * @param lastName
     * @param street
     * @param city
     * @param postalCode
     * @param phone
     * @param email
     * @param joinDate
     */
    public Customer(String id, String firstName, String lastName, String street, String city,String postalCode, String phone, String email, LocalDate joinDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.postalCode =postalCode;
        this.city = city;
        this.phone = phone;
        this.email = email;
        this.joinDate = joinDate;
    }

    /**
     * gets first name
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * gets last name
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * gets street address
     * @return street
     */
    public String getStreet() {
        return street;
    }

    /**
     *
      * @param street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * gets the city
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * gets the phone number
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * gets email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * gets joinDAte
     * @return joindate
     */
    public LocalDate getJoinDate() {
        return joinDate;
    }

    /**
     *
     * @param joinDate
     */
    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    /**
     *
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     *
     * @param postalCode
     */
    public  void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * to string methods
     * @return String
     */
    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", joinDate='" + joinDate + '\'' +
                '}';
    }
}
