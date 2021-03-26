/*    */ package com.sun.jna.platform;
/*    */ 
/*    */ import com.sun.jna.platform.win32.WinDef;
/*    */ import java.awt.Rectangle;
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
/*    */ public class DesktopWindow
/*    */ {
/*    */   private WinDef.HWND hwnd;
/*    */   private String title;
/*    */   private String filePath;
/*    */   private Rectangle locAndSize;
/*    */   
/*    */   public DesktopWindow(WinDef.HWND hwnd, String title, String filePath, Rectangle locAndSize) {
/* 55 */     this.hwnd = hwnd;
/* 56 */     this.title = title;
/* 57 */     this.filePath = filePath;
/* 58 */     this.locAndSize = locAndSize;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public WinDef.HWND getHWND() {
/* 65 */     return this.hwnd;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getTitle() {
/* 72 */     return this.title;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getFilePath() {
/* 79 */     return this.filePath;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Rectangle getLocAndSize() {
/* 86 */     return this.locAndSize;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/sun/jna/platform/DesktopWindow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */