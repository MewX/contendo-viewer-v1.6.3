/*     */ package org.apache.xml.utils;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import javax.xml.transform.SourceLocator;
/*     */ import org.xml.sax.Locator;
/*     */ import org.xml.sax.SAXParseException;
/*     */ import org.xml.sax.helpers.LocatorImpl;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SAXSourceLocator
/*     */   extends LocatorImpl
/*     */   implements Serializable, SourceLocator
/*     */ {
/*     */   Locator m_locator;
/*     */   
/*     */   public SAXSourceLocator() {}
/*     */   
/*     */   public SAXSourceLocator(Locator locator) {
/*  56 */     this.m_locator = locator;
/*  57 */     setColumnNumber(locator.getColumnNumber());
/*  58 */     setLineNumber(locator.getLineNumber());
/*  59 */     setPublicId(locator.getPublicId());
/*  60 */     setSystemId(locator.getSystemId());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SAXSourceLocator(SourceLocator locator) {
/*  71 */     this.m_locator = null;
/*  72 */     setColumnNumber(locator.getColumnNumber());
/*  73 */     setLineNumber(locator.getLineNumber());
/*  74 */     setPublicId(locator.getPublicId());
/*  75 */     setSystemId(locator.getSystemId());
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
/*     */   public SAXSourceLocator(SAXParseException spe) {
/*  87 */     setLineNumber(spe.getLineNumber());
/*  88 */     setColumnNumber(spe.getColumnNumber());
/*  89 */     setPublicId(spe.getPublicId());
/*  90 */     setSystemId(spe.getSystemId());
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
/*     */   public String getPublicId() {
/* 106 */     return (null == this.m_locator) ? super.getPublicId() : this.m_locator.getPublicId();
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
/*     */   public String getSystemId() {
/* 125 */     return (null == this.m_locator) ? super.getSystemId() : this.m_locator.getSystemId();
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
/*     */   public int getLineNumber() {
/* 145 */     return (null == this.m_locator) ? super.getLineNumber() : this.m_locator.getLineNumber();
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
/*     */   public int getColumnNumber() {
/* 165 */     return (null == this.m_locator) ? super.getColumnNumber() : this.m_locator.getColumnNumber();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/SAXSourceLocator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */