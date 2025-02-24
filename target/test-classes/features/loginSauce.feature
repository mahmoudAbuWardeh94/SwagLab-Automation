Feature: Login to the website

  Scenario Outline: Login with invalid credentials
    Given the user navigates to the login page
    When the user enters invalid username "<username>" and password "<password>"
    Then the error message should be shown "<errorMessage>"

    Examples:
      | username        | password      | errorMessage                              |
      | wrong_user      | secret_sauce  | Epic sadface: Username and password do not match any user in this service |
      | standard_user   | wrong_pass    | Epic sadface: Username and password do not match any user in this service|
      | locked_out_user | secret_sauce  | Epic sadface: Sorry, this user has been locked out. |
      
      
  Scenario: User logs in with valid credentials then Successful checkout process
    Given the user navigates to the login page
    When the user enters valid username and password
      
    Then the user should be logged in successfully
    When the user can add the most expensive two products
    And the user proceeds to checkout
    And the user fills in the checkout form and continues

    Then the user should be on the Overview page
    And the total amount before taxes should be verified
    And the URL should match the expected checkout URL

    When the user clicks on the Finish button
    Then the Thank You and Order Dispatched messages should be displayed