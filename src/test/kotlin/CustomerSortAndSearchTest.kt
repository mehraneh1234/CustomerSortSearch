import org.junit.Test
import java.io.*
import org.junit.jupiter.api.Assertions.assertEquals
import objects.Customer
// Mehraneh Hamedani - 30062786 - AT2 - Activity1 - CustomerSortAndSearch Test
class CustomerSortAndSearchTest {
    // To test the object of a class can create
    @Test
    fun `test customer object creation`() {
        val customer = Customer(1, "John Doe", "john@example.com", "1234567890")
        assertEquals(1, customer.id)
        assertEquals("John Doe", customer.name)
        assertEquals("john@example.com", customer.email)
        assertEquals("1234567890", customer.mobile)
    }
    // To check the file can save in a binary file and read from the file
    @Test
    fun `test customer object serialization`() {
        val customer = Customer(1, "John Doe", "john@example.com", "1234567890")
        val fileName = "testCustomer.dat"

        try {
            val outputStream = ObjectOutputStream(FileOutputStream(fileName))
            outputStream.writeObject(customer)
            outputStream.close()

            val inputStream = ObjectInputStream(FileInputStream(fileName))
            val savedCustomer = inputStream.readObject() as Customer
            inputStream.close()

            assertEquals(customer.id, savedCustomer.id)
            assertEquals(customer.name, savedCustomer.name)
            assertEquals(customer.email, savedCustomer.email)
            assertEquals(customer.mobile, savedCustomer.mobile)

            File(fileName).delete()
        } catch (e: IOException) {
            println("Error during serialization: ${e.message}")
        } catch (e: ClassNotFoundException) {
            println("Error during deserialization: ${e.message}")
        }
    }
    // To check the correctness of the override toString method and the way to display for users
    @Test
    fun `test customer toString method`() {
        val customer = Customer(1, "John Doe", "john@example.com", "1234567890")
        val expectedString = "Customer(id=1, name='John Doe', email='john@example.com', mobile='1234567890')"
        assertEquals(expectedString, customer.toString())
    }
}