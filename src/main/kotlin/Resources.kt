import java.io.InputStream

class Resources {
    fun resourceOrNull(resource:String): InputStream? = Resources::class.java.getResource(resource)?.openStream()
}