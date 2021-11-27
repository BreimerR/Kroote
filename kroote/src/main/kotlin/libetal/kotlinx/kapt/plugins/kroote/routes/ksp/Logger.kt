package libetal.kotlinx.kapt.plugins.kroote.routes.ksp

import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.KSNode

object Logger {
    lateinit var logger: KSPLogger

    fun logging(message: String, symbol: KSNode? = null) {
        logger.logging(message, symbol)
    }

    fun info(message: String, symbol: KSNode? = null) {
        logger.info(message, symbol)
    }

    fun warn(message: String, symbol: KSNode? = null) {
        logger.warn(message, symbol)
    }

    fun error(message: String, symbol: KSNode? = null) {
        logger.error(message, symbol)
    }

    fun error(message: Any?, symbol: KSNode? = null) = error(message?.toString() ?: "Can't Resolve error message", symbol)

}