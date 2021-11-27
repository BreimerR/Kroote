package libetal.kotlinx.kapt.plugins.kroote.routes


import java.lang.RuntimeException

object Routes {

    val routes by lazy {
        mutableListOf<Route>()
    }

    private val Route.existing
        get() = routes.firstOrNull {
            it == this
        }

    operator fun plusAssign(route: Route) = addRoute(route)

    fun addRoute(route: Route) {
        route.existing?.let {
            throw RuntimeException("Already Existing EndPoint registered  ${route.name} and ${it.name} point to the same end point ")
        }

        routes += route
    }



}