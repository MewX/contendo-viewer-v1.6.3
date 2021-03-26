/*     */ package org.apache.fontbox.util.autodetect;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.net.URI;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
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
/*     */ public class FontFileFinder
/*     */ {
/*  33 */   private static final Log LOG = LogFactory.getLog(FontFileFinder.class);
/*     */   
/*  35 */   private FontDirFinder fontDirFinder = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private FontDirFinder determineDirFinder() {
/*  46 */     String osName = System.getProperty("os.name");
/*  47 */     if (osName.startsWith("Windows"))
/*     */     {
/*  49 */       return new WindowsFontDirFinder();
/*     */     }
/*  51 */     if (osName.startsWith("Mac"))
/*     */     {
/*  53 */       return new MacFontDirFinder();
/*     */     }
/*  55 */     if (osName.startsWith("OS/400"))
/*     */     {
/*  57 */       return new OS400FontDirFinder();
/*     */     }
/*     */ 
/*     */     
/*  61 */     return new UnixFontDirFinder();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<URI> find() {
/*  72 */     if (this.fontDirFinder == null)
/*     */     {
/*  74 */       this.fontDirFinder = determineDirFinder();
/*     */     }
/*  76 */     List<File> fontDirs = this.fontDirFinder.find();
/*  77 */     List<URI> results = new ArrayList<URI>();
/*  78 */     for (File dir : fontDirs)
/*     */     {
/*  80 */       walk(dir, results);
/*     */     }
/*  82 */     return results;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<URI> find(String dir) {
/*  93 */     List<URI> results = new ArrayList<URI>();
/*  94 */     File directory = new File(dir);
/*  95 */     if (directory.isDirectory())
/*     */     {
/*  97 */       walk(directory, results);
/*     */     }
/*  99 */     return results;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void walk(File directory, List<URI> results) {
/* 111 */     if (directory.isDirectory()) {
/*     */       
/* 113 */       File[] filelist = directory.listFiles();
/* 114 */       if (filelist != null)
/*     */       {
/* 116 */         for (File file : filelist) {
/*     */           
/* 118 */           if (file.isDirectory()) {
/*     */ 
/*     */             
/* 121 */             if (!file.getName().startsWith("."))
/*     */             {
/*     */ 
/*     */               
/* 125 */               walk(file, results);
/*     */             }
/*     */           } else {
/*     */             
/* 129 */             if (LOG.isDebugEnabled())
/*     */             {
/* 131 */               LOG.debug("checkFontfile check " + file);
/*     */             }
/* 133 */             if (checkFontfile(file)) {
/*     */               
/* 135 */               if (LOG.isDebugEnabled())
/*     */               {
/* 137 */                 LOG.debug("checkFontfile found " + file);
/*     */               }
/* 139 */               results.add(file.toURI());
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean checkFontfile(File file) {
/* 155 */     String name = file.getName().toLowerCase(Locale.US);
/* 156 */     return ((name.endsWith(".ttf") || name.endsWith(".otf") || name.endsWith(".pfb") || name.endsWith(".ttc")) && 
/*     */       
/* 158 */       !name.startsWith("fonts."));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/util/autodetect/FontFileFinder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */