package kts

import java.io.File

inline fun java(block: JavaBuilder.() -> Unit) {
    val java = Java()
    JavaBuilder(java).block()
    java()
}

class Java(override val cmd: String = "java") : Cmd<JavaBuilder> {

    var zero = false
    var dcevm = false
    val classPath = ArrayList<File>()
    val modulePath = ArrayList<String>()
    val upgradeModulePath = ArrayList<String>()
    var listModules = false
    var describeModule = ""
    var dryRun = false
    var validateModules = false
    val systemProperties = mutableMapOf<String, String>()
    var verbose: Verbose? = null
    var versionSerr = false
    var version = false
    var showVersionSerr = false
    var showVersion = false
    var showModuleResolution = false
    var helpSerr = false
    var help = false
    var helpExtraSerr = false
    var helpExtra = false
    val enableAssertions = ArrayList<String>()
    val disableAssertions = ArrayList<String>()
    var systemAssertions: Boolean? = null
    var agentLib = ""
    var agentPath = ""
    var javaAgent = ""
    var splash: File? = null
    val argumentFiles = ArrayList<File>()
    var disableFiles = false
    var enablePreview = false

    // Extra, `-X`
    var batch = false
    val bootClasshpath = ArrayList<String>()
    var checkJni = false
    var comp = false
    var debug = false
    var diag = false
    var future = false
    var int = false
    var internalVersion = false
    var logGC: File? = null
    var mixed = false
    var mn = ""
    var ms = ""
    var mm = ""
    var noClassGC = false
    var rs = false
    var share: Share? = null
    var showSettings: Settings? = null
    var ss = ""
    var verify = false
    val addReads = ArrayList<String>()
    val addExports = ArrayList<String>()
    val addOpens = ArrayList<String>()
    var illegalAccess: Access? = null
    val limitModules = ArrayList<String>()
    val patchModule = mutableMapOf<String, ArrayList<File>>()
    var source = -1


    var mainClass = ""
    var jar: File? = null
    var module = ""
    var sourceFile: File? = null

    var custom = ""

    val arguments = ArrayList<String>()

    override fun cmdLine(): List<String> {
        val args = arrayListOf<String>()
        if (zero) args += "-zero"
        if (dcevm) args += "-dcevm"
        if (classPath.isNotEmpty()) args.add("-cp", classPath.joinToString(File.pathSeparator) { it.absolutePath })
        if (modulePath.isNotEmpty()) args.add("-p", modulePath.joinToString(File.pathSeparator))
        if (upgradeModulePath.isNotEmpty()) args.add("--upgrade-module-path", upgradeModulePath.joinToString(File.pathSeparator))
        if (listModules) args + "--list-modules"
        if (describeModule.isNotEmpty()) args.add("-d", describeModule)
        if (dryRun) args += "--dry-run"
        if (validateModules) args += "--validate-modules"
        for ((key, value) in systemProperties) args += "-D$key=$value"
        verbose?.let { args += "-verbose:$it" }
        if (versionSerr) args += "-version"
        if (version) args += "--version"
        if (showVersionSerr) args += "-showversion"
        if (showVersion) args += "--show-version"
        if (showModuleResolution) args += "--show-module-resolution"
        if (helpSerr) args += "-h"
        if (help) args += "--help"
        if (helpExtraSerr) args += "-X"
        if (helpExtra) args += "--help-extra"
        for (ea in enableAssertions) args += "-ea:$ea"
        for (da in disableAssertions) args += "-da:$da"
        systemAssertions?.let { args += if (it) "-esa" else "-dsa" }
        if (agentLib.isNotEmpty()) args += "-agentlib:$agentLib"
        if (agentPath.isNotEmpty()) args += "-agentpath:$agentPath"
        if (javaAgent.isNotEmpty()) args += "-javaagent:$javaAgent"
        splash?.run { args += "-splash:$absolutePath" }
        for (af in argumentFiles) args += "@${af.absolutePath}"
        if (disableFiles) args += "-disable-@files"
        if (enablePreview) args += "--enable-preview"

        // extra
        if (batch) args += "-Xbatch"
        if (bootClasshpath.isNotEmpty()) args += "-Xbootclasspath/a${bootClasshpath.joinToString(File.pathSeparator)}"
        if (checkJni) args += "-Xcheck:jni"
        if (comp) args += "-Xcomp"
        if (debug) args += "-Xdebug"
        if (diag) args += "-Xdiag"
        if (future) args += "-Xfuture"
        if (int) args += "-Xint"
        if (internalVersion) args += "-Xinternalversion"
        logGC?.run { args += "-Xloggc:$absolutePath" }
        if (mixed) args += "-Xmixed"
        if (mn.isNotEmpty()) args += "-Xmn$mn"
        if (ms.isNotEmpty()) args += "-Xms$ms"
        if (mm.isNotEmpty()) args += "-Xmm$mm"
        if (noClassGC) args += "-Xnoclassgc"
        if (rs) args += "-Xrs"
        share?.let { args += "-Xshare:$it" }
        showSettings?.let { args += "-XshowSettings:$it" }
        if (ss.isNotEmpty()) args += "-Xss$ss"
        if (verify) args += "-Xverify"
        if (addReads.isNotEmpty()) args.add("--add-reads", addReads.joinToString(","))
        if (addExports.isNotEmpty()) args.add("--add-exports", addExports.joinToString(","))
        if (addOpens.isNotEmpty()) args.add("--add-opens", addOpens.joinToString(","))
        illegalAccess?.let { args += "--illegal-access=$it" }
        if (limitModules.isNotEmpty()) args.add("--limit-modules", limitModules.joinToString(","))
        for ((key, value) in patchModule) args.add("--patch-module", "$key=${value.joinToString(File.pathSeparator) { it.absolutePath }}")
        if (source != -1) args.add("--source", source)

        if (custom.isNotEmpty())
            args += custom

        when {
            mainClass.isNotEmpty() -> args += mainClass
            jar != null -> args += jar!!.absolutePath
            module.isNotEmpty() -> args += module
            sourceFile != null -> args += sourceFile!!.absolutePath
        }

        args += arguments

        //        print(cmd)
        return args
    }

    override fun invoke(block: JavaBuilder.() -> Unit) = apply { JavaBuilder(this).block() }

    enum class Verbose { `class`, module, gc, jni }
    enum class Share {
        /** use shared class data if possible (default) */
        auto,

        /** do not attempt to use shared class data */
        off,

        /** require using shared class data, otherwise fail. */
        on
    }

    enum class Settings {
        /** all settings */
        all,

        /** all locale related settings */
        locale,

        /** all property settings */
        properties,

        /** all vm related settings */
        vm,

        /** (Linux Only) host system or container configuration */
        system
    }

    enum class Access { deny, permit, warn, debug }
}