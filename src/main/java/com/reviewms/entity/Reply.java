package com.reviewms.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Reply {
	private String replyId = UUID.randomUUID().toString();
    private String userId;
    private String comment;
    private LocalDateTime timestamp;
}
