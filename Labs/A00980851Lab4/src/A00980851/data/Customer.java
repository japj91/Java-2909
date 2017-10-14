package A00980851.data;

import java.time.LocalDate;

/**
 * Name            Japneet Johal A00 980 851
 * Project Name    A00980851Lab2
 * Class  Name     Customer
 * Date            2017-05-03
 * class uses a inner class to set all params
 */
public class Customer {
    private int identifier;
    private String firstName;
    private String lastName;
    private String streetName;
    private String city;
    private String postalCode;
    private String phoneNumber;
    private String emailAddress;
    private LocalDate date;

    public Customer() {
    }

    public static class Builder {

        private int identifier;
        private String firstName;
        private String lastName;
        private String streetName;
        private String city;
        private String postalCode;
        private String phoneNumber;
        private String emailAddress;
        private LocalDate date;


        /**
         * @param identifier  - entry identifier
         * @param phoneNumber - entry phoneNumber
         */

        public Builder(int identifier, String phoneNumber) {
            this.identifier = identifier;
            this.phoneNumber = phoneNumber;
        }

        /**
         * @param val - the value of firstName
         * @return Builder
         */
        public Builder firstName(String val) {
            this.firstName = val;
            return this;
        }

        /**
         * @param val - the value of last name
         * @return Builder
         */

        public Builder lastName(String val) {
            this.lastName = val;
            return this;
        }

        /**
         * @param val - the value of streetName
         * @return Builder
         */

        public Builder streetName(String val) {
            this.streetName = val;
            return this;
        }

        /**
         * @param val - the value of city
         * @return Builder
         */

        public Builder city(String val) {
            this.city = val;
            return this;
        }

        public Builder date(LocalDate date) {
            this.date = date;
            return this;
        }

        /**
         * @param val - the value of postalCode
         * @return Builder
         */

        public Builder postalCode(String val) {
            this.postalCode = val;
            return this;
        }

        /**
         * @param val - the value of emailAddress
         * @return Builder
         */

        public Builder emailAddress(String val) {
            this.emailAddress = val;
            return this;
        }

        /**
         * @return a cusomter object
         */
        public Customer build() {
            return new Customer(this);
        }
    }

    /**
     * @param builder takes a builder object as a param
     */
    public Customer(Builder builder) {

        setIdentifier(builder.identifier);

        setFirstName(builder.firstName);

        setLastName(builder.lastName);

        setStreetName(builder.streetName);

        setCity(builder.city);

        setPostalCode(builder.postalCode);

        setPhoneNumber(builder.phoneNumber);

        setEmailAddress(builder.emailAddress);

        setDate(builder.date);
    }

    /**
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city - takes the String city
     */
    public void setCity(String city) {
        if (city != null) {
            this.city = city;
        }
    }

    /**
     * @return identifer
     */
    public int getIdentifier() {
        return identifier;
    }

    /**
     * @param identifier - takes the String identfier
     */
    public void setIdentifier(int identifier) {
        String temp = String.valueOf(identifier);
        if (temp != null) {
            this.identifier = identifier;
        }
    }

    /**
     * @return fistName
     */

    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName - takes the String firstName
     */
    public void setFirstName(String firstName) {
        if (firstName != null) {
            this.firstName = firstName;
        }
    }

    /**
     * @return LastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName - takes the String lastName
     */
    public void setLastName(String lastName) {
        if (lastName != null) {
            this.lastName = lastName;
        }
    }

    /**
     * @return streetname
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * @param streetName - takes the String streetName
     */
    public void setStreetName(String streetName) {
        if (streetName != null) {
            this.streetName = streetName;
        }
    }

    /**
     * @return postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode - takes the String postalCode
     */
    public void setPostalCode(String postalCode) {
        if (postalCode != null) {
            this.postalCode = postalCode;
        }
    }

    /**
     * @return phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber- takes the String phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber != null) {
            this.phoneNumber = phoneNumber;
        }
    }

    /**
     * @return emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param emailAddress - takes the String emailAddress
     */
    public void setEmailAddress(String emailAddress) {
        if (emailAddress != null) {
            this.emailAddress = emailAddress;
        }
    }

    /**
     * @return String - the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @param date - the date user joined
     */
    public void setDate(LocalDate date) {
        if (date != null) {
            this.date = date;
        }
    }

    @Override
    public String toString() {
        return "Customer{" +
                "identifier=" + identifier +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", streetName='" + streetName + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
