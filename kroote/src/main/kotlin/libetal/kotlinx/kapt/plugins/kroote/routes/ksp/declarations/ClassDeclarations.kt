package libetal.kotlinx.kapt.plugins.kroote.routes.ksp.declarations

import com.google.devtools.ksp.symbol.*

class ClassDeclarations(
    override var simpleName: KSName,
    override var packageName: KSName,
    override var annotations: Sequence<KSAnnotation>,
    override var superTypes: Sequence<KSTypeReference>,
    override var declarations: Sequence<KSDeclaration>,
    override var classKind: ClassKind = ClassKind.CLASS,
    override var parent: KSNode? = null,
    override var docString: String? = null,
    override var isActual: Boolean = false,
    override var isExpect: Boolean = false,
    override var qualifiedName: KSName? = null,
    override var containingFile: KSFile? = null,
    override var origin: Origin = Origin.KOTLIN,
    override var parentDeclaration: KSDeclaration? = null,
    override var primaryConstructor: KSFunctionDeclaration? = null,
    override var location: Location = FileLocation("", 0),
    override var isCompanionObject: Boolean = false,
    modifiers: MutableSet<Modifier> = mutableSetOf(),
    typeParameters: MutableList<KSTypeParameter> = mutableListOf()
) : KSClassDeclaration {

    override var modifiers = modifiers.toSet()
    override var typeParameters = typeParameters.toList()


    override fun <D, R> accept(visitor: KSVisitor<D, R>, data: D): R {
        TODO("Not To be Implemented. Do Not Call Directly.")
    }

    override fun asStarProjectedType(): KSType {
        TODO("Not To be Implemented. Do Not Call Directly.")
    }

    override fun asType(typeArguments: List<KSTypeArgument>): KSType {
        TODO("Not To be Implemented. Do Not Call Directly.")
    }

    override fun findActuals(): Sequence<KSDeclaration> {
        TODO("Not To be Implemented. Do Not Call Directly.")
    }

    override fun findExpects(): Sequence<KSDeclaration> {
        TODO("Not To be Implemented. Do Not Call Directly.")
    }

    override fun getAllFunctions(): Sequence<KSFunctionDeclaration> {
        TODO("Not To be Implemented. Do Not Call Directly.")
    }

    override fun getAllProperties(): Sequence<KSPropertyDeclaration> {
        TODO("Not To be Implemented. Do Not Call Directly.")
    }

    override fun getSealedSubclasses(): Sequence<KSClassDeclaration> {
        TODO("Not To be Implemented. Do Not Call Directly.")
    }
}