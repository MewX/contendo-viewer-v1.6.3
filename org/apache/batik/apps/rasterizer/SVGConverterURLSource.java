/*     */ package org.apache.batik.apps.rasterizer;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import org.apache.batik.util.ParsedURL;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGConverterURLSource
/*     */   implements SVGConverterSource
/*     */ {
/*     */   protected static final String SVG_EXTENSION = ".svg";
/*     */   protected static final String SVGZ_EXTENSION = ".svgz";
/*     */   public static final String ERROR_INVALID_URL = "SVGConverterURLSource.error.invalid.url";
/*     */   ParsedURL purl;
/*     */   String name;
/*     */   
/*     */   public SVGConverterURLSource(String url) throws SVGConverterException {
/*  52 */     this.purl = new ParsedURL(url);
/*     */ 
/*     */     
/*  55 */     String path = this.purl.getPath();
/*  56 */     int n = path.lastIndexOf('/');
/*  57 */     String file = path;
/*  58 */     if (n != -1)
/*     */     {
/*     */       
/*  61 */       file = path.substring(n + 1);
/*     */     }
/*  63 */     if (file.length() == 0) {
/*  64 */       int idx = path.lastIndexOf('/', n - 1);
/*  65 */       file = path.substring(idx + 1, n);
/*     */     } 
/*  67 */     if (file.length() == 0) {
/*  68 */       throw new SVGConverterException("SVGConverterURLSource.error.invalid.url", new Object[] { url });
/*     */     }
/*     */     
/*  71 */     n = file.indexOf('?');
/*  72 */     String args = "";
/*  73 */     if (n != -1) {
/*  74 */       args = file.substring(n + 1);
/*  75 */       file = file.substring(0, n);
/*     */     } 
/*     */     
/*  78 */     this.name = file;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  84 */     String ref = this.purl.getRef();
/*  85 */     if (ref != null && ref.length() != 0) {
/*  86 */       this.name += "_" + ref.hashCode();
/*     */     }
/*  88 */     if (args != null && args.length() != 0) {
/*  89 */       this.name += "_" + args.hashCode();
/*     */     }
/*     */   }
/*     */   
/*     */   public String toString() {
/*  94 */     return this.purl.toString();
/*     */   }
/*     */   
/*     */   public String getURI() {
/*  98 */     return toString();
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 102 */     if (o == null || !(o instanceof SVGConverterURLSource)) {
/* 103 */       return false;
/*     */     }
/*     */     
/* 106 */     return this.purl.equals(((SVGConverterURLSource)o).purl);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 110 */     return this.purl.hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public InputStream openStream() throws IOException {
/* 115 */     return this.purl.openStream();
/*     */   }
/*     */   
/*     */   public boolean isSameAs(String srcStr) {
/* 119 */     return toString().equals(srcStr);
/*     */   }
/*     */   
/*     */   public boolean isReadable() {
/* 123 */     return true;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 127 */     return this.name;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/rasterizer/SVGConverterURLSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */