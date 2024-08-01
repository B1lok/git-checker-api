package com.analyzer.gitchecker.integration;

import com.analyzer.gitchecker.config.WebClientConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class GithubUserControllerIntegrationTest {
  @Autowired
  private MockMvc mockMvc;

  private final String url = "/api/v1/github/users";

  @Autowired
  private WebClientConfig config;

  @Test
  public void testGetUser() throws Exception {
    var existingName = "B1lok";
    var resultActions = mockMvc.perform(MockMvcRequestBuilders.get(url + "/repositories/{username}", existingName)
            .accept(MediaType.APPLICATION_JSON));

    resultActions.andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].ownerLogin").value(existingName));
  }

  @Test
  public void testGetUserNotFound() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get(url + "/repositories/{username}", "34fd2134fs")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  }
}