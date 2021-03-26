/*     */ package org.apache.xalan.templates;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xml.utils.QName;
/*     */ import org.apache.xpath.XPath;
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
/*     */ 
/*     */ 
/*     */ public class KeyDeclaration
/*     */   extends ElemTemplateElement
/*     */ {
/*     */   private QName m_name;
/*     */   private XPath m_matchPattern;
/*     */   private XPath m_use;
/*     */   
/*     */   public KeyDeclaration(Stylesheet parentNode, int docOrderNumber) {
/*  95 */     this.m_matchPattern = null;
/*     */     this.m_parentNode = parentNode;
/*     */     setUid(docOrderNumber);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(QName name) {
/*     */     this.m_name = name;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMatch(XPath v) {
/* 108 */     this.m_matchPattern = v;
/*     */   }
/*     */ 
/*     */   
/*     */   public QName getName() {
/*     */     return this.m_name;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNodeName() {
/*     */     return "key";
/*     */   }
/*     */   
/*     */   public XPath getMatch() {
/* 122 */     return this.m_matchPattern;
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
/*     */   public void setUse(XPath v) {
/* 141 */     this.m_use = v;
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
/*     */   public XPath getUse() {
/* 154 */     return this.m_use;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getXSLToken() {
/* 165 */     return 31;
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
/*     */   public void compose(StylesheetRoot sroot) throws TransformerException {
/* 177 */     super.compose(sroot);
/* 178 */     Vector vnames = sroot.getComposeState().getVariableNames();
/* 179 */     if (null != this.m_matchPattern)
/* 180 */       this.m_matchPattern.fixupVariables(vnames, sroot.getComposeState().getGlobalsSize()); 
/* 181 */     if (null != this.m_use) {
/* 182 */       this.m_use.fixupVariables(vnames, sroot.getComposeState().getGlobalsSize());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void recompose(StylesheetRoot root) {
/* 192 */     root.recomposeKeys(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/KeyDeclaration.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */