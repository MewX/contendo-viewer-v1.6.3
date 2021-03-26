/*     */ package org.apache.bcel.util;
/*     */ 
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JavaWrapper
/*     */ {
/*     */   private java.lang.ClassLoader loader;
/*     */   
/*     */   private static java.lang.ClassLoader getClassLoader() {
/*  79 */     String s = System.getProperty("bcel.classloader");
/*     */     
/*  81 */     if (s == null || "".equals(s)) {
/*  82 */       s = "org.apache.bcel.util.ClassLoader";
/*     */     }
/*     */     try {
/*  85 */       return (java.lang.ClassLoader)Class.forName(s).newInstance();
/*     */     } catch (Exception e) {
/*  87 */       throw new RuntimeException(e.toString());
/*     */     } 
/*     */   }
/*     */   
/*     */   public JavaWrapper(java.lang.ClassLoader loader) {
/*  92 */     this.loader = loader;
/*     */   }
/*     */   
/*     */   public JavaWrapper() {
/*  96 */     this(getClassLoader());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void runMain(String class_name, String[] argv) throws ClassNotFoundException {
/* 106 */     Class cl = this.loader.loadClass(class_name);
/* 107 */     Method method = null;
/*     */     
/*     */     try {
/* 110 */       method = cl.getMethod("main", new Class[] { argv.getClass() });
/*     */ 
/*     */ 
/*     */       
/* 114 */       int m = method.getModifiers();
/* 115 */       Class r = method.getReturnType();
/*     */       
/* 117 */       if (!Modifier.isPublic(m) || !Modifier.isStatic(m) || Modifier.isAbstract(m) || r != void.class)
/*     */       {
/* 119 */         throw new NoSuchMethodException(); } 
/*     */     } catch (NoSuchMethodException no) {
/* 121 */       System.out.println("In class " + class_name + ": public static void main(String[] argv) is not defined");
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*     */     try {
/* 127 */       method.invoke(null, new Object[] { argv });
/*     */     } catch (Exception ex) {
/* 129 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] argv) throws Exception {
/* 139 */     if (argv.length == 0) {
/* 140 */       System.out.println("Missing class name.");
/*     */       
/*     */       return;
/*     */     } 
/* 144 */     String class_name = argv[0];
/* 145 */     String[] new_argv = new String[argv.length - 1];
/* 146 */     System.arraycopy(argv, 1, new_argv, 0, new_argv.length);
/*     */     
/* 148 */     JavaWrapper wrapper = new JavaWrapper();
/* 149 */     wrapper.runMain(class_name, new_argv);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/util/JavaWrapper.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */