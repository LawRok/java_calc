import java.util.Scanner;

public class Main {
    static Scanner in = new Scanner(System.in);
    static int first, second;
    static double result;
    static String[] numbers;
    static String myString;
    static boolean flag = false;
//    static String[] roman = {"X", "IX", "VIII", "VII", "VI", "V", "IV", "III", "II", "I"};
//    static int[] arab = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };


    public static void main(String[] args) {
        while (true) {
            myString = in.nextLine().toUpperCase();
            numbers = myString.split(" ");
            if (numbers.length > 3) {
                throw new IllegalArgumentException("Only an expression in the format \"a +/-* b\"");
            } else if (numbers.length < 2) {
                throw new IllegalArgumentException("The string is not a mathematical operation ");
            }
            try {
                first = Integer.parseInt(numbers[0]);
                second = Integer.parseInt(numbers[2]);
            } catch (IllegalArgumentException e) {
                try {
                    first = toArabic(numbers[0]);
                    second = toArabic(numbers[2]);
                    flag = true;
                } catch ( IllegalArgumentException el) {
                    throw new IllegalArgumentException("Only the Roman or Arabic numbering system");
                }
            }
            if (first < 1 || first > 10 || second > 10 || second < 1) {
                throw new IllegalArgumentException("Only the numbers 1 from 10.");
            }
            if (flag) {
                if (!((int)Double.parseDouble(calc(myString)) < 1)) {
                    System.out.println(toRoman((int)Double.parseDouble(calc(myString))));
                } else {
                    throw new IllegalArgumentException("There are no negative numbers in the Roman numeral system");
                }
            } else {
                System.out.println(calc(myString));
            }
            flag = false;
        }
    }
    private static int toArabic (String input) {
        return switch (input) {
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII" -> 8;
            case "IX" -> 9;
            case "X" -> 10;
            default -> -1;
        };
    }
//    private static String toRoman(int n) {
//        String[] romanNumerals = { "M",  "CM", "D", "CD", "C", "XC", "L",  "X", "IX", "V", "I" };
//        int[] romanNumeralNums = {  1000, 900, 500,  400 , 100,  90,  50,   10,    9,   5,   1 };
//        String finalRomanNum = "";
//
//        for (int i = 0; i < romanNumeralNums.length; i ++) {
//            int currentNum = n /romanNumeralNums[i];
//            if (currentNum==0) {
//                continue;
//            }
//
//            for (int j = 0; j < currentNum; j++) {
//                finalRomanNum += romanNumerals[i];
//            }
//
//            n = n%romanNumeralNums[i];
//        }
//        return finalRomanNum;
//    }
    private static String toRoman(int n) {
        return "I".repeat(n)
                .replace("IIIII", "V")
                .replace("IIII", "IV")
                .replace("VV", "X")
                .replace("VIV", "IX")
                .replace("XXXXX", "L")
                .replace("XXXX", "XL")
                .replace("LL", "C");
    }
    public static String calc(String str) {
        if (str.indexOf( '+' ) > 0) {
            result = first + second;
        } else if (str.indexOf( '-' ) > 0) {
            result = first - second;
        } else if (str.indexOf( '*' ) > 0) {
            result = first * second;
        } else if (str.indexOf( '/' ) > 0) {
            try {
                result = first / second;
            } catch (ArithmeticException e) {
                System.out.println("Divided by zero operation cannot possible.");
            }
        } else {
            throw new IllegalArgumentException("Invalid operation.");
        }
        return Double.toString(result);
    }
}