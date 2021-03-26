/*     */ package com.sun.jna.platform.win32;
/*     */ 
/*     */ import com.sun.jna.Pointer;
/*     */ import com.sun.jna.Structure;
/*     */ import com.sun.jna.ptr.IntByReference;
/*     */ import com.sun.jna.ptr.PointerByReference;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Netapi32Util
/*     */ {
/*     */   public static class Group
/*     */   {
/*     */     public String name;
/*     */   }
/*     */   
/*     */   public static class User
/*     */   {
/*     */     public String name;
/*     */     public String comment;
/*     */   }
/*     */   
/*     */   public static class UserInfo
/*     */     extends User
/*     */   {
/*     */     public String fullName;
/*     */     public String sidString;
/*     */     public WinNT.PSID sid;
/*     */     public int flags;
/*     */   }
/*     */   
/*     */   public static class LocalGroup
/*     */     extends Group
/*     */   {
/*     */     public String comment;
/*     */   }
/*     */   
/*     */   public static String getDCName() {
/* 105 */     return getDCName(null, null);
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
/*     */   public static String getDCName(String serverName, String domainName) {
/* 119 */     PointerByReference bufptr = new PointerByReference();
/*     */     try {
/* 121 */       int rc = Netapi32.INSTANCE.NetGetDCName(domainName, serverName, bufptr);
/* 122 */       if (0 != rc) {
/* 123 */         throw new Win32Exception(rc);
/*     */       }
/* 125 */       return bufptr.getValue().getWideString(0L);
/*     */     } finally {
/* 127 */       if (0 != Netapi32.INSTANCE.NetApiBufferFree(bufptr.getValue())) {
/* 128 */         throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getJoinStatus() {
/* 138 */     return getJoinStatus(null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getJoinStatus(String computerName) {
/* 147 */     PointerByReference lpNameBuffer = new PointerByReference();
/* 148 */     IntByReference bufferType = new IntByReference();
/*     */     
/*     */     try {
/* 151 */       int rc = Netapi32.INSTANCE.NetGetJoinInformation(computerName, lpNameBuffer, bufferType);
/* 152 */       if (0 != rc) {
/* 153 */         throw new Win32Exception(rc);
/*     */       }
/* 155 */       return bufferType.getValue();
/*     */     } finally {
/* 157 */       if (lpNameBuffer.getPointer() != null) {
/* 158 */         int rc = Netapi32.INSTANCE.NetApiBufferFree(lpNameBuffer.getValue());
/* 159 */         if (0 != rc) {
/* 160 */           throw new Win32Exception(rc);
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
/*     */   public static String getDomainName(String computerName) {
/* 172 */     PointerByReference lpNameBuffer = new PointerByReference();
/* 173 */     IntByReference bufferType = new IntByReference();
/*     */     
/*     */     try {
/* 176 */       int rc = Netapi32.INSTANCE.NetGetJoinInformation(computerName, lpNameBuffer, bufferType);
/* 177 */       if (0 != rc) {
/* 178 */         throw new Win32Exception(rc);
/*     */       }
/*     */       
/* 181 */       return lpNameBuffer.getValue().getWideString(0L);
/*     */     } finally {
/* 183 */       if (lpNameBuffer.getPointer() != null) {
/* 184 */         int rc = Netapi32.INSTANCE.NetApiBufferFree(lpNameBuffer.getValue());
/* 185 */         if (0 != rc) {
/* 186 */           throw new Win32Exception(rc);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static LocalGroup[] getLocalGroups() {
/* 197 */     return getLocalGroups(null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static LocalGroup[] getLocalGroups(String serverName) {
/* 206 */     PointerByReference bufptr = new PointerByReference();
/* 207 */     IntByReference entriesRead = new IntByReference();
/* 208 */     IntByReference totalEntries = new IntByReference();
/*     */     try {
/* 210 */       int rc = Netapi32.INSTANCE.NetLocalGroupEnum(serverName, 1, bufptr, -1, entriesRead, totalEntries, null);
/* 211 */       if (0 != rc || bufptr.getValue() == Pointer.NULL) {
/* 212 */         throw new Win32Exception(rc);
/*     */       }
/* 214 */       LMAccess.LOCALGROUP_INFO_1 group = new LMAccess.LOCALGROUP_INFO_1(bufptr.getValue());
/* 215 */       LMAccess.LOCALGROUP_INFO_1[] groups = (LMAccess.LOCALGROUP_INFO_1[])group.toArray(entriesRead.getValue());
/*     */       
/* 217 */       ArrayList<LocalGroup> result = new ArrayList<LocalGroup>();
/* 218 */       for (LMAccess.LOCALGROUP_INFO_1 lgpi : groups) {
/* 219 */         LocalGroup lgp = new LocalGroup();
/* 220 */         if (lgpi.lgrui1_name != null) {
/* 221 */           lgp.name = lgpi.lgrui1_name.toString();
/*     */         }
/* 223 */         if (lgpi.lgrui1_comment != null) {
/* 224 */           lgp.comment = lgpi.lgrui1_comment.toString();
/*     */         }
/* 226 */         result.add(lgp);
/*     */       } 
/* 228 */       return result.<LocalGroup>toArray(new LocalGroup[0]);
/*     */     } finally {
/* 230 */       if (bufptr.getValue() != Pointer.NULL) {
/* 231 */         int rc = Netapi32.INSTANCE.NetApiBufferFree(bufptr.getValue());
/* 232 */         if (0 != rc) {
/* 233 */           throw new Win32Exception(rc);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Group[] getGlobalGroups() {
/* 244 */     return getGlobalGroups(null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Group[] getGlobalGroups(String serverName) {
/* 253 */     PointerByReference bufptr = new PointerByReference();
/* 254 */     IntByReference entriesRead = new IntByReference();
/* 255 */     IntByReference totalEntries = new IntByReference();
/*     */     try {
/* 257 */       int rc = Netapi32.INSTANCE.NetGroupEnum(serverName, 1, bufptr, -1, entriesRead, totalEntries, null);
/*     */ 
/*     */       
/* 260 */       if (0 != rc || bufptr.getValue() == Pointer.NULL) {
/* 261 */         throw new Win32Exception(rc);
/*     */       }
/* 263 */       LMAccess.GROUP_INFO_1 group = new LMAccess.GROUP_INFO_1(bufptr.getValue());
/* 264 */       LMAccess.GROUP_INFO_1[] groups = (LMAccess.GROUP_INFO_1[])group.toArray(entriesRead.getValue());
/*     */       
/* 266 */       ArrayList<LocalGroup> result = new ArrayList<LocalGroup>();
/* 267 */       for (LMAccess.GROUP_INFO_1 lgpi : groups) {
/* 268 */         LocalGroup lgp = new LocalGroup();
/* 269 */         if (lgpi.grpi1_name != null) {
/* 270 */           lgp.name = lgpi.grpi1_name.toString();
/*     */         }
/* 272 */         if (lgpi.grpi1_comment != null) {
/* 273 */           lgp.comment = lgpi.grpi1_comment.toString();
/*     */         }
/* 275 */         result.add(lgp);
/*     */       } 
/* 277 */       return result.<Group>toArray((Group[])new LocalGroup[0]);
/*     */     } finally {
/* 279 */       if (bufptr.getValue() != Pointer.NULL) {
/* 280 */         int rc = Netapi32.INSTANCE.NetApiBufferFree(bufptr.getValue());
/* 281 */         if (0 != rc) {
/* 282 */           throw new Win32Exception(rc);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static User[] getUsers() {
/* 293 */     return getUsers(null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static User[] getUsers(String serverName) {
/* 302 */     PointerByReference bufptr = new PointerByReference();
/* 303 */     IntByReference entriesRead = new IntByReference();
/* 304 */     IntByReference totalEntries = new IntByReference();
/*     */     try {
/* 306 */       int rc = Netapi32.INSTANCE.NetUserEnum(serverName, 1, 0, bufptr, -1, entriesRead, totalEntries, null);
/*     */ 
/*     */ 
/*     */       
/* 310 */       if (0 != rc || bufptr.getValue() == Pointer.NULL) {
/* 311 */         throw new Win32Exception(rc);
/*     */       }
/* 313 */       LMAccess.USER_INFO_1 user = new LMAccess.USER_INFO_1(bufptr.getValue());
/* 314 */       LMAccess.USER_INFO_1[] users = (LMAccess.USER_INFO_1[])user.toArray(entriesRead.getValue());
/* 315 */       ArrayList<User> result = new ArrayList<User>();
/* 316 */       for (LMAccess.USER_INFO_1 lu : users) {
/* 317 */         User auser = new User();
/* 318 */         if (lu.usri1_name != null) {
/* 319 */           auser.name = lu.usri1_name.toString();
/*     */         }
/* 321 */         result.add(auser);
/*     */       } 
/* 323 */       return result.<User>toArray(new User[0]);
/*     */     } finally {
/* 325 */       if (bufptr.getValue() != Pointer.NULL) {
/* 326 */         int rc = Netapi32.INSTANCE.NetApiBufferFree(bufptr.getValue());
/* 327 */         if (0 != rc) {
/* 328 */           throw new Win32Exception(rc);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Group[] getCurrentUserLocalGroups() {
/* 339 */     return getUserLocalGroups(Secur32Util.getUserNameEx(2));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Group[] getUserLocalGroups(String userName) {
/* 348 */     return getUserLocalGroups(userName, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Group[] getUserLocalGroups(String userName, String serverName) {
/* 358 */     PointerByReference bufptr = new PointerByReference();
/* 359 */     IntByReference entriesread = new IntByReference();
/* 360 */     IntByReference totalentries = new IntByReference();
/*     */     try {
/* 362 */       int rc = Netapi32.INSTANCE.NetUserGetLocalGroups(serverName, userName, 0, 0, bufptr, -1, entriesread, totalentries);
/*     */ 
/*     */       
/* 365 */       if (rc != 0) {
/* 366 */         throw new Win32Exception(rc);
/*     */       }
/* 368 */       LMAccess.LOCALGROUP_USERS_INFO_0 lgroup = new LMAccess.LOCALGROUP_USERS_INFO_0(bufptr.getValue());
/* 369 */       LMAccess.LOCALGROUP_USERS_INFO_0[] lgroups = (LMAccess.LOCALGROUP_USERS_INFO_0[])lgroup.toArray(entriesread.getValue());
/* 370 */       ArrayList<Group> result = new ArrayList<Group>();
/* 371 */       for (LMAccess.LOCALGROUP_USERS_INFO_0 lgpi : lgroups) {
/* 372 */         LocalGroup lgp = new LocalGroup();
/* 373 */         if (lgpi.lgrui0_name != null) {
/* 374 */           lgp.name = lgpi.lgrui0_name.toString();
/*     */         }
/* 376 */         result.add(lgp);
/*     */       } 
/* 378 */       return result.<Group>toArray(new Group[0]);
/*     */     } finally {
/* 380 */       if (bufptr.getValue() != Pointer.NULL) {
/* 381 */         int rc = Netapi32.INSTANCE.NetApiBufferFree(bufptr.getValue());
/* 382 */         if (0 != rc) {
/* 383 */           throw new Win32Exception(rc);
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
/*     */   public static Group[] getUserGroups(String userName) {
/* 395 */     return getUserGroups(userName, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Group[] getUserGroups(String userName, String serverName) {
/* 405 */     PointerByReference bufptr = new PointerByReference();
/* 406 */     IntByReference entriesread = new IntByReference();
/* 407 */     IntByReference totalentries = new IntByReference();
/*     */     try {
/* 409 */       int rc = Netapi32.INSTANCE.NetUserGetGroups(serverName, userName, 0, bufptr, -1, entriesread, totalentries);
/*     */ 
/*     */       
/* 412 */       if (rc != 0) {
/* 413 */         throw new Win32Exception(rc);
/*     */       }
/* 415 */       LMAccess.GROUP_USERS_INFO_0 lgroup = new LMAccess.GROUP_USERS_INFO_0(bufptr.getValue());
/* 416 */       LMAccess.GROUP_USERS_INFO_0[] lgroups = (LMAccess.GROUP_USERS_INFO_0[])lgroup.toArray(entriesread.getValue());
/* 417 */       ArrayList<Group> result = new ArrayList<Group>();
/* 418 */       for (LMAccess.GROUP_USERS_INFO_0 lgpi : lgroups) {
/* 419 */         Group lgp = new Group();
/* 420 */         if (lgpi.grui0_name != null) {
/* 421 */           lgp.name = lgpi.grui0_name.toString();
/*     */         }
/* 423 */         result.add(lgp);
/*     */       } 
/* 425 */       return result.<Group>toArray(new Group[0]);
/*     */     } finally {
/* 427 */       if (bufptr.getValue() != Pointer.NULL) {
/* 428 */         int rc = Netapi32.INSTANCE.NetApiBufferFree(bufptr.getValue());
/* 429 */         if (0 != rc) {
/* 430 */           throw new Win32Exception(rc);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class DomainController
/*     */   {
/*     */     public String name;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String address;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int addressType;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Guid.GUID domainGuid;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String domainName;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String dnsForestName;
/*     */ 
/*     */ 
/*     */     
/*     */     public int flags;
/*     */ 
/*     */ 
/*     */     
/*     */     public String clientSiteName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static DomainController getDC() {
/* 482 */     DsGetDC.PDOMAIN_CONTROLLER_INFO pdci = new DsGetDC.PDOMAIN_CONTROLLER_INFO();
/* 483 */     int rc = Netapi32.INSTANCE.DsGetDcName(null, null, null, null, 0, pdci);
/* 484 */     if (0 != rc) {
/* 485 */       throw new Win32Exception(rc);
/*     */     }
/* 487 */     DomainController dc = new DomainController();
/* 488 */     if (pdci.dci.DomainControllerAddress != null) {
/* 489 */       dc.address = pdci.dci.DomainControllerAddress.toString();
/*     */     }
/* 491 */     dc.addressType = pdci.dci.DomainControllerAddressType;
/* 492 */     if (pdci.dci.ClientSiteName != null) {
/* 493 */       dc.clientSiteName = pdci.dci.ClientSiteName.toString();
/*     */     }
/* 495 */     if (pdci.dci.DnsForestName != null) {
/* 496 */       dc.dnsForestName = pdci.dci.DnsForestName.toString();
/*     */     }
/* 498 */     dc.domainGuid = pdci.dci.DomainGuid;
/* 499 */     if (pdci.dci.DomainName != null) {
/* 500 */       dc.domainName = pdci.dci.DomainName.toString();
/*     */     }
/* 502 */     dc.flags = pdci.dci.Flags;
/* 503 */     if (pdci.dci.DomainControllerName != null) {
/* 504 */       dc.name = pdci.dci.DomainControllerName.toString();
/*     */     }
/* 506 */     rc = Netapi32.INSTANCE.NetApiBufferFree(pdci.dci.getPointer());
/* 507 */     if (0 != rc) {
/* 508 */       throw new Win32Exception(rc);
/*     */     }
/* 510 */     return dc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class DomainTrust
/*     */   {
/*     */     public String NetbiosDomainName;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String DnsDomainName;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public WinNT.PSID DomainSid;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String DomainSidString;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Guid.GUID DomainGuid;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String DomainGuidString;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private int flags;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean isInForest() {
/* 557 */       return ((this.flags & 0x1) != 0);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean isOutbound() {
/* 568 */       return ((this.flags & 0x2) != 0);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean isRoot() {
/* 579 */       return ((this.flags & 0x4) != 0);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean isPrimary() {
/* 589 */       return ((this.flags & 0x8) != 0);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean isNativeMode() {
/* 598 */       return ((this.flags & 0x10) != 0);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean isInbound() {
/* 609 */       return ((this.flags & 0x20) != 0);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static DomainTrust[] getDomainTrusts() {
/* 619 */     return getDomainTrusts(null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static DomainTrust[] getDomainTrusts(String serverName) {
/* 630 */     IntByReference domainTrustCount = new IntByReference();
/* 631 */     PointerByReference domainsPointerRef = new PointerByReference();
/* 632 */     int rc = Netapi32.INSTANCE.DsEnumerateDomainTrusts(serverName, 63, domainsPointerRef, domainTrustCount);
/*     */     
/* 634 */     if (0 != rc) {
/* 635 */       throw new Win32Exception(rc);
/*     */     }
/*     */     try {
/* 638 */       DsGetDC.DS_DOMAIN_TRUSTS domainTrustRefs = new DsGetDC.DS_DOMAIN_TRUSTS(domainsPointerRef.getValue());
/* 639 */       DsGetDC.DS_DOMAIN_TRUSTS[] domainTrusts = (DsGetDC.DS_DOMAIN_TRUSTS[])domainTrustRefs.toArray((Structure[])new DsGetDC.DS_DOMAIN_TRUSTS[domainTrustCount.getValue()]);
/* 640 */       ArrayList<DomainTrust> trusts = new ArrayList<DomainTrust>(domainTrustCount.getValue());
/* 641 */       for (DsGetDC.DS_DOMAIN_TRUSTS domainTrust : domainTrusts) {
/* 642 */         DomainTrust t = new DomainTrust();
/* 643 */         if (domainTrust.DnsDomainName != null) {
/* 644 */           t.DnsDomainName = domainTrust.DnsDomainName.toString();
/*     */         }
/* 646 */         if (domainTrust.NetbiosDomainName != null) {
/* 647 */           t.NetbiosDomainName = domainTrust.NetbiosDomainName.toString();
/*     */         }
/* 649 */         t.DomainSid = domainTrust.DomainSid;
/* 650 */         if (domainTrust.DomainSid != null) {
/* 651 */           t.DomainSidString = Advapi32Util.convertSidToStringSid(domainTrust.DomainSid);
/*     */         }
/* 653 */         t.DomainGuid = domainTrust.DomainGuid;
/* 654 */         if (domainTrust.DomainGuid != null) {
/* 655 */           t.DomainGuidString = Ole32Util.getStringFromGUID(domainTrust.DomainGuid);
/*     */         }
/* 657 */         t.flags = domainTrust.Flags;
/* 658 */         trusts.add(t);
/*     */       } 
/* 660 */       return trusts.<DomainTrust>toArray(new DomainTrust[0]);
/*     */     } finally {
/* 662 */       rc = Netapi32.INSTANCE.NetApiBufferFree(domainsPointerRef.getValue());
/* 663 */       if (0 != rc) {
/* 664 */         throw new Win32Exception(rc);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static UserInfo getUserInfo(String accountName) {
/* 670 */     return getUserInfo(accountName, getDCName());
/*     */   }
/*     */   
/*     */   public static UserInfo getUserInfo(String accountName, String domainName) {
/* 674 */     PointerByReference bufptr = new PointerByReference();
/* 675 */     int rc = -1;
/*     */     try {
/* 677 */       rc = Netapi32.INSTANCE.NetUserGetInfo(domainName, accountName, 23, bufptr);
/* 678 */       if (rc == 0) {
/* 679 */         LMAccess.USER_INFO_23 info_23 = new LMAccess.USER_INFO_23(bufptr.getValue());
/* 680 */         UserInfo userInfo = new UserInfo();
/* 681 */         if (info_23.usri23_comment != null) {
/* 682 */           userInfo.comment = info_23.usri23_comment.toString();
/*     */         }
/* 684 */         userInfo.flags = info_23.usri23_flags;
/* 685 */         if (info_23.usri23_full_name != null) {
/* 686 */           userInfo.fullName = info_23.usri23_full_name.toString();
/*     */         }
/* 688 */         if (info_23.usri23_name != null) {
/* 689 */           userInfo.name = info_23.usri23_name.toString();
/*     */         }
/* 691 */         if (info_23.usri23_user_sid != null) {
/* 692 */           userInfo.sidString = Advapi32Util.convertSidToStringSid(info_23.usri23_user_sid);
/*     */         }
/* 694 */         userInfo.sid = info_23.usri23_user_sid;
/* 695 */         return userInfo;
/*     */       } 
/* 697 */       throw new Win32Exception(rc);
/*     */     } finally {
/*     */       
/* 700 */       if (bufptr.getValue() != Pointer.NULL)
/* 701 */         Netapi32.INSTANCE.NetApiBufferFree(bufptr.getValue()); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/sun/jna/platform/win32/Netapi32Util.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */