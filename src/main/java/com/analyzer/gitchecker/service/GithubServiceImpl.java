package com.analyzer.gitchecker.service;

import com.analyzer.gitchecker.exceptions.GithubBranchException;
import com.analyzer.gitchecker.exceptions.GithubClientException;
import com.analyzer.gitchecker.exceptions.GithubRepositoryNotFoundException;
import com.analyzer.gitchecker.web.dto.GithubBranchDto;
import com.analyzer.gitchecker.web.dto.GithubRepositoryDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GithubServiceImpl implements GithubService {

  private final WebClient webClient;

  @Override
  public List<GithubRepositoryDto> getRepositories(String username) {
    return webClient.get()
        .uri("/users/{username}/repos", username)
        .retrieve()
        .onStatus(
            HttpStatusCode::is4xxClientError,
            response -> Mono.error(new GithubRepositoryNotFoundException("Repositories not found for user: " + username))
        )
        .onStatus(
            HttpStatusCode::is5xxServerError,
            response -> Mono.error(new GithubClientException("Server error while fetching repositories for user: " + username))
        )
        .bodyToFlux(GithubRepositoryDto.class)
        .filter(repo -> !repo.isFork())
        .flatMap(this::getBranchesForRepository)
        .collectList()
        .block();
  }

  private Mono<GithubRepositoryDto> getBranchesForRepository(GithubRepositoryDto repo) {
    return webClient.get()
        .uri("https://api.github.com/repos/{owner}/{repo}/branches", repo.getOwnerLogin(), repo.getRepositoryName())
        .retrieve()
        .onStatus(
            HttpStatusCode::is4xxClientError,
            response -> Mono.error(new GithubBranchException("Branches not found for repository: " + repo.getRepositoryName()))
        )
        .onStatus(
            HttpStatusCode::is5xxServerError,
            response -> Mono.error(new GithubClientException("Server error while fetching branches for repository: " + repo.getRepositoryName()))
        )
        .bodyToFlux(GithubBranchDto.class)
        .collectList()
        .map(branches -> {
          repo.setBranches(branches);
          return repo;
        });
  }
}
