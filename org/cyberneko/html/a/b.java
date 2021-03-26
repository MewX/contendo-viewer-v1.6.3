/*     */ package org.cyberneko.html.a;
/*     */ 
/*     */ import org.apache.xerces.parsers.DOMParser;
/*     */ import org.apache.xerces.xni.Augmentations;
/*     */ import org.apache.xerces.xni.XNIException;
/*     */ import org.apache.xerces.xni.parser.XMLParserConfiguration;
/*     */ import org.cyberneko.html.b.a;
/*     */ import org.cyberneko.html.c;
/*     */ import org.xml.sax.SAXNotRecognizedException;
/*     */ import org.xml.sax.SAXNotSupportedException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class b
/*     */   extends DOMParser
/*     */ {
/*     */   public b() {
/*  49 */     super((XMLParserConfiguration)new c());
/*     */     
/*     */     try {
/*  52 */       setProperty("http://apache.org/xml/properties/dom/document-class-name", "org.apache.html.dom.HTMLDocumentImpl");
/*     */     
/*     */     }
/*  55 */     catch (SAXNotRecognizedException e) {
/*  56 */       throw new RuntimeException("http://apache.org/xml/properties/dom/document-class-name property not recognized");
/*     */     }
/*  58 */     catch (SAXNotSupportedException e) {
/*  59 */       throw new RuntimeException("http://apache.org/xml/properties/dom/document-class-name property not supported");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doctypeDecl(String root, String pubid, String sysid, Augmentations augs) throws XNIException {
/*  81 */     String VERSION = a.a().b();
/*  82 */     boolean okay = true;
/*  83 */     if (VERSION.startsWith("Xerces-J 2.")) {
/*  84 */       okay = (a() > 5);
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*  89 */     else if (VERSION.startsWith("XML4J")) {
/*  90 */       okay = false;
/*     */     } 
/*     */ 
/*     */     
/*  94 */     if (okay) {
/*  95 */       super.doctypeDecl(root, pubid, sysid, augs);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int a() {
/*     */     try {
/* 107 */       String VERSION = a.a().b();
/* 108 */       int index1 = VERSION.indexOf('.') + 1;
/* 109 */       int index2 = VERSION.indexOf('.', index1);
/* 110 */       if (index2 == -1) index2 = VERSION.length(); 
/* 111 */       return Integer.parseInt(VERSION.substring(index1, index2));
/*     */     }
/* 113 */     catch (Exception e) {
/* 114 */       return -1;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/cyberneko/html/a/b.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */