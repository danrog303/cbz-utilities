package com.github.danrog303.cbzutilities.unpacking
import com.github.danrog303.cbzutilities.models.ComicInfo
import com.github.danrog303.cbzutilities.models.ExpectedComicInformationEntries
import org.xml.sax.InputSource
import java.io.File
import java.io.InputStream
import java.io.StringReader
import javax.xml.parsers.DocumentBuilderFactory

/**
 * Generates [ComicInfo] object based on data from specified xml file
 * @param xmlFilePath XML file to parse
 */
class ComicInfoXmlReader(private val xmlFilePath: String) {
    fun read(): ComicInfo {
        val result = ComicInfo()
        val docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
        val doc = docBuilder.parse(File(xmlFilePath))

        for (expectedInformationEntry in ExpectedComicInformationEntries.expectedEntries) {
            val expectedTags = doc.documentElement.getElementsByTagName(expectedInformationEntry.xmlTagName)

            if (expectedTags.length > 0) {
                result.set(expectedInformationEntry.key, expectedTags.item(0).textContent)
            }
        }

        return result
    }
}