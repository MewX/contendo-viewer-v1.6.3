/*      */ package org.apache.xalan.xsltc.dom;
/*      */ 
/*      */ import javax.xml.transform.SourceLocator;
/*      */ import org.apache.xalan.xsltc.DOM;
/*      */ import org.apache.xalan.xsltc.StripFilter;
/*      */ import org.apache.xalan.xsltc.TransletException;
/*      */ import org.apache.xalan.xsltc.runtime.AttributeList;
/*      */ import org.apache.xalan.xsltc.runtime.BasisLibrary;
/*      */ import org.apache.xalan.xsltc.runtime.Hashtable;
/*      */ import org.apache.xml.dtm.DTMAxisIterator;
/*      */ import org.apache.xml.dtm.DTMAxisTraverser;
/*      */ import org.apache.xml.dtm.DTMWSFilter;
/*      */ import org.apache.xml.serializer.SerializationHandler;
/*      */ import org.apache.xml.utils.XMLString;
/*      */ import org.w3c.dom.Node;
/*      */ import org.w3c.dom.NodeList;
/*      */ import org.xml.sax.Attributes;
/*      */ import org.xml.sax.ContentHandler;
/*      */ import org.xml.sax.DTDHandler;
/*      */ import org.xml.sax.EntityResolver;
/*      */ import org.xml.sax.ErrorHandler;
/*      */ import org.xml.sax.SAXException;
/*      */ import org.xml.sax.ext.DeclHandler;
/*      */ import org.xml.sax.ext.LexicalHandler;
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
/*      */ public class AdaptiveResultTreeImpl
/*      */   extends SimpleResultTreeImpl
/*      */ {
/*   76 */   private static int _documentURIIndex = 0;
/*      */ 
/*      */ 
/*      */   
/*      */   private SAXImpl _dom;
/*      */ 
/*      */ 
/*      */   
/*      */   private DTMWSFilter _wsfilter;
/*      */ 
/*      */   
/*      */   private int _initSize;
/*      */ 
/*      */   
/*      */   private boolean _buildIdIndex;
/*      */ 
/*      */   
/*   93 */   private final AttributeList _attributes = new AttributeList();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String _openElementName;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AdaptiveResultTreeImpl(XSLTCDTMManager dtmManager, int documentID, DTMWSFilter wsfilter, int initSize, boolean buildIdIndex) {
/*  104 */     super(dtmManager, documentID);
/*      */     
/*  106 */     this._wsfilter = wsfilter;
/*  107 */     this._initSize = initSize;
/*  108 */     this._buildIdIndex = buildIdIndex;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public DOM getNestedDOM() {
/*  114 */     return (DOM)this._dom;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getDocument() {
/*  120 */     if (this._dom != null) {
/*  121 */       return this._dom.getDocument();
/*      */     }
/*      */     
/*  124 */     return super.getDocument();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getStringValue() {
/*  131 */     if (this._dom != null) {
/*  132 */       return this._dom.getStringValue();
/*      */     }
/*      */     
/*  135 */     return super.getStringValue();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public DTMAxisIterator getIterator() {
/*  141 */     if (this._dom != null) {
/*  142 */       return this._dom.getIterator();
/*      */     }
/*      */     
/*  145 */     return super.getIterator();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public DTMAxisIterator getChildren(int node) {
/*  151 */     if (this._dom != null) {
/*  152 */       return this._dom.getChildren(node);
/*      */     }
/*      */     
/*  155 */     return super.getChildren(node);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public DTMAxisIterator getTypedChildren(int type) {
/*  161 */     if (this._dom != null) {
/*  162 */       return this._dom.getTypedChildren(type);
/*      */     }
/*      */     
/*  165 */     return super.getTypedChildren(type);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public DTMAxisIterator getAxisIterator(int axis) {
/*  171 */     if (this._dom != null) {
/*  172 */       return this._dom.getAxisIterator(axis);
/*      */     }
/*      */     
/*  175 */     return super.getAxisIterator(axis);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public DTMAxisIterator getTypedAxisIterator(int axis, int type) {
/*  181 */     if (this._dom != null) {
/*  182 */       return this._dom.getTypedAxisIterator(axis, type);
/*      */     }
/*      */     
/*  185 */     return super.getTypedAxisIterator(axis, type);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public DTMAxisIterator getNthDescendant(int node, int n, boolean includeself) {
/*  191 */     if (this._dom != null) {
/*  192 */       return this._dom.getNthDescendant(node, n, includeself);
/*      */     }
/*      */     
/*  195 */     return super.getNthDescendant(node, n, includeself);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public DTMAxisIterator getNamespaceAxisIterator(int axis, int ns) {
/*  201 */     if (this._dom != null) {
/*  202 */       return this._dom.getNamespaceAxisIterator(axis, ns);
/*      */     }
/*      */     
/*  205 */     return super.getNamespaceAxisIterator(axis, ns);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DTMAxisIterator getNodeValueIterator(DTMAxisIterator iter, int returnType, String value, boolean op) {
/*  212 */     if (this._dom != null) {
/*  213 */       return this._dom.getNodeValueIterator(iter, returnType, value, op);
/*      */     }
/*      */     
/*  216 */     return super.getNodeValueIterator(iter, returnType, value, op);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public DTMAxisIterator orderNodes(DTMAxisIterator source, int node) {
/*  222 */     if (this._dom != null) {
/*  223 */       return this._dom.orderNodes(source, node);
/*      */     }
/*      */     
/*  226 */     return super.orderNodes(source, node);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNodeName(int node) {
/*  232 */     if (this._dom != null) {
/*  233 */       return this._dom.getNodeName(node);
/*      */     }
/*      */     
/*  236 */     return super.getNodeName(node);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNodeNameX(int node) {
/*  242 */     if (this._dom != null) {
/*  243 */       return this._dom.getNodeNameX(node);
/*      */     }
/*      */     
/*  246 */     return super.getNodeNameX(node);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNamespaceName(int node) {
/*  252 */     if (this._dom != null) {
/*  253 */       return this._dom.getNamespaceName(node);
/*      */     }
/*      */     
/*  256 */     return super.getNamespaceName(node);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getExpandedTypeID(int nodeHandle) {
/*  263 */     if (this._dom != null) {
/*  264 */       return this._dom.getExpandedTypeID(nodeHandle);
/*      */     }
/*      */     
/*  267 */     return super.getExpandedTypeID(nodeHandle);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNamespaceType(int node) {
/*  273 */     if (this._dom != null) {
/*  274 */       return this._dom.getNamespaceType(node);
/*      */     }
/*      */     
/*  277 */     return super.getNamespaceType(node);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getParent(int nodeHandle) {
/*  283 */     if (this._dom != null) {
/*  284 */       return this._dom.getParent(nodeHandle);
/*      */     }
/*      */     
/*  287 */     return super.getParent(nodeHandle);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getAttributeNode(int gType, int element) {
/*  293 */     if (this._dom != null) {
/*  294 */       return this._dom.getAttributeNode(gType, element);
/*      */     }
/*      */     
/*  297 */     return super.getAttributeNode(gType, element);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getStringValueX(int nodeHandle) {
/*  303 */     if (this._dom != null) {
/*  304 */       return this._dom.getStringValueX(nodeHandle);
/*      */     }
/*      */     
/*  307 */     return super.getStringValueX(nodeHandle);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void copy(int node, SerializationHandler handler) throws TransletException {
/*  314 */     if (this._dom != null) {
/*  315 */       this._dom.copy(node, handler);
/*      */     } else {
/*      */       
/*  318 */       super.copy(node, handler);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void copy(DTMAxisIterator nodes, SerializationHandler handler) throws TransletException {
/*  325 */     if (this._dom != null) {
/*  326 */       this._dom.copy(nodes, handler);
/*      */     } else {
/*      */       
/*  329 */       super.copy(nodes, handler);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String shallowCopy(int node, SerializationHandler handler) throws TransletException {
/*  336 */     if (this._dom != null) {
/*  337 */       return this._dom.shallowCopy(node, handler);
/*      */     }
/*      */     
/*  340 */     return super.shallowCopy(node, handler);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean lessThan(int node1, int node2) {
/*  346 */     if (this._dom != null) {
/*  347 */       return this._dom.lessThan(node1, node2);
/*      */     }
/*      */     
/*  350 */     return super.lessThan(node1, node2);
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
/*      */   public void characters(int node, SerializationHandler handler) throws TransletException {
/*  363 */     if (this._dom != null) {
/*  364 */       this._dom.characters(node, handler);
/*      */     } else {
/*      */       
/*  367 */       super.characters(node, handler);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public Node makeNode(int index) {
/*  373 */     if (this._dom != null) {
/*  374 */       return this._dom.makeNode(index);
/*      */     }
/*      */     
/*  377 */     return super.makeNode(index);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Node makeNode(DTMAxisIterator iter) {
/*  383 */     if (this._dom != null) {
/*  384 */       return this._dom.makeNode(iter);
/*      */     }
/*      */     
/*  387 */     return super.makeNode(iter);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public NodeList makeNodeList(int index) {
/*  393 */     if (this._dom != null) {
/*  394 */       return this._dom.makeNodeList(index);
/*      */     }
/*      */     
/*  397 */     return super.makeNodeList(index);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public NodeList makeNodeList(DTMAxisIterator iter) {
/*  403 */     if (this._dom != null) {
/*  404 */       return this._dom.makeNodeList(iter);
/*      */     }
/*      */     
/*  407 */     return super.makeNodeList(iter);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getLanguage(int node) {
/*  413 */     if (this._dom != null) {
/*  414 */       return this._dom.getLanguage(node);
/*      */     }
/*      */     
/*  417 */     return super.getLanguage(node);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getSize() {
/*  423 */     if (this._dom != null) {
/*  424 */       return this._dom.getSize();
/*      */     }
/*      */     
/*  427 */     return super.getSize();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDocumentURI(int node) {
/*  433 */     if (this._dom != null) {
/*  434 */       return this._dom.getDocumentURI(node);
/*      */     }
/*      */     
/*  437 */     return "adaptive_rtf" + _documentURIIndex++;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFilter(StripFilter filter) {
/*  443 */     if (this._dom != null) {
/*  444 */       this._dom.setFilter(filter);
/*      */     } else {
/*      */       
/*  447 */       super.setFilter(filter);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void setupMapping(String[] names, String[] uris, int[] types, String[] namespaces) {
/*  453 */     if (this._dom != null) {
/*  454 */       this._dom.setupMapping(names, uris, types, namespaces);
/*      */     } else {
/*      */       
/*  457 */       super.setupMapping(names, uris, types, namespaces);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isElement(int node) {
/*  463 */     if (this._dom != null) {
/*  464 */       return this._dom.isElement(node);
/*      */     }
/*      */     
/*  467 */     return super.isElement(node);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isAttribute(int node) {
/*  473 */     if (this._dom != null) {
/*  474 */       return this._dom.isAttribute(node);
/*      */     }
/*      */     
/*  477 */     return super.isAttribute(node);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String lookupNamespace(int node, String prefix) throws TransletException {
/*  484 */     if (this._dom != null) {
/*  485 */       return this._dom.lookupNamespace(node, prefix);
/*      */     }
/*      */     
/*  488 */     return super.lookupNamespace(node, prefix);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getNodeIdent(int nodehandle) {
/*  497 */     if (this._dom != null) {
/*  498 */       return this._dom.getNodeIdent(nodehandle);
/*      */     }
/*      */     
/*  501 */     return super.getNodeIdent(nodehandle);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getNodeHandle(int nodeId) {
/*  510 */     if (this._dom != null) {
/*  511 */       return this._dom.getNodeHandle(nodeId);
/*      */     }
/*      */     
/*  514 */     return super.getNodeHandle(nodeId);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public DOM getResultTreeFrag(int initialSize, int rtfType) {
/*  520 */     if (this._dom != null) {
/*  521 */       return this._dom.getResultTreeFrag(initialSize, rtfType);
/*      */     }
/*      */     
/*  524 */     return super.getResultTreeFrag(initialSize, rtfType);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public SerializationHandler getOutputDomBuilder() {
/*  530 */     return (SerializationHandler)this;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getNSType(int node) {
/*  535 */     if (this._dom != null) {
/*  536 */       return this._dom.getNSType(node);
/*      */     }
/*      */     
/*  539 */     return super.getNSType(node);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getUnparsedEntityURI(String name) {
/*  545 */     if (this._dom != null) {
/*  546 */       return this._dom.getUnparsedEntityURI(name);
/*      */     }
/*      */     
/*  549 */     return super.getUnparsedEntityURI(name);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Hashtable getElementsWithIDs() {
/*  555 */     if (this._dom != null) {
/*  556 */       return this._dom.getElementsWithIDs();
/*      */     }
/*      */     
/*  559 */     return super.getElementsWithIDs();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void maybeEmitStartElement() throws SAXException {
/*  569 */     if (this._openElementName != null) {
/*      */       int index;
/*      */       
/*  572 */       if ((index = this._openElementName.indexOf(":")) < 0) {
/*  573 */         this._dom.startElement(null, this._openElementName, this._openElementName, (Attributes)this._attributes);
/*      */       } else {
/*  575 */         this._dom.startElement(null, this._openElementName.substring(index + 1), this._openElementName, (Attributes)this._attributes);
/*      */       } 
/*      */       
/*  578 */       this._openElementName = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void prepareNewDOM() throws SAXException {
/*  585 */     this._dom = (SAXImpl)this._dtmManager.getDTM(null, true, this._wsfilter, true, false, false, this._initSize, this._buildIdIndex);
/*      */ 
/*      */     
/*  588 */     this._dom.startDocument();
/*      */     
/*  590 */     for (int i = 0; i < this._size; i++) {
/*  591 */       String str = this._textArray[i];
/*  592 */       this._dom.characters(str.toCharArray(), 0, str.length());
/*      */     } 
/*  594 */     this._size = 0;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void startDocument() throws SAXException {}
/*      */ 
/*      */   
/*      */   public void endDocument() throws SAXException {
/*  603 */     if (this._dom != null) {
/*  604 */       this._dom.endDocument();
/*      */     } else {
/*      */       
/*  607 */       super.endDocument();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void characters(String str) throws SAXException {
/*  613 */     if (this._dom != null) {
/*  614 */       characters(str.toCharArray(), 0, str.length());
/*      */     } else {
/*      */       
/*  617 */       super.characters(str);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void characters(char[] ch, int offset, int length) throws SAXException {
/*  624 */     if (this._dom != null) {
/*  625 */       maybeEmitStartElement();
/*  626 */       this._dom.characters(ch, offset, length);
/*      */     } else {
/*      */       
/*  629 */       super.characters(ch, offset, length);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean setEscaping(boolean escape) throws SAXException {
/*  635 */     if (this._dom != null) {
/*  636 */       return this._dom.setEscaping(escape);
/*      */     }
/*      */     
/*  639 */     return super.setEscaping(escape);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void startElement(String elementName) throws SAXException {
/*  645 */     if (this._dom == null) {
/*  646 */       prepareNewDOM();
/*      */     }
/*      */     
/*  649 */     maybeEmitStartElement();
/*  650 */     this._openElementName = elementName;
/*  651 */     this._attributes.clear();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void startElement(String uri, String localName, String qName) throws SAXException {
/*  657 */     startElement(qName);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
/*  663 */     startElement(qName);
/*      */   }
/*      */ 
/*      */   
/*      */   public void endElement(String elementName) throws SAXException {
/*  668 */     maybeEmitStartElement();
/*  669 */     this._dom.endElement(null, null, elementName);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void endElement(String uri, String localName, String qName) throws SAXException {
/*  675 */     endElement(qName);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void addUniqueAttribute(String qName, String value, int flags) throws SAXException {
/*  681 */     addAttribute(qName, value);
/*      */   }
/*      */ 
/*      */   
/*      */   public void addAttribute(String name, String value) {
/*  686 */     if (this._openElementName != null) {
/*  687 */       this._attributes.add(name, value);
/*      */     } else {
/*      */       
/*  690 */       BasisLibrary.runTimeError("STRAY_ATTRIBUTE_ERR", name);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void namespaceAfterStartElement(String prefix, String uri) throws SAXException {
/*  697 */     if (this._dom == null) {
/*  698 */       prepareNewDOM();
/*      */     }
/*      */     
/*  701 */     this._dom.startPrefixMapping(prefix, uri);
/*      */   }
/*      */ 
/*      */   
/*      */   public void comment(String comment) throws SAXException {
/*  706 */     if (this._dom == null) {
/*  707 */       prepareNewDOM();
/*      */     }
/*      */     
/*  710 */     maybeEmitStartElement();
/*  711 */     char[] chars = comment.toCharArray();
/*  712 */     this._dom.comment(chars, 0, chars.length);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void comment(char[] chars, int offset, int length) throws SAXException {
/*  718 */     if (this._dom == null) {
/*  719 */       prepareNewDOM();
/*      */     }
/*      */     
/*  722 */     maybeEmitStartElement();
/*  723 */     this._dom.comment(chars, offset, length);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void processingInstruction(String target, String data) throws SAXException {
/*  729 */     if (this._dom == null) {
/*  730 */       prepareNewDOM();
/*      */     }
/*      */     
/*  733 */     maybeEmitStartElement();
/*  734 */     this._dom.processingInstruction(target, data);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFeature(String featureId, boolean state) {
/*  741 */     if (this._dom != null) {
/*  742 */       this._dom.setFeature(featureId, state);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setProperty(String property, Object value) {
/*  748 */     if (this._dom != null) {
/*  749 */       this._dom.setProperty(property, value);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public DTMAxisTraverser getAxisTraverser(int axis) {
/*  755 */     if (this._dom != null) {
/*  756 */       return this._dom.getAxisTraverser(axis);
/*      */     }
/*      */     
/*  759 */     return super.getAxisTraverser(axis);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasChildNodes(int nodeHandle) {
/*  765 */     if (this._dom != null) {
/*  766 */       return this._dom.hasChildNodes(nodeHandle);
/*      */     }
/*      */     
/*  769 */     return super.hasChildNodes(nodeHandle);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getFirstChild(int nodeHandle) {
/*  775 */     if (this._dom != null) {
/*  776 */       return this._dom.getFirstChild(nodeHandle);
/*      */     }
/*      */     
/*  779 */     return super.getFirstChild(nodeHandle);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getLastChild(int nodeHandle) {
/*  785 */     if (this._dom != null) {
/*  786 */       return this._dom.getLastChild(nodeHandle);
/*      */     }
/*      */     
/*  789 */     return super.getLastChild(nodeHandle);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getAttributeNode(int elementHandle, String namespaceURI, String name) {
/*  795 */     if (this._dom != null) {
/*  796 */       return this._dom.getAttributeNode(elementHandle, namespaceURI, name);
/*      */     }
/*      */     
/*  799 */     return super.getAttributeNode(elementHandle, namespaceURI, name);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getFirstAttribute(int nodeHandle) {
/*  805 */     if (this._dom != null) {
/*  806 */       return this._dom.getFirstAttribute(nodeHandle);
/*      */     }
/*      */     
/*  809 */     return super.getFirstAttribute(nodeHandle);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getFirstNamespaceNode(int nodeHandle, boolean inScope) {
/*  815 */     if (this._dom != null) {
/*  816 */       return this._dom.getFirstNamespaceNode(nodeHandle, inScope);
/*      */     }
/*      */     
/*  819 */     return super.getFirstNamespaceNode(nodeHandle, inScope);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNextSibling(int nodeHandle) {
/*  825 */     if (this._dom != null) {
/*  826 */       return this._dom.getNextSibling(nodeHandle);
/*      */     }
/*      */     
/*  829 */     return super.getNextSibling(nodeHandle);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPreviousSibling(int nodeHandle) {
/*  835 */     if (this._dom != null) {
/*  836 */       return this._dom.getPreviousSibling(nodeHandle);
/*      */     }
/*      */     
/*  839 */     return super.getPreviousSibling(nodeHandle);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNextAttribute(int nodeHandle) {
/*  845 */     if (this._dom != null) {
/*  846 */       return this._dom.getNextAttribute(nodeHandle);
/*      */     }
/*      */     
/*  849 */     return super.getNextAttribute(nodeHandle);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNextNamespaceNode(int baseHandle, int namespaceHandle, boolean inScope) {
/*  856 */     if (this._dom != null) {
/*  857 */       return this._dom.getNextNamespaceNode(baseHandle, namespaceHandle, inScope);
/*      */     }
/*      */     
/*  860 */     return super.getNextNamespaceNode(baseHandle, namespaceHandle, inScope);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getOwnerDocument(int nodeHandle) {
/*  866 */     if (this._dom != null) {
/*  867 */       return this._dom.getOwnerDocument(nodeHandle);
/*      */     }
/*      */     
/*  870 */     return super.getOwnerDocument(nodeHandle);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getDocumentRoot(int nodeHandle) {
/*  876 */     if (this._dom != null) {
/*  877 */       return this._dom.getDocumentRoot(nodeHandle);
/*      */     }
/*      */     
/*  880 */     return super.getDocumentRoot(nodeHandle);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public XMLString getStringValue(int nodeHandle) {
/*  886 */     if (this._dom != null) {
/*  887 */       return this._dom.getStringValue(nodeHandle);
/*      */     }
/*      */     
/*  890 */     return super.getStringValue(nodeHandle);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getStringValueChunkCount(int nodeHandle) {
/*  896 */     if (this._dom != null) {
/*  897 */       return this._dom.getStringValueChunkCount(nodeHandle);
/*      */     }
/*      */     
/*  900 */     return super.getStringValueChunkCount(nodeHandle);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public char[] getStringValueChunk(int nodeHandle, int chunkIndex, int[] startAndLen) {
/*  907 */     if (this._dom != null) {
/*  908 */       return this._dom.getStringValueChunk(nodeHandle, chunkIndex, startAndLen);
/*      */     }
/*      */     
/*  911 */     return super.getStringValueChunk(nodeHandle, chunkIndex, startAndLen);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getExpandedTypeID(String namespace, String localName, int type) {
/*  917 */     if (this._dom != null) {
/*  918 */       return this._dom.getExpandedTypeID(namespace, localName, type);
/*      */     }
/*      */     
/*  921 */     return super.getExpandedTypeID(namespace, localName, type);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getLocalNameFromExpandedNameID(int ExpandedNameID) {
/*  927 */     if (this._dom != null) {
/*  928 */       return this._dom.getLocalNameFromExpandedNameID(ExpandedNameID);
/*      */     }
/*      */     
/*  931 */     return super.getLocalNameFromExpandedNameID(ExpandedNameID);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNamespaceFromExpandedNameID(int ExpandedNameID) {
/*  937 */     if (this._dom != null) {
/*  938 */       return this._dom.getNamespaceFromExpandedNameID(ExpandedNameID);
/*      */     }
/*      */     
/*  941 */     return super.getNamespaceFromExpandedNameID(ExpandedNameID);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getLocalName(int nodeHandle) {
/*  947 */     if (this._dom != null) {
/*  948 */       return this._dom.getLocalName(nodeHandle);
/*      */     }
/*      */     
/*  951 */     return super.getLocalName(nodeHandle);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getPrefix(int nodeHandle) {
/*  957 */     if (this._dom != null) {
/*  958 */       return this._dom.getPrefix(nodeHandle);
/*      */     }
/*      */     
/*  961 */     return super.getPrefix(nodeHandle);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNamespaceURI(int nodeHandle) {
/*  967 */     if (this._dom != null) {
/*  968 */       return this._dom.getNamespaceURI(nodeHandle);
/*      */     }
/*      */     
/*  971 */     return super.getNamespaceURI(nodeHandle);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNodeValue(int nodeHandle) {
/*  977 */     if (this._dom != null) {
/*  978 */       return this._dom.getNodeValue(nodeHandle);
/*      */     }
/*      */     
/*  981 */     return super.getNodeValue(nodeHandle);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public short getNodeType(int nodeHandle) {
/*  987 */     if (this._dom != null) {
/*  988 */       return this._dom.getNodeType(nodeHandle);
/*      */     }
/*      */     
/*  991 */     return super.getNodeType(nodeHandle);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public short getLevel(int nodeHandle) {
/*  997 */     if (this._dom != null) {
/*  998 */       return this._dom.getLevel(nodeHandle);
/*      */     }
/*      */     
/* 1001 */     return super.getLevel(nodeHandle);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSupported(String feature, String version) {
/* 1007 */     if (this._dom != null) {
/* 1008 */       return this._dom.isSupported(feature, version);
/*      */     }
/*      */     
/* 1011 */     return super.isSupported(feature, version);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDocumentBaseURI() {
/* 1017 */     if (this._dom != null) {
/* 1018 */       return this._dom.getDocumentBaseURI();
/*      */     }
/*      */     
/* 1021 */     return super.getDocumentBaseURI();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDocumentBaseURI(String baseURI) {
/* 1027 */     if (this._dom != null) {
/* 1028 */       this._dom.setDocumentBaseURI(baseURI);
/*      */     } else {
/*      */       
/* 1031 */       super.setDocumentBaseURI(baseURI);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public String getDocumentSystemIdentifier(int nodeHandle) {
/* 1037 */     if (this._dom != null) {
/* 1038 */       return this._dom.getDocumentSystemIdentifier(nodeHandle);
/*      */     }
/*      */     
/* 1041 */     return super.getDocumentSystemIdentifier(nodeHandle);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDocumentEncoding(int nodeHandle) {
/* 1047 */     if (this._dom != null) {
/* 1048 */       return this._dom.getDocumentEncoding(nodeHandle);
/*      */     }
/*      */     
/* 1051 */     return super.getDocumentEncoding(nodeHandle);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDocumentStandalone(int nodeHandle) {
/* 1057 */     if (this._dom != null) {
/* 1058 */       return this._dom.getDocumentStandalone(nodeHandle);
/*      */     }
/*      */     
/* 1061 */     return super.getDocumentStandalone(nodeHandle);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDocumentVersion(int documentHandle) {
/* 1067 */     if (this._dom != null) {
/* 1068 */       return this._dom.getDocumentVersion(documentHandle);
/*      */     }
/*      */     
/* 1071 */     return super.getDocumentVersion(documentHandle);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getDocumentAllDeclarationsProcessed() {
/* 1077 */     if (this._dom != null) {
/* 1078 */       return this._dom.getDocumentAllDeclarationsProcessed();
/*      */     }
/*      */     
/* 1081 */     return super.getDocumentAllDeclarationsProcessed();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDocumentTypeDeclarationSystemIdentifier() {
/* 1087 */     if (this._dom != null) {
/* 1088 */       return this._dom.getDocumentTypeDeclarationSystemIdentifier();
/*      */     }
/*      */     
/* 1091 */     return super.getDocumentTypeDeclarationSystemIdentifier();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDocumentTypeDeclarationPublicIdentifier() {
/* 1097 */     if (this._dom != null) {
/* 1098 */       return this._dom.getDocumentTypeDeclarationPublicIdentifier();
/*      */     }
/*      */     
/* 1101 */     return super.getDocumentTypeDeclarationPublicIdentifier();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getElementById(String elementId) {
/* 1107 */     if (this._dom != null) {
/* 1108 */       return this._dom.getElementById(elementId);
/*      */     }
/*      */     
/* 1111 */     return super.getElementById(elementId);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsPreStripping() {
/* 1117 */     if (this._dom != null) {
/* 1118 */       return this._dom.supportsPreStripping();
/*      */     }
/*      */     
/* 1121 */     return super.supportsPreStripping();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isNodeAfter(int firstNodeHandle, int secondNodeHandle) {
/* 1127 */     if (this._dom != null) {
/* 1128 */       return this._dom.isNodeAfter(firstNodeHandle, secondNodeHandle);
/*      */     }
/*      */     
/* 1131 */     return super.isNodeAfter(firstNodeHandle, secondNodeHandle);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isCharacterElementContentWhitespace(int nodeHandle) {
/* 1137 */     if (this._dom != null) {
/* 1138 */       return this._dom.isCharacterElementContentWhitespace(nodeHandle);
/*      */     }
/*      */     
/* 1141 */     return super.isCharacterElementContentWhitespace(nodeHandle);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isDocumentAllDeclarationsProcessed(int documentHandle) {
/* 1147 */     if (this._dom != null) {
/* 1148 */       return this._dom.isDocumentAllDeclarationsProcessed(documentHandle);
/*      */     }
/*      */     
/* 1151 */     return super.isDocumentAllDeclarationsProcessed(documentHandle);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isAttributeSpecified(int attributeHandle) {
/* 1157 */     if (this._dom != null) {
/* 1158 */       return this._dom.isAttributeSpecified(attributeHandle);
/*      */     }
/*      */     
/* 1161 */     return super.isAttributeSpecified(attributeHandle);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void dispatchCharactersEvents(int nodeHandle, ContentHandler ch, boolean normalize) throws SAXException {
/* 1169 */     if (this._dom != null) {
/* 1170 */       this._dom.dispatchCharactersEvents(nodeHandle, ch, normalize);
/*      */     } else {
/*      */       
/* 1173 */       super.dispatchCharactersEvents(nodeHandle, ch, normalize);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void dispatchToEvents(int nodeHandle, ContentHandler ch) throws SAXException {
/* 1180 */     if (this._dom != null) {
/* 1181 */       this._dom.dispatchToEvents(nodeHandle, ch);
/*      */     } else {
/*      */       
/* 1184 */       super.dispatchToEvents(nodeHandle, ch);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public Node getNode(int nodeHandle) {
/* 1190 */     if (this._dom != null) {
/* 1191 */       return this._dom.getNode(nodeHandle);
/*      */     }
/*      */     
/* 1194 */     return super.getNode(nodeHandle);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean needsTwoThreads() {
/* 1200 */     if (this._dom != null) {
/* 1201 */       return this._dom.needsTwoThreads();
/*      */     }
/*      */     
/* 1204 */     return super.needsTwoThreads();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ContentHandler getContentHandler() {
/* 1210 */     if (this._dom != null) {
/* 1211 */       return this._dom.getContentHandler();
/*      */     }
/*      */     
/* 1214 */     return super.getContentHandler();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public LexicalHandler getLexicalHandler() {
/* 1220 */     if (this._dom != null) {
/* 1221 */       return this._dom.getLexicalHandler();
/*      */     }
/*      */     
/* 1224 */     return super.getLexicalHandler();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityResolver getEntityResolver() {
/* 1230 */     if (this._dom != null) {
/* 1231 */       return this._dom.getEntityResolver();
/*      */     }
/*      */     
/* 1234 */     return super.getEntityResolver();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public DTDHandler getDTDHandler() {
/* 1240 */     if (this._dom != null) {
/* 1241 */       return this._dom.getDTDHandler();
/*      */     }
/*      */     
/* 1244 */     return super.getDTDHandler();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ErrorHandler getErrorHandler() {
/* 1250 */     if (this._dom != null) {
/* 1251 */       return this._dom.getErrorHandler();
/*      */     }
/*      */     
/* 1254 */     return super.getErrorHandler();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public DeclHandler getDeclHandler() {
/* 1260 */     if (this._dom != null) {
/* 1261 */       return this._dom.getDeclHandler();
/*      */     }
/*      */     
/* 1264 */     return super.getDeclHandler();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void appendChild(int newChild, boolean clone, boolean cloneDepth) {
/* 1270 */     if (this._dom != null) {
/* 1271 */       this._dom.appendChild(newChild, clone, cloneDepth);
/*      */     } else {
/*      */       
/* 1274 */       super.appendChild(newChild, clone, cloneDepth);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void appendTextChild(String str) {
/* 1280 */     if (this._dom != null) {
/* 1281 */       this._dom.appendTextChild(str);
/*      */     } else {
/*      */       
/* 1284 */       super.appendTextChild(str);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public SourceLocator getSourceLocatorFor(int node) {
/* 1290 */     if (this._dom != null) {
/* 1291 */       return this._dom.getSourceLocatorFor(node);
/*      */     }
/*      */     
/* 1294 */     return super.getSourceLocatorFor(node);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void documentRegistration() {
/* 1300 */     if (this._dom != null) {
/* 1301 */       this._dom.documentRegistration();
/*      */     } else {
/*      */       
/* 1304 */       super.documentRegistration();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void documentRelease() {
/* 1310 */     if (this._dom != null) {
/* 1311 */       this._dom.documentRelease();
/*      */     } else {
/*      */       
/* 1314 */       super.documentRelease();
/*      */     } 
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/dom/AdaptiveResultTreeImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */