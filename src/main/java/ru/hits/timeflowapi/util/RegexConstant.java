package ru.hits.timeflowapi.util;

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

}
