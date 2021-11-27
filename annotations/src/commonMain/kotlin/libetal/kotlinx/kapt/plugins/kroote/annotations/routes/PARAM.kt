package libetal.kotlinx.kapt.plugins.kroote.annotations.routes

@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class PARAM(
    @Suppress("unused")
    val name: String = "",
    @Suppress("unused") val defaultValue: String = ""
)