# Country Data Summary Generator

This Java program is designed to generate a summary of country data based on user input and display the top 10 countries with the maximum internet and calling usage. It accomplishes the following main tasks:

1. **User Input:** It takes a country code as input from the user.

2. **Data Display:** It retrieves and displays data associated with the given country code from a text file.

3. **Top 10 Rankings:** It identifies and prints the top 10 countries with the highest internet usage and the top 10 countries with the most calling usage.

## Object-Oriented Programming Concepts

This program incorporates several Object-Oriented Programming (OOP) concepts to create a well-structured and efficient solution:

### Method Overloading

- Two methods with the same name but different parameters are implemented to handle different data types and logic.

### Exception Handling

- Exception handling using `try-catch` blocks and the `throws` keyword ensures that the program gracefully handles errors and exceptions.

### Constructor

- A parameterized constructor is created to initialize objects with specific data.

### ArrayList

- ArrayLists are used for dynamic array implementation, making it easier to manage and manipulate data.

### HashSet

- HashSet is employed to keep track of repeated items and ensure data integrity.

### File Handling

- File handling is utilized to read data from a text file, providing a source of country-related information.

### Comparator Interface

- The Comparator interface is implemented to find and sort countries based on their internet and calling usage in descending order.

### Basic OOP Concepts

- The program utilizes fundamental OOP concepts such as classes, objects, methods, and interfaces.
- It demonstrates the use of the `static` keyword where appropriate.

## Usage

To use this program, follow these steps:

1. Clone this repository to your local machine.

2. Compile the Java source code using a Java compiler.

3. Run the compiled program.

4. Enter a country code when prompted.

5. The program will display the data associated with the entered country code and the top 10 countries with maximum internet and calling usage.

## License

This project is licensed under the [MIT License](LICENSE).

## Acknowledgments

- Special thanks to the Object-Oriented Programming concepts and the Java programming language for making this project possible.
