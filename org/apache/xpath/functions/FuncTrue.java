/*    */ package org.apache.xpath.functions;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import javax.xml.transform.TransformerException;
/*    */ import org.apache.xpath.XPathContext;
/*    */ import org.apache.xpath.objects.XBoolean;
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
/*    */ public class FuncTrue
/*    */   extends Function
/*    */ {
/*    */   public XObject execute(XPathContext xctxt) throws TransformerException {
/* 42 */     return (XObject)XBoolean.S_TRUE;
/*    */   }
/*    */   
/*    */   public void fixupVariables(Vector vars, int globalsSize) {}
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/functions/FuncTrue.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */