/*      */ package org.apache.commons.io;
/*      */ 
/*      */ import java.io.BufferedOutputStream;
/*      */ import java.io.File;
/*      */ import java.io.FileFilter;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.InputStreamReader;
/*      */ import java.io.OutputStream;
/*      */ import java.io.Reader;
/*      */ import java.math.BigInteger;
/*      */ import java.net.URL;
/*      */ import java.net.URLConnection;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.channels.FileChannel;
/*      */ import java.nio.charset.Charset;
/*      */ import java.nio.charset.StandardCharsets;
/*      */ import java.nio.file.Files;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Date;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.zip.CRC32;
/*      */ import java.util.zip.CheckedInputStream;
/*      */ import java.util.zip.Checksum;
/*      */ import org.apache.commons.io.filefilter.DirectoryFileFilter;
/*      */ import org.apache.commons.io.filefilter.FalseFileFilter;
/*      */ import org.apache.commons.io.filefilter.FileFilterUtils;
/*      */ import org.apache.commons.io.filefilter.IOFileFilter;
/*      */ import org.apache.commons.io.filefilter.SuffixFileFilter;
/*      */ import org.apache.commons.io.filefilter.TrueFileFilter;
/*      */ import org.apache.commons.io.output.NullOutputStream;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class FileUtils
/*      */ {
/*      */   public static final long ONE_KB = 1024L;
/*   98 */   public static final BigInteger ONE_KB_BI = BigInteger.valueOf(1024L);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final long ONE_MB = 1048576L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  110 */   public static final BigInteger ONE_MB_BI = ONE_KB_BI.multiply(ONE_KB_BI);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final long FILE_COPY_BUFFER_SIZE = 31457280L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final long ONE_GB = 1073741824L;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  127 */   public static final BigInteger ONE_GB_BI = ONE_KB_BI.multiply(ONE_MB_BI);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final long ONE_TB = 1099511627776L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  139 */   public static final BigInteger ONE_TB_BI = ONE_KB_BI.multiply(ONE_GB_BI);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final long ONE_PB = 1125899906842624L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  151 */   public static final BigInteger ONE_PB_BI = ONE_KB_BI.multiply(ONE_TB_BI);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final long ONE_EB = 1152921504606846976L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  163 */   public static final BigInteger ONE_EB_BI = ONE_KB_BI.multiply(ONE_PB_BI);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  168 */   public static final BigInteger ONE_ZB = BigInteger.valueOf(1024L).multiply(BigInteger.valueOf(1152921504606846976L));
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  173 */   public static final BigInteger ONE_YB = ONE_KB_BI.multiply(ONE_ZB);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  178 */   public static final File[] EMPTY_FILE_ARRAY = new File[0];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static File getFile(File directory, String... names) {
/*  190 */     if (directory == null) {
/*  191 */       throw new NullPointerException("directory must not be null");
/*      */     }
/*  193 */     if (names == null) {
/*  194 */       throw new NullPointerException("names must not be null");
/*      */     }
/*  196 */     File file = directory;
/*  197 */     for (String name : names) {
/*  198 */       file = new File(file, name);
/*      */     }
/*  200 */     return file;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static File getFile(String... names) {
/*  211 */     if (names == null) {
/*  212 */       throw new NullPointerException("names must not be null");
/*      */     }
/*  214 */     File file = null;
/*  215 */     for (String name : names) {
/*  216 */       if (file == null) {
/*  217 */         file = new File(name);
/*      */       } else {
/*  219 */         file = new File(file, name);
/*      */       } 
/*      */     } 
/*  222 */     return file;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getTempDirectoryPath() {
/*  233 */     return System.getProperty("java.io.tmpdir");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static File getTempDirectory() {
/*  244 */     return new File(getTempDirectoryPath());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getUserDirectoryPath() {
/*  255 */     return System.getProperty("user.home");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static File getUserDirectory() {
/*  266 */     return new File(getUserDirectoryPath());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static FileInputStream openInputStream(File file) throws IOException {
/*  289 */     if (file.exists()) {
/*  290 */       if (file.isDirectory()) {
/*  291 */         throw new IOException("File '" + file + "' exists but is a directory");
/*      */       }
/*  293 */       if (!file.canRead()) {
/*  294 */         throw new IOException("File '" + file + "' cannot be read");
/*      */       }
/*      */     } else {
/*  297 */       throw new FileNotFoundException("File '" + file + "' does not exist");
/*      */     } 
/*  299 */     return new FileInputStream(file);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static FileOutputStream openOutputStream(File file) throws IOException {
/*  324 */     return openOutputStream(file, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static FileOutputStream openOutputStream(File file, boolean append) throws IOException {
/*  350 */     if (file.exists()) {
/*  351 */       if (file.isDirectory()) {
/*  352 */         throw new IOException("File '" + file + "' exists but is a directory");
/*      */       }
/*  354 */       if (!file.canWrite()) {
/*  355 */         throw new IOException("File '" + file + "' cannot be written to");
/*      */       }
/*      */     } else {
/*  358 */       File parent = file.getParentFile();
/*  359 */       if (parent != null && 
/*  360 */         !parent.mkdirs() && !parent.isDirectory()) {
/*  361 */         throw new IOException("Directory '" + parent + "' could not be created");
/*      */       }
/*      */     } 
/*      */     
/*  365 */     return new FileOutputStream(file, append);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String byteCountToDisplaySize(BigInteger size) {
/*      */     String displaySize;
/*  388 */     if (size.divide(ONE_EB_BI).compareTo(BigInteger.ZERO) > 0) {
/*  389 */       displaySize = String.valueOf(size.divide(ONE_EB_BI)) + " EB";
/*  390 */     } else if (size.divide(ONE_PB_BI).compareTo(BigInteger.ZERO) > 0) {
/*  391 */       displaySize = String.valueOf(size.divide(ONE_PB_BI)) + " PB";
/*  392 */     } else if (size.divide(ONE_TB_BI).compareTo(BigInteger.ZERO) > 0) {
/*  393 */       displaySize = String.valueOf(size.divide(ONE_TB_BI)) + " TB";
/*  394 */     } else if (size.divide(ONE_GB_BI).compareTo(BigInteger.ZERO) > 0) {
/*  395 */       displaySize = String.valueOf(size.divide(ONE_GB_BI)) + " GB";
/*  396 */     } else if (size.divide(ONE_MB_BI).compareTo(BigInteger.ZERO) > 0) {
/*  397 */       displaySize = String.valueOf(size.divide(ONE_MB_BI)) + " MB";
/*  398 */     } else if (size.divide(ONE_KB_BI).compareTo(BigInteger.ZERO) > 0) {
/*  399 */       displaySize = String.valueOf(size.divide(ONE_KB_BI)) + " KB";
/*      */     } else {
/*  401 */       displaySize = String.valueOf(size) + " bytes";
/*      */     } 
/*  403 */     return displaySize;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String byteCountToDisplaySize(long size) {
/*  422 */     return byteCountToDisplaySize(BigInteger.valueOf(size));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void touch(File file) throws IOException {
/*  439 */     if (!file.exists()) {
/*  440 */       openOutputStream(file).close();
/*      */     }
/*  442 */     boolean success = file.setLastModified(System.currentTimeMillis());
/*  443 */     if (!success) {
/*  444 */       throw new IOException("Unable to set the last modification time for " + file);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static File[] convertFileCollectionToFileArray(Collection<File> files) {
/*  458 */     return files.<File>toArray(new File[files.size()]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void innerListFiles(Collection<File> files, File directory, IOFileFilter filter, boolean includeSubDirectories) {
/*  473 */     File[] found = directory.listFiles((FileFilter)filter);
/*      */     
/*  475 */     if (found != null) {
/*  476 */       for (File file : found) {
/*  477 */         if (file.isDirectory()) {
/*  478 */           if (includeSubDirectories) {
/*  479 */             files.add(file);
/*      */           }
/*  481 */           innerListFiles(files, file, filter, includeSubDirectories);
/*      */         } else {
/*  483 */           files.add(file);
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Collection<File> listFiles(File directory, IOFileFilter fileFilter, IOFileFilter dirFilter) {
/*  517 */     validateListFilesParameters(directory, fileFilter);
/*      */     
/*  519 */     IOFileFilter effFileFilter = setUpEffectiveFileFilter(fileFilter);
/*  520 */     IOFileFilter effDirFilter = setUpEffectiveDirFilter(dirFilter);
/*      */ 
/*      */     
/*  523 */     Collection<File> files = new LinkedList<>();
/*  524 */     innerListFiles(files, directory, 
/*  525 */         FileFilterUtils.or(new IOFileFilter[] { effFileFilter, effDirFilter }, ), false);
/*  526 */     return files;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void validateListFilesParameters(File directory, IOFileFilter fileFilter) {
/*  540 */     if (!directory.isDirectory()) {
/*  541 */       throw new IllegalArgumentException("Parameter 'directory' is not a directory: " + directory);
/*      */     }
/*  543 */     if (fileFilter == null) {
/*  544 */       throw new NullPointerException("Parameter 'fileFilter' is null");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static IOFileFilter setUpEffectiveFileFilter(IOFileFilter fileFilter) {
/*  555 */     return FileFilterUtils.and(new IOFileFilter[] { fileFilter, FileFilterUtils.notFileFilter(DirectoryFileFilter.INSTANCE) });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static IOFileFilter setUpEffectiveDirFilter(IOFileFilter dirFilter) {
/*  565 */     return (dirFilter == null) ? FalseFileFilter.INSTANCE : FileFilterUtils.and(new IOFileFilter[] { dirFilter, DirectoryFileFilter.INSTANCE });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Collection<File> listFilesAndDirs(File directory, IOFileFilter fileFilter, IOFileFilter dirFilter) {
/*  590 */     validateListFilesParameters(directory, fileFilter);
/*      */     
/*  592 */     IOFileFilter effFileFilter = setUpEffectiveFileFilter(fileFilter);
/*  593 */     IOFileFilter effDirFilter = setUpEffectiveDirFilter(dirFilter);
/*      */ 
/*      */     
/*  596 */     Collection<File> files = new LinkedList<>();
/*  597 */     if (directory.isDirectory()) {
/*  598 */       files.add(directory);
/*      */     }
/*  600 */     innerListFiles(files, directory, 
/*  601 */         FileFilterUtils.or(new IOFileFilter[] { effFileFilter, effDirFilter }, ), true);
/*  602 */     return files;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Iterator<File> iterateFiles(File directory, IOFileFilter fileFilter, IOFileFilter dirFilter) {
/*  625 */     return listFiles(directory, fileFilter, dirFilter).iterator();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Iterator<File> iterateFilesAndDirs(File directory, IOFileFilter fileFilter, IOFileFilter dirFilter) {
/*  650 */     return listFilesAndDirs(directory, fileFilter, dirFilter).iterator();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String[] toSuffixes(String[] extensions) {
/*  662 */     String[] suffixes = new String[extensions.length];
/*  663 */     for (int i = 0; i < extensions.length; i++) {
/*  664 */       suffixes[i] = "." + extensions[i];
/*      */     }
/*  666 */     return suffixes;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Collection<File> listFiles(File directory, String[] extensions, boolean recursive) {
/*      */     SuffixFileFilter suffixFileFilter;
/*  683 */     if (extensions == null) {
/*  684 */       IOFileFilter filter = TrueFileFilter.INSTANCE;
/*      */     } else {
/*  686 */       String[] suffixes = toSuffixes(extensions);
/*  687 */       suffixFileFilter = new SuffixFileFilter(suffixes);
/*      */     } 
/*  689 */     return listFiles(directory, (IOFileFilter)suffixFileFilter, recursive ? TrueFileFilter.INSTANCE : FalseFileFilter.INSTANCE);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Iterator<File> iterateFiles(File directory, String[] extensions, boolean recursive) {
/*  708 */     return listFiles(directory, extensions, recursive).iterator();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean contentEquals(File file1, File file2) throws IOException {
/*  728 */     boolean file1Exists = file1.exists();
/*  729 */     if (file1Exists != file2.exists()) {
/*  730 */       return false;
/*      */     }
/*      */     
/*  733 */     if (!file1Exists)
/*      */     {
/*  735 */       return true;
/*      */     }
/*      */     
/*  738 */     if (file1.isDirectory() || file2.isDirectory())
/*      */     {
/*  740 */       throw new IOException("Can't compare directories, only files");
/*      */     }
/*      */     
/*  743 */     if (file1.length() != file2.length())
/*      */     {
/*  745 */       return false;
/*      */     }
/*      */     
/*  748 */     if (file1.getCanonicalFile().equals(file2.getCanonicalFile()))
/*      */     {
/*  750 */       return true;
/*      */     }
/*      */     
/*  753 */     try(InputStream input1 = new FileInputStream(file1); 
/*  754 */         InputStream input2 = new FileInputStream(file2)) {
/*  755 */       return IOUtils.contentEquals(input1, input2);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean contentEqualsIgnoreEOL(File file1, File file2, String charsetName) throws IOException {
/*  779 */     boolean file1Exists = file1.exists();
/*  780 */     if (file1Exists != file2.exists()) {
/*  781 */       return false;
/*      */     }
/*      */     
/*  784 */     if (!file1Exists)
/*      */     {
/*  786 */       return true;
/*      */     }
/*      */     
/*  789 */     if (file1.isDirectory() || file2.isDirectory())
/*      */     {
/*  791 */       throw new IOException("Can't compare directories, only files");
/*      */     }
/*      */     
/*  794 */     if (file1.getCanonicalFile().equals(file2.getCanonicalFile()))
/*      */     {
/*  796 */       return true;
/*      */     }
/*      */     
/*  799 */     try(Reader input1 = (charsetName == null) ? new InputStreamReader(new FileInputStream(file1), 
/*  800 */           Charset.defaultCharset()) : new InputStreamReader(new FileInputStream(file1), charsetName); 
/*      */         
/*  802 */         Reader input2 = (charsetName == null) ? new InputStreamReader(new FileInputStream(file2), 
/*  803 */           Charset.defaultCharset()) : new InputStreamReader(new FileInputStream(file2), charsetName)) {
/*      */       
/*  805 */       return IOUtils.contentEqualsIgnoreEOL(input1, input2);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static File toFile(URL url) {
/*  825 */     if (url == null || !"file".equalsIgnoreCase(url.getProtocol())) {
/*  826 */       return null;
/*      */     }
/*  828 */     String filename = url.getFile().replace('/', File.separatorChar);
/*  829 */     filename = decodeUrl(filename);
/*  830 */     return new File(filename);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static String decodeUrl(String url) {
/*  849 */     String decoded = url;
/*  850 */     if (url != null && url.indexOf('%') >= 0) {
/*  851 */       int n = url.length();
/*  852 */       StringBuilder buffer = new StringBuilder();
/*  853 */       ByteBuffer bytes = ByteBuffer.allocate(n);
/*  854 */       for (int i = 0; i < n; ) {
/*  855 */         if (url.charAt(i) == '%') {
/*      */           try {
/*      */             do {
/*  858 */               byte octet = (byte)Integer.parseInt(url.substring(i + 1, i + 3), 16);
/*  859 */               bytes.put(octet);
/*  860 */               i += 3;
/*  861 */             } while (i < n && url.charAt(i) == '%');
/*      */             continue;
/*  863 */           } catch (RuntimeException runtimeException) {
/*      */ 
/*      */           
/*      */           } finally {
/*  867 */             if (bytes.position() > 0) {
/*  868 */               bytes.flip();
/*  869 */               buffer.append(StandardCharsets.UTF_8.decode(bytes).toString());
/*  870 */               bytes.clear();
/*      */             } 
/*      */           } 
/*      */         }
/*  874 */         buffer.append(url.charAt(i++));
/*      */       } 
/*  876 */       decoded = buffer.toString();
/*      */     } 
/*  878 */     return decoded;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static File[] toFiles(URL[] urls) {
/*  901 */     if (urls == null || urls.length == 0) {
/*  902 */       return EMPTY_FILE_ARRAY;
/*      */     }
/*  904 */     File[] files = new File[urls.length];
/*  905 */     for (int i = 0; i < urls.length; i++) {
/*  906 */       URL url = urls[i];
/*  907 */       if (url != null) {
/*  908 */         if (!url.getProtocol().equals("file")) {
/*  909 */           throw new IllegalArgumentException("URL could not be converted to a File: " + url);
/*      */         }
/*      */         
/*  912 */         files[i] = toFile(url);
/*      */       } 
/*      */     } 
/*  915 */     return files;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static URL[] toURLs(File[] files) throws IOException {
/*  929 */     URL[] urls = new URL[files.length];
/*      */     
/*  931 */     for (int i = 0; i < urls.length; i++) {
/*  932 */       urls[i] = files[i].toURI().toURL();
/*      */     }
/*      */     
/*  935 */     return urls;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void copyFileToDirectory(File srcFile, File destDir) throws IOException {
/*  961 */     copyFileToDirectory(srcFile, destDir, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void copyFileToDirectory(File srcFile, File destDir, boolean preserveFileDate) throws IOException {
/*  993 */     if (destDir == null) {
/*  994 */       throw new NullPointerException("Destination must not be null");
/*      */     }
/*  996 */     if (destDir.exists() && !destDir.isDirectory()) {
/*  997 */       throw new IllegalArgumentException("Destination '" + destDir + "' is not a directory");
/*      */     }
/*  999 */     File destFile = new File(destDir, srcFile.getName());
/* 1000 */     copyFile(srcFile, destFile, preserveFileDate);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void copyFile(File srcFile, File destFile) throws IOException {
/* 1028 */     copyFile(srcFile, destFile, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void copyFile(File srcFile, File destFile, boolean preserveFileDate) throws IOException {
/* 1060 */     checkFileRequirements(srcFile, destFile);
/* 1061 */     if (srcFile.isDirectory()) {
/* 1062 */       throw new IOException("Source '" + srcFile + "' exists but is a directory");
/*      */     }
/* 1064 */     if (srcFile.getCanonicalPath().equals(destFile.getCanonicalPath())) {
/* 1065 */       throw new IOException("Source '" + srcFile + "' and destination '" + destFile + "' are the same");
/*      */     }
/* 1067 */     File parentFile = destFile.getParentFile();
/* 1068 */     if (parentFile != null && 
/* 1069 */       !parentFile.mkdirs() && !parentFile.isDirectory()) {
/* 1070 */       throw new IOException("Destination '" + parentFile + "' directory cannot be created");
/*      */     }
/*      */     
/* 1073 */     if (destFile.exists() && !destFile.canWrite()) {
/* 1074 */       throw new IOException("Destination '" + destFile + "' exists but is read-only");
/*      */     }
/* 1076 */     doCopyFile(srcFile, destFile, preserveFileDate);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static long copyFile(File input, OutputStream output) throws IOException {
/* 1093 */     try (FileInputStream fis = new FileInputStream(input)) {
/* 1094 */       return IOUtils.copyLarge(fis, output);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void doCopyFile(File srcFile, File destFile, boolean preserveFileDate) throws IOException {
/* 1117 */     if (destFile.exists() && destFile.isDirectory()) {
/* 1118 */       throw new IOException("Destination '" + destFile + "' exists but is a directory");
/*      */     }
/*      */     
/* 1121 */     try(FileInputStream fis = new FileInputStream(srcFile); 
/* 1122 */         FileChannel input = fis.getChannel(); 
/* 1123 */         FileOutputStream fos = new FileOutputStream(destFile); 
/* 1124 */         FileChannel output = fos.getChannel()) {
/* 1125 */       long size = input.size();
/* 1126 */       long pos = 0L;
/* 1127 */       long count = 0L;
/* 1128 */       while (pos < size) {
/* 1129 */         long remain = size - pos;
/* 1130 */         count = (remain > 31457280L) ? 31457280L : remain;
/* 1131 */         long bytesCopied = output.transferFrom(input, pos, count);
/* 1132 */         if (bytesCopied == 0L) {
/*      */           break;
/*      */         }
/* 1135 */         pos += bytesCopied;
/*      */       } 
/*      */     } 
/*      */     
/* 1139 */     long srcLen = srcFile.length();
/* 1140 */     long dstLen = destFile.length();
/* 1141 */     if (srcLen != dstLen) {
/* 1142 */       throw new IOException("Failed to copy full contents from '" + srcFile + "' to '" + destFile + "' Expected length: " + srcLen + " Actual: " + dstLen);
/*      */     }
/*      */     
/* 1145 */     if (preserveFileDate) {
/* 1146 */       destFile.setLastModified(srcFile.lastModified());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void copyDirectoryToDirectory(File srcDir, File destDir) throws IOException {
/* 1175 */     if (srcDir == null) {
/* 1176 */       throw new NullPointerException("Source must not be null");
/*      */     }
/* 1178 */     if (srcDir.exists() && !srcDir.isDirectory()) {
/* 1179 */       throw new IllegalArgumentException("Source '" + destDir + "' is not a directory");
/*      */     }
/* 1181 */     if (destDir == null) {
/* 1182 */       throw new NullPointerException("Destination must not be null");
/*      */     }
/* 1184 */     if (destDir.exists() && !destDir.isDirectory()) {
/* 1185 */       throw new IllegalArgumentException("Destination '" + destDir + "' is not a directory");
/*      */     }
/* 1187 */     copyDirectory(srcDir, new File(destDir, srcDir.getName()), true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void copyDirectory(File srcDir, File destDir) throws IOException {
/* 1215 */     copyDirectory(srcDir, destDir, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void copyDirectory(File srcDir, File destDir, boolean preserveFileDate) throws IOException {
/* 1246 */     copyDirectory(srcDir, destDir, null, preserveFileDate);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void copyDirectory(File srcDir, File destDir, FileFilter filter) throws IOException {
/* 1295 */     copyDirectory(srcDir, destDir, filter, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void copyDirectory(File srcDir, File destDir, FileFilter filter, boolean preserveFileDate) throws IOException {
/* 1346 */     checkFileRequirements(srcDir, destDir);
/* 1347 */     if (!srcDir.isDirectory()) {
/* 1348 */       throw new IOException("Source '" + srcDir + "' exists but is not a directory");
/*      */     }
/* 1350 */     if (srcDir.getCanonicalPath().equals(destDir.getCanonicalPath())) {
/* 1351 */       throw new IOException("Source '" + srcDir + "' and destination '" + destDir + "' are the same");
/*      */     }
/*      */ 
/*      */     
/* 1355 */     List<String> exclusionList = null;
/* 1356 */     if (destDir.getCanonicalPath().startsWith(srcDir.getCanonicalPath())) {
/* 1357 */       File[] srcFiles = (filter == null) ? srcDir.listFiles() : srcDir.listFiles(filter);
/* 1358 */       if (srcFiles != null && srcFiles.length > 0) {
/* 1359 */         exclusionList = new ArrayList<>(srcFiles.length);
/* 1360 */         for (File srcFile : srcFiles) {
/* 1361 */           File copiedFile = new File(destDir, srcFile.getName());
/* 1362 */           exclusionList.add(copiedFile.getCanonicalPath());
/*      */         } 
/*      */       } 
/*      */     } 
/* 1366 */     doCopyDirectory(srcDir, destDir, filter, preserveFileDate, exclusionList);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void checkFileRequirements(File src, File dest) throws FileNotFoundException {
/* 1376 */     if (src == null) {
/* 1377 */       throw new NullPointerException("Source must not be null");
/*      */     }
/* 1379 */     if (dest == null) {
/* 1380 */       throw new NullPointerException("Destination must not be null");
/*      */     }
/* 1382 */     if (!src.exists()) {
/* 1383 */       throw new FileNotFoundException("Source '" + src + "' does not exist");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void doCopyDirectory(File srcDir, File destDir, FileFilter filter, boolean preserveFileDate, List<String> exclusionList) throws IOException {
/* 1402 */     File[] srcFiles = (filter == null) ? srcDir.listFiles() : srcDir.listFiles(filter);
/* 1403 */     if (srcFiles == null) {
/* 1404 */       throw new IOException("Failed to list contents of " + srcDir);
/*      */     }
/* 1406 */     if (destDir.exists()) {
/* 1407 */       if (!destDir.isDirectory()) {
/* 1408 */         throw new IOException("Destination '" + destDir + "' exists but is not a directory");
/*      */       }
/*      */     }
/* 1411 */     else if (!destDir.mkdirs() && !destDir.isDirectory()) {
/* 1412 */       throw new IOException("Destination '" + destDir + "' directory cannot be created");
/*      */     } 
/*      */     
/* 1415 */     if (!destDir.canWrite()) {
/* 1416 */       throw new IOException("Destination '" + destDir + "' cannot be written to");
/*      */     }
/* 1418 */     for (File srcFile : srcFiles) {
/* 1419 */       File dstFile = new File(destDir, srcFile.getName());
/* 1420 */       if (exclusionList == null || !exclusionList.contains(srcFile.getCanonicalPath())) {
/* 1421 */         if (srcFile.isDirectory()) {
/* 1422 */           doCopyDirectory(srcFile, dstFile, filter, preserveFileDate, exclusionList);
/*      */         } else {
/* 1424 */           doCopyFile(srcFile, dstFile, preserveFileDate);
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1430 */     if (preserveFileDate) {
/* 1431 */       destDir.setLastModified(srcDir.lastModified());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void copyURLToFile(URL source, File destination) throws IOException {
/* 1456 */     copyInputStreamToFile(source.openStream(), destination);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void copyURLToFile(URL source, File destination, int connectionTimeout, int readTimeout) throws IOException {
/* 1481 */     URLConnection connection = source.openConnection();
/* 1482 */     connection.setConnectTimeout(connectionTimeout);
/* 1483 */     connection.setReadTimeout(readTimeout);
/* 1484 */     copyInputStreamToFile(connection.getInputStream(), destination);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void copyInputStreamToFile(InputStream source, File destination) throws IOException {
/* 1505 */     try (InputStream in = source) {
/* 1506 */       copyToFile(in, destination);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void copyToFile(InputStream source, File destination) throws IOException {
/* 1528 */     try(InputStream in = source; 
/* 1529 */         OutputStream out = openOutputStream(destination)) {
/* 1530 */       IOUtils.copy(in, out);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void copyToDirectory(File src, File destDir) throws IOException {
/* 1560 */     if (src == null) {
/* 1561 */       throw new NullPointerException("Source must not be null");
/*      */     }
/* 1563 */     if (src.isFile()) {
/* 1564 */       copyFileToDirectory(src, destDir);
/* 1565 */     } else if (src.isDirectory()) {
/* 1566 */       copyDirectoryToDirectory(src, destDir);
/*      */     } else {
/* 1568 */       throw new IOException("The source " + src + " does not exist");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void copyToDirectory(Iterable<File> srcs, File destDir) throws IOException {
/* 1595 */     if (srcs == null) {
/* 1596 */       throw new NullPointerException("Sources must not be null");
/*      */     }
/* 1598 */     for (File src : srcs) {
/* 1599 */       copyFileToDirectory(src, destDir);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void deleteDirectory(File directory) throws IOException {
/* 1612 */     if (!directory.exists()) {
/*      */       return;
/*      */     }
/*      */     
/* 1616 */     if (!isSymlink(directory)) {
/* 1617 */       cleanDirectory(directory);
/*      */     }
/*      */     
/* 1620 */     if (!directory.delete()) {
/* 1621 */       String message = "Unable to delete directory " + directory + ".";
/*      */       
/* 1623 */       throw new IOException(message);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean deleteQuietly(File file) {
/* 1643 */     if (file == null) {
/* 1644 */       return false;
/*      */     }
/*      */     try {
/* 1647 */       if (file.isDirectory()) {
/* 1648 */         cleanDirectory(file);
/*      */       }
/* 1650 */     } catch (Exception exception) {}
/*      */ 
/*      */     
/*      */     try {
/* 1654 */       return file.delete();
/* 1655 */     } catch (Exception ignored) {
/* 1656 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean directoryContains(File directory, File child) throws IOException {
/* 1685 */     if (directory == null) {
/* 1686 */       throw new IllegalArgumentException("Directory must not be null");
/*      */     }
/*      */     
/* 1689 */     if (!directory.isDirectory()) {
/* 1690 */       throw new IllegalArgumentException("Not a directory: " + directory);
/*      */     }
/*      */     
/* 1693 */     if (child == null) {
/* 1694 */       return false;
/*      */     }
/*      */     
/* 1697 */     if (!directory.exists() || !child.exists()) {
/* 1698 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 1702 */     String canonicalParent = directory.getCanonicalPath();
/* 1703 */     String canonicalChild = child.getCanonicalPath();
/*      */     
/* 1705 */     return FilenameUtils.directoryContains(canonicalParent, canonicalChild);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void cleanDirectory(File directory) throws IOException {
/* 1716 */     File[] files = verifiedListFiles(directory);
/*      */     
/* 1718 */     IOException exception = null;
/* 1719 */     for (File file : files) {
/*      */       try {
/* 1721 */         forceDelete(file);
/* 1722 */       } catch (IOException ioe) {
/* 1723 */         exception = ioe;
/*      */       } 
/*      */     } 
/*      */     
/* 1727 */     if (null != exception) {
/* 1728 */       throw exception;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static File[] verifiedListFiles(File directory) throws IOException {
/* 1739 */     if (!directory.exists()) {
/* 1740 */       String message = directory + " does not exist";
/* 1741 */       throw new IllegalArgumentException(message);
/*      */     } 
/*      */     
/* 1744 */     if (!directory.isDirectory()) {
/* 1745 */       String message = directory + " is not a directory";
/* 1746 */       throw new IllegalArgumentException(message);
/*      */     } 
/*      */     
/* 1749 */     File[] files = directory.listFiles();
/* 1750 */     if (files == null) {
/* 1751 */       throw new IOException("Failed to list contents of " + directory);
/*      */     }
/* 1753 */     return files;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean waitFor(File file, int seconds) {
/* 1769 */     long finishAt = System.currentTimeMillis() + seconds * 1000L;
/* 1770 */     boolean wasInterrupted = false;
/*      */     try {
/* 1772 */       while (!file.exists()) {
/* 1773 */         long remaining = finishAt - System.currentTimeMillis();
/* 1774 */         if (remaining < 0L) {
/* 1775 */           return false;
/*      */         }
/*      */         try {
/* 1778 */           Thread.sleep(Math.min(100L, remaining));
/* 1779 */         } catch (InterruptedException ignore) {
/* 1780 */           wasInterrupted = true;
/* 1781 */         } catch (Exception ex) {
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } finally {
/* 1786 */       if (wasInterrupted) {
/* 1787 */         Thread.currentThread().interrupt();
/*      */       }
/*      */     } 
/* 1790 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String readFileToString(File file, Charset encoding) throws IOException {
/* 1805 */     try (InputStream in = openInputStream(file)) {
/* 1806 */       return IOUtils.toString(in, Charsets.toCharset(encoding));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String readFileToString(File file, String encoding) throws IOException {
/* 1822 */     return readFileToString(file, Charsets.toCharset(encoding));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static String readFileToString(File file) throws IOException {
/* 1838 */     return readFileToString(file, Charset.defaultCharset());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static byte[] readFileToByteArray(File file) throws IOException {
/* 1851 */     try (InputStream in = openInputStream(file)) {
/* 1852 */       long fileLength = file.length();
/*      */       
/* 1854 */       return (fileLength > 0L) ? IOUtils.toByteArray(in, fileLength) : IOUtils.toByteArray(in);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<String> readLines(File file, Charset encoding) throws IOException {
/* 1869 */     try (InputStream in = openInputStream(file)) {
/* 1870 */       return IOUtils.readLines(in, Charsets.toCharset(encoding));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<String> readLines(File file, String encoding) throws IOException {
/* 1886 */     return readLines(file, Charsets.toCharset(encoding));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static List<String> readLines(File file) throws IOException {
/* 1901 */     return readLines(file, Charset.defaultCharset());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static LineIterator lineIterator(File file, String encoding) throws IOException {
/* 1936 */     InputStream in = null;
/*      */     try {
/* 1938 */       in = openInputStream(file);
/* 1939 */       return IOUtils.lineIterator(in, encoding);
/* 1940 */     } catch (IOException|RuntimeException ex) {
/*      */       try {
/* 1942 */         if (in != null) {
/* 1943 */           in.close();
/*      */         }
/*      */       }
/* 1946 */       catch (IOException e) {
/* 1947 */         ex.addSuppressed(e);
/*      */       } 
/* 1949 */       throw ex;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static LineIterator lineIterator(File file) throws IOException {
/* 1963 */     return lineIterator(file, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void writeStringToFile(File file, String data, Charset encoding) throws IOException {
/* 1983 */     writeStringToFile(file, data, encoding, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void writeStringToFile(File file, String data, String encoding) throws IOException {
/* 1999 */     writeStringToFile(file, data, encoding, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void writeStringToFile(File file, String data, Charset encoding, boolean append) throws IOException {
/* 2015 */     try (OutputStream out = openOutputStream(file, append)) {
/* 2016 */       IOUtils.write(data, out, encoding);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void writeStringToFile(File file, String data, String encoding, boolean append) throws IOException {
/* 2035 */     writeStringToFile(file, data, Charsets.toCharset(encoding), append);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static void writeStringToFile(File file, String data) throws IOException {
/* 2048 */     writeStringToFile(file, data, Charset.defaultCharset(), false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static void writeStringToFile(File file, String data, boolean append) throws IOException {
/* 2064 */     writeStringToFile(file, data, Charset.defaultCharset(), append);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static void write(File file, CharSequence data) throws IOException {
/* 2078 */     write(file, data, Charset.defaultCharset(), false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static void write(File file, CharSequence data, boolean append) throws IOException {
/* 2094 */     write(file, data, Charset.defaultCharset(), append);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void write(File file, CharSequence data, Charset encoding) throws IOException {
/* 2107 */     write(file, data, encoding, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void write(File file, CharSequence data, String encoding) throws IOException {
/* 2121 */     write(file, data, encoding, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void write(File file, CharSequence data, Charset encoding, boolean append) throws IOException {
/* 2137 */     String str = (data == null) ? null : data.toString();
/* 2138 */     writeStringToFile(file, str, encoding, append);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void write(File file, CharSequence data, String encoding, boolean append) throws IOException {
/* 2156 */     write(file, data, Charsets.toCharset(encoding), append);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void writeByteArrayToFile(File file, byte[] data) throws IOException {
/* 2171 */     writeByteArrayToFile(file, data, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void writeByteArrayToFile(File file, byte[] data, boolean append) throws IOException {
/* 2186 */     writeByteArrayToFile(file, data, 0, data.length, append);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void writeByteArrayToFile(File file, byte[] data, int off, int len) throws IOException {
/* 2203 */     writeByteArrayToFile(file, data, off, len, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void writeByteArrayToFile(File file, byte[] data, int off, int len, boolean append) throws IOException {
/* 2222 */     try (OutputStream out = openOutputStream(file, append)) {
/* 2223 */       out.write(data, off, len);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void writeLines(File file, String encoding, Collection<?> lines) throws IOException {
/* 2244 */     writeLines(file, encoding, lines, null, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void writeLines(File file, String encoding, Collection<?> lines, boolean append) throws IOException {
/* 2263 */     writeLines(file, encoding, lines, null, append);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void writeLines(File file, Collection<?> lines) throws IOException {
/* 2277 */     writeLines(file, null, lines, null, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void writeLines(File file, Collection<?> lines, boolean append) throws IOException {
/* 2293 */     writeLines(file, null, lines, null, append);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void writeLines(File file, String encoding, Collection<?> lines, String lineEnding) throws IOException {
/* 2314 */     writeLines(file, encoding, lines, lineEnding, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void writeLines(File file, String encoding, Collection<?> lines, String lineEnding, boolean append) throws IOException {
/* 2334 */     try (OutputStream out = new BufferedOutputStream(openOutputStream(file, append))) {
/* 2335 */       IOUtils.writeLines(lines, lineEnding, out, encoding);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void writeLines(File file, Collection<?> lines, String lineEnding) throws IOException {
/* 2352 */     writeLines(file, null, lines, lineEnding, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void writeLines(File file, Collection<?> lines, String lineEnding, boolean append) throws IOException {
/* 2370 */     writeLines(file, null, lines, lineEnding, append);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void forceDelete(File file) throws IOException {
/* 2390 */     if (file.isDirectory()) {
/* 2391 */       deleteDirectory(file);
/*      */     } else {
/* 2393 */       boolean filePresent = file.exists();
/* 2394 */       if (!file.delete()) {
/* 2395 */         if (!filePresent) {
/* 2396 */           throw new FileNotFoundException("File does not exist: " + file);
/*      */         }
/* 2398 */         String message = "Unable to delete file: " + file;
/*      */         
/* 2400 */         throw new IOException(message);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void forceDeleteOnExit(File file) throws IOException {
/* 2414 */     if (file.isDirectory()) {
/* 2415 */       deleteDirectoryOnExit(file);
/*      */     } else {
/* 2417 */       file.deleteOnExit();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void deleteDirectoryOnExit(File directory) throws IOException {
/* 2429 */     if (!directory.exists()) {
/*      */       return;
/*      */     }
/*      */     
/* 2433 */     directory.deleteOnExit();
/* 2434 */     if (!isSymlink(directory)) {
/* 2435 */       cleanDirectoryOnExit(directory);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void cleanDirectoryOnExit(File directory) throws IOException {
/* 2447 */     File[] files = verifiedListFiles(directory);
/*      */     
/* 2449 */     IOException exception = null;
/* 2450 */     for (File file : files) {
/*      */       try {
/* 2452 */         forceDeleteOnExit(file);
/* 2453 */       } catch (IOException ioe) {
/* 2454 */         exception = ioe;
/*      */       } 
/*      */     } 
/*      */     
/* 2458 */     if (null != exception) {
/* 2459 */       throw exception;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void forceMkdir(File directory) throws IOException {
/* 2475 */     if (directory.exists()) {
/* 2476 */       if (!directory.isDirectory()) {
/* 2477 */         String message = "File " + directory + " exists and is not a directory. Unable to create directory.";
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2482 */         throw new IOException(message);
/*      */       }
/*      */     
/* 2485 */     } else if (!directory.mkdirs()) {
/*      */ 
/*      */       
/* 2488 */       if (!directory.isDirectory()) {
/* 2489 */         String message = "Unable to create directory " + directory;
/*      */         
/* 2491 */         throw new IOException(message);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void forceMkdirParent(File file) throws IOException {
/* 2507 */     File parent = file.getParentFile();
/* 2508 */     if (parent == null) {
/*      */       return;
/*      */     }
/* 2511 */     forceMkdir(parent);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static long sizeOf(File file) {
/* 2539 */     if (!file.exists()) {
/* 2540 */       String message = file + " does not exist";
/* 2541 */       throw new IllegalArgumentException(message);
/*      */     } 
/*      */     
/* 2544 */     if (file.isDirectory()) {
/* 2545 */       return sizeOfDirectory0(file);
/*      */     }
/* 2547 */     return file.length();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static BigInteger sizeOfAsBigInteger(File file) {
/* 2572 */     if (!file.exists()) {
/* 2573 */       String message = file + " does not exist";
/* 2574 */       throw new IllegalArgumentException(message);
/*      */     } 
/*      */     
/* 2577 */     if (file.isDirectory()) {
/* 2578 */       return sizeOfDirectoryBig0(file);
/*      */     }
/* 2580 */     return BigInteger.valueOf(file.length());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static long sizeOfDirectory(File directory) {
/* 2598 */     checkDirectory(directory);
/* 2599 */     return sizeOfDirectory0(directory);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static long sizeOfDirectory0(File directory) {
/* 2610 */     File[] files = directory.listFiles();
/* 2611 */     if (files == null) {
/* 2612 */       return 0L;
/*      */     }
/* 2614 */     long size = 0L;
/*      */     
/* 2616 */     for (File file : files) {
/*      */       try {
/* 2618 */         if (!isSymlink(file)) {
/* 2619 */           size += sizeOf0(file);
/* 2620 */           if (size < 0L) {
/*      */             break;
/*      */           }
/*      */         } 
/* 2624 */       } catch (IOException iOException) {}
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2629 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static long sizeOf0(File file) {
/* 2640 */     if (file.isDirectory()) {
/* 2641 */       return sizeOfDirectory0(file);
/*      */     }
/* 2643 */     return file.length();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static BigInteger sizeOfDirectoryAsBigInteger(File directory) {
/* 2656 */     checkDirectory(directory);
/* 2657 */     return sizeOfDirectoryBig0(directory);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static BigInteger sizeOfDirectoryBig0(File directory) {
/* 2669 */     File[] files = directory.listFiles();
/* 2670 */     if (files == null) {
/* 2671 */       return BigInteger.ZERO;
/*      */     }
/* 2673 */     BigInteger size = BigInteger.ZERO;
/*      */     
/* 2675 */     for (File file : files) {
/*      */       try {
/* 2677 */         if (!isSymlink(file)) {
/* 2678 */           size = size.add(sizeOfBig0(file));
/*      */         }
/* 2680 */       } catch (IOException iOException) {}
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2685 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static BigInteger sizeOfBig0(File fileOrDir) {
/* 2696 */     if (fileOrDir.isDirectory()) {
/* 2697 */       return sizeOfDirectoryBig0(fileOrDir);
/*      */     }
/* 2699 */     return BigInteger.valueOf(fileOrDir.length());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void checkDirectory(File directory) {
/* 2710 */     if (!directory.exists()) {
/* 2711 */       throw new IllegalArgumentException(directory + " does not exist");
/*      */     }
/* 2713 */     if (!directory.isDirectory()) {
/* 2714 */       throw new IllegalArgumentException(directory + " is not a directory");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isFileNewer(File file, File reference) {
/* 2733 */     if (reference == null) {
/* 2734 */       throw new IllegalArgumentException("No specified reference file");
/*      */     }
/* 2736 */     if (!reference.exists()) {
/* 2737 */       throw new IllegalArgumentException("The reference file '" + reference + "' doesn't exist");
/*      */     }
/*      */     
/* 2740 */     return isFileNewer(file, reference.lastModified());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isFileNewer(File file, Date date) {
/* 2756 */     if (date == null) {
/* 2757 */       throw new IllegalArgumentException("No specified date");
/*      */     }
/* 2759 */     return isFileNewer(file, date.getTime());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isFileNewer(File file, long timeMillis) {
/* 2775 */     if (file == null) {
/* 2776 */       throw new IllegalArgumentException("No specified file");
/*      */     }
/* 2778 */     if (!file.exists()) {
/* 2779 */       return false;
/*      */     }
/* 2781 */     return (file.lastModified() > timeMillis);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isFileOlder(File file, File reference) {
/* 2800 */     if (reference == null) {
/* 2801 */       throw new IllegalArgumentException("No specified reference file");
/*      */     }
/* 2803 */     if (!reference.exists()) {
/* 2804 */       throw new IllegalArgumentException("The reference file '" + reference + "' doesn't exist");
/*      */     }
/*      */     
/* 2807 */     return isFileOlder(file, reference.lastModified());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isFileOlder(File file, Date date) {
/* 2823 */     if (date == null) {
/* 2824 */       throw new IllegalArgumentException("No specified date");
/*      */     }
/* 2826 */     return isFileOlder(file, date.getTime());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isFileOlder(File file, long timeMillis) {
/* 2842 */     if (file == null) {
/* 2843 */       throw new IllegalArgumentException("No specified file");
/*      */     }
/* 2845 */     if (!file.exists()) {
/* 2846 */       return false;
/*      */     }
/* 2848 */     return (file.lastModified() < timeMillis);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static long checksumCRC32(File file) throws IOException {
/* 2864 */     CRC32 crc = new CRC32();
/* 2865 */     checksum(file, crc);
/* 2866 */     return crc.getValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Checksum checksum(File file, Checksum checksum) throws IOException {
/* 2887 */     if (file.isDirectory()) {
/* 2888 */       throw new IllegalArgumentException("Checksums can't be computed on directories");
/*      */     }
/* 2890 */     try (InputStream in = new CheckedInputStream(new FileInputStream(file), checksum)) {
/* 2891 */       IOUtils.copy(in, (OutputStream)new NullOutputStream());
/*      */     } 
/* 2893 */     return checksum;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void moveDirectory(File srcDir, File destDir) throws IOException {
/* 2910 */     if (srcDir == null) {
/* 2911 */       throw new NullPointerException("Source must not be null");
/*      */     }
/* 2913 */     if (destDir == null) {
/* 2914 */       throw new NullPointerException("Destination must not be null");
/*      */     }
/* 2916 */     if (!srcDir.exists()) {
/* 2917 */       throw new FileNotFoundException("Source '" + srcDir + "' does not exist");
/*      */     }
/* 2919 */     if (!srcDir.isDirectory()) {
/* 2920 */       throw new IOException("Source '" + srcDir + "' is not a directory");
/*      */     }
/* 2922 */     if (destDir.exists()) {
/* 2923 */       throw new FileExistsException("Destination '" + destDir + "' already exists");
/*      */     }
/* 2925 */     boolean rename = srcDir.renameTo(destDir);
/* 2926 */     if (!rename) {
/* 2927 */       if (destDir.getCanonicalPath().startsWith(srcDir.getCanonicalPath() + File.separator)) {
/* 2928 */         throw new IOException("Cannot move directory: " + srcDir + " to a subdirectory of itself: " + destDir);
/*      */       }
/* 2930 */       copyDirectory(srcDir, destDir);
/* 2931 */       deleteDirectory(srcDir);
/* 2932 */       if (srcDir.exists()) {
/* 2933 */         throw new IOException("Failed to delete original directory '" + srcDir + "' after copy to '" + destDir + "'");
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void moveDirectoryToDirectory(File src, File destDir, boolean createDestDir) throws IOException {
/* 2954 */     if (src == null) {
/* 2955 */       throw new NullPointerException("Source must not be null");
/*      */     }
/* 2957 */     if (destDir == null) {
/* 2958 */       throw new NullPointerException("Destination directory must not be null");
/*      */     }
/* 2960 */     if (!destDir.exists() && createDestDir) {
/* 2961 */       destDir.mkdirs();
/*      */     }
/* 2963 */     if (!destDir.exists()) {
/* 2964 */       throw new FileNotFoundException("Destination directory '" + destDir + "' does not exist [createDestDir=" + createDestDir + "]");
/*      */     }
/*      */     
/* 2967 */     if (!destDir.isDirectory()) {
/* 2968 */       throw new IOException("Destination '" + destDir + "' is not a directory");
/*      */     }
/* 2970 */     moveDirectory(src, new File(destDir, src.getName()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void moveFile(File srcFile, File destFile) throws IOException {
/* 2988 */     if (srcFile == null) {
/* 2989 */       throw new NullPointerException("Source must not be null");
/*      */     }
/* 2991 */     if (destFile == null) {
/* 2992 */       throw new NullPointerException("Destination must not be null");
/*      */     }
/* 2994 */     if (!srcFile.exists()) {
/* 2995 */       throw new FileNotFoundException("Source '" + srcFile + "' does not exist");
/*      */     }
/* 2997 */     if (srcFile.isDirectory()) {
/* 2998 */       throw new IOException("Source '" + srcFile + "' is a directory");
/*      */     }
/* 3000 */     if (destFile.exists()) {
/* 3001 */       throw new FileExistsException("Destination '" + destFile + "' already exists");
/*      */     }
/* 3003 */     if (destFile.isDirectory()) {
/* 3004 */       throw new IOException("Destination '" + destFile + "' is a directory");
/*      */     }
/* 3006 */     boolean rename = srcFile.renameTo(destFile);
/* 3007 */     if (!rename) {
/* 3008 */       copyFile(srcFile, destFile);
/* 3009 */       if (!srcFile.delete()) {
/* 3010 */         deleteQuietly(destFile);
/* 3011 */         throw new IOException("Failed to delete original file '" + srcFile + "' after copy to '" + destFile + "'");
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void moveFileToDirectory(File srcFile, File destDir, boolean createDestDir) throws IOException {
/* 3032 */     if (srcFile == null) {
/* 3033 */       throw new NullPointerException("Source must not be null");
/*      */     }
/* 3035 */     if (destDir == null) {
/* 3036 */       throw new NullPointerException("Destination directory must not be null");
/*      */     }
/* 3038 */     if (!destDir.exists() && createDestDir) {
/* 3039 */       destDir.mkdirs();
/*      */     }
/* 3041 */     if (!destDir.exists()) {
/* 3042 */       throw new FileNotFoundException("Destination directory '" + destDir + "' does not exist [createDestDir=" + createDestDir + "]");
/*      */     }
/*      */     
/* 3045 */     if (!destDir.isDirectory()) {
/* 3046 */       throw new IOException("Destination '" + destDir + "' is not a directory");
/*      */     }
/* 3048 */     moveFile(srcFile, new File(destDir, srcFile.getName()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void moveToDirectory(File src, File destDir, boolean createDestDir) throws IOException {
/* 3068 */     if (src == null) {
/* 3069 */       throw new NullPointerException("Source must not be null");
/*      */     }
/* 3071 */     if (destDir == null) {
/* 3072 */       throw new NullPointerException("Destination must not be null");
/*      */     }
/* 3074 */     if (!src.exists()) {
/* 3075 */       throw new FileNotFoundException("Source '" + src + "' does not exist");
/*      */     }
/* 3077 */     if (src.isDirectory()) {
/* 3078 */       moveDirectoryToDirectory(src, destDir, createDestDir);
/*      */     } else {
/* 3080 */       moveFileToDirectory(src, destDir, createDestDir);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isSymlink(File file) throws IOException {
/* 3104 */     if (file == null) {
/* 3105 */       throw new NullPointerException("File must not be null");
/*      */     }
/* 3107 */     return Files.isSymbolicLink(file.toPath());
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/FileUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */