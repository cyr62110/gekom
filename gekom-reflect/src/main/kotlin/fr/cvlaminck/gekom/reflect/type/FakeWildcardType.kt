package fr.cvlaminck.gekom.reflect.type

import fr.cvlaminck.gekom.reflect.util.TypeNameUtils
import fr.cvlaminck.gekom.reflect.util.TypeUtils
import java.lang.reflect.Type
import java.lang.reflect.WildcardType

/**
 * An implementation of [WildcardType].
 *
 * The implementation only supports wildcard type that are legitimated in JRE 6 to 11 specifications, which means:
 * - only one upper bound and one lower bound
 * - if a lower bound is defined, then the upper bound is Object.
 *
 * The implementation of [hashCode] is specially made to be compatible so you can use [FakeWildcardType] in hash-driven
 * stuctures as well as legitimately created [WildcardType].
 *
 * @since 1.0.0
 */
class FakeWildcardType(
        private val upperBound: Type = Object::class.java,
        private val lowerBound: Type? = null
) : WildcardType {

    init {
        if (lowerBound != null && upperBound == Object::class.java) {
            throw IllegalArgumentException("As JRE 6 to 11 specifications, if an lower bound is specified, the upper bound will always be Object.")
        }
    }

    override fun getUpperBounds(): Array<Type> = arrayOf(upperBound)

    override fun getLowerBounds(): Array<Type> = if (lowerBound != null) arrayOf(lowerBound) else arrayOf()

    override fun equals(other: Any?): Boolean {
        if (other !is WildcardType) {
            return false
        }
        return TypeUtils.equals(this, other)
    }

    override fun hashCode(): Int {
        var result = 31 + upperBound.hashCode()
        result = result xor if (lowerBound != null) 31 + lowerBound.hashCode() else 1
        return result
    }

    override fun toString(): String = TypeNameUtils.getTypeName(this)
}
