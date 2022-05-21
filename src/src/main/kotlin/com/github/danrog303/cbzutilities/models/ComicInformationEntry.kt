package com.github.danrog303.cbzutilities.models;

/**
 * Represents single metadata entry that can be used in cbz-utilities program.
 * [ComicInformationEntry] maps key name (like "--author") to an XML equivalent (like ＜Author＞).
 * All expected comic information entries are stored in [ExpectedComicInformationEntries] class.
 */
data class ComicInformationEntry(val key: String, val xmlTagName: String, val isNumeric: Boolean) {
}
