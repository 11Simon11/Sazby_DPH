package com.engeto.sazbydph;

public class Main {
    public static final String INPUT_FILE = "vat-eu.txt";

    public static void main(String[] args) {

        CountryList countryList = new CountryList();
        try {
            countryList.importFromFile(INPUT_FILE);
        } catch (CountryException e) {
            System.err.println(e.getLocalizedMessage());
        }

        for (int i = 0; i < countryList.size(); i++) {
            System.out.println(countryList.getCountry(i).outputString());
        }

    }
}
