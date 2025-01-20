# Linsolve
# Simplex Method Solver - Java Maven Project

## Overview

This project is a Java-based implementation of the simplex method for solving linear programming problems. It supports both single-phase and double-phase simplex approaches, providing flexibility for various problem types. The project is designed to process and solve problems in a structured manner, ensuring clarity and accuracy.

## Features

- Scanning and parsing of linear programming problems.
- Lexical validation of the input.
- Conversion of validated input into an algebraic model.
- Selection and execution of the appropriate simplex method (single-phase or double-phase).
- Computation of results and termination.
- Comprehensive test bench for validation and verification.

## Prerequisites

- Java Development Kit (JDK) 8 or higher.
- Apache Maven 3.6 or higher.
- Eclipse IDE (optional but recommended for development).

## Project Structure

The project follows a modular structure:

1. **Scanning Problem**: Reads and interprets the linear programming problem.
2. **Lexical Validation**: Validates the input format for correctness.
3. **Model Conversion**: Converts input into an algebraic representation suitable for processing.
4. **Dispatching Solver**: Determines whether the problem should use single-phase or double-phase simplex.
5. **Simplex Execution**: Executes the simplex algorithm based on the problem type.
6. **Result Computation**: Computes and presents the results.
7. **Termination**: Finalizes the process.
8. **Test Bench**: Provides tests to validate the implementation.

## Build and Run Instructions

To build and execute tests for this project, use the following commands:

1. **Clean and Build the Project**:

   ```bash
   mvn clean install
   ```

2. **Generate Surefire Test Report**:

   ```bash
   mvn surefire-report:report
   ```

3. **Run Tests and Generate Site Report**:

   ```bash
   mvn clean test site
   ```

## Usage

1. Clone the repository to your local machine.
2. Import the project into Eclipse IDE (optional).
3. Use Maven to build and test the project as described above.
4. Run the main class to execute the simplex solver.

## Testing

The project includes a robust test bench to ensure correctness and reliability. Tests cover all major functionalities, including:

- Input validation.
- Model conversion.
- Single-phase and double-phase simplex execution.
- Result computation and termination.

## Future Enhancements

This is the first version of the simplex method solver. Future improvements may include:

- Support for additional optimization techniques.
- Enhanced input handling and user interfaces.
- Performance optimizations for large-scale problems.
- Integration with other mathematical libraries.

## Contributions

Contributions are welcome! Please feel free to fork the repository and submit pull requests with enhancements or bug fixes.

## License

This project is licensed under the MIT License. See the LICENSE file for more details. For commercial use cases, please contact the project maintainers to discuss terms and permissions.

## Acknowledgments

Thanks to all contributors and the open-source community for supporting this project.

