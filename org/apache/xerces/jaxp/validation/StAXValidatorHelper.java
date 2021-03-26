package org.apache.xerces.jaxp.validation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.Comment;
import javax.xml.stream.events.DTD;
import javax.xml.stream.events.EndDocument;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.EntityDeclaration;
import javax.xml.stream.events.EntityReference;
import javax.xml.stream.events.Namespace;
import javax.xml.stream.events.ProcessingInstruction;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.stax.StAXResult;
import javax.xml.transform.stax.StAXSource;
import org.apache.xerces.impl.XMLErrorReporter;
import org.apache.xerces.impl.validation.EntityState;
import org.apache.xerces.impl.validation.ValidationManager;
import org.apache.xerces.impl.xs.XMLSchemaValidator;
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
import org.apache.xerces.xni.parser.XMLParseException;
import org.xml.sax.SAXException;

final class StAXValidatorHelper implements EntityState, ValidatorHelper {
  private static final String STRING_INTERNING = "javax.xml.stream.isInterning";
  
  private static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
  
  private static final String SCHEMA_VALIDATOR = "http://apache.org/xml/properties/internal/validator/schema";
  
  private static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
  
  private static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
  
  private final XMLErrorReporter fErrorReporter;
  
  private final XMLSchemaValidator fSchemaValidator;
  
  private final SymbolTable fSymbolTable;
  
  private final ValidationManager fValidationManager;
  
  private final XMLSchemaValidatorComponentManager fComponentManager;
  
  private final JAXPNamespaceContextWrapper fNamespaceContext;
  
  private final StAXLocationWrapper fStAXLocationWrapper = new StAXLocationWrapper();
  
  private final XMLStreamReaderLocation fXMLStreamReaderLocation = new XMLStreamReaderLocation();
  
  private HashMap fEntities = null;
  
  private boolean fStringsInternalized = false;
  
  private StreamHelper fStreamHelper;
  
  private EventHelper fEventHelper;
  
  private StAXDocumentHandler fStAXValidatorHandler;
  
  private StAXStreamResultBuilder fStAXStreamResultBuilder;
  
  private StAXEventResultBuilder fStAXEventResultBuilder;
  
  private int fDepth = 0;
  
  private XMLEvent fCurrentEvent = null;
  
  final QName fElementQName = new QName();
  
  final QName fAttributeQName = new QName();
  
  final XMLAttributesImpl fAttributes = new XMLAttributesImpl();
  
  final ArrayList fDeclaredPrefixes = new ArrayList();
  
  final XMLString fTempString = new XMLString();
  
  final XMLStringBuffer fStringBuffer = new XMLStringBuffer();
  
  public StAXValidatorHelper(XMLSchemaValidatorComponentManager paramXMLSchemaValidatorComponentManager) {
    this.fComponentManager = paramXMLSchemaValidatorComponentManager;
    this.fErrorReporter = (XMLErrorReporter)this.fComponentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
    this.fSchemaValidator = (XMLSchemaValidator)this.fComponentManager.getProperty("http://apache.org/xml/properties/internal/validator/schema");
    this.fSymbolTable = (SymbolTable)this.fComponentManager.getProperty("http://apache.org/xml/properties/internal/symbol-table");
    this.fValidationManager = (ValidationManager)this.fComponentManager.getProperty("http://apache.org/xml/properties/internal/validation-manager");
    this.fNamespaceContext = new JAXPNamespaceContextWrapper(this.fSymbolTable);
    this.fNamespaceContext.setDeclaredPrefixes(this.fDeclaredPrefixes);
  }
  
  public void validate(Source paramSource, Result paramResult) throws SAXException, IOException {
    if (paramResult instanceof StAXResult || paramResult == null) {
      StAXSource stAXSource = (StAXSource)paramSource;
      StAXResult stAXResult = (StAXResult)paramResult;
      try {
        XMLStreamReader xMLStreamReader = stAXSource.getXMLStreamReader();
        if (xMLStreamReader != null) {
          if (this.fStreamHelper == null)
            this.fStreamHelper = new StreamHelper(this); 
          this.fStreamHelper.validate(xMLStreamReader, stAXResult);
        } else {
          if (this.fEventHelper == null)
            this.fEventHelper = new EventHelper(this); 
          this.fEventHelper.validate(stAXSource.getXMLEventReader(), stAXResult);
        } 
      } catch (XMLStreamException xMLStreamException) {
        throw new SAXException(xMLStreamException);
      } catch (XMLParseException xMLParseException) {
        throw Util.toSAXParseException(xMLParseException);
      } catch (XNIException xNIException) {
        throw Util.toSAXException(xNIException);
      } finally {
        this.fCurrentEvent = null;
        this.fStAXLocationWrapper.setLocation(null);
        this.fXMLStreamReaderLocation.setXMLStreamReader(null);
        if (this.fStAXValidatorHandler != null)
          this.fStAXValidatorHandler.setStAXResult(null); 
      } 
      return;
    } 
    throw new IllegalArgumentException(JAXPValidationMessageFormatter.formatMessage(this.fComponentManager.getLocale(), "SourceResultMismatch", new Object[] { paramSource.getClass().getName(), paramResult.getClass().getName() }));
  }
  
  public boolean isEntityDeclared(String paramString) {
    return (this.fEntities != null) ? this.fEntities.containsKey(paramString) : false;
  }
  
  public boolean isEntityUnparsed(String paramString) {
    if (this.fEntities != null) {
      EntityDeclaration entityDeclaration = (EntityDeclaration)this.fEntities.get(paramString);
      if (entityDeclaration != null)
        return (entityDeclaration.getNotationName() != null); 
    } 
    return false;
  }
  
  final EntityDeclaration getEntityDeclaration(String paramString) {
    return (this.fEntities != null) ? (EntityDeclaration)this.fEntities.get(paramString) : null;
  }
  
  final XMLEvent getCurrentEvent() {
    return this.fCurrentEvent;
  }
  
  final void fillQName(QName paramQName, String paramString1, String paramString2, String paramString3) {
    if (!this.fStringsInternalized) {
      paramString1 = (paramString1 != null && paramString1.length() > 0) ? this.fSymbolTable.addSymbol(paramString1) : null;
      paramString2 = (paramString2 != null) ? this.fSymbolTable.addSymbol(paramString2) : XMLSymbols.EMPTY_STRING;
      paramString3 = (paramString3 != null && paramString3.length() > 0) ? this.fSymbolTable.addSymbol(paramString3) : XMLSymbols.EMPTY_STRING;
    } else {
      if (paramString1 != null && paramString1.length() == 0)
        paramString1 = null; 
      if (paramString2 == null)
        paramString2 = XMLSymbols.EMPTY_STRING; 
      if (paramString3 == null)
        paramString3 = XMLSymbols.EMPTY_STRING; 
    } 
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
  
  final void setup(Location paramLocation, StAXResult paramStAXResult, boolean paramBoolean) {
    this.fDepth = 0;
    this.fComponentManager.reset();
    setupStAXResultHandler(paramStAXResult);
    this.fValidationManager.setEntityState(this);
    if (this.fEntities != null && !this.fEntities.isEmpty())
      this.fEntities.clear(); 
    this.fStAXLocationWrapper.setLocation(paramLocation);
    this.fErrorReporter.setDocumentLocator((XMLLocator)this.fStAXLocationWrapper);
    this.fStringsInternalized = paramBoolean;
  }
  
  final void processEntityDeclarations(List paramList) {
    byte b = (paramList != null) ? paramList.size() : 0;
    if (b) {
      if (this.fEntities == null)
        this.fEntities = new HashMap(); 
      for (byte b1 = 0; b1 < b; b1++) {
        EntityDeclaration entityDeclaration = paramList.get(b1);
        this.fEntities.put(entityDeclaration.getName(), entityDeclaration);
      } 
    } 
  }
  
  private void setupStAXResultHandler(StAXResult paramStAXResult) {
    if (paramStAXResult == null) {
      this.fStAXValidatorHandler = null;
      this.fSchemaValidator.setDocumentHandler(null);
      return;
    } 
    XMLStreamWriter xMLStreamWriter = paramStAXResult.getXMLStreamWriter();
    if (xMLStreamWriter != null) {
      if (this.fStAXStreamResultBuilder == null)
        this.fStAXStreamResultBuilder = new StAXStreamResultBuilder(this.fNamespaceContext); 
      this.fStAXValidatorHandler = this.fStAXStreamResultBuilder;
      this.fStAXStreamResultBuilder.setStAXResult(paramStAXResult);
    } else {
      if (this.fStAXEventResultBuilder == null)
        this.fStAXEventResultBuilder = new StAXEventResultBuilder(this, this.fNamespaceContext); 
      this.fStAXValidatorHandler = this.fStAXEventResultBuilder;
      this.fStAXEventResultBuilder.setStAXResult(paramStAXResult);
    } 
    this.fSchemaValidator.setDocumentHandler(this.fStAXValidatorHandler);
  }
  
  static final class XMLStreamReaderLocation implements Location {
    private XMLStreamReader reader;
    
    public int getCharacterOffset() {
      Location location = getLocation();
      return (location != null) ? location.getCharacterOffset() : -1;
    }
    
    public int getColumnNumber() {
      Location location = getLocation();
      return (location != null) ? location.getColumnNumber() : -1;
    }
    
    public int getLineNumber() {
      Location location = getLocation();
      return (location != null) ? location.getLineNumber() : -1;
    }
    
    public String getPublicId() {
      Location location = getLocation();
      return (location != null) ? location.getPublicId() : null;
    }
    
    public String getSystemId() {
      Location location = getLocation();
      return (location != null) ? location.getSystemId() : null;
    }
    
    public void setXMLStreamReader(XMLStreamReader param1XMLStreamReader) {
      this.reader = param1XMLStreamReader;
    }
    
    private Location getLocation() {
      return (this.reader != null) ? this.reader.getLocation() : null;
    }
  }
  
  final class EventHelper {
    private static final int CHUNK_SIZE = 1024;
    
    private static final int CHUNK_MASK = 1023;
    
    private final char[] fCharBuffer;
    
    private final StAXValidatorHelper this$0;
    
    EventHelper(StAXValidatorHelper this$0) {
      this.this$0 = this$0;
      this.fCharBuffer = new char[1024];
    }
    
    final void validate(XMLEventReader param1XMLEventReader, StAXResult param1StAXResult) throws SAXException, XMLStreamException {
      this.this$0.fCurrentEvent = param1XMLEventReader.peek();
      if (this.this$0.fCurrentEvent != null) {
        int i = this.this$0.fCurrentEvent.getEventType();
        if (i != 7 && i != 1)
          throw new SAXException(JAXPValidationMessageFormatter.formatMessage(this.this$0.fComponentManager.getLocale(), "StAXIllegalInitialState", null)); 
        this.this$0.setup(null, param1StAXResult, false);
        this.this$0.fSchemaValidator.startDocument((XMLLocator)this.this$0.fStAXLocationWrapper, null, (NamespaceContext)this.this$0.fNamespaceContext, null);
        while (param1XMLEventReader.hasNext()) {
          StartElement startElement;
          EndElement endElement;
          DTD dTD;
          this.this$0.fCurrentEvent = param1XMLEventReader.nextEvent();
          i = this.this$0.fCurrentEvent.getEventType();
          switch (i) {
            case 1:
              ++this.this$0.fDepth;
              startElement = this.this$0.fCurrentEvent.asStartElement();
              fillQName(this.this$0.fElementQName, startElement.getName());
              fillXMLAttributes(startElement);
              fillDeclaredPrefixes(startElement);
              this.this$0.fNamespaceContext.setNamespaceContext(startElement.getNamespaceContext());
              this.this$0.fStAXLocationWrapper.setLocation(startElement.getLocation());
              this.this$0.fSchemaValidator.startElement(this.this$0.fElementQName, (XMLAttributes)this.this$0.fAttributes, null);
            case 2:
              endElement = this.this$0.fCurrentEvent.asEndElement();
              fillQName(this.this$0.fElementQName, endElement.getName());
              fillDeclaredPrefixes(endElement);
              this.this$0.fStAXLocationWrapper.setLocation(endElement.getLocation());
              this.this$0.fSchemaValidator.endElement(this.this$0.fElementQName, null);
              if (--this.this$0.fDepth <= 0)
                break; 
            case 4:
            case 6:
              if (this.this$0.fStAXValidatorHandler != null) {
                Characters characters = this.this$0.fCurrentEvent.asCharacters();
                this.this$0.fStAXValidatorHandler.setIgnoringCharacters(true);
                sendCharactersToValidator(characters.getData());
                this.this$0.fStAXValidatorHandler.setIgnoringCharacters(false);
                this.this$0.fStAXValidatorHandler.characters(characters);
                continue;
              } 
              sendCharactersToValidator(this.this$0.fCurrentEvent.asCharacters().getData());
            case 12:
              if (this.this$0.fStAXValidatorHandler != null) {
                Characters characters = this.this$0.fCurrentEvent.asCharacters();
                this.this$0.fStAXValidatorHandler.setIgnoringCharacters(true);
                this.this$0.fSchemaValidator.startCDATA(null);
                sendCharactersToValidator(this.this$0.fCurrentEvent.asCharacters().getData());
                this.this$0.fSchemaValidator.endCDATA(null);
                this.this$0.fStAXValidatorHandler.setIgnoringCharacters(false);
                this.this$0.fStAXValidatorHandler.cdata(characters);
                continue;
              } 
              this.this$0.fSchemaValidator.startCDATA(null);
              sendCharactersToValidator(this.this$0.fCurrentEvent.asCharacters().getData());
              this.this$0.fSchemaValidator.endCDATA(null);
            case 7:
              ++this.this$0.fDepth;
              if (this.this$0.fStAXValidatorHandler != null)
                this.this$0.fStAXValidatorHandler.startDocument((StartDocument)this.this$0.fCurrentEvent); 
            case 8:
              if (this.this$0.fStAXValidatorHandler != null)
                this.this$0.fStAXValidatorHandler.endDocument((EndDocument)this.this$0.fCurrentEvent); 
            case 3:
              if (this.this$0.fStAXValidatorHandler != null)
                this.this$0.fStAXValidatorHandler.processingInstruction((ProcessingInstruction)this.this$0.fCurrentEvent); 
            case 5:
              if (this.this$0.fStAXValidatorHandler != null)
                this.this$0.fStAXValidatorHandler.comment((Comment)this.this$0.fCurrentEvent); 
            case 9:
              if (this.this$0.fStAXValidatorHandler != null)
                this.this$0.fStAXValidatorHandler.entityReference((EntityReference)this.this$0.fCurrentEvent); 
            case 11:
              dTD = (DTD)this.this$0.fCurrentEvent;
              this.this$0.processEntityDeclarations(dTD.getEntities());
              if (this.this$0.fStAXValidatorHandler != null)
                this.this$0.fStAXValidatorHandler.doctypeDecl(dTD); 
          } 
        } 
        this.this$0.fSchemaValidator.endDocument(null);
      } 
    }
    
    private void fillQName(QName param1QName, QName param1QName1) {
      this.this$0.fillQName(param1QName, param1QName1.getNamespaceURI(), param1QName1.getLocalPart(), param1QName1.getPrefix());
    }
    
    private void fillXMLAttributes(StartElement param1StartElement) {
      this.this$0.fAttributes.removeAllAttributes();
      Iterator iterator = param1StartElement.getAttributes();
      while (iterator.hasNext()) {
        Attribute attribute = iterator.next();
        fillQName(this.this$0.fAttributeQName, attribute.getName());
        String str = attribute.getDTDType();
        int i = this.this$0.fAttributes.getLength();
        this.this$0.fAttributes.addAttributeNS(this.this$0.fAttributeQName, (str != null) ? str : XMLSymbols.fCDATASymbol, attribute.getValue());
        this.this$0.fAttributes.setSpecified(i, attribute.isSpecified());
      } 
    }
    
    private void fillDeclaredPrefixes(StartElement param1StartElement) {
      fillDeclaredPrefixes(param1StartElement.getNamespaces());
    }
    
    private void fillDeclaredPrefixes(EndElement param1EndElement) {
      fillDeclaredPrefixes(param1EndElement.getNamespaces());
    }
    
    private void fillDeclaredPrefixes(Iterator param1Iterator) {
      this.this$0.fDeclaredPrefixes.clear();
      while (param1Iterator.hasNext()) {
        Namespace namespace = param1Iterator.next();
        String str = namespace.getPrefix();
        this.this$0.fDeclaredPrefixes.add((str != null) ? str : "");
      } 
    }
    
    private void sendCharactersToValidator(String param1String) {
      // Byte code:
      //   0: aload_1
      //   1: ifnull -> 131
      //   4: aload_1
      //   5: invokevirtual length : ()I
      //   8: istore_2
      //   9: iload_2
      //   10: sipush #1023
      //   13: iand
      //   14: istore_3
      //   15: iload_3
      //   16: ifle -> 64
      //   19: aload_1
      //   20: iconst_0
      //   21: iload_3
      //   22: aload_0
      //   23: getfield fCharBuffer : [C
      //   26: iconst_0
      //   27: invokevirtual getChars : (II[CI)V
      //   30: aload_0
      //   31: getfield this$0 : Lorg/apache/xerces/jaxp/validation/StAXValidatorHelper;
      //   34: getfield fTempString : Lorg/apache/xerces/xni/XMLString;
      //   37: aload_0
      //   38: getfield fCharBuffer : [C
      //   41: iconst_0
      //   42: iload_3
      //   43: invokevirtual setValues : ([CII)V
      //   46: aload_0
      //   47: getfield this$0 : Lorg/apache/xerces/jaxp/validation/StAXValidatorHelper;
      //   50: invokestatic access$400 : (Lorg/apache/xerces/jaxp/validation/StAXValidatorHelper;)Lorg/apache/xerces/impl/xs/XMLSchemaValidator;
      //   53: aload_0
      //   54: getfield this$0 : Lorg/apache/xerces/jaxp/validation/StAXValidatorHelper;
      //   57: getfield fTempString : Lorg/apache/xerces/xni/XMLString;
      //   60: aconst_null
      //   61: invokevirtual characters : (Lorg/apache/xerces/xni/XMLString;Lorg/apache/xerces/xni/Augmentations;)V
      //   64: iload_3
      //   65: istore #4
      //   67: goto -> 125
      //   70: aload_1
      //   71: iload #4
      //   73: wide iinc #4 1024
      //   79: iload #4
      //   81: aload_0
      //   82: getfield fCharBuffer : [C
      //   85: iconst_0
      //   86: invokevirtual getChars : (II[CI)V
      //   89: aload_0
      //   90: getfield this$0 : Lorg/apache/xerces/jaxp/validation/StAXValidatorHelper;
      //   93: getfield fTempString : Lorg/apache/xerces/xni/XMLString;
      //   96: aload_0
      //   97: getfield fCharBuffer : [C
      //   100: iconst_0
      //   101: sipush #1024
      //   104: invokevirtual setValues : ([CII)V
      //   107: aload_0
      //   108: getfield this$0 : Lorg/apache/xerces/jaxp/validation/StAXValidatorHelper;
      //   111: invokestatic access$400 : (Lorg/apache/xerces/jaxp/validation/StAXValidatorHelper;)Lorg/apache/xerces/impl/xs/XMLSchemaValidator;
      //   114: aload_0
      //   115: getfield this$0 : Lorg/apache/xerces/jaxp/validation/StAXValidatorHelper;
      //   118: getfield fTempString : Lorg/apache/xerces/xni/XMLString;
      //   121: aconst_null
      //   122: invokevirtual characters : (Lorg/apache/xerces/xni/XMLString;Lorg/apache/xerces/xni/Augmentations;)V
      //   125: iload #4
      //   127: iload_2
      //   128: if_icmplt -> 70
      //   131: return
    }
  }
  
  final class StreamHelper {
    private final StAXValidatorHelper this$0;
    
    StreamHelper(StAXValidatorHelper this$0) {
      this.this$0 = this$0;
    }
    
    final void validate(XMLStreamReader param1XMLStreamReader, StAXResult param1StAXResult) throws SAXException, XMLStreamException {
      if (param1XMLStreamReader.hasNext()) {
        int i = param1XMLStreamReader.getEventType();
        if (i != 7 && i != 1)
          throw new SAXException(JAXPValidationMessageFormatter.formatMessage(this.this$0.fComponentManager.getLocale(), "StAXIllegalInitialState", null)); 
        this.this$0.fXMLStreamReaderLocation.setXMLStreamReader(param1XMLStreamReader);
        this.this$0.setup(this.this$0.fXMLStreamReaderLocation, param1StAXResult, Boolean.TRUE.equals(param1XMLStreamReader.getProperty("javax.xml.stream.isInterning")));
        this.this$0.fSchemaValidator.startDocument((XMLLocator)this.this$0.fStAXLocationWrapper, null, (NamespaceContext)this.this$0.fNamespaceContext, null);
        do {
          switch (i) {
            case 1:
              ++this.this$0.fDepth;
              this.this$0.fillQName(this.this$0.fElementQName, param1XMLStreamReader.getNamespaceURI(), param1XMLStreamReader.getLocalName(), param1XMLStreamReader.getPrefix());
              fillXMLAttributes(param1XMLStreamReader);
              fillDeclaredPrefixes(param1XMLStreamReader);
              this.this$0.fNamespaceContext.setNamespaceContext(param1XMLStreamReader.getNamespaceContext());
              this.this$0.fSchemaValidator.startElement(this.this$0.fElementQName, (XMLAttributes)this.this$0.fAttributes, null);
              break;
            case 2:
              this.this$0.fillQName(this.this$0.fElementQName, param1XMLStreamReader.getNamespaceURI(), param1XMLStreamReader.getLocalName(), param1XMLStreamReader.getPrefix());
              fillDeclaredPrefixes(param1XMLStreamReader);
              this.this$0.fNamespaceContext.setNamespaceContext(param1XMLStreamReader.getNamespaceContext());
              this.this$0.fSchemaValidator.endElement(this.this$0.fElementQName, null);
              --this.this$0.fDepth;
              break;
            case 4:
            case 6:
              this.this$0.fTempString.setValues(param1XMLStreamReader.getTextCharacters(), param1XMLStreamReader.getTextStart(), param1XMLStreamReader.getTextLength());
              this.this$0.fSchemaValidator.characters(this.this$0.fTempString, null);
              break;
            case 12:
              this.this$0.fSchemaValidator.startCDATA(null);
              this.this$0.fTempString.setValues(param1XMLStreamReader.getTextCharacters(), param1XMLStreamReader.getTextStart(), param1XMLStreamReader.getTextLength());
              this.this$0.fSchemaValidator.characters(this.this$0.fTempString, null);
              this.this$0.fSchemaValidator.endCDATA(null);
              break;
            case 7:
              ++this.this$0.fDepth;
              if (this.this$0.fStAXValidatorHandler != null)
                this.this$0.fStAXValidatorHandler.startDocument(param1XMLStreamReader); 
              break;
            case 3:
              if (this.this$0.fStAXValidatorHandler != null)
                this.this$0.fStAXValidatorHandler.processingInstruction(param1XMLStreamReader); 
              break;
            case 5:
              if (this.this$0.fStAXValidatorHandler != null)
                this.this$0.fStAXValidatorHandler.comment(param1XMLStreamReader); 
              break;
            case 9:
              if (this.this$0.fStAXValidatorHandler != null)
                this.this$0.fStAXValidatorHandler.entityReference(param1XMLStreamReader); 
              break;
            case 11:
              this.this$0.processEntityDeclarations((List)param1XMLStreamReader.getProperty("javax.xml.stream.entities"));
              break;
          } 
          i = param1XMLStreamReader.next();
        } while (param1XMLStreamReader.hasNext() && this.this$0.fDepth > 0);
        this.this$0.fSchemaValidator.endDocument(null);
        if (i == 8 && this.this$0.fStAXValidatorHandler != null)
          this.this$0.fStAXValidatorHandler.endDocument(param1XMLStreamReader); 
      } 
    }
    
    private void fillXMLAttributes(XMLStreamReader param1XMLStreamReader) {
      this.this$0.fAttributes.removeAllAttributes();
      int i = param1XMLStreamReader.getAttributeCount();
      for (byte b = 0; b < i; b++) {
        this.this$0.fillQName(this.this$0.fAttributeQName, param1XMLStreamReader.getAttributeNamespace(b), param1XMLStreamReader.getAttributeLocalName(b), param1XMLStreamReader.getAttributePrefix(b));
        String str = param1XMLStreamReader.getAttributeType(b);
        this.this$0.fAttributes.addAttributeNS(this.this$0.fAttributeQName, (str != null) ? str : XMLSymbols.fCDATASymbol, param1XMLStreamReader.getAttributeValue(b));
        this.this$0.fAttributes.setSpecified(b, param1XMLStreamReader.isAttributeSpecified(b));
      } 
    }
    
    private void fillDeclaredPrefixes(XMLStreamReader param1XMLStreamReader) {
      this.this$0.fDeclaredPrefixes.clear();
      int i = param1XMLStreamReader.getNamespaceCount();
      for (byte b = 0; b < i; b++) {
        String str = param1XMLStreamReader.getNamespacePrefix(b);
        this.this$0.fDeclaredPrefixes.add((str != null) ? str : "");
      } 
    }
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/jaxp/validation/StAXValidatorHelper.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */