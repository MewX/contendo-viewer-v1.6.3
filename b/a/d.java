/*     */ package b.a;
/*     */ 
/*     */ import java.util.Stack;
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
/*     */ public class d
/*     */ {
/*     */   protected Stack a;
/*     */   protected int b;
/*     */   protected Stack c;
/*     */   
/*     */   public d(Stack paramStack) throws Exception {
/*  31 */     if (paramStack == null) {
/*  32 */       throw new Exception(
/*  33 */           "Internal parser error: attempt to create null virtual stack");
/*     */     }
/*     */     
/*  36 */     this.a = paramStack;
/*  37 */     this.c = new Stack();
/*  38 */     this.b = 0;
/*     */ 
/*     */     
/*  41 */     b();
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
/*     */   protected void b() {
/*  84 */     if (this.b >= this.a.size()) {
/*     */       return;
/*     */     }
/*  87 */     b b = this.a.elementAt(this.a.size() - 1 - this.b);
/*     */ 
/*     */     
/*  90 */     this.b++;
/*     */ 
/*     */     
/*  93 */     this.c.push(new Integer(b.b));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a() {
/* 103 */     return this.c.empty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int d() throws Exception {
/* 111 */     if (this.c.empty()) {
/* 112 */       throw new Exception(
/* 113 */           "Internal parser error: top() called on empty virtual stack");
/*     */     }
/* 115 */     return ((Integer)this.c.peek()).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void c() throws Exception {
/* 123 */     if (this.c.empty()) {
/* 124 */       throw new Exception(
/* 125 */           "Internal parser error: pop from empty virtual stack");
/*     */     }
/*     */     
/* 128 */     this.c.pop();
/*     */ 
/*     */     
/* 131 */     if (this.c.empty()) {
/* 132 */       b();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(int paramInt) {
/* 140 */     this.c.push(new Integer(paramInt));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/b/a/d.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */