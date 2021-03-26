/*     */ package org.apache.batik.dom.util;
/*     */ 
/*     */ import org.apache.batik.util.XMLConstants;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.Element;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XLinkSupport
/*     */   implements XMLConstants
/*     */ {
/*     */   public static String getXLinkType(Element elt) {
/*  38 */     return elt.getAttributeNS("http://www.w3.org/1999/xlink", "type");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setXLinkType(Element elt, String str) {
/*  45 */     if (!"simple".equals(str) && !"extended".equals(str) && !"locator".equals(str) && !"arc".equals(str))
/*     */     {
/*     */ 
/*     */       
/*  49 */       throw new DOMException((short)12, "xlink:type='" + str + "'");
/*     */     }
/*     */     
/*  52 */     elt.setAttributeNS("http://www.w3.org/1999/xlink", "type", str);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getXLinkRole(Element elt) {
/*  59 */     return elt.getAttributeNS("http://www.w3.org/1999/xlink", "role");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setXLinkRole(Element elt, String str) {
/*  66 */     elt.setAttributeNS("http://www.w3.org/1999/xlink", "role", str);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getXLinkArcRole(Element elt) {
/*  73 */     return elt.getAttributeNS("http://www.w3.org/1999/xlink", "arcrole");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setXLinkArcRole(Element elt, String str) {
/*  80 */     elt.setAttributeNS("http://www.w3.org/1999/xlink", "arcrole", str);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getXLinkTitle(Element elt) {
/*  87 */     return elt.getAttributeNS("http://www.w3.org/1999/xlink", "title");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setXLinkTitle(Element elt, String str) {
/*  94 */     elt.setAttributeNS("http://www.w3.org/1999/xlink", "title", str);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getXLinkShow(Element elt) {
/* 101 */     return elt.getAttributeNS("http://www.w3.org/1999/xlink", "show");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setXLinkShow(Element elt, String str) {
/* 108 */     if (!"new".equals(str) && !"replace".equals(str) && !"embed".equals(str))
/*     */     {
/*     */       
/* 111 */       throw new DOMException((short)12, "xlink:show='" + str + "'");
/*     */     }
/*     */     
/* 114 */     elt.setAttributeNS("http://www.w3.org/1999/xlink", "show", str);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getXLinkActuate(Element elt) {
/* 121 */     return elt.getAttributeNS("http://www.w3.org/1999/xlink", "actuate");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setXLinkActuate(Element elt, String str) {
/* 128 */     if (!"onReplace".equals(str) && !"onLoad".equals(str)) {
/* 129 */       throw new DOMException((short)12, "xlink:actuate='" + str + "'");
/*     */     }
/*     */     
/* 132 */     elt.setAttributeNS("http://www.w3.org/1999/xlink", "actuate", str);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getXLinkHref(Element elt) {
/* 139 */     return elt.getAttributeNS("http://www.w3.org/1999/xlink", "href");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setXLinkHref(Element elt, String str) {
/* 146 */     elt.setAttributeNS("http://www.w3.org/1999/xlink", "href", str);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/util/XLinkSupport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */