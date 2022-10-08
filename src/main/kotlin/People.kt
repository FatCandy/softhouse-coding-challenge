import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JacksonXmlRootElement(localName = "people")
data class People(
    val person: List<Person>
)

data class Person(
    val firstname: String,
    val surname: String,
    val address: Address? = null,
    val family: Family? = null,
    val phone: Phone? = null
)

data class Family(
    var name: String?,
    var born: String?,
    var address: Address? = null,
    var phone: Phone? = null
)

data class Address(
    val street: String?,
    val town: String?,
    val postcode: String?
)

data class Phone(
    val mobile: String?,
    val landline: String?
)