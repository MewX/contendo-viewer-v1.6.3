/*    */ package org.apache.xpath.operations;
/*    */ 
/*    */ import javax.xml.transform.TransformerException;
/*    */ import org.apache.xml.dtm.DTMManager;
/*    */ import org.apache.xpath.Expression;
/*    */ import org.apache.xpath.XPathContext;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class VariableSafeAbsRef
/*    */   extends Variable
/*    */ {
/*    */   public XObject execute(XPathContext xctxt, boolean destructiveOK) throws TransformerException {
/* 58 */     XNodeSet xns = (XNodeSet)super.execute(xctxt, destructiveOK);
/* 59 */     DTMManager dtmMgr = xctxt.getDTMManager();
/* 60 */     int context = xctxt.getContextNode();
/* 61 */     if (dtmMgr.getDTM(xns.getRoot()).getDocument() != dtmMgr.getDTM(context).getDocument()) {
/*    */ 
/*    */       
/* 64 */       Expression expr = (Expression)xns.getContainedIter();
/* 65 */       xns = (XNodeSet)expr.asIterator(xctxt, context);
/*    */     } 
/* 67 */     return (XObject)xns;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/operations/VariableSafeAbsRef.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */