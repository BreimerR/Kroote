package libetal.kotlinx.kapt.plugins.kroote.routes.ksp

import kotlin.reflect.KProperty

class TypedDelegateParameter<T>(private val arguments: DelegateParameters, val converter: (Any?) -> T) {

    operator fun getValue(receiver: Nothing?, property: KProperty<*>): T =
        converter(arguments.getValue(receiver, property))

}