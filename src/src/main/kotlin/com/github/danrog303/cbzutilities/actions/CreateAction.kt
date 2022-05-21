package com.github.danrog303.cbzutilities.actions
import com.github.danrog303.cbzutilities.models.ComicInfo
import com.github.danrog303.cbzutilities.models.ExpectedComicInformationEntries
import com.github.danrog303.cbzutilities.packing.CbzPacker

/**
 * Maps values specified in args collection to [ComicInfo] object and calls [CbzPacker] to create valid cbz file.
 * @param args Parsed console arguments generated by main function
 */
class CreateAction(private val args: Map<String, Any>): Runnable {
    override fun run() {
        val comicInfo = ComicInfo()
        for (expectedInformationEntry in ExpectedComicInformationEntries.expectedEntries) {
            comicInfo.set(expectedInformationEntry.key, args[expectedInformationEntry.key]?.toString())
        }

        CbzPacker(comicInfo, args["input"].toString(), args["output"].toString()).pack()
    }
}
