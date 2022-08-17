package com.blz.employeepayroll.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DepartmentDTO {
    private Long id;
    private String departmentName;
    private String departmentDesc;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
