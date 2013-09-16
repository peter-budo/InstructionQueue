package com.petermiklosko.pim;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InstructionMessageTest {

    @Test
    public void shouldThrowExceptionWhenOutOfRangeValueSubmitted() {
        try {
            new InstructionMessage(-1, -1, -1, -1, -1);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Expected instructionType in range 0 < instructionType < 100");
        }
    }

    @Test
    public void shouldResultInEqualPriorities() {
        InstructionMessage message = new InstructionMessage(2, 2, 2, 2, 2);
        InstructionMessage mockedMessage = mock(InstructionMessage.class);
        when(mockedMessage.getPriority()).thenReturn(Priority.HIGH);
        assertEquals(0, message.compareTo(mockedMessage));
    }

    @Test
    public void shouldResultInLesserPriority() {
        InstructionMessage message = new InstructionMessage(91, 91, 91, 91, 91);
        InstructionMessage mockedMessage = mock(InstructionMessage.class);
        when(mockedMessage.getPriority()).thenReturn(Priority.MEDIUM);
        assertEquals(-1, message.compareTo(mockedMessage));
    }

    @Test
    public void shouldResultInGreaterPriority() {
        InstructionMessage message = new InstructionMessage(1, 1, 1, 1, 1111);
        InstructionMessage mockedMessage = mock(InstructionMessage.class);
        when(mockedMessage.getPriority()).thenReturn(Priority.MEDIUM);
        assertEquals(1, message.compareTo(mockedMessage));
    }
}
