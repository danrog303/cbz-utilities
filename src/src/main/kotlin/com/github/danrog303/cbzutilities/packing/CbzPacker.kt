package com.github.danrog303.cbzutilities.packing
import com.github.danrog303.cbzutilities.models.ComicInfo
import org.zeroturnaround.zip.ZipUtil
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

/**
 * Takes [ComicInfo] object with input and output paths and creates cbz file with correct metadata
 * created by [ComicInfoXmlCreator] class.
 * @param comicInfo Object that stores comic book metadata
 * @param inputPath Path to directory that contains comic book images
 * @param outputPath Path to cbz file that will be created
 */
class CbzPacker(private val comicInfo: ComicInfo, private val inputPath: String,
                private val outputPath: String, private val coverImagePath: String? = null) {
    fun pack() {
        val cbzTempDir = File(Files.createTempDirectory("cbz-packer-").toFile().absolutePath)
        val comicContentTempDir = File(Paths.get(cbzTempDir.absolutePath, "ComicContent").toString())
        File(inputPath).copyRecursively(comicContentTempDir)

        val xmlCreator = ComicInfoXmlCreator(comicInfo)
        xmlCreator.generateXml(Paths.get(cbzTempDir.absolutePath, "ComicInfo.xml").toString())

        if (coverImagePath != null) {
            CbzCoverChange(comicContentTempDir.absolutePath).addCover(coverImagePath)
        }

        val targetOutput = Paths.get(outputPath).toFile()
        ZipUtil.pack(cbzTempDir, targetOutput)
        cbzTempDir.deleteRecursively()
    }
}
