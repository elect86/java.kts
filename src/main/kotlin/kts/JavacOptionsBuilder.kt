package kts

import java.io.File
import java.nio.charset.Charset

class JavacOptionsBuilder(val javac: JavaC) {

    /** Read options and filenames from file */
    var filename: File
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            javac.filename = value
        }

    /** Options to pass to annotation processors */
    val annotationProcessorOptions: ArrayList<String> by javac::annPrOptions

    /** Root modules to resolve in addition to the initial modules, or all modules on the module path if <module> is
     *  ALL-MODULE-PATH. */
    val addModules: ArrayList<String> by javac::addModules

    /** Override location of bootstrap class files */
    var bootClassPath: File
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            javac.bootClassPath = value
        }

    /** Specify where to find user class files and annotation processors */
    val classPath: ArrayList<File> by javac::classPath

    /** Specify where to place generated class files */
    var directory: File
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            javac.directory = value
        }

    /** Output source locations where deprecated APIs are used */
    val deprecation: Unit
        get() {
            javac.deprecation = true
        }

    /** Enable preview language features. To be used in conjunction with either -source or --release. */
    val enablePreview: Unit
        get() {
            javac.enablePreview = true
        }

    /** Specify character encoding used by source files */
    var encoding: Charset
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            javac.encoding = value
        }

    /** Override location of endorsed standards path */
    val endorsedDirs: ArrayList<File> by javac::endorsedDirs

    /** Override location of installed extensions */
    val extDirs: ArrayList<File> by javac::extDirs

    /** Debugging info to generate */
    var debuggingInfo: JavaC.Info
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            javac.debuggingInfo = value
        }

    /** Specify where to place generated native header files */
    var headersDir: File
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            javac.headersDir = value
        }

    /** Print this help message */
    val help: Unit
        get() {
            javac.help = true
        }

    /** Print help on extra options */
    val helpExtra: Unit
        get() {
            javac.helpExtra = true
        }

    /** Specify whether or not to generate class files for implicitly referenced files */
    var implicit: JavaC.Implicit
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            javac.implicit = value
        }

    /** Pass <flag> directly to the runtime system */
    var flag: String
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            javac.flag = value
        }

    /** Limit the universe of observable modules */
    val limitModules: ArrayList<String> by javac::limitModules

    /** Compile only the specified module, check timestamps */
    var module: String
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            javac.module = value
        }

    /** Specify where to find application modules */
    var modulePath: File
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            javac.modulePath = value
        }

    /** Specify where to find input source files for multiple modules */
    var moduleSourcePath: File
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            javac.moduleSourcePath = value
        }

    /** Specify version of modules that are being compiled */
    var moduleVersion: String
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            javac.moduleVersion = value
        }

    /** Generate no warnings */
    val noWarn: Unit
        get() {
            javac.noWarn = true
        }

    /** Generate metadata for reflection on method parameters */
    val parameters: Unit
        get() {
            javac.parameters = true
        }

    /** Control whether annotation processing and/or compilation is done. */
    var proc: JavaC.Proc
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            javac.proc = value
        }

    /** Names of the annotation processors to run; bypasses default discovery process */
    val processor: ArrayList<String> by javac::processor

    /** Specify a module path where to find annotation processors */
    var processorModulePath: File
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            javac.processorModulePath = value
        }

    /** Specify where to find annotation processors */
    var processorPath: File
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            javac.processorPath = value
        }

    /** Check that API used is available in the specified profile */
    var profile: String
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            javac.profile = value
        }

    /** Compile for a specific VM version. Supported targets: 6, 7, 8, 9, 10, 11 */
    var release: Int
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            check(value >= 6)
            javac.release = value
        }

    /** Specify where to place generated source files */
    var sourceDir: File
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            javac.sourceDir = value
        }

    /** Provide source compatibility with specified release */
    var source: Int
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            javac.source = value
        }

    /** Specify where to find input source files */
    var sourcePath: File
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            javac.sourcePath = value
        }

    /** Override location of system modules */
    var system: JavaC.System
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            javac.system = value
        }

    /** Generate class files for specific VM version */
    var target: Int
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            javac.target = value
        }

    /** Override location of upgradeable modules */
    var upgradeModulePath: File
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            javac.upgradeModulePath = value
        }

    /** Override location of upgradeable modules */
    val verbose: Unit
        get() {
            javac.verbose = true
        }

    /** Version information */
    val version: Unit
        get() {
            javac.version = true
        }

    /** Terminate compilation if warnings occur */
    val wError: Unit
        get() {
            javac.wError = true
        }
}