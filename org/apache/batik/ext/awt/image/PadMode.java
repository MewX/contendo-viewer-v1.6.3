/*    */ package org.apache.batik.ext.awt.image;
/*    */ 
/*    */ import java.io.ObjectStreamException;
/*    */ import java.io.Serializable;
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
/*    */ public final class PadMode
/*    */   implements Serializable
/*    */ {
/*    */   public static final int MODE_ZERO_PAD = 1;
/*    */   public static final int MODE_REPLICATE = 2;
/*    */   public static final int MODE_WRAP = 3;
/* 39 */   public static final PadMode ZERO_PAD = new PadMode(1);
/*    */ 
/*    */   
/* 42 */   public static final PadMode REPLICATE = new PadMode(2);
/*    */ 
/*    */   
/* 45 */   public static final PadMode WRAP = new PadMode(3);
/*    */   
/*    */   private int mode;
/*    */ 
/*    */   
/*    */   public int getMode() {
/* 51 */     return this.mode;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private PadMode(int mode) {
/* 60 */     this.mode = mode;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private Object readResolve() throws ObjectStreamException {
/* 71 */     switch (this.mode) {
/*    */       case 1:
/* 73 */         return ZERO_PAD;
/*    */       case 2:
/* 75 */         return REPLICATE;
/*    */       case 3:
/* 77 */         return WRAP;
/*    */     } 
/* 79 */     throw new RuntimeException("Unknown Pad Mode type");
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/PadMode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */