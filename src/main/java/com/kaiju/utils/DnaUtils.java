package com.kaiju.utils;



public class DnaUtils {

    private static final String REX_DIGITS = "\\D+";


    public static int calculateSum(String sample) {
        String onlyNumbers = sample.replaceAll(REX_DIGITS,"");
        int length = onlyNumbers.length();
        int max = -1;
        for (int i = 0; i< length;i++) {
            if ((i + 3) <= length ) {
                int totalSubset = 0;
                int to = i +3;
                for (int j = i;j<to;j++) {
                    int currentNumber = Character.getNumericValue(onlyNumbers.charAt(j));
                    totalSubset += currentNumber;
                }
                if (totalSubset > max) {
                    max = totalSubset;
                }
            }
        }
        return max;
    }


}
