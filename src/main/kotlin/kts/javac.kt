package kts

import java.io.File
import java.nio.charset.Charset

inline fun javac(block: JavacBuilder.() -> Unit) {
    val javac = JavaC()
    JavacBuilder(javac).block()
    javac()
}

class JavacBuilder(val javac: JavaC) {

    fun options(block: JavacOptionsBuilder.() -> Unit) = JavacOptionsBuilder(javac).block()
    fun extraOptions(block: JavacExtraOptionsBuilder.() -> Unit) = JavacExtraOptionsBuilder(javac).block()
    fun X(block: JavacExtraOptionsBuilder.() -> Unit) = JavacExtraOptionsBuilder(javac).block()

    val sourceFiles: ArrayList<File> by javac::sourceFiles
}

class JavaC(override val cmd: String = "javac") : Cmd<JavacBuilder> {

    var filename: File? = null
    val annPrOptions = ArrayList<String>()
    val addModules = ArrayList<String>()
    var bootClassPath: File? = null
    var classPath: File? = null
    var directory: File? = null
    var deprecation = false
    var enablePreview = false
    var encoding: Charset? = null
    val endorsedDirs = ArrayList<File>()
    val extDirs = ArrayList<File>()
    var debuggingInfo: Info? = null
    var headersDir: File? = null
    var help = false
    var helpExtra = false
    var implicit: Implicit? = null
    var flag = ""
    val limitModules = ArrayList<String>()
    var module = ""
    var modulePath: File? = null
    var moduleSourcePath: File? = null
    var moduleVersion = ""
    var noWarn = false
    var parameters = false
    var proc: Proc? = null
    val processor = ArrayList<String>()
    var processorModulePath: File? = null
    var processorPath: File? = null
    var profile = ""
    var release = -1
    var sourceDir: File? = null
    var source = -1
    var sourcePath: File? = null
    var system: System? = null
    var target = -1
    var upgradeModulePath: File? = null
    var verbose = false
    var version = false
    var wError = false

    val custom = ArrayList<String>()

    val sourceFiles = ArrayList<File>()

    override fun cmdLine(): List<String> {

        val args = arrayListOf<String>()

        filename?.run { args += "@$absolutePath" }
        for (a in annPrOptions) args += "-A$a"
        if (addModules.isNotEmpty()) args.add("--add-modules", addModules.joinToString(","))
        bootClassPath?.run { args.add("--boot-class-path", absolutePath) }
        classPath?.run { args.add("-cp", absolutePath) }
        directory?.run { args.add("-d", absolutePath) }
        if (deprecation) args += "-deprecation"
        if (enablePreview) args += "--enable-preview"
        encoding?.run { args.add("-encoding", name()) }
        if (endorsedDirs.isNotEmpty()) args.add("-endorseddirs", endorsedDirs.joinToString(File.pathSeparator) { it.absolutePath })
        if (extDirs.isNotEmpty()) args.add("-extdirs", extDirs.joinToString(File.pathSeparator) { it.absolutePath })
        debuggingInfo?.run { args += "-g$n" }
        headersDir?.run { args.add("-h", absolutePath) }
        if (help) args += "-?"
        if (helpExtra) args += "-X"
        implicit?.let { args += "-implicit:$it" }
        if (flag.isNotEmpty()) args += "-J$flag"
        if (limitModules.isNotEmpty()) args.add("--limit-modules", limitModules.joinToString(","))
        if (module.isNotEmpty()) args.add("-m", module)
        modulePath?.run { args.add("-p", absolutePath) }
        moduleSourcePath?.run { args.add("--module-source-path", absolutePath) }
        if (moduleVersion.isNotEmpty()) args.add("--module-version", moduleVersion)
        if (noWarn) args += "-nowarn"
        if (parameters) args += "-parameters"
        proc?.let { args += "-proc:$it" }
        if (processor.isNotEmpty()) args.add("-processor", processor.joinToString(","))
        processorModulePath?.run { args.add("--processor-module-path", absolutePath) }
        processorPath?.run { args.add("--processor-path", absolutePath) }
        if (profile.isNotEmpty()) args.add("-profile", profile)
        if (release != -1) args.add("--release", release)
        sourceDir?.run { args.add("-s", absolutePath) }
        if (source != -1) args.add("-source", source)
        sourcePath?.run { args.add("--source-path", absolutePath) }
        system?.let { args.add("--system", it) }
        if (target != -1) args.add("-target", target)
        upgradeModulePath?.run { args.add("--upgrade-module-path", absolutePath) }
        if (verbose) args += "-verbose"
        if (version) args += "-version"
        if (wError) args += "-Werror"

        extra(args)

        args += custom

        for (src in sourceFiles)
            if (src.isFile && src.extension == "java")
                args += src.absolutePath
            else // it's a dir, expand
                args += src.walk().filter { it.isFile && it.extension == "java" }.map { it.absolutePath }

        return args
    }

    override fun invoke(block: JavacBuilder.() -> Unit) = apply { JavacBuilder(this).block() }

    enum class Info {
        all, lines, vars, source, none;

        val n
            get() = when (this) {
                all -> ""
                else -> ":$name"
            }
    }

    enum class Implicit { none, `class` }
    enum class Proc { none, only }

    enum class System { jdk, none }

    val extra = Extra()

    class Extra {

        val addExports = mutableMapOf<String, String>()
        val addReads = mutableMapOf<String, String>()
        var defaultModuleForCreatedFiles = ""
        val endorsedDirs = ArrayList<File>()
        val extDirs = ArrayList<File>()
        var doclintFormat: Doc? = null
        val patchModule = mutableMapOf<String, String>()
        var bootclasspath = ""
        var bootclasspathAppend = ""
        var bootclasspathPrepend = ""
        var diags: Diags? = null
        var doclintRecommended = false
        var doclint = ArrayList<String>()
        var doclintPackage = ""
        var lintRecommended = false
        var lint = ArrayList<String>()
        var maxErrors = -1
        var maxWarnings = -1
        var pkginfo: Pkginfo? = null
        var plugin = ""
        var prefer: Prefer? = null
        var print = false
        var printProcessorInfo = false
        var printRounds = false
        var stdout: File? = null

        operator fun invoke(args: ArrayList<String>) {

            if (addExports.isNotEmpty()) {
                args += "--add-exports"
                for ((key, value) in addExports)
                    args += "$key=$value"
            }
            if (addReads.isNotEmpty()) {
                args += "--add-reads"
                for ((key, value) in addReads)
                    args += "$key=$value"
            }
            if (defaultModuleForCreatedFiles.isNotEmpty()) args.add("--default-module-for-created-files", defaultModuleForCreatedFiles)
            if (endorsedDirs.isNotEmpty()) args += "-Djava.endorsed.dirs=${endorsedDirs.joinToString(File.pathSeparator)}"
            if (extDirs.isNotEmpty()) args += "-Djava.ext.dirs=${extDirs.joinToString(File.pathSeparator)}"
            doclintFormat?.let { args.add("--doclint-format", it) }
            if (patchModule.isNotEmpty()) {
                args += "--patch-module"
                for ((key, value) in patchModule)
                    args += "$key=$value"
            }
            if (bootclasspath.isNotEmpty()) args += "-Xbootclasspath:$bootclasspath"
            if (bootclasspathAppend.isNotEmpty()) args += "-Xbootclasspath/a:$bootclasspathAppend"
            if (bootclasspathPrepend.isNotEmpty()) args += "-Xbootclasspath/p:$bootclasspathPrepend"
            diags?.let { args += "-Xdiags:$it" }
            if (doclintRecommended) args += "-Xdoclint"
            if (doclint.isNotEmpty()) args += "-Xdoclint:${doclint.joinToString(",")}"
            if (doclintPackage.isNotEmpty()) args += "-Xdoclint/package:$doclintPackage"
            if (lintRecommended) args += "-Xlint"
            if (lint.isNotEmpty()) args += "-Xlint:${lint.joinToString(",")}"
            if (maxErrors != -1) args.add("-Xmaxerrs", maxErrors)
            if (maxWarnings != -1) args.add("-Xmaxwarns", maxWarnings)
            pkginfo?.let { args += "-Xpkginfo:$it" }
            if (plugin.isNotEmpty()) args += "-Xplugin:\"$plugin\""
            prefer?.let { args += "-Xprefer:$it" }
            if (print) args += "-Xprint"
            if (printProcessorInfo) args += "-XprintProcessorInfo"
            if (printRounds) args += "-XprintRounds"
            stdout?.run { args.add("-Xstdout", absolutePath) }
        }
    }
}

enum class Doc { html4, html5 }
enum class Diags { compact, verbose }

enum class Doclint {
    all, none, accessibility, html, missing, reference, syntax;

    val public
        get() = "$name/public"
    val protected
        get() = "$name/protected"
    val `package`
        get() = "$name/package"
    val private
        get() = "$name/private"
}

enum class Lint {
    /** Enable all warnings */
    all,

    /** Warn about an auxiliary class that is hidden in a source file, and is used from other files. */
    auxiliaryclass,

    /** Warn about use of unnecessary casts. */
    cast,

    /** Warn about issues related to classfile contents. */
    classfile,

    /** Warn about use of deprecated items. */
    deprecation,

    /** Warn about items marked as deprecated in JavaDoc but not using the @Deprecated annotation. */
    `dep-ann`,

    /** Warn about division by constant integer 0. */
    divzero,

    /** Warn about empty statement after if. */
    empty,

    /** Warn about issues regarding module exports. */
    exports,

    /** Warn about falling through from one case of a switch statement to the next. */
    fallthrough,

    /** Warn about finally clauses that do not terminate normally. */
    finally,

    /** Warn about module system related issues. */
    module,

    /** Warn about issues regarding module opens. */
    opens,

    /** Warn about issues relating to use of command line options. */
    options,

    /** Warn about issues regarding method overloads. */
    overloads,

    /** Warn about issues regarding method overrides. */
    overrides,

    /** Warn about invalid path elements on the command line. */
    path,

    /** Warn about issues regarding annotation processing. */
    processing,

    /** Warn about use of raw types. */
    rawtypes,

    /** Warn about use of API that has been marked for removal. */
    removal,

    /** Warn about use of automatic modules in the requires clauses. */
    `requires-automatic`,

    /** Warn about automatic modules in requires transitive. */
    `requires-transitive-automatic`,

    /** Warn about Serializable classes that do not provide a serial version ID. Also warn about access to
     *  non-public members from a serializable element. */
    serial,

    /** Warn about accessing a static member using an instance. */
    static,

    /** Warn about issues relating to use of try blocks (i.e. try-with-resources). */
    `try`,

    /** Warn about unchecked operations. */
    unchecked,

    /** Warn about potentially unsafe vararg methods */
    varargs,

    /** Warn about use of preview language features */
    preview,

    /** Disable all warnings */
    none,
}

enum class Pkginfo { always, legacy, nonempty }

enum class Prefer { source, newer }