package org.apache.xerces.parsers;

import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.xni.XMLDTDContentModelHandler;
import org.apache.xerces.xni.XMLDTDHandler;
import org.apache.xerces.xni.XMLDocumentHandler;
import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.xni.XMLResourceIdentifier;
import org.apache.xerces.xni.XMLString;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.parser.XMLDTDContentModelSource;
import org.apache.xerces.xni.parser.XMLDTDSource;
import org.apache.xerces.xni.parser.XMLDocumentSource;
import org.apache.xerces.xni.parser.XMLParserConfiguration;

public abstract class AbstractXMLDocumentParser extends XMLParser implements XMLDTDContentModelHandler, XMLDTDHandler, XMLDocumentHandler {
  protected boolean fInDTD;
  
  protected XMLDocumentSource fDocumentSource;
  
  protected XMLDTDSource fDTDSource;
  
  protected XMLDTDContentModelSource fDTDContentModelSource;
  
  protected AbstractXMLDocumentParser(XMLParserConfiguration paramXMLParserConfiguration) {
    super(paramXMLParserConfiguration);
    paramXMLParserConfiguration.setDocumentHandler(this);
    paramXMLParserConfiguration.setDTDHandler(this);
    paramXMLParserConfiguration.setDTDContentModelHandler(this);
  }
  
  public void startDocument(XMLLocator paramXMLLocator, String paramString, NamespaceContext paramNamespaceContext, Augmentations paramAugmentations) throws XNIException {}
  
  public void xmlDecl(String paramString1, String paramString2, String paramString3, Augmentations paramAugmentations) throws XNIException {}
  
  public void doctypeDecl(String paramString1, String paramString2, String paramString3, Augmentations paramAugmentations) throws XNIException {}
  
  public void startElement(QName paramQName, XMLAttributes paramXMLAttributes, Augmentations paramAugmentations) throws XNIException {}
  
  public void emptyElement(QName paramQName, XMLAttributes paramXMLAttributes, Augmentations paramAugmentations) throws XNIException {
    startElement(paramQName, paramXMLAttributes, paramAugmentations);
    endElement(paramQName, paramAugmentations);
  }
  
  public void characters(XMLString paramXMLString, Augmentations paramAugmentations) throws XNIException {}
  
  public void ignorableWhitespace(XMLString paramXMLString, Augmentations paramAugmentations) throws XNIException {}
  
  public void endElement(QName paramQName, Augmentations paramAugmentations) throws XNIException {}
  
  public void startCDATA(Augmentations paramAugmentations) throws XNIException {}
  
  public void endCDATA(Augmentations paramAugmentations) throws XNIException {}
  
  public void endDocument(Augmentations paramAugmentations) throws XNIException {}
  
  public void startGeneralEntity(String paramString1, XMLResourceIdentifier paramXMLResourceIdentifier, String paramString2, Augmentations paramAugmentations) throws XNIException {}
  
  public void textDecl(String paramString1, String paramString2, Augmentations paramAugmentations) throws XNIException {}
  
  public void endGeneralEntity(String paramString, Augmentations paramAugmentations) throws XNIException {}
  
  public void comment(XMLString paramXMLString, Augmentations paramAugmentations) throws XNIException {}
  
  public void processingInstruction(String paramString, XMLString paramXMLString, Augmentations paramAugmentations) throws XNIException {}
  
  public void setDocumentSource(XMLDocumentSource paramXMLDocumentSource) {
    this.fDocumentSource = paramXMLDocumentSource;
  }
  
  public XMLDocumentSource getDocumentSource() {
    return this.fDocumentSource;
  }
  
  public void startDTD(XMLLocator paramXMLLocator, Augmentations paramAugmentations) throws XNIException {
    this.fInDTD = true;
  }
  
  public void startExternalSubset(XMLResourceIdentifier paramXMLResourceIdentifier, Augmentations paramAugmentations) throws XNIException {}
  
  public void endExternalSubset(Augmentations paramAugmentations) throws XNIException {}
  
  public void startParameterEntity(String paramString1, XMLResourceIdentifier paramXMLResourceIdentifier, String paramString2, Augmentations paramAugmentations) throws XNIException {}
  
  public void endParameterEntity(String paramString, Augmentations paramAugmentations) throws XNIException {}
  
  public void ignoredCharacters(XMLString paramXMLString, Augmentations paramAugmentations) throws XNIException {}
  
  public void elementDecl(String paramString1, String paramString2, Augmentations paramAugmentations) throws XNIException {}
  
  public void startAttlist(String paramString, Augmentations paramAugmentations) throws XNIException {}
  
  public void attributeDecl(String paramString1, String paramString2, String paramString3, String[] paramArrayOfString, String paramString4, XMLString paramXMLString1, XMLString paramXMLString2, Augmentations paramAugmentations) throws XNIException {}
  
  public void endAttlist(Augmentations paramAugmentations) throws XNIException {}
  
  public void internalEntityDecl(String paramString, XMLString paramXMLString1, XMLString paramXMLString2, Augmentations paramAugmentations) throws XNIException {}
  
  public void externalEntityDecl(String paramString, XMLResourceIdentifier paramXMLResourceIdentifier, Augmentations paramAugmentations) throws XNIException {}
  
  public void unparsedEntityDecl(String paramString1, XMLResourceIdentifier paramXMLResourceIdentifier, String paramString2, Augmentations paramAugmentations) throws XNIException {}
  
  public void notationDecl(String paramString, XMLResourceIdentifier paramXMLResourceIdentifier, Augmentations paramAugmentations) throws XNIException {}
  
  public void startConditional(short paramShort, Augmentations paramAugmentations) throws XNIException {}
  
  public void endConditional(Augmentations paramAugmentations) throws XNIException {}
  
  public void endDTD(Augmentations paramAugmentations) throws XNIException {
    this.fInDTD = false;
  }
  
  public void setDTDSource(XMLDTDSource paramXMLDTDSource) {
    this.fDTDSource = paramXMLDTDSource;
  }
  
  public XMLDTDSource getDTDSource() {
    return this.fDTDSource;
  }
  
  public void startContentModel(String paramString, Augmentations paramAugmentations) throws XNIException {}
  
  public void any(Augmentations paramAugmentations) throws XNIException {}
  
  public void empty(Augmentations paramAugmentations) throws XNIException {}
  
  public void startGroup(Augmentations paramAugmentations) throws XNIException {}
  
  public void pcdata(Augmentations paramAugmentations) throws XNIException {}
  
  public void element(String paramString, Augmentations paramAugmentations) throws XNIException {}
  
  public void separator(short paramShort, Augmentations paramAugmentations) throws XNIException {}
  
  public void occurrence(short paramShort, Augmentations paramAugmentations) throws XNIException {}
  
  public void endGroup(Augmentations paramAugmentations) throws XNIException {}
  
  public void endContentModel(Augmentations paramAugmentations) throws XNIException {}
  
  public void setDTDContentModelSource(XMLDTDContentModelSource paramXMLDTDContentModelSource) {
    this.fDTDContentModelSource = paramXMLDTDContentModelSource;
  }
  
  public XMLDTDContentModelSource getDTDContentModelSource() {
    return this.fDTDContentModelSource;
  }
  
  protected void reset() throws XNIException {
    super.reset();
    this.fInDTD = false;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/parsers/AbstractXMLDocumentParser.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */