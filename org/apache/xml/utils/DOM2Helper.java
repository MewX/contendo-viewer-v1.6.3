/*     */ package org.apache.xml.utils;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import javax.xml.parsers.DocumentBuilder;
/*     */ import javax.xml.parsers.DocumentBuilderFactory;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DOM2Helper
/*     */   extends DOMHelper
/*     */ {
/*     */   private Document m_doc;
/*     */   
/*     */   public void checkNode(Node node) throws TransformerException {}
/*     */   
/*     */   public boolean supportsSAX() {
/*  77 */     return true;
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
/*     */   public void setDocument(Document doc) {
/*  95 */     this.m_doc = doc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Document getDocument() {
/* 106 */     return this.m_doc;
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
/*     */   public void parse(InputSource source) throws TransformerException {
/*     */     
/* 140 */     try { DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
/*     */ 
/*     */       
/* 143 */       builderFactory.setNamespaceAware(true);
/* 144 */       builderFactory.setValidating(true);
/*     */       
/* 146 */       DocumentBuilder parser = builderFactory.newDocumentBuilder();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 163 */       parser.setErrorHandler(new DefaultErrorHandler());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 171 */       setDocument(parser.parse(source)); } catch (SAXException se)
/*     */     
/*     */     { 
/*     */       
/* 175 */       throw new TransformerException(se); } catch (ParserConfigurationException pce)
/*     */     
/*     */     { 
/*     */       
/* 179 */       throw new TransformerException(pce); } catch (IOException ioe)
/*     */     
/*     */     { 
/*     */       
/* 183 */       throw new TransformerException(ioe); }
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
/*     */   
/*     */   public Element getElementByID(String id, Document doc) {
/* 206 */     return doc.getElementById(id);
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
/*     */   public static boolean isNodeAfter(Node node1, Node node2) {
/* 232 */     if (node1 instanceof DOMOrder && node2 instanceof DOMOrder) {
/*     */       
/* 234 */       int index1 = ((DOMOrder)node1).getUid();
/* 235 */       int index2 = ((DOMOrder)node2).getUid();
/*     */       
/* 237 */       return (index1 <= index2);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 244 */     return DOMHelper.isNodeAfter(node1, node2);
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
/*     */   public static Node getParentOfNode(Node node) {
/* 262 */     Node parent = node.getParentNode();
/* 263 */     if (parent == null && 2 == node.getNodeType())
/* 264 */       parent = ((Attr)node).getOwnerElement(); 
/* 265 */     return parent;
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
/*     */   public String getLocalNameOfNode(Node n) {
/* 282 */     String name = n.getLocalName();
/*     */     
/* 284 */     return (null == name) ? super.getLocalNameOfNode(n) : name;
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
/*     */   public String getNamespaceOfNode(Node n) {
/* 304 */     return n.getNamespaceURI();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/DOM2Helper.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */