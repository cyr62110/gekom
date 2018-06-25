package fr.cvlaminck.gekom.core.type

import fr.cvlaminck.gekom.api.type.TypeModel
import fr.cvlaminck.gekom.api.type.TypeProperty
import fr.cvlaminck.gekom.reflect.TypeDescriptor

internal data class DefaultTypeModel(
        override val type: TypeDescriptor,
        override val tag: String?,
        override val properties: Collection<TypeProperty<Any, Any>>
) : TypeModel {

    override fun getPropertyByNameOrAlias(nameOrAlias: String): TypeProperty<Any, Any>? {
        TODO("not implemented")
    }
}
