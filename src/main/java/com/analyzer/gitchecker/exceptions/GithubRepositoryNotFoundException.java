package com.analyzer.gitchecker.exceptions;

public class GithubRepositoryNotFoundException extends RuntimeException {
  public GithubRepositoryNotFoundException(String message) {
    super(message);
  }
}
