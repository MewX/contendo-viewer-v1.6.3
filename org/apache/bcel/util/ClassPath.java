/*     */ package org.apache.bcel.util;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FilenameFilter;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.zip.ZipEntry;
/*     */ import java.util.zip.ZipFile;
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
/*     */ public class ClassPath
/*     */ {
/*     */   private PathEntry[] paths;
/*     */   
/*     */   public ClassPath(String class_path) {
/*  75 */     ArrayList vec = new ArrayList();
/*     */     
/*  77 */     StringTokenizer tok = new StringTokenizer(class_path, System.getProperty("path.separator"));
/*     */     
/*  79 */     while (tok.hasMoreTokens()) {
/*     */       
/*  81 */       String path = tok.nextToken();
/*     */       
/*  83 */       if (!path.equals("")) {
/*  84 */         File file = new File(path);
/*     */         
/*     */         try {
/*  87 */           if (file.exists()) {
/*  88 */             if (file.isDirectory()) {
/*  89 */               vec.add(new Dir(path)); continue;
/*     */             } 
/*  91 */             vec.add(new Zip(new ZipFile(file)));
/*     */           } 
/*     */         } catch (IOException e) {
/*  94 */           System.err.println("CLASSPATH component " + file + ": " + e);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  99 */     this.paths = new PathEntry[vec.size()];
/* 100 */     vec.toArray(this.paths);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ClassPath() {
/* 107 */     this(getClassPath());
/*     */   }
/*     */   
/*     */   private static final void getPathComponents(String path, ArrayList list) {
/* 111 */     if (path != null) {
/* 112 */       StringTokenizer tok = new StringTokenizer(path, File.pathSeparator);
/*     */       
/* 114 */       while (tok.hasMoreTokens()) {
/* 115 */         String name = tok.nextToken();
/* 116 */         File file = new File(name);
/*     */         
/* 118 */         if (file.exists()) {
/* 119 */           list.add(name);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String getClassPath() {
/* 130 */     String class_path = System.getProperty("java.class.path");
/* 131 */     String boot_path = System.getProperty("sun.boot.class.path");
/* 132 */     String ext_path = System.getProperty("java.ext.dirs");
/*     */     
/* 134 */     ArrayList list = new ArrayList();
/*     */     
/* 136 */     getPathComponents(class_path, list);
/* 137 */     getPathComponents(boot_path, list);
/*     */     
/* 139 */     ArrayList dirs = new ArrayList();
/* 140 */     getPathComponents(ext_path, dirs);
/*     */     
/* 142 */     for (Iterator e = dirs.iterator(); e.hasNext(); ) {
/* 143 */       File ext_dir = new File(e.next());
/* 144 */       String[] extensions = ext_dir.list(new FilenameFilter() {
/*     */             public boolean accept(File dir, String name) {
/* 146 */               name = name.toLowerCase();
/* 147 */               return (name.endsWith(".zip") || name.endsWith(".jar"));
/*     */             }
/*     */           });
/*     */       
/* 151 */       if (extensions != null)
/* 152 */         for (int i = 0; i < extensions.length; i++) {
/* 153 */           list.add(ext_path + File.separatorChar + extensions[i]);
/*     */         } 
/*     */     } 
/* 156 */     StringBuffer buf = new StringBuffer();
/*     */     
/* 158 */     for (Iterator iterator1 = list.iterator(); iterator1.hasNext(); ) {
/* 159 */       buf.append(iterator1.next());
/*     */       
/* 161 */       if (iterator1.hasNext()) {
/* 162 */         buf.append(File.pathSeparatorChar);
/*     */       }
/*     */     } 
/* 165 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream getInputStream(String name) throws IOException {
/* 173 */     return getInputStream(name, ".class");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream getInputStream(String name, String suffix) throws IOException {
/* 184 */     InputStream is = null;
/*     */     
/*     */     try {
/* 187 */       is = getClass().getClassLoader().getResourceAsStream(name + suffix);
/* 188 */     } catch (Exception e) {}
/*     */     
/* 190 */     if (is != null) {
/* 191 */       return is;
/*     */     }
/* 193 */     return getClassFile(name, suffix).getInputStream();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ClassFile getClassFile(String name, String suffix) throws IOException {
/* 202 */     for (int i = 0; i < this.paths.length; i++) {
/*     */       ClassFile cf;
/*     */       
/* 205 */       if ((cf = this.paths[i].getClassFile(name, suffix)) != null) {
/* 206 */         return cf;
/*     */       }
/*     */     } 
/* 209 */     throw new IOException("Couldn't find: " + name + suffix);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ClassFile getClassFile(String name) throws IOException {
/* 217 */     return getClassFile(name, ".class");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getBytes(String name, String suffix) throws IOException {
/* 226 */     InputStream is = getInputStream(name, suffix);
/*     */     
/* 228 */     if (is == null) {
/* 229 */       throw new IOException("Couldn't find: " + name + suffix);
/*     */     }
/* 231 */     DataInputStream dis = new DataInputStream(is);
/* 232 */     byte[] bytes = new byte[is.available()];
/* 233 */     dis.readFully(bytes);
/* 234 */     dis.close(); is.close();
/*     */     
/* 236 */     return bytes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getBytes(String name) throws IOException {
/* 243 */     return getBytes(name, ".class");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPath(String name) throws IOException {
/* 251 */     int index = name.lastIndexOf('.');
/* 252 */     String suffix = "";
/*     */     
/* 254 */     if (index > 0) {
/* 255 */       suffix = name.substring(index);
/* 256 */       name = name.substring(0, index);
/*     */     } 
/*     */     
/* 259 */     return getPath(name, suffix);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPath(String name, String suffix) throws IOException {
/* 268 */     return getClassFile(name, suffix).getPath();
/*     */   }
/*     */ 
/*     */   
/*     */   private static abstract class PathEntry
/*     */   {
/*     */     private PathEntry() {}
/*     */ 
/*     */     
/*     */     abstract ClassPath.ClassFile getClassFile(String param1String1, String param1String2) throws IOException;
/*     */   }
/*     */ 
/*     */   
/*     */   public static abstract class ClassFile
/*     */   {
/*     */     public abstract InputStream getInputStream() throws IOException;
/*     */     
/*     */     public abstract String getPath();
/*     */     
/*     */     public abstract long getTime();
/*     */     
/*     */     public abstract long getSize();
/*     */   }
/*     */   
/*     */   private static class Dir
/*     */     extends PathEntry
/*     */   {
/*     */     private String dir;
/*     */     
/*     */     Dir(String d) {
/* 298 */       this.dir = d;
/*     */     }
/*     */     ClassPath.ClassFile getClassFile(String name, String suffix) throws IOException {
/* 301 */       File file = new File(this.dir + File.separatorChar + name.replace('.', File.separatorChar) + suffix);
/*     */ 
/*     */       
/* 304 */       return file.exists() ? (ClassPath.ClassFile)new Object(this, file) : null;
/*     */     }
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
/*     */     public String toString() {
/* 317 */       return this.dir;
/*     */     }
/*     */   }
/*     */   
/*     */   private static class Zip extends PathEntry {
/*     */     Zip(ZipFile z) {
/* 323 */       this.zip = z;
/*     */     }
/*     */     ClassPath.ClassFile getClassFile(String name, String suffix) throws IOException {
/* 326 */       ZipEntry entry = this.zip.getEntry(name.replace('.', '/') + suffix);
/*     */       
/* 328 */       return (entry != null) ? (ClassPath.ClassFile)new Object(this, entry) : null;
/*     */     }
/*     */     
/*     */     private ZipFile zip;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/util/ClassPath.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */