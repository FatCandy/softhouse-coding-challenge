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
    val personParser = PersonParser()
    val resources = Resources()
    resources.resourceOrNull("input_data")?.use { inputStream ->
        inputStream.bufferedReader().use {
            it.useLines { lines ->
                println(xmlMapper.writeValueAsString(personParser.extractPeople(lines)))
            }
        }
    }
}