Feature: Be able to move a protagonist on the board

  Scenario: A simple move

    Given the following working board
   #1234567890123456789
    """
       #################
    ####...............#
    #....<.............#
    ####################
    """
    When Pacman moves left
    Then Pacman is located at column 5 and row 3


  Scenario: A move on the wall

    Given the following working board
   #1234567890123456789
    """
       #################
    ####<..............#
    #..................#
    ####################
    """
    When Pacman moves left
    Then Pacman is still located at column 5 and row 2
    When Pacman moves up
    Then Pacman is still located at column 5 and row 2
