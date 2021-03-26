/*     */ package org.apache.xalan.lib;
/*     */ 
/*     */ import javax.xml.transform.SourceLocator;
/*     */ import org.apache.xalan.extensions.ExpressionContext;
/*     */ import org.apache.xml.dtm.ref.DTMNodeProxy;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NodeInfo
/*     */ {
/*     */   public static String systemId(ExpressionContext context) {
/*  48 */     Node contextNode = context.getContextNode();
/*  49 */     int nodeHandler = ((DTMNodeProxy)contextNode).getDTMNodeNumber();
/*  50 */     SourceLocator locator = ((DTMNodeProxy)contextNode).getDTM().getSourceLocatorFor(nodeHandler);
/*     */ 
/*     */     
/*  53 */     if (locator != null) {
/*  54 */       return locator.getSystemId();
/*     */     }
/*  56 */     return null;
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
/*     */   public static String systemId(NodeList nodeList) {
/*  70 */     if (nodeList == null || nodeList.getLength() == 0) {
/*  71 */       return null;
/*     */     }
/*  73 */     Node node = nodeList.item(0);
/*  74 */     int nodeHandler = ((DTMNodeProxy)node).getDTMNodeNumber();
/*  75 */     SourceLocator locator = ((DTMNodeProxy)node).getDTM().getSourceLocatorFor(nodeHandler);
/*     */ 
/*     */     
/*  78 */     if (locator != null) {
/*  79 */       return locator.getSystemId();
/*     */     }
/*  81 */     return null;
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
/*     */   public static String publicId(ExpressionContext context) {
/*  95 */     Node contextNode = context.getContextNode();
/*  96 */     int nodeHandler = ((DTMNodeProxy)contextNode).getDTMNodeNumber();
/*  97 */     SourceLocator locator = ((DTMNodeProxy)contextNode).getDTM().getSourceLocatorFor(nodeHandler);
/*     */ 
/*     */     
/* 100 */     if (locator != null) {
/* 101 */       return locator.getPublicId();
/*     */     }
/* 103 */     return null;
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
/*     */   public static String publicId(NodeList nodeList) {
/* 118 */     if (nodeList == null || nodeList.getLength() == 0) {
/* 119 */       return null;
/*     */     }
/* 121 */     Node node = nodeList.item(0);
/* 122 */     int nodeHandler = ((DTMNodeProxy)node).getDTMNodeNumber();
/* 123 */     SourceLocator locator = ((DTMNodeProxy)node).getDTM().getSourceLocatorFor(nodeHandler);
/*     */ 
/*     */     
/* 126 */     if (locator != null) {
/* 127 */       return locator.getPublicId();
/*     */     }
/* 129 */     return null;
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
/*     */   public static int lineNumber(ExpressionContext context) {
/* 148 */     Node contextNode = context.getContextNode();
/* 149 */     int nodeHandler = ((DTMNodeProxy)contextNode).getDTMNodeNumber();
/* 150 */     SourceLocator locator = ((DTMNodeProxy)contextNode).getDTM().getSourceLocatorFor(nodeHandler);
/*     */ 
/*     */     
/* 153 */     if (locator != null) {
/* 154 */       return locator.getLineNumber();
/*     */     }
/* 156 */     return -1;
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
/*     */   public static int lineNumber(NodeList nodeList) {
/* 176 */     if (nodeList == null || nodeList.getLength() == 0) {
/* 177 */       return -1;
/*     */     }
/* 179 */     Node node = nodeList.item(0);
/* 180 */     int nodeHandler = ((DTMNodeProxy)node).getDTMNodeNumber();
/* 181 */     SourceLocator locator = ((DTMNodeProxy)node).getDTM().getSourceLocatorFor(nodeHandler);
/*     */ 
/*     */     
/* 184 */     if (locator != null) {
/* 185 */       return locator.getLineNumber();
/*     */     }
/* 187 */     return -1;
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
/*     */   public static int columnNumber(ExpressionContext context) {
/* 206 */     Node contextNode = context.getContextNode();
/* 207 */     int nodeHandler = ((DTMNodeProxy)contextNode).getDTMNodeNumber();
/* 208 */     SourceLocator locator = ((DTMNodeProxy)contextNode).getDTM().getSourceLocatorFor(nodeHandler);
/*     */ 
/*     */     
/* 211 */     if (locator != null) {
/* 212 */       return locator.getColumnNumber();
/*     */     }
/* 214 */     return -1;
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
/*     */   public static int columnNumber(NodeList nodeList) {
/* 234 */     if (nodeList == null || nodeList.getLength() == 0) {
/* 235 */       return -1;
/*     */     }
/* 237 */     Node node = nodeList.item(0);
/* 238 */     int nodeHandler = ((DTMNodeProxy)node).getDTMNodeNumber();
/* 239 */     SourceLocator locator = ((DTMNodeProxy)node).getDTM().getSourceLocatorFor(nodeHandler);
/*     */ 
/*     */     
/* 242 */     if (locator != null) {
/* 243 */       return locator.getColumnNumber();
/*     */     }
/* 245 */     return -1;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/lib/NodeInfo.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */