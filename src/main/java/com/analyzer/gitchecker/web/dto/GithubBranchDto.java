package com.analyzer.gitchecker.web.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GithubBranchDto {
  @JsonProperty("name")
  private String name;
  private String commitSha;

  @JsonSetter("commit")
  public void setOwnerLogin(Map<String, String> commit) {
    this.commitSha = commit.get("sha");
  }

  @JsonGetter("commitSha")
  public String getOwnerLogin() {
    return commitSha;
  }
}