package kts

@DslMarker
annotation class KotlinMarker

operator fun String.invoke(args: ArrayList<String>): String {
    args.add(0, this)
    val process = ProcessBuilder(args)
        .inheritIO()
        //        .directory(workingDir)
        //        .redirectOutput(Redirect.INHERIT)
        //        .redirectError(Redirect.INHERIT)
        .start()
    //        .waitFor(60, TimeUnit.MINUTES)
    return String(process.inputStream.readAllBytes())
}

fun main() {
    java {
        options {
            version
        }
        println(java.cmdLine())
    }

    javac {
        options {
            version
        }
        println(javac.cmdLine())
    }

//    val javac = JavaC().invoke {
//        extraOptions {
//            doclint {
//                +Doclint.all
//                -Doclint.html.`package`
//            }
//        }
//    }
//    println(javac.cmdLine)
}

fun ArrayList<String>.add(key: String, value: Any) {
    add(key)
    add(value.toString())
}