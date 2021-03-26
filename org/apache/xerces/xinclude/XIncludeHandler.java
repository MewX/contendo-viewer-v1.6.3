package org.apache.xerces.xinclude;

import java.io.CharConversionException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Stack;
import java.util.StringTokenizer;
import org.apache.xerces.impl.Constants;
import org.apache.xerces.impl.XMLEntityManager;
import org.apache.xerces.impl.XMLErrorReporter;
import org.apache.xerces.impl.io.MalformedByteSequenceException;
import org.apache.xerces.util.AugmentationsImpl;
import org.apache.xerces.util.HTTPInputSource;
import org.apache.xerces.util.IntStack;
import org.apache.xerces.util.ParserConfigurationSettings;
import org.apache.xerces.util.SecurityManager;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.util.URI;
import org.apache.xerces.util.XMLAttributesImpl;
import org.apache.xerces.util.XMLChar;
import org.apache.xerces.util.XMLLocatorWrapper;
import org.apache.xerces.util.XMLResourceIdentifierImpl;
import org.apache.xerces.util.XMLSymbols;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.xni.XMLDTDHandler;
import org.apache.xerces.xni.XMLDocumentHandler;
import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.xni.XMLResourceIdentifier;
import org.apache.xerces.xni.XMLString;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.parser.XMLComponent;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.parser.XMLDTDFilter;
import org.apache.xerces.xni.parser.XMLDTDSource;
import org.apache.xerces.xni.parser.XMLDocumentFilter;
import org.apache.xerces.xni.parser.XMLDocumentSource;
import org.apache.xerces.xni.parser.XMLEntityResolver;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xerces.xni.parser.XMLParserConfiguration;
import org.apache.xerces.xpointer.XPointerHandler;
import org.apache.xerces.xpointer.XPointerProcessor;

public class XIncludeHandler implements XMLComponent, XMLDTDFilter, XMLDocumentFilter {
  public static final String XINCLUDE_DEFAULT_CONFIGURATION = "org.apache.xerces.parsers.XIncludeParserConfiguration";
  
  public static final String HTTP_ACCEPT = "Accept";
  
  public static final String HTTP_ACCEPT_LANGUAGE = "Accept-Language";
  
  public static final String XPOINTER = "xpointer";
  
  public static final String XINCLUDE_NS_URI = "http://www.w3.org/2001/XInclude".intern();
  
  public static final String XINCLUDE_INCLUDE = "include".intern();
  
  public static final String XINCLUDE_FALLBACK = "fallback".intern();
  
  public static final String XINCLUDE_PARSE_XML = "xml".intern();
  
  public static final String XINCLUDE_PARSE_TEXT = "text".intern();
  
  public static final String XINCLUDE_ATTR_HREF = "href".intern();
  
  public static final String XINCLUDE_ATTR_PARSE = "parse".intern();
  
  public static final String XINCLUDE_ATTR_ENCODING = "encoding".intern();
  
  public static final String XINCLUDE_ATTR_ACCEPT = "accept".intern();
  
  public static final String XINCLUDE_ATTR_ACCEPT_LANGUAGE = "accept-language".intern();
  
  public static final String XINCLUDE_INCLUDED = "[included]".intern();
  
  public static final String CURRENT_BASE_URI = "currentBaseURI";
  
  private static final String XINCLUDE_BASE = "base".intern();
  
  private static final QName XML_BASE_QNAME = new QName(XMLSymbols.PREFIX_XML, XINCLUDE_BASE, (XMLSymbols.PREFIX_XML + ":" + XINCLUDE_BASE).intern(), NamespaceContext.XML_URI);
  
  private static final String XINCLUDE_LANG = "lang".intern();
  
  private static final QName XML_LANG_QNAME = new QName(XMLSymbols.PREFIX_XML, XINCLUDE_LANG, (XMLSymbols.PREFIX_XML + ":" + XINCLUDE_LANG).intern(), NamespaceContext.XML_URI);
  
  private static final QName NEW_NS_ATTR_QNAME = new QName(XMLSymbols.PREFIX_XMLNS, "", XMLSymbols.PREFIX_XMLNS + ":", NamespaceContext.XMLNS_URI);
  
  private static final int STATE_NORMAL_PROCESSING = 1;
  
  private static final int STATE_IGNORE = 2;
  
  private static final int STATE_EXPECT_FALLBACK = 3;
  
  protected static final String VALIDATION = "http://xml.org/sax/features/validation";
  
  protected static final String SCHEMA_VALIDATION = "http://apache.org/xml/features/validation/schema";
  
  protected static final String DYNAMIC_VALIDATION = "http://apache.org/xml/features/validation/dynamic";
  
  protected static final String ALLOW_UE_AND_NOTATION_EVENTS = "http://xml.org/sax/features/allow-dtd-events-after-endDTD";
  
  protected static final String XINCLUDE_FIXUP_BASE_URIS = "http://apache.org/xml/features/xinclude/fixup-base-uris";
  
  protected static final String XINCLUDE_FIXUP_LANGUAGE = "http://apache.org/xml/features/xinclude/fixup-language";
  
  protected static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
  
  protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
  
  protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
  
  protected static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
  
  protected static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
  
  protected static final String BUFFER_SIZE = "http://apache.org/xml/properties/input-buffer-size";
  
  protected static final String PARSER_SETTINGS = "http://apache.org/xml/features/internal/parser-settings";
  
  private static final String[] RECOGNIZED_FEATURES = new String[] { "http://xml.org/sax/features/allow-dtd-events-after-endDTD", "http://apache.org/xml/features/xinclude/fixup-base-uris", "http://apache.org/xml/features/xinclude/fixup-language" };
  
  private static final Boolean[] FEATURE_DEFAULTS = new Boolean[] { Boolean.TRUE, Boolean.TRUE, Boolean.TRUE };
  
  private static final String[] RECOGNIZED_PROPERTIES = new String[] { "http://apache.org/xml/properties/internal/error-reporter", "http://apache.org/xml/properties/internal/entity-resolver", "http://apache.org/xml/properties/security-manager", "http://apache.org/xml/properties/input-buffer-size" };
  
  private static final Object[] PROPERTY_DEFAULTS = new Object[] { null, null, null, new Integer(2048) };
  
  protected XMLDocumentHandler fDocumentHandler;
  
  protected XMLDocumentSource fDocumentSource;
  
  protected XMLDTDHandler fDTDHandler;
  
  protected XMLDTDSource fDTDSource;
  
  protected XIncludeHandler fParentXIncludeHandler;
  
  protected int fBufferSize = 2048;
  
  protected String fParentRelativeURI;
  
  protected XMLParserConfiguration fChildConfig;
  
  protected XMLParserConfiguration fXIncludeChildConfig;
  
  protected XMLParserConfiguration fXPointerChildConfig;
  
  protected XPointerProcessor fXPtrProcessor = null;
  
  protected XMLLocator fDocLocation;
  
  protected XMLLocatorWrapper fXIncludeLocator = new XMLLocatorWrapper();
  
  protected XIncludeMessageFormatter fXIncludeMessageFormatter = new XIncludeMessageFormatter();
  
  protected XIncludeNamespaceSupport fNamespaceContext;
  
  protected SymbolTable fSymbolTable;
  
  protected XMLErrorReporter fErrorReporter;
  
  protected XMLEntityResolver fEntityResolver;
  
  protected SecurityManager fSecurityManager;
  
  protected XIncludeTextReader fXInclude10TextReader;
  
  protected XIncludeTextReader fXInclude11TextReader;
  
  protected final XMLResourceIdentifier fCurrentBaseURI;
  
  protected final IntStack fBaseURIScope;
  
  protected final Stack fBaseURI;
  
  protected final Stack fLiteralSystemID;
  
  protected final Stack fExpandedSystemID;
  
  protected final IntStack fLanguageScope;
  
  protected final Stack fLanguageStack;
  
  protected String fCurrentLanguage;
  
  protected String fHrefFromParent;
  
  protected ParserConfigurationSettings fSettings;
  
  private int fDepth = 0;
  
  private int fResultDepth;
  
  private static final int INITIAL_SIZE = 8;
  
  private boolean[] fSawInclude = new boolean[8];
  
  private boolean[] fSawFallback = new boolean[8];
  
  private int[] fState = new int[8];
  
  private final ArrayList fNotations;
  
  private final ArrayList fUnparsedEntities;
  
  private boolean fFixupBaseURIs = true;
  
  private boolean fFixupLanguage = true;
  
  private boolean fSendUEAndNotationEvents;
  
  private boolean fIsXML11;
  
  private boolean fInDTD;
  
  boolean fHasIncludeReportedContent;
  
  private boolean fSeenRootElement;
  
  private boolean fNeedCopyFeatures = true;
  
  private static final boolean[] gNeedEscaping = new boolean[128];
  
  private static final char[] gAfterEscaping1 = new char[128];
  
  private static final char[] gAfterEscaping2 = new char[128];
  
  private static final char[] gHexChs = new char[] { 
      '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
      'A', 'B', 'C', 'D', 'E', 'F' };
  
  public XIncludeHandler() {
    this.fSawFallback[this.fDepth] = false;
    this.fSawInclude[this.fDepth] = false;
    this.fState[this.fDepth] = 1;
    this.fNotations = new ArrayList();
    this.fUnparsedEntities = new ArrayList();
    this.fBaseURIScope = new IntStack();
    this.fBaseURI = new Stack();
    this.fLiteralSystemID = new Stack();
    this.fExpandedSystemID = new Stack();
    this.fCurrentBaseURI = (XMLResourceIdentifier)new XMLResourceIdentifierImpl();
    this.fLanguageScope = new IntStack();
    this.fLanguageStack = new Stack();
    this.fCurrentLanguage = null;
  }
  
  public void reset(XMLComponentManager paramXMLComponentManager) throws XNIException {
    this.fNamespaceContext = null;
    this.fDepth = 0;
    this.fResultDepth = isRootDocument() ? 0 : this.fParentXIncludeHandler.getResultDepth();
    this.fNotations.clear();
    this.fUnparsedEntities.clear();
    this.fParentRelativeURI = null;
    this.fIsXML11 = false;
    this.fInDTD = false;
    this.fSeenRootElement = false;
    this.fBaseURIScope.clear();
    this.fBaseURI.clear();
    this.fLiteralSystemID.clear();
    this.fExpandedSystemID.clear();
    this.fLanguageScope.clear();
    this.fLanguageStack.clear();
    for (byte b1 = 0; b1 < this.fState.length; b1++)
      this.fState[b1] = 1; 
    for (byte b2 = 0; b2 < this.fSawFallback.length; b2++)
      this.fSawFallback[b2] = false; 
    for (byte b3 = 0; b3 < this.fSawInclude.length; b3++)
      this.fSawInclude[b3] = false; 
    try {
      if (!paramXMLComponentManager.getFeature("http://apache.org/xml/features/internal/parser-settings"))
        return; 
    } catch (XMLConfigurationException xMLConfigurationException) {}
    this.fNeedCopyFeatures = true;
    try {
      this.fSendUEAndNotationEvents = paramXMLComponentManager.getFeature("http://xml.org/sax/features/allow-dtd-events-after-endDTD");
      if (this.fChildConfig != null)
        this.fChildConfig.setFeature("http://xml.org/sax/features/allow-dtd-events-after-endDTD", this.fSendUEAndNotationEvents); 
    } catch (XMLConfigurationException xMLConfigurationException) {}
    try {
      this.fFixupBaseURIs = paramXMLComponentManager.getFeature("http://apache.org/xml/features/xinclude/fixup-base-uris");
      if (this.fChildConfig != null)
        this.fChildConfig.setFeature("http://apache.org/xml/features/xinclude/fixup-base-uris", this.fFixupBaseURIs); 
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fFixupBaseURIs = true;
    } 
    try {
      this.fFixupLanguage = paramXMLComponentManager.getFeature("http://apache.org/xml/features/xinclude/fixup-language");
      if (this.fChildConfig != null)
        this.fChildConfig.setFeature("http://apache.org/xml/features/xinclude/fixup-language", this.fFixupLanguage); 
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fFixupLanguage = true;
    } 
    try {
      SymbolTable symbolTable = (SymbolTable)paramXMLComponentManager.getProperty("http://apache.org/xml/properties/internal/symbol-table");
      if (symbolTable != null) {
        this.fSymbolTable = symbolTable;
        if (this.fChildConfig != null)
          this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/symbol-table", symbolTable); 
      } 
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fSymbolTable = null;
    } 
    try {
      XMLErrorReporter xMLErrorReporter = (XMLErrorReporter)paramXMLComponentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
      if (xMLErrorReporter != null) {
        setErrorReporter(xMLErrorReporter);
        if (this.fChildConfig != null)
          this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/error-reporter", xMLErrorReporter); 
      } 
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fErrorReporter = null;
    } 
    try {
      XMLEntityResolver xMLEntityResolver = (XMLEntityResolver)paramXMLComponentManager.getProperty("http://apache.org/xml/properties/internal/entity-resolver");
      if (xMLEntityResolver != null) {
        this.fEntityResolver = xMLEntityResolver;
        if (this.fChildConfig != null)
          this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/entity-resolver", xMLEntityResolver); 
      } 
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fEntityResolver = null;
    } 
    try {
      SecurityManager securityManager = (SecurityManager)paramXMLComponentManager.getProperty("http://apache.org/xml/properties/security-manager");
      if (securityManager != null) {
        this.fSecurityManager = securityManager;
        if (this.fChildConfig != null)
          this.fChildConfig.setProperty("http://apache.org/xml/properties/security-manager", securityManager); 
      } 
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fSecurityManager = null;
    } 
    try {
      Integer integer = (Integer)paramXMLComponentManager.getProperty("http://apache.org/xml/properties/input-buffer-size");
      if (integer != null && integer.intValue() > 0) {
        this.fBufferSize = integer.intValue();
        if (this.fChildConfig != null)
          this.fChildConfig.setProperty("http://apache.org/xml/properties/input-buffer-size", integer); 
      } else {
        this.fBufferSize = ((Integer)getPropertyDefault("http://apache.org/xml/properties/input-buffer-size")).intValue();
      } 
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fBufferSize = ((Integer)getPropertyDefault("http://apache.org/xml/properties/input-buffer-size")).intValue();
    } 
    if (this.fXInclude10TextReader != null)
      this.fXInclude10TextReader.setBufferSize(this.fBufferSize); 
    if (this.fXInclude11TextReader != null)
      this.fXInclude11TextReader.setBufferSize(this.fBufferSize); 
    this.fSettings = new ParserConfigurationSettings();
    copyFeatures(paramXMLComponentManager, this.fSettings);
    try {
      if (paramXMLComponentManager.getFeature("http://apache.org/xml/features/validation/schema")) {
        this.fSettings.setFeature("http://apache.org/xml/features/validation/schema", false);
        if (Constants.NS_XMLSCHEMA.equals(paramXMLComponentManager.getProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage"))) {
          this.fSettings.setFeature("http://xml.org/sax/features/validation", false);
        } else if (paramXMLComponentManager.getFeature("http://xml.org/sax/features/validation")) {
          this.fSettings.setFeature("http://apache.org/xml/features/validation/dynamic", true);
        } 
      } 
    } catch (XMLConfigurationException xMLConfigurationException) {}
  }
  
  public String[] getRecognizedFeatures() {
    return (String[])RECOGNIZED_FEATURES.clone();
  }
  
  public void setFeature(String paramString, boolean paramBoolean) throws XMLConfigurationException {
    if (paramString.equals("http://xml.org/sax/features/allow-dtd-events-after-endDTD"))
      this.fSendUEAndNotationEvents = paramBoolean; 
    if (this.fSettings != null) {
      this.fNeedCopyFeatures = true;
      this.fSettings.setFeature(paramString, paramBoolean);
    } 
  }
  
  public String[] getRecognizedProperties() {
    return (String[])RECOGNIZED_PROPERTIES.clone();
  }
  
  public void setProperty(String paramString, Object paramObject) throws XMLConfigurationException {
    if (paramString.equals("http://apache.org/xml/properties/internal/symbol-table")) {
      this.fSymbolTable = (SymbolTable)paramObject;
      if (this.fChildConfig != null)
        this.fChildConfig.setProperty(paramString, paramObject); 
      return;
    } 
    if (paramString.equals("http://apache.org/xml/properties/internal/error-reporter")) {
      setErrorReporter((XMLErrorReporter)paramObject);
      if (this.fChildConfig != null)
        this.fChildConfig.setProperty(paramString, paramObject); 
      return;
    } 
    if (paramString.equals("http://apache.org/xml/properties/internal/entity-resolver")) {
      this.fEntityResolver = (XMLEntityResolver)paramObject;
      if (this.fChildConfig != null)
        this.fChildConfig.setProperty(paramString, paramObject); 
      return;
    } 
    if (paramString.equals("http://apache.org/xml/properties/security-manager")) {
      this.fSecurityManager = (SecurityManager)paramObject;
      if (this.fChildConfig != null)
        this.fChildConfig.setProperty(paramString, paramObject); 
      return;
    } 
    if (paramString.equals("http://apache.org/xml/properties/input-buffer-size")) {
      Integer integer = (Integer)paramObject;
      if (this.fChildConfig != null)
        this.fChildConfig.setProperty(paramString, paramObject); 
      if (integer != null && integer.intValue() > 0) {
        this.fBufferSize = integer.intValue();
        if (this.fXInclude10TextReader != null)
          this.fXInclude10TextReader.setBufferSize(this.fBufferSize); 
        if (this.fXInclude11TextReader != null)
          this.fXInclude11TextReader.setBufferSize(this.fBufferSize); 
      } 
      return;
    } 
  }
  
  public Boolean getFeatureDefault(String paramString) {
    for (byte b = 0; b < RECOGNIZED_FEATURES.length; b++) {
      if (RECOGNIZED_FEATURES[b].equals(paramString))
        return FEATURE_DEFAULTS[b]; 
    } 
    return null;
  }
  
  public Object getPropertyDefault(String paramString) {
    for (byte b = 0; b < RECOGNIZED_PROPERTIES.length; b++) {
      if (RECOGNIZED_PROPERTIES[b].equals(paramString))
        return PROPERTY_DEFAULTS[b]; 
    } 
    return null;
  }
  
  public void setDocumentHandler(XMLDocumentHandler paramXMLDocumentHandler) {
    if (this.fDocumentHandler != paramXMLDocumentHandler) {
      this.fDocumentHandler = paramXMLDocumentHandler;
      if (this.fXIncludeChildConfig != null)
        this.fXIncludeChildConfig.setDocumentHandler(paramXMLDocumentHandler); 
      if (this.fXPointerChildConfig != null)
        this.fXPointerChildConfig.setDocumentHandler(paramXMLDocumentHandler); 
    } 
  }
  
  public XMLDocumentHandler getDocumentHandler() {
    return this.fDocumentHandler;
  }
  
  public void startDocument(XMLLocator paramXMLLocator, String paramString, NamespaceContext paramNamespaceContext, Augmentations paramAugmentations) throws XNIException {
    AugmentationsImpl augmentationsImpl;
    this.fErrorReporter.setDocumentLocator(paramXMLLocator);
    if (!(paramNamespaceContext instanceof XIncludeNamespaceSupport))
      reportFatalError("IncompatibleNamespaceContext"); 
    this.fNamespaceContext = (XIncludeNamespaceSupport)paramNamespaceContext;
    this.fDocLocation = paramXMLLocator;
    this.fXIncludeLocator.setLocator(this.fDocLocation);
    setupCurrentBaseURI(paramXMLLocator);
    saveBaseURI();
    if (paramAugmentations == null)
      augmentationsImpl = new AugmentationsImpl(); 
    augmentationsImpl.putItem("currentBaseURI", this.fCurrentBaseURI);
    if (!isRootDocument()) {
      this.fParentXIncludeHandler.fHasIncludeReportedContent = true;
      if (this.fParentXIncludeHandler.searchForRecursiveIncludes(this.fCurrentBaseURI.getExpandedSystemId()))
        reportFatalError("RecursiveInclude", new Object[] { this.fCurrentBaseURI.getExpandedSystemId() }); 
    } 
    this.fCurrentLanguage = XMLSymbols.EMPTY_STRING;
    saveLanguage(this.fCurrentLanguage);
    if (isRootDocument() && this.fDocumentHandler != null)
      this.fDocumentHandler.startDocument((XMLLocator)this.fXIncludeLocator, paramString, paramNamespaceContext, (Augmentations)augmentationsImpl); 
  }
  
  public void xmlDecl(String paramString1, String paramString2, String paramString3, Augmentations paramAugmentations) throws XNIException {
    this.fIsXML11 = "1.1".equals(paramString1);
    if (isRootDocument() && this.fDocumentHandler != null)
      this.fDocumentHandler.xmlDecl(paramString1, paramString2, paramString3, paramAugmentations); 
  }
  
  public void doctypeDecl(String paramString1, String paramString2, String paramString3, Augmentations paramAugmentations) throws XNIException {
    if (isRootDocument() && this.fDocumentHandler != null)
      this.fDocumentHandler.doctypeDecl(paramString1, paramString2, paramString3, paramAugmentations); 
  }
  
  public void comment(XMLString paramXMLString, Augmentations paramAugmentations) throws XNIException {
    if (!this.fInDTD) {
      if (this.fDocumentHandler != null && getState() == 1) {
        this.fDepth++;
        paramAugmentations = modifyAugmentations(paramAugmentations);
        this.fDocumentHandler.comment(paramXMLString, paramAugmentations);
        this.fDepth--;
      } 
    } else if (this.fDTDHandler != null) {
      this.fDTDHandler.comment(paramXMLString, paramAugmentations);
    } 
  }
  
  public void processingInstruction(String paramString, XMLString paramXMLString, Augmentations paramAugmentations) throws XNIException {
    if (!this.fInDTD) {
      if (this.fDocumentHandler != null && getState() == 1) {
        this.fDepth++;
        paramAugmentations = modifyAugmentations(paramAugmentations);
        this.fDocumentHandler.processingInstruction(paramString, paramXMLString, paramAugmentations);
        this.fDepth--;
      } 
    } else if (this.fDTDHandler != null) {
      this.fDTDHandler.processingInstruction(paramString, paramXMLString, paramAugmentations);
    } 
  }
  
  public void startElement(QName paramQName, XMLAttributes paramXMLAttributes, Augmentations paramAugmentations) throws XNIException {
    this.fDepth++;
    int i = getState(this.fDepth - 1);
    if (i == 3 && getState(this.fDepth - 2) == 3) {
      setState(2);
    } else {
      setState(i);
    } 
    processXMLBaseAttributes(paramXMLAttributes);
    if (this.fFixupLanguage)
      processXMLLangAttributes(paramXMLAttributes); 
    if (isIncludeElement(paramQName)) {
      boolean bool = handleIncludeElement(paramXMLAttributes);
      if (bool) {
        setState(2);
      } else {
        setState(3);
      } 
    } else if (isFallbackElement(paramQName)) {
      handleFallbackElement();
    } else if (hasXIncludeNamespace(paramQName)) {
      if (getSawInclude(this.fDepth - 1))
        reportFatalError("IncludeChild", new Object[] { paramQName.rawname }); 
      if (getSawFallback(this.fDepth - 1))
        reportFatalError("FallbackChild", new Object[] { paramQName.rawname }); 
      if (getState() == 1) {
        if (this.fResultDepth++ == 0)
          checkMultipleRootElements(); 
        if (this.fDocumentHandler != null) {
          paramAugmentations = modifyAugmentations(paramAugmentations);
          paramXMLAttributes = processAttributes(paramXMLAttributes);
          this.fDocumentHandler.startElement(paramQName, paramXMLAttributes, paramAugmentations);
        } 
      } 
    } else if (getState() == 1) {
      if (this.fResultDepth++ == 0)
        checkMultipleRootElements(); 
      if (this.fDocumentHandler != null) {
        paramAugmentations = modifyAugmentations(paramAugmentations);
        paramXMLAttributes = processAttributes(paramXMLAttributes);
        this.fDocumentHandler.startElement(paramQName, paramXMLAttributes, paramAugmentations);
      } 
    } 
  }
  
  public void emptyElement(QName paramQName, XMLAttributes paramXMLAttributes, Augmentations paramAugmentations) throws XNIException {
    this.fDepth++;
    int i = getState(this.fDepth - 1);
    if (i == 3 && getState(this.fDepth - 2) == 3) {
      setState(2);
    } else {
      setState(i);
    } 
    processXMLBaseAttributes(paramXMLAttributes);
    if (this.fFixupLanguage)
      processXMLLangAttributes(paramXMLAttributes); 
    if (isIncludeElement(paramQName)) {
      boolean bool = handleIncludeElement(paramXMLAttributes);
      if (bool) {
        setState(2);
      } else {
        reportFatalError("NoFallback");
      } 
    } else if (isFallbackElement(paramQName)) {
      handleFallbackElement();
    } else if (hasXIncludeNamespace(paramQName)) {
      if (getSawInclude(this.fDepth - 1))
        reportFatalError("IncludeChild", new Object[] { paramQName.rawname }); 
      if (getSawFallback(this.fDepth - 1))
        reportFatalError("FallbackChild", new Object[] { paramQName.rawname }); 
      if (getState() == 1) {
        if (this.fResultDepth == 0)
          checkMultipleRootElements(); 
        if (this.fDocumentHandler != null) {
          paramAugmentations = modifyAugmentations(paramAugmentations);
          paramXMLAttributes = processAttributes(paramXMLAttributes);
          this.fDocumentHandler.emptyElement(paramQName, paramXMLAttributes, paramAugmentations);
        } 
      } 
    } else if (getState() == 1) {
      if (this.fResultDepth == 0)
        checkMultipleRootElements(); 
      if (this.fDocumentHandler != null) {
        paramAugmentations = modifyAugmentations(paramAugmentations);
        paramXMLAttributes = processAttributes(paramXMLAttributes);
        this.fDocumentHandler.emptyElement(paramQName, paramXMLAttributes, paramAugmentations);
      } 
    } 
    setSawFallback(this.fDepth + 1, false);
    setSawInclude(this.fDepth, false);
    if (this.fBaseURIScope.size() > 0 && this.fDepth == this.fBaseURIScope.peek())
      restoreBaseURI(); 
    this.fDepth--;
  }
  
  public void endElement(QName paramQName, Augmentations paramAugmentations) throws XNIException {
    if (isIncludeElement(paramQName) && getState() == 3 && !getSawFallback(this.fDepth + 1))
      reportFatalError("NoFallback"); 
    if (isFallbackElement(paramQName)) {
      if (getState() == 1)
        setState(2); 
    } else if (getState() == 1) {
      this.fResultDepth--;
      if (this.fDocumentHandler != null)
        this.fDocumentHandler.endElement(paramQName, paramAugmentations); 
    } 
    setSawFallback(this.fDepth + 1, false);
    setSawInclude(this.fDepth, false);
    if (this.fBaseURIScope.size() > 0 && this.fDepth == this.fBaseURIScope.peek())
      restoreBaseURI(); 
    if (this.fLanguageScope.size() > 0 && this.fDepth == this.fLanguageScope.peek())
      this.fCurrentLanguage = restoreLanguage(); 
    this.fDepth--;
  }
  
  public void startGeneralEntity(String paramString1, XMLResourceIdentifier paramXMLResourceIdentifier, String paramString2, Augmentations paramAugmentations) throws XNIException {
    if (getState() == 1)
      if (this.fResultDepth == 0) {
        if (paramAugmentations != null && Boolean.TRUE.equals(paramAugmentations.getItem("ENTITY_SKIPPED")))
          reportFatalError("UnexpandedEntityReferenceIllegal"); 
      } else if (this.fDocumentHandler != null) {
        this.fDocumentHandler.startGeneralEntity(paramString1, paramXMLResourceIdentifier, paramString2, paramAugmentations);
      }  
  }
  
  public void textDecl(String paramString1, String paramString2, Augmentations paramAugmentations) throws XNIException {
    if (this.fDocumentHandler != null && getState() == 1)
      this.fDocumentHandler.textDecl(paramString1, paramString2, paramAugmentations); 
  }
  
  public void endGeneralEntity(String paramString, Augmentations paramAugmentations) throws XNIException {
    if (this.fDocumentHandler != null && getState() == 1 && this.fResultDepth != 0)
      this.fDocumentHandler.endGeneralEntity(paramString, paramAugmentations); 
  }
  
  public void characters(XMLString paramXMLString, Augmentations paramAugmentations) throws XNIException {
    if (getState() == 1)
      if (this.fResultDepth == 0) {
        checkWhitespace(paramXMLString);
      } else if (this.fDocumentHandler != null) {
        this.fDepth++;
        paramAugmentations = modifyAugmentations(paramAugmentations);
        this.fDocumentHandler.characters(paramXMLString, paramAugmentations);
        this.fDepth--;
      }  
  }
  
  public void ignorableWhitespace(XMLString paramXMLString, Augmentations paramAugmentations) throws XNIException {
    if (this.fDocumentHandler != null && getState() == 1 && this.fResultDepth != 0)
      this.fDocumentHandler.ignorableWhitespace(paramXMLString, paramAugmentations); 
  }
  
  public void startCDATA(Augmentations paramAugmentations) throws XNIException {
    if (this.fDocumentHandler != null && getState() == 1 && this.fResultDepth != 0)
      this.fDocumentHandler.startCDATA(paramAugmentations); 
  }
  
  public void endCDATA(Augmentations paramAugmentations) throws XNIException {
    if (this.fDocumentHandler != null && getState() == 1 && this.fResultDepth != 0)
      this.fDocumentHandler.endCDATA(paramAugmentations); 
  }
  
  public void endDocument(Augmentations paramAugmentations) throws XNIException {
    if (isRootDocument()) {
      if (!this.fSeenRootElement)
        reportFatalError("RootElementRequired"); 
      if (this.fDocumentHandler != null)
        this.fDocumentHandler.endDocument(paramAugmentations); 
    } 
  }
  
  public void setDocumentSource(XMLDocumentSource paramXMLDocumentSource) {
    this.fDocumentSource = paramXMLDocumentSource;
  }
  
  public XMLDocumentSource getDocumentSource() {
    return this.fDocumentSource;
  }
  
  public void attributeDecl(String paramString1, String paramString2, String paramString3, String[] paramArrayOfString, String paramString4, XMLString paramXMLString1, XMLString paramXMLString2, Augmentations paramAugmentations) throws XNIException {
    if (this.fDTDHandler != null)
      this.fDTDHandler.attributeDecl(paramString1, paramString2, paramString3, paramArrayOfString, paramString4, paramXMLString1, paramXMLString2, paramAugmentations); 
  }
  
  public void elementDecl(String paramString1, String paramString2, Augmentations paramAugmentations) throws XNIException {
    if (this.fDTDHandler != null)
      this.fDTDHandler.elementDecl(paramString1, paramString2, paramAugmentations); 
  }
  
  public void endAttlist(Augmentations paramAugmentations) throws XNIException {
    if (this.fDTDHandler != null)
      this.fDTDHandler.endAttlist(paramAugmentations); 
  }
  
  public void endConditional(Augmentations paramAugmentations) throws XNIException {
    if (this.fDTDHandler != null)
      this.fDTDHandler.endConditional(paramAugmentations); 
  }
  
  public void endDTD(Augmentations paramAugmentations) throws XNIException {
    if (this.fDTDHandler != null)
      this.fDTDHandler.endDTD(paramAugmentations); 
    this.fInDTD = false;
  }
  
  public void endExternalSubset(Augmentations paramAugmentations) throws XNIException {
    if (this.fDTDHandler != null)
      this.fDTDHandler.endExternalSubset(paramAugmentations); 
  }
  
  public void endParameterEntity(String paramString, Augmentations paramAugmentations) throws XNIException {
    if (this.fDTDHandler != null)
      this.fDTDHandler.endParameterEntity(paramString, paramAugmentations); 
  }
  
  public void externalEntityDecl(String paramString, XMLResourceIdentifier paramXMLResourceIdentifier, Augmentations paramAugmentations) throws XNIException {
    if (this.fDTDHandler != null)
      this.fDTDHandler.externalEntityDecl(paramString, paramXMLResourceIdentifier, paramAugmentations); 
  }
  
  public XMLDTDSource getDTDSource() {
    return this.fDTDSource;
  }
  
  public void ignoredCharacters(XMLString paramXMLString, Augmentations paramAugmentations) throws XNIException {
    if (this.fDTDHandler != null)
      this.fDTDHandler.ignoredCharacters(paramXMLString, paramAugmentations); 
  }
  
  public void internalEntityDecl(String paramString, XMLString paramXMLString1, XMLString paramXMLString2, Augmentations paramAugmentations) throws XNIException {
    if (this.fDTDHandler != null)
      this.fDTDHandler.internalEntityDecl(paramString, paramXMLString1, paramXMLString2, paramAugmentations); 
  }
  
  public void notationDecl(String paramString, XMLResourceIdentifier paramXMLResourceIdentifier, Augmentations paramAugmentations) throws XNIException {
    addNotation(paramString, paramXMLResourceIdentifier, paramAugmentations);
    if (this.fDTDHandler != null)
      this.fDTDHandler.notationDecl(paramString, paramXMLResourceIdentifier, paramAugmentations); 
  }
  
  public void setDTDSource(XMLDTDSource paramXMLDTDSource) {
    this.fDTDSource = paramXMLDTDSource;
  }
  
  public void startAttlist(String paramString, Augmentations paramAugmentations) throws XNIException {
    if (this.fDTDHandler != null)
      this.fDTDHandler.startAttlist(paramString, paramAugmentations); 
  }
  
  public void startConditional(short paramShort, Augmentations paramAugmentations) throws XNIException {
    if (this.fDTDHandler != null)
      this.fDTDHandler.startConditional(paramShort, paramAugmentations); 
  }
  
  public void startDTD(XMLLocator paramXMLLocator, Augmentations paramAugmentations) throws XNIException {
    this.fInDTD = true;
    if (this.fDTDHandler != null)
      this.fDTDHandler.startDTD(paramXMLLocator, paramAugmentations); 
  }
  
  public void startExternalSubset(XMLResourceIdentifier paramXMLResourceIdentifier, Augmentations paramAugmentations) throws XNIException {
    if (this.fDTDHandler != null)
      this.fDTDHandler.startExternalSubset(paramXMLResourceIdentifier, paramAugmentations); 
  }
  
  public void startParameterEntity(String paramString1, XMLResourceIdentifier paramXMLResourceIdentifier, String paramString2, Augmentations paramAugmentations) throws XNIException {
    if (this.fDTDHandler != null)
      this.fDTDHandler.startParameterEntity(paramString1, paramXMLResourceIdentifier, paramString2, paramAugmentations); 
  }
  
  public void unparsedEntityDecl(String paramString1, XMLResourceIdentifier paramXMLResourceIdentifier, String paramString2, Augmentations paramAugmentations) throws XNIException {
    addUnparsedEntity(paramString1, paramXMLResourceIdentifier, paramString2, paramAugmentations);
    if (this.fDTDHandler != null)
      this.fDTDHandler.unparsedEntityDecl(paramString1, paramXMLResourceIdentifier, paramString2, paramAugmentations); 
  }
  
  public XMLDTDHandler getDTDHandler() {
    return this.fDTDHandler;
  }
  
  public void setDTDHandler(XMLDTDHandler paramXMLDTDHandler) {
    this.fDTDHandler = paramXMLDTDHandler;
  }
  
  private void setErrorReporter(XMLErrorReporter paramXMLErrorReporter) {
    this.fErrorReporter = paramXMLErrorReporter;
    if (this.fErrorReporter != null) {
      this.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/xinclude", this.fXIncludeMessageFormatter);
      if (this.fDocLocation != null)
        this.fErrorReporter.setDocumentLocator(this.fDocLocation); 
    } 
  }
  
  protected void handleFallbackElement() {
    if (!getSawInclude(this.fDepth - 1)) {
      if (getState() == 2)
        return; 
      reportFatalError("FallbackParent");
    } 
    setSawInclude(this.fDepth, false);
    this.fNamespaceContext.setContextInvalid();
    if (getSawFallback(this.fDepth)) {
      reportFatalError("MultipleFallbacks");
    } else {
      setSawFallback(this.fDepth, true);
    } 
    if (getState() == 3)
      setState(1); 
  }
  
  protected boolean handleIncludeElement(XMLAttributes paramXMLAttributes) throws XNIException {
    if (getSawInclude(this.fDepth - 1))
      reportFatalError("IncludeChild", new Object[] { XINCLUDE_INCLUDE }); 
    if (getState() == 2)
      return true; 
    setSawInclude(this.fDepth, true);
    this.fNamespaceContext.setContextInvalid();
    String str1 = paramXMLAttributes.getValue(XINCLUDE_ATTR_HREF);
    String str2 = paramXMLAttributes.getValue(XINCLUDE_ATTR_PARSE);
    String str3 = paramXMLAttributes.getValue("xpointer");
    String str4 = paramXMLAttributes.getValue(XINCLUDE_ATTR_ACCEPT);
    String str5 = paramXMLAttributes.getValue(XINCLUDE_ATTR_ACCEPT_LANGUAGE);
    if (str2 == null)
      str2 = XINCLUDE_PARSE_XML; 
    if (str1 == null)
      str1 = XMLSymbols.EMPTY_STRING; 
    if (str1.length() == 0 && XINCLUDE_PARSE_XML.equals(str2))
      if (str3 == null) {
        reportFatalError("XpointerMissing");
      } else {
        Locale locale = (this.fErrorReporter != null) ? this.fErrorReporter.getLocale() : null;
        String str = this.fXIncludeMessageFormatter.formatMessage(locale, "XPointerStreamability", null);
        reportResourceError("XMLResourceError", new Object[] { str1, str });
        return false;
      }  
    URI uRI = null;
    try {
      uRI = new URI(str1, true);
      if (uRI.getFragment() != null)
        reportFatalError("HrefFragmentIdentifierIllegal", new Object[] { str1 }); 
    } catch (org.apache.xerces.util.URI.MalformedURIException malformedURIException) {
      String str = escapeHref(str1);
      if (str1 != str) {
        str1 = str;
        try {
          uRI = new URI(str1, true);
          if (uRI.getFragment() != null)
            reportFatalError("HrefFragmentIdentifierIllegal", new Object[] { str1 }); 
        } catch (org.apache.xerces.util.URI.MalformedURIException malformedURIException1) {
          reportFatalError("HrefSyntacticallyInvalid", new Object[] { str1 });
        } 
      } else {
        reportFatalError("HrefSyntacticallyInvalid", new Object[] { str1 });
      } 
    } 
    if (str4 != null && !isValidInHTTPHeader(str4)) {
      reportFatalError("AcceptMalformed", null);
      str4 = null;
    } 
    if (str5 != null && !isValidInHTTPHeader(str5)) {
      reportFatalError("AcceptLanguageMalformed", null);
      str5 = null;
    } 
    XMLInputSource xMLInputSource = null;
    if (this.fEntityResolver != null)
      try {
        XMLResourceIdentifierImpl xMLResourceIdentifierImpl = new XMLResourceIdentifierImpl(null, str1, this.fCurrentBaseURI.getExpandedSystemId(), XMLEntityManager.expandSystemId(str1, this.fCurrentBaseURI.getExpandedSystemId(), false));
        xMLInputSource = this.fEntityResolver.resolveEntity((XMLResourceIdentifier)xMLResourceIdentifierImpl);
        if (xMLInputSource != null && !(xMLInputSource instanceof HTTPInputSource) && (str4 != null || str5 != null) && xMLInputSource.getCharacterStream() == null && xMLInputSource.getByteStream() == null)
          xMLInputSource = createInputSource(xMLInputSource.getPublicId(), xMLInputSource.getSystemId(), xMLInputSource.getBaseSystemId(), str4, str5); 
      } catch (IOException iOException) {
        reportResourceError("XMLResourceError", new Object[] { str1, iOException.getMessage() }, iOException);
        return false;
      }  
    if (xMLInputSource == null)
      if (str4 != null || str5 != null) {
        xMLInputSource = createInputSource(null, str1, this.fCurrentBaseURI.getExpandedSystemId(), str4, str5);
      } else {
        xMLInputSource = new XMLInputSource(null, str1, this.fCurrentBaseURI.getExpandedSystemId());
      }  
    if (str2.equals(XINCLUDE_PARSE_XML)) {
      if ((str3 != null && this.fXPointerChildConfig == null) || (str3 == null && this.fXIncludeChildConfig == null)) {
        String str = "org.apache.xerces.parsers.XIncludeParserConfiguration";
        if (str3 != null)
          str = "org.apache.xerces.parsers.XPointerParserConfiguration"; 
        this.fChildConfig = (XMLParserConfiguration)ObjectFactory.newInstance(str, ObjectFactory.findClassLoader(), true);
        if (this.fSymbolTable != null)
          this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/symbol-table", this.fSymbolTable); 
        if (this.fErrorReporter != null)
          this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter); 
        if (this.fEntityResolver != null)
          this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/entity-resolver", this.fEntityResolver); 
        this.fChildConfig.setProperty("http://apache.org/xml/properties/security-manager", this.fSecurityManager);
        this.fChildConfig.setProperty("http://apache.org/xml/properties/input-buffer-size", new Integer(this.fBufferSize));
        this.fNeedCopyFeatures = true;
        this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/namespace-context", this.fNamespaceContext);
        this.fChildConfig.setFeature("http://apache.org/xml/features/xinclude/fixup-base-uris", this.fFixupBaseURIs);
        this.fChildConfig.setFeature("http://apache.org/xml/features/xinclude/fixup-language", this.fFixupLanguage);
        if (str3 != null) {
          XPointerHandler xPointerHandler = (XPointerHandler)this.fChildConfig.getProperty("http://apache.org/xml/properties/internal/xpointer-handler");
          this.fXPtrProcessor = (XPointerProcessor)xPointerHandler;
          ((XPointerHandler)this.fXPtrProcessor).setProperty("http://apache.org/xml/properties/internal/namespace-context", this.fNamespaceContext);
          ((XPointerHandler)this.fXPtrProcessor).setProperty("http://apache.org/xml/features/xinclude/fixup-base-uris", this.fFixupBaseURIs ? Boolean.TRUE : Boolean.FALSE);
          ((XPointerHandler)this.fXPtrProcessor).setProperty("http://apache.org/xml/features/xinclude/fixup-language", this.fFixupLanguage ? Boolean.TRUE : Boolean.FALSE);
          if (this.fErrorReporter != null)
            ((XPointerHandler)this.fXPtrProcessor).setProperty("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter); 
          xPointerHandler.setParent(this);
          xPointerHandler.setHref(str1);
          xPointerHandler.setXIncludeLocator(this.fXIncludeLocator);
          xPointerHandler.setDocumentHandler(getDocumentHandler());
          this.fXPointerChildConfig = this.fChildConfig;
        } else {
          XIncludeHandler xIncludeHandler = (XIncludeHandler)this.fChildConfig.getProperty("http://apache.org/xml/properties/internal/xinclude-handler");
          xIncludeHandler.setParent(this);
          xIncludeHandler.setHref(str1);
          xIncludeHandler.setXIncludeLocator(this.fXIncludeLocator);
          xIncludeHandler.setDocumentHandler(getDocumentHandler());
          this.fXIncludeChildConfig = this.fChildConfig;
        } 
      } 
      if (str3 != null) {
        this.fChildConfig = this.fXPointerChildConfig;
        try {
          this.fXPtrProcessor.parseXPointer(str3);
        } catch (XNIException xNIException) {
          reportResourceError("XMLResourceError", new Object[] { str1, xNIException.getMessage() });
          return false;
        } 
      } else {
        this.fChildConfig = this.fXIncludeChildConfig;
      } 
      if (this.fNeedCopyFeatures)
        copyFeatures((XMLComponentManager)this.fSettings, this.fChildConfig); 
      this.fNeedCopyFeatures = false;
      try {
        this.fHasIncludeReportedContent = false;
        this.fNamespaceContext.pushScope();
        this.fChildConfig.parse(xMLInputSource);
        this.fXIncludeLocator.setLocator(this.fDocLocation);
        if (this.fErrorReporter != null)
          this.fErrorReporter.setDocumentLocator(this.fDocLocation); 
        if (str3 != null && !this.fXPtrProcessor.isXPointerResolved()) {
          Locale locale = (this.fErrorReporter != null) ? this.fErrorReporter.getLocale() : null;
          String str = this.fXIncludeMessageFormatter.formatMessage(locale, "XPointerResolutionUnsuccessful", null);
          reportResourceError("XMLResourceError", new Object[] { str1, str });
          return false;
        } 
      } catch (XNIException xNIException) {
        this.fXIncludeLocator.setLocator(this.fDocLocation);
        if (this.fErrorReporter != null)
          this.fErrorReporter.setDocumentLocator(this.fDocLocation); 
        reportFatalError("XMLParseError", new Object[] { str1 });
      } catch (IOException iOException) {
        this.fXIncludeLocator.setLocator(this.fDocLocation);
        if (this.fErrorReporter != null)
          this.fErrorReporter.setDocumentLocator(this.fDocLocation); 
        if (this.fHasIncludeReportedContent)
          throw new XNIException(iOException); 
        reportResourceError("XMLResourceError", new Object[] { str1, iOException.getMessage() }, iOException);
        return false;
      } finally {
        this.fNamespaceContext.popScope();
      } 
    } else if (str2.equals(XINCLUDE_PARSE_TEXT)) {
      String str = paramXMLAttributes.getValue(XINCLUDE_ATTR_ENCODING);
      xMLInputSource.setEncoding(str);
      XIncludeTextReader xIncludeTextReader = null;
      try {
        this.fHasIncludeReportedContent = false;
        if (!this.fIsXML11) {
          if (this.fXInclude10TextReader == null) {
            this.fXInclude10TextReader = new XIncludeTextReader(xMLInputSource, this, this.fBufferSize);
          } else {
            this.fXInclude10TextReader.setInputSource(xMLInputSource);
          } 
          xIncludeTextReader = this.fXInclude10TextReader;
        } else {
          if (this.fXInclude11TextReader == null) {
            this.fXInclude11TextReader = new XInclude11TextReader(xMLInputSource, this, this.fBufferSize);
          } else {
            this.fXInclude11TextReader.setInputSource(xMLInputSource);
          } 
          xIncludeTextReader = this.fXInclude11TextReader;
        } 
        xIncludeTextReader.setErrorReporter(this.fErrorReporter);
        xIncludeTextReader.parse();
      } catch (MalformedByteSequenceException malformedByteSequenceException) {
        this.fErrorReporter.reportError(malformedByteSequenceException.getDomain(), malformedByteSequenceException.getKey(), malformedByteSequenceException.getArguments(), (short)2, (Exception)malformedByteSequenceException);
      } catch (CharConversionException charConversionException) {
        this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "CharConversionFailure", null, (short)2, charConversionException);
      } catch (IOException iOException) {
        if (this.fHasIncludeReportedContent)
          throw new XNIException(iOException); 
        reportResourceError("TextResourceError", new Object[] { str1, iOException.getMessage() }, iOException);
        return false;
      } finally {
        if (xIncludeTextReader != null)
          try {
            xIncludeTextReader.close();
          } catch (IOException iOException) {
            reportResourceError("TextResourceError", new Object[] { str1, iOException.getMessage() }, iOException);
            return false;
          }  
      } 
    } else {
      reportFatalError("InvalidParseValue", new Object[] { str2 });
    } 
    return true;
  }
  
  protected boolean hasXIncludeNamespace(QName paramQName) {
    return (paramQName.uri == XINCLUDE_NS_URI || this.fNamespaceContext.getURI(paramQName.prefix) == XINCLUDE_NS_URI);
  }
  
  protected boolean isIncludeElement(QName paramQName) {
    return (paramQName.localpart.equals(XINCLUDE_INCLUDE) && hasXIncludeNamespace(paramQName));
  }
  
  protected boolean isFallbackElement(QName paramQName) {
    return (paramQName.localpart.equals(XINCLUDE_FALLBACK) && hasXIncludeNamespace(paramQName));
  }
  
  protected boolean sameBaseURIAsIncludeParent() {
    String str1 = getIncludeParentBaseURI();
    String str2 = this.fCurrentBaseURI.getExpandedSystemId();
    return (str1 != null && str1.equals(str2));
  }
  
  protected boolean sameLanguageAsIncludeParent() {
    String str = getIncludeParentLanguage();
    return (str != null && str.equalsIgnoreCase(this.fCurrentLanguage));
  }
  
  protected void setupCurrentBaseURI(XMLLocator paramXMLLocator) {
    this.fCurrentBaseURI.setBaseSystemId(paramXMLLocator.getBaseSystemId());
    if (paramXMLLocator.getLiteralSystemId() != null) {
      this.fCurrentBaseURI.setLiteralSystemId(paramXMLLocator.getLiteralSystemId());
    } else {
      this.fCurrentBaseURI.setLiteralSystemId(this.fHrefFromParent);
    } 
    String str = paramXMLLocator.getExpandedSystemId();
    if (str == null)
      try {
        str = XMLEntityManager.expandSystemId(this.fCurrentBaseURI.getLiteralSystemId(), this.fCurrentBaseURI.getBaseSystemId(), false);
        if (str == null)
          str = this.fCurrentBaseURI.getLiteralSystemId(); 
      } catch (org.apache.xerces.util.URI.MalformedURIException malformedURIException) {
        reportFatalError("ExpandedSystemId");
      }  
    this.fCurrentBaseURI.setExpandedSystemId(str);
  }
  
  protected boolean searchForRecursiveIncludes(String paramString) {
    return paramString.equals(this.fCurrentBaseURI.getExpandedSystemId()) ? true : ((this.fParentXIncludeHandler == null) ? false : this.fParentXIncludeHandler.searchForRecursiveIncludes(paramString));
  }
  
  protected boolean isTopLevelIncludedItem() {
    return (isTopLevelIncludedItemViaInclude() || isTopLevelIncludedItemViaFallback());
  }
  
  protected boolean isTopLevelIncludedItemViaInclude() {
    return (this.fDepth == 1 && !isRootDocument());
  }
  
  protected boolean isTopLevelIncludedItemViaFallback() {
    return getSawFallback(this.fDepth - 1);
  }
  
  protected XMLAttributes processAttributes(XMLAttributes paramXMLAttributes) {
    XMLAttributesImpl xMLAttributesImpl;
    if (isTopLevelIncludedItem()) {
      if (this.fFixupBaseURIs && !sameBaseURIAsIncludeParent()) {
        if (paramXMLAttributes == null)
          xMLAttributesImpl = new XMLAttributesImpl(); 
        String str = null;
        try {
          str = getRelativeBaseURI();
        } catch (org.apache.xerces.util.URI.MalformedURIException malformedURIException) {
          str = this.fCurrentBaseURI.getExpandedSystemId();
        } 
        int i = xMLAttributesImpl.addAttribute(XML_BASE_QNAME, XMLSymbols.fCDATASymbol, str);
        xMLAttributesImpl.setSpecified(i, true);
      } 
      if (this.fFixupLanguage && !sameLanguageAsIncludeParent()) {
        if (xMLAttributesImpl == null)
          xMLAttributesImpl = new XMLAttributesImpl(); 
        int i = xMLAttributesImpl.addAttribute(XML_LANG_QNAME, XMLSymbols.fCDATASymbol, this.fCurrentLanguage);
        xMLAttributesImpl.setSpecified(i, true);
      } 
      Enumeration enumeration = this.fNamespaceContext.getAllPrefixes();
      while (enumeration.hasMoreElements()) {
        String str1 = enumeration.nextElement();
        String str2 = this.fNamespaceContext.getURIFromIncludeParent(str1);
        String str3 = this.fNamespaceContext.getURI(str1);
        if (str2 != str3 && xMLAttributesImpl != null) {
          if (str1 == XMLSymbols.EMPTY_STRING) {
            if (xMLAttributesImpl.getValue(NamespaceContext.XMLNS_URI, XMLSymbols.PREFIX_XMLNS) == null) {
              if (xMLAttributesImpl == null)
                xMLAttributesImpl = new XMLAttributesImpl(); 
              QName qName = (QName)NEW_NS_ATTR_QNAME.clone();
              qName.prefix = null;
              qName.localpart = XMLSymbols.PREFIX_XMLNS;
              qName.rawname = XMLSymbols.PREFIX_XMLNS;
              int i = xMLAttributesImpl.addAttribute(qName, XMLSymbols.fCDATASymbol, (str3 != null) ? str3 : XMLSymbols.EMPTY_STRING);
              xMLAttributesImpl.setSpecified(i, true);
              this.fNamespaceContext.declarePrefix(str1, str3);
            } 
            continue;
          } 
          if (xMLAttributesImpl.getValue(NamespaceContext.XMLNS_URI, str1) == null) {
            if (xMLAttributesImpl == null)
              xMLAttributesImpl = new XMLAttributesImpl(); 
            QName qName = (QName)NEW_NS_ATTR_QNAME.clone();
            qName.localpart = str1;
            qName.rawname += str1;
            qName.rawname = (this.fSymbolTable != null) ? this.fSymbolTable.addSymbol(qName.rawname) : qName.rawname.intern();
            int i = xMLAttributesImpl.addAttribute(qName, XMLSymbols.fCDATASymbol, (str3 != null) ? str3 : XMLSymbols.EMPTY_STRING);
            xMLAttributesImpl.setSpecified(i, true);
            this.fNamespaceContext.declarePrefix(str1, str3);
          } 
        } 
      } 
    } 
    if (xMLAttributesImpl != null) {
      int i = xMLAttributesImpl.getLength();
      for (byte b = 0; b < i; b++) {
        String str1 = xMLAttributesImpl.getType(b);
        String str2 = xMLAttributesImpl.getValue(b);
        if (str1 == XMLSymbols.fENTITYSymbol)
          checkUnparsedEntity(str2); 
        if (str1 == XMLSymbols.fENTITIESSymbol) {
          StringTokenizer stringTokenizer = new StringTokenizer(str2);
          while (stringTokenizer.hasMoreTokens()) {
            String str = stringTokenizer.nextToken();
            checkUnparsedEntity(str);
          } 
        } else if (str1 == XMLSymbols.fNOTATIONSymbol) {
          checkNotation(str2);
        } 
      } 
    } 
    return (XMLAttributes)xMLAttributesImpl;
  }
  
  protected String getRelativeBaseURI() throws URI.MalformedURIException {
    int i = getIncludeParentDepth();
    String str = getRelativeURI(i);
    if (isRootDocument())
      return str; 
    if (str.length() == 0)
      str = this.fCurrentBaseURI.getLiteralSystemId(); 
    if (i == 0) {
      if (this.fParentRelativeURI == null)
        this.fParentRelativeURI = this.fParentXIncludeHandler.getRelativeBaseURI(); 
      if (this.fParentRelativeURI.length() == 0)
        return str; 
      URI uRI1 = new URI(this.fParentRelativeURI, true);
      URI uRI2 = new URI(uRI1, str);
      String str1 = uRI1.getScheme();
      String str2 = uRI2.getScheme();
      if (!isEqual(str1, str2))
        return str; 
      String str3 = uRI1.getAuthority();
      String str4 = uRI2.getAuthority();
      if (!isEqual(str3, str4))
        return uRI2.getSchemeSpecificPart(); 
      String str5 = uRI2.getPath();
      String str6 = uRI2.getQueryString();
      String str7 = uRI2.getFragment();
      if (str6 != null || str7 != null) {
        StringBuffer stringBuffer = new StringBuffer();
        if (str5 != null)
          stringBuffer.append(str5); 
        if (str6 != null) {
          stringBuffer.append('?');
          stringBuffer.append(str6);
        } 
        if (str7 != null) {
          stringBuffer.append('#');
          stringBuffer.append(str7);
        } 
        return stringBuffer.toString();
      } 
      return str5;
    } 
    return str;
  }
  
  private String getIncludeParentBaseURI() {
    int i = getIncludeParentDepth();
    return (!isRootDocument() && i == 0) ? this.fParentXIncludeHandler.getIncludeParentBaseURI() : getBaseURI(i);
  }
  
  private String getIncludeParentLanguage() {
    int i = getIncludeParentDepth();
    return (!isRootDocument() && i == 0) ? this.fParentXIncludeHandler.getIncludeParentLanguage() : getLanguage(i);
  }
  
  private int getIncludeParentDepth() {
    for (int i = this.fDepth - 1; i >= 0; i--) {
      if (!getSawInclude(i) && !getSawFallback(i))
        return i; 
    } 
    return 0;
  }
  
  private int getResultDepth() {
    return this.fResultDepth;
  }
  
  protected Augmentations modifyAugmentations(Augmentations paramAugmentations) {
    return modifyAugmentations(paramAugmentations, false);
  }
  
  protected Augmentations modifyAugmentations(Augmentations paramAugmentations, boolean paramBoolean) {
    AugmentationsImpl augmentationsImpl;
    if (paramBoolean || isTopLevelIncludedItem()) {
      if (paramAugmentations == null)
        augmentationsImpl = new AugmentationsImpl(); 
      augmentationsImpl.putItem(XINCLUDE_INCLUDED, Boolean.TRUE);
    } 
    return (Augmentations)augmentationsImpl;
  }
  
  protected int getState(int paramInt) {
    return this.fState[paramInt];
  }
  
  protected int getState() {
    return this.fState[this.fDepth];
  }
  
  protected void setState(int paramInt) {
    if (this.fDepth >= this.fState.length) {
      int[] arrayOfInt = new int[this.fDepth * 2];
      System.arraycopy(this.fState, 0, arrayOfInt, 0, this.fState.length);
      this.fState = arrayOfInt;
    } 
    this.fState[this.fDepth] = paramInt;
  }
  
  protected void setSawFallback(int paramInt, boolean paramBoolean) {
    if (paramInt >= this.fSawFallback.length) {
      boolean[] arrayOfBoolean = new boolean[paramInt * 2];
      System.arraycopy(this.fSawFallback, 0, arrayOfBoolean, 0, this.fSawFallback.length);
      this.fSawFallback = arrayOfBoolean;
    } 
    this.fSawFallback[paramInt] = paramBoolean;
  }
  
  protected boolean getSawFallback(int paramInt) {
    return (paramInt >= this.fSawFallback.length) ? false : this.fSawFallback[paramInt];
  }
  
  protected void setSawInclude(int paramInt, boolean paramBoolean) {
    if (paramInt >= this.fSawInclude.length) {
      boolean[] arrayOfBoolean = new boolean[paramInt * 2];
      System.arraycopy(this.fSawInclude, 0, arrayOfBoolean, 0, this.fSawInclude.length);
      this.fSawInclude = arrayOfBoolean;
    } 
    this.fSawInclude[paramInt] = paramBoolean;
  }
  
  protected boolean getSawInclude(int paramInt) {
    return (paramInt >= this.fSawInclude.length) ? false : this.fSawInclude[paramInt];
  }
  
  protected void reportResourceError(String paramString) {
    reportResourceError(paramString, null);
  }
  
  protected void reportResourceError(String paramString, Object[] paramArrayOfObject) {
    reportResourceError(paramString, paramArrayOfObject, null);
  }
  
  protected void reportResourceError(String paramString, Object[] paramArrayOfObject, Exception paramException) {
    reportError(paramString, paramArrayOfObject, (short)0, paramException);
  }
  
  protected void reportFatalError(String paramString) {
    reportFatalError(paramString, null);
  }
  
  protected void reportFatalError(String paramString, Object[] paramArrayOfObject) {
    reportFatalError(paramString, paramArrayOfObject, null);
  }
  
  protected void reportFatalError(String paramString, Object[] paramArrayOfObject, Exception paramException) {
    reportError(paramString, paramArrayOfObject, (short)2, paramException);
  }
  
  private void reportError(String paramString, Object[] paramArrayOfObject, short paramShort, Exception paramException) {
    if (this.fErrorReporter != null)
      this.fErrorReporter.reportError("http://www.w3.org/TR/xinclude", paramString, paramArrayOfObject, paramShort, paramException); 
  }
  
  protected void setParent(XIncludeHandler paramXIncludeHandler) {
    this.fParentXIncludeHandler = paramXIncludeHandler;
  }
  
  protected void setHref(String paramString) {
    this.fHrefFromParent = paramString;
  }
  
  protected void setXIncludeLocator(XMLLocatorWrapper paramXMLLocatorWrapper) {
    this.fXIncludeLocator = paramXMLLocatorWrapper;
  }
  
  protected boolean isRootDocument() {
    return (this.fParentXIncludeHandler == null);
  }
  
  protected void addUnparsedEntity(String paramString1, XMLResourceIdentifier paramXMLResourceIdentifier, String paramString2, Augmentations paramAugmentations) {
    UnparsedEntity unparsedEntity = new UnparsedEntity();
    unparsedEntity.name = paramString1;
    unparsedEntity.systemId = paramXMLResourceIdentifier.getLiteralSystemId();
    unparsedEntity.publicId = paramXMLResourceIdentifier.getPublicId();
    unparsedEntity.baseURI = paramXMLResourceIdentifier.getBaseSystemId();
    unparsedEntity.expandedSystemId = paramXMLResourceIdentifier.getExpandedSystemId();
    unparsedEntity.notation = paramString2;
    unparsedEntity.augmentations = paramAugmentations;
    this.fUnparsedEntities.add(unparsedEntity);
  }
  
  protected void addNotation(String paramString, XMLResourceIdentifier paramXMLResourceIdentifier, Augmentations paramAugmentations) {
    Notation notation = new Notation();
    notation.name = paramString;
    notation.systemId = paramXMLResourceIdentifier.getLiteralSystemId();
    notation.publicId = paramXMLResourceIdentifier.getPublicId();
    notation.baseURI = paramXMLResourceIdentifier.getBaseSystemId();
    notation.expandedSystemId = paramXMLResourceIdentifier.getExpandedSystemId();
    notation.augmentations = paramAugmentations;
    this.fNotations.add(notation);
  }
  
  protected void checkUnparsedEntity(String paramString) {
    UnparsedEntity unparsedEntity = new UnparsedEntity();
    unparsedEntity.name = paramString;
    int i = this.fUnparsedEntities.indexOf(unparsedEntity);
    if (i != -1) {
      unparsedEntity = this.fUnparsedEntities.get(i);
      checkNotation(unparsedEntity.notation);
      checkAndSendUnparsedEntity(unparsedEntity);
    } 
  }
  
  protected void checkNotation(String paramString) {
    Notation notation = new Notation();
    notation.name = paramString;
    int i = this.fNotations.indexOf(notation);
    if (i != -1) {
      notation = this.fNotations.get(i);
      checkAndSendNotation(notation);
    } 
  }
  
  protected void checkAndSendUnparsedEntity(UnparsedEntity paramUnparsedEntity) {
    if (isRootDocument()) {
      int i = this.fUnparsedEntities.indexOf(paramUnparsedEntity);
      if (i == -1) {
        XMLResourceIdentifierImpl xMLResourceIdentifierImpl = new XMLResourceIdentifierImpl(paramUnparsedEntity.publicId, paramUnparsedEntity.systemId, paramUnparsedEntity.baseURI, paramUnparsedEntity.expandedSystemId);
        addUnparsedEntity(paramUnparsedEntity.name, (XMLResourceIdentifier)xMLResourceIdentifierImpl, paramUnparsedEntity.notation, paramUnparsedEntity.augmentations);
        if (this.fSendUEAndNotationEvents && this.fDTDHandler != null)
          this.fDTDHandler.unparsedEntityDecl(paramUnparsedEntity.name, (XMLResourceIdentifier)xMLResourceIdentifierImpl, paramUnparsedEntity.notation, paramUnparsedEntity.augmentations); 
      } else {
        UnparsedEntity unparsedEntity = this.fUnparsedEntities.get(i);
        if (!paramUnparsedEntity.isDuplicate(unparsedEntity))
          reportFatalError("NonDuplicateUnparsedEntity", new Object[] { paramUnparsedEntity.name }); 
      } 
    } else {
      this.fParentXIncludeHandler.checkAndSendUnparsedEntity(paramUnparsedEntity);
    } 
  }
  
  protected void checkAndSendNotation(Notation paramNotation) {
    if (isRootDocument()) {
      int i = this.fNotations.indexOf(paramNotation);
      if (i == -1) {
        XMLResourceIdentifierImpl xMLResourceIdentifierImpl = new XMLResourceIdentifierImpl(paramNotation.publicId, paramNotation.systemId, paramNotation.baseURI, paramNotation.expandedSystemId);
        addNotation(paramNotation.name, (XMLResourceIdentifier)xMLResourceIdentifierImpl, paramNotation.augmentations);
        if (this.fSendUEAndNotationEvents && this.fDTDHandler != null)
          this.fDTDHandler.notationDecl(paramNotation.name, (XMLResourceIdentifier)xMLResourceIdentifierImpl, paramNotation.augmentations); 
      } else {
        Notation notation = this.fNotations.get(i);
        if (!paramNotation.isDuplicate(notation))
          reportFatalError("NonDuplicateNotation", new Object[] { paramNotation.name }); 
      } 
    } else {
      this.fParentXIncludeHandler.checkAndSendNotation(paramNotation);
    } 
  }
  
  private void checkWhitespace(XMLString paramXMLString) {
    int i = paramXMLString.offset + paramXMLString.length;
    for (int j = paramXMLString.offset; j < i; j++) {
      if (!XMLChar.isSpace(paramXMLString.ch[j])) {
        reportFatalError("ContentIllegalAtTopLevel");
        return;
      } 
    } 
  }
  
  private void checkMultipleRootElements() {
    if (getRootElementProcessed())
      reportFatalError("MultipleRootElements"); 
    setRootElementProcessed(true);
  }
  
  private void setRootElementProcessed(boolean paramBoolean) {
    if (isRootDocument()) {
      this.fSeenRootElement = paramBoolean;
      return;
    } 
    this.fParentXIncludeHandler.setRootElementProcessed(paramBoolean);
  }
  
  private boolean getRootElementProcessed() {
    return isRootDocument() ? this.fSeenRootElement : this.fParentXIncludeHandler.getRootElementProcessed();
  }
  
  protected void copyFeatures(XMLComponentManager paramXMLComponentManager, ParserConfigurationSettings paramParserConfigurationSettings) {
    Enumeration enumeration = Constants.getXercesFeatures();
    copyFeatures1(enumeration, "http://apache.org/xml/features/", paramXMLComponentManager, paramParserConfigurationSettings);
    enumeration = Constants.getSAXFeatures();
    copyFeatures1(enumeration, "http://xml.org/sax/features/", paramXMLComponentManager, paramParserConfigurationSettings);
  }
  
  protected void copyFeatures(XMLComponentManager paramXMLComponentManager, XMLParserConfiguration paramXMLParserConfiguration) {
    Enumeration enumeration = Constants.getXercesFeatures();
    copyFeatures1(enumeration, "http://apache.org/xml/features/", paramXMLComponentManager, paramXMLParserConfiguration);
    enumeration = Constants.getSAXFeatures();
    copyFeatures1(enumeration, "http://xml.org/sax/features/", paramXMLComponentManager, paramXMLParserConfiguration);
  }
  
  private void copyFeatures1(Enumeration paramEnumeration, String paramString, XMLComponentManager paramXMLComponentManager, ParserConfigurationSettings paramParserConfigurationSettings) {
    while (paramEnumeration.hasMoreElements()) {
      String str = paramString + (String)paramEnumeration.nextElement();
      paramParserConfigurationSettings.addRecognizedFeatures(new String[] { str });
      try {
        paramParserConfigurationSettings.setFeature(str, paramXMLComponentManager.getFeature(str));
      } catch (XMLConfigurationException xMLConfigurationException) {}
    } 
  }
  
  private void copyFeatures1(Enumeration paramEnumeration, String paramString, XMLComponentManager paramXMLComponentManager, XMLParserConfiguration paramXMLParserConfiguration) {
    while (paramEnumeration.hasMoreElements()) {
      String str = paramString + (String)paramEnumeration.nextElement();
      boolean bool = paramXMLComponentManager.getFeature(str);
      try {
        paramXMLParserConfiguration.setFeature(str, bool);
      } catch (XMLConfigurationException xMLConfigurationException) {}
    } 
  }
  
  protected void saveBaseURI() {
    this.fBaseURIScope.push(this.fDepth);
    this.fBaseURI.push(this.fCurrentBaseURI.getBaseSystemId());
    this.fLiteralSystemID.push(this.fCurrentBaseURI.getLiteralSystemId());
    this.fExpandedSystemID.push(this.fCurrentBaseURI.getExpandedSystemId());
  }
  
  protected void restoreBaseURI() {
    this.fBaseURI.pop();
    this.fLiteralSystemID.pop();
    this.fExpandedSystemID.pop();
    this.fBaseURIScope.pop();
    this.fCurrentBaseURI.setBaseSystemId(this.fBaseURI.peek());
    this.fCurrentBaseURI.setLiteralSystemId(this.fLiteralSystemID.peek());
    this.fCurrentBaseURI.setExpandedSystemId(this.fExpandedSystemID.peek());
  }
  
  protected void saveLanguage(String paramString) {
    this.fLanguageScope.push(this.fDepth);
    this.fLanguageStack.push(paramString);
  }
  
  public String restoreLanguage() {
    this.fLanguageStack.pop();
    this.fLanguageScope.pop();
    return this.fLanguageStack.peek();
  }
  
  public String getBaseURI(int paramInt) {
    int i = scopeOfBaseURI(paramInt);
    return this.fExpandedSystemID.elementAt(i);
  }
  
  public String getLanguage(int paramInt) {
    int i = scopeOfLanguage(paramInt);
    return this.fLanguageStack.elementAt(i);
  }
  
  public String getRelativeURI(int paramInt) throws URI.MalformedURIException {
    int i = scopeOfBaseURI(paramInt) + 1;
    if (i == this.fBaseURIScope.size())
      return ""; 
    URI uRI = new URI("file", this.fLiteralSystemID.elementAt(i));
    for (int j = i + 1; j < this.fBaseURIScope.size(); j++)
      uRI = new URI(uRI, this.fLiteralSystemID.elementAt(j)); 
    return uRI.getPath();
  }
  
  private int scopeOfBaseURI(int paramInt) {
    for (int i = this.fBaseURIScope.size() - 1; i >= 0; i--) {
      if (this.fBaseURIScope.elementAt(i) <= paramInt)
        return i; 
    } 
    return -1;
  }
  
  private int scopeOfLanguage(int paramInt) {
    for (int i = this.fLanguageScope.size() - 1; i >= 0; i--) {
      if (this.fLanguageScope.elementAt(i) <= paramInt)
        return i; 
    } 
    return -1;
  }
  
  protected void processXMLBaseAttributes(XMLAttributes paramXMLAttributes) {
    String str = paramXMLAttributes.getValue(NamespaceContext.XML_URI, "base");
    if (str != null)
      try {
        String str1 = XMLEntityManager.expandSystemId(str, this.fCurrentBaseURI.getExpandedSystemId(), false);
        this.fCurrentBaseURI.setLiteralSystemId(str);
        this.fCurrentBaseURI.setBaseSystemId(this.fCurrentBaseURI.getExpandedSystemId());
        this.fCurrentBaseURI.setExpandedSystemId(str1);
        saveBaseURI();
      } catch (org.apache.xerces.util.URI.MalformedURIException malformedURIException) {} 
  }
  
  protected void processXMLLangAttributes(XMLAttributes paramXMLAttributes) {
    String str = paramXMLAttributes.getValue(NamespaceContext.XML_URI, "lang");
    if (str != null) {
      this.fCurrentLanguage = str;
      saveLanguage(this.fCurrentLanguage);
    } 
  }
  
  private boolean isValidInHTTPHeader(String paramString) {
    for (int i = paramString.length() - 1; i >= 0; i--) {
      char c = paramString.charAt(i);
      if (c < ' ' || c > '~')
        return false; 
    } 
    return true;
  }
  
  private XMLInputSource createInputSource(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5) {
    HTTPInputSource hTTPInputSource = new HTTPInputSource(paramString1, paramString2, paramString3);
    if (paramString4 != null && paramString4.length() > 0)
      hTTPInputSource.setHTTPRequestProperty("Accept", paramString4); 
    if (paramString5 != null && paramString5.length() > 0)
      hTTPInputSource.setHTTPRequestProperty("Accept-Language", paramString5); 
    return (XMLInputSource)hTTPInputSource;
  }
  
  private boolean isEqual(String paramString1, String paramString2) {
    return (paramString1 == paramString2 || (paramString1 != null && paramString1.equals(paramString2)));
  }
  
  private String escapeHref(String paramString) {
    int i = paramString.length();
    StringBuffer stringBuffer = new StringBuffer(i * 3);
    byte b;
    for (b = 0; b < i; b++) {
      char c = paramString.charAt(b);
      if (c > '~')
        break; 
      if (c < ' ')
        return paramString; 
      if (gNeedEscaping[c]) {
        stringBuffer.append('%');
        stringBuffer.append(gAfterEscaping1[c]);
        stringBuffer.append(gAfterEscaping2[c]);
      } else {
        stringBuffer.append((char)c);
      } 
    } 
    if (b < i) {
      for (byte b1 = b; b1 < i; b1++) {
        char c = paramString.charAt(b1);
        if ((c >= ' ' && c <= '~') || (c >= ' ' && c <= '퟿') || (c >= '豈' && c <= '﷏') || (c >= 'ﷰ' && c <= '￯'))
          continue; 
        if (XMLChar.isHighSurrogate(c) && ++b1 < i) {
          char c1 = paramString.charAt(b1);
          if (XMLChar.isLowSurrogate(c1)) {
            int j = XMLChar.supplemental((char)c, (char)c1);
            if (j < 983040 && (j & 0xFFFF) <= 65533)
              continue; 
          } 
        } 
        return paramString;
      } 
      byte[] arrayOfByte = null;
      try {
        arrayOfByte = paramString.substring(b).getBytes("UTF-8");
      } catch (UnsupportedEncodingException unsupportedEncodingException) {
        return paramString;
      } 
      i = arrayOfByte.length;
      for (b = 0; b < i; b++) {
        byte b2 = arrayOfByte[b];
        if (b2 < 0) {
          int j = b2 + 256;
          stringBuffer.append('%');
          stringBuffer.append(gHexChs[j >> 4]);
          stringBuffer.append(gHexChs[j & 0xF]);
        } else if (gNeedEscaping[b2]) {
          stringBuffer.append('%');
          stringBuffer.append(gAfterEscaping1[b2]);
          stringBuffer.append(gAfterEscaping2[b2]);
        } else {
          stringBuffer.append((char)b2);
        } 
      } 
    } 
    return (stringBuffer.length() != i) ? stringBuffer.toString() : paramString;
  }
  
  static {
    for (char c : new char[] { ' ', '<', '>', '"', '{', '}', '|', '\\', '^', '`' }) {
      gNeedEscaping[c] = true;
      gAfterEscaping1[c] = gHexChs[c >> 4];
      gAfterEscaping2[c] = gHexChs[c & 0xF];
    } 
  }
  
  protected static class UnparsedEntity {
    public String name;
    
    public String systemId;
    
    public String baseURI;
    
    public String publicId;
    
    public String expandedSystemId;
    
    public String notation;
    
    public Augmentations augmentations;
    
    public boolean equals(Object param1Object) {
      if (param1Object == null)
        return false; 
      if (param1Object instanceof UnparsedEntity) {
        UnparsedEntity unparsedEntity = (UnparsedEntity)param1Object;
        return this.name.equals(unparsedEntity.name);
      } 
      return false;
    }
    
    public boolean isDuplicate(Object param1Object) {
      if (param1Object != null && param1Object instanceof UnparsedEntity) {
        UnparsedEntity unparsedEntity = (UnparsedEntity)param1Object;
        return (this.name.equals(unparsedEntity.name) && isEqual(this.publicId, unparsedEntity.publicId) && isEqual(this.expandedSystemId, unparsedEntity.expandedSystemId) && isEqual(this.notation, unparsedEntity.notation));
      } 
      return false;
    }
    
    private boolean isEqual(String param1String1, String param1String2) {
      return (param1String1 == param1String2 || (param1String1 != null && param1String1.equals(param1String2)));
    }
  }
  
  protected static class Notation {
    public String name;
    
    public String systemId;
    
    public String baseURI;
    
    public String publicId;
    
    public String expandedSystemId;
    
    public Augmentations augmentations;
    
    public boolean equals(Object param1Object) {
      if (param1Object == null)
        return false; 
      if (param1Object instanceof Notation) {
        Notation notation = (Notation)param1Object;
        return this.name.equals(notation.name);
      } 
      return false;
    }
    
    public boolean isDuplicate(Object param1Object) {
      if (param1Object != null && param1Object instanceof Notation) {
        Notation notation = (Notation)param1Object;
        return (this.name.equals(notation.name) && isEqual(this.publicId, notation.publicId) && isEqual(this.expandedSystemId, notation.expandedSystemId));
      } 
      return false;
    }
    
    private boolean isEqual(String param1String1, String param1String2) {
      return (param1String1 == param1String2 || (param1String1 != null && param1String1.equals(param1String2)));
    }
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/xinclude/XIncludeHandler.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */