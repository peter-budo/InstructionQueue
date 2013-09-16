package com.petermiklosko.pim;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;

public class InstructionQueue {
    private final int QUEUE_MAXIMUM_SIZE = Integer.MAX_VALUE;
    private final BlockingQueue<InstructionMessage> queue = new PriorityBlockingQueue<InstructionMessage>();
    private boolean isStopped = false;
    private Semaphore semaphore = new Semaphore(QUEUE_MAXIMUM_SIZE);

    public boolean addMessage(InstructionMessage message) {
        synchronized (this) {
            if (isStopped) {
                return false;
            }
            if (!semaphore.tryAcquire()) {
                throw new Error("Too many threads");
            }
        }
        try {
            return queue.offer(message);
        } finally {
            semaphore.release();
        }
    }

    public boolean remove(InstructionMessage message) {
        return queue.remove(message);
    }

    public InstructionMessage getMessage() {
        return queue.peek();
    }

    private boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }

    public Collection<InstructionMessage> shutDown() {
        synchronized (this) {
            isStopped = true;
        }
        semaphore.acquireUninterruptibly(QUEUE_MAXIMUM_SIZE);
        Set<InstructionMessage> messageSet = new HashSet<InstructionMessage>();
        queue.drainTo(messageSet);
        return messageSet;
    }
}