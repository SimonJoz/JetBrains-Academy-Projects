# Flashcard App
When learning a new language, it can be hard to remember all the new vocabulary, 
which is exactly where flashcards can help. 
Typically, flashcards show a hint (a task, or a picture) on one side, and the right answer on the other.
This simple CLI application emulates a set of flashcards in form -- term and its definition.
Program gives the term and ask for a definition and then check if answer is correct.
Application supports a few functions that are described below. 


# Description of function:
  Supported actions:
   - add: Add a card.
   - remove: Remove a card.
   - import: Load cards from the file.
   - export: Save cards to file as a serialized map(Map<String, Definition>)
   - ask: Ask for a definition of some random cards.
   - exit: Exit the program.
   - log: Saves the application log to the given file.
   - hardest card: Prints the term of the card that has the most mistakes.
   - reset stats: Erases the mistake count for all cards.

# Runtime arguments:
  Program may take two arguments at runtime:
   - "-export" followed by <yourFileName.txt> : save all the cards that are in the program memory into
        a passed file after the user has entered exit.
   - "-import" followed by <yourFileName.txt> : import cards from the passed file into program
        memory at the start up.
   
# Example of usage:
   - java Flashcards -import <fileName.txt>
   - java Flashcards -export <fileName.txt>
   
   - java Flashcards -import <fileName.txt> -export <fileName.txt>
   - java Flashcards -export <fileName.txt> -import <fileName.txt>
   
# Important:
    !!! The import file must contain a serialized map(Map<String, Definition>) to be successfully imported into program memory !!!
    
# Note
Project has been created based on strict specification from JetBrains Academy.

More about project specification may be found at: https://hyperskill.org/projects/44:

