/*     */ package org.apache.fontbox.util.autodetect;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.fontbox.util.Charsets;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WindowsFontDirFinder
/*     */   implements FontDirFinder
/*     */ {
/*     */   private String getWinDir(String osName) throws IOException {
/*     */     Process process;
/*  42 */     Runtime runtime = Runtime.getRuntime();
/*  43 */     if (osName.startsWith("Windows 9")) {
/*     */       
/*  45 */       process = runtime.exec("command.com /c echo %windir%");
/*     */     }
/*     */     else {
/*     */       
/*  49 */       process = runtime.exec("cmd.exe /c echo %windir%");
/*     */     } 
/*     */     
/*  52 */     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(), Charsets.ISO_8859_1));
/*  53 */     String winDir = bufferedReader.readLine();
/*  54 */     bufferedReader.close();
/*  55 */     return winDir;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<File> find() {
/*  66 */     List<File> fontDirList = new ArrayList<File>();
/*  67 */     String windir = null;
/*     */     
/*     */     try {
/*  70 */       windir = System.getProperty("env.windir");
/*     */     }
/*  72 */     catch (SecurityException securityException) {}
/*     */ 
/*     */ 
/*     */     
/*  76 */     String osName = System.getProperty("os.name");
/*  77 */     if (windir == null) {
/*     */       
/*     */       try {
/*     */         
/*  81 */         windir = getWinDir(osName);
/*     */       }
/*  83 */       catch (IOException iOException) {
/*     */ 
/*     */       
/*     */       }
/*  87 */       catch (SecurityException securityException) {}
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  94 */     if (windir != null) {
/*     */ 
/*     */       
/*  97 */       if (windir.endsWith("/"))
/*     */       {
/*  99 */         windir = windir.substring(0, windir.length() - 1);
/*     */       }
/* 101 */       File osFontsDir = new File(windir + File.separator + "FONTS");
/* 102 */       if (osFontsDir.exists() && osFontsDir.canRead())
/*     */       {
/* 104 */         fontDirList.add(osFontsDir);
/*     */       }
/* 106 */       File psFontsDir = new File(windir.substring(0, 2) + File.separator + "PSFONTS");
/* 107 */       if (psFontsDir.exists() && psFontsDir.canRead())
/*     */       {
/* 109 */         fontDirList.add(psFontsDir);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 114 */       String windowsDirName = osName.endsWith("NT") ? "WINNT" : "WINDOWS";
/*     */       char driveLetter;
/* 116 */       for (driveLetter = 'C'; driveLetter <= 'E'; driveLetter = (char)(driveLetter + 1)) {
/*     */         
/* 118 */         File osFontsDir = new File(driveLetter + ":" + File.separator + windowsDirName + File.separator + "FONTS");
/*     */ 
/*     */         
/*     */         try {
/* 122 */           if (osFontsDir.exists() && osFontsDir.canRead()) {
/*     */             
/* 124 */             fontDirList.add(osFontsDir);
/*     */             
/*     */             break;
/*     */           } 
/* 128 */         } catch (SecurityException securityException) {}
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 134 */       for (driveLetter = 'C'; driveLetter <= 'E'; driveLetter = (char)(driveLetter + 1)) {
/*     */         
/* 136 */         File psFontsDir = new File(driveLetter + ":" + File.separator + "PSFONTS");
/*     */         
/*     */         try {
/* 139 */           if (psFontsDir.exists() && psFontsDir.canRead()) {
/*     */             
/* 141 */             fontDirList.add(psFontsDir);
/*     */             
/*     */             break;
/*     */           } 
/* 145 */         } catch (SecurityException securityException) {}
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 151 */     return fontDirList;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/util/autodetect/WindowsFontDirFinder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */