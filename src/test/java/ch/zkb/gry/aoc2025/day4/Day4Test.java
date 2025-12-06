package ch.zkb.gry.aoc2025.day4;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day4Test {

    @Test
    void part1() {
        try (FileInputStream fis = new FileInputStream("./src/test/resources/day4/input.txt")) {
            int count = Day4Part1.compute(fis);
            assertEquals(1493, count);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void part2() {
        try (FileInputStream fis = new FileInputStream("./src/test/resources/day4/input.txt")) {
            int count = Day4Part2.compute(fis);
            assertEquals(9194, count);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
