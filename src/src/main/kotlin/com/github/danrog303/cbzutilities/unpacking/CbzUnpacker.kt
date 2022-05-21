package com.github.danrog303.cbzutilities.unpacking
import org.zeroturnaround.zip.ZipUtil
import java.io.File

/**
 * Takes cbz file and extracts its content to the specified directory
 * @param cbzFilePath Cbz file to be extracted
 * @param outputPath Directory where the extracted files will be copied
 */
class CbzUnpacker(private val cbzFilePath: String, private val outputPath: String) {
    fun unpack() {
        ZipUtil.unpack(File(cbzFilePath), File(outputPath))
    }
}
