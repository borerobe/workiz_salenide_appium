package constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JobStatus {
    SUBMITTED("Submitted"),
    IN_PROGRESS("In Progress"),
    CANCELLED("Canceled"),
    DONE("Done"),
    PENDING("Pending"),
    DONE_PENDING_APPROVAL("Done Pending Approval");

    private final String value;

    @Override
    public String toString() {
        return value;
    }
}
