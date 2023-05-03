Feature: PetStoreAPI POST Pet

@Pet
  Scenario Outline: Requests are sent to post pet ep and the returned responses are checked
    Given send request to post pet ep "<test>"
    Then check pet ep response message
    Examples:
      | test |
      | test2 |