package com.test.lifung.demo.entity;

import com.test.lifung.demo.common.utils.CommonUtils;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ToDo implements Serializable {
    /** to-do id. */
    private String id;

    /** user id. */
    private String userId;

    /** Title. */
    private String title;

    /** Description. */
    private String description;

    /** Start time. */
    private LocalDateTime startTime;
}
