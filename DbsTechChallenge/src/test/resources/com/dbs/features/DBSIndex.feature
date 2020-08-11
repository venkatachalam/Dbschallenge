Feature: DBS Application functionality
  This is used to run the functionality against DBS Application

  @AllTestCases
  Scenario: Acceptance Criteria 1 to validate the count of award links
    Given I launch DBS application for entity
    When I click on the Learn More Button
    And I click on "Singapore" country in the left panel
    And I read and write the Meals businesses
    And I click on "About" link in the header tabs
    And I click on "Who We Are" in the sub tab
    And I click on "Our Awards & Accolades" link
    Then I should see 22 award links in the page

  @AllTestCases
  Scenario Outline: Acceptance Criteria 1 to validate the values of awards name and caption of the award
    Given I launch DBS application for entity
    When I click on the Learn More Button
    And I click on "About" link in the header tabs
    And I click on "Who We Are" in the sub tab
    And I click on "Our Awards & Accolades" link
    Then I should see "<Award Name>" as award name and "<Caption of the award>" as caption of the award is matched with table

    Examples: 
      | Award Name     | Caption of the award                                    |
      | A World First  | Euromoney                                               |
      | The Banker     | Bank of the Year 2018                                   |
      | Global Finance | Best Bank in the World 2018                             |
      | Euromoney      | Awards for Excellence                                   |
      | Global Finance | World's Best Banks                                      |
      | Global Finance | World's Best Investment Banks and Derivatives Providers |
