package ru.hits.timeflowapi.util;

public class RegexConstant {

    public static final String FULL_NAME_REGEX =
            "^[А-ЯЁ][\\p{IsCyrillic}IV]*[-'\\p{IsCyrillic}., IV]*\\"
                    + "(?[\\p{IsCyrillic}IV][-'\\p{IsCyrillic}., IV]*\\)?[-'\\p{IsCyrillic}., IV]*$";

}
