/*    */ package org.apache.xmlgraphics.image.loader.cache;
/*    */ 
/*    */ import org.apache.xmlgraphics.image.loader.ImageFlavor;
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
/*    */ public class ImageKey
/*    */ {
/*    */   private String uri;
/*    */   private ImageFlavor flavor;
/*    */   
/*    */   public ImageKey(String uri, ImageFlavor flavor) {
/* 38 */     if (uri == null) {
/* 39 */       throw new NullPointerException("URI must not be null");
/*    */     }
/* 41 */     if (flavor == null) {
/* 42 */       throw new NullPointerException("flavor must not be null");
/*    */     }
/* 44 */     this.uri = uri;
/* 45 */     this.flavor = flavor;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 50 */     int prime = 31;
/* 51 */     int result = 1;
/* 52 */     result = 31 * result + ((this.flavor == null) ? 0 : this.flavor.hashCode());
/* 53 */     result = 31 * result + ((this.uri == null) ? 0 : this.uri.hashCode());
/* 54 */     return result;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 59 */     if (this == obj) {
/* 60 */       return true;
/*    */     }
/* 62 */     if (obj == null) {
/* 63 */       return false;
/*    */     }
/* 65 */     if (getClass() != obj.getClass()) {
/* 66 */       return false;
/*    */     }
/* 68 */     ImageKey other = (ImageKey)obj;
/* 69 */     if (!this.uri.equals(other.uri)) {
/* 70 */       return false;
/*    */     }
/* 72 */     if (!this.flavor.equals(other.flavor)) {
/* 73 */       return false;
/*    */     }
/* 75 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 80 */     return this.uri + " (" + this.flavor + ")";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/cache/ImageKey.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */