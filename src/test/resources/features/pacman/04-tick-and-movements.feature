Feature: Be able to control time and animate creatures

  Scenario: Speed denotes denotes the number of ticks requires
            to go from the middle of one tile to the next one

    Given the following working board
   #1234567890123456789
    """
    ####################
    #..................#
    #.<................#
    #..................#
    ####################
    """
    And the time is frozen
    And Pacman speed is 30
    When Pacman moves right
    Then Pacman is still located at column 3 and row 3
    When time ticks 1 time
    Then Pacman is still located at column 3 and row 3
    When time ticks 13 times
    Then Pacman is still located at column 3 and row 3
    When time ticks 1 time
    Then Pacman is located at column 4 and row 3

  Scenario: Speed denotes denotes the number of ticks requires
            to traverse a tile side to side

    Given the following working board
    #1234567890123456789
    """
    ####################
    #..................#
    #.<................#
    #..................#
    ####################
    """
    And the time is frozen
    And Pacman speed is 30
    When Pacman moves right
    When time ticks 14 times
    Then Pacman is still located at column 3 and row 3
    When time ticks 1 time
    Then Pacman is located at column 4 and row 3
    When time ticks 30 times
    Then Pacman is located at column 4 and row 3
    When time ticks 1 time
    Then Pacman is located at column 5 and row 3


  Scenario: Once a move has been triggered,
            Pacman continue to move right ahead at the given speed

    Given the following working board
    #1234567890123456789
    """
    ####################
    #..................#
    #.<................#
    #..................#
    ####################
    """
    And the time is frozen
    And Pacman speed is 30
    When Pacman moves right
    Then Pacman is still located at column 3 and row 3
    When time ticks 30 times
    Then Pacman is located at column 4 and row 3
    When time ticks 30 times
    Then Pacman is located at column 5 and row 3

