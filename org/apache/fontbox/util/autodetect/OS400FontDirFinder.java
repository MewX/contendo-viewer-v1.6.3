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
/*    */ public class OS400FontDirFinder
/*    */   extends NativeFontDirFinder
/*    */ {
/*    */   protected String[] getSearchableDirectories() {
/* 27 */     return new String[] { System.getProperty("user.home") + "/.fonts", "/QIBM/ProdData/OS400/Fonts" };
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/util/autodetect/OS400FontDirFinder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */