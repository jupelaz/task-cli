package org.taskcli.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status{
    READY("Ready",0),
    IN_PROGRESS("In progress", 1),
    DONE("Done", 2);

    private final String value;
    private final int id;

    public static Status fromId(int i) {
        return switch (i) {
            case 0 -> READY;
            case 1 -> IN_PROGRESS;
            case 2 -> DONE;
            default -> throw new IllegalArgumentException();
        };
    }
}
