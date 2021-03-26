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
/*    */ public class XMLNamespaceEnabledImageFlavor
/*    */   extends RefinedImageFlavor
/*    */ {
/* 28 */   public static final ImageFlavor SVG_DOM = new XMLNamespaceEnabledImageFlavor(ImageFlavor.XML_DOM, "http://www.w3.org/2000/svg");
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private String namespace;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public XMLNamespaceEnabledImageFlavor(ImageFlavor parentFlavor, String namespace) {
/* 39 */     super(parentFlavor.getName() + ";namespace=" + namespace, parentFlavor);
/* 40 */     this.namespace = namespace;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getNamespace() {
/* 45 */     return this.namespace;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object o) {
/* 50 */     if (this == o) {
/* 51 */       return true;
/*    */     }
/* 53 */     if (o == null || getClass() != o.getClass()) {
/* 54 */       return false;
/*    */     }
/* 56 */     if (!super.equals(o)) {
/* 57 */       return false;
/*    */     }
/*    */     
/* 60 */     XMLNamespaceEnabledImageFlavor that = (XMLNamespaceEnabledImageFlavor)o;
/*    */     
/* 62 */     if ((this.namespace != null) ? !this.namespace.equals(that.namespace) : (that.namespace != null)) {
/* 63 */       return false;
/*    */     }
/*    */     
/* 66 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 71 */     int result = super.hashCode();
/* 72 */     result = 31 * result + ((this.namespace != null) ? this.namespace.hashCode() : 0);
/* 73 */     return result;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/XMLNamespaceEnabledImageFlavor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */