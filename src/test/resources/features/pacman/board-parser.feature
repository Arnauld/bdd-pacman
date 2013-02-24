Feature: Be able to read a board from text

  Scenario: An empty board with food

    Given the following board
   #01234567890123456789
    """
    ####################
    #..................#
    #..................#
    ####################
    """
    When one loads it
    Then the board size must be 20x4
    And the cell at column 1 and row 1 is a wall
    And the cell at column 20 and row 1 is a wall
    And the cell at column 20 and row 4 is a wall
    But the cell at column 2 and row 2 is a food


  Scenario: An broken corner board

    Given the following board
    """
       #################
       #...............#
    ####...............#
    #..................#
    ####################
    """
    When one loads it
    Then the board size must be 20x5
    And the cell at column 1 and row 1 is a not wall
    And the cell at column 4 and row 1 is a wall
    And the cell at column 4 and row 2 is a wall
    But the cell at column 5 and row 2 is a food


  Scenario: An broken corner board

    Given the following board
   #1234567890123456789
    """
       #################
       #...........P...#
    ####..<...o.....C..#
    #..................#
    ####################
    """
    When one loads it
    Then the board size must be 20x5
    And the cell at column 1 and row 1 is a not wall
    And the cell at column 4 and row 1 is a wall
    And the cell at column 4 and row 2 is a wall
    But the cell at column 5 and row 2 is a food
    And the cell at column 11 and row 3 is a pacgum
    And Pacman is located at column 7 and row 3
    And Pinky is located at column 16 and row 2
    And Clyde is located at column 17 and row 3

