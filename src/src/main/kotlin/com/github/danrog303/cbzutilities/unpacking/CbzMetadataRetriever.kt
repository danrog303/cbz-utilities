package com.github.danrog303.cbzutilities.unpacking;
import com.github.danrog303.cbzutilities.models.ComicInfo
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

/**
 * Temporarly decompresses cbz file and calls [ComicInfoXmlReader] to return its metadata.
 * @param cbzFilePath File to analyse
 */
class CbzMetadataRetriever(private val cbzFilePath: String) {
    fun readMetadata(): ComicInfo? {
        val cbzTempDir = File(Files.createTempDirectory("cbz-metadata-retrieve-").toFile().absolutePath)
        val expectedMetadataFile = Paths.get(cbzTempDir.absolutePath, "ComicInfo.xml").toFile()

        try {
            CbzUnpacker(cbzFilePath, cbzTempDir.absolutePath).unpack()
            return if(expectedMetadataFile.exists()) {
                ComicInfoXmlReader(expectedMetadataFile.absolutePath).read()
            } else {
                null
            }
        } finally {
            cbzTempDir.deleteRecursively()
        }
    }
}
