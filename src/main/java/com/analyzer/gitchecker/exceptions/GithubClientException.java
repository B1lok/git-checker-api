package com.analyzer.gitchecker.exceptions;

public class GithubClientException extends RuntimeException{
  public GithubClientException(String message) {
    super(message);
  }
}
