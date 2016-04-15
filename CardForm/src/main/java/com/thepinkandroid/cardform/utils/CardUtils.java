package com.thepinkandroid.cardform.utils;

/**
 * Static helper methods for dealing with cards.
 */
public class CardUtils
{
    /**
     * Performs the Luhn check on the given card number.
     *
     * @param cardNumber a String consisting of numeric digits (only).
     * @return {@code true} if the sequence passes the checksum
     * @throws IllegalArgumentException if {@code cardNumber} contained a non-digit (where {@link
     *                                  Character#isDefined(char)} is {@code false}).
     * @see <a href="http://en.wikipedia.org/wiki/Luhn_algorithm">Luhn Algorithm (Wikipedia)</a>
     */
    public static boolean isLuhnValid(String cardNumber)
    {
        final String reversed = new StringBuffer(cardNumber).reverse().toString();
        final int len = reversed.length();
        int oddSum = 0;
        int evenSum = 0;
        for (int i = 0; i < len; i++)
        {
            final char c = reversed.charAt(i);
            if (!Character.isDigit(c))
            {
                throw new IllegalArgumentException(String.format("Not a digit: '%s'", c));
            }
            final int digit = Character.digit(c, 10);
            if (i % 2 == 0)
            {
                oddSum += digit;
            }
            else
            {
                evenSum += digit / 5 + (2 * digit) % 10;
            }
        }
        return (oddSum + evenSum) % 10 == 0;
    }

    public static boolean isIsracardValid(String cardNumber)
    {
        int sum = 0;
        int len = cardNumber.length();

        // If the number is less than 9 digits
        if (len == 8)
        {
            cardNumber = "0" + cardNumber;
            len++;
        }

        for (int i = 0; i < len; i++)
        {
            final char c = cardNumber.charAt(i);
            if (!Character.isDigit(c))
            {
                throw new IllegalArgumentException(String.format("Not a digit: '%s'", c));
            }

            final int digit = Character.digit(c, 10);
            sum = sum + (digit * (9 - i));
        }
        return sum % 11 == 0;
    }

}
