/*    */ package b.a;
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
/*    */ public class b
/*    */ {
/*    */   public int a;
/*    */   public int b;
/*    */   boolean c;
/*    */   public int d;
/*    */   public int e;
/*    */   public Object f;
/*    */   
/*    */   public b(int paramInt1, int paramInt2, int paramInt3, Object paramObject) {
/* 30 */     this(paramInt1);
/* 31 */     this.d = paramInt2;
/* 32 */     this.e = paramInt3;
/* 33 */     this.f = paramObject;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public b(int paramInt, Object paramObject) {
/* 41 */     this(paramInt, -1, -1, paramObject);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public b(int paramInt1, int paramInt2, int paramInt3) {
/* 49 */     this(paramInt1, paramInt2, paramInt3, null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public b(int paramInt) {
/* 57 */     this(paramInt, -1);
/* 58 */     this.d = -1;
/* 59 */     this.e = -1;
/* 60 */     this.f = null;
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
/*    */   b(int paramInt1, int paramInt2) {
/* 86 */     this.c = false;
/*    */     this.a = paramInt1;
/*    */     this.b = paramInt2;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 98 */     return "#" + this.a;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/b/a/b.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */