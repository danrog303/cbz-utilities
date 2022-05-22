package com.github.danrog303.cbzutilities.models

/**
 * Stores data about valid keys that can be used in [ComicInfo] object.
 * This data can be accessed by calling [ExpectedComicInformationEntries.expectedEntries] static field.
 */
class ExpectedComicInformationEntries {
    companion object {
        val expectedEntries = listOf(
            /* Adding value like this means that calling >cbz-utilities create --title XYZ<
            will be mapped to <Title>XYZ</Title> entry in ComicInfo.xml file. */
            ComicInformationEntry("title", "Title", false),

            ComicInformationEntry("series", "Series", false),
            ComicInformationEntry("volume", "Volume", true),
            ComicInformationEntry("genre", "Genre", false),
            ComicInformationEntry("writer", "Writer", false),
            ComicInformationEntry("publisher", "Publisher", false)
        )
    }
}