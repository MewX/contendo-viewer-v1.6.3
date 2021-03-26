/*    */ package com.sun.jna.ptr;
/*    */ 
/*    */ import com.sun.jna.Native;
/*    */ import com.sun.jna.Pointer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PointerByReference
/*    */   extends ByReference
/*    */ {
/*    */   public PointerByReference() {
/* 36 */     this(null);
/*    */   }
/*    */   
/*    */   public PointerByReference(Pointer value) {
/* 40 */     super(Native.POINTER_SIZE);
/* 41 */     setValue(value);
/*    */   }
/*    */   
/*    */   public void setValue(Pointer value) {
/* 45 */     getPointer().setPointer(0L, value);
/*    */   }
/*    */   
/*    */   public Pointer getValue() {
/* 49 */     return getPointer().getPointer(0L);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/sun/jna/ptr/PointerByReference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */