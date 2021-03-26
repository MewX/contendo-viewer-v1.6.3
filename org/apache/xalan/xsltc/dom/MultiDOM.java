/*     */ package org.apache.xalan.xsltc.dom;
/*     */ 
/*     */ import org.apache.xalan.xsltc.DOM;
/*     */ import org.apache.xalan.xsltc.StripFilter;
/*     */ import org.apache.xalan.xsltc.TransletException;
/*     */ import org.apache.xalan.xsltc.runtime.BasisLibrary;
/*     */ import org.apache.xalan.xsltc.runtime.Hashtable;
/*     */ import org.apache.xml.dtm.DTMAxisIterator;
/*     */ import org.apache.xml.dtm.DTMManager;
/*     */ import org.apache.xml.dtm.ref.DTMAxisIteratorBase;
/*     */ import org.apache.xml.dtm.ref.DTMDefaultBase;
/*     */ import org.apache.xml.serializer.SerializationHandler;
/*     */ import org.apache.xml.utils.SuballocatedIntVector;
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
/*     */ public final class MultiDOM
/*     */   implements DOM
/*     */ {
/*     */   private static final int NO_TYPE = -2;
/*     */   private static final int INITIAL_SIZE = 4;
/*     */   private DOM[] _adapters;
/*     */   private DOMAdapter _main;
/*     */   private DTMManager _dtmManager;
/*     */   private int _free;
/*     */   private int _size;
/*  54 */   private Hashtable _documents = new Hashtable();
/*     */   
/*     */   private final class AxisIterator extends DTMAxisIteratorBase {
/*     */     private final int _axis;
/*     */     private final int _type;
/*     */     private DTMAxisIterator _source;
/*     */     private int _dtmId;
/*     */     private final MultiDOM this$0;
/*     */     
/*     */     public AxisIterator(MultiDOM this$0, int axis, int type) {
/*  64 */       this.this$0 = this$0; this._dtmId = -1;
/*  65 */       this._axis = axis;
/*  66 */       this._type = type;
/*     */     }
/*     */     
/*     */     public int next() {
/*  70 */       if (this._source == null) {
/*  71 */         return -1;
/*     */       }
/*  73 */       return this._source.next();
/*     */     }
/*     */ 
/*     */     
/*     */     public void setRestartable(boolean flag) {
/*  78 */       if (this._source != null) {
/*  79 */         this._source.setRestartable(flag);
/*     */       }
/*     */     }
/*     */     
/*     */     public DTMAxisIterator setStartNode(int node) {
/*  84 */       if (node == -1) {
/*  85 */         return (DTMAxisIterator)this;
/*     */       }
/*     */       
/*  88 */       int dom = node >>> 16;
/*     */ 
/*     */       
/*  91 */       if (this._source == null || this._dtmId != dom) {
/*  92 */         if (this._type == -2) {
/*  93 */           this._source = this.this$0._adapters[dom].getAxisIterator(this._axis);
/*  94 */         } else if (this._axis == 3) {
/*  95 */           this._source = this.this$0._adapters[dom].getTypedChildren(this._type);
/*     */         } else {
/*  97 */           this._source = this.this$0._adapters[dom].getTypedAxisIterator(this._axis, this._type);
/*     */         } 
/*     */       }
/*     */       
/* 101 */       this._dtmId = dom;
/* 102 */       this._source.setStartNode(node);
/* 103 */       return (DTMAxisIterator)this;
/*     */     }
/*     */     
/*     */     public DTMAxisIterator reset() {
/* 107 */       if (this._source != null) {
/* 108 */         this._source.reset();
/*     */       }
/* 110 */       return (DTMAxisIterator)this;
/*     */     }
/*     */     
/*     */     public int getLast() {
/* 114 */       if (this._source != null) {
/* 115 */         return this._source.getLast();
/*     */       }
/*     */       
/* 118 */       return -1;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getPosition() {
/* 123 */       if (this._source != null) {
/* 124 */         return this._source.getPosition();
/*     */       }
/*     */       
/* 127 */       return -1;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isReverse() {
/* 132 */       return Axis.isReverse[this._axis];
/*     */     }
/*     */     
/*     */     public void setMark() {
/* 136 */       if (this._source != null) {
/* 137 */         this._source.setMark();
/*     */       }
/*     */     }
/*     */     
/*     */     public void gotoMark() {
/* 142 */       if (this._source != null) {
/* 143 */         this._source.gotoMark();
/*     */       }
/*     */     }
/*     */     
/*     */     public DTMAxisIterator cloneIterator() {
/* 148 */       AxisIterator clone = new AxisIterator(this.this$0, this._axis, this._type);
/* 149 */       if (this._source != null) {
/* 150 */         clone._source = this._source.cloneIterator();
/*     */       }
/* 152 */       clone._dtmId = this._dtmId;
/* 153 */       return (DTMAxisIterator)clone;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private final class NodeValueIterator
/*     */     extends DTMAxisIteratorBase
/*     */   {
/*     */     private DTMAxisIterator _source;
/*     */     
/*     */     private String _value;
/*     */     
/*     */     private boolean _op;
/*     */     private final boolean _isReverse;
/*     */     private int _returnType;
/*     */     private final MultiDOM this$0;
/*     */     
/*     */     public NodeValueIterator(MultiDOM this$0, DTMAxisIterator source, int returnType, String value, boolean op) {
/* 171 */       this.this$0 = this$0; this._returnType = 1;
/* 172 */       this._source = source;
/* 173 */       this._returnType = returnType;
/* 174 */       this._value = value;
/* 175 */       this._op = op;
/* 176 */       this._isReverse = source.isReverse();
/*     */     }
/*     */     
/*     */     public boolean isReverse() {
/* 180 */       return this._isReverse;
/*     */     }
/*     */     
/*     */     public DTMAxisIterator cloneIterator() {
/*     */       
/* 185 */       try { NodeValueIterator clone = (NodeValueIterator)clone();
/* 186 */         clone._source = this._source.cloneIterator();
/* 187 */         clone.setRestartable(false);
/* 188 */         return clone.reset(); } catch (CloneNotSupportedException e)
/*     */       
/*     */       { 
/* 191 */         BasisLibrary.runTimeError("ITERATOR_CLONE_ERR", e.toString());
/*     */         
/* 193 */         return null; }
/*     */     
/*     */     }
/*     */ 
/*     */     
/*     */     public void setRestartable(boolean isRestartable) {
/* 199 */       this._isRestartable = isRestartable;
/* 200 */       this._source.setRestartable(isRestartable);
/*     */     }
/*     */     
/*     */     public DTMAxisIterator reset() {
/* 204 */       this._source.reset();
/* 205 */       return resetPosition();
/*     */     }
/*     */ 
/*     */     
/*     */     public int next() {
/*     */       int node;
/* 211 */       while ((node = this._source.next()) != -1) {
/* 212 */         String val = this.this$0.getStringValueX(node);
/* 213 */         if (this._value.equals(val) == this._op) {
/* 214 */           if (this._returnType == 0) {
/* 215 */             return returnNode(node);
/*     */           }
/* 217 */           return returnNode(this.this$0.getParent(node));
/*     */         } 
/*     */       } 
/* 220 */       return -1;
/*     */     }
/*     */     
/*     */     public DTMAxisIterator setStartNode(int node) {
/* 224 */       if (this._isRestartable) {
/* 225 */         this._source.setStartNode(this._startNode = node);
/* 226 */         return resetPosition();
/*     */       } 
/* 228 */       return (DTMAxisIterator)this;
/*     */     }
/*     */     
/*     */     public void setMark() {
/* 232 */       this._source.setMark();
/*     */     }
/*     */     
/*     */     public void gotoMark() {
/* 236 */       this._source.gotoMark();
/*     */     }
/*     */   }
/*     */   
/*     */   public MultiDOM(DOM main) {
/* 241 */     this._size = 4;
/* 242 */     this._free = 1;
/* 243 */     this._adapters = new DOM[4];
/* 244 */     DOMAdapter adapter = (DOMAdapter)main;
/* 245 */     this._adapters[0] = adapter;
/* 246 */     this._main = adapter;
/* 247 */     DOM dom = adapter.getDOMImpl();
/* 248 */     if (dom instanceof DTMDefaultBase) {
/* 249 */       this._dtmManager = ((DTMDefaultBase)dom).getManager();
/*     */     }
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
/* 266 */     addDOMAdapter(adapter, false);
/*     */   }
/*     */   
/*     */   public int nextMask() {
/* 270 */     return this._free;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setupMapping(String[] names, String[] uris, int[] types, String[] namespaces) {}
/*     */ 
/*     */   
/*     */   public int addDOMAdapter(DOMAdapter adapter) {
/* 278 */     return addDOMAdapter(adapter, true);
/*     */   }
/*     */ 
/*     */   
/*     */   private int addDOMAdapter(DOMAdapter adapter, boolean indexByURI) {
/* 283 */     DOM dom = adapter.getDOMImpl();
/*     */     
/* 285 */     int domNo = 1;
/* 286 */     int dtmSize = 1;
/* 287 */     SuballocatedIntVector dtmIds = null;
/* 288 */     if (dom instanceof DTMDefaultBase) {
/* 289 */       DTMDefaultBase dtmdb = (DTMDefaultBase)dom;
/* 290 */       dtmIds = dtmdb.getDTMIDs();
/* 291 */       dtmSize = dtmIds.size();
/* 292 */       domNo = dtmIds.elementAt(dtmSize - 1) >>> 16;
/*     */     }
/* 294 */     else if (dom instanceof SimpleResultTreeImpl) {
/* 295 */       SimpleResultTreeImpl simpleRTF = (SimpleResultTreeImpl)dom;
/* 296 */       domNo = simpleRTF.getDocument() >>> 16;
/*     */     } 
/*     */     
/* 299 */     if (domNo >= this._size) {
/* 300 */       int oldSize = this._size;
/*     */       do {
/* 302 */         this._size *= 2;
/* 303 */       } while (this._size <= domNo);
/*     */       
/* 305 */       DOMAdapter[] newArray = new DOMAdapter[this._size];
/* 306 */       System.arraycopy(this._adapters, 0, newArray, 0, oldSize);
/* 307 */       this._adapters = (DOM[])newArray;
/*     */     } 
/*     */     
/* 310 */     this._free = domNo + 1;
/*     */     
/* 312 */     if (dtmSize == 1) {
/* 313 */       this._adapters[domNo] = adapter;
/*     */     }
/* 315 */     else if (dtmIds != null) {
/* 316 */       int domPos = 0;
/* 317 */       for (int i = dtmSize - 1; i >= 0; i--) {
/* 318 */         domPos = dtmIds.elementAt(i) >>> 16;
/* 319 */         this._adapters[domPos] = adapter;
/*     */       } 
/* 321 */       domNo = domPos;
/*     */     } 
/*     */ 
/*     */     
/* 325 */     if (indexByURI) {
/* 326 */       String uri = adapter.getDocumentURI(0);
/* 327 */       this._documents.put(uri, new Integer(domNo));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 333 */     if (dom instanceof AdaptiveResultTreeImpl) {
/* 334 */       AdaptiveResultTreeImpl adaptiveRTF = (AdaptiveResultTreeImpl)dom;
/* 335 */       DOM nestedDom = adaptiveRTF.getNestedDOM();
/* 336 */       if (nestedDom != null) {
/* 337 */         DOMAdapter newAdapter = new DOMAdapter(nestedDom, adapter.getNamesArray(), adapter.getUrisArray(), adapter.getTypesArray(), adapter.getNamespaceArray());
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 342 */         addDOMAdapter(newAdapter);
/*     */       } 
/*     */     } 
/*     */     
/* 346 */     return domNo;
/*     */   }
/*     */   
/*     */   public int getDocumentMask(String uri) {
/* 350 */     Integer domIdx = (Integer)this._documents.get(uri);
/* 351 */     if (domIdx == null) {
/* 352 */       return -1;
/*     */     }
/* 354 */     return domIdx.intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public DOM getDOMAdapter(String uri) {
/* 359 */     Integer domIdx = (Integer)this._documents.get(uri);
/* 360 */     if (domIdx == null) {
/* 361 */       return null;
/*     */     }
/* 363 */     return this._adapters[domIdx.intValue()];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDocument() {
/* 369 */     return this._main.getDocument();
/*     */   }
/*     */   
/*     */   public DTMManager getDTMManager() {
/* 373 */     return this._dtmManager;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DTMAxisIterator getIterator() {
/* 381 */     return this._main.getIterator();
/*     */   }
/*     */   
/*     */   public String getStringValue() {
/* 385 */     return this._main.getStringValue();
/*     */   }
/*     */   
/*     */   public DTMAxisIterator getChildren(int node) {
/* 389 */     return this._adapters[getDTMId(node)].getChildren(node);
/*     */   }
/*     */   
/*     */   public DTMAxisIterator getTypedChildren(int type) {
/* 393 */     return (DTMAxisIterator)new AxisIterator(this, 3, type);
/*     */   }
/*     */   
/*     */   public DTMAxisIterator getAxisIterator(int axis) {
/* 397 */     return (DTMAxisIterator)new AxisIterator(this, axis, -2);
/*     */   }
/*     */ 
/*     */   
/*     */   public DTMAxisIterator getTypedAxisIterator(int axis, int type) {
/* 402 */     return (DTMAxisIterator)new AxisIterator(this, axis, type);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public DTMAxisIterator getNthDescendant(int node, int n, boolean includeself) {
/* 408 */     return this._adapters[getDTMId(node)].getNthDescendant(node, n, includeself);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DTMAxisIterator getNodeValueIterator(DTMAxisIterator iterator, int type, String value, boolean op) {
/* 415 */     return (DTMAxisIterator)new NodeValueIterator(this, iterator, type, value, op);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public DTMAxisIterator getNamespaceAxisIterator(int axis, int ns) {
/* 421 */     DTMAxisIterator iterator = this._main.getNamespaceAxisIterator(axis, ns);
/* 422 */     return iterator;
/*     */   }
/*     */   
/*     */   public DTMAxisIterator orderNodes(DTMAxisIterator source, int node) {
/* 426 */     return this._adapters[getDTMId(node)].orderNodes(source, node);
/*     */   }
/*     */   
/*     */   public int getExpandedTypeID(int node) {
/* 430 */     if (node != -1) {
/* 431 */       return this._adapters[node >>> 16].getExpandedTypeID(node);
/*     */     }
/*     */     
/* 434 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNamespaceType(int node) {
/* 439 */     return this._adapters[getDTMId(node)].getNamespaceType(node);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNSType(int node) {
/* 444 */     return this._adapters[getDTMId(node)].getNSType(node);
/*     */   }
/*     */   
/*     */   public int getParent(int node) {
/* 448 */     if (node == -1) {
/* 449 */       return -1;
/*     */     }
/* 451 */     return this._adapters[node >>> 16].getParent(node);
/*     */   }
/*     */   
/*     */   public int getAttributeNode(int type, int el) {
/* 455 */     if (el == -1) {
/* 456 */       return -1;
/*     */     }
/* 458 */     return this._adapters[el >>> 16].getAttributeNode(type, el);
/*     */   }
/*     */   
/*     */   public String getNodeName(int node) {
/* 462 */     if (node == -1) {
/* 463 */       return "";
/*     */     }
/* 465 */     return this._adapters[node >>> 16].getNodeName(node);
/*     */   }
/*     */   
/*     */   public String getNodeNameX(int node) {
/* 469 */     if (node == -1) {
/* 470 */       return "";
/*     */     }
/* 472 */     return this._adapters[node >>> 16].getNodeNameX(node);
/*     */   }
/*     */   
/*     */   public String getNamespaceName(int node) {
/* 476 */     if (node == -1) {
/* 477 */       return "";
/*     */     }
/* 479 */     return this._adapters[node >>> 16].getNamespaceName(node);
/*     */   }
/*     */   
/*     */   public String getStringValueX(int node) {
/* 483 */     if (node == -1) {
/* 484 */       return "";
/*     */     }
/* 486 */     return this._adapters[node >>> 16].getStringValueX(node);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void copy(int node, SerializationHandler handler) throws TransletException {
/* 492 */     if (node != -1) {
/* 493 */       this._adapters[node >>> 16].copy(node, handler);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void copy(DTMAxisIterator nodes, SerializationHandler handler) throws TransletException {
/*     */     int node;
/* 501 */     while ((node = nodes.next()) != -1) {
/* 502 */       this._adapters[node >>> 16].copy(node, handler);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shallowCopy(int node, SerializationHandler handler) throws TransletException {
/* 510 */     if (node == -1) {
/* 511 */       return "";
/*     */     }
/* 513 */     return this._adapters[node >>> 16].shallowCopy(node, handler);
/*     */   }
/*     */   
/*     */   public boolean lessThan(int node1, int node2) {
/* 517 */     if (node1 == -1) {
/* 518 */       return true;
/*     */     }
/* 520 */     if (node2 == -1) {
/* 521 */       return false;
/*     */     }
/* 523 */     int dom1 = getDTMId(node1);
/* 524 */     int dom2 = getDTMId(node2);
/* 525 */     return (dom1 == dom2) ? this._adapters[dom1].lessThan(node1, node2) : ((dom1 < dom2));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void characters(int textNode, SerializationHandler handler) throws TransletException {
/* 532 */     if (textNode != -1) {
/* 533 */       this._adapters[textNode >>> 16].characters(textNode, handler);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setFilter(StripFilter filter) {
/* 538 */     for (int dom = 0; dom < this._free; dom++) {
/* 539 */       if (this._adapters[dom] != null) {
/* 540 */         this._adapters[dom].setFilter(filter);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public Node makeNode(int index) {
/* 546 */     if (index == -1) {
/* 547 */       return null;
/*     */     }
/* 549 */     return this._adapters[getDTMId(index)].makeNode(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public Node makeNode(DTMAxisIterator iter) {
/* 554 */     return this._main.makeNode(iter);
/*     */   }
/*     */   
/*     */   public NodeList makeNodeList(int index) {
/* 558 */     if (index == -1) {
/* 559 */       return null;
/*     */     }
/* 561 */     return this._adapters[getDTMId(index)].makeNodeList(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public NodeList makeNodeList(DTMAxisIterator iter) {
/* 566 */     return this._main.makeNodeList(iter);
/*     */   }
/*     */   
/*     */   public String getLanguage(int node) {
/* 570 */     return this._adapters[getDTMId(node)].getLanguage(node);
/*     */   }
/*     */   
/*     */   public int getSize() {
/* 574 */     int size = 0;
/* 575 */     for (int i = 0; i < this._size; i++) {
/* 576 */       size += this._adapters[i].getSize();
/*     */     }
/* 578 */     return size;
/*     */   }
/*     */   
/*     */   public String getDocumentURI(int node) {
/* 582 */     if (node == -1) {
/* 583 */       node = 0;
/*     */     }
/* 585 */     return this._adapters[node >>> 16].getDocumentURI(0);
/*     */   }
/*     */   
/*     */   public boolean isElement(int node) {
/* 589 */     if (node == -1) {
/* 590 */       return false;
/*     */     }
/* 592 */     return this._adapters[node >>> 16].isElement(node);
/*     */   }
/*     */   
/*     */   public boolean isAttribute(int node) {
/* 596 */     if (node == -1) {
/* 597 */       return false;
/*     */     }
/* 599 */     return this._adapters[node >>> 16].isAttribute(node);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDTMId(int nodeHandle) {
/* 604 */     if (nodeHandle == -1) {
/* 605 */       return 0;
/*     */     }
/* 607 */     int id = nodeHandle >>> 16;
/* 608 */     while (id >= 2 && this._adapters[id] == this._adapters[id - 1]) {
/* 609 */       id--;
/*     */     }
/* 611 */     return id;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNodeIdent(int nodeHandle) {
/* 616 */     return this._adapters[nodeHandle >>> 16].getNodeIdent(nodeHandle);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNodeHandle(int nodeId) {
/* 621 */     return this._main.getNodeHandle(nodeId);
/*     */   }
/*     */ 
/*     */   
/*     */   public DOM getResultTreeFrag(int initSize, int rtfType) {
/* 626 */     return this._main.getResultTreeFrag(initSize, rtfType);
/*     */   }
/*     */ 
/*     */   
/*     */   public DOM getResultTreeFrag(int initSize, int rtfType, boolean addToManager) {
/* 631 */     return this._main.getResultTreeFrag(initSize, rtfType, addToManager);
/*     */   }
/*     */ 
/*     */   
/*     */   public DOM getMain() {
/* 636 */     return this._main;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SerializationHandler getOutputDomBuilder() {
/* 644 */     return this._main.getOutputDomBuilder();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String lookupNamespace(int node, String prefix) throws TransletException {
/* 650 */     return this._main.lookupNamespace(node, prefix);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUnparsedEntityURI(String entity) {
/* 655 */     return this._main.getUnparsedEntityURI(entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public Hashtable getElementsWithIDs() {
/* 660 */     return this._main.getElementsWithIDs();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/dom/MultiDOM.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */