/*     */ package org.apache.xalan.xsltc.runtime;
/*     */ 
/*     */ import org.apache.xml.serializer.EmptySerializer;
/*     */ import org.xml.sax.SAXException;
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
/*     */ public final class StringValueHandler
/*     */   extends EmptySerializer
/*     */ {
/*  33 */   private StringBuffer _buffer = new StringBuffer();
/*  34 */   private String _str = null;
/*     */   private static final String EMPTY_STR = "";
/*     */   private boolean m_escaping = false;
/*  37 */   private int _nestedLevel = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   public void characters(char[] ch, int off, int len) throws SAXException {
/*  42 */     if (this._nestedLevel > 0) {
/*     */       return;
/*     */     }
/*  45 */     if (this._str != null) {
/*  46 */       this._buffer.append(this._str);
/*  47 */       this._str = null;
/*     */     } 
/*  49 */     this._buffer.append(ch, off, len);
/*     */   }
/*     */   
/*     */   public String getValue() {
/*  53 */     if (this._buffer.length() != 0) {
/*  54 */       String str = this._buffer.toString();
/*  55 */       this._buffer.setLength(0);
/*  56 */       return str;
/*     */     } 
/*     */     
/*  59 */     String result = this._str;
/*  60 */     this._str = null;
/*  61 */     return (result != null) ? result : "";
/*     */   }
/*     */ 
/*     */   
/*     */   public void characters(String characters) throws SAXException {
/*  66 */     if (this._nestedLevel > 0) {
/*     */       return;
/*     */     }
/*  69 */     if (this._str == null && this._buffer.length() == 0) {
/*  70 */       this._str = characters;
/*     */     } else {
/*     */       
/*  73 */       if (this._str != null) {
/*  74 */         this._buffer.append(this._str);
/*  75 */         this._str = null;
/*     */       } 
/*     */       
/*  78 */       this._buffer.append(characters);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void startElement(String qname) throws SAXException {
/*  83 */     this._nestedLevel++;
/*     */   }
/*     */   
/*     */   public void endElement(String qname) throws SAXException {
/*  87 */     this._nestedLevel--;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setEscaping(boolean bool) {
/*  93 */     boolean oldEscaping = this.m_escaping;
/*  94 */     this.m_escaping = bool;
/*     */     
/*  96 */     return bool;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getValueOfPI() {
/* 104 */     String value = getValue();
/*     */     
/* 106 */     if (value.indexOf("?>") > 0) {
/* 107 */       int n = value.length();
/* 108 */       StringBuffer valueOfPI = new StringBuffer();
/*     */       
/* 110 */       for (int i = 0; i < n; ) {
/* 111 */         char ch = value.charAt(i++);
/* 112 */         if (ch == '?' && value.charAt(i) == '>') {
/* 113 */           valueOfPI.append("? >"); i++;
/*     */           continue;
/*     */         } 
/* 116 */         valueOfPI.append(ch);
/*     */       } 
/*     */       
/* 119 */       return valueOfPI.toString();
/*     */     } 
/* 121 */     return value;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/runtime/StringValueHandler.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */