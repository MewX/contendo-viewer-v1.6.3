/*    */ package com.a.a.b;
/*    */ 
/*    */ import java.io.EOFException;
/*    */ import java.io.File;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.IOException;
/*    */ import java.io.RandomAccessFile;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class j
/*    */   extends h
/*    */ {
/*    */   RandomAccessFile a;
/*    */   
/*    */   public j(File file) throws FileNotFoundException, IOException {
/* 18 */     this.a = new RandomAccessFile(file, "r");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public j(RandomAccessFile raf) {
/* 25 */     this.a = raf;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int a(byte[] buf, int off, int length) throws IOException {
/* 34 */     return this.a.read(buf, off, length);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void b(byte[] buf, int off, int length) throws EOFException, IOException {
/* 43 */     this.a.readFully(buf, off, length);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public long a() throws IOException {
/* 51 */     return this.a.getFilePointer();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(long pos) throws IOException {
/* 59 */     this.a.seek(pos);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public long b() throws IOException {
/* 67 */     return this.a.length();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void close() throws IOException {
/* 75 */     this.a.close();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/b/j.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */