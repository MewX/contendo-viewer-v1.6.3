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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class SWITCH
/*     */   implements CompoundInstruction
/*     */ {
/*     */   private int[] match;
/*     */   private InstructionHandle[] targets;
/*     */   private Select instruction;
/*     */   private int match_length;
/*     */   
/*     */   public SWITCH(int[] match, InstructionHandle[] targets, InstructionHandle target, int max_gap) {
/*  88 */     this.match = (int[])match.clone();
/*  89 */     this.targets = (InstructionHandle[])targets.clone();
/*     */     
/*  91 */     if ((this.match_length = match.length) < 2) {
/*  92 */       this.instruction = new TABLESWITCH(match, targets, target);
/*     */     } else {
/*  94 */       sort(0, this.match_length - 1);
/*     */       
/*  96 */       if (matchIsOrdered(max_gap)) {
/*  97 */         fillup(max_gap, target);
/*     */         
/*  99 */         this.instruction = new TABLESWITCH(this.match, this.targets, target);
/*     */       } else {
/*     */         
/* 102 */         this.instruction = new LOOKUPSWITCH(this.match, this.targets, target);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public SWITCH(int[] match, InstructionHandle[] targets, InstructionHandle target) {
/* 108 */     this(match, targets, target, 1);
/*     */   }
/*     */   
/*     */   private final void fillup(int max_gap, InstructionHandle target) {
/* 112 */     int max_size = this.match_length + this.match_length * max_gap;
/* 113 */     int[] m_vec = new int[max_size];
/* 114 */     InstructionHandle[] t_vec = new InstructionHandle[max_size];
/* 115 */     int count = 1;
/*     */     
/* 117 */     m_vec[0] = this.match[0];
/* 118 */     t_vec[0] = this.targets[0];
/*     */     
/* 120 */     for (int i = 1; i < this.match_length; i++) {
/* 121 */       int prev = this.match[i - 1];
/* 122 */       int gap = this.match[i] - prev;
/*     */       
/* 124 */       for (int j = 1; j < gap; j++) {
/* 125 */         m_vec[count] = prev + j;
/* 126 */         t_vec[count] = target;
/* 127 */         count++;
/*     */       } 
/*     */       
/* 130 */       m_vec[count] = this.match[i];
/* 131 */       t_vec[count] = this.targets[i];
/* 132 */       count++;
/*     */     } 
/*     */     
/* 135 */     this.match = new int[count];
/* 136 */     this.targets = new InstructionHandle[count];
/*     */     
/* 138 */     System.arraycopy(m_vec, 0, this.match, 0, count);
/* 139 */     System.arraycopy(t_vec, 0, this.targets, 0, count);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final void sort(int l, int r) {
/* 146 */     int i = l, j = r;
/* 147 */     int m = this.match[(l + r) / 2];
/*     */     
/*     */     while (true) {
/*     */       while (true) {
/* 151 */         if (this.match[i] >= m)
/* 152 */         { for (; m < this.match[j]; j--);
/*     */           
/* 154 */           if (i <= j) {
/* 155 */             int h = this.match[i]; this.match[i] = this.match[j]; this.match[j] = h;
/* 156 */             InstructionHandle h2 = this.targets[i]; this.targets[i] = this.targets[j]; this.targets[j] = h2;
/* 157 */             i++; j--;
/*     */           } 
/* 159 */           if (i > j)
/*     */             break;  continue; }  i++;
/* 161 */       }  if (l < j) sort(l, j); 
/* 162 */       if (i < r) sort(i, r); 
/*     */       return;
/*     */     } 
/*     */     i++;
/*     */     continue;
/*     */   }
/*     */   private final boolean matchIsOrdered(int max_gap) {
/* 169 */     for (int i = 1; i < this.match_length; i++) {
/* 170 */       if (this.match[i] - this.match[i - 1] > max_gap)
/* 171 */         return false; 
/*     */     } 
/* 173 */     return true;
/*     */   }
/*     */   
/*     */   public final InstructionList getInstructionList() {
/* 177 */     return new InstructionList(this.instruction);
/*     */   }
/*     */   
/*     */   public final Instruction getInstruction() {
/* 181 */     return this.instruction;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/SWITCH.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */