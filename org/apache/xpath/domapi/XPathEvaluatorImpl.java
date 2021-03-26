/*     */ package org.apache.xpath.domapi;
/*     */ 
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xml.utils.PrefixResolver;
/*     */ import org.apache.xpath.XPath;
/*     */ import org.apache.xpath.res.XPATHMessages;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.xpath.XPathEvaluator;
/*     */ import org.w3c.dom.xpath.XPathException;
/*     */ import org.w3c.dom.xpath.XPathExpression;
/*     */ import org.w3c.dom.xpath.XPathNSResolver;
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
/*     */ public class XPathEvaluatorImpl
/*     */   implements XPathEvaluator
/*     */ {
/*     */   class DummyPrefixResolver
/*     */     implements PrefixResolver
/*     */   {
/*     */     private final XPathEvaluatorImpl this$0;
/*     */     
/*     */     public DummyPrefixResolver(XPathEvaluatorImpl this$0) {
/*  71 */       this.this$0 = this$0;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getNamespaceForPrefix(String prefix, Node context) {
/*  80 */       String fmsg = XPATHMessages.createXPATHMessage("ER_NULL_RESOLVER", null);
/*  81 */       throw new DOMException((short)14, fmsg);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getNamespaceForPrefix(String prefix) {
/*  91 */       return getNamespaceForPrefix(prefix, null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean handlesNullPrefixes() {
/*  98 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getBaseIdentifier() {
/* 105 */       return null;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 114 */   private Document m_doc = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XPathEvaluatorImpl() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XPathEvaluatorImpl(Document doc) {
/* 130 */     this.m_doc = doc;
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
/*     */   public XPathExpression createExpression(String expression, XPathNSResolver resolver) throws XPathException, DOMException {
/*     */     
/* 165 */     try { XPath xpath = new XPath(expression, null, (null == resolver) ? new DummyPrefixResolver(this) : (PrefixResolver)resolver, 0);
/*     */ 
/*     */ 
/*     */       
/* 169 */       return new XPathExpressionImpl(xpath, this.m_doc); } catch (TransformerException e)
/*     */     
/*     */     { 
/* 172 */       throw new DOMException((short)1, e.getMessageAndLocation()); }
/*     */   
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
/*     */   public XPathNSResolver createNSResolver(Node nodeResolver) {
/* 194 */     return new XPathNSResolverImpl((nodeResolver.getNodeType() == 9) ? ((Document)nodeResolver).getDocumentElement() : nodeResolver);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object evaluate(String expression, Node contextNode, XPathNSResolver resolver, short type, Object result) throws XPathException, DOMException {
/* 257 */     XPathExpression xpathExpression = createExpression(expression, resolver);
/*     */     
/* 259 */     return xpathExpression.evaluate(contextNode, type, result);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/domapi/XPathEvaluatorImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */