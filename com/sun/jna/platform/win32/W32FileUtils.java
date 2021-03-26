/*    */ package com.sun.jna.platform.win32;
/*    */ 
/*    */ import com.sun.jna.platform.FileUtils;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
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
/*    */ public class W32FileUtils
/*    */   extends FileUtils
/*    */ {
/*    */   public boolean hasTrash() {
/* 34 */     return true;
/*    */   }
/*    */   
/*    */   public void moveToTrash(File[] files) throws IOException {
/* 38 */     Shell32 shell = Shell32.INSTANCE;
/* 39 */     ShellAPI.SHFILEOPSTRUCT fileop = new ShellAPI.SHFILEOPSTRUCT();
/* 40 */     fileop.wFunc = 3;
/* 41 */     String[] paths = new String[files.length];
/* 42 */     for (int i = 0; i < paths.length; i++) {
/* 43 */       paths[i] = files[i].getAbsolutePath();
/*    */     }
/* 45 */     fileop.pFrom = fileop.encodePaths(paths);
/* 46 */     fileop.fFlags = 1620;
/* 47 */     int ret = shell.SHFileOperation(fileop);
/* 48 */     if (ret != 0) {
/* 49 */       throw new IOException("Move to trash failed: " + fileop.pFrom + ": " + 
/* 50 */           Kernel32Util.formatMessageFromLastErrorCode(ret));
/*    */     }
/* 52 */     if (fileop.fAnyOperationsAborted)
/* 53 */       throw new IOException("Move to trash aborted"); 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/sun/jna/platform/win32/W32FileUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */