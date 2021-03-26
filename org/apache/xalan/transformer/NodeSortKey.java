/*     */ package org.apache.xalan.transformer;
/*     */ 
/*     */ import java.text.Collator;
/*     */ import java.util.Locale;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xml.utils.PrefixResolver;
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
/*     */ class NodeSortKey
/*     */ {
/*     */   XPath m_selectPat;
/*     */   boolean m_treatAsNumbers;
/*     */   boolean m_descending;
/*     */   boolean m_caseOrderUpper;
/*     */   Collator m_col;
/*     */   Locale m_locale;
/*     */   PrefixResolver m_namespaceContext;
/*     */   TransformerImpl m_processor;
/*     */   
/*     */   NodeSortKey(TransformerImpl transformer, XPath selectPat, boolean treatAsNumbers, boolean descending, String langValue, boolean caseOrderUpper, PrefixResolver namespaceContext) throws TransformerException {
/*  79 */     this.m_processor = transformer;
/*  80 */     this.m_namespaceContext = namespaceContext;
/*  81 */     this.m_selectPat = selectPat;
/*  82 */     this.m_treatAsNumbers = treatAsNumbers;
/*  83 */     this.m_descending = descending;
/*  84 */     this.m_caseOrderUpper = caseOrderUpper;
/*     */     
/*  86 */     if (null != langValue && !this.m_treatAsNumbers) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  98 */       this.m_locale = new Locale(langValue.toLowerCase(), Locale.getDefault().getCountry());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 105 */       if (null == this.m_locale)
/*     */       {
/*     */ 
/*     */         
/* 109 */         this.m_locale = Locale.getDefault();
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 114 */       this.m_locale = Locale.getDefault();
/*     */     } 
/*     */     
/* 117 */     this.m_col = Collator.getInstance(this.m_locale);
/*     */     
/* 119 */     if (null == this.m_col) {
/*     */       
/* 121 */       this.m_processor.getMsgMgr().warn(null, "WG_CANNOT_FIND_COLLATOR", new Object[] { langValue });
/*     */ 
/*     */       
/* 124 */       this.m_col = Collator.getInstance();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/transformer/NodeSortKey.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */