/*    */ package com.a.a.b;
/*    */ 
/*    */ import com.a.a.e.b;
/*    */ import java.io.Closeable;
/*    */ import java.io.EOFException;
/*    */ import java.io.File;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.IOException;
/*    */ 
/*    */ public abstract class h
/*    */   implements Closeable {
/*    */   public int a(byte[] buf) throws IOException {
/* 13 */     return a(buf, 0, buf.length);
/*    */   }
/*    */   
/*    */   public abstract int a(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
/*    */   
/*    */   public abstract void b(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws EOFException, IOException;
/*    */   
/*    */   public abstract long a() throws IOException;
/*    */   
/*    */   public abstract void a(long paramLong) throws IOException;
/*    */   
/*    */   public abstract long b() throws IOException;
/*    */   
/*    */   public static h a(File file) throws FileNotFoundException, IOException {
/* 27 */     return new j(file);
/*    */   }
/*    */   
/*    */   public static h a(b buf) {
/* 31 */     return new i(buf);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/b/h.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */