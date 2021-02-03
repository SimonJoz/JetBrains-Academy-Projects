package com.company;

import static com.company.Constance.*;

public class Length {
    private final double factor;

    private Length(String unit) {
        this.factor = switch (unit) {
            case "yard" -> YARD_TO_METER;
            case "foot" -> FOOT_TO_METER;
            case "inch" -> INCH_TO_METER;
            case "km" -> KM_TO_METER;
            case "meter" -> METER;
            case "cm" -> CM_TO_METERS;
            case "mm" -> MM_TO_METERS;
            case "mile" -> MILE_TO_METER;
            default -> throw new IllegalStateException("Unit not recognized..: " + unit);
        };
    }

    public static void lengthConverter(DataReader reader) {
        System.out.println("List of converting types:\n[ MM | CM | METER " +
                "| KM | INCH | FOOT | YARD | MILE ]\nPlease choose one of above.\nConvert from: ");
        Length from = new Length(reader.readText());

        System.out.println("Convert to: ");
        Length to = new Length(reader.readText());

        System.out.println("Enter value: ");
        double meter = from.toMeters(reader.readDouble());
        System.out.printf("Result is: %.2f\n", to.fromMeters(meter));

    }


    private double toMeters(double measurement) {
        return (measurement * factor);
    }

    private double fromMeters(double measurement) {
        return (measurement / factor);
    }
}
