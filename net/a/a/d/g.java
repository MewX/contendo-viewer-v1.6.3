/*    */ package net.a.a.d;
/*    */ 
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Graphics2D;
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import java.lang.reflect.Constructor;
/*    */ import java.lang.reflect.InvocationTargetException;
/*    */ import net.a.a.c;
/*    */ import net.a.a.g.c;
/*    */ import org.freehep.graphics2d.VectorGraphics;
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
/*    */ public class g
/*    */   implements e
/*    */ {
/*    */   private final Constructor<VectorGraphics> a;
/*    */   
/*    */   g(Class<?> paramClass) throws NoSuchMethodException {
/* 47 */     this
/* 48 */       .a = (Constructor)paramClass.getConstructor(new Class[] { OutputStream.class, Dimension.class });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Dimension a(Node paramNode, c paramc, OutputStream paramOutputStream) throws IOException {
/* 54 */     VectorGraphics vectorGraphics1 = a(new ByteArrayOutputStream(), new Dimension(1, 1));
/*    */     
/* 56 */     c c1 = new c(paramNode, paramc, (Graphics2D)vectorGraphics1);
/* 57 */     int i = (int)Math.ceil(c1.b());
/*    */     
/* 59 */     Dimension dimension = new Dimension((int)Math.ceil(c1.a()), (int)Math.ceil(c1
/* 60 */           .c()) + i);
/*    */ 
/*    */     
/* 63 */     VectorGraphics vectorGraphics2 = a(paramOutputStream, dimension);
/* 64 */     vectorGraphics2.setCreator("JEuclid (from MathML)");
/* 65 */     vectorGraphics2.startExport();
/* 66 */     c1.a((Graphics2D)vectorGraphics2, 0.0F, i);
/* 67 */     vectorGraphics2.endExport();
/*    */     
/* 69 */     return dimension;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public e.a a(Node paramNode, c paramc) {
/* 75 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   private VectorGraphics a(OutputStream paramOutputStream, Dimension paramDimension) throws IOException {
/*    */     try {
/* 81 */       return this.a.newInstance(new Object[] { paramOutputStream, paramDimension });
/* 82 */     } catch (InvocationTargetException invocationTargetException) {
/* 83 */       throw new IOException(invocationTargetException.toString());
/* 84 */     } catch (IllegalArgumentException illegalArgumentException) {
/* 85 */       throw new IOException(illegalArgumentException.toString());
/* 86 */     } catch (InstantiationException instantiationException) {
/* 87 */       throw new IOException(instantiationException.toString());
/* 88 */     } catch (IllegalAccessException illegalAccessException) {
/* 89 */       throw new IOException(illegalAccessException.toString());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/d/g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */