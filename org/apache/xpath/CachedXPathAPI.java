/*     */ package org.apache.xpath;
/*     */ 
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xml.utils.PrefixResolver;
/*     */ import org.apache.xml.utils.PrefixResolverDefault;
/*     */ import org.apache.xpath.objects.XObject;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ import org.w3c.dom.traversal.NodeIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CachedXPathAPI
/*     */ {
/*     */   protected XPathContext xpathSupport;
/*     */   
/*     */   public CachedXPathAPI() {
/*  67 */     this.xpathSupport = new XPathContext();
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
/*     */   public CachedXPathAPI(CachedXPathAPI priorXPathAPI) {
/*  82 */     this.xpathSupport = priorXPathAPI.xpathSupport;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XPathContext getXPathContext() {
/*  93 */     return this.xpathSupport;
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
/*     */   public Node selectSingleNode(Node contextNode, String str) throws TransformerException {
/* 111 */     return selectSingleNode(contextNode, str, contextNode);
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
/*     */   public Node selectSingleNode(Node contextNode, String str, Node namespaceNode) throws TransformerException {
/* 131 */     NodeIterator nl = selectNodeIterator(contextNode, str, namespaceNode);
/*     */ 
/*     */     
/* 134 */     return nl.nextNode();
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
/*     */   public NodeIterator selectNodeIterator(Node contextNode, String str) throws TransformerException {
/* 150 */     return selectNodeIterator(contextNode, str, contextNode);
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
/*     */   public NodeIterator selectNodeIterator(Node contextNode, String str, Node namespaceNode) throws TransformerException {
/* 170 */     XObject list = eval(contextNode, str, namespaceNode);
/*     */ 
/*     */     
/* 173 */     return list.nodeset();
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
/*     */   public NodeList selectNodeList(Node contextNode, String str) throws TransformerException {
/* 189 */     return selectNodeList(contextNode, str, contextNode);
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
/*     */   public NodeList selectNodeList(Node contextNode, String str, Node namespaceNode) throws TransformerException {
/* 209 */     XObject list = eval(contextNode, str, namespaceNode);
/*     */ 
/*     */     
/* 212 */     return list.nodelist();
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
/*     */   public XObject eval(Node contextNode, String str) throws TransformerException {
/* 234 */     return eval(contextNode, str, contextNode);
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
/*     */   public XObject eval(Node contextNode, String str, Node namespaceNode) throws TransformerException {
/* 271 */     PrefixResolverDefault prefixResolver = new PrefixResolverDefault((namespaceNode.getNodeType() == 9) ? ((Document)namespaceNode).getDocumentElement() : namespaceNode);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 276 */     XPath xpath = new XPath(str, null, (PrefixResolver)prefixResolver, 0, null);
/*     */ 
/*     */ 
/*     */     
/* 280 */     int ctxtNode = this.xpathSupport.getDTMHandleFromNode(contextNode);
/*     */     
/* 282 */     return xpath.execute(this.xpathSupport, ctxtNode, (PrefixResolver)prefixResolver);
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
/*     */   public XObject eval(Node contextNode, String str, PrefixResolver prefixResolver) throws TransformerException {
/* 318 */     XPath xpath = new XPath(str, null, prefixResolver, 0, null);
/*     */ 
/*     */     
/* 321 */     XPathContext xpathSupport = new XPathContext();
/* 322 */     int ctxtNode = xpathSupport.getDTMHandleFromNode(contextNode);
/*     */     
/* 324 */     return xpath.execute(xpathSupport, ctxtNode, prefixResolver);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/CachedXPathAPI.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */