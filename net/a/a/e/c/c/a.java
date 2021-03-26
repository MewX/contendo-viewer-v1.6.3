/*     */ package net.a.a.e.c.c;
/*     */ 
/*     */ import net.a.a.c;
/*     */ import net.a.a.c.b;
/*     */ import net.a.a.c.e;
/*     */ import net.a.a.e.c;
/*     */ import org.apache.batik.dom.AbstractDocument;
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
/*     */ public abstract class a
/*     */   extends c
/*     */ {
/*     */   public static final String r = "subscriptshift";
/*     */   public static final String s = "superscriptshift";
/*     */   
/*     */   public a(String paramString, AbstractDocument paramAbstractDocument) {
/*  51 */     super(paramString, paramAbstractDocument);
/*     */     
/*  53 */     a("subscriptshift", "0");
/*     */     
/*  55 */     a("superscriptshift", "0");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSubscriptshift() {
/*  63 */     return a("subscriptshift");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSubscriptshift(String paramString) {
/*  71 */     setAttribute("subscriptshift", paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSuperscriptshift() {
/*  79 */     return 
/*  80 */       a("superscriptshift");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSuperscriptshift(String paramString) {
/*  88 */     setAttribute("superscriptshift", paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public c a(int paramInt, c paramc) {
/*  96 */     c c1 = b(paramc);
/*  97 */     if (paramInt == 0) {
/*  98 */       return c1;
/*     */     }
/* 100 */     return (c)new e((c)new b(c1), 1);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/c/c/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */