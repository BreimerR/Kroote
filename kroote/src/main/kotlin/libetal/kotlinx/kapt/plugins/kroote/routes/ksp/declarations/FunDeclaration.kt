package libetal.kotlinx.kapt.plugins.kroote.routes.ksp.declarations

import com.google.devtools.ksp.symbol.*

class FunDeclaration(
    override val simpleName: KSName,
    override val packageName: KSName,
    override val qualifiedName: KSName,
    override val annotations: Sequence<KSAnnotation>,
    override val declarations: Sequence<KSDeclaration>
) : KSFunctionDeclaration {

    override var parent: KSNode? = null
    override var modifiers = mutableSetOf<Modifier>()
    override var returnType: KSTypeReference? = null
    override var parameters = mutableListOf<KSValueParameter>()
    override var extensionReceiver: KSTypeReference? = null
    override var parentDeclaration: KSDeclaration? = null
    override var docString: String? = null
    override var containingFile: KSFile? = null
    override var isAbstract: Boolean = false
    override var isActual: Boolean = false
    override var isExpect: Boolean = false
    override val location: Location = FileLocation("", 0)
    override val origin: Origin = Origin.KOTLIN
    override val functionKind: FunctionKind = FunctionKind.MEMBER
    override val typeParameters = mutableListOf<KSTypeParameter>()


    override fun findActuals(): Sequence<KSDeclaration> {
        TODO("Not To be Implemented. Do Not Call Directly.")
    }

    override fun findExpects(): Sequence<KSDeclaration> {
        TODO("Not To be Implemented. Do Not Call Directly.")
    }

    override fun asMemberOf(containing: KSType): KSFunction {
        TODO("Not To be Implemented. Do Not Call Directly.")
    }

    override fun findOverridee(): KSDeclaration? {
        TODO("Not To be Implemented. Do Not Call Directly.")
    }

    override fun <D, R> accept(visitor: KSVisitor<D, R>, data: D): R {
        TODO("Not To be Implemented. Do Not Call Directly.")
    }
}
