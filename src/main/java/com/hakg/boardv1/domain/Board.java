package com.hakg.boardv1.domain;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Board {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private int viewCount;
}
