package org.apache.xerces.impl.xs.traversers;

import java.util.ArrayList;
import java.util.Iterator;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.Namespace;
import javax.xml.stream.events.ProcessingInstruction;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import org.apache.xerces.impl.xs.opti.SchemaDOMParser;
import org.apache.xerces.util.JAXPNamespaceContextWrapper;
import org.apache.xerces.util.StAXLocationWrapper;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.util.XMLAttributesImpl;
import org.apache.xerces.util.XMLStringBuffer;
import org.apache.xerces.util.XMLSymbols;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.xni.XMLString;
import org.apache.xerces.xni.XNIException;
import org.w3c.dom.Document;

final class StAXSchemaParser {
  private static final int CHUNK_SIZE = 1024;
  
  private static final int CHUNK_MASK = 1023;
  
  private final char[] fCharBuffer = new char[1024];
  
  private SymbolTable fSymbolTable;
  
  private SchemaDOMParser fSchemaDOMParser;
  
  private final StAXLocationWrapper fLocationWrapper = new StAXLocationWrapper();
  
  private final JAXPNamespaceContextWrapper fNamespaceContext = new JAXPNamespaceContextWrapper(this.fSymbolTable);
  
  private final QName fElementQName = new QName();
  
  private final QName fAttributeQName = new QName();
  
  private final XMLAttributesImpl fAttributes = new XMLAttributesImpl();
  
  private final XMLString fTempString = new XMLString();
  
  private final ArrayList fDeclaredPrefixes = new ArrayList();
  
  private final XMLStringBuffer fStringBuffer = new XMLStringBuffer();
  
  private int fDepth;
  
  public StAXSchemaParser() {
    this.fNamespaceContext.setDeclaredPrefixes(this.fDeclaredPrefixes);
  }
  
  public void reset(SchemaDOMParser paramSchemaDOMParser, SymbolTable paramSymbolTable) {
    this.fSchemaDOMParser = paramSchemaDOMParser;
    this.fSymbolTable = paramSymbolTable;
    this.fNamespaceContext.setSymbolTable(this.fSymbolTable);
    this.fNamespaceContext.reset();
  }
  
  public Document getDocument() {
    return this.fSchemaDOMParser.getDocument();
  }
  
  public void parse(XMLEventReader paramXMLEventReader) throws XMLStreamException, XNIException {
    XMLEvent xMLEvent = paramXMLEventReader.peek();
    if (xMLEvent != null) {
      int i = xMLEvent.getEventType();
      if (i != 7 && i != 1)
        throw new XMLStreamException(); 
      this.fLocationWrapper.setLocation(xMLEvent.getLocation());
      this.fSchemaDOMParser.startDocument((XMLLocator)this.fLocationWrapper, null, (NamespaceContext)this.fNamespaceContext, null);
      while (paramXMLEventReader.hasNext()) {
        StartElement startElement;
        EndElement endElement;
        ProcessingInstruction processingInstruction;
        xMLEvent = paramXMLEventReader.nextEvent();
        i = xMLEvent.getEventType();
        switch (i) {
          case 1:
            this.fDepth++;
            startElement = xMLEvent.asStartElement();
            fillQName(this.fElementQName, startElement.getName());
            this.fLocationWrapper.setLocation(startElement.getLocation());
            this.fNamespaceContext.setNamespaceContext(startElement.getNamespaceContext());
            fillXMLAttributes(startElement);
            fillDeclaredPrefixes(startElement);
            addNamespaceDeclarations();
            this.fNamespaceContext.pushContext();
            this.fSchemaDOMParser.startElement(this.fElementQName, (XMLAttributes)this.fAttributes, null);
          case 2:
            endElement = xMLEvent.asEndElement();
            fillQName(this.fElementQName, endElement.getName());
            fillDeclaredPrefixes(endElement);
            this.fLocationWrapper.setLocation(endElement.getLocation());
            this.fSchemaDOMParser.endElement(this.fElementQName, null);
            this.fNamespaceContext.popContext();
            this.fDepth--;
            if (this.fDepth <= 0)
              break; 
          case 4:
            sendCharactersToSchemaParser(xMLEvent.asCharacters().getData(), false);
          case 6:
            sendCharactersToSchemaParser(xMLEvent.asCharacters().getData(), true);
          case 12:
            this.fSchemaDOMParser.startCDATA(null);
            sendCharactersToSchemaParser(xMLEvent.asCharacters().getData(), false);
            this.fSchemaDOMParser.endCDATA(null);
          case 3:
            processingInstruction = (ProcessingInstruction)xMLEvent;
            fillProcessingInstruction(processingInstruction.getData());
            this.fSchemaDOMParser.processingInstruction(processingInstruction.getTarget(), this.fTempString, null);
          case 7:
            this.fDepth++;
        } 
      } 
      this.fLocationWrapper.setLocation(null);
      this.fNamespaceContext.setNamespaceContext(null);
      this.fSchemaDOMParser.endDocument(null);
    } 
  }
  
  public void parse(XMLStreamReader paramXMLStreamReader) throws XMLStreamException, XNIException {
    if (paramXMLStreamReader.hasNext()) {
      int i = paramXMLStreamReader.getEventType();
      if (i != 7 && i != 1)
        throw new XMLStreamException(); 
      this.fLocationWrapper.setLocation(paramXMLStreamReader.getLocation());
      this.fSchemaDOMParser.startDocument((XMLLocator)this.fLocationWrapper, null, (NamespaceContext)this.fNamespaceContext, null);
      boolean bool = true;
      while (paramXMLStreamReader.hasNext()) {
        if (!bool) {
          i = paramXMLStreamReader.next();
        } else {
          bool = false;
        } 
        switch (i) {
          case 1:
            this.fDepth++;
            this.fLocationWrapper.setLocation(paramXMLStreamReader.getLocation());
            this.fNamespaceContext.setNamespaceContext(paramXMLStreamReader.getNamespaceContext());
            fillQName(this.fElementQName, paramXMLStreamReader.getNamespaceURI(), paramXMLStreamReader.getLocalName(), paramXMLStreamReader.getPrefix());
            fillXMLAttributes(paramXMLStreamReader);
            fillDeclaredPrefixes(paramXMLStreamReader);
            addNamespaceDeclarations();
            this.fNamespaceContext.pushContext();
            this.fSchemaDOMParser.startElement(this.fElementQName, (XMLAttributes)this.fAttributes, null);
          case 2:
            this.fLocationWrapper.setLocation(paramXMLStreamReader.getLocation());
            this.fNamespaceContext.setNamespaceContext(paramXMLStreamReader.getNamespaceContext());
            fillQName(this.fElementQName, paramXMLStreamReader.getNamespaceURI(), paramXMLStreamReader.getLocalName(), paramXMLStreamReader.getPrefix());
            fillDeclaredPrefixes(paramXMLStreamReader);
            this.fSchemaDOMParser.endElement(this.fElementQName, null);
            this.fNamespaceContext.popContext();
            this.fDepth--;
            if (this.fDepth <= 0)
              break; 
          case 4:
            this.fTempString.setValues(paramXMLStreamReader.getTextCharacters(), paramXMLStreamReader.getTextStart(), paramXMLStreamReader.getTextLength());
            this.fSchemaDOMParser.characters(this.fTempString, null);
          case 6:
            this.fTempString.setValues(paramXMLStreamReader.getTextCharacters(), paramXMLStreamReader.getTextStart(), paramXMLStreamReader.getTextLength());
            this.fSchemaDOMParser.ignorableWhitespace(this.fTempString, null);
          case 12:
            this.fSchemaDOMParser.startCDATA(null);
            this.fTempString.setValues(paramXMLStreamReader.getTextCharacters(), paramXMLStreamReader.getTextStart(), paramXMLStreamReader.getTextLength());
            this.fSchemaDOMParser.characters(this.fTempString, null);
            this.fSchemaDOMParser.endCDATA(null);
          case 3:
            fillProcessingInstruction(paramXMLStreamReader.getPIData());
            this.fSchemaDOMParser.processingInstruction(paramXMLStreamReader.getPITarget(), this.fTempString, null);
          case 7:
            this.fDepth++;
        } 
      } 
      this.fLocationWrapper.setLocation(null);
      this.fNamespaceContext.setNamespaceContext(null);
      this.fSchemaDOMParser.endDocument(null);
    } 
  }
  
  private void sendCharactersToSchemaParser(String paramString, boolean paramBoolean) {
    // Byte code:
    //   0: aload_1
    //   1: ifnull -> 156
    //   4: aload_1
    //   5: invokevirtual length : ()I
    //   8: istore_3
    //   9: iload_3
    //   10: sipush #1023
    //   13: iand
    //   14: istore #4
    //   16: iload #4
    //   18: ifle -> 78
    //   21: aload_1
    //   22: iconst_0
    //   23: iload #4
    //   25: aload_0
    //   26: getfield fCharBuffer : [C
    //   29: iconst_0
    //   30: invokevirtual getChars : (II[CI)V
    //   33: aload_0
    //   34: getfield fTempString : Lorg/apache/xerces/xni/XMLString;
    //   37: aload_0
    //   38: getfield fCharBuffer : [C
    //   41: iconst_0
    //   42: iload #4
    //   44: invokevirtual setValues : ([CII)V
    //   47: iload_2
    //   48: ifeq -> 66
    //   51: aload_0
    //   52: getfield fSchemaDOMParser : Lorg/apache/xerces/impl/xs/opti/SchemaDOMParser;
    //   55: aload_0
    //   56: getfield fTempString : Lorg/apache/xerces/xni/XMLString;
    //   59: aconst_null
    //   60: invokevirtual ignorableWhitespace : (Lorg/apache/xerces/xni/XMLString;Lorg/apache/xerces/xni/Augmentations;)V
    //   63: goto -> 78
    //   66: aload_0
    //   67: getfield fSchemaDOMParser : Lorg/apache/xerces/impl/xs/opti/SchemaDOMParser;
    //   70: aload_0
    //   71: getfield fTempString : Lorg/apache/xerces/xni/XMLString;
    //   74: aconst_null
    //   75: invokevirtual characters : (Lorg/apache/xerces/xni/XMLString;Lorg/apache/xerces/xni/Augmentations;)V
    //   78: iload #4
    //   80: istore #5
    //   82: goto -> 150
    //   85: aload_1
    //   86: iload #5
    //   88: wide iinc #5 1024
    //   94: iload #5
    //   96: aload_0
    //   97: getfield fCharBuffer : [C
    //   100: iconst_0
    //   101: invokevirtual getChars : (II[CI)V
    //   104: aload_0
    //   105: getfield fTempString : Lorg/apache/xerces/xni/XMLString;
    //   108: aload_0
    //   109: getfield fCharBuffer : [C
    //   112: iconst_0
    //   113: sipush #1024
    //   116: invokevirtual setValues : ([CII)V
    //   119: iload_2
    //   120: ifeq -> 138
    //   123: aload_0
    //   124: getfield fSchemaDOMParser : Lorg/apache/xerces/impl/xs/opti/SchemaDOMParser;
    //   127: aload_0
    //   128: getfield fTempString : Lorg/apache/xerces/xni/XMLString;
    //   131: aconst_null
    //   132: invokevirtual ignorableWhitespace : (Lorg/apache/xerces/xni/XMLString;Lorg/apache/xerces/xni/Augmentations;)V
    //   135: goto -> 150
    //   138: aload_0
    //   139: getfield fSchemaDOMParser : Lorg/apache/xerces/impl/xs/opti/SchemaDOMParser;
    //   142: aload_0
    //   143: getfield fTempString : Lorg/apache/xerces/xni/XMLString;
    //   146: aconst_null
    //   147: invokevirtual characters : (Lorg/apache/xerces/xni/XMLString;Lorg/apache/xerces/xni/Augmentations;)V
    //   150: iload #5
    //   152: iload_3
    //   153: if_icmplt -> 85
    //   156: return
  }
  
  private void fillProcessingInstruction(String paramString) {
    int i = paramString.length();
    char[] arrayOfChar = this.fCharBuffer;
    if (arrayOfChar.length < i) {
      arrayOfChar = paramString.toCharArray();
    } else {
      paramString.getChars(0, i, arrayOfChar, 0);
    } 
    this.fTempString.setValues(arrayOfChar, 0, i);
  }
  
  private void fillXMLAttributes(StartElement paramStartElement) {
    this.fAttributes.removeAllAttributes();
    Iterator iterator = paramStartElement.getAttributes();
    while (iterator.hasNext()) {
      Attribute attribute = iterator.next();
      fillQName(this.fAttributeQName, attribute.getName());
      String str = attribute.getDTDType();
      int i = this.fAttributes.getLength();
      this.fAttributes.addAttributeNS(this.fAttributeQName, (str != null) ? str : XMLSymbols.fCDATASymbol, attribute.getValue());
      this.fAttributes.setSpecified(i, attribute.isSpecified());
    } 
  }
  
  private void fillXMLAttributes(XMLStreamReader paramXMLStreamReader) {
    this.fAttributes.removeAllAttributes();
    int i = paramXMLStreamReader.getAttributeCount();
    for (byte b = 0; b < i; b++) {
      fillQName(this.fAttributeQName, paramXMLStreamReader.getAttributeNamespace(b), paramXMLStreamReader.getAttributeLocalName(b), paramXMLStreamReader.getAttributePrefix(b));
      String str = paramXMLStreamReader.getAttributeType(b);
      this.fAttributes.addAttributeNS(this.fAttributeQName, (str != null) ? str : XMLSymbols.fCDATASymbol, paramXMLStreamReader.getAttributeValue(b));
      this.fAttributes.setSpecified(b, paramXMLStreamReader.isAttributeSpecified(b));
    } 
  }
  
  private void addNamespaceDeclarations() {
    String str1 = null;
    String str2 = null;
    String str3 = null;
    str4 = null;
    String str5 = null;
    for (String str4 : this.fDeclaredPrefixes) {
      str5 = this.fNamespaceContext.getURI(str4);
      if (str4.length() > 0) {
        str1 = XMLSymbols.PREFIX_XMLNS;
        str2 = str4;
        this.fStringBuffer.clear();
        this.fStringBuffer.append(str1);
        this.fStringBuffer.append(':');
        this.fStringBuffer.append(str2);
        str3 = this.fSymbolTable.addSymbol(this.fStringBuffer.ch, this.fStringBuffer.offset, this.fStringBuffer.length);
      } else {
        str1 = XMLSymbols.EMPTY_STRING;
        str2 = XMLSymbols.PREFIX_XMLNS;
        str3 = XMLSymbols.PREFIX_XMLNS;
      } 
      this.fAttributeQName.setValues(str1, str2, str3, NamespaceContext.XMLNS_URI);
      this.fAttributes.addAttribute(this.fAttributeQName, XMLSymbols.fCDATASymbol, (str5 != null) ? str5 : XMLSymbols.EMPTY_STRING);
    } 
  }
  
  private void fillDeclaredPrefixes(StartElement paramStartElement) {
    fillDeclaredPrefixes(paramStartElement.getNamespaces());
  }
  
  private void fillDeclaredPrefixes(EndElement paramEndElement) {
    fillDeclaredPrefixes(paramEndElement.getNamespaces());
  }
  
  private void fillDeclaredPrefixes(Iterator paramIterator) {
    this.fDeclaredPrefixes.clear();
    while (paramIterator.hasNext()) {
      Namespace namespace = paramIterator.next();
      String str = namespace.getPrefix();
      this.fDeclaredPrefixes.add((str != null) ? str : "");
    } 
  }
  
  private void fillDeclaredPrefixes(XMLStreamReader paramXMLStreamReader) {
    this.fDeclaredPrefixes.clear();
    int i = paramXMLStreamReader.getNamespaceCount();
    for (byte b = 0; b < i; b++) {
      String str = paramXMLStreamReader.getNamespacePrefix(b);
      this.fDeclaredPrefixes.add((str != null) ? str : "");
    } 
  }
  
  private void fillQName(QName paramQName, QName paramQName1) {
    fillQName(paramQName, paramQName1.getNamespaceURI(), paramQName1.getLocalPart(), paramQName1.getPrefix());
  }
  
  final void fillQName(QName paramQName, String paramString1, String paramString2, String paramString3) {
    paramString1 = (paramString1 != null && paramString1.length() > 0) ? this.fSymbolTable.addSymbol(paramString1) : null;
    paramString2 = (paramString2 != null) ? this.fSymbolTable.addSymbol(paramString2) : XMLSymbols.EMPTY_STRING;
    paramString3 = (paramString3 != null && paramString3.length() > 0) ? this.fSymbolTable.addSymbol(paramString3) : XMLSymbols.EMPTY_STRING;
    String str = paramString2;
    if (paramString3 != XMLSymbols.EMPTY_STRING) {
      this.fStringBuffer.clear();
      this.fStringBuffer.append(paramString3);
      this.fStringBuffer.append(':');
      this.fStringBuffer.append(paramString2);
      str = this.fSymbolTable.addSymbol(this.fStringBuffer.ch, this.fStringBuffer.offset, this.fStringBuffer.length);
    } 
    paramQName.setValues(paramString3, paramString2, str, paramString1);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/xs/traversers/StAXSchemaParser.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */