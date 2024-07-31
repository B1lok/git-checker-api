package com.analyzer.gitchecker.web;

import com.analyzer.gitchecker.exceptions.GithubBranchException;
import com.analyzer.gitchecker.exceptions.GithubClientException;
import com.analyzer.gitchecker.exceptions.GithubRepositoryNotFoundException;
import com.analyzer.gitchecker.web.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionTranslator {

  @ExceptionHandler({GithubRepositoryNotFoundException.class,
      GithubBranchException.class})
  public ResponseEntity<ExceptionResponse> handleNotFound(RuntimeException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(exceptionResponse(exception.getMessage(), 404));
  }

  @ExceptionHandler({GithubClientException.class})
  public ResponseEntity<ExceptionResponse> handleGithubClientError(RuntimeException exception) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(exceptionResponse(exception.getMessage(), 500));
  }

  private ExceptionResponse exceptionResponse(String message, int status) {
    return new ExceptionResponse(status, message);
  }
}