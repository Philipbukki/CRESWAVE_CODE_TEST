package com.pbukki.creswave.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@AllArgsConstructor(staticName = "build")
@Data
public class CommentResponseDto {
    private List<CommentDto> content;
    private long pageNo;
    private long pageSize;
    private long totalElements;
    private long totalPages;
    private boolean last;

}
