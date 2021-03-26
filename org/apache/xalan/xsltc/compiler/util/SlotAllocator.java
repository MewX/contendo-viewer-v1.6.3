/*    */ package org.apache.xalan.xsltc.compiler.util;
/*    */ 
/*    */ import org.apache.bcel.generic.LocalVariableGen;
/*    */ import org.apache.bcel.generic.Type;
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
/*    */ final class SlotAllocator
/*    */ {
/*    */   private int _firstAvailableSlot;
/* 31 */   private int _size = 8;
/* 32 */   private int _free = 0;
/* 33 */   private int[] _slotsTaken = new int[this._size];
/*    */   
/*    */   public void initialize(LocalVariableGen[] vars) {
/* 36 */     int length = vars.length;
/* 37 */     int slot = 0;
/*    */     
/* 39 */     for (int i = 0; i < length; i++) {
/* 40 */       int size = vars[i].getType().getSize();
/* 41 */       int index = vars[i].getIndex();
/* 42 */       slot = Math.max(slot, index + size);
/*    */     } 
/* 44 */     this._firstAvailableSlot = slot;
/*    */   }
/*    */   
/*    */   public int allocateSlot(Type type) {
/* 48 */     int size = type.getSize();
/* 49 */     int limit = this._free;
/* 50 */     int slot = this._firstAvailableSlot, where = 0;
/*    */     
/* 52 */     if (this._free + size > this._size) {
/* 53 */       int[] array = new int[this._size *= 2];
/* 54 */       for (int i = 0; i < limit; i++)
/* 55 */         array[i] = this._slotsTaken[i]; 
/* 56 */       this._slotsTaken = array;
/*    */     } 
/*    */     
/* 59 */     while (where < limit) {
/* 60 */       if (slot + size <= this._slotsTaken[where]) {
/*    */         
/* 62 */         for (int i = limit - 1; i >= where; i--) {
/* 63 */           this._slotsTaken[i + size] = this._slotsTaken[i];
/*    */         }
/*    */         break;
/*    */       } 
/* 67 */       slot = this._slotsTaken[where++] + 1;
/*    */     } 
/*    */ 
/*    */     
/* 71 */     for (int j = 0; j < size; j++) {
/* 72 */       this._slotsTaken[where + j] = slot + j;
/*    */     }
/* 74 */     this._free += size;
/* 75 */     return slot;
/*    */   }
/*    */   
/*    */   public void releaseSlot(LocalVariableGen lvg) {
/* 79 */     int size = lvg.getType().getSize();
/* 80 */     int slot = lvg.getIndex();
/* 81 */     int limit = this._free;
/*    */     
/* 83 */     for (int i = 0; i < limit; i++) {
/* 84 */       if (this._slotsTaken[i] == slot) {
/* 85 */         int j = i + size;
/* 86 */         while (j < limit) {
/* 87 */           this._slotsTaken[i++] = this._slotsTaken[j++];
/*    */         }
/* 89 */         this._free -= size;
/*    */         return;
/*    */       } 
/*    */     } 
/* 93 */     String state = "Variable slot allocation error(size=" + size + ", slot=" + slot + ", limit=" + limit + ")";
/*    */     
/* 95 */     ErrorMsg err = new ErrorMsg("INTERNAL_ERR", state);
/* 96 */     throw new Error(err.toString());
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/util/SlotAllocator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */