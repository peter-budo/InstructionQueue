package com.petermiklosko.pim;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class InstructionQueueShould {

    private final InstructionMessage lowPriority = new InstructionMessage(99, 99, 99, 99, 9999);
    private final InstructionMessage mediumPriority = new InstructionMessage(55, 55, 55, 55, 5555);
    private final InstructionMessage highPriority = new InstructionMessage(1, 1, 1, 1, 1111);
    private InstructionQueue queue;

    @Before
    public void setUp() throws Exception {
        queue = new InstructionQueue();
        queue.addMessage(lowPriority);
        queue.addMessage(mediumPriority);
        queue.addMessage(highPriority);
    }

    @After
    public void tearDown() throws Exception {
        queue.shutDown();
    }

    @Test
    public void shouldSuccessfullyAdd() {
        assertEquals(3, queue.size());
    }

    @Test
    public void shouldRemoveMessageOfTheQueue() {
        int sizeAtStart = queue.size();
        queue.remove(lowPriority);
        assertTrue(sizeAtStart > queue.size());
    }

    @Test
    public void shouldRetrieveFromHeadWithoutRemoving() {
        int sizeAtStart = queue.size();
        queue.getMessage();
        assertTrue(sizeAtStart == queue.size());
    }
}