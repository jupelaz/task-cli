package org.taskcli.model;

import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@Builder
public class Task {
    @Positive(message = "Id must be positive")
    private int id;
    private String description;
    @NonNull
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static class TaskBuilder {
        public Task build() {
            if (id < 0) {
                throw new IllegalArgumentException("Id must be positive");
            }
            return new Task(id, description, status, createdAt, updatedAt);
        }
    }
}

