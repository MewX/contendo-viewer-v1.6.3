/*    */ package org.apache.xmlgraphics.image.loader.pipeline;
/*    */ 
/*    */ import org.apache.xmlgraphics.image.loader.spi.ImageConverter;
/*    */ import org.apache.xmlgraphics.image.loader.util.Penalty;
/*    */ import org.apache.xmlgraphics.util.dijkstra.Edge;
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
/*    */ class ImageConversionEdge
/*    */   implements Edge
/*    */ {
/*    */   private ImageRepresentation source;
/*    */   private ImageRepresentation target;
/*    */   private ImageConverter converter;
/*    */   private int penalty;
/*    */   
/*    */   public ImageConversionEdge(ImageConverter converter, Penalty penalty) {
/* 44 */     this.converter = converter;
/* 45 */     this.source = new ImageRepresentation(converter.getSourceFlavor());
/* 46 */     this.target = new ImageRepresentation(converter.getTargetFlavor());
/* 47 */     this.penalty = Math.max(0, penalty.getValue());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ImageConverter getImageConverter() {
/* 55 */     return this.converter;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPenalty() {
/* 60 */     return this.penalty;
/*    */   }
/*    */ 
/*    */   
/*    */   public Vertex getStart() {
/* 65 */     return this.source;
/*    */   }
/*    */ 
/*    */   
/*    */   public Vertex getEnd() {
/* 70 */     return this.target;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/pipeline/ImageConversionEdge.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */