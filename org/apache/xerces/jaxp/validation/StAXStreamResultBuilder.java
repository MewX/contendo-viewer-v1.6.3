package org.apache.xerces.jaxp.validation;

import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.Comment;
import javax.xml.stream.events.DTD;
import javax.xml.stream.events.EndDocument;
import javax.xml.stream.events.EntityReference;
import javax.xml.stream.events.ProcessingInstruction;
import javax.xml.stream.events.StartDocument;
import javax.xml.transform.stax.StAXResult;
import org.apache.xerces.util.JAXPNamespaceContextWrapper;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.xni.XMLResourceIdentifier;
import org.apache.xerces.xni.XMLString;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.parser.XMLDocumentSource;

final class StAXStreamResultBuilder implements StAXDocumentHandler {
  private XMLStreamWriter fStreamWriter;
  
  private final JAXPNamespaceContextWrapper fNamespaceContext;
  
  private boolean fIgnoreChars;
  
  private boolean fInCDATA;
  
  private final QName fAttrName = new QName();
  
  public StAXStreamResultBuilder(JAXPNamespaceContextWrapper paramJAXPNamespaceContextWrapper) {
    this.fNamespaceContext = paramJAXPNamespaceContextWrapper;
  }
  
  public void setStAXResult(StAXResult paramStAXResult) {
    this.fIgnoreChars = false;
    this.fInCDATA = false;
    this.fAttrName.clear();
    this.fStreamWriter = (paramStAXResult != null) ? paramStAXResult.getXMLStreamWriter() : null;
  }
  
  public void startDocument(XMLStreamReader paramXMLStreamReader) throws XMLStreamException {
    String str1 = paramXMLStreamReader.getVersion();
    String str2 = paramXMLStreamReader.getCharacterEncodingScheme();
    this.fStreamWriter.writeStartDocument((str2 != null) ? str2 : "UTF-8", (str1 != null) ? str1 : "1.0");
  }
  
  public void endDocument(XMLStreamReader paramXMLStreamReader) throws XMLStreamException {
    this.fStreamWriter.writeEndDocument();
    this.fStreamWriter.flush();
  }
  
  public void comment(XMLStreamReader paramXMLStreamReader) throws XMLStreamException {
    this.fStreamWriter.writeComment(paramXMLStreamReader.getText());
  }
  
  public void processingInstruction(XMLStreamReader paramXMLStreamReader) throws XMLStreamException {
    String str = paramXMLStreamReader.getPIData();
    if (str != null && str.length() > 0) {
      this.fStreamWriter.writeProcessingInstruction(paramXMLStreamReader.getPITarget(), str);
    } else {
      this.fStreamWriter.writeProcessingInstruction(paramXMLStreamReader.getPITarget());
    } 
  }
  
  public void entityReference(XMLStreamReader paramXMLStreamReader) throws XMLStreamException {
    this.fStreamWriter.writeEntityRef(paramXMLStreamReader.getLocalName());
  }
  
  public void startDocument(StartDocument paramStartDocument) throws XMLStreamException {
    String str1 = paramStartDocument.getVersion();
    String str2 = paramStartDocument.getCharacterEncodingScheme();
    this.fStreamWriter.writeStartDocument((str2 != null) ? str2 : "UTF-8", (str1 != null) ? str1 : "1.0");
  }
  
  public void endDocument(EndDocument paramEndDocument) throws XMLStreamException {
    this.fStreamWriter.writeEndDocument();
    this.fStreamWriter.flush();
  }
  
  public void doctypeDecl(DTD paramDTD) throws XMLStreamException {
    this.fStreamWriter.writeDTD(paramDTD.getDocumentTypeDeclaration());
  }
  
  public void characters(Characters paramCharacters) throws XMLStreamException {
    this.fStreamWriter.writeCharacters(paramCharacters.getData());
  }
  
  public void cdata(Characters paramCharacters) throws XMLStreamException {
    this.fStreamWriter.writeCData(paramCharacters.getData());
  }
  
  public void comment(Comment paramComment) throws XMLStreamException {
    this.fStreamWriter.writeComment(paramComment.getText());
  }
  
  public void processingInstruction(ProcessingInstruction paramProcessingInstruction) throws XMLStreamException {
    String str = paramProcessingInstruction.getData();
    if (str != null && str.length() > 0) {
      this.fStreamWriter.writeProcessingInstruction(paramProcessingInstruction.getTarget(), str);
    } else {
      this.fStreamWriter.writeProcessingInstruction(paramProcessingInstruction.getTarget());
    } 
  }
  
  public void entityReference(EntityReference paramEntityReference) throws XMLStreamException {
    this.fStreamWriter.writeEntityRef(paramEntityReference.getName());
  }
  
  public void setIgnoringCharacters(boolean paramBoolean) {
    this.fIgnoreChars = paramBoolean;
  }
  
  public void startDocument(XMLLocator paramXMLLocator, String paramString, NamespaceContext paramNamespaceContext, Augmentations paramAugmentations) throws XNIException {}
  
  public void xmlDecl(String paramString1, String paramString2, String paramString3, Augmentations paramAugmentations) throws XNIException {}
  
  public void doctypeDecl(String paramString1, String paramString2, String paramString3, Augmentations paramAugmentations) throws XNIException {}
  
  public void comment(XMLString paramXMLString, Augmentations paramAugmentations) throws XNIException {}
  
  public void processingInstruction(String paramString, XMLString paramXMLString, Augmentations paramAugmentations) throws XNIException {}
  
  public void startElement(QName paramQName, XMLAttributes paramXMLAttributes, Augmentations paramAugmentations) throws XNIException {
    try {
      if (paramQName.prefix.length() > 0) {
        this.fStreamWriter.writeStartElement(paramQName.prefix, paramQName.localpart, (paramQName.uri != null) ? paramQName.uri : "");
      } else if (paramQName.uri != null) {
        this.fStreamWriter.writeStartElement(paramQName.uri, paramQName.localpart);
      } else {
        this.fStreamWriter.writeStartElement(paramQName.localpart);
      } 
      int i = this.fNamespaceContext.getDeclaredPrefixCount();
      NamespaceContext namespaceContext = this.fNamespaceContext.getNamespaceContext();
      for (byte b1 = 0; b1 < i; b1++) {
        String str1 = this.fNamespaceContext.getDeclaredPrefixAt(b1);
        String str2 = namespaceContext.getNamespaceURI(str1);
        if (str1.length() == 0) {
          this.fStreamWriter.writeDefaultNamespace((str2 != null) ? str2 : "");
        } else {
          this.fStreamWriter.writeNamespace(str1, (str2 != null) ? str2 : "");
        } 
      } 
      i = paramXMLAttributes.getLength();
      for (byte b2 = 0; b2 < i; b2++) {
        paramXMLAttributes.getName(b2, this.fAttrName);
        if (this.fAttrName.prefix.length() > 0) {
          this.fStreamWriter.writeAttribute(this.fAttrName.prefix, (this.fAttrName.uri != null) ? this.fAttrName.uri : "", this.fAttrName.localpart, paramXMLAttributes.getValue(b2));
        } else if (this.fAttrName.uri != null) {
          this.fStreamWriter.writeAttribute(this.fAttrName.uri, this.fAttrName.localpart, paramXMLAttributes.getValue(b2));
        } else {
          this.fStreamWriter.writeAttribute(this.fAttrName.localpart, paramXMLAttributes.getValue(b2));
        } 
      } 
    } catch (XMLStreamException xMLStreamException) {
      throw new XNIException(xMLStreamException);
    } 
  }
  
  public void emptyElement(QName paramQName, XMLAttributes paramXMLAttributes, Augmentations paramAugmentations) throws XNIException {
    startElement(paramQName, paramXMLAttributes, paramAugmentations);
    endElement(paramQName, paramAugmentations);
  }
  
  public void startGeneralEntity(String paramString1, XMLResourceIdentifier paramXMLResourceIdentifier, String paramString2, Augmentations paramAugmentations) throws XNIException {}
  
  public void textDecl(String paramString1, String paramString2, Augmentations paramAugmentations) throws XNIException {}
  
  public void endGeneralEntity(String paramString, Augmentations paramAugmentations) throws XNIException {}
  
  public void characters(XMLString paramXMLString, Augmentations paramAugmentations) throws XNIException {
    if (!this.fIgnoreChars)
      try {
        if (!this.fInCDATA) {
          this.fStreamWriter.writeCharacters(paramXMLString.ch, paramXMLString.offset, paramXMLString.length);
        } else {
          this.fStreamWriter.writeCData(paramXMLString.toString());
        } 
      } catch (XMLStreamException xMLStreamException) {
        throw new XNIException(xMLStreamException);
      }  
  }
  
  public void ignorableWhitespace(XMLString paramXMLString, Augmentations paramAugmentations) throws XNIException {
    characters(paramXMLString, paramAugmentations);
  }
  
  public void endElement(QName paramQName, Augmentations paramAugmentations) throws XNIException {
    try {
      this.fStreamWriter.writeEndElement();
    } catch (XMLStreamException xMLStreamException) {
      throw new XNIException(xMLStreamException);
    } 
  }
  
  public void startCDATA(Augmentations paramAugmentations) throws XNIException {
    this.fInCDATA = true;
  }
  
  public void endCDATA(Augmentations paramAugmentations) throws XNIException {
    this.fInCDATA = false;
  }
  
  public void endDocument(Augmentations paramAugmentations) throws XNIException {}
  
  public void setDocumentSource(XMLDocumentSource paramXMLDocumentSource) {}
  
  public XMLDocumentSource getDocumentSource() {
    return null;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/jaxp/validation/StAXStreamResultBuilder.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */