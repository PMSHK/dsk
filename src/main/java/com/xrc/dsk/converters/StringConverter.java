package com.xrc.dsk.converters;

public class StringConverter {
    public static double convertToNumber(String str) {
        if (str == null || str.isEmpty()) {
            return 0.0;
        }
//        int start = 0;
//        int end = 0;
//        StringBuilder builder = new StringBuilder(str);
//        for (int i = 0; i < builder.length(); i++) {
//            if (builder.charAt(i) == '.') {
//                continue;
//            }
//            if (!Character.isDigit(builder.charAt(i)) && i <= end) {
//                start++;
//                end = start;
//            } else {
//                end++;
//            }
//        }
//        if (!Character.isDigit(builder.charAt(end))) {
//            end--;
//        }
//        if (start == end && start == builder.length()) {
//            return 0.0;
//        }
//        return Double.parseDouble(builder.substring(start, end));
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
}
