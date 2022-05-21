package com.github.danrog303.cbzutilities.models

/**
 * Stores metadata of single comic book in key-value pair collection.
 * List of valid keys is specified by [ExpectedComicInformationEntries] class.
 */
class ComicInfo {
    private val metadata = mutableMapOf<String, String?>()

    init {
        for (expectedInformationEntry in ExpectedComicInformationEntries.expectedEntries) {
            metadata[expectedInformationEntry.key] = null
        }
    }

    fun set(key: String, value: String?) {
        if (metadata.keys.contains(key)) {
            metadata[key] = value
        }
    }

    fun get(key: String): String? {
        return metadata.getOrDefault(key, null)
    }
}