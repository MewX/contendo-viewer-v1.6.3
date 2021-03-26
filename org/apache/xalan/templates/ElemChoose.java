/*     */ package org.apache.xalan.templates;
/*     */ 
/*     */ import javax.xml.transform.SourceLocator;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.transformer.TransformerImpl;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.objects.XObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ElemChoose
/*     */   extends ElemTemplateElement
/*     */ {
/*     */   public int getXSLToken() {
/*  48 */     return 37;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNodeName() {
/*  58 */     return "choose";
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
/*     */   public void execute(TransformerImpl transformer) throws TransformerException {
/*  80 */     if (TransformerImpl.S_DEBUG) {
/*  81 */       transformer.getTraceManager().fireTraceEvent(this);
/*     */     }
/*  83 */     boolean found = false;
/*     */     
/*  85 */     ElemTemplateElement childElem = getFirstChildElem();
/*  86 */     for (; childElem != null; childElem = childElem.getNextSiblingElem()) {
/*     */       
/*  88 */       int type = childElem.getXSLToken();
/*     */       
/*  90 */       if (38 == type) {
/*     */         
/*  92 */         found = true;
/*     */         
/*  94 */         ElemWhen when = (ElemWhen)childElem;
/*     */ 
/*     */         
/*  97 */         XPathContext xctxt = transformer.getXPathContext();
/*  98 */         int sourceNode = xctxt.getCurrentNode();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 105 */         if (TransformerImpl.S_DEBUG) {
/*     */           
/* 107 */           XObject test = when.getTest().execute(xctxt, sourceNode, when);
/*     */           
/* 109 */           if (TransformerImpl.S_DEBUG) {
/* 110 */             transformer.getTraceManager().fireSelectedEvent(sourceNode, when, "test", when.getTest(), test);
/*     */           }
/*     */           
/* 113 */           if (test.bool()) {
/*     */             
/* 115 */             transformer.getTraceManager().fireTraceEvent(when);
/*     */             
/* 117 */             transformer.executeChildTemplates(when, true);
/*     */             
/* 119 */             transformer.getTraceManager().fireTraceEndEvent(when);
/*     */ 
/*     */ 
/*     */             
/*     */             return;
/*     */           } 
/* 125 */         } else if (when.getTest().bool(xctxt, sourceNode, when)) {
/*     */           
/* 127 */           transformer.executeChildTemplates(when, true);
/*     */ 
/*     */           
/*     */           return;
/*     */         } 
/* 132 */       } else if (39 == type) {
/*     */         
/* 134 */         found = true;
/*     */         
/* 136 */         if (TransformerImpl.S_DEBUG) {
/* 137 */           transformer.getTraceManager().fireTraceEvent(childElem);
/*     */         }
/*     */         
/* 140 */         transformer.executeChildTemplates(childElem, true);
/*     */         
/* 142 */         if (TransformerImpl.S_DEBUG) {
/* 143 */           transformer.getTraceManager().fireTraceEndEvent(childElem);
/*     */         }
/*     */         return;
/*     */       } 
/*     */     } 
/* 148 */     if (!found) {
/* 149 */       transformer.getMsgMgr().error((SourceLocator)this, "ER_CHOOSE_REQUIRES_WHEN");
/*     */     }
/*     */     
/* 152 */     if (TransformerImpl.S_DEBUG) {
/* 153 */       transformer.getTraceManager().fireTraceEndEvent(this);
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
/*     */   public ElemTemplateElement appendChild(ElemTemplateElement newChild) {
/* 168 */     int type = newChild.getXSLToken();
/*     */     
/* 170 */     switch (type) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 38:
/*     */       case 39:
/* 185 */         return super.appendChild(newChild);
/*     */     } 
/*     */     error("ER_CANNOT_ADD", new Object[] { newChild.getNodeName(), getNodeName() });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canAcceptVariables() {
/* 194 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/ElemChoose.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */