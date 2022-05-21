package com.github.danrog303.cbzutilities.packing
import com.github.danrog303.cbzutilities.models.ComicInfo
import com.github.danrog303.cbzutilities.models.ExpectedComicInformationEntries
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

/**
 * Takes [ComicInfo] object and creates metadata XML file.
 * @param comicInfo Object that stores comic book metadata
 */
class ComicInfoXmlCreator(private val comicInfo: ComicInfo) {
    fun generateXml(xmlOutputPath: String) {
        val docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
        val doc = docBuilder.newDocument()

        val rootElement = doc.createElement("ComicInfo")

        // Write xmlns attributes
        rootElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance")
        rootElement.setAttribute("xmlns:xsd", "http://www.w3.org/2001/XMLSchema")

        for (expectedInformationEntry in ExpectedComicInformationEntries.expectedEntries) {
           val metadataValue = comicInfo.get(expectedInformationEntry.key)

            if (metadataValue != null) {
                val xmlElement = doc.createElement(expectedInformationEntry.xmlTagName)
                xmlElement.appendChild(doc.createTextNode(metadataValue))
                rootElement.appendChild(xmlElement)
            }
        }

        // Append root element to the document
        doc.appendChild(rootElement)

        // Write XML with human-readable formatting
        val transformer = TransformerFactory.newInstance().newTransformer()
        transformer.setOutputProperty(OutputKeys.INDENT, "yes")
        transformer.setOutputProperty("{https://xml.apache.org/xslt}indent-amount", "4")
        transformer.transform(DOMSource(doc), StreamResult(File(xmlOutputPath)))
    }
}