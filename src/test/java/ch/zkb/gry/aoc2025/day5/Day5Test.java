package ch.zkb.gry.aoc2025.day5;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day5Test {

    @Test
    void part1() {
        try (FileInputStream fis = new FileInputStream("./src/test/resources/day5/input.txt")) {
            long count = Day5Part1.compute(fis);
            assertEquals(607, count);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void part2() {
        try (FileInputStream fis = new FileInputStream("./src/test/resources/day5/input.txt")) {
            long count = Day5Part2.compute(fis);
            assertEquals(342433357244012L, count);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
