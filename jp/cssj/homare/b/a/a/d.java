/*     */ package jp.cssj.homare.b.a.a;
/*     */ 
/*     */ import jp.cssj.homare.b.a.j;
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
/*     */ public abstract class d
/*     */ {
/*     */   public static final byte a = 0;
/*     */   public static final byte b = 1;
/*     */   
/*     */   public static class a
/*     */     extends d
/*     */   {
/*     */     public final j d;
/*     */     
/*     */     public a(j box) {
/*  29 */       if (!e && box == null) throw new AssertionError(); 
/*  30 */       this.d = box;
/*     */     }
/*     */     
/*     */     private a() {
/*  34 */       this.d = null;
/*     */     }
/*     */     
/*     */     public byte a() {
/*  38 */       return 0;
/*     */     }
/*     */     
/*     */     public String toString() {
/*  42 */       if (this.d == null) {
/*  43 */         return "AUTO_BREAK_MODE";
/*     */       }
/*  45 */       return "AUTO_BREAK_MODE/" + (this.d.b()).al;
/*     */     }
/*     */   }
/*     */   
/*  49 */   public static a c = new a();
/*     */ 
/*     */   
/*     */   public abstract byte a();
/*     */ 
/*     */   
/*     */   public static class b
/*     */     extends d
/*     */   {
/*     */     public final j d;
/*     */     
/*     */     public final byte e;
/*     */     
/*     */     public b(j box, byte breakType) {
/*  63 */       if (!f && breakType != 4 && breakType != 7 && breakType != 5 && breakType != 6) throw new AssertionError();
/*     */       
/*  65 */       this.d = box;
/*  66 */       this.e = breakType;
/*     */     }
/*     */     
/*     */     public byte a() {
/*  70 */       return 1;
/*     */     }
/*     */     
/*     */     public String toString() {
/*  74 */       switch (this.e) {
/*     */         case 4:
/*  76 */           return "FORCE_BREAK_MODE ALWAYS";
/*     */         case 7:
/*  78 */           return "FORCE_BREAK_MODE COLUMN";
/*     */         case 5:
/*  80 */           return "FORCE_BREAK_MODE LEFT";
/*     */         case 6:
/*  82 */           return "FORCE_BREAK_MODE RIGHT";
/*     */       } 
/*  84 */       throw new IllegalStateException();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class c
/*     */     extends b
/*     */   {
/*     */     public final int g;
/*     */     
/*     */     public final int h;
/*     */ 
/*     */     
/*     */     public c(jp.cssj.homare.b.a.d box, byte breakType, int rowGroup, int row) {
/*  99 */       super((j)box, breakType);
/* 100 */       if (!i && row != -1 && box.a() != 11) throw new AssertionError(); 
/* 101 */       if (!i && row == -1 && box.a() != 10) throw new AssertionError(); 
/* 102 */       this.g = rowGroup;
/* 103 */       this.h = row;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/a/a/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */