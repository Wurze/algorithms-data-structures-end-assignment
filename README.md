# Algorithms data-structures end-assignment
This Java application processes a dataset containing information about books, such as title, author, price, and publication year. The application provides a graphical user interface (GUI) to execute various algorithms on the dataset and display the results and duration of the algorithms' execution.
## Features

The application supports the following algorithms:

* Algorithm 1: Sort by title → sortItems: Sorts the books in alphabetical order by their titles.
* Algorithm 2: Search book by title → searchItemByCriteria: Searches for a book in the dataset using its title.
* Algorithm 3: Sort by natural order → sortItemsByNaturalOrder: Sorts the books in the dataset based on their natural order.
* Algorithm 4: Search books by author → searchItemsByFieldValue: Searches for all books written by a specific author in the dataset.

The application uses three different data structures (LinkedListDS, ArrayListDS, and TreeMapDS) to store the dataset, allowing users to compare the performance of the algorithms for different data structures.

The application also provides basic manipulation methods for the dataset:

* Retrieve a book by its index in the dataset.
* Add a new book to the dataset.
* Delete a book by its index in the dataset.
* Update a book at a specified index with a new book.

## Getting Started
### Prerequisites

* Java Development Kit (JDK) 8 or later
* Integrated Development Environment (IDE), such as IntelliJ IDEA or Eclipse

### Installation

1. Clone the repository to your local machine.

```bash
git clone https://github.com/yourusername/book-dataset-processor.git
```
2. Open the project in your IDE.

3. Build the project and run the Main.java class to launch the GUI.

### Usage

4. Load Books Data: Click the "Load Books Data" button to load the dataset into the application.
5. Algorithm Selection: Select a data structure and algorithm to execute from the drop-down menus.
6. Execute Algorithm: Click the "Execute Algorithm" button to run the selected algorithm on the dataset.
7. Results: The results and duration of the algorithm's execution will be displayed in the output area.

## Dataset

The books.csv file contains the dataset used in this application. Each line in the file represents a book, with the following comma-separated fields:

* Title
* Author
* Rating (0-5 scale)
* Publication date (DD/MM/YYYY format)


## License

MIT
