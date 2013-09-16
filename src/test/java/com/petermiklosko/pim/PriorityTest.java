package com.petermiklosko.pim;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PriorityTest {

    @Test
    public void shouldReturnHighPriority() {
        int value = 2;
        Priority actual = Priority.priorityOf(value);
        Priority expected = Priority.HIGH;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnMediumPriority() {
        int value = 12;
        Priority actual = Priority.priorityOf(value);
        Priority expected = Priority.MEDIUM;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnLowPriority() {
        int value = 92;
        Priority actual = Priority.priorityOf(value);
        Priority expected = Priority.LOW;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSucceedOnAllBorderValues() {
        //HIGH range
        assertEquals(Priority.HIGH, Priority.priorityOf(1));
        assertEquals(Priority.HIGH, Priority.priorityOf(10));

        //MEDIUM range
        assertEquals(Priority.MEDIUM, Priority.priorityOf(11));
        assertEquals(Priority.MEDIUM, Priority.priorityOf(90));

        //LOW range
        assertEquals(Priority.LOW, Priority.priorityOf(91));
        assertEquals(Priority.LOW, Priority.priorityOf(99));
    }
}
