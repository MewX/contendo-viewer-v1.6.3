package org.apache.xerces.jaxp.validation;

import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.Comment;
import javax.xml.stream.events.DTD;
import javax.xml.stream.events.EndDocument;
import javax.xml.stream.events.EntityReference;
import javax.xml.stream.events.ProcessingInstruction;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.XMLEvent;
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

final class StAXEventResultBuilder implements StAXDocumentHandler {
  private XMLEventWriter fEventWriter;
  
  private final XMLEventFactory fEventFactory;
  
  private final StAXValidatorHelper fStAXValidatorHelper;
  
  private final JAXPNamespaceContextWrapper fNamespaceContext;
  
  private boolean fIgnoreChars;
  
  private boolean fInCDATA;
  
  private final QName fAttrName = new QName();
  
  private static final Iterator EMPTY_COLLECTION_ITERATOR = new Iterator() {
      public boolean hasNext() {
        return false;
      }
      
      public Object next() {
        throw new NoSuchElementException();
      }
      
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  
  public StAXEventResultBuilder(StAXValidatorHelper paramStAXValidatorHelper, JAXPNamespaceContextWrapper paramJAXPNamespaceContextWrapper) {
    this.fStAXValidatorHelper = paramStAXValidatorHelper;
    this.fNamespaceContext = paramJAXPNamespaceContextWrapper;
    this.fEventFactory = XMLEventFactory.newInstance();
  }
  
  public void setStAXResult(StAXResult paramStAXResult) {
    this.fIgnoreChars = false;
    this.fInCDATA = false;
    this.fEventWriter = (paramStAXResult != null) ? paramStAXResult.getXMLEventWriter() : null;
  }
  
  public void startDocument(XMLStreamReader paramXMLStreamReader) throws XMLStreamException {
    String str1 = paramXMLStreamReader.getVersion();
    String str2 = paramXMLStreamReader.getCharacterEncodingScheme();
    boolean bool = paramXMLStreamReader.standaloneSet();
    this.fEventWriter.add(this.fEventFactory.createStartDocument((str2 != null) ? str2 : "UTF-8", (str1 != null) ? str1 : "1.0", bool));
  }
  
  public void endDocument(XMLStreamReader paramXMLStreamReader) throws XMLStreamException {
    this.fEventWriter.add(this.fEventFactory.createEndDocument());
    this.fEventWriter.flush();
  }
  
  public void comment(XMLStreamReader paramXMLStreamReader) throws XMLStreamException {
    this.fEventWriter.add(this.fEventFactory.createComment(paramXMLStreamReader.getText()));
  }
  
  public void processingInstruction(XMLStreamReader paramXMLStreamReader) throws XMLStreamException {
    String str = paramXMLStreamReader.getPIData();
    this.fEventWriter.add(this.fEventFactory.createProcessingInstruction(paramXMLStreamReader.getPITarget(), (str != null) ? str : ""));
  }
  
  public void entityReference(XMLStreamReader paramXMLStreamReader) throws XMLStreamException {
    String str = paramXMLStreamReader.getLocalName();
    this.fEventWriter.add(this.fEventFactory.createEntityReference(str, this.fStAXValidatorHelper.getEntityDeclaration(str)));
  }
  
  public void startDocument(StartDocument paramStartDocument) throws XMLStreamException {
    this.fEventWriter.add(paramStartDocument);
  }
  
  public void endDocument(EndDocument paramEndDocument) throws XMLStreamException {
    this.fEventWriter.add(paramEndDocument);
    this.fEventWriter.flush();
  }
  
  public void doctypeDecl(DTD paramDTD) throws XMLStreamException {
    this.fEventWriter.add(paramDTD);
  }
  
  public void characters(Characters paramCharacters) throws XMLStreamException {
    this.fEventWriter.add(paramCharacters);
  }
  
  public void cdata(Characters paramCharacters) throws XMLStreamException {
    this.fEventWriter.add(paramCharacters);
  }
  
  public void comment(Comment paramComment) throws XMLStreamException {
    this.fEventWriter.add(paramComment);
  }
  
  public void processingInstruction(ProcessingInstruction paramProcessingInstruction) throws XMLStreamException {
    this.fEventWriter.add(paramProcessingInstruction);
  }
  
  public void entityReference(EntityReference paramEntityReference) throws XMLStreamException {
    this.fEventWriter.add(paramEntityReference);
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
      int i = paramXMLAttributes.getLength();
      if (i == 0) {
        XMLEvent xMLEvent = this.fStAXValidatorHelper.getCurrentEvent();
        if (xMLEvent != null) {
          this.fEventWriter.add(xMLEvent);
          return;
        } 
      } 
      this.fEventWriter.add(this.fEventFactory.createStartElement(paramQName.prefix, (paramQName.uri != null) ? paramQName.uri : "", paramQName.localpart, getAttributeIterator(paramXMLAttributes, i), getNamespaceIterator(), this.fNamespaceContext.getNamespaceContext()));
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
          this.fEventWriter.add(this.fEventFactory.createCharacters(paramXMLString.toString()));
        } else {
          this.fEventWriter.add(this.fEventFactory.createCData(paramXMLString.toString()));
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
      XMLEvent xMLEvent = this.fStAXValidatorHelper.getCurrentEvent();
      if (xMLEvent != null) {
        this.fEventWriter.add(xMLEvent);
      } else {
        this.fEventWriter.add(this.fEventFactory.createEndElement(paramQName.prefix, paramQName.uri, paramQName.localpart, getNamespaceIterator()));
      } 
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
  
  private Iterator getAttributeIterator(XMLAttributes paramXMLAttributes, int paramInt) {
    return (paramInt > 0) ? new AttributeIterator(this, paramXMLAttributes, paramInt) : EMPTY_COLLECTION_ITERATOR;
  }
  
  private Iterator getNamespaceIterator() {
    int i = this.fNamespaceContext.getDeclaredPrefixCount();
    return (i > 0) ? new NamespaceIterator(this, i) : EMPTY_COLLECTION_ITERATOR;
  }
  
  final class NamespaceIterator implements Iterator {
    NamespaceContext fNC;
    
    int fIndex;
    
    int fEnd;
    
    private final StAXEventResultBuilder this$0;
    
    NamespaceIterator(StAXEventResultBuilder this$0, int param1Int) {
      this.this$0 = this$0;
      this.fNC = this$0.fNamespaceContext.getNamespaceContext();
      this.fIndex = 0;
      this.fEnd = param1Int;
    }
    
    public boolean hasNext() {
      if (this.fIndex < this.fEnd)
        return true; 
      this.fNC = null;
      return false;
    }
    
    public Object next() {
      if (!hasNext())
        throw new NoSuchElementException(); 
      String str1 = this.this$0.fNamespaceContext.getDeclaredPrefixAt(this.fIndex++);
      String str2 = this.fNC.getNamespaceURI(str1);
      return (str1.length() == 0) ? this.this$0.fEventFactory.createNamespace((str2 != null) ? str2 : "") : this.this$0.fEventFactory.createNamespace(str1, (str2 != null) ? str2 : "");
    }
    
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }
  
  final class AttributeIterator implements Iterator {
    XMLAttributes fAttributes;
    
    int fIndex;
    
    int fEnd;
    
    private final StAXEventResultBuilder this$0;
    
    AttributeIterator(StAXEventResultBuilder this$0, XMLAttributes param1XMLAttributes, int param1Int) {
      this.this$0 = this$0;
      this.fAttributes = param1XMLAttributes;
      this.fIndex = 0;
      this.fEnd = param1Int;
    }
    
    public boolean hasNext() {
      if (this.fIndex < this.fEnd)
        return true; 
      this.fAttributes = null;
      return false;
    }
    
    public Object next() {
      if (!hasNext())
        throw new NoSuchElementException(); 
      this.fAttributes.getName(this.fIndex, this.this$0.fAttrName);
      return this.this$0.fEventFactory.createAttribute(this.this$0.fAttrName.prefix, (this.this$0.fAttrName.uri != null) ? this.this$0.fAttrName.uri : "", this.this$0.fAttrName.localpart, this.fAttributes.getValue(this.fIndex++));
    }
    
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/jaxp/validation/StAXEventResultBuilder.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */