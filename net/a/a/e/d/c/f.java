/*    */ package net.a.a.e.d.c;
/*    */ 
/*    */ import org.w3c.dom.Node;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class f
/*    */ {
/*    */   public static String a(Node paramNode) {
/* 44 */     String str = paramNode.getTextContent();
/* 45 */     if (str == null) {
/* 46 */       return "";
/*    */     }
/*    */     
/* 49 */     StringBuilder stringBuilder = new StringBuilder();
/*    */ 
/*    */     
/* 52 */     stringBuilder.append(str.trim());
/*    */     
/* 54 */     for (byte b = 0; b < stringBuilder.length() - 1; b++) {
/* 55 */       if (stringBuilder.charAt(b) <= ' ' && stringBuilder
/* 56 */         .charAt(b + 1) <= ' ') {
/* 57 */         stringBuilder.deleteCharAt(b);
/*    */ 
/*    */         
/* 60 */         b--;
/*    */       } 
/*    */     } 
/*    */     
/* 64 */     return a.a(stringBuilder.toString());
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/d/c/f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */