/*     */ package com.sun.jna.platform.win32.COM;
/*     */ 
/*     */ import com.sun.jna.Function;
/*     */ import com.sun.jna.Pointer;
/*     */ import com.sun.jna.platform.win32.Guid;
/*     */ import com.sun.jna.platform.win32.ShTypes;
/*     */ import com.sun.jna.platform.win32.WinDef;
/*     */ import com.sun.jna.platform.win32.WinNT;
/*     */ import com.sun.jna.ptr.IntByReference;
/*     */ import com.sun.jna.ptr.PointerByReference;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface IShellFolder
/*     */ {
/*  46 */   public static final Guid.IID IID_ISHELLFOLDER = new Guid.IID("{000214E6-0000-0000-C000-000000000046}");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   WinNT.HRESULT QueryInterface(Guid.REFIID paramREFIID, PointerByReference paramPointerByReference);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int AddRef();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int Release();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   WinNT.HRESULT ParseDisplayName(WinDef.HWND paramHWND, Pointer paramPointer, String paramString, IntByReference paramIntByReference1, PointerByReference paramPointerByReference, IntByReference paramIntByReference2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   WinNT.HRESULT EnumObjects(WinDef.HWND paramHWND, int paramInt, PointerByReference paramPointerByReference);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   WinNT.HRESULT BindToObject(Pointer paramPointer1, Pointer paramPointer2, Guid.REFIID paramREFIID, PointerByReference paramPointerByReference);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   WinNT.HRESULT BindToStorage(Pointer paramPointer1, Pointer paramPointer2, Guid.REFIID paramREFIID, PointerByReference paramPointerByReference);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   WinNT.HRESULT CompareIDs(WinDef.LPARAM paramLPARAM, Pointer paramPointer1, Pointer paramPointer2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   WinNT.HRESULT CreateViewObject(WinDef.HWND paramHWND, Guid.REFIID paramREFIID, PointerByReference paramPointerByReference);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   WinNT.HRESULT GetAttributesOf(int paramInt, Pointer paramPointer, IntByReference paramIntByReference);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   WinNT.HRESULT GetUIObjectOf(WinDef.HWND paramHWND, int paramInt, Pointer paramPointer, Guid.REFIID paramREFIID, IntByReference paramIntByReference, PointerByReference paramPointerByReference);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   WinNT.HRESULT GetDisplayNameOf(Pointer paramPointer, int paramInt, ShTypes.STRRET paramSTRRET);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   WinNT.HRESULT SetNameOf(WinDef.HWND paramHWND, Pointer paramPointer, String paramString, int paramInt, PointerByReference paramPointerByReference);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Converter
/*     */   {
/*     */     public static IShellFolder PointerToIShellFolder(PointerByReference ptr) {
/* 457 */       final Pointer interfacePointer = ptr.getValue();
/* 458 */       Pointer vTablePointer = interfacePointer.getPointer(0L);
/* 459 */       final Pointer[] vTable = new Pointer[13];
/* 460 */       vTablePointer.read(0L, vTable, 0, 13);
/* 461 */       return new IShellFolder()
/*     */         {
/*     */           public WinNT.HRESULT QueryInterface(Guid.REFIID byValue, PointerByReference pointerByReference)
/*     */           {
/* 465 */             Function f = Function.getFunction(vTable[0], 63);
/* 466 */             return new WinNT.HRESULT(f.invokeInt(new Object[] { this.val$interfacePointer, byValue, pointerByReference }));
/*     */           }
/*     */ 
/*     */           
/*     */           public int AddRef() {
/* 471 */             Function f = Function.getFunction(vTable[1], 63);
/* 472 */             return f.invokeInt(new Object[] { this.val$interfacePointer });
/*     */           }
/*     */           
/*     */           public int Release() {
/* 476 */             Function f = Function.getFunction(vTable[2], 63);
/* 477 */             return f.invokeInt(new Object[] { this.val$interfacePointer });
/*     */           }
/*     */ 
/*     */           
/*     */           public WinNT.HRESULT ParseDisplayName(WinDef.HWND hwnd, Pointer pbc, String pszDisplayName, IntByReference pchEaten, PointerByReference ppidl, IntByReference pdwAttributes) {
/* 482 */             Function f = Function.getFunction(vTable[3], 63);
/* 483 */             return new WinNT.HRESULT(f.invokeInt(new Object[] { this.val$interfacePointer, hwnd, pbc, pszDisplayName, pchEaten, ppidl, pdwAttributes }));
/*     */           }
/*     */ 
/*     */           
/*     */           public WinNT.HRESULT EnumObjects(WinDef.HWND hwnd, int grfFlags, PointerByReference ppenumIDList) {
/* 488 */             Function f = Function.getFunction(vTable[4], 63);
/* 489 */             return new WinNT.HRESULT(f.invokeInt(new Object[] { this.val$interfacePointer, hwnd, Integer.valueOf(grfFlags), ppenumIDList }));
/*     */           }
/*     */           
/*     */           public WinNT.HRESULT BindToObject(Pointer pidl, Pointer pbc, Guid.REFIID riid, PointerByReference ppv) {
/* 493 */             Function f = Function.getFunction(vTable[5], 63);
/* 494 */             return new WinNT.HRESULT(f.invokeInt(new Object[] { this.val$interfacePointer, pidl, pbc, riid, ppv }));
/*     */           }
/*     */ 
/*     */           
/*     */           public WinNT.HRESULT BindToStorage(Pointer pidl, Pointer pbc, Guid.REFIID riid, PointerByReference ppv) {
/* 499 */             Function f = Function.getFunction(vTable[6], 63);
/* 500 */             return new WinNT.HRESULT(f.invokeInt(new Object[] { this.val$interfacePointer, pidl, pbc, riid, ppv }));
/*     */           }
/*     */ 
/*     */           
/*     */           public WinNT.HRESULT CompareIDs(WinDef.LPARAM lParam, Pointer pidl1, Pointer pidl2) {
/* 505 */             Function f = Function.getFunction(vTable[7], 63);
/* 506 */             return new WinNT.HRESULT(f.invokeInt(new Object[] { this.val$interfacePointer, lParam, pidl1, pidl2 }));
/*     */           }
/*     */ 
/*     */           
/*     */           public WinNT.HRESULT CreateViewObject(WinDef.HWND hwndOwner, Guid.REFIID riid, PointerByReference ppv) {
/* 511 */             Function f = Function.getFunction(vTable[8], 63);
/* 512 */             return new WinNT.HRESULT(f.invokeInt(new Object[] { this.val$interfacePointer, hwndOwner, riid, ppv }));
/*     */           }
/*     */ 
/*     */           
/*     */           public WinNT.HRESULT GetAttributesOf(int cidl, Pointer apidl, IntByReference rgfInOut) {
/* 517 */             Function f = Function.getFunction(vTable[9], 63);
/* 518 */             return new WinNT.HRESULT(f.invokeInt(new Object[] { this.val$interfacePointer, Integer.valueOf(cidl), apidl, rgfInOut }));
/*     */           }
/*     */ 
/*     */           
/*     */           public WinNT.HRESULT GetUIObjectOf(WinDef.HWND hwndOwner, int cidl, Pointer apidl, Guid.REFIID riid, IntByReference rgfReserved, PointerByReference ppv) {
/* 523 */             Function f = Function.getFunction(vTable[10], 63);
/* 524 */             return new WinNT.HRESULT(f.invokeInt(new Object[] { this.val$interfacePointer, hwndOwner, Integer.valueOf(cidl), apidl, riid, rgfReserved, ppv }));
/*     */           }
/*     */           
/*     */           public WinNT.HRESULT GetDisplayNameOf(Pointer pidl, int flags, ShTypes.STRRET pName) {
/* 528 */             Function f = Function.getFunction(vTable[11], 63);
/* 529 */             return new WinNT.HRESULT(f.invokeInt(new Object[] { this.val$interfacePointer, pidl, Integer.valueOf(flags), pName }));
/*     */           }
/*     */ 
/*     */           
/*     */           public WinNT.HRESULT SetNameOf(WinDef.HWND hwnd, Pointer pidl, String pszName, int uFlags, PointerByReference ppidlOut) {
/* 534 */             Function f = Function.getFunction(vTable[12], 63);
/* 535 */             return new WinNT.HRESULT(f.invokeInt(new Object[] { this.val$interfacePointer, hwnd, pidl, pszName, Integer.valueOf(uFlags), ppidlOut }));
/*     */           }
/*     */         };
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/sun/jna/platform/win32/COM/IShellFolder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */