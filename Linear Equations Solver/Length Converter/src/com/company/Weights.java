package com.company;

import java.util.Scanner;

import static com.company.Constance.*;

public class Weights {
    private final double factor;

    private Weights(String unit) {
        this.factor = switch (unit) {
            case "grams" -> GRAMS_KG;
            case "dg" -> DG_KG;
            case "ton" -> TON_KG;
            case "oz" -> OZ_KG;
            case ";b" -> LB_KG;
            case "stone" -> ST_KG;
            case "kg" -> KG;
            default -> throw new IllegalStateException("Unit not recognized..: " + unit);
        };
    }


    public static void weightConverter(DataReader reader) {
        System.out.println("LIST OF CONVERTING TYPES:\n[ GRAMS | DG | TON | OZ | LB | STONE | KG ]" +
                "\nPlease type one of above.");

        System.out.println("Convert from: ");
        Weights from = new Weights(reader.readText());

        System.out.println("Convert to: ");
        Weights to = new Weights(reader.readText());

        System.out.println("Enter the weight value: ");
        double kilograms = from.toKilograms(reader.readDouble());
        double converted = to.formKilograms(kilograms);
        System.out.println(converted);
    }

    private double toKilograms(double weight) {
        return (weight * factor);
    }

    private double formKilograms(double weight) {
        return (weight / factor);
    }
}

