package libetal.kotlinx.kapt.plugins.kroote.annotations.routes

import kotlin.annotation.AnnotationRetention.SOURCE
import kotlin.annotation.AnnotationTarget.FUNCTION
import kotlin.annotation.AnnotationTarget.PROPERTY

@Retention(SOURCE)
@Target(FUNCTION, PROPERTY)
annotation class GET(
    val route: String = "",
    val customCode: String = "",
    val authenticator:String = ""
)















