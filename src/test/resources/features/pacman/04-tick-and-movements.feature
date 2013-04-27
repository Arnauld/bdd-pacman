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

  Scenario: Speed denotes the number of ticks requires
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
    # Just cross the tile
    Then Pacman is located at column 4 and row 3
    When time ticks 30 times
    Then Pacman is located at column 4 and row 3
    When time ticks 1 time
    # Just cross the tile
    Then Pacman is located at column 5 and row 3


  Scenario: Once a move has been triggered,
            Pacman continue to move ahead at the given speed

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

  Scenario: Once Pacman ticks in one direction, it has to wait next tile to change direction

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
    When Pacman moves up
    When time ticks 14 times
    Then Pacman is located at column 4 and row 3
    When time ticks 30 times
    Then Pacman is still located at column 4 and row 3
    When time ticks 1 time
    Then Pacman is located at column 4 and row 2

  Scenario: Until next direction is triggered, Pacman can change direction at wish

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
    When time ticks 1 time
    When Pacman moves up
    When Pacman moves down
    When time ticks 1 times
    When Pacman moves right
    When time ticks 12 times
    Then Pacman next direction is right
    When Pacman moves left
    Then Pacman next direction is left
    Then Pacman is still located at column 3 and row 3
    # tile change there, direction change, won't affect current move
    When time ticks 1 times
    Then Pacman is located at column 4 and row 3
    Then Pacman next direction is left
    When Pacman moves up
    When time ticks 15 times
    Then Pacman next direction is up
    Then Pacman is still located at column 4 and row 3
    When time ticks 1 time
    Then Pacman is still located at column 4 and row 3
    But Pacman next direction is null
    And Pacman current direction is up
    When time ticks 15 times
    Then Pacman is located at column 4 and row 2
