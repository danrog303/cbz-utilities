package com.github.danrog303.cbzutilities
import com.github.danrog303.cbzutilities.actions.CreateAction
import com.github.danrog303.cbzutilities.actions.ExtractAction
import com.github.danrog303.cbzutilities.actions.InfoAction
import com.github.danrog303.cbzutilities.models.ExpectedComicInformationEntries
import net.sourceforge.argparse4j.ArgumentParsers
import net.sourceforge.argparse4j.inf.ArgumentParserException
import kotlin.system.exitProcess

/**
 * Starting point of the program.
 * Parses the arguments specified by the user - and based on that passes control to one of the actions.
 */
fun main(args: Array<String>) {
    val parser = ArgumentParsers.newFor("cbz-utilities").build()
    parser.description("CLI tool for creating comic book files.")

    // Initialization of sub parsers
    val subParsers = parser.addSubparsers()
    val createAction = subParsers.addParser("create").help("create new cbz file")
    val extractAction = subParsers.addParser("extract").help("extract files from existing cbz file")
    val infoAction = subParsers.addParser("info").help("display information about specific cbz file")

    // Adding default arguments, which are used to distinguish which action has been requested
    createAction.setDefaults(mapOf("action" to "create"))
    extractAction.setDefaults(mapOf("action" to "extract"))
    infoAction.setDefaults(mapOf("action" to "info"))

    // Adding arguments for "cbz-utilities create" sub action
    // Most of the arguments are generated dynamically, based on ExpectedComicInformationEntries file
    createAction.addArgument("input").help("directory with images to be converted to cbz")
    createAction.addArgument("output").help("path and name of the output file")
    for (expectedInformationEntry in ExpectedComicInformationEntries.expectedEntries) {
        createAction.addArgument("--${expectedInformationEntry.key}")
            .help("set ${expectedInformationEntry.xmlTagName.lowercase()} metadata")
    }

    // Parsing arguments and passing control to one of the actions
    try {
        val parsedArgs = parser.parseArgs(args)

        when(parsedArgs.attrs["action"]) {
            "create" -> CreateAction(parsedArgs.attrs).run()
            "extract" -> ExtractAction(parsedArgs.attrs).run()
            "info" -> InfoAction(parsedArgs.attrs).run()
        }
    } catch (e: ArgumentParserException) {
        parser.handleError(e)
        exitProcess(1)
    }
}
