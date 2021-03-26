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
/*     */ 
/*     */ 
/*     */ public class XPathAPI
/*     */ {
/*     */   public static Node selectSingleNode(Node contextNode, String str) throws TransformerException {
/*  67 */     return selectSingleNode(contextNode, str, contextNode);
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
/*     */   public static Node selectSingleNode(Node contextNode, String str, Node namespaceNode) throws TransformerException {
/*  87 */     NodeIterator nl = selectNodeIterator(contextNode, str, namespaceNode);
/*     */ 
/*     */     
/*  90 */     return nl.nextNode();
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
/*     */   public static NodeIterator selectNodeIterator(Node contextNode, String str) throws TransformerException {
/* 106 */     return selectNodeIterator(contextNode, str, contextNode);
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
/*     */   public static NodeIterator selectNodeIterator(Node contextNode, String str, Node namespaceNode) throws TransformerException {
/* 126 */     XObject list = eval(contextNode, str, namespaceNode);
/*     */ 
/*     */     
/* 129 */     return list.nodeset();
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
/*     */   public static NodeList selectNodeList(Node contextNode, String str) throws TransformerException {
/* 145 */     return selectNodeList(contextNode, str, contextNode);
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
/*     */   public static NodeList selectNodeList(Node contextNode, String str, Node namespaceNode) throws TransformerException {
/* 165 */     XObject list = eval(contextNode, str, namespaceNode);
/*     */ 
/*     */     
/* 168 */     return list.nodelist();
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
/*     */   public static XObject eval(Node contextNode, String str) throws TransformerException {
/* 190 */     return eval(contextNode, str, contextNode);
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
/*     */   public static XObject eval(Node contextNode, String str, Node namespaceNode) throws TransformerException {
/* 222 */     XPathContext xpathSupport = new XPathContext();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 228 */     PrefixResolverDefault prefixResolver = new PrefixResolverDefault((namespaceNode.getNodeType() == 9) ? ((Document)namespaceNode).getDocumentElement() : namespaceNode);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 233 */     XPath xpath = new XPath(str, null, (PrefixResolver)prefixResolver, 0, null);
/*     */ 
/*     */ 
/*     */     
/* 237 */     int ctxtNode = xpathSupport.getDTMHandleFromNode(contextNode);
/*     */     
/* 239 */     return xpath.execute(xpathSupport, ctxtNode, (PrefixResolver)prefixResolver);
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
/*     */   public static XObject eval(Node contextNode, String str, PrefixResolver prefixResolver) throws TransformerException {
/* 275 */     XPath xpath = new XPath(str, null, prefixResolver, 0, null);
/*     */ 
/*     */     
/* 278 */     XPathContext xpathSupport = new XPathContext();
/* 279 */     int ctxtNode = xpathSupport.getDTMHandleFromNode(contextNode);
/*     */     
/* 281 */     return xpath.execute(xpathSupport, ctxtNode, prefixResolver);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/XPathAPI.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */