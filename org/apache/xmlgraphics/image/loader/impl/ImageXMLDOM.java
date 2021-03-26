/*    */ package org.apache.xmlgraphics.image.loader.impl;
/*    */ 
/*    */ import org.apache.xmlgraphics.image.loader.ImageFlavor;
/*    */ import org.apache.xmlgraphics.image.loader.ImageInfo;
/*    */ import org.apache.xmlgraphics.image.loader.XMLNamespaceEnabledImageFlavor;
/*    */ import org.w3c.dom.Document;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ImageXMLDOM
/*    */   extends AbstractImage
/*    */ {
/*    */   private ImageFlavor flavor;
/*    */   private Document doc;
/*    */   private String rootNamespace;
/*    */   
/*    */   public ImageXMLDOM(ImageInfo info, Document doc, String rootNamespace) {
/* 44 */     super(info);
/* 45 */     this.doc = doc;
/* 46 */     this.rootNamespace = rootNamespace;
/* 47 */     this.flavor = (ImageFlavor)new XMLNamespaceEnabledImageFlavor(ImageFlavor.XML_DOM, rootNamespace);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ImageXMLDOM(ImageInfo info, Document doc, XMLNamespaceEnabledImageFlavor flavor) {
/* 57 */     super(info);
/* 58 */     this.doc = doc;
/* 59 */     this.rootNamespace = flavor.getNamespace();
/* 60 */     this.flavor = (ImageFlavor)flavor;
/*    */   }
/*    */ 
/*    */   
/*    */   public ImageFlavor getFlavor() {
/* 65 */     return this.flavor;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCacheable() {
/* 70 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Document getDocument() {
/* 78 */     return this.doc;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getRootNamespace() {
/* 86 */     return this.rootNamespace;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/ImageXMLDOM.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */