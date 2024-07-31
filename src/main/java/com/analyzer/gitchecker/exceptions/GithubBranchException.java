package com.analyzer.gitchecker.exceptions;

public class GithubBranchException extends RuntimeException{
  public GithubBranchException(String message) {
    super(message);
  }
}
