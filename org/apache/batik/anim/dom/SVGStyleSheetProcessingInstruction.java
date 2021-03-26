/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import org.apache.batik.css.engine.CSSEngine;
/*     */ import org.apache.batik.css.engine.CSSStyleSheetNode;
/*     */ import org.apache.batik.css.engine.StyleSheet;
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.dom.StyleSheetFactory;
/*     */ import org.apache.batik.dom.StyleSheetProcessingInstruction;
/*     */ import org.apache.batik.util.ParsedURL;
/*     */ import org.w3c.dom.DOMException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGStyleSheetProcessingInstruction
/*     */   extends StyleSheetProcessingInstruction
/*     */   implements CSSStyleSheetNode
/*     */ {
/*     */   protected StyleSheet styleSheet;
/*     */   
/*     */   protected SVGStyleSheetProcessingInstruction() {}
/*     */   
/*     */   public SVGStyleSheetProcessingInstruction(String data, AbstractDocument owner, StyleSheetFactory f) {
/*  62 */     super(data, owner, f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStyleSheetURI() {
/*  69 */     SVGOMDocument svgDoc = (SVGOMDocument)getOwnerDocument();
/*  70 */     ParsedURL url = svgDoc.getParsedURL();
/*  71 */     String href = (String)getPseudoAttributes().get("href");
/*  72 */     if (url != null) {
/*  73 */       return (new ParsedURL(url, href)).toString();
/*     */     }
/*  75 */     return href;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StyleSheet getCSSStyleSheet() {
/*  82 */     if (this.styleSheet == null) {
/*  83 */       HashMap<String, String> attrs = getPseudoAttributes();
/*  84 */       String type = attrs.get("type");
/*     */       
/*  86 */       if ("text/css".equals(type)) {
/*  87 */         String title = attrs.get("title");
/*  88 */         String media = attrs.get("media");
/*  89 */         String href = attrs.get("href");
/*  90 */         String alternate = attrs.get("alternate");
/*  91 */         SVGOMDocument doc = (SVGOMDocument)getOwnerDocument();
/*  92 */         ParsedURL durl = doc.getParsedURL();
/*  93 */         ParsedURL burl = new ParsedURL(durl, href);
/*  94 */         CSSEngine e = doc.getCSSEngine();
/*     */         
/*  96 */         this.styleSheet = e.parseStyleSheet(burl, media);
/*  97 */         this.styleSheet.setAlternate("yes".equals(alternate));
/*  98 */         this.styleSheet.setTitle(title);
/*     */       } 
/*     */     } 
/* 101 */     return this.styleSheet;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setData(String data) throws DOMException {
/* 109 */     super.setData(data);
/* 110 */     this.styleSheet = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 117 */     return (Node)new SVGStyleSheetProcessingInstruction();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGStyleSheetProcessingInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */