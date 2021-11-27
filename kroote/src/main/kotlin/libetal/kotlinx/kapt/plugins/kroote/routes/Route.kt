package libetal.kotlinx.kapt.plugins.kroote.routes

data class Route(
    var name: String,
    val classQualifiedName: String,
    val parent: Route? = null,
    val resolved: Boolean = false
) {

    init {
        name = name.trim('/')
    }

    val endPoints by lazy {
        name.split("/")
    }

    val lastEndPoint by lazy {
        endPoints.last()
    }

    val endPointsSize by lazy {
        endPoints.size
    }

    private val url: String by lazy {
        "${parent?.url ?: ""}/$name"
    }




}