/*    */ package org.apache.batik.apps.rasterizer;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.InputStream;
/*    */ import java.net.MalformedURLException;
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
/*    */ public class SVGConverterFileSource
/*    */   implements SVGConverterSource
/*    */ {
/*    */   File file;
/*    */   String ref;
/*    */   
/*    */   public SVGConverterFileSource(File file) {
/* 38 */     this.file = file;
/*    */   }
/*    */   
/*    */   public SVGConverterFileSource(File file, String ref) {
/* 42 */     this.file = file;
/* 43 */     this.ref = ref;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 47 */     String name = this.file.getName();
/* 48 */     if (this.ref != null && !"".equals(this.ref)) {
/* 49 */       name = name + '#' + this.ref;
/*    */     }
/* 51 */     return name;
/*    */   }
/*    */   
/*    */   public File getFile() {
/* 55 */     return this.file;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 59 */     return getName();
/*    */   }
/*    */   
/*    */   public String getURI() {
/*    */     try {
/* 64 */       String uri = this.file.toURI().toURL().toString();
/* 65 */       if (this.ref != null && !"".equals(this.ref)) {
/* 66 */         uri = uri + '#' + this.ref;
/*    */       }
/* 68 */       return uri;
/* 69 */     } catch (MalformedURLException e) {
/* 70 */       throw new RuntimeException(e.getMessage());
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean equals(Object o) {
/* 75 */     if (o == null || !(o instanceof SVGConverterFileSource)) {
/* 76 */       return false;
/*    */     }
/*    */     
/* 79 */     return this.file.equals(((SVGConverterFileSource)o).file);
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 83 */     return this.file.hashCode();
/*    */   }
/*    */   
/*    */   public InputStream openStream() throws FileNotFoundException {
/* 87 */     return new FileInputStream(this.file);
/*    */   }
/*    */   
/*    */   public boolean isSameAs(String srcStr) {
/* 91 */     if (this.file.toString().equals(srcStr)) {
/* 92 */       return true;
/*    */     }
/*    */     
/* 95 */     return false;
/*    */   }
/*    */   
/*    */   public boolean isReadable() {
/* 99 */     return this.file.canRead();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/rasterizer/SVGConverterFileSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */