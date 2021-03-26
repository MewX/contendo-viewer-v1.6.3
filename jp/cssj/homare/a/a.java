/*    */ package jp.cssj.homare.a;
/*    */ 
/*    */ import java.text.MessageFormat;
/*    */ import java.util.ResourceBundle;
/*    */ import jp.cssj.b.a.d;
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
/*    */ public class a
/*    */ {
/* 19 */   private static final ResourceBundle a = ResourceBundle.getBundle(b.class.getName());
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String a(short code, String[] args) {
/* 28 */     if ((code & 0xFFF) < 2048) {
/* 29 */       return d.b(code);
/*    */     }
/* 31 */     String str = Integer.toHexString(code).toUpperCase();
/*    */     try {
/* 33 */       str = a.getString(str);
/* 34 */     } catch (Exception e) {
/* 35 */       if (args.length > 0) {
/* 36 */         str = args[0];
/*    */       }
/*    */     } 
/* 39 */     return str;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String b(short code, String[] args) {
/* 49 */     String str = a(code, args);
/* 50 */     if (args != null) {
/* 51 */       for (int i = 0; i < args.length; i++) {
/* 52 */         if (args[i] != null && args[i].length() > 2083) {
/* 53 */           args[i] = args[i].substring(0, 2080) + "...";
/*    */         }
/*    */       } 
/*    */     }
/* 57 */     str = MessageFormat.format(str, (Object[])args);
/* 58 */     return str;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/a/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */