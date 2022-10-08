class PersonParser {

    fun extractPeople(lines: Sequence<String>): People {
        var rawPerson: MutableList<String>? = null
        val people = mutableListOf<Person?>()
        lines.forEach { line ->
            if (line.first() == 'P') {
                rawPerson?.let { person ->
                    people.add(parse(person.toList()))
                }
                rawPerson = mutableListOf(line)
            } else {
                rawPerson?.add(line)
            }
        }
        rawPerson?.let { person ->
            people.add(parse(person.toList()))
        }
        return People(people.filterNotNull())
    }

    private fun parse(input: List<String>): Person? {
        var parentNode = PERSON
        var person: Person? = null
        input.forEach {
            when (it.first().uppercase()) {
                PERSON -> {
                    person = parsePerson(it.drop(2))
                    parentNode = PERSON
                }
                TELEPHONE -> {
                    if (parentNode == PERSON) {
                        person?.phone = parsePhone(it.drop(2))
                    } else {
                        person?.family?.lastOrNull()?.phone = parsePhone(it.drop(2))
                    }
                }
                ADDRESS -> {
                    if (parentNode == PERSON) {
                        person?.address = parseAddress(it.drop(2))
                    } else {
                        person?.family?.lastOrNull()?.address = parseAddress(it.drop(2))
                    }
                }
                FAMILY -> {
                    person?.family?.add(parseFamily(it.drop(2)))
                    parentNode = FAMILY
                }
                else -> {}
            }
        }
        return person
    }

    private fun parsePerson(rawPerson: String?): Person? = rawPerson?.delimited()?.let { names ->
        Person(firstname = names[0], surname = names[1])
    }

    private fun parsePhone(rawPhone: String?): Phone? = rawPhone?.delimited()?.let { numbers ->
        Phone(mobile = numbers.getOrNull(0), landline = numbers.getOrNull(1))
    }

    private fun parseAddress(rawAddress: String?): Address? = rawAddress?.delimited()?.let { address ->
        Address(address.getOrNull(0), address.getOrNull(1), address.getOrNull(2))
    }

    private fun parseFamily(rawFamily: String?): Family? = rawFamily?.delimited()?.let { family ->
        Family(family.getOrNull(0), family.getOrNull(1))
    }

    private fun String?.delimited(): List<String>? = this?.split(DELIMITER)

    companion object {
        private const val PERSON = "P"
        private const val ADDRESS = "A"
        private const val TELEPHONE = "T"
        private const val FAMILY = "F"
        private const val DELIMITER = "|"
    }
}