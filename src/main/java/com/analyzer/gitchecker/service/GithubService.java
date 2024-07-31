package com.analyzer.gitchecker.service;

import com.analyzer.gitchecker.web.dto.GithubRepositoryDto;
import java.util.List;

public interface GithubService {

  List<GithubRepositoryDto> getRepositories(String username);
}
