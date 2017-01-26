package ProgQue.Practice;

import java.util.Arrays;

/**
 * Author: Ajit Ku. Sahoo
 * Date: 10/18/2016.
 *
 * GoDaddy hacker rank question
 */
public class SubSequencesOfString { //check out SubsequencesLC78 - a concise solution

    private String[] getSubSequences(String str) {
        if (str.isEmpty()) {
            return new String[0];
        }

        int strLength = str.length();
        int limit = (int) Math.pow(2, strLength);
        String[] seq = new String[limit-1];

        for (int i = 1; i < limit; i++) {
            String binary = Integer.toBinaryString(i);
            int noOfZeroesToPad = strLength - binary.length(); //no. of zeroes to pad at left to make 'binary' of equal length as of 'str'
            binary = getPrefixOfZeroes(noOfZeroesToPad) + binary;
            seq[i-1] = getSubSeq(str, binary).toString();
        }

        Arrays.sort(seq); //need output in sorting order
        return seq;
    }

    private StringBuilder getSubSeq(String str, String binary) {
        StringBuilder builder = new StringBuilder();
        for (int j = 0; j < str.length(); j++) {
            if (binary.charAt(j) == '1') { //if bit is 1, pick the character from S at that index
                builder.append(str.charAt(j));
            }
        }
        return builder;
    }

    private String getPrefixOfZeroes(int noOfZeroes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < noOfZeroes; i++)
            sb.append('0');
        return sb.toString();
    }
    public static void main(String[] args) {
        int count = 0;
        for (String str : new SubSequencesOfString().getSubSequences("HackerRank")) {
            System.out.println(++count + " " + str);
        }
    }
}
