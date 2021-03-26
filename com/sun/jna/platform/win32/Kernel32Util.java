/*      */ package com.sun.jna.platform.win32;
/*      */ 
/*      */ import com.sun.jna.LastErrorException;
/*      */ import com.sun.jna.Memory;
/*      */ import com.sun.jna.Native;
/*      */ import com.sun.jna.Pointer;
/*      */ import com.sun.jna.Structure;
/*      */ import com.sun.jna.ptr.IntByReference;
/*      */ import com.sun.jna.ptr.PointerByReference;
/*      */ import com.sun.jna.win32.W32APITypeMapper;
/*      */ import java.io.File;
/*      */ import java.io.FileNotFoundException;
/*      */ import java.nio.ByteOrder;
/*      */ import java.util.ArrayList;
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.TreeMap;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class Kernel32Util
/*      */   implements WinDef
/*      */ {
/*      */   public static final String VOLUME_GUID_PATH_PREFIX = "\\\\?\\Volume{";
/*      */   public static final String VOLUME_GUID_PATH_SUFFIX = "}\\";
/*      */   
/*      */   public static String getComputerName() {
/*   63 */     char[] buffer = new char[WinBase.MAX_COMPUTERNAME_LENGTH + 1];
/*   64 */     IntByReference lpnSize = new IntByReference(buffer.length);
/*   65 */     if (!Kernel32.INSTANCE.GetComputerName(buffer, lpnSize)) {
/*   66 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*   68 */     return Native.toString(buffer);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void freeLocalMemory(Pointer ptr) {
/*   78 */     Pointer res = Kernel32.INSTANCE.LocalFree(ptr);
/*   79 */     if (res != null) {
/*   80 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void freeGlobalMemory(Pointer ptr) {
/*   91 */     Pointer res = Kernel32.INSTANCE.GlobalFree(ptr);
/*   92 */     if (res != null) {
/*   93 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
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
/*      */   public static void closeHandleRefs(WinNT.HANDLEByReference... refs) {
/*  108 */     Win32Exception err = null;
/*  109 */     for (WinNT.HANDLEByReference r : refs) {
/*      */       try {
/*  111 */         closeHandleRef(r);
/*  112 */       } catch (Win32Exception e) {
/*  113 */         if (err == null) {
/*  114 */           err = e;
/*      */         } else {
/*  116 */           err.addSuppressedReflected((Throwable)e);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  121 */     if (err != null) {
/*  122 */       throw err;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void closeHandleRef(WinNT.HANDLEByReference ref) {
/*  132 */     closeHandle((ref == null) ? null : ref.getValue());
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
/*      */   public static void closeHandles(WinNT.HANDLE... handles) {
/*  146 */     Win32Exception err = null;
/*  147 */     for (WinNT.HANDLE h : handles) {
/*      */       try {
/*  149 */         closeHandle(h);
/*  150 */       } catch (Win32Exception e) {
/*  151 */         if (err == null) {
/*  152 */           err = e;
/*      */         } else {
/*  154 */           err.addSuppressedReflected((Throwable)e);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  159 */     if (err != null) {
/*  160 */       throw err;
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
/*      */   public static void closeHandle(WinNT.HANDLE h) {
/*  172 */     if (h == null) {
/*      */       return;
/*      */     }
/*      */     
/*  176 */     if (!Kernel32.INSTANCE.CloseHandle(h)) {
/*  177 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
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
/*      */   public static String formatMessage(int code) {
/*  189 */     PointerByReference buffer = new PointerByReference();
/*  190 */     int nLen = Kernel32.INSTANCE.FormatMessage(4864, null, code, 0, buffer, 0, null);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  198 */     if (nLen == 0) {
/*  199 */       throw new LastErrorException(Native.getLastError());
/*      */     }
/*      */     
/*  202 */     Pointer ptr = buffer.getValue();
/*      */     try {
/*  204 */       String s = ptr.getWideString(0L);
/*  205 */       return s.trim();
/*      */     } finally {
/*  207 */       freeLocalMemory(ptr);
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
/*      */   public static String formatMessage(WinNT.HRESULT code) {
/*  219 */     return formatMessage(code.intValue());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String formatMessageFromLastErrorCode(int code) {
/*  230 */     return formatMessage(W32Errors.HRESULT_FROM_WIN32(code));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getLastErrorMessage() {
/*  238 */     return formatMessageFromLastErrorCode(Kernel32.INSTANCE
/*  239 */         .GetLastError());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getTempPath() {
/*  248 */     WinDef.DWORD nBufferLength = new WinDef.DWORD(260L);
/*  249 */     char[] buffer = new char[nBufferLength.intValue()];
/*  250 */     if (Kernel32.INSTANCE.GetTempPath(nBufferLength, buffer).intValue() == 0) {
/*  251 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*  253 */     return Native.toString(buffer);
/*      */   }
/*      */   
/*      */   public static void deleteFile(String filename) {
/*  257 */     if (!Kernel32.INSTANCE.DeleteFile(filename)) {
/*  258 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<String> getLogicalDriveStrings() {
/*  268 */     WinDef.DWORD dwSize = Kernel32.INSTANCE.GetLogicalDriveStrings(new WinDef.DWORD(0L), null);
/*  269 */     if (dwSize.intValue() <= 0) {
/*  270 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*      */     
/*  273 */     char[] buf = new char[dwSize.intValue()];
/*  274 */     dwSize = Kernel32.INSTANCE.GetLogicalDriveStrings(dwSize, buf);
/*  275 */     int bufSize = dwSize.intValue();
/*  276 */     if (bufSize <= 0) {
/*  277 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*      */     
/*  280 */     return Native.toStringList(buf, 0, bufSize);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getFileAttributes(String fileName) {
/*  291 */     int fileAttributes = Kernel32.INSTANCE.GetFileAttributes(fileName);
/*  292 */     if (fileAttributes == -1) {
/*  293 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*  295 */     return fileAttributes;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getFileType(String fileName) throws FileNotFoundException {
/*  305 */     File f = new File(fileName);
/*  306 */     if (!f.exists()) {
/*  307 */       throw new FileNotFoundException(fileName);
/*      */     }
/*      */     
/*  310 */     WinNT.HANDLE hFile = null;
/*  311 */     Win32Exception err = null; try {
/*      */       int rc;
/*  313 */       hFile = Kernel32.INSTANCE.CreateFile(fileName, -2147483648, 1, new WinBase.SECURITY_ATTRIBUTES(), 3, 128, (new WinNT.HANDLEByReference())
/*      */ 
/*      */           
/*  316 */           .getValue());
/*      */       
/*  318 */       if (WinBase.INVALID_HANDLE_VALUE.equals(hFile)) {
/*  319 */         throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */       }
/*      */       
/*  322 */       int type = Kernel32.INSTANCE.GetFileType(hFile);
/*  323 */       switch (type) {
/*      */         case 0:
/*  325 */           rc = Kernel32.INSTANCE.GetLastError();
/*  326 */           switch (rc) {
/*      */             case 0:
/*      */               break;
/*      */           } 
/*  330 */           throw new Win32Exception(rc);
/*      */       } 
/*      */       
/*  333 */       return type;
/*      */     }
/*  335 */     catch (Win32Exception e) {
/*  336 */       err = e;
/*  337 */       throw err;
/*      */     } finally {
/*      */       try {
/*  340 */         closeHandle(hFile);
/*  341 */       } catch (Win32Exception e) {
/*  342 */         if (err == null) {
/*  343 */           err = e;
/*      */         } else {
/*  345 */           err.addSuppressedReflected((Throwable)e);
/*      */         } 
/*      */       } 
/*      */       
/*  349 */       if (err != null) {
/*  350 */         throw err;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getDriveType(String rootName) {
/*  360 */     return Kernel32.INSTANCE.GetDriveType(rootName);
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
/*      */   public static String getEnvironmentVariable(String name) {
/*  372 */     int size = Kernel32.INSTANCE.GetEnvironmentVariable(name, null, 0);
/*  373 */     if (size == 0)
/*  374 */       return null; 
/*  375 */     if (size < 0) {
/*  376 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*      */     
/*  379 */     char[] buffer = new char[size];
/*  380 */     size = Kernel32.INSTANCE.GetEnvironmentVariable(name, buffer, buffer.length);
/*      */     
/*  382 */     if (size <= 0) {
/*  383 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*  385 */     return Native.toString(buffer);
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
/*      */   public static Map<String, String> getEnvironmentVariables() {
/*  397 */     Pointer lpszEnvironmentBlock = Kernel32.INSTANCE.GetEnvironmentStrings();
/*  398 */     if (lpszEnvironmentBlock == null) {
/*  399 */       throw new LastErrorException(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*      */     
/*      */     try {
/*  403 */       return getEnvironmentVariables(lpszEnvironmentBlock, 0L);
/*      */     } finally {
/*  405 */       if (!Kernel32.INSTANCE.FreeEnvironmentStrings(lpszEnvironmentBlock)) {
/*  406 */         throw new LastErrorException(Kernel32.INSTANCE.GetLastError());
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
/*      */   public static Map<String, String> getEnvironmentVariables(Pointer lpszEnvironmentBlock, long offset) {
/*  423 */     if (lpszEnvironmentBlock == null) {
/*  424 */       return null;
/*      */     }
/*      */     
/*  427 */     Map<String, String> vars = new TreeMap<String, String>();
/*  428 */     boolean asWideChars = isWideCharEnvironmentStringBlock(lpszEnvironmentBlock, offset);
/*  429 */     long stepFactor = asWideChars ? 2L : 1L;
/*  430 */     long curOffset = offset; while (true) {
/*  431 */       String nvp = readEnvironmentStringBlockEntry(lpszEnvironmentBlock, curOffset, asWideChars);
/*  432 */       int len = nvp.length();
/*  433 */       if (len == 0) {
/*      */         break;
/*      */       }
/*      */       
/*  437 */       int pos = nvp.indexOf('=');
/*  438 */       if (pos < 0) {
/*  439 */         throw new IllegalArgumentException("Missing variable value separator in " + nvp);
/*      */       }
/*      */       
/*  442 */       String name = nvp.substring(0, pos), value = nvp.substring(pos + 1);
/*  443 */       vars.put(name, value);
/*      */       
/*  445 */       curOffset += (len + 1) * stepFactor;
/*      */     } 
/*      */     
/*  448 */     return vars;
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
/*      */   public static String readEnvironmentStringBlockEntry(Pointer lpszEnvironmentBlock, long offset, boolean asWideChars) {
/*  464 */     long endOffset = findEnvironmentStringBlockEntryEnd(lpszEnvironmentBlock, offset, asWideChars);
/*  465 */     int dataLen = (int)(endOffset - offset);
/*  466 */     if (dataLen == 0) {
/*  467 */       return "";
/*      */     }
/*      */     
/*  470 */     int charsLen = asWideChars ? (dataLen / 2) : dataLen;
/*  471 */     char[] chars = new char[charsLen];
/*  472 */     long curOffset = offset, stepSize = asWideChars ? 2L : 1L;
/*  473 */     ByteOrder byteOrder = ByteOrder.nativeOrder();
/*  474 */     for (int index = 0; index < chars.length; index++, curOffset += stepSize) {
/*  475 */       byte b = lpszEnvironmentBlock.getByte(curOffset);
/*  476 */       if (asWideChars) {
/*  477 */         byte x = lpszEnvironmentBlock.getByte(curOffset + 1L);
/*  478 */         if (ByteOrder.LITTLE_ENDIAN.equals(byteOrder)) {
/*  479 */           chars[index] = (char)(x << 8 & 0xFF00 | b & 0xFF);
/*      */         } else {
/*  481 */           chars[index] = (char)(b << 8 & 0xFF00 | x & 0xFF);
/*      */         } 
/*      */       } else {
/*  484 */         chars[index] = (char)(b & 0xFF);
/*      */       } 
/*      */     } 
/*      */     
/*  488 */     return new String(chars);
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
/*      */   public static long findEnvironmentStringBlockEntryEnd(Pointer lpszEnvironmentBlock, long offset, boolean asWideChars) {
/*      */     long curOffset;
/*      */     long stepSize;
/*  504 */     for (curOffset = offset, stepSize = asWideChars ? 2L : 1L;; curOffset += stepSize) {
/*  505 */       byte b = lpszEnvironmentBlock.getByte(curOffset);
/*  506 */       if (b == 0) {
/*  507 */         return curOffset;
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
/*      */   public static boolean isWideCharEnvironmentStringBlock(Pointer lpszEnvironmentBlock, long offset) {
/*  540 */     byte b0 = lpszEnvironmentBlock.getByte(offset);
/*  541 */     byte b1 = lpszEnvironmentBlock.getByte(offset + 1L);
/*  542 */     ByteOrder byteOrder = ByteOrder.nativeOrder();
/*  543 */     if (ByteOrder.LITTLE_ENDIAN.equals(byteOrder)) {
/*  544 */       return isWideCharEnvironmentStringBlock(b1);
/*      */     }
/*  546 */     return isWideCharEnvironmentStringBlock(b0);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean isWideCharEnvironmentStringBlock(byte charsetIndicator) {
/*  552 */     if (charsetIndicator != 0) {
/*  553 */       return false;
/*      */     }
/*  555 */     return true;
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
/*      */   public static final int getPrivateProfileInt(String appName, String keyName, int defaultValue, String fileName) {
/*  581 */     return Kernel32.INSTANCE.GetPrivateProfileInt(appName, keyName, defaultValue, fileName);
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String getPrivateProfileString(String lpAppName, String lpKeyName, String lpDefault, String lpFileName) {
/*  636 */     char[] buffer = new char[1024];
/*  637 */     Kernel32.INSTANCE.GetPrivateProfileString(lpAppName, lpKeyName, lpDefault, buffer, new WinDef.DWORD(buffer.length), lpFileName);
/*      */     
/*  639 */     return Native.toString(buffer);
/*      */   }
/*      */ 
/*      */   
/*      */   public static final void writePrivateProfileString(String appName, String keyName, String string, String fileName) {
/*  644 */     if (!Kernel32.INSTANCE.WritePrivateProfileString(appName, keyName, string, fileName))
/*      */     {
/*  646 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final WinNT.SYSTEM_LOGICAL_PROCESSOR_INFORMATION[] getLogicalProcessorInformation() {
/*      */     Memory memory;
/*  657 */     int sizePerStruct = (new WinNT.SYSTEM_LOGICAL_PROCESSOR_INFORMATION()).size();
/*  658 */     WinDef.DWORDByReference bufferSize = new WinDef.DWORDByReference(new WinDef.DWORD(sizePerStruct));
/*      */ 
/*      */     
/*      */     while (true) {
/*  662 */       memory = new Memory(bufferSize.getValue().intValue());
/*  663 */       if (!Kernel32.INSTANCE.GetLogicalProcessorInformation((Pointer)memory, bufferSize)) {
/*      */         
/*  665 */         int err = Kernel32.INSTANCE.GetLastError();
/*  666 */         if (err != 122)
/*  667 */           throw new Win32Exception(err); 
/*      */         continue;
/*      */       } 
/*      */       break;
/*      */     } 
/*  672 */     WinNT.SYSTEM_LOGICAL_PROCESSOR_INFORMATION firstInformation = new WinNT.SYSTEM_LOGICAL_PROCESSOR_INFORMATION((Pointer)memory);
/*      */     
/*  674 */     int returnedStructCount = bufferSize.getValue().intValue() / sizePerStruct;
/*      */     
/*  676 */     return (WinNT.SYSTEM_LOGICAL_PROCESSOR_INFORMATION[])firstInformation
/*  677 */       .toArray((Structure[])new WinNT.SYSTEM_LOGICAL_PROCESSOR_INFORMATION[returnedStructCount]);
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
/*      */   public static final WinNT.SYSTEM_LOGICAL_PROCESSOR_INFORMATION_EX[] getLogicalProcessorInformationEx(int relationshipType) {
/*      */     Memory memory;
/*  698 */     WinDef.DWORDByReference bufferSize = new WinDef.DWORDByReference(new WinDef.DWORD(1L));
/*      */     
/*      */     while (true) {
/*  701 */       memory = new Memory(bufferSize.getValue().intValue());
/*  702 */       if (!Kernel32.INSTANCE.GetLogicalProcessorInformationEx(relationshipType, (Pointer)memory, bufferSize)) {
/*  703 */         int err = Kernel32.INSTANCE.GetLastError();
/*  704 */         if (err != 122) {
/*  705 */           throw new Win32Exception(err);
/*      */         }
/*      */         continue;
/*      */       } 
/*      */       break;
/*      */     } 
/*  711 */     List<WinNT.SYSTEM_LOGICAL_PROCESSOR_INFORMATION_EX> procInfoList = new ArrayList<WinNT.SYSTEM_LOGICAL_PROCESSOR_INFORMATION_EX>();
/*  712 */     int offset = 0;
/*  713 */     while (offset < bufferSize.getValue().intValue()) {
/*      */       
/*  715 */       WinNT.SYSTEM_LOGICAL_PROCESSOR_INFORMATION_EX information = WinNT.SYSTEM_LOGICAL_PROCESSOR_INFORMATION_EX.fromPointer(memory.share(offset));
/*  716 */       procInfoList.add(information);
/*  717 */       offset += information.size;
/*      */     } 
/*  719 */     return procInfoList.<WinNT.SYSTEM_LOGICAL_PROCESSOR_INFORMATION_EX>toArray(new WinNT.SYSTEM_LOGICAL_PROCESSOR_INFORMATION_EX[0]);
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
/*      */   public static final String[] getPrivateProfileSection(String appName, String fileName) {
/*  740 */     char[] buffer = new char[32768];
/*  741 */     if (Kernel32.INSTANCE.GetPrivateProfileSection(appName, buffer, new WinDef.DWORD(buffer.length), fileName).intValue() == 0) {
/*  742 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*  744 */     return (new String(buffer)).split("\000");
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
/*      */   public static final String[] getPrivateProfileSectionNames(String fileName) {
/*  759 */     char[] buffer = new char[65536];
/*  760 */     if (Kernel32.INSTANCE.GetPrivateProfileSectionNames(buffer, new WinDef.DWORD(buffer.length), fileName).intValue() == 0) {
/*  761 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*  763 */     return (new String(buffer)).split("\000");
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
/*      */   public static final void writePrivateProfileSection(String appName, String[] strings, String fileName) {
/*  776 */     StringBuilder buffer = new StringBuilder();
/*  777 */     for (String string : strings)
/*  778 */       buffer.append(string).append(false); 
/*  779 */     buffer.append(false);
/*  780 */     if (!Kernel32.INSTANCE.WritePrivateProfileSection(appName, buffer.toString(), fileName)) {
/*  781 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
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
/*      */   public static final List<String> queryDosDevice(String lpszDeviceName, int maxTargetSize) {
/*  793 */     char[] lpTargetPath = new char[maxTargetSize];
/*  794 */     int dwSize = Kernel32.INSTANCE.QueryDosDevice(lpszDeviceName, lpTargetPath, lpTargetPath.length);
/*  795 */     if (dwSize == 0) {
/*  796 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*      */     
/*  799 */     return Native.toStringList(lpTargetPath, 0, dwSize);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final List<String> getVolumePathNamesForVolumeName(String lpszVolumeName) {
/*  809 */     char[] lpszVolumePathNames = new char[261];
/*  810 */     IntByReference lpcchReturnLength = new IntByReference();
/*      */     
/*  812 */     if (!Kernel32.INSTANCE.GetVolumePathNamesForVolumeName(lpszVolumeName, lpszVolumePathNames, lpszVolumePathNames.length, lpcchReturnLength)) {
/*  813 */       int hr = Kernel32.INSTANCE.GetLastError();
/*  814 */       if (hr != 234) {
/*  815 */         throw new Win32Exception(hr);
/*      */       }
/*      */       
/*  818 */       int required = lpcchReturnLength.getValue();
/*  819 */       lpszVolumePathNames = new char[required];
/*      */       
/*  821 */       if (!Kernel32.INSTANCE.GetVolumePathNamesForVolumeName(lpszVolumeName, lpszVolumePathNames, lpszVolumePathNames.length, lpcchReturnLength)) {
/*  822 */         throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */       }
/*      */     } 
/*      */     
/*  826 */     int bufSize = lpcchReturnLength.getValue();
/*  827 */     return Native.toStringList(lpszVolumePathNames, 0, bufSize);
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
/*      */   public static final String extractVolumeGUID(String volumeGUIDPath) {
/*  847 */     if (volumeGUIDPath == null || volumeGUIDPath
/*  848 */       .length() <= "\\\\?\\Volume{".length() + "}\\".length() || 
/*  849 */       !volumeGUIDPath.startsWith("\\\\?\\Volume{") || 
/*  850 */       !volumeGUIDPath.endsWith("}\\")) {
/*  851 */       throw new IllegalArgumentException("Bad volume GUID path format: " + volumeGUIDPath);
/*      */     }
/*      */     
/*  854 */     return volumeGUIDPath.substring("\\\\?\\Volume{".length(), volumeGUIDPath.length() - "}\\".length());
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
/*      */   public static final String QueryFullProcessImageName(WinNT.HANDLE hProcess, int dwFlags) {
/*  871 */     char[] path = new char[260];
/*  872 */     IntByReference lpdwSize = new IntByReference(path.length);
/*  873 */     if (Kernel32.INSTANCE.QueryFullProcessImageName(hProcess, 0, path, lpdwSize))
/*  874 */       return (new String(path)).substring(0, lpdwSize.getValue()); 
/*  875 */     throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
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
/*      */   public static byte[] getResource(String path, String type, String name) {
/*  892 */     WinDef.HMODULE target = Kernel32.INSTANCE.LoadLibraryEx(path, null, 2);
/*      */     
/*  894 */     if (target == null) {
/*  895 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*      */     
/*  898 */     Win32Exception err = null;
/*  899 */     Pointer start = null;
/*  900 */     int length = 0;
/*  901 */     byte[] results = null; try {
/*      */       Memory memory1, memory2;
/*  903 */       Pointer t = null;
/*      */       try {
/*  905 */         t = new Pointer(Long.parseLong(type));
/*  906 */       } catch (NumberFormatException e) {
/*  907 */         memory1 = new Memory((Native.WCHAR_SIZE * (type.length() + 1)));
/*  908 */         memory1.setWideString(0L, type);
/*      */       } 
/*      */       
/*  911 */       Pointer n = null;
/*      */       try {
/*  913 */         n = new Pointer(Long.parseLong(name));
/*  914 */       } catch (NumberFormatException e) {
/*  915 */         memory2 = new Memory((Native.WCHAR_SIZE * (name.length() + 1)));
/*  916 */         memory2.setWideString(0L, name);
/*      */       } 
/*      */       
/*  919 */       WinDef.HRSRC hrsrc = Kernel32.INSTANCE.FindResource(target, (Pointer)memory2, (Pointer)memory1);
/*  920 */       if (hrsrc == null) {
/*  921 */         throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */       }
/*      */ 
/*      */       
/*  925 */       WinNT.HANDLE loaded = Kernel32.INSTANCE.LoadResource(target, hrsrc);
/*  926 */       if (loaded == null) {
/*  927 */         throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */       }
/*      */       
/*  930 */       length = Kernel32.INSTANCE.SizeofResource(target, hrsrc);
/*  931 */       if (length == 0) {
/*  932 */         throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  937 */       start = Kernel32.INSTANCE.LockResource(loaded);
/*  938 */       if (start == null) {
/*  939 */         throw new IllegalStateException("LockResource returned null.");
/*      */       }
/*      */       
/*  942 */       results = start.getByteArray(0L, length);
/*  943 */     } catch (Win32Exception we) {
/*  944 */       err = we;
/*      */     } finally {
/*      */       
/*  947 */       if (target != null && 
/*  948 */         !Kernel32.INSTANCE.FreeLibrary(target)) {
/*  949 */         Win32Exception we = new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*  950 */         if (err != null) {
/*  951 */           we.addSuppressedReflected((Throwable)err);
/*      */         }
/*  953 */         throw we;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  958 */     if (err != null) {
/*  959 */       throw err;
/*      */     }
/*      */     
/*  962 */     return results;
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
/*      */   public static Map<String, List<String>> getResourceNames(String path) {
/*  975 */     WinDef.HMODULE target = Kernel32.INSTANCE.LoadLibraryEx(path, null, 2);
/*      */     
/*  977 */     if (target == null) {
/*  978 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*      */     
/*  981 */     final List<String> types = new ArrayList<String>();
/*  982 */     final Map<String, List<String>> result = new LinkedHashMap<String, List<String>>();
/*      */     
/*  984 */     WinBase.EnumResTypeProc ertp = new WinBase.EnumResTypeProc()
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public boolean invoke(WinDef.HMODULE module, Pointer type, Pointer lParam)
/*      */         {
/*  992 */           if (Pointer.nativeValue(type) <= 65535L) {
/*  993 */             types.add(Pointer.nativeValue(type) + "");
/*      */           } else {
/*  995 */             types.add(type.getWideString(0L));
/*      */           } 
/*  997 */           return true;
/*      */         }
/*      */       };
/*      */     
/* 1001 */     WinBase.EnumResNameProc ernp = new WinBase.EnumResNameProc()
/*      */       {
/*      */         public boolean invoke(WinDef.HMODULE module, Pointer type, Pointer name, Pointer lParam)
/*      */         {
/* 1005 */           String typeName = "";
/*      */           
/* 1007 */           if (Pointer.nativeValue(type) <= 65535L) {
/* 1008 */             typeName = Pointer.nativeValue(type) + "";
/*      */           } else {
/* 1010 */             typeName = type.getWideString(0L);
/*      */           } 
/*      */           
/* 1013 */           if (Pointer.nativeValue(name) < 65535L) {
/* 1014 */             ((List<String>)result.get(typeName)).add(Pointer.nativeValue(name) + "");
/*      */           } else {
/* 1016 */             ((List<String>)result.get(typeName)).add(name.getWideString(0L));
/*      */           } 
/*      */           
/* 1019 */           return true;
/*      */         }
/*      */       };
/*      */ 
/*      */     
/* 1024 */     Win32Exception err = null;
/*      */     try {
/* 1026 */       if (!Kernel32.INSTANCE.EnumResourceTypes(target, ertp, null)) {
/* 1027 */         throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */       }
/*      */       
/* 1030 */       for (String typeName : types) {
/* 1031 */         Memory memory; result.put(typeName, new ArrayList<String>());
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1036 */         Pointer pointer = null;
/*      */         try {
/* 1038 */           pointer = new Pointer(Long.parseLong(typeName));
/* 1039 */         } catch (NumberFormatException e) {
/* 1040 */           memory = new Memory((Native.WCHAR_SIZE * (typeName.length() + 1)));
/* 1041 */           memory.setWideString(0L, typeName);
/*      */         } 
/*      */         
/* 1044 */         boolean callResult = Kernel32.INSTANCE.EnumResourceNames(target, (Pointer)memory, ernp, null);
/*      */         
/* 1046 */         if (!callResult) {
/* 1047 */           throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */         }
/*      */       } 
/* 1050 */     } catch (Win32Exception e) {
/* 1051 */       err = e;
/*      */     }
/*      */     finally {
/*      */       
/* 1055 */       if (target != null && 
/* 1056 */         !Kernel32.INSTANCE.FreeLibrary(target)) {
/* 1057 */         Win32Exception we = new Win32Exception(Kernel32.INSTANCE.GetLastError());
/* 1058 */         if (err != null) {
/* 1059 */           we.addSuppressedReflected((Throwable)err);
/*      */         }
/* 1061 */         throw we;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1066 */     if (err != null) {
/* 1067 */       throw err;
/*      */     }
/* 1069 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<Tlhelp32.MODULEENTRY32W> getModules(int processID) {
/* 1080 */     WinNT.HANDLE snapshot = Kernel32.INSTANCE.CreateToolhelp32Snapshot(Tlhelp32.TH32CS_SNAPMODULE, new WinDef.DWORD(processID));
/* 1081 */     if (snapshot == null) {
/* 1082 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*      */     
/* 1085 */     Win32Exception we = null;
/*      */     try {
/* 1087 */       Tlhelp32.MODULEENTRY32W first = new Tlhelp32.MODULEENTRY32W();
/*      */       
/* 1089 */       if (!Kernel32.INSTANCE.Module32FirstW(snapshot, first)) {
/* 1090 */         throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */       }
/*      */       
/* 1093 */       List<Tlhelp32.MODULEENTRY32W> modules = new ArrayList<Tlhelp32.MODULEENTRY32W>();
/* 1094 */       modules.add(first);
/*      */       
/* 1096 */       Tlhelp32.MODULEENTRY32W next = new Tlhelp32.MODULEENTRY32W();
/* 1097 */       while (Kernel32.INSTANCE.Module32NextW(snapshot, next)) {
/* 1098 */         modules.add(next);
/* 1099 */         next = new Tlhelp32.MODULEENTRY32W();
/*      */       } 
/*      */       
/* 1102 */       int lastError = Kernel32.INSTANCE.GetLastError();
/*      */ 
/*      */ 
/*      */       
/* 1106 */       if (lastError != 0 && lastError != 18) {
/* 1107 */         throw new Win32Exception(lastError);
/*      */       }
/*      */       
/* 1110 */       return modules;
/* 1111 */     } catch (Win32Exception e) {
/* 1112 */       we = e;
/* 1113 */       throw we;
/*      */     } finally {
/*      */       try {
/* 1116 */         closeHandle(snapshot);
/* 1117 */       } catch (Win32Exception e) {
/* 1118 */         if (we == null) {
/* 1119 */           we = e;
/*      */         } else {
/* 1121 */           we.addSuppressedReflected((Throwable)e);
/*      */         } 
/*      */       } 
/*      */       
/* 1125 */       if (we != null) {
/* 1126 */         throw we;
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
/*      */   public static String expandEnvironmentStrings(String input) {
/*      */     Memory resultMemory;
/* 1152 */     if (input == null) {
/* 1153 */       return "";
/*      */     }
/*      */     
/* 1156 */     int resultChars = Kernel32.INSTANCE.ExpandEnvironmentStrings(input, null, 0);
/*      */     
/* 1158 */     if (resultChars == 0) {
/* 1159 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*      */ 
/*      */     
/* 1163 */     if (W32APITypeMapper.DEFAULT == W32APITypeMapper.UNICODE) {
/* 1164 */       resultMemory = new Memory((resultChars * Native.WCHAR_SIZE));
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 1169 */       resultMemory = new Memory((resultChars + 1));
/*      */     } 
/* 1171 */     resultChars = Kernel32.INSTANCE.ExpandEnvironmentStrings(input, (Pointer)resultMemory, resultChars);
/*      */     
/* 1173 */     if (resultChars == 0) {
/* 1174 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*      */     
/* 1177 */     if (W32APITypeMapper.DEFAULT == W32APITypeMapper.UNICODE) {
/* 1178 */       return resultMemory.getWideString(0L);
/*      */     }
/* 1180 */     return resultMemory.getString(0L);
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/sun/jna/platform/win32/Kernel32Util.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */