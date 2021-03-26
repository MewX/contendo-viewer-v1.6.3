/*      */ package org.apache.commons.io;
/*      */ 
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Stack;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class FilenameUtils
/*      */ {
/*      */   private static final int NOT_FOUND = -1;
/*      */   public static final char EXTENSION_SEPARATOR = '.';
/*   96 */   public static final String EXTENSION_SEPARATOR_STR = Character.toString('.');
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final char UNIX_SEPARATOR = '/';
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final char WINDOWS_SEPARATOR = '\\';
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  111 */   private static final char SYSTEM_SEPARATOR = File.separatorChar;
/*      */ 
/*      */   
/*      */   private static final char OTHER_SEPARATOR;
/*      */ 
/*      */   
/*      */   static {
/*  118 */     if (isSystemWindows()) {
/*  119 */       OTHER_SEPARATOR = '/';
/*      */     } else {
/*  121 */       OTHER_SEPARATOR = '\\';
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
/*      */   static boolean isSystemWindows() {
/*  139 */     return (SYSTEM_SEPARATOR == '\\');
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean isSeparator(char ch) {
/*  150 */     return (ch == '/' || ch == '\\');
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
/*      */   public static String normalize(String filename) {
/*  195 */     return doNormalize(filename, SYSTEM_SEPARATOR, true);
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
/*      */   public static String normalize(String filename, boolean unixSeparator) {
/*  242 */     char separator = unixSeparator ? '/' : '\\';
/*  243 */     return doNormalize(filename, separator, true);
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
/*      */   public static String normalizeNoEndSeparator(String filename) {
/*  289 */     return doNormalize(filename, SYSTEM_SEPARATOR, false);
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
/*      */   public static String normalizeNoEndSeparator(String filename, boolean unixSeparator) {
/*  336 */     char separator = unixSeparator ? '/' : '\\';
/*  337 */     return doNormalize(filename, separator, false);
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
/*      */   private static String doNormalize(String filename, char separator, boolean keepSeparator) {
/*  349 */     if (filename == null) {
/*  350 */       return null;
/*      */     }
/*      */     
/*  353 */     failIfNullBytePresent(filename);
/*      */     
/*  355 */     int size = filename.length();
/*  356 */     if (size == 0) {
/*  357 */       return filename;
/*      */     }
/*  359 */     int prefix = getPrefixLength(filename);
/*  360 */     if (prefix < 0) {
/*  361 */       return null;
/*      */     }
/*      */     
/*  364 */     char[] array = new char[size + 2];
/*  365 */     filename.getChars(0, filename.length(), array, 0);
/*      */ 
/*      */     
/*  368 */     char otherSeparator = (separator == SYSTEM_SEPARATOR) ? OTHER_SEPARATOR : SYSTEM_SEPARATOR;
/*  369 */     for (int i = 0; i < array.length; i++) {
/*  370 */       if (array[i] == otherSeparator) {
/*  371 */         array[i] = separator;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  376 */     boolean lastIsDirectory = true;
/*  377 */     if (array[size - 1] != separator) {
/*  378 */       array[size++] = separator;
/*  379 */       lastIsDirectory = false;
/*      */     } 
/*      */     
/*      */     int j;
/*  383 */     for (j = prefix + 1; j < size; j++) {
/*  384 */       if (array[j] == separator && array[j - 1] == separator) {
/*  385 */         System.arraycopy(array, j, array, j - 1, size - j);
/*  386 */         size--;
/*  387 */         j--;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  392 */     for (j = prefix + 1; j < size; j++) {
/*  393 */       if (array[j] == separator && array[j - 1] == '.' && (j == prefix + 1 || array[j - 2] == separator)) {
/*      */         
/*  395 */         if (j == size - 1) {
/*  396 */           lastIsDirectory = true;
/*      */         }
/*  398 */         System.arraycopy(array, j + 1, array, j - 1, size - j);
/*  399 */         size -= 2;
/*  400 */         j--;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  406 */     for (j = prefix + 2; j < size; j++) {
/*  407 */       if (array[j] == separator && array[j - 1] == '.' && array[j - 2] == '.' && (j == prefix + 2 || array[j - 3] == separator)) {
/*      */         
/*  409 */         if (j == prefix + 2) {
/*  410 */           return null;
/*      */         }
/*  412 */         if (j == size - 1) {
/*  413 */           lastIsDirectory = true;
/*      */         }
/*      */         
/*  416 */         int k = j - 4; while (true) { if (k >= prefix) {
/*  417 */             if (array[k] == separator) {
/*      */               
/*  419 */               System.arraycopy(array, j + 1, array, k + 1, size - j);
/*  420 */               size -= j - k;
/*  421 */               j = k + 1; break;
/*      */             } 
/*      */             k--;
/*      */             continue;
/*      */           } 
/*  426 */           System.arraycopy(array, j + 1, array, prefix, size - j);
/*  427 */           size -= j + 1 - prefix;
/*  428 */           j = prefix + 1; break; }
/*      */       
/*      */       } 
/*      */     } 
/*  432 */     if (size <= 0) {
/*  433 */       return "";
/*      */     }
/*  435 */     if (size <= prefix) {
/*  436 */       return new String(array, 0, size);
/*      */     }
/*  438 */     if (lastIsDirectory && keepSeparator) {
/*  439 */       return new String(array, 0, size);
/*      */     }
/*  441 */     return new String(array, 0, size - 1);
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
/*      */   public static String concat(String basePath, String fullFilenameToAdd) {
/*  486 */     int prefix = getPrefixLength(fullFilenameToAdd);
/*  487 */     if (prefix < 0) {
/*  488 */       return null;
/*      */     }
/*  490 */     if (prefix > 0) {
/*  491 */       return normalize(fullFilenameToAdd);
/*      */     }
/*  493 */     if (basePath == null) {
/*  494 */       return null;
/*      */     }
/*  496 */     int len = basePath.length();
/*  497 */     if (len == 0) {
/*  498 */       return normalize(fullFilenameToAdd);
/*      */     }
/*  500 */     char ch = basePath.charAt(len - 1);
/*  501 */     if (isSeparator(ch)) {
/*  502 */       return normalize(basePath + fullFilenameToAdd);
/*      */     }
/*  504 */     return normalize(basePath + '/' + fullFilenameToAdd);
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
/*      */   public static boolean directoryContains(String canonicalParent, String canonicalChild) throws IOException {
/*  535 */     if (canonicalParent == null) {
/*  536 */       throw new IllegalArgumentException("Directory must not be null");
/*      */     }
/*      */     
/*  539 */     if (canonicalChild == null) {
/*  540 */       return false;
/*      */     }
/*      */     
/*  543 */     if (IOCase.SYSTEM.checkEquals(canonicalParent, canonicalChild)) {
/*  544 */       return false;
/*      */     }
/*      */     
/*  547 */     return IOCase.SYSTEM.checkStartsWith(canonicalChild, canonicalParent);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String separatorsToUnix(String path) {
/*  558 */     if (path == null || path.indexOf('\\') == -1) {
/*  559 */       return path;
/*      */     }
/*  561 */     return path.replace('\\', '/');
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String separatorsToWindows(String path) {
/*  571 */     if (path == null || path.indexOf('/') == -1) {
/*  572 */       return path;
/*      */     }
/*  574 */     return path.replace('/', '\\');
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String separatorsToSystem(String path) {
/*  584 */     if (path == null) {
/*  585 */       return null;
/*      */     }
/*  587 */     if (isSystemWindows()) {
/*  588 */       return separatorsToWindows(path);
/*      */     }
/*  590 */     return separatorsToUnix(path);
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
/*      */   public static int getPrefixLength(String filename) {
/*  634 */     if (filename == null) {
/*  635 */       return -1;
/*      */     }
/*  637 */     int len = filename.length();
/*  638 */     if (len == 0) {
/*  639 */       return 0;
/*      */     }
/*  641 */     char ch0 = filename.charAt(0);
/*  642 */     if (ch0 == ':') {
/*  643 */       return -1;
/*      */     }
/*  645 */     if (len == 1) {
/*  646 */       if (ch0 == '~') {
/*  647 */         return 2;
/*      */       }
/*  649 */       return isSeparator(ch0) ? 1 : 0;
/*      */     } 
/*  651 */     if (ch0 == '~') {
/*  652 */       int posUnix = filename.indexOf('/', 1);
/*  653 */       int posWin = filename.indexOf('\\', 1);
/*  654 */       if (posUnix == -1 && posWin == -1) {
/*  655 */         return len + 1;
/*      */       }
/*  657 */       posUnix = (posUnix == -1) ? posWin : posUnix;
/*  658 */       posWin = (posWin == -1) ? posUnix : posWin;
/*  659 */       return Math.min(posUnix, posWin) + 1;
/*      */     } 
/*  661 */     char ch1 = filename.charAt(1);
/*  662 */     if (ch1 == ':') {
/*  663 */       ch0 = Character.toUpperCase(ch0);
/*  664 */       if (ch0 >= 'A' && ch0 <= 'Z') {
/*  665 */         if (len == 2 || !isSeparator(filename.charAt(2))) {
/*  666 */           return 2;
/*      */         }
/*  668 */         return 3;
/*  669 */       }  if (ch0 == '/') {
/*  670 */         return 1;
/*      */       }
/*  672 */       return -1;
/*      */     } 
/*  674 */     if (isSeparator(ch0) && isSeparator(ch1)) {
/*  675 */       int posUnix = filename.indexOf('/', 2);
/*  676 */       int posWin = filename.indexOf('\\', 2);
/*  677 */       if ((posUnix == -1 && posWin == -1) || posUnix == 2 || posWin == 2) {
/*  678 */         return -1;
/*      */       }
/*  680 */       posUnix = (posUnix == -1) ? posWin : posUnix;
/*  681 */       posWin = (posWin == -1) ? posUnix : posWin;
/*  682 */       return Math.min(posUnix, posWin) + 1;
/*      */     } 
/*  684 */     return isSeparator(ch0) ? 1 : 0;
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
/*      */   public static int indexOfLastSeparator(String filename) {
/*  702 */     if (filename == null) {
/*  703 */       return -1;
/*      */     }
/*  705 */     int lastUnixPos = filename.lastIndexOf('/');
/*  706 */     int lastWindowsPos = filename.lastIndexOf('\\');
/*  707 */     return Math.max(lastUnixPos, lastWindowsPos);
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
/*      */   public static int indexOfExtension(String filename) {
/*  725 */     if (filename == null) {
/*  726 */       return -1;
/*      */     }
/*  728 */     int extensionPos = filename.lastIndexOf('.');
/*  729 */     int lastSeparator = indexOfLastSeparator(filename);
/*  730 */     return (lastSeparator > extensionPos) ? -1 : extensionPos;
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
/*      */   public static String getPrefix(String filename) {
/*  764 */     if (filename == null) {
/*  765 */       return null;
/*      */     }
/*  767 */     int len = getPrefixLength(filename);
/*  768 */     if (len < 0) {
/*  769 */       return null;
/*      */     }
/*  771 */     if (len > filename.length()) {
/*  772 */       failIfNullBytePresent(filename + '/');
/*  773 */       return filename + '/';
/*      */     } 
/*  775 */     String path = filename.substring(0, len);
/*  776 */     failIfNullBytePresent(path);
/*  777 */     return path;
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
/*      */   public static String getPath(String filename) {
/*  804 */     return doGetPath(filename, 1);
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
/*      */   public static String getPathNoEndSeparator(String filename) {
/*  832 */     return doGetPath(filename, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String doGetPath(String filename, int separatorAdd) {
/*  843 */     if (filename == null) {
/*  844 */       return null;
/*      */     }
/*  846 */     int prefix = getPrefixLength(filename);
/*  847 */     if (prefix < 0) {
/*  848 */       return null;
/*      */     }
/*  850 */     int index = indexOfLastSeparator(filename);
/*  851 */     int endIndex = index + separatorAdd;
/*  852 */     if (prefix >= filename.length() || index < 0 || prefix >= endIndex) {
/*  853 */       return "";
/*      */     }
/*  855 */     String path = filename.substring(prefix, endIndex);
/*  856 */     failIfNullBytePresent(path);
/*  857 */     return path;
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
/*      */   public static String getFullPath(String filename) {
/*  886 */     return doGetFullPath(filename, true);
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
/*      */   public static String getFullPathNoEndSeparator(String filename) {
/*  916 */     return doGetFullPath(filename, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String doGetFullPath(String filename, boolean includeSeparator) {
/*  927 */     if (filename == null) {
/*  928 */       return null;
/*      */     }
/*  930 */     int prefix = getPrefixLength(filename);
/*  931 */     if (prefix < 0) {
/*  932 */       return null;
/*      */     }
/*  934 */     if (prefix >= filename.length()) {
/*  935 */       if (includeSeparator) {
/*  936 */         return getPrefix(filename);
/*      */       }
/*  938 */       return filename;
/*      */     } 
/*      */     
/*  941 */     int index = indexOfLastSeparator(filename);
/*  942 */     if (index < 0) {
/*  943 */       return filename.substring(0, prefix);
/*      */     }
/*  945 */     int end = index + (includeSeparator ? 1 : 0);
/*  946 */     if (end == 0) {
/*  947 */       end++;
/*      */     }
/*  949 */     return filename.substring(0, end);
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
/*      */   public static String getName(String filename) {
/*  971 */     if (filename == null) {
/*  972 */       return null;
/*      */     }
/*  974 */     failIfNullBytePresent(filename);
/*  975 */     int index = indexOfLastSeparator(filename);
/*  976 */     return filename.substring(index + 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void failIfNullBytePresent(String path) {
/*  986 */     int len = path.length();
/*  987 */     for (int i = 0; i < len; i++) {
/*  988 */       if (path.charAt(i) == '\000') {
/*  989 */         throw new IllegalArgumentException("Null byte present in file/path name. There are no known legitimate use cases for such data, but several injection attacks may use it");
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
/*      */   public static String getBaseName(String filename) {
/* 1014 */     return removeExtension(getName(filename));
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
/*      */   public static String getExtension(String filename) {
/* 1036 */     if (filename == null) {
/* 1037 */       return null;
/*      */     }
/* 1039 */     int index = indexOfExtension(filename);
/* 1040 */     if (index == -1) {
/* 1041 */       return "";
/*      */     }
/* 1043 */     return filename.substring(index + 1);
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
/*      */   public static String removeExtension(String filename) {
/* 1066 */     if (filename == null) {
/* 1067 */       return null;
/*      */     }
/* 1069 */     failIfNullBytePresent(filename);
/*      */     
/* 1071 */     int index = indexOfExtension(filename);
/* 1072 */     if (index == -1) {
/* 1073 */       return filename;
/*      */     }
/* 1075 */     return filename.substring(0, index);
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
/*      */   public static boolean equals(String filename1, String filename2) {
/* 1092 */     return equals(filename1, filename2, false, IOCase.SENSITIVE);
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
/*      */   public static boolean equalsOnSystem(String filename1, String filename2) {
/* 1107 */     return equals(filename1, filename2, false, IOCase.SYSTEM);
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
/*      */   public static boolean equalsNormalized(String filename1, String filename2) {
/* 1123 */     return equals(filename1, filename2, true, IOCase.SENSITIVE);
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
/*      */   public static boolean equalsNormalizedOnSystem(String filename1, String filename2) {
/* 1140 */     return equals(filename1, filename2, true, IOCase.SYSTEM);
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
/*      */   public static boolean equals(String filename1, String filename2, boolean normalized, IOCase caseSensitivity) {
/* 1158 */     if (filename1 == null || filename2 == null) {
/* 1159 */       return (filename1 == null && filename2 == null);
/*      */     }
/* 1161 */     if (normalized) {
/* 1162 */       filename1 = normalize(filename1);
/* 1163 */       filename2 = normalize(filename2);
/* 1164 */       if (filename1 == null || filename2 == null) {
/* 1165 */         throw new NullPointerException("Error normalizing one or both of the file names");
/*      */       }
/*      */     } 
/*      */     
/* 1169 */     if (caseSensitivity == null) {
/* 1170 */       caseSensitivity = IOCase.SENSITIVE;
/*      */     }
/* 1172 */     return caseSensitivity.checkEquals(filename1, filename2);
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
/*      */   public static boolean isExtension(String filename, String extension) {
/* 1189 */     if (filename == null) {
/* 1190 */       return false;
/*      */     }
/* 1192 */     failIfNullBytePresent(filename);
/*      */     
/* 1194 */     if (extension == null || extension.isEmpty()) {
/* 1195 */       return (indexOfExtension(filename) == -1);
/*      */     }
/* 1197 */     String fileExt = getExtension(filename);
/* 1198 */     return fileExt.equals(extension);
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
/*      */   public static boolean isExtension(String filename, String[] extensions) {
/* 1214 */     if (filename == null) {
/* 1215 */       return false;
/*      */     }
/* 1217 */     failIfNullBytePresent(filename);
/*      */     
/* 1219 */     if (extensions == null || extensions.length == 0) {
/* 1220 */       return (indexOfExtension(filename) == -1);
/*      */     }
/* 1222 */     String fileExt = getExtension(filename);
/* 1223 */     for (String extension : extensions) {
/* 1224 */       if (fileExt.equals(extension)) {
/* 1225 */         return true;
/*      */       }
/*      */     } 
/* 1228 */     return false;
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
/*      */   public static boolean isExtension(String filename, Collection<String> extensions) {
/* 1244 */     if (filename == null) {
/* 1245 */       return false;
/*      */     }
/* 1247 */     failIfNullBytePresent(filename);
/*      */     
/* 1249 */     if (extensions == null || extensions.isEmpty()) {
/* 1250 */       return (indexOfExtension(filename) == -1);
/*      */     }
/* 1252 */     String fileExt = getExtension(filename);
/* 1253 */     for (String extension : extensions) {
/* 1254 */       if (fileExt.equals(extension)) {
/* 1255 */         return true;
/*      */       }
/*      */     } 
/* 1258 */     return false;
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
/*      */   public static boolean wildcardMatch(String filename, String wildcardMatcher) {
/* 1285 */     return wildcardMatch(filename, wildcardMatcher, IOCase.SENSITIVE);
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
/*      */   public static boolean wildcardMatchOnSystem(String filename, String wildcardMatcher) {
/* 1311 */     return wildcardMatch(filename, wildcardMatcher, IOCase.SYSTEM);
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
/*      */   public static boolean wildcardMatch(String filename, String wildcardMatcher, IOCase caseSensitivity) {
/* 1329 */     if (filename == null && wildcardMatcher == null) {
/* 1330 */       return true;
/*      */     }
/* 1332 */     if (filename == null || wildcardMatcher == null) {
/* 1333 */       return false;
/*      */     }
/* 1335 */     if (caseSensitivity == null) {
/* 1336 */       caseSensitivity = IOCase.SENSITIVE;
/*      */     }
/* 1338 */     String[] wcs = splitOnTokens(wildcardMatcher);
/* 1339 */     boolean anyChars = false;
/* 1340 */     int textIdx = 0;
/* 1341 */     int wcsIdx = 0;
/* 1342 */     Stack<int[]> backtrack = (Stack)new Stack<>();
/*      */ 
/*      */     
/*      */     do {
/* 1346 */       if (backtrack.size() > 0) {
/* 1347 */         int[] array = backtrack.pop();
/* 1348 */         wcsIdx = array[0];
/* 1349 */         textIdx = array[1];
/* 1350 */         anyChars = true;
/*      */       } 
/*      */ 
/*      */       
/* 1354 */       while (wcsIdx < wcs.length) {
/*      */         
/* 1356 */         if (wcs[wcsIdx].equals("?")) {
/*      */           
/* 1358 */           textIdx++;
/* 1359 */           if (textIdx > filename.length()) {
/*      */             break;
/*      */           }
/* 1362 */           anyChars = false;
/*      */         }
/* 1364 */         else if (wcs[wcsIdx].equals("*")) {
/*      */           
/* 1366 */           anyChars = true;
/* 1367 */           if (wcsIdx == wcs.length - 1) {
/* 1368 */             textIdx = filename.length();
/*      */           }
/*      */         }
/*      */         else {
/*      */           
/* 1373 */           if (anyChars) {
/*      */             
/* 1375 */             textIdx = caseSensitivity.checkIndexOf(filename, textIdx, wcs[wcsIdx]);
/* 1376 */             if (textIdx == -1) {
/*      */               break;
/*      */             }
/*      */             
/* 1380 */             int repeat = caseSensitivity.checkIndexOf(filename, textIdx + 1, wcs[wcsIdx]);
/* 1381 */             if (repeat >= 0) {
/* 1382 */               backtrack.push(new int[] { wcsIdx, repeat });
/*      */             
/*      */             }
/*      */           }
/* 1386 */           else if (!caseSensitivity.checkRegionMatches(filename, textIdx, wcs[wcsIdx])) {
/*      */             break;
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1393 */           textIdx += wcs[wcsIdx].length();
/* 1394 */           anyChars = false;
/*      */         } 
/*      */         
/* 1397 */         wcsIdx++;
/*      */       } 
/*      */ 
/*      */       
/* 1401 */       if (wcsIdx == wcs.length && textIdx == filename.length()) {
/* 1402 */         return true;
/*      */       }
/*      */     }
/* 1405 */     while (backtrack.size() > 0);
/*      */     
/* 1407 */     return false;
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
/*      */   static String[] splitOnTokens(String text) {
/* 1422 */     if (text.indexOf('?') == -1 && text.indexOf('*') == -1) {
/* 1423 */       return new String[] { text };
/*      */     }
/*      */     
/* 1426 */     char[] array = text.toCharArray();
/* 1427 */     ArrayList<String> list = new ArrayList<>();
/* 1428 */     StringBuilder buffer = new StringBuilder();
/* 1429 */     char prevChar = Character.MIN_VALUE;
/* 1430 */     for (char ch : array) {
/* 1431 */       if (ch == '?' || ch == '*') {
/* 1432 */         if (buffer.length() != 0) {
/* 1433 */           list.add(buffer.toString());
/* 1434 */           buffer.setLength(0);
/*      */         } 
/* 1436 */         if (ch == '?') {
/* 1437 */           list.add("?");
/* 1438 */         } else if (prevChar != '*') {
/* 1439 */           list.add("*");
/*      */         } 
/*      */       } else {
/* 1442 */         buffer.append(ch);
/*      */       } 
/* 1444 */       prevChar = ch;
/*      */     } 
/* 1446 */     if (buffer.length() != 0) {
/* 1447 */       list.add(buffer.toString());
/*      */     }
/*      */     
/* 1450 */     return list.<String>toArray(new String[list.size()]);
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/FilenameUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */