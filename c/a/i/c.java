/*     */ package c.a.i;
/*     */ 
/*     */ import java.util.Hashtable;
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
/*     */ public class c
/*     */ {
/*  78 */   private static final Hashtable a = new Hashtable<Object, Object>();
/*     */ 
/*     */   
/*  81 */   private static f b = new j(System.out, System.err, 78);
/*     */ 
/*     */ 
/*     */   
/*  85 */   private static final Hashtable c = new Hashtable<Object, Object>();
/*     */ 
/*     */ 
/*     */   
/*  89 */   private static i d = null;
/*     */ 
/*     */   
/*     */   public static void a(Thread t, i pw) {
/*  93 */     if (pw == null) {
/*  94 */       throw new NullPointerException();
/*     */     }
/*  96 */     if (t == null) {
/*  97 */       d = pw;
/*     */     } else {
/*     */       
/* 100 */       c.put(t, pw);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static i a() {
/* 111 */     i pw = (i)c.get(Thread.currentThread());
/* 112 */     return (pw == null) ? d : pw;
/*     */   }
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
/*     */   public static void a(Thread t, f ml) {
/* 127 */     if (ml == null) {
/* 128 */       throw new NullPointerException();
/*     */     }
/* 130 */     if (t == null) {
/* 131 */       b = ml;
/*     */     } else {
/*     */       
/* 134 */       a.put(t, ml);
/*     */     } 
/*     */   }
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
/*     */   public static f b() {
/* 150 */     f ml = (f)a.get(Thread.currentThread());
/* 151 */     return (ml == null) ? b : ml;
/*     */   }
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
/*     */   public static f a(Thread t) {
/* 168 */     f ml = (f)a.get(t);
/* 169 */     return (ml == null) ? b : ml;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/i/c.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */