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
/*    */ 
/*    */ 
/*    */ public class c
/*    */   extends a
/*    */ {
/*    */   public c() throws InstantiationException {
/*    */     try {
/* 40 */       b();
/* 41 */     } catch (Error e) {
/* 42 */       throw new InstantiationException(e.getMessage());
/*    */     } 
/*    */   }
/*    */   
/*    */   public String b() {
/* 47 */     return (new Version()).getVersion();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(XMLDocumentHandler documentHandler, XMLLocator locator, String encoding, NamespaceContext nscontext, Augmentations augs) {
/* 53 */     documentHandler.startDocument(locator, encoding, augs);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(XMLDocumentFilter filter, XMLDocumentSource lastSource) {
/* 58 */     filter.setDocumentSource(lastSource);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/cyberneko/html/b/c.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */