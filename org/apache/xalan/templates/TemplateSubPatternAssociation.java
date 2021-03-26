/*     */ package org.apache.xalan.templates;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xml.utils.QName;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.patterns.StepPattern;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class TemplateSubPatternAssociation
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   StepPattern m_stepPattern;
/*     */   private String m_pattern;
/*     */   private ElemTemplate m_template;
/*  47 */   private TemplateSubPatternAssociation m_next = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean m_wild;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String m_targetString;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   TemplateSubPatternAssociation(ElemTemplate template, StepPattern pattern, String pat) {
/*  64 */     this.m_pattern = pat;
/*  65 */     this.m_template = template;
/*  66 */     this.m_stepPattern = pattern;
/*  67 */     this.m_targetString = this.m_stepPattern.getTargetString();
/*  68 */     this.m_wild = this.m_targetString.equals("*");
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
/*     */   public Object clone() throws CloneNotSupportedException {
/*  81 */     TemplateSubPatternAssociation tspa = (TemplateSubPatternAssociation)super.clone();
/*     */ 
/*     */     
/*  84 */     tspa.m_next = null;
/*     */     
/*  86 */     return tspa;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String getTargetString() {
/*  97 */     return this.m_targetString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTargetString(String key) {
/* 108 */     this.m_targetString = key;
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
/*     */   boolean matchMode(QName m1) {
/* 120 */     return matchModes(m1, this.m_template.getMode());
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
/*     */   private boolean matchModes(QName m1, QName m2) {
/* 133 */     return ((null == m1 && null == m2) || (null != m1 && null != m2 && m1.equals(m2)));
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
/*     */   public boolean matches(XPathContext xctxt, int targetNode, QName mode) throws TransformerException {
/* 152 */     double score = this.m_stepPattern.getMatchScore(xctxt, targetNode);
/*     */     
/* 154 */     return (Double.NEGATIVE_INFINITY != score && matchModes(mode, this.m_template.getMode()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean isWild() {
/* 165 */     return this.m_wild;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final StepPattern getStepPattern() {
/* 176 */     return this.m_stepPattern;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String getPattern() {
/* 187 */     return this.m_pattern;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDocOrderPos() {
/* 198 */     return this.m_template.getUid();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getImportLevel() {
/* 209 */     return this.m_template.getStylesheetComposed().getImportCountComposed();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final ElemTemplate getTemplate() {
/* 220 */     return this.m_template;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final TemplateSubPatternAssociation getNext() {
/* 230 */     return this.m_next;
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
/*     */   public void setNext(TemplateSubPatternAssociation mp) {
/* 244 */     this.m_next = mp;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/TemplateSubPatternAssociation.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */