package fr.cvlaminck.gekom.core.reflect

import fr.cvlaminck.gekom.core.reflect.utils.TypeNameUtils
import fr.cvlaminck.gekom.core.reflect.utils.TypeUtils
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.lang.reflect.WildcardType
import java.util.*

/**
 * An implementation of [ParameterizedType].
 *
 * The implementation of [hashCode] is specially made to be compatible so you can use [FakeParameterizedType] in hash-driven
 * stuctures as well as legitimately created [ParameterizedType].
 *
 * @since 1.0.0
 */
class FakeParameterizedType(
        private val ownerType: Type?,
        private val rawType: Type,
        private val actualTypeArguments: Array<Type>
) : ParameterizedType {

    override fun getOwnerType(): Type? = this.ownerType
    override fun getRawType(): Type = this.rawType;
    override fun getActualTypeArguments(): Array<Type> = this.actualTypeArguments

    override fun toString(): String = TypeNameUtils.getTypeName(this)

    override fun equals(other: Any?): Boolean {
        if (other !is ParameterizedType) {
            return false
        }
        return TypeUtils.equals(this, other)
    }

    override fun hashCode(): Int {
        var result = ownerType?.hashCode() ?: 0
        result = 31 * result + rawType.hashCode()
        result = 31 * result + Arrays.hashCode(actualTypeArguments)
        return result
    }
}