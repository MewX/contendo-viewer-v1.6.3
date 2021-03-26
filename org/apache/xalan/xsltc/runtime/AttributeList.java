/*     */ package org.apache.xalan.xsltc.runtime;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import org.xml.sax.Attributes;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AttributeList
/*     */   implements Attributes
/*     */ {
/*     */   private static final String EMPTYSTRING = "";
/*     */   private static final String CDATASTRING = "CDATA";
/*     */   private Hashtable _attributes;
/*     */   private Vector _names;
/*     */   private Vector _qnames;
/*     */   private Vector _values;
/*     */   private Vector _uris;
/*  50 */   private int _length = 0;
/*     */ 
/*     */   
/*     */   public AttributeList() {}
/*     */ 
/*     */   
/*     */   public AttributeList(Attributes attributes) {
/*  57 */     this();
/*  58 */     if (attributes != null) {
/*  59 */       int count = attributes.getLength();
/*  60 */       for (int i = 0; i < count; i++) {
/*  61 */         add(attributes.getQName(i), attributes.getValue(i));
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
/*     */   private void alloc() {
/*  73 */     this._attributes = new Hashtable();
/*  74 */     this._names = new Vector();
/*  75 */     this._values = new Vector();
/*  76 */     this._qnames = new Vector();
/*  77 */     this._uris = new Vector();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLength() {
/*  84 */     return this._length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getURI(int index) {
/*  91 */     if (index < this._length) {
/*  92 */       return this._uris.elementAt(index);
/*     */     }
/*  94 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName(int index) {
/* 101 */     if (index < this._length) {
/* 102 */       return this._names.elementAt(index);
/*     */     }
/* 104 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getQName(int pos) {
/* 111 */     if (pos < this._length) {
/* 112 */       return this._qnames.elementAt(pos);
/*     */     }
/* 114 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getType(int index) {
/* 121 */     return "CDATA";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getIndex(String namespaceURI, String localPart) {
/* 128 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getIndex(String qname) {
/* 135 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getType(String uri, String localName) {
/* 142 */     return "CDATA";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getType(String qname) {
/* 149 */     return "CDATA";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getValue(int pos) {
/* 156 */     if (pos < this._length) {
/* 157 */       return this._values.elementAt(pos);
/*     */     }
/* 159 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getValue(String qname) {
/* 166 */     if (this._attributes != null) {
/* 167 */       Integer obj = (Integer)this._attributes.get(qname);
/* 168 */       if (obj == null) return null; 
/* 169 */       return getValue(obj.intValue());
/*     */     } 
/*     */     
/* 172 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getValue(String uri, String localName) {
/* 179 */     return getValue(uri + ':' + localName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(String qname, String value) {
/* 187 */     if (this._attributes == null) {
/* 188 */       alloc();
/*     */     }
/*     */     
/* 191 */     Integer obj = (Integer)this._attributes.get(qname);
/* 192 */     if (obj == null) {
/* 193 */       this._attributes.put(qname, obj = new Integer(this._length++));
/* 194 */       this._qnames.addElement(qname);
/* 195 */       this._values.addElement(value);
/* 196 */       int col = qname.lastIndexOf(':');
/* 197 */       if (col > -1) {
/* 198 */         this._uris.addElement(qname.substring(0, col));
/* 199 */         this._names.addElement(qname.substring(col + 1));
/*     */       } else {
/*     */         
/* 202 */         this._uris.addElement("");
/* 203 */         this._names.addElement(qname);
/*     */       } 
/*     */     } else {
/*     */       
/* 207 */       int index = obj.intValue();
/* 208 */       this._values.set(index, value);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 216 */     this._length = 0;
/* 217 */     if (this._attributes != null) {
/* 218 */       this._attributes.clear();
/* 219 */       this._names.removeAllElements();
/* 220 */       this._values.removeAllElements();
/* 221 */       this._qnames.removeAllElements();
/* 222 */       this._uris.removeAllElements();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/runtime/AttributeList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */