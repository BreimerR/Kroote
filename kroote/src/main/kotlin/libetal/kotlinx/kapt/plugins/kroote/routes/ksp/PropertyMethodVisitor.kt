package libetal.kotlinx.kapt.plugins.kroote.routes.ksp

import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSNode
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.google.devtools.ksp.visitor.KSTopDownVisitor

class PropertyMethodVisitor(private val methodName: String, ksAnnotation: KSAnnotation) :
    KSTopDownVisitor<MutableList<Method>, Unit>() {

    private var path: String? = null
    private var customCode: String? = null
    private var authenticator: String? = null

    private val annotationArguments by lazy {
        ksAnnotation.arguments
    }

    init {

        for (argument in annotationArguments) {
            when (argument.name?.asString()) {
                "route" -> argument.value?.toString()?.let {

                    if (it.isNotBlank()) path = it
                }
                "authenticator" -> argument.value?.toString()?.let {
                    if (it.isNotBlank()) authenticator = it
                }
                "customCode" -> argument.value?.toString()?.let {
                    if (it.isNotBlank()) customCode = it
                }
                else -> Logger.error("Unknown argument ${argument.name?.asString()}")
            }
        }
    }

    override fun defaultHandler(node: KSNode, data: MutableList<Method>) {

    }

    override fun visitPropertyDeclaration(property: KSPropertyDeclaration, data: MutableList<Method>) {
        super.visitPropertyDeclaration(property, data)
        val method = Method(
            methodName,
            customCode ?: "call.respond(HttpStatusCode.OK,${property.simpleName.asString()})",
            path,
            authenticator
        )

        data += method
    }

}
