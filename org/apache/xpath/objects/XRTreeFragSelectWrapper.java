/*     */ package org.apache.xpath.objects;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xml.dtm.DTMIterator;
/*     */ import org.apache.xml.utils.XMLString;
/*     */ import org.apache.xpath.Expression;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.res.XPATHMessages;
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
/*     */ public class XRTreeFragSelectWrapper
/*     */   extends XRTreeFrag
/*     */   implements Cloneable
/*     */ {
/*     */   XObject m_selected;
/*     */   
/*     */   public XRTreeFragSelectWrapper(Expression expr) {
/*  37 */     super(expr);
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
/*     */   public void fixupVariables(Vector vars, int globalsSize) {
/*  52 */     ((Expression)this.m_obj).fixupVariables(vars, globalsSize);
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
/*     */   public XObject execute(XPathContext xctxt) throws TransformerException {
/*  67 */     this.m_selected = ((Expression)this.m_obj).execute(xctxt);
/*  68 */     this.m_selected.allowDetachToRelease(this.m_allowRelease);
/*  69 */     if (this.m_selected.getType() == 3) {
/*  70 */       return this.m_selected;
/*     */     }
/*  72 */     return new XString(this.m_selected.str());
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
/*     */   public void detach() {
/*  86 */     if (this.m_allowRelease) {
/*     */       
/*  88 */       this.m_selected.detach();
/*  89 */       this.m_selected = null;
/*     */     } 
/*     */     
/*  92 */     super.detach();
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
/*     */   public double num() throws TransformerException {
/* 104 */     return this.m_selected.num();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XMLString xstr() {
/* 115 */     return this.m_selected.xstr();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String str() {
/* 125 */     return this.m_selected.str();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/* 135 */     return 3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int rtf() {
/* 145 */     throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_RTF_NOT_SUPPORTED_XRTREEFRAGSELECTWRAPPER", null));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DTMIterator asNodeIterator() {
/* 155 */     throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_RTF_NOT_SUPPORTED_XRTREEFRAGSELECTWRAPPER", null));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/objects/XRTreeFragSelectWrapper.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */