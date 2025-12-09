package ch.zkb.gry.aoc2025.day03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.math.BigInteger;

public class Day3Part2 {

    private static final Logger logger = LoggerFactory.getLogger(Day3Part2.class);
    private static final int DIGITS_NEEDED = 12;

    public static BigInteger compute(InputStream is) {
        BigInteger total = BigInteger.ZERO;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                int len = line.length();
                if (len < DIGITS_NEEDED) {
                    logger.debug("Bank too short for 12 digits: {}", line);
                    continue;
                }

                BigInteger bankValue = getBankValue(len, line);
                logger.debug("Bank: {} -> max 12-digit joltage: {}", line, bankValue);
                total = total.add(bankValue);
            }
        } catch (IOException e) {
            logger.error("Error reading input stream", e);
        }

        logger.info("Total output joltage (Part 2): {}", total);
        return total;
    }

    private static BigInteger getBankValue(int len, String line) {
        int toRemove = len - DIGITS_NEEDED;
        StringBuilder stack = new StringBuilder();

        for (char ch : line.toCharArray()) {
            int digit = ch - '0';
            while (stack.length() > 0 && toRemove > 0 && digit > (stack.charAt(stack.length() - 1) - '0')) {
                stack.deleteCharAt(stack.length() - 1);
                toRemove--;
            }
            stack.append(ch);
        }

        // Remove any remaining from the end
        while (toRemove > 0) {
            stack.deleteCharAt(stack.length() - 1);
            toRemove--;
        }

        return new BigInteger(stack.toString());
    }
}
