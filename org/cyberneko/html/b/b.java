/*    */ package org.cyberneko.html.b;
/*    */ 
/*    */ import org.apache.xerces.impl.Version;
/*    */ import org.apache.xerces.xni.Augmentations;
/*    */ import org.apache.xerces.xni.NamespaceContext;
/*    */ import org.apache.xerces.xni.XMLDocumentHandler;
/*    */ import org.apache.xerces.xni.XMLLocator;
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
/*    */ public class b
/*    */   extends a
/*    */ {
/*    */   public String b() {
/* 39 */     return Version.fVersion;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(XMLDocumentHandler documentHandler, String prefix, String uri, Augmentations augs) {
/* 45 */     documentHandler.startPrefixMapping(prefix, uri, augs);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(XMLDocumentHandler documentHandler, String prefix, Augmentations augs) {
/* 51 */     documentHandler.endPrefixMapping(prefix, augs);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(XMLDocumentHandler documentHandler, XMLLocator locator, String encoding, NamespaceContext nscontext, Augmentations augs) {
/* 57 */     documentHandler.startDocument(locator, encoding, augs);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/cyberneko/html/b/b.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */