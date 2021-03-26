/*    */ package org.apache.xpath.domapi;
/*    */ 
/*    */ import org.apache.xml.utils.PrefixResolverDefault;
/*    */ import org.w3c.dom.Node;
/*    */ import org.w3c.dom.xpath.XPathNSResolver;
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
/*    */ public class XPathNSResolverImpl
/*    */   extends PrefixResolverDefault
/*    */   implements XPathNSResolver
/*    */ {
/*    */   public XPathNSResolverImpl(Node xpathExpressionContext) {
/* 49 */     super(xpathExpressionContext);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String lookupNamespaceURI(String prefix) {
/* 56 */     return getNamespaceForPrefix(prefix);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/domapi/XPathNSResolverImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */