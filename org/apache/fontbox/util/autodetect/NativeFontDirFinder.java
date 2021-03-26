/*    */ package org.apache.fontbox.util.autodetect;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
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
/*    */ public abstract class NativeFontDirFinder
/*    */   implements FontDirFinder
/*    */ {
/*    */   public List<File> find() {
/* 38 */     List<File> fontDirList = new ArrayList<File>();
/* 39 */     String[] searchableDirectories = getSearchableDirectories();
/* 40 */     if (searchableDirectories != null)
/*    */     {
/* 42 */       for (String searchableDirectorie : searchableDirectories) {
/*    */         
/* 44 */         File fontDir = new File(searchableDirectorie);
/*    */         
/*    */         try {
/* 47 */           if (fontDir.exists() && fontDir.canRead())
/*    */           {
/* 49 */             fontDirList.add(fontDir);
/*    */           }
/*    */         }
/* 52 */         catch (SecurityException securityException) {}
/*    */       } 
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 58 */     return fontDirList;
/*    */   }
/*    */   
/*    */   protected abstract String[] getSearchableDirectories();
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/util/autodetect/NativeFontDirFinder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */