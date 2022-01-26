package com.engeto.sazbydph;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
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

    public void printCountryList() {
        for (Country country: countryList) {
            System.out.println(country.outputStringBase());
        }
    }

    public void printSortedCountries() {
        Collections.sort(this.countryList, new CountryComparator().reversed());
        for (Country country: countryList) {
            if (country.getVat() > 20 && !country.specialVatUsed) {
                System.out.println(country.outputString());
            }
        }
        System.out.print("====================\nSazba VAT 20 % nebo nižší nebo používají speciální sazbu: ");
        for (Country country: countryList) {
            if (country.getVat() <= 20 || country.isSpecialVatUsed()) {
                if (countryList.indexOf(country) + 1 == countryList.size()) {
                    System.out.println(country.getAbb());
                } else {
                    System.out.print(country.getAbb()+", ");
                }
            }
        }
    }

    public void printSortedCustom(int customVat) {
        Collections.sort(this.countryList, new CountryComparator().reversed());
        for (Country country: countryList) {
            if (country.getVat() > customVat && !country.specialVatUsed) {
                System.out.println(country.outputString());
            }
        }
        System.out.print("====================\nSazba VAT "+customVat+" % nebo nižší nebo používají speciální sazbu: ");
        for (Country country: countryList) {
            if (country.getVat() <= customVat || country.isSpecialVatUsed()) {
                if (countryList.indexOf(country) + 1 == countryList.size()) {
                    System.out.println(country.getAbb());
                } else {
                    System.out.print(country.getAbb()+", ");
                }
            }
        }
    }

    public void exportToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            for (Country country: countryList) {
                if (country.getVat() > 20 && !country.specialVatUsed) {
                    writer.println(country.outputString());
                }
            }
            writer.print("====================\nSazba VAT 20 % nebo nižší nebo používají speciální sazbu: ");
            for (Country country: countryList) {
                if (country.getVat() <= 20 || country.isSpecialVatUsed()) {
                    if (countryList.indexOf(country) + 1 == countryList.size()) {
                        writer.print(country.getAbb());
                    } else {
                        writer.print(country.getAbb()+", ");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void exportToFileCustom(int customVat) {
        File file = new File("vat-over-"+customVat+".txt");
        try (PrintWriter writer = new PrintWriter(file)) {
            for (Country country: countryList) {
                if (country.getVat() > customVat && !country.specialVatUsed) {
                    writer.println(country.outputString());
                }
            }
            writer.print("====================\nSazba VAT "+customVat+" % nebo nižší nebo používají speciální sazbu: ");
            for (Country country: countryList) {
                if (country.getVat() <= customVat || country.isSpecialVatUsed()) {
                    if (countryList.indexOf(country) + 1 == countryList.size()) {
                        writer.print(country.getAbb());
                    } else {
                        writer.print(country.getAbb()+", ");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
