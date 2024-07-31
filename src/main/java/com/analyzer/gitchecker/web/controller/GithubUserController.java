package com.analyzer.gitchecker.web.controller;


import com.analyzer.gitchecker.service.GithubService;
import com.analyzer.gitchecker.web.dto.GithubRepositoryDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/github/users")
@RequiredArgsConstructor
@Tag(name = "Github user controller")
public class GithubUserController {
  private final GithubService githubService;

  @GetMapping("/repositories/{username}")
  @Operation(summary = "Get repositories by Github username", responses = {
      @ApiResponse(responseCode = "200"),
      @ApiResponse(responseCode = "404", content = @Content)
  })
  public ResponseEntity<List<GithubRepositoryDto>> getRepositoriesByUsername(@PathVariable String username,
                                                                             @RequestHeader("Accept") String acceptHeader) {
    return ResponseEntity.ok().body(githubService.getRepositories(username));
  }
}
