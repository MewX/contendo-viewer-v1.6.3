package org.apache.xerces.impl.xs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;
import javax.xml.namespace.QName;
import org.apache.xerces.impl.RevalidationHandler;
import org.apache.xerces.impl.XMLEntityManager;
import org.apache.xerces.impl.XMLErrorReporter;
import org.apache.xerces.impl.dv.DatatypeException;
import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidatedInfo;
import org.apache.xerces.impl.dv.ValidationContext;
import org.apache.xerces.impl.dv.XSSimpleType;
import org.apache.xerces.impl.dv.xs.XSSimpleTypeDecl;
import org.apache.xerces.impl.validation.ConfigurableValidationState;
import org.apache.xerces.impl.validation.ValidationManager;
import org.apache.xerces.impl.validation.ValidationState;
import org.apache.xerces.impl.xs.identity.Field;
import org.apache.xerces.impl.xs.identity.FieldActivator;
import org.apache.xerces.impl.xs.identity.IdentityConstraint;
import org.apache.xerces.impl.xs.identity.KeyRef;
import org.apache.xerces.impl.xs.identity.Selector;
import org.apache.xerces.impl.xs.identity.UniqueOrKey;
import org.apache.xerces.impl.xs.identity.ValueStore;
import org.apache.xerces.impl.xs.identity.XPathMatcher;
import org.apache.xerces.impl.xs.models.CMBuilder;
import org.apache.xerces.impl.xs.models.CMNodeFactory;
import org.apache.xerces.impl.xs.models.XSCMValidator;
import org.apache.xerces.util.AugmentationsImpl;
import org.apache.xerces.util.IntStack;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.util.URI;
import org.apache.xerces.util.XMLAttributesImpl;
import org.apache.xerces.util.XMLChar;
import org.apache.xerces.util.XMLSymbols;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.xni.XMLDocumentHandler;
import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.xni.XMLResourceIdentifier;
import org.apache.xerces.xni.XMLString;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.grammars.Grammar;
import org.apache.xerces.xni.grammars.XMLGrammarDescription;
import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.apache.xerces.xni.parser.XMLComponent;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.parser.XMLDocumentFilter;
import org.apache.xerces.xni.parser.XMLDocumentSource;
import org.apache.xerces.xni.parser.XMLEntityResolver;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xerces.xs.ShortList;
import org.apache.xerces.xs.StringList;
import org.apache.xerces.xs.XSObjectList;
import org.apache.xerces.xs.XSTypeDefinition;
import org.apache.xerces.xs.XSValue;

public class XMLSchemaValidator implements RevalidationHandler, XSElementDeclHelper, FieldActivator, XMLComponent, XMLDocumentFilter {
  private static final boolean DEBUG = false;
  
  protected static final String VALIDATION = "http://xml.org/sax/features/validation";
  
  protected static final String SCHEMA_VALIDATION = "http://apache.org/xml/features/validation/schema";
  
  protected static final String SCHEMA_FULL_CHECKING = "http://apache.org/xml/features/validation/schema-full-checking";
  
  protected static final String DYNAMIC_VALIDATION = "http://apache.org/xml/features/validation/dynamic";
  
  protected static final String NORMALIZE_DATA = "http://apache.org/xml/features/validation/schema/normalized-value";
  
  protected static final String SCHEMA_ELEMENT_DEFAULT = "http://apache.org/xml/features/validation/schema/element-default";
  
  protected static final String SCHEMA_AUGMENT_PSVI = "http://apache.org/xml/features/validation/schema/augment-psvi";
  
  protected static final String ALLOW_JAVA_ENCODINGS = "http://apache.org/xml/features/allow-java-encodings";
  
  protected static final String STANDARD_URI_CONFORMANT_FEATURE = "http://apache.org/xml/features/standard-uri-conformant";
  
  protected static final String GENERATE_SYNTHETIC_ANNOTATIONS = "http://apache.org/xml/features/generate-synthetic-annotations";
  
  protected static final String VALIDATE_ANNOTATIONS = "http://apache.org/xml/features/validate-annotations";
  
  protected static final String HONOUR_ALL_SCHEMALOCATIONS = "http://apache.org/xml/features/honour-all-schemaLocations";
  
  protected static final String USE_GRAMMAR_POOL_ONLY = "http://apache.org/xml/features/internal/validation/schema/use-grammar-pool-only";
  
  protected static final String CONTINUE_AFTER_FATAL_ERROR = "http://apache.org/xml/features/continue-after-fatal-error";
  
  protected static final String PARSER_SETTINGS = "http://apache.org/xml/features/internal/parser-settings";
  
  protected static final String NAMESPACE_GROWTH = "http://apache.org/xml/features/namespace-growth";
  
  protected static final String TOLERATE_DUPLICATES = "http://apache.org/xml/features/internal/tolerate-duplicates";
  
  protected static final String IGNORE_XSI_TYPE = "http://apache.org/xml/features/validation/schema/ignore-xsi-type-until-elemdecl";
  
  protected static final String ID_IDREF_CHECKING = "http://apache.org/xml/features/validation/id-idref-checking";
  
  protected static final String UNPARSED_ENTITY_CHECKING = "http://apache.org/xml/features/validation/unparsed-entity-checking";
  
  protected static final String IDENTITY_CONSTRAINT_CHECKING = "http://apache.org/xml/features/validation/identity-constraint-checking";
  
  public static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
  
  public static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
  
  public static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
  
  public static final String XMLGRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
  
  protected static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
  
  protected static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
  
  protected static final String SCHEMA_LOCATION = "http://apache.org/xml/properties/schema/external-schemaLocation";
  
  protected static final String SCHEMA_NONS_LOCATION = "http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation";
  
  protected static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
  
  protected static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
  
  protected static final String ROOT_TYPE_DEF = "http://apache.org/xml/properties/validation/schema/root-type-definition";
  
  protected static final String ROOT_ELEMENT_DECL = "http://apache.org/xml/properties/validation/schema/root-element-declaration";
  
  protected static final String SCHEMA_DV_FACTORY = "http://apache.org/xml/properties/internal/validation/schema/dv-factory";
  
  private static final String[] RECOGNIZED_FEATURES = new String[] { 
      "http://xml.org/sax/features/validation", "http://apache.org/xml/features/validation/schema", "http://apache.org/xml/features/validation/dynamic", "http://apache.org/xml/features/validation/schema-full-checking", "http://apache.org/xml/features/allow-java-encodings", "http://apache.org/xml/features/continue-after-fatal-error", "http://apache.org/xml/features/standard-uri-conformant", "http://apache.org/xml/features/generate-synthetic-annotations", "http://apache.org/xml/features/validate-annotations", "http://apache.org/xml/features/honour-all-schemaLocations", 
      "http://apache.org/xml/features/internal/validation/schema/use-grammar-pool-only", "http://apache.org/xml/features/validation/schema/ignore-xsi-type-until-elemdecl", "http://apache.org/xml/features/validation/id-idref-checking", "http://apache.org/xml/features/validation/identity-constraint-checking", "http://apache.org/xml/features/validation/unparsed-entity-checking", "http://apache.org/xml/features/namespace-growth", "http://apache.org/xml/features/internal/tolerate-duplicates" };
  
  private static final Boolean[] FEATURE_DEFAULTS = new Boolean[] { 
      null, null, null, null, null, null, null, null, null, null, 
      null, null, null, null, null, null, null };
  
  private static final String[] RECOGNIZED_PROPERTIES = new String[] { 
      "http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-reporter", "http://apache.org/xml/properties/internal/entity-resolver", "http://apache.org/xml/properties/internal/validation-manager", "http://apache.org/xml/properties/schema/external-schemaLocation", "http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation", "http://java.sun.com/xml/jaxp/properties/schemaSource", "http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://apache.org/xml/properties/validation/schema/root-type-definition", "http://apache.org/xml/properties/validation/schema/root-element-declaration", 
      "http://apache.org/xml/properties/internal/validation/schema/dv-factory" };
  
  private static final Object[] PROPERTY_DEFAULTS = new Object[] { 
      null, null, null, null, null, null, null, null, null, null, 
      null };
  
  protected static final int ID_CONSTRAINT_NUM = 1;
  
  static final XSAttributeDecl XSI_TYPE = SchemaGrammar.SG_XSI.getGlobalAttributeDecl(SchemaSymbols.XSI_TYPE);
  
  static final XSAttributeDecl XSI_NIL = SchemaGrammar.SG_XSI.getGlobalAttributeDecl(SchemaSymbols.XSI_NIL);
  
  static final XSAttributeDecl XSI_SCHEMALOCATION = SchemaGrammar.SG_XSI.getGlobalAttributeDecl(SchemaSymbols.XSI_SCHEMALOCATION);
  
  static final XSAttributeDecl XSI_NONAMESPACESCHEMALOCATION = SchemaGrammar.SG_XSI.getGlobalAttributeDecl(SchemaSymbols.XSI_NONAMESPACESCHEMALOCATION);
  
  private static final Hashtable EMPTY_TABLE = new Hashtable();
  
  protected ElementPSVImpl fCurrentPSVI = new ElementPSVImpl();
  
  protected final AugmentationsImpl fAugmentations = new AugmentationsImpl();
  
  protected XMLString fDefaultValue;
  
  protected boolean fDynamicValidation = false;
  
  protected boolean fSchemaDynamicValidation = false;
  
  protected boolean fDoValidation = false;
  
  protected boolean fFullChecking = false;
  
  protected boolean fNormalizeData = true;
  
  protected boolean fSchemaElementDefault = true;
  
  protected boolean fAugPSVI = true;
  
  protected boolean fIdConstraint = false;
  
  protected boolean fUseGrammarPoolOnly = false;
  
  protected boolean fNamespaceGrowth = false;
  
  private String fSchemaType = null;
  
  protected boolean fEntityRef = false;
  
  protected boolean fInCDATA = false;
  
  protected SymbolTable fSymbolTable;
  
  private XMLLocator fLocator;
  
  protected final XSIErrorReporter fXSIErrorReporter = new XSIErrorReporter(this);
  
  protected XMLEntityResolver fEntityResolver;
  
  protected ValidationManager fValidationManager = null;
  
  protected ConfigurableValidationState fValidationState = new ConfigurableValidationState();
  
  protected XMLGrammarPool fGrammarPool;
  
  protected String fExternalSchemas = null;
  
  protected String fExternalNoNamespaceSchema = null;
  
  protected Object fJaxpSchemaSource = null;
  
  protected final XSDDescription fXSDDescription = new XSDDescription();
  
  protected final Hashtable fLocationPairs = new Hashtable();
  
  protected final Hashtable fExpandedLocationPairs = new Hashtable();
  
  protected final ArrayList fUnparsedLocations = new ArrayList();
  
  protected XMLDocumentHandler fDocumentHandler;
  
  protected XMLDocumentSource fDocumentSource;
  
  static final int INITIAL_STACK_SIZE = 8;
  
  static final int INC_STACK_SIZE = 8;
  
  private static final boolean DEBUG_NORMALIZATION = false;
  
  private final XMLString fEmptyXMLStr = new XMLString(null, 0, -1);
  
  private static final int BUFFER_SIZE = 20;
  
  private final XMLString fNormalizedStr = new XMLString();
  
  private boolean fFirstChunk = true;
  
  private boolean fTrailing = false;
  
  private short fWhiteSpace = -1;
  
  private boolean fUnionType = false;
  
  private final XSGrammarBucket fGrammarBucket = new XSGrammarBucket();
  
  private final SubstitutionGroupHandler fSubGroupHandler = new SubstitutionGroupHandler(this);
  
  private final XSSimpleType fQNameDV = (XSSimpleType)SchemaGrammar.SG_SchemaNS.getGlobalTypeDecl("QName");
  
  private final CMNodeFactory nodeFactory = new CMNodeFactory();
  
  private final CMBuilder fCMBuilder = new CMBuilder(this.nodeFactory);
  
  private final XMLSchemaLoader fSchemaLoader = new XMLSchemaLoader(this.fXSIErrorReporter.fErrorReporter, this.fGrammarBucket, this.fSubGroupHandler, this.fCMBuilder);
  
  private String fValidationRoot;
  
  private int fSkipValidationDepth;
  
  private int fNFullValidationDepth;
  
  private int fNNoneValidationDepth;
  
  private int fElementDepth;
  
  private boolean fSubElement;
  
  private boolean[] fSubElementStack = new boolean[8];
  
  private XSElementDecl fCurrentElemDecl;
  
  private XSElementDecl[] fElemDeclStack = new XSElementDecl[8];
  
  private boolean fNil;
  
  private boolean[] fNilStack = new boolean[8];
  
  private XSNotationDecl fNotation;
  
  private XSNotationDecl[] fNotationStack = new XSNotationDecl[8];
  
  private XSTypeDefinition fCurrentType;
  
  private XSTypeDefinition[] fTypeStack = new XSTypeDefinition[8];
  
  private XSCMValidator fCurrentCM;
  
  private XSCMValidator[] fCMStack = new XSCMValidator[8];
  
  private int[] fCurrCMState;
  
  private int[][] fCMStateStack = new int[8][];
  
  private boolean fStrictAssess = true;
  
  private boolean[] fStrictAssessStack = new boolean[8];
  
  private final StringBuffer fBuffer = new StringBuffer();
  
  private boolean fAppendBuffer = true;
  
  private boolean fSawText = false;
  
  private boolean[] fSawTextStack = new boolean[8];
  
  private boolean fSawCharacters = false;
  
  private boolean[] fStringContent = new boolean[8];
  
  private final QName fTempQName = new QName();
  
  private QName fRootTypeQName = null;
  
  private XSTypeDefinition fRootTypeDefinition = null;
  
  private QName fRootElementDeclQName = null;
  
  private XSElementDecl fRootElementDeclaration = null;
  
  private int fIgnoreXSITypeDepth;
  
  private boolean fIDCChecking;
  
  private ValidatedInfo fValidatedInfo = new ValidatedInfo();
  
  private ValidationState fState4XsiType = new ValidationState();
  
  private ValidationState fState4ApplyDefault = new ValidationState();
  
  protected XPathMatcherStack fMatcherStack = new XPathMatcherStack();
  
  protected ValueStoreCache fValueStoreCache = new ValueStoreCache(this);
  
  public String[] getRecognizedFeatures() {
    return (String[])RECOGNIZED_FEATURES.clone();
  }
  
  public void setFeature(String paramString, boolean paramBoolean) throws XMLConfigurationException {}
  
  public String[] getRecognizedProperties() {
    return (String[])RECOGNIZED_PROPERTIES.clone();
  }
  
  public void setProperty(String paramString, Object paramObject) throws XMLConfigurationException {
    if (paramString.equals("http://apache.org/xml/properties/validation/schema/root-type-definition")) {
      if (paramObject == null) {
        this.fRootTypeQName = null;
        this.fRootTypeDefinition = null;
      } else if (paramObject instanceof QName) {
        this.fRootTypeQName = (QName)paramObject;
        this.fRootTypeDefinition = null;
      } else {
        this.fRootTypeDefinition = (XSTypeDefinition)paramObject;
        this.fRootTypeQName = null;
      } 
    } else if (paramString.equals("http://apache.org/xml/properties/validation/schema/root-element-declaration")) {
      if (paramObject == null) {
        this.fRootElementDeclQName = null;
        this.fRootElementDeclaration = null;
      } else if (paramObject instanceof QName) {
        this.fRootElementDeclQName = (QName)paramObject;
        this.fRootElementDeclaration = null;
      } else {
        this.fRootElementDeclaration = (XSElementDecl)paramObject;
        this.fRootElementDeclQName = null;
      } 
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
    this.fDocumentHandler = paramXMLDocumentHandler;
  }
  
  public XMLDocumentHandler getDocumentHandler() {
    return this.fDocumentHandler;
  }
  
  public void setDocumentSource(XMLDocumentSource paramXMLDocumentSource) {
    this.fDocumentSource = paramXMLDocumentSource;
  }
  
  public XMLDocumentSource getDocumentSource() {
    return this.fDocumentSource;
  }
  
  public void startDocument(XMLLocator paramXMLLocator, String paramString, NamespaceContext paramNamespaceContext, Augmentations paramAugmentations) throws XNIException {
    this.fValidationState.setNamespaceSupport(paramNamespaceContext);
    this.fState4XsiType.setNamespaceSupport(paramNamespaceContext);
    this.fState4ApplyDefault.setNamespaceSupport(paramNamespaceContext);
    this.fLocator = paramXMLLocator;
    handleStartDocument(paramXMLLocator, paramString);
    if (this.fDocumentHandler != null)
      this.fDocumentHandler.startDocument(paramXMLLocator, paramString, paramNamespaceContext, paramAugmentations); 
  }
  
  public void xmlDecl(String paramString1, String paramString2, String paramString3, Augmentations paramAugmentations) throws XNIException {
    if (this.fDocumentHandler != null)
      this.fDocumentHandler.xmlDecl(paramString1, paramString2, paramString3, paramAugmentations); 
  }
  
  public void doctypeDecl(String paramString1, String paramString2, String paramString3, Augmentations paramAugmentations) throws XNIException {
    if (this.fDocumentHandler != null)
      this.fDocumentHandler.doctypeDecl(paramString1, paramString2, paramString3, paramAugmentations); 
  }
  
  public void startElement(QName paramQName, XMLAttributes paramXMLAttributes, Augmentations paramAugmentations) throws XNIException {
    Augmentations augmentations = handleStartElement(paramQName, paramXMLAttributes, paramAugmentations);
    if (this.fDocumentHandler != null)
      this.fDocumentHandler.startElement(paramQName, paramXMLAttributes, augmentations); 
  }
  
  public void emptyElement(QName paramQName, XMLAttributes paramXMLAttributes, Augmentations paramAugmentations) throws XNIException {
    Augmentations augmentations = handleStartElement(paramQName, paramXMLAttributes, paramAugmentations);
    this.fDefaultValue = null;
    if (this.fElementDepth != -2)
      augmentations = handleEndElement(paramQName, augmentations); 
    if (this.fDocumentHandler != null)
      if (!this.fSchemaElementDefault || this.fDefaultValue == null) {
        this.fDocumentHandler.emptyElement(paramQName, paramXMLAttributes, augmentations);
      } else {
        this.fDocumentHandler.startElement(paramQName, paramXMLAttributes, augmentations);
        this.fDocumentHandler.characters(this.fDefaultValue, null);
        this.fDocumentHandler.endElement(paramQName, augmentations);
      }  
  }
  
  public void characters(XMLString paramXMLString, Augmentations paramAugmentations) throws XNIException {
    paramXMLString = handleCharacters(paramXMLString);
    if (this.fDocumentHandler != null)
      if (this.fNormalizeData && this.fUnionType) {
        if (paramAugmentations != null)
          this.fDocumentHandler.characters(this.fEmptyXMLStr, paramAugmentations); 
      } else {
        this.fDocumentHandler.characters(paramXMLString, paramAugmentations);
      }  
  }
  
  public void ignorableWhitespace(XMLString paramXMLString, Augmentations paramAugmentations) throws XNIException {
    handleIgnorableWhitespace(paramXMLString);
    if (this.fDocumentHandler != null)
      this.fDocumentHandler.ignorableWhitespace(paramXMLString, paramAugmentations); 
  }
  
  public void endElement(QName paramQName, Augmentations paramAugmentations) throws XNIException {
    this.fDefaultValue = null;
    Augmentations augmentations = handleEndElement(paramQName, paramAugmentations);
    if (this.fDocumentHandler != null)
      if (!this.fSchemaElementDefault || this.fDefaultValue == null) {
        this.fDocumentHandler.endElement(paramQName, augmentations);
      } else {
        this.fDocumentHandler.characters(this.fDefaultValue, null);
        this.fDocumentHandler.endElement(paramQName, augmentations);
      }  
  }
  
  public void startCDATA(Augmentations paramAugmentations) throws XNIException {
    this.fInCDATA = true;
    if (this.fDocumentHandler != null)
      this.fDocumentHandler.startCDATA(paramAugmentations); 
  }
  
  public void endCDATA(Augmentations paramAugmentations) throws XNIException {
    this.fInCDATA = false;
    if (this.fDocumentHandler != null)
      this.fDocumentHandler.endCDATA(paramAugmentations); 
  }
  
  public void endDocument(Augmentations paramAugmentations) throws XNIException {
    handleEndDocument();
    if (this.fDocumentHandler != null)
      this.fDocumentHandler.endDocument(paramAugmentations); 
    this.fLocator = null;
  }
  
  public boolean characterData(String paramString, Augmentations paramAugmentations) {
    this.fSawText = (this.fSawText || paramString.length() > 0);
    if (this.fNormalizeData && this.fWhiteSpace != -1 && this.fWhiteSpace != 0) {
      normalizeWhitespace(paramString, (this.fWhiteSpace == 2));
      this.fBuffer.append(this.fNormalizedStr.ch, this.fNormalizedStr.offset, this.fNormalizedStr.length);
    } else if (this.fAppendBuffer) {
      this.fBuffer.append(paramString);
    } 
    boolean bool = true;
    if (this.fCurrentType != null && this.fCurrentType.getTypeCategory() == 15) {
      XSComplexTypeDecl xSComplexTypeDecl = (XSComplexTypeDecl)this.fCurrentType;
      if (xSComplexTypeDecl.fContentType == 2)
        for (byte b = 0; b < paramString.length(); b++) {
          if (!XMLChar.isSpace(paramString.charAt(b))) {
            bool = false;
            this.fSawCharacters = true;
            break;
          } 
        }  
    } 
    return bool;
  }
  
  public void elementDefault(String paramString) {}
  
  public void startGeneralEntity(String paramString1, XMLResourceIdentifier paramXMLResourceIdentifier, String paramString2, Augmentations paramAugmentations) throws XNIException {
    this.fEntityRef = true;
    if (this.fDocumentHandler != null)
      this.fDocumentHandler.startGeneralEntity(paramString1, paramXMLResourceIdentifier, paramString2, paramAugmentations); 
  }
  
  public void textDecl(String paramString1, String paramString2, Augmentations paramAugmentations) throws XNIException {
    if (this.fDocumentHandler != null)
      this.fDocumentHandler.textDecl(paramString1, paramString2, paramAugmentations); 
  }
  
  public void comment(XMLString paramXMLString, Augmentations paramAugmentations) throws XNIException {
    if (this.fDocumentHandler != null)
      this.fDocumentHandler.comment(paramXMLString, paramAugmentations); 
  }
  
  public void processingInstruction(String paramString, XMLString paramXMLString, Augmentations paramAugmentations) throws XNIException {
    if (this.fDocumentHandler != null)
      this.fDocumentHandler.processingInstruction(paramString, paramXMLString, paramAugmentations); 
  }
  
  public void endGeneralEntity(String paramString, Augmentations paramAugmentations) throws XNIException {
    this.fEntityRef = false;
    if (this.fDocumentHandler != null)
      this.fDocumentHandler.endGeneralEntity(paramString, paramAugmentations); 
  }
  
  public XMLSchemaValidator() {
    this.fState4XsiType.setExtraChecking(false);
    this.fState4ApplyDefault.setFacetChecking(false);
  }
  
  public void reset(XMLComponentManager paramXMLComponentManager) throws XMLConfigurationException {
    boolean bool1;
    boolean bool2;
    this.fIdConstraint = false;
    this.fLocationPairs.clear();
    this.fExpandedLocationPairs.clear();
    this.fValidationState.resetIDTables();
    this.fSchemaLoader.reset(paramXMLComponentManager);
    this.fCurrentElemDecl = null;
    this.fCurrentCM = null;
    this.fCurrCMState = null;
    this.fSkipValidationDepth = -1;
    this.fNFullValidationDepth = -1;
    this.fNNoneValidationDepth = -1;
    this.fElementDepth = -1;
    this.fSubElement = false;
    this.fSchemaDynamicValidation = false;
    this.fEntityRef = false;
    this.fInCDATA = false;
    this.fMatcherStack.clear();
    this.fXSIErrorReporter.reset((XMLErrorReporter)paramXMLComponentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter"));
    try {
      bool1 = paramXMLComponentManager.getFeature("http://apache.org/xml/features/internal/parser-settings");
    } catch (XMLConfigurationException xMLConfigurationException) {
      bool1 = true;
    } 
    if (!bool1) {
      this.fValidationManager.addValidationState((ValidationState)this.fValidationState);
      this.nodeFactory.reset();
      XMLSchemaLoader.processExternalHints(this.fExternalSchemas, this.fExternalNoNamespaceSchema, this.fLocationPairs, this.fXSIErrorReporter.fErrorReporter);
      return;
    } 
    this.nodeFactory.reset(paramXMLComponentManager);
    SymbolTable symbolTable = (SymbolTable)paramXMLComponentManager.getProperty("http://apache.org/xml/properties/internal/symbol-table");
    if (symbolTable != this.fSymbolTable)
      this.fSymbolTable = symbolTable; 
    try {
      this.fNamespaceGrowth = paramXMLComponentManager.getFeature("http://apache.org/xml/features/namespace-growth");
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fNamespaceGrowth = false;
    } 
    try {
      this.fDynamicValidation = paramXMLComponentManager.getFeature("http://apache.org/xml/features/validation/dynamic");
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fDynamicValidation = false;
    } 
    if (this.fDynamicValidation) {
      this.fDoValidation = true;
    } else {
      try {
        this.fDoValidation = paramXMLComponentManager.getFeature("http://xml.org/sax/features/validation");
      } catch (XMLConfigurationException xMLConfigurationException) {
        this.fDoValidation = false;
      } 
    } 
    if (this.fDoValidation)
      try {
        this.fDoValidation = paramXMLComponentManager.getFeature("http://apache.org/xml/features/validation/schema");
      } catch (XMLConfigurationException xMLConfigurationException) {} 
    try {
      this.fFullChecking = paramXMLComponentManager.getFeature("http://apache.org/xml/features/validation/schema-full-checking");
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fFullChecking = false;
    } 
    try {
      this.fNormalizeData = paramXMLComponentManager.getFeature("http://apache.org/xml/features/validation/schema/normalized-value");
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fNormalizeData = false;
    } 
    try {
      this.fSchemaElementDefault = paramXMLComponentManager.getFeature("http://apache.org/xml/features/validation/schema/element-default");
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fSchemaElementDefault = false;
    } 
    try {
      this.fAugPSVI = paramXMLComponentManager.getFeature("http://apache.org/xml/features/validation/schema/augment-psvi");
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fAugPSVI = true;
    } 
    try {
      this.fSchemaType = (String)paramXMLComponentManager.getProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage");
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fSchemaType = null;
    } 
    try {
      this.fUseGrammarPoolOnly = paramXMLComponentManager.getFeature("http://apache.org/xml/features/internal/validation/schema/use-grammar-pool-only");
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fUseGrammarPoolOnly = false;
    } 
    this.fEntityResolver = (XMLEntityResolver)paramXMLComponentManager.getProperty("http://apache.org/xml/properties/internal/entity-manager");
    this.fValidationManager = (ValidationManager)paramXMLComponentManager.getProperty("http://apache.org/xml/properties/internal/validation-manager");
    this.fValidationManager.addValidationState((ValidationState)this.fValidationState);
    this.fValidationState.setSymbolTable(this.fSymbolTable);
    try {
      Object object = paramXMLComponentManager.getProperty("http://apache.org/xml/properties/validation/schema/root-type-definition");
      if (object == null) {
        this.fRootTypeQName = null;
        this.fRootTypeDefinition = null;
      } else if (object instanceof QName) {
        this.fRootTypeQName = (QName)object;
        this.fRootTypeDefinition = null;
      } else {
        this.fRootTypeDefinition = (XSTypeDefinition)object;
        this.fRootTypeQName = null;
      } 
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fRootTypeQName = null;
      this.fRootTypeDefinition = null;
    } 
    try {
      Object object = paramXMLComponentManager.getProperty("http://apache.org/xml/properties/validation/schema/root-element-declaration");
      if (object == null) {
        this.fRootElementDeclQName = null;
        this.fRootElementDeclaration = null;
      } else if (object instanceof QName) {
        this.fRootElementDeclQName = (QName)object;
        this.fRootElementDeclaration = null;
      } else {
        this.fRootElementDeclaration = (XSElementDecl)object;
        this.fRootElementDeclQName = null;
      } 
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fRootElementDeclQName = null;
      this.fRootElementDeclaration = null;
    } 
    try {
      bool2 = paramXMLComponentManager.getFeature("http://apache.org/xml/features/validation/schema/ignore-xsi-type-until-elemdecl");
    } catch (XMLConfigurationException xMLConfigurationException) {
      bool2 = false;
    } 
    this.fIgnoreXSITypeDepth = bool2 ? 0 : -1;
    try {
      this.fIDCChecking = paramXMLComponentManager.getFeature("http://apache.org/xml/features/validation/identity-constraint-checking");
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fIDCChecking = true;
    } 
    try {
      this.fValidationState.setIdIdrefChecking(paramXMLComponentManager.getFeature("http://apache.org/xml/features/validation/id-idref-checking"));
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fValidationState.setIdIdrefChecking(true);
    } 
    try {
      this.fValidationState.setUnparsedEntityChecking(paramXMLComponentManager.getFeature("http://apache.org/xml/features/validation/unparsed-entity-checking"));
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fValidationState.setUnparsedEntityChecking(true);
    } 
    try {
      this.fExternalSchemas = (String)paramXMLComponentManager.getProperty("http://apache.org/xml/properties/schema/external-schemaLocation");
      this.fExternalNoNamespaceSchema = (String)paramXMLComponentManager.getProperty("http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation");
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fExternalSchemas = null;
      this.fExternalNoNamespaceSchema = null;
    } 
    XMLSchemaLoader.processExternalHints(this.fExternalSchemas, this.fExternalNoNamespaceSchema, this.fLocationPairs, this.fXSIErrorReporter.fErrorReporter);
    try {
      this.fJaxpSchemaSource = paramXMLComponentManager.getProperty("http://java.sun.com/xml/jaxp/properties/schemaSource");
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fJaxpSchemaSource = null;
    } 
    try {
      this.fGrammarPool = (XMLGrammarPool)paramXMLComponentManager.getProperty("http://apache.org/xml/properties/internal/grammar-pool");
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fGrammarPool = null;
    } 
    this.fState4XsiType.setSymbolTable(symbolTable);
    this.fState4ApplyDefault.setSymbolTable(symbolTable);
  }
  
  public void startValueScopeFor(IdentityConstraint paramIdentityConstraint, int paramInt) {
    ValueStoreBase valueStoreBase = this.fValueStoreCache.getValueStoreFor(paramIdentityConstraint, paramInt);
    valueStoreBase.startValueScope();
  }
  
  public XPathMatcher activateField(Field paramField, int paramInt) {
    ValueStoreBase valueStoreBase = this.fValueStoreCache.getValueStoreFor(paramField.getIdentityConstraint(), paramInt);
    XPathMatcher xPathMatcher = paramField.createMatcher(valueStoreBase);
    this.fMatcherStack.addMatcher(xPathMatcher);
    xPathMatcher.startDocumentFragment();
    return xPathMatcher;
  }
  
  public void endValueScopeFor(IdentityConstraint paramIdentityConstraint, int paramInt) {
    ValueStoreBase valueStoreBase = this.fValueStoreCache.getValueStoreFor(paramIdentityConstraint, paramInt);
    valueStoreBase.endValueScope();
  }
  
  private void activateSelectorFor(IdentityConstraint paramIdentityConstraint) {
    Selector selector = paramIdentityConstraint.getSelector();
    XMLSchemaValidator xMLSchemaValidator = this;
    if (selector == null)
      return; 
    XPathMatcher xPathMatcher = selector.createMatcher(xMLSchemaValidator, this.fElementDepth);
    this.fMatcherStack.addMatcher(xPathMatcher);
    xPathMatcher.startDocumentFragment();
  }
  
  public XSElementDecl getGlobalElementDecl(QName paramQName) {
    SchemaGrammar schemaGrammar = findSchemaGrammar((short)5, paramQName.uri, null, paramQName, null);
    return (schemaGrammar != null) ? schemaGrammar.getGlobalElementDecl(paramQName.localpart) : null;
  }
  
  void ensureStackCapacity() {
    if (this.fElementDepth == this.fElemDeclStack.length) {
      int i = this.fElementDepth + 8;
      boolean[] arrayOfBoolean = new boolean[i];
      System.arraycopy(this.fSubElementStack, 0, arrayOfBoolean, 0, this.fElementDepth);
      this.fSubElementStack = arrayOfBoolean;
      XSElementDecl[] arrayOfXSElementDecl = new XSElementDecl[i];
      System.arraycopy(this.fElemDeclStack, 0, arrayOfXSElementDecl, 0, this.fElementDepth);
      this.fElemDeclStack = arrayOfXSElementDecl;
      arrayOfBoolean = new boolean[i];
      System.arraycopy(this.fNilStack, 0, arrayOfBoolean, 0, this.fElementDepth);
      this.fNilStack = arrayOfBoolean;
      XSNotationDecl[] arrayOfXSNotationDecl = new XSNotationDecl[i];
      System.arraycopy(this.fNotationStack, 0, arrayOfXSNotationDecl, 0, this.fElementDepth);
      this.fNotationStack = arrayOfXSNotationDecl;
      XSTypeDefinition[] arrayOfXSTypeDefinition = new XSTypeDefinition[i];
      System.arraycopy(this.fTypeStack, 0, arrayOfXSTypeDefinition, 0, this.fElementDepth);
      this.fTypeStack = arrayOfXSTypeDefinition;
      XSCMValidator[] arrayOfXSCMValidator = new XSCMValidator[i];
      System.arraycopy(this.fCMStack, 0, arrayOfXSCMValidator, 0, this.fElementDepth);
      this.fCMStack = arrayOfXSCMValidator;
      arrayOfBoolean = new boolean[i];
      System.arraycopy(this.fSawTextStack, 0, arrayOfBoolean, 0, this.fElementDepth);
      this.fSawTextStack = arrayOfBoolean;
      arrayOfBoolean = new boolean[i];
      System.arraycopy(this.fStringContent, 0, arrayOfBoolean, 0, this.fElementDepth);
      this.fStringContent = arrayOfBoolean;
      arrayOfBoolean = new boolean[i];
      System.arraycopy(this.fStrictAssessStack, 0, arrayOfBoolean, 0, this.fElementDepth);
      this.fStrictAssessStack = arrayOfBoolean;
      int[][] arrayOfInt = new int[i][];
      System.arraycopy(this.fCMStateStack, 0, arrayOfInt, 0, this.fElementDepth);
      this.fCMStateStack = arrayOfInt;
    } 
  }
  
  void handleStartDocument(XMLLocator paramXMLLocator, String paramString) {
    if (this.fIDCChecking)
      this.fValueStoreCache.startDocument(); 
    if (this.fAugPSVI) {
      this.fCurrentPSVI.fGrammars = null;
      this.fCurrentPSVI.fSchemaInformation = null;
    } 
  }
  
  void handleEndDocument() {
    if (this.fIDCChecking)
      this.fValueStoreCache.endDocument(); 
  }
  
  XMLString handleCharacters(XMLString paramXMLString) {
    if (this.fSkipValidationDepth >= 0)
      return paramXMLString; 
    this.fSawText = (this.fSawText || paramXMLString.length > 0);
    if (this.fNormalizeData && this.fWhiteSpace != -1 && this.fWhiteSpace != 0) {
      normalizeWhitespace(paramXMLString, (this.fWhiteSpace == 2));
      paramXMLString = this.fNormalizedStr;
    } 
    if (this.fAppendBuffer)
      this.fBuffer.append(paramXMLString.ch, paramXMLString.offset, paramXMLString.length); 
    if (this.fCurrentType != null && this.fCurrentType.getTypeCategory() == 15) {
      XSComplexTypeDecl xSComplexTypeDecl = (XSComplexTypeDecl)this.fCurrentType;
      if (xSComplexTypeDecl.fContentType == 2)
        for (int i = paramXMLString.offset; i < paramXMLString.offset + paramXMLString.length; i++) {
          if (!XMLChar.isSpace(paramXMLString.ch[i])) {
            this.fSawCharacters = true;
            break;
          } 
        }  
    } 
    return paramXMLString;
  }
  
  private void normalizeWhitespace(XMLString paramXMLString, boolean paramBoolean) {
    boolean bool = paramBoolean;
    boolean bool1 = false;
    boolean bool2 = false;
    boolean bool3 = false;
    int i = paramXMLString.offset + paramXMLString.length;
    if (this.fNormalizedStr.ch == null || this.fNormalizedStr.ch.length < paramXMLString.length + 1)
      this.fNormalizedStr.ch = new char[paramXMLString.length + 1]; 
    this.fNormalizedStr.offset = 1;
    this.fNormalizedStr.length = 1;
    for (int j = paramXMLString.offset; j < i; j++) {
      char c = paramXMLString.ch[j];
      if (XMLChar.isSpace(c)) {
        if (!bool) {
          this.fNormalizedStr.ch[this.fNormalizedStr.length++] = ' ';
          bool = paramBoolean;
        } 
        if (!bool1)
          bool2 = true; 
      } else {
        this.fNormalizedStr.ch[this.fNormalizedStr.length++] = c;
        bool = false;
        bool1 = true;
      } 
    } 
    if (bool)
      if (this.fNormalizedStr.length > 1) {
        this.fNormalizedStr.length--;
        bool3 = true;
      } else if (bool2 && !this.fFirstChunk) {
        bool3 = true;
      }  
    if (this.fNormalizedStr.length > 1 && !this.fFirstChunk && this.fWhiteSpace == 2)
      if (this.fTrailing) {
        this.fNormalizedStr.offset = 0;
        this.fNormalizedStr.ch[0] = ' ';
      } else if (bool2) {
        this.fNormalizedStr.offset = 0;
        this.fNormalizedStr.ch[0] = ' ';
      }  
    this.fNormalizedStr.length -= this.fNormalizedStr.offset;
    this.fTrailing = bool3;
    if (bool3 || bool1)
      this.fFirstChunk = false; 
  }
  
  private void normalizeWhitespace(String paramString, boolean paramBoolean) {
    boolean bool = paramBoolean;
    int i = paramString.length();
    if (this.fNormalizedStr.ch == null || this.fNormalizedStr.ch.length < i)
      this.fNormalizedStr.ch = new char[i]; 
    this.fNormalizedStr.offset = 0;
    this.fNormalizedStr.length = 0;
    for (byte b = 0; b < i; b++) {
      char c = paramString.charAt(b);
      if (XMLChar.isSpace(c)) {
        if (!bool) {
          this.fNormalizedStr.ch[this.fNormalizedStr.length++] = ' ';
          bool = paramBoolean;
        } 
      } else {
        this.fNormalizedStr.ch[this.fNormalizedStr.length++] = c;
        bool = false;
      } 
    } 
    if (bool && this.fNormalizedStr.length != 0)
      this.fNormalizedStr.length--; 
  }
  
  void handleIgnorableWhitespace(XMLString paramXMLString) {
    if (this.fSkipValidationDepth >= 0)
      return; 
  }
  
  Augmentations handleStartElement(QName paramQName, XMLAttributes paramXMLAttributes, Augmentations paramAugmentations) {
    if (this.fElementDepth == -1 && this.fValidationManager.isGrammarFound() && this.fSchemaType == null)
      this.fSchemaDynamicValidation = true; 
    if (!this.fUseGrammarPoolOnly) {
      String str3 = paramXMLAttributes.getValue(SchemaSymbols.URI_XSI, SchemaSymbols.XSI_SCHEMALOCATION);
      String str4 = paramXMLAttributes.getValue(SchemaSymbols.URI_XSI, SchemaSymbols.XSI_NONAMESPACESCHEMALOCATION);
      storeLocations(str3, str4);
    } 
    if (this.fSkipValidationDepth >= 0) {
      this.fElementDepth++;
      if (this.fAugPSVI)
        paramAugmentations = getEmptyAugs(paramAugmentations); 
      return paramAugmentations;
    } 
    Object object = null;
    if (this.fCurrentCM != null) {
      object = this.fCurrentCM.oneTransition(paramQName, this.fCurrCMState, this.fSubGroupHandler);
      if (this.fCurrCMState[0] == -1) {
        XSComplexTypeDecl xSComplexTypeDecl = (XSComplexTypeDecl)this.fCurrentType;
        Vector vector;
        if (xSComplexTypeDecl.fParticle != null && (vector = this.fCurrentCM.whatCanGoHere(this.fCurrCMState)).size() > 0) {
          String str = expectedStr(vector);
          int[] arrayOfInt = this.fCurrentCM.occurenceInfo(this.fCurrCMState);
          if (arrayOfInt != null) {
            int j = arrayOfInt[0];
            int k = arrayOfInt[1];
            int m = arrayOfInt[2];
            if (m < j) {
              int n = j - m;
              if (n > 1) {
                reportSchemaError("cvc-complex-type.2.4.h", new Object[] { paramQName.rawname, this.fCurrentCM.getTermName(arrayOfInt[3]), Integer.toString(j), Integer.toString(n) });
              } else {
                reportSchemaError("cvc-complex-type.2.4.g", new Object[] { paramQName.rawname, this.fCurrentCM.getTermName(arrayOfInt[3]), Integer.toString(j) });
              } 
            } else if (m >= k && k != -1) {
              reportSchemaError("cvc-complex-type.2.4.e", new Object[] { paramQName.rawname, str, Integer.toString(k) });
            } else {
              reportSchemaError("cvc-complex-type.2.4.a", new Object[] { paramQName.rawname, str });
            } 
          } else {
            reportSchemaError("cvc-complex-type.2.4.a", new Object[] { paramQName.rawname, str });
          } 
        } else {
          int[] arrayOfInt = this.fCurrentCM.occurenceInfo(this.fCurrCMState);
          if (arrayOfInt != null) {
            int j = arrayOfInt[1];
            int k = arrayOfInt[2];
            if (k >= j && j != -1) {
              reportSchemaError("cvc-complex-type.2.4.f", new Object[] { paramQName.rawname, Integer.toString(j) });
            } else {
              reportSchemaError("cvc-complex-type.2.4.d", new Object[] { paramQName.rawname });
            } 
          } else {
            reportSchemaError("cvc-complex-type.2.4.d", new Object[] { paramQName.rawname });
          } 
        } 
      } 
    } 
    if (this.fElementDepth != -1) {
      ensureStackCapacity();
      this.fSubElementStack[this.fElementDepth] = true;
      this.fSubElement = false;
      this.fElemDeclStack[this.fElementDepth] = this.fCurrentElemDecl;
      this.fNilStack[this.fElementDepth] = this.fNil;
      this.fNotationStack[this.fElementDepth] = this.fNotation;
      this.fTypeStack[this.fElementDepth] = this.fCurrentType;
      this.fStrictAssessStack[this.fElementDepth] = this.fStrictAssess;
      this.fCMStack[this.fElementDepth] = this.fCurrentCM;
      this.fCMStateStack[this.fElementDepth] = this.fCurrCMState;
      this.fSawTextStack[this.fElementDepth] = this.fSawText;
      this.fStringContent[this.fElementDepth] = this.fSawCharacters;
    } 
    this.fElementDepth++;
    this.fCurrentElemDecl = null;
    XSWildcardDecl xSWildcardDecl = null;
    this.fCurrentType = null;
    this.fStrictAssess = true;
    this.fNil = false;
    this.fNotation = null;
    this.fBuffer.setLength(0);
    this.fSawText = false;
    this.fSawCharacters = false;
    if (object != null)
      if (object instanceof XSElementDecl) {
        this.fCurrentElemDecl = (XSElementDecl)object;
      } else {
        xSWildcardDecl = (XSWildcardDecl)object;
      }  
    if (xSWildcardDecl != null && xSWildcardDecl.fProcessContents == 2) {
      this.fSkipValidationDepth = this.fElementDepth;
      if (this.fAugPSVI)
        paramAugmentations = getEmptyAugs(paramAugmentations); 
      return paramAugmentations;
    } 
    if (this.fElementDepth == 0)
      if (this.fRootElementDeclaration != null) {
        this.fCurrentElemDecl = this.fRootElementDeclaration;
        checkElementMatchesRootElementDecl(this.fCurrentElemDecl, paramQName);
      } else if (this.fRootElementDeclQName != null) {
        processRootElementDeclQName(this.fRootElementDeclQName, paramQName);
      } else if (this.fRootTypeDefinition != null) {
        this.fCurrentType = this.fRootTypeDefinition;
      } else if (this.fRootTypeQName != null) {
        processRootTypeQName(this.fRootTypeQName);
      }  
    if (this.fCurrentType == null) {
      if (this.fCurrentElemDecl == null) {
        SchemaGrammar schemaGrammar = findSchemaGrammar((short)5, paramQName.uri, null, paramQName, paramXMLAttributes);
        if (schemaGrammar != null)
          this.fCurrentElemDecl = schemaGrammar.getGlobalElementDecl(paramQName.localpart); 
      } 
      if (this.fCurrentElemDecl != null)
        this.fCurrentType = this.fCurrentElemDecl.fType; 
    } 
    if (this.fElementDepth == this.fIgnoreXSITypeDepth && this.fCurrentElemDecl == null)
      this.fIgnoreXSITypeDepth++; 
    String str1 = null;
    if (this.fElementDepth >= this.fIgnoreXSITypeDepth)
      str1 = paramXMLAttributes.getValue(SchemaSymbols.URI_XSI, SchemaSymbols.XSI_TYPE); 
    if (this.fCurrentType == null && str1 == null) {
      if (this.fElementDepth == 0) {
        if (this.fDynamicValidation || this.fSchemaDynamicValidation) {
          if (this.fDocumentSource != null) {
            this.fDocumentSource.setDocumentHandler(this.fDocumentHandler);
            if (this.fDocumentHandler != null)
              this.fDocumentHandler.setDocumentSource(this.fDocumentSource); 
            this.fElementDepth = -2;
            return paramAugmentations;
          } 
          this.fSkipValidationDepth = this.fElementDepth;
          if (this.fAugPSVI)
            paramAugmentations = getEmptyAugs(paramAugmentations); 
          return paramAugmentations;
        } 
        this.fXSIErrorReporter.fErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", "cvc-elt.1.a", new Object[] { paramQName.rawname }, (short)1);
      } else if (xSWildcardDecl != null && xSWildcardDecl.fProcessContents == 1) {
        reportSchemaError("cvc-complex-type.2.4.c", new Object[] { paramQName.rawname });
      } 
      this.fCurrentType = (XSTypeDefinition)SchemaGrammar.fAnyType;
      this.fStrictAssess = false;
      this.fNFullValidationDepth = this.fElementDepth;
      this.fAppendBuffer = false;
      this.fXSIErrorReporter.pushContext();
    } else {
      this.fXSIErrorReporter.pushContext();
      if (str1 != null) {
        XSTypeDefinition xSTypeDefinition = this.fCurrentType;
        this.fCurrentType = getAndCheckXsiType(paramQName, str1, paramXMLAttributes);
        if (this.fCurrentType == null)
          if (xSTypeDefinition == null) {
            this.fCurrentType = (XSTypeDefinition)SchemaGrammar.fAnyType;
          } else {
            this.fCurrentType = xSTypeDefinition;
          }  
      } 
      this.fNNoneValidationDepth = this.fElementDepth;
      if (this.fCurrentElemDecl != null && this.fCurrentElemDecl.getConstraintType() == 2) {
        this.fAppendBuffer = true;
      } else if (this.fCurrentType.getTypeCategory() == 16) {
        this.fAppendBuffer = true;
      } else {
        XSComplexTypeDecl xSComplexTypeDecl = (XSComplexTypeDecl)this.fCurrentType;
        this.fAppendBuffer = (xSComplexTypeDecl.fContentType == 1);
      } 
    } 
    if (this.fCurrentElemDecl != null && this.fCurrentElemDecl.getAbstract())
      reportSchemaError("cvc-elt.2", new Object[] { paramQName.rawname }); 
    if (this.fElementDepth == 0)
      this.fValidationRoot = paramQName.rawname; 
    if (this.fNormalizeData) {
      this.fFirstChunk = true;
      this.fTrailing = false;
      this.fUnionType = false;
      this.fWhiteSpace = -1;
    } 
    if (this.fCurrentType.getTypeCategory() == 15) {
      XSComplexTypeDecl xSComplexTypeDecl = (XSComplexTypeDecl)this.fCurrentType;
      if (xSComplexTypeDecl.getAbstract())
        reportSchemaError("cvc-type.2", new Object[] { paramQName.rawname }); 
      if (this.fNormalizeData && xSComplexTypeDecl.fContentType == 1)
        if (xSComplexTypeDecl.fXSSimpleType.getVariety() == 3) {
          this.fUnionType = true;
        } else {
          try {
            this.fWhiteSpace = xSComplexTypeDecl.fXSSimpleType.getWhitespace();
          } catch (DatatypeException datatypeException) {}
        }  
    } else if (this.fNormalizeData) {
      XSSimpleType xSSimpleType = (XSSimpleType)this.fCurrentType;
      if (xSSimpleType.getVariety() == 3) {
        this.fUnionType = true;
      } else {
        try {
          this.fWhiteSpace = xSSimpleType.getWhitespace();
        } catch (DatatypeException datatypeException) {}
      } 
    } 
    this.fCurrentCM = null;
    if (this.fCurrentType.getTypeCategory() == 15)
      this.fCurrentCM = ((XSComplexTypeDecl)this.fCurrentType).getContentModel(this.fCMBuilder); 
    this.fCurrCMState = null;
    if (this.fCurrentCM != null)
      this.fCurrCMState = this.fCurrentCM.startContentModel(); 
    String str2 = paramXMLAttributes.getValue(SchemaSymbols.URI_XSI, SchemaSymbols.XSI_NIL);
    if (str2 != null && this.fCurrentElemDecl != null)
      this.fNil = getXsiNil(paramQName, str2); 
    XSAttributeGroupDecl xSAttributeGroupDecl = null;
    if (this.fCurrentType.getTypeCategory() == 15) {
      XSComplexTypeDecl xSComplexTypeDecl = (XSComplexTypeDecl)this.fCurrentType;
      xSAttributeGroupDecl = xSComplexTypeDecl.getAttrGrp();
    } 
    if (this.fIDCChecking) {
      this.fValueStoreCache.startElement();
      this.fMatcherStack.pushContext();
      if (this.fCurrentElemDecl != null && this.fCurrentElemDecl.fIDCPos > 0) {
        this.fIdConstraint = true;
        this.fValueStoreCache.initValueStoresFor(this.fCurrentElemDecl, this);
      } 
    } 
    processAttributes(paramQName, paramXMLAttributes, xSAttributeGroupDecl);
    if (xSAttributeGroupDecl != null)
      addDefaultAttributes(paramQName, paramXMLAttributes, xSAttributeGroupDecl); 
    int i = this.fMatcherStack.getMatcherCount();
    for (byte b = 0; b < i; b++) {
      XPathMatcher xPathMatcher = this.fMatcherStack.getMatcherAt(b);
      xPathMatcher.startElement(paramQName, paramXMLAttributes);
    } 
    if (this.fAugPSVI) {
      paramAugmentations = getEmptyAugs(paramAugmentations);
      this.fCurrentPSVI.fValidationContext = this.fValidationRoot;
      this.fCurrentPSVI.fDeclaration = this.fCurrentElemDecl;
      this.fCurrentPSVI.fTypeDecl = this.fCurrentType;
      this.fCurrentPSVI.fNotation = this.fNotation;
      this.fCurrentPSVI.fNil = this.fNil;
    } 
    return paramAugmentations;
  }
  
  Augmentations handleEndElement(QName paramQName, Augmentations paramAugmentations) {
    if (this.fSkipValidationDepth >= 0) {
      if (this.fSkipValidationDepth == this.fElementDepth && this.fSkipValidationDepth > 0) {
        this.fNFullValidationDepth = this.fSkipValidationDepth - 1;
        this.fSkipValidationDepth = -1;
        this.fElementDepth--;
        this.fSubElement = this.fSubElementStack[this.fElementDepth];
        this.fCurrentElemDecl = this.fElemDeclStack[this.fElementDepth];
        this.fNil = this.fNilStack[this.fElementDepth];
        this.fNotation = this.fNotationStack[this.fElementDepth];
        this.fCurrentType = this.fTypeStack[this.fElementDepth];
        this.fCurrentCM = this.fCMStack[this.fElementDepth];
        this.fStrictAssess = this.fStrictAssessStack[this.fElementDepth];
        this.fCurrCMState = this.fCMStateStack[this.fElementDepth];
        this.fSawText = this.fSawTextStack[this.fElementDepth];
        this.fSawCharacters = this.fStringContent[this.fElementDepth];
      } else {
        this.fElementDepth--;
      } 
      if (this.fElementDepth == -1 && this.fFullChecking && !this.fUseGrammarPoolOnly)
        XSConstraints.fullSchemaChecking(this.fGrammarBucket, this.fSubGroupHandler, this.fCMBuilder, this.fXSIErrorReporter.fErrorReporter); 
      if (this.fAugPSVI)
        paramAugmentations = getEmptyAugs(paramAugmentations); 
      return paramAugmentations;
    } 
    processElementContent(paramQName);
    if (this.fIDCChecking) {
      int i = this.fMatcherStack.getMatcherCount();
      for (int j = i - 1; j >= 0; j--) {
        XPathMatcher xPathMatcher = this.fMatcherStack.getMatcherAt(j);
        if (this.fCurrentElemDecl == null) {
          xPathMatcher.endElement(paramQName, this.fCurrentType, false, this.fValidatedInfo.actualValue, this.fValidatedInfo.actualValueType, this.fValidatedInfo.itemValueTypes);
        } else {
          xPathMatcher.endElement(paramQName, this.fCurrentType, this.fCurrentElemDecl.getNillable(), (this.fDefaultValue == null) ? this.fValidatedInfo.actualValue : this.fCurrentElemDecl.fDefault.actualValue, (this.fDefaultValue == null) ? this.fValidatedInfo.actualValueType : this.fCurrentElemDecl.fDefault.actualValueType, (this.fDefaultValue == null) ? this.fValidatedInfo.itemValueTypes : this.fCurrentElemDecl.fDefault.itemValueTypes);
        } 
      } 
      if (this.fMatcherStack.size() > 0)
        this.fMatcherStack.popContext(); 
      int k = this.fMatcherStack.getMatcherCount();
      for (int m = i - 1; m >= k; m--) {
        XPathMatcher xPathMatcher = this.fMatcherStack.getMatcherAt(m);
        Selector.Matcher matcher = (Selector.Matcher)xPathMatcher;
        IdentityConstraint identityConstraint;
        if (xPathMatcher instanceof Selector.Matcher && (identityConstraint = matcher.getIdentityConstraint()) != null && identityConstraint.getCategory() != 2)
          this.fValueStoreCache.transplant(identityConstraint, matcher.getInitialDepth()); 
      } 
      for (int n = i - 1; n >= k; n--) {
        XPathMatcher xPathMatcher = this.fMatcherStack.getMatcherAt(n);
        Selector.Matcher matcher = (Selector.Matcher)xPathMatcher;
        IdentityConstraint identityConstraint;
        if (xPathMatcher instanceof Selector.Matcher && (identityConstraint = matcher.getIdentityConstraint()) != null && identityConstraint.getCategory() == 2) {
          ValueStoreBase valueStoreBase = this.fValueStoreCache.getValueStoreFor(identityConstraint, matcher.getInitialDepth());
          if (valueStoreBase != null)
            valueStoreBase.endDocumentFragment(); 
        } 
      } 
      this.fValueStoreCache.endElement();
    } 
    if (this.fElementDepth < this.fIgnoreXSITypeDepth)
      this.fIgnoreXSITypeDepth--; 
    SchemaGrammar[] arrayOfSchemaGrammar = null;
    if (this.fElementDepth == 0) {
      String str = this.fValidationState.checkIDRefID();
      this.fValidationState.resetIDTables();
      if (str != null)
        reportSchemaError("cvc-id.1", new Object[] { str }); 
      if (this.fFullChecking && !this.fUseGrammarPoolOnly)
        XSConstraints.fullSchemaChecking(this.fGrammarBucket, this.fSubGroupHandler, this.fCMBuilder, this.fXSIErrorReporter.fErrorReporter); 
      arrayOfSchemaGrammar = this.fGrammarBucket.getGrammars();
      if (this.fGrammarPool != null) {
        for (byte b = 0; b < arrayOfSchemaGrammar.length; b++)
          arrayOfSchemaGrammar[b].setImmutable(true); 
        this.fGrammarPool.cacheGrammars("http://www.w3.org/2001/XMLSchema", (Grammar[])arrayOfSchemaGrammar);
      } 
      paramAugmentations = endElementPSVI(true, arrayOfSchemaGrammar, paramAugmentations);
    } else {
      paramAugmentations = endElementPSVI(false, arrayOfSchemaGrammar, paramAugmentations);
      this.fElementDepth--;
      this.fSubElement = this.fSubElementStack[this.fElementDepth];
      this.fCurrentElemDecl = this.fElemDeclStack[this.fElementDepth];
      this.fNil = this.fNilStack[this.fElementDepth];
      this.fNotation = this.fNotationStack[this.fElementDepth];
      this.fCurrentType = this.fTypeStack[this.fElementDepth];
      this.fCurrentCM = this.fCMStack[this.fElementDepth];
      this.fStrictAssess = this.fStrictAssessStack[this.fElementDepth];
      this.fCurrCMState = this.fCMStateStack[this.fElementDepth];
      this.fSawText = this.fSawTextStack[this.fElementDepth];
      this.fSawCharacters = this.fStringContent[this.fElementDepth];
      this.fWhiteSpace = -1;
      this.fAppendBuffer = false;
      this.fUnionType = false;
    } 
    return paramAugmentations;
  }
  
  final Augmentations endElementPSVI(boolean paramBoolean, SchemaGrammar[] paramArrayOfSchemaGrammar, Augmentations paramAugmentations) {
    if (this.fAugPSVI) {
      paramAugmentations = getEmptyAugs(paramAugmentations);
      this.fCurrentPSVI.fDeclaration = this.fCurrentElemDecl;
      this.fCurrentPSVI.fTypeDecl = this.fCurrentType;
      this.fCurrentPSVI.fNotation = this.fNotation;
      this.fCurrentPSVI.fValidationContext = this.fValidationRoot;
      this.fCurrentPSVI.fNil = this.fNil;
      if (this.fElementDepth > this.fNFullValidationDepth) {
        this.fCurrentPSVI.fValidationAttempted = 2;
      } else if (this.fElementDepth > this.fNNoneValidationDepth) {
        this.fCurrentPSVI.fValidationAttempted = 0;
      } else {
        this.fCurrentPSVI.fValidationAttempted = 1;
      } 
      if (this.fNFullValidationDepth == this.fElementDepth)
        this.fNFullValidationDepth = this.fElementDepth - 1; 
      if (this.fNNoneValidationDepth == this.fElementDepth)
        this.fNNoneValidationDepth = this.fElementDepth - 1; 
      if (this.fDefaultValue != null)
        this.fCurrentPSVI.fSpecified = true; 
      this.fCurrentPSVI.fValue.copyFrom((XSValue)this.fValidatedInfo);
      if (this.fStrictAssess) {
        String[] arrayOfString = this.fXSIErrorReporter.mergeContext();
        this.fCurrentPSVI.fErrors = arrayOfString;
        this.fCurrentPSVI.fValidity = (arrayOfString == null) ? 2 : 1;
      } else {
        this.fCurrentPSVI.fValidity = 0;
        this.fXSIErrorReporter.popContext();
      } 
      if (paramBoolean) {
        this.fCurrentPSVI.fGrammars = paramArrayOfSchemaGrammar;
        this.fCurrentPSVI.fSchemaInformation = null;
      } 
    } 
    return paramAugmentations;
  }
  
  Augmentations getEmptyAugs(Augmentations paramAugmentations) {
    AugmentationsImpl augmentationsImpl;
    if (paramAugmentations == null) {
      augmentationsImpl = this.fAugmentations;
      augmentationsImpl.removeAllItems();
    } 
    augmentationsImpl.putItem("ELEMENT_PSVI", this.fCurrentPSVI);
    this.fCurrentPSVI.reset();
    return (Augmentations)augmentationsImpl;
  }
  
  void storeLocations(String paramString1, String paramString2) {
    if (paramString1 != null && !XMLSchemaLoader.tokenizeSchemaLocationStr(paramString1, this.fLocationPairs, (this.fLocator == null) ? null : this.fLocator.getExpandedSystemId()))
      this.fXSIErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", "SchemaLocation", new Object[] { paramString1 }, (short)0); 
    if (paramString2 != null) {
      XMLSchemaLoader.LocationArray locationArray = (XMLSchemaLoader.LocationArray)this.fLocationPairs.get(XMLSymbols.EMPTY_STRING);
      if (locationArray == null) {
        locationArray = new XMLSchemaLoader.LocationArray();
        this.fLocationPairs.put(XMLSymbols.EMPTY_STRING, locationArray);
      } 
      if (this.fLocator != null)
        try {
          paramString2 = XMLEntityManager.expandSystemId(paramString2, this.fLocator.getExpandedSystemId(), false);
        } catch (org.apache.xerces.util.URI.MalformedURIException malformedURIException) {} 
      locationArray.addLocation(paramString2);
    } 
  }
  
  SchemaGrammar findSchemaGrammar(short paramShort, String paramString, QName paramQName1, QName paramQName2, XMLAttributes paramXMLAttributes) {
    SchemaGrammar schemaGrammar = null;
    schemaGrammar = this.fGrammarBucket.getGrammar(paramString);
    if (schemaGrammar == null) {
      this.fXSDDescription.setNamespace(paramString);
      if (this.fGrammarPool != null) {
        schemaGrammar = (SchemaGrammar)this.fGrammarPool.retrieveGrammar((XMLGrammarDescription)this.fXSDDescription);
        if (schemaGrammar != null && !this.fGrammarBucket.putGrammar(schemaGrammar, true, this.fNamespaceGrowth)) {
          this.fXSIErrorReporter.fErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", "GrammarConflict", null, (short)0);
          schemaGrammar = null;
        } 
      } 
    } 
    if (!this.fUseGrammarPoolOnly && (schemaGrammar == null || (this.fNamespaceGrowth && !hasSchemaComponent(schemaGrammar, paramShort, paramQName2)))) {
      this.fXSDDescription.reset();
      this.fXSDDescription.fContextType = paramShort;
      this.fXSDDescription.setNamespace(paramString);
      this.fXSDDescription.fEnclosedElementName = paramQName1;
      this.fXSDDescription.fTriggeringComponent = paramQName2;
      this.fXSDDescription.fAttributes = paramXMLAttributes;
      if (this.fLocator != null)
        this.fXSDDescription.setBaseSystemId(this.fLocator.getExpandedSystemId()); 
      Hashtable hashtable = this.fLocationPairs;
      Object object = hashtable.get((paramString == null) ? XMLSymbols.EMPTY_STRING : paramString);
      if (object != null) {
        String[] arrayOfString = ((XMLSchemaLoader.LocationArray)object).getLocationArray();
        if (arrayOfString.length != 0)
          setLocationHints(this.fXSDDescription, arrayOfString, schemaGrammar); 
      } 
      if (schemaGrammar == null || this.fXSDDescription.fLocationHints != null) {
        boolean bool = true;
        if (schemaGrammar != null)
          hashtable = EMPTY_TABLE; 
        try {
          XMLInputSource xMLInputSource = XMLSchemaLoader.resolveDocument(this.fXSDDescription, hashtable, this.fEntityResolver);
          if (schemaGrammar != null && this.fNamespaceGrowth)
            try {
              if (schemaGrammar.getDocumentLocations().contains(XMLEntityManager.expandSystemId(xMLInputSource.getSystemId(), xMLInputSource.getBaseSystemId(), false)))
                bool = false; 
            } catch (org.apache.xerces.util.URI.MalformedURIException malformedURIException) {} 
          if (bool)
            schemaGrammar = this.fSchemaLoader.loadSchema(this.fXSDDescription, xMLInputSource, this.fLocationPairs); 
        } catch (IOException iOException) {
          String[] arrayOfString = this.fXSDDescription.getLocationHints();
          this.fXSIErrorReporter.fErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", "schema_reference.4", new Object[] { (arrayOfString != null) ? arrayOfString[0] : XMLSymbols.EMPTY_STRING }, (short)0, iOException);
        } 
      } 
    } 
    return schemaGrammar;
  }
  
  private boolean hasSchemaComponent(SchemaGrammar paramSchemaGrammar, short paramShort, QName paramQName) {
    if (paramSchemaGrammar != null && paramQName != null) {
      String str = paramQName.localpart;
      if (str != null && str.length() > 0)
        switch (paramShort) {
          case 5:
            return (paramSchemaGrammar.getElementDeclaration(str) != null);
          case 6:
            return (paramSchemaGrammar.getAttributeDeclaration(str) != null);
          case 7:
            return (paramSchemaGrammar.getTypeDefinition(str) != null);
        }  
    } 
    return false;
  }
  
  private void setLocationHints(XSDDescription paramXSDDescription, String[] paramArrayOfString, SchemaGrammar paramSchemaGrammar) {
    int i = paramArrayOfString.length;
    if (paramSchemaGrammar == null) {
      this.fXSDDescription.fLocationHints = new String[i];
      System.arraycopy(paramArrayOfString, 0, this.fXSDDescription.fLocationHints, 0, i);
    } else {
      setLocationHints(paramXSDDescription, paramArrayOfString, paramSchemaGrammar.getDocumentLocations());
    } 
  }
  
  private void setLocationHints(XSDDescription paramXSDDescription, String[] paramArrayOfString, StringList paramStringList) {
    int i = paramArrayOfString.length;
    String[] arrayOfString = new String[i];
    byte b1 = 0;
    for (byte b2 = 0; b2 < i; b2++) {
      if (!paramStringList.contains(paramArrayOfString[b2]))
        arrayOfString[b1++] = paramArrayOfString[b2]; 
    } 
    if (b1 > 0)
      if (b1 == i) {
        this.fXSDDescription.fLocationHints = arrayOfString;
      } else {
        this.fXSDDescription.fLocationHints = new String[b1];
        System.arraycopy(arrayOfString, 0, this.fXSDDescription.fLocationHints, 0, b1);
      }  
  }
  
  XSTypeDefinition getAndCheckXsiType(QName paramQName, String paramString, XMLAttributes paramXMLAttributes) {
    QName qName = null;
    try {
      qName = (QName)this.fQNameDV.validate(paramString, (ValidationContext)this.fValidationState, null);
    } catch (InvalidDatatypeValueException invalidDatatypeValueException) {
      reportSchemaError(invalidDatatypeValueException.getKey(), invalidDatatypeValueException.getArgs());
      reportSchemaError("cvc-elt.4.1", new Object[] { paramQName.rawname, SchemaSymbols.URI_XSI + "," + SchemaSymbols.XSI_TYPE, paramString });
      return null;
    } 
    XSTypeDefinition xSTypeDefinition = null;
    if (qName.uri == SchemaSymbols.URI_SCHEMAFORSCHEMA)
      xSTypeDefinition = SchemaGrammar.SG_SchemaNS.getGlobalTypeDecl(qName.localpart); 
    if (xSTypeDefinition == null) {
      SchemaGrammar schemaGrammar = findSchemaGrammar((short)7, qName.uri, paramQName, qName, paramXMLAttributes);
      if (schemaGrammar != null)
        xSTypeDefinition = schemaGrammar.getGlobalTypeDecl(qName.localpart); 
    } 
    if (xSTypeDefinition == null) {
      reportSchemaError("cvc-elt.4.2", new Object[] { paramQName.rawname, paramString });
      return null;
    } 
    if (this.fCurrentType != null) {
      short s = 0;
      if (this.fCurrentElemDecl != null)
        s = this.fCurrentElemDecl.fBlock; 
      if (this.fCurrentType.getTypeCategory() == 15)
        s = (short)(s | ((XSComplexTypeDecl)this.fCurrentType).fBlock); 
      if (!XSConstraints.checkTypeDerivationOk(xSTypeDefinition, this.fCurrentType, s))
        reportSchemaError("cvc-elt.4.3", new Object[] { paramQName.rawname, paramString, this.fCurrentType.getName() }); 
    } 
    return xSTypeDefinition;
  }
  
  boolean getXsiNil(QName paramQName, String paramString) {
    if (this.fCurrentElemDecl != null && !this.fCurrentElemDecl.getNillable()) {
      reportSchemaError("cvc-elt.3.1", new Object[] { paramQName.rawname, SchemaSymbols.URI_XSI + "," + SchemaSymbols.XSI_NIL });
    } else {
      String str = XMLChar.trim(paramString);
      if (str.equals("true") || str.equals("1")) {
        if (this.fCurrentElemDecl != null && this.fCurrentElemDecl.getConstraintType() == 2)
          reportSchemaError("cvc-elt.3.2.2", new Object[] { paramQName.rawname, SchemaSymbols.URI_XSI + "," + SchemaSymbols.XSI_NIL }); 
        return true;
      } 
    } 
    return false;
  }
  
  void processAttributes(QName paramQName, XMLAttributes paramXMLAttributes, XSAttributeGroupDecl paramXSAttributeGroupDecl) {
    // Byte code:
    //   0: aconst_null
    //   1: astore #4
    //   3: aload_2
    //   4: invokeinterface getLength : ()I
    //   9: istore #5
    //   11: aconst_null
    //   12: astore #6
    //   14: aconst_null
    //   15: astore #7
    //   17: aload_0
    //   18: getfield fCurrentType : Lorg/apache/xerces/xs/XSTypeDefinition;
    //   21: ifnull -> 38
    //   24: aload_0
    //   25: getfield fCurrentType : Lorg/apache/xerces/xs/XSTypeDefinition;
    //   28: invokeinterface getTypeCategory : ()S
    //   33: bipush #16
    //   35: if_icmpne -> 42
    //   38: iconst_1
    //   39: goto -> 43
    //   42: iconst_0
    //   43: istore #8
    //   45: aconst_null
    //   46: astore #9
    //   48: iconst_0
    //   49: istore #10
    //   51: aconst_null
    //   52: astore #11
    //   54: iload #8
    //   56: ifne -> 80
    //   59: aload_3
    //   60: invokevirtual getAttributeUses : ()Lorg/apache/xerces/xs/XSObjectList;
    //   63: astore #9
    //   65: aload #9
    //   67: invokeinterface getLength : ()I
    //   72: istore #10
    //   74: aload_3
    //   75: getfield fAttributeWC : Lorg/apache/xerces/impl/xs/XSWildcardDecl;
    //   78: astore #11
    //   80: iconst_0
    //   81: istore #12
    //   83: goto -> 706
    //   86: aload_2
    //   87: iload #12
    //   89: aload_0
    //   90: getfield fTempQName : Lorg/apache/xerces/xni/QName;
    //   93: invokeinterface getName : (ILorg/apache/xerces/xni/QName;)V
    //   98: aload_0
    //   99: getfield fAugPSVI : Z
    //   102: ifne -> 112
    //   105: aload_0
    //   106: getfield fIdConstraint : Z
    //   109: ifeq -> 179
    //   112: aload_2
    //   113: iload #12
    //   115: invokeinterface getAugmentations : (I)Lorg/apache/xerces/xni/Augmentations;
    //   120: astore #6
    //   122: aload #6
    //   124: ldc 'ATTRIBUTE_PSVI'
    //   126: invokeinterface getItem : (Ljava/lang/String;)Ljava/lang/Object;
    //   131: checkcast org/apache/xerces/impl/xs/AttributePSVImpl
    //   134: astore #7
    //   136: aload #7
    //   138: ifnull -> 149
    //   141: aload #7
    //   143: invokevirtual reset : ()V
    //   146: goto -> 170
    //   149: new org/apache/xerces/impl/xs/AttributePSVImpl
    //   152: dup
    //   153: invokespecial <init> : ()V
    //   156: astore #7
    //   158: aload #6
    //   160: ldc 'ATTRIBUTE_PSVI'
    //   162: aload #7
    //   164: invokeinterface putItem : (Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;
    //   169: pop
    //   170: aload #7
    //   172: aload_0
    //   173: getfield fValidationRoot : Ljava/lang/String;
    //   176: putfield fValidationContext : Ljava/lang/String;
    //   179: aload_0
    //   180: getfield fTempQName : Lorg/apache/xerces/xni/QName;
    //   183: getfield uri : Ljava/lang/String;
    //   186: getstatic org/apache/xerces/impl/xs/SchemaSymbols.URI_XSI : Ljava/lang/String;
    //   189: if_acmpne -> 297
    //   192: aconst_null
    //   193: astore #13
    //   195: aload_0
    //   196: getfield fTempQName : Lorg/apache/xerces/xni/QName;
    //   199: getfield localpart : Ljava/lang/String;
    //   202: getstatic org/apache/xerces/impl/xs/SchemaSymbols.XSI_TYPE : Ljava/lang/String;
    //   205: if_acmpne -> 216
    //   208: getstatic org/apache/xerces/impl/xs/XMLSchemaValidator.XSI_TYPE : Lorg/apache/xerces/impl/xs/XSAttributeDecl;
    //   211: astore #13
    //   213: goto -> 276
    //   216: aload_0
    //   217: getfield fTempQName : Lorg/apache/xerces/xni/QName;
    //   220: getfield localpart : Ljava/lang/String;
    //   223: getstatic org/apache/xerces/impl/xs/SchemaSymbols.XSI_NIL : Ljava/lang/String;
    //   226: if_acmpne -> 237
    //   229: getstatic org/apache/xerces/impl/xs/XMLSchemaValidator.XSI_NIL : Lorg/apache/xerces/impl/xs/XSAttributeDecl;
    //   232: astore #13
    //   234: goto -> 276
    //   237: aload_0
    //   238: getfield fTempQName : Lorg/apache/xerces/xni/QName;
    //   241: getfield localpart : Ljava/lang/String;
    //   244: getstatic org/apache/xerces/impl/xs/SchemaSymbols.XSI_SCHEMALOCATION : Ljava/lang/String;
    //   247: if_acmpne -> 258
    //   250: getstatic org/apache/xerces/impl/xs/XMLSchemaValidator.XSI_SCHEMALOCATION : Lorg/apache/xerces/impl/xs/XSAttributeDecl;
    //   253: astore #13
    //   255: goto -> 276
    //   258: aload_0
    //   259: getfield fTempQName : Lorg/apache/xerces/xni/QName;
    //   262: getfield localpart : Ljava/lang/String;
    //   265: getstatic org/apache/xerces/impl/xs/SchemaSymbols.XSI_NONAMESPACESCHEMALOCATION : Ljava/lang/String;
    //   268: if_acmpne -> 276
    //   271: getstatic org/apache/xerces/impl/xs/XMLSchemaValidator.XSI_NONAMESPACESCHEMALOCATION : Lorg/apache/xerces/impl/xs/XSAttributeDecl;
    //   274: astore #13
    //   276: aload #13
    //   278: ifnull -> 297
    //   281: aload_0
    //   282: aload_1
    //   283: aload_2
    //   284: iload #12
    //   286: aload #13
    //   288: aconst_null
    //   289: aload #7
    //   291: invokevirtual processOneAttribute : (Lorg/apache/xerces/xni/QName;Lorg/apache/xerces/xni/XMLAttributes;ILorg/apache/xerces/impl/xs/XSAttributeDecl;Lorg/apache/xerces/impl/xs/XSAttributeUseImpl;Lorg/apache/xerces/impl/xs/AttributePSVImpl;)V
    //   294: goto -> 703
    //   297: aload_0
    //   298: getfield fTempQName : Lorg/apache/xerces/xni/QName;
    //   301: getfield rawname : Ljava/lang/String;
    //   304: getstatic org/apache/xerces/util/XMLSymbols.PREFIX_XMLNS : Ljava/lang/String;
    //   307: if_acmpeq -> 703
    //   310: aload_0
    //   311: getfield fTempQName : Lorg/apache/xerces/xni/QName;
    //   314: getfield rawname : Ljava/lang/String;
    //   317: ldc 'xmlns:'
    //   319: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   322: ifeq -> 328
    //   325: goto -> 703
    //   328: iload #8
    //   330: ifeq -> 363
    //   333: aload_0
    //   334: ldc 'cvc-type.3.1.1'
    //   336: iconst_2
    //   337: anewarray java/lang/Object
    //   340: dup
    //   341: iconst_0
    //   342: aload_1
    //   343: getfield rawname : Ljava/lang/String;
    //   346: aastore
    //   347: dup
    //   348: iconst_1
    //   349: aload_0
    //   350: getfield fTempQName : Lorg/apache/xerces/xni/QName;
    //   353: getfield rawname : Ljava/lang/String;
    //   356: aastore
    //   357: invokevirtual reportSchemaError : (Ljava/lang/String;[Ljava/lang/Object;)V
    //   360: goto -> 703
    //   363: aconst_null
    //   364: astore #13
    //   366: iconst_0
    //   367: istore #15
    //   369: goto -> 432
    //   372: aload #9
    //   374: iload #15
    //   376: invokeinterface item : (I)Lorg/apache/xerces/xs/XSObject;
    //   381: checkcast org/apache/xerces/impl/xs/XSAttributeUseImpl
    //   384: astore #14
    //   386: aload #14
    //   388: getfield fAttrDecl : Lorg/apache/xerces/impl/xs/XSAttributeDecl;
    //   391: getfield fName : Ljava/lang/String;
    //   394: aload_0
    //   395: getfield fTempQName : Lorg/apache/xerces/xni/QName;
    //   398: getfield localpart : Ljava/lang/String;
    //   401: if_acmpne -> 429
    //   404: aload #14
    //   406: getfield fAttrDecl : Lorg/apache/xerces/impl/xs/XSAttributeDecl;
    //   409: getfield fTargetNamespace : Ljava/lang/String;
    //   412: aload_0
    //   413: getfield fTempQName : Lorg/apache/xerces/xni/QName;
    //   416: getfield uri : Ljava/lang/String;
    //   419: if_acmpne -> 429
    //   422: aload #14
    //   424: astore #13
    //   426: goto -> 439
    //   429: iinc #15, 1
    //   432: iload #15
    //   434: iload #10
    //   436: if_icmplt -> 372
    //   439: aload #13
    //   441: ifnonnull -> 502
    //   444: aload #11
    //   446: ifnull -> 464
    //   449: aload #11
    //   451: aload_0
    //   452: getfield fTempQName : Lorg/apache/xerces/xni/QName;
    //   455: getfield uri : Ljava/lang/String;
    //   458: invokevirtual allowNamespace : (Ljava/lang/String;)Z
    //   461: ifne -> 502
    //   464: aload_0
    //   465: ldc 'cvc-complex-type.3.2.2'
    //   467: iconst_2
    //   468: anewarray java/lang/Object
    //   471: dup
    //   472: iconst_0
    //   473: aload_1
    //   474: getfield rawname : Ljava/lang/String;
    //   477: aastore
    //   478: dup
    //   479: iconst_1
    //   480: aload_0
    //   481: getfield fTempQName : Lorg/apache/xerces/xni/QName;
    //   484: getfield rawname : Ljava/lang/String;
    //   487: aastore
    //   488: invokevirtual reportSchemaError : (Ljava/lang/String;[Ljava/lang/Object;)V
    //   491: aload_0
    //   492: aload_0
    //   493: getfield fElementDepth : I
    //   496: putfield fNFullValidationDepth : I
    //   499: goto -> 703
    //   502: aconst_null
    //   503: astore #16
    //   505: aload #13
    //   507: ifnull -> 520
    //   510: aload #13
    //   512: getfield fAttrDecl : Lorg/apache/xerces/impl/xs/XSAttributeDecl;
    //   515: astore #16
    //   517: goto -> 689
    //   520: aload #11
    //   522: getfield fProcessContents : S
    //   525: iconst_2
    //   526: if_icmpne -> 532
    //   529: goto -> 703
    //   532: aload_0
    //   533: bipush #6
    //   535: aload_0
    //   536: getfield fTempQName : Lorg/apache/xerces/xni/QName;
    //   539: getfield uri : Ljava/lang/String;
    //   542: aload_1
    //   543: aload_0
    //   544: getfield fTempQName : Lorg/apache/xerces/xni/QName;
    //   547: aload_2
    //   548: invokevirtual findSchemaGrammar : (SLjava/lang/String;Lorg/apache/xerces/xni/QName;Lorg/apache/xerces/xni/QName;Lorg/apache/xerces/xni/XMLAttributes;)Lorg/apache/xerces/impl/xs/SchemaGrammar;
    //   551: astore #17
    //   553: aload #17
    //   555: ifnull -> 572
    //   558: aload #17
    //   560: aload_0
    //   561: getfield fTempQName : Lorg/apache/xerces/xni/QName;
    //   564: getfield localpart : Ljava/lang/String;
    //   567: invokevirtual getGlobalAttributeDecl : (Ljava/lang/String;)Lorg/apache/xerces/impl/xs/XSAttributeDecl;
    //   570: astore #16
    //   572: aload #16
    //   574: ifnonnull -> 616
    //   577: aload #11
    //   579: getfield fProcessContents : S
    //   582: iconst_1
    //   583: if_icmpne -> 703
    //   586: aload_0
    //   587: ldc 'cvc-complex-type.3.2.2'
    //   589: iconst_2
    //   590: anewarray java/lang/Object
    //   593: dup
    //   594: iconst_0
    //   595: aload_1
    //   596: getfield rawname : Ljava/lang/String;
    //   599: aastore
    //   600: dup
    //   601: iconst_1
    //   602: aload_0
    //   603: getfield fTempQName : Lorg/apache/xerces/xni/QName;
    //   606: getfield rawname : Ljava/lang/String;
    //   609: aastore
    //   610: invokevirtual reportSchemaError : (Ljava/lang/String;[Ljava/lang/Object;)V
    //   613: goto -> 703
    //   616: aload #16
    //   618: getfield fType : Lorg/apache/xerces/impl/dv/XSSimpleType;
    //   621: invokeinterface getTypeCategory : ()S
    //   626: bipush #16
    //   628: if_icmpne -> 689
    //   631: aload #16
    //   633: getfield fType : Lorg/apache/xerces/impl/dv/XSSimpleType;
    //   636: invokeinterface isIDType : ()Z
    //   641: ifeq -> 689
    //   644: aload #4
    //   646: ifnull -> 682
    //   649: aload_0
    //   650: ldc 'cvc-complex-type.5.1'
    //   652: iconst_3
    //   653: anewarray java/lang/Object
    //   656: dup
    //   657: iconst_0
    //   658: aload_1
    //   659: getfield rawname : Ljava/lang/String;
    //   662: aastore
    //   663: dup
    //   664: iconst_1
    //   665: aload #16
    //   667: getfield fName : Ljava/lang/String;
    //   670: aastore
    //   671: dup
    //   672: iconst_2
    //   673: aload #4
    //   675: aastore
    //   676: invokevirtual reportSchemaError : (Ljava/lang/String;[Ljava/lang/Object;)V
    //   679: goto -> 689
    //   682: aload #16
    //   684: getfield fName : Ljava/lang/String;
    //   687: astore #4
    //   689: aload_0
    //   690: aload_1
    //   691: aload_2
    //   692: iload #12
    //   694: aload #16
    //   696: aload #13
    //   698: aload #7
    //   700: invokevirtual processOneAttribute : (Lorg/apache/xerces/xni/QName;Lorg/apache/xerces/xni/XMLAttributes;ILorg/apache/xerces/impl/xs/XSAttributeDecl;Lorg/apache/xerces/impl/xs/XSAttributeUseImpl;Lorg/apache/xerces/impl/xs/AttributePSVImpl;)V
    //   703: iinc #12, 1
    //   706: iload #12
    //   708: iload #5
    //   710: if_icmplt -> 86
    //   713: iload #8
    //   715: ifne -> 759
    //   718: aload_3
    //   719: getfield fIDAttrName : Ljava/lang/String;
    //   722: ifnull -> 759
    //   725: aload #4
    //   727: ifnull -> 759
    //   730: aload_0
    //   731: ldc 'cvc-complex-type.5.2'
    //   733: iconst_3
    //   734: anewarray java/lang/Object
    //   737: dup
    //   738: iconst_0
    //   739: aload_1
    //   740: getfield rawname : Ljava/lang/String;
    //   743: aastore
    //   744: dup
    //   745: iconst_1
    //   746: aload #4
    //   748: aastore
    //   749: dup
    //   750: iconst_2
    //   751: aload_3
    //   752: getfield fIDAttrName : Ljava/lang/String;
    //   755: aastore
    //   756: invokevirtual reportSchemaError : (Ljava/lang/String;[Ljava/lang/Object;)V
    //   759: return
  }
  
  void processOneAttribute(QName paramQName, XMLAttributes paramXMLAttributes, int paramInt, XSAttributeDecl paramXSAttributeDecl, XSAttributeUseImpl paramXSAttributeUseImpl, AttributePSVImpl paramAttributePSVImpl) {
    String str = paramXMLAttributes.getValue(paramInt);
    this.fXSIErrorReporter.pushContext();
    XSSimpleType xSSimpleType = paramXSAttributeDecl.fType;
    Object object = null;
    try {
      object = xSSimpleType.validate(str, (ValidationContext)this.fValidationState, this.fValidatedInfo);
      if (this.fNormalizeData)
        paramXMLAttributes.setValue(paramInt, this.fValidatedInfo.normalizedValue); 
      if (xSSimpleType.getVariety() == 1 && xSSimpleType.getPrimitiveKind() == 20) {
        QName qName = (QName)object;
        SchemaGrammar schemaGrammar = this.fGrammarBucket.getGrammar(qName.uri);
        if (schemaGrammar != null)
          this.fNotation = schemaGrammar.getGlobalNotationDecl(qName.localpart); 
      } 
    } catch (InvalidDatatypeValueException invalidDatatypeValueException) {
      reportSchemaError(invalidDatatypeValueException.getKey(), invalidDatatypeValueException.getArgs());
      reportSchemaError("cvc-attribute.3", new Object[] { paramQName.rawname, this.fTempQName.rawname, str, (xSSimpleType instanceof XSSimpleTypeDecl) ? ((XSSimpleTypeDecl)xSSimpleType).getTypeName() : xSSimpleType.getName() });
    } 
    if (object != null && paramXSAttributeDecl.getConstraintType() == 2 && (!ValidatedInfo.isComparable(this.fValidatedInfo, paramXSAttributeDecl.fDefault) || !object.equals(paramXSAttributeDecl.fDefault.actualValue)))
      reportSchemaError("cvc-attribute.4", new Object[] { paramQName.rawname, this.fTempQName.rawname, str, paramXSAttributeDecl.fDefault.stringValue() }); 
    if (object != null && paramXSAttributeUseImpl != null && paramXSAttributeUseImpl.fConstraintType == 2 && (!ValidatedInfo.isComparable(this.fValidatedInfo, paramXSAttributeUseImpl.fDefault) || !object.equals(paramXSAttributeUseImpl.fDefault.actualValue)))
      reportSchemaError("cvc-complex-type.3.1", new Object[] { paramQName.rawname, this.fTempQName.rawname, str, paramXSAttributeUseImpl.fDefault.stringValue() }); 
    if (this.fIdConstraint)
      paramAttributePSVImpl.fValue.copyFrom((XSValue)this.fValidatedInfo); 
    if (this.fAugPSVI) {
      paramAttributePSVImpl.fDeclaration = paramXSAttributeDecl;
      paramAttributePSVImpl.fTypeDecl = (XSTypeDefinition)xSSimpleType;
      paramAttributePSVImpl.fValue.copyFrom((XSValue)this.fValidatedInfo);
      paramAttributePSVImpl.fValidationAttempted = 2;
      this.fNNoneValidationDepth = this.fElementDepth;
      String[] arrayOfString = this.fXSIErrorReporter.mergeContext();
      paramAttributePSVImpl.fErrors = arrayOfString;
      paramAttributePSVImpl.fValidity = (arrayOfString == null) ? 2 : 1;
    } 
  }
  
  void addDefaultAttributes(QName paramQName, XMLAttributes paramXMLAttributes, XSAttributeGroupDecl paramXSAttributeGroupDecl) {
    XSObjectList xSObjectList = paramXSAttributeGroupDecl.getAttributeUses();
    int i = xSObjectList.getLength();
    for (byte b = 0; b < i; b++) {
      XSAttributeUseImpl xSAttributeUseImpl = (XSAttributeUseImpl)xSObjectList.item(b);
      XSAttributeDecl xSAttributeDecl = xSAttributeUseImpl.fAttrDecl;
      short s = xSAttributeUseImpl.fConstraintType;
      ValidatedInfo validatedInfo = xSAttributeUseImpl.fDefault;
      if (s == 0) {
        s = xSAttributeDecl.getConstraintType();
        validatedInfo = xSAttributeDecl.fDefault;
      } 
      boolean bool = (paramXMLAttributes.getValue(xSAttributeDecl.fTargetNamespace, xSAttributeDecl.fName) != null) ? true : false;
      if (xSAttributeUseImpl.fUse == 1 && !bool)
        reportSchemaError("cvc-complex-type.4", new Object[] { paramQName.rawname, xSAttributeDecl.fName }); 
      if (!bool && s != 0) {
        int j;
        QName qName = new QName(null, xSAttributeDecl.fName, xSAttributeDecl.fName, xSAttributeDecl.fTargetNamespace);
        String str = (validatedInfo != null) ? validatedInfo.stringValue() : "";
        if (paramXMLAttributes instanceof XMLAttributesImpl) {
          XMLAttributesImpl xMLAttributesImpl = (XMLAttributesImpl)paramXMLAttributes;
          j = xMLAttributesImpl.getLength();
          xMLAttributesImpl.addAttributeNS(qName, "CDATA", str);
        } else {
          j = paramXMLAttributes.addAttribute(qName, "CDATA", str);
        } 
        if (this.fAugPSVI) {
          Augmentations augmentations = paramXMLAttributes.getAugmentations(j);
          AttributePSVImpl attributePSVImpl = new AttributePSVImpl();
          augmentations.putItem("ATTRIBUTE_PSVI", attributePSVImpl);
          attributePSVImpl.fDeclaration = xSAttributeDecl;
          attributePSVImpl.fTypeDecl = (XSTypeDefinition)xSAttributeDecl.fType;
          attributePSVImpl.fValue.copyFrom((XSValue)validatedInfo);
          attributePSVImpl.fValidationContext = this.fValidationRoot;
          attributePSVImpl.fValidity = 2;
          attributePSVImpl.fValidationAttempted = 2;
          attributePSVImpl.fSpecified = true;
        } 
      } 
    } 
  }
  
  void processElementContent(QName paramQName) {
    if (this.fCurrentElemDecl != null && this.fCurrentElemDecl.fDefault != null && !this.fSawText && !this.fSubElement && !this.fNil) {
      String str = this.fCurrentElemDecl.fDefault.stringValue();
      int i = str.length();
      if (this.fNormalizedStr.ch == null || this.fNormalizedStr.ch.length < i)
        this.fNormalizedStr.ch = new char[i]; 
      str.getChars(0, i, this.fNormalizedStr.ch, 0);
      this.fNormalizedStr.offset = 0;
      this.fNormalizedStr.length = i;
      this.fDefaultValue = this.fNormalizedStr;
    } 
    this.fValidatedInfo.normalizedValue = null;
    if (this.fNil && (this.fSubElement || this.fSawText))
      reportSchemaError("cvc-elt.3.2.1", new Object[] { paramQName.rawname, SchemaSymbols.URI_XSI + "," + SchemaSymbols.XSI_NIL }); 
    this.fValidatedInfo.reset();
    if (this.fCurrentElemDecl != null && this.fCurrentElemDecl.getConstraintType() != 0 && !this.fSubElement && !this.fSawText && !this.fNil) {
      if (this.fCurrentType != this.fCurrentElemDecl.fType && XSConstraints.ElementDefaultValidImmediate(this.fCurrentType, this.fCurrentElemDecl.fDefault.stringValue(), (ValidationContext)this.fState4XsiType, null) == null)
        reportSchemaError("cvc-elt.5.1.1", new Object[] { paramQName.rawname, this.fCurrentType.getName(), this.fCurrentElemDecl.fDefault.stringValue() }); 
      elementLocallyValidType(paramQName, this.fCurrentElemDecl.fDefault.stringValue());
    } else {
      Object object = elementLocallyValidType(paramQName, this.fBuffer);
      if (this.fCurrentElemDecl != null && this.fCurrentElemDecl.getConstraintType() == 2 && !this.fNil) {
        String str = this.fBuffer.toString();
        if (this.fSubElement)
          reportSchemaError("cvc-elt.5.2.2.1", new Object[] { paramQName.rawname }); 
        if (this.fCurrentType.getTypeCategory() == 15) {
          XSComplexTypeDecl xSComplexTypeDecl = (XSComplexTypeDecl)this.fCurrentType;
          if (xSComplexTypeDecl.fContentType == 3) {
            if (!this.fCurrentElemDecl.fDefault.normalizedValue.equals(str))
              reportSchemaError("cvc-elt.5.2.2.2.1", new Object[] { paramQName.rawname, str, this.fCurrentElemDecl.fDefault.normalizedValue }); 
          } else if (xSComplexTypeDecl.fContentType == 1 && object != null && (!ValidatedInfo.isComparable(this.fValidatedInfo, this.fCurrentElemDecl.fDefault) || !object.equals(this.fCurrentElemDecl.fDefault.actualValue))) {
            reportSchemaError("cvc-elt.5.2.2.2.2", new Object[] { paramQName.rawname, str, this.fCurrentElemDecl.fDefault.stringValue() });
          } 
        } else if (this.fCurrentType.getTypeCategory() == 16 && object != null && (!ValidatedInfo.isComparable(this.fValidatedInfo, this.fCurrentElemDecl.fDefault) || !object.equals(this.fCurrentElemDecl.fDefault.actualValue))) {
          reportSchemaError("cvc-elt.5.2.2.2.2", new Object[] { paramQName.rawname, str, this.fCurrentElemDecl.fDefault.stringValue() });
        } 
      } 
    } 
    if (this.fDefaultValue == null && this.fNormalizeData && this.fDocumentHandler != null && this.fUnionType) {
      String str = this.fValidatedInfo.normalizedValue;
      if (str == null)
        str = this.fBuffer.toString(); 
      int i = str.length();
      if (this.fNormalizedStr.ch == null || this.fNormalizedStr.ch.length < i)
        this.fNormalizedStr.ch = new char[i]; 
      str.getChars(0, i, this.fNormalizedStr.ch, 0);
      this.fNormalizedStr.offset = 0;
      this.fNormalizedStr.length = i;
      this.fDocumentHandler.characters(this.fNormalizedStr, null);
    } 
  }
  
  Object elementLocallyValidType(QName paramQName, Object paramObject) {
    if (this.fCurrentType == null)
      return null; 
    Object object = null;
    if (this.fCurrentType.getTypeCategory() == 16) {
      if (this.fSubElement)
        reportSchemaError("cvc-type.3.1.2", new Object[] { paramQName.rawname }); 
      if (!this.fNil) {
        XSSimpleType xSSimpleType = (XSSimpleType)this.fCurrentType;
        try {
          if (!this.fNormalizeData || this.fUnionType)
            this.fValidationState.setNormalizationRequired(true); 
          object = xSSimpleType.validate(paramObject, (ValidationContext)this.fValidationState, this.fValidatedInfo);
        } catch (InvalidDatatypeValueException invalidDatatypeValueException) {
          reportSchemaError(invalidDatatypeValueException.getKey(), invalidDatatypeValueException.getArgs());
          reportSchemaError("cvc-type.3.1.3", new Object[] { paramQName.rawname, paramObject });
        } 
      } 
    } else {
      object = elementLocallyValidComplexType(paramQName, paramObject);
    } 
    return object;
  }
  
  Object elementLocallyValidComplexType(QName paramQName, Object paramObject) {
    Object object = null;
    XSComplexTypeDecl xSComplexTypeDecl = (XSComplexTypeDecl)this.fCurrentType;
    if (!this.fNil) {
      if (xSComplexTypeDecl.fContentType == 0 && (this.fSubElement || this.fSawText)) {
        reportSchemaError("cvc-complex-type.2.1", new Object[] { paramQName.rawname });
      } else if (xSComplexTypeDecl.fContentType == 1) {
        if (this.fSubElement)
          reportSchemaError("cvc-complex-type.2.2", new Object[] { paramQName.rawname }); 
        XSSimpleType xSSimpleType = xSComplexTypeDecl.fXSSimpleType;
        try {
          if (!this.fNormalizeData || this.fUnionType)
            this.fValidationState.setNormalizationRequired(true); 
          object = xSSimpleType.validate(paramObject, (ValidationContext)this.fValidationState, this.fValidatedInfo);
        } catch (InvalidDatatypeValueException invalidDatatypeValueException) {
          reportSchemaError(invalidDatatypeValueException.getKey(), invalidDatatypeValueException.getArgs());
          reportSchemaError("cvc-complex-type.2.2", new Object[] { paramQName.rawname });
        } 
      } else if (xSComplexTypeDecl.fContentType == 2 && this.fSawCharacters) {
        reportSchemaError("cvc-complex-type.2.3", new Object[] { paramQName.rawname });
      } 
      if ((xSComplexTypeDecl.fContentType == 2 || xSComplexTypeDecl.fContentType == 3) && this.fCurrCMState[0] >= 0 && !this.fCurrentCM.endContentModel(this.fCurrCMState)) {
        String str = expectedStr(this.fCurrentCM.whatCanGoHere(this.fCurrCMState));
        int[] arrayOfInt = this.fCurrentCM.occurenceInfo(this.fCurrCMState);
        if (arrayOfInt != null) {
          int i = arrayOfInt[0];
          int j = arrayOfInt[2];
          if (j < i) {
            int k = i - j;
            if (k > 1) {
              reportSchemaError("cvc-complex-type.2.4.j", new Object[] { paramQName.rawname, this.fCurrentCM.getTermName(arrayOfInt[3]), Integer.toString(i), Integer.toString(k) });
            } else {
              reportSchemaError("cvc-complex-type.2.4.i", new Object[] { paramQName.rawname, this.fCurrentCM.getTermName(arrayOfInt[3]), Integer.toString(i) });
            } 
          } else {
            reportSchemaError("cvc-complex-type.2.4.b", new Object[] { paramQName.rawname, str });
          } 
        } else {
          reportSchemaError("cvc-complex-type.2.4.b", new Object[] { paramQName.rawname, str });
        } 
      } 
    } 
    return object;
  }
  
  void processRootTypeQName(QName paramQName) {
    String str = paramQName.getNamespaceURI();
    if (str != null && str.equals(""))
      str = null; 
    if (SchemaSymbols.URI_SCHEMAFORSCHEMA.equals(str)) {
      this.fCurrentType = SchemaGrammar.SG_SchemaNS.getGlobalTypeDecl(paramQName.getLocalPart());
    } else {
      SchemaGrammar schemaGrammar = findSchemaGrammar((short)5, str, null, null, null);
      if (schemaGrammar != null)
        this.fCurrentType = schemaGrammar.getGlobalTypeDecl(paramQName.getLocalPart()); 
    } 
    if (this.fCurrentType == null) {
      String str1 = paramQName.getPrefix().equals("") ? paramQName.getLocalPart() : (paramQName.getPrefix() + ":" + paramQName.getLocalPart());
      reportSchemaError("cvc-type.1", new Object[] { str1 });
    } 
  }
  
  void processRootElementDeclQName(QName paramQName, QName paramQName1) {
    String str = paramQName.getNamespaceURI();
    if (str != null && str.equals(""))
      str = null; 
    SchemaGrammar schemaGrammar = findSchemaGrammar((short)5, str, null, null, null);
    if (schemaGrammar != null)
      this.fCurrentElemDecl = schemaGrammar.getGlobalElementDecl(paramQName.getLocalPart()); 
    if (this.fCurrentElemDecl == null) {
      String str1 = paramQName.getPrefix().equals("") ? paramQName.getLocalPart() : (paramQName.getPrefix() + ":" + paramQName.getLocalPart());
      reportSchemaError("cvc-elt.1.a", new Object[] { str1 });
    } else {
      checkElementMatchesRootElementDecl(this.fCurrentElemDecl, paramQName1);
    } 
  }
  
  void checkElementMatchesRootElementDecl(XSElementDecl paramXSElementDecl, QName paramQName) {
    if (paramQName.localpart != paramXSElementDecl.fName || paramQName.uri != paramXSElementDecl.fTargetNamespace)
      reportSchemaError("cvc-elt.1.b", new Object[] { paramQName.rawname, paramXSElementDecl.fName }); 
  }
  
  void reportSchemaError(String paramString, Object[] paramArrayOfObject) {
    if (this.fDoValidation)
      this.fXSIErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", paramString, paramArrayOfObject, (short)1); 
  }
  
  private String expectedStr(Vector paramVector) {
    StringBuffer stringBuffer = new StringBuffer("{");
    int i = paramVector.size();
    for (byte b = 0; b < i; b++) {
      if (b > 0)
        stringBuffer.append(", "); 
      stringBuffer.append(paramVector.elementAt(b).toString());
    } 
    stringBuffer.append('}');
    return stringBuffer.toString();
  }
  
  protected static final class ShortVector {
    private int fLength;
    
    private short[] fData;
    
    public ShortVector() {}
    
    public ShortVector(int param1Int) {
      this.fData = new short[param1Int];
    }
    
    public int length() {
      return this.fLength;
    }
    
    public void add(short param1Short) {
      ensureCapacity(this.fLength + 1);
      this.fData[this.fLength++] = param1Short;
    }
    
    public short valueAt(int param1Int) {
      return this.fData[param1Int];
    }
    
    public void clear() {
      this.fLength = 0;
    }
    
    public boolean contains(short param1Short) {
      for (byte b = 0; b < this.fLength; b++) {
        if (this.fData[b] == param1Short)
          return true; 
      } 
      return false;
    }
    
    private void ensureCapacity(int param1Int) {
      if (this.fData == null) {
        this.fData = new short[8];
      } else if (this.fData.length <= param1Int) {
        short[] arrayOfShort = new short[this.fData.length * 2];
        System.arraycopy(this.fData, 0, arrayOfShort, 0, this.fData.length);
        this.fData = arrayOfShort;
      } 
    }
  }
  
  protected static final class LocalIDKey {
    public IdentityConstraint fId;
    
    public int fDepth;
    
    public LocalIDKey() {}
    
    public LocalIDKey(IdentityConstraint param1IdentityConstraint, int param1Int) {
      this.fId = param1IdentityConstraint;
      this.fDepth = param1Int;
    }
    
    public int hashCode() {
      return this.fId.hashCode() + this.fDepth;
    }
    
    public boolean equals(Object param1Object) {
      if (param1Object instanceof LocalIDKey) {
        LocalIDKey localIDKey = (LocalIDKey)param1Object;
        return (localIDKey.fId == this.fId && localIDKey.fDepth == this.fDepth);
      } 
      return false;
    }
  }
  
  protected class ValueStoreCache {
    final XMLSchemaValidator.LocalIDKey fLocalId;
    
    protected final ArrayList fValueStores;
    
    protected final HashMap fIdentityConstraint2ValueStoreMap;
    
    protected final Stack fGlobalMapStack;
    
    protected final HashMap fGlobalIDConstraintMap;
    
    private final XMLSchemaValidator this$0;
    
    public ValueStoreCache(XMLSchemaValidator this$0) {
      this.this$0 = this$0;
      this.fLocalId = new XMLSchemaValidator.LocalIDKey();
      this.fValueStores = new ArrayList();
      this.fIdentityConstraint2ValueStoreMap = new HashMap();
      this.fGlobalMapStack = new Stack();
      this.fGlobalIDConstraintMap = new HashMap();
    }
    
    public void startDocument() {
      this.fValueStores.clear();
      this.fIdentityConstraint2ValueStoreMap.clear();
      this.fGlobalIDConstraintMap.clear();
      this.fGlobalMapStack.removeAllElements();
    }
    
    public void startElement() {
      if (this.fGlobalIDConstraintMap.size() > 0) {
        this.fGlobalMapStack.push(this.fGlobalIDConstraintMap.clone());
      } else {
        this.fGlobalMapStack.push((Object)null);
      } 
      this.fGlobalIDConstraintMap.clear();
    }
    
    public void endElement() {
      if (this.fGlobalMapStack.isEmpty())
        return; 
      HashMap hashMap = this.fGlobalMapStack.pop();
      if (hashMap == null)
        return; 
      for (Map.Entry entry : hashMap.entrySet()) {
        IdentityConstraint identityConstraint = (IdentityConstraint)entry.getKey();
        XMLSchemaValidator.ValueStoreBase valueStoreBase = (XMLSchemaValidator.ValueStoreBase)entry.getValue();
        if (valueStoreBase != null) {
          XMLSchemaValidator.ValueStoreBase valueStoreBase1 = (XMLSchemaValidator.ValueStoreBase)this.fGlobalIDConstraintMap.get(identityConstraint);
          if (valueStoreBase1 == null) {
            this.fGlobalIDConstraintMap.put(identityConstraint, valueStoreBase);
            continue;
          } 
          if (valueStoreBase1 != valueStoreBase)
            valueStoreBase1.append(valueStoreBase); 
        } 
      } 
    }
    
    public void initValueStoresFor(XSElementDecl param1XSElementDecl, FieldActivator param1FieldActivator) {
      IdentityConstraint[] arrayOfIdentityConstraint = param1XSElementDecl.fIDConstraints;
      int i = param1XSElementDecl.fIDCPos;
      for (byte b = 0; b < i; b++) {
        UniqueOrKey uniqueOrKey1;
        XMLSchemaValidator.LocalIDKey localIDKey;
        XMLSchemaValidator.UniqueValueStore uniqueValueStore;
        UniqueOrKey uniqueOrKey2;
        XMLSchemaValidator.KeyValueStore keyValueStore;
        KeyRef keyRef;
        XMLSchemaValidator.KeyRefValueStore keyRefValueStore;
        switch (arrayOfIdentityConstraint[b].getCategory()) {
          case 3:
            uniqueOrKey1 = (UniqueOrKey)arrayOfIdentityConstraint[b];
            localIDKey = new XMLSchemaValidator.LocalIDKey((IdentityConstraint)uniqueOrKey1, this.this$0.fElementDepth);
            uniqueValueStore = (XMLSchemaValidator.UniqueValueStore)this.fIdentityConstraint2ValueStoreMap.get(localIDKey);
            if (uniqueValueStore == null) {
              uniqueValueStore = new XMLSchemaValidator.UniqueValueStore(this.this$0, uniqueOrKey1);
              this.fIdentityConstraint2ValueStoreMap.put(localIDKey, uniqueValueStore);
            } else {
              uniqueValueStore.clear();
            } 
            this.fValueStores.add(uniqueValueStore);
            this.this$0.activateSelectorFor(arrayOfIdentityConstraint[b]);
            break;
          case 1:
            uniqueOrKey2 = (UniqueOrKey)arrayOfIdentityConstraint[b];
            localIDKey = new XMLSchemaValidator.LocalIDKey((IdentityConstraint)uniqueOrKey2, this.this$0.fElementDepth);
            keyValueStore = (XMLSchemaValidator.KeyValueStore)this.fIdentityConstraint2ValueStoreMap.get(localIDKey);
            if (keyValueStore == null) {
              keyValueStore = new XMLSchemaValidator.KeyValueStore(this.this$0, uniqueOrKey2);
              this.fIdentityConstraint2ValueStoreMap.put(localIDKey, keyValueStore);
            } else {
              keyValueStore.clear();
            } 
            this.fValueStores.add(keyValueStore);
            this.this$0.activateSelectorFor(arrayOfIdentityConstraint[b]);
            break;
          case 2:
            keyRef = (KeyRef)arrayOfIdentityConstraint[b];
            localIDKey = new XMLSchemaValidator.LocalIDKey((IdentityConstraint)keyRef, this.this$0.fElementDepth);
            keyRefValueStore = (XMLSchemaValidator.KeyRefValueStore)this.fIdentityConstraint2ValueStoreMap.get(localIDKey);
            if (keyRefValueStore == null) {
              keyRefValueStore = new XMLSchemaValidator.KeyRefValueStore(this.this$0, keyRef, null);
              this.fIdentityConstraint2ValueStoreMap.put(localIDKey, keyRefValueStore);
            } else {
              keyRefValueStore.clear();
            } 
            this.fValueStores.add(keyRefValueStore);
            this.this$0.activateSelectorFor(arrayOfIdentityConstraint[b]);
            break;
        } 
      } 
    }
    
    public XMLSchemaValidator.ValueStoreBase getValueStoreFor(IdentityConstraint param1IdentityConstraint, int param1Int) {
      this.fLocalId.fDepth = param1Int;
      this.fLocalId.fId = param1IdentityConstraint;
      return (XMLSchemaValidator.ValueStoreBase)this.fIdentityConstraint2ValueStoreMap.get(this.fLocalId);
    }
    
    public XMLSchemaValidator.ValueStoreBase getGlobalValueStoreFor(IdentityConstraint param1IdentityConstraint) {
      return (XMLSchemaValidator.ValueStoreBase)this.fGlobalIDConstraintMap.get(param1IdentityConstraint);
    }
    
    public void transplant(IdentityConstraint param1IdentityConstraint, int param1Int) {
      this.fLocalId.fDepth = param1Int;
      this.fLocalId.fId = param1IdentityConstraint;
      XMLSchemaValidator.ValueStoreBase valueStoreBase1 = (XMLSchemaValidator.ValueStoreBase)this.fIdentityConstraint2ValueStoreMap.get(this.fLocalId);
      if (param1IdentityConstraint.getCategory() == 2)
        return; 
      XMLSchemaValidator.ValueStoreBase valueStoreBase2 = (XMLSchemaValidator.ValueStoreBase)this.fGlobalIDConstraintMap.get(param1IdentityConstraint);
      if (valueStoreBase2 != null) {
        valueStoreBase2.append(valueStoreBase1);
        this.fGlobalIDConstraintMap.put(param1IdentityConstraint, valueStoreBase2);
      } else {
        this.fGlobalIDConstraintMap.put(param1IdentityConstraint, valueStoreBase1);
      } 
    }
    
    public void endDocument() {
      int i = this.fValueStores.size();
      for (byte b = 0; b < i; b++) {
        XMLSchemaValidator.ValueStoreBase valueStoreBase = this.fValueStores.get(b);
        valueStoreBase.endDocument();
      } 
    }
    
    public String toString() {
      String str = super.toString();
      int i = str.lastIndexOf('$');
      if (i != -1)
        return str.substring(i + 1); 
      int j = str.lastIndexOf('.');
      return (j != -1) ? str.substring(j + 1) : str;
    }
  }
  
  protected class KeyRefValueStore extends ValueStoreBase {
    protected XMLSchemaValidator.ValueStoreBase fKeyValueStore;
    
    private final XMLSchemaValidator this$0;
    
    public KeyRefValueStore(XMLSchemaValidator this$0, KeyRef param1KeyRef, XMLSchemaValidator.KeyValueStore param1KeyValueStore) {
      super(this$0, (IdentityConstraint)param1KeyRef);
      this.this$0 = this$0;
      this.fKeyValueStore = param1KeyValueStore;
    }
    
    public void endDocumentFragment() {
      super.endDocumentFragment();
      this.fKeyValueStore = (XMLSchemaValidator.ValueStoreBase)this.this$0.fValueStoreCache.fGlobalIDConstraintMap.get(((KeyRef)this.fIdentityConstraint).getKey());
      if (this.fKeyValueStore == null) {
        String str1 = "KeyRefOutOfScope";
        String str2 = this.fIdentityConstraint.toString();
        this.this$0.reportSchemaError(str1, new Object[] { str2 });
        return;
      } 
      int i = this.fKeyValueStore.contains(this);
      if (i != -1) {
        String str1 = "KeyNotFound";
        String str2 = toString(this.fValues, i, this.fFieldCount);
        String str3 = this.fIdentityConstraint.getElementName();
        String str4 = this.fIdentityConstraint.getName();
        this.this$0.reportSchemaError(str1, new Object[] { str4, str2, str3 });
      } 
    }
    
    public void endDocument() {
      super.endDocument();
    }
  }
  
  protected class KeyValueStore extends ValueStoreBase {
    private final XMLSchemaValidator this$0;
    
    public KeyValueStore(XMLSchemaValidator this$0, UniqueOrKey param1UniqueOrKey) {
      super(this$0, (IdentityConstraint)param1UniqueOrKey);
      this.this$0 = this$0;
    }
    
    protected void checkDuplicateValues() {
      if (contains()) {
        String str1 = "DuplicateKey";
        String str2 = toString(this.fLocalValues);
        String str3 = this.fIdentityConstraint.getElementName();
        String str4 = this.fIdentityConstraint.getIdentityConstraintName();
        this.this$0.reportSchemaError(str1, new Object[] { str2, str3, str4 });
      } 
    }
  }
  
  protected class UniqueValueStore extends ValueStoreBase {
    private final XMLSchemaValidator this$0;
    
    public UniqueValueStore(XMLSchemaValidator this$0, UniqueOrKey param1UniqueOrKey) {
      super(this$0, (IdentityConstraint)param1UniqueOrKey);
      this.this$0 = this$0;
    }
    
    protected void checkDuplicateValues() {
      if (contains()) {
        String str1 = "DuplicateUnique";
        String str2 = toString(this.fLocalValues);
        String str3 = this.fIdentityConstraint.getElementName();
        String str4 = this.fIdentityConstraint.getIdentityConstraintName();
        this.this$0.reportSchemaError(str1, new Object[] { str2, str3, str4 });
      } 
    }
  }
  
  protected abstract class ValueStoreBase implements ValueStore {
    protected IdentityConstraint fIdentityConstraint;
    
    protected int fFieldCount;
    
    protected Field[] fFields;
    
    protected Object[] fLocalValues;
    
    protected short[] fLocalValueTypes;
    
    protected ShortList[] fLocalItemValueTypes;
    
    protected int fValuesCount;
    
    public final Vector fValues;
    
    public XMLSchemaValidator.ShortVector fValueTypes;
    
    public Vector fItemValueTypes;
    
    private boolean fUseValueTypeVector;
    
    private int fValueTypesLength;
    
    private short fValueType;
    
    private boolean fUseItemValueTypeVector;
    
    private int fItemValueTypesLength;
    
    private ShortList fItemValueType;
    
    final StringBuffer fTempBuffer;
    
    private final XMLSchemaValidator this$0;
    
    protected ValueStoreBase(XMLSchemaValidator this$0, IdentityConstraint param1IdentityConstraint) {
      this.this$0 = this$0;
      this.fFieldCount = 0;
      this.fFields = null;
      this.fLocalValues = null;
      this.fLocalValueTypes = null;
      this.fLocalItemValueTypes = null;
      this.fValues = new Vector();
      this.fValueTypes = null;
      this.fItemValueTypes = null;
      this.fUseValueTypeVector = false;
      this.fValueTypesLength = 0;
      this.fValueType = 0;
      this.fUseItemValueTypeVector = false;
      this.fItemValueTypesLength = 0;
      this.fItemValueType = null;
      this.fTempBuffer = new StringBuffer();
      this.fIdentityConstraint = param1IdentityConstraint;
      this.fFieldCount = this.fIdentityConstraint.getFieldCount();
      this.fFields = new Field[this.fFieldCount];
      this.fLocalValues = new Object[this.fFieldCount];
      this.fLocalValueTypes = new short[this.fFieldCount];
      this.fLocalItemValueTypes = new ShortList[this.fFieldCount];
      for (byte b = 0; b < this.fFieldCount; b++)
        this.fFields[b] = this.fIdentityConstraint.getFieldAt(b); 
    }
    
    public void clear() {
      this.fValuesCount = 0;
      this.fUseValueTypeVector = false;
      this.fValueTypesLength = 0;
      this.fValueType = 0;
      this.fUseItemValueTypeVector = false;
      this.fItemValueTypesLength = 0;
      this.fItemValueType = null;
      this.fValues.setSize(0);
      if (this.fValueTypes != null)
        this.fValueTypes.clear(); 
      if (this.fItemValueTypes != null)
        this.fItemValueTypes.setSize(0); 
    }
    
    public void append(ValueStoreBase param1ValueStoreBase) {
      for (byte b = 0; b < param1ValueStoreBase.fValues.size(); b++)
        this.fValues.addElement(param1ValueStoreBase.fValues.elementAt(b)); 
    }
    
    public void startValueScope() {
      this.fValuesCount = 0;
      for (byte b = 0; b < this.fFieldCount; b++) {
        this.fLocalValues[b] = null;
        this.fLocalValueTypes[b] = 0;
        this.fLocalItemValueTypes[b] = null;
      } 
    }
    
    public void endValueScope() {
      if (this.fValuesCount == 0) {
        if (this.fIdentityConstraint.getCategory() == 1) {
          String str1 = "AbsentKeyValue";
          String str2 = this.fIdentityConstraint.getElementName();
          String str3 = this.fIdentityConstraint.getIdentityConstraintName();
          this.this$0.reportSchemaError(str1, new Object[] { str2, str3 });
        } 
        return;
      } 
      if (this.fValuesCount != this.fFieldCount) {
        if (this.fIdentityConstraint.getCategory() == 1) {
          String str1 = "KeyNotEnoughValues";
          UniqueOrKey uniqueOrKey = (UniqueOrKey)this.fIdentityConstraint;
          String str2 = this.fIdentityConstraint.getElementName();
          String str3 = uniqueOrKey.getIdentityConstraintName();
          this.this$0.reportSchemaError(str1, new Object[] { str2, str3 });
        } 
        return;
      } 
    }
    
    public void endDocumentFragment() {}
    
    public void endDocument() {}
    
    public void reportError(String param1String, Object[] param1ArrayOfObject) {
      this.this$0.reportSchemaError(param1String, param1ArrayOfObject);
    }
    
    public void addValue(Field param1Field, boolean param1Boolean, Object param1Object, short param1Short, ShortList param1ShortList) {
      int i;
      for (i = this.fFieldCount - 1; i > -1 && this.fFields[i] != param1Field; i--);
      if (i == -1) {
        String str1 = "UnknownField";
        String str2 = this.fIdentityConstraint.getElementName();
        String str3 = this.fIdentityConstraint.getIdentityConstraintName();
        this.this$0.reportSchemaError(str1, new Object[] { param1Field.toString(), str2, str3 });
        return;
      } 
      if (!param1Boolean) {
        String str1 = "FieldMultipleMatch";
        String str2 = this.fIdentityConstraint.getIdentityConstraintName();
        this.this$0.reportSchemaError(str1, new Object[] { param1Field.toString(), str2 });
      } else {
        this.fValuesCount++;
      } 
      this.fLocalValues[i] = param1Object;
      this.fLocalValueTypes[i] = param1Short;
      this.fLocalItemValueTypes[i] = param1ShortList;
      if (this.fValuesCount == this.fFieldCount) {
        checkDuplicateValues();
        for (i = 0; i < this.fFieldCount; i++) {
          this.fValues.addElement(this.fLocalValues[i]);
          addValueType(this.fLocalValueTypes[i]);
          addItemValueType(this.fLocalItemValueTypes[i]);
        } 
      } 
    }
    
    public boolean contains() {
      int i = 0;
      int j = this.fValues.size();
      for (int k = 0; k < j; k = i) {
        i = k + this.fFieldCount;
        for (byte b = 0;; b++) {
          if (b >= this.fFieldCount)
            return true; 
          Object object = this.fLocalValues[b];
          Object object1 = this.fValues.elementAt(k);
          short s1 = this.fLocalValueTypes[b];
          short s2 = getValueTypeAt(k);
          if (object == null || object1 == null || s1 != s2 || !object.equals(object1))
            break; 
          if (s1 == 44 || s1 == 43) {
            ShortList shortList1 = this.fLocalItemValueTypes[b];
            ShortList shortList2 = getItemValueTypeAt(k);
            if (shortList1 == null || shortList2 == null || !shortList1.equals(shortList2))
              break; 
          } 
          k++;
        } 
      } 
      return false;
    }
    
    public int contains(ValueStoreBase param1ValueStoreBase) {
      Vector vector = param1ValueStoreBase.fValues;
      int i = vector.size();
      if (this.fFieldCount <= 1) {
        for (byte b = 0; b < i; b++) {
          short s = param1ValueStoreBase.getValueTypeAt(b);
          if (!valueTypeContains(s) || !this.fValues.contains(vector.elementAt(b)))
            return b; 
          if (s == 44 || s == 43) {
            ShortList shortList = param1ValueStoreBase.getItemValueTypeAt(b);
            if (!itemValueTypeContains(shortList))
              return b; 
          } 
        } 
      } else {
        int j = this.fValues.size();
        for (int k = 0; k < i; k += this.fFieldCount) {
          int m = 0;
          while (true) {
            if (m >= j)
              return k; 
            for (byte b = 0; b < this.fFieldCount; b++) {
              Object object1 = vector.elementAt(k + b);
              Object object2 = this.fValues.elementAt(m + b);
              short s1 = param1ValueStoreBase.getValueTypeAt(k + b);
              short s2 = getValueTypeAt(m + b);
              if (object1 != object2)
                if (s1 == s2) {
                  if (object1 != null) {
                    if (!object1.equals(object2))
                      // Byte code: goto -> 288 
                  } else {
                    // Byte code: goto -> 288
                  } 
                } else {
                  // Byte code: goto -> 288
                }  
              if (s1 == 44 || s1 == 43) {
                ShortList shortList1 = param1ValueStoreBase.getItemValueTypeAt(k + b);
                ShortList shortList2 = getItemValueTypeAt(m + b);
                if (shortList1 != null) {
                  if (shortList2 != null) {
                    if (!shortList1.equals(shortList2)) {
                      m += this.fFieldCount;
                      continue;
                    } 
                  } else {
                    // Byte code: goto -> 288
                  } 
                } else {
                  // Byte code: goto -> 288
                } 
              } 
            } 
            break;
          } 
        } 
      } 
      return -1;
    }
    
    protected void checkDuplicateValues() {}
    
    protected String toString(Object[] param1ArrayOfObject) {
      int i = param1ArrayOfObject.length;
      if (i == 0)
        return ""; 
      this.fTempBuffer.setLength(0);
      for (byte b = 0; b < i; b++) {
        if (b > 0)
          this.fTempBuffer.append(','); 
        this.fTempBuffer.append(param1ArrayOfObject[b]);
      } 
      return this.fTempBuffer.toString();
    }
    
    protected String toString(Vector param1Vector, int param1Int1, int param1Int2) {
      if (param1Int2 == 0)
        return ""; 
      if (param1Int2 == 1)
        return String.valueOf(param1Vector.elementAt(param1Int1)); 
      StringBuffer stringBuffer = new StringBuffer();
      for (byte b = 0; b < param1Int2; b++) {
        if (b > 0)
          stringBuffer.append(','); 
        stringBuffer.append(param1Vector.elementAt(param1Int1 + b));
      } 
      return stringBuffer.toString();
    }
    
    public String toString() {
      String str = super.toString();
      int i = str.lastIndexOf('$');
      if (i != -1)
        str = str.substring(i + 1); 
      int j = str.lastIndexOf('.');
      if (j != -1)
        str = str.substring(j + 1); 
      return str + '[' + this.fIdentityConstraint + ']';
    }
    
    private void addValueType(short param1Short) {
      if (this.fUseValueTypeVector) {
        this.fValueTypes.add(param1Short);
      } else if (this.fValueTypesLength++ == 0) {
        this.fValueType = param1Short;
      } else if (this.fValueType != param1Short) {
        this.fUseValueTypeVector = true;
        if (this.fValueTypes == null)
          this.fValueTypes = new XMLSchemaValidator.ShortVector(this.fValueTypesLength * 2); 
        for (byte b = 1; b < this.fValueTypesLength; b++)
          this.fValueTypes.add(this.fValueType); 
        this.fValueTypes.add(param1Short);
      } 
    }
    
    private short getValueTypeAt(int param1Int) {
      return this.fUseValueTypeVector ? this.fValueTypes.valueAt(param1Int) : this.fValueType;
    }
    
    private boolean valueTypeContains(short param1Short) {
      return this.fUseValueTypeVector ? this.fValueTypes.contains(param1Short) : ((this.fValueType == param1Short));
    }
    
    private void addItemValueType(ShortList param1ShortList) {
      if (this.fUseItemValueTypeVector) {
        this.fItemValueTypes.add(param1ShortList);
      } else if (this.fItemValueTypesLength++ == 0) {
        this.fItemValueType = param1ShortList;
      } else if (this.fItemValueType != param1ShortList && (this.fItemValueType == null || !this.fItemValueType.equals(param1ShortList))) {
        this.fUseItemValueTypeVector = true;
        if (this.fItemValueTypes == null)
          this.fItemValueTypes = new Vector(this.fItemValueTypesLength * 2); 
        for (byte b = 1; b < this.fItemValueTypesLength; b++)
          this.fItemValueTypes.add(this.fItemValueType); 
        this.fItemValueTypes.add(param1ShortList);
      } 
    }
    
    private ShortList getItemValueTypeAt(int param1Int) {
      return this.fUseItemValueTypeVector ? this.fItemValueTypes.elementAt(param1Int) : this.fItemValueType;
    }
    
    private boolean itemValueTypeContains(ShortList param1ShortList) {
      return this.fUseItemValueTypeVector ? this.fItemValueTypes.contains(param1ShortList) : ((this.fItemValueType == param1ShortList || (this.fItemValueType != null && this.fItemValueType.equals(param1ShortList))));
    }
  }
  
  protected static class XPathMatcherStack {
    protected XPathMatcher[] fMatchers = new XPathMatcher[4];
    
    protected int fMatchersCount;
    
    protected IntStack fContextStack = new IntStack();
    
    public void clear() {
      for (byte b = 0; b < this.fMatchersCount; b++)
        this.fMatchers[b] = null; 
      this.fMatchersCount = 0;
      this.fContextStack.clear();
    }
    
    public int size() {
      return this.fContextStack.size();
    }
    
    public int getMatcherCount() {
      return this.fMatchersCount;
    }
    
    public void addMatcher(XPathMatcher param1XPathMatcher) {
      ensureMatcherCapacity();
      this.fMatchers[this.fMatchersCount++] = param1XPathMatcher;
    }
    
    public XPathMatcher getMatcherAt(int param1Int) {
      return this.fMatchers[param1Int];
    }
    
    public void pushContext() {
      this.fContextStack.push(this.fMatchersCount);
    }
    
    public void popContext() {
      this.fMatchersCount = this.fContextStack.pop();
    }
    
    private void ensureMatcherCapacity() {
      if (this.fMatchersCount == this.fMatchers.length) {
        XPathMatcher[] arrayOfXPathMatcher = new XPathMatcher[this.fMatchers.length * 2];
        System.arraycopy(this.fMatchers, 0, arrayOfXPathMatcher, 0, this.fMatchers.length);
        this.fMatchers = arrayOfXPathMatcher;
      } 
    }
  }
  
  protected final class XSIErrorReporter {
    XMLErrorReporter fErrorReporter;
    
    Vector fErrors;
    
    int[] fContext;
    
    int fContextCount;
    
    private final XMLSchemaValidator this$0;
    
    protected XSIErrorReporter(XMLSchemaValidator this$0) {
      this.this$0 = this$0;
      this.fErrors = new Vector();
      this.fContext = new int[8];
    }
    
    public void reset(XMLErrorReporter param1XMLErrorReporter) {
      this.fErrorReporter = param1XMLErrorReporter;
      this.fErrors.removeAllElements();
      this.fContextCount = 0;
    }
    
    public void pushContext() {
      if (!this.this$0.fAugPSVI)
        return; 
      if (this.fContextCount == this.fContext.length) {
        int i = this.fContextCount + 8;
        int[] arrayOfInt = new int[i];
        System.arraycopy(this.fContext, 0, arrayOfInt, 0, this.fContextCount);
        this.fContext = arrayOfInt;
      } 
      this.fContext[this.fContextCount++] = this.fErrors.size();
    }
    
    public String[] popContext() {
      if (!this.this$0.fAugPSVI)
        return null; 
      int i = this.fContext[--this.fContextCount];
      int j = this.fErrors.size() - i;
      if (j == 0)
        return null; 
      String[] arrayOfString = new String[j];
      for (byte b = 0; b < j; b++)
        arrayOfString[b] = this.fErrors.elementAt(i + b); 
      this.fErrors.setSize(i);
      return arrayOfString;
    }
    
    public String[] mergeContext() {
      if (!this.this$0.fAugPSVI)
        return null; 
      int i = this.fContext[--this.fContextCount];
      int j = this.fErrors.size() - i;
      if (j == 0)
        return null; 
      String[] arrayOfString = new String[j];
      for (byte b = 0; b < j; b++)
        arrayOfString[b] = this.fErrors.elementAt(i + b); 
      return arrayOfString;
    }
    
    public void reportError(String param1String1, String param1String2, Object[] param1ArrayOfObject, short param1Short) throws XNIException {
      String str = this.fErrorReporter.reportError(param1String1, param1String2, param1ArrayOfObject, param1Short);
      if (this.this$0.fAugPSVI) {
        this.fErrors.addElement(param1String2);
        this.fErrors.addElement(str);
      } 
    }
    
    public void reportError(XMLLocator param1XMLLocator, String param1String1, String param1String2, Object[] param1ArrayOfObject, short param1Short) throws XNIException {
      String str = this.fErrorReporter.reportError(param1XMLLocator, param1String1, param1String2, param1ArrayOfObject, param1Short);
      if (this.this$0.fAugPSVI) {
        this.fErrors.addElement(param1String2);
        this.fErrors.addElement(str);
      } 
    }
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/xs/XMLSchemaValidator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */