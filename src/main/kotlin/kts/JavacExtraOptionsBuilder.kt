package kts

import java.io.File

class JavacExtraOptionsBuilder(val javac: JavaC) {

    /** Specify a package to be considered as exported from its defining module to additional modules, or to all
     *  unnamed modules if <other-module> is ALL-UNNAMED. */
    val addExports: MutableMap<String, String> by javac.extra::addExports

    /** Specify additional modules to be considered as required by a given module. <other-module> may be ALL-UNNAMED
     *  to require the unnamed module. */
    val addReadss: MutableMap<String, String> by javac.extra::addReads

    /** Fallback target module for files created by annotation processors, if none specified or inferred. */
    var defaultModuleForCreatedFiles: String
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            javac.extra.defaultModuleForCreatedFiles = value
        }

    /** Override location of endorsed standards path */
    val endorsedDirs: ArrayList<File> by javac.extra::endorsedDirs

    /** Override location of installed extensions */
    val extDirs: ArrayList<File> by javac.extra::extDirs

    /** Specify the format for documentation comments */
    var doclintFormat: Doc
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            javac.extra.doclintFormat = value
        }

    /** Override or augment a module with classes and resources in JAR files or directories */
    val patchModule: MutableMap<String, String> by javac.extra::patchModule

    /** Override location of bootstrap class files */
    var bootclasspath: String
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            javac.extra.bootclasspath = value
        }

    /** Append to the bootstrap class path */
    var bootclasspathAppend: String
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            javac.extra.bootclasspathAppend = value
        }

    /** Prepend to the bootstrap class path */
    var bootclasspathPrepend: String
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            javac.extra.bootclasspathPrepend = value
        }

    /** Select a diagnostic mode */
    var diagnosticMode: String
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            javac.extra.bootclasspathPrepend = value
        }

    /** Enable recommended checks for problems in javadoc comments */
    var doclint: Boolean
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            javac.extra.doclintRecommended = value
        }

    fun doclint(block: DoclintBuilder.() -> Unit) = DoclintBuilder().block()

    inner class DoclintBuilder {
        operator fun Doclint.unaryPlus() {
            javac.extra.doclint += name
        }
        operator fun Doclint.unaryMinus() {
            javac.extra.doclint += "-$name"
        }
        operator fun String.unaryPlus() {
            javac.extra.doclint += this
        }
        operator fun String.unaryMinus() {
            javac.extra.doclint += "-$this"
        }
    }

    /** -Xdoclint:(all|none|[-]<group>)[/<access>]
     *  Enable or disable specific checks for problems in javadoc comments, where <group> is one of accessibility,
     *  html, missing, reference, or syntax, and <access> is one of public, protected, package, or private. */


    /** Enable or disable checks in specific packages. Each <package> is either the qualified name of a package or a
     *  package name prefix followed by .*, which expands to all sub-packages of the given package. Each <package> can
     *  be prefixed with - to disable checks for the specified package or packages. */
    var doclintPackage: String
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            javac.extra.doclintPackage = value
        }

    /** Enable recommended warnings */
    var lint: Boolean
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            javac.extra.lintRecommended = value
        }

    /** Warnings to enable or disable, separated by comma. Precede a key by - to disable the specified warning. */
    fun lint(block: LintBuilder.() -> Unit) = LintBuilder().block()

    inner class LintBuilder {
        operator fun Lint.unaryPlus() {
            javac.extra.lint += name
        }
        operator fun Lint.unaryMinus() {
            javac.extra.lint += "-$name"
        }
    }

    /** Set the maximum number of errors to print */
    var maxErrors: Int
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            javac.extra.maxErrors = value
        }

    /** Set the maximum number of warnings to print */
    var maxWarnings: Int
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            javac.extra.maxWarnings = value
        }

    /** Specify handling of package-info files */
    var pkginfo: Pkginfo
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            javac.extra.pkginfo = value
        }

    /** Name and optional arguments for a plug-in to be run */
    var plugin: String
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            javac.extra.plugin = value
        }

    /** Specify which file to read when both a source file and class file are found for an implicitly compiled class */
    var prefer: Prefer
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            javac.extra.prefer = value
        }

    /** Print out a textual representation of specified types */
    var print: Boolean
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            javac.extra.print = value
        }

    /** Print information about which annotations a processor is asked to process */
    var printProcessorInfo: Boolean
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            javac.extra.printProcessorInfo = value
        }

    /** Print information about rounds of annotation processing */
    var printRounds: Boolean
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            javac.extra.printRounds = value
        }

    /** Redirect standard output */
    var stdout: File
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            javac.extra.stdout = value
        }
}