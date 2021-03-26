/*     */ package org.apache.commons.io;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStream;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.StringTokenizer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public class FileSystemUtils
/*     */ {
/*  53 */   private static final FileSystemUtils INSTANCE = new FileSystemUtils();
/*     */ 
/*     */   
/*     */   private static final int INIT_PROBLEM = -1;
/*     */ 
/*     */   
/*     */   private static final int OTHER = 0;
/*     */   
/*     */   private static final int WINDOWS = 1;
/*     */   
/*     */   private static final int UNIX = 2;
/*     */   
/*     */   private static final int POSIX_UNIX = 3;
/*     */   
/*     */   private static final int OS;
/*     */   
/*     */   private static final String DF;
/*     */ 
/*     */   
/*     */   static {
/*  73 */     int os = 0;
/*  74 */     String dfPath = "df";
/*     */     try {
/*  76 */       String osName = System.getProperty("os.name");
/*  77 */       if (osName == null) {
/*  78 */         throw new IOException("os.name not found");
/*     */       }
/*  80 */       osName = osName.toLowerCase(Locale.ENGLISH);
/*     */       
/*  82 */       if (osName.contains("windows")) {
/*  83 */         os = 1;
/*  84 */       } else if (osName.contains("linux") || osName
/*  85 */         .contains("mpe/ix") || osName
/*  86 */         .contains("freebsd") || osName
/*  87 */         .contains("openbsd") || osName
/*  88 */         .contains("irix") || osName
/*  89 */         .contains("digital unix") || osName
/*  90 */         .contains("unix") || osName
/*  91 */         .contains("mac os x")) {
/*  92 */         os = 2;
/*  93 */       } else if (osName.contains("sun os") || osName
/*  94 */         .contains("sunos") || osName
/*  95 */         .contains("solaris")) {
/*  96 */         os = 3;
/*  97 */         dfPath = "/usr/xpg4/bin/df";
/*  98 */       } else if (osName.contains("hp-ux") || osName
/*  99 */         .contains("aix")) {
/* 100 */         os = 3;
/*     */       } else {
/* 102 */         os = 0;
/*     */       }
/*     */     
/* 105 */     } catch (Exception ex) {
/* 106 */       os = -1;
/*     */     } 
/* 108 */     OS = os;
/* 109 */     DF = dfPath;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static long freeSpace(String path) throws IOException {
/* 148 */     return INSTANCE.freeSpaceOS(path, OS, false, -1L);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static long freeSpaceKb(String path) throws IOException {
/* 179 */     return freeSpaceKb(path, -1L);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static long freeSpaceKb(String path, long timeout) throws IOException {
/* 210 */     return INSTANCE.freeSpaceOS(path, OS, true, timeout);
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
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static long freeSpaceKb() throws IOException {
/* 229 */     return freeSpaceKb(-1L);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static long freeSpaceKb(long timeout) throws IOException {
/* 250 */     return freeSpaceKb((new File(".")).getAbsolutePath(), timeout);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   long freeSpaceOS(String path, int os, boolean kb, long timeout) throws IOException {
/* 275 */     if (path == null) {
/* 276 */       throw new IllegalArgumentException("Path must not be null");
/*     */     }
/* 278 */     switch (os) {
/*     */       case 1:
/* 280 */         return kb ? (freeSpaceWindows(path, timeout) / 1024L) : freeSpaceWindows(path, timeout);
/*     */       case 2:
/* 282 */         return freeSpaceUnix(path, kb, false, timeout);
/*     */       case 3:
/* 284 */         return freeSpaceUnix(path, kb, true, timeout);
/*     */       case 0:
/* 286 */         throw new IllegalStateException("Unsupported operating system");
/*     */     } 
/* 288 */     throw new IllegalStateException("Exception caught when determining operating system");
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
/*     */   long freeSpaceWindows(String path, long timeout) throws IOException {
/* 304 */     String normPath = FilenameUtils.normalize(path, false);
/* 305 */     if (normPath == null) {
/* 306 */       throw new IllegalArgumentException(path);
/*     */     }
/* 308 */     if (normPath.length() > 0 && normPath.charAt(0) != '"') {
/* 309 */       normPath = "\"" + normPath + "\"";
/*     */     }
/*     */ 
/*     */     
/* 313 */     String[] cmdAttribs = { "cmd.exe", "/C", "dir /a /-c " + normPath };
/*     */ 
/*     */     
/* 316 */     List<String> lines = performCommand(cmdAttribs, 2147483647, timeout);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 322 */     for (int i = lines.size() - 1; i >= 0; i--) {
/* 323 */       String line = lines.get(i);
/* 324 */       if (line.length() > 0) {
/* 325 */         return parseDir(line, normPath);
/*     */       }
/*     */     } 
/*     */     
/* 329 */     throw new IOException("Command line 'dir /-c' did not return any info for path '" + normPath + "'");
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
/*     */ 
/*     */   
/*     */   long parseDir(String line, String path) throws IOException {
/* 347 */     int bytesStart = 0;
/* 348 */     int bytesEnd = 0;
/* 349 */     int j = line.length() - 1;
/* 350 */     while (j >= 0) {
/* 351 */       char c = line.charAt(j);
/* 352 */       if (Character.isDigit(c)) {
/*     */ 
/*     */         
/* 355 */         bytesEnd = j + 1;
/*     */         break;
/*     */       } 
/* 358 */       j--;
/*     */     } 
/* 360 */     while (j >= 0) {
/* 361 */       char c = line.charAt(j);
/* 362 */       if (!Character.isDigit(c) && c != ',' && c != '.') {
/*     */ 
/*     */         
/* 365 */         bytesStart = j + 1;
/*     */         break;
/*     */       } 
/* 368 */       j--;
/*     */     } 
/* 370 */     if (j < 0) {
/* 371 */       throw new IOException("Command line 'dir /-c' did not return valid info for path '" + path + "'");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 377 */     StringBuilder buf = new StringBuilder(line.substring(bytesStart, bytesEnd));
/* 378 */     for (int k = 0; k < buf.length(); k++) {
/* 379 */       if (buf.charAt(k) == ',' || buf.charAt(k) == '.') {
/* 380 */         buf.deleteCharAt(k--);
/*     */       }
/*     */     } 
/* 383 */     return parseBytes(buf.toString(), path);
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
/*     */   
/*     */   long freeSpaceUnix(String path, boolean kb, boolean posix, long timeout) throws IOException {
/* 400 */     if (path.isEmpty()) {
/* 401 */       throw new IllegalArgumentException("Path must not be empty");
/*     */     }
/*     */ 
/*     */     
/* 405 */     String flags = "-";
/* 406 */     if (kb) {
/* 407 */       flags = flags + "k";
/*     */     }
/* 409 */     if (posix) {
/* 410 */       flags = flags + "P";
/*     */     }
/*     */     
/* 413 */     (new String[3])[0] = DF; (new String[3])[1] = flags; (new String[3])[2] = path; (new String[2])[0] = DF; (new String[2])[1] = path; String[] cmdAttribs = (flags.length() > 1) ? new String[3] : new String[2];
/*     */ 
/*     */     
/* 416 */     List<String> lines = performCommand(cmdAttribs, 3, timeout);
/* 417 */     if (lines.size() < 2)
/*     */     {
/* 419 */       throw new IOException("Command line '" + DF + "' did not return info as expected for path '" + path + "'- response was " + lines);
/*     */     }
/*     */ 
/*     */     
/* 423 */     String line2 = lines.get(1);
/*     */ 
/*     */     
/* 426 */     StringTokenizer tok = new StringTokenizer(line2, " ");
/* 427 */     if (tok.countTokens() < 4) {
/*     */       
/* 429 */       if (tok.countTokens() == 1 && lines.size() >= 3) {
/* 430 */         String line3 = lines.get(2);
/* 431 */         tok = new StringTokenizer(line3, " ");
/*     */       } else {
/* 433 */         throw new IOException("Command line '" + DF + "' did not return data as expected for path '" + path + "'- check path is valid");
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 438 */       tok.nextToken();
/*     */     } 
/* 440 */     tok.nextToken();
/* 441 */     tok.nextToken();
/* 442 */     String freeSpace = tok.nextToken();
/* 443 */     return parseBytes(freeSpace, path);
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
/*     */   long parseBytes(String freeSpace, String path) throws IOException {
/*     */     try {
/* 457 */       long bytes = Long.parseLong(freeSpace);
/* 458 */       if (bytes < 0L) {
/* 459 */         throw new IOException("Command line '" + DF + "' did not find free space in response for path '" + path + "'- check path is valid");
/*     */       }
/*     */ 
/*     */       
/* 463 */       return bytes;
/*     */     }
/* 465 */     catch (NumberFormatException ex) {
/* 466 */       throw new IOException("Command line '" + DF + "' did not return numeric data as expected for path '" + path + "'- check path is valid", ex);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   List<String> performCommand(String[] cmdAttribs, int max, long timeout) throws IOException {
/* 492 */     List<String> lines = new ArrayList<>(20);
/* 493 */     Process proc = null;
/* 494 */     InputStream in = null;
/* 495 */     OutputStream out = null;
/* 496 */     InputStream err = null;
/* 497 */     BufferedReader inr = null;
/*     */     
/*     */     try {
/* 500 */       Thread monitor = ThreadMonitor.start(timeout);
/*     */       
/* 502 */       proc = openProcess(cmdAttribs);
/* 503 */       in = proc.getInputStream();
/* 504 */       out = proc.getOutputStream();
/* 505 */       err = proc.getErrorStream();
/*     */       
/* 507 */       inr = new BufferedReader(new InputStreamReader(in, Charset.defaultCharset()));
/* 508 */       String line = inr.readLine();
/* 509 */       while (line != null && lines.size() < max) {
/* 510 */         line = line.toLowerCase(Locale.ENGLISH).trim();
/* 511 */         lines.add(line);
/* 512 */         line = inr.readLine();
/*     */       } 
/*     */       
/* 515 */       proc.waitFor();
/*     */       
/* 517 */       ThreadMonitor.stop(monitor);
/*     */       
/* 519 */       if (proc.exitValue() != 0)
/*     */       {
/* 521 */         throw new IOException("Command line returned OS error code '" + proc
/* 522 */             .exitValue() + "' for command " + 
/* 523 */             Arrays.asList(cmdAttribs));
/*     */       }
/* 525 */       if (lines.isEmpty())
/*     */       {
/* 527 */         throw new IOException("Command line did not return any info for command " + 
/*     */             
/* 529 */             Arrays.asList(cmdAttribs));
/*     */       }
/*     */       
/* 532 */       inr.close();
/* 533 */       inr = null;
/*     */       
/* 535 */       in.close();
/* 536 */       in = null;
/*     */       
/* 538 */       if (out != null) {
/* 539 */         out.close();
/* 540 */         out = null;
/*     */       } 
/*     */       
/* 543 */       if (err != null) {
/* 544 */         err.close();
/* 545 */         err = null;
/*     */       } 
/*     */       
/* 548 */       return lines;
/*     */     }
/* 550 */     catch (InterruptedException ex) {
/* 551 */       throw new IOException("Command line threw an InterruptedException for command " + 
/*     */           
/* 553 */           Arrays.asList(cmdAttribs) + " timeout=" + timeout, ex);
/*     */     } finally {
/* 555 */       IOUtils.closeQuietly(in);
/* 556 */       IOUtils.closeQuietly(out);
/* 557 */       IOUtils.closeQuietly(err);
/* 558 */       IOUtils.closeQuietly(inr);
/* 559 */       if (proc != null) {
/* 560 */         proc.destroy();
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
/*     */   Process openProcess(String[] cmdAttribs) throws IOException {
/* 573 */     return Runtime.getRuntime().exec(cmdAttribs);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/FileSystemUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */