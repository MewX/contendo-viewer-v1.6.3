/*     */ package org.apache.batik.util.gui.xmleditor;
/*     */ 
/*     */ import java.awt.Font;
/*     */ import javax.swing.JEditorPane;
/*     */ import javax.swing.text.DefaultEditorKit;
/*     */ import javax.swing.text.Document;
/*     */ import javax.swing.text.Element;
/*     */ import javax.swing.text.View;
/*     */ import javax.swing.text.ViewFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XMLEditorKit
/*     */   extends DefaultEditorKit
/*     */ {
/*     */   public static final String XML_MIME_TYPE = "text/xml";
/*     */   protected XMLContext context;
/*  41 */   protected ViewFactory factory = null;
/*     */ 
/*     */   
/*     */   public XMLEditorKit() {
/*  45 */     this(null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XMLEditorKit(XMLContext context) {
/*  53 */     this.factory = new XMLViewFactory();
/*  54 */     if (context == null) {
/*  55 */       this.context = new XMLContext();
/*     */     } else {
/*  57 */       this.context = context;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XMLContext getStylePreferences() {
/*  65 */     return this.context;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void install(JEditorPane c) {
/*  72 */     super.install(c);
/*     */     
/*  74 */     Object obj = this.context.getSyntaxFont("default");
/*  75 */     if (obj != null) {
/*  76 */       c.setFont((Font)obj);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getContentType() {
/*  87 */     return "text/xml";
/*     */   }
/*     */ 
/*     */   
/*     */   public Object clone() {
/*  92 */     XMLEditorKit kit = new XMLEditorKit();
/*  93 */     kit.context = this.context;
/*  94 */     return kit;
/*     */   }
/*     */ 
/*     */   
/*     */   public Document createDefaultDocument() {
/*  99 */     XMLDocument doc = new XMLDocument(this.context);
/* 100 */     return doc;
/*     */   }
/*     */ 
/*     */   
/*     */   public ViewFactory getViewFactory() {
/* 105 */     return this.factory;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected class XMLViewFactory
/*     */     implements ViewFactory
/*     */   {
/*     */     public View create(Element elem) {
/* 114 */       return new XMLView(XMLEditorKit.this.context, elem);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/gui/xmleditor/XMLEditorKit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */