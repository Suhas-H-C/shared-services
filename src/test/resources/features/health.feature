@health
Feature: Health check API Triggered

  Scenario: Check status and body of health-check API
    When Health check API is triggered "/health/health-check"
    And status is 200
    Then response is "Health Check success"

