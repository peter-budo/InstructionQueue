package com.petermiklosko.pim;

import com.google.common.base.Objects;

import static com.google.common.base.Preconditions.checkArgument;

public class InstructionMessage implements Comparable<InstructionMessage> {

    private final int instructionType;
    private final int productCode;
    private final int quantity;
    private final int uom;
    private final int timeStamp;
    private final Priority priority;

    public InstructionMessage(int instructionType, int productCode, int quantity, int uom, int timeStamp) {
        checkArgument(0 < instructionType && instructionType < 100, "Expected instructionType in range 0 < instructionType < 100");
        checkArgument(0 < productCode, "Expected productCode greater then 0");
        checkArgument(0 < quantity, "expected quantity greater then 0");
        checkArgument(0 <= uom && uom < 256, "Expected uom in range 0 <= uom < 256");
        checkArgument(0 < timeStamp, "Expected timeStamp greater then 0");

        this.instructionType = instructionType;
        this.productCode = productCode;
        this.quantity = quantity;
        this.uom = uom;
        this.timeStamp = timeStamp;
        this.priority = Priority.priorityOf(instructionType);
    }

    public Priority getPriority() {
        return priority;
    }

    @Override
    public int compareTo(InstructionMessage message) {
        return priority.compareTo(message.getPriority());
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof InstructionMessage) {
            InstructionMessage that = (InstructionMessage) o;
            return Objects.equal(this.instructionType, that.instructionType)
                    && Objects.equal(this.productCode, that.productCode)
                    && Objects.equal(this.quantity, that.quantity)
                    && Objects.equal(this.uom, that.uom)
                    && Objects.equal(this.timeStamp, that.timeStamp);
        }
        return false;
    }
}
