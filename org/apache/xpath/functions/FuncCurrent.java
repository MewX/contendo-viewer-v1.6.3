/*    */ package org.apache.xpath.functions;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import javax.xml.transform.TransformerException;
/*    */ import org.apache.xalan.res.XSLMessages;
/*    */ import org.apache.xpath.XPathContext;
/*    */ import org.apache.xpath.axes.LocPathIterator;
/*    */ import org.apache.xpath.axes.PredicatedNodeTest;
/*    */ import org.apache.xpath.axes.SubContextList;
/*    */ import org.apache.xpath.objects.XNodeSet;
/*    */ import org.apache.xpath.objects.XObject;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FuncCurrent
/*    */   extends Function
/*    */ {
/*    */   public XObject execute(XPathContext xctxt) throws TransformerException {
/* 52 */     SubContextList subContextList = xctxt.getCurrentNodeList();
/* 53 */     int currentNode = -1;
/*    */     
/* 55 */     if (null != subContextList) {
/* 56 */       if (subContextList instanceof PredicatedNodeTest) {
/* 57 */         LocPathIterator iter = ((PredicatedNodeTest)subContextList).getLocPathIterator();
/*    */         
/* 59 */         currentNode = iter.getCurrentContextNode();
/* 60 */       } else if (subContextList instanceof org.apache.xpath.patterns.StepPattern) {
/* 61 */         throw new RuntimeException(XSLMessages.createMessage("ER_PROCESSOR_ERROR", null));
/*    */       }
/*    */     
/*    */     } else {
/*    */       
/* 66 */       currentNode = xctxt.getContextNode();
/*    */     } 
/* 68 */     return (XObject)new XNodeSet(currentNode, xctxt.getDTMManager());
/*    */   }
/*    */   
/*    */   public void fixupVariables(Vector vars, int globalsSize) {}
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/functions/FuncCurrent.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */