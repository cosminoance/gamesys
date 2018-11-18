Feature: Testing some API calls while
  Stalking on JackpotJoy's youtube feed

  Scenario: JackpotJoy everything should be ok 200
    Given the endpoint https://www.youtube.com
    When I GET application/xml /feeds/videos.xml?user=jackpotjoy
    Then I should receive response code 200
    Then I should print entries as string
    And save response as file for youtube.com/feeds/videos/user-jackpotjoy

  Scenario: Wrong query parameter, expect 400
    Given the endpoint https://www.youtube.com
    When I GET application/xml /feeds/videos.xml?users=jackpotjoy
    Then I should receive response code 400

  Scenario: Wrong parameter, expect 404
    Given the endpoint https://www.youtube.com
    When I GET application/xml /feeds/videos.xml?user=jackpot12312joy
    Then I should receive response code 404

  Scenario: Wrong author, expect The Batman
    Given the endpoint https://www.youtube.com
    When I GET application/xml /feeds/videos.xml?user=jackpotjoy
    Then I should have at least 1 author as The Batman
    
    Scenario: Wrong author, expect Jackpotjoy
    Given the endpoint https://www.youtube.com
    When I GET application/xml /feeds/videos.xml?user=jackpotjoy
    Then I should have at least 15 author as Jackpotjoy
