package com.santatostada.database.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidation {
    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN =
            "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$";

    public EmailValidation() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    public boolean validate(final String email) {
        matcher = pattern.matcher(email);

        return matcher.matches();
    }
}
