/*     */ package org.apache.xalan.xsltc.cmdline;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.net.URL;
/*     */ import java.util.Vector;
/*     */ import org.apache.xalan.xsltc.cmdline.getopt.GetOpt;
/*     */ import org.apache.xalan.xsltc.cmdline.getopt.GetOptsException;
/*     */ import org.apache.xalan.xsltc.compiler.XSLTC;
/*     */ import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Compile
/*     */ {
/*  40 */   private static int VERSION_MAJOR = 1;
/*  41 */   private static int VERSION_MINOR = 4;
/*  42 */   private static int VERSION_DELTA = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean _allowExit = true;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void printUsage() {
/*  52 */     StringBuffer vers = new StringBuffer("XSLTC version " + VERSION_MAJOR + "." + VERSION_MINOR + ((VERSION_DELTA > 0) ? ("." + VERSION_DELTA) : ""));
/*     */ 
/*     */     
/*  55 */     System.err.println(vers + "\n" + new ErrorMsg("COMPILE_USAGE_STR"));
/*     */     
/*  57 */     if (_allowExit) System.exit(-1);
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/*     */     
/*  69 */     try { boolean bool1, inputIsURL = false;
/*  70 */       boolean useStdIn = false;
/*  71 */       boolean classNameSet = false;
/*  72 */       GetOpt getopt = new GetOpt(args, "o:d:j:p:uxhsinv");
/*  73 */       if (args.length < 1) printUsage();
/*     */       
/*  75 */       XSLTC xsltc = new XSLTC();
/*  76 */       xsltc.init();
/*     */       
/*     */       int c;
/*  79 */       while ((c = getopt.getNextOption()) != -1) {
/*  80 */         switch (c) {
/*     */           case 105:
/*  82 */             useStdIn = true;
/*     */             continue;
/*     */           case 111:
/*  85 */             xsltc.setClassName(getopt.getOptionArg());
/*  86 */             classNameSet = true;
/*     */             continue;
/*     */           case 100:
/*  89 */             xsltc.setDestDirectory(getopt.getOptionArg());
/*     */             continue;
/*     */           case 112:
/*  92 */             xsltc.setPackageName(getopt.getOptionArg());
/*     */             continue;
/*     */           case 106:
/*  95 */             xsltc.setJarFileName(getopt.getOptionArg());
/*     */             continue;
/*     */           case 120:
/*  98 */             xsltc.setDebug(true);
/*     */             continue;
/*     */           case 117:
/* 101 */             inputIsURL = true;
/*     */             continue;
/*     */           case 115:
/* 104 */             _allowExit = false;
/*     */             continue;
/*     */           case 110:
/* 107 */             xsltc.setTemplateInlining(true);
/*     */             continue;
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 113 */         printUsage();
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 120 */       if (useStdIn) {
/* 121 */         if (!classNameSet) {
/* 122 */           System.err.println(new ErrorMsg("COMPILE_STDIN_ERR"));
/* 123 */           if (_allowExit) System.exit(-1); 
/*     */         } 
/* 125 */         bool1 = xsltc.compile(System.in, xsltc.getClassName());
/*     */       }
/*     */       else {
/*     */         
/* 129 */         String[] stylesheetNames = getopt.getCmdArgs();
/* 130 */         Vector stylesheetVector = new Vector();
/* 131 */         for (int i = 0; i < stylesheetNames.length; i++) {
/* 132 */           URL uRL; String name = stylesheetNames[i];
/*     */           
/* 134 */           if (inputIsURL) {
/* 135 */             uRL = new URL(name);
/*     */           } else {
/* 137 */             uRL = (new File(name)).toURL();
/* 138 */           }  stylesheetVector.addElement(uRL);
/*     */         } 
/* 140 */         bool1 = xsltc.compile(stylesheetVector);
/*     */       } 
/*     */ 
/*     */       
/* 144 */       if (bool1)
/* 145 */       { xsltc.printWarnings();
/* 146 */         if (xsltc.getJarFileName() != null) xsltc.outputToJar(); 
/* 147 */         if (_allowExit) System.exit(0);
/*     */          }
/*     */       else
/* 150 */       { xsltc.printWarnings();
/* 151 */         xsltc.printErrors();
/* 152 */         if (_allowExit) System.exit(-1);  }  } catch (GetOptsException ex)
/*     */     
/*     */     { 
/*     */       
/* 156 */       System.err.println(ex);
/* 157 */       printUsage(); } catch (Exception e)
/*     */     
/*     */     { 
/* 160 */       e.printStackTrace();
/* 161 */       if (_allowExit) System.exit(-1);  }
/*     */   
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/cmdline/Compile.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */