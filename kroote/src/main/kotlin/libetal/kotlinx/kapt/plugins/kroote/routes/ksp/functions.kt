package libetal.kotlinx.kapt.plugins.kroote.routes.ksp

import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSDeclaration

fun KSAnnotated.getAnnotation(annotation: String): KSAnnotation? =
    annotations.firstOrNull { it.annotationType.resolve().declaration.qualifiedName?.asString() == annotation }

fun <R> KSAnnotated.useAnnotation(annotation: String, utilize: (KSAnnotation) -> R) = getAnnotation(annotation)?.let(utilize)

fun KSAnnotated.useFirst(vararg consumers: Pair<String, (KSAnnotation) -> Unit>) {
    for ((annotation, consumer) in consumers) {
        if (useAnnotation(annotation, consumer) == Unit) break
    }
}



