/*    */ package org.apache.batik.ext.awt.image.spi;
/*    */ 
/*    */ import java.awt.Image;
/*    */ import org.apache.batik.ext.awt.image.renderable.Filter;
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
/*    */ public abstract class BrokenLinkProvider
/*    */ {
/*    */   public static final String BROKEN_LINK_PROPERTY = "org.apache.batik.BrokenLinkImage";
/*    */   
/*    */   public abstract Filter getBrokenLinkImage(Object paramObject, String paramString, Object[] paramArrayOfObject);
/*    */   
/*    */   public static boolean hasBrokenLinkProperty(Filter f) {
/* 63 */     Object o = f.getProperty("org.apache.batik.BrokenLinkImage");
/* 64 */     if (o == null) return false; 
/* 65 */     if (o == Image.UndefinedProperty) return false; 
/* 66 */     return true;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/spi/BrokenLinkProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */