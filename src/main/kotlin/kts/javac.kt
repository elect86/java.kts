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

    val sourceFiles: ArrayList<File> by javac::sourceFiles
}

class JavaC {

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

    val sourceFiles = ArrayList<File>()

    operator fun invoke(): String {
        val cmd = "kotlinc"
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

        print(cmd)
        return cmd(args)
    }

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
}
