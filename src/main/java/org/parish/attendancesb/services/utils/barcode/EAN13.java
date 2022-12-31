package org.parish.attendancesb.services.utils.barcode;

public class EAN13 {

    /**
     * @param codeIn code EAN only 12 digits
     * @return Code control.
     * @throws Exception codeIn is not 12 digits
     */
    private static int calculateControlCode(String codeIn) throws Exception {

        if (codeIn.length() != 12) {
            throw new Exception("codeIn length expected 12 obtained " + codeIn.length());
        }
        char[] digit = codeIn.toCharArray();
        int[] xPar = {1, 3};
        int sum = 0;

        for (int i = 0; i < digit.length; i++) {
            sum += Character.getNumericValue(digit[i]) * xPar[i % 2];
        }
        int aux = 10 - sum % 10;
        return (aux == 10) ? 0 : aux;
    }

    /**
     * @param codeIn
     * @return
     * @throws NumberFormatException codeIn invalid number format
     * @throws Exception             codeIn is not 12 digits
     */
    private static Long calculateEAN13(String codeIn) throws NumberFormatException, Exception {
        return Long.parseLong(codeIn + calculateControlCode(codeIn));
    }

    public static String get(String barcode) throws Exception {
        return barcode + calculateControlCode(barcode);
    }

    public static String get(int value){
        String code = getCode(value);
        try {
            return code + calculateControlCode(code);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getCode(int value){
        return repeatZeros(value) + value;
    }

    private static String repeatZeros(int value) {
        return "0".repeat(12 - String.valueOf(value).length());
    }

    public static void main(String[] args) throws Exception {
        EAN13 ean = new EAN13();
        System.out.println(ean.calculateEAN13("000000000001"));
        System.out.println(ean.calculateControlCode("000000000001"));
        System.out.println(ean.get("000000000001"));
        System.out.println(ean.get(1));
        System.out.println(ean.get(34));
        System.out.println(ean.get(444));
    }
}