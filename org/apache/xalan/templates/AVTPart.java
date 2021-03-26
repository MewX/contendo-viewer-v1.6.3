/*    */ package org.apache.xalan.templates;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Vector;
/*    */ import javax.xml.transform.TransformerException;
/*    */ import org.apache.xml.utils.FastStringBuffer;
/*    */ import org.apache.xml.utils.PrefixResolver;
/*    */ import org.apache.xpath.XPathContext;
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
/*    */ public abstract class AVTPart
/*    */   implements Serializable, XSLTVisitable
/*    */ {
/*    */   public abstract String getSimpleString();
/*    */   
/*    */   public abstract void evaluate(XPathContext paramXPathContext, FastStringBuffer paramFastStringBuffer, int paramInt, PrefixResolver paramPrefixResolver) throws TransformerException;
/*    */   
/*    */   public void setXPathSupport(XPathContext support) {}
/*    */   
/*    */   public boolean canTraverseOutsideSubtree() {
/* 76 */     return false;
/*    */   }
/*    */   
/*    */   public abstract void fixupVariables(Vector paramVector, int paramInt);
/*    */   
/*    */   public abstract void callVisitors(XSLTVisitor paramXSLTVisitor);
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/AVTPart.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */