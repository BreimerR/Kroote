package libetal.kotlinx.kapt.plugins.kroote.routes.ksp

import com.google.devtools.ksp.symbol.*
import com.google.devtools.ksp.visitor.KSTopDownVisitor
import libetal.kotlinx.kapt.plugins.kroote.routes.Annotations

class RouteVisitor : KSTopDownVisitor<MutableList<Route>, Unit>() {
    override fun defaultHandler(node: KSNode, data: MutableList<Route>) {}

    override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: MutableList<Route>) {
        super.visitClassDeclaration(classDeclaration, data)

        var path = ""
        var name = ""
        var authenticator = ""

        classDeclaration.useAnnotation(Annotations.ROUTE) { ksAnnotation ->
            val arguments = ksAnnotation.arguments

            for (argument in arguments) {

                when (argument.name?.asString()) {
                    "route" -> argument.value?.toString()?.let {

                        path = it
                    }
                    "name" -> argument.value?.toString()?.let {
                        name = it
                    }
                    "authenticator" -> argument.value?.toString()?.let {
                        authenticator = it
                    }
                    else -> Logger.error("Unknown argument ${argument.name?.asString()}")
                }
            }
        }

        classDeclaration.qualifiedName?.asString()?.let { qualifiedClassName ->
            val route = Route(qualifiedClassName, path, name, authenticator)

            classDeclaration.declarations.forEach { ksDeclaration ->

                ksDeclaration.useFirst(
                    Annotations.GET to { ksAnnotation ->
                        processDeclarationMethod("get", ksDeclaration, ksAnnotation, route)
                    },
                    Annotations.POST to { ksAnnotation ->
                        processDeclarationMethod("post", ksDeclaration, ksAnnotation, route)
                    },

                    Annotations.DELETE to {
                        processDeclarationMethod("delete", ksDeclaration, it, route)
                    },

                    Annotations.HEAD to {
                        processDeclarationMethod("head", ksDeclaration, it, route)
                    },

                    Annotations.OPTION to {
                        processDeclarationMethod("option", ksDeclaration, it, route)
                    },

                    Annotations.PATCH to {
                        processDeclarationMethod("patch", ksDeclaration, it, route)
                    },

                    Annotations.PUT to {
                        processDeclarationMethod("put", ksDeclaration, it, route)
                    }

                )

            }

            data += route
        }

    }

    private fun processDeclarationMethod(
        methodName: String,
        ksDeclaration: KSDeclaration,
        ksAnnotation: KSAnnotation,
        route: Route
    ): Unit? = when (ksDeclaration) {
        is KSFunctionDeclaration -> ksDeclaration.accept(FunMethodVisitor(methodName, ksAnnotation), route.methods)
        is KSPropertyDeclaration -> ksDeclaration.accept(PropertyMethodVisitor(methodName, ksAnnotation), route.methods)
        else -> null
    }


}



