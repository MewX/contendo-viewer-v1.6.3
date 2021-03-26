/*     */ package org.apache.xpath.objects;
/*     */ 
/*     */ import org.apache.xml.utils.FastStringBuffer;
/*     */ import org.apache.xpath.res.XPATHMessages;
/*     */ import org.xml.sax.ContentHandler;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.ext.LexicalHandler;
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
/*     */ public class XStringForChars
/*     */   extends XString
/*     */ {
/*     */   int m_start;
/*     */   int m_length;
/*  37 */   protected String m_strCache = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XStringForChars(char[] val, int start, int length) {
/*  48 */     super(val);
/*  49 */     this.m_start = start;
/*  50 */     this.m_length = length;
/*  51 */     if (null == val) {
/*  52 */       throw new IllegalArgumentException(XPATHMessages.createXPATHMessage("ER_FASTSTRINGBUFFER_CANNOT_BE_NULL", null));
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
/*     */   private XStringForChars(String val) {
/*  64 */     super(val);
/*  65 */     throw new IllegalArgumentException(XPATHMessages.createXPATHMessage("ER_XSTRINGFORCHARS_CANNOT_TAKE_STRING", null));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FastStringBuffer fsb() {
/*  76 */     throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_FSB_NOT_SUPPORTED_XSTRINGFORCHARS", null));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void appendToFsb(FastStringBuffer fsb) {
/*  86 */     fsb.append((char[])this.m_obj, this.m_start, this.m_length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasString() {
/*  97 */     return (null != this.m_strCache);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String str() {
/* 108 */     if (null == this.m_strCache) {
/* 109 */       this.m_strCache = new String((char[])this.m_obj, this.m_start, this.m_length);
/*     */     }
/* 111 */     return this.m_strCache;
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
/*     */   public Object object() {
/* 123 */     return str();
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
/*     */ 
/*     */   
/*     */   public void dispatchCharactersEvents(ContentHandler ch) throws SAXException {
/* 140 */     ch.characters((char[])this.m_obj, this.m_start, this.m_length);
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
/*     */   public void dispatchAsComment(LexicalHandler lh) throws SAXException {
/* 155 */     lh.comment((char[])this.m_obj, this.m_start, this.m_length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int length() {
/* 166 */     return this.m_length;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public char charAt(int index) {
/* 184 */     return ((char[])this.m_obj)[index + this.m_start];
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
/*     */   public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) {
/* 210 */     System.arraycopy(this.m_obj, this.m_start + srcBegin, dst, dstBegin, srcEnd);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/objects/XStringForChars.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */