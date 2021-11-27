package libetal.kotlinx.kapt.plugins.kroote.routes.ksp

import com.google.devtools.ksp.symbol.KSValueArgument
import kotlin.reflect.KProperty

open class DelegateParameters(arguments: List<KSValueArgument>) {

    private var arguments: List<KSValueArgument>? = arguments

    private val reserve = mutableMapOf<String, Any?>()

    @Suppress("UNCHECKED_CAST")
    operator fun <T> invoke(converter: (Any?) -> T) = TypedDelegateParameter(this, converter)

    open operator fun getValue(receiver: Nothing?, property: KProperty<*>): Any? {

        arguments?.let {
            it.forEachIndexed { i, argument ->
                argument.name?.asString()?.let { argumentName ->
                    reserve[argumentName] = argument.value
                } ?: Logger.error("Can't resolve argument $i for @PARAM")
            }

            arguments = null
        }

        return reserve[property.name]
    }
}

