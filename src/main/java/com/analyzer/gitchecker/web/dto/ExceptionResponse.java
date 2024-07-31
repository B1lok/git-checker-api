package com.analyzer.gitchecker.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionResponse {
  private int status;
  private String message;
}
