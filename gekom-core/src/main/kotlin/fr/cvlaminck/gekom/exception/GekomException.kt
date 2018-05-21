package fr.cvlaminck.gekom.exception

/**
 * Base class of all exceptions thrown by the library.
 */
class GekomException(
        message: String,
        cause: Throwable
): RuntimeException(message, cause)
