package com.github.danrog303.cbzutilities.models

/**
 * Stores data about valid keys that can be used in [ComicInfo] object.
 * This data can be accessed by calling [ExpectedComicInformationEntries.expectedEntries] static field.
 */
class ExpectedComicInformationEntries {
    companion object {
        val expectedEntries = listOf(
            ComicInformationEntry("title", "Title", false),
            ComicInformationEntry("series", "Series", false),
            ComicInformationEntry("volume", "Volume", true),
            ComicInformationEntry("genre", "Genre", false),
            ComicInformationEntry("writer", "Writer", false),
            ComicInformationEntry("publisher", "Publisher", false)
        )
    }
}