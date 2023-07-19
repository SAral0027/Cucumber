Feature: GIT Requests

  Background: 
    Given Hit the baseURI

  @GETUSER
  Scenario Outline: Get User Info from GIT
    When User make GET request for user info with "<endpoint>"
    Then User should verify statuscode 200 and response body "<login>" value

    Examples: 
      | endpoint        | login     |
      | users/SAral0027 | SAral0027 |

  @GETREPO
  Scenario Outline: Get Repo Info from GIT
    When User make GET request for repo info with "<endpoint>"
    Then User should verify statuscode 200 and response body contains "<name>" value

    Examples: 
      | endpoint              | name       |
      | users/SAral0027/repos | BenchStudy |

  @CREATEREPO
  Scenario Outline: Create New Repo in GIT
    Given Add the required headers and create body
    When User make POST request to create new repo with "<endpoint>"
    Then User should verify response body "<name>" and statuscode 201

    Examples: 
      | endpoint   | name |
      | user/repos | REST |

  @UPDATEREPO
  Scenario Outline: Update Existing Repo in GIT
    Given Add the required headers and update body
    When User make PATCH request to update existing repo with "<endpoint>"
    Then User should verify response body conatins "<name>" and statuscode 200

    Examples: 
      | endpoint             | name        |
      | repos/SAral0027/REST | RESTAssured |

  @DELETEREPO
  Scenario Outline: Delete Existing Repo in GIT
    Given Add the required headers
    When User make DELETE request to delete existing repo with "<endpoint>"
    Then User should verify statuscode 204

    Examples: 
      | endpoint                    |
      | repos/SAral0027/RESTAssured |
