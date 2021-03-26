/*     */ package org.apache.xalan.xsltc.dom;
/*     */ 
/*     */ import org.apache.xalan.xsltc.DOM;
/*     */ import org.apache.xalan.xsltc.DOMEnhancedForDTM;
/*     */ import org.apache.xalan.xsltc.StripFilter;
/*     */ import org.apache.xalan.xsltc.TransletException;
/*     */ import org.apache.xalan.xsltc.runtime.Hashtable;
/*     */ import org.apache.xml.dtm.DTMAxisIterator;
/*     */ import org.apache.xml.serializer.SerializationHandler;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class DOMAdapter
/*     */   implements DOM
/*     */ {
/*     */   private DOMEnhancedForDTM _enhancedDOM;
/*     */   private DOM _dom;
/*     */   private String[] _namesArray;
/*     */   private String[] _urisArray;
/*     */   private int[] _typesArray;
/*     */   private String[] _namespaceArray;
/*  51 */   private short[] _mapping = null;
/*  52 */   private int[] _reverse = null;
/*  53 */   private short[] _NSmapping = null;
/*  54 */   private short[] _NSreverse = null;
/*     */   
/*  56 */   private StripFilter _filter = null;
/*     */ 
/*     */ 
/*     */   
/*     */   private int _multiDOMMask;
/*     */ 
/*     */ 
/*     */   
/*     */   public DOMAdapter(DOM dom, String[] namesArray, String[] urisArray, int[] typesArray, String[] namespaceArray) {
/*  65 */     if (dom instanceof DOMEnhancedForDTM) {
/*  66 */       this._enhancedDOM = (DOMEnhancedForDTM)dom;
/*     */     }
/*     */     
/*  69 */     this._dom = dom;
/*  70 */     this._namesArray = namesArray;
/*  71 */     this._urisArray = urisArray;
/*  72 */     this._typesArray = typesArray;
/*  73 */     this._namespaceArray = namespaceArray;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setupMapping(String[] names, String[] urisArray, int[] typesArray, String[] namespaces) {
/*  78 */     this._namesArray = names;
/*  79 */     this._urisArray = urisArray;
/*  80 */     this._typesArray = typesArray;
/*  81 */     this._namespaceArray = namespaces;
/*     */   }
/*     */   
/*     */   public String[] getNamesArray() {
/*  85 */     return this._namesArray;
/*     */   }
/*     */   
/*     */   public String[] getUrisArray() {
/*  89 */     return this._urisArray;
/*     */   }
/*     */   
/*     */   public int[] getTypesArray() {
/*  93 */     return this._typesArray;
/*     */   }
/*     */   
/*     */   public String[] getNamespaceArray() {
/*  97 */     return this._namespaceArray;
/*     */   }
/*     */   
/*     */   public DOM getDOMImpl() {
/* 101 */     return this._dom;
/*     */   }
/*     */   
/*     */   private short[] getMapping() {
/* 105 */     if (this._mapping == null && 
/* 106 */       this._enhancedDOM != null) {
/* 107 */       this._mapping = this._enhancedDOM.getMapping(this._namesArray, this._urisArray, this._typesArray);
/*     */     }
/*     */ 
/*     */     
/* 111 */     return this._mapping;
/*     */   }
/*     */   
/*     */   private int[] getReverse() {
/* 115 */     if (this._reverse == null && 
/* 116 */       this._enhancedDOM != null) {
/* 117 */       this._reverse = this._enhancedDOM.getReverseMapping(this._namesArray, this._urisArray, this._typesArray);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 122 */     return this._reverse;
/*     */   }
/*     */   
/*     */   private short[] getNSMapping() {
/* 126 */     if (this._NSmapping == null && 
/* 127 */       this._enhancedDOM != null) {
/* 128 */       this._NSmapping = this._enhancedDOM.getNamespaceMapping(this._namespaceArray);
/*     */     }
/*     */     
/* 131 */     return this._NSmapping;
/*     */   }
/*     */   
/*     */   private short[] getNSReverse() {
/* 135 */     if (this._NSreverse == null && 
/* 136 */       this._enhancedDOM != null) {
/* 137 */       this._NSreverse = this._enhancedDOM.getReverseNamespaceMapping(this._namespaceArray);
/*     */     }
/*     */ 
/*     */     
/* 141 */     return this._NSreverse;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DTMAxisIterator getIterator() {
/* 148 */     return this._dom.getIterator();
/*     */   }
/*     */   
/*     */   public String getStringValue() {
/* 152 */     return this._dom.getStringValue();
/*     */   }
/*     */   
/*     */   public DTMAxisIterator getChildren(int node) {
/* 156 */     if (this._enhancedDOM != null) {
/* 157 */       return this._enhancedDOM.getChildren(node);
/*     */     }
/*     */     
/* 160 */     DTMAxisIterator iterator = this._dom.getChildren(node);
/* 161 */     return iterator.setStartNode(node);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFilter(StripFilter filter) {
/* 166 */     this._filter = filter;
/*     */   }
/*     */   
/*     */   public DTMAxisIterator getTypedChildren(int type) {
/* 170 */     int[] reverse = getReverse();
/*     */     
/* 172 */     if (this._enhancedDOM != null) {
/* 173 */       return this._enhancedDOM.getTypedChildren(reverse[type]);
/*     */     }
/*     */     
/* 176 */     return this._dom.getTypedChildren(type);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public DTMAxisIterator getNamespaceAxisIterator(int axis, int ns) {
/* 182 */     return this._dom.getNamespaceAxisIterator(axis, getNSReverse()[ns]);
/*     */   }
/*     */   
/*     */   public DTMAxisIterator getAxisIterator(int axis) {
/* 186 */     if (this._enhancedDOM != null) {
/* 187 */       return this._enhancedDOM.getAxisIterator(axis);
/*     */     }
/*     */     
/* 190 */     return this._dom.getAxisIterator(axis);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public DTMAxisIterator getTypedAxisIterator(int axis, int type) {
/* 196 */     int[] reverse = getReverse();
/* 197 */     if (this._enhancedDOM != null) {
/* 198 */       return this._enhancedDOM.getTypedAxisIterator(axis, reverse[type]);
/*     */     }
/* 200 */     return this._dom.getTypedAxisIterator(axis, type);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMultiDOMMask() {
/* 205 */     return this._multiDOMMask;
/*     */   }
/*     */   
/*     */   public void setMultiDOMMask(int mask) {
/* 209 */     this._multiDOMMask = mask;
/*     */   }
/*     */ 
/*     */   
/*     */   public DTMAxisIterator getNthDescendant(int type, int n, boolean includeself) {
/* 214 */     return this._dom.getNthDescendant(getReverse()[type], n, includeself);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public DTMAxisIterator getNodeValueIterator(DTMAxisIterator iterator, int type, String value, boolean op) {
/* 220 */     return this._dom.getNodeValueIterator(iterator, type, value, op);
/*     */   }
/*     */   
/*     */   public DTMAxisIterator orderNodes(DTMAxisIterator source, int node) {
/* 224 */     return this._dom.orderNodes(source, node);
/*     */   }
/*     */   
/*     */   public int getExpandedTypeID(int node) {
/* 228 */     if (this._enhancedDOM != null) {
/* 229 */       return getMapping()[this._enhancedDOM.getExpandedTypeID2(node)];
/*     */     }
/*     */     
/* 232 */     return getMapping()[this._dom.getExpandedTypeID(node)];
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNamespaceType(int node) {
/* 237 */     return getNSMapping()[this._dom.getNSType(node)];
/*     */   }
/*     */   
/*     */   public int getNSType(int node) {
/* 241 */     return this._dom.getNSType(node);
/*     */   }
/*     */   
/*     */   public int getParent(int node) {
/* 245 */     return this._dom.getParent(node);
/*     */   }
/*     */   
/*     */   public int getAttributeNode(int type, int element) {
/* 249 */     return this._dom.getAttributeNode(getReverse()[type], element);
/*     */   }
/*     */   
/*     */   public String getNodeName(int node) {
/* 253 */     if (node == -1) {
/* 254 */       return "";
/*     */     }
/* 256 */     return this._dom.getNodeName(node);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNodeNameX(int node) {
/* 261 */     if (node == -1) {
/* 262 */       return "";
/*     */     }
/* 264 */     return this._dom.getNodeNameX(node);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNamespaceName(int node) {
/* 269 */     if (node == -1) {
/* 270 */       return "";
/*     */     }
/* 272 */     return this._dom.getNamespaceName(node);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getStringValueX(int node) {
/* 277 */     if (this._enhancedDOM != null) {
/* 278 */       return this._enhancedDOM.getStringValueX(node);
/*     */     }
/*     */     
/* 281 */     if (node == -1) {
/* 282 */       return "";
/*     */     }
/* 284 */     return this._dom.getStringValueX(node);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void copy(int node, SerializationHandler handler) throws TransletException {
/* 291 */     this._dom.copy(node, handler);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void copy(DTMAxisIterator nodes, SerializationHandler handler) throws TransletException {
/* 297 */     this._dom.copy(nodes, handler);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String shallowCopy(int node, SerializationHandler handler) throws TransletException {
/* 303 */     if (this._enhancedDOM != null) {
/* 304 */       return this._enhancedDOM.shallowCopy(node, handler);
/*     */     }
/*     */     
/* 307 */     return this._dom.shallowCopy(node, handler);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean lessThan(int node1, int node2) {
/* 313 */     return this._dom.lessThan(node1, node2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void characters(int textNode, SerializationHandler handler) throws TransletException {
/* 319 */     if (this._enhancedDOM != null) {
/* 320 */       this._enhancedDOM.characters(textNode, handler);
/*     */     } else {
/*     */       
/* 323 */       this._dom.characters(textNode, handler);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Node makeNode(int index) {
/* 329 */     return this._dom.makeNode(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public Node makeNode(DTMAxisIterator iter) {
/* 334 */     return this._dom.makeNode(iter);
/*     */   }
/*     */ 
/*     */   
/*     */   public NodeList makeNodeList(int index) {
/* 339 */     return this._dom.makeNodeList(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public NodeList makeNodeList(DTMAxisIterator iter) {
/* 344 */     return this._dom.makeNodeList(iter);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLanguage(int node) {
/* 349 */     return this._dom.getLanguage(node);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSize() {
/* 354 */     return this._dom.getSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDocumentURI(String uri) {
/* 359 */     if (this._enhancedDOM != null) {
/* 360 */       this._enhancedDOM.setDocumentURI(uri);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDocumentURI() {
/* 366 */     if (this._enhancedDOM != null) {
/* 367 */       return this._enhancedDOM.getDocumentURI();
/*     */     }
/*     */     
/* 370 */     return "";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDocumentURI(int node) {
/* 376 */     return this._dom.getDocumentURI(node);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDocument() {
/* 381 */     return this._dom.getDocument();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isElement(int node) {
/* 386 */     return this._dom.isElement(node);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAttribute(int node) {
/* 391 */     return this._dom.isAttribute(node);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNodeIdent(int nodeHandle) {
/* 396 */     return this._dom.getNodeIdent(nodeHandle);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNodeHandle(int nodeId) {
/* 401 */     return this._dom.getNodeHandle(nodeId);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DOM getResultTreeFrag(int initSize, int rtfType) {
/* 409 */     if (this._enhancedDOM != null) {
/* 410 */       return this._enhancedDOM.getResultTreeFrag(initSize, rtfType);
/*     */     }
/*     */     
/* 413 */     return this._dom.getResultTreeFrag(initSize, rtfType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DOM getResultTreeFrag(int initSize, int rtfType, boolean addToManager) {
/* 423 */     if (this._enhancedDOM != null) {
/* 424 */       return this._enhancedDOM.getResultTreeFrag(initSize, rtfType, addToManager);
/*     */     }
/*     */ 
/*     */     
/* 428 */     return this._dom.getResultTreeFrag(initSize, rtfType, addToManager);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SerializationHandler getOutputDomBuilder() {
/* 438 */     return this._dom.getOutputDomBuilder();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String lookupNamespace(int node, String prefix) throws TransletException {
/* 444 */     return this._dom.lookupNamespace(node, prefix);
/*     */   }
/*     */   
/*     */   public String getUnparsedEntityURI(String entity) {
/* 448 */     return this._dom.getUnparsedEntityURI(entity);
/*     */   }
/*     */   
/*     */   public Hashtable getElementsWithIDs() {
/* 452 */     return this._dom.getElementsWithIDs();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/dom/DOMAdapter.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */