package libetal.kotlinx.kapt.plugins.kroote.annotations.routes

import kotlin.annotation.AnnotationRetention.SOURCE
import kotlin.annotation.AnnotationTarget.FUNCTION


/**TODO
 * Headers definition
 * */
@Retention(SOURCE)
@Target(FUNCTION)
annotation class POST(
    val route: String = "",
    val customCode: String = "",
    val authenticator:String = ""
)
