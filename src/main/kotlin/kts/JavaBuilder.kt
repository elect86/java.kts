package kts

import java.io.File

class JavaBuilder(val java: Java) {

    fun options(block: JavaOptionsBuilder.() -> Unit) = JavaOptionsBuilder(java).block()

    /** to execute a class */
    var mainClass: String
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            java.mainClass = value
        }

    /** to execute a jar file */
    var jar: File
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            java.jar = value
        }

    /** to execute the main class in a module */
    var module: String
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            java.module = value
        }

    /** to execute a single source-file program */
    var sourceFile: File
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            java.sourceFile = value
        }
}