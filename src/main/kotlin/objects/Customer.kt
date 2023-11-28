package objects
import java.io.Serializable
// Mehraneh Hamedani - 30062786 - AT2 - Activity1 - CustomerSortAndSearch
// Define a class with 4 properties which inherit Serializable to allow read and write in a file
class Customer (val id: Int, val name: String, val email: String, val mobile: String) : Serializable {
    // Override the toString file to display the custpmers' detail in an appropriate way not
    // as an object name.
    override fun toString(): String {
        return "Customer(id=$id, name='$name', email='$email', mobile='$mobile')"
    }
}