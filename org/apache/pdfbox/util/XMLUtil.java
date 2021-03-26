/*     */ package org.apache.pdfbox.util;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import javax.xml.parsers.DocumentBuilder;
/*     */ import javax.xml.parsers.DocumentBuilderFactory;
/*     */ import javax.xml.parsers.FactoryConfigurationError;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
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
/*     */ public final class XMLUtil
/*     */ {
/*     */   public static Document parse(InputStream is) throws IOException {
/*  58 */     return parse(is, false);
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
/*     */   public static Document parse(InputStream is, boolean nsAware) throws IOException {
/*     */     try {
/*  73 */       DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
/*  74 */       builderFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
/*  75 */       builderFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
/*     */       
/*  77 */       builderFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
/*     */       
/*  79 */       builderFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
/*     */       
/*  81 */       builderFactory.setXIncludeAware(false);
/*  82 */       builderFactory.setExpandEntityReferences(false);
/*  83 */       builderFactory.setNamespaceAware(nsAware);
/*  84 */       DocumentBuilder builder = builderFactory.newDocumentBuilder();
/*  85 */       return builder.parse(is);
/*     */     }
/*  87 */     catch (FactoryConfigurationError e) {
/*     */       
/*  89 */       throw new IOException(e.getMessage(), e);
/*     */     }
/*  91 */     catch (ParserConfigurationException e) {
/*     */       
/*  93 */       throw new IOException(e.getMessage(), e);
/*     */     }
/*  95 */     catch (SAXException e) {
/*     */       
/*  97 */       throw new IOException(e.getMessage(), e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getNodeValue(Element node) {
/* 109 */     StringBuilder sb = new StringBuilder();
/* 110 */     NodeList children = node.getChildNodes();
/* 111 */     int numNodes = children.getLength();
/* 112 */     for (int i = 0; i < numNodes; i++) {
/*     */       
/* 114 */       Node next = children.item(i);
/* 115 */       if (next instanceof org.w3c.dom.Text)
/*     */       {
/* 117 */         sb.append(next.getNodeValue());
/*     */       }
/*     */     } 
/* 120 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/util/XMLUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */