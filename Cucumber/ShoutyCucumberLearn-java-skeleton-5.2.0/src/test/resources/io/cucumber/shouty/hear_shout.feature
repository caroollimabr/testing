Feature: Hear shout
  Scenario: Listener is within range
    Given Lucy is located/standing 15 meter(s) from Sean
    When Sean shouts "free bagels at Sean's"
    Then Lucy hears Sean's message

  Scenario: Listener hears a different message
    Given Lucy is located 15 meters from Sean
    When Sean shouts "Free coffee!"
    Then Lucy hears Sean's message