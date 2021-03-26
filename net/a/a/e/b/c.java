/*    */ package net.a.a.e.b;
/*    */ 
/*    */ import org.apache.batik.dom.AbstractDOMImplementation;
/*    */ import org.w3c.dom.DOMImplementation;
/*    */ import org.w3c.dom.Document;
/*    */ import org.w3c.dom.DocumentType;
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
/*    */ public final class c
/*    */   extends AbstractDOMImplementation
/*    */ {
/*    */   private static final long a = 1L;
/*    */   
/*    */   private static final class a
/*    */   {
/* 34 */     private static final c a = new c();
/*    */   }
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
/*    */   public Document createDocument(String paramString1, String paramString2, DocumentType paramDocumentType) {
/* 50 */     a a = new a(paramDocumentType);
/* 51 */     a.appendChild(a.createElementNS(paramString1, paramString2));
/* 52 */     return (Document)a;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public DocumentType createDocumentType(String paramString1, String paramString2, String paramString3) {
/* 58 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static DOMImplementation a() {
/* 67 */     return (DOMImplementation)a.a();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/b/c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */