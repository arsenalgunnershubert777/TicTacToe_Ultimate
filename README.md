# TicTacToe
### Description
This is a tic tac toe GUI program that allows the user to play against another user or a computer, with timer options, and other featuers such as ultimate tic tac toe and smart AI.

### How to Compile and Run program (through Eclipse)
- First download the archive zip file that was submitted to this assignment
- Then open up Eclipse and click 'File' ... 'Import' ... from the main menu
- Then expand 'General', select 'Existing Projects into Workspace'
- Make sure that 'Select Archive File' is checked and browse for the ZIP file
- click 'Finish'
- After the project is opened up, you should be able to compile and run it 
  - Make sure the Java Compiler has the 1.8 compliance level, or it will not compile
  - To check for that, go to 'Project', then click 'Properties', go to 'Java Compiler', click 'Enable project specific settings', and set the 'compiler compliance level' to 1.8
  - Make sure Java 8 and the JavaFX plugin are installed beforehand
- To run the program, go to the course.oop.application package and run the Main.java program


### How to play the TicTacToe game
- When the program runs, the first screen that will pop up is the menu screen, which has options to quit game or play game or clear player records
- If the play game button is clicked, the screen moves to the settings screen, where you input information to play a tic tac toe game
  - In order to play against a computer, the number of players text box has to be 1, the player One Name and Marker should be inputted, the order vs computer should be inputted (1 for player first and 2 for computer first), and the timer should be inputted (in seconds, where 0 means no timer)
  - In order to play two players against each other, the number of players text box has to be 2, the player One and player Two Name and Marker should be inputted, and the timer should be inputted
  - The type of game can be selected from a dropdown such as:
    - NxN Tic Tac Toe (normal 3x3 or any NxN)
    - Ultimate Tic Tac Toe (meta Tic Tac Toe with 9 3x3 squares)
    - Territory Tic Tac Toe (2 neutral and 2 dead squares on 5x5)
  - Additional features can be added such as:
    - Random N'th move for every type of game
    - Easy, Normal, Hard difficulty AI for Normal/NxN, and Territory games
  - After new players are created, they can be selected from the dropdown menus Player One Select and Player Two Select
  - Emojis for the markers can be selected from the dropdown at the bottom right and the buttons to its left will determine which Player to place them for
  - The Player records are also displayed in the settings screen, which can be cleared in the menu screen 
  - The return menu button will take you back the menu, while the start game button will start a game with the provided input boxes
  - Please also do not use the same marker for both players
- If the start game button is clicked, the screen moves to the game screen, where the tic tac toe board is displayed
  - To play, whoever is going first will click on the tile they want to place, and the tile will change to indicate the marker
  - There is a status text that will display Game on! until one player or the computer wins, and then there will be a sound and animation
  - If the timer is on, and time runs out for a player, the other player wins automatically
  - You can return to the menu, play again with the same settings, or play with different settings from the game screen

### Link for Youtube Video Demo
- http://youtu.be/JAIwIZamTls?hd=1

#### Creator Contact
- If there are any issues, or clarifications needed, for the compile, run, or play of this game, please contact me at hubert777@ufl.edu, or 407-765-8806
- Thanks and enjoy!
