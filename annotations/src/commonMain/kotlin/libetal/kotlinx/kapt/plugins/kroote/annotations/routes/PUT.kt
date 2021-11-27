package libetal.kotlinx.kapt.plugins.kroote.annotations.routes


import kotlin.annotation.AnnotationRetention.SOURCE
import kotlin.annotation.AnnotationTarget.FUNCTION

@Retention(SOURCE)
@Target(FUNCTION)
annotation class PUT(
    val route: String = "",
    val customCode: String = "",
    val authenticator:String = ""
)
