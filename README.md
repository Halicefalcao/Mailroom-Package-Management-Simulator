Mailroom Package Management Simulator

Welcome to the Irving Mailroom Package Management Simulator! This project simulates the package handling system used at the Irving Mailroom, where packages are organized into stacks based on their recipient's name. The goal is to help the mailroom efficiently manage package deliveries and track them using stacks.

Problem Overview
The Irving Mailroom manages packages using 5 stacks based on the recipient's name:

Stack 1: For recipients whose names start with A-G
Stack 2: For recipients whose names start with H-J
Stack 3: For recipients whose names start with K-M
Stack 4: For recipients whose names start with N-R
Stack 5: For recipients whose names start with S-Z
Floor Stack: A special stack with unlimited capacity where misplaced or extra packages are temporarily stored.
Each stack has a maximum capacity of 7 packages, and the system handles operations like adding packages, finding packages, moving packages between stacks, and more. The simulation is built to help the mailroom workers identify where to store, retrieve, and move packages with ease.


Key Features:
Package Management: Simulates adding, retrieving, and moving packages across different stacks.
Dynamic Stacks: The system includes a special Floor stack, which can store any number of packages.
Handling Overflows: Packages are added to the nearest non-full stack if one is full.
Automated Daily Cleanup: Automatically removes packages that are older than 5 days when the day is advanced.
Menu Options: A menu-driven interface allows the user to interact with the system using various commands.
Exception Handling: Custom exceptions ensure that errors are gracefully handled, and the system continues to run smoothly.

Features & Commands
D - Deliver Package
Add a new package to the appropriate stack based on the recipient's name.

G - Get Packages for a User
Retrieve the topmost package from a recipient’s stack.

T - Make it Tomorrow
Advance the day, removing packages older than 5 days.

P - Print the Stacks
Print a summary of the contents of all the stacks.

M - Move a Package from One Stack to Another
Move a package between two stacks.

F - Find Packages in the Wrong Stack and Move to Floor
Move packages that are in the wrong stack to the Floor stack.

L - List All the Packages Awaiting a User
List all packages that are awaiting a specific user in the mailroom.

E - Empty the Floor
Empty the Floor stack, effectively deleting all packages.

Q - Quit
Exit the simulation.


Project Classes:
1. Package Class
    Represents a package in the mailroom. Contains details such as the recipient, arrival date, and weight of the package.
    Constructor: public Package(String recipient, int arrivalDate, double weight)
    Member Variables: recipient, arrivalDate, weight
    Methods: Getters and setters for all member variables.
2. PackageStack Class
    Simulates a stack to hold packages. Implements basic stack operations such as push, pop, peek, and isFull.
    Methods: push(Package x), pop(), peek(), isFull(), isEmpty()
3. MailroomManager Class
   Driver class to manage the mailroom. Handles user input and simulates the operations performed in the mailroom. Implements a menu-driven interface for interacting with the system.
4. Exceptions
   FullStackException: Thrown when a stack reaches its maximum capacity (7 packages).
   EmptyStackException: Thrown when trying to pop from an empty stack.

Installation and Setup:
1.Clone the repository:git clone https://github.com/yourusername/Irving-Mailroom-Package-Management-Simulator.git
2.Navigate to the project directory:cd Irving-Mailroom-Package-Management-Simulator
3.Compile and run the MailRoomManager Class to start the program


