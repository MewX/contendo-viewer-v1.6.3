/*    */ package org.apache.pdfbox.pdmodel.font;
/*    */ 
/*    */ import java.lang.ref.SoftReference;
/*    */ import java.util.Map;
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ import org.apache.fontbox.FontBoxFont;
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
/*    */ public final class FontCache
/*    */ {
/* 33 */   private final Map<FontInfo, SoftReference<FontBoxFont>> cache = new ConcurrentHashMap<FontInfo, SoftReference<FontBoxFont>>();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void addFont(FontInfo info, FontBoxFont font) {
/* 41 */     this.cache.put(info, new SoftReference<FontBoxFont>(font));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public FontBoxFont getFont(FontInfo info) {
/* 49 */     SoftReference<FontBoxFont> reference = this.cache.get(info);
/* 50 */     return (reference != null) ? reference.get() : null;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/FontCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */