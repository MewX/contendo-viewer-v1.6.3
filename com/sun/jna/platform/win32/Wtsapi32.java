/*     */ package com.sun.jna.platform.win32;
/*     */ 
/*     */ import com.sun.jna.Native;
/*     */ import com.sun.jna.Pointer;
/*     */ import com.sun.jna.Structure;
/*     */ import com.sun.jna.Structure.FieldOrder;
/*     */ import com.sun.jna.ptr.IntByReference;
/*     */ import com.sun.jna.ptr.PointerByReference;
/*     */ import com.sun.jna.win32.StdCallLibrary;
/*     */ import com.sun.jna.win32.W32APIOptions;
/*     */ import com.sun.jna.win32.W32APITypeMapper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface Wtsapi32
/*     */   extends StdCallLibrary
/*     */ {
/*  42 */   public static final Wtsapi32 INSTANCE = (Wtsapi32)Native.load("Wtsapi32", Wtsapi32.class, W32APIOptions.DEFAULT_OPTIONS);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int NOTIFY_FOR_ALL_SESSIONS = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int NOTIFY_FOR_THIS_SESSION = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int WTS_CONSOLE_CONNECT = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int WTS_CONSOLE_DISCONNECT = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int WTS_REMOTE_CONNECT = 3;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int WTS_REMOTE_DISCONNECT = 4;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int WTS_SESSION_LOGON = 5;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int WTS_SESSION_LOGOFF = 6;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int WTS_SESSION_LOCK = 7;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int WTS_SESSION_UNLOCK = 8;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int WTS_SESSION_REMOTE_CONTROL = 9;
/*     */ 
/*     */ 
/*     */   
/* 101 */   public static final WinNT.HANDLE WTS_CURRENT_SERVER_HANDLE = new WinNT.HANDLE(null);
/*     */   
/*     */   public static final int WTS_CURRENT_SESSION = -1;
/*     */   
/*     */   public static final int WTS_ANY_SESSION = -2;
/*     */   
/*     */   public static final int WTS_PROCESS_INFO_LEVEL_0 = 0;
/*     */   
/*     */   public static final int WTS_PROCESS_INFO_LEVEL_1 = 1;
/*     */ 
/*     */   
/*     */   boolean WTSRegisterSessionNotification(WinDef.HWND paramHWND, int paramInt);
/*     */ 
/*     */   
/*     */   boolean WTSUnRegisterSessionNotification(WinDef.HWND paramHWND);
/*     */ 
/*     */   
/*     */   boolean WTSEnumerateProcessesEx(WinNT.HANDLE paramHANDLE, IntByReference paramIntByReference1, int paramInt, PointerByReference paramPointerByReference, IntByReference paramIntByReference2);
/*     */ 
/*     */   
/*     */   boolean WTSFreeMemoryEx(int paramInt1, Pointer paramPointer, int paramInt2);
/*     */ 
/*     */   
/*     */   @FieldOrder({"SessionId", "ProcessId", "pProcessName", "pUserSid", "NumberOfThreads", "HandleCount", "PagefileUsage", "PeakPagefileUsage", "WorkingSetSize", "PeakWorkingSetSize", "UserTime", "KernelTime"})
/*     */   public static class WTS_PROCESS_INFO_EX
/*     */     extends Structure
/*     */   {
/*     */     public int SessionId;
/*     */     
/*     */     public int ProcessId;
/*     */     
/*     */     public String pProcessName;
/*     */     public WinNT.PSID pUserSid;
/*     */     public int NumberOfThreads;
/*     */     public int HandleCount;
/*     */     public int PagefileUsage;
/*     */     public int PeakPagefileUsage;
/*     */     public int WorkingSetSize;
/*     */     public int PeakWorkingSetSize;
/*     */     public WinNT.LARGE_INTEGER UserTime;
/*     */     public WinNT.LARGE_INTEGER KernelTime;
/*     */     
/*     */     public WTS_PROCESS_INFO_EX() {
/* 144 */       super(W32APITypeMapper.DEFAULT);
/*     */     }
/*     */     
/*     */     public WTS_PROCESS_INFO_EX(Pointer p) {
/* 148 */       super(p, 0, W32APITypeMapper.DEFAULT);
/* 149 */       read();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/sun/jna/platform/win32/Wtsapi32.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */