/*    */ package org.apache.xmlgraphics.image.loader.pipeline;
/*    */ 
/*    */ import org.apache.xmlgraphics.image.loader.ImageFlavor;
/*    */ import org.apache.xmlgraphics.util.dijkstra.Vertex;
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
/*    */ public class ImageRepresentation
/*    */   implements Vertex
/*    */ {
/*    */   private ImageFlavor flavor;
/*    */   
/*    */   public ImageRepresentation(ImageFlavor flavor) {
/* 39 */     if (flavor == null) {
/* 40 */       throw new NullPointerException("flavor must not be null");
/*    */     }
/* 42 */     this.flavor = flavor;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ImageFlavor getFlavor() {
/* 50 */     return this.flavor;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 55 */     assert obj != null;
/* 56 */     return toString().equals(obj.toString());
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 61 */     return getFlavor().hashCode();
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(Object obj) {
/* 66 */     return toString().compareTo(((ImageRepresentation)obj).toString());
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 71 */     return getFlavor().toString();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/pipeline/ImageRepresentation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */