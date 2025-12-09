package ch.zkb.gry.aoc2025.day02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Scanner;

public class Day2Part2 {

    private static final Logger logger = LoggerFactory.getLogger(Day2Part2.class);

    public static long compute(InputStream is) {
        String[] parts = new Scanner(is).nextLine().trim().split(",");
        long sum = 0;

        for (String part : parts) {
            part = part.trim();
            if (part.isEmpty()) continue;
            String[] range = part.split("-");
            if (range.length != 2) continue;
            try {
                long low = Long.parseLong(range[0]);
                long high = Long.parseLong(range[1]);
                if (low > high) continue;
                for (long n = low; n <= high; n++) {
                    if (isInvalid(n)) {
                        sum += n;
                    }
                }
            } catch (NumberFormatException e) {
                logger.warn("Invalid range format: {}", part);
            }
        }

        logger.info("Sum of invalid IDs (Part 2): {}", sum);
        return sum;
    }

    private static boolean isInvalid(long num) {
        String s = String.valueOf(num);
        int len = s.length();

        // No leading zero allowed in the number itself
        if (s.startsWith("0")) return false;

        // Try all possible repeat lengths k (length of the repeated block)
        // k from 1 to len/2
        for (int k = 1; k <= len / 2; k++) {
            if (len % k != 0) continue; // Must divide evenly for full repeats

            int repeats = len / k;
            if (repeats < 2) continue; // At least twice

            String block = s.substring(0, k);
            boolean matches = true;
            for (int i = 1; i < repeats; i++) {
                if (!s.substring(i * k, (i + 1) * k).equals(block)) {
                    matches = false;
                    break;
                }
            }
            if (matches) {
                return true;
            }
        }
        return false;
    }
}
