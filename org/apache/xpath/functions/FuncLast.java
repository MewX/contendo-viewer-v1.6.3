/*    */ package org.apache.xpath.functions;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import javax.xml.transform.TransformerException;
/*    */ import org.apache.xml.dtm.DTMIterator;
/*    */ import org.apache.xpath.XPathContext;
/*    */ import org.apache.xpath.axes.SubContextList;
/*    */ import org.apache.xpath.compiler.Compiler;
/*    */ import org.apache.xpath.objects.XNumber;
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
/*    */ public class FuncLast
/*    */   extends Function
/*    */ {
/*    */   private boolean m_isTopLevel;
/*    */   
/*    */   public void postCompileStep(Compiler compiler) {
/* 44 */     this.m_isTopLevel = (compiler.getLocationPathDepth() == -1);
/*    */   }
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
/*    */   public int getCountOfContextNodeList(XPathContext xctxt) throws TransformerException {
/*    */     boolean bool;
/* 62 */     SubContextList iter = this.m_isTopLevel ? null : xctxt.getSubContextList();
/*    */ 
/*    */     
/* 65 */     if (null != iter) {
/* 66 */       return iter.getLastPos(xctxt);
/*    */     }
/* 68 */     DTMIterator cnl = xctxt.getContextNodeList();
/*    */     
/* 70 */     if (null != cnl) {
/*    */       
/* 72 */       bool = cnl.getLength();
/*    */     }
/*    */     else {
/*    */       
/* 76 */       bool = false;
/* 77 */     }  return bool;
/*    */   }
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
/*    */   public XObject execute(XPathContext xctxt) throws TransformerException {
/* 90 */     XNumber xnum = new XNumber(getCountOfContextNodeList(xctxt));
/*    */     
/* 92 */     return (XObject)xnum;
/*    */   }
/*    */   
/*    */   public void fixupVariables(Vector vars, int globalsSize) {}
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/functions/FuncLast.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */