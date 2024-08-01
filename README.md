
# Git Checker API

Git Checker API is a service that lists all non-fork GitHub repositories for a given user and provides information about the branches and their latest commits. It also handles cases where the user does not exist, returning a 404 response with a custom message.

## Features

- List all non-fork repositories for a given GitHub username.
- Provide repository name, owner login, branch names, and the last commit SHA for each branch.
- Handle non-existent GitHub users with a 404 response.

## Technologies Used

- Java 21
- Spring Boot 3
- GitHub REST API v3

## Installation

1. Clone the repository:
   ```
   git clone https://github.com/B1lok/git-checker-api.git
   cd git-checker-api
   ```

2. Build the project using Maven:
   ```
   mvn clean install
   ```

3. Run the application:
   ```
   mvn spring-boot:run
   ```

## Usage

### List Non-Fork Repositories

To list all non-fork repositories for a given user, make a GET request to the following endpoint:

```http
GET /api/v1/github/users/repositories/{username}
Accept: application/json
```

#### Response

```
[
  {
    "repositoryName": "example-repo",
    "ownerLogin": "username",
    "branches": [
      {
        "name": "main",
        "commitSha": "abc123def456..."
      },
      {
        "name": "feature-branch",
        "commitSha": "789xyz..."
      }
    ]
  },
  ...
]
```

### Handle Non-Existent GitHub Users

If the GitHub user does not exist, a 404 response is returned with the following format:

#### Response

```
{
  "status": 404,
  "message": "User not found"
}
```

## API Documentation

The API documentation is available at [GitHub REST API v3](https://developer.github.com/v3).
