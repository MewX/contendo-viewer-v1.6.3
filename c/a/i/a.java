/*     */ package c.a.i;
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
/*     */ public class a
/*     */ {
/*     */   public static final int a = 8;
/*     */   public static final int b = 4;
/*     */   
/*     */   public static void a(int[] arr, int val) {
/*  79 */     int len = arr.length;
/*     */     
/*  81 */     if (len < 8) {
/*     */       
/*  83 */       for (int i = len - 1; i >= 0; i--) {
/*  84 */         arr[i] = val;
/*     */       }
/*     */     } else {
/*     */       
/*  88 */       int len2 = len >> 1; int i;
/*  89 */       for (i = 0; i < 4; i++) {
/*  90 */         arr[i] = val;
/*     */       }
/*  92 */       for (; i <= len2; i <<= 1)
/*     */       {
/*  94 */         System.arraycopy(arr, 0, arr, i, i);
/*     */       }
/*  96 */       if (i < len) {
/*  97 */         System.arraycopy(arr, 0, arr, i, len - i);
/*     */       }
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void a(byte[] arr, byte val) {
/* 118 */     int len = arr.length;
/*     */     
/* 120 */     if (len < 8) {
/*     */       
/* 122 */       for (int i = len - 1; i >= 0; i--) {
/* 123 */         arr[i] = val;
/*     */       }
/*     */     } else {
/*     */       
/* 127 */       int len2 = len >> 1; int i;
/* 128 */       for (i = 0; i < 4; i++) {
/* 129 */         arr[i] = val;
/*     */       }
/* 131 */       for (; i <= len2; i <<= 1)
/*     */       {
/* 133 */         System.arraycopy(arr, 0, arr, i, i);
/*     */       }
/* 135 */       if (i < len)
/* 136 */         System.arraycopy(arr, 0, arr, i, len - i); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/i/a.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */