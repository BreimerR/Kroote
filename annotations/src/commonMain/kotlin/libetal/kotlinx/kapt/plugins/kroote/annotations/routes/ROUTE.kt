package libetal.kotlinx.kapt.plugins.kroote.annotations.routes


import kotlin.annotation.AnnotationRetention.SOURCE
import kotlin.annotation.AnnotationTarget.CLASS


@Target(CLASS)
@Retention(SOURCE)
annotation class ROUTE(
    val route: String,
    val name: String = "",
    /**
     * Update [libetal.kotlinx.kapt.plugins.kroote.routes.ksp.RouteVisitor][22:39]
     * */
    val authenticator: String = ""
)
