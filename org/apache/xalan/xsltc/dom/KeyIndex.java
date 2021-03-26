/*     */ package org.apache.xalan.xsltc.dom;
/*     */ 
/*     */ import java.util.StringTokenizer;
/*     */ import org.apache.xalan.xsltc.DOM;
/*     */ import org.apache.xalan.xsltc.DOMEnhancedForDTM;
/*     */ import org.apache.xalan.xsltc.runtime.Hashtable;
/*     */ import org.apache.xalan.xsltc.util.IntegerArray;
/*     */ import org.apache.xml.dtm.DTMAxisIterator;
/*     */ import org.apache.xml.dtm.ref.DTMAxisIteratorBase;
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
/*     */ public class KeyIndex
/*     */   extends DTMAxisIteratorBase
/*     */ {
/*  41 */   private Hashtable _index = new Hashtable();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  47 */   private IntegerArray _nodes = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private DOM _dom;
/*     */ 
/*     */ 
/*     */   
/*     */   private DOMEnhancedForDTM _enhancedDOM;
/*     */ 
/*     */ 
/*     */   
/*  60 */   private int _markedPosition = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   public KeyIndex(int dummy) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRestartable(boolean flag) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(Object value, int node) {
/*     */     IntegerArray nodes;
/*  74 */     if ((nodes = (IntegerArray)this._index.get(value)) == null) {
/*  75 */       this._index.put(value, nodes = new IntegerArray());
/*     */     }
/*  77 */     nodes.add(node);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void merge(KeyIndex other) {
/*  84 */     if (other == null)
/*     */       return; 
/*  86 */     if (other._nodes != null) {
/*  87 */       if (this._nodes == null) {
/*  88 */         this._nodes = other._nodes;
/*     */       } else {
/*     */         
/*  91 */         this._nodes.merge(other._nodes);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void lookupId(Object value) {
/* 105 */     this._nodes = null;
/*     */     
/* 107 */     StringTokenizer values = new StringTokenizer((String)value);
/* 108 */     while (values.hasMoreElements()) {
/* 109 */       String token = (String)values.nextElement();
/* 110 */       IntegerArray nodes = (IntegerArray)this._index.get(token);
/*     */       
/* 112 */       if (nodes == null && this._enhancedDOM != null && this._enhancedDOM.hasDOMSource())
/*     */       {
/* 114 */         nodes = getDOMNodeById(token);
/*     */       }
/*     */       
/* 117 */       if (nodes == null)
/*     */         continue; 
/* 119 */       if (this._nodes == null) {
/* 120 */         this._nodes = nodes;
/*     */         continue;
/*     */       } 
/* 123 */       this._nodes.merge(nodes);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IntegerArray getDOMNodeById(String id) {
/* 135 */     IntegerArray nodes = null;
/* 136 */     if (this._enhancedDOM != null) {
/* 137 */       int ident = this._enhancedDOM.getElementById(id);
/* 138 */       if (ident != -1) {
/* 139 */         nodes = new IntegerArray();
/* 140 */         this._index.put(id, nodes);
/* 141 */         nodes.add(ident);
/*     */       } 
/*     */     } 
/* 144 */     return nodes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void lookupKey(Object value) {
/* 152 */     this._nodes = (IntegerArray)this._index.get(value);
/* 153 */     this._position = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int next() {
/* 160 */     if (this._nodes == null) return -1;
/*     */     
/* 162 */     return (this._position < this._nodes.cardinality()) ? this._dom.getNodeHandle(this._nodes.at(this._position++)) : -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int containsID(int node, Object value) {
/* 167 */     String string = (String)value;
/* 168 */     if (string.indexOf(' ') > -1) {
/* 169 */       StringTokenizer values = new StringTokenizer(string);
/*     */       
/* 171 */       while (values.hasMoreElements()) {
/* 172 */         String token = (String)values.nextElement();
/* 173 */         IntegerArray integerArray = (IntegerArray)this._index.get(token);
/*     */         
/* 175 */         if (integerArray == null && this._enhancedDOM != null && this._enhancedDOM.hasDOMSource())
/*     */         {
/* 177 */           integerArray = getDOMNodeById(token);
/*     */         }
/* 179 */         if (integerArray != null && integerArray.indexOf(node) >= 0) {
/* 180 */           return 1;
/*     */         }
/*     */       } 
/* 183 */       return 0;
/*     */     } 
/*     */     
/* 186 */     IntegerArray nodes = (IntegerArray)this._index.get(value);
/* 187 */     if (nodes == null && this._enhancedDOM != null && this._enhancedDOM.hasDOMSource()) {
/* 188 */       nodes = getDOMNodeById(string);
/*     */     }
/* 190 */     return (nodes != null && nodes.indexOf(node) >= 0) ? 1 : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int containsKey(int node, Object value) {
/* 195 */     IntegerArray nodes = (IntegerArray)this._index.get(value);
/* 196 */     return (nodes != null && nodes.indexOf(node) >= 0) ? 1 : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DTMAxisIterator reset() {
/* 203 */     this._position = 0;
/* 204 */     return (DTMAxisIterator)this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLast() {
/* 211 */     return (this._nodes == null) ? 0 : this._nodes.cardinality();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPosition() {
/* 218 */     return this._position;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMark() {
/* 225 */     this._markedPosition = this._position;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void gotoMark() {
/* 232 */     this._position = this._markedPosition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DTMAxisIterator setStartNode(int start) {
/* 240 */     if (start == -1) {
/* 241 */       this._nodes = null;
/*     */     }
/* 243 */     else if (this._nodes != null) {
/* 244 */       this._position = 0;
/*     */     } 
/* 246 */     return (DTMAxisIterator)this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStartNode() {
/* 254 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReverse() {
/* 261 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DTMAxisIterator cloneIterator() {
/* 268 */     KeyIndex other = new KeyIndex(0);
/* 269 */     other._index = this._index;
/* 270 */     other._nodes = this._nodes;
/* 271 */     other._position = this._position;
/* 272 */     return (DTMAxisIterator)other;
/*     */   }
/*     */   
/*     */   public void setDom(DOM dom) {
/* 276 */     this._dom = dom;
/* 277 */     if (dom instanceof DOMEnhancedForDTM) {
/* 278 */       this._enhancedDOM = (DOMEnhancedForDTM)dom;
/*     */     }
/* 280 */     else if (dom instanceof DOMAdapter) {
/* 281 */       DOM idom = ((DOMAdapter)dom).getDOMImpl();
/* 282 */       if (idom instanceof DOMEnhancedForDTM)
/* 283 */         this._enhancedDOM = (DOMEnhancedForDTM)idom; 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/dom/KeyIndex.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */