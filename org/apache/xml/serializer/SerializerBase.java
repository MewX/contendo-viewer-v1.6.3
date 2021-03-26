/*      */ package org.apache.xml.serializer;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.OutputStream;
/*      */ import java.io.Writer;
/*      */ import java.util.Properties;
/*      */ import java.util.Vector;
/*      */ import javax.xml.transform.SourceLocator;
/*      */ import javax.xml.transform.Transformer;
/*      */ import org.apache.xml.dtm.ref.dom2dtm.DOM2DTM;
/*      */ import org.apache.xml.res.XMLMessages;
/*      */ import org.w3c.dom.Node;
/*      */ import org.xml.sax.Attributes;
/*      */ import org.xml.sax.ContentHandler;
/*      */ import org.xml.sax.Locator;
/*      */ import org.xml.sax.SAXException;
/*      */ import org.xml.sax.SAXParseException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class SerializerBase
/*      */   implements DOM2DTM.CharacterNodeHandler, SerializationHandler, SerializerConstants
/*      */ {
/*      */   protected void fireEndElem(String name) throws SAXException {
/*   58 */     if (this.m_tracer != null) {
/*      */       
/*   60 */       flushMyWriter();
/*   61 */       this.m_tracer.fireGenerateEvent(4, name, (Attributes)null);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void fireCharEvent(char[] chars, int start, int length) throws SAXException {
/*   74 */     if (this.m_tracer != null) {
/*      */       
/*   76 */       flushMyWriter();
/*   77 */       this.m_tracer.fireGenerateEvent(5, chars, start, length);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean m_needToCallStartDocument = true;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean m_cdataTagOpen = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   96 */   protected AttributesImplSerializer m_attributes = new AttributesImplSerializer();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean m_inEntityRef = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean m_inExternalDTD = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String m_doctypeSystem;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String m_doctypePublic;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean m_needToOutputDocTypeDecl = true;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  126 */   private String m_encoding = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean m_shouldNotWriteXMLHeader = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String m_standalone;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean m_standaloneWasSpecified = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean m_doIndent = false;
/*      */ 
/*      */ 
/*      */   
/*  150 */   protected int m_indentAmount = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  155 */   private String m_version = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String m_mediatype;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Transformer m_transformer;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  173 */   protected Vector m_cdataSectionElements = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected NamespaceMappings m_prefixMap;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected SerializerTrace m_tracer;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected SourceLocator m_sourceLocator;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  196 */   protected Writer m_writer = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  204 */   protected ElemContext m_elemContext = new ElemContext();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  212 */   protected char[] m_charsBuff = new char[60];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  220 */   protected char[] m_attrBuff = new char[30];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void comment(String data) throws SAXException {
/*  229 */     int length = data.length();
/*  230 */     if (length > this.m_charsBuff.length)
/*      */     {
/*  232 */       this.m_charsBuff = new char[length * 2 + 1];
/*      */     }
/*  234 */     data.getChars(0, length, this.m_charsBuff, 0);
/*  235 */     comment(this.m_charsBuff, 0, length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String patchName(String qname) {
/*  250 */     int lastColon = qname.lastIndexOf(':');
/*      */     
/*  252 */     if (lastColon > 0) {
/*  253 */       int firstColon = qname.indexOf(':');
/*  254 */       String prefix = qname.substring(0, firstColon);
/*  255 */       String localName = qname.substring(lastColon + 1);
/*      */ 
/*      */       
/*  258 */       String uri = this.m_prefixMap.lookupNamespace(prefix);
/*  259 */       if (uri != null && uri.length() == 0) {
/*  260 */         return localName;
/*      */       }
/*  262 */       if (firstColon != lastColon) {
/*  263 */         return prefix + ':' + localName;
/*      */       }
/*      */     } 
/*  266 */     return qname;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static String getLocalName(String qname) {
/*  277 */     int col = qname.lastIndexOf(':');
/*  278 */     return (col > 0) ? qname.substring(col + 1) : qname;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDocumentLocator(Locator locator) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addAttribute(String uri, String localName, String rawName, String type, String value) throws SAXException {
/*  339 */     if (this.m_elemContext.m_startTagOpen)
/*      */     {
/*  341 */       addAttributeAlways(uri, localName, rawName, type, value);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addAttributeAlways(String uri, String localName, String rawName, String type, String value) {
/*  374 */     int index = this.m_attributes.getIndex(rawName);
/*  375 */     if (index >= 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  381 */       this.m_attributes.setValue(index, value);
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  386 */       this.m_attributes.addAttribute(uri, localName, rawName, type, value);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addAttribute(String name, String value) {
/*  402 */     if (this.m_elemContext.m_startTagOpen) {
/*      */       
/*  404 */       String patchedName = patchName(name);
/*  405 */       String localName = getLocalName(patchedName);
/*  406 */       String uri = getNamespaceURI(patchedName, false);
/*      */       
/*  408 */       addAttributeAlways(uri, localName, patchedName, "CDATA", value);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addAttributes(Attributes atts) throws SAXException {
/*  422 */     int nAtts = atts.getLength();
/*      */     
/*  424 */     for (int i = 0; i < nAtts; i++) {
/*      */       
/*  426 */       String uri = atts.getURI(i);
/*      */       
/*  428 */       if (null == uri) {
/*  429 */         uri = "";
/*      */       }
/*  431 */       addAttributeAlways(uri, atts.getLocalName(i), atts.getQName(i), atts.getType(i), atts.getValue(i));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ContentHandler asContentHandler() throws IOException {
/*  452 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endEntity(String name) throws SAXException {
/*  464 */     if (name.equals("[dtd]"))
/*  465 */       this.m_inExternalDTD = false; 
/*  466 */     this.m_inEntityRef = false;
/*      */     
/*  468 */     if (this.m_tracer != null) {
/*  469 */       fireEndEntity(name);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void close() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void initCDATA() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getEncoding() {
/*  498 */     return this.m_encoding;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEncoding(String m_encoding) {
/*  507 */     this.m_encoding = m_encoding;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setOmitXMLDeclaration(boolean b) {
/*  517 */     this.m_shouldNotWriteXMLHeader = b;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getOmitXMLDeclaration() {
/*  527 */     return this.m_shouldNotWriteXMLHeader;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDoctypePublic() {
/*  539 */     return this.m_doctypePublic;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDoctypePublic(String doctypePublic) {
/*  548 */     this.m_doctypePublic = doctypePublic;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDoctypeSystem() {
/*  561 */     return this.m_doctypeSystem;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDoctypeSystem(String doctypeSystem) {
/*  570 */     this.m_doctypeSystem = doctypeSystem;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDoctype(String doctypeSystem, String doctypePublic) {
/*  581 */     this.m_doctypeSystem = doctypeSystem;
/*  582 */     this.m_doctypePublic = doctypePublic;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setStandalone(String standalone) {
/*  594 */     if (standalone != null) {
/*      */       
/*  596 */       this.m_standaloneWasSpecified = true;
/*  597 */       setStandaloneInternal(standalone);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setStandaloneInternal(String standalone) {
/*  607 */     if ("yes".equals(standalone)) {
/*  608 */       this.m_standalone = "yes";
/*      */     } else {
/*  610 */       this.m_standalone = "no";
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getStandalone() {
/*  622 */     return this.m_standalone;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getIndent() {
/*  631 */     return this.m_doIndent;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getMediaType() {
/*  641 */     return this.m_mediatype;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getVersion() {
/*  650 */     return this.m_version;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setVersion(String version) {
/*  660 */     this.m_version = version;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMediaType(String mediaType) {
/*  672 */     this.m_mediatype = mediaType;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getIndentAmount() {
/*  680 */     return this.m_indentAmount;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setIndentAmount(int m_indentAmount) {
/*  689 */     this.m_indentAmount = m_indentAmount;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setIndent(boolean doIndent) {
/*  701 */     this.m_doIndent = doIndent;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void namespaceAfterStartElement(String uri, String prefix) throws SAXException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DOMSerializer asDOMSerializer() throws IOException {
/*  733 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isCdataSection() {
/*  755 */     boolean b = false;
/*      */     
/*  757 */     if (null != this.m_cdataSectionElements) {
/*      */       
/*  759 */       if (this.m_elemContext.m_elementLocalName == null) {
/*  760 */         this.m_elemContext.m_elementLocalName = getLocalName(this.m_elemContext.m_elementName);
/*      */       }
/*  762 */       if (this.m_elemContext.m_elementURI == null) {
/*      */         
/*  764 */         String prefix = getPrefixPart(this.m_elemContext.m_elementName);
/*  765 */         if (prefix != null) {
/*  766 */           this.m_elemContext.m_elementURI = this.m_prefixMap.lookupNamespace(prefix);
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  771 */       if (null != this.m_elemContext.m_elementURI && this.m_elemContext.m_elementURI.length() == 0)
/*      */       {
/*  773 */         this.m_elemContext.m_elementURI = null;
/*      */       }
/*  775 */       int nElems = this.m_cdataSectionElements.size();
/*      */ 
/*      */       
/*  778 */       for (int i = 0; i < nElems; i += 2) {
/*      */         
/*  780 */         String uri = this.m_cdataSectionElements.elementAt(i);
/*  781 */         String loc = this.m_cdataSectionElements.elementAt(i + 1);
/*  782 */         if (loc.equals(this.m_elemContext.m_elementLocalName) && subPartMatch(this.m_elemContext.m_elementURI, uri)) {
/*      */ 
/*      */           
/*  785 */           b = true;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/*  791 */     return b;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final boolean subPartMatch(String p, String t) {
/*  804 */     return (p == t || (null != p && p.equals(t)));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final String getPrefixPart(String qname) {
/*  818 */     int col = qname.indexOf(':');
/*  819 */     return (col > 0) ? qname.substring(0, col) : null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NamespaceMappings getNamespaceMappings() {
/*  830 */     return this.m_prefixMap;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getPrefix(String namespaceURI) {
/*  841 */     String prefix = this.m_prefixMap.lookupPrefix(namespaceURI);
/*  842 */     return prefix;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNamespaceURI(String qname, boolean isElement) {
/*  855 */     String uri = "";
/*  856 */     int col = qname.lastIndexOf(':');
/*  857 */     String prefix = (col > 0) ? qname.substring(0, col) : "";
/*      */     
/*  859 */     if (!"".equals(prefix) || isElement)
/*      */     {
/*  861 */       if (this.m_prefixMap != null) {
/*      */         
/*  863 */         uri = this.m_prefixMap.lookupNamespace(prefix);
/*  864 */         if (uri == null && !prefix.equals("xmlns"))
/*      */         {
/*  866 */           throw new RuntimeException(XMLMessages.createXMLMessage("ER_NAMESPACE_PREFIX", new Object[] { qname.substring(0, col) }));
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  873 */     return uri;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNamespaceURIFromPrefix(String prefix) {
/*  885 */     String uri = null;
/*  886 */     if (this.m_prefixMap != null)
/*  887 */       uri = this.m_prefixMap.lookupNamespace(prefix); 
/*  888 */     return uri;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void entityReference(String name) throws SAXException {
/*  901 */     flushPending();
/*      */     
/*  903 */     startEntity(name);
/*  904 */     endEntity(name);
/*      */     
/*  906 */     if (this.m_tracer != null) {
/*  907 */       fireEntityReference(name);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTransformer(Transformer t) {
/*  917 */     this.m_transformer = t;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  922 */     if (this.m_transformer instanceof SerializerTrace && ((SerializerTrace)this.m_transformer).hasTraceListeners()) {
/*      */       
/*  924 */       this.m_tracer = (SerializerTrace)this.m_transformer;
/*      */     } else {
/*  926 */       this.m_tracer = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Transformer getTransformer() {
/*  936 */     return this.m_transformer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void characters(Node node) throws SAXException {
/*  948 */     flushPending();
/*  949 */     String data = node.getNodeValue();
/*  950 */     if (data != null) {
/*      */       
/*  952 */       int length = data.length();
/*  953 */       if (length > this.m_charsBuff.length)
/*      */       {
/*  955 */         this.m_charsBuff = new char[length * 2 + 1];
/*      */       }
/*  957 */       data.getChars(0, length, this.m_charsBuff, 0);
/*  958 */       characters(this.m_charsBuff, 0, length);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void error(SAXParseException exc) throws SAXException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void fatalError(SAXParseException exc) throws SAXException {
/*  974 */     this.m_elemContext.m_startTagOpen = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void warning(SAXParseException exc) throws SAXException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void fireStartEntity(String name) throws SAXException {
/*  992 */     if (this.m_tracer != null) {
/*      */       
/*  994 */       flushMyWriter();
/*  995 */       this.m_tracer.fireGenerateEvent(9, name);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void flushMyWriter() {
/* 1024 */     if (this.m_writer != null) {
/*      */ 
/*      */       
/*      */       try { 
/* 1028 */         this.m_writer.flush(); } catch (IOException iOException) {}
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void fireCDATAEvent(char[] chars, int start, int length) throws SAXException {
/* 1045 */     if (this.m_tracer != null) {
/*      */       
/* 1047 */       flushMyWriter();
/* 1048 */       this.m_tracer.fireGenerateEvent(10, chars, start, length);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void fireCommentEvent(char[] chars, int start, int length) throws SAXException {
/* 1061 */     if (this.m_tracer != null) {
/*      */       
/* 1063 */       flushMyWriter();
/* 1064 */       this.m_tracer.fireGenerateEvent(8, new String(chars, start, length));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void fireEndEntity(String name) throws SAXException {
/* 1076 */     if (this.m_tracer != null) {
/* 1077 */       flushMyWriter();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void fireStartDoc() throws SAXException {
/* 1087 */     if (this.m_tracer != null) {
/*      */       
/* 1089 */       flushMyWriter();
/* 1090 */       this.m_tracer.fireGenerateEvent(1);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void fireEndDoc() throws SAXException {
/* 1101 */     if (this.m_tracer != null) {
/*      */       
/* 1103 */       flushMyWriter();
/* 1104 */       this.m_tracer.fireGenerateEvent(2);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void fireStartElem(String elemName) throws SAXException {
/* 1118 */     if (this.m_tracer != null) {
/*      */       
/* 1120 */       flushMyWriter();
/* 1121 */       this.m_tracer.fireGenerateEvent(3, elemName, this.m_attributes);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void fireEscapingEvent(String name, String data) throws SAXException {
/* 1147 */     if (this.m_tracer != null) {
/*      */       
/* 1149 */       flushMyWriter();
/* 1150 */       this.m_tracer.fireGenerateEvent(7, name, data);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void fireEntityReference(String name) throws SAXException {
/* 1162 */     if (this.m_tracer != null) {
/*      */       
/* 1164 */       flushMyWriter();
/* 1165 */       this.m_tracer.fireGenerateEvent(9, name, (Attributes)null);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void startDocument() throws SAXException {
/* 1187 */     startDocumentInternal();
/* 1188 */     this.m_needToCallStartDocument = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void startDocumentInternal() throws SAXException {
/* 1210 */     if (this.m_tracer != null) {
/* 1211 */       fireStartDoc();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSourceLocator(SourceLocator locator) {
/* 1222 */     this.m_sourceLocator = locator;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setNamespaceMappings(NamespaceMappings mappings) {
/* 1233 */     this.m_prefixMap = mappings;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean reset() {
/* 1238 */     resetSerializerBase();
/* 1239 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void resetSerializerBase() {
/* 1248 */     this.m_attributes.clear();
/* 1249 */     this.m_cdataSectionElements = null;
/* 1250 */     this.m_elemContext = new ElemContext();
/* 1251 */     this.m_doctypePublic = null;
/* 1252 */     this.m_doctypeSystem = null;
/* 1253 */     this.m_doIndent = false;
/* 1254 */     this.m_encoding = null;
/* 1255 */     this.m_indentAmount = 0;
/* 1256 */     this.m_inEntityRef = false;
/* 1257 */     this.m_inExternalDTD = false;
/* 1258 */     this.m_mediatype = null;
/* 1259 */     this.m_needToCallStartDocument = true;
/* 1260 */     this.m_needToOutputDocTypeDecl = false;
/* 1261 */     if (this.m_prefixMap != null)
/* 1262 */       this.m_prefixMap.reset(); 
/* 1263 */     this.m_shouldNotWriteXMLHeader = false;
/* 1264 */     this.m_sourceLocator = null;
/* 1265 */     this.m_standalone = null;
/* 1266 */     this.m_standaloneWasSpecified = false;
/* 1267 */     this.m_tracer = null;
/* 1268 */     this.m_transformer = null;
/* 1269 */     this.m_version = null;
/*      */   }
/*      */   
/*      */   public abstract void flushPending() throws SAXException;
/*      */   
/*      */   public abstract boolean setEscaping(boolean paramBoolean) throws SAXException;
/*      */   
/*      */   public abstract void serialize(Node paramNode) throws IOException;
/*      */   
/*      */   public abstract void setContentHandler(ContentHandler paramContentHandler);
/*      */   
/*      */   public abstract void addUniqueAttribute(String paramString1, String paramString2, int paramInt) throws SAXException;
/*      */   
/*      */   public abstract boolean startPrefixMapping(String paramString1, String paramString2, boolean paramBoolean) throws SAXException;
/*      */   
/*      */   public abstract void startElement(String paramString) throws SAXException;
/*      */   
/*      */   public abstract void startElement(String paramString1, String paramString2, String paramString3) throws SAXException;
/*      */   
/*      */   public abstract void endElement(String paramString) throws SAXException;
/*      */   
/*      */   public abstract void characters(String paramString) throws SAXException;
/*      */   
/*      */   public abstract void skippedEntity(String paramString) throws SAXException;
/*      */   
/*      */   public abstract void processingInstruction(String paramString1, String paramString2) throws SAXException;
/*      */   
/*      */   public abstract void ignorableWhitespace(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws SAXException;
/*      */   
/*      */   public abstract void characters(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws SAXException;
/*      */   
/*      */   public abstract void endElement(String paramString1, String paramString2, String paramString3) throws SAXException;
/*      */   
/*      */   public abstract void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes) throws SAXException;
/*      */   
/*      */   public abstract void endPrefixMapping(String paramString) throws SAXException;
/*      */   
/*      */   public abstract void startPrefixMapping(String paramString1, String paramString2) throws SAXException;
/*      */   
/*      */   public abstract void endDocument() throws SAXException;
/*      */   
/*      */   public abstract void comment(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws SAXException;
/*      */   
/*      */   public abstract void endCDATA() throws SAXException;
/*      */   
/*      */   public abstract void startCDATA() throws SAXException;
/*      */   
/*      */   public abstract void startEntity(String paramString) throws SAXException;
/*      */   
/*      */   public abstract void endDTD() throws SAXException;
/*      */   
/*      */   public abstract void startDTD(String paramString1, String paramString2, String paramString3) throws SAXException;
/*      */   
/*      */   public abstract void setCdataSectionElements(Vector paramVector);
/*      */   
/*      */   public abstract void externalEntityDecl(String paramString1, String paramString2, String paramString3) throws SAXException;
/*      */   
/*      */   public abstract void internalEntityDecl(String paramString1, String paramString2) throws SAXException;
/*      */   
/*      */   public abstract void attributeDecl(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5) throws SAXException;
/*      */   
/*      */   public abstract void elementDecl(String paramString1, String paramString2) throws SAXException;
/*      */   
/*      */   public abstract Properties getOutputFormat();
/*      */   
/*      */   public abstract void setOutputFormat(Properties paramProperties);
/*      */   
/*      */   public abstract Writer getWriter();
/*      */   
/*      */   public abstract void setWriter(Writer paramWriter);
/*      */   
/*      */   public abstract OutputStream getOutputStream();
/*      */   
/*      */   public abstract void setOutputStream(OutputStream paramOutputStream);
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/serializer/SerializerBase.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */