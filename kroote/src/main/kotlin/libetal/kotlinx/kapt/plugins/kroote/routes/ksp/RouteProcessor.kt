package libetal.kotlinx.kapt.plugins.kroote.routes.ksp

import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import libetal.kotlinx.kapt.plugins.kroote.routes.Annotations
import libetal.kotlinx.kapt.plugins.kroote.routes.ksp.Logger.logger
import java.io.OutputStreamWriter

class RouteProcessor(private val environment: SymbolProcessorEnvironment) : SymbolProcessor {

    private val packageName = "kroote.generated"

    private val codeGenerator by lazy {
        environment.codeGenerator
    }

    override fun process(resolver: Resolver): List<KSAnnotated> {

        try {
            codeGenerator.createNewFile(
                Dependencies(true, *resolver.getAllFiles().toList().toTypedArray()),
                packageName,
                "routes",
                "kt"
            ).use { output ->
                OutputStreamWriter(output).use { writer ->
                    writer.apply {
                        write("package $packageName")
                        write("\n\n")
                        addImport("io.ktor.auth.*")
                        addImport("io.ktor.http.*")
                        addImport("io.ktor.routing.*")
                        addImport("io.ktor.response.*")
                        addImport("io.ktor.application.*")
                        write("\n")
                        val visitor = RouteVisitor()

                        val routes = mutableListOf<Route>()

                        resolver.getSymbolsWithAnnotation(Annotations.ROUTE)
                            .filterIsInstance<KSClassDeclaration>()
                            .forEach {
                                it.accept(
                                    visitor,
                                    routes
                                )
                            }

                        val indent = "    "

                        val routesString = routes.joinToString("\n") {
                            it.toString(indent + indent)
                        }

                        write(
                            """fun Application.krooteRoutes(){
                               |${indent}routing {
                               |$routesString
                               |$indent}
                               |}""".trimMargin()
                        )

                    }
                }
            }
        } catch (e: Exception) {
            logger.warn("File already exists don't know how to handle them yet")
        }
        return emptyList()
    }

    private fun OutputStreamWriter.addImport(import: String) = write("import $import\n")

}



