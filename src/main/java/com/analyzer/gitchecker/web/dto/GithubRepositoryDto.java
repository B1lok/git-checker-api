package com.analyzer.gitchecker.web.dto;

import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
public class GithubRepositoryDto {
  @JsonSetter("name")
  private String repositoryName;

  private boolean fork;

  @Getter
  @Setter
  private List<GithubBranchDto> branches;

  private String ownerLogin;

  @JsonSetter("owner")
  public void setOwnerLogin(Map<String, String> owner) {
    this.ownerLogin = owner.get("login");
  }

  @JsonGetter("ownerLogin")
  public String getOwnerLogin() {
    return ownerLogin;
  }

  @JsonGetter("repositoryName")
  public String getRepositoryName() {
    return repositoryName;
  }

  @JsonSetter("fork")
  public void setFork(boolean fork) {
    this.fork = fork;
  }

  @JsonIgnore
  public boolean isFork() {
    return fork;
  }
}