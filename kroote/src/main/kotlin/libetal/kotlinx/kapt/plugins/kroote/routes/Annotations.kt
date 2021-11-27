package libetal.kotlinx.kapt.plugins.kroote.routes

import kotlin.reflect.KProperty

object Annotations {

    val GET by this
    val PUT by this
    val POST by this
    val HEAD by this
    val PATCH by this
    val PARAM by this
    val ROUTE by this
    val OPTION by this
    val DELETE by this
    val RECEIVED by this

    val packageName = "libetal.kotlinx.kapt.plugins.kroote.annotations.routes"

    operator fun getValue(receiver: Any, property: KProperty<*>): String = "$packageName.${property.name.uppercase()}"
}