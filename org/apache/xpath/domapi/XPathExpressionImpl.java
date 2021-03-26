/*     */ package org.apache.xpath.domapi;
/*     */ 
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xml.utils.PrefixResolver;
/*     */ import org.apache.xpath.XPath;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.objects.XObject;
/*     */ import org.apache.xpath.res.XPATHMessages;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.xpath.XPathException;
/*     */ import org.w3c.dom.xpath.XPathExpression;
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
/*     */ public class XPathExpressionImpl
/*     */   implements XPathExpression
/*     */ {
/*     */   private PrefixResolver m_resolver;
/*     */   private XPath m_xpath;
/*  64 */   private Document m_doc = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   XPathExpressionImpl(XPath xpath, Document doc) {
/*  74 */     this.m_xpath = xpath;
/*  75 */     this.m_doc = doc;
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
/*     */   public Object evaluate(Node contextNode, short type, Object result) throws XPathException, DOMException {
/* 129 */     if (this.m_doc != null) {
/*     */ 
/*     */       
/* 132 */       if (contextNode != this.m_doc && !contextNode.getOwnerDocument().equals(this.m_doc)) {
/* 133 */         String fmsg = XPATHMessages.createXPATHMessage("ER_WRONG_DOCUMENT", null);
/* 134 */         throw new DOMException((short)4, fmsg);
/*     */       } 
/*     */ 
/*     */       
/* 138 */       short nodeType = contextNode.getNodeType();
/* 139 */       if (nodeType != 9 && nodeType != 1 && nodeType != 2 && nodeType != 3 && nodeType != 4 && nodeType != 8 && nodeType != 7 && nodeType != 13) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 147 */         String fmsg = XPATHMessages.createXPATHMessage("ER_WRONG_NODETYPE", null);
/* 148 */         throw new DOMException((short)9, fmsg);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 155 */     if (!XPathResultImpl.isValidType(type)) {
/* 156 */       String fmsg = XPATHMessages.createXPATHMessage("ER_INVALID_XPATH_TYPE", new Object[] { new Integer(type) });
/* 157 */       throw new XPathException((short)2, fmsg);
/*     */     } 
/*     */ 
/*     */     
/* 161 */     XPathContext xpathSupport = new XPathContext();
/*     */ 
/*     */     
/* 164 */     if (null != this.m_doc) {
/* 165 */       xpathSupport.getDTMHandleFromNode(this.m_doc);
/*     */     }
/*     */     
/* 168 */     XObject xobj = null;
/*     */     
/* 170 */     try { xobj = this.m_xpath.execute(xpathSupport, contextNode, this.m_resolver); } catch (TransformerException te)
/*     */     
/*     */     { 
/* 173 */       throw new XPathException((short)1, te.getMessageAndLocation()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 180 */     return new XPathResultImpl(type, xobj, contextNode);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/domapi/XPathExpressionImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */