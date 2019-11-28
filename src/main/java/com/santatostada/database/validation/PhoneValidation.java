package com.santatostada.database.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneValidation {
    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN =
            "^(\\s*)?(\\+)?([- _():=+]?\\d[- _():=+]?){10,14}(\\s*)?$";

    public PhoneValidation() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    public boolean validate(final String phoneNumber) {
        matcher = pattern.matcher(phoneNumber);

        return matcher.matches();
    }
}
