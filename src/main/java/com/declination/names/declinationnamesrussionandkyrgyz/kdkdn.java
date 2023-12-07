package com.declination.names.declinationnamesrussionandkyrgyz;

public class kdkdn {
    public static void main(String[] args) {
        Declination name = new Declination("Акпаралиев Санжар Таалайбекович");

        System.out.println(name.fullName(DeclinationProcessor.gcaseRod));
        System.out.println(name.fullName(DeclinationProcessor.gcaseDat));
        System.out.println(name.fullName(DeclinationProcessor.gcaseVin));
        System.out.println(name.fullName(DeclinationProcessor.gcaseTvor));
        System.out.println(name.fullName(DeclinationProcessor.gcasePred));
    }
}
