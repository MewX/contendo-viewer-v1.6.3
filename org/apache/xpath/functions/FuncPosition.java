/*     */ package org.apache.xpath.functions;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xml.dtm.DTMIterator;
/*     */ import org.apache.xml.utils.WrappedRuntimeException;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.axes.SubContextList;
/*     */ import org.apache.xpath.compiler.Compiler;
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
/*     */ public class FuncPosition
/*     */   extends Function
/*     */ {
/*     */   private boolean m_isTopLevel;
/*     */   
/*     */   public void postCompileStep(Compiler compiler) {
/*  43 */     this.m_isTopLevel = (compiler.getLocationPathDepth() == -1);
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
/*     */   public int getPositionInContextNodeList(XPathContext xctxt) {
/*  59 */     SubContextList iter = this.m_isTopLevel ? null : xctxt.getSubContextList();
/*     */     
/*  61 */     if (null != iter) {
/*     */       
/*  63 */       int prox = iter.getProximityPosition(xctxt);
/*     */ 
/*     */       
/*  66 */       return prox;
/*     */     } 
/*     */     
/*  69 */     DTMIterator cnl = xctxt.getContextNodeList();
/*     */     
/*  71 */     if (null != cnl) {
/*     */       
/*  73 */       int n = cnl.getCurrentNode();
/*  74 */       if (n == -1) {
/*     */         
/*  76 */         if (cnl.getCurrentPos() == 0) {
/*  77 */           return 0;
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  86 */         try { cnl = cnl.cloneWithReset(); } catch (CloneNotSupportedException cnse)
/*     */         
/*     */         { 
/*     */           
/*  90 */           throw new WrappedRuntimeException(cnse); }
/*     */         
/*  92 */         int currentNode = xctxt.getContextNode();
/*     */         
/*  94 */         while (-1 != (n = cnl.nextNode())) {
/*     */           
/*  96 */           if (n == currentNode) {
/*     */             break;
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 102 */       return cnl.getCurrentPos();
/*     */     } 
/*     */ 
/*     */     
/* 106 */     return -1;
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
/*     */   public XObject execute(XPathContext xctxt) throws TransformerException {
/* 119 */     double pos = getPositionInContextNodeList(xctxt);
/*     */     
/* 121 */     return (XObject)new XNumber(pos);
/*     */   }
/*     */   
/*     */   public void fixupVariables(Vector vars, int globalsSize) {}
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/functions/FuncPosition.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */