/*      */ package com.sun.jna.platform.win32;
/*      */ 
/*      */ import com.sun.jna.Memory;
/*      */ import com.sun.jna.Native;
/*      */ import com.sun.jna.Pointer;
/*      */ import com.sun.jna.ptr.IntByReference;
/*      */ import com.sun.jna.ptr.LongByReference;
/*      */ import com.sun.jna.ptr.PointerByReference;
/*      */ import com.sun.jna.win32.W32APITypeMapper;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.Closeable;
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class Advapi32Util
/*      */ {
/*      */   public static class Account
/*      */   {
/*      */     public String name;
/*      */     public String domain;
/*      */     public byte[] sid;
/*      */     public String sidString;
/*      */     public int accountType;
/*      */     public String fqn;
/*      */   }
/*      */   
/*      */   public static String getUserName() {
/*  145 */     char[] buffer = new char[128];
/*  146 */     IntByReference len = new IntByReference(buffer.length);
/*  147 */     boolean result = Advapi32.INSTANCE.GetUserNameW(buffer, len);
/*      */     
/*  149 */     if (!result) {
/*  150 */       switch (Kernel32.INSTANCE.GetLastError()) {
/*      */         case 122:
/*  152 */           buffer = new char[len.getValue()];
/*      */           break;
/*      */         
/*      */         default:
/*  156 */           throw new Win32Exception(Native.getLastError());
/*      */       } 
/*      */       
/*  159 */       result = Advapi32.INSTANCE.GetUserNameW(buffer, len);
/*      */     } 
/*      */     
/*  162 */     if (!result) {
/*  163 */       throw new Win32Exception(Native.getLastError());
/*      */     }
/*      */     
/*  166 */     return Native.toString(buffer);
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
/*      */   public static Account getAccountByName(String accountName) {
/*  178 */     return getAccountByName(null, accountName);
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
/*      */   public static Account getAccountByName(String systemName, String accountName) {
/*  191 */     IntByReference pSid = new IntByReference(0);
/*  192 */     IntByReference cchDomainName = new IntByReference(0);
/*  193 */     PointerByReference peUse = new PointerByReference();
/*      */     
/*  195 */     if (Advapi32.INSTANCE.LookupAccountName(systemName, accountName, null, pSid, null, cchDomainName, peUse))
/*      */     {
/*  197 */       throw new RuntimeException("LookupAccountNameW was expected to fail with ERROR_INSUFFICIENT_BUFFER");
/*      */     }
/*      */ 
/*      */     
/*  201 */     int rc = Kernel32.INSTANCE.GetLastError();
/*  202 */     if (pSid.getValue() == 0 || rc != 122) {
/*  203 */       throw new Win32Exception(rc);
/*      */     }
/*      */     
/*  206 */     Memory sidMemory = new Memory(pSid.getValue());
/*  207 */     WinNT.PSID result = new WinNT.PSID((Pointer)sidMemory);
/*  208 */     char[] referencedDomainName = new char[cchDomainName.getValue() + 1];
/*      */     
/*  210 */     if (!Advapi32.INSTANCE.LookupAccountName(systemName, accountName, result, pSid, referencedDomainName, cchDomainName, peUse))
/*      */     {
/*  212 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*      */     
/*  215 */     Account account = new Account();
/*  216 */     account.accountType = peUse.getPointer().getInt(0L);
/*      */     
/*  218 */     String[] accountNamePartsBs = accountName.split("\\\\", 2);
/*  219 */     String[] accountNamePartsAt = accountName.split("@", 2);
/*      */     
/*  221 */     if (accountNamePartsBs.length == 2) {
/*  222 */       account.name = accountNamePartsBs[1];
/*  223 */     } else if (accountNamePartsAt.length == 2) {
/*  224 */       account.name = accountNamePartsAt[0];
/*      */     } else {
/*  226 */       account.name = accountName;
/*      */     } 
/*      */     
/*  229 */     if (cchDomainName.getValue() > 0) {
/*  230 */       account.domain = Native.toString(referencedDomainName);
/*  231 */       account.fqn = account.domain + "\\" + account.name;
/*      */     } else {
/*  233 */       account.fqn = account.name;
/*      */     } 
/*      */     
/*  236 */     account.sid = result.getBytes();
/*  237 */     account.sidString = convertSidToStringSid(new WinNT.PSID(account.sid));
/*  238 */     return account;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Account getAccountBySid(WinNT.PSID sid) {
/*  249 */     return getAccountBySid((String)null, sid);
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
/*      */   public static Account getAccountBySid(String systemName, WinNT.PSID sid) {
/*  262 */     IntByReference cchName = new IntByReference();
/*  263 */     IntByReference cchDomainName = new IntByReference();
/*  264 */     PointerByReference peUse = new PointerByReference();
/*      */     
/*  266 */     if (Advapi32.INSTANCE.LookupAccountSid(systemName, sid, null, cchName, null, cchDomainName, peUse))
/*      */     {
/*  268 */       throw new RuntimeException("LookupAccountSidW was expected to fail with ERROR_INSUFFICIENT_BUFFER");
/*      */     }
/*      */ 
/*      */     
/*  272 */     int rc = Kernel32.INSTANCE.GetLastError();
/*  273 */     if (cchName.getValue() == 0 || rc != 122)
/*      */     {
/*  275 */       throw new Win32Exception(rc);
/*      */     }
/*      */     
/*  278 */     char[] domainName = new char[cchDomainName.getValue()];
/*  279 */     char[] name = new char[cchName.getValue()];
/*      */     
/*  281 */     if (!Advapi32.INSTANCE.LookupAccountSid(systemName, sid, name, cchName, domainName, cchDomainName, peUse))
/*      */     {
/*  283 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*      */     
/*  286 */     Account account = new Account();
/*  287 */     account.accountType = peUse.getPointer().getInt(0L);
/*  288 */     account.name = Native.toString(name);
/*      */     
/*  290 */     if (cchDomainName.getValue() > 0) {
/*  291 */       account.domain = Native.toString(domainName);
/*  292 */       account.fqn = account.domain + "\\" + account.name;
/*      */     } else {
/*  294 */       account.fqn = account.name;
/*      */     } 
/*      */     
/*  297 */     account.sid = sid.getBytes();
/*  298 */     account.sidString = convertSidToStringSid(sid);
/*  299 */     return account;
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
/*      */   public static String convertSidToStringSid(WinNT.PSID sid) {
/*  311 */     PointerByReference stringSid = new PointerByReference();
/*  312 */     if (!Advapi32.INSTANCE.ConvertSidToStringSid(sid, stringSid)) {
/*  313 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*      */     
/*  316 */     Pointer ptr = stringSid.getValue();
/*      */     try {
/*  318 */       return ptr.getWideString(0L);
/*      */     } finally {
/*  320 */       Kernel32Util.freeLocalMemory(ptr);
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
/*      */   public static byte[] convertStringSidToSid(String sidString) {
/*  333 */     WinNT.PSIDByReference pSID = new WinNT.PSIDByReference();
/*  334 */     if (!Advapi32.INSTANCE.ConvertStringSidToSid(sidString, pSID)) {
/*  335 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*      */     
/*  338 */     WinNT.PSID value = pSID.getValue();
/*      */     try {
/*  340 */       return value.getBytes();
/*      */     } finally {
/*  342 */       Kernel32Util.freeLocalMemory(value.getPointer());
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
/*      */   public static boolean isWellKnownSid(String sidString, int wellKnownSidType) {
/*  357 */     WinNT.PSIDByReference pSID = new WinNT.PSIDByReference();
/*  358 */     if (!Advapi32.INSTANCE.ConvertStringSidToSid(sidString, pSID)) {
/*  359 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*      */     
/*  362 */     WinNT.PSID value = pSID.getValue();
/*      */     try {
/*  364 */       return Advapi32.INSTANCE.IsWellKnownSid(value, wellKnownSidType);
/*      */     } finally {
/*  366 */       Kernel32Util.freeLocalMemory(value.getPointer());
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
/*      */   public static boolean isWellKnownSid(byte[] sidBytes, int wellKnownSidType) {
/*  381 */     WinNT.PSID pSID = new WinNT.PSID(sidBytes);
/*  382 */     return Advapi32.INSTANCE.IsWellKnownSid(pSID, wellKnownSidType);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int alignOnDWORD(int cbAcl) {
/*  391 */     return cbAcl + 3 & 0xFFFFFFFC;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getAceSize(int sidLength) {
/*  400 */     return Native.getNativeSize(WinNT.ACCESS_ALLOWED_ACE.class, null) + sidLength - 4;
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
/*      */   public static Account getAccountBySid(String sidString) {
/*  413 */     return getAccountBySid((String)null, sidString);
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
/*      */   public static Account getAccountBySid(String systemName, String sidString) {
/*  426 */     return getAccountBySid(systemName, new WinNT.PSID(convertStringSidToSid(sidString)));
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
/*      */   public static Account[] getTokenGroups(WinNT.HANDLE hToken) {
/*  439 */     IntByReference tokenInformationLength = new IntByReference();
/*  440 */     if (Advapi32.INSTANCE.GetTokenInformation(hToken, 2, null, 0, tokenInformationLength))
/*      */     {
/*      */       
/*  443 */       throw new RuntimeException("Expected GetTokenInformation to fail with ERROR_INSUFFICIENT_BUFFER");
/*      */     }
/*      */     
/*  446 */     int rc = Kernel32.INSTANCE.GetLastError();
/*  447 */     if (rc != 122) {
/*  448 */       throw new Win32Exception(rc);
/*      */     }
/*      */ 
/*      */     
/*  452 */     WinNT.TOKEN_GROUPS groups = new WinNT.TOKEN_GROUPS(tokenInformationLength.getValue());
/*  453 */     if (!Advapi32.INSTANCE.GetTokenInformation(hToken, 2, groups, tokenInformationLength
/*      */         
/*  455 */         .getValue(), tokenInformationLength)) {
/*  456 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*  458 */     ArrayList<Account> userGroups = new ArrayList<Account>();
/*      */     
/*  460 */     for (WinNT.SID_AND_ATTRIBUTES sidAndAttribute : groups.getGroups()) {
/*      */       Account group;
/*      */       try {
/*  463 */         group = getAccountBySid(sidAndAttribute.Sid);
/*  464 */       } catch (Exception e) {
/*  465 */         group = new Account();
/*  466 */         group.sid = sidAndAttribute.Sid.getBytes();
/*  467 */         group
/*  468 */           .sidString = convertSidToStringSid(sidAndAttribute.Sid);
/*  469 */         group.name = group.sidString;
/*  470 */         group.fqn = group.sidString;
/*  471 */         group.accountType = 2;
/*      */       } 
/*  473 */       userGroups.add(group);
/*      */     } 
/*  475 */     return userGroups.<Account>toArray(new Account[0]);
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
/*      */   public static Account getTokenAccount(WinNT.HANDLE hToken) {
/*  488 */     IntByReference tokenInformationLength = new IntByReference();
/*  489 */     if (Advapi32.INSTANCE.GetTokenInformation(hToken, 1, null, 0, tokenInformationLength))
/*      */     {
/*      */       
/*  492 */       throw new RuntimeException("Expected GetTokenInformation to fail with ERROR_INSUFFICIENT_BUFFER");
/*      */     }
/*      */     
/*  495 */     int rc = Kernel32.INSTANCE.GetLastError();
/*  496 */     if (rc != 122) {
/*  497 */       throw new Win32Exception(rc);
/*      */     }
/*      */ 
/*      */     
/*  501 */     WinNT.TOKEN_USER user = new WinNT.TOKEN_USER(tokenInformationLength.getValue());
/*  502 */     if (!Advapi32.INSTANCE.GetTokenInformation(hToken, 1, user, tokenInformationLength
/*      */         
/*  504 */         .getValue(), tokenInformationLength)) {
/*  505 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*  507 */     return getAccountBySid(user.User.Sid);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Account[] getCurrentUserGroups() {
/*  516 */     WinNT.HANDLEByReference phToken = new WinNT.HANDLEByReference();
/*  517 */     Win32Exception err = null;
/*      */     
/*      */     try {
/*  520 */       WinNT.HANDLE threadHandle = Kernel32.INSTANCE.GetCurrentThread();
/*  521 */       if (!Advapi32.INSTANCE.OpenThreadToken(threadHandle, 10, true, phToken)) {
/*      */         
/*  523 */         int rc = Kernel32.INSTANCE.GetLastError();
/*  524 */         if (rc != 1008) {
/*  525 */           throw new Win32Exception(rc);
/*      */         }
/*      */         
/*  528 */         WinNT.HANDLE processHandle = Kernel32.INSTANCE.GetCurrentProcess();
/*  529 */         if (!Advapi32.INSTANCE.OpenProcessToken(processHandle, 10, phToken))
/*      */         {
/*  531 */           throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */         }
/*      */       } 
/*      */       
/*  535 */       return getTokenGroups(phToken.getValue());
/*  536 */     } catch (Win32Exception e) {
/*  537 */       err = e;
/*  538 */       throw err;
/*      */     } finally {
/*  540 */       WinNT.HANDLE hToken = phToken.getValue();
/*  541 */       if (!WinBase.INVALID_HANDLE_VALUE.equals(hToken)) {
/*      */         try {
/*  543 */           Kernel32Util.closeHandle(hToken);
/*  544 */         } catch (Win32Exception e) {
/*  545 */           if (err == null) {
/*  546 */             err = e;
/*      */           } else {
/*  548 */             err.addSuppressedReflected((Throwable)e);
/*      */           } 
/*      */         } 
/*      */       }
/*      */       
/*  553 */       if (err != null) {
/*  554 */         throw err;
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
/*      */   public static boolean registryKeyExists(WinReg.HKEY root, String key) {
/*  569 */     return registryKeyExists(root, key, 0);
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
/*      */   public static boolean registryKeyExists(WinReg.HKEY root, String key, int samDesiredExtra) {
/*  585 */     WinReg.HKEYByReference phkKey = new WinReg.HKEYByReference();
/*  586 */     int rc = Advapi32.INSTANCE.RegOpenKeyEx(root, key, 0, 0x20019 | samDesiredExtra, phkKey);
/*      */     
/*  588 */     switch (rc) {
/*      */       case 0:
/*  590 */         Advapi32.INSTANCE.RegCloseKey(phkKey.getValue());
/*  591 */         return true;
/*      */       case 2:
/*  593 */         return false;
/*      */     } 
/*  595 */     throw new Win32Exception(rc);
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
/*      */   public static boolean registryValueExists(WinReg.HKEY root, String key, String value) {
/*  611 */     return registryValueExists(root, key, value, 0);
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
/*      */   public static boolean registryValueExists(WinReg.HKEY root, String key, String value, int samDesiredExtra) {
/*  630 */     WinReg.HKEYByReference phkKey = new WinReg.HKEYByReference();
/*  631 */     int rc = Advapi32.INSTANCE.RegOpenKeyEx(root, key, 0, 0x20019 | samDesiredExtra, phkKey);
/*      */     
/*  633 */     switch (rc) {
/*      */       case 0:
/*      */         break;
/*      */       case 2:
/*  637 */         return false;
/*      */       default:
/*  639 */         throw new Win32Exception(rc);
/*      */     }  try {
/*      */       boolean bool;
/*  642 */       IntByReference lpcbData = new IntByReference();
/*  643 */       IntByReference lpType = new IntByReference();
/*  644 */       rc = Advapi32.INSTANCE.RegQueryValueEx(phkKey.getValue(), value, 0, lpType, (Pointer)null, lpcbData);
/*      */       
/*  646 */       switch (rc) {
/*      */         case 0:
/*      */         case 122:
/*      */         case 234:
/*  650 */           bool = true; return bool;
/*      */         case 2:
/*  652 */           bool = false; return bool;
/*      */       } 
/*  654 */       throw new Win32Exception(rc);
/*      */     } finally {
/*      */       
/*  657 */       if (phkKey.getValue() != WinBase.INVALID_HANDLE_VALUE) {
/*  658 */         rc = Advapi32.INSTANCE.RegCloseKey(phkKey.getValue());
/*  659 */         if (rc != 0) {
/*  660 */           throw new Win32Exception(rc);
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
/*      */   public static String registryGetStringValue(WinReg.HKEY root, String key, String value) {
/*  679 */     return registryGetStringValue(root, key, value, 0);
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
/*      */   public static String registryGetStringValue(WinReg.HKEY root, String key, String value, int samDesiredExtra) {
/*  698 */     WinReg.HKEYByReference phkKey = new WinReg.HKEYByReference();
/*  699 */     int rc = Advapi32.INSTANCE.RegOpenKeyEx(root, key, 0, 0x20019 | samDesiredExtra, phkKey);
/*      */     
/*  701 */     if (rc != 0) {
/*  702 */       throw new Win32Exception(rc);
/*      */     }
/*      */     try {
/*  705 */       return registryGetStringValue(phkKey.getValue(), value);
/*      */     } finally {
/*  707 */       rc = Advapi32.INSTANCE.RegCloseKey(phkKey.getValue());
/*  708 */       if (rc != 0) {
/*  709 */         throw new Win32Exception(rc);
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
/*      */   public static String registryGetStringValue(WinReg.HKEY hKey, String value) {
/*  724 */     IntByReference lpcbData = new IntByReference();
/*  725 */     IntByReference lpType = new IntByReference();
/*  726 */     int rc = Advapi32.INSTANCE.RegQueryValueEx(hKey, value, 0, lpType, (Pointer)null, lpcbData);
/*      */     
/*  728 */     if (rc != 0 && rc != 122)
/*      */     {
/*  730 */       throw new Win32Exception(rc);
/*      */     }
/*  732 */     if (lpType.getValue() != 1 && lpType
/*  733 */       .getValue() != 2) {
/*  734 */       throw new RuntimeException("Unexpected registry type " + lpType
/*  735 */           .getValue() + ", expected REG_SZ or REG_EXPAND_SZ");
/*      */     }
/*      */     
/*  738 */     if (lpcbData.getValue() == 0) {
/*  739 */       return "";
/*      */     }
/*      */     
/*  742 */     Memory mem = new Memory((lpcbData.getValue() + Native.WCHAR_SIZE));
/*  743 */     mem.clear();
/*  744 */     rc = Advapi32.INSTANCE.RegQueryValueEx(hKey, value, 0, lpType, (Pointer)mem, lpcbData);
/*      */     
/*  746 */     if (rc != 0 && rc != 122)
/*      */     {
/*  748 */       throw new Win32Exception(rc);
/*      */     }
/*  750 */     if (W32APITypeMapper.DEFAULT == W32APITypeMapper.UNICODE) {
/*  751 */       return mem.getWideString(0L);
/*      */     }
/*  753 */     return mem.getString(0L);
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
/*      */   public static String registryGetExpandableStringValue(WinReg.HKEY root, String key, String value) {
/*  770 */     return registryGetExpandableStringValue(root, key, value, 0);
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
/*      */   public static String registryGetExpandableStringValue(WinReg.HKEY root, String key, String value, int samDesiredExtra) {
/*  789 */     WinReg.HKEYByReference phkKey = new WinReg.HKEYByReference();
/*  790 */     int rc = Advapi32.INSTANCE.RegOpenKeyEx(root, key, 0, 0x20019 | samDesiredExtra, phkKey);
/*      */     
/*  792 */     if (rc != 0) {
/*  793 */       throw new Win32Exception(rc);
/*      */     }
/*      */     try {
/*  796 */       return registryGetExpandableStringValue(phkKey.getValue(), value);
/*      */     } finally {
/*  798 */       rc = Advapi32.INSTANCE.RegCloseKey(phkKey.getValue());
/*  799 */       if (rc != 0) {
/*  800 */         throw new Win32Exception(rc);
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
/*      */   public static String registryGetExpandableStringValue(WinReg.HKEY hKey, String value) {
/*  815 */     IntByReference lpcbData = new IntByReference();
/*  816 */     IntByReference lpType = new IntByReference();
/*  817 */     int rc = Advapi32.INSTANCE.RegQueryValueEx(hKey, value, 0, lpType, (char[])null, lpcbData);
/*      */     
/*  819 */     if (rc != 0 && rc != 122)
/*      */     {
/*  821 */       throw new Win32Exception(rc);
/*      */     }
/*  823 */     if (lpType.getValue() != 2) {
/*  824 */       throw new RuntimeException("Unexpected registry type " + lpType
/*  825 */           .getValue() + ", expected REG_SZ");
/*      */     }
/*  827 */     if (lpcbData.getValue() == 0) {
/*  828 */       return "";
/*      */     }
/*      */     
/*  831 */     Memory mem = new Memory((lpcbData.getValue() + Native.WCHAR_SIZE));
/*  832 */     mem.clear();
/*  833 */     rc = Advapi32.INSTANCE.RegQueryValueEx(hKey, value, 0, lpType, (Pointer)mem, lpcbData);
/*      */     
/*  835 */     if (rc != 0 && rc != 122)
/*      */     {
/*  837 */       throw new Win32Exception(rc);
/*      */     }
/*  839 */     if (W32APITypeMapper.DEFAULT == W32APITypeMapper.UNICODE) {
/*  840 */       return mem.getWideString(0L);
/*      */     }
/*  842 */     return mem.getString(0L);
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
/*      */   public static String[] registryGetStringArray(WinReg.HKEY root, String key, String value) {
/*  859 */     return registryGetStringArray(root, key, value, 0);
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
/*      */   public static String[] registryGetStringArray(WinReg.HKEY root, String key, String value, int samDesiredExtra) {
/*  878 */     WinReg.HKEYByReference phkKey = new WinReg.HKEYByReference();
/*  879 */     int rc = Advapi32.INSTANCE.RegOpenKeyEx(root, key, 0, 0x20019 | samDesiredExtra, phkKey);
/*      */     
/*  881 */     if (rc != 0) {
/*  882 */       throw new Win32Exception(rc);
/*      */     }
/*      */     try {
/*  885 */       return registryGetStringArray(phkKey.getValue(), value);
/*      */     } finally {
/*  887 */       rc = Advapi32.INSTANCE.RegCloseKey(phkKey.getValue());
/*  888 */       if (rc != 0) {
/*  889 */         throw new Win32Exception(rc);
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
/*      */   public static String[] registryGetStringArray(WinReg.HKEY hKey, String value) {
/*  904 */     IntByReference lpcbData = new IntByReference();
/*  905 */     IntByReference lpType = new IntByReference();
/*  906 */     int rc = Advapi32.INSTANCE.RegQueryValueEx(hKey, value, 0, lpType, (char[])null, lpcbData);
/*      */     
/*  908 */     if (rc != 0 && rc != 122)
/*      */     {
/*  910 */       throw new Win32Exception(rc);
/*      */     }
/*  912 */     if (lpType.getValue() != 7) {
/*  913 */       throw new RuntimeException("Unexpected registry type " + lpType
/*  914 */           .getValue() + ", expected REG_SZ");
/*      */     }
/*      */ 
/*      */     
/*  918 */     Memory data = new Memory((lpcbData.getValue() + 2 * Native.WCHAR_SIZE));
/*  919 */     data.clear();
/*  920 */     rc = Advapi32.INSTANCE.RegQueryValueEx(hKey, value, 0, lpType, (Pointer)data, lpcbData);
/*      */     
/*  922 */     if (rc != 0 && rc != 122)
/*      */     {
/*  924 */       throw new Win32Exception(rc);
/*      */     }
/*  926 */     ArrayList<String> result = new ArrayList<String>();
/*  927 */     int offset = 0;
/*  928 */     while (offset < data.size()) {
/*      */       String s;
/*  930 */       if (W32APITypeMapper.DEFAULT == W32APITypeMapper.UNICODE) {
/*  931 */         s = data.getWideString(offset);
/*  932 */         offset += s.length() * Native.WCHAR_SIZE;
/*  933 */         offset += Native.WCHAR_SIZE;
/*      */       } else {
/*  935 */         s = data.getString(offset);
/*  936 */         offset += s.length();
/*  937 */         offset++;
/*      */       } 
/*      */       
/*  940 */       if (s.length() == 0) {
/*      */         break;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  946 */       result.add(s);
/*      */     } 
/*      */     
/*  949 */     return result.<String>toArray(new String[0]);
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
/*      */   public static byte[] registryGetBinaryValue(WinReg.HKEY root, String key, String value) {
/*  965 */     return registryGetBinaryValue(root, key, value, 0);
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
/*      */   public static byte[] registryGetBinaryValue(WinReg.HKEY root, String key, String value, int samDesiredExtra) {
/*  984 */     WinReg.HKEYByReference phkKey = new WinReg.HKEYByReference();
/*  985 */     int rc = Advapi32.INSTANCE.RegOpenKeyEx(root, key, 0, 0x20019 | samDesiredExtra, phkKey);
/*      */     
/*  987 */     if (rc != 0) {
/*  988 */       throw new Win32Exception(rc);
/*      */     }
/*      */     try {
/*  991 */       return registryGetBinaryValue(phkKey.getValue(), value);
/*      */     } finally {
/*  993 */       rc = Advapi32.INSTANCE.RegCloseKey(phkKey.getValue());
/*  994 */       if (rc != 0) {
/*  995 */         throw new Win32Exception(rc);
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
/*      */   public static byte[] registryGetBinaryValue(WinReg.HKEY hKey, String value) {
/* 1010 */     IntByReference lpcbData = new IntByReference();
/* 1011 */     IntByReference lpType = new IntByReference();
/* 1012 */     int rc = Advapi32.INSTANCE.RegQueryValueEx(hKey, value, 0, lpType, (Pointer)null, lpcbData);
/*      */     
/* 1014 */     if (rc != 0 && rc != 122)
/*      */     {
/* 1016 */       throw new Win32Exception(rc);
/*      */     }
/* 1018 */     if (lpType.getValue() != 3) {
/* 1019 */       throw new RuntimeException("Unexpected registry type " + lpType
/* 1020 */           .getValue() + ", expected REG_BINARY");
/*      */     }
/* 1022 */     byte[] data = new byte[lpcbData.getValue()];
/* 1023 */     rc = Advapi32.INSTANCE.RegQueryValueEx(hKey, value, 0, lpType, data, lpcbData);
/*      */     
/* 1025 */     if (rc != 0 && rc != 122)
/*      */     {
/* 1027 */       throw new Win32Exception(rc);
/*      */     }
/* 1029 */     return data;
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
/*      */   public static int registryGetIntValue(WinReg.HKEY root, String key, String value) {
/* 1044 */     return registryGetIntValue(root, key, value, 0);
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
/*      */   public static int registryGetIntValue(WinReg.HKEY root, String key, String value, int samDesiredExtra) {
/* 1062 */     WinReg.HKEYByReference phkKey = new WinReg.HKEYByReference();
/* 1063 */     int rc = Advapi32.INSTANCE.RegOpenKeyEx(root, key, 0, 0x20019 | samDesiredExtra, phkKey);
/*      */     
/* 1065 */     if (rc != 0) {
/* 1066 */       throw new Win32Exception(rc);
/*      */     }
/*      */     try {
/* 1069 */       return registryGetIntValue(phkKey.getValue(), value);
/*      */     } finally {
/* 1071 */       rc = Advapi32.INSTANCE.RegCloseKey(phkKey.getValue());
/* 1072 */       if (rc != 0) {
/* 1073 */         throw new Win32Exception(rc);
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
/*      */   public static int registryGetIntValue(WinReg.HKEY hKey, String value) {
/* 1088 */     IntByReference lpcbData = new IntByReference();
/* 1089 */     IntByReference lpType = new IntByReference();
/* 1090 */     int rc = Advapi32.INSTANCE.RegQueryValueEx(hKey, value, 0, lpType, (char[])null, lpcbData);
/*      */     
/* 1092 */     if (rc != 0 && rc != 122)
/*      */     {
/* 1094 */       throw new Win32Exception(rc);
/*      */     }
/* 1096 */     if (lpType.getValue() != 4) {
/* 1097 */       throw new RuntimeException("Unexpected registry type " + lpType
/* 1098 */           .getValue() + ", expected REG_DWORD");
/*      */     }
/* 1100 */     IntByReference data = new IntByReference();
/* 1101 */     rc = Advapi32.INSTANCE.RegQueryValueEx(hKey, value, 0, lpType, data, lpcbData);
/*      */     
/* 1103 */     if (rc != 0 && rc != 122)
/*      */     {
/* 1105 */       throw new Win32Exception(rc);
/*      */     }
/* 1107 */     return data.getValue();
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
/*      */   public static long registryGetLongValue(WinReg.HKEY root, String key, String value) {
/* 1122 */     return registryGetLongValue(root, key, value, 0);
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
/*      */   public static long registryGetLongValue(WinReg.HKEY root, String key, String value, int samDesiredExtra) {
/* 1140 */     WinReg.HKEYByReference phkKey = new WinReg.HKEYByReference();
/* 1141 */     int rc = Advapi32.INSTANCE.RegOpenKeyEx(root, key, 0, 0x20019 | samDesiredExtra, phkKey);
/*      */     
/* 1143 */     if (rc != 0) {
/* 1144 */       throw new Win32Exception(rc);
/*      */     }
/*      */     try {
/* 1147 */       return registryGetLongValue(phkKey.getValue(), value);
/*      */     } finally {
/* 1149 */       rc = Advapi32.INSTANCE.RegCloseKey(phkKey.getValue());
/* 1150 */       if (rc != 0) {
/* 1151 */         throw new Win32Exception(rc);
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
/*      */   public static long registryGetLongValue(WinReg.HKEY hKey, String value) {
/* 1166 */     IntByReference lpcbData = new IntByReference();
/* 1167 */     IntByReference lpType = new IntByReference();
/* 1168 */     int rc = Advapi32.INSTANCE.RegQueryValueEx(hKey, value, 0, lpType, (char[])null, lpcbData);
/*      */     
/* 1170 */     if (rc != 0 && rc != 122)
/*      */     {
/* 1172 */       throw new Win32Exception(rc);
/*      */     }
/* 1174 */     if (lpType.getValue() != 11) {
/* 1175 */       throw new RuntimeException("Unexpected registry type " + lpType
/* 1176 */           .getValue() + ", expected REG_QWORD");
/*      */     }
/* 1178 */     LongByReference data = new LongByReference();
/* 1179 */     rc = Advapi32.INSTANCE.RegQueryValueEx(hKey, value, 0, lpType, data, lpcbData);
/*      */     
/* 1181 */     if (rc != 0 && rc != 122)
/*      */     {
/* 1183 */       throw new Win32Exception(rc);
/*      */     }
/* 1185 */     return data.getValue();
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
/*      */   public static Object registryGetValue(WinReg.HKEY hkKey, String subKey, String lpValueName) {
/* 1202 */     Object result = null;
/* 1203 */     IntByReference lpType = new IntByReference();
/* 1204 */     IntByReference lpcbData = new IntByReference();
/*      */     
/* 1206 */     int rc = Advapi32.INSTANCE.RegGetValue(hkKey, subKey, lpValueName, 65535, lpType, (Pointer)null, lpcbData);
/*      */ 
/*      */ 
/*      */     
/* 1210 */     if (lpType.getValue() == 0) {
/* 1211 */       return null;
/*      */     }
/* 1213 */     if (rc != 0 && rc != 122)
/*      */     {
/* 1215 */       throw new Win32Exception(rc);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1222 */     Memory byteData = new Memory((lpcbData.getValue() + Native.WCHAR_SIZE));
/* 1223 */     byteData.clear();
/*      */     
/* 1225 */     rc = Advapi32.INSTANCE.RegGetValue(hkKey, subKey, lpValueName, 65535, lpType, (Pointer)byteData, lpcbData);
/*      */ 
/*      */     
/* 1228 */     if (rc != 0) {
/* 1229 */       throw new Win32Exception(rc);
/*      */     }
/*      */     
/* 1232 */     if (lpType.getValue() == 4) {
/* 1233 */       result = Integer.valueOf(byteData.getInt(0L));
/* 1234 */     } else if (lpType.getValue() == 11) {
/* 1235 */       result = Long.valueOf(byteData.getLong(0L));
/* 1236 */     } else if (lpType.getValue() == 3) {
/* 1237 */       result = byteData.getByteArray(0L, lpcbData.getValue());
/* 1238 */     } else if (lpType.getValue() == 1 || lpType
/* 1239 */       .getValue() == 2) {
/* 1240 */       if (W32APITypeMapper.DEFAULT == W32APITypeMapper.UNICODE) {
/* 1241 */         result = byteData.getWideString(0L);
/*      */       } else {
/* 1243 */         result = byteData.getString(0L);
/*      */       } 
/*      */     } 
/*      */     
/* 1247 */     return result;
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
/*      */   public static boolean registryCreateKey(WinReg.HKEY hKey, String keyName) {
/* 1260 */     return registryCreateKey(hKey, keyName, 0);
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
/*      */   public static boolean registryCreateKey(WinReg.HKEY hKey, String keyName, int samDesiredExtra) {
/* 1276 */     WinReg.HKEYByReference phkResult = new WinReg.HKEYByReference();
/* 1277 */     IntByReference lpdwDisposition = new IntByReference();
/* 1278 */     int rc = Advapi32.INSTANCE.RegCreateKeyEx(hKey, keyName, 0, null, 0, 0x20019 | samDesiredExtra, null, phkResult, lpdwDisposition);
/*      */ 
/*      */     
/* 1281 */     if (rc != 0) {
/* 1282 */       throw new Win32Exception(rc);
/*      */     }
/* 1284 */     rc = Advapi32.INSTANCE.RegCloseKey(phkResult.getValue());
/* 1285 */     if (rc != 0) {
/* 1286 */       throw new Win32Exception(rc);
/*      */     }
/* 1288 */     return (1 == lpdwDisposition.getValue());
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
/*      */   public static boolean registryCreateKey(WinReg.HKEY root, String parentPath, String keyName) {
/* 1304 */     return registryCreateKey(root, parentPath, keyName, 0);
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
/*      */   public static boolean registryCreateKey(WinReg.HKEY root, String parentPath, String keyName, int samDesiredExtra) {
/* 1323 */     WinReg.HKEYByReference phkKey = new WinReg.HKEYByReference();
/* 1324 */     int rc = Advapi32.INSTANCE.RegOpenKeyEx(root, parentPath, 0, 0x4 | samDesiredExtra, phkKey);
/*      */     
/* 1326 */     if (rc != 0) {
/* 1327 */       throw new Win32Exception(rc);
/*      */     }
/*      */     try {
/* 1330 */       return registryCreateKey(phkKey.getValue(), keyName);
/*      */     } finally {
/* 1332 */       rc = Advapi32.INSTANCE.RegCloseKey(phkKey.getValue());
/* 1333 */       if (rc != 0) {
/* 1334 */         throw new Win32Exception(rc);
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
/*      */   public static void registrySetIntValue(WinReg.HKEY hKey, String name, int value) {
/* 1350 */     byte[] data = new byte[4];
/* 1351 */     data[0] = (byte)(value & 0xFF);
/* 1352 */     data[1] = (byte)(value >> 8 & 0xFF);
/* 1353 */     data[2] = (byte)(value >> 16 & 0xFF);
/* 1354 */     data[3] = (byte)(value >> 24 & 0xFF);
/* 1355 */     int rc = Advapi32.INSTANCE.RegSetValueEx(hKey, name, 0, 4, data, 4);
/*      */     
/* 1357 */     if (rc != 0) {
/* 1358 */       throw new Win32Exception(rc);
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
/*      */   public static void registrySetIntValue(WinReg.HKEY root, String keyPath, String name, int value) {
/* 1376 */     registrySetIntValue(root, keyPath, name, value, 0);
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
/*      */   public static void registrySetIntValue(WinReg.HKEY root, String keyPath, String name, int value, int samDesiredExtra) {
/* 1396 */     WinReg.HKEYByReference phkKey = new WinReg.HKEYByReference();
/* 1397 */     int rc = Advapi32.INSTANCE.RegOpenKeyEx(root, keyPath, 0, 0x2001F | samDesiredExtra, phkKey);
/*      */     
/* 1399 */     if (rc != 0) {
/* 1400 */       throw new Win32Exception(rc);
/*      */     }
/*      */     try {
/* 1403 */       registrySetIntValue(phkKey.getValue(), name, value);
/*      */     } finally {
/* 1405 */       rc = Advapi32.INSTANCE.RegCloseKey(phkKey.getValue());
/* 1406 */       if (rc != 0) {
/* 1407 */         throw new Win32Exception(rc);
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
/*      */   public static void registrySetLongValue(WinReg.HKEY hKey, String name, long value) {
/* 1423 */     byte[] data = new byte[8];
/* 1424 */     data[0] = (byte)(int)(value & 0xFFL);
/* 1425 */     data[1] = (byte)(int)(value >> 8L & 0xFFL);
/* 1426 */     data[2] = (byte)(int)(value >> 16L & 0xFFL);
/* 1427 */     data[3] = (byte)(int)(value >> 24L & 0xFFL);
/* 1428 */     data[4] = (byte)(int)(value >> 32L & 0xFFL);
/* 1429 */     data[5] = (byte)(int)(value >> 40L & 0xFFL);
/* 1430 */     data[6] = (byte)(int)(value >> 48L & 0xFFL);
/* 1431 */     data[7] = (byte)(int)(value >> 56L & 0xFFL);
/* 1432 */     int rc = Advapi32.INSTANCE.RegSetValueEx(hKey, name, 0, 11, data, 8);
/*      */     
/* 1434 */     if (rc != 0) {
/* 1435 */       throw new Win32Exception(rc);
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
/*      */   public static void registrySetLongValue(WinReg.HKEY root, String keyPath, String name, long value) {
/* 1453 */     registrySetLongValue(root, keyPath, name, value, 0);
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
/*      */   public static void registrySetLongValue(WinReg.HKEY root, String keyPath, String name, long value, int samDesiredExtra) {
/* 1473 */     WinReg.HKEYByReference phkKey = new WinReg.HKEYByReference();
/* 1474 */     int rc = Advapi32.INSTANCE.RegOpenKeyEx(root, keyPath, 0, 0x2001F | samDesiredExtra, phkKey);
/*      */     
/* 1476 */     if (rc != 0) {
/* 1477 */       throw new Win32Exception(rc);
/*      */     }
/*      */     try {
/* 1480 */       registrySetLongValue(phkKey.getValue(), name, value);
/*      */     } finally {
/* 1482 */       rc = Advapi32.INSTANCE.RegCloseKey(phkKey.getValue());
/* 1483 */       if (rc != 0) {
/* 1484 */         throw new Win32Exception(rc);
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
/*      */   public static void registrySetStringValue(WinReg.HKEY hKey, String name, String value) {
/*      */     Memory data;
/* 1501 */     if (value == null) {
/* 1502 */       value = "";
/*      */     }
/*      */     
/* 1505 */     if (W32APITypeMapper.DEFAULT == W32APITypeMapper.UNICODE) {
/* 1506 */       data = new Memory(((value.length() + 1) * Native.WCHAR_SIZE));
/* 1507 */       data.setWideString(0L, value);
/*      */     } else {
/* 1509 */       data = new Memory((value.length() + 1));
/* 1510 */       data.setString(0L, value);
/*      */     } 
/* 1512 */     int rc = Advapi32.INSTANCE.RegSetValueEx(hKey, name, 0, 1, (Pointer)data, 
/* 1513 */         (int)data.size());
/* 1514 */     if (rc != 0) {
/* 1515 */       throw new Win32Exception(rc);
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
/*      */   public static void registrySetStringValue(WinReg.HKEY root, String keyPath, String name, String value) {
/* 1533 */     registrySetStringValue(root, keyPath, name, value, 0);
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
/*      */   public static void registrySetStringValue(WinReg.HKEY root, String keyPath, String name, String value, int samDesiredExtra) {
/* 1553 */     WinReg.HKEYByReference phkKey = new WinReg.HKEYByReference();
/* 1554 */     int rc = Advapi32.INSTANCE.RegOpenKeyEx(root, keyPath, 0, 0x2001F | samDesiredExtra, phkKey);
/*      */     
/* 1556 */     if (rc != 0) {
/* 1557 */       throw new Win32Exception(rc);
/*      */     }
/*      */     try {
/* 1560 */       registrySetStringValue(phkKey.getValue(), name, value);
/*      */     } finally {
/* 1562 */       rc = Advapi32.INSTANCE.RegCloseKey(phkKey.getValue());
/* 1563 */       if (rc != 0) {
/* 1564 */         throw new Win32Exception(rc);
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
/*      */   public static void registrySetExpandableStringValue(WinReg.HKEY hKey, String name, String value) {
/*      */     Memory data;
/* 1582 */     if (W32APITypeMapper.DEFAULT == W32APITypeMapper.UNICODE) {
/* 1583 */       data = new Memory(((value.length() + 1) * Native.WCHAR_SIZE));
/* 1584 */       data.setWideString(0L, value);
/*      */     } else {
/* 1586 */       data = new Memory((value.length() + 1));
/* 1587 */       data.setString(0L, value);
/*      */     } 
/* 1589 */     int rc = Advapi32.INSTANCE.RegSetValueEx(hKey, name, 0, 2, (Pointer)data, 
/* 1590 */         (int)data.size());
/* 1591 */     if (rc != 0) {
/* 1592 */       throw new Win32Exception(rc);
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
/*      */   public static void registrySetExpandableStringValue(WinReg.HKEY root, String keyPath, String name, String value) {
/* 1610 */     registrySetExpandableStringValue(root, keyPath, name, value, 0);
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
/*      */   public static void registrySetExpandableStringValue(WinReg.HKEY root, String keyPath, String name, String value, int samDesiredExtra) {
/* 1630 */     WinReg.HKEYByReference phkKey = new WinReg.HKEYByReference();
/* 1631 */     int rc = Advapi32.INSTANCE.RegOpenKeyEx(root, keyPath, 0, 0x2001F | samDesiredExtra, phkKey);
/*      */     
/* 1633 */     if (rc != 0) {
/* 1634 */       throw new Win32Exception(rc);
/*      */     }
/*      */     try {
/* 1637 */       registrySetExpandableStringValue(phkKey.getValue(), name, value);
/*      */     } finally {
/* 1639 */       rc = Advapi32.INSTANCE.RegCloseKey(phkKey.getValue());
/* 1640 */       if (rc != 0) {
/* 1641 */         throw new Win32Exception(rc);
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
/*      */   public static void registrySetStringArray(WinReg.HKEY hKey, String name, String[] arr) {
/* 1659 */     int charwidth = (W32APITypeMapper.DEFAULT == W32APITypeMapper.UNICODE) ? Native.WCHAR_SIZE : 1;
/*      */     
/* 1661 */     int size = 0;
/* 1662 */     for (String s : arr) {
/* 1663 */       size += s.length() * charwidth;
/* 1664 */       size += charwidth;
/*      */     } 
/* 1666 */     size += charwidth;
/*      */     
/* 1668 */     int offset = 0;
/* 1669 */     Memory data = new Memory(size);
/* 1670 */     data.clear();
/* 1671 */     for (String s : arr) {
/* 1672 */       if (W32APITypeMapper.DEFAULT == W32APITypeMapper.UNICODE) {
/* 1673 */         data.setWideString(offset, s);
/*      */       } else {
/* 1675 */         data.setString(offset, s);
/*      */       } 
/* 1677 */       offset += s.length() * charwidth;
/* 1678 */       offset += charwidth;
/*      */     } 
/*      */     
/* 1681 */     int rc = Advapi32.INSTANCE.RegSetValueEx(hKey, name, 0, 7, (Pointer)data, size);
/*      */ 
/*      */     
/* 1684 */     if (rc != 0) {
/* 1685 */       throw new Win32Exception(rc);
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
/*      */   public static void registrySetStringArray(WinReg.HKEY root, String keyPath, String name, String[] arr) {
/* 1703 */     registrySetStringArray(root, keyPath, name, arr, 0);
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
/*      */   public static void registrySetStringArray(WinReg.HKEY root, String keyPath, String name, String[] arr, int samDesiredExtra) {
/* 1723 */     WinReg.HKEYByReference phkKey = new WinReg.HKEYByReference();
/* 1724 */     int rc = Advapi32.INSTANCE.RegOpenKeyEx(root, keyPath, 0, 0x2001F | samDesiredExtra, phkKey);
/*      */     
/* 1726 */     if (rc != 0) {
/* 1727 */       throw new Win32Exception(rc);
/*      */     }
/*      */     try {
/* 1730 */       registrySetStringArray(phkKey.getValue(), name, arr);
/*      */     } finally {
/* 1732 */       rc = Advapi32.INSTANCE.RegCloseKey(phkKey.getValue());
/* 1733 */       if (rc != 0) {
/* 1734 */         throw new Win32Exception(rc);
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
/*      */   public static void registrySetBinaryValue(WinReg.HKEY hKey, String name, byte[] data) {
/* 1751 */     int rc = Advapi32.INSTANCE.RegSetValueEx(hKey, name, 0, 3, data, data.length);
/*      */     
/* 1753 */     if (rc != 0) {
/* 1754 */       throw new Win32Exception(rc);
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
/*      */   public static void registrySetBinaryValue(WinReg.HKEY root, String keyPath, String name, byte[] data) {
/* 1772 */     registrySetBinaryValue(root, keyPath, name, data, 0);
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
/*      */   public static void registrySetBinaryValue(WinReg.HKEY root, String keyPath, String name, byte[] data, int samDesiredExtra) {
/* 1792 */     WinReg.HKEYByReference phkKey = new WinReg.HKEYByReference();
/* 1793 */     int rc = Advapi32.INSTANCE.RegOpenKeyEx(root, keyPath, 0, 0x2001F | samDesiredExtra, phkKey);
/*      */     
/* 1795 */     if (rc != 0) {
/* 1796 */       throw new Win32Exception(rc);
/*      */     }
/*      */     try {
/* 1799 */       registrySetBinaryValue(phkKey.getValue(), name, data);
/*      */     } finally {
/* 1801 */       rc = Advapi32.INSTANCE.RegCloseKey(phkKey.getValue());
/* 1802 */       if (rc != 0) {
/* 1803 */         throw new Win32Exception(rc);
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
/*      */   public static void registryDeleteKey(WinReg.HKEY hKey, String keyName) {
/* 1817 */     int rc = Advapi32.INSTANCE.RegDeleteKey(hKey, keyName);
/* 1818 */     if (rc != 0) {
/* 1819 */       throw new Win32Exception(rc);
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
/*      */   public static void registryDeleteKey(WinReg.HKEY root, String keyPath, String keyName) {
/* 1835 */     registryDeleteKey(root, keyPath, keyName, 0);
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
/*      */   public static void registryDeleteKey(WinReg.HKEY root, String keyPath, String keyName, int samDesiredExtra) {
/* 1853 */     WinReg.HKEYByReference phkKey = new WinReg.HKEYByReference();
/* 1854 */     int rc = Advapi32.INSTANCE.RegOpenKeyEx(root, keyPath, 0, 0x2001F | samDesiredExtra, phkKey);
/*      */     
/* 1856 */     if (rc != 0) {
/* 1857 */       throw new Win32Exception(rc);
/*      */     }
/*      */     try {
/* 1860 */       registryDeleteKey(phkKey.getValue(), keyName);
/*      */     } finally {
/* 1862 */       rc = Advapi32.INSTANCE.RegCloseKey(phkKey.getValue());
/* 1863 */       if (rc != 0) {
/* 1864 */         throw new Win32Exception(rc);
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
/*      */   public static void registryDeleteValue(WinReg.HKEY hKey, String valueName) {
/* 1878 */     int rc = Advapi32.INSTANCE.RegDeleteValue(hKey, valueName);
/* 1879 */     if (rc != 0) {
/* 1880 */       throw new Win32Exception(rc);
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
/*      */   public static void registryDeleteValue(WinReg.HKEY root, String keyPath, String valueName) {
/* 1896 */     registryDeleteValue(root, keyPath, valueName, 0);
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
/*      */   public static void registryDeleteValue(WinReg.HKEY root, String keyPath, String valueName, int samDesiredExtra) {
/* 1914 */     WinReg.HKEYByReference phkKey = new WinReg.HKEYByReference();
/* 1915 */     int rc = Advapi32.INSTANCE.RegOpenKeyEx(root, keyPath, 0, 0x2001F | samDesiredExtra, phkKey);
/*      */     
/* 1917 */     if (rc != 0) {
/* 1918 */       throw new Win32Exception(rc);
/*      */     }
/*      */     try {
/* 1921 */       registryDeleteValue(phkKey.getValue(), valueName);
/*      */     } finally {
/* 1923 */       rc = Advapi32.INSTANCE.RegCloseKey(phkKey.getValue());
/* 1924 */       if (rc != 0) {
/* 1925 */         throw new Win32Exception(rc);
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
/*      */   public static String[] registryGetKeys(WinReg.HKEY hKey) {
/* 1938 */     IntByReference lpcSubKeys = new IntByReference();
/* 1939 */     IntByReference lpcMaxSubKeyLen = new IntByReference();
/*      */     
/* 1941 */     int rc = Advapi32.INSTANCE.RegQueryInfoKey(hKey, null, null, null, lpcSubKeys, lpcMaxSubKeyLen, null, null, null, null, null, null);
/*      */     
/* 1943 */     if (rc != 0) {
/* 1944 */       throw new Win32Exception(rc);
/*      */     }
/* 1946 */     ArrayList<String> keys = new ArrayList<String>(lpcSubKeys.getValue());
/* 1947 */     char[] name = new char[lpcMaxSubKeyLen.getValue() + 1];
/* 1948 */     for (int i = 0; i < lpcSubKeys.getValue(); i++) {
/*      */       
/* 1950 */       IntByReference lpcchValueName = new IntByReference(lpcMaxSubKeyLen.getValue() + 1);
/* 1951 */       rc = Advapi32.INSTANCE.RegEnumKeyEx(hKey, i, name, lpcchValueName, null, null, null, null);
/*      */       
/* 1953 */       if (rc != 0) {
/* 1954 */         throw new Win32Exception(rc);
/*      */       }
/* 1956 */       keys.add(Native.toString(name));
/*      */     } 
/* 1958 */     return keys.<String>toArray(new String[0]);
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
/*      */   public static String[] registryGetKeys(WinReg.HKEY root, String keyPath) {
/* 1971 */     return registryGetKeys(root, keyPath, 0);
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
/*      */   public static String[] registryGetKeys(WinReg.HKEY root, String keyPath, int samDesiredExtra) {
/* 1987 */     WinReg.HKEYByReference phkKey = new WinReg.HKEYByReference();
/* 1988 */     int rc = Advapi32.INSTANCE.RegOpenKeyEx(root, keyPath, 0, 0x20019 | samDesiredExtra, phkKey);
/*      */     
/* 1990 */     if (rc != 0) {
/* 1991 */       throw new Win32Exception(rc);
/*      */     }
/*      */     try {
/* 1994 */       return registryGetKeys(phkKey.getValue());
/*      */     } finally {
/* 1996 */       rc = Advapi32.INSTANCE.RegCloseKey(phkKey.getValue());
/* 1997 */       if (rc != 0) {
/* 1998 */         throw new Win32Exception(rc);
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
/*      */   public static WinReg.HKEYByReference registryGetKey(WinReg.HKEY root, String keyPath, int samDesired) {
/* 2018 */     WinReg.HKEYByReference phkKey = new WinReg.HKEYByReference();
/* 2019 */     int rc = Advapi32.INSTANCE.RegOpenKeyEx(root, keyPath, 0, samDesired, phkKey);
/*      */     
/* 2021 */     if (rc != 0) {
/* 2022 */       throw new Win32Exception(rc);
/*      */     }
/*      */     
/* 2025 */     return phkKey;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void registryCloseKey(WinReg.HKEY hKey) {
/* 2035 */     int rc = Advapi32.INSTANCE.RegCloseKey(hKey);
/* 2036 */     if (rc != 0) {
/* 2037 */       throw new Win32Exception(rc);
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
/*      */   public static TreeMap<String, Object> registryGetValues(WinReg.HKEY hKey) {
/* 2049 */     IntByReference lpcValues = new IntByReference();
/* 2050 */     IntByReference lpcMaxValueNameLen = new IntByReference();
/* 2051 */     IntByReference lpcMaxValueLen = new IntByReference();
/* 2052 */     int rc = Advapi32.INSTANCE.RegQueryInfoKey(hKey, null, null, null, null, null, null, lpcValues, lpcMaxValueNameLen, lpcMaxValueLen, null, null);
/*      */ 
/*      */     
/* 2055 */     if (rc != 0) {
/* 2056 */       throw new Win32Exception(rc);
/*      */     }
/* 2058 */     TreeMap<String, Object> keyValues = new TreeMap<String, Object>();
/* 2059 */     char[] name = new char[lpcMaxValueNameLen.getValue() + 1];
/*      */ 
/*      */ 
/*      */     
/* 2063 */     Memory byteData = new Memory((lpcMaxValueLen.getValue() + 2 * Native.WCHAR_SIZE));
/* 2064 */     for (int i = 0; i < lpcValues.getValue(); i++) {
/* 2065 */       byteData.clear();
/*      */       
/* 2067 */       IntByReference lpcchValueName = new IntByReference(lpcMaxValueNameLen.getValue() + 1);
/*      */       
/* 2069 */       IntByReference lpcbData = new IntByReference(lpcMaxValueLen.getValue());
/* 2070 */       IntByReference lpType = new IntByReference();
/* 2071 */       rc = Advapi32.INSTANCE.RegEnumValue(hKey, i, name, lpcchValueName, (IntByReference)null, lpType, (Pointer)byteData, lpcbData);
/*      */       
/* 2073 */       if (rc != 0) {
/* 2074 */         throw new Win32Exception(rc);
/*      */       }
/*      */       
/* 2077 */       String nameString = Native.toString(name);
/*      */       
/* 2079 */       if (lpcbData.getValue() == 0) {
/* 2080 */         switch (lpType.getValue()) {
/*      */           case 3:
/* 2082 */             keyValues.put(nameString, new byte[0]);
/*      */             break;
/*      */           
/*      */           case 1:
/*      */           case 2:
/* 2087 */             keyValues.put(nameString, new char[0]);
/*      */             break;
/*      */           
/*      */           case 7:
/* 2091 */             keyValues.put(nameString, new String[0]);
/*      */             break;
/*      */           
/*      */           case 0:
/* 2095 */             keyValues.put(nameString, null);
/*      */             break;
/*      */           
/*      */           default:
/* 2099 */             throw new RuntimeException("Unsupported empty type: " + lpType
/* 2100 */                 .getValue());
/*      */         } 
/*      */       } else {
/*      */         ArrayList<String> result;
/*      */         int offset;
/* 2105 */         switch (lpType.getValue()) {
/*      */           case 11:
/* 2107 */             keyValues.put(nameString, Long.valueOf(byteData.getLong(0L)));
/*      */             break;
/*      */           
/*      */           case 4:
/* 2111 */             keyValues.put(nameString, Integer.valueOf(byteData.getInt(0L)));
/*      */             break;
/*      */           
/*      */           case 1:
/*      */           case 2:
/* 2116 */             if (W32APITypeMapper.DEFAULT == W32APITypeMapper.UNICODE) {
/* 2117 */               keyValues.put(nameString, byteData.getWideString(0L)); break;
/*      */             } 
/* 2119 */             keyValues.put(nameString, byteData.getString(0L));
/*      */             break;
/*      */ 
/*      */           
/*      */           case 3:
/* 2124 */             keyValues.put(nameString, byteData
/* 2125 */                 .getByteArray(0L, lpcbData.getValue()));
/*      */             break;
/*      */           
/*      */           case 7:
/* 2129 */             result = new ArrayList<String>();
/* 2130 */             offset = 0;
/* 2131 */             while (offset < byteData.size()) {
/*      */               String s;
/* 2133 */               if (W32APITypeMapper.DEFAULT == W32APITypeMapper.UNICODE) {
/* 2134 */                 s = byteData.getWideString(offset);
/* 2135 */                 offset += s.length() * Native.WCHAR_SIZE;
/* 2136 */                 offset += Native.WCHAR_SIZE;
/*      */               } else {
/* 2138 */                 s = byteData.getString(offset);
/* 2139 */                 offset += s.length();
/* 2140 */                 offset++;
/*      */               } 
/*      */               
/* 2143 */               if (s.length() == 0) {
/*      */                 break;
/*      */               }
/*      */ 
/*      */ 
/*      */               
/* 2149 */               result.add(s);
/*      */             } 
/*      */             
/* 2152 */             keyValues.put(nameString, result.toArray(new String[0]));
/*      */             break;
/*      */           
/*      */           default:
/* 2156 */             throw new RuntimeException("Unsupported type: " + lpType
/* 2157 */                 .getValue());
/*      */         } 
/*      */       } 
/* 2160 */     }  return keyValues;
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
/*      */   public static TreeMap<String, Object> registryGetValues(WinReg.HKEY root, String keyPath) {
/* 2174 */     return registryGetValues(root, keyPath, 0);
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
/*      */   public static TreeMap<String, Object> registryGetValues(WinReg.HKEY root, String keyPath, int samDesiredExtra) {
/* 2191 */     WinReg.HKEYByReference phkKey = new WinReg.HKEYByReference();
/* 2192 */     int rc = Advapi32.INSTANCE.RegOpenKeyEx(root, keyPath, 0, 0x20019 | samDesiredExtra, phkKey);
/*      */     
/* 2194 */     if (rc != 0) {
/* 2195 */       throw new Win32Exception(rc);
/*      */     }
/*      */     try {
/* 2198 */       return registryGetValues(phkKey.getValue());
/*      */     } finally {
/* 2200 */       rc = Advapi32.INSTANCE.RegCloseKey(phkKey.getValue());
/* 2201 */       if (rc != 0) {
/* 2202 */         throw new Win32Exception(rc);
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
/*      */   public static InfoKey registryQueryInfoKey(WinReg.HKEY hKey, int lpcbSecurityDescriptor) {
/* 2219 */     InfoKey infoKey = new InfoKey(hKey, lpcbSecurityDescriptor);
/* 2220 */     int rc = Advapi32.INSTANCE.RegQueryInfoKey(hKey, infoKey.lpClass, infoKey.lpcClass, null, infoKey.lpcSubKeys, infoKey.lpcMaxSubKeyLen, infoKey.lpcMaxClassLen, infoKey.lpcValues, infoKey.lpcMaxValueNameLen, infoKey.lpcMaxValueLen, infoKey.lpcbSecurityDescriptor, infoKey.lpftLastWriteTime);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2227 */     if (rc != 0) {
/* 2228 */       throw new Win32Exception(rc);
/*      */     }
/*      */     
/* 2231 */     return infoKey;
/*      */   }
/*      */   
/*      */   public static class InfoKey {
/*      */     public WinReg.HKEY hKey;
/* 2236 */     public char[] lpClass = new char[260];
/* 2237 */     public IntByReference lpcClass = new IntByReference(260);
/* 2238 */     public IntByReference lpcSubKeys = new IntByReference();
/* 2239 */     public IntByReference lpcMaxSubKeyLen = new IntByReference();
/* 2240 */     public IntByReference lpcMaxClassLen = new IntByReference();
/* 2241 */     public IntByReference lpcValues = new IntByReference();
/* 2242 */     public IntByReference lpcMaxValueNameLen = new IntByReference();
/* 2243 */     public IntByReference lpcMaxValueLen = new IntByReference();
/* 2244 */     public IntByReference lpcbSecurityDescriptor = new IntByReference();
/* 2245 */     public WinBase.FILETIME lpftLastWriteTime = new WinBase.FILETIME();
/*      */ 
/*      */     
/*      */     public InfoKey() {}
/*      */     
/*      */     public InfoKey(WinReg.HKEY hKey, int securityDescriptor) {
/* 2251 */       this.hKey = hKey;
/* 2252 */       this.lpcbSecurityDescriptor = new IntByReference(securityDescriptor);
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
/*      */   public static EnumKey registryRegEnumKey(WinReg.HKEY hKey, int dwIndex) {
/* 2266 */     EnumKey enumKey = new EnumKey(hKey, dwIndex);
/* 2267 */     int rc = Advapi32.INSTANCE.RegEnumKeyEx(hKey, enumKey.dwIndex, enumKey.lpName, enumKey.lpcName, null, enumKey.lpClass, enumKey.lpcbClass, enumKey.lpftLastWriteTime);
/*      */ 
/*      */ 
/*      */     
/* 2271 */     if (rc != 0) {
/* 2272 */       throw new Win32Exception(rc);
/*      */     }
/*      */     
/* 2275 */     return enumKey;
/*      */   }
/*      */   
/*      */   public static class EnumKey {
/*      */     public WinReg.HKEY hKey;
/* 2280 */     public int dwIndex = 0;
/* 2281 */     public char[] lpName = new char[255];
/* 2282 */     public IntByReference lpcName = new IntByReference(255);
/*      */     
/* 2284 */     public char[] lpClass = new char[255];
/* 2285 */     public IntByReference lpcbClass = new IntByReference(255);
/*      */     
/* 2287 */     public WinBase.FILETIME lpftLastWriteTime = new WinBase.FILETIME();
/*      */ 
/*      */     
/*      */     public EnumKey() {}
/*      */     
/*      */     public EnumKey(WinReg.HKEY hKey, int dwIndex) {
/* 2293 */       this.hKey = hKey;
/* 2294 */       this.dwIndex = dwIndex;
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
/*      */   public static String getEnvironmentBlock(Map<String, String> environment) {
/* 2309 */     StringBuilder out = new StringBuilder(environment.size() * 32);
/* 2310 */     for (Map.Entry<String, String> entry : environment.entrySet()) {
/* 2311 */       String key = entry.getKey(), value = entry.getValue();
/* 2312 */       if (value != null) {
/* 2313 */         out.append(key).append("=").append(value).append(false);
/*      */       }
/*      */     } 
/* 2316 */     return out.append(false).toString();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public enum EventLogType
/*      */   {
/* 2323 */     Error, Warning, Informational, AuditSuccess, AuditFailure;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static class EventLogRecord
/*      */   {
/*      */     private WinNT.EVENTLOGRECORD _record;
/*      */ 
/*      */     
/*      */     private String _source;
/*      */     
/*      */     private byte[] _data;
/*      */     
/*      */     private String[] _strings;
/*      */ 
/*      */     
/*      */     public WinNT.EVENTLOGRECORD getRecord() {
/* 2341 */       return this._record;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int getEventId() {
/* 2350 */       return this._record.EventID.intValue();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getSource() {
/* 2359 */       return this._source;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int getStatusCode() {
/* 2368 */       return this._record.EventID.intValue() & 0xFFFF;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int getRecordNumber() {
/* 2379 */       return this._record.RecordNumber.intValue();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int getLength() {
/* 2388 */       return this._record.Length.intValue();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String[] getStrings() {
/* 2397 */       return this._strings;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Advapi32Util.EventLogType getType() {
/* 2406 */       switch (this._record.EventType.intValue()) {
/*      */         case 0:
/*      */         case 4:
/* 2409 */           return Advapi32Util.EventLogType.Informational;
/*      */         case 16:
/* 2411 */           return Advapi32Util.EventLogType.AuditFailure;
/*      */         case 8:
/* 2413 */           return Advapi32Util.EventLogType.AuditSuccess;
/*      */         case 1:
/* 2415 */           return Advapi32Util.EventLogType.Error;
/*      */         case 2:
/* 2417 */           return Advapi32Util.EventLogType.Warning;
/*      */       } 
/* 2419 */       throw new RuntimeException("Invalid type: " + this._record.EventType
/* 2420 */           .intValue());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public byte[] getData() {
/* 2430 */       return this._data;
/*      */     }
/*      */     
/*      */     public EventLogRecord(Pointer pevlr) {
/* 2434 */       this._record = new WinNT.EVENTLOGRECORD(pevlr);
/* 2435 */       this._source = pevlr.getWideString(this._record.size());
/*      */       
/* 2437 */       if (this._record.DataLength.intValue() > 0) {
/* 2438 */         this._data = pevlr.getByteArray(this._record.DataOffset.intValue(), this._record.DataLength
/* 2439 */             .intValue());
/*      */       }
/*      */       
/* 2442 */       if (this._record.NumStrings.intValue() > 0) {
/* 2443 */         ArrayList<String> strings = new ArrayList<String>();
/* 2444 */         int count = this._record.NumStrings.intValue();
/* 2445 */         long offset = this._record.StringOffset.intValue();
/* 2446 */         while (count > 0) {
/* 2447 */           String s = pevlr.getWideString(offset);
/* 2448 */           strings.add(s);
/* 2449 */           offset += (s.length() * Native.WCHAR_SIZE);
/* 2450 */           offset += Native.WCHAR_SIZE;
/* 2451 */           count--;
/*      */         } 
/* 2453 */         this._strings = strings.<String>toArray(new String[0]);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static class EventLogIterator
/*      */     implements Iterable<EventLogRecord>, Iterator<EventLogRecord>
/*      */   {
/*      */     private WinNT.HANDLE _h;
/*      */     
/* 2465 */     private Memory _buffer = new Memory(65536L);
/*      */     
/*      */     private boolean _done = false;
/* 2468 */     private int _dwRead = 0;
/*      */     
/* 2470 */     private Pointer _pevlr = null;
/*      */     private int _flags;
/*      */     
/*      */     public EventLogIterator(String sourceName) {
/* 2474 */       this(null, sourceName, 4);
/*      */     }
/*      */     
/*      */     public EventLogIterator(String serverName, String sourceName, int flags) {
/* 2478 */       this._flags = flags;
/* 2479 */       this._h = Advapi32.INSTANCE.OpenEventLog(serverName, sourceName);
/* 2480 */       if (this._h == null) {
/* 2481 */         throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     private boolean read() {
/* 2487 */       if (this._done || this._dwRead > 0) {
/* 2488 */         return false;
/*      */       }
/*      */       
/* 2491 */       IntByReference pnBytesRead = new IntByReference();
/* 2492 */       IntByReference pnMinNumberOfBytesNeeded = new IntByReference();
/*      */ 
/*      */       
/* 2495 */       if (!Advapi32.INSTANCE.ReadEventLog(this._h, 0x1 | this._flags, 0, (Pointer)this._buffer, 
/* 2496 */           (int)this._buffer.size(), pnBytesRead, pnMinNumberOfBytesNeeded)) {
/*      */ 
/*      */         
/* 2499 */         int rc = Kernel32.INSTANCE.GetLastError();
/*      */ 
/*      */         
/* 2502 */         if (rc == 122) {
/* 2503 */           this._buffer = new Memory(pnMinNumberOfBytesNeeded.getValue());
/*      */           
/* 2505 */           if (!Advapi32.INSTANCE.ReadEventLog(this._h, 0x1 | this._flags, 0, (Pointer)this._buffer, 
/*      */               
/* 2507 */               (int)this._buffer.size(), pnBytesRead, pnMinNumberOfBytesNeeded))
/*      */           {
/* 2509 */             throw new Win32Exception(Kernel32.INSTANCE
/* 2510 */                 .GetLastError());
/*      */           }
/*      */         } else {
/*      */           
/* 2514 */           close();
/* 2515 */           if (rc != 38) {
/* 2516 */             throw new Win32Exception(rc);
/*      */           }
/* 2518 */           return false;
/*      */         } 
/*      */       } 
/*      */       
/* 2522 */       this._dwRead = pnBytesRead.getValue();
/* 2523 */       this._pevlr = (Pointer)this._buffer;
/* 2524 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void close() {
/* 2532 */       this._done = true;
/* 2533 */       if (this._h != null) {
/* 2534 */         if (!Advapi32.INSTANCE.CloseEventLog(this._h)) {
/* 2535 */           throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */         }
/* 2537 */         this._h = null;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Iterator<Advapi32Util.EventLogRecord> iterator() {
/* 2545 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean hasNext() {
/* 2552 */       read();
/* 2553 */       return !this._done;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Advapi32Util.EventLogRecord next() {
/* 2560 */       read();
/* 2561 */       Advapi32Util.EventLogRecord record = new Advapi32Util.EventLogRecord(this._pevlr);
/* 2562 */       this._dwRead -= record.getLength();
/* 2563 */       this._pevlr = this._pevlr.share(record.getLength());
/* 2564 */       return record;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void remove() {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static WinNT.ACE_HEADER[] getFileSecurity(String fileName, boolean compact) {
/*      */     boolean repeat;
/*      */     Memory memory;
/* 2581 */     int infoType = 4;
/* 2582 */     int nLength = 1024;
/*      */ 
/*      */ 
/*      */     
/*      */     do {
/* 2587 */       repeat = false;
/* 2588 */       memory = new Memory(nLength);
/* 2589 */       IntByReference lpnSize = new IntByReference();
/* 2590 */       boolean succeded = Advapi32.INSTANCE.GetFileSecurity(fileName, infoType, (Pointer)memory, nLength, lpnSize);
/*      */ 
/*      */       
/* 2593 */       if (!succeded) {
/* 2594 */         int lastError = Kernel32.INSTANCE.GetLastError();
/* 2595 */         memory.clear();
/* 2596 */         if (122 != lastError) {
/* 2597 */           throw new Win32Exception(lastError);
/*      */         }
/*      */       } 
/* 2600 */       int lengthNeeded = lpnSize.getValue();
/* 2601 */       if (nLength >= lengthNeeded)
/* 2602 */         continue;  repeat = true;
/* 2603 */       nLength = lengthNeeded;
/* 2604 */       memory.clear();
/*      */     }
/* 2606 */     while (repeat);
/*      */     
/* 2608 */     WinNT.SECURITY_DESCRIPTOR_RELATIVE sdr = new WinNT.SECURITY_DESCRIPTOR_RELATIVE((Pointer)memory);
/*      */     
/* 2610 */     WinNT.ACL dacl = sdr.getDiscretionaryACL();
/* 2611 */     WinNT.ACE_HEADER[] aceStructures = dacl.getACEs();
/*      */     
/* 2613 */     if (compact) {
/* 2614 */       List<WinNT.ACE_HEADER> result = new ArrayList<WinNT.ACE_HEADER>();
/* 2615 */       Map<String, WinNT.ACCESS_ACEStructure> aceMap = new HashMap<String, WinNT.ACCESS_ACEStructure>();
/* 2616 */       for (WinNT.ACE_HEADER aceStructure : aceStructures) {
/* 2617 */         if (aceStructure instanceof WinNT.ACCESS_ACEStructure) {
/* 2618 */           WinNT.ACCESS_ACEStructure accessACEStructure = (WinNT.ACCESS_ACEStructure)aceStructure;
/* 2619 */           boolean inherted = ((aceStructure.AceFlags & 0x1F) != 0);
/*      */           
/* 2621 */           String key = accessACEStructure.getSidString() + "/" + inherted + "/" + aceStructure.getClass().getName();
/* 2622 */           WinNT.ACCESS_ACEStructure aceStructure2 = aceMap.get(key);
/* 2623 */           if (aceStructure2 != null) {
/* 2624 */             int accessMask = aceStructure2.Mask;
/* 2625 */             accessMask |= accessACEStructure.Mask;
/* 2626 */             aceStructure2.Mask = accessMask;
/*      */           } else {
/* 2628 */             aceMap.put(key, accessACEStructure);
/* 2629 */             result.add(aceStructure2);
/*      */           } 
/*      */         } else {
/* 2632 */           result.add(aceStructure);
/*      */         } 
/*      */       } 
/* 2635 */       return result.<WinNT.ACE_HEADER>toArray(new WinNT.ACE_HEADER[0]);
/*      */     } 
/*      */     
/* 2638 */     return aceStructures;
/*      */   }
/*      */   
/*      */   public enum AccessCheckPermission {
/* 2642 */     READ(-2147483648),
/* 2643 */     WRITE(1073741824),
/* 2644 */     EXECUTE(536870912);
/*      */     
/*      */     final int code;
/*      */     
/*      */     AccessCheckPermission(int code) {
/* 2649 */       this.code = code;
/*      */     }
/*      */     
/*      */     public int getCode() {
/* 2653 */       return this.code;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private static Memory getSecurityDescriptorForFile(String absoluteFilePath) {
/* 2659 */     int infoType = 7;
/*      */ 
/*      */     
/* 2662 */     IntByReference lpnSize = new IntByReference();
/* 2663 */     boolean succeeded = Advapi32.INSTANCE.GetFileSecurity(absoluteFilePath, 7, null, 0, lpnSize);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2669 */     if (!succeeded) {
/* 2670 */       int lastError = Kernel32.INSTANCE.GetLastError();
/* 2671 */       if (122 != lastError) {
/* 2672 */         throw new Win32Exception(lastError);
/*      */       }
/*      */     } 
/*      */     
/* 2676 */     int nLength = lpnSize.getValue();
/* 2677 */     Memory securityDescriptorMemoryPointer = new Memory(nLength);
/* 2678 */     succeeded = Advapi32.INSTANCE.GetFileSecurity(absoluteFilePath, 7, (Pointer)securityDescriptorMemoryPointer, nLength, lpnSize);
/*      */ 
/*      */     
/* 2681 */     if (!succeeded) {
/* 2682 */       securityDescriptorMemoryPointer.clear();
/* 2683 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     } 
/*      */     
/* 2686 */     return securityDescriptorMemoryPointer;
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
/*      */   public static Memory getSecurityDescriptorForObject(String absoluteObjectPath, int objectType, boolean getSACL) {
/* 2704 */     int infoType = 0x7 | (getSACL ? 8 : 0);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2709 */     PointerByReference ppSecurityDescriptor = new PointerByReference();
/*      */     
/* 2711 */     int lastError = Advapi32.INSTANCE.GetNamedSecurityInfo(absoluteObjectPath, objectType, infoType, null, null, null, null, ppSecurityDescriptor);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2721 */     if (lastError != 0) {
/* 2722 */       throw new Win32Exception(lastError);
/*      */     }
/*      */     
/* 2725 */     int nLength = Advapi32.INSTANCE.GetSecurityDescriptorLength(ppSecurityDescriptor.getValue());
/* 2726 */     Memory memory = new Memory(nLength);
/* 2727 */     Pointer secValue = ppSecurityDescriptor.getValue();
/*      */     try {
/* 2729 */       byte[] data = secValue.getByteArray(0L, nLength);
/* 2730 */       memory.write(0L, data, 0, nLength);
/* 2731 */       return memory;
/*      */     } finally {
/* 2733 */       Kernel32Util.freeLocalMemory(secValue);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void setSecurityDescriptorForObject(String absoluteObjectPath, int objectType, WinNT.SECURITY_DESCRIPTOR_RELATIVE securityDescriptor, boolean setOwner, boolean setGroup, boolean setDACL, boolean setSACL, boolean setDACLProtectedStatus, boolean setSACLProtectedStatus) {
/* 2777 */     WinNT.PSID psidOwner = securityDescriptor.getOwner();
/* 2778 */     WinNT.PSID psidGroup = securityDescriptor.getGroup();
/* 2779 */     WinNT.ACL dacl = securityDescriptor.getDiscretionaryACL();
/* 2780 */     WinNT.ACL sacl = securityDescriptor.getSystemACL();
/*      */     
/* 2782 */     int infoType = 0;
/*      */     
/* 2784 */     if (setOwner) {
/* 2785 */       if (psidOwner == null)
/* 2786 */         throw new IllegalArgumentException("SECURITY_DESCRIPTOR_RELATIVE does not contain owner"); 
/* 2787 */       if (!Advapi32.INSTANCE.IsValidSid(psidOwner))
/* 2788 */         throw new IllegalArgumentException("Owner PSID is invalid"); 
/* 2789 */       infoType |= 0x1;
/*      */     } 
/*      */     
/* 2792 */     if (setGroup) {
/* 2793 */       if (psidGroup == null)
/* 2794 */         throw new IllegalArgumentException("SECURITY_DESCRIPTOR_RELATIVE does not contain group"); 
/* 2795 */       if (!Advapi32.INSTANCE.IsValidSid(psidGroup))
/* 2796 */         throw new IllegalArgumentException("Group PSID is invalid"); 
/* 2797 */       infoType |= 0x2;
/*      */     } 
/*      */     
/* 2800 */     if (setDACL) {
/* 2801 */       if (dacl == null)
/* 2802 */         throw new IllegalArgumentException("SECURITY_DESCRIPTOR_RELATIVE does not contain DACL"); 
/* 2803 */       if (!Advapi32.INSTANCE.IsValidAcl(dacl.getPointer()))
/* 2804 */         throw new IllegalArgumentException("DACL is invalid"); 
/* 2805 */       infoType |= 0x4;
/*      */     } 
/*      */     
/* 2808 */     if (setSACL) {
/* 2809 */       if (sacl == null)
/* 2810 */         throw new IllegalArgumentException("SECURITY_DESCRIPTOR_RELATIVE does not contain SACL"); 
/* 2811 */       if (!Advapi32.INSTANCE.IsValidAcl(sacl.getPointer()))
/* 2812 */         throw new IllegalArgumentException("SACL is invalid"); 
/* 2813 */       infoType |= 0x8;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2821 */     if (setDACLProtectedStatus) {
/* 2822 */       if ((securityDescriptor.Control & 0x1000) != 0) {
/* 2823 */         infoType |= Integer.MIN_VALUE;
/*      */       }
/* 2825 */       else if ((securityDescriptor.Control & 0x1000) == 0) {
/* 2826 */         infoType |= 0x20000000;
/*      */       } 
/*      */     }
/*      */     
/* 2830 */     if (setSACLProtectedStatus) {
/* 2831 */       if ((securityDescriptor.Control & 0x2000) != 0) {
/* 2832 */         infoType |= 0x40000000;
/* 2833 */       } else if ((securityDescriptor.Control & 0x2000) == 0) {
/* 2834 */         infoType |= 0x10000000;
/*      */       } 
/*      */     }
/*      */     
/* 2838 */     int lastError = Advapi32.INSTANCE.SetNamedSecurityInfo(absoluteObjectPath, objectType, infoType, setOwner ? psidOwner
/*      */ 
/*      */ 
/*      */         
/* 2842 */         .getPointer() : null, setGroup ? psidGroup
/* 2843 */         .getPointer() : null, setDACL ? dacl
/* 2844 */         .getPointer() : null, setSACL ? sacl
/* 2845 */         .getPointer() : null);
/*      */     
/* 2847 */     if (lastError != 0) {
/* 2848 */       throw new Win32Exception(lastError);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean accessCheck(File file, AccessCheckPermission permissionToCheck) {
/* 2859 */     Memory securityDescriptorMemoryPointer = getSecurityDescriptorForFile(file.getAbsolutePath().replace('/', '\\'));
/*      */     
/* 2861 */     WinNT.HANDLEByReference openedAccessToken = new WinNT.HANDLEByReference();
/* 2862 */     WinNT.HANDLEByReference duplicatedToken = new WinNT.HANDLEByReference();
/* 2863 */     Win32Exception err = null;
/*      */     try {
/* 2865 */       int desireAccess = 131086;
/* 2866 */       WinNT.HANDLE hProcess = Kernel32.INSTANCE.GetCurrentProcess();
/* 2867 */       if (!Advapi32.INSTANCE.OpenProcessToken(hProcess, desireAccess, openedAccessToken)) {
/* 2868 */         throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */       }
/*      */       
/* 2871 */       if (!Advapi32.INSTANCE.DuplicateToken(openedAccessToken.getValue(), 2, duplicatedToken)) {
/* 2872 */         throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */       }
/*      */       
/* 2875 */       WinNT.GENERIC_MAPPING mapping = new WinNT.GENERIC_MAPPING();
/* 2876 */       mapping.genericRead = new WinDef.DWORD(1179785L);
/* 2877 */       mapping.genericWrite = new WinDef.DWORD(1179926L);
/* 2878 */       mapping.genericExecute = new WinDef.DWORD(1179808L);
/* 2879 */       mapping.genericAll = new WinDef.DWORD(2032127L);
/*      */       
/* 2881 */       WinDef.DWORDByReference rights = new WinDef.DWORDByReference(new WinDef.DWORD(permissionToCheck.getCode()));
/* 2882 */       Advapi32.INSTANCE.MapGenericMask(rights, mapping);
/*      */       
/* 2884 */       WinNT.PRIVILEGE_SET privileges = new WinNT.PRIVILEGE_SET(1);
/* 2885 */       privileges.PrivilegeCount = new WinDef.DWORD(0L);
/* 2886 */       WinDef.DWORDByReference privilegeLength = new WinDef.DWORDByReference(new WinDef.DWORD(privileges.size()));
/*      */       
/* 2888 */       WinDef.DWORDByReference grantedAccess = new WinDef.DWORDByReference();
/* 2889 */       WinDef.BOOLByReference result = new WinDef.BOOLByReference();
/* 2890 */       if (!Advapi32.INSTANCE.AccessCheck((Pointer)securityDescriptorMemoryPointer, duplicatedToken
/* 2891 */           .getValue(), rights
/* 2892 */           .getValue(), mapping, privileges, privilegeLength, grantedAccess, result))
/*      */       {
/*      */         
/* 2895 */         throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */       }
/*      */       
/* 2898 */       return result.getValue().booleanValue();
/* 2899 */     } catch (Win32Exception e) {
/* 2900 */       err = e;
/* 2901 */       throw err;
/*      */     } finally {
/*      */       try {
/* 2904 */         Kernel32Util.closeHandleRefs(new WinNT.HANDLEByReference[] { openedAccessToken, duplicatedToken });
/* 2905 */       } catch (Win32Exception e) {
/* 2906 */         if (err == null) {
/* 2907 */           err = e;
/*      */         } else {
/* 2909 */           err.addSuppressedReflected((Throwable)e);
/*      */         } 
/*      */       } 
/*      */       
/* 2913 */       if (securityDescriptorMemoryPointer != null) {
/* 2914 */         securityDescriptorMemoryPointer.clear();
/*      */       }
/*      */       
/* 2917 */       if (err != null) {
/* 2918 */         throw err;
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
/*      */   public static WinNT.SECURITY_DESCRIPTOR_RELATIVE getFileSecurityDescriptor(File file, boolean getSACL) {
/* 2935 */     Memory securityDesc = getSecurityDescriptorForObject(file.getAbsolutePath().replaceAll("/", "\\"), 1, getSACL);
/* 2936 */     WinNT.SECURITY_DESCRIPTOR_RELATIVE sdr = new WinNT.SECURITY_DESCRIPTOR_RELATIVE((Pointer)securityDesc);
/* 2937 */     return sdr;
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
/*      */   public static void setFileSecurityDescriptor(File file, WinNT.SECURITY_DESCRIPTOR_RELATIVE securityDescriptor, boolean setOwner, boolean setGroup, boolean setDACL, boolean setSACL, boolean setDACLProtectedStatus, boolean setSACLProtectedStatus) {
/* 2969 */     setSecurityDescriptorForObject(file.getAbsolutePath().replaceAll("/", "\\"), 1, securityDescriptor, setOwner, setGroup, setDACL, setSACL, setDACLProtectedStatus, setSACLProtectedStatus);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void encryptFile(File file) {
/* 2979 */     String lpFileName = file.getAbsolutePath();
/* 2980 */     if (!Advapi32.INSTANCE.EncryptFile(lpFileName)) {
/* 2981 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void decryptFile(File file) {
/* 2992 */     String lpFileName = file.getAbsolutePath();
/* 2993 */     if (!Advapi32.INSTANCE.DecryptFile(lpFileName, new WinDef.DWORD(0L))) {
/* 2994 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
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
/*      */   public static int fileEncryptionStatus(File file) {
/* 3006 */     WinDef.DWORDByReference status = new WinDef.DWORDByReference();
/* 3007 */     String lpFileName = file.getAbsolutePath();
/* 3008 */     if (!Advapi32.INSTANCE.FileEncryptionStatus(lpFileName, status)) {
/* 3009 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/* 3011 */     return status.getValue().intValue();
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
/*      */   public static void disableEncryption(File directory, boolean disable) {
/* 3024 */     String dirPath = directory.getAbsolutePath();
/* 3025 */     if (!Advapi32.INSTANCE.EncryptionDisable(dirPath, disable)) {
/* 3026 */       throw new Win32Exception(Native.getLastError());
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
/*      */   public static void backupEncryptedFile(File src, File destDir) {
/* 3044 */     if (!destDir.isDirectory()) {
/* 3045 */       throw new IllegalArgumentException("destDir must be a directory.");
/*      */     }
/*      */     
/* 3048 */     WinDef.ULONG readFlag = new WinDef.ULONG(0L);
/* 3049 */     WinDef.ULONG writeFlag = new WinDef.ULONG(1L);
/*      */     
/* 3051 */     if (src.isDirectory()) {
/* 3052 */       writeFlag.setValue(3L);
/*      */     }
/*      */ 
/*      */     
/* 3056 */     String srcFileName = src.getAbsolutePath();
/* 3057 */     PointerByReference pvContext = new PointerByReference();
/* 3058 */     if (Advapi32.INSTANCE.OpenEncryptedFileRaw(srcFileName, readFlag, pvContext) != 0)
/*      */     {
/* 3060 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*      */ 
/*      */     
/* 3064 */     final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
/* 3065 */     WinBase.FE_EXPORT_FUNC pfExportCallback = new WinBase.FE_EXPORT_FUNC()
/*      */       {
/*      */         public WinDef.DWORD callback(Pointer pbData, Pointer pvCallbackContext, WinDef.ULONG ulLength)
/*      */         {
/* 3069 */           byte[] arr = pbData.getByteArray(0L, ulLength.intValue());
/*      */           try {
/* 3071 */             outputStream.write(arr);
/* 3072 */           } catch (IOException e) {
/* 3073 */             throw new RuntimeException(e);
/*      */           } 
/* 3075 */           return new WinDef.DWORD(0L);
/*      */         }
/*      */       };
/*      */     
/* 3079 */     if (Advapi32.INSTANCE.ReadEncryptedFileRaw(pfExportCallback, null, pvContext
/* 3080 */         .getValue()) != 0) {
/* 3081 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 3086 */       outputStream.close();
/* 3087 */     } catch (IOException e) {
/* 3088 */       throw new RuntimeException(e);
/*      */     } 
/* 3090 */     Advapi32.INSTANCE.CloseEncryptedFileRaw(pvContext.getValue());
/*      */ 
/*      */ 
/*      */     
/* 3094 */     String destFileName = destDir.getAbsolutePath() + File.separator + src.getName();
/* 3095 */     pvContext = new PointerByReference();
/* 3096 */     if (Advapi32.INSTANCE.OpenEncryptedFileRaw(destFileName, writeFlag, pvContext) != 0)
/*      */     {
/* 3098 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*      */ 
/*      */     
/* 3102 */     final IntByReference elementsReadWrapper = new IntByReference(0);
/* 3103 */     WinBase.FE_IMPORT_FUNC pfImportCallback = new WinBase.FE_IMPORT_FUNC()
/*      */       {
/*      */         public WinDef.DWORD callback(Pointer pbData, Pointer pvCallbackContext, WinDef.ULONGByReference ulLength)
/*      */         {
/* 3107 */           int elementsRead = elementsReadWrapper.getValue();
/* 3108 */           int remainingElements = outputStream.size() - elementsRead;
/* 3109 */           int length = Math.min(remainingElements, ulLength.getValue().intValue());
/* 3110 */           pbData.write(0L, outputStream.toByteArray(), elementsRead, length);
/*      */           
/* 3112 */           elementsReadWrapper.setValue(elementsRead + length);
/* 3113 */           ulLength.setValue(new WinDef.ULONG(length));
/* 3114 */           return new WinDef.DWORD(0L);
/*      */         }
/*      */       };
/*      */     
/* 3118 */     if (Advapi32.INSTANCE.WriteEncryptedFileRaw(pfImportCallback, null, pvContext
/* 3119 */         .getValue()) != 0) {
/* 3120 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*      */ 
/*      */     
/* 3124 */     Advapi32.INSTANCE.CloseEncryptedFileRaw(pvContext.getValue());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class Privilege
/*      */     implements Closeable
/*      */   {
/*      */     private boolean currentlyImpersonating = false;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private boolean privilegesEnabled = false;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private final WinNT.LUID[] pLuids;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Privilege(String... privileges) throws IllegalArgumentException, Win32Exception {
/* 3152 */       this.pLuids = new WinNT.LUID[privileges.length];
/* 3153 */       int i = 0;
/* 3154 */       for (String p : privileges) {
/* 3155 */         this.pLuids[i] = new WinNT.LUID();
/* 3156 */         if (!Advapi32.INSTANCE.LookupPrivilegeValue(null, p, this.pLuids[i])) {
/* 3157 */           throw new IllegalArgumentException("Failed to find privilege \"" + privileges[i] + "\" - " + Kernel32.INSTANCE.GetLastError());
/*      */         }
/* 3159 */         i++;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void close() {
/* 3169 */       disable();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Privilege enable() throws Win32Exception {
/* 3181 */       if (this.privilegesEnabled) {
/* 3182 */         return this;
/*      */       }
/*      */       
/* 3185 */       WinNT.HANDLEByReference phThreadToken = new WinNT.HANDLEByReference();
/*      */       
/*      */       try {
/* 3188 */         phThreadToken.setValue(getThreadToken());
/* 3189 */         WinNT.TOKEN_PRIVILEGES tp = new WinNT.TOKEN_PRIVILEGES(this.pLuids.length);
/* 3190 */         for (int i = 0; i < this.pLuids.length; i++) {
/* 3191 */           tp.Privileges[i] = new WinNT.LUID_AND_ATTRIBUTES(this.pLuids[i], new WinDef.DWORD(2L));
/*      */         }
/* 3193 */         if (!Advapi32.INSTANCE.AdjustTokenPrivileges(phThreadToken.getValue(), false, tp, 0, null, null)) {
/* 3194 */           throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */         }
/* 3196 */         this.privilegesEnabled = true;
/*      */       }
/* 3198 */       catch (Win32Exception ex) {
/*      */         
/* 3200 */         if (this.currentlyImpersonating) {
/* 3201 */           Advapi32.INSTANCE.SetThreadToken(null, null);
/* 3202 */           this.currentlyImpersonating = false;
/*      */         
/*      */         }
/* 3205 */         else if (this.privilegesEnabled) {
/* 3206 */           WinNT.TOKEN_PRIVILEGES tp = new WinNT.TOKEN_PRIVILEGES(this.pLuids.length);
/* 3207 */           for (int i = 0; i < this.pLuids.length; i++) {
/* 3208 */             tp.Privileges[i] = new WinNT.LUID_AND_ATTRIBUTES(this.pLuids[i], new WinDef.DWORD(0L));
/*      */           }
/* 3210 */           Advapi32.INSTANCE.AdjustTokenPrivileges(phThreadToken.getValue(), false, tp, 0, null, null);
/* 3211 */           this.privilegesEnabled = false;
/*      */         } 
/*      */         
/* 3214 */         throw ex;
/*      */       }
/*      */       finally {
/*      */         
/* 3218 */         if (phThreadToken.getValue() != WinBase.INVALID_HANDLE_VALUE && phThreadToken
/* 3219 */           .getValue() != null) {
/* 3220 */           Kernel32.INSTANCE.CloseHandle(phThreadToken.getValue());
/* 3221 */           phThreadToken.setValue(null);
/*      */         } 
/*      */       } 
/* 3224 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void disable() throws Win32Exception {
/* 3233 */       WinNT.HANDLEByReference phThreadToken = new WinNT.HANDLEByReference();
/*      */       
/*      */       try {
/* 3236 */         phThreadToken.setValue(getThreadToken());
/* 3237 */         if (this.currentlyImpersonating) {
/* 3238 */           Advapi32.INSTANCE.SetThreadToken(null, null);
/*      */ 
/*      */         
/*      */         }
/* 3242 */         else if (this.privilegesEnabled) {
/* 3243 */           WinNT.TOKEN_PRIVILEGES tp = new WinNT.TOKEN_PRIVILEGES(this.pLuids.length);
/* 3244 */           for (int i = 0; i < this.pLuids.length; i++) {
/* 3245 */             tp.Privileges[i] = new WinNT.LUID_AND_ATTRIBUTES(this.pLuids[i], new WinDef.DWORD(0L));
/*      */           }
/* 3247 */           Advapi32.INSTANCE.AdjustTokenPrivileges(phThreadToken.getValue(), false, tp, 0, null, null);
/* 3248 */           this.privilegesEnabled = false;
/*      */         }
/*      */       
/*      */       }
/*      */       finally {
/*      */         
/* 3254 */         if (phThreadToken.getValue() != WinBase.INVALID_HANDLE_VALUE && phThreadToken
/* 3255 */           .getValue() != null) {
/* 3256 */           Kernel32.INSTANCE.CloseHandle(phThreadToken.getValue());
/* 3257 */           phThreadToken.setValue(null);
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WinNT.HANDLE getThreadToken() throws Win32Exception {
/* 3270 */       WinNT.HANDLEByReference phThreadToken = new WinNT.HANDLEByReference();
/* 3271 */       WinNT.HANDLEByReference phProcessToken = new WinNT.HANDLEByReference();
/*      */ 
/*      */       
/*      */       try {
/* 3275 */         if (!Advapi32.INSTANCE.OpenThreadToken(Kernel32.INSTANCE.GetCurrentThread(), 32, false, phThreadToken))
/*      */         {
/*      */ 
/*      */ 
/*      */           
/* 3280 */           int lastError = Kernel32.INSTANCE.GetLastError();
/* 3281 */           if (1008 != lastError) {
/* 3282 */             throw new Win32Exception(lastError);
/*      */           }
/*      */ 
/*      */           
/* 3286 */           if (!Advapi32.INSTANCE.OpenProcessToken(Kernel32.INSTANCE.GetCurrentProcess(), 2, phProcessToken)) {
/* 3287 */             throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */           }
/*      */ 
/*      */           
/* 3291 */           if (!Advapi32.INSTANCE.DuplicateTokenEx(phProcessToken.getValue(), 36, null, 2, 2, phThreadToken))
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 3297 */             throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */           }
/*      */ 
/*      */           
/* 3301 */           if (!Advapi32.INSTANCE.SetThreadToken(null, phThreadToken.getValue())) {
/* 3302 */             throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */           }
/* 3304 */           this.currentlyImpersonating = true;
/*      */         }
/*      */       
/* 3307 */       } catch (Win32Exception ex) {
/*      */         
/* 3309 */         if (phThreadToken.getValue() != WinBase.INVALID_HANDLE_VALUE && phThreadToken
/* 3310 */           .getValue() != null) {
/* 3311 */           Kernel32.INSTANCE.CloseHandle(phThreadToken.getValue());
/* 3312 */           phThreadToken.setValue(null);
/*      */         } 
/* 3314 */         throw ex;
/*      */       
/*      */       }
/*      */       finally {
/*      */         
/* 3319 */         if (phProcessToken.getValue() != WinBase.INVALID_HANDLE_VALUE && phProcessToken
/* 3320 */           .getValue() != null) {
/* 3321 */           Kernel32.INSTANCE.CloseHandle(phProcessToken.getValue());
/* 3322 */           phProcessToken.setValue(null);
/*      */         } 
/*      */       } 
/*      */       
/* 3326 */       return phThreadToken.getValue();
/*      */     }
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/sun/jna/platform/win32/Advapi32Util.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */