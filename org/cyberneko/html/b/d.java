/*    */ package org.cyberneko.html.b;
/*    */ 
/*    */ import org.apache.xerces.impl.Version;
/*    */ import org.apache.xerces.xni.Augmentations;
/*    */ import org.apache.xerces.xni.NamespaceContext;
/*    */ import org.apache.xerces.xni.XMLDocumentHandler;
/*    */ import org.apache.xerces.xni.XMLLocator;
/*    */ import org.apache.xerces.xni.parser.XMLDocumentFilter;
/*    */ import org.apache.xerces.xni.parser.XMLDocumentSource;
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
/*    */ public class d
/*    */   extends a
/*    */ {
/*    */   protected d() throws InstantiationException {
/*    */     try {
/* 38 */       b();
/*    */     }
/* 40 */     catch (Throwable e) {
/* 41 */       throw new InstantiationException(e.getMessage());
/*    */     } 
/*    */   }
/*    */   
/*    */   public String b() {
/* 46 */     return Version.getVersion();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(XMLDocumentHandler documentHandler, String prefix, String uri, Augmentations augs) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(XMLDocumentHandler documentHandler, XMLLocator locator, String encoding, NamespaceContext nscontext, Augmentations augs) {
/* 57 */     documentHandler.startDocument(locator, encoding, nscontext, augs);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(XMLDocumentFilter filter, XMLDocumentSource lastSource) {
/* 62 */     filter.setDocumentSource(lastSource);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/cyberneko/html/b/d.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */