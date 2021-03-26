/*     */ package org.apache.bcel.generic;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class BranchHandle
/*     */   extends InstructionHandle
/*     */ {
/*     */   private BranchInstruction bi;
/*     */   
/*     */   private BranchHandle(BranchInstruction i) {
/*  73 */     super(i);
/*  74 */     this.bi = i;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  79 */   private static BranchHandle bh_list = null;
/*     */   
/*     */   static final BranchHandle getBranchHandle(BranchInstruction i) {
/*  82 */     if (bh_list == null) {
/*  83 */       return new BranchHandle(i);
/*     */     }
/*  85 */     BranchHandle bh = bh_list;
/*  86 */     bh_list = (BranchHandle)bh.next;
/*     */     
/*  88 */     bh.setInstruction(i);
/*     */     
/*  90 */     return bh;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addHandle() {
/*  97 */     this.next = bh_list;
/*  98 */     bh_list = this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPosition() {
/* 105 */     return this.bi.position;
/*     */   }
/*     */   void setPosition(int pos) {
/* 108 */     this.i_position = this.bi.position = pos;
/*     */   }
/*     */   
/*     */   protected int updatePosition(int offset, int max_offset) {
/* 112 */     int x = this.bi.updatePosition(offset, max_offset);
/* 113 */     this.i_position = this.bi.position;
/* 114 */     return x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTarget(InstructionHandle ih) {
/* 121 */     this.bi.setTarget(ih);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTarget(InstructionHandle old_ih, InstructionHandle new_ih) {
/* 128 */     this.bi.updateTarget(old_ih, new_ih);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InstructionHandle getTarget() {
/* 135 */     return this.bi.getTarget();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInstruction(Instruction i) {
/* 142 */     super.setInstruction(i);
/*     */     
/* 144 */     if (!(i instanceof BranchInstruction)) {
/* 145 */       throw new ClassGenException("Assigning " + i + " to branch handle which is not a branch instruction");
/*     */     }
/*     */     
/* 148 */     this.bi = (BranchInstruction)i;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/BranchHandle.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */