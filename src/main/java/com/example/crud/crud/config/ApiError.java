package com.example.crud.crud.config;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ApiError {
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
   private LocalDateTime timestamp;
   private Integer code;
   private String status;
   private List<String> errors;
}
