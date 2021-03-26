/*    */ package net.a.a.f;
/*    */ 
/*    */ import java.awt.Font;
/*    */ import java.awt.FontFormatException;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.util.List;
/*    */ import java.util.Set;
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
/*    */ public abstract class b
/*    */ {
/*    */   public static final String a = "sansserif";
/* 48 */   private static b b = new a();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static b b() {
/* 56 */     return b;
/*    */   }
/*    */   
/*    */   public abstract Font a(String paramString, int paramInt, float paramFloat);
/*    */   
/*    */   public abstract Font a(List<String> paramList, int paramInt1, int paramInt2, float paramFloat);
/*    */   
/*    */   public abstract Font a(int paramInt, File paramFile) throws IOException, FontFormatException;
/*    */   
/*    */   public abstract Font a(int paramInt, InputStream paramInputStream) throws IOException, FontFormatException;
/*    */   
/*    */   public abstract Set<String> a();
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/f/b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */