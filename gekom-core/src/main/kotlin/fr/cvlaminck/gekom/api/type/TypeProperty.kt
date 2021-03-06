package fr.cvlaminck.gekom.api.type

import fr.cvlaminck.gekom.reflect.TypeDescriptor
import java.lang.reflect.Type

/**
 * A [TypeProperty] represents a
 *
 * A property can be:
 * - readable: The value of the property can be read on instances of the type [T].
 * - editable: The value of the property can be set on instances of the type [T].
 * - injectable: The value of the property can be set when an instance of the type [T] is created.
 *
 * @param T Type to which this property belongs.
 * @param PT Type of the value of this property.
 *
 * @since 1.0.0
 */
interface TypeProperty<in T : Any, PT : Any> {

    /**
     * Returns the name of the property.
     *
     * @since 1.0.0
     */
    val name: String

    /**
     * Return aliases that may be use to also refer to this property in addition to its name.
     *
     * @since 1.0.0
     */
    val aliases: Collection<String>

    /**
     * Returns the type of the value.
     *
     * @since 1.0.0
     */
    val type: TypeDescriptor

    /**
     * Returns true if the property value can be read.
     *
     * A property is considered readable if one of the following condition is matched:
     * - a getter with the following signature: PT get{{capitalized name}}()
     * - a field with the name of the property.
     *
     * @since 1.0.0
     */
    val readable: Boolean

    /**
     * Returns true if the property value can be written on a existing object.
     *
     * A property is considered editable if one of the following condition is matched:
     * - a setter with the following signature: void set{{capitalized name}}(PT value)
     * - a field with the name of the property.
     *
     * @since 1.0.0
     */
    val editable: Boolean

    /**
     * Returns true if the property value can be injected when the object is created.
     *
     * A property is considered injectable if its value can be set though one of the constructor.
     * A good example of this case is kotlin dataclass where all the properties are only injectable.
     */
    val injectable: Boolean

    /**
     * Returns the value of property for the provided object.
     *
     * @param obj Object from which the value will be read.
     * @return the value of the property for the provided object.
     * @since 1.0.0
     */
    fun get(obj: T): PT

    /**
     * Set the [value] of the property for the provided object.
     *
     * @param obj Object on which to set the value.
     * @param value Value to set.
     * @since 1.0.0
     */
    fun set(obj: T, value: PT)
}
