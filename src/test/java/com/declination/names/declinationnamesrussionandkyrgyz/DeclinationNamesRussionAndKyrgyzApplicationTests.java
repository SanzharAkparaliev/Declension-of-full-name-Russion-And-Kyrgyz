package com.declination.names.declinationnamesrussionandkyrgyz;


import org.junit.jupiter.api.Assertions;
import org.junit.Test;

public class DeclinationNamesRussionAndKyrgyzApplicationTests {

    @Test
    public void convertor() {
        // ФИО
        Declination name = new Declination("Акпаралиев Санжар Таалайбекович");
        Assertions.assertEquals("Акпаралиева Санжара Таалайбековича", name.fullName(DeclinationProcessor.gcaseRod));
        Assertions.assertEquals("Акпаралиеву Санжару Таалайбековичу", name.fullName(DeclinationProcessor.gcaseDat));
        Assertions.assertEquals("Акпаралиева Санжара Таалайбековича", name.fullName(DeclinationProcessor.gcaseVin));
        Assertions.assertEquals("Акпаралиевым Санжаром Таалайбековичем", name.fullName(DeclinationProcessor.gcaseTvor));
        Assertions.assertEquals("Акпаралиеве Санжаре Таалайбековиче", name.fullName(DeclinationProcessor.gcasePred));
    }

    @Test
    public void first_convertor() {
        // в таком виде тоже
        Declination name = new Declination("Санжар Таалайбекович Акпаралиев");
        Assertions.assertEquals("Санжара Таалайбековича Акпаралиева", name.fullName(DeclinationProcessor.gcaseRod));
        Assertions.assertEquals("Санжару Таалайбековичу Акпаралиеву", name.fullName(DeclinationProcessor.gcaseDat));
        Assertions.assertEquals("Санжара Таалайбековича Акпаралиева", name.fullName(DeclinationProcessor.gcaseVin));
        Assertions.assertEquals("Санжаром Таалайбековичем Акпаралиевым", name.fullName(DeclinationProcessor.gcaseTvor));
        Assertions.assertEquals("Санжаре Таалайбековиче Акпаралиеве", name.fullName(DeclinationProcessor.gcasePred));
    }

    @Test
    public void surname_and_name_convertor() {
        // можно явно указать составляющие
        Declination name = new Declination("Санжар Акпаралиев");
        Assertions.assertEquals("Санжара Акпаралиева", name.fullName(DeclinationProcessor.gcaseRod));
        Assertions.assertEquals("Санжару Акпаралиеву", name.fullName(DeclinationProcessor.gcaseDat));
        Assertions.assertEquals("Санжара Акпаралиева", name.fullName(DeclinationProcessor.gcaseVin));
        Assertions.assertEquals("Санжаром Акпаралиевом", name.fullName(DeclinationProcessor.gcaseTvor));
        Assertions.assertEquals("Санжаре Акпаралиеве", name.fullName(DeclinationProcessor.gcasePred));
    }

    @Test
    public void surname_and_name_with_gender_converter() {
        // можно явно указать пол ('m' или 'f')
        Declination name = new Declination("Санжар", "Акпаралиев", "", "m");
        Assertions.assertEquals("Санжара Акпаралиева", name.fullName(DeclinationProcessor.gcaseRod));
        Assertions.assertEquals("Санжару Акпаралиеву", name.fullName(DeclinationProcessor.gcaseDat));
        Assertions.assertEquals("Санжара Акпаралиева", name.fullName(DeclinationProcessor.gcaseVin));
        Assertions.assertEquals("Санжаром Акпаралиевом", name.fullName(DeclinationProcessor.gcaseTvor));
        Assertions.assertEquals("Санжаре Акпаралиеве", name.fullName(DeclinationProcessor.gcasePred));
    }

    @Test
    public void kyzy_converter() {
        // можно явно указать кызы
        Declination name = new Declination("Курбанбек кызы Айжамал");
        Assertions.assertEquals("Курбанбек кызы   Айжамал", name.fullName(DeclinationProcessor.gcaseRod));
        Assertions.assertEquals("Курбанбек кызы   Айжамал", name.fullName(DeclinationProcessor.gcaseDat));
        Assertions.assertEquals("Курбанбек кызы   Айжамал", name.fullName(DeclinationProcessor.gcaseVin));
        Assertions.assertEquals("Курбанбек кызы   Айжамал", name.fullName(DeclinationProcessor.gcaseTvor));
        Assertions.assertEquals("Курбанбек кызы   Айжамал", name.fullName(DeclinationProcessor.gcasePred));
    }

    @Test
    public void uulu_converter() {
        // можно явно указать уулу
        Declination name = new Declination("Таалайбек уулу Санжар");
        Assertions.assertEquals("Таалайбек уулу   Санжара", name.fullName(DeclinationProcessor.gcaseRod));
        Assertions.assertEquals("Таалайбек уулу   Санжару", name.fullName(DeclinationProcessor.gcaseDat));
        Assertions.assertEquals("Таалайбек уулу   Санжара", name.fullName(DeclinationProcessor.gcaseVin));
        Assertions.assertEquals("Таалайбек уулу   Санжаром", name.fullName(DeclinationProcessor.gcaseTvor));
        Assertions.assertEquals("Таалайбек уулу   Санжаре", name.fullName(DeclinationProcessor.gcasePred));
    }
}
