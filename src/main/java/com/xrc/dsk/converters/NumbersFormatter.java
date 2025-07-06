package com.xrc.dsk.converters;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumbersFormatter {
    public static BigDecimal formatWithPrecision(Number value, int precision) {
        BigDecimal decimal;
        if (value instanceof BigDecimal) {
            decimal = (BigDecimal) value;
        } else {
            decimal = new BigDecimal(value.toString());
        }
        return decimal.setScale(precision, RoundingMode.HALF_UP);
    }
}
