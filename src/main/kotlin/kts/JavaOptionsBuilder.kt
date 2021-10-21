package kts

import java.io.File

// https://www.oracle.com/java/technologies/javase/vmoptions-jsp.html

@KotlinMarker
class JavaOptionsBuilder(val java: Java) {

    /** to select the "zero" VM */
    val zero: Unit
        get() {
            java.zero = true
        }

    /** to select the "dcevm" VM */
    val dcevm: Unit
        get() {
            java.dcevm = true
        }

    /** list of directories, JAR archives, and ZIP archives to search for class files. */
    val classPath: ArrayList<String> by java::classPath

    /** list of directories, each directory is a directory of modules. */
    val modulePath: ArrayList<String> by java::modulePath

    /** list of directories, each directory is a directory of modules that replace upgradeable modules in the
     *  runtime image */
    val upgradeModulePath: ArrayList<String> by java::upgradeModulePath

    /** list observable modules and exit */
    val listModules: Unit
        get() {
            java.listModules = true
        }

    /** describe a module and exit */
    var describeModule: String
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            java.describeModule = value
        }

    /** create VM and load main class but do not execute main method. The --dry-run option may be useful for validating
     *  the command-line options such as the module system configuration. */
    val dryRun: Unit
        get() {
            java.dryRun = true
        }

    /** validate all modules and exit. The --validate-modules option may be useful for finding conflicts and other
     *  errors with modules on the module path. */
    val validateModules: Unit
        get() {
            java.validateModules = true
        }

    /** set a system property */
    val systemProperties: MutableMap<String, String> by java::systemProperties

    /** enable verbose output */
    var verbose: Java.Verbose
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            java.verbose = value
        }

    /** print product version to the error stream and exit */
    val versionSerr: Unit
        get() {
            java.versionSerr = true
        }

    /** print product version to the output stream and exit */
    val version: Unit
        get() {
            java.version = true
        }

    /** print product version to the error stream and continue */
    val showVersionSerr: Unit
        get() {
            java.showVersionSerr = true
        }

    /** print product version to the output stream and continue */
    val showVersion: Unit
        get() {
            java.showVersion = true
        }

    /** show module resolution output during startup */
    val showModuleResolution: Unit
        get() {
            java.showModuleResolution = true
        }

    /** print this help message to the error stream */
    val helpSerr: Unit
        get() {
            java.helpSerr = true
        }

    /** print this help message to the output stream */
    val help: Unit
        get() {
            java.help = true
        }

    /** print help on extra options to the error stream */
    val helpExtraSerr: Unit
        get() {
            java.helpExtraSerr = true
        }

    /** print help on extra options to the output stream */
    val helpExtra: Unit
        get() {
            java.helpExtra = true
        }

    /** enable assertions with specified granularity */
    val enableAssertions: ArrayList<String> by java::enableAssertions

    /** disable assertions with specified granularity */
    val disableAssertions: ArrayList<String> by java::disableAssertions

    /** system assertions */
    var systemAssertions: Boolean
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            java.systemAssertions = value
        }

    /** load native agent library <libname>, e.g. -agentlib:jdwp. See also -agentlib:jdwp=help */
    var agentLib: String
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            java.agentLib = value
        }

    /** load native agent library by full pathname */
    var agentPath: String
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            java.agentPath = value
        }

    /** load Java programming language agent, see java.lang.instrument */
    var javaAgent: String
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            java.javaAgent = value
        }

    /** show splash screen with specified image HiDPI scaled images are automatically supported and used if available.
     *  The unscaled image filename, e.g. image.ext, should always be passed as the argument to the -splash option.
     *  The most appropriate scaled image provided will be picked up automatically.
     *  See the SplashScreen API documentation for more information */
    var splash: File
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            java.splash = value
        }

    /** one or more argument files containing options */
    val argumentFiles: ArrayList<File> by java::argumentFiles

    /** prevent further argument file expansion */
    var disableFiles: Boolean
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            java.disableFiles = value
        }

    /** allow classes to depend on preview features of this release */
    var enablePreview: Boolean
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            java.enablePreview = value
        }


    fun extraOptions(block: ExtraOptions.() -> Unit) = ExtraOptions().block()
    fun X(block: ExtraOptions.() -> Unit) = ExtraOptions().block()

    inner class ExtraOptions {

        /** disable background compilation */
        val batch: Unit
            get() {
                java.batch = true
            }

        /** append to end of bootstrap class path */
        val bootClasspath: ArrayList<String> by java::bootClasshpath

        /** perform additional checks for JNI functions */
        val chechJni: Unit
            get() {
                java.checkJni = true
            }

        /** forces compilation of methods on first invocation */
        val comp: Unit
            get() {
                java.comp = true
            }

        /** provided for backward compatibility */
        val debug: Unit
            get() {
                java.debug = true
            }

        /** show additional diagnostic messages */
        val diag: Unit
            get() {
                java.diag = true
            }

        /** enable strictest checks, anticipating future default */
        val future: Unit
            get() {
                java.future = true
            }

        /** interpreted mode execution only */
        val int: Unit
            get() {
                java.int = true
            }

        /** displays more detailed JVM version information than the `version` option */
        val internalVersion: Unit
            get() {
                java.internalVersion = true
            }

        /** log GC status to a file with time stamps */
        var logGC: File
            @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
            set(value) {
                java.logGC = value
            }

        /** mixed mode execution (default) */
        val mixed: Unit
            get() {
                java.mixed = true
            }

        /** sets the initial and maximum size (in bytes) of the heap for the young generation (nursery) */
        var memory: String
            @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
            set(value) {
                java.mn = value
            }

        /** set initial Java heap size */
        var memoryInitial: String
            @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
            set(value) {
                java.ms = value
            }

        /** set maximum Java heap size */
        var memoryMaximum: String
            @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
            set(value) {
                java.mm = value
            }

        /** disable class garbage collection */
        val noClassGC: Unit
            get() {
                java.noClassGC = true
            }

        /** reduce use of OS signals by Java/VM (see documentation) */
        val rs: Unit
            get() {
                java.rs = true
            }

        /** shared class data usage */
        var share: Java.Share
            @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
            set(value) {
                java.share = value
            }

        /** show settings and continue */
        var showSettings: Java.Settings
            @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
            set(value) {
                java.showSettings = value
            }

        /** set java thread stack size */
        var ss: String
            @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
            set(value) {
                java.ss = value
            }

        /** sets the mode of the bytecode verifier */
        val verify: Unit
            get() {
                java.verify = true
            }

        /** <module>=<target-module>(,<target-module>)*
         *  updates <module> to read <target-module>, regardless of module declaration.
         *  <target-module> can be ALL-UNNAMED to read all unnamed modules. */
        val addReads: ArrayList<String> by java::addReads

        /** <module>/<package>=<target-module>(,<target-module>)*
         *  updates <module> to export <package> to <target-module>, regardless of module declaration.
         *  <target-module> can be ALL-UNNAMED to export to all unnamed modules. */
        val addExports: ArrayList<String> by java::addExports

        /** <module>/<package>=<target-module>(,<target-module>)*
         *  updates <module> to open <package> to <target-module>, regardless of module declaration. */
        val addOpens: ArrayList<String> by java::addOpens

        /** permit or deny access to members of types in named modules by code in unnamed modules.
         *  This option will be removed in a future release. */
        var illegalAccess: Java.Access
            @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
            set(value) {
                java.illegalAccess = value
            }

        /** <module name>[,<module name>...]
         *  limit the universe of observable modules */
        val limitModules: ArrayList<String> by java::limitModules

        /** <module>=<file>(:<file>)*
         *  override or augment a module with classes and resources in JAR files or directories. */
        val patchModule: MutableMap<String, ArrayList<File>> by java::patchModule

        /** set the version of the source in source-file mode. */
        var source: Int
            @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
            set(value) {
                java.source = value
            }
    }
}