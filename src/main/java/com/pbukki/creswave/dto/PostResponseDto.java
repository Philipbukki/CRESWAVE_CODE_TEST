package com.pbukki.creswave.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor(staticName = "build")
public class PostResponseDto {

    private List<PostDto> content;
    private long pageNo;
    private long pageSize;
    private long totalElements;
    private long totalPages;
    private boolean last;
}
