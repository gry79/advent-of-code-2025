package ch.zkb.gry.aoc2025.day2;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day2Test {

    @Test
    void part1() {
        try (FileInputStream fis = new FileInputStream("./src/test/resources/day2/input.txt")) {
            long count = Day2Part1.compute(fis);
            assertEquals(17077011375L, count);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void part2() {
        try (FileInputStream fis = new FileInputStream("./src/test/resources/day2/input.txt")) {
            long count = Day2Part2.compute(fis);
            assertEquals(36037497037L, count);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
