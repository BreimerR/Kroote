package libetal.kotlinx.kapt.plugins.kroote.routes.ksp.declarations

import com.google.devtools.ksp.symbol.*

class TypeReference(
    override val annotations: Sequence<KSAnnotation>,
    override val modifiers: Set<Modifier>,
    override val location: Location = FileLocation("", 0),
    override val origin: Origin = Origin.KOTLIN,
    override val parent: KSNode? = null,
    override val element: KSReferenceElement? = null
) : KSTypeReference {

    override fun <D, R> accept(visitor: KSVisitor<D, R>, data: D): R = TODO("Not To be Implemented. Do Not Call Directly.")

    override fun resolve(): KSType = TODO("Not To be Implemented. Do Not Call Directly.")

}