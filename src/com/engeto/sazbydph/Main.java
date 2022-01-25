package com.engeto.sazbydph;

public class Main {
    public static final String INPUT_FILE = "vat-eu.txt";
    public static final String OUTPUT_FILE = "vat-over-20.txt";

    public static void main(String[] args) {

        CountryList countryList = new CountryList();
        try {
            countryList.importFromFile(INPUT_FILE);
        } catch (CountryException e) {
            System.err.println(e.getLocalizedMessage());
        }

        // countryList.printCountryList();

        // countryList.printVatAbove20SpecialVatNotUsed();

        countryList.printSortedCountries();

        countryList.exportToFile(OUTPUT_FILE);

    }
}
