/*     */ package com.sun.jna.platform.win32;
/*     */ 
/*     */ import com.sun.jna.Native;
/*     */ import com.sun.jna.Pointer;
/*     */ import com.sun.jna.Structure;
/*     */ import com.sun.jna.Structure.FieldOrder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface Tlhelp32
/*     */ {
/*  43 */   public static final WinDef.DWORD TH32CS_SNAPHEAPLIST = new WinDef.DWORD(1L);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   public static final WinDef.DWORD TH32CS_SNAPPROCESS = new WinDef.DWORD(2L);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   public static final WinDef.DWORD TH32CS_SNAPTHREAD = new WinDef.DWORD(4L);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  71 */   public static final WinDef.DWORD TH32CS_SNAPMODULE = new WinDef.DWORD(8L);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  78 */   public static final WinDef.DWORD TH32CS_SNAPMODULE32 = new WinDef.DWORD(16L);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   public static final WinDef.DWORD TH32CS_SNAPALL = new WinDef.DWORD((TH32CS_SNAPHEAPLIST.intValue() | TH32CS_SNAPPROCESS
/*  84 */       .intValue() | TH32CS_SNAPTHREAD.intValue() | TH32CS_SNAPMODULE.intValue()));
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  89 */   public static final WinDef.DWORD TH32CS_INHERIT = new WinDef.DWORD(-2147483648L);
/*     */   public static final int MAX_MODULE_NAME32 = 255;
/*     */   
/*     */   @FieldOrder({"dwSize", "cntUsage", "th32ProcessID", "th32DefaultHeapID", "th32ModuleID", "cntThreads", "th32ParentProcessID", "pcPriClassBase", "dwFlags", "szExeFile"})
/*     */   public static class PROCESSENTRY32 extends Structure {
/*     */     public WinDef.DWORD dwSize;
/*     */     public WinDef.DWORD cntUsage;
/*     */     public WinDef.DWORD th32ProcessID;
/*     */     public BaseTSD.ULONG_PTR th32DefaultHeapID;
/*     */     public WinDef.DWORD th32ModuleID;
/*     */     public WinDef.DWORD cntThreads;
/*     */     public WinDef.DWORD th32ParentProcessID;
/*     */     public WinDef.LONG pcPriClassBase;
/*     */     public WinDef.DWORD dwFlags;
/*     */     
/*     */     public static class ByReference extends PROCESSENTRY32 implements Structure.ByReference {
/*     */       public ByReference(Pointer memory) {
/* 106 */         super(memory);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       public ByReference() {}
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 162 */     public char[] szExeFile = new char[260];
/*     */     
/*     */     public PROCESSENTRY32() {
/* 165 */       this.dwSize = new WinDef.DWORD(size());
/*     */     }
/*     */     
/*     */     public PROCESSENTRY32(Pointer memory) {
/* 169 */       super(memory);
/* 170 */       read();
/*     */     }
/*     */   }
/*     */   
/*     */   @FieldOrder({"dwSize", "th32ModuleID", "th32ProcessID", "GlblcntUsage", "ProccntUsage", "modBaseAddr", "modBaseSize", "hModule", "szModule", "szExePath"})
/*     */   public static class MODULEENTRY32W
/*     */     extends Structure
/*     */   {
/*     */     public WinDef.DWORD dwSize;
/*     */     public WinDef.DWORD th32ModuleID;
/*     */     public WinDef.DWORD th32ProcessID;
/*     */     public WinDef.DWORD GlblcntUsage;
/*     */     public WinDef.DWORD ProccntUsage;
/*     */     public Pointer modBaseAddr;
/*     */     public WinDef.DWORD modBaseSize;
/*     */     public WinDef.HMODULE hModule;
/*     */     
/*     */     public static class ByReference
/*     */       extends MODULEENTRY32W
/*     */       implements Structure.ByReference {
/*     */       public ByReference() {}
/*     */       
/*     */       public ByReference(Pointer memory) {
/* 193 */         super(memory);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 244 */     public char[] szModule = new char[256];
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 249 */     public char[] szExePath = new char[260];
/*     */     
/*     */     public MODULEENTRY32W() {
/* 252 */       this.dwSize = new WinDef.DWORD(size());
/*     */     }
/*     */     
/*     */     public MODULEENTRY32W(Pointer memory) {
/* 256 */       super(memory);
/* 257 */       read();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String szModule() {
/* 264 */       return Native.toString(this.szModule);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String szExePath() {
/* 271 */       return Native.toString(this.szExePath);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/sun/jna/platform/win32/Tlhelp32.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */