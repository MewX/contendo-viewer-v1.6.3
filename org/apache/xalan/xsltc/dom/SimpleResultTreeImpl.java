/*      */ package org.apache.xalan.xsltc.dom;
/*      */ 
/*      */ import javax.xml.transform.SourceLocator;
/*      */ import org.apache.xalan.xsltc.DOM;
/*      */ import org.apache.xalan.xsltc.StripFilter;
/*      */ import org.apache.xalan.xsltc.TransletException;
/*      */ import org.apache.xalan.xsltc.runtime.Hashtable;
/*      */ import org.apache.xml.dtm.DTM;
/*      */ import org.apache.xml.dtm.DTMAxisIterator;
/*      */ import org.apache.xml.dtm.DTMAxisTraverser;
/*      */ import org.apache.xml.dtm.DTMManager;
/*      */ import org.apache.xml.dtm.ref.DTMAxisIteratorBase;
/*      */ import org.apache.xml.dtm.ref.DTMManagerDefault;
/*      */ import org.apache.xml.serializer.EmptySerializer;
/*      */ import org.apache.xml.serializer.SerializationHandler;
/*      */ import org.apache.xml.utils.XMLString;
/*      */ import org.apache.xml.utils.XMLStringDefault;
/*      */ import org.w3c.dom.Node;
/*      */ import org.w3c.dom.NodeList;
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
/*      */ public class SimpleResultTreeImpl
/*      */   extends EmptySerializer
/*      */   implements DOM, DTM
/*      */ {
/*      */   public final class SimpleIterator
/*      */     extends DTMAxisIteratorBase
/*      */   {
/*      */     static final int DIRECTION_UP = 0;
/*      */     static final int DIRECTION_DOWN = 1;
/*      */     static final int NO_TYPE = -1;
/*      */     int _direction;
/*      */     int _type;
/*      */     int _currentNode;
/*      */     private final SimpleResultTreeImpl this$0;
/*      */     
/*      */     public SimpleIterator(SimpleResultTreeImpl this$0) {
/*   84 */       this.this$0 = this$0;
/*      */       this._direction = 1;
/*      */       this._type = -1;
/*      */     } public SimpleIterator(SimpleResultTreeImpl this$0, int direction) {
/*   88 */       this.this$0 = this$0; this._direction = 1; this._type = -1;
/*   89 */       this._direction = direction;
/*      */     }
/*      */     
/*      */     public SimpleIterator(SimpleResultTreeImpl this$0, int direction, int type) {
/*   93 */       this.this$0 = this$0; this._direction = 1; this._type = -1;
/*   94 */       this._direction = direction;
/*   95 */       this._type = type;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int next() {
/*  102 */       if (this._direction == 1) {
/*  103 */         while (this._currentNode < 2) {
/*  104 */           if (this._type != -1) {
/*  105 */             if ((this._currentNode == 0 && this._type == 0) || (this._currentNode == 1 && this._type == 3))
/*      */             {
/*  107 */               return returnNode(this.this$0.getNodeHandle(this._currentNode++));
/*      */             }
/*  109 */             this._currentNode++;
/*      */             continue;
/*      */           } 
/*  112 */           return returnNode(this.this$0.getNodeHandle(this._currentNode++));
/*      */         } 
/*      */         
/*  115 */         return -1;
/*      */       } 
/*      */ 
/*      */       
/*  119 */       while (this._currentNode >= 0) {
/*  120 */         if (this._type != -1) {
/*  121 */           if ((this._currentNode == 0 && this._type == 0) || (this._currentNode == 1 && this._type == 3))
/*      */           {
/*  123 */             return returnNode(this.this$0.getNodeHandle(this._currentNode--));
/*      */           }
/*  125 */           this._currentNode--;
/*      */           continue;
/*      */         } 
/*  128 */         return returnNode(this.this$0.getNodeHandle(this._currentNode--));
/*      */       } 
/*      */       
/*  131 */       return -1;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public DTMAxisIterator setStartNode(int nodeHandle) {
/*  137 */       int nodeID = this.this$0.getNodeIdent(nodeHandle);
/*  138 */       this._startNode = nodeID;
/*      */ 
/*      */       
/*  141 */       if (!this._includeSelf && nodeID != -1) {
/*  142 */         if (this._direction == 1) {
/*  143 */           nodeID++;
/*  144 */         } else if (this._direction == 0) {
/*  145 */           nodeID--;
/*      */         } 
/*      */       }
/*  148 */       this._currentNode = nodeID;
/*  149 */       return (DTMAxisIterator)this;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setMark() {
/*  154 */       this._markedNode = this._currentNode;
/*      */     }
/*      */ 
/*      */     
/*      */     public void gotoMark() {
/*  159 */       this._currentNode = this._markedNode;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public final class SingletonIterator
/*      */     extends DTMAxisIteratorBase
/*      */   {
/*      */     static final int NO_TYPE = -1;
/*      */     
/*      */     int _type;
/*      */     int _currentNode;
/*      */     private final SimpleResultTreeImpl this$0;
/*      */     
/*      */     public SingletonIterator(SimpleResultTreeImpl this$0) {
/*  174 */       this.this$0 = this$0;
/*      */       this._type = -1;
/*      */     }
/*      */     public SingletonIterator(SimpleResultTreeImpl this$0, int type) {
/*  178 */       this.this$0 = this$0; this._type = -1;
/*  179 */       this._type = type;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setMark() {
/*  184 */       this._markedNode = this._currentNode;
/*      */     }
/*      */ 
/*      */     
/*      */     public void gotoMark() {
/*  189 */       this._currentNode = this._markedNode;
/*      */     }
/*      */ 
/*      */     
/*      */     public DTMAxisIterator setStartNode(int nodeHandle) {
/*  194 */       this._currentNode = this._startNode = this.this$0.getNodeIdent(nodeHandle);
/*  195 */       return (DTMAxisIterator)this;
/*      */     }
/*      */ 
/*      */     
/*      */     public int next() {
/*  200 */       if (this._currentNode == -1) {
/*  201 */         return -1;
/*      */       }
/*  203 */       this._currentNode = -1;
/*      */       
/*  205 */       if (this._type != -1) {
/*  206 */         if ((this._currentNode == 0 && this._type == 0) || (this._currentNode == 1 && this._type == 3))
/*      */         {
/*  208 */           return this.this$0.getNodeHandle(this._currentNode);
/*      */         }
/*      */       } else {
/*  211 */         return this.this$0.getNodeHandle(this._currentNode);
/*      */       } 
/*  213 */       return -1;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*  219 */   private static final DTMAxisIterator EMPTY_ITERATOR = (DTMAxisIterator)new DTMAxisIteratorBase() {
/*      */       public DTMAxisIterator reset() {
/*  221 */         return (DTMAxisIterator)this;
/*  222 */       } public DTMAxisIterator setStartNode(int node) { return (DTMAxisIterator)this; } public int next() {
/*  223 */         return -1;
/*      */       }
/*      */       public void setMark() {}
/*  226 */       public int getLast() { return 0; } public void gotoMark() {} public int getPosition() {
/*  227 */         return 0; } public DTMAxisIterator cloneIterator() {
/*  228 */         return (DTMAxisIterator)this;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       public void setRestartable(boolean isRestartable) {}
/*      */     };
/*      */ 
/*      */   
/*      */   public static final int RTF_ROOT = 0;
/*      */   
/*      */   public static final int RTF_TEXT = 1;
/*      */   
/*      */   public static final int NUMBER_OF_NODES = 2;
/*      */   
/*  243 */   private static int _documentURIIndex = 0;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final String EMPTY_STR = "";
/*      */ 
/*      */ 
/*      */   
/*      */   private String _text;
/*      */ 
/*      */ 
/*      */   
/*      */   protected String[] _textArray;
/*      */ 
/*      */   
/*      */   protected XSLTCDTMManager _dtmManager;
/*      */ 
/*      */   
/*  261 */   protected int _size = 0;
/*      */ 
/*      */   
/*      */   private int _documentID;
/*      */ 
/*      */   
/*  267 */   private BitArray _dontEscape = null;
/*      */ 
/*      */   
/*      */   private boolean _escaping = true;
/*      */ 
/*      */ 
/*      */   
/*      */   public SimpleResultTreeImpl(XSLTCDTMManager dtmManager, int documentID) {
/*  275 */     this._dtmManager = dtmManager;
/*  276 */     this._documentID = documentID;
/*  277 */     this._textArray = new String[4];
/*      */   }
/*      */ 
/*      */   
/*      */   public DTMManagerDefault getDTMManager() {
/*  282 */     return this._dtmManager;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getDocument() {
/*  288 */     return this._documentID;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getStringValue() {
/*  294 */     return this._text;
/*      */   }
/*      */ 
/*      */   
/*      */   public DTMAxisIterator getIterator() {
/*  299 */     return (DTMAxisIterator)new SingletonIterator(this, getDocument());
/*      */   }
/*      */ 
/*      */   
/*      */   public DTMAxisIterator getChildren(int node) {
/*  304 */     return (new SimpleIterator(this)).setStartNode(node);
/*      */   }
/*      */ 
/*      */   
/*      */   public DTMAxisIterator getTypedChildren(int type) {
/*  309 */     return (DTMAxisIterator)new SimpleIterator(this, 1, type);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DTMAxisIterator getAxisIterator(int axis) {
/*  316 */     switch (axis) {
/*      */       
/*      */       case 3:
/*      */       case 4:
/*  320 */         return (DTMAxisIterator)new SimpleIterator(this, 1);
/*      */       case 0:
/*      */       case 10:
/*  323 */         return (DTMAxisIterator)new SimpleIterator(this, 0);
/*      */       case 1:
/*  325 */         return (new SimpleIterator(this, 0)).includeSelf();
/*      */       case 5:
/*  327 */         return (new SimpleIterator(this, 1)).includeSelf();
/*      */       case 13:
/*  329 */         return (DTMAxisIterator)new SingletonIterator(this);
/*      */     } 
/*  331 */     return EMPTY_ITERATOR;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public DTMAxisIterator getTypedAxisIterator(int axis, int type) {
/*  337 */     switch (axis) {
/*      */       
/*      */       case 3:
/*      */       case 4:
/*  341 */         return (DTMAxisIterator)new SimpleIterator(this, 1, type);
/*      */       case 0:
/*      */       case 10:
/*  344 */         return (DTMAxisIterator)new SimpleIterator(this, 0, type);
/*      */       case 1:
/*  346 */         return (new SimpleIterator(this, 0, type)).includeSelf();
/*      */       case 5:
/*  348 */         return (new SimpleIterator(this, 1, type)).includeSelf();
/*      */       case 13:
/*  350 */         return (DTMAxisIterator)new SingletonIterator(this, type);
/*      */     } 
/*  352 */     return EMPTY_ITERATOR;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DTMAxisIterator getNthDescendant(int node, int n, boolean includeself) {
/*  359 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public DTMAxisIterator getNamespaceAxisIterator(int axis, int ns) {
/*  364 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DTMAxisIterator getNodeValueIterator(DTMAxisIterator iter, int returnType, String value, boolean op) {
/*  371 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public DTMAxisIterator orderNodes(DTMAxisIterator source, int node) {
/*  376 */     return source;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getNodeName(int node) {
/*  381 */     if (getNodeIdent(node) == 1) {
/*  382 */       return "#text";
/*      */     }
/*  384 */     return "";
/*      */   }
/*      */ 
/*      */   
/*      */   public String getNodeNameX(int node) {
/*  389 */     return "";
/*      */   }
/*      */ 
/*      */   
/*      */   public String getNamespaceName(int node) {
/*  394 */     return "";
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getExpandedTypeID(int nodeHandle) {
/*  400 */     int nodeID = getNodeIdent(nodeHandle);
/*  401 */     if (nodeID == 1)
/*  402 */       return 3; 
/*  403 */     if (nodeID == 0) {
/*  404 */       return 0;
/*      */     }
/*  406 */     return -1;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getNamespaceType(int node) {
/*  411 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getParent(int nodeHandle) {
/*  416 */     int nodeID = getNodeIdent(nodeHandle);
/*  417 */     return (nodeID == 1) ? getNodeHandle(0) : -1;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getAttributeNode(int gType, int element) {
/*  422 */     return -1;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getStringValueX(int nodeHandle) {
/*  427 */     int nodeID = getNodeIdent(nodeHandle);
/*  428 */     if (nodeID == 0 || nodeID == 1) {
/*  429 */       return this._text;
/*      */     }
/*  431 */     return "";
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void copy(int node, SerializationHandler handler) throws TransletException {
/*  437 */     characters(node, handler);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void copy(DTMAxisIterator nodes, SerializationHandler handler) throws TransletException {
/*      */     int node;
/*  444 */     while ((node = nodes.next()) != -1)
/*      */     {
/*  446 */       copy(node, handler);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String shallowCopy(int node, SerializationHandler handler) throws TransletException {
/*  453 */     characters(node, handler);
/*  454 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean lessThan(int node1, int node2) {
/*  459 */     if (node1 == -1) {
/*  460 */       return false;
/*      */     }
/*  462 */     if (node2 == -1) {
/*  463 */       return true;
/*      */     }
/*      */     
/*  466 */     return (node1 < node2);
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
/*      */   public void characters(int node, SerializationHandler handler) throws TransletException {
/*  478 */     int nodeID = getNodeIdent(node);
/*  479 */     if (nodeID == 0 || nodeID == 1) {
/*  480 */       boolean escapeBit = false;
/*  481 */       boolean oldEscapeSetting = false;
/*      */       
/*      */       try {
/*  484 */         for (int i = 0; i < this._size; i++) {
/*      */           
/*  486 */           if (this._dontEscape != null) {
/*  487 */             escapeBit = this._dontEscape.getBit(i);
/*  488 */             if (escapeBit) {
/*  489 */               oldEscapeSetting = handler.setEscaping(false);
/*      */             }
/*      */           } 
/*      */           
/*  493 */           handler.characters(this._textArray[i]);
/*      */           
/*  495 */           if (escapeBit) {
/*  496 */             handler.setEscaping(oldEscapeSetting);
/*      */           }
/*      */         } 
/*      */       } catch (SAXException e) {
/*  500 */         throw new TransletException(e);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Node makeNode(int index) {
/*  508 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public Node makeNode(DTMAxisIterator iter) {
/*  513 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public NodeList makeNodeList(int index) {
/*  518 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public NodeList makeNodeList(DTMAxisIterator iter) {
/*  523 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getLanguage(int node) {
/*  528 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getSize() {
/*  533 */     return 2;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getDocumentURI(int node) {
/*  538 */     return "simple_rtf" + _documentURIIndex++;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFilter(StripFilter filter) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void setupMapping(String[] names, String[] uris, int[] types, String[] namespaces) {}
/*      */ 
/*      */   
/*      */   public boolean isElement(int node) {
/*  551 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isAttribute(int node) {
/*  556 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String lookupNamespace(int node, String prefix) throws TransletException {
/*  562 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNodeIdent(int nodehandle) {
/*  570 */     return (nodehandle != -1) ? (nodehandle - this._documentID) : -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNodeHandle(int nodeId) {
/*  578 */     return (nodeId != -1) ? (nodeId + this._documentID) : -1;
/*      */   }
/*      */ 
/*      */   
/*      */   public DOM getResultTreeFrag(int initialSize, int rtfType) {
/*  583 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public DOM getResultTreeFrag(int initialSize, int rtfType, boolean addToManager) {
/*  588 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public SerializationHandler getOutputDomBuilder() {
/*  593 */     return (SerializationHandler)this;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getNSType(int node) {
/*  598 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getUnparsedEntityURI(String name) {
/*  603 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public Hashtable getElementsWithIDs() {
/*  608 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void startDocument() throws SAXException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endDocument() throws SAXException {
/*  627 */     if (this._size == 1) {
/*  628 */       this._text = this._textArray[0];
/*      */     } else {
/*  630 */       StringBuffer buffer = new StringBuffer();
/*  631 */       for (int i = 0; i < this._size; i++) {
/*  632 */         buffer.append(this._textArray[i]);
/*      */       }
/*  634 */       this._text = buffer.toString();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void characters(String str) throws SAXException {
/*  641 */     if (this._size >= this._textArray.length) {
/*  642 */       String[] newTextArray = new String[this._textArray.length * 2];
/*  643 */       System.arraycopy(this._textArray, 0, newTextArray, 0, this._textArray.length);
/*  644 */       this._textArray = newTextArray;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  649 */     if (!this._escaping) {
/*      */       
/*  651 */       if (this._dontEscape == null) {
/*  652 */         this._dontEscape = new BitArray(8);
/*      */       }
/*      */ 
/*      */       
/*  656 */       if (this._size >= this._dontEscape.size()) {
/*  657 */         this._dontEscape.resize(this._dontEscape.size() * 2);
/*      */       }
/*  659 */       this._dontEscape.setBit(this._size);
/*      */     } 
/*      */     
/*  662 */     this._textArray[this._size++] = str;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void characters(char[] ch, int offset, int length) throws SAXException {
/*  668 */     if (this._size >= this._textArray.length) {
/*  669 */       String[] newTextArray = new String[this._textArray.length * 2];
/*  670 */       System.arraycopy(this._textArray, 0, newTextArray, 0, this._textArray.length);
/*  671 */       this._textArray = newTextArray;
/*      */     } 
/*      */     
/*  674 */     if (!this._escaping) {
/*  675 */       if (this._dontEscape == null) {
/*  676 */         this._dontEscape = new BitArray(8);
/*      */       }
/*      */       
/*  679 */       if (this._size >= this._dontEscape.size()) {
/*  680 */         this._dontEscape.resize(this._dontEscape.size() * 2);
/*      */       }
/*  682 */       this._dontEscape.setBit(this._size);
/*      */     } 
/*      */     
/*  685 */     this._textArray[this._size++] = new String(ch, offset, length);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean setEscaping(boolean escape) throws SAXException {
/*  691 */     boolean temp = this._escaping;
/*  692 */     this._escaping = escape;
/*  693 */     return temp;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFeature(String featureId, boolean state) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setProperty(String property, Object value) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DTMAxisTraverser getAxisTraverser(int axis) {
/*  717 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasChildNodes(int nodeHandle) {
/*  722 */     return (getNodeIdent(nodeHandle) == 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getFirstChild(int nodeHandle) {
/*  727 */     int nodeID = getNodeIdent(nodeHandle);
/*  728 */     if (nodeID == 0) {
/*  729 */       return getNodeHandle(1);
/*      */     }
/*  731 */     return -1;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getLastChild(int nodeHandle) {
/*  736 */     return getFirstChild(nodeHandle);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getAttributeNode(int elementHandle, String namespaceURI, String name) {
/*  741 */     return -1;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getFirstAttribute(int nodeHandle) {
/*  746 */     return -1;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getFirstNamespaceNode(int nodeHandle, boolean inScope) {
/*  751 */     return -1;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getNextSibling(int nodeHandle) {
/*  756 */     return -1;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getPreviousSibling(int nodeHandle) {
/*  761 */     return -1;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getNextAttribute(int nodeHandle) {
/*  766 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNextNamespaceNode(int baseHandle, int namespaceHandle, boolean inScope) {
/*  772 */     return -1;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getOwnerDocument(int nodeHandle) {
/*  777 */     return getDocument();
/*      */   }
/*      */ 
/*      */   
/*      */   public int getDocumentRoot(int nodeHandle) {
/*  782 */     return getDocument();
/*      */   }
/*      */ 
/*      */   
/*      */   public XMLString getStringValue(int nodeHandle) {
/*  787 */     return (XMLString)new XMLStringDefault(getStringValueX(nodeHandle));
/*      */   }
/*      */ 
/*      */   
/*      */   public int getStringValueChunkCount(int nodeHandle) {
/*  792 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public char[] getStringValueChunk(int nodeHandle, int chunkIndex, int[] startAndLen) {
/*  798 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getExpandedTypeID(String namespace, String localName, int type) {
/*  803 */     return -1;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getLocalNameFromExpandedNameID(int ExpandedNameID) {
/*  808 */     return "";
/*      */   }
/*      */ 
/*      */   
/*      */   public String getNamespaceFromExpandedNameID(int ExpandedNameID) {
/*  813 */     return "";
/*      */   }
/*      */ 
/*      */   
/*      */   public String getLocalName(int nodeHandle) {
/*  818 */     return "";
/*      */   }
/*      */ 
/*      */   
/*      */   public String getPrefix(int nodeHandle) {
/*  823 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getNamespaceURI(int nodeHandle) {
/*  828 */     return "";
/*      */   }
/*      */ 
/*      */   
/*      */   public String getNodeValue(int nodeHandle) {
/*  833 */     return (getNodeIdent(nodeHandle) == 1) ? this._text : null;
/*      */   }
/*      */ 
/*      */   
/*      */   public short getNodeType(int nodeHandle) {
/*  838 */     int nodeID = getNodeIdent(nodeHandle);
/*  839 */     if (nodeID == 1)
/*  840 */       return 3; 
/*  841 */     if (nodeID == 0) {
/*  842 */       return 0;
/*      */     }
/*  844 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public short getLevel(int nodeHandle) {
/*  850 */     int nodeID = getNodeIdent(nodeHandle);
/*  851 */     if (nodeID == 1)
/*  852 */       return 2; 
/*  853 */     if (nodeID == 0) {
/*  854 */       return 1;
/*      */     }
/*  856 */     return -1;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isSupported(String feature, String version) {
/*  861 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getDocumentBaseURI() {
/*  866 */     return "";
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDocumentBaseURI(String baseURI) {}
/*      */ 
/*      */   
/*      */   public String getDocumentSystemIdentifier(int nodeHandle) {
/*  875 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getDocumentEncoding(int nodeHandle) {
/*  880 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getDocumentStandalone(int nodeHandle) {
/*  885 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getDocumentVersion(int documentHandle) {
/*  890 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getDocumentAllDeclarationsProcessed() {
/*  895 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getDocumentTypeDeclarationSystemIdentifier() {
/*  900 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getDocumentTypeDeclarationPublicIdentifier() {
/*  905 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getElementById(String elementId) {
/*  910 */     return -1;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean supportsPreStripping() {
/*  915 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isNodeAfter(int firstNodeHandle, int secondNodeHandle) {
/*  920 */     return lessThan(firstNodeHandle, secondNodeHandle);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isCharacterElementContentWhitespace(int nodeHandle) {
/*  925 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isDocumentAllDeclarationsProcessed(int documentHandle) {
/*  930 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isAttributeSpecified(int attributeHandle) {
/*  935 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void dispatchCharactersEvents(int nodeHandle, ContentHandler ch, boolean normalize) throws SAXException {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void dispatchToEvents(int nodeHandle, ContentHandler ch) throws SAXException {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node getNode(int nodeHandle) {
/*  953 */     return makeNode(nodeHandle);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean needsTwoThreads() {
/*  958 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public ContentHandler getContentHandler() {
/*  963 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public LexicalHandler getLexicalHandler() {
/*  968 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityResolver getEntityResolver() {
/*  973 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public DTDHandler getDTDHandler() {
/*  978 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public ErrorHandler getErrorHandler() {
/*  983 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public DeclHandler getDeclHandler() {
/*  988 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void appendChild(int newChild, boolean clone, boolean cloneDepth) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void appendTextChild(String str) {}
/*      */ 
/*      */   
/*      */   public SourceLocator getSourceLocatorFor(int node) {
/* 1001 */     return null;
/*      */   }
/*      */   
/*      */   public void documentRegistration() {}
/*      */   
/*      */   public void documentRelease() {}
/*      */   
/*      */   public void migrateTo(DTMManager manager) {}
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/dom/SimpleResultTreeImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */