package libetal.kotlinx.kapt.plugins.kroote.annotations.routes

import kotlin.annotation.AnnotationRetention.SOURCE
import kotlin.annotation.AnnotationTarget.*

/**
 * Used to generate
 * routing{
 *     delete(routeString){
 *        // customCode or call to annotated method
 *     }
 * }
 * */
@Retention(SOURCE)
@Target(FUNCTION, LOCAL_VARIABLE, PROPERTY)
annotation class DELETE(
    val route: String = "",
    val customCode: String = "",
    val authenticator: String = ""
)
