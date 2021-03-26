/*     */ package org.apache.batik.dom.util;
/*     */ 
/*     */ import org.apache.batik.util.XMLConstants;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class XMLSupport
/*     */   implements XMLConstants
/*     */ {
/*     */   public static String getXMLLang(Element elt) {
/*  45 */     Attr attr = elt.getAttributeNodeNS("http://www.w3.org/XML/1998/namespace", "lang");
/*  46 */     if (attr != null) {
/*  47 */       return attr.getNodeValue();
/*     */     }
/*  49 */     for (Node n = elt.getParentNode(); n != null; n = n.getParentNode()) {
/*  50 */       if (n.getNodeType() == 1) {
/*  51 */         attr = ((Element)n).getAttributeNodeNS("http://www.w3.org/XML/1998/namespace", "lang");
/*     */         
/*  53 */         if (attr != null) {
/*  54 */           return attr.getNodeValue();
/*     */         }
/*     */       } 
/*     */     } 
/*  58 */     return "en";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getXMLSpace(Element elt) {
/*  65 */     Attr attr = elt.getAttributeNodeNS("http://www.w3.org/XML/1998/namespace", "space");
/*  66 */     if (attr != null) {
/*  67 */       return attr.getNodeValue();
/*     */     }
/*  69 */     for (Node n = elt.getParentNode(); n != null; n = n.getParentNode()) {
/*  70 */       if (n.getNodeType() == 1) {
/*  71 */         attr = ((Element)n).getAttributeNodeNS("http://www.w3.org/XML/1998/namespace", "space");
/*     */         
/*  73 */         if (attr != null) {
/*  74 */           return attr.getNodeValue();
/*     */         }
/*     */       } 
/*     */     } 
/*  78 */     return "default";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String defaultXMLSpace(String data) {
/*  86 */     int nChars = data.length();
/*  87 */     StringBuffer result = new StringBuffer(nChars);
/*  88 */     boolean space = false;
/*  89 */     for (int i = 0; i < nChars; i++) {
/*  90 */       char c = data.charAt(i);
/*  91 */       switch (c) {
/*     */         case '\n':
/*     */         case '\r':
/*  94 */           space = false;
/*     */           break;
/*     */         case '\t':
/*     */         case ' ':
/*  98 */           if (!space) {
/*  99 */             result.append(' ');
/* 100 */             space = true;
/*     */           } 
/*     */           break;
/*     */         default:
/* 104 */           result.append(c);
/* 105 */           space = false; break;
/*     */       } 
/*     */     } 
/* 108 */     return result.toString().trim();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String preserveXMLSpace(String data) {
/* 116 */     int nChars = data.length();
/* 117 */     StringBuffer result = new StringBuffer(nChars);
/* 118 */     for (int i = 0; i < data.length(); i++) {
/* 119 */       char c = data.charAt(i);
/* 120 */       switch (c) {
/*     */         case '\t':
/*     */         case '\n':
/*     */         case '\r':
/* 124 */           result.append(' ');
/*     */           break;
/*     */         default:
/* 127 */           result.append(c); break;
/*     */       } 
/*     */     } 
/* 130 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/util/XMLSupport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */