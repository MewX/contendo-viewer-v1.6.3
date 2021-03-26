/*    */ package org.apache.xmlgraphics.java2d.color;
/*    */ 
/*    */ import java.awt.color.ColorSpace;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class ColorSpaces
/*    */ {
/*    */   private static DeviceCMYKColorSpace deviceCMYK;
/*    */   private static CIELabColorSpace cieLabD50;
/*    */   private static CIELabColorSpace cieLabD65;
/*    */   
/*    */   public static synchronized DeviceCMYKColorSpace getDeviceCMYKColorSpace() {
/* 42 */     if (deviceCMYK == null) {
/* 43 */       deviceCMYK = new DeviceCMYKColorSpace();
/*    */     }
/* 45 */     return deviceCMYK;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean isDeviceColorSpace(ColorSpace cs) {
/* 54 */     return cs instanceof AbstractDeviceSpecificColorSpace;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static synchronized CIELabColorSpace getCIELabColorSpaceD50() {
/* 62 */     if (cieLabD50 == null) {
/* 63 */       cieLabD50 = new CIELabColorSpace(CIELabColorSpace.getD50WhitePoint());
/*    */     }
/* 65 */     return cieLabD50;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static synchronized CIELabColorSpace getCIELabColorSpaceD65() {
/* 73 */     if (cieLabD65 == null) {
/* 74 */       cieLabD65 = new CIELabColorSpace(CIELabColorSpace.getD65WhitePoint());
/*    */     }
/* 76 */     return cieLabD65;
/*    */   }
/*    */   
/* 79 */   private static final ColorSpaceOrigin UNKNOWN_ORIGIN = new ColorSpaceOrigin()
/*    */     {
/*    */       public String getProfileURI() {
/* 82 */         return null;
/*    */       }
/*    */       
/*    */       public String getProfileName() {
/* 86 */         return null;
/*    */       }
/*    */     };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static ColorSpaceOrigin getColorSpaceOrigin(ColorSpace cs) {
/* 96 */     if (cs instanceof ColorSpaceOrigin) {
/* 97 */       return (ColorSpaceOrigin)cs;
/*    */     }
/* 99 */     return UNKNOWN_ORIGIN;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/java2d/color/ColorSpaces.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */