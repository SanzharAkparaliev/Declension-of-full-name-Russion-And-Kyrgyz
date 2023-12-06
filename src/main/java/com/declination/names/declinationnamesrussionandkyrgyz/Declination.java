package com.declination.names.declinationnamesrussionandkyrgyz;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Declination {
    private static final String SEX_M = "m";
    private static final String SEX_F = "f";
    private static final String SUFFIX_UULU = "уулу";
    private static final String SUFFIX_KYZY = "кызы";
    private  String lastName;
    private  String firstName;
    private  String middleName;
    private  String sex;
    private  boolean fullNameSurnameLast;

    public Declination(final String fullName) {
        Matcher matcher = Pattern.compile("^\\s*(\\S+)(\\s+(\\S+)(\\s+(\\S+))?)?\\s*$").matcher(fullName);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Cannot parse supplied name");
        }

        if (hasSurnameLastFormat(matcher)) {
            processSurnameLastFormat(matcher);
        } else if (hasUuLuFormat(matcher)) {
            processUuLuFormat(matcher);
        } else if (hasKyzFormat(matcher)) {
            processKyzFormat(matcher);
        } else {
            processDefaultFormat(matcher);
        }
    }

    private boolean hasSurnameLastFormat(Matcher matcher) {
        return matcher.group(5) != null && matcher.group(3).matches(".*(ич|на)$") && !matcher.group(5).matches(".*(ич|на)$");
    }

    private void processSurnameLastFormat(Matcher matcher) {
        this.lastName = matcher.group(5);
        this.firstName = matcher.group(1);
        this.middleName = matcher.group(3);
        this.fullNameSurnameLast = true;
        this.sex = getSex();
    }

    private boolean hasUuLuFormat(Matcher matcher) {
        return matcher.group(3).equals(SUFFIX_UULU);
    }

    private void processUuLuFormat(Matcher matcher) {
        this.firstName = matcher.group(2).replace(SUFFIX_UULU, "");
        this.lastName = matcher.group(1) + " " + SUFFIX_UULU;
        this.middleName = "";
        this.fullNameSurnameLast = false;
        this.sex = SEX_M;
    }

    private boolean hasKyzFormat(Matcher matcher) {
        return matcher.group(3).equals(SUFFIX_KYZY);
    }

    private void processKyzFormat(Matcher matcher) {
        this.firstName = matcher.group(2).replace(SUFFIX_KYZY, "");
        this.lastName = matcher.group(1) + " " + SUFFIX_KYZY;
        this.middleName = "";
        this.fullNameSurnameLast = false;
        this.sex = SEX_F;
    }

    private void processDefaultFormat(Matcher matcher) {
        this.lastName = matcher.group(1);
        this.firstName = matcher.group(3);
        this.middleName = matcher.group(5);
        this.fullNameSurnameLast = false;
        this.sex = getSex();
    }



    public Declination(final String lastName, final String firstName, final String middleName, final String sex) {
        this.lastName = lastName;
        this.firstName = (firstName == null) ? "" : firstName;
        this.middleName = (middleName == null) ? "" : middleName;
        this.fullNameSurnameLast = false;
        this.sex = (sex == null) ? getSex() : sex;
    }

    private String getSex() {
        if (this.middleName != null && this.middleName.length() > 2) {
            if ("ич".equals(this.middleName.substring(this.middleName.length() - 2))) {
                return SEX_M;
            } else if ("на".equals(this.middleName.substring(this.middleName.length() - 2))) {
                return SEX_F;
            }
        }
        return "";
    }

    public String fullName(String gcase) {
        String result = (fullNameSurnameLast ? "" : lastName(gcase) + " ")
                + firstName(gcase) + " " + middleName(gcase);
        if (fullNameSurnameLast) {
            result += " " + lastName(gcase);
        }
        return result.trim();
    }

    public String lastName(String gcase) {
        return DeclinationProcessor.word(lastName, sex, "lastName", gcase);
    }

    public String firstName(String gcase) {
        return DeclinationProcessor.word(firstName, sex, "firstName", gcase);
    }

    public String middleName(String gcase) {
        return DeclinationProcessor.word(middleName, sex, "middleName", gcase);
    }
}
