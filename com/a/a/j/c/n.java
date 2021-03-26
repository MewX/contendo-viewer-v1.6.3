/*    */ package com.a.a.j.c;
/*    */ 
/*    */ import com.a.a.b.b.b;
/*    */ import com.a.a.e.b;
/*    */ import java.io.File;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.RandomAccessFile;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class n
/*    */   extends e
/*    */ {
/*    */   protected File a;
/*    */   
/*    */   public n(m folder, String path, File file) throws FileNotFoundException {
/* 22 */     super(folder, path);
/* 23 */     this.a = file;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public InputStream d() throws IOException {
/* 31 */     return b.b(this.a);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public b e() throws IOException {
/* 38 */     return b.a(new RandomAccessFile(this.a, "r"));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public long f() {
/* 46 */     return this.a.length();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean g() {
/* 54 */     return this.a.isDirectory();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/j/c/n.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */