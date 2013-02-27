Feature: Whenever a ghost and Pacman are in the same tile: only one can survive!

  Scenario: Ghost eat Pacman

    Given the following working board
    #1234567890123456789
    """
       #################
    ####...............#
    #...P<.............#
    ####################
    """
    When Pinky moves right
    Then Pinky is located at column 6 and row 3
    And Pacman is dead


  Scenario: Pacman dies if it goes on a Ghost

    Given the following working board
    #1234567890123456789
    """
       #################
    ####...............#
    #...P<.............#
    ####################
    """
    When Pacman moves left
    Then Pinky is still located at column 5 and row 3
    And Pacman is dead

  Scenario: Pacman "under Pacgum" is the strongest!

    Given the following working board
    #1234567890123456789
    """
       #################
    ####...............#
    #...P<.............#
    ####################
    """
    And Pacman eat a Pacgum
    When Pacman moves left
    Then Pacman is located at column 5 and row 3
    And Pinky is dead


