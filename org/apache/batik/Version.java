/*     */ package org.apache.batik;
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
/*     */ public final class Version
/*     */ {
/*     */   public static String getVersion() {
/*  70 */     Package pkg = Version.class.getPackage();
/*  71 */     String version = null;
/*  72 */     if (pkg != null) {
/*  73 */       version = pkg.getImplementationVersion();
/*     */     }
/*  75 */     String headURL = "$HeadURL: https://svn.apache.org/repos/asf/xmlgraphics/batik/branches/maven/batik-util/src/main/java/org/apache/batik/Version.java $";
/*     */     
/*  77 */     String prefix = "$HeadURL: ";
/*  78 */     String suffix = "/sources/org/apache/batik/Version.java $";
/*  79 */     if (headURL.startsWith(prefix) && headURL.endsWith(suffix)) {
/*  80 */       headURL = headURL.substring(prefix.length(), headURL.length() - suffix.length());
/*     */       
/*  82 */       if (!headURL.endsWith("/trunk")) {
/*  83 */         int index1 = headURL.lastIndexOf('/');
/*  84 */         int index2 = headURL.lastIndexOf('/', index1 - 1);
/*  85 */         String name = headURL.substring(index1 + 1);
/*  86 */         String type = headURL.substring(index2 + 1, index1);
/*  87 */         String tagPrefix = "batik-";
/*  88 */         if (type.equals("tags") && name.startsWith(tagPrefix)) {
/*     */           
/*  90 */           version = name.substring(tagPrefix.length()).replace('_', '.');
/*     */         }
/*  92 */         else if (type.equals("branches")) {
/*     */           
/*  94 */           version = version + "; " + name;
/*     */         } 
/*     */       } 
/*     */     } 
/*  98 */     if (version == null) {
/*  99 */       version = "development version";
/*     */     }
/*     */     
/* 102 */     return version;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/Version.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */