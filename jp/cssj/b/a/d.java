/*     */ package jp.cssj.b.a;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.text.MessageFormat;
/*     */ import java.util.ResourceBundle;
/*     */ import jp.cssj.b.b.a;
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
/*     */ public final class d
/*     */ {
/*  25 */   public static final a a = new j(System.out);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  32 */   public static final a b = new j(System.err);
/*     */   
/*     */   public static a a(PrintStream out) {
/*  35 */     return new j(out);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  41 */   public static final a c = new a()
/*     */     {
/*     */       public void a(short code, String[] args, String mes) {}
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final short d = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final short e = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final short f = 3;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final short g = 4;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final short a(short code) {
/*  71 */     return (short)(code >> 12 & 0xF);
/*     */   }
/*     */   
/*  74 */   private static final ResourceBundle h = ResourceBundle.getBundle(c.class.getName());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String b(short code) {
/*  83 */     String str = Integer.toHexString(code).toUpperCase();
/*  84 */     str = h.getString(str);
/*  85 */     return str;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String a(short code, String[] args) {
/*  95 */     String str = b(code);
/*  96 */     if (args != null) {
/*  97 */       for (int i = 0; i < args.length; i++) {
/*  98 */         if (args[i] != null && args[i].length() > 2083) {
/*  99 */           args[i] = args[i].substring(0, 2080) + "...";
/*     */         }
/*     */       } 
/*     */     }
/* 103 */     str = MessageFormat.format(str, (Object[])args);
/* 104 */     return str;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/b/a/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */