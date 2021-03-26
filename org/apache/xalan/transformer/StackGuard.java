/*     */ package org.apache.xalan.transformer;
/*     */ 
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.res.XSLMessages;
/*     */ import org.apache.xalan.templates.ElemTemplate;
/*     */ import org.apache.xalan.templates.ElemTemplateElement;
/*     */ import org.apache.xml.utils.ObjectStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StackGuard
/*     */ {
/*  44 */   public static int m_recursionLimit = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   TransformerImpl m_transformer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRecursionLimit() {
/*  62 */     return m_recursionLimit;
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
/*     */   public void setRecursionLimit(int limit) {
/*  79 */     m_recursionLimit = limit;
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
/*     */   public StackGuard(TransformerImpl transformerImpl) {
/*  91 */     this.m_transformer = transformerImpl;
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
/*     */   public int countLikeTemplates(ElemTemplate templ, int pos) {
/* 104 */     ObjectStack elems = this.m_transformer.getCurrentTemplateElements();
/* 105 */     int count = 1;
/* 106 */     for (int i = pos - 1; i >= 0; i--) {
/*     */       
/* 108 */       if ((ElemTemplateElement)elems.elementAt(i) == templ) {
/* 109 */         count++;
/*     */       }
/*     */     } 
/* 112 */     return count;
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
/*     */   private ElemTemplate getNextMatchOrNamedTemplate(int pos) {
/* 125 */     ObjectStack elems = this.m_transformer.getCurrentTemplateElements();
/* 126 */     for (int i = pos; i >= 0; i--) {
/*     */       
/* 128 */       ElemTemplateElement elem = (ElemTemplateElement)elems.elementAt(i);
/* 129 */       if (null != elem)
/*     */       {
/* 131 */         if (elem.getXSLToken() == 19)
/*     */         {
/* 133 */           return (ElemTemplate)elem;
/*     */         }
/*     */       }
/*     */     } 
/* 137 */     return null;
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
/*     */   public void checkForInfinateLoop() throws TransformerException {
/* 150 */     int nTemplates = this.m_transformer.getCurrentTemplateElementsCount();
/* 151 */     if (nTemplates < m_recursionLimit) {
/*     */       return;
/*     */     }
/* 154 */     if (m_recursionLimit <= 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 159 */     for (int i = nTemplates - 1; i >= m_recursionLimit; i--) {
/*     */       
/* 161 */       ElemTemplate template = getNextMatchOrNamedTemplate(i);
/*     */       
/* 163 */       if (null == template) {
/*     */         break;
/*     */       }
/* 166 */       int loopCount = countLikeTemplates(template, i);
/*     */       
/* 168 */       if (loopCount >= m_recursionLimit) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 175 */         String idIs = XSLMessages.createMessage((null != template.getName()) ? "nameIs" : "matchPatternIs", null);
/* 176 */         Object[] msgArgs = { new Integer(loopCount), idIs, (null != template.getName()) ? template.getName().toString() : template.getMatch().getPatternString() };
/*     */ 
/*     */         
/* 179 */         String msg = XSLMessages.createMessage("recursionTooDeep", msgArgs);
/*     */         
/* 181 */         throw new TransformerException(msg);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/transformer/StackGuard.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */