Feature: Beautify a board from sharp based text

  #
  # ┘└ ┐┌ │─ □
  #

  Scenario: An empty board

    Given the following board
    """
    ####################
    #..................#
    #..................#
    ####################
    """
    When one beautifies it
    Then the resulting board must be:
    """
    ┌──────────────────┐
    │..................│
    │..................│
    └──────────────────┘
    """

  Scenario: An broken corner board

      Given the following board
      """
         #################
         #...............#
      ####...............#
      #..................#
      ####################
      """
      When one beautifies it
      Then the resulting board must be:
      """
         ┌───────────────┐
         │...............│
      ┌──┘...............│
      │..................│
      └──────────────────┘
      """

  Scenario: The more complex but empty board

    Given the following board
    """
    ############################
    #            ##            #
    # #### ##### ## ##### ######
    # #  # #   # ## #   # #
    # #### ##### ## ##### ######
    #                          #
    ############################
    """
    When one beautifies it
    Then the resulting board must be:
    """
    ┌────────────┐┌────────────┐
    │            ││            │
    │ ┌──┐ ┌───┐ ││ ┌───┐ ┌────┘
    │ │  │ │   │ ││ │   │ │
    │ └──┘ └───┘ └┘ └───┘ └────┐
    │                          │
    └──────────────────────────┘
    """
