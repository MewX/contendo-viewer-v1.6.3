/*    */ package net.a.a.d;
/*    */ 
/*    */ import java.awt.Dimension;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import javax.imageio.ImageWriter;
/*    */ import javax.imageio.stream.MemoryCacheImageOutputStream;
/*    */ import net.a.a.c;
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
/*    */ public class j
/*    */   implements e
/*    */ {
/*    */   private final ImageWriter a;
/*    */   private final int b;
/*    */   
/*    */   j(ImageWriter paramImageWriter, boolean paramBoolean) {
/* 54 */     this.a = paramImageWriter;
/* 55 */     if (paramBoolean) {
/* 56 */       this.b = 1;
/*    */     } else {
/* 58 */       this.b = 2;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Dimension a(Node paramNode, c paramc, OutputStream paramOutputStream) throws IOException {
/* 65 */     MemoryCacheImageOutputStream memoryCacheImageOutputStream = new MemoryCacheImageOutputStream(paramOutputStream);
/*    */     
/* 67 */     BufferedImage bufferedImage = c.a().a(paramNode, paramc, this.b);
/*    */     
/* 69 */     synchronized (this.a) {
/* 70 */       this.a.setOutput(memoryCacheImageOutputStream);
/* 71 */       this.a.write(bufferedImage);
/*    */     } 
/* 73 */     memoryCacheImageOutputStream.close();
/* 74 */     return new Dimension(bufferedImage.getWidth(), bufferedImage.getHeight());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public e.a a(Node paramNode, c paramc) {
/* 80 */     return null;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/d/j.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */