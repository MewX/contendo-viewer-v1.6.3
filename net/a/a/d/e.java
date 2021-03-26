/*    */ package net.a.a.d;
/*    */ 
/*    */ import java.awt.Dimension;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import net.a.a.c;
/*    */ import org.w3c.dom.Document;
/*    */ import org.w3c.dom.Node;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface e
/*    */ {
/*    */   Dimension a(Node paramNode, c paramc, OutputStream paramOutputStream) throws IOException;
/*    */   
/*    */   a a(Node paramNode, c paramc);
/*    */   
/*    */   public static class a
/*    */   {
/*    */     private final Document a;
/*    */     private final Dimension b;
/*    */     private final float c;
/*    */     
/*    */     public a(Document param1Document, Dimension param1Dimension, float param1Float) {
/* 68 */       this.a = param1Document;
/* 69 */       this.b = param1Dimension;
/* 70 */       this.c = param1Float;
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public Document a() {
/* 78 */       return this.a;
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public Dimension b() {
/* 85 */       return this.b;
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public float c() {
/* 92 */       return this.c;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/d/e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */