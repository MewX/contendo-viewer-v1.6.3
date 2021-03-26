/*     */ package net.a.a.a;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import net.a.a.c;
/*     */ import net.a.a.c.c;
/*     */ import net.a.a.c.d;
/*     */ import net.a.a.d.c;
/*     */ import net.a.a.d.f;
/*     */ import net.a.a.f;
/*     */ import org.apache.tools.ant.BuildException;
/*     */ import org.apache.tools.ant.DirectoryScanner;
/*     */ import org.apache.tools.ant.taskdefs.MatchingTask;
/*     */ import org.apache.tools.ant.util.FileUtils;
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
/*     */ public class a
/*     */   extends MatchingTask
/*     */ {
/*     */   private static final String a = "./";
/*     */   private static final char b = '.';
/*     */   private File c;
/*     */   private File d;
/*     */   private File e;
/*     */   private File f;
/*  60 */   private String g = "image/png";
/*     */ 
/*     */   
/*     */   private boolean h;
/*     */ 
/*     */   
/*     */   private final f i;
/*     */   
/*     */   private final FileUtils j;
/*     */ 
/*     */   
/*     */   public a() {
/*  72 */     this
/*  73 */       .i = (f)new c(c.a());
/*  74 */     this.j = FileUtils.getFileUtils();
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
/*     */   
/*     */   public void a() {
/*  87 */     if (this.d == null) {
/*  88 */       this.d = getProject().resolveFile("./");
/*     */       
/*  90 */       log("Base is not sets, sets to " + this.d, 1);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  95 */     if (this.e != null && this.f != null) {
/*  96 */       log("Transforming file: " + this.e + " --> " + this.f, 3);
/*     */       
/*     */       try {
/*  99 */         c.a().a(this.e, this.f, this.g, (c)this.i);
/*     */       }
/* 101 */       catch (IOException iOException) {
/* 102 */         throw new BuildException(iOException);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 113 */     if (this.c == null) {
/* 114 */       throw new BuildException("m_destDir attributes must be set!");
/*     */     }
/* 116 */     DirectoryScanner directoryScanner = getDirectoryScanner(this.d);
/* 117 */     log("Transforming into " + this.c, 2);
/*     */ 
/*     */     
/* 120 */     String[] arrayOfString1 = directoryScanner.getIncludedFiles();
/* 121 */     log("Included files: " + Arrays.toString((Object[])arrayOfString1), 3);
/*     */     
/* 123 */     a(this.d, Arrays.asList(arrayOfString1), this.c);
/*     */ 
/*     */     
/* 126 */     String[] arrayOfString2 = directoryScanner.getIncludedDirectories();
/* 127 */     log("Included directories: " + Arrays.toString((Object[])arrayOfString2), 3);
/*     */     
/* 129 */     for (String str : arrayOfString2) {
/* 130 */       arrayOfString1 = this.j.resolveFile(this.d, str).list();
/* 131 */       a(this.d, Arrays.asList(arrayOfString1), this.c);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(boolean paramBoolean) {
/* 142 */     a(d.h, Boolean.valueOf(paramBoolean));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(float paramFloat) {
/* 152 */     a(d.f, Float.valueOf(paramFloat));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(String paramString) {
/* 162 */     if (k(paramString)) {
/* 163 */       log("Attribute \"backgroundcolor\" is empty, not used", 1);
/*     */     } else {
/*     */       
/* 166 */       a(d.j, paramString);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(boolean paramBoolean) {
/* 177 */     a(d.g, Boolean.valueOf(paramBoolean));
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
/*     */   public void b(String paramString) {
/* 189 */     a(d.a, paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void c(String paramString) {
/* 199 */     a(d.p, paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void d(String paramString) {
/* 209 */     a(d.o, paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(float paramFloat) {
/* 219 */     a(d.b, Float.valueOf(paramFloat));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void e(String paramString) {
/* 229 */     a(d.m, paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void f(String paramString) {
/* 239 */     a(d.k, paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void g(String paramString) {
/* 249 */     a(d.n, paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void h(String paramString) {
/* 259 */     a(d.l, paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void i(String paramString) {
/* 269 */     if (k(paramString)) {
/*     */       
/* 271 */       log("Attribute \"foregroundcolor\" is empty, use default color", 1);
/*     */     }
/*     */     else {
/*     */       
/* 275 */       a(d.i, paramString);
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
/*     */   public void c(boolean paramBoolean) {
/* 287 */     a(d.q, Boolean.valueOf(paramBoolean));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(int paramInt) {
/* 297 */     a(d.e, Integer.valueOf(paramInt));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void c(float paramFloat) {
/* 307 */     a(d.c, Float.valueOf(paramFloat));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void d(float paramFloat) {
/* 317 */     a(d.d, Float.valueOf(paramFloat));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void d(boolean paramBoolean) {
/* 327 */     a("force", Boolean.valueOf(paramBoolean));
/* 328 */     this.h = paramBoolean;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(File paramFile) {
/* 338 */     a("basedir", paramFile);
/* 339 */     this.d = paramFile;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(File paramFile) {
/* 350 */     a("destdir", paramFile);
/* 351 */     this.c = paramFile;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void c(File paramFile) {
/* 361 */     a("out", paramFile);
/* 362 */     this.f = paramFile;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void d(File paramFile) {
/* 372 */     a("in", paramFile);
/* 373 */     this.e = paramFile;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void j(String paramString) {
/* 383 */     a("type", paramString);
/* 384 */     this.g = paramString;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void a(File paramFile1, List<String> paramList, File paramFile2) {
/* 400 */     for (String str1 : paramList) {
/* 401 */       File file1 = null;
/* 402 */       File file2 = null;
/*     */       
/* 404 */       String str2 = '.' + f.a().a(this.g);
/*     */       
/* 406 */       log("Found extension: " + str2, 4);
/*     */       try {
/* 408 */         file2 = this.j.resolveFile(paramFile1, str1);
/*     */         
/* 410 */         int i = str1.lastIndexOf('.');
/*     */         
/* 412 */         if (i > 0) {
/* 413 */           file1 = this.j.resolveFile(paramFile2, str1
/* 414 */               .substring(0, i) + str2);
/*     */         } else {
/*     */           
/* 417 */           file1 = this.j.resolveFile(paramFile2, str1 + str2);
/*     */         } 
/*     */         
/* 420 */         log("Input file: " + file2, 4);
/* 421 */         log("Output file: " + file1, 4);
/* 422 */         if (this.h || 
/* 423 */           !this.j.isUpToDate(file2, file1)) {
/* 424 */           this.j.createNewFile(file1, true);
/* 425 */           c.a().a(file2, file1, this.g, (c)this.i);
/*     */         }
/*     */       
/* 428 */       } catch (IOException iOException) {
/*     */ 
/*     */         
/* 431 */         log("Failed to process " + file2, 0);
/* 432 */         FileUtils.delete(file1);
/*     */         
/* 434 */         throw new BuildException(iOException);
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
/*     */   
/*     */   private void a(d paramd, String paramString) {
/* 448 */     a(paramd, paramd.b(paramString));
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
/*     */   private void a(d paramd, Object paramObject) {
/* 460 */     a(paramd.c(), paramObject);
/* 461 */     this.i.a(paramd, paramObject);
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
/*     */ 
/*     */   
/*     */   private boolean k(String paramString) {
/* 475 */     return (paramString == null || paramString.length() == 0);
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
/*     */   private void a(String paramString, Object paramObject) {
/* 487 */     log("Sets property \"" + paramString + "\" with value: " + paramObject, 4);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/a/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */