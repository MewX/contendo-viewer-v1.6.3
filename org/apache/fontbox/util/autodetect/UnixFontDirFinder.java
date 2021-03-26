/*    */ package org.apache.fontbox.util.autodetect;
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
/*    */ public class UnixFontDirFinder
/*    */   extends NativeFontDirFinder
/*    */ {
/*    */   protected String[] getSearchableDirectories() {
/* 35 */     return new String[] { System.getProperty("user.home") + "/.fonts", "/usr/local/fonts", "/usr/local/share/fonts", "/usr/share/fonts", "/usr/X11R6/lib/X11/fonts" };
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/util/autodetect/UnixFontDirFinder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */