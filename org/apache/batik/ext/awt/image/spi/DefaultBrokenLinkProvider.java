/*    */ package org.apache.batik.ext.awt.image.spi;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.util.Hashtable;
/*    */ import org.apache.batik.ext.awt.image.GraphicsUtil;
/*    */ import org.apache.batik.ext.awt.image.renderable.Filter;
/*    */ import org.apache.batik.ext.awt.image.renderable.RedRable;
/*    */ import org.apache.batik.i18n.LocalizableSupport;
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
/*    */ public class DefaultBrokenLinkProvider
/*    */   extends BrokenLinkProvider
/*    */ {
/* 38 */   static Filter brokenLinkImg = null;
/*    */   
/*    */   static final String MESSAGE_RSRC = "resources.Messages";
/* 41 */   static final Color BROKEN_LINK_COLOR = new Color(255, 255, 255, 190);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String formatMessage(Object base, String code, Object[] params) {
/* 47 */     ClassLoader cl = null;
/*    */     
/*    */     try {
/* 50 */       cl = DefaultBrokenLinkProvider.class.getClassLoader();
/*    */ 
/*    */       
/* 53 */       cl = base.getClass().getClassLoader();
/* 54 */     } catch (SecurityException securityException) {}
/*    */ 
/*    */     
/* 57 */     LocalizableSupport ls = new LocalizableSupport("resources.Messages", base.getClass(), cl);
/* 58 */     return ls.formatMessage(code, params);
/*    */   }
/*    */ 
/*    */   
/*    */   public Filter getBrokenLinkImage(Object base, String code, Object[] params) {
/* 63 */     synchronized (DefaultBrokenLinkProvider.class) {
/* 64 */       if (brokenLinkImg != null) {
/* 65 */         return brokenLinkImg;
/*    */       }
/*    */       
/* 68 */       BufferedImage bi = new BufferedImage(100, 100, 2);
/*    */ 
/*    */ 
/*    */       
/* 72 */       Hashtable<Object, Object> ht = new Hashtable<Object, Object>();
/* 73 */       ht.put("org.apache.batik.BrokenLinkImage", formatMessage(base, code, params));
/*    */       
/* 75 */       bi = new BufferedImage(bi.getColorModel(), bi.getRaster(), bi.isAlphaPremultiplied(), ht);
/*    */ 
/*    */       
/* 78 */       Graphics2D g2d = bi.createGraphics();
/*    */       
/* 80 */       g2d.setColor(BROKEN_LINK_COLOR);
/* 81 */       g2d.fillRect(0, 0, 100, 100);
/* 82 */       g2d.setColor(Color.black);
/* 83 */       g2d.drawRect(2, 2, 96, 96);
/* 84 */       g2d.drawString("Broken Image", 6, 50);
/* 85 */       g2d.dispose();
/*    */       
/* 87 */       brokenLinkImg = (Filter)new RedRable(GraphicsUtil.wrap(bi));
/* 88 */       return brokenLinkImg;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/spi/DefaultBrokenLinkProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */