/*     */ package org.apache.batik.util;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.jar.JarEntry;
/*     */ import java.util.jar.JarFile;
/*     */ import java.util.zip.ZipEntry;
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
/*     */ public class ClassFileUtilities
/*     */ {
/*     */   public static final byte CONSTANT_UTF8_INFO = 1;
/*     */   public static final byte CONSTANT_INTEGER_INFO = 3;
/*     */   public static final byte CONSTANT_FLOAT_INFO = 4;
/*     */   public static final byte CONSTANT_LONG_INFO = 5;
/*     */   public static final byte CONSTANT_DOUBLE_INFO = 6;
/*     */   public static final byte CONSTANT_CLASS_INFO = 7;
/*     */   public static final byte CONSTANT_STRING_INFO = 8;
/*     */   public static final byte CONSTANT_FIELDREF_INFO = 9;
/*     */   public static final byte CONSTANT_METHODREF_INFO = 10;
/*     */   public static final byte CONSTANT_INTERFACEMETHODREF_INFO = 11;
/*     */   public static final byte CONSTANT_NAMEANDTYPE_INFO = 12;
/*     */   
/*     */   public static void main(String[] args) {
/*  80 */     boolean showFiles = false;
/*  81 */     if (args.length == 1 && args[0].equals("-f")) {
/*  82 */       showFiles = true;
/*  83 */     } else if (args.length != 0) {
/*  84 */       System.err.println("usage: org.apache.batik.util.ClassFileUtilities [-f]");
/*  85 */       System.err.println();
/*  86 */       System.err.println("  -f    list files that cause each jar file dependency");
/*  87 */       System.exit(1);
/*     */     } 
/*     */     
/*  90 */     File cwd = new File(".");
/*  91 */     File buildDir = null;
/*  92 */     String[] cwdFiles = cwd.list();
/*  93 */     for (String cwdFile : cwdFiles) {
/*  94 */       if (cwdFile.startsWith("batik-")) {
/*  95 */         buildDir = new File(cwdFile);
/*  96 */         if (!buildDir.isDirectory()) {
/*  97 */           buildDir = null;
/*     */         } else {
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 103 */     if (buildDir == null || !buildDir.isDirectory()) {
/* 104 */       System.out.println("Directory 'batik-xxx' not found in current directory!");
/*     */       
/*     */       return;
/*     */     } 
/*     */     try {
/* 109 */       Map<Object, Object> cs = new HashMap<Object, Object>();
/* 110 */       Map<Object, Object> js = new HashMap<Object, Object>();
/* 111 */       collectJars(buildDir, js, cs);
/*     */       
/* 113 */       Set<JarFile> classpath = new HashSet();
/* 114 */       Iterator<ClassFile> i = js.values().iterator();
/* 115 */       while (i.hasNext()) {
/* 116 */         classpath.add(((Jar)i.next()).jarFile);
/*     */       }
/*     */       
/* 119 */       i = cs.values().iterator();
/* 120 */       while (i.hasNext()) {
/* 121 */         ClassFile fromFile = i.next();
/*     */         
/* 123 */         Set result = getClassDependencies(fromFile.getInputStream(), classpath, false);
/*     */         
/* 125 */         for (Object aResult : result) {
/* 126 */           ClassFile toFile = (ClassFile)cs.get(aResult);
/* 127 */           if (fromFile != toFile && toFile != null) {
/* 128 */             fromFile.deps.add(toFile);
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 133 */       i = cs.values().iterator();
/* 134 */       while (i.hasNext()) {
/* 135 */         ClassFile fromFile = i.next();
/* 136 */         for (Object dep : fromFile.deps) {
/* 137 */           ClassFile toFile = (ClassFile)dep;
/* 138 */           Jar fromJar = fromFile.jar;
/* 139 */           Jar toJar = toFile.jar;
/* 140 */           if (fromFile.name.equals(toFile.name) || toJar == fromJar || fromJar.files.contains(toFile.name)) {
/*     */             continue;
/*     */           }
/*     */ 
/*     */           
/* 145 */           Integer n = (Integer)fromJar.deps.get(toJar);
/* 146 */           if (n == null) {
/* 147 */             fromJar.deps.put(toJar, Integer.valueOf(1)); continue;
/*     */           } 
/* 149 */           fromJar.deps.put(toJar, Integer.valueOf(n.intValue() + 1));
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 154 */       List<Triple> triples = new ArrayList(10);
/* 155 */       i = js.values().iterator();
/* 156 */       while (i.hasNext()) {
/* 157 */         Jar fromJar = (Jar)i.next();
/* 158 */         for (Object o : fromJar.deps.keySet()) {
/* 159 */           Jar toJar = (Jar)o;
/* 160 */           Triple t = new Triple();
/* 161 */           t.from = fromJar;
/* 162 */           t.to = toJar;
/* 163 */           t.count = ((Integer)fromJar.deps.get(toJar)).intValue();
/* 164 */           triples.add(t);
/*     */         } 
/*     */       } 
/* 167 */       Collections.sort(triples);
/*     */       
/* 169 */       i = (Iterator)triples.iterator();
/* 170 */       while (i.hasNext()) {
/* 171 */         Triple t = (Triple)i.next();
/* 172 */         System.out.println(t.count + "," + t.from.name + "," + t.to.name);
/*     */         
/* 174 */         if (showFiles) {
/* 175 */           for (Object file : t.from.files) {
/* 176 */             ClassFile fromFile = (ClassFile)file;
/* 177 */             for (Object dep : fromFile.deps) {
/* 178 */               ClassFile toFile = (ClassFile)dep;
/* 179 */               if (toFile.jar == t.to && !t.from.files.contains(toFile.name))
/*     */               {
/* 181 */                 System.out.println("\t" + fromFile.name + " --> " + toFile.name);
/*     */               }
/*     */             }
/*     */           
/*     */           }
/*     */         
/*     */         }
/*     */       } 
/* 189 */     } catch (IOException e) {
/* 190 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected static class ClassFile {
/*     */     public String name;
/* 196 */     public List deps = new ArrayList(10); public ClassFileUtilities.Jar jar;
/*     */     
/*     */     public InputStream getInputStream() throws IOException {
/* 199 */       return this.jar.jarFile.getInputStream(this.jar.jarFile.getEntry(this.name));
/*     */     }
/*     */   }
/*     */   
/*     */   protected static class Jar {
/*     */     public String name;
/*     */     public File file;
/*     */     public JarFile jarFile;
/* 207 */     public Map deps = new HashMap<Object, Object>();
/* 208 */     public Set files = new HashSet(); }
/*     */   
/*     */   protected static class Triple implements Comparable {
/*     */     public ClassFileUtilities.Jar from;
/*     */     public ClassFileUtilities.Jar to;
/*     */     public int count;
/*     */     
/*     */     public int compareTo(Object o) {
/* 216 */       return ((Triple)o).count - this.count;
/*     */     }
/*     */   }
/*     */   
/*     */   private static void collectJars(File dir, Map<String, Jar> jars, Map<String, ClassFile> classFiles) throws IOException {
/* 221 */     File[] files = dir.listFiles();
/* 222 */     for (File file : files) {
/* 223 */       String n = file.getName();
/* 224 */       if (n.endsWith(".jar") && file.isFile()) {
/* 225 */         Jar j = new Jar();
/* 226 */         j.name = file.getPath();
/* 227 */         j.file = file;
/* 228 */         j.jarFile = new JarFile(file);
/* 229 */         jars.put(j.name, j);
/*     */         
/* 231 */         Enumeration<JarEntry> entries = j.jarFile.entries();
/* 232 */         while (entries.hasMoreElements()) {
/* 233 */           ZipEntry ze = entries.nextElement();
/* 234 */           String name = ze.getName();
/* 235 */           if (name.endsWith(".class")) {
/* 236 */             ClassFile cf = new ClassFile();
/* 237 */             cf.name = name;
/* 238 */             cf.jar = j;
/* 239 */             classFiles.put(j.name + '!' + cf.name, cf);
/* 240 */             j.files.add(cf);
/*     */           } 
/*     */         } 
/* 243 */       } else if (file.isDirectory()) {
/* 244 */         collectJars(file, jars, classFiles);
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
/*     */ 
/*     */ 
/*     */   
/*     */   public static Set getClassDependencies(String path, Set classpath, boolean rec) throws IOException {
/* 261 */     return getClassDependencies(new FileInputStream(path), classpath, rec);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Set getClassDependencies(InputStream is, Set classpath, boolean rec) throws IOException {
/* 269 */     Set result = new HashSet();
/* 270 */     Set done = new HashSet();
/*     */     
/* 272 */     computeClassDependencies(is, classpath, done, result, rec);
/*     */     
/* 274 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void computeClassDependencies(InputStream is, Set classpath, Set<String> done, Set<String> result, boolean rec) throws IOException {
/* 284 */     for (Object o : getClassDependencies(is)) {
/* 285 */       String s = (String)o;
/* 286 */       if (!done.contains(s)) {
/* 287 */         done.add(s);
/*     */         
/* 289 */         for (Object aClasspath : classpath) {
/* 290 */           InputStream depis = null;
/* 291 */           String path = null;
/* 292 */           Object cpEntry = aClasspath;
/* 293 */           if (cpEntry instanceof JarFile) {
/* 294 */             JarFile jarFile = (JarFile)cpEntry;
/* 295 */             String classFileName = s + ".class";
/* 296 */             ZipEntry ze = jarFile.getEntry(classFileName);
/* 297 */             if (ze != null) {
/* 298 */               path = jarFile.getName() + '!' + classFileName;
/* 299 */               depis = jarFile.getInputStream(ze);
/*     */             } 
/*     */           } else {
/* 302 */             path = (String)cpEntry + '/' + s + ".class";
/* 303 */             File f = new File(path);
/* 304 */             if (f.isFile()) {
/* 305 */               depis = new FileInputStream(f);
/*     */             }
/*     */           } 
/*     */           
/* 309 */           if (depis != null) {
/* 310 */             result.add(path);
/*     */             
/* 312 */             if (rec) {
/* 313 */               computeClassDependencies(depis, classpath, done, result, rec);
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
/*     */   public static Set getClassDependencies(InputStream is) throws IOException {
/* 327 */     DataInputStream dis = new DataInputStream(is);
/*     */     
/* 329 */     if (dis.readInt() != -889275714) {
/* 330 */       throw new IOException("Invalid classfile");
/*     */     }
/*     */     
/* 333 */     dis.readInt();
/*     */     
/* 335 */     int len = dis.readShort();
/* 336 */     String[] strs = new String[len];
/* 337 */     Set<Integer> classes = new HashSet();
/* 338 */     Set<Integer> desc = new HashSet();
/*     */     
/* 340 */     for (int i = 1; i < len; i++) {
/* 341 */       int constCode = dis.readByte() & 0xFF;
/* 342 */       switch (constCode) {
/*     */         case 5:
/*     */         case 6:
/* 345 */           dis.readLong();
/* 346 */           i++;
/*     */           break;
/*     */         
/*     */         case 3:
/*     */         case 4:
/*     */         case 9:
/*     */         case 10:
/*     */         case 11:
/* 354 */           dis.readInt();
/*     */           break;
/*     */         
/*     */         case 7:
/* 358 */           classes.add(Integer.valueOf(dis.readShort() & 0xFFFF));
/*     */           break;
/*     */         
/*     */         case 8:
/* 362 */           dis.readShort();
/*     */           break;
/*     */         
/*     */         case 12:
/* 366 */           dis.readShort();
/* 367 */           desc.add(Integer.valueOf(dis.readShort() & 0xFFFF));
/*     */           break;
/*     */         
/*     */         case 1:
/* 371 */           strs[i] = dis.readUTF();
/*     */           break;
/*     */         
/*     */         default:
/* 375 */           throw new RuntimeException("unexpected data in constant-pool:" + constCode);
/*     */       } 
/*     */     
/*     */     } 
/* 379 */     Set<String> result = new HashSet();
/*     */     
/* 381 */     Iterator<Integer> it = classes.iterator();
/* 382 */     while (it.hasNext()) {
/* 383 */       result.add(strs[((Integer)it.next()).intValue()]);
/*     */     }
/*     */     
/* 386 */     it = desc.iterator();
/* 387 */     while (it.hasNext()) {
/* 388 */       result.addAll(getDescriptorClasses(strs[((Integer)it.next()).intValue()]));
/*     */     }
/*     */     
/* 391 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static Set getDescriptorClasses(String desc) {
/*     */     StringBuffer sb;
/* 398 */     Set<String> result = new HashSet();
/* 399 */     int i = 0;
/* 400 */     char c = desc.charAt(i);
/* 401 */     switch (c) {
/*     */       case '(':
/*     */         while (true) {
/* 404 */           StringBuffer stringBuffer; c = desc.charAt(++i);
/* 405 */           switch (c) {
/*     */             case '[':
/*     */               while (true) {
/* 408 */                 c = desc.charAt(++i);
/* 409 */                 if (c == '[' || 
/* 410 */                   c != 'L');
/*     */               } 
/*     */ 
/*     */             
/*     */             case 'L':
/* 415 */               c = desc.charAt(++i);
/* 416 */               stringBuffer = new StringBuffer();
/* 417 */               while (c != ';') {
/* 418 */                 stringBuffer.append(c);
/* 419 */                 c = desc.charAt(++i);
/*     */               } 
/* 421 */               result.add(stringBuffer.toString());
/*     */               continue;
/*     */             
/*     */             default:
/*     */               continue;
/*     */             case ')':
/*     */               break;
/*     */           } 
/*     */           break;
/*     */         } 
/* 431 */         c = desc.charAt(++i);
/* 432 */         switch (c) {
/*     */           case '[':
/*     */             while (true) {
/* 435 */               c = desc.charAt(++i);
/* 436 */               if (c != '[') {
/* 437 */                 if (c != 'L')
/*     */                   // Byte code: goto -> 399  break;
/*     */               } 
/*     */             } 
/*     */           case 'L':
/* 442 */             c = desc.charAt(++i);
/* 443 */             sb = new StringBuffer();
/* 444 */             while (c != ';') {
/* 445 */               sb.append(c);
/* 446 */               c = desc.charAt(++i);
/*     */             } 
/* 448 */             result.add(sb.toString());
/*     */             break;
/*     */         } 
/*     */ 
/*     */         
/*     */         break;
/*     */ 
/*     */       
/*     */       case '[':
/*     */         while (true) {
/* 458 */           c = desc.charAt(++i);
/* 459 */           if (c != '[') {
/* 460 */             if (c != 'L')
/*     */               // Byte code: goto -> 399  break;
/*     */           } 
/*     */         } 
/*     */       case 'L':
/* 465 */         c = desc.charAt(++i);
/* 466 */         sb = new StringBuffer();
/* 467 */         while (c != ';') {
/* 468 */           sb.append(c);
/* 469 */           c = desc.charAt(++i);
/*     */         } 
/* 471 */         result.add(sb.toString());
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 477 */     return result;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/ClassFileUtilities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */