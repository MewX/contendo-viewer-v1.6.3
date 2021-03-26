/*     */ package org.apache.batik.apps.svgbrowser;
/*     */ 
/*     */ import java.io.File;
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
/*     */ public class SVGInputHandler
/*     */   implements SquiggleInputHandler
/*     */ {
/*  33 */   public static final String[] SVG_MIME_TYPES = new String[] { "image/svg+xml" };
/*     */ 
/*     */   
/*  36 */   public static final String[] SVG_FILE_EXTENSIONS = new String[] { ".svg", ".svgz" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getHandledMimeTypes() {
/*  43 */     return SVG_MIME_TYPES;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getHandledExtensions() {
/*  50 */     return SVG_FILE_EXTENSIONS;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescription() {
/*  57 */     return "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handle(ParsedURL purl, JSVGViewerFrame svgViewerFrame) {
/*  64 */     svgViewerFrame.getJSVGCanvas().loadSVGDocument(purl.toString());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean accept(File f) {
/*  71 */     return (f != null && f.isFile() && accept(f.getPath()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean accept(ParsedURL purl) {
/*  81 */     if (purl == null) {
/*  82 */       return false;
/*     */     }
/*     */     
/*  85 */     String path = purl.getPath();
/*  86 */     if (path == null) return false;
/*     */     
/*  88 */     return accept(path);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean accept(String path) {
/*  95 */     if (path == null) return false; 
/*  96 */     for (String SVG_FILE_EXTENSION : SVG_FILE_EXTENSIONS) {
/*  97 */       if (path.endsWith(SVG_FILE_EXTENSION)) {
/*  98 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 102 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/svgbrowser/SVGInputHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */