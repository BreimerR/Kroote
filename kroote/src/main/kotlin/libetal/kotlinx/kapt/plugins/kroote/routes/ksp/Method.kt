package libetal.kotlinx.kapt.plugins.kroote.routes.ksp

data class Method(
    val name: String,
    val code: String,
    val route: String? = null,
    val authenticatedBy: String? = null
) {
    val parameters by lazy {
        mutableListOf<Parameter>()
    }

    fun getActualCode(indent: String) = code.split('\n').joinToString("\n") { "$indent$it" }

    val path by lazy {
        if (route == null || route == "") {
            ""
        } else "($route)"
    }

    fun toString(indent: String = ""): String = if (authenticatedBy == null || authenticatedBy.isBlank()) {
        """|$indent
            |$indent$name$path{
            |${getActualCode("$indent    ")}
            |$indent}
        """.trimMargin()
    } else
        """|${indent}authenticate("$authenticatedBy") {
               |$indent    $name$path{
               |${getActualCode("$indent        ")}
               |$indent    }
               |$indent}
        """.trimMargin()
}
