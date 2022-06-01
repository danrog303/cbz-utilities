package com.github.danrog303.cbzutilities.packing;
import java.io.File
import java.nio.file.Paths

/**
 * Normally, when you create CBZ file, first page of first chapter is treated as a comic book cover.
 * This class helps to rearrange files in comic book directory and allows specifying new first file which then
 * will be used as a front cover image.
 */
class CbzCoverChange(private val comicContentDirectory: String) {

    /**
     * Recursively changes names of all graphic files (from any name to Page_number).
     */
    private fun rearrangeDirectoryStructure() {
        var currentIndex = 1

        File(comicContentDirectory).walkTopDown().forEach {
            if (it.isDirectory) {
                currentIndex = 1
            } else if (isImageFile(it)) {
                val parentDirectory = it.parentFile.absolutePath
                val newFileName = "Page_${currentIndex}.${it.extension}"
                it.renameTo(Paths.get(parentDirectory, newFileName).toFile())

                currentIndex++
            }
        }
    }

    /**
     * Rearranges directory structure and inserts new cover file.
     * This method modifies file names and inserts new files, so make sure to call it only on copy of comic book
     * directory (not on the directory specified by user).
     */
    fun addCover(coverImagePath: String) {
        rearrangeDirectoryStructure()

        val ccdDirectory = File(comicContentDirectory)
        var coverDirectory: File = ccdDirectory

        // Normally, cover should be placed in root of ComicContent (so in ccdDirectory)
        // But when ComicContent contains some directories (chapters), cover should be placed in first chapter instead
        for (it in ccdDirectory.listFiles()) {
            if (it.isDirectory) {
                coverDirectory = it
                break
            }
        }

        val coverFile = File(coverImagePath)
        coverFile.copyTo(Paths.get(coverDirectory.absolutePath, "Page_0.${coverFile.extension}").toFile())
    }

    /**
     * Checks if [file] is an image with one of supported extensions.
     */
    private fun isImageFile(file: File): Boolean {
        return arrayOf("png", "gif", "jpeg", "jpg").contains(file.extension)
    }

}
