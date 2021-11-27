package libetal.kotlinx.kapt.plugins.kroote.routes.ksp.declarations

import com.google.devtools.ksp.symbol.*

class ParameterDeclaration(
    override val simpleName: KSName,
    override val type: KSTypeReference,
    override val packageName: KSName,
    override val annotations: Sequence<KSAnnotation>,
    override val docString: String? = null,
    override val parentDeclaration: KSDeclaration? = null,
    override val typeParameters: List<KSTypeParameter> = listOf(),
    override val containingFile: KSFile? = null,
    override val isActual: Boolean = true,
    override val qualifiedName: KSName? = null,
    override val isExpect: Boolean = false,
    override val modifiers: Set<Modifier> = setOf(Modifier.PUBLIC),
    override val location: Location = FileLocation("", 0),
    override val origin: Origin = Origin.KOTLIN,
    override val parent: KSNode? = null,
    override val extensionReceiver: KSTypeReference? = null,
    override val getter: KSPropertyGetter? = null,
    override val setter: KSPropertySetter? = null,
    override val hasBackingField: Boolean = false,
    override val isMutable: Boolean = false
) : KSPropertyDeclaration {

    override fun <D, R> accept(visitor: KSVisitor<D, R>, data: D): R = TODO("Not To be Implemented. Do Not Call Directly.")

    override fun asMemberOf(containing: KSType): KSType = TODO("Not To be Implemented. Do Not Call Directly.")

    override fun findActuals(): Sequence<KSDeclaration> = TODO("Not To be Implemented. Do Not Call Directly.")

    override fun findExpects(): Sequence<KSDeclaration> = TODO("Not To be Implemented. Do Not Call Directly.")

    override fun findOverridee(): KSPropertyDeclaration = TODO("Not To be Implemented. Do Not Call Directly.")

    override fun isDelegated(): Boolean = TODO("Not To be Implemented. Do Not Call Directly.")
}