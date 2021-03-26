/*    */ package org.apache.batik.apps.svgbrowser;
/*    */ 
/*    */ import java.io.File;
/*    */ import javax.swing.filechooser.FileFilter;
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
/*    */ public class SquiggleInputHandlerFilter
/*    */   extends FileFilter
/*    */ {
/*    */   protected SquiggleInputHandler handler;
/*    */   
/*    */   public SquiggleInputHandlerFilter(SquiggleInputHandler handler) {
/* 35 */     this.handler = handler;
/*    */   }
/*    */   
/*    */   public boolean accept(File f) {
/* 39 */     return (f.isDirectory() || this.handler.accept(f));
/*    */   }
/*    */   
/*    */   public String getDescription() {
/* 43 */     StringBuffer sb = new StringBuffer();
/* 44 */     String[] extensions = this.handler.getHandledExtensions();
/* 45 */     int n = (extensions != null) ? extensions.length : 0;
/* 46 */     for (int i = 0; i < n; i++) {
/* 47 */       if (i > 0) {
/* 48 */         sb.append(", ");
/*    */       }
/* 50 */       sb.append(extensions[i]);
/*    */     } 
/*    */     
/* 53 */     if (n > 0) {
/* 54 */       sb.append(' ');
/*    */     }
/*    */     
/* 57 */     sb.append(this.handler.getDescription());
/* 58 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/svgbrowser/SquiggleInputHandlerFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */