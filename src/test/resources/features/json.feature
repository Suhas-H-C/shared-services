@jsonFetch
  Feature: Check Json Content

    Scenario Outline: Successfully process JSON get request
      Given User triggers "/file/fetch-json" with following "<stringPath>"
      Then Content is not empty
    Examples:
      | stringPath |
      | ipData   |