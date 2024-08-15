package com.maids.libraryTask.basis;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@JsonInclude(value= JsonInclude.Include.NON_EMPTY, content= JsonInclude.Include.NON_NULL)
public class PageableResponse<T> extends BaseResponse<T>{
    
    private Integer currentPage;
    private Long totalItems;
    private Integer totalPages;
    private Integer currentItems;

}
