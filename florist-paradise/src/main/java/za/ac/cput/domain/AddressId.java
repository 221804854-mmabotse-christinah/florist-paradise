package za.ac.cput.domain;

import java.io.Serializable;
import java.util.Objects;

public class AddressId implements Serializable {
    private int streetNumber;
    private String streetName;
    private String postalCode;

    public AddressId() {
    }
    public AddressId(int streetNumber, String streetName, String postalCode) {
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.postalCode = postalCode;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressId addressId = (AddressId) o;
        return streetNumber == addressId.streetNumber && Objects.equals(streetName, addressId.streetName) && Objects.equals(postalCode, addressId.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streetNumber, streetName, postalCode);
    }

    @Override
    public String toString() {
        return "AddressId{" +
                "streetNumber=" + streetNumber +
                ", streetName='" + streetName + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}
