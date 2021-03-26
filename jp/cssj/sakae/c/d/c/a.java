/*    */ package jp.cssj.sakae.c.d.c;
/*    */ 
/*    */ import java.awt.font.TextAttribute;
/*    */ import java.text.AttributedCharacterIterator;
/*    */ import jp.cssj.sakae.c.a.c;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class a
/*    */ {
/*    */   static class a
/*    */     extends AttributedCharacterIterator.Attribute
/*    */   {
/*    */     private static final long a = 1L;
/*    */     
/*    */     protected a() {
/* 18 */       super("WRITING_MODE");
/*    */     }
/*    */   }
/*    */   
/* 22 */   public static final AttributedCharacterIterator.Attribute a = new a();
/* 23 */   public static final Byte b = Byte.valueOf((byte)1);
/* 24 */   public static final Byte c = Byte.valueOf((byte)3);
/*    */   
/*    */   public static final c a(String awtFamily, c defaultFamily) {
/* 27 */     if (awtFamily == null) {
/* 28 */       return defaultFamily;
/*    */     }
/* 30 */     return c.a(awtFamily);
/*    */   }
/*    */   
/*    */   public static final short a(Float awtWeight, short defaultWeight) {
/* 34 */     if (awtWeight == null)
/* 35 */       return defaultWeight; 
/* 36 */     if (awtWeight.compareTo(TextAttribute.WEIGHT_EXTRA_LIGHT) <= 0)
/* 37 */       return 100; 
/* 38 */     if (awtWeight.compareTo(TextAttribute.WEIGHT_LIGHT) <= 0)
/* 39 */       return 200; 
/* 40 */     if (awtWeight.compareTo(TextAttribute.WEIGHT_DEMILIGHT) <= 0)
/* 41 */       return 300; 
/* 42 */     if (awtWeight.compareTo(TextAttribute.WEIGHT_REGULAR) <= 0)
/* 43 */       return 400; 
/* 44 */     if (awtWeight.compareTo(TextAttribute.WEIGHT_SEMIBOLD) <= 0)
/* 45 */       return 500; 
/* 46 */     if (awtWeight.compareTo(TextAttribute.WEIGHT_MEDIUM) <= 0)
/* 47 */       return 500; 
/* 48 */     if (awtWeight.compareTo(TextAttribute.WEIGHT_DEMIBOLD) <= 0)
/* 49 */       return 600; 
/* 50 */     if (awtWeight.compareTo(TextAttribute.WEIGHT_BOLD) <= 0)
/* 51 */       return 700; 
/* 52 */     if (awtWeight.compareTo(TextAttribute.WEIGHT_HEAVY) <= 0)
/* 53 */       return 700; 
/* 54 */     if (awtWeight.compareTo(TextAttribute.WEIGHT_EXTRABOLD) <= 0) {
/* 55 */       return 800;
/*    */     }
/* 57 */     return 900;
/*    */   }
/*    */   
/*    */   public static final double a(Float awtSize, double defaultSize) {
/* 61 */     return (awtSize != null) ? awtSize.doubleValue() : defaultSize;
/*    */   }
/*    */   
/*    */   public static final byte a(Float awtPosture, byte defaultStyle) {
/* 65 */     if (awtPosture == null)
/* 66 */       return defaultStyle; 
/* 67 */     if (awtPosture.equals(TextAttribute.POSTURE_OBLIQUE)) {
/* 68 */       return 3;
/*    */     }
/* 70 */     return 1;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/c/d/c/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */