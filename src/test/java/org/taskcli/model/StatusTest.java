package org.taskcli.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StatusTest {

    @Test
    void testEnumValues() {
        assertEquals("Ready", Status.READY.getValue());
        assertEquals(0, Status.READY.getId());

        assertEquals("In progress", Status.IN_PROGRESS.getValue());
        assertEquals(1, Status.IN_PROGRESS.getId());

        assertEquals("Done", Status.DONE.getValue());
        assertEquals(2, Status.DONE.getId());
    }

    @Test
    void testGetters() {
        Status ready = Status.READY;
        assertEquals("Ready", ready.getValue());
        assertEquals(0, ready.getId());

        Status inProgress = Status.IN_PROGRESS;
        assertEquals("In progress", inProgress.getValue());
        assertEquals(1, inProgress.getId());

        Status done = Status.DONE;
        assertEquals("Done", done.getValue());
        assertEquals(2, done.getId());
    }

    @Test
    void testEnumCannotBeInstantiatedWithInvalidValues() {
        assertThrows(IllegalArgumentException.class, () -> {
            // This is a placeholder for the actual code that should throw an exception
            // when trying to instantiate an enum with invalid values. Since enums in Java
            // cannot be instantiated directly, this test should focus on ensuring that
            // any factory method or similar mechanism that might allow invalid values
            // throws an exception. For the purpose of this example, we'll assume there's
            // a method `fromId` that throws an exception for invalid IDs.
            Status.fromId(-1);
        });
    }
}