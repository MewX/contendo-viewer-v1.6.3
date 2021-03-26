/*    */ package jp.cssj.sakae.c.a;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Arrays;
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
/*    */ public class g
/*    */   implements Serializable
/*    */ {
/*    */   private static final long a = 0L;
/*    */   public static final byte f = 0;
/*    */   public static final byte g = 1;
/*    */   public static final byte h = 2;
/*    */   public static final byte i = 3;
/*    */   public static final byte j = 4;
/*    */   public static final g k;
/*    */   private final byte[] b;
/*    */   
/*    */   static {
/* 34 */     k = new g(new byte[] { 0, 1 });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public g(byte[] policies) {
/* 40 */     if (!l && policies.length <= 0) throw new AssertionError(); 
/* 41 */     this.b = policies;
/*    */   }
/*    */   
/*    */   public int b() {
/* 45 */     return this.b.length;
/*    */   }
/*    */   
/*    */   public byte a(int i) {
/* 49 */     return this.b[i];
/*    */   }
/*    */   
/*    */   public boolean equals(Object o) {
/* 53 */     if (o == null || !(o instanceof g)) {
/* 54 */       return false;
/*    */     }
/* 56 */     g a = (g)o;
/* 57 */     return Arrays.equals(this.b, a.b);
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 61 */     int h = this.b[0];
/* 62 */     for (int i = 1; i < this.b.length; i++) {
/* 63 */       h = 31 * h + this.b[i];
/*    */     }
/* 65 */     return h;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 69 */     StringBuffer buff = new StringBuffer();
/* 70 */     for (int i = 0; i < this.b.length; i++) {
/* 71 */       switch (this.b[i]) {
/*    */         case 0:
/* 73 */           buff.append("core ");
/*    */           break;
/*    */         case 1:
/* 76 */           buff.append("cid-keyed ");
/*    */           break;
/*    */         case 2:
/* 79 */           buff.append("cid-identity ");
/*    */           break;
/*    */         case 3:
/* 82 */           buff.append("embedded ");
/*    */           break;
/*    */         case 4:
/* 85 */           buff.append("outlines ");
/*    */           break;
/*    */         default:
/* 88 */           throw new IllegalStateException();
/*    */       } 
/*    */     } 
/* 91 */     return buff.toString();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/c/a/g.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */