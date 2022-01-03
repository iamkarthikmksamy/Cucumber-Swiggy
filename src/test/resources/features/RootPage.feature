Feature: Swiggy Application Root Page features

Scenario: Validate various root page features of Swiggy application
    Given user is on root page of swiggy application
    When user tries to see the title of the page
    Then page title should be "Order food online from India's best food delivery service. Order from restaurants near you"
    When user tries to see the login and sign in link
    Then login and sign in link should be visible with the text "Login" and "Sign up"
    When user tries to see the delivery location text box & find food button
    Then delivery location text box should be visible with placed holder value "Enter your delivery location"
    And find food button should be visible with the text "FIND FOOD"
    When user tries to see the information of popular <cities> in india
    Then system should be showing the links with below city name
      | Ahmedabad |
      | Bangalore |
      | Chennai   |
      | Delhi     |
      | Gurgaon   |
      | Hyderabad |
      | Kolkata   |
      | Mumbai    |
      | Pune      |
    When user enters the location "Kumbakonam" in the search text box
    Then system should list the possible locations as a drop down list
    When user chooses the desired location "Kumbakonam Temples, Kanmani Devi Nagar, Kumbakonam, Tamil Nadu, India" from the drop down list
    Then system should navigate the user to restaurants page with the url "https://www.swiggy.com/restaurants"