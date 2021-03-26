/*    */ package org.apache.xmlgraphics.image.loader;
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
/*    */ public class MimeEnabledImageFlavor
/*    */   extends RefinedImageFlavor
/*    */ {
/*    */   private String mime;
/*    */   
/*    */   public MimeEnabledImageFlavor(ImageFlavor parentFlavor, String mime) {
/* 35 */     super(mime + ";" + parentFlavor.getName(), parentFlavor);
/* 36 */     this.mime = mime;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMimeType() {
/* 41 */     return this.mime;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object o) {
/* 46 */     if (this == o) {
/* 47 */       return true;
/*    */     }
/* 49 */     if (o == null || getClass() != o.getClass()) {
/* 50 */       return false;
/*    */     }
/* 52 */     if (!super.equals(o)) {
/* 53 */       return false;
/*    */     }
/*    */     
/* 56 */     MimeEnabledImageFlavor that = (MimeEnabledImageFlavor)o;
/*    */     
/* 58 */     if ((this.mime != null) ? !this.mime.equals(that.mime) : (that.mime != null)) {
/* 59 */       return false;
/*    */     }
/*    */     
/* 62 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 67 */     int result = super.hashCode();
/* 68 */     result = 31 * result + ((this.mime != null) ? this.mime.hashCode() : 0);
/* 69 */     return result;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/MimeEnabledImageFlavor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */