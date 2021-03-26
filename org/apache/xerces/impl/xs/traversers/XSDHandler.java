package org.apache.xerces.impl.xs.traversers;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Stack;
import java.util.Vector;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.apache.xerces.impl.XMLEntityManager;
import org.apache.xerces.impl.XMLErrorReporter;
import org.apache.xerces.impl.dv.SchemaDVFactory;
import org.apache.xerces.impl.dv.XSSimpleType;
import org.apache.xerces.impl.xs.SchemaGrammar;
import org.apache.xerces.impl.xs.SchemaNamespaceSupport;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.xerces.impl.xs.XMLSchemaException;
import org.apache.xerces.impl.xs.XMLSchemaLoader;
import org.apache.xerces.impl.xs.XSAttributeDecl;
import org.apache.xerces.impl.xs.XSAttributeGroupDecl;
import org.apache.xerces.impl.xs.XSComplexTypeDecl;
import org.apache.xerces.impl.xs.XSDDescription;
import org.apache.xerces.impl.xs.XSDeclarationPool;
import org.apache.xerces.impl.xs.XSElementDecl;
import org.apache.xerces.impl.xs.XSGrammarBucket;
import org.apache.xerces.impl.xs.XSGroupDecl;
import org.apache.xerces.impl.xs.XSModelGroupImpl;
import org.apache.xerces.impl.xs.XSNotationDecl;
import org.apache.xerces.impl.xs.XSParticleDecl;
import org.apache.xerces.impl.xs.identity.IdentityConstraint;
import org.apache.xerces.impl.xs.opti.ElementImpl;
import org.apache.xerces.impl.xs.opti.SchemaDOM;
import org.apache.xerces.impl.xs.opti.SchemaDOMParser;
import org.apache.xerces.impl.xs.opti.SchemaParsingConfig;
import org.apache.xerces.impl.xs.util.SimpleLocator;
import org.apache.xerces.impl.xs.util.XSInputSource;
import org.apache.xerces.parsers.SAXParser;
import org.apache.xerces.parsers.XML11Configuration;
import org.apache.xerces.util.DOMInputSource;
import org.apache.xerces.util.DOMUtil;
import org.apache.xerces.util.DefaultErrorHandler;
import org.apache.xerces.util.ErrorHandlerWrapper;
import org.apache.xerces.util.SAXInputSource;
import org.apache.xerces.util.StAXInputSource;
import org.apache.xerces.util.StAXLocationWrapper;
import org.apache.xerces.util.SymbolHash;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.util.URI;
import org.apache.xerces.util.XMLSymbols;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.grammars.Grammar;
import org.apache.xerces.xni.grammars.XMLGrammarDescription;
import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.apache.xerces.xni.grammars.XMLSchemaDescription;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.parser.XMLEntityResolver;
import org.apache.xerces.xni.parser.XMLErrorHandler;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xerces.xni.parser.XMLParseException;
import org.apache.xerces.xni.parser.XMLParserConfiguration;
import org.apache.xerces.xs.StringList;
import org.apache.xerces.xs.XSAttributeDeclaration;
import org.apache.xerces.xs.XSAttributeGroupDefinition;
import org.apache.xerces.xs.XSAttributeUse;
import org.apache.xerces.xs.XSElementDeclaration;
import org.apache.xerces.xs.XSModelGroup;
import org.apache.xerces.xs.XSModelGroupDefinition;
import org.apache.xerces.xs.XSNamedMap;
import org.apache.xerces.xs.XSObject;
import org.apache.xerces.xs.XSObjectList;
import org.apache.xerces.xs.XSParticle;
import org.apache.xerces.xs.XSSimpleTypeDefinition;
import org.apache.xerces.xs.XSTerm;
import org.apache.xerces.xs.XSTypeDefinition;
import org.apache.xerces.xs.datatypes.ObjectList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class XSDHandler {
  protected static final String VALIDATION = "http://xml.org/sax/features/validation";
  
  protected static final String XMLSCHEMA_VALIDATION = "http://apache.org/xml/features/validation/schema";
  
  protected static final String ALLOW_JAVA_ENCODINGS = "http://apache.org/xml/features/allow-java-encodings";
  
  protected static final String CONTINUE_AFTER_FATAL_ERROR = "http://apache.org/xml/features/continue-after-fatal-error";
  
  protected static final String STANDARD_URI_CONFORMANT_FEATURE = "http://apache.org/xml/features/standard-uri-conformant";
  
  protected static final String DISALLOW_DOCTYPE = "http://apache.org/xml/features/disallow-doctype-decl";
  
  protected static final String GENERATE_SYNTHETIC_ANNOTATIONS = "http://apache.org/xml/features/generate-synthetic-annotations";
  
  protected static final String VALIDATE_ANNOTATIONS = "http://apache.org/xml/features/validate-annotations";
  
  protected static final String HONOUR_ALL_SCHEMALOCATIONS = "http://apache.org/xml/features/honour-all-schemaLocations";
  
  protected static final String NAMESPACE_GROWTH = "http://apache.org/xml/features/namespace-growth";
  
  protected static final String TOLERATE_DUPLICATES = "http://apache.org/xml/features/internal/tolerate-duplicates";
  
  private static final String NAMESPACE_PREFIXES = "http://xml.org/sax/features/namespace-prefixes";
  
  protected static final String STRING_INTERNING = "http://xml.org/sax/features/string-interning";
  
  protected static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
  
  protected static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
  
  public static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
  
  protected static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
  
  public static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
  
  public static final String XMLGRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
  
  public static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
  
  protected static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
  
  protected static final String LOCALE = "http://apache.org/xml/properties/locale";
  
  protected static final boolean DEBUG_NODE_POOL = false;
  
  static final int ATTRIBUTE_TYPE = 1;
  
  static final int ATTRIBUTEGROUP_TYPE = 2;
  
  static final int ELEMENT_TYPE = 3;
  
  static final int GROUP_TYPE = 4;
  
  static final int IDENTITYCONSTRAINT_TYPE = 5;
  
  static final int NOTATION_TYPE = 6;
  
  static final int TYPEDECL_TYPE = 7;
  
  public static final String REDEF_IDENTIFIER = "_fn3dktizrknc9pi";
  
  protected Hashtable fNotationRegistry = new Hashtable();
  
  protected XSDeclarationPool fDeclPool = null;
  
  private Hashtable fUnparsedAttributeRegistry = new Hashtable();
  
  private Hashtable fUnparsedAttributeGroupRegistry = new Hashtable();
  
  private Hashtable fUnparsedElementRegistry = new Hashtable();
  
  private Hashtable fUnparsedGroupRegistry = new Hashtable();
  
  private Hashtable fUnparsedIdentityConstraintRegistry = new Hashtable();
  
  private Hashtable fUnparsedNotationRegistry = new Hashtable();
  
  private Hashtable fUnparsedTypeRegistry = new Hashtable();
  
  private Hashtable fUnparsedAttributeRegistrySub = new Hashtable();
  
  private Hashtable fUnparsedAttributeGroupRegistrySub = new Hashtable();
  
  private Hashtable fUnparsedElementRegistrySub = new Hashtable();
  
  private Hashtable fUnparsedGroupRegistrySub = new Hashtable();
  
  private Hashtable fUnparsedIdentityConstraintRegistrySub = new Hashtable();
  
  private Hashtable fUnparsedNotationRegistrySub = new Hashtable();
  
  private Hashtable fUnparsedTypeRegistrySub = new Hashtable();
  
  private Hashtable[] fUnparsedRegistriesExt = new Hashtable[] { null, new Hashtable(), new Hashtable(), new Hashtable(), new Hashtable(), new Hashtable(), new Hashtable(), new Hashtable() };
  
  private Hashtable fXSDocumentInfoRegistry = new Hashtable();
  
  private Hashtable fDependencyMap = new Hashtable();
  
  private Hashtable fImportMap = new Hashtable();
  
  private Vector fAllTNSs = new Vector();
  
  private Hashtable fLocationPairs = null;
  
  private static final Hashtable EMPTY_TABLE = new Hashtable();
  
  Hashtable fHiddenNodes = null;
  
  private Hashtable fTraversed = new Hashtable();
  
  private Hashtable fDoc2SystemId = new Hashtable();
  
  private XSDocumentInfo fRoot = null;
  
  private Hashtable fDoc2XSDocumentMap = new Hashtable();
  
  private Hashtable fRedefine2XSDMap = new Hashtable();
  
  private Hashtable fRedefine2NSSupport = new Hashtable();
  
  private Hashtable fRedefinedRestrictedAttributeGroupRegistry = new Hashtable();
  
  private Hashtable fRedefinedRestrictedGroupRegistry = new Hashtable();
  
  private boolean fLastSchemaWasDuplicate;
  
  private boolean fValidateAnnotations = false;
  
  private boolean fHonourAllSchemaLocations = false;
  
  boolean fNamespaceGrowth = false;
  
  boolean fTolerateDuplicates = false;
  
  private XMLErrorReporter fErrorReporter;
  
  private XMLEntityResolver fEntityResolver;
  
  private XSAttributeChecker fAttributeChecker;
  
  private SymbolTable fSymbolTable;
  
  private XSGrammarBucket fGrammarBucket;
  
  private XSDDescription fSchemaGrammarDescription;
  
  private XMLGrammarPool fGrammarPool;
  
  XSDAttributeGroupTraverser fAttributeGroupTraverser;
  
  XSDAttributeTraverser fAttributeTraverser;
  
  XSDComplexTypeTraverser fComplexTypeTraverser;
  
  XSDElementTraverser fElementTraverser;
  
  XSDGroupTraverser fGroupTraverser;
  
  XSDKeyrefTraverser fKeyrefTraverser;
  
  XSDNotationTraverser fNotationTraverser;
  
  XSDSimpleTypeTraverser fSimpleTypeTraverser;
  
  XSDUniqueOrKeyTraverser fUniqueOrKeyTraverser;
  
  XSDWildcardTraverser fWildCardTraverser;
  
  SchemaDVFactory fDVFactory;
  
  SchemaDOMParser fSchemaParser;
  
  SchemaContentHandler fXSContentHandler;
  
  StAXSchemaParser fStAXSchemaParser;
  
  XML11Configuration fAnnotationValidator;
  
  XSAnnotationGrammarPool fGrammarBucketAdapter;
  
  private static final int INIT_STACK_SIZE = 30;
  
  private static final int INC_STACK_SIZE = 10;
  
  private int fLocalElemStackPos = 0;
  
  private XSParticleDecl[] fParticle = new XSParticleDecl[30];
  
  private Element[] fLocalElementDecl = new Element[30];
  
  private XSDocumentInfo[] fLocalElementDecl_schema = new XSDocumentInfo[30];
  
  private int[] fAllContext = new int[30];
  
  private XSObject[] fParent = new XSObject[30];
  
  private String[][] fLocalElemNamespaceContext = new String[30][1];
  
  private static final int INIT_KEYREF_STACK = 2;
  
  private static final int INC_KEYREF_STACK_AMOUNT = 2;
  
  private int fKeyrefStackPos = 0;
  
  private Element[] fKeyrefs = new Element[2];
  
  private XSDocumentInfo[] fKeyrefsMapXSDocumentInfo = new XSDocumentInfo[2];
  
  private XSElementDecl[] fKeyrefElems = new XSElementDecl[2];
  
  private String[][] fKeyrefNamespaceContext = new String[2][1];
  
  SymbolHash fGlobalAttrDecls = new SymbolHash();
  
  SymbolHash fGlobalAttrGrpDecls = new SymbolHash();
  
  SymbolHash fGlobalElemDecls = new SymbolHash();
  
  SymbolHash fGlobalGroupDecls = new SymbolHash();
  
  SymbolHash fGlobalNotationDecls = new SymbolHash();
  
  SymbolHash fGlobalIDConstraintDecls = new SymbolHash();
  
  SymbolHash fGlobalTypeDecls = new SymbolHash();
  
  private static final String[][] NS_ERROR_CODES = new String[][] { { "src-include.2.1", "src-include.2.1" }, { "src-redefine.3.1", "src-redefine.3.1" }, { "src-import.3.1", "src-import.3.2" }, null, { "TargetNamespace.1", "TargetNamespace.2" }, { "TargetNamespace.1", "TargetNamespace.2" }, { "TargetNamespace.1", "TargetNamespace.2" }, { "TargetNamespace.1", "TargetNamespace.2" } };
  
  private static final String[] ELE_ERROR_CODES = new String[] { "src-include.1", "src-redefine.2", "src-import.2", "schema_reference.4", "schema_reference.4", "schema_reference.4", "schema_reference.4", "schema_reference.4" };
  
  private Vector fReportedTNS = null;
  
  private static final String[] COMP_TYPE = new String[] { null, "attribute declaration", "attribute group", "element declaration", "group", "identity constraint", "notation", "type definition" };
  
  private static final String[] CIRCULAR_CODES = new String[] { "Internal-Error", "Internal-Error", "src-attribute_group.3", "e-props-correct.6", "mg-props-correct.2", "Internal-Error", "Internal-Error", "st-props-correct.2" };
  
  private SimpleLocator xl = new SimpleLocator();
  
  private String null2EmptyString(String paramString) {
    return (paramString == null) ? XMLSymbols.EMPTY_STRING : paramString;
  }
  
  private String emptyString2Null(String paramString) {
    return (paramString == XMLSymbols.EMPTY_STRING) ? null : paramString;
  }
  
  private String doc2SystemId(Element paramElement) {
    String str = null;
    if (paramElement.getOwnerDocument() instanceof SchemaDOM)
      str = ((SchemaDOM)paramElement.getOwnerDocument()).getDocumentURI(); 
    return (str != null) ? str : (String)this.fDoc2SystemId.get(paramElement);
  }
  
  public XSDHandler() {
    this.fHiddenNodes = new Hashtable();
    this.fSchemaParser = new SchemaDOMParser((XMLParserConfiguration)new SchemaParsingConfig());
  }
  
  public XSDHandler(XSGrammarBucket paramXSGrammarBucket) {
    this();
    this.fGrammarBucket = paramXSGrammarBucket;
    this.fSchemaGrammarDescription = new XSDDescription();
  }
  
  public SchemaGrammar parseSchema(XMLInputSource paramXMLInputSource, XSDDescription paramXSDDescription, Hashtable paramHashtable) throws IOException {
    this.fLocationPairs = paramHashtable;
    this.fSchemaParser.resetNodePool();
    SchemaGrammar schemaGrammar = null;
    String str = null;
    short s = paramXSDDescription.getContextType();
    if (s != 3) {
      if (this.fHonourAllSchemaLocations && s == 2 && isExistingGrammar(paramXSDDescription, this.fNamespaceGrowth)) {
        schemaGrammar = this.fGrammarBucket.getGrammar(paramXSDDescription.getTargetNamespace());
      } else {
        schemaGrammar = findGrammar(paramXSDDescription, this.fNamespaceGrowth);
      } 
      if (schemaGrammar != null) {
        if (!this.fNamespaceGrowth)
          return schemaGrammar; 
        try {
          if (schemaGrammar.getDocumentLocations().contains(XMLEntityManager.expandSystemId(paramXMLInputSource.getSystemId(), paramXMLInputSource.getBaseSystemId(), false)))
            return schemaGrammar; 
        } catch (org.apache.xerces.util.URI.MalformedURIException malformedURIException) {}
      } 
      str = paramXSDDescription.getTargetNamespace();
      if (str != null)
        str = this.fSymbolTable.addSymbol(str); 
    } 
    prepareForParse();
    Element element = null;
    if (paramXMLInputSource instanceof DOMInputSource) {
      element = getSchemaDocument(str, (DOMInputSource)paramXMLInputSource, (s == 3), s, (Element)null);
    } else if (paramXMLInputSource instanceof SAXInputSource) {
      element = getSchemaDocument(str, (SAXInputSource)paramXMLInputSource, (s == 3), s, (Element)null);
    } else if (paramXMLInputSource instanceof StAXInputSource) {
      element = getSchemaDocument(str, (StAXInputSource)paramXMLInputSource, (s == 3), s, (Element)null);
    } else if (paramXMLInputSource instanceof XSInputSource) {
      element = getSchemaDocument((XSInputSource)paramXMLInputSource, paramXSDDescription);
    } else {
      element = getSchemaDocument(str, paramXMLInputSource, (s == 3), s, (Element)null);
    } 
    if (element == null)
      return (paramXMLInputSource instanceof XSInputSource) ? this.fGrammarBucket.getGrammar(paramXSDDescription.getTargetNamespace()) : schemaGrammar; 
    if (s == 3) {
      Element element1 = element;
      str = DOMUtil.getAttrValue(element1, SchemaSymbols.ATT_TARGETNAMESPACE);
      if (str != null && str.length() > 0) {
        str = this.fSymbolTable.addSymbol(str);
        paramXSDDescription.setTargetNamespace(str);
      } else {
        str = null;
      } 
      schemaGrammar = findGrammar(paramXSDDescription, this.fNamespaceGrowth);
      String str1 = XMLEntityManager.expandSystemId(paramXMLInputSource.getSystemId(), paramXMLInputSource.getBaseSystemId(), false);
      if (schemaGrammar != null && (!this.fNamespaceGrowth || (str1 != null && schemaGrammar.getDocumentLocations().contains(str1))))
        return schemaGrammar; 
      XSDKey xSDKey = new XSDKey(str1, s, str);
      this.fTraversed.put(xSDKey, element);
      if (str1 != null)
        this.fDoc2SystemId.put(element, str1); 
    } 
    prepareForTraverse();
    this.fRoot = constructTrees(element, paramXMLInputSource.getSystemId(), paramXSDDescription, (schemaGrammar != null));
    if (this.fRoot == null)
      return null; 
    buildGlobalNameRegistries();
    ArrayList arrayList = this.fValidateAnnotations ? new ArrayList() : null;
    traverseSchemas(arrayList);
    traverseLocalElements();
    resolveKeyRefs();
    for (int i = this.fAllTNSs.size() - 1; i >= 0; i--) {
      String str1 = this.fAllTNSs.elementAt(i);
      Vector vector = (Vector)this.fImportMap.get(str1);
      SchemaGrammar schemaGrammar1 = this.fGrammarBucket.getGrammar(emptyString2Null(str1));
      if (schemaGrammar1 != null) {
        byte b1 = 0;
        for (byte b2 = 0; b2 < vector.size(); b2++) {
          SchemaGrammar schemaGrammar2 = this.fGrammarBucket.getGrammar(vector.elementAt(b2));
          if (schemaGrammar2 != null)
            vector.setElementAt(schemaGrammar2, b1++); 
        } 
        vector.setSize(b1);
        schemaGrammar1.setImportedGrammars(vector);
      } 
    } 
    if (this.fValidateAnnotations && arrayList.size() > 0)
      validateAnnotations(arrayList); 
    return this.fGrammarBucket.getGrammar(this.fRoot.fTargetNamespace);
  }
  
  private void validateAnnotations(ArrayList paramArrayList) {
    if (this.fAnnotationValidator == null)
      createAnnotationValidator(); 
    int i = paramArrayList.size();
    XMLInputSource xMLInputSource = new XMLInputSource(null, null, null);
    this.fGrammarBucketAdapter.refreshGrammars(this.fGrammarBucket);
    for (byte b = 0; b < i; b += 2) {
      xMLInputSource.setSystemId(paramArrayList.get(b));
      for (XSAnnotationInfo xSAnnotationInfo = (XSAnnotationInfo)paramArrayList.get(b + 1); xSAnnotationInfo != null; xSAnnotationInfo = xSAnnotationInfo.next) {
        xMLInputSource.setCharacterStream(new StringReader(xSAnnotationInfo.fAnnotation));
        try {
          this.fAnnotationValidator.parse(xMLInputSource);
        } catch (IOException iOException) {}
      } 
    } 
  }
  
  private void createAnnotationValidator() {
    this.fAnnotationValidator = new XML11Configuration();
    this.fGrammarBucketAdapter = new XSAnnotationGrammarPool();
    this.fAnnotationValidator.setFeature("http://xml.org/sax/features/validation", true);
    this.fAnnotationValidator.setFeature("http://apache.org/xml/features/validation/schema", true);
    this.fAnnotationValidator.setProperty("http://apache.org/xml/properties/internal/grammar-pool", this.fGrammarBucketAdapter);
    XMLErrorHandler xMLErrorHandler = this.fErrorReporter.getErrorHandler();
    this.fAnnotationValidator.setProperty("http://apache.org/xml/properties/internal/error-handler", (xMLErrorHandler != null) ? xMLErrorHandler : new DefaultErrorHandler());
    Locale locale = this.fErrorReporter.getLocale();
    this.fAnnotationValidator.setProperty("http://apache.org/xml/properties/locale", locale);
  }
  
  SchemaGrammar getGrammar(String paramString) {
    return this.fGrammarBucket.getGrammar(paramString);
  }
  
  protected SchemaGrammar findGrammar(XSDDescription paramXSDDescription, boolean paramBoolean) {
    SchemaGrammar schemaGrammar = this.fGrammarBucket.getGrammar(paramXSDDescription.getTargetNamespace());
    if (schemaGrammar == null && this.fGrammarPool != null) {
      schemaGrammar = (SchemaGrammar)this.fGrammarPool.retrieveGrammar((XMLGrammarDescription)paramXSDDescription);
      if (schemaGrammar != null && !this.fGrammarBucket.putGrammar(schemaGrammar, true, paramBoolean)) {
        reportSchemaWarning("GrammarConflict", null, null);
        schemaGrammar = null;
      } 
    } 
    return schemaGrammar;
  }
  
  protected XSDocumentInfo constructTrees(Element paramElement, String paramString, XSDDescription paramXSDDescription, boolean paramBoolean) {
    if (paramElement == null)
      return null; 
    String str = paramXSDDescription.getTargetNamespace();
    short s = paramXSDDescription.getContextType();
    XSDocumentInfo xSDocumentInfo = null;
    try {
      xSDocumentInfo = new XSDocumentInfo(paramElement, this.fAttributeChecker, this.fSymbolTable);
    } catch (XMLSchemaException xMLSchemaException) {
      reportSchemaError(ELE_ERROR_CODES[s], new Object[] { paramString }, paramElement);
      return null;
    } 
    if (xSDocumentInfo.fTargetNamespace != null && xSDocumentInfo.fTargetNamespace.length() == 0) {
      reportSchemaWarning("EmptyTargetNamespace", new Object[] { paramString }, paramElement);
      xSDocumentInfo.fTargetNamespace = null;
    } 
    if (str != null) {
      boolean bool = false;
      if (s == 0 || s == 1) {
        if (xSDocumentInfo.fTargetNamespace == null) {
          xSDocumentInfo.fTargetNamespace = str;
          xSDocumentInfo.fIsChameleonSchema = true;
        } else if (str != xSDocumentInfo.fTargetNamespace) {
          reportSchemaError(NS_ERROR_CODES[s][bool], new Object[] { str, xSDocumentInfo.fTargetNamespace }, paramElement);
          return null;
        } 
      } else if (s != 3 && str != xSDocumentInfo.fTargetNamespace) {
        reportSchemaError(NS_ERROR_CODES[s][bool], new Object[] { str, xSDocumentInfo.fTargetNamespace }, paramElement);
        return null;
      } 
    } else if (xSDocumentInfo.fTargetNamespace != null) {
      if (s == 3) {
        paramXSDDescription.setTargetNamespace(xSDocumentInfo.fTargetNamespace);
        str = xSDocumentInfo.fTargetNamespace;
      } else {
        boolean bool = true;
        reportSchemaError(NS_ERROR_CODES[s][bool], new Object[] { str, xSDocumentInfo.fTargetNamespace }, paramElement);
        return null;
      } 
    } 
    xSDocumentInfo.addAllowedNS(xSDocumentInfo.fTargetNamespace);
    SchemaGrammar schemaGrammar = null;
    if (paramBoolean) {
      SchemaGrammar schemaGrammar1 = this.fGrammarBucket.getGrammar(xSDocumentInfo.fTargetNamespace);
      if (schemaGrammar1.isImmutable()) {
        schemaGrammar = new SchemaGrammar(schemaGrammar1);
        this.fGrammarBucket.putGrammar(schemaGrammar);
        updateImportListWith(schemaGrammar);
      } else {
        schemaGrammar = schemaGrammar1;
      } 
      updateImportListFor(schemaGrammar);
    } else if (s == 0 || s == 1) {
      schemaGrammar = this.fGrammarBucket.getGrammar(xSDocumentInfo.fTargetNamespace);
    } else if (this.fHonourAllSchemaLocations && s == 2) {
      schemaGrammar = findGrammar(paramXSDDescription, false);
      if (schemaGrammar == null) {
        schemaGrammar = new SchemaGrammar(xSDocumentInfo.fTargetNamespace, paramXSDDescription.makeClone(), this.fSymbolTable);
        this.fGrammarBucket.putGrammar(schemaGrammar);
      } 
    } else {
      schemaGrammar = new SchemaGrammar(xSDocumentInfo.fTargetNamespace, paramXSDDescription.makeClone(), this.fSymbolTable);
      this.fGrammarBucket.putGrammar(schemaGrammar);
    } 
    schemaGrammar.addDocument(null, (String)this.fDoc2SystemId.get(xSDocumentInfo.fSchemaElement));
    this.fDoc2XSDocumentMap.put(paramElement, xSDocumentInfo);
    Vector vector = new Vector();
    Element element1 = paramElement;
    Element element2 = null;
    for (Element element3 = DOMUtil.getFirstChildElement(element1); element3 != null; element3 = DOMUtil.getNextSiblingElement(element3)) {
      String str1 = null;
      String str2 = null;
      String str3 = DOMUtil.getLocalName(element3);
      byte b = -1;
      boolean bool = false;
      if (str3.equals(SchemaSymbols.ELT_ANNOTATION))
        continue; 
      if (str3.equals(SchemaSymbols.ELT_IMPORT)) {
        b = 2;
        Object[] arrayOfObject = this.fAttributeChecker.checkAttributes(element3, true, xSDocumentInfo);
        str2 = (String)arrayOfObject[XSAttributeChecker.ATTIDX_SCHEMALOCATION];
        str1 = (String)arrayOfObject[XSAttributeChecker.ATTIDX_NAMESPACE];
        if (str1 != null)
          str1 = this.fSymbolTable.addSymbol(str1); 
        Element element = DOMUtil.getFirstChildElement(element3);
        if (element != null) {
          String str5 = DOMUtil.getLocalName(element);
          if (str5.equals(SchemaSymbols.ELT_ANNOTATION)) {
            schemaGrammar.addAnnotation(this.fElementTraverser.traverseAnnotationDecl(element, arrayOfObject, true, xSDocumentInfo));
          } else {
            reportSchemaError("s4s-elt-must-match.1", new Object[] { str3, "annotation?", str5 }, element3);
          } 
          if (DOMUtil.getNextSiblingElement(element) != null)
            reportSchemaError("s4s-elt-must-match.1", new Object[] { str3, "annotation?", DOMUtil.getLocalName(DOMUtil.getNextSiblingElement(element)) }, element3); 
        } else {
          String str5 = DOMUtil.getSyntheticAnnotation(element3);
          if (str5 != null)
            schemaGrammar.addAnnotation(this.fElementTraverser.traverseSyntheticAnnotation(element3, str5, arrayOfObject, true, xSDocumentInfo)); 
        } 
        this.fAttributeChecker.returnAttrArray(arrayOfObject, xSDocumentInfo);
        if (str1 == xSDocumentInfo.fTargetNamespace) {
          reportSchemaError((str1 != null) ? "src-import.1.1" : "src-import.1.2", new Object[] { str1 }, element3);
          continue;
        } 
        if (xSDocumentInfo.isAllowedNS(str1)) {
          if (!this.fHonourAllSchemaLocations && !this.fNamespaceGrowth)
            continue; 
        } else {
          xSDocumentInfo.addAllowedNS(str1);
        } 
        String str4 = null2EmptyString(xSDocumentInfo.fTargetNamespace);
        Vector vector1 = (Vector)this.fImportMap.get(str4);
        if (vector1 == null) {
          this.fAllTNSs.addElement(str4);
          vector1 = new Vector();
          this.fImportMap.put(str4, vector1);
          vector1.addElement(str1);
        } else if (!vector1.contains(str1)) {
          vector1.addElement(str1);
        } 
        this.fSchemaGrammarDescription.reset();
        this.fSchemaGrammarDescription.setContextType((short)2);
        this.fSchemaGrammarDescription.setBaseSystemId(doc2SystemId(paramElement));
        this.fSchemaGrammarDescription.setLiteralSystemId(str2);
        this.fSchemaGrammarDescription.setLocationHints(new String[] { str2 });
        this.fSchemaGrammarDescription.setTargetNamespace(str1);
        SchemaGrammar schemaGrammar1 = findGrammar(this.fSchemaGrammarDescription, this.fNamespaceGrowth);
        if (schemaGrammar1 != null)
          if (this.fNamespaceGrowth) {
            try {
              if (schemaGrammar1.getDocumentLocations().contains(XMLEntityManager.expandSystemId(str2, this.fSchemaGrammarDescription.getBaseSystemId(), false)))
                continue; 
              bool = true;
            } catch (org.apache.xerces.util.URI.MalformedURIException malformedURIException) {}
          } else if (!this.fHonourAllSchemaLocations || isExistingGrammar(this.fSchemaGrammarDescription, false)) {
            continue;
          }  
        element2 = resolveSchema(this.fSchemaGrammarDescription, false, element3, (schemaGrammar1 == null));
      } else if (str3.equals(SchemaSymbols.ELT_INCLUDE) || str3.equals(SchemaSymbols.ELT_REDEFINE)) {
        Object[] arrayOfObject = this.fAttributeChecker.checkAttributes(element3, true, xSDocumentInfo);
        str2 = (String)arrayOfObject[XSAttributeChecker.ATTIDX_SCHEMALOCATION];
        if (str3.equals(SchemaSymbols.ELT_REDEFINE))
          this.fRedefine2NSSupport.put(element3, new SchemaNamespaceSupport(xSDocumentInfo.fNamespaceSupport)); 
        if (str3.equals(SchemaSymbols.ELT_INCLUDE)) {
          Element element = DOMUtil.getFirstChildElement(element3);
          if (element != null) {
            String str4 = DOMUtil.getLocalName(element);
            if (str4.equals(SchemaSymbols.ELT_ANNOTATION)) {
              schemaGrammar.addAnnotation(this.fElementTraverser.traverseAnnotationDecl(element, arrayOfObject, true, xSDocumentInfo));
            } else {
              reportSchemaError("s4s-elt-must-match.1", new Object[] { str3, "annotation?", str4 }, element3);
            } 
            if (DOMUtil.getNextSiblingElement(element) != null)
              reportSchemaError("s4s-elt-must-match.1", new Object[] { str3, "annotation?", DOMUtil.getLocalName(DOMUtil.getNextSiblingElement(element)) }, element3); 
          } else {
            String str4 = DOMUtil.getSyntheticAnnotation(element3);
            if (str4 != null)
              schemaGrammar.addAnnotation(this.fElementTraverser.traverseSyntheticAnnotation(element3, str4, arrayOfObject, true, xSDocumentInfo)); 
          } 
        } else {
          for (Element element = DOMUtil.getFirstChildElement(element3); element != null; element = DOMUtil.getNextSiblingElement(element)) {
            String str4 = DOMUtil.getLocalName(element);
            if (str4.equals(SchemaSymbols.ELT_ANNOTATION)) {
              schemaGrammar.addAnnotation(this.fElementTraverser.traverseAnnotationDecl(element, arrayOfObject, true, xSDocumentInfo));
              DOMUtil.setHidden(element, this.fHiddenNodes);
            } else {
              String str5 = DOMUtil.getSyntheticAnnotation(element3);
              if (str5 != null)
                schemaGrammar.addAnnotation(this.fElementTraverser.traverseSyntheticAnnotation(element3, str5, arrayOfObject, true, xSDocumentInfo)); 
            } 
          } 
        } 
        this.fAttributeChecker.returnAttrArray(arrayOfObject, xSDocumentInfo);
        if (str2 == null)
          reportSchemaError("s4s-att-must-appear", new Object[] { "<include> or <redefine>", "schemaLocation" }, element3); 
        boolean bool1 = false;
        b = 0;
        if (str3.equals(SchemaSymbols.ELT_REDEFINE)) {
          bool1 = nonAnnotationContent(element3);
          b = 1;
        } 
        this.fSchemaGrammarDescription.reset();
        this.fSchemaGrammarDescription.setContextType(b);
        this.fSchemaGrammarDescription.setBaseSystemId(doc2SystemId(paramElement));
        this.fSchemaGrammarDescription.setLocationHints(new String[] { str2 });
        this.fSchemaGrammarDescription.setTargetNamespace(str);
        boolean bool2 = false;
        XMLInputSource xMLInputSource = resolveSchemaSource(this.fSchemaGrammarDescription, bool1, element3, true);
        if (this.fNamespaceGrowth && b == 0)
          try {
            String str4 = XMLEntityManager.expandSystemId(xMLInputSource.getSystemId(), xMLInputSource.getBaseSystemId(), false);
            bool2 = schemaGrammar.getDocumentLocations().contains(str4);
          } catch (org.apache.xerces.util.URI.MalformedURIException malformedURIException) {} 
        if (!bool2) {
          element2 = resolveSchema(xMLInputSource, this.fSchemaGrammarDescription, bool1, element3);
          str1 = xSDocumentInfo.fTargetNamespace;
        } else {
          this.fLastSchemaWasDuplicate = true;
        } 
      } else {
        break;
      } 
      XSDocumentInfo xSDocumentInfo1 = null;
      if (this.fLastSchemaWasDuplicate) {
        xSDocumentInfo1 = (element2 == null) ? null : (XSDocumentInfo)this.fDoc2XSDocumentMap.get(element2);
      } else {
        xSDocumentInfo1 = constructTrees(element2, str2, this.fSchemaGrammarDescription, bool);
      } 
      if (str3.equals(SchemaSymbols.ELT_REDEFINE) && xSDocumentInfo1 != null)
        this.fRedefine2XSDMap.put(element3, xSDocumentInfo1); 
      if (element2 != null) {
        if (xSDocumentInfo1 != null)
          vector.addElement(xSDocumentInfo1); 
        element2 = null;
      } 
      continue;
    } 
    this.fDependencyMap.put(xSDocumentInfo, vector);
    return xSDocumentInfo;
  }
  
  private boolean isExistingGrammar(XSDDescription paramXSDDescription, boolean paramBoolean) {
    SchemaGrammar schemaGrammar = this.fGrammarBucket.getGrammar(paramXSDDescription.getTargetNamespace());
    if (schemaGrammar == null)
      return (findGrammar(paramXSDDescription, paramBoolean) != null); 
    if (schemaGrammar.isImmutable())
      return true; 
    try {
      return schemaGrammar.getDocumentLocations().contains(XMLEntityManager.expandSystemId(paramXSDDescription.getLiteralSystemId(), paramXSDDescription.getBaseSystemId(), false));
    } catch (org.apache.xerces.util.URI.MalformedURIException malformedURIException) {
      return false;
    } 
  }
  
  private void updateImportListFor(SchemaGrammar paramSchemaGrammar) {
    Vector vector = paramSchemaGrammar.getImportedGrammars();
    if (vector != null)
      for (byte b = 0; b < vector.size(); b++) {
        SchemaGrammar schemaGrammar1 = vector.elementAt(b);
        SchemaGrammar schemaGrammar2 = this.fGrammarBucket.getGrammar(schemaGrammar1.getTargetNamespace());
        if (schemaGrammar2 != null && schemaGrammar1 != schemaGrammar2)
          vector.set(b, schemaGrammar2); 
      }  
  }
  
  private void updateImportListWith(SchemaGrammar paramSchemaGrammar) {
    SchemaGrammar[] arrayOfSchemaGrammar = this.fGrammarBucket.getGrammars();
    for (byte b = 0; b < arrayOfSchemaGrammar.length; b++) {
      SchemaGrammar schemaGrammar = arrayOfSchemaGrammar[b];
      if (schemaGrammar != paramSchemaGrammar) {
        Vector vector = schemaGrammar.getImportedGrammars();
        if (vector != null)
          for (byte b1 = 0; b1 < vector.size(); b1++) {
            SchemaGrammar schemaGrammar1 = vector.elementAt(b1);
            if (null2EmptyString(schemaGrammar1.getTargetNamespace()).equals(null2EmptyString(paramSchemaGrammar.getTargetNamespace()))) {
              if (schemaGrammar1 != paramSchemaGrammar)
                vector.set(b1, paramSchemaGrammar); 
              break;
            } 
          }  
      } 
    } 
  }
  
  protected void buildGlobalNameRegistries() {
    Stack stack = new Stack();
    stack.push(this.fRoot);
    while (!stack.empty()) {
      XSDocumentInfo xSDocumentInfo = stack.pop();
      Element element1 = xSDocumentInfo.fSchemaElement;
      if (DOMUtil.isHidden(element1, this.fHiddenNodes))
        continue; 
      Element element2 = element1;
      boolean bool = true;
      for (Element element3 = DOMUtil.getFirstChildElement(element2); element3 != null; element3 = DOMUtil.getNextSiblingElement(element3)) {
        if (!DOMUtil.getLocalName(element3).equals(SchemaSymbols.ELT_ANNOTATION))
          if (DOMUtil.getLocalName(element3).equals(SchemaSymbols.ELT_INCLUDE) || DOMUtil.getLocalName(element3).equals(SchemaSymbols.ELT_IMPORT)) {
            if (!bool)
              reportSchemaError("s4s-elt-invalid-content.3", new Object[] { DOMUtil.getLocalName(element3) }, element3); 
            DOMUtil.setHidden(element3, this.fHiddenNodes);
          } else if (DOMUtil.getLocalName(element3).equals(SchemaSymbols.ELT_REDEFINE)) {
            if (!bool)
              reportSchemaError("s4s-elt-invalid-content.3", new Object[] { DOMUtil.getLocalName(element3) }, element3); 
            for (Element element = DOMUtil.getFirstChildElement(element3); element != null; element = DOMUtil.getNextSiblingElement(element)) {
              String str = DOMUtil.getAttrValue(element, SchemaSymbols.ATT_NAME);
              if (str.length() != 0) {
                String str1 = (xSDocumentInfo.fTargetNamespace == null) ? ("," + str) : (xSDocumentInfo.fTargetNamespace + "," + str);
                String str2 = DOMUtil.getLocalName(element);
                if (str2.equals(SchemaSymbols.ELT_ATTRIBUTEGROUP)) {
                  checkForDuplicateNames(str1, 2, this.fUnparsedAttributeGroupRegistry, this.fUnparsedAttributeGroupRegistrySub, element, xSDocumentInfo);
                  String str3 = DOMUtil.getAttrValue(element, SchemaSymbols.ATT_NAME) + "_fn3dktizrknc9pi";
                  renameRedefiningComponents(xSDocumentInfo, element, SchemaSymbols.ELT_ATTRIBUTEGROUP, str, str3);
                } else if (str2.equals(SchemaSymbols.ELT_COMPLEXTYPE) || str2.equals(SchemaSymbols.ELT_SIMPLETYPE)) {
                  checkForDuplicateNames(str1, 7, this.fUnparsedTypeRegistry, this.fUnparsedTypeRegistrySub, element, xSDocumentInfo);
                  String str3 = DOMUtil.getAttrValue(element, SchemaSymbols.ATT_NAME) + "_fn3dktizrknc9pi";
                  if (str2.equals(SchemaSymbols.ELT_COMPLEXTYPE)) {
                    renameRedefiningComponents(xSDocumentInfo, element, SchemaSymbols.ELT_COMPLEXTYPE, str, str3);
                  } else {
                    renameRedefiningComponents(xSDocumentInfo, element, SchemaSymbols.ELT_SIMPLETYPE, str, str3);
                  } 
                } else if (str2.equals(SchemaSymbols.ELT_GROUP)) {
                  checkForDuplicateNames(str1, 4, this.fUnparsedGroupRegistry, this.fUnparsedGroupRegistrySub, element, xSDocumentInfo);
                  String str3 = DOMUtil.getAttrValue(element, SchemaSymbols.ATT_NAME) + "_fn3dktizrknc9pi";
                  renameRedefiningComponents(xSDocumentInfo, element, SchemaSymbols.ELT_GROUP, str, str3);
                } 
              } 
            } 
          } else {
            bool = false;
            String str = DOMUtil.getAttrValue(element3, SchemaSymbols.ATT_NAME);
            if (str.length() != 0) {
              String str1 = (xSDocumentInfo.fTargetNamespace == null) ? ("," + str) : (xSDocumentInfo.fTargetNamespace + "," + str);
              String str2 = DOMUtil.getLocalName(element3);
              if (str2.equals(SchemaSymbols.ELT_ATTRIBUTE)) {
                checkForDuplicateNames(str1, 1, this.fUnparsedAttributeRegistry, this.fUnparsedAttributeRegistrySub, element3, xSDocumentInfo);
              } else if (str2.equals(SchemaSymbols.ELT_ATTRIBUTEGROUP)) {
                checkForDuplicateNames(str1, 2, this.fUnparsedAttributeGroupRegistry, this.fUnparsedAttributeGroupRegistrySub, element3, xSDocumentInfo);
              } else if (str2.equals(SchemaSymbols.ELT_COMPLEXTYPE) || str2.equals(SchemaSymbols.ELT_SIMPLETYPE)) {
                checkForDuplicateNames(str1, 7, this.fUnparsedTypeRegistry, this.fUnparsedTypeRegistrySub, element3, xSDocumentInfo);
              } else if (str2.equals(SchemaSymbols.ELT_ELEMENT)) {
                checkForDuplicateNames(str1, 3, this.fUnparsedElementRegistry, this.fUnparsedElementRegistrySub, element3, xSDocumentInfo);
              } else if (str2.equals(SchemaSymbols.ELT_GROUP)) {
                checkForDuplicateNames(str1, 4, this.fUnparsedGroupRegistry, this.fUnparsedGroupRegistrySub, element3, xSDocumentInfo);
              } else if (str2.equals(SchemaSymbols.ELT_NOTATION)) {
                checkForDuplicateNames(str1, 6, this.fUnparsedNotationRegistry, this.fUnparsedNotationRegistrySub, element3, xSDocumentInfo);
              } 
            } 
          }  
      } 
      DOMUtil.setHidden(element1, this.fHiddenNodes);
      Vector vector = (Vector)this.fDependencyMap.get(xSDocumentInfo);
      for (byte b = 0; b < vector.size(); b++)
        stack.push(vector.elementAt(b)); 
    } 
  }
  
  protected void traverseSchemas(ArrayList paramArrayList) {
    setSchemasVisible(this.fRoot);
    Stack stack = new Stack();
    stack.push(this.fRoot);
    while (!stack.empty()) {
      XSDocumentInfo xSDocumentInfo = stack.pop();
      Element element1 = xSDocumentInfo.fSchemaElement;
      SchemaGrammar schemaGrammar = this.fGrammarBucket.getGrammar(xSDocumentInfo.fTargetNamespace);
      if (DOMUtil.isHidden(element1, this.fHiddenNodes))
        continue; 
      Element element2 = element1;
      boolean bool = false;
      for (Element element3 = DOMUtil.getFirstVisibleChildElement(element2, this.fHiddenNodes); element3 != null; element3 = DOMUtil.getNextVisibleSiblingElement(element3, this.fHiddenNodes)) {
        DOMUtil.setHidden(element3, this.fHiddenNodes);
        String str = DOMUtil.getLocalName(element3);
        if (DOMUtil.getLocalName(element3).equals(SchemaSymbols.ELT_REDEFINE)) {
          xSDocumentInfo.backupNSSupport((SchemaNamespaceSupport)this.fRedefine2NSSupport.get(element3));
          for (Element element = DOMUtil.getFirstVisibleChildElement(element3, this.fHiddenNodes); element != null; element = DOMUtil.getNextVisibleSiblingElement(element, this.fHiddenNodes)) {
            String str1 = DOMUtil.getLocalName(element);
            DOMUtil.setHidden(element, this.fHiddenNodes);
            if (str1.equals(SchemaSymbols.ELT_ATTRIBUTEGROUP)) {
              this.fAttributeGroupTraverser.traverseGlobal(element, xSDocumentInfo, schemaGrammar);
            } else if (str1.equals(SchemaSymbols.ELT_COMPLEXTYPE)) {
              this.fComplexTypeTraverser.traverseGlobal(element, xSDocumentInfo, schemaGrammar);
            } else if (str1.equals(SchemaSymbols.ELT_GROUP)) {
              this.fGroupTraverser.traverseGlobal(element, xSDocumentInfo, schemaGrammar);
            } else if (str1.equals(SchemaSymbols.ELT_SIMPLETYPE)) {
              this.fSimpleTypeTraverser.traverseGlobal(element, xSDocumentInfo, schemaGrammar);
            } else {
              reportSchemaError("s4s-elt-must-match.1", new Object[] { DOMUtil.getLocalName(element3), "(annotation | (simpleType | complexType | group | attributeGroup))*", str1 }, element);
            } 
          } 
          xSDocumentInfo.restoreNSSupport();
        } else if (str.equals(SchemaSymbols.ELT_ATTRIBUTE)) {
          this.fAttributeTraverser.traverseGlobal(element3, xSDocumentInfo, schemaGrammar);
        } else if (str.equals(SchemaSymbols.ELT_ATTRIBUTEGROUP)) {
          this.fAttributeGroupTraverser.traverseGlobal(element3, xSDocumentInfo, schemaGrammar);
        } else if (str.equals(SchemaSymbols.ELT_COMPLEXTYPE)) {
          this.fComplexTypeTraverser.traverseGlobal(element3, xSDocumentInfo, schemaGrammar);
        } else if (str.equals(SchemaSymbols.ELT_ELEMENT)) {
          this.fElementTraverser.traverseGlobal(element3, xSDocumentInfo, schemaGrammar);
        } else if (str.equals(SchemaSymbols.ELT_GROUP)) {
          this.fGroupTraverser.traverseGlobal(element3, xSDocumentInfo, schemaGrammar);
        } else if (str.equals(SchemaSymbols.ELT_NOTATION)) {
          this.fNotationTraverser.traverse(element3, xSDocumentInfo, schemaGrammar);
        } else if (str.equals(SchemaSymbols.ELT_SIMPLETYPE)) {
          this.fSimpleTypeTraverser.traverseGlobal(element3, xSDocumentInfo, schemaGrammar);
        } else if (str.equals(SchemaSymbols.ELT_ANNOTATION)) {
          schemaGrammar.addAnnotation(this.fElementTraverser.traverseAnnotationDecl(element3, xSDocumentInfo.getSchemaAttrs(), true, xSDocumentInfo));
          bool = true;
        } else {
          reportSchemaError("s4s-elt-invalid-content.1", new Object[] { SchemaSymbols.ELT_SCHEMA, DOMUtil.getLocalName(element3) }, element3);
        } 
      } 
      if (!bool) {
        String str = DOMUtil.getSyntheticAnnotation(element2);
        if (str != null)
          schemaGrammar.addAnnotation(this.fElementTraverser.traverseSyntheticAnnotation(element2, str, xSDocumentInfo.getSchemaAttrs(), true, xSDocumentInfo)); 
      } 
      if (paramArrayList != null) {
        XSAnnotationInfo xSAnnotationInfo = xSDocumentInfo.getAnnotations();
        if (xSAnnotationInfo != null) {
          paramArrayList.add(doc2SystemId(element1));
          paramArrayList.add(xSAnnotationInfo);
        } 
      } 
      xSDocumentInfo.returnSchemaAttrs();
      DOMUtil.setHidden(element1, this.fHiddenNodes);
      Vector vector = (Vector)this.fDependencyMap.get(xSDocumentInfo);
      for (byte b = 0; b < vector.size(); b++)
        stack.push(vector.elementAt(b)); 
    } 
  }
  
  private final boolean needReportTNSError(String paramString) {
    if (this.fReportedTNS == null) {
      this.fReportedTNS = new Vector();
    } else if (this.fReportedTNS.contains(paramString)) {
      return false;
    } 
    this.fReportedTNS.addElement(paramString);
    return true;
  }
  
  void addGlobalAttributeDecl(XSAttributeDecl paramXSAttributeDecl) {
    String str1 = paramXSAttributeDecl.getNamespace();
    String str2 = (str1 == null || str1.length() == 0) ? ("," + paramXSAttributeDecl.getName()) : (str1 + "," + paramXSAttributeDecl.getName());
    if (this.fGlobalAttrDecls.get(str2) == null)
      this.fGlobalAttrDecls.put(str2, paramXSAttributeDecl); 
  }
  
  void addGlobalAttributeGroupDecl(XSAttributeGroupDecl paramXSAttributeGroupDecl) {
    String str1 = paramXSAttributeGroupDecl.getNamespace();
    String str2 = (str1 == null || str1.length() == 0) ? ("," + paramXSAttributeGroupDecl.getName()) : (str1 + "," + paramXSAttributeGroupDecl.getName());
    if (this.fGlobalAttrGrpDecls.get(str2) == null)
      this.fGlobalAttrGrpDecls.put(str2, paramXSAttributeGroupDecl); 
  }
  
  void addGlobalElementDecl(XSElementDecl paramXSElementDecl) {
    String str1 = paramXSElementDecl.getNamespace();
    String str2 = (str1 == null || str1.length() == 0) ? ("," + paramXSElementDecl.getName()) : (str1 + "," + paramXSElementDecl.getName());
    if (this.fGlobalElemDecls.get(str2) == null)
      this.fGlobalElemDecls.put(str2, paramXSElementDecl); 
  }
  
  void addGlobalGroupDecl(XSGroupDecl paramXSGroupDecl) {
    String str1 = paramXSGroupDecl.getNamespace();
    String str2 = (str1 == null || str1.length() == 0) ? ("," + paramXSGroupDecl.getName()) : (str1 + "," + paramXSGroupDecl.getName());
    if (this.fGlobalGroupDecls.get(str2) == null)
      this.fGlobalGroupDecls.put(str2, paramXSGroupDecl); 
  }
  
  void addGlobalNotationDecl(XSNotationDecl paramXSNotationDecl) {
    String str1 = paramXSNotationDecl.getNamespace();
    String str2 = (str1 == null || str1.length() == 0) ? ("," + paramXSNotationDecl.getName()) : (str1 + "," + paramXSNotationDecl.getName());
    if (this.fGlobalNotationDecls.get(str2) == null)
      this.fGlobalNotationDecls.put(str2, paramXSNotationDecl); 
  }
  
  void addGlobalTypeDecl(XSTypeDefinition paramXSTypeDefinition) {
    String str1 = paramXSTypeDefinition.getNamespace();
    String str2 = (str1 == null || str1.length() == 0) ? ("," + paramXSTypeDefinition.getName()) : (str1 + "," + paramXSTypeDefinition.getName());
    if (this.fGlobalTypeDecls.get(str2) == null)
      this.fGlobalTypeDecls.put(str2, paramXSTypeDefinition); 
  }
  
  void addIDConstraintDecl(IdentityConstraint paramIdentityConstraint) {
    String str1 = paramIdentityConstraint.getNamespace();
    String str2 = (str1 == null || str1.length() == 0) ? ("," + paramIdentityConstraint.getIdentityConstraintName()) : (str1 + "," + paramIdentityConstraint.getIdentityConstraintName());
    if (this.fGlobalIDConstraintDecls.get(str2) == null)
      this.fGlobalIDConstraintDecls.put(str2, paramIdentityConstraint); 
  }
  
  private XSAttributeDecl getGlobalAttributeDecl(String paramString) {
    return (XSAttributeDecl)this.fGlobalAttrDecls.get(paramString);
  }
  
  private XSAttributeGroupDecl getGlobalAttributeGroupDecl(String paramString) {
    return (XSAttributeGroupDecl)this.fGlobalAttrGrpDecls.get(paramString);
  }
  
  private XSElementDecl getGlobalElementDecl(String paramString) {
    return (XSElementDecl)this.fGlobalElemDecls.get(paramString);
  }
  
  private XSGroupDecl getGlobalGroupDecl(String paramString) {
    return (XSGroupDecl)this.fGlobalGroupDecls.get(paramString);
  }
  
  private XSNotationDecl getGlobalNotationDecl(String paramString) {
    return (XSNotationDecl)this.fGlobalNotationDecls.get(paramString);
  }
  
  private XSTypeDefinition getGlobalTypeDecl(String paramString) {
    return (XSTypeDefinition)this.fGlobalTypeDecls.get(paramString);
  }
  
  private IdentityConstraint getIDConstraintDecl(String paramString) {
    return (IdentityConstraint)this.fGlobalIDConstraintDecls.get(paramString);
  }
  
  protected Object getGlobalDecl(XSDocumentInfo paramXSDocumentInfo, int paramInt, QName paramQName, Element paramElement) {
    if (paramQName.uri != null && paramQName.uri == SchemaSymbols.URI_SCHEMAFORSCHEMA && paramInt == 7) {
      XSTypeDefinition xSTypeDefinition = SchemaGrammar.SG_SchemaNS.getGlobalTypeDecl(paramQName.localpart);
      if (xSTypeDefinition != null)
        return xSTypeDefinition; 
    } 
    if (!paramXSDocumentInfo.isAllowedNS(paramQName.uri) && paramXSDocumentInfo.needReportTNSError(paramQName.uri)) {
      String str1 = (paramQName.uri == null) ? "src-resolve.4.1" : "src-resolve.4.2";
      reportSchemaError(str1, new Object[] { this.fDoc2SystemId.get(paramXSDocumentInfo.fSchemaElement), paramQName.uri, paramQName.rawname }, paramElement);
    } 
    SchemaGrammar schemaGrammar = this.fGrammarBucket.getGrammar(paramQName.uri);
    if (schemaGrammar == null) {
      if (needReportTNSError(paramQName.uri))
        reportSchemaError("src-resolve", new Object[] { paramQName.rawname, COMP_TYPE[paramInt] }, paramElement); 
      return null;
    } 
    Object object = getGlobalDeclFromGrammar(schemaGrammar, paramInt, paramQName.localpart);
    String str = (paramQName.uri == null) ? ("," + paramQName.localpart) : (paramQName.uri + "," + paramQName.localpart);
    if (!this.fTolerateDuplicates) {
      if (object != null)
        return object; 
    } else {
      Object object1 = getGlobalDecl(str, paramInt);
      if (object1 != null)
        return object1; 
    } 
    XSDocumentInfo xSDocumentInfo1 = null;
    Element element = null;
    XSDocumentInfo xSDocumentInfo2 = null;
    switch (paramInt) {
      case 1:
        element = (Element)this.fUnparsedAttributeRegistry.get(str);
        xSDocumentInfo2 = (XSDocumentInfo)this.fUnparsedAttributeRegistrySub.get(str);
        break;
      case 2:
        element = (Element)this.fUnparsedAttributeGroupRegistry.get(str);
        xSDocumentInfo2 = (XSDocumentInfo)this.fUnparsedAttributeGroupRegistrySub.get(str);
        break;
      case 3:
        element = (Element)this.fUnparsedElementRegistry.get(str);
        xSDocumentInfo2 = (XSDocumentInfo)this.fUnparsedElementRegistrySub.get(str);
        break;
      case 4:
        element = (Element)this.fUnparsedGroupRegistry.get(str);
        xSDocumentInfo2 = (XSDocumentInfo)this.fUnparsedGroupRegistrySub.get(str);
        break;
      case 5:
        element = (Element)this.fUnparsedIdentityConstraintRegistry.get(str);
        xSDocumentInfo2 = (XSDocumentInfo)this.fUnparsedIdentityConstraintRegistrySub.get(str);
        break;
      case 6:
        element = (Element)this.fUnparsedNotationRegistry.get(str);
        xSDocumentInfo2 = (XSDocumentInfo)this.fUnparsedNotationRegistrySub.get(str);
        break;
      case 7:
        element = (Element)this.fUnparsedTypeRegistry.get(str);
        xSDocumentInfo2 = (XSDocumentInfo)this.fUnparsedTypeRegistrySub.get(str);
        break;
      default:
        reportSchemaError("Internal-Error", new Object[] { "XSDHandler asked to locate component of type " + paramInt + "; it does not recognize this type!" }, paramElement);
        break;
    } 
    if (element == null) {
      if (object == null)
        reportSchemaError("src-resolve", new Object[] { paramQName.rawname, COMP_TYPE[paramInt] }, paramElement); 
      return object;
    } 
    xSDocumentInfo1 = findXSDocumentForDecl(paramXSDocumentInfo, element, xSDocumentInfo2);
    if (xSDocumentInfo1 == null) {
      if (object == null) {
        String str1 = (paramQName.uri == null) ? "src-resolve.4.1" : "src-resolve.4.2";
        reportSchemaError(str1, new Object[] { this.fDoc2SystemId.get(paramXSDocumentInfo.fSchemaElement), paramQName.uri, paramQName.rawname }, paramElement);
      } 
      return object;
    } 
    if (DOMUtil.isHidden(element, this.fHiddenNodes)) {
      if (object == null) {
        String str1 = CIRCULAR_CODES[paramInt];
        if (paramInt == 7 && SchemaSymbols.ELT_COMPLEXTYPE.equals(DOMUtil.getLocalName(element)))
          str1 = "ct-props-correct.3"; 
        reportSchemaError(str1, new Object[] { paramQName.prefix + ":" + paramQName.localpart }, paramElement);
      } 
      return object;
    } 
    return traverseGlobalDecl(paramInt, element, xSDocumentInfo1, schemaGrammar);
  }
  
  protected Object getGlobalDecl(String paramString, int paramInt) {
    XSAttributeGroupDecl xSAttributeGroupDecl;
    XSElementDecl xSElementDecl;
    XSGroupDecl xSGroupDecl;
    IdentityConstraint identityConstraint;
    XSNotationDecl xSNotationDecl;
    XSTypeDefinition xSTypeDefinition;
    XSAttributeDecl xSAttributeDecl = null;
    switch (paramInt) {
      case 1:
        xSAttributeDecl = getGlobalAttributeDecl(paramString);
        break;
      case 2:
        xSAttributeGroupDecl = getGlobalAttributeGroupDecl(paramString);
        break;
      case 3:
        xSElementDecl = getGlobalElementDecl(paramString);
        break;
      case 4:
        xSGroupDecl = getGlobalGroupDecl(paramString);
        break;
      case 5:
        identityConstraint = getIDConstraintDecl(paramString);
        break;
      case 6:
        xSNotationDecl = getGlobalNotationDecl(paramString);
        break;
      case 7:
        xSTypeDefinition = getGlobalTypeDecl(paramString);
        break;
    } 
    return xSTypeDefinition;
  }
  
  protected Object getGlobalDeclFromGrammar(SchemaGrammar paramSchemaGrammar, int paramInt, String paramString) {
    XSAttributeGroupDecl xSAttributeGroupDecl;
    XSElementDecl xSElementDecl;
    XSGroupDecl xSGroupDecl;
    IdentityConstraint identityConstraint;
    XSNotationDecl xSNotationDecl;
    XSTypeDefinition xSTypeDefinition;
    XSAttributeDecl xSAttributeDecl = null;
    switch (paramInt) {
      case 1:
        xSAttributeDecl = paramSchemaGrammar.getGlobalAttributeDecl(paramString);
        break;
      case 2:
        xSAttributeGroupDecl = paramSchemaGrammar.getGlobalAttributeGroupDecl(paramString);
        break;
      case 3:
        xSElementDecl = paramSchemaGrammar.getGlobalElementDecl(paramString);
        break;
      case 4:
        xSGroupDecl = paramSchemaGrammar.getGlobalGroupDecl(paramString);
        break;
      case 5:
        identityConstraint = paramSchemaGrammar.getIDConstraintDecl(paramString);
        break;
      case 6:
        xSNotationDecl = paramSchemaGrammar.getGlobalNotationDecl(paramString);
        break;
      case 7:
        xSTypeDefinition = paramSchemaGrammar.getGlobalTypeDecl(paramString);
        break;
    } 
    return xSTypeDefinition;
  }
  
  protected Object getGlobalDeclFromGrammar(SchemaGrammar paramSchemaGrammar, int paramInt, String paramString1, String paramString2) {
    XSAttributeGroupDecl xSAttributeGroupDecl;
    XSElementDecl xSElementDecl;
    XSGroupDecl xSGroupDecl;
    IdentityConstraint identityConstraint;
    XSNotationDecl xSNotationDecl;
    XSTypeDefinition xSTypeDefinition;
    XSAttributeDecl xSAttributeDecl = null;
    switch (paramInt) {
      case 1:
        xSAttributeDecl = paramSchemaGrammar.getGlobalAttributeDecl(paramString1, paramString2);
        break;
      case 2:
        xSAttributeGroupDecl = paramSchemaGrammar.getGlobalAttributeGroupDecl(paramString1, paramString2);
        break;
      case 3:
        xSElementDecl = paramSchemaGrammar.getGlobalElementDecl(paramString1, paramString2);
        break;
      case 4:
        xSGroupDecl = paramSchemaGrammar.getGlobalGroupDecl(paramString1, paramString2);
        break;
      case 5:
        identityConstraint = paramSchemaGrammar.getIDConstraintDecl(paramString1, paramString2);
        break;
      case 6:
        xSNotationDecl = paramSchemaGrammar.getGlobalNotationDecl(paramString1, paramString2);
        break;
      case 7:
        xSTypeDefinition = paramSchemaGrammar.getGlobalTypeDecl(paramString1, paramString2);
        break;
    } 
    return xSTypeDefinition;
  }
  
  protected Object traverseGlobalDecl(int paramInt, Element paramElement, XSDocumentInfo paramXSDocumentInfo, SchemaGrammar paramSchemaGrammar) {
    XSSimpleType xSSimpleType;
    XSAttributeDecl xSAttributeDecl;
    XSElementDecl xSElementDecl;
    XSAttributeGroupDecl xSAttributeGroupDecl;
    XSGroupDecl xSGroupDecl;
    XSNotationDecl xSNotationDecl;
    XSComplexTypeDecl xSComplexTypeDecl = null;
    DOMUtil.setHidden(paramElement, this.fHiddenNodes);
    SchemaNamespaceSupport schemaNamespaceSupport = null;
    Element element = DOMUtil.getParent(paramElement);
    if (DOMUtil.getLocalName(element).equals(SchemaSymbols.ELT_REDEFINE))
      schemaNamespaceSupport = (SchemaNamespaceSupport)this.fRedefine2NSSupport.get(element); 
    paramXSDocumentInfo.backupNSSupport(schemaNamespaceSupport);
    switch (paramInt) {
      case 7:
        if (DOMUtil.getLocalName(paramElement).equals(SchemaSymbols.ELT_COMPLEXTYPE)) {
          xSComplexTypeDecl = this.fComplexTypeTraverser.traverseGlobal(paramElement, paramXSDocumentInfo, paramSchemaGrammar);
          break;
        } 
        xSSimpleType = this.fSimpleTypeTraverser.traverseGlobal(paramElement, paramXSDocumentInfo, paramSchemaGrammar);
        break;
      case 1:
        xSAttributeDecl = this.fAttributeTraverser.traverseGlobal(paramElement, paramXSDocumentInfo, paramSchemaGrammar);
        break;
      case 3:
        xSElementDecl = this.fElementTraverser.traverseGlobal(paramElement, paramXSDocumentInfo, paramSchemaGrammar);
        break;
      case 2:
        xSAttributeGroupDecl = this.fAttributeGroupTraverser.traverseGlobal(paramElement, paramXSDocumentInfo, paramSchemaGrammar);
        break;
      case 4:
        xSGroupDecl = this.fGroupTraverser.traverseGlobal(paramElement, paramXSDocumentInfo, paramSchemaGrammar);
        break;
      case 6:
        xSNotationDecl = this.fNotationTraverser.traverse(paramElement, paramXSDocumentInfo, paramSchemaGrammar);
        break;
    } 
    paramXSDocumentInfo.restoreNSSupport();
    return xSNotationDecl;
  }
  
  public String schemaDocument2SystemId(XSDocumentInfo paramXSDocumentInfo) {
    return (String)this.fDoc2SystemId.get(paramXSDocumentInfo.fSchemaElement);
  }
  
  Object getGrpOrAttrGrpRedefinedByRestriction(int paramInt, QName paramQName, XSDocumentInfo paramXSDocumentInfo, Element paramElement) {
    String str1 = (paramQName.uri != null) ? (paramQName.uri + "," + paramQName.localpart) : ("," + paramQName.localpart);
    String str2 = null;
    switch (paramInt) {
      case 2:
        str2 = (String)this.fRedefinedRestrictedAttributeGroupRegistry.get(str1);
        break;
      case 4:
        str2 = (String)this.fRedefinedRestrictedGroupRegistry.get(str1);
        break;
      default:
        return null;
    } 
    if (str2 == null)
      return null; 
    int i = str2.indexOf(",");
    QName qName = new QName(XMLSymbols.EMPTY_STRING, str2.substring(i + 1), str2.substring(i), (i == 0) ? null : str2.substring(0, i));
    Object object = getGlobalDecl(paramXSDocumentInfo, paramInt, qName, paramElement);
    if (object == null) {
      switch (paramInt) {
        case 2:
          reportSchemaError("src-redefine.7.2.1", new Object[] { paramQName.localpart }, paramElement);
          break;
        case 4:
          reportSchemaError("src-redefine.6.2.1", new Object[] { paramQName.localpart }, paramElement);
          break;
      } 
      return null;
    } 
    return object;
  }
  
  protected void resolveKeyRefs() {
    for (byte b = 0; b < this.fKeyrefStackPos; b++) {
      XSDocumentInfo xSDocumentInfo = this.fKeyrefsMapXSDocumentInfo[b];
      xSDocumentInfo.fNamespaceSupport.makeGlobal();
      xSDocumentInfo.fNamespaceSupport.setEffectiveContext(this.fKeyrefNamespaceContext[b]);
      SchemaGrammar schemaGrammar = this.fGrammarBucket.getGrammar(xSDocumentInfo.fTargetNamespace);
      DOMUtil.setHidden(this.fKeyrefs[b], this.fHiddenNodes);
      this.fKeyrefTraverser.traverse(this.fKeyrefs[b], this.fKeyrefElems[b], xSDocumentInfo, schemaGrammar);
    } 
  }
  
  protected Hashtable getIDRegistry() {
    return this.fUnparsedIdentityConstraintRegistry;
  }
  
  protected Hashtable getIDRegistry_sub() {
    return this.fUnparsedIdentityConstraintRegistrySub;
  }
  
  protected void storeKeyRef(Element paramElement, XSDocumentInfo paramXSDocumentInfo, XSElementDecl paramXSElementDecl) {
    String str = DOMUtil.getAttrValue(paramElement, SchemaSymbols.ATT_NAME);
    if (str.length() != 0) {
      String str1 = (paramXSDocumentInfo.fTargetNamespace == null) ? ("," + str) : (paramXSDocumentInfo.fTargetNamespace + "," + str);
      checkForDuplicateNames(str1, 5, this.fUnparsedIdentityConstraintRegistry, this.fUnparsedIdentityConstraintRegistrySub, paramElement, paramXSDocumentInfo);
    } 
    if (this.fKeyrefStackPos == this.fKeyrefs.length) {
      Element[] arrayOfElement = new Element[this.fKeyrefStackPos + 2];
      System.arraycopy(this.fKeyrefs, 0, arrayOfElement, 0, this.fKeyrefStackPos);
      this.fKeyrefs = arrayOfElement;
      XSElementDecl[] arrayOfXSElementDecl = new XSElementDecl[this.fKeyrefStackPos + 2];
      System.arraycopy(this.fKeyrefElems, 0, arrayOfXSElementDecl, 0, this.fKeyrefStackPos);
      this.fKeyrefElems = arrayOfXSElementDecl;
      String[][] arrayOfString = new String[this.fKeyrefStackPos + 2][];
      System.arraycopy(this.fKeyrefNamespaceContext, 0, arrayOfString, 0, this.fKeyrefStackPos);
      this.fKeyrefNamespaceContext = arrayOfString;
      XSDocumentInfo[] arrayOfXSDocumentInfo = new XSDocumentInfo[this.fKeyrefStackPos + 2];
      System.arraycopy(this.fKeyrefsMapXSDocumentInfo, 0, arrayOfXSDocumentInfo, 0, this.fKeyrefStackPos);
      this.fKeyrefsMapXSDocumentInfo = arrayOfXSDocumentInfo;
    } 
    this.fKeyrefs[this.fKeyrefStackPos] = paramElement;
    this.fKeyrefElems[this.fKeyrefStackPos] = paramXSElementDecl;
    this.fKeyrefNamespaceContext[this.fKeyrefStackPos] = paramXSDocumentInfo.fNamespaceSupport.getEffectiveLocalContext();
    this.fKeyrefsMapXSDocumentInfo[this.fKeyrefStackPos++] = paramXSDocumentInfo;
  }
  
  private Element resolveSchema(XSDDescription paramXSDDescription, boolean paramBoolean1, Element paramElement, boolean paramBoolean2) {
    XMLInputSource xMLInputSource = null;
    try {
      Hashtable hashtable = paramBoolean2 ? this.fLocationPairs : EMPTY_TABLE;
      xMLInputSource = XMLSchemaLoader.resolveDocument(paramXSDDescription, hashtable, this.fEntityResolver);
    } catch (IOException iOException) {
      if (paramBoolean1) {
        reportSchemaError("schema_reference.4", new Object[] { paramXSDDescription.getLocationHints()[0] }, paramElement);
      } else {
        reportSchemaWarning("schema_reference.4", new Object[] { paramXSDDescription.getLocationHints()[0] }, paramElement);
      } 
    } 
    return (xMLInputSource instanceof DOMInputSource) ? getSchemaDocument(paramXSDDescription.getTargetNamespace(), (DOMInputSource)xMLInputSource, paramBoolean1, paramXSDDescription.getContextType(), paramElement) : ((xMLInputSource instanceof SAXInputSource) ? getSchemaDocument(paramXSDDescription.getTargetNamespace(), (SAXInputSource)xMLInputSource, paramBoolean1, paramXSDDescription.getContextType(), paramElement) : ((xMLInputSource instanceof StAXInputSource) ? getSchemaDocument(paramXSDDescription.getTargetNamespace(), (StAXInputSource)xMLInputSource, paramBoolean1, paramXSDDescription.getContextType(), paramElement) : ((xMLInputSource instanceof XSInputSource) ? getSchemaDocument((XSInputSource)xMLInputSource, paramXSDDescription) : getSchemaDocument(paramXSDDescription.getTargetNamespace(), xMLInputSource, paramBoolean1, paramXSDDescription.getContextType(), paramElement))));
  }
  
  private Element resolveSchema(XMLInputSource paramXMLInputSource, XSDDescription paramXSDDescription, boolean paramBoolean, Element paramElement) {
    return (paramXMLInputSource instanceof DOMInputSource) ? getSchemaDocument(paramXSDDescription.getTargetNamespace(), (DOMInputSource)paramXMLInputSource, paramBoolean, paramXSDDescription.getContextType(), paramElement) : ((paramXMLInputSource instanceof SAXInputSource) ? getSchemaDocument(paramXSDDescription.getTargetNamespace(), (SAXInputSource)paramXMLInputSource, paramBoolean, paramXSDDescription.getContextType(), paramElement) : ((paramXMLInputSource instanceof StAXInputSource) ? getSchemaDocument(paramXSDDescription.getTargetNamespace(), (StAXInputSource)paramXMLInputSource, paramBoolean, paramXSDDescription.getContextType(), paramElement) : ((paramXMLInputSource instanceof XSInputSource) ? getSchemaDocument((XSInputSource)paramXMLInputSource, paramXSDDescription) : getSchemaDocument(paramXSDDescription.getTargetNamespace(), paramXMLInputSource, paramBoolean, paramXSDDescription.getContextType(), paramElement))));
  }
  
  private XMLInputSource resolveSchemaSource(XSDDescription paramXSDDescription, boolean paramBoolean1, Element paramElement, boolean paramBoolean2) {
    XMLInputSource xMLInputSource = null;
    try {
      Hashtable hashtable = paramBoolean2 ? this.fLocationPairs : EMPTY_TABLE;
      xMLInputSource = XMLSchemaLoader.resolveDocument(paramXSDDescription, hashtable, this.fEntityResolver);
    } catch (IOException iOException) {
      if (paramBoolean1) {
        reportSchemaError("schema_reference.4", new Object[] { paramXSDDescription.getLocationHints()[0] }, paramElement);
      } else {
        reportSchemaWarning("schema_reference.4", new Object[] { paramXSDDescription.getLocationHints()[0] }, paramElement);
      } 
    } 
    return xMLInputSource;
  }
  
  private Element getSchemaDocument(String paramString, XMLInputSource paramXMLInputSource, boolean paramBoolean, short paramShort, Element paramElement) {
    boolean bool = true;
    IOException iOException = null;
    Element element = null;
    try {
      if (paramXMLInputSource != null && (paramXMLInputSource.getSystemId() != null || paramXMLInputSource.getByteStream() != null || paramXMLInputSource.getCharacterStream() != null)) {
        XSDKey xSDKey = null;
        String str = null;
        str = XMLEntityManager.expandSystemId(paramXMLInputSource.getSystemId(), paramXMLInputSource.getBaseSystemId(), false);
        xSDKey = new XSDKey(str, paramShort, paramString);
        if (paramShort != 3 && (element = (Element)this.fTraversed.get(xSDKey)) != null) {
          this.fLastSchemaWasDuplicate = true;
          return element;
        } 
        this.fSchemaParser.parse(paramXMLInputSource);
        Document document = this.fSchemaParser.getDocument();
        element = (document != null) ? DOMUtil.getRoot(document) : null;
        return getSchemaDocument0(xSDKey, str, element);
      } 
      bool = false;
    } catch (IOException iOException1) {
      iOException = iOException1;
    } 
    return getSchemaDocument1(paramBoolean, bool, paramXMLInputSource, paramElement, iOException);
  }
  
  private Element getSchemaDocument(String paramString, SAXInputSource paramSAXInputSource, boolean paramBoolean, short paramShort, Element paramElement) {
    XMLReader xMLReader = paramSAXInputSource.getXMLReader();
    InputSource inputSource = paramSAXInputSource.getInputSource();
    boolean bool = true;
    IOException iOException = null;
    Element element = null;
    try {
      if (inputSource != null && (inputSource.getSystemId() != null || inputSource.getByteStream() != null || inputSource.getCharacterStream() != null)) {
        SAXParser sAXParser;
        XSDKey xSDKey = null;
        String str = null;
        str = XMLEntityManager.expandSystemId(inputSource.getSystemId(), paramSAXInputSource.getBaseSystemId(), false);
        xSDKey = new XSDKey(str, paramShort, paramString);
        if (paramShort != 3 && (element = (Element)this.fTraversed.get(xSDKey)) != null) {
          this.fLastSchemaWasDuplicate = true;
          return element;
        } 
        boolean bool1 = false;
        if (xMLReader != null) {
          try {
            bool1 = xMLReader.getFeature("http://xml.org/sax/features/namespace-prefixes");
          } catch (SAXException sAXException) {}
        } else {
          try {
            xMLReader = XMLReaderFactory.createXMLReader();
          } catch (SAXException sAXException) {
            sAXParser = new SAXParser();
          } 
          try {
            sAXParser.setFeature("http://xml.org/sax/features/namespace-prefixes", true);
            bool1 = true;
            if (sAXParser instanceof SAXParser) {
              Object object = this.fSchemaParser.getProperty("http://apache.org/xml/properties/security-manager");
              if (object != null)
                sAXParser.setProperty("http://apache.org/xml/properties/security-manager", object); 
            } 
          } catch (SAXException sAXException) {}
        } 
        boolean bool2 = false;
        try {
          bool2 = sAXParser.getFeature("http://xml.org/sax/features/string-interning");
        } catch (SAXException sAXException) {}
        if (this.fXSContentHandler == null)
          this.fXSContentHandler = new SchemaContentHandler(); 
        this.fXSContentHandler.reset(this.fSchemaParser, this.fSymbolTable, bool1, bool2);
        sAXParser.setContentHandler(this.fXSContentHandler);
        sAXParser.setErrorHandler(this.fErrorReporter.getSAXErrorHandler());
        sAXParser.parse(inputSource);
        try {
          sAXParser.setContentHandler(null);
          sAXParser.setErrorHandler(null);
        } catch (Exception exception) {}
        Document document = this.fXSContentHandler.getDocument();
        element = (document != null) ? DOMUtil.getRoot(document) : null;
        return getSchemaDocument0(xSDKey, str, element);
      } 
      bool = false;
    } catch (SAXParseException sAXParseException) {
      throw SAX2XNIUtil.createXMLParseException0(sAXParseException);
    } catch (SAXException sAXException) {
      throw SAX2XNIUtil.createXNIException0(sAXException);
    } catch (IOException iOException1) {
      iOException = iOException1;
    } 
    return getSchemaDocument1(paramBoolean, bool, (XMLInputSource)paramSAXInputSource, paramElement, iOException);
  }
  
  private Element getSchemaDocument(String paramString, DOMInputSource paramDOMInputSource, boolean paramBoolean, short paramShort, Element paramElement) {
    boolean bool = true;
    IOException iOException = null;
    Element element1 = null;
    Element element2 = null;
    Node node = paramDOMInputSource.getNode();
    short s = -1;
    if (node != null) {
      s = node.getNodeType();
      if (s == 9) {
        element2 = DOMUtil.getRoot((Document)node);
      } else if (s == 1) {
        element2 = (Element)node;
      } 
    } 
    try {
      if (element2 != null) {
        XSDKey xSDKey = null;
        String str = null;
        if (paramShort != 3) {
          str = XMLEntityManager.expandSystemId(paramDOMInputSource.getSystemId(), paramDOMInputSource.getBaseSystemId(), false);
          boolean bool1 = (s == 9) ? true : false;
          if (!bool1) {
            Node node1 = element2.getParentNode();
            if (node1 != null)
              bool1 = (node1.getNodeType() == 9) ? true : false; 
          } 
          xSDKey = new XSDKey(str, paramShort, paramString);
          if (bool1 && (element1 = (Element)this.fTraversed.get(xSDKey)) != null) {
            this.fLastSchemaWasDuplicate = true;
            return element1;
          } 
        } 
        element1 = element2;
        return getSchemaDocument0(xSDKey, str, element1);
      } 
      bool = false;
    } catch (IOException iOException1) {
      iOException = iOException1;
    } 
    return getSchemaDocument1(paramBoolean, bool, (XMLInputSource)paramDOMInputSource, paramElement, iOException);
  }
  
  private Element getSchemaDocument(String paramString, StAXInputSource paramStAXInputSource, boolean paramBoolean, short paramShort, Element paramElement) {
    IOException iOException = null;
    Element element = null;
    try {
      boolean bool = paramStAXInputSource.shouldConsumeRemainingContent();
      XMLStreamReader xMLStreamReader = paramStAXInputSource.getXMLStreamReader();
      XMLEventReader xMLEventReader = paramStAXInputSource.getXMLEventReader();
      XSDKey xSDKey = null;
      String str = null;
      if (paramShort != 3) {
        str = XMLEntityManager.expandSystemId(paramStAXInputSource.getSystemId(), paramStAXInputSource.getBaseSystemId(), false);
        boolean bool1 = bool;
        if (!bool1)
          if (xMLStreamReader != null) {
            bool1 = (xMLStreamReader.getEventType() == 7);
          } else {
            bool1 = xMLEventReader.peek().isStartDocument();
          }  
        xSDKey = new XSDKey(str, paramShort, paramString);
        if (bool1 && (element = (Element)this.fTraversed.get(xSDKey)) != null) {
          this.fLastSchemaWasDuplicate = true;
          return element;
        } 
      } 
      if (this.fStAXSchemaParser == null)
        this.fStAXSchemaParser = new StAXSchemaParser(); 
      this.fStAXSchemaParser.reset(this.fSchemaParser, this.fSymbolTable);
      if (xMLStreamReader != null) {
        this.fStAXSchemaParser.parse(xMLStreamReader);
        if (bool)
          while (xMLStreamReader.hasNext())
            xMLStreamReader.next();  
      } else {
        this.fStAXSchemaParser.parse(xMLEventReader);
        if (bool)
          while (xMLEventReader.hasNext())
            xMLEventReader.nextEvent();  
      } 
      Document document = this.fStAXSchemaParser.getDocument();
      element = (document != null) ? DOMUtil.getRoot(document) : null;
      return getSchemaDocument0(xSDKey, str, element);
    } catch (XMLStreamException xMLStreamException) {
      Throwable throwable = xMLStreamException.getNestedException();
      if (throwable instanceof IOException) {
        iOException = (IOException)throwable;
      } else {
        StAXLocationWrapper stAXLocationWrapper = new StAXLocationWrapper();
        stAXLocationWrapper.setLocation(xMLStreamException.getLocation());
        throw new XMLParseException(stAXLocationWrapper, xMLStreamException.getMessage(), xMLStreamException);
      } 
    } catch (IOException iOException1) {
      iOException = iOException1;
    } 
    return getSchemaDocument1(paramBoolean, true, (XMLInputSource)paramStAXInputSource, paramElement, iOException);
  }
  
  private Element getSchemaDocument0(XSDKey paramXSDKey, String paramString, Element paramElement) {
    if (paramXSDKey != null)
      this.fTraversed.put(paramXSDKey, paramElement); 
    if (paramString != null)
      this.fDoc2SystemId.put(paramElement, paramString); 
    this.fLastSchemaWasDuplicate = false;
    return paramElement;
  }
  
  private Element getSchemaDocument1(boolean paramBoolean1, boolean paramBoolean2, XMLInputSource paramXMLInputSource, Element paramElement, IOException paramIOException) {
    if (paramBoolean1) {
      if (paramBoolean2) {
        reportSchemaError("schema_reference.4", new Object[] { paramXMLInputSource.getSystemId() }, paramElement, paramIOException);
      } else {
        reportSchemaError("schema_reference.4", new Object[] { (paramXMLInputSource == null) ? "" : paramXMLInputSource.getSystemId() }, paramElement, paramIOException);
      } 
    } else if (paramBoolean2) {
      reportSchemaWarning("schema_reference.4", new Object[] { paramXMLInputSource.getSystemId() }, paramElement, paramIOException);
    } 
    this.fLastSchemaWasDuplicate = false;
    return null;
  }
  
  private Element getSchemaDocument(XSInputSource paramXSInputSource, XSDDescription paramXSDDescription) {
    SchemaGrammar[] arrayOfSchemaGrammar = paramXSInputSource.getGrammars();
    short s = paramXSDDescription.getContextType();
    if (arrayOfSchemaGrammar != null && arrayOfSchemaGrammar.length > 0) {
      Vector vector = expandGrammars(arrayOfSchemaGrammar);
      if (this.fNamespaceGrowth || !existingGrammars(vector)) {
        addGrammars(vector);
        if (s == 3)
          paramXSDDescription.setTargetNamespace(arrayOfSchemaGrammar[0].getTargetNamespace()); 
      } 
    } else {
      XSObject[] arrayOfXSObject = paramXSInputSource.getComponents();
      if (arrayOfXSObject != null && arrayOfXSObject.length > 0) {
        Hashtable hashtable = new Hashtable();
        Vector vector = expandComponents(arrayOfXSObject, hashtable);
        if (this.fNamespaceGrowth || canAddComponents(vector)) {
          addGlobalComponents(vector, hashtable);
          if (s == 3)
            paramXSDDescription.setTargetNamespace(arrayOfXSObject[0].getNamespace()); 
        } 
      } 
    } 
    return null;
  }
  
  private Vector expandGrammars(SchemaGrammar[] paramArrayOfSchemaGrammar) {
    Vector vector = new Vector();
    for (byte b1 = 0; b1 < paramArrayOfSchemaGrammar.length; b1++) {
      if (!vector.contains(paramArrayOfSchemaGrammar[b1]))
        vector.add(paramArrayOfSchemaGrammar[b1]); 
    } 
    for (byte b2 = 0; b2 < vector.size(); b2++) {
      SchemaGrammar schemaGrammar = vector.elementAt(b2);
      Vector vector1 = schemaGrammar.getImportedGrammars();
      if (vector1 != null)
        for (int i = vector1.size() - 1; i >= 0; i--) {
          SchemaGrammar schemaGrammar1 = vector1.elementAt(i);
          if (!vector.contains(schemaGrammar1))
            vector.addElement(schemaGrammar1); 
        }  
    } 
    return vector;
  }
  
  private boolean existingGrammars(Vector paramVector) {
    int i = paramVector.size();
    XSDDescription xSDDescription = new XSDDescription();
    for (byte b = 0; b < i; b++) {
      SchemaGrammar schemaGrammar1 = paramVector.elementAt(b);
      xSDDescription.setNamespace(schemaGrammar1.getTargetNamespace());
      SchemaGrammar schemaGrammar2 = findGrammar(xSDDescription, false);
      if (schemaGrammar2 != null)
        return true; 
    } 
    return false;
  }
  
  private boolean canAddComponents(Vector paramVector) {
    int i = paramVector.size();
    XSDDescription xSDDescription = new XSDDescription();
    for (byte b = 0; b < i; b++) {
      XSObject xSObject = paramVector.elementAt(b);
      if (!canAddComponent(xSObject, xSDDescription))
        return false; 
    } 
    return true;
  }
  
  private boolean canAddComponent(XSObject paramXSObject, XSDDescription paramXSDDescription) {
    paramXSDDescription.setNamespace(paramXSObject.getNamespace());
    SchemaGrammar schemaGrammar = findGrammar(paramXSDDescription, false);
    if (schemaGrammar == null)
      return true; 
    if (schemaGrammar.isImmutable())
      return false; 
    short s = paramXSObject.getType();
    String str = paramXSObject.getName();
    switch (s) {
      case 3:
        return (schemaGrammar.getGlobalTypeDecl(str) == paramXSObject);
      case 1:
        return (schemaGrammar.getGlobalAttributeDecl(str) == paramXSObject);
      case 5:
        return (schemaGrammar.getGlobalAttributeDecl(str) == paramXSObject);
      case 2:
        return (schemaGrammar.getGlobalElementDecl(str) == paramXSObject);
      case 6:
        return (schemaGrammar.getGlobalGroupDecl(str) == paramXSObject);
      case 11:
        return (schemaGrammar.getGlobalNotationDecl(str) == paramXSObject);
    } 
    return true;
  }
  
  private void addGrammars(Vector paramVector) {
    int i = paramVector.size();
    XSDDescription xSDDescription = new XSDDescription();
    for (byte b = 0; b < i; b++) {
      SchemaGrammar schemaGrammar1 = paramVector.elementAt(b);
      xSDDescription.setNamespace(schemaGrammar1.getTargetNamespace());
      SchemaGrammar schemaGrammar2 = findGrammar(xSDDescription, this.fNamespaceGrowth);
      if (schemaGrammar1 != schemaGrammar2)
        addGrammarComponents(schemaGrammar1, schemaGrammar2); 
    } 
  }
  
  private void addGrammarComponents(SchemaGrammar paramSchemaGrammar1, SchemaGrammar paramSchemaGrammar2) {
    if (paramSchemaGrammar2 == null) {
      createGrammarFrom(paramSchemaGrammar1);
      return;
    } 
    SchemaGrammar schemaGrammar = paramSchemaGrammar2;
    if (schemaGrammar.isImmutable())
      schemaGrammar = createGrammarFrom(paramSchemaGrammar2); 
    addNewGrammarLocations(paramSchemaGrammar1, schemaGrammar);
    addNewImportedGrammars(paramSchemaGrammar1, schemaGrammar);
    addNewGrammarComponents(paramSchemaGrammar1, schemaGrammar);
  }
  
  private SchemaGrammar createGrammarFrom(SchemaGrammar paramSchemaGrammar) {
    SchemaGrammar schemaGrammar = new SchemaGrammar(paramSchemaGrammar);
    this.fGrammarBucket.putGrammar(schemaGrammar);
    updateImportListWith(schemaGrammar);
    updateImportListFor(schemaGrammar);
    return schemaGrammar;
  }
  
  private void addNewGrammarLocations(SchemaGrammar paramSchemaGrammar1, SchemaGrammar paramSchemaGrammar2) {
    StringList stringList1 = paramSchemaGrammar1.getDocumentLocations();
    int i = stringList1.size();
    StringList stringList2 = paramSchemaGrammar2.getDocumentLocations();
    for (byte b = 0; b < i; b++) {
      String str = stringList1.item(b);
      if (!stringList2.contains(str))
        paramSchemaGrammar2.addDocument(null, str); 
    } 
  }
  
  private void addNewImportedGrammars(SchemaGrammar paramSchemaGrammar1, SchemaGrammar paramSchemaGrammar2) {
    Vector vector = paramSchemaGrammar1.getImportedGrammars();
    if (vector != null) {
      Vector vector1 = paramSchemaGrammar2.getImportedGrammars();
      if (vector1 == null) {
        vector1 = (Vector)vector.clone();
        paramSchemaGrammar2.setImportedGrammars(vector1);
      } else {
        updateImportList(vector, vector1);
      } 
    } 
  }
  
  private void updateImportList(Vector paramVector1, Vector paramVector2) {
    int i = paramVector1.size();
    for (byte b = 0; b < i; b++) {
      SchemaGrammar schemaGrammar = paramVector1.elementAt(b);
      if (!containedImportedGrammar(paramVector2, schemaGrammar))
        paramVector2.add(schemaGrammar); 
    } 
  }
  
  private void addNewGrammarComponents(SchemaGrammar paramSchemaGrammar1, SchemaGrammar paramSchemaGrammar2) {
    paramSchemaGrammar2.resetComponents();
    addGlobalElementDecls(paramSchemaGrammar1, paramSchemaGrammar2);
    addGlobalAttributeDecls(paramSchemaGrammar1, paramSchemaGrammar2);
    addGlobalAttributeGroupDecls(paramSchemaGrammar1, paramSchemaGrammar2);
    addGlobalGroupDecls(paramSchemaGrammar1, paramSchemaGrammar2);
    addGlobalTypeDecls(paramSchemaGrammar1, paramSchemaGrammar2);
    addGlobalNotationDecls(paramSchemaGrammar1, paramSchemaGrammar2);
  }
  
  private void addGlobalElementDecls(SchemaGrammar paramSchemaGrammar1, SchemaGrammar paramSchemaGrammar2) {
    XSNamedMap xSNamedMap = paramSchemaGrammar1.getComponents((short)2);
    int i = xSNamedMap.getLength();
    for (byte b1 = 0; b1 < i; b1++) {
      XSElementDecl xSElementDecl1 = (XSElementDecl)xSNamedMap.item(b1);
      XSElementDecl xSElementDecl2 = paramSchemaGrammar2.getGlobalElementDecl(xSElementDecl1.getName());
      if (xSElementDecl2 == null) {
        paramSchemaGrammar2.addGlobalElementDecl(xSElementDecl1);
      } else if (xSElementDecl2 != xSElementDecl1) {
      
      } 
    } 
    ObjectList objectList = paramSchemaGrammar1.getComponentsExt((short)2);
    i = objectList.getLength();
    for (byte b2 = 0; b2 < i; b2 += 2) {
      String str1 = (String)objectList.item(b2);
      int j = str1.indexOf(',');
      String str2 = str1.substring(0, j);
      String str3 = str1.substring(j + 1, str1.length());
      XSElementDecl xSElementDecl1 = (XSElementDecl)objectList.item(b2 + 1);
      XSElementDecl xSElementDecl2 = paramSchemaGrammar2.getGlobalElementDecl(str3, str2);
      if (xSElementDecl2 == null) {
        paramSchemaGrammar2.addGlobalElementDecl(xSElementDecl1, str2);
      } else if (xSElementDecl2 != xSElementDecl1) {
      
      } 
    } 
  }
  
  private void addGlobalAttributeDecls(SchemaGrammar paramSchemaGrammar1, SchemaGrammar paramSchemaGrammar2) {
    XSNamedMap xSNamedMap = paramSchemaGrammar1.getComponents((short)1);
    int i = xSNamedMap.getLength();
    for (byte b1 = 0; b1 < i; b1++) {
      XSAttributeDecl xSAttributeDecl1 = (XSAttributeDecl)xSNamedMap.item(b1);
      XSAttributeDecl xSAttributeDecl2 = paramSchemaGrammar2.getGlobalAttributeDecl(xSAttributeDecl1.getName());
      if (xSAttributeDecl2 == null) {
        paramSchemaGrammar2.addGlobalAttributeDecl(xSAttributeDecl1);
      } else if (xSAttributeDecl2 != xSAttributeDecl1 && !this.fTolerateDuplicates) {
        reportSharingError(xSAttributeDecl1.getNamespace(), xSAttributeDecl1.getName());
      } 
    } 
    ObjectList objectList = paramSchemaGrammar1.getComponentsExt((short)1);
    i = objectList.getLength();
    for (byte b2 = 0; b2 < i; b2 += 2) {
      String str1 = (String)objectList.item(b2);
      int j = str1.indexOf(',');
      String str2 = str1.substring(0, j);
      String str3 = str1.substring(j + 1, str1.length());
      XSAttributeDecl xSAttributeDecl1 = (XSAttributeDecl)objectList.item(b2 + 1);
      XSAttributeDecl xSAttributeDecl2 = paramSchemaGrammar2.getGlobalAttributeDecl(str3, str2);
      if (xSAttributeDecl2 == null) {
        paramSchemaGrammar2.addGlobalAttributeDecl(xSAttributeDecl1, str2);
      } else if (xSAttributeDecl2 != xSAttributeDecl1) {
      
      } 
    } 
  }
  
  private void addGlobalAttributeGroupDecls(SchemaGrammar paramSchemaGrammar1, SchemaGrammar paramSchemaGrammar2) {
    XSNamedMap xSNamedMap = paramSchemaGrammar1.getComponents((short)5);
    int i = xSNamedMap.getLength();
    for (byte b1 = 0; b1 < i; b1++) {
      XSAttributeGroupDecl xSAttributeGroupDecl1 = (XSAttributeGroupDecl)xSNamedMap.item(b1);
      XSAttributeGroupDecl xSAttributeGroupDecl2 = paramSchemaGrammar2.getGlobalAttributeGroupDecl(xSAttributeGroupDecl1.getName());
      if (xSAttributeGroupDecl2 == null) {
        paramSchemaGrammar2.addGlobalAttributeGroupDecl(xSAttributeGroupDecl1);
      } else if (xSAttributeGroupDecl2 != xSAttributeGroupDecl1 && !this.fTolerateDuplicates) {
        reportSharingError(xSAttributeGroupDecl1.getNamespace(), xSAttributeGroupDecl1.getName());
      } 
    } 
    ObjectList objectList = paramSchemaGrammar1.getComponentsExt((short)5);
    i = objectList.getLength();
    for (byte b2 = 0; b2 < i; b2 += 2) {
      String str1 = (String)objectList.item(b2);
      int j = str1.indexOf(',');
      String str2 = str1.substring(0, j);
      String str3 = str1.substring(j + 1, str1.length());
      XSAttributeGroupDecl xSAttributeGroupDecl1 = (XSAttributeGroupDecl)objectList.item(b2 + 1);
      XSAttributeGroupDecl xSAttributeGroupDecl2 = paramSchemaGrammar2.getGlobalAttributeGroupDecl(str3, str2);
      if (xSAttributeGroupDecl2 == null) {
        paramSchemaGrammar2.addGlobalAttributeGroupDecl(xSAttributeGroupDecl1, str2);
      } else if (xSAttributeGroupDecl2 != xSAttributeGroupDecl1) {
      
      } 
    } 
  }
  
  private void addGlobalNotationDecls(SchemaGrammar paramSchemaGrammar1, SchemaGrammar paramSchemaGrammar2) {
    XSNamedMap xSNamedMap = paramSchemaGrammar1.getComponents((short)11);
    int i = xSNamedMap.getLength();
    for (byte b1 = 0; b1 < i; b1++) {
      XSNotationDecl xSNotationDecl1 = (XSNotationDecl)xSNamedMap.item(b1);
      XSNotationDecl xSNotationDecl2 = paramSchemaGrammar2.getGlobalNotationDecl(xSNotationDecl1.getName());
      if (xSNotationDecl2 == null) {
        paramSchemaGrammar2.addGlobalNotationDecl(xSNotationDecl1);
      } else if (xSNotationDecl2 != xSNotationDecl1 && !this.fTolerateDuplicates) {
        reportSharingError(xSNotationDecl1.getNamespace(), xSNotationDecl1.getName());
      } 
    } 
    ObjectList objectList = paramSchemaGrammar1.getComponentsExt((short)11);
    i = objectList.getLength();
    for (byte b2 = 0; b2 < i; b2 += 2) {
      String str1 = (String)objectList.item(b2);
      int j = str1.indexOf(',');
      String str2 = str1.substring(0, j);
      String str3 = str1.substring(j + 1, str1.length());
      XSNotationDecl xSNotationDecl1 = (XSNotationDecl)objectList.item(b2 + 1);
      XSNotationDecl xSNotationDecl2 = paramSchemaGrammar2.getGlobalNotationDecl(str3, str2);
      if (xSNotationDecl2 == null) {
        paramSchemaGrammar2.addGlobalNotationDecl(xSNotationDecl1, str2);
      } else if (xSNotationDecl2 != xSNotationDecl1) {
      
      } 
    } 
  }
  
  private void addGlobalGroupDecls(SchemaGrammar paramSchemaGrammar1, SchemaGrammar paramSchemaGrammar2) {
    XSNamedMap xSNamedMap = paramSchemaGrammar1.getComponents((short)6);
    int i = xSNamedMap.getLength();
    for (byte b1 = 0; b1 < i; b1++) {
      XSGroupDecl xSGroupDecl1 = (XSGroupDecl)xSNamedMap.item(b1);
      XSGroupDecl xSGroupDecl2 = paramSchemaGrammar2.getGlobalGroupDecl(xSGroupDecl1.getName());
      if (xSGroupDecl2 == null) {
        paramSchemaGrammar2.addGlobalGroupDecl(xSGroupDecl1);
      } else if (xSGroupDecl1 != xSGroupDecl2 && !this.fTolerateDuplicates) {
        reportSharingError(xSGroupDecl1.getNamespace(), xSGroupDecl1.getName());
      } 
    } 
    ObjectList objectList = paramSchemaGrammar1.getComponentsExt((short)6);
    i = objectList.getLength();
    for (byte b2 = 0; b2 < i; b2 += 2) {
      String str1 = (String)objectList.item(b2);
      int j = str1.indexOf(',');
      String str2 = str1.substring(0, j);
      String str3 = str1.substring(j + 1, str1.length());
      XSGroupDecl xSGroupDecl1 = (XSGroupDecl)objectList.item(b2 + 1);
      XSGroupDecl xSGroupDecl2 = paramSchemaGrammar2.getGlobalGroupDecl(str3, str2);
      if (xSGroupDecl2 == null) {
        paramSchemaGrammar2.addGlobalGroupDecl(xSGroupDecl1, str2);
      } else if (xSGroupDecl2 != xSGroupDecl1) {
      
      } 
    } 
  }
  
  private void addGlobalTypeDecls(SchemaGrammar paramSchemaGrammar1, SchemaGrammar paramSchemaGrammar2) {
    XSNamedMap xSNamedMap = paramSchemaGrammar1.getComponents((short)3);
    int i = xSNamedMap.getLength();
    for (byte b1 = 0; b1 < i; b1++) {
      XSTypeDefinition xSTypeDefinition1 = (XSTypeDefinition)xSNamedMap.item(b1);
      XSTypeDefinition xSTypeDefinition2 = paramSchemaGrammar2.getGlobalTypeDecl(xSTypeDefinition1.getName());
      if (xSTypeDefinition2 == null) {
        paramSchemaGrammar2.addGlobalTypeDecl(xSTypeDefinition1);
      } else if (xSTypeDefinition2 != xSTypeDefinition1 && !this.fTolerateDuplicates) {
        reportSharingError(xSTypeDefinition1.getNamespace(), xSTypeDefinition1.getName());
      } 
    } 
    ObjectList objectList = paramSchemaGrammar1.getComponentsExt((short)3);
    i = objectList.getLength();
    for (byte b2 = 0; b2 < i; b2 += 2) {
      String str1 = (String)objectList.item(b2);
      int j = str1.indexOf(',');
      String str2 = str1.substring(0, j);
      String str3 = str1.substring(j + 1, str1.length());
      XSTypeDefinition xSTypeDefinition1 = (XSTypeDefinition)objectList.item(b2 + 1);
      XSTypeDefinition xSTypeDefinition2 = paramSchemaGrammar2.getGlobalTypeDecl(str3, str2);
      if (xSTypeDefinition2 == null) {
        paramSchemaGrammar2.addGlobalTypeDecl(xSTypeDefinition1, str2);
      } else if (xSTypeDefinition2 != xSTypeDefinition1) {
      
      } 
    } 
  }
  
  private Vector expandComponents(XSObject[] paramArrayOfXSObject, Hashtable paramHashtable) {
    Vector vector = new Vector();
    for (byte b1 = 0; b1 < paramArrayOfXSObject.length; b1++) {
      if (!vector.contains(paramArrayOfXSObject[b1]))
        vector.add(paramArrayOfXSObject[b1]); 
    } 
    for (byte b2 = 0; b2 < vector.size(); b2++) {
      XSObject xSObject = vector.elementAt(b2);
      expandRelatedComponents(xSObject, vector, paramHashtable);
    } 
    return vector;
  }
  
  private void expandRelatedComponents(XSObject paramXSObject, Vector paramVector, Hashtable paramHashtable) {
    short s = paramXSObject.getType();
    switch (s) {
      case 3:
        expandRelatedTypeComponents((XSTypeDefinition)paramXSObject, paramVector, paramXSObject.getNamespace(), paramHashtable);
        break;
      case 1:
        expandRelatedAttributeComponents((XSAttributeDeclaration)paramXSObject, paramVector, paramXSObject.getNamespace(), paramHashtable);
        break;
      case 5:
        expandRelatedAttributeGroupComponents((XSAttributeGroupDefinition)paramXSObject, paramVector, paramXSObject.getNamespace(), paramHashtable);
      case 2:
        expandRelatedElementComponents((XSElementDeclaration)paramXSObject, paramVector, paramXSObject.getNamespace(), paramHashtable);
        break;
      case 6:
        expandRelatedModelGroupDefinitionComponents((XSModelGroupDefinition)paramXSObject, paramVector, paramXSObject.getNamespace(), paramHashtable);
        break;
    } 
  }
  
  private void expandRelatedAttributeComponents(XSAttributeDeclaration paramXSAttributeDeclaration, Vector paramVector, String paramString, Hashtable paramHashtable) {
    addRelatedType((XSTypeDefinition)paramXSAttributeDeclaration.getTypeDefinition(), paramVector, paramString, paramHashtable);
  }
  
  private void expandRelatedElementComponents(XSElementDeclaration paramXSElementDeclaration, Vector paramVector, String paramString, Hashtable paramHashtable) {
    addRelatedType(paramXSElementDeclaration.getTypeDefinition(), paramVector, paramString, paramHashtable);
    XSElementDeclaration xSElementDeclaration = paramXSElementDeclaration.getSubstitutionGroupAffiliation();
    if (xSElementDeclaration != null)
      addRelatedElement(xSElementDeclaration, paramVector, paramString, paramHashtable); 
  }
  
  private void expandRelatedTypeComponents(XSTypeDefinition paramXSTypeDefinition, Vector paramVector, String paramString, Hashtable paramHashtable) {
    if (paramXSTypeDefinition instanceof XSComplexTypeDecl) {
      expandRelatedComplexTypeComponents((XSComplexTypeDecl)paramXSTypeDefinition, paramVector, paramString, paramHashtable);
    } else if (paramXSTypeDefinition instanceof org.apache.xerces.impl.dv.xs.XSSimpleTypeDecl) {
      expandRelatedSimpleTypeComponents((XSSimpleTypeDefinition)paramXSTypeDefinition, paramVector, paramString, paramHashtable);
    } 
  }
  
  private void expandRelatedModelGroupDefinitionComponents(XSModelGroupDefinition paramXSModelGroupDefinition, Vector paramVector, String paramString, Hashtable paramHashtable) {
    expandRelatedModelGroupComponents(paramXSModelGroupDefinition.getModelGroup(), paramVector, paramString, paramHashtable);
  }
  
  private void expandRelatedAttributeGroupComponents(XSAttributeGroupDefinition paramXSAttributeGroupDefinition, Vector paramVector, String paramString, Hashtable paramHashtable) {
    expandRelatedAttributeUsesComponents(paramXSAttributeGroupDefinition.getAttributeUses(), paramVector, paramString, paramHashtable);
  }
  
  private void expandRelatedComplexTypeComponents(XSComplexTypeDecl paramXSComplexTypeDecl, Vector paramVector, String paramString, Hashtable paramHashtable) {
    addRelatedType(paramXSComplexTypeDecl.getBaseType(), paramVector, paramString, paramHashtable);
    expandRelatedAttributeUsesComponents(paramXSComplexTypeDecl.getAttributeUses(), paramVector, paramString, paramHashtable);
    XSParticle xSParticle = paramXSComplexTypeDecl.getParticle();
    if (xSParticle != null)
      expandRelatedParticleComponents(xSParticle, paramVector, paramString, paramHashtable); 
  }
  
  private void expandRelatedSimpleTypeComponents(XSSimpleTypeDefinition paramXSSimpleTypeDefinition, Vector paramVector, String paramString, Hashtable paramHashtable) {
    XSTypeDefinition xSTypeDefinition = paramXSSimpleTypeDefinition.getBaseType();
    if (xSTypeDefinition != null)
      addRelatedType(xSTypeDefinition, paramVector, paramString, paramHashtable); 
    XSSimpleTypeDefinition xSSimpleTypeDefinition1 = paramXSSimpleTypeDefinition.getItemType();
    if (xSSimpleTypeDefinition1 != null)
      addRelatedType((XSTypeDefinition)xSSimpleTypeDefinition1, paramVector, paramString, paramHashtable); 
    XSSimpleTypeDefinition xSSimpleTypeDefinition2 = paramXSSimpleTypeDefinition.getPrimitiveType();
    if (xSSimpleTypeDefinition2 != null)
      addRelatedType((XSTypeDefinition)xSSimpleTypeDefinition2, paramVector, paramString, paramHashtable); 
    XSObjectList xSObjectList = paramXSSimpleTypeDefinition.getMemberTypes();
    if (xSObjectList.size() > 0)
      for (byte b = 0; b < xSObjectList.size(); b++)
        addRelatedType((XSTypeDefinition)xSObjectList.item(b), paramVector, paramString, paramHashtable);  
  }
  
  private void expandRelatedAttributeUsesComponents(XSObjectList paramXSObjectList, Vector paramVector, String paramString, Hashtable paramHashtable) {
    byte b1 = (paramXSObjectList == null) ? 0 : paramXSObjectList.size();
    for (byte b2 = 0; b2 < b1; b2++)
      expandRelatedAttributeUseComponents((XSAttributeUse)paramXSObjectList.item(b2), paramVector, paramString, paramHashtable); 
  }
  
  private void expandRelatedAttributeUseComponents(XSAttributeUse paramXSAttributeUse, Vector paramVector, String paramString, Hashtable paramHashtable) {
    addRelatedAttribute(paramXSAttributeUse.getAttrDeclaration(), paramVector, paramString, paramHashtable);
  }
  
  private void expandRelatedParticleComponents(XSParticle paramXSParticle, Vector paramVector, String paramString, Hashtable paramHashtable) {
    XSTerm xSTerm = paramXSParticle.getTerm();
    switch (xSTerm.getType()) {
      case 2:
        addRelatedElement((XSElementDeclaration)xSTerm, paramVector, paramString, paramHashtable);
        break;
      case 7:
        expandRelatedModelGroupComponents((XSModelGroup)xSTerm, paramVector, paramString, paramHashtable);
        break;
    } 
  }
  
  private void expandRelatedModelGroupComponents(XSModelGroup paramXSModelGroup, Vector paramVector, String paramString, Hashtable paramHashtable) {
    XSObjectList xSObjectList = paramXSModelGroup.getParticles();
    byte b1 = (xSObjectList == null) ? 0 : xSObjectList.getLength();
    for (byte b2 = 0; b2 < b1; b2++)
      expandRelatedParticleComponents((XSParticle)xSObjectList.item(b2), paramVector, paramString, paramHashtable); 
  }
  
  private void addRelatedType(XSTypeDefinition paramXSTypeDefinition, Vector paramVector, String paramString, Hashtable paramHashtable) {
    if (!paramXSTypeDefinition.getAnonymous()) {
      if (!paramXSTypeDefinition.getNamespace().equals(SchemaSymbols.URI_SCHEMAFORSCHEMA) && !paramVector.contains(paramXSTypeDefinition)) {
        Vector vector = findDependentNamespaces(paramString, paramHashtable);
        addNamespaceDependency(paramString, paramXSTypeDefinition.getNamespace(), vector);
        paramVector.add(paramXSTypeDefinition);
      } 
    } else {
      expandRelatedTypeComponents(paramXSTypeDefinition, paramVector, paramString, paramHashtable);
    } 
  }
  
  private void addRelatedElement(XSElementDeclaration paramXSElementDeclaration, Vector paramVector, String paramString, Hashtable paramHashtable) {
    if (paramXSElementDeclaration.getScope() == 1) {
      if (!paramVector.contains(paramXSElementDeclaration)) {
        Vector vector = findDependentNamespaces(paramString, paramHashtable);
        addNamespaceDependency(paramString, paramXSElementDeclaration.getNamespace(), vector);
        paramVector.add(paramXSElementDeclaration);
      } 
    } else {
      expandRelatedElementComponents(paramXSElementDeclaration, paramVector, paramString, paramHashtable);
    } 
  }
  
  private void addRelatedAttribute(XSAttributeDeclaration paramXSAttributeDeclaration, Vector paramVector, String paramString, Hashtable paramHashtable) {
    if (paramXSAttributeDeclaration.getScope() == 1) {
      if (!paramVector.contains(paramXSAttributeDeclaration)) {
        Vector vector = findDependentNamespaces(paramString, paramHashtable);
        addNamespaceDependency(paramString, paramXSAttributeDeclaration.getNamespace(), vector);
        paramVector.add(paramXSAttributeDeclaration);
      } 
    } else {
      expandRelatedAttributeComponents(paramXSAttributeDeclaration, paramVector, paramString, paramHashtable);
    } 
  }
  
  private void addGlobalComponents(Vector paramVector, Hashtable paramHashtable) {
    XSDDescription xSDDescription = new XSDDescription();
    int i = paramVector.size();
    for (byte b = 0; b < i; b++)
      addGlobalComponent(paramVector.elementAt(b), xSDDescription); 
    updateImportDependencies(paramHashtable);
  }
  
  private void addGlobalComponent(XSObject paramXSObject, XSDDescription paramXSDDescription) {
    String str1 = paramXSObject.getNamespace();
    paramXSDDescription.setNamespace(str1);
    SchemaGrammar schemaGrammar = getSchemaGrammar(paramXSDDescription);
    short s = paramXSObject.getType();
    String str2 = paramXSObject.getName();
    switch (s) {
      case 3:
        if (!((XSTypeDefinition)paramXSObject).getAnonymous()) {
          if (schemaGrammar.getGlobalTypeDecl(str2) == null)
            schemaGrammar.addGlobalTypeDecl((XSTypeDefinition)paramXSObject); 
          if (schemaGrammar.getGlobalTypeDecl(str2, "") == null)
            schemaGrammar.addGlobalTypeDecl((XSTypeDefinition)paramXSObject, ""); 
        } 
        break;
      case 1:
        if (((XSAttributeDecl)paramXSObject).getScope() == 1) {
          if (schemaGrammar.getGlobalAttributeDecl(str2) == null)
            schemaGrammar.addGlobalAttributeDecl((XSAttributeDecl)paramXSObject); 
          if (schemaGrammar.getGlobalAttributeDecl(str2, "") == null)
            schemaGrammar.addGlobalAttributeDecl((XSAttributeDecl)paramXSObject, ""); 
        } 
        break;
      case 5:
        if (schemaGrammar.getGlobalAttributeDecl(str2) == null)
          schemaGrammar.addGlobalAttributeGroupDecl((XSAttributeGroupDecl)paramXSObject); 
        if (schemaGrammar.getGlobalAttributeDecl(str2, "") == null)
          schemaGrammar.addGlobalAttributeGroupDecl((XSAttributeGroupDecl)paramXSObject, ""); 
        break;
      case 2:
        if (((XSElementDecl)paramXSObject).getScope() == 1) {
          schemaGrammar.addGlobalElementDeclAll((XSElementDecl)paramXSObject);
          if (schemaGrammar.getGlobalElementDecl(str2) == null)
            schemaGrammar.addGlobalElementDecl((XSElementDecl)paramXSObject); 
          if (schemaGrammar.getGlobalElementDecl(str2, "") == null)
            schemaGrammar.addGlobalElementDecl((XSElementDecl)paramXSObject, ""); 
        } 
        break;
      case 6:
        if (schemaGrammar.getGlobalGroupDecl(str2) == null)
          schemaGrammar.addGlobalGroupDecl((XSGroupDecl)paramXSObject); 
        if (schemaGrammar.getGlobalGroupDecl(str2, "") == null)
          schemaGrammar.addGlobalGroupDecl((XSGroupDecl)paramXSObject, ""); 
        break;
      case 11:
        if (schemaGrammar.getGlobalNotationDecl(str2) == null)
          schemaGrammar.addGlobalNotationDecl((XSNotationDecl)paramXSObject); 
        if (schemaGrammar.getGlobalNotationDecl(str2, "") == null)
          schemaGrammar.addGlobalNotationDecl((XSNotationDecl)paramXSObject, ""); 
        break;
    } 
  }
  
  private void updateImportDependencies(Hashtable paramHashtable) {
    Enumeration enumeration = paramHashtable.keys();
    while (enumeration.hasMoreElements()) {
      String str = enumeration.nextElement();
      Vector vector = (Vector)paramHashtable.get(null2EmptyString(str));
      if (vector.size() > 0)
        expandImportList(str, vector); 
    } 
  }
  
  private void expandImportList(String paramString, Vector paramVector) {
    SchemaGrammar schemaGrammar = this.fGrammarBucket.getGrammar(paramString);
    if (schemaGrammar != null) {
      Vector vector = schemaGrammar.getImportedGrammars();
      if (vector == null) {
        vector = new Vector();
        addImportList(schemaGrammar, vector, paramVector);
        schemaGrammar.setImportedGrammars(vector);
      } else {
        updateImportList(schemaGrammar, vector, paramVector);
      } 
    } 
  }
  
  private void addImportList(SchemaGrammar paramSchemaGrammar, Vector paramVector1, Vector paramVector2) {
    int i = paramVector2.size();
    for (byte b = 0; b < i; b++) {
      SchemaGrammar schemaGrammar = this.fGrammarBucket.getGrammar(paramVector2.elementAt(b));
      if (schemaGrammar != null)
        paramVector1.add(schemaGrammar); 
    } 
  }
  
  private void updateImportList(SchemaGrammar paramSchemaGrammar, Vector paramVector1, Vector paramVector2) {
    int i = paramVector2.size();
    for (byte b = 0; b < i; b++) {
      SchemaGrammar schemaGrammar = this.fGrammarBucket.getGrammar(paramVector2.elementAt(b));
      if (schemaGrammar != null && !containedImportedGrammar(paramVector1, schemaGrammar))
        paramVector1.add(schemaGrammar); 
    } 
  }
  
  private boolean containedImportedGrammar(Vector paramVector, SchemaGrammar paramSchemaGrammar) {
    int i = paramVector.size();
    for (byte b = 0; b < i; b++) {
      SchemaGrammar schemaGrammar = paramVector.elementAt(b);
      if (null2EmptyString(schemaGrammar.getTargetNamespace()).equals(null2EmptyString(paramSchemaGrammar.getTargetNamespace())))
        return true; 
    } 
    return false;
  }
  
  private SchemaGrammar getSchemaGrammar(XSDDescription paramXSDDescription) {
    SchemaGrammar schemaGrammar = findGrammar(paramXSDDescription, this.fNamespaceGrowth);
    if (schemaGrammar == null) {
      schemaGrammar = new SchemaGrammar(paramXSDDescription.getNamespace(), paramXSDDescription.makeClone(), this.fSymbolTable);
      this.fGrammarBucket.putGrammar(schemaGrammar);
    } else if (schemaGrammar.isImmutable()) {
      schemaGrammar = createGrammarFrom(schemaGrammar);
    } 
    return schemaGrammar;
  }
  
  private Vector findDependentNamespaces(String paramString, Hashtable paramHashtable) {
    String str = null2EmptyString(paramString);
    Vector vector = (Vector)paramHashtable.get(str);
    if (vector == null) {
      vector = new Vector();
      paramHashtable.put(str, vector);
    } 
    return vector;
  }
  
  private void addNamespaceDependency(String paramString1, String paramString2, Vector paramVector) {
    String str1 = null2EmptyString(paramString1);
    String str2 = null2EmptyString(paramString2);
    if (!str1.equals(str2) && !paramVector.contains(str2))
      paramVector.add(str2); 
  }
  
  private void reportSharingError(String paramString1, String paramString2) {
    String str = (paramString1 == null) ? ("," + paramString2) : (paramString1 + "," + paramString2);
    reportSchemaError("sch-props-correct.2", new Object[] { str }, null);
  }
  
  private void createTraversers() {
    this.fAttributeChecker = new XSAttributeChecker(this);
    this.fAttributeGroupTraverser = new XSDAttributeGroupTraverser(this, this.fAttributeChecker);
    this.fAttributeTraverser = new XSDAttributeTraverser(this, this.fAttributeChecker);
    this.fComplexTypeTraverser = new XSDComplexTypeTraverser(this, this.fAttributeChecker);
    this.fElementTraverser = new XSDElementTraverser(this, this.fAttributeChecker);
    this.fGroupTraverser = new XSDGroupTraverser(this, this.fAttributeChecker);
    this.fKeyrefTraverser = new XSDKeyrefTraverser(this, this.fAttributeChecker);
    this.fNotationTraverser = new XSDNotationTraverser(this, this.fAttributeChecker);
    this.fSimpleTypeTraverser = new XSDSimpleTypeTraverser(this, this.fAttributeChecker);
    this.fUniqueOrKeyTraverser = new XSDUniqueOrKeyTraverser(this, this.fAttributeChecker);
    this.fWildCardTraverser = new XSDWildcardTraverser(this, this.fAttributeChecker);
  }
  
  void prepareForParse() {
    this.fTraversed.clear();
    this.fDoc2SystemId.clear();
    this.fHiddenNodes.clear();
    this.fLastSchemaWasDuplicate = false;
  }
  
  void prepareForTraverse() {
    this.fUnparsedAttributeRegistry.clear();
    this.fUnparsedAttributeGroupRegistry.clear();
    this.fUnparsedElementRegistry.clear();
    this.fUnparsedGroupRegistry.clear();
    this.fUnparsedIdentityConstraintRegistry.clear();
    this.fUnparsedNotationRegistry.clear();
    this.fUnparsedTypeRegistry.clear();
    this.fUnparsedAttributeRegistrySub.clear();
    this.fUnparsedAttributeGroupRegistrySub.clear();
    this.fUnparsedElementRegistrySub.clear();
    this.fUnparsedGroupRegistrySub.clear();
    this.fUnparsedIdentityConstraintRegistrySub.clear();
    this.fUnparsedNotationRegistrySub.clear();
    this.fUnparsedTypeRegistrySub.clear();
    for (byte b1 = 1; b1 <= 7; b1++)
      this.fUnparsedRegistriesExt[b1].clear(); 
    this.fXSDocumentInfoRegistry.clear();
    this.fDependencyMap.clear();
    this.fDoc2XSDocumentMap.clear();
    this.fRedefine2XSDMap.clear();
    this.fRedefine2NSSupport.clear();
    this.fAllTNSs.removeAllElements();
    this.fImportMap.clear();
    this.fRoot = null;
    for (byte b2 = 0; b2 < this.fLocalElemStackPos; b2++) {
      this.fParticle[b2] = null;
      this.fLocalElementDecl[b2] = null;
      this.fLocalElementDecl_schema[b2] = null;
      this.fLocalElemNamespaceContext[b2] = null;
    } 
    this.fLocalElemStackPos = 0;
    for (byte b3 = 0; b3 < this.fKeyrefStackPos; b3++) {
      this.fKeyrefs[b3] = null;
      this.fKeyrefElems[b3] = null;
      this.fKeyrefNamespaceContext[b3] = null;
      this.fKeyrefsMapXSDocumentInfo[b3] = null;
    } 
    this.fKeyrefStackPos = 0;
    if (this.fAttributeChecker == null)
      createTraversers(); 
    Locale locale = this.fErrorReporter.getLocale();
    this.fAttributeChecker.reset(this.fSymbolTable);
    this.fAttributeGroupTraverser.reset(this.fSymbolTable, this.fValidateAnnotations, locale);
    this.fAttributeTraverser.reset(this.fSymbolTable, this.fValidateAnnotations, locale);
    this.fComplexTypeTraverser.reset(this.fSymbolTable, this.fValidateAnnotations, locale);
    this.fElementTraverser.reset(this.fSymbolTable, this.fValidateAnnotations, locale);
    this.fGroupTraverser.reset(this.fSymbolTable, this.fValidateAnnotations, locale);
    this.fKeyrefTraverser.reset(this.fSymbolTable, this.fValidateAnnotations, locale);
    this.fNotationTraverser.reset(this.fSymbolTable, this.fValidateAnnotations, locale);
    this.fSimpleTypeTraverser.reset(this.fSymbolTable, this.fValidateAnnotations, locale);
    this.fUniqueOrKeyTraverser.reset(this.fSymbolTable, this.fValidateAnnotations, locale);
    this.fWildCardTraverser.reset(this.fSymbolTable, this.fValidateAnnotations, locale);
    this.fRedefinedRestrictedAttributeGroupRegistry.clear();
    this.fRedefinedRestrictedGroupRegistry.clear();
    this.fGlobalAttrDecls.clear();
    this.fGlobalAttrGrpDecls.clear();
    this.fGlobalElemDecls.clear();
    this.fGlobalGroupDecls.clear();
    this.fGlobalNotationDecls.clear();
    this.fGlobalIDConstraintDecls.clear();
    this.fGlobalTypeDecls.clear();
  }
  
  public void setDeclPool(XSDeclarationPool paramXSDeclarationPool) {
    this.fDeclPool = paramXSDeclarationPool;
  }
  
  public void setDVFactory(SchemaDVFactory paramSchemaDVFactory) {
    this.fDVFactory = paramSchemaDVFactory;
  }
  
  public void reset(XMLComponentManager paramXMLComponentManager) {
    this.fSymbolTable = (SymbolTable)paramXMLComponentManager.getProperty("http://apache.org/xml/properties/internal/symbol-table");
    this.fEntityResolver = (XMLEntityResolver)paramXMLComponentManager.getProperty("http://apache.org/xml/properties/internal/entity-manager");
    XMLEntityResolver xMLEntityResolver = (XMLEntityResolver)paramXMLComponentManager.getProperty("http://apache.org/xml/properties/internal/entity-resolver");
    if (xMLEntityResolver != null)
      this.fSchemaParser.setEntityResolver(xMLEntityResolver); 
    this.fErrorReporter = (XMLErrorReporter)paramXMLComponentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
    try {
      XMLErrorHandler xMLErrorHandler = this.fErrorReporter.getErrorHandler();
      if (xMLErrorHandler != this.fSchemaParser.getProperty("http://apache.org/xml/properties/internal/error-handler")) {
        this.fSchemaParser.setProperty("http://apache.org/xml/properties/internal/error-handler", (xMLErrorHandler != null) ? xMLErrorHandler : new DefaultErrorHandler());
        if (this.fAnnotationValidator != null)
          this.fAnnotationValidator.setProperty("http://apache.org/xml/properties/internal/error-handler", (xMLErrorHandler != null) ? xMLErrorHandler : new DefaultErrorHandler()); 
      } 
      Locale locale = this.fErrorReporter.getLocale();
      if (locale != this.fSchemaParser.getProperty("http://apache.org/xml/properties/locale")) {
        this.fSchemaParser.setProperty("http://apache.org/xml/properties/locale", locale);
        if (this.fAnnotationValidator != null)
          this.fAnnotationValidator.setProperty("http://apache.org/xml/properties/locale", locale); 
      } 
    } catch (XMLConfigurationException xMLConfigurationException) {}
    try {
      this.fValidateAnnotations = paramXMLComponentManager.getFeature("http://apache.org/xml/features/validate-annotations");
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fValidateAnnotations = false;
    } 
    try {
      this.fHonourAllSchemaLocations = paramXMLComponentManager.getFeature("http://apache.org/xml/features/honour-all-schemaLocations");
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fHonourAllSchemaLocations = false;
    } 
    try {
      this.fNamespaceGrowth = paramXMLComponentManager.getFeature("http://apache.org/xml/features/namespace-growth");
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fNamespaceGrowth = false;
    } 
    try {
      this.fTolerateDuplicates = paramXMLComponentManager.getFeature("http://apache.org/xml/features/internal/tolerate-duplicates");
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fTolerateDuplicates = false;
    } 
    try {
      this.fSchemaParser.setFeature("http://apache.org/xml/features/continue-after-fatal-error", this.fErrorReporter.getFeature("http://apache.org/xml/features/continue-after-fatal-error"));
    } catch (XMLConfigurationException xMLConfigurationException) {}
    try {
      this.fSchemaParser.setFeature("http://apache.org/xml/features/allow-java-encodings", paramXMLComponentManager.getFeature("http://apache.org/xml/features/allow-java-encodings"));
    } catch (XMLConfigurationException xMLConfigurationException) {}
    try {
      this.fSchemaParser.setFeature("http://apache.org/xml/features/standard-uri-conformant", paramXMLComponentManager.getFeature("http://apache.org/xml/features/standard-uri-conformant"));
    } catch (XMLConfigurationException xMLConfigurationException) {}
    try {
      this.fGrammarPool = (XMLGrammarPool)paramXMLComponentManager.getProperty("http://apache.org/xml/properties/internal/grammar-pool");
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fGrammarPool = null;
    } 
    try {
      this.fSchemaParser.setFeature("http://apache.org/xml/features/disallow-doctype-decl", paramXMLComponentManager.getFeature("http://apache.org/xml/features/disallow-doctype-decl"));
    } catch (XMLConfigurationException xMLConfigurationException) {}
    try {
      Object object = paramXMLComponentManager.getProperty("http://apache.org/xml/properties/security-manager");
      if (object != null)
        this.fSchemaParser.setProperty("http://apache.org/xml/properties/security-manager", object); 
    } catch (XMLConfigurationException xMLConfigurationException) {}
  }
  
  void traverseLocalElements() {
    this.fElementTraverser.fDeferTraversingLocalElements = false;
    for (byte b = 0; b < this.fLocalElemStackPos; b++) {
      Element element = this.fLocalElementDecl[b];
      XSDocumentInfo xSDocumentInfo = this.fLocalElementDecl_schema[b];
      SchemaGrammar schemaGrammar = this.fGrammarBucket.getGrammar(xSDocumentInfo.fTargetNamespace);
      this.fElementTraverser.traverseLocal(this.fParticle[b], element, xSDocumentInfo, schemaGrammar, this.fAllContext[b], this.fParent[b], this.fLocalElemNamespaceContext[b]);
      if ((this.fParticle[b]).fType == 0) {
        XSModelGroupImpl xSModelGroupImpl = null;
        if (this.fParent[b] instanceof XSComplexTypeDecl) {
          XSParticle xSParticle = ((XSComplexTypeDecl)this.fParent[b]).getParticle();
          if (xSParticle != null)
            xSModelGroupImpl = (XSModelGroupImpl)xSParticle.getTerm(); 
        } else {
          xSModelGroupImpl = ((XSGroupDecl)this.fParent[b]).fModelGroup;
        } 
        if (xSModelGroupImpl != null)
          removeParticle(xSModelGroupImpl, this.fParticle[b]); 
      } 
    } 
  }
  
  private boolean removeParticle(XSModelGroupImpl paramXSModelGroupImpl, XSParticleDecl paramXSParticleDecl) {
    for (byte b = 0; b < paramXSModelGroupImpl.fParticleCount; b++) {
      XSParticleDecl xSParticleDecl = paramXSModelGroupImpl.fParticles[b];
      if (xSParticleDecl == paramXSParticleDecl) {
        for (byte b1 = b; b1 < paramXSModelGroupImpl.fParticleCount - 1; b1++)
          paramXSModelGroupImpl.fParticles[b1] = paramXSModelGroupImpl.fParticles[b1 + 1]; 
        paramXSModelGroupImpl.fParticleCount--;
        return true;
      } 
      if (xSParticleDecl.fType == 3 && removeParticle((XSModelGroupImpl)xSParticleDecl.fValue, paramXSParticleDecl))
        return true; 
    } 
    return false;
  }
  
  void fillInLocalElemInfo(Element paramElement, XSDocumentInfo paramXSDocumentInfo, int paramInt, XSObject paramXSObject, XSParticleDecl paramXSParticleDecl) {
    if (this.fParticle.length == this.fLocalElemStackPos) {
      XSParticleDecl[] arrayOfXSParticleDecl = new XSParticleDecl[this.fLocalElemStackPos + 10];
      System.arraycopy(this.fParticle, 0, arrayOfXSParticleDecl, 0, this.fLocalElemStackPos);
      this.fParticle = arrayOfXSParticleDecl;
      Element[] arrayOfElement = new Element[this.fLocalElemStackPos + 10];
      System.arraycopy(this.fLocalElementDecl, 0, arrayOfElement, 0, this.fLocalElemStackPos);
      this.fLocalElementDecl = arrayOfElement;
      XSDocumentInfo[] arrayOfXSDocumentInfo = new XSDocumentInfo[this.fLocalElemStackPos + 10];
      System.arraycopy(this.fLocalElementDecl_schema, 0, arrayOfXSDocumentInfo, 0, this.fLocalElemStackPos);
      this.fLocalElementDecl_schema = arrayOfXSDocumentInfo;
      int[] arrayOfInt = new int[this.fLocalElemStackPos + 10];
      System.arraycopy(this.fAllContext, 0, arrayOfInt, 0, this.fLocalElemStackPos);
      this.fAllContext = arrayOfInt;
      XSObject[] arrayOfXSObject = new XSObject[this.fLocalElemStackPos + 10];
      System.arraycopy(this.fParent, 0, arrayOfXSObject, 0, this.fLocalElemStackPos);
      this.fParent = arrayOfXSObject;
      String[][] arrayOfString = new String[this.fLocalElemStackPos + 10][];
      System.arraycopy(this.fLocalElemNamespaceContext, 0, arrayOfString, 0, this.fLocalElemStackPos);
      this.fLocalElemNamespaceContext = arrayOfString;
    } 
    this.fParticle[this.fLocalElemStackPos] = paramXSParticleDecl;
    this.fLocalElementDecl[this.fLocalElemStackPos] = paramElement;
    this.fLocalElementDecl_schema[this.fLocalElemStackPos] = paramXSDocumentInfo;
    this.fAllContext[this.fLocalElemStackPos] = paramInt;
    this.fParent[this.fLocalElemStackPos] = paramXSObject;
    this.fLocalElemNamespaceContext[this.fLocalElemStackPos++] = paramXSDocumentInfo.fNamespaceSupport.getEffectiveLocalContext();
  }
  
  void checkForDuplicateNames(String paramString, int paramInt, Hashtable paramHashtable1, Hashtable paramHashtable2, Element paramElement, XSDocumentInfo paramXSDocumentInfo) {
    Object object = null;
    if ((object = paramHashtable1.get(paramString)) == null) {
      if (this.fNamespaceGrowth && !this.fTolerateDuplicates)
        checkForDuplicateNames(paramString, paramInt, paramElement); 
      paramHashtable1.put(paramString, paramElement);
      paramHashtable2.put(paramString, paramXSDocumentInfo);
    } else {
      Element element1 = (Element)object;
      XSDocumentInfo xSDocumentInfo1 = paramHashtable2.get(paramString);
      if (element1 == paramElement)
        return; 
      Element element2 = null;
      XSDocumentInfo xSDocumentInfo2 = null;
      boolean bool = true;
      if (DOMUtil.getLocalName(element2 = DOMUtil.getParent(element1)).equals(SchemaSymbols.ELT_REDEFINE)) {
        xSDocumentInfo2 = (XSDocumentInfo)this.fRedefine2XSDMap.get(element2);
      } else if (DOMUtil.getLocalName(DOMUtil.getParent(paramElement)).equals(SchemaSymbols.ELT_REDEFINE)) {
        xSDocumentInfo2 = xSDocumentInfo1;
        bool = false;
      } 
      if (xSDocumentInfo2 != null) {
        if (xSDocumentInfo1 == paramXSDocumentInfo) {
          reportSchemaError("sch-props-correct.2", new Object[] { paramString }, paramElement);
          return;
        } 
        String str = paramString.substring(paramString.lastIndexOf(',') + 1) + "_fn3dktizrknc9pi";
        if (xSDocumentInfo2 == paramXSDocumentInfo) {
          paramElement.setAttribute(SchemaSymbols.ATT_NAME, str);
          if (paramXSDocumentInfo.fTargetNamespace == null) {
            paramHashtable1.put("," + str, paramElement);
            paramHashtable2.put("," + str, paramXSDocumentInfo);
          } else {
            paramHashtable1.put(paramXSDocumentInfo.fTargetNamespace + "," + str, paramElement);
            paramHashtable2.put(paramXSDocumentInfo.fTargetNamespace + "," + str, paramXSDocumentInfo);
          } 
          if (paramXSDocumentInfo.fTargetNamespace == null) {
            checkForDuplicateNames("," + str, paramInt, paramHashtable1, paramHashtable2, paramElement, paramXSDocumentInfo);
          } else {
            checkForDuplicateNames(paramXSDocumentInfo.fTargetNamespace + "," + str, paramInt, paramHashtable1, paramHashtable2, paramElement, paramXSDocumentInfo);
          } 
        } else if (bool) {
          if (paramXSDocumentInfo.fTargetNamespace == null) {
            checkForDuplicateNames("," + str, paramInt, paramHashtable1, paramHashtable2, paramElement, paramXSDocumentInfo);
          } else {
            checkForDuplicateNames(paramXSDocumentInfo.fTargetNamespace + "," + str, paramInt, paramHashtable1, paramHashtable2, paramElement, paramXSDocumentInfo);
          } 
        } else {
          reportSchemaError("sch-props-correct.2", new Object[] { paramString }, paramElement);
        } 
      } else if (!this.fTolerateDuplicates || this.fUnparsedRegistriesExt[paramInt].get(paramString) == paramXSDocumentInfo) {
        reportSchemaError("sch-props-correct.2", new Object[] { paramString }, paramElement);
      } 
    } 
    if (this.fTolerateDuplicates)
      this.fUnparsedRegistriesExt[paramInt].put(paramString, paramXSDocumentInfo); 
  }
  
  void checkForDuplicateNames(String paramString, int paramInt, Element paramElement) {
    int i = paramString.indexOf(',');
    String str = paramString.substring(0, i);
    SchemaGrammar schemaGrammar = this.fGrammarBucket.getGrammar(emptyString2Null(str));
    if (schemaGrammar != null) {
      Object object = getGlobalDeclFromGrammar(schemaGrammar, paramInt, paramString.substring(i + 1));
      if (object != null)
        reportSchemaError("sch-props-correct.2", new Object[] { paramString }, paramElement); 
    } 
  }
  
  private void renameRedefiningComponents(XSDocumentInfo paramXSDocumentInfo, Element paramElement, String paramString1, String paramString2, String paramString3) {
    if (paramString1.equals(SchemaSymbols.ELT_SIMPLETYPE)) {
      Element element = DOMUtil.getFirstChildElement(paramElement);
      if (element == null) {
        reportSchemaError("src-redefine.5.a.a", null, paramElement);
      } else {
        String str = DOMUtil.getLocalName(element);
        if (str.equals(SchemaSymbols.ELT_ANNOTATION))
          element = DOMUtil.getNextSiblingElement(element); 
        if (element == null) {
          reportSchemaError("src-redefine.5.a.a", null, paramElement);
        } else {
          str = DOMUtil.getLocalName(element);
          if (!str.equals(SchemaSymbols.ELT_RESTRICTION)) {
            reportSchemaError("src-redefine.5.a.b", new Object[] { str }, paramElement);
          } else {
            Object[] arrayOfObject = this.fAttributeChecker.checkAttributes(element, false, paramXSDocumentInfo);
            QName qName = (QName)arrayOfObject[XSAttributeChecker.ATTIDX_BASE];
            if (qName == null || qName.uri != paramXSDocumentInfo.fTargetNamespace || !qName.localpart.equals(paramString2)) {
              reportSchemaError("src-redefine.5.a.c", new Object[] { str, ((paramXSDocumentInfo.fTargetNamespace == null) ? "" : paramXSDocumentInfo.fTargetNamespace) + "," + paramString2 }, paramElement);
            } else if (qName.prefix != null && qName.prefix.length() > 0) {
              element.setAttribute(SchemaSymbols.ATT_BASE, qName.prefix + ":" + paramString3);
            } else {
              element.setAttribute(SchemaSymbols.ATT_BASE, paramString3);
            } 
            this.fAttributeChecker.returnAttrArray(arrayOfObject, paramXSDocumentInfo);
          } 
        } 
      } 
    } else if (paramString1.equals(SchemaSymbols.ELT_COMPLEXTYPE)) {
      Element element = DOMUtil.getFirstChildElement(paramElement);
      if (element == null) {
        reportSchemaError("src-redefine.5.b.a", null, paramElement);
      } else {
        if (DOMUtil.getLocalName(element).equals(SchemaSymbols.ELT_ANNOTATION))
          element = DOMUtil.getNextSiblingElement(element); 
        if (element == null) {
          reportSchemaError("src-redefine.5.b.a", null, paramElement);
        } else {
          Element element1 = DOMUtil.getFirstChildElement(element);
          if (element1 == null) {
            reportSchemaError("src-redefine.5.b.b", null, element);
          } else {
            String str = DOMUtil.getLocalName(element1);
            if (str.equals(SchemaSymbols.ELT_ANNOTATION))
              element1 = DOMUtil.getNextSiblingElement(element1); 
            if (element1 == null) {
              reportSchemaError("src-redefine.5.b.b", null, element);
            } else {
              str = DOMUtil.getLocalName(element1);
              if (!str.equals(SchemaSymbols.ELT_RESTRICTION) && !str.equals(SchemaSymbols.ELT_EXTENSION)) {
                reportSchemaError("src-redefine.5.b.c", new Object[] { str }, element1);
              } else {
                Object[] arrayOfObject = this.fAttributeChecker.checkAttributes(element1, false, paramXSDocumentInfo);
                QName qName = (QName)arrayOfObject[XSAttributeChecker.ATTIDX_BASE];
                if (qName == null || qName.uri != paramXSDocumentInfo.fTargetNamespace || !qName.localpart.equals(paramString2)) {
                  reportSchemaError("src-redefine.5.b.d", new Object[] { str, ((paramXSDocumentInfo.fTargetNamespace == null) ? "" : paramXSDocumentInfo.fTargetNamespace) + "," + paramString2 }, element1);
                } else if (qName.prefix != null && qName.prefix.length() > 0) {
                  element1.setAttribute(SchemaSymbols.ATT_BASE, qName.prefix + ":" + paramString3);
                } else {
                  element1.setAttribute(SchemaSymbols.ATT_BASE, paramString3);
                } 
              } 
            } 
          } 
        } 
      } 
    } else if (paramString1.equals(SchemaSymbols.ELT_ATTRIBUTEGROUP)) {
      String str = (paramXSDocumentInfo.fTargetNamespace == null) ? ("," + paramString2) : (paramXSDocumentInfo.fTargetNamespace + "," + paramString2);
      int i = changeRedefineGroup(str, paramString1, paramString3, paramElement, paramXSDocumentInfo);
      if (i > 1) {
        reportSchemaError("src-redefine.7.1", new Object[] { new Integer(i) }, paramElement);
      } else if (i != 1) {
        if (paramXSDocumentInfo.fTargetNamespace == null) {
          this.fRedefinedRestrictedAttributeGroupRegistry.put(str, "," + paramString3);
        } else {
          this.fRedefinedRestrictedAttributeGroupRegistry.put(str, paramXSDocumentInfo.fTargetNamespace + "," + paramString3);
        } 
      } 
    } else if (paramString1.equals(SchemaSymbols.ELT_GROUP)) {
      String str = (paramXSDocumentInfo.fTargetNamespace == null) ? ("," + paramString2) : (paramXSDocumentInfo.fTargetNamespace + "," + paramString2);
      int i = changeRedefineGroup(str, paramString1, paramString3, paramElement, paramXSDocumentInfo);
      if (i > 1) {
        reportSchemaError("src-redefine.6.1.1", new Object[] { new Integer(i) }, paramElement);
      } else if (i != 1) {
        if (paramXSDocumentInfo.fTargetNamespace == null) {
          this.fRedefinedRestrictedGroupRegistry.put(str, "," + paramString3);
        } else {
          this.fRedefinedRestrictedGroupRegistry.put(str, paramXSDocumentInfo.fTargetNamespace + "," + paramString3);
        } 
      } 
    } else {
      reportSchemaError("Internal-Error", new Object[] { "could not handle this particular <redefine>; please submit your schemas and instance document in a bug report!" }, paramElement);
    } 
  }
  
  private String findQName(String paramString, XSDocumentInfo paramXSDocumentInfo) {
    SchemaNamespaceSupport schemaNamespaceSupport = paramXSDocumentInfo.fNamespaceSupport;
    int i = paramString.indexOf(':');
    String str1 = XMLSymbols.EMPTY_STRING;
    if (i > 0)
      str1 = paramString.substring(0, i); 
    String str2 = schemaNamespaceSupport.getURI(this.fSymbolTable.addSymbol(str1));
    String str3 = (i == 0) ? paramString : paramString.substring(i + 1);
    if (str1 == XMLSymbols.EMPTY_STRING && str2 == null && paramXSDocumentInfo.fIsChameleonSchema)
      str2 = paramXSDocumentInfo.fTargetNamespace; 
    return (str2 == null) ? ("," + str3) : (str2 + "," + str3);
  }
  
  private int changeRedefineGroup(String paramString1, String paramString2, String paramString3, Element paramElement, XSDocumentInfo paramXSDocumentInfo) {
    int i = 0;
    for (Element element = DOMUtil.getFirstChildElement(paramElement); element != null; element = DOMUtil.getNextSiblingElement(element)) {
      String str = DOMUtil.getLocalName(element);
      if (!str.equals(paramString2)) {
        i += changeRedefineGroup(paramString1, paramString2, paramString3, element, paramXSDocumentInfo);
      } else {
        String str1 = element.getAttribute(SchemaSymbols.ATT_REF);
        if (str1.length() != 0) {
          String str2 = findQName(str1, paramXSDocumentInfo);
          if (paramString1.equals(str2)) {
            String str3 = XMLSymbols.EMPTY_STRING;
            int j = str1.indexOf(":");
            if (j > 0) {
              str3 = str1.substring(0, j);
              element.setAttribute(SchemaSymbols.ATT_REF, str3 + ":" + paramString3);
            } else {
              element.setAttribute(SchemaSymbols.ATT_REF, paramString3);
            } 
            i++;
            if (paramString2.equals(SchemaSymbols.ELT_GROUP)) {
              String str4 = element.getAttribute(SchemaSymbols.ATT_MINOCCURS);
              String str5 = element.getAttribute(SchemaSymbols.ATT_MAXOCCURS);
              if ((str5.length() != 0 && !str5.equals("1")) || (str4.length() != 0 && !str4.equals("1")))
                reportSchemaError("src-redefine.6.1.2", new Object[] { str1 }, element); 
            } 
          } 
        } 
      } 
    } 
    return i;
  }
  
  private XSDocumentInfo findXSDocumentForDecl(XSDocumentInfo paramXSDocumentInfo1, Element paramElement, XSDocumentInfo paramXSDocumentInfo2) {
    XSDocumentInfo xSDocumentInfo = paramXSDocumentInfo2;
    return (xSDocumentInfo == null) ? null : xSDocumentInfo;
  }
  
  private boolean nonAnnotationContent(Element paramElement) {
    for (Element element = DOMUtil.getFirstChildElement(paramElement); element != null; element = DOMUtil.getNextSiblingElement(element)) {
      if (!DOMUtil.getLocalName(element).equals(SchemaSymbols.ELT_ANNOTATION))
        return true; 
    } 
    return false;
  }
  
  private void setSchemasVisible(XSDocumentInfo paramXSDocumentInfo) {
    if (DOMUtil.isHidden(paramXSDocumentInfo.fSchemaElement, this.fHiddenNodes)) {
      DOMUtil.setVisible(paramXSDocumentInfo.fSchemaElement, this.fHiddenNodes);
      Vector vector = (Vector)this.fDependencyMap.get(paramXSDocumentInfo);
      for (byte b = 0; b < vector.size(); b++)
        setSchemasVisible(vector.elementAt(b)); 
    } 
  }
  
  public SimpleLocator element2Locator(Element paramElement) {
    if (!(paramElement instanceof ElementImpl))
      return null; 
    SimpleLocator simpleLocator = new SimpleLocator();
    return element2Locator(paramElement, simpleLocator) ? simpleLocator : null;
  }
  
  public boolean element2Locator(Element paramElement, SimpleLocator paramSimpleLocator) {
    if (paramSimpleLocator == null)
      return false; 
    if (paramElement instanceof ElementImpl) {
      ElementImpl elementImpl = (ElementImpl)paramElement;
      Document document = elementImpl.getOwnerDocument();
      String str = (String)this.fDoc2SystemId.get(DOMUtil.getRoot(document));
      int i = elementImpl.getLineNumber();
      int j = elementImpl.getColumnNumber();
      paramSimpleLocator.setValues(str, str, i, j, elementImpl.getCharacterOffset());
      return true;
    } 
    return false;
  }
  
  void reportSchemaError(String paramString, Object[] paramArrayOfObject, Element paramElement) {
    reportSchemaError(paramString, paramArrayOfObject, paramElement, null);
  }
  
  void reportSchemaError(String paramString, Object[] paramArrayOfObject, Element paramElement, Exception paramException) {
    if (element2Locator(paramElement, this.xl)) {
      this.fErrorReporter.reportError((XMLLocator)this.xl, "http://www.w3.org/TR/xml-schema-1", paramString, paramArrayOfObject, (short)1, paramException);
    } else {
      this.fErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", paramString, paramArrayOfObject, (short)1, paramException);
    } 
  }
  
  void reportSchemaWarning(String paramString, Object[] paramArrayOfObject, Element paramElement) {
    reportSchemaWarning(paramString, paramArrayOfObject, paramElement, null);
  }
  
  void reportSchemaWarning(String paramString, Object[] paramArrayOfObject, Element paramElement, Exception paramException) {
    if (element2Locator(paramElement, this.xl)) {
      this.fErrorReporter.reportError((XMLLocator)this.xl, "http://www.w3.org/TR/xml-schema-1", paramString, paramArrayOfObject, (short)0, paramException);
    } else {
      this.fErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", paramString, paramArrayOfObject, (short)0, paramException);
    } 
  }
  
  public void setGenerateSyntheticAnnotations(boolean paramBoolean) {
    this.fSchemaParser.setFeature("http://apache.org/xml/features/generate-synthetic-annotations", paramBoolean);
  }
  
  private static final class SAX2XNIUtil extends ErrorHandlerWrapper {
    public static XMLParseException createXMLParseException0(SAXParseException param1SAXParseException) {
      return ErrorHandlerWrapper.createXMLParseException(param1SAXParseException);
    }
    
    public static XNIException createXNIException0(SAXException param1SAXException) {
      return ErrorHandlerWrapper.createXNIException(param1SAXException);
    }
  }
  
  private static class XSDKey {
    String systemId;
    
    short referType;
    
    String referNS;
    
    XSDKey(String param1String1, short param1Short, String param1String2) {
      this.systemId = param1String1;
      this.referType = param1Short;
      this.referNS = param1String2;
    }
    
    public int hashCode() {
      return (this.referNS == null) ? 0 : this.referNS.hashCode();
    }
    
    public boolean equals(Object param1Object) {
      if (!(param1Object instanceof XSDKey))
        return false; 
      XSDKey xSDKey = (XSDKey)param1Object;
      return (this.referNS != xSDKey.referNS) ? false : (!(this.systemId == null || !this.systemId.equals(xSDKey.systemId)));
    }
  }
  
  private static class XSAnnotationGrammarPool implements XMLGrammarPool {
    private XSGrammarBucket fGrammarBucket;
    
    private Grammar[] fInitialGrammarSet;
    
    private XSAnnotationGrammarPool() {}
    
    public Grammar[] retrieveInitialGrammarSet(String param1String) {
      if (param1String == "http://www.w3.org/2001/XMLSchema") {
        if (this.fInitialGrammarSet == null)
          if (this.fGrammarBucket == null) {
            this.fInitialGrammarSet = new Grammar[] { (Grammar)SchemaGrammar.Schema4Annotations.INSTANCE };
          } else {
            SchemaGrammar[] arrayOfSchemaGrammar = this.fGrammarBucket.getGrammars();
            for (byte b = 0; b < arrayOfSchemaGrammar.length; b++) {
              if (SchemaSymbols.URI_SCHEMAFORSCHEMA.equals(arrayOfSchemaGrammar[b].getTargetNamespace())) {
                this.fInitialGrammarSet = (Grammar[])arrayOfSchemaGrammar;
                return this.fInitialGrammarSet;
              } 
            } 
            Grammar[] arrayOfGrammar = new Grammar[arrayOfSchemaGrammar.length + 1];
            System.arraycopy(arrayOfSchemaGrammar, 0, arrayOfGrammar, 0, arrayOfSchemaGrammar.length);
            arrayOfGrammar[arrayOfGrammar.length - 1] = (Grammar)SchemaGrammar.Schema4Annotations.INSTANCE;
            this.fInitialGrammarSet = arrayOfGrammar;
          }  
        return this.fInitialGrammarSet;
      } 
      return new Grammar[0];
    }
    
    public void cacheGrammars(String param1String, Grammar[] param1ArrayOfGrammar) {}
    
    public Grammar retrieveGrammar(XMLGrammarDescription param1XMLGrammarDescription) {
      if (param1XMLGrammarDescription.getGrammarType() == "http://www.w3.org/2001/XMLSchema") {
        String str = ((XMLSchemaDescription)param1XMLGrammarDescription).getTargetNamespace();
        if (this.fGrammarBucket != null) {
          SchemaGrammar schemaGrammar = this.fGrammarBucket.getGrammar(str);
          if (schemaGrammar != null)
            return (Grammar)schemaGrammar; 
        } 
        if (SchemaSymbols.URI_SCHEMAFORSCHEMA.equals(str))
          return (Grammar)SchemaGrammar.Schema4Annotations.INSTANCE; 
      } 
      return null;
    }
    
    public void refreshGrammars(XSGrammarBucket param1XSGrammarBucket) {
      this.fGrammarBucket = param1XSGrammarBucket;
      this.fInitialGrammarSet = null;
    }
    
    public void lockPool() {}
    
    public void unlockPool() {}
    
    public void clear() {}
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/xs/traversers/XSDHandler.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */