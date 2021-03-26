/*    */ package org.apache.xmlgraphics.image.loader;
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
/*    */ public interface ImageProcessingHints
/*    */ {
/* 30 */   public static final Object SOURCE_RESOLUTION = "SOURCE_RESOLUTION";
/*    */   
/* 32 */   public static final Object TARGET_RESOLUTION = "TARGET_RESOLUTION";
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 40 */   public static final Object IMAGE_SESSION_CONTEXT = "IMAGE_SESSION_CONTEXT";
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 48 */   public static final Object IMAGE_MANAGER = "IMAGE_MANAGER";
/*    */ 
/*    */   
/* 51 */   public static final Object IGNORE_COLOR_PROFILE = "IGNORE_COLOR_PROFILE";
/*    */ 
/*    */   
/* 54 */   public static final Object BITMAP_TYPE_INTENT = "BITMAP_TYPE_INTENT";
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static final String BITMAP_TYPE_INTENT_GRAY = "gray";
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static final String BITMAP_TYPE_INTENT_MONO = "mono";
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 71 */   public static final Object TRANSPARENCY_INTENT = "TRANSPARENCY_INTENT";
/*    */   public static final String TRANSPARENCY_INTENT_PRESERVE = "preserve";
/*    */   public static final String TRANSPARENCY_INTENT_IGNORE = "ignore";
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/ImageProcessingHints.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */