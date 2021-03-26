/*     */ package org.apache.xalan.templates;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NamespaceAlias
/*     */   extends ElemTemplateElement
/*     */ {
/*     */   private String m_StylesheetPrefix;
/*     */   private String m_StylesheetNamespace;
/*     */   private String m_ResultPrefix;
/*     */   private String m_ResultNamespace;
/*     */   
/*     */   public NamespaceAlias(int docOrderNumber) {
/*  39 */     this.m_docOrderNumber = docOrderNumber;
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
/*     */   public void setStylesheetPrefix(String v) {
/*  55 */     this.m_StylesheetPrefix = v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStylesheetPrefix() {
/*  65 */     return this.m_StylesheetPrefix;
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
/*     */   public void setStylesheetNamespace(String v) {
/*  81 */     this.m_StylesheetNamespace = v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStylesheetNamespace() {
/*  91 */     return this.m_StylesheetNamespace;
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
/*     */   public void setResultPrefix(String v) {
/* 107 */     this.m_ResultPrefix = v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getResultPrefix() {
/* 117 */     return this.m_ResultPrefix;
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
/*     */   public void setResultNamespace(String v) {
/* 133 */     this.m_ResultNamespace = v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getResultNamespace() {
/* 143 */     return this.m_ResultNamespace;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void recompose(StylesheetRoot root) {
/* 153 */     root.recomposeNamespaceAliases(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/NamespaceAlias.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */