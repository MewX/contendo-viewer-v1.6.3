/*     */ package org.apache.batik.util.gui.xmleditor;
/*     */ 
/*     */ import javax.swing.text.AttributeSet;
/*     */ import javax.swing.text.BadLocationException;
/*     */ import javax.swing.text.Element;
/*     */ import javax.swing.text.PlainDocument;
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
/*     */ public class XMLDocument
/*     */   extends PlainDocument
/*     */ {
/*     */   protected XMLScanner lexer;
/*     */   protected XMLContext context;
/*  37 */   protected XMLToken cacheToken = null;
/*     */   
/*     */   public XMLDocument() {
/*  40 */     this(new XMLContext());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XMLDocument(XMLContext context) {
/*  48 */     this.context = context;
/*  49 */     this.lexer = new XMLScanner();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XMLToken getScannerStart(int pos) throws BadLocationException {
/*  57 */     int ctx = 3;
/*  58 */     int offset = 0;
/*  59 */     int tokenOffset = 0;
/*     */     
/*  61 */     if (this.cacheToken != null) {
/*  62 */       if (this.cacheToken.getStartOffset() > pos) {
/*  63 */         this.cacheToken = null;
/*     */       } else {
/*  65 */         ctx = this.cacheToken.getContext();
/*  66 */         offset = this.cacheToken.getStartOffset();
/*  67 */         tokenOffset = offset;
/*     */         
/*  69 */         Element element = getDefaultRootElement();
/*  70 */         int line1 = element.getElementIndex(pos);
/*  71 */         int line2 = element.getElementIndex(offset);
/*     */ 
/*     */         
/*  74 */         if (line1 - line2 < 50) {
/*  75 */           return this.cacheToken;
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*  80 */     String str = getText(offset, pos - offset);
/*  81 */     this.lexer.setString(str);
/*  82 */     this.lexer.reset();
/*     */ 
/*     */     
/*  85 */     int lastCtx = ctx;
/*  86 */     int lastOffset = offset;
/*  87 */     while (offset < pos) {
/*  88 */       lastOffset = offset;
/*  89 */       lastCtx = ctx;
/*     */       
/*  91 */       offset = this.lexer.scan(ctx) + tokenOffset;
/*  92 */       ctx = this.lexer.getScanValue();
/*     */     } 
/*  94 */     this.cacheToken = new XMLToken(lastCtx, lastOffset, offset);
/*  95 */     return this.cacheToken;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
/* 102 */     super.insertString(offset, str, a);
/*     */     
/* 104 */     if (this.cacheToken != null && 
/* 105 */       this.cacheToken.getStartOffset() >= offset) {
/* 106 */       this.cacheToken = null;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(int offs, int len) throws BadLocationException {
/* 114 */     super.remove(offs, len);
/*     */     
/* 116 */     if (this.cacheToken != null && 
/* 117 */       this.cacheToken.getStartOffset() >= offs) {
/* 118 */       this.cacheToken = null;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public int find(String str, int fromIndex, boolean caseSensitive) throws BadLocationException {
/* 134 */     int offset = -1;
/* 135 */     int startOffset = -1;
/* 136 */     int len = 0;
/* 137 */     int charIndex = 0;
/*     */     
/* 139 */     Element rootElement = getDefaultRootElement();
/*     */     
/* 141 */     int elementIndex = rootElement.getElementIndex(fromIndex);
/* 142 */     if (elementIndex < 0) return offset;
/*     */ 
/*     */     
/* 145 */     charIndex = fromIndex - rootElement.getElement(elementIndex).getStartOffset();
/*     */ 
/*     */     
/* 148 */     for (int i = elementIndex; i < rootElement.getElementCount(); i++) {
/* 149 */       Element element = rootElement.getElement(i);
/* 150 */       startOffset = element.getStartOffset();
/* 151 */       if (element.getEndOffset() > getLength()) {
/* 152 */         len = getLength() - startOffset;
/*     */       } else {
/* 154 */         len = element.getEndOffset() - startOffset;
/*     */       } 
/*     */       
/* 157 */       String text = getText(startOffset, len);
/*     */       
/* 159 */       if (!caseSensitive) {
/* 160 */         text = text.toLowerCase();
/* 161 */         str = str.toLowerCase();
/*     */       } 
/*     */       
/* 164 */       charIndex = text.indexOf(str, charIndex);
/* 165 */       if (charIndex != -1) {
/* 166 */         offset = startOffset + charIndex;
/*     */         break;
/*     */       } 
/* 169 */       charIndex = 0;
/*     */     } 
/*     */     
/* 172 */     return offset;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/gui/xmleditor/XMLDocument.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */