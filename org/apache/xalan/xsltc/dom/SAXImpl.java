/*      */ package org.apache.xalan.xsltc.dom;
/*      */ 
/*      */ import java.util.Enumeration;
/*      */ import javax.xml.transform.Source;
/*      */ import javax.xml.transform.dom.DOMSource;
/*      */ import org.apache.xalan.xsltc.DOM;
/*      */ import org.apache.xalan.xsltc.DOMEnhancedForDTM;
/*      */ import org.apache.xalan.xsltc.StripFilter;
/*      */ import org.apache.xalan.xsltc.TransletException;
/*      */ import org.apache.xalan.xsltc.runtime.BasisLibrary;
/*      */ import org.apache.xalan.xsltc.runtime.Hashtable;
/*      */ import org.apache.xml.dtm.Axis;
/*      */ import org.apache.xml.dtm.DTM;
/*      */ import org.apache.xml.dtm.DTMAxisIterator;
/*      */ import org.apache.xml.dtm.DTMManager;
/*      */ import org.apache.xml.dtm.DTMWSFilter;
/*      */ import org.apache.xml.dtm.ref.DTMAxisIterNodeList;
/*      */ import org.apache.xml.dtm.ref.DTMAxisIteratorBase;
/*      */ import org.apache.xml.dtm.ref.DTMDefaultBase;
/*      */ import org.apache.xml.dtm.ref.DTMDefaultBaseIterators;
/*      */ import org.apache.xml.dtm.ref.DTMNodeProxy;
/*      */ import org.apache.xml.dtm.ref.EmptyIterator;
/*      */ import org.apache.xml.dtm.ref.ExpandedNameTable;
/*      */ import org.apache.xml.dtm.ref.sax2dtm.SAX2DTM;
/*      */ import org.apache.xml.dtm.ref.sax2dtm.SAX2DTM2;
/*      */ import org.apache.xml.serializer.SerializationHandler;
/*      */ import org.apache.xml.serializer.ToXMLSAXHandler;
/*      */ import org.apache.xml.utils.SystemIDResolver;
/*      */ import org.apache.xml.utils.XMLStringFactory;
/*      */ import org.w3c.dom.Document;
/*      */ import org.w3c.dom.DocumentType;
/*      */ import org.w3c.dom.Entity;
/*      */ import org.w3c.dom.NamedNodeMap;
/*      */ import org.w3c.dom.Node;
/*      */ import org.w3c.dom.NodeList;
/*      */ import org.xml.sax.Attributes;
/*      */ import org.xml.sax.ContentHandler;
/*      */ import org.xml.sax.SAXException;
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
/*      */ public final class SAXImpl
/*      */   extends SAX2DTM2
/*      */   implements DOMEnhancedForDTM, DOMBuilder
/*      */ {
/*   84 */   private int _uriCount = 0;
/*   85 */   private int _prefixCount = 0;
/*      */ 
/*      */   
/*      */   private int[] _xmlSpaceStack;
/*      */   
/*   90 */   private int _idx = 1;
/*      */   
/*      */   private boolean _preserve = false;
/*      */   
/*      */   private static final String XML_STRING = "xml:";
/*      */   private static final String XML_PREFIX = "xml";
/*      */   private static final String XMLSPACE_STRING = "xml:space";
/*      */   private static final String PRESERVE_STRING = "preserve";
/*      */   private static final String XMLNS_PREFIX = "xmlns";
/*      */   private static final String XML_URI = "http://www.w3.org/XML/1998/namespace";
/*      */   private boolean _escaping = true;
/*      */   private boolean _disableEscaping = false;
/*  102 */   private int _textNodeToProcess = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final String EMPTYSTRING = "";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  112 */   private static final DTMAxisIterator EMPTYITERATOR = EmptyIterator.getInstance();
/*      */   
/*  114 */   private int _namesSize = -1;
/*      */ 
/*      */   
/*  117 */   private Hashtable _nsIndex = new Hashtable();
/*      */ 
/*      */   
/*  120 */   private int _size = 0;
/*      */ 
/*      */   
/*  123 */   private BitArray _dontEscape = null;
/*      */ 
/*      */   
/*  126 */   private String _documentURI = null;
/*  127 */   private static int _documentURIIndex = 0;
/*      */ 
/*      */ 
/*      */   
/*      */   private Document _document;
/*      */ 
/*      */ 
/*      */   
/*  135 */   private Hashtable _node2Ids = null;
/*      */ 
/*      */   
/*      */   private boolean _hasDOMSource = false;
/*      */ 
/*      */   
/*      */   private XSLTCDTMManager _dtmManager;
/*      */ 
/*      */   
/*      */   private Node[] _nodes;
/*      */ 
/*      */   
/*      */   private NodeList[] _nodeLists;
/*      */   
/*      */   private static final String XML_LANG_ATTRIBUTE = "http://www.w3.org/XML/1998/namespace:@lang";
/*      */ 
/*      */   
/*      */   public void setDocumentURI(String uri) {
/*  153 */     if (uri != null) {
/*  154 */       setDocumentBaseURI(SystemIDResolver.getAbsoluteURI(uri));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDocumentURI() {
/*  162 */     String baseURI = getDocumentBaseURI();
/*  163 */     return (baseURI != null) ? baseURI : ("rtf" + _documentURIIndex++);
/*      */   }
/*      */   
/*      */   public String getDocumentURI(int node) {
/*  167 */     return getDocumentURI();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setupMapping(String[] names, String[] urisArray, int[] typesArray, String[] namespaces) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String lookupNamespace(int node, String prefix) throws TransletException {
/*  184 */     SAX2DTM2.AncestorIterator ancestors = new SAX2DTM2.AncestorIterator(this);
/*      */     
/*  186 */     if (isElement(node)) {
/*  187 */       ancestors.includeSelf();
/*      */     }
/*      */     
/*  190 */     ancestors.setStartNode(node); int anode;
/*  191 */     while ((anode = ancestors.next()) != -1) {
/*  192 */       DTMDefaultBaseIterators.NamespaceIterator namespaces = new DTMDefaultBaseIterators.NamespaceIterator((DTMDefaultBaseIterators)this);
/*      */       
/*  194 */       namespaces.setStartNode(anode); int nsnode;
/*  195 */       while ((nsnode = namespaces.next()) != -1) {
/*  196 */         if (getLocalName(nsnode).equals(prefix)) {
/*  197 */           return getNodeValue(nsnode);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  202 */     BasisLibrary.runTimeError("NAMESPACE_PREFIX_ERR", prefix);
/*  203 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isElement(int node) {
/*  210 */     return (getNodeType(node) == 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isAttribute(int node) {
/*  217 */     return (getNodeType(node) == 2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getSize() {
/*  224 */     return getNumberOfNodes();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFilter(StripFilter filter) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean lessThan(int node1, int node2) {
/*  238 */     if (node1 == -1) {
/*  239 */       return false;
/*      */     }
/*      */     
/*  242 */     if (node2 == -1) {
/*  243 */       return true;
/*      */     }
/*      */     
/*  246 */     return (node1 < node2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node makeNode(int index) {
/*  253 */     if (this._nodes == null) {
/*  254 */       this._nodes = new Node[this._namesSize];
/*      */     }
/*      */     
/*  257 */     int nodeID = makeNodeIdentity(index);
/*  258 */     if (nodeID < 0) {
/*  259 */       return null;
/*      */     }
/*  261 */     if (nodeID < this._nodes.length) {
/*  262 */       return (this._nodes[nodeID] != null) ? this._nodes[nodeID] : (this._nodes[nodeID] = (Node)new DTMNodeProxy((DTM)this, index));
/*      */     }
/*      */ 
/*      */     
/*  266 */     return (Node)new DTMNodeProxy((DTM)this, index);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node makeNode(DTMAxisIterator iter) {
/*  275 */     return makeNode(iter.next());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NodeList makeNodeList(int index) {
/*  282 */     if (this._nodeLists == null) {
/*  283 */       this._nodeLists = new NodeList[this._namesSize];
/*      */     }
/*      */     
/*  286 */     int nodeID = makeNodeIdentity(index);
/*  287 */     if (nodeID < 0) {
/*  288 */       return null;
/*      */     }
/*  290 */     if (nodeID < this._nodeLists.length) {
/*  291 */       return (this._nodeLists[nodeID] != null) ? this._nodeLists[nodeID] : (this._nodeLists[nodeID] = (NodeList)new DTMAxisIterNodeList((DTM)this, (DTMAxisIterator)new DTMDefaultBaseIterators.SingletonIterator((DTMDefaultBaseIterators)this, index)));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  296 */     return (NodeList)new DTMAxisIterNodeList((DTM)this, (DTMAxisIterator)new DTMDefaultBaseIterators.SingletonIterator((DTMDefaultBaseIterators)this, index));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NodeList makeNodeList(DTMAxisIterator iter) {
/*  305 */     return (NodeList)new DTMAxisIterNodeList((DTM)this, iter);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public class TypedNamespaceIterator
/*      */     extends DTMDefaultBaseIterators.NamespaceIterator
/*      */   {
/*      */     private String _nsPrefix;
/*      */ 
/*      */ 
/*      */     
/*      */     private final SAXImpl this$0;
/*      */ 
/*      */ 
/*      */     
/*      */     public TypedNamespaceIterator(SAXImpl this$0, int nodeType) {
/*  323 */       super((DTMDefaultBaseIterators)this$0); this.this$0 = this$0;
/*  324 */       if (((DTMDefaultBase)this$0).m_expandedNameTable != null) {
/*  325 */         this._nsPrefix = ((DTMDefaultBase)this$0).m_expandedNameTable.getLocalName(nodeType);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int next() {
/*  335 */       if (this._nsPrefix == null || this._nsPrefix.length() == 0) {
/*  336 */         return -1;
/*      */       }
/*  338 */       int node = -1;
/*  339 */       for (node = super.next(); node != -1; node = super.next()) {
/*  340 */         if (this._nsPrefix.compareTo(this.this$0.getLocalName(node)) == 0) {
/*  341 */           return returnNode(node);
/*      */         }
/*      */       } 
/*  344 */       return -1;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private final class NodeValueIterator
/*      */     extends DTMDefaultBaseIterators.InternalAxisIteratorBase
/*      */   {
/*      */     private DTMAxisIterator _source;
/*      */     
/*      */     private String _value;
/*      */     
/*      */     private boolean _op;
/*      */     
/*      */     private final boolean _isReverse;
/*      */     
/*      */     private int _returnType;
/*      */     
/*      */     private final SAXImpl this$0;
/*      */     
/*      */     public NodeValueIterator(SAXImpl this$0, DTMAxisIterator source, int returnType, String value, boolean op) {
/*  365 */       super((DTMDefaultBaseIterators)this$0); this.this$0 = this$0; this._returnType = 1;
/*  366 */       this._source = source;
/*  367 */       this._returnType = returnType;
/*  368 */       this._value = value;
/*  369 */       this._op = op;
/*  370 */       this._isReverse = source.isReverse();
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean isReverse() {
/*  375 */       return this._isReverse;
/*      */     }
/*      */ 
/*      */     
/*      */     public DTMAxisIterator cloneIterator() {
/*      */       
/*  381 */       try { NodeValueIterator clone = (NodeValueIterator)clone();
/*  382 */         ((DTMAxisIteratorBase)clone)._isRestartable = false;
/*  383 */         clone._source = this._source.cloneIterator();
/*  384 */         clone._value = this._value;
/*  385 */         clone._op = this._op;
/*  386 */         return clone.reset(); } catch (CloneNotSupportedException e)
/*      */       
/*      */       { 
/*  389 */         BasisLibrary.runTimeError("ITERATOR_CLONE_ERR", e.toString());
/*      */         
/*  391 */         return null; }
/*      */     
/*      */     }
/*      */ 
/*      */     
/*      */     public void setRestartable(boolean isRestartable) {
/*  397 */       ((DTMAxisIteratorBase)this)._isRestartable = isRestartable;
/*  398 */       this._source.setRestartable(isRestartable);
/*      */     }
/*      */ 
/*      */     
/*      */     public DTMAxisIterator reset() {
/*  403 */       this._source.reset();
/*  404 */       return resetPosition();
/*      */     }
/*      */ 
/*      */     
/*      */     public int next() {
/*      */       int node;
/*  410 */       while ((node = this._source.next()) != -1) {
/*  411 */         String val = this.this$0.getStringValueX(node);
/*  412 */         if (this._value.equals(val) == this._op) {
/*  413 */           if (this._returnType == 0) {
/*  414 */             return returnNode(node);
/*      */           }
/*      */           
/*  417 */           return returnNode(this.this$0.getParent(node));
/*      */         } 
/*      */       } 
/*      */       
/*  421 */       return -1;
/*      */     }
/*      */ 
/*      */     
/*      */     public DTMAxisIterator setStartNode(int node) {
/*  426 */       if (((DTMAxisIteratorBase)this)._isRestartable) {
/*  427 */         this._source.setStartNode(((DTMAxisIteratorBase)this)._startNode = node);
/*  428 */         return resetPosition();
/*      */       } 
/*  430 */       return (DTMAxisIterator)this;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setMark() {
/*  435 */       this._source.setMark();
/*      */     }
/*      */ 
/*      */     
/*      */     public void gotoMark() {
/*  440 */       this._source.gotoMark();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public DTMAxisIterator getNodeValueIterator(DTMAxisIterator iterator, int type, String value, boolean op) {
/*  447 */     return (DTMAxisIterator)new NodeValueIterator(this, iterator, type, value, op);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DTMAxisIterator orderNodes(DTMAxisIterator source, int node) {
/*  455 */     return (DTMAxisIterator)new DupFilterIterator(source);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DTMAxisIterator getIterator() {
/*  464 */     return (DTMAxisIterator)new DTMDefaultBaseIterators.SingletonIterator((DTMDefaultBaseIterators)this, getDocument());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNSType(int node) {
/*  472 */     String s = getNamespaceURI(node);
/*  473 */     if (s == null) {
/*  474 */       return 0;
/*      */     }
/*  476 */     int eType = getIdForNamespace(s);
/*  477 */     return ((Integer)this._nsIndex.get(new Integer(eType))).intValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNamespaceType(int node) {
/*  487 */     return super.getNamespaceType(node);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int[] setupMapping(String[] names, String[] uris, int[] types, int nNames) {
/*  496 */     int[] result = new int[((DTMDefaultBase)this).m_expandedNameTable.getSize()];
/*  497 */     for (int i = 0; i < nNames; i++) {
/*      */       
/*  499 */       int type = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID(uris[i], names[i], types[i], false);
/*  500 */       result[type] = type;
/*      */     } 
/*  502 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getGeneralizedType(String name) {
/*  509 */     return getGeneralizedType(name, true);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getGeneralizedType(String name, boolean searchOnly) {
/*      */     boolean bool;
/*  516 */     String ns = null;
/*  517 */     int index = -1;
/*      */ 
/*      */ 
/*      */     
/*  521 */     if ((index = name.lastIndexOf(":")) > -1) {
/*  522 */       ns = name.substring(0, index);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  527 */     int lNameStartIdx = index + 1;
/*      */ 
/*      */ 
/*      */     
/*  531 */     if (name.charAt(lNameStartIdx) == '@') {
/*  532 */       bool = true;
/*  533 */       lNameStartIdx++;
/*      */     } else {
/*      */       
/*  536 */       bool = true;
/*      */     } 
/*      */ 
/*      */     
/*  540 */     String lName = (lNameStartIdx == 0) ? name : name.substring(lNameStartIdx);
/*      */     
/*  542 */     return ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID(ns, lName, bool, searchOnly);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short[] getMapping(String[] names, String[] uris, int[] types) {
/*  552 */     if (this._namesSize < 0) {
/*  553 */       return getMapping2(names, uris, types);
/*      */     }
/*      */ 
/*      */     
/*  557 */     int namesLength = names.length;
/*  558 */     int exLength = ((DTMDefaultBase)this).m_expandedNameTable.getSize();
/*      */     
/*  560 */     short[] result = new short[exLength];
/*      */     
/*      */     int i;
/*  563 */     for (i = 0; i < 14; i++) {
/*  564 */       result[i] = (short)i;
/*      */     }
/*      */     
/*  567 */     for (i = 14; i < exLength; i++) {
/*  568 */       result[i] = ((DTMDefaultBase)this).m_expandedNameTable.getType(i);
/*      */     }
/*      */ 
/*      */     
/*  572 */     for (i = 0; i < namesLength; i++) {
/*  573 */       int genType = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID(uris[i], names[i], types[i], true);
/*      */ 
/*      */ 
/*      */       
/*  577 */       if (genType >= 0 && genType < exLength) {
/*  578 */         result[genType] = (short)(i + 14);
/*      */       }
/*      */     } 
/*      */     
/*  582 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int[] getReverseMapping(String[] names, String[] uris, int[] types) {
/*  591 */     int[] result = new int[names.length + 14];
/*      */     
/*      */     int i;
/*  594 */     for (i = 0; i < 14; i++) {
/*  595 */       result[i] = i;
/*      */     }
/*      */ 
/*      */     
/*  599 */     for (i = 0; i < names.length; i++) {
/*  600 */       int type = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID(uris[i], names[i], types[i], true);
/*  601 */       result[i + 14] = type;
/*      */     } 
/*  603 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private short[] getMapping2(String[] names, String[] uris, int[] types) {
/*  613 */     int namesLength = names.length;
/*  614 */     int exLength = ((DTMDefaultBase)this).m_expandedNameTable.getSize();
/*  615 */     int[] generalizedTypes = null;
/*  616 */     if (namesLength > 0) {
/*  617 */       generalizedTypes = new int[namesLength];
/*      */     }
/*      */     
/*  620 */     int resultLength = exLength;
/*      */     int i;
/*  622 */     for (i = 0; i < namesLength; i++) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  627 */       generalizedTypes[i] = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID(uris[i], names[i], types[i], false);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  632 */       if (this._namesSize < 0 && generalizedTypes[i] >= resultLength) {
/*  633 */         resultLength = generalizedTypes[i] + 1;
/*      */       }
/*      */     } 
/*      */     
/*  637 */     short[] result = new short[resultLength];
/*      */ 
/*      */     
/*  640 */     for (i = 0; i < 14; i++) {
/*  641 */       result[i] = (short)i;
/*      */     }
/*      */     
/*  644 */     for (i = 14; i < exLength; i++) {
/*  645 */       result[i] = ((DTMDefaultBase)this).m_expandedNameTable.getType(i);
/*      */     }
/*      */ 
/*      */     
/*  649 */     for (i = 0; i < namesLength; i++) {
/*  650 */       int genType = generalizedTypes[i];
/*  651 */       if (genType >= 0 && genType < resultLength) {
/*  652 */         result[genType] = (short)(i + 14);
/*      */       }
/*      */     } 
/*      */     
/*  656 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short[] getNamespaceMapping(String[] namespaces) {
/*  664 */     int nsLength = namespaces.length;
/*  665 */     int mappingLength = this._uriCount;
/*      */     
/*  667 */     short[] result = new short[mappingLength];
/*      */     
/*      */     int i;
/*  670 */     for (i = 0; i < mappingLength; i++) {
/*  671 */       result[i] = -1;
/*      */     }
/*      */     
/*  674 */     for (i = 0; i < nsLength; i++) {
/*  675 */       int eType = getIdForNamespace(namespaces[i]);
/*  676 */       Integer type = (Integer)this._nsIndex.get(new Integer(eType));
/*  677 */       if (type != null) {
/*  678 */         result[type.intValue()] = (short)i;
/*      */       }
/*      */     } 
/*      */     
/*  682 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short[] getReverseNamespaceMapping(String[] namespaces) {
/*  691 */     int length = namespaces.length;
/*  692 */     short[] result = new short[length];
/*      */     
/*  694 */     for (int i = 0; i < length; i++) {
/*  695 */       int eType = getIdForNamespace(namespaces[i]);
/*  696 */       Integer type = (Integer)this._nsIndex.get(new Integer(eType));
/*  697 */       result[i] = (type == null) ? -1 : type.shortValue();
/*      */     } 
/*      */     
/*  700 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SAXImpl(XSLTCDTMManager mgr, Source source, int dtmIdentity, DTMWSFilter whiteSpaceFilter, XMLStringFactory xstringfactory, boolean doIndexing, boolean buildIdIndex) {
/*  711 */     this(mgr, source, dtmIdentity, whiteSpaceFilter, xstringfactory, doIndexing, 512, buildIdIndex, false);
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
/*      */   public SAXImpl(XSLTCDTMManager mgr, Source source, int dtmIdentity, DTMWSFilter whiteSpaceFilter, XMLStringFactory xstringfactory, boolean doIndexing, int blocksize, boolean buildIdIndex, boolean newNameTable) {
/*  725 */     super((DTMManager)mgr, source, dtmIdentity, whiteSpaceFilter, xstringfactory, doIndexing, blocksize, false, buildIdIndex, newNameTable);
/*      */ 
/*      */     
/*  728 */     this._dtmManager = mgr;
/*  729 */     this._size = blocksize;
/*      */ 
/*      */     
/*  732 */     this._xmlSpaceStack = new int[(blocksize <= 64) ? 4 : 64];
/*      */ 
/*      */     
/*  735 */     this._xmlSpaceStack[0] = 0;
/*      */ 
/*      */ 
/*      */     
/*  739 */     if (source instanceof DOMSource) {
/*  740 */       this._hasDOMSource = true;
/*  741 */       DOMSource domsrc = (DOMSource)source;
/*  742 */       Node node = domsrc.getNode();
/*  743 */       if (node instanceof Document) {
/*  744 */         this._document = (Document)node;
/*      */       } else {
/*      */         
/*  747 */         this._document = node.getOwnerDocument();
/*      */       } 
/*  749 */       this._node2Ids = new Hashtable();
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
/*      */   public void migrateTo(DTMManager manager) {
/*  761 */     super.migrateTo(manager);
/*  762 */     if (manager instanceof XSLTCDTMManager) {
/*  763 */       this._dtmManager = (XSLTCDTMManager)manager;
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
/*      */   public int getElementById(String idString) {
/*  775 */     Node node = this._document.getElementById(idString);
/*  776 */     if (node != null) {
/*  777 */       Integer id = (Integer)this._node2Ids.get(node);
/*  778 */       return (id != null) ? id.intValue() : -1;
/*      */     } 
/*      */     
/*  781 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasDOMSource() {
/*  790 */     return this._hasDOMSource;
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
/*      */   private void xmlSpaceDefine(String val, int node) {
/*  803 */     boolean setting = val.equals("preserve");
/*  804 */     if (setting != this._preserve) {
/*  805 */       this._xmlSpaceStack[this._idx++] = node;
/*  806 */       this._preserve = setting;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void xmlSpaceRevert(int node) {
/*  816 */     if (node == this._xmlSpaceStack[this._idx - 1]) {
/*  817 */       this._idx--;
/*  818 */       this._preserve = !this._preserve;
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
/*      */   protected boolean getShouldStripWhitespace() {
/*  830 */     return this._preserve ? false : super.getShouldStripWhitespace();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void handleTextEscaping() {
/*  837 */     if (this._disableEscaping && this._textNodeToProcess != -1 && _type(this._textNodeToProcess) == 3) {
/*      */       
/*  839 */       if (this._dontEscape == null) {
/*  840 */         this._dontEscape = new BitArray(this._size);
/*      */       }
/*      */ 
/*      */       
/*  844 */       if (this._textNodeToProcess >= this._dontEscape.size()) {
/*  845 */         this._dontEscape.resize(this._dontEscape.size() * 2);
/*      */       }
/*      */       
/*  848 */       this._dontEscape.setBit(this._textNodeToProcess);
/*  849 */       this._disableEscaping = false;
/*      */     } 
/*  851 */     this._textNodeToProcess = -1;
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
/*      */   public void characters(char[] ch, int start, int length) throws SAXException {
/*  864 */     super.characters(ch, start, length);
/*      */     
/*  866 */     this._disableEscaping = !this._escaping;
/*  867 */     this._textNodeToProcess = getNumberOfNodes();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void startDocument() throws SAXException {
/*  875 */     super.startDocument();
/*      */     
/*  877 */     this._nsIndex.put(new Integer(0), new Integer(this._uriCount++));
/*  878 */     definePrefixAndUri("xml", "http://www.w3.org/XML/1998/namespace");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endDocument() throws SAXException {
/*  886 */     super.endDocument();
/*      */     
/*  888 */     handleTextEscaping();
/*  889 */     this._namesSize = ((DTMDefaultBase)this).m_expandedNameTable.getSize();
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
/*      */   public void startElement(String uri, String localName, String qname, Attributes attributes, Node node) throws SAXException {
/*  901 */     startElement(uri, localName, qname, attributes);
/*      */     
/*  903 */     if (this.m_buildIdIndex) {
/*  904 */       this._node2Ids.put(node, new Integer(((SAX2DTM)this).m_parents.peek()));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void startElement(String uri, String localName, String qname, Attributes attributes) throws SAXException {
/*  915 */     super.startElement(uri, localName, qname, attributes);
/*      */     
/*  917 */     handleTextEscaping();
/*      */     
/*  919 */     if (((DTMDefaultBase)this).m_wsfilter != null) {
/*      */ 
/*      */ 
/*      */       
/*  923 */       int index = attributes.getIndex("xml:space");
/*  924 */       if (index >= 0) {
/*  925 */         xmlSpaceDefine(attributes.getValue(index), ((SAX2DTM)this).m_parents.peek());
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endElement(String namespaceURI, String localName, String qname) throws SAXException {
/*  936 */     super.endElement(namespaceURI, localName, qname);
/*      */     
/*  938 */     handleTextEscaping();
/*      */ 
/*      */     
/*  941 */     if (((DTMDefaultBase)this).m_wsfilter != null) {
/*  942 */       xmlSpaceRevert(((SAX2DTM)this).m_previous);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void processingInstruction(String target, String data) throws SAXException {
/*  952 */     super.processingInstruction(target, data);
/*  953 */     handleTextEscaping();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
/*  963 */     super.ignorableWhitespace(ch, start, length);
/*  964 */     this._textNodeToProcess = getNumberOfNodes();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void startPrefixMapping(String prefix, String uri) throws SAXException {
/*  973 */     super.startPrefixMapping(prefix, uri);
/*  974 */     handleTextEscaping();
/*      */     
/*  976 */     definePrefixAndUri(prefix, uri);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void definePrefixAndUri(String prefix, String uri) throws SAXException {
/*  983 */     Integer eType = new Integer(getIdForNamespace(uri));
/*  984 */     if ((Integer)this._nsIndex.get(eType) == null) {
/*  985 */       this._nsIndex.put(eType, new Integer(this._uriCount++));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void comment(char[] ch, int start, int length) throws SAXException {
/*  995 */     super.comment(ch, start, length);
/*  996 */     handleTextEscaping();
/*      */   }
/*      */   
/*      */   public boolean setEscaping(boolean value) {
/* 1000 */     boolean temp = this._escaping;
/* 1001 */     this._escaping = value;
/* 1002 */     return temp;
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
/*      */   public void print(int node, int level) {
/* 1014 */     switch (getNodeType(node)) {
/*      */       
/*      */       case 0:
/*      */       case 9:
/* 1018 */         print(getFirstChild(node), level);
/*      */         return;
/*      */       case 3:
/*      */       case 7:
/*      */       case 8:
/* 1023 */         System.out.print(getStringValueX(node));
/*      */         return;
/*      */     } 
/* 1026 */     String name = getNodeName(node);
/* 1027 */     System.out.print("<" + name);
/* 1028 */     for (int a = getFirstAttribute(node); a != -1; a = getNextAttribute(a))
/*      */     {
/* 1030 */       System.out.print("\n" + getNodeName(a) + "=\"" + getStringValueX(a) + "\"");
/*      */     }
/* 1032 */     System.out.print('>');
/* 1033 */     for (int child = getFirstChild(node); child != -1; 
/* 1034 */       child = getNextSibling(child)) {
/* 1035 */       print(child, level + 1);
/*      */     }
/* 1037 */     System.out.println("</" + name + '>');
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNodeName(int node) {
/* 1048 */     int nodeh = node;
/* 1049 */     short type = getNodeType(nodeh);
/* 1050 */     switch (type) {
/*      */       
/*      */       case 0:
/*      */       case 3:
/*      */       case 8:
/*      */       case 9:
/* 1056 */         return "";
/*      */       case 13:
/* 1058 */         return getLocalName(nodeh);
/*      */     } 
/* 1060 */     return super.getNodeName(nodeh);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNamespaceName(int node) {
/* 1069 */     if (node == -1) {
/* 1070 */       return "";
/*      */     }
/*      */     
/*      */     String s;
/* 1074 */     return ((s = getNamespaceURI(node)) == null) ? "" : s;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getAttributeNode(int type, int element) {
/* 1083 */     int attr = getFirstAttribute(element);
/* 1084 */     for (; attr != -1; 
/* 1085 */       attr = getNextAttribute(attr)) {
/*      */       
/* 1087 */       if (getExpandedTypeID(attr) == type) return attr; 
/*      */     } 
/* 1089 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getAttributeValue(int type, int element) {
/* 1097 */     int attr = getAttributeNode(type, element);
/* 1098 */     return (attr != -1) ? getStringValueX(attr) : "";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getAttributeValue(String name, int element) {
/* 1106 */     return getAttributeValue(getGeneralizedType(name), element);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DTMAxisIterator getChildren(int node) {
/* 1114 */     return (new SAX2DTM2.ChildrenIterator(this)).setStartNode(node);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DTMAxisIterator getTypedChildren(int type) {
/* 1123 */     return (DTMAxisIterator)new SAX2DTM2.TypedChildrenIterator(this, type);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DTMAxisIterator getAxisIterator(int axis) {
/* 1134 */     switch (axis) {
/*      */       
/*      */       case 13:
/* 1137 */         return (DTMAxisIterator)new DTMDefaultBaseIterators.SingletonIterator((DTMDefaultBaseIterators)this);
/*      */       case 3:
/* 1139 */         return (DTMAxisIterator)new SAX2DTM2.ChildrenIterator(this);
/*      */       case 10:
/* 1141 */         return (DTMAxisIterator)new SAX2DTM2.ParentIterator(this);
/*      */       case 0:
/* 1143 */         return (DTMAxisIterator)new SAX2DTM2.AncestorIterator(this);
/*      */       case 1:
/* 1145 */         return (new SAX2DTM2.AncestorIterator(this)).includeSelf();
/*      */       case 2:
/* 1147 */         return (DTMAxisIterator)new SAX2DTM2.AttributeIterator(this);
/*      */       case 4:
/* 1149 */         return (DTMAxisIterator)new SAX2DTM2.DescendantIterator(this);
/*      */       case 5:
/* 1151 */         return (new SAX2DTM2.DescendantIterator(this)).includeSelf();
/*      */       case 6:
/* 1153 */         return (DTMAxisIterator)new SAX2DTM2.FollowingIterator(this);
/*      */       case 11:
/* 1155 */         return (DTMAxisIterator)new SAX2DTM2.PrecedingIterator(this);
/*      */       case 7:
/* 1157 */         return (DTMAxisIterator)new SAX2DTM2.FollowingSiblingIterator(this);
/*      */       case 12:
/* 1159 */         return (DTMAxisIterator)new SAX2DTM2.PrecedingSiblingIterator(this);
/*      */       case 9:
/* 1161 */         return (DTMAxisIterator)new DTMDefaultBaseIterators.NamespaceIterator((DTMDefaultBaseIterators)this);
/*      */     } 
/* 1163 */     BasisLibrary.runTimeError("AXIS_SUPPORT_ERR", Axis.names[axis]);
/*      */     
/* 1165 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DTMAxisIterator getTypedAxisIterator(int axis, int type) {
/* 1175 */     if (axis == 3) {
/* 1176 */       return (DTMAxisIterator)new SAX2DTM2.TypedChildrenIterator(this, type);
/*      */     }
/*      */     
/* 1179 */     if (type == -1) {
/* 1180 */       return EMPTYITERATOR;
/*      */     }
/*      */     
/* 1183 */     switch (axis) {
/*      */       
/*      */       case 13:
/* 1186 */         return (DTMAxisIterator)new SAX2DTM2.TypedSingletonIterator(this, type);
/*      */       case 3:
/* 1188 */         return (DTMAxisIterator)new SAX2DTM2.TypedChildrenIterator(this, type);
/*      */       case 10:
/* 1190 */         return (new SAX2DTM2.ParentIterator(this)).setNodeType(type);
/*      */       case 0:
/* 1192 */         return (DTMAxisIterator)new SAX2DTM2.TypedAncestorIterator(this, type);
/*      */       case 1:
/* 1194 */         return (new SAX2DTM2.TypedAncestorIterator(this, type)).includeSelf();
/*      */       case 2:
/* 1196 */         return (DTMAxisIterator)new SAX2DTM2.TypedAttributeIterator(this, type);
/*      */       case 4:
/* 1198 */         return (DTMAxisIterator)new SAX2DTM2.TypedDescendantIterator(this, type);
/*      */       case 5:
/* 1200 */         return (new SAX2DTM2.TypedDescendantIterator(this, type)).includeSelf();
/*      */       case 6:
/* 1202 */         return (DTMAxisIterator)new SAX2DTM2.TypedFollowingIterator(this, type);
/*      */       case 11:
/* 1204 */         return (DTMAxisIterator)new SAX2DTM2.TypedPrecedingIterator(this, type);
/*      */       case 7:
/* 1206 */         return (DTMAxisIterator)new SAX2DTM2.TypedFollowingSiblingIterator(this, type);
/*      */       case 12:
/* 1208 */         return (DTMAxisIterator)new SAX2DTM2.TypedPrecedingSiblingIterator(this, type);
/*      */       case 9:
/* 1210 */         return (DTMAxisIterator)new TypedNamespaceIterator(this, type);
/*      */     } 
/* 1212 */     BasisLibrary.runTimeError("TYPED_AXIS_SUPPORT_ERR", Axis.names[axis]);
/*      */     
/* 1214 */     return null;
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
/*      */   public DTMAxisIterator getNamespaceAxisIterator(int axis, int ns) {
/* 1227 */     DTMAxisIterator iterator = null;
/*      */     
/* 1229 */     if (ns == -1) {
/* 1230 */       return EMPTYITERATOR;
/*      */     }
/*      */     
/* 1233 */     switch (axis) {
/*      */       case 3:
/* 1235 */         return (DTMAxisIterator)new NamespaceChildrenIterator(this, ns);
/*      */       case 2:
/* 1237 */         return (DTMAxisIterator)new NamespaceAttributeIterator(this, ns);
/*      */     } 
/* 1239 */     return (DTMAxisIterator)new NamespaceWildcardIterator(this, axis, ns);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final class NamespaceWildcardIterator
/*      */     extends DTMDefaultBaseIterators.InternalAxisIteratorBase
/*      */   {
/*      */     protected int m_nsType;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected DTMAxisIterator m_baseIterator;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private final SAXImpl this$0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public NamespaceWildcardIterator(SAXImpl this$0, int axis, int nsType) {
/* 1269 */       super((DTMDefaultBaseIterators)this$0); this.this$0 = this$0;
/* 1270 */       this.m_nsType = nsType;
/*      */ 
/*      */ 
/*      */       
/* 1274 */       switch (axis) {
/*      */ 
/*      */         
/*      */         case 2:
/* 1278 */           this.m_baseIterator = this$0.getAxisIterator(axis);
/*      */ 
/*      */ 
/*      */         
/*      */         case 9:
/* 1283 */           this.m_baseIterator = this$0.getAxisIterator(axis);
/*      */           break;
/*      */       } 
/*      */ 
/*      */       
/* 1288 */       this.m_baseIterator = this$0.getTypedAxisIterator(axis, 1);
/*      */     }
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
/*      */     public DTMAxisIterator setStartNode(int node) {
/* 1303 */       if (((DTMAxisIteratorBase)this)._isRestartable) {
/* 1304 */         ((DTMAxisIteratorBase)this)._startNode = node;
/* 1305 */         this.m_baseIterator.setStartNode(node);
/* 1306 */         resetPosition();
/*      */       } 
/* 1308 */       return (DTMAxisIterator)this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int next() {
/*      */       int node;
/* 1319 */       while ((node = this.m_baseIterator.next()) != -1) {
/*      */         
/* 1321 */         if (this.this$0.getNSType(node) == this.m_nsType) {
/* 1322 */           return returnNode(node);
/*      */         }
/*      */       } 
/*      */       
/* 1326 */       return -1;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public DTMAxisIterator cloneIterator() {
/*      */       
/* 1337 */       try { DTMAxisIterator nestedClone = this.m_baseIterator.cloneIterator();
/* 1338 */         NamespaceWildcardIterator clone = (NamespaceWildcardIterator)clone();
/*      */ 
/*      */         
/* 1341 */         clone.m_baseIterator = nestedClone;
/* 1342 */         clone.m_nsType = this.m_nsType;
/* 1343 */         ((DTMAxisIteratorBase)clone)._isRestartable = false;
/*      */         
/* 1345 */         return (DTMAxisIterator)clone; } catch (CloneNotSupportedException e)
/*      */       
/* 1347 */       { BasisLibrary.runTimeError("ITERATOR_CLONE_ERR", e.toString());
/*      */         
/* 1349 */         return null; }
/*      */     
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean isReverse() {
/* 1359 */       return this.m_baseIterator.isReverse();
/*      */     }
/*      */     
/*      */     public void setMark() {
/* 1363 */       this.m_baseIterator.setMark();
/*      */     }
/*      */     
/*      */     public void gotoMark() {
/* 1367 */       this.m_baseIterator.gotoMark();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final class NamespaceChildrenIterator
/*      */     extends DTMDefaultBaseIterators.InternalAxisIteratorBase
/*      */   {
/*      */     private final int _nsType;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private final SAXImpl this$0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public NamespaceChildrenIterator(SAXImpl this$0, int type) {
/* 1390 */       super((DTMDefaultBaseIterators)this$0); this.this$0 = this$0;
/* 1391 */       this._nsType = type;
/*      */     }
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
/*      */     public DTMAxisIterator setStartNode(int node) {
/* 1404 */       if (node == 0) {
/* 1405 */         node = this.this$0.getDocument();
/*      */       }
/*      */       
/* 1408 */       if (((DTMAxisIteratorBase)this)._isRestartable) {
/* 1409 */         ((DTMAxisIteratorBase)this)._startNode = node;
/* 1410 */         this._currentNode = (node == -1) ? -1 : -2;
/*      */         
/* 1412 */         return resetPosition();
/*      */       } 
/*      */       
/* 1415 */       return (DTMAxisIterator)this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int next() {
/* 1424 */       if (this._currentNode != -1) {
/* 1425 */         int node = (-2 == this._currentNode) ? this.this$0._firstch(this.this$0.makeNodeIdentity(((DTMAxisIteratorBase)this)._startNode)) : this.this$0._nextsib(this._currentNode);
/*      */ 
/*      */         
/* 1428 */         for (; node != -1; 
/* 1429 */           node = this.this$0._nextsib(node)) {
/* 1430 */           int nodeHandle = this.this$0.makeNodeHandle(node);
/*      */           
/* 1432 */           if (this.this$0.getNSType(nodeHandle) == this._nsType) {
/* 1433 */             this._currentNode = node;
/*      */             
/* 1435 */             return returnNode(nodeHandle);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 1440 */       return -1;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final class NamespaceAttributeIterator
/*      */     extends DTMDefaultBaseIterators.InternalAxisIteratorBase
/*      */   {
/*      */     private final int _nsType;
/*      */ 
/*      */ 
/*      */     
/*      */     private final SAXImpl this$0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public NamespaceAttributeIterator(SAXImpl this$0, int nsType) {
/* 1461 */       super((DTMDefaultBaseIterators)this$0);
/*      */       this.this$0 = this$0;
/* 1463 */       this._nsType = nsType;
/*      */     }
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
/*      */     public DTMAxisIterator setStartNode(int node) {
/* 1476 */       if (node == 0) {
/* 1477 */         node = this.this$0.getDocument();
/*      */       }
/*      */       
/* 1480 */       if (((DTMAxisIteratorBase)this)._isRestartable) {
/* 1481 */         int nsType = this._nsType;
/*      */         
/* 1483 */         ((DTMAxisIteratorBase)this)._startNode = node;
/*      */         
/* 1485 */         node = this.this$0.getFirstAttribute(node);
/* 1486 */         for (; node != -1; 
/* 1487 */           node = this.this$0.getNextAttribute(node)) {
/* 1488 */           if (this.this$0.getNSType(node) == nsType) {
/*      */             break;
/*      */           }
/*      */         } 
/*      */         
/* 1493 */         this._currentNode = node;
/* 1494 */         return resetPosition();
/*      */       } 
/*      */       
/* 1497 */       return (DTMAxisIterator)this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int next() {
/* 1506 */       int node = this._currentNode;
/* 1507 */       int nsType = this._nsType;
/*      */ 
/*      */       
/* 1510 */       if (node == -1) {
/* 1511 */         return -1;
/*      */       }
/*      */       
/* 1514 */       int nextNode = this.this$0.getNextAttribute(node);
/* 1515 */       for (; nextNode != -1; 
/* 1516 */         nextNode = this.this$0.getNextAttribute(nextNode)) {
/* 1517 */         if (this.this$0.getNSType(nextNode) == nsType) {
/*      */           break;
/*      */         }
/*      */       } 
/*      */       
/* 1522 */       this._currentNode = nextNode;
/*      */       
/* 1524 */       return returnNode(node);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DTMAxisIterator getTypedDescendantIterator(int type) {
/* 1534 */     return (DTMAxisIterator)new SAX2DTM2.TypedDescendantIterator(this, type);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DTMAxisIterator getNthDescendant(int type, int n, boolean includeself) {
/* 1542 */     SAX2DTM2.TypedDescendantIterator typedDescendantIterator = new SAX2DTM2.TypedDescendantIterator(this, type);
/* 1543 */     return (DTMAxisIterator)new DTMDefaultBaseIterators.NthDescendantIterator((DTMDefaultBaseIterators)this, n);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void characters(int node, SerializationHandler handler) throws TransletException {
/* 1552 */     if (node != -1) {
/*      */       
/* 1554 */       try { dispatchCharactersEvents(node, (ContentHandler)handler, false); } catch (SAXException e)
/*      */       
/* 1556 */       { throw new TransletException(e); }
/*      */     
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void copy(DTMAxisIterator nodes, SerializationHandler handler) throws TransletException {
/*      */     int node;
/* 1568 */     while ((node = nodes.next()) != -1) {
/* 1569 */       copy(node, handler);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void copy(SerializationHandler handler) throws TransletException {
/* 1578 */     copy(getDocument(), handler);
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
/*      */   public void copy(int node, SerializationHandler handler) throws TransletException {
/* 1591 */     copy(node, handler, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void copy(int node, SerializationHandler handler, boolean isChild) throws TransletException {
/* 1598 */     int nodeID = makeNodeIdentity(node);
/* 1599 */     int eType = _exptype2(nodeID);
/* 1600 */     int type = _exptype2Type(eType); try {
/*      */       int c; boolean oldEscapeSetting;
/*      */       boolean escapeBit;
/* 1603 */       switch (type) {
/*      */         
/*      */         case 0:
/*      */         case 9:
/* 1607 */           for (c = _firstch2(nodeID); c != -1; c = _nextsib2(c)) {
/* 1608 */             copy(makeNodeHandle(c), handler, true);
/*      */           }
/*      */           return;
/*      */         case 7:
/* 1612 */           copyPI(node, handler);
/*      */           return;
/*      */         case 8:
/* 1615 */           handler.comment(getStringValueX(node));
/*      */           return;
/*      */         case 3:
/* 1618 */           oldEscapeSetting = false;
/* 1619 */           escapeBit = false;
/*      */           
/* 1621 */           if (this._dontEscape != null) {
/* 1622 */             escapeBit = this._dontEscape.getBit(getNodeIdent(node));
/* 1623 */             if (escapeBit) {
/* 1624 */               oldEscapeSetting = handler.setEscaping(false);
/*      */             }
/*      */           } 
/*      */           
/* 1628 */           copyTextNode(nodeID, handler);
/*      */           
/* 1630 */           if (escapeBit) {
/* 1631 */             handler.setEscaping(oldEscapeSetting);
/*      */           }
/*      */           return;
/*      */         case 2:
/* 1635 */           copyAttribute(nodeID, eType, handler);
/*      */           return;
/*      */         case 13:
/* 1638 */           handler.namespaceAfterStartElement(getNodeNameX(node), getNodeValue(node));
/*      */           return;
/*      */       } 
/* 1641 */       if (type == 1) {
/*      */ 
/*      */         
/* 1644 */         String name = copyElement(nodeID, eType, handler);
/*      */ 
/*      */         
/* 1647 */         copyNS(nodeID, handler, !isChild);
/* 1648 */         copyAttributes(nodeID, handler);
/*      */         
/* 1650 */         for (int i = _firstch2(nodeID); i != -1; i = _nextsib2(i)) {
/* 1651 */           copy(makeNodeHandle(i), handler, true);
/*      */         }
/*      */ 
/*      */         
/* 1655 */         handler.endElement(name);
/*      */       }
/*      */       else {
/*      */         
/* 1659 */         String uri = getNamespaceName(node);
/* 1660 */         if (uri.length() != 0) {
/* 1661 */           String prefix = getPrefix(node);
/* 1662 */           handler.namespaceAfterStartElement(prefix, uri);
/*      */         } 
/* 1664 */         handler.addAttribute(getNodeName(node), getNodeValue(node));
/*      */       } 
/* 1666 */     } catch (Exception e) {
/*      */ 
/*      */ 
/*      */       
/* 1670 */       throw new TransletException(e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void copyPI(int node, SerializationHandler handler) throws TransletException {
/* 1680 */     String target = getNodeName(node);
/* 1681 */     String value = getStringValueX(node);
/*      */ 
/*      */     
/* 1684 */     try { handler.processingInstruction(target, value); } catch (Exception e)
/*      */     
/* 1686 */     { throw new TransletException(e); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String shallowCopy(int node, SerializationHandler handler) throws TransletException {
/* 1696 */     int nodeID = makeNodeIdentity(node);
/* 1697 */     int exptype = _exptype2(nodeID);
/* 1698 */     int type = _exptype2Type(exptype);
/*      */     
/*      */     try { String name;
/* 1701 */       switch (type) {
/*      */         
/*      */         case 1:
/* 1704 */           name = copyElement(nodeID, exptype, handler);
/* 1705 */           copyNS(nodeID, handler, true);
/* 1706 */           return name;
/*      */         case 0:
/*      */         case 9:
/* 1709 */           return "";
/*      */         case 3:
/* 1711 */           copyTextNode(nodeID, handler);
/* 1712 */           return null;
/*      */         case 7:
/* 1714 */           copyPI(node, handler);
/* 1715 */           return null;
/*      */         case 8:
/* 1717 */           handler.comment(getStringValueX(node));
/* 1718 */           return null;
/*      */         case 13:
/* 1720 */           handler.namespaceAfterStartElement(getNodeNameX(node), getNodeValue(node));
/* 1721 */           return null;
/*      */         case 2:
/* 1723 */           copyAttribute(nodeID, exptype, handler);
/* 1724 */           return null;
/*      */       } 
/* 1726 */       String uri1 = getNamespaceName(node);
/* 1727 */       if (uri1.length() != 0) {
/* 1728 */         String prefix = getPrefix(node);
/* 1729 */         handler.namespaceAfterStartElement(prefix, uri1);
/*      */       } 
/* 1731 */       handler.addAttribute(getNodeName(node), getNodeValue(node));
/* 1732 */       return null; } catch (Exception e)
/*      */     
/*      */     { 
/* 1735 */       throw new TransletException(e); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getLanguage(int node) {
/* 1744 */     int parent = node;
/* 1745 */     while (-1 != parent) {
/* 1746 */       if (1 == getNodeType(parent)) {
/* 1747 */         int langAttr = getAttributeNode(parent, "http://www.w3.org/XML/1998/namespace", "lang");
/*      */         
/* 1749 */         if (-1 != langAttr) {
/* 1750 */           return getNodeValue(langAttr);
/*      */         }
/*      */       } 
/*      */       
/* 1754 */       parent = getParent(parent);
/*      */     } 
/* 1756 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DOMBuilder getBuilder() {
/* 1766 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SerializationHandler getOutputDomBuilder() {
/* 1775 */     return (SerializationHandler)new ToXMLSAXHandler(this, "UTF-8");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DOM getResultTreeFrag(int initSize, int rtfType) {
/* 1783 */     return getResultTreeFrag(initSize, rtfType, true);
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
/*      */   public DOM getResultTreeFrag(int initSize, int rtfType, boolean addToManager) {
/* 1796 */     if (rtfType == 0) {
/* 1797 */       if (addToManager) {
/* 1798 */         int dtmPos = this._dtmManager.getFirstFreeDTMID();
/* 1799 */         SimpleResultTreeImpl rtf = new SimpleResultTreeImpl(this._dtmManager, dtmPos << 16);
/*      */         
/* 1801 */         this._dtmManager.addDTM(rtf, dtmPos, 0);
/* 1802 */         return rtf;
/*      */       } 
/*      */       
/* 1805 */       return new SimpleResultTreeImpl(this._dtmManager, 0);
/*      */     } 
/*      */     
/* 1808 */     if (rtfType == 1) {
/* 1809 */       if (addToManager) {
/* 1810 */         int dtmPos = this._dtmManager.getFirstFreeDTMID();
/* 1811 */         AdaptiveResultTreeImpl rtf = new AdaptiveResultTreeImpl(this._dtmManager, dtmPos << 16, ((DTMDefaultBase)this).m_wsfilter, initSize, this.m_buildIdIndex);
/*      */ 
/*      */         
/* 1814 */         this._dtmManager.addDTM(rtf, dtmPos, 0);
/* 1815 */         return rtf;
/*      */       } 
/*      */ 
/*      */       
/* 1819 */       return new AdaptiveResultTreeImpl(this._dtmManager, 0, ((DTMDefaultBase)this).m_wsfilter, initSize, this.m_buildIdIndex);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1824 */     return (DOM)this._dtmManager.getDTM(null, true, ((DTMDefaultBase)this).m_wsfilter, true, false, false, initSize, this.m_buildIdIndex);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Hashtable getElementsWithIDs() {
/* 1834 */     if (((SAX2DTM)this).m_idAttributes == null) {
/* 1835 */       return null;
/*      */     }
/*      */ 
/*      */     
/* 1839 */     Enumeration idValues = ((SAX2DTM)this).m_idAttributes.keys();
/* 1840 */     if (!idValues.hasMoreElements()) {
/* 1841 */       return null;
/*      */     }
/*      */     
/* 1844 */     Hashtable idAttrsTable = new Hashtable();
/*      */     
/* 1846 */     while (idValues.hasMoreElements()) {
/* 1847 */       Object idValue = idValues.nextElement();
/*      */       
/* 1849 */       idAttrsTable.put(idValue, ((SAX2DTM)this).m_idAttributes.get(idValue));
/*      */     } 
/*      */     
/* 1852 */     return idAttrsTable;
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
/*      */   public String getUnparsedEntityURI(String name) {
/* 1864 */     if (this._document != null) {
/* 1865 */       String uri = "";
/* 1866 */       DocumentType doctype = this._document.getDoctype();
/* 1867 */       if (doctype != null) {
/* 1868 */         NamedNodeMap entities = doctype.getEntities();
/*      */         
/* 1870 */         if (entities == null) {
/* 1871 */           return uri;
/*      */         }
/*      */         
/* 1874 */         Entity entity = (Entity)entities.getNamedItem(name);
/*      */         
/* 1876 */         if (entity == null) {
/* 1877 */           return uri;
/*      */         }
/*      */         
/* 1880 */         String notationName = entity.getNotationName();
/* 1881 */         if (notationName != null) {
/* 1882 */           uri = entity.getSystemId();
/* 1883 */           if (uri == null) {
/* 1884 */             uri = entity.getPublicId();
/*      */           }
/*      */         } 
/*      */       } 
/* 1888 */       return uri;
/*      */     } 
/*      */     
/* 1891 */     return super.getUnparsedEntityURI(name);
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/dom/SAXImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */