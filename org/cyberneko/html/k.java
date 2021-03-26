/*    */ package org.cyberneko.html;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import org.apache.xerces.xni.Augmentations;
/*    */ import org.apache.xerces.xni.XMLDocumentHandler;
/*    */ import org.apache.xerces.xni.XMLString;
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
/*    */ class k
/*    */ {
/*    */   static class a
/*    */   {
/*    */     private XMLString a;
/*    */     private Augmentations b;
/*    */     
/*    */     static Augmentations b(a x0) {
/* 38 */       return x0.b; } static XMLString a(a x0) { return x0.a; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public a(XMLString text, Augmentations augs) {
/* 45 */       char[] chars = new char[text.length];
/* 46 */       System.arraycopy(text.ch, text.offset, chars, 0, text.length);
/* 47 */       this.a = new XMLString(chars, 0, chars.length);
/* 48 */       if (augs != null)
/* 49 */         this.b = new a(augs); 
/*    */     }
/*    */   }
/* 52 */   private final List a = new ArrayList();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(XMLString text, Augmentations augs) {
/* 59 */     if (!this.a.isEmpty() || text.toString().trim().length() > 0) {
/* 60 */       this.a.add(new a(text, augs));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(XMLDocumentHandler tagBalancer) {
/* 68 */     for (Iterator iter = this.a.iterator(); iter.hasNext(); ) {
/* 69 */       a entry = iter.next();
/* 70 */       tagBalancer.characters(a.a(entry), a.b(entry));
/*    */     } 
/*    */     
/* 73 */     this.a.clear();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 81 */     return this.a.isEmpty();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/cyberneko/html/k.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */