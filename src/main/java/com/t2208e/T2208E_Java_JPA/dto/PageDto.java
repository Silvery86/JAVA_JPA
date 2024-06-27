package com.t2208e.T2208E_Java_JPA.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageDto {
    @SuppressWarnings("rawtypes")
    private List content;
    private long totalElements;
    private int number;
    private int numberOfElements;
    private int totalPages;
}
