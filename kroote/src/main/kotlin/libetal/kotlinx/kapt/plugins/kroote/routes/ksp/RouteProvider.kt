package libetal.kotlinx.kapt.plugins.kroote.routes.ksp

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import libetal.kotlinx.kapt.plugins.kroote.routes.ksp.RouteProcessor

class RouteProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {

        Logger.logger = environment.logger

        return RouteProcessor(
            environment
        )
    }
}