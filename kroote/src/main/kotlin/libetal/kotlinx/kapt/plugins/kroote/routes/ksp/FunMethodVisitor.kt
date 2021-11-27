package libetal.kotlinx.kapt.plugins.kroote.routes.ksp

import com.google.devtools.ksp.symbol.*
import com.google.devtools.ksp.visitor.KSTopDownVisitor
import libetal.kotlinx.kapt.plugins.kroote.routes.Annotations
import java.lang.RuntimeException

class FunMethodVisitor(private val methodName: String, private val ksAnnotation: KSAnnotation) :
    KSTopDownVisitor<MutableList<Method>, Unit>() {

    private val annotationArguments by lazy {
        ksAnnotation.arguments
    }

    private var path: String? = null
    private var customCode: String? = null
    private var authenticator: String? = null

    init {

        for (argument in annotationArguments) {
            when (argument.name?.asString()) {
                "route" -> argument.value?.toString()?.let {
                    if (it.isNotBlank()) path = it
                }
                "customCode" -> argument.value?.toString()?.let {
                    if (it.isNotBlank())
                        customCode = it
                }
                "authenticator" -> argument.value?.toString()?.let {
                    if (it.isNotBlank()) authenticator = it
                }

                else -> Logger.error("Unknown argument ${argument.name?.asString()}")
            }
        }
    }

    /**@Description
     * Creates code going into a method route i.e
     * get{
     *    code goes here
     * }
     * */
    private fun getActualCode(function: KSFunctionDeclaration): String {

        val functionName =
            function.qualifiedName?.asString() ?: "Can't resolve function name. Fatal error"

        return customCode?.let {
            if (it.isBlank()) Logger.error("Can't use empty code")
            it
        } ?: run {
            val parameters = mutableListOf<Pair<KSValueParameter, KSAnnotation>>()

            function.parameters.forEach { ksValueParameter ->
                ksValueParameter.useAnnotation(Annotations.PARAM) { ksAnnotation ->
                    parameters += ksValueParameter to ksAnnotation
                }
            }

            var results = ""

            var parametersString = ""

            val parametersValName = "parameters"

            if (parameters.size > 0) {
                results += "val $parametersValName: Parameters = call.parameters"

                parameters.forEachIndexed { i, (valueParameter, ksAnnotation) ->

                    val delegateParameters = DelegateParameters(ksAnnotation.arguments)


                    val valueParameterName = valueParameter.name?.asString()
                        ?: throw RuntimeException("Can't resolve name for parameter in function $functionName")

                    val name by delegateParameters {
                        it?.toString()?.ifBlank { null } ?: valueParameterName
                    }

                    if (i == 0)
                        parametersString += valueParameterName
                    else if (i != parameters.size)
                        parametersString += ", $valueParameterName"

                    results += """|
                        |val $valueParameterName = $parametersValName["$name"]?.""".trimMargin()

                    results += when (val valueParameterType =
                        valueParameter.type.resolve().declaration.qualifiedName?.asString()) {
                        "kotlin.String", "String" -> "toString"
                        "kotlin.Int", "Int" -> "toInt"
                        "kotlin.Float", "Float" -> "toFloat"
                        "kotlin.Boolean", "Boolean" -> "toBoolean"
                        null -> throw RuntimeException("Could not resolve type for parameter [$i]($valueParameterName) of function $functionName")
                        else -> throw RuntimeException("Unsupported Type $valueParameterType")
                    } + "()"

                    if (!valueParameter.type.resolve().isMarkedNullable) {

                        val defaultValue by delegateParameters {
                            val defaultValue = it?.toString()

                            defaultValue?.ifBlank { Logger.error("""Can't use defaultValueAs("") for parameter [$i]($valueParameterName) for function $functionName()""") }
                            defaultValue
                                ?: Logger.error("Provide default value for parameter [$i]($valueParameterName) for function $functionName(), enforcement might happen in next release.")

                            defaultValue!!
                        }

                        results += " ?: " + when (val valueParameterType =
                            valueParameter.type.resolve().declaration.qualifiedName?.asString()) {
                            "kotlin.String", "String" -> """"$defaultValue""""
                            "kotlin.Int", "Int" -> defaultValue.toInt()
                            "kotlin.Float", "Float" -> defaultValue.toFloat()
                            "kotlin.Boolean", "Boolean" -> defaultValue.toBoolean()
                            null -> throw RuntimeException("Could not resolve type for parameter $i of function $functionName")
                            else -> throw RuntimeException("Unsupported Type $valueParameterType")
                        }
                    }

                }

            }


            results += "\n${function.simpleName.asString()}(" + parametersString + ")"

            results

        }
    }

    override fun defaultHandler(node: KSNode, data: MutableList<Method>) {}

    override fun visitFunctionDeclaration(function: KSFunctionDeclaration, data: MutableList<Method>) {
        super.visitFunctionDeclaration(function, data)

        try {

            val method = Method(
                methodName,
                getActualCode(function),
                path,
                authenticator
            )


            data += method
        } catch (e: Exception) {
            Logger.error(e.message)
        }
    }

}

