@fields
Feature: Check fields data

  Scenario: Check fields data for IpData.java
    When user makes API request "/str/fetch-fields"
    Then response is good
