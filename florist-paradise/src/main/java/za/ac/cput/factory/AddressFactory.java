package za.ac.cput.factory;

import za.ac.cput.domain.Address;
import za.ac.cput.utility.Helper;

public class AddressFactory {
    public static Address buildAddress(int streetNumber, String streetName, String suburb, String city, String postalCode) {
        if (
                streetNumber<=0 ||
                        Helper.isNullOrEmpty(streetName) ||
                        Helper.isNullOrEmpty(suburb) ||
                        Helper.isNullOrEmpty(city) ||
                        Helper.isNullOrEmpty(postalCode)
        ) {
            return null;
        }

        return new Address.AddressBuilder()
                .setStreetNumber(streetNumber)
                .setStreetName(streetName)
                .setSuburb(suburb)
                .setCity(city)
                .setPostalCode(postalCode)
                .buildAddress();
    }
}
