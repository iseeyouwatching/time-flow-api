package ru.hits.timeflowapi.util.constants;

import lombok.experimental.UtilityClass;

/**
 * Вспомогательный класс, который содержит в себе константы регулярных выражений.
 */
@UtilityClass
public class RegexConstant {

    /**
     * Регулярное выражение, предназначенное для проверки корректности введенных фамилии, имени и отчества.
     */
    public static final String FULL_NAME_REGEX =
            "^[А-ЯЁ][\\p{IsCyrillic}IV]*[-'\\p{IsCyrillic}., IV]*\\"
                    + "(?[\\p{IsCyrillic}IV][-'\\p{IsCyrillic}., IV]*\\)?[-'\\p{IsCyrillic}., IV]*$";

    public static final String POST_ROLE_REGEX = "ROLE_[A-Z]+";

    public static final String POST_NAME_REGEX = "[А-ЯЁ][а-яё\\s]*";

}
