/*
 * BinarySum.java
 *
 * Version:
 *     1
 *
 */

/**
 * This program is used to perform summation of two
 * binary inputs as strings and to convert summation of
 * binary to decimal and hex.
 *
 * @author  Ishika Prasad
 */

import  java.lang.Math;
public class BinarySum {

    /**
     * This method is used to calculate the summation of
     * two binary inputs.
     * @param binary1 first binary input
     * @param binary2 second binary input
     * @return summation of two binary inputs
     */
    public String binaryadd(int binary1, int binary2) {
        int sum = 0;
        int extra = 0;
        String sumarray = "";
        while((binary1 != 0) || (binary2 != 0)) {

            sum = (binary1 % 10) + (binary2 %10) + extra;
            extra = 0;
            if(sum > 1) {
                extra = 1;
                sum %= 2;
            }
            sumarray = sum + sumarray;
            binary1 /= 10;
            binary2 /= 10;
        }
        if(extra == 1) {
            sumarray = 1 + sumarray;
        }

        if((sumarray.length() % 8) != 0) {
            int prepend   =   8 - (sumarray.length()%8);
            for(int i = 0; i < prepend; i++) {
                sumarray = 0 + sumarray;
            }
        }
        System.out.print("Sum in  binary: ");
        sumarray = "b'" + sumarray + "'";
        System.out.println(sumarray);
        return sumarray;
    }

    /**
     * This method is to perform conversion to decimal
     * from binary
     * @param binSum summation of two binary
     * @return sum in decimal value
     */
    public int toDecimal(String binSum) {
        int decimalSum=0, count = 0;

        String bintemp = binSum.substring(2,binSum.length()-1);
        int len = bintemp.length();
        while(len!=0){
            if(bintemp.charAt(len-1) =='1') {
                decimalSum += Math.pow(2, count);
                }
                count++;
                len--;
            }
            System.out.println("Sum in decimal: "+ decimalSum);
        return decimalSum;
    }

    /**
     * This method is used to perform conversion to hex
     * from decimal
     * @param decimalSum summation of decimal values
     * @return sum in hex value
     */
    public String toHex(int decimalSum) {
        String hexSum="";
        int hexRem = 0;
        while(decimalSum!=0){
            hexRem = decimalSum%16;
            if(hexRem>9){
                hexSum = ((char)(64+hexRem-9))+ hexSum;
            }else {
                hexSum = hexRem + hexSum;
            }
            decimalSum = decimalSum/16;
        }
        hexSum = "0x"+hexSum;
        System.out.println("Sum in hex: "+ hexSum);
        return hexSum;
    }

    /**
     * The main program.
     * @param args command line input
     *             args[0] first binary number
     *             args[1] second binary number
     *
     */
    public static void main(String[] args) {
        if(args.length != 2) {
            System.out.println("Argument should be two binary strings");
        }
        int binary1 = Integer.parseInt(args[0]);
        int binary2 = Integer.parseInt(args[1]);
        BinarySum binarySum = new BinarySum();
        String toconvert = binarySum.binaryadd(binary1, binary2);
        int decimalSum = binarySum.toDecimal(toconvert);
        binarySum.toHex(decimalSum);
    }
}
