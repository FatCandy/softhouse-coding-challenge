import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper

fun main(args: Array<String>) {

    val xmlMapper = XmlMapper(
        JacksonXmlModule().apply {
            setDefaultUseWrapper(false)
        }).apply {
        enable(SerializationFeature.INDENT_OUTPUT)
        enable(SerializationFeature.WRAP_ROOT_VALUE)
    }

    val people = People(
        listOf(
            Person(
                firstname = "Victoria",
                surname = "Bernadotte",
                address = Address(
                    "Haga Slott",
                    "Stockholm",
                    "101"
                )
            )
        )
    )


    val xml = xmlMapper.writeValueAsString(people)
    println(xml)
}