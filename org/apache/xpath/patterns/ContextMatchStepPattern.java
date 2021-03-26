/*     */ package org.apache.xpath.patterns;
/*     */ 
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xml.dtm.DTM;
/*     */ import org.apache.xml.dtm.DTMAxisTraverser;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.axes.WalkerFactory;
/*     */ import org.apache.xpath.objects.XNumber;
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
/*     */ public class ContextMatchStepPattern
/*     */   extends StepPattern
/*     */ {
/*     */   public ContextMatchStepPattern(int axis, int paxis) {
/*  41 */     super(-1, axis, paxis);
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
/*     */   public XObject execute(XPathContext xctxt) throws TransformerException {
/*  62 */     if (xctxt.getIteratorRoot() == xctxt.getCurrentNode()) {
/*  63 */       return (XObject)getStaticScore();
/*     */     }
/*  65 */     return (XObject)NodeTest.SCORE_NONE;
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
/*     */   public XObject executeRelativePathPattern(XPathContext xctxt, StepPattern prevStep) throws TransformerException {
/*     */     XObject xObject;
/*  88 */     XNumber xNumber = NodeTest.SCORE_NONE;
/*  89 */     int context = xctxt.getCurrentNode();
/*  90 */     DTM dtm = xctxt.getDTM(context);
/*     */     
/*  92 */     if (null != dtm) {
/*     */       
/*  94 */       int predContext = xctxt.getCurrentNode();
/*     */ 
/*     */       
/*  97 */       int axis = this.m_axis;
/*     */       
/*  99 */       boolean needToTraverseAttrs = WalkerFactory.isDownwardAxisOfMany(axis);
/* 100 */       boolean iterRootIsAttr = (dtm.getNodeType(xctxt.getIteratorRoot()) == 2);
/*     */ 
/*     */       
/* 103 */       if (11 == axis && iterRootIsAttr)
/*     */       {
/* 105 */         axis = 15;
/*     */       }
/*     */       
/* 108 */       DTMAxisTraverser traverser = dtm.getAxisTraverser(axis);
/*     */       
/* 110 */       for (int relative = traverser.first(context); -1 != relative; 
/* 111 */         relative = traverser.next(context, relative)) {
/*     */ 
/*     */         
/*     */         try {
/* 115 */           xctxt.pushCurrentNode(relative);
/*     */           
/* 117 */           xObject = execute(xctxt);
/*     */           
/* 119 */           if (xObject != NodeTest.SCORE_NONE) {
/*     */ 
/*     */ 
/*     */             
/* 123 */             if (executePredicates(xctxt, dtm, context)) {
/* 124 */               return xObject;
/*     */             }
/* 126 */             XNumber xNumber1 = NodeTest.SCORE_NONE;
/*     */           } 
/*     */           
/* 129 */           if (needToTraverseAttrs && iterRootIsAttr && 1 == dtm.getNodeType(relative))
/*     */           {
/*     */             
/* 132 */             int xaxis = 2;
/* 133 */             for (int i = 0; i < 2; i++)
/*     */             {
/* 135 */               DTMAxisTraverser atraverser = dtm.getAxisTraverser(xaxis);
/*     */               
/* 137 */               int arelative = atraverser.first(relative);
/* 138 */               for (; -1 != arelative; 
/* 139 */                 arelative = atraverser.next(relative, arelative)) {
/*     */ 
/*     */                 
/*     */                 try {
/* 143 */                   xctxt.pushCurrentNode(arelative);
/*     */                   
/* 145 */                   xObject = execute(xctxt);
/*     */                   
/* 147 */                   if (xObject != NodeTest.SCORE_NONE)
/*     */                   {
/*     */ 
/*     */ 
/*     */                     
/* 152 */                     if (xObject != NodeTest.SCORE_NONE) {
/* 153 */                       return xObject;
/*     */                     }
/*     */                   }
/*     */                 } finally {
/*     */                   
/* 158 */                   xctxt.popCurrentNode();
/*     */                 } 
/*     */               } 
/* 161 */               xaxis = 9;
/*     */             }
/*     */           
/*     */           }
/*     */         
/*     */         } finally {
/*     */           
/* 168 */           xctxt.popCurrentNode();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 174 */     return xObject;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/patterns/ContextMatchStepPattern.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */