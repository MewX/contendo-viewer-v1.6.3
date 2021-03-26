/*    */ package org.apache.batik.swing.svg;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SVGFileFilter
/*    */   extends FileFilter
/*    */ {
/*    */   public boolean accept(File f) {
/* 38 */     boolean accept = false;
/* 39 */     String fileName = null;
/* 40 */     if (f != null)
/* 41 */       if (f.isDirectory()) {
/* 42 */         accept = true;
/*    */       } else {
/* 44 */         fileName = f.getPath().toLowerCase();
/* 45 */         if (fileName.endsWith(".svg") || fileName.endsWith(".svgz")) {
/* 46 */           accept = true;
/*    */         }
/*    */       }  
/* 49 */     return accept;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 56 */     return ".svg, .svgz";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/swing/svg/SVGFileFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */