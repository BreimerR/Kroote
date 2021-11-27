package libetal.kotlinx.kapt.plugins.kroote.routes.ksp

data class Route(
    val qualifiedName: String,
    val path: String,
    val name: String,
    val authenticator: String,

    ) {
    val methods by lazy {
        mutableListOf<Method>()
    }

    private fun getMethodsString(indent: String = ""): String = methods.joinToString("\n") { it.toString(indent) }

    fun toString(indent: String = ""): String = if (authenticator.isBlank()) {
        """|${indent}route("$path"){
               |${indent}    with($qualifiedName){
               |${getMethodsString("$indent        ")}
               |$indent    }
               |$indent}
               |""".trimMargin()
    } else """|${indent}authenticate("$authenticator") {
            |${indent}    route("$path"){
            |${indent}        with($qualifiedName){
            |${getMethodsString("$indent            ")}
            |$indent        }
            |$indent    }
            |$indent}
        """.trimMargin()


}