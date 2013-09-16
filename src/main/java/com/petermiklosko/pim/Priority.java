package com.petermiklosko.pim;

import java.util.ArrayList;
import java.util.List;

public enum Priority {
    LOW(91, 99),
    MEDIUM(11, 90),
    HIGH(1, 10);

    private final int rangeStart;
    private final int rangeEnd;

    private Priority(int rangeStart, int rangeEnd) {
        this.rangeStart = rangeStart;
        this.rangeEnd = rangeEnd;
    }

    private static final List<Priority> PRIORITIES = new ArrayList<Priority>() {{
        add(Priority.HIGH);
        add(Priority.MEDIUM);
        add(Priority.LOW);
    }};

    public static Priority priorityOf(int value) {
        for (Priority priority : PRIORITIES) {
            if (priority.rangeStart <= value && value <= priority.rangeEnd) {
                return priority;
            }
        }
        //TODO refactor since this will never happen
        return Priority.LOW;
    }
}
