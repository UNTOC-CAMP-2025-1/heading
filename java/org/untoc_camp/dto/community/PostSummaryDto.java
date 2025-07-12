package org.untoc_camp.dto.community;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PostSummaryDto {
    private Long id;
    private String title;
    private String author;
    private LocalDateTime createdAt;
    private Long commentCount;
}
