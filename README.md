# tic-tac-toe
Simple tic-tac-toe game running in terminal

This is a simple console game developed as a part of a Java Developer course in HyperSkill academy (JetBrains).

It can be played in 3 modes:
- user vs computer
- user vs user
- computer vs computer

"Computer" player (or rather AI) has 3 levels: easy, medium and hard.
Easy - just makes a move on a random available spot.
Medium - analyzes if there is a winning spot or a potential winning spot of the second player and chooses that one if it exists. Otherwise makes a random move.
Hard - analyzes the whole game ahead for each move and chooses the one that will lead to victory.

In order to invoke the game print "start" then indicate the players:
- "user" - if you'd like to insert moves manually
- "easy" - for computer AI of easy level
- "medium" - for computer AI of medium level
- "hard" - for computer AI of hard level

Example: start user hard
(Play a game against AI in hard mode)

The layout of the game board is the following (provided by the task specification in the project):

13 23 33
12 22 32
11 21 31

All the implementation and structure of the project is mine.
