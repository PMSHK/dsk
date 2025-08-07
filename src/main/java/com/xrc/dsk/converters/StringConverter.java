package com.xrc.dsk.converters;

public class StringConverter {
    public static double convertToNumber(String str) {
        if (str == null || str.isEmpty()) {
            return 0.0;
        }
        int start = -1;
        int end = -1;
        boolean hasDot = false;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (Character.isDigit(c)) {
                if (start == -1) start = i;
                end = i;
            } else if (c == '-' && start == -1) {
                start = i; // Разрешаем минус в начале
            } else if (c == '.' && !hasDot && start != -1) {
                hasDot = true; // Разрешаем одну точку
            } else if (start != -1) {
                break; // Завершаем при недопустимом символе после числа
            }
        }

        if (start == -1) {
            return 0.0;
        }

        try {
            return Double.parseDouble(str.substring(start, end + 1));
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public static double extractNumber(String str) {
        if (str == null || str.isBlank()) {
            return 0.0;
        }
        StringBuilder builder = new StringBuilder();
        boolean hasDot = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (Character.isDigit(c)) {
                builder.append(c);
            } else if ((c == '.' || c == ',') && !hasDot) {
                builder.append('.');
                hasDot = true;
            }
        }
        return !builder.isEmpty() ? Double.parseDouble(builder.toString()) : 0.0;
    }

    public static String extractLetters(String str) {
        if (str == null || str.isBlank()) {return "";}
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isLetter(c)) {
                builder.append(c);
            }
        }
        return builder.toString();
    }
}
