package ch.zkb.gry.aoc2025.day3;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day3Test {

    @Test
    void part1() {
        try (FileInputStream fis = new FileInputStream("./src/test/resources/day3/input.txt")) {
            long count = Day3Part1.compute(fis);
            assertEquals(17445, count);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void part2() {
        try (FileInputStream fis = new FileInputStream("./src/test/resources/day3/input.txt")) {
            BigInteger count = Day3Part2.compute(fis);
            assertEquals(new BigInteger("173229689350551"), count);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
