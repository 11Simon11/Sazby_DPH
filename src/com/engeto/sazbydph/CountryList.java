package com.engeto.sazbydph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class CountryList {

    public static final String DEFAULT_DELIMITER = "\t";

    ArrayList<Country> countryList = new ArrayList<>();

    public Country getCountry(int index) {
        return countryList.get(index);
    }

    public void addCountry(Country country) {
        countryList.add(country);
    }

    public void removeCountry(int index) {
        countryList.remove(index);
    }

    public int size() {
        return countryList.size();
    }

    public void importFromFile(String fileName) throws CountryException {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            int line = 0;
            while (scanner.hasNextLine()) {
                String record =scanner.nextLine();
                line++;
                try {
                    this.addCountry(Country.parse(record, DEFAULT_DELIMITER));
                } catch (CountryException e) {
                    throw new CountryException("Neplatný vstupní soubor "+fileName+" na řádku "+line+":\n\t"+e.getLocalizedMessage());
                }
            }
        } catch (FileNotFoundException e) {
            throw new CountryException("Vstupní soubor: "+fileName+" nebyl nalezen: "+e.getLocalizedMessage());
        }
    }
}
