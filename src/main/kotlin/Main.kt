import objects.Customer
import java.io.*
import java.text.NumberFormat
import java.util.*

// Mehraneh Hamedani - 30062786 - AT2 - Activity1 - CustomerSortAndSearch
fun main(args: Array<String>) {
    // Define an ArrayList to collect the customer objects
    val customerList = ArrayList<Customer>()
    customerList.add(Customer(1, "David Jones", "david@email.com", "1234567890"))
    customerList.add(Customer(2, "Millan Brown", "millan@gmail.com", "3345678901"))
    customerList.add(Customer(3, "Sarah Dalton", "sarah@email.com", "3446789012"))
    customerList.add(Customer(4, "Joee Zack", "joee@example.com", "6667890123"))
    customerList.add(Customer(5, "Nikkie Lee", "nikkie@gmail.com", "5778901234"))

    // Show unsorted customers
    println("Unsorted Customer List:")
    customerList.forEach {
        println(it)
    }
    // Define all options and display them to users.
    var option: Int
    var items = true
    while (items) {
        println("\n1. Sort Customers by Name")
        println("2. Search Customers")
        println("3. Save Customers to File")
        println("4. Read Customers from File")
        println("5. Exit")
        print(" Enter your choice: ")
        // If user's input between 1 and 5 then do specific action separately regards on
        // the selected number
        try{
            option = readln()?.toInt() ?: 0
            when (option) {
                1 -> { // If user's input is 1 then sort the list of customers and display
                    val sortedList = customerList.sortedBy { it.name }
                    println("\nSorted Customer List by Name:")
                    sortedList.forEach {
                        println(it)
                    }
                }
                2 -> { // If user's input is 2 then search the specific customer by a term of name,
                    // phone and email then display that customer's detail if the result is not empty
                    print("Enter search term: ")
                    val searchTerm = readLine() ?: ""
                    val results = customerList.filter {
                        it.name.contains(searchTerm, true) ||
                                it.email.contains(searchTerm, true) ||
                                it.mobile.contains(searchTerm, true)
                    }
                    if (results.isNotEmpty()) {
                        print("\nSearch Results:")
                        results.forEach {
                            println(it)
                        }
                    }
                    else {
                        println("No results found.")
                    }
                } // If user's input is 3 then the data in an array list saved in the customers file.
                3 -> {
                    try {
                        val outputStream = ObjectOutputStream(FileOutputStream("customers.dat"))
                        outputStream.writeObject(customerList)
                        outputStream.close()
                        println("Customers saved to file successfully.")
                    }// If file cannot be saved then display the exception message.
                    catch( e: IOException) {
                        println("Error while saving customers to file: ${e.message}")
                    }
                } // If user's input is 4 then read the data from the customers file then display them.
                4 -> {
                    try {
                        val inputStream = ObjectInputStream(FileInputStream("customers.dat"))
                        val savedCustomers = inputStream.readObject() as ArrayList<Customer>
                        inputStream.close()
                        println("\nCustomers read from file:")
                        savedCustomers.forEach {
                            println(it)
                        }
                    }
                    catch (e: IOException) {
                            println("Error while reading customers from file: ${e.message}")
                    } catch (e: ClassNotFoundException) {
                        println("Error while reading customers from file: ${e.message}")
                    }
                }// If user's input is 5 then exit the program.
                5 -> {
                    items = false
                    println("Existing...")
                }// If user's input is not between 1 and 5 then display an appropriate message.
                else -> println("Invalid choice. Please enter a valid option.")
            }// If user's input is not between 1 and 5 then display an appropriate message.
        } catch (e: NumberFormatException) {
            println("Invalid input. Please enter a number.")
        }
    }
}