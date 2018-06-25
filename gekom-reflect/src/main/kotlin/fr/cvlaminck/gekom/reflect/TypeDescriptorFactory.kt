package fr.cvlaminck.gekom.reflect

import fr.cvlaminck.gekom.reflect.type.FakeParameterizedType
import fr.cvlaminck.gekom.reflect.type.FakeWildcardType
import java.lang.reflect.Field
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * Provides utility method to easily obtains [TypeDescriptor] in various case where the java.lang.reflect API should
 * be use.
 *
 * @since 1.0.0
 */
class TypeDescriptorFactory {

    /**
     * Returns a [TypeDescriptor] describing the type of the provided class.
     *
     * @since 1.0.0
     */
    fun of(clazz: Class<*>) = TypeDescriptor(clazz)

    /**
     * Returns a [TypeDescriptor] describing the type of the provided [type].
     *
     * @since 1.0.0
     */
    fun of(type: Type) = TypeDescriptor(type)

    /**
     * Returns a [TypeDescriptor] describing a parameterized type with provided [ownerType], [rawType] and [actualTypeArguments].
     *
     * @since 1.0.0
     */
    @Suppress("UNCHECKED_CAST")
    fun ofParameterizedType(ownerType: Type?, rawType: Type, vararg actualTypeArguments: Type) = TypeDescriptor(FakeParameterizedType(ownerType, rawType, actualTypeArguments as Array<Type>))

    /**
     * Return a [TypeDescriptor] describing a wildcard type with provided [lowerBound] and [upperBound].
     *
     * This implementation only supports wildcard type that are legitimated in JRE 6 to 11 specifications, which means:
     * - only one upper bound and one lower bound
     * - if a lower bound is defined, then the upper bound is Object.
     *
     * @since 1.0.0
     */
    fun ofWildcardType(upperBound: Type, lowerBound: Type? = null) = TypeDescriptor(FakeWildcardType(upperBound, lowerBound))

    /**
     * Return a list of [TypeDescriptor] describing all the types of the type arguments for the provided [type].
     *
     * @since 1.0.0
     */
    fun ofActualTypeArguments(type: ParameterizedType): List<TypeDescriptor> = arrayListOf(*type.actualTypeArguments)
            .map { of(it) }

    /**
     * Returns a [TypeDescriptor] describing the type of the provided [field].
     *
     * @since 1.0.0
     */
    fun ofField(field: Field) = TypeDescriptor(field.genericType)

    /**
     * Returns a [TypeDescriptor] describing the type of the return value of the provided [method].
     *
     * @since 1.0.0
     */
    fun ofReturnValue(method: Method) = TypeDescriptor(method.genericReturnType)

    /**
     * Return a [TypeDescriptor] describing the type of the ith parameters of the provided [method].
     *
     * @since 1.0.0
     */
    fun ofParameter(method: Method, index: Int): TypeDescriptor = TypeDescriptor(method.genericParameterTypes[index])
}
