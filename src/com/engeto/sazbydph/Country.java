package com.engeto.sazbydph;

import java.math.BigDecimal;

public class Country {

    String abb;
    String name;
    Double vat;
    Double loweredVat;
    boolean specialVatUsed;

    public Country(String abb, String name, Double vat, Double loweredVat, boolean specialVatUsed) {
        this.abb = abb;
        this.name = name;
        this.vat = vat;
        this.loweredVat = loweredVat;
        this.specialVatUsed = specialVatUsed;
    }

    public static Country parse(String text, String delimiter) throws CountryException {
        String[] items = text.split(delimiter);

        int numberOfItems = items.length;
        if (numberOfItems != 5) throw new CountryException("Nesprávný počet údajů na řádku: \""+text+"\"\n\t");

        String abb = items[0];
        String name = items[1];
        try {
            items[2] = items[2].replace(",", ".");
            items[3] = items[3].replace(",", ".");
            Double vat = Double.parseDouble(items[2]);
            Double loweredVat = Double.parseDouble(items[3]);
            boolean specialVatUsed;
            if (items[4].equals("true")) specialVatUsed = true;
            else if (items[4].equals("false")) specialVatUsed = false;
            else throw new CountryException("Neplatný boolean údaj na řádku: \""+text+"\"\n\t");
            return new Country(abb, name,vat,loweredVat,specialVatUsed);
        } catch (NumberFormatException e) { throw new CountryException("Špatný formát čísla na řádku: \""+text+"\"\n\t"+e.getLocalizedMessage()); }
    }

    public String outputStringBase() {
        return getName()+" ("+getAbb()+"): "+Math.round(getVat())+" %";
    }

    public String outputString() {
        return getName()+" ("+getAbb()+"): "+Math.round(getVat())+" % ("+Math.round(getLoweredVat())+" %)";
    }

    public String getAbb() {
        return abb;
    }

    public void setAbb(String abb) {
        this.abb = abb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public Double getLoweredVat() {
        return loweredVat;
    }

    public void setLoweredVat(double loweredVat) {
        this.loweredVat = loweredVat;
    }

    public boolean isSpecialVatUsed() {
        return specialVatUsed;
    }

    public void setSpecialVatUsed(boolean specialVatUsed) {
        this.specialVatUsed = specialVatUsed;
    }
}
