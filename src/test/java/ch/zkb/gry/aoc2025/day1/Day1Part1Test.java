package ch.zkb.gry.aoc2025.day1;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day1Part1Test {

    @Test
    void test() {
        try (FileInputStream fis = new FileInputStream("./src/test/resources/day1/input.txt")) {
            long count = Day1Part1.computePassword(fis);
            assertEquals(1100, count);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
