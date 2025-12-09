package ch.zkb.gry.aoc2025.day01;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day1Test {

    @Test
    void part1() {
        try (FileInputStream fis = new FileInputStream("./src/test/resources/day01/input.txt")) {
            long count = Day1Part1.computePassword(fis);
            assertEquals(1100, count);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void part2() {
        try (FileInputStream fis = new FileInputStream("./src/test/resources/day01/input.txt")) {
            long count = Day1Part2.computePassword(fis);
            assertEquals(6358, count);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
