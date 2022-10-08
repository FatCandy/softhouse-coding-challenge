import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PeopleTest {

    private val personParser = PersonParser()
    private val resources = Resources()
    private var people: People? = resources.resourceOrNull("input_data")?.use { inputStream ->
        inputStream.bufferedReader().use {
            it.useLines { lines ->
                personParser.extractPeople(lines)
            }
        }
    }

    @Test
    fun expectValidPersons() {
        people?.let {
            assertEquals(it.person.size, 2)
            assertEquals(it.person.first().firstname, "Victoria")
            assertEquals(it.person.first().surname, "Bernadotte")
            assertEquals(it.person.last().firstname, "Joe")
            assertEquals(it.person.last().surname, "Biden")
        } ?: run {
            assert(false)
        }
    }

    @Test
    fun expectValidTelephone() {
        people?.let {
            assertEquals(it.person.first().phone?.mobile, "070-0101010")
            assertEquals(it.person.first().phone?.landline, "0459-123456")
            assertEquals(it.person.last().phone, null)
        } ?: run {
            assert(false)
        }
    }

    @Test
    fun expectValidFamily() {
        people?.let {
            assertEquals(it.person.first().family.size, 2)
            assertEquals(it.person.first().family.first()?.name, "Estelle")
            assertEquals(it.person.first().family.last()?.name, "Oscar")
            assertEquals(it.person.first().family.last()?.name, "Oscar")
            assertEquals(it.person.first().family.last()?.address, null)
            assertEquals(it.person.first().family.first()?.address?.street, "Solliden")
            assertEquals(it.person.last().family.size, 0)
        } ?: run {
            assert(false)
        }
    }
}