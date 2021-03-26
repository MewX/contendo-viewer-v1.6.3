/*      */ package org.apache.bcel.generic;
/*      */ 
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.DataOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.Serializable;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import org.apache.bcel.Constants;
/*      */ import org.apache.bcel.classfile.Constant;
/*      */ import org.apache.bcel.util.ByteSequence;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class InstructionList
/*      */   implements Serializable
/*      */ {
/*   85 */   private InstructionHandle start = null; private InstructionHandle end = null;
/*   86 */   private int length = 0;
/*      */ 
/*      */ 
/*      */   
/*      */   private int[] byte_positions;
/*      */ 
/*      */ 
/*      */   
/*      */   private ArrayList observers;
/*      */ 
/*      */ 
/*      */   
/*      */   public InstructionList(Instruction i) {
/*   99 */     append(i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public InstructionList(BranchInstruction i) {
/*  107 */     append(i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public InstructionList(CompoundInstruction c) {
/*  117 */     append(c.getInstructionList());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEmpty() {
/*  123 */     return (this.start == null);
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
/*      */   public static InstructionHandle findHandle(InstructionHandle[] ihs, int[] pos, int count, int target) {
/*  138 */     int l = 0, r = count - 1;
/*      */ 
/*      */ 
/*      */     
/*      */     do {
/*  143 */       int i = (l + r) / 2;
/*  144 */       int j = pos[i];
/*      */       
/*  146 */       if (j == target)
/*  147 */         return ihs[i]; 
/*  148 */       if (target < j)
/*  149 */       { r = i - 1; }
/*      */       else
/*  151 */       { l = i + 1; } 
/*  152 */     } while (l <= r);
/*      */     
/*  154 */     return null;
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
/*      */   public InstructionHandle findHandle(int pos) {
/*  166 */     InstructionHandle[] ihs = getInstructionHandles();
/*  167 */     return findHandle(ihs, this.byte_positions, this.length, pos);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public InstructionList(byte[] code) {
/*  176 */     ByteSequence bytes = new ByteSequence(code);
/*  177 */     InstructionHandle[] ihs = new InstructionHandle[code.length];
/*  178 */     int[] pos = new int[code.length];
/*  179 */     int count = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  185 */     try { while (bytes.available() > 0) {
/*      */         InstructionHandle instructionHandle;
/*  187 */         int off = bytes.getIndex();
/*  188 */         pos[count] = off;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  193 */         Instruction instruction = Instruction.readInstruction(bytes);
/*      */         
/*  195 */         if (instruction instanceof BranchInstruction) {
/*  196 */           instructionHandle = append((BranchInstruction)instruction);
/*      */         } else {
/*  198 */           instructionHandle = append(instruction);
/*      */         } 
/*  200 */         instructionHandle.setPosition(off);
/*  201 */         ihs[count] = instructionHandle;
/*      */         
/*  203 */         count++;
/*      */       }  }
/*  205 */     catch (IOException e) { throw new ClassGenException(e.toString()); }
/*      */     
/*  207 */     this.byte_positions = new int[count];
/*  208 */     System.arraycopy(pos, 0, this.byte_positions, 0, count);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  213 */     for (int i = 0; i < count; i++) {
/*  214 */       if (ihs[i] instanceof BranchHandle) {
/*  215 */         BranchInstruction bi = (BranchInstruction)(ihs[i]).instruction;
/*  216 */         int target = bi.position + bi.getIndex();
/*      */ 
/*      */         
/*  219 */         InstructionHandle ih = findHandle(ihs, pos, count, target);
/*      */         
/*  221 */         if (ih == null) {
/*  222 */           throw new ClassGenException("Couldn't find target for branch: " + bi);
/*      */         }
/*  224 */         bi.setTarget(ih);
/*      */ 
/*      */         
/*  227 */         if (bi instanceof Select) {
/*  228 */           Select s = (Select)bi;
/*  229 */           int[] indices = s.getIndices();
/*      */           
/*  231 */           for (int j = 0; j < indices.length; j++) {
/*  232 */             target = bi.position + indices[j];
/*  233 */             ih = findHandle(ihs, pos, count, target);
/*      */             
/*  235 */             if (ih == null) {
/*  236 */               throw new ClassGenException("Couldn't find target for switch: " + bi);
/*      */             }
/*  238 */             s.setTarget(j, ih);
/*      */           } 
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
/*      */   public InstructionHandle append(InstructionHandle ih, InstructionList il) {
/*  254 */     if (il == null) {
/*  255 */       throw new ClassGenException("Appending null InstructionList");
/*      */     }
/*  257 */     if (il.isEmpty()) {
/*  258 */       return ih;
/*      */     }
/*  260 */     InstructionHandle next = ih.next, ret = il.start;
/*      */     
/*  262 */     ih.next = il.start;
/*  263 */     il.start.prev = ih;
/*      */     
/*  265 */     il.end.next = next;
/*      */     
/*  267 */     if (next != null) {
/*  268 */       next.prev = il.end;
/*      */     } else {
/*  270 */       this.end = il.end;
/*      */     } 
/*  272 */     this.length += il.length;
/*      */     
/*  274 */     il.clear();
/*      */     
/*  276 */     return ret;
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
/*      */   public InstructionHandle append(Instruction i, InstructionList il) {
/*      */     InstructionHandle ih;
/*  290 */     if ((ih = findInstruction2(i)) == null) {
/*  291 */       throw new ClassGenException("Instruction " + i + " is not contained in this list.");
/*      */     }
/*      */     
/*  294 */     return append(ih, il);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public InstructionHandle append(InstructionList il) {
/*  305 */     if (il == null) {
/*  306 */       throw new ClassGenException("Appending null InstructionList");
/*      */     }
/*  308 */     if (il.isEmpty()) {
/*  309 */       return null;
/*      */     }
/*  311 */     if (isEmpty()) {
/*  312 */       this.start = il.start;
/*  313 */       this.end = il.end;
/*  314 */       this.length = il.length;
/*      */       
/*  316 */       il.clear();
/*      */       
/*  318 */       return this.start;
/*      */     } 
/*  320 */     return append(this.end, il);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void append(InstructionHandle ih) {
/*  329 */     if (isEmpty()) {
/*  330 */       this.start = this.end = ih;
/*  331 */       ih.next = ih.prev = null;
/*      */     } else {
/*      */       
/*  334 */       this.end.next = ih;
/*  335 */       ih.prev = this.end;
/*  336 */       ih.next = null;
/*  337 */       this.end = ih;
/*      */     } 
/*      */     
/*  340 */     this.length++;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public InstructionHandle append(Instruction i) {
/*  350 */     InstructionHandle ih = InstructionHandle.getInstructionHandle(i);
/*  351 */     append(ih);
/*      */     
/*  353 */     return ih;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BranchHandle append(BranchInstruction i) {
/*  363 */     BranchHandle ih = BranchHandle.getBranchHandle(i);
/*  364 */     append(ih);
/*      */     
/*  366 */     return ih;
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
/*      */   public InstructionHandle append(Instruction i, Instruction j) {
/*  378 */     return append(i, new InstructionList(j));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public InstructionHandle append(Instruction i, CompoundInstruction c) {
/*  389 */     return append(i, c.getInstructionList());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public InstructionHandle append(CompoundInstruction c) {
/*  399 */     return append(c.getInstructionList());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public InstructionHandle append(InstructionHandle ih, CompoundInstruction c) {
/*  410 */     return append(ih, c.getInstructionList());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public InstructionHandle append(InstructionHandle ih, Instruction i) {
/*  421 */     return append(ih, new InstructionList(i));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BranchHandle append(InstructionHandle ih, BranchInstruction i) {
/*  432 */     BranchHandle bh = BranchHandle.getBranchHandle(i);
/*  433 */     InstructionList il = new InstructionList();
/*  434 */     il.append(bh);
/*      */     
/*  436 */     append(ih, il);
/*      */     
/*  438 */     return bh;
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
/*      */   public InstructionHandle insert(InstructionHandle ih, InstructionList il) {
/*  450 */     if (il == null) {
/*  451 */       throw new ClassGenException("Inserting null InstructionList");
/*      */     }
/*  453 */     if (il.isEmpty()) {
/*  454 */       return ih;
/*      */     }
/*  456 */     InstructionHandle prev = ih.prev, ret = il.start;
/*      */     
/*  458 */     ih.prev = il.end;
/*  459 */     il.end.next = ih;
/*      */     
/*  461 */     il.start.prev = prev;
/*      */     
/*  463 */     if (prev != null) {
/*  464 */       prev.next = il.start;
/*      */     } else {
/*  466 */       this.start = il.start;
/*      */     } 
/*  468 */     this.length += il.length;
/*      */     
/*  470 */     il.clear();
/*      */     
/*  472 */     return ret;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public InstructionHandle insert(InstructionList il) {
/*  482 */     if (isEmpty()) {
/*  483 */       append(il);
/*  484 */       return this.start;
/*      */     } 
/*      */     
/*  487 */     return insert(this.start, il);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void insert(InstructionHandle ih) {
/*  496 */     if (isEmpty()) {
/*  497 */       this.start = this.end = ih;
/*  498 */       ih.next = ih.prev = null;
/*      */     } else {
/*  500 */       this.start.prev = ih;
/*  501 */       ih.next = this.start;
/*  502 */       ih.prev = null;
/*  503 */       this.start = ih;
/*      */     } 
/*      */     
/*  506 */     this.length++;
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
/*      */   public InstructionHandle insert(Instruction i, InstructionList il) {
/*      */     InstructionHandle ih;
/*  521 */     if ((ih = findInstruction1(i)) == null) {
/*  522 */       throw new ClassGenException("Instruction " + i + " is not contained in this list.");
/*      */     }
/*      */     
/*  525 */     return insert(ih, il);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public InstructionHandle insert(Instruction i) {
/*  535 */     InstructionHandle ih = InstructionHandle.getInstructionHandle(i);
/*  536 */     insert(ih);
/*      */     
/*  538 */     return ih;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BranchHandle insert(BranchInstruction i) {
/*  548 */     BranchHandle ih = BranchHandle.getBranchHandle(i);
/*  549 */     insert(ih);
/*  550 */     return ih;
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
/*      */   public InstructionHandle insert(Instruction i, Instruction j) {
/*  562 */     return insert(i, new InstructionList(j));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public InstructionHandle insert(Instruction i, CompoundInstruction c) {
/*  573 */     return insert(i, c.getInstructionList());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public InstructionHandle insert(CompoundInstruction c) {
/*  583 */     return insert(c.getInstructionList());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public InstructionHandle insert(InstructionHandle ih, Instruction i) {
/*  594 */     return insert(ih, new InstructionList(i));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public InstructionHandle insert(InstructionHandle ih, CompoundInstruction c) {
/*  605 */     return insert(ih, c.getInstructionList());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BranchHandle insert(InstructionHandle ih, BranchInstruction i) {
/*  616 */     BranchHandle bh = BranchHandle.getBranchHandle(i);
/*  617 */     InstructionList il = new InstructionList();
/*  618 */     il.append(bh);
/*      */     
/*  620 */     insert(ih, il);
/*      */     
/*  622 */     return bh;
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
/*      */   public void move(InstructionHandle start, InstructionHandle end, InstructionHandle target) {
/*  639 */     if (start == null || end == null) {
/*  640 */       throw new ClassGenException("Invalid null handle: From " + start + " to " + end);
/*      */     }
/*  642 */     if (target == start || target == end) {
/*  643 */       throw new ClassGenException("Invalid range: From " + start + " to " + end + " contains target " + target);
/*      */     }
/*      */     
/*  646 */     for (InstructionHandle ih = start; ih != end.next; ih = ih.next) {
/*  647 */       if (ih == null)
/*  648 */         throw new ClassGenException("Invalid range: From " + start + " to " + end); 
/*  649 */       if (ih == target) {
/*  650 */         throw new ClassGenException("Invalid range: From " + start + " to " + end + " contains target " + target);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  656 */     InstructionHandle prev = start.prev, next = end.next;
/*      */     
/*  658 */     if (prev != null) {
/*  659 */       prev.next = next;
/*      */     } else {
/*  661 */       this.start = next;
/*      */     } 
/*  663 */     if (next != null) {
/*  664 */       next.prev = prev;
/*      */     } else {
/*  666 */       this.end = prev;
/*      */     } 
/*  668 */     start.prev = end.next = null;
/*      */ 
/*      */ 
/*      */     
/*  672 */     if (target == null) {
/*  673 */       end.next = this.start;
/*  674 */       this.start = start;
/*      */     } else {
/*  676 */       next = target.next;
/*      */       
/*  678 */       target.next = start;
/*  679 */       start.prev = target;
/*  680 */       end.next = next;
/*      */       
/*  682 */       if (next != null) {
/*  683 */         next.prev = end;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void move(InstructionHandle ih, InstructionHandle target) {
/*  694 */     move(ih, ih, target);
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
/*      */   private void remove(InstructionHandle prev, InstructionHandle next) throws TargetLostException {
/*      */     InstructionHandle instructionHandle1, instructionHandle2;
/*  710 */     if (prev == null && next == null) {
/*  711 */       instructionHandle1 = instructionHandle2 = this.start;
/*  712 */       this.start = this.end = null;
/*      */     } else {
/*  714 */       if (prev == null) {
/*  715 */         instructionHandle1 = this.start;
/*  716 */         this.start = next;
/*      */       } else {
/*  718 */         instructionHandle1 = prev.next;
/*  719 */         prev.next = next;
/*      */       } 
/*      */       
/*  722 */       if (next == null) {
/*  723 */         instructionHandle2 = this.end;
/*  724 */         this.end = prev;
/*      */       } else {
/*  726 */         instructionHandle2 = next.prev;
/*  727 */         next.prev = prev;
/*      */       } 
/*      */     } 
/*      */     
/*  731 */     instructionHandle1.prev = null;
/*  732 */     instructionHandle2.next = null;
/*      */     
/*  734 */     ArrayList target_vec = new ArrayList();
/*      */     
/*  736 */     for (InstructionHandle ih = instructionHandle1; ih != null; ih = ih.next) {
/*  737 */       ih.getInstruction().dispose();
/*      */     }
/*  739 */     StringBuffer buf = new StringBuffer("{ ");
/*  740 */     for (InstructionHandle instructionHandle3 = instructionHandle1; instructionHandle3 != null; instructionHandle3 = next) {
/*  741 */       next = instructionHandle3.next;
/*  742 */       this.length--;
/*      */       
/*  744 */       if (instructionHandle3.hasTargeters()) {
/*  745 */         target_vec.add(instructionHandle3);
/*  746 */         buf.append(instructionHandle3.toString(true) + " ");
/*  747 */         instructionHandle3.next = instructionHandle3.prev = null;
/*      */       } else {
/*  749 */         instructionHandle3.dispose();
/*      */       } 
/*      */     } 
/*  752 */     buf.append("}");
/*      */     
/*  754 */     if (!target_vec.isEmpty()) {
/*  755 */       InstructionHandle[] targeted = new InstructionHandle[target_vec.size()];
/*  756 */       target_vec.toArray(targeted);
/*  757 */       throw new TargetLostException(targeted, buf.toString());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void delete(InstructionHandle ih) throws TargetLostException {
/*  768 */     remove(ih.prev, ih.next);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void delete(Instruction i) throws TargetLostException {
/*      */     InstructionHandle ih;
/*  780 */     if ((ih = findInstruction1(i)) == null) {
/*  781 */       throw new ClassGenException("Instruction " + i + " is not contained in this list.");
/*      */     }
/*  783 */     delete(ih);
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
/*      */   public void delete(InstructionHandle from, InstructionHandle to) throws TargetLostException {
/*  797 */     remove(from.prev, to.next);
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
/*      */   public void delete(Instruction from, Instruction to) throws TargetLostException {
/*      */     InstructionHandle from_ih;
/*  811 */     if ((from_ih = findInstruction1(from)) == null) {
/*  812 */       throw new ClassGenException("Instruction " + from + " is not contained in this list.");
/*      */     }
/*      */     InstructionHandle to_ih;
/*  815 */     if ((to_ih = findInstruction2(to)) == null) {
/*  816 */       throw new ClassGenException("Instruction " + to + " is not contained in this list.");
/*      */     }
/*  818 */     delete(from_ih, to_ih);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private InstructionHandle findInstruction1(Instruction i) {
/*  828 */     for (InstructionHandle ih = this.start; ih != null; ih = ih.next) {
/*  829 */       if (ih.instruction == i)
/*  830 */         return ih; 
/*      */     } 
/*  832 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private InstructionHandle findInstruction2(Instruction i) {
/*  842 */     for (InstructionHandle ih = this.end; ih != null; ih = ih.prev) {
/*  843 */       if (ih.instruction == i)
/*  844 */         return ih; 
/*      */     } 
/*  846 */     return null;
/*      */   }
/*      */   
/*      */   public boolean contains(InstructionHandle i) {
/*  850 */     if (i == null) {
/*  851 */       return false;
/*      */     }
/*  853 */     for (InstructionHandle ih = this.start; ih != null; ih = ih.next) {
/*  854 */       if (ih == i)
/*  855 */         return true; 
/*      */     } 
/*  857 */     return false;
/*      */   }
/*      */   
/*      */   public boolean contains(Instruction i) {
/*  861 */     return (findInstruction1(i) != null);
/*      */   }
/*      */   
/*      */   public void setPositions() {
/*  865 */     setPositions(false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPositions(boolean check) {
/*  876 */     int max_additional_bytes = 0, additional_bytes = 0;
/*  877 */     int index = 0, count = 0;
/*  878 */     int[] pos = new int[this.length];
/*      */ 
/*      */ 
/*      */     
/*  882 */     if (check) {
/*  883 */       for (InstructionHandle instructionHandle = this.start; instructionHandle != null; instructionHandle = instructionHandle.next) {
/*  884 */         Instruction i = instructionHandle.instruction;
/*      */         
/*  886 */         if (i instanceof BranchInstruction) {
/*  887 */           Instruction inst = (((BranchInstruction)i).getTarget()).instruction;
/*  888 */           if (!contains(inst)) {
/*  889 */             throw new ClassGenException("Branch target of " + Constants.OPCODE_NAMES[i.opcode] + ":" + inst + " not in instruction list");
/*      */           }
/*      */ 
/*      */           
/*  893 */           if (i instanceof Select) {
/*  894 */             InstructionHandle[] targets = ((Select)i).getTargets();
/*      */             
/*  896 */             for (int j = 0; j < targets.length; j++) {
/*  897 */               inst = (targets[j]).instruction;
/*  898 */               if (!contains(inst)) {
/*  899 */                 throw new ClassGenException("Branch target of " + Constants.OPCODE_NAMES[i.opcode] + ":" + inst + " not in instruction list");
/*      */               }
/*      */             } 
/*      */           } 
/*      */ 
/*      */           
/*  905 */           if (!(instructionHandle instanceof BranchHandle)) {
/*  906 */             throw new ClassGenException("Branch instruction " + Constants.OPCODE_NAMES[i.opcode] + ":" + inst + " not contained in BranchHandle.");
/*      */           }
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  917 */     for (InstructionHandle ih = this.start; ih != null; ih = ih.next) {
/*  918 */       Instruction i = ih.instruction;
/*      */       
/*  920 */       ih.setPosition(index);
/*  921 */       pos[count++] = index;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  928 */       switch (i.getOpcode()) { case 167:
/*      */         case 168:
/*  930 */           max_additional_bytes += 2;
/*      */           break;
/*      */         case 170:
/*      */         case 171:
/*  934 */           max_additional_bytes += 3;
/*      */           break; }
/*      */ 
/*      */       
/*  938 */       index += i.getLength();
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  945 */     for (InstructionHandle instructionHandle1 = this.start; instructionHandle1 != null; instructionHandle1 = instructionHandle1.next) {
/*  946 */       additional_bytes += instructionHandle1.updatePosition(additional_bytes, max_additional_bytes);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  951 */     index = count = 0;
/*  952 */     for (InstructionHandle instructionHandle2 = this.start; instructionHandle2 != null; instructionHandle2 = instructionHandle2.next) {
/*  953 */       Instruction i = instructionHandle2.instruction;
/*      */       
/*  955 */       instructionHandle2.setPosition(index);
/*  956 */       pos[count++] = index;
/*  957 */       index += i.getLength();
/*      */     } 
/*      */     
/*  960 */     this.byte_positions = new int[count];
/*  961 */     System.arraycopy(pos, 0, this.byte_positions, 0, count);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getByteCode() {
/*  972 */     setPositions();
/*      */     
/*  974 */     ByteArrayOutputStream b = new ByteArrayOutputStream();
/*  975 */     DataOutputStream out = new DataOutputStream(b);
/*      */     
/*      */     try {
/*  978 */       for (InstructionHandle ih = this.start; ih != null; ih = ih.next) {
/*  979 */         Instruction i = ih.instruction;
/*  980 */         i.dump(out);
/*      */       } 
/*      */     } catch (IOException e) {
/*  983 */       System.err.println(e);
/*  984 */       return null;
/*      */     } 
/*      */     
/*  987 */     return b.toByteArray();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Instruction[] getInstructions() {
/*  994 */     ByteSequence bytes = new ByteSequence(getByteCode());
/*  995 */     ArrayList instructions = new ArrayList();
/*      */     
/*      */     try {
/*  998 */       while (bytes.available() > 0)
/*  999 */         instructions.add(Instruction.readInstruction(bytes)); 
/*      */     } catch (IOException e) {
/* 1001 */       throw new ClassGenException(e.toString());
/*      */     } 
/* 1003 */     Instruction[] result = new Instruction[instructions.size()];
/* 1004 */     instructions.toArray(result);
/* 1005 */     return result;
/*      */   }
/*      */   
/*      */   public String toString() {
/* 1009 */     return toString(true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString(boolean verbose) {
/* 1017 */     StringBuffer buf = new StringBuffer();
/*      */     
/* 1019 */     for (InstructionHandle ih = this.start; ih != null; ih = ih.next) {
/* 1020 */       buf.append(ih.toString(verbose) + "\n");
/*      */     }
/*      */     
/* 1023 */     return buf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Iterator iterator() {
/* 1030 */     return new Iterator(this) { private InstructionHandle ih;
/*      */         private final InstructionList this$0;
/*      */         
/*      */         public Object next() {
/* 1034 */           InstructionHandle i = this.ih;
/* 1035 */           this.ih = this.ih.next;
/* 1036 */           return i;
/*      */         }
/*      */         
/*      */         public void remove() {
/* 1040 */           throw new UnsupportedOperationException();
/*      */         }
/*      */         public boolean hasNext() {
/* 1043 */           return (this.ih != null);
/*      */         } }
/*      */       ;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public InstructionHandle[] getInstructionHandles() {
/* 1051 */     InstructionHandle[] ihs = new InstructionHandle[this.length];
/* 1052 */     InstructionHandle ih = this.start;
/*      */     
/* 1054 */     for (int i = 0; i < this.length; i++) {
/* 1055 */       ihs[i] = ih;
/* 1056 */       ih = ih.next;
/*      */     } 
/*      */     
/* 1059 */     return ihs;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int[] getInstructionPositions() {
/* 1069 */     return this.byte_positions;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public InstructionList copy() {
/* 1075 */     HashMap map = new HashMap();
/* 1076 */     InstructionList il = new InstructionList();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1082 */     for (InstructionHandle ih = this.start; ih != null; ih = ih.next) {
/* 1083 */       Instruction i = ih.instruction;
/* 1084 */       Instruction c = i.copy();
/*      */       
/* 1086 */       if (c instanceof BranchInstruction) {
/* 1087 */         map.put(ih, il.append((BranchInstruction)c));
/*      */       } else {
/* 1089 */         map.put(ih, il.append(c));
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1094 */     InstructionHandle instructionHandle1 = this.start;
/* 1095 */     InstructionHandle ch = il.start;
/*      */     
/* 1097 */     while (instructionHandle1 != null) {
/* 1098 */       Instruction i = instructionHandle1.instruction;
/* 1099 */       Instruction c = ch.instruction;
/*      */       
/* 1101 */       if (i instanceof BranchInstruction) {
/* 1102 */         BranchInstruction bi = (BranchInstruction)i;
/* 1103 */         BranchInstruction bc = (BranchInstruction)c;
/* 1104 */         InstructionHandle itarget = bi.getTarget();
/*      */ 
/*      */         
/* 1107 */         bc.setTarget((InstructionHandle)map.get(itarget));
/*      */         
/* 1109 */         if (bi instanceof Select) {
/* 1110 */           InstructionHandle[] itargets = ((Select)bi).getTargets();
/* 1111 */           InstructionHandle[] ctargets = ((Select)bc).getTargets();
/*      */           
/* 1113 */           for (int j = 0; j < itargets.length; j++) {
/* 1114 */             ctargets[j] = (InstructionHandle)map.get(itargets[j]);
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/* 1119 */       instructionHandle1 = instructionHandle1.next;
/* 1120 */       ch = ch.next;
/*      */     } 
/*      */     
/* 1123 */     return il;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void replaceConstantPool(ConstantPoolGen old_cp, ConstantPoolGen new_cp) {
/* 1130 */     for (InstructionHandle ih = this.start; ih != null; ih = ih.next) {
/* 1131 */       Instruction i = ih.instruction;
/*      */       
/* 1133 */       if (i instanceof CPInstruction) {
/* 1134 */         CPInstruction ci = (CPInstruction)i;
/* 1135 */         Constant c = old_cp.getConstant(ci.getIndex());
/* 1136 */         ci.setIndex(new_cp.addConstant(c, old_cp));
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void clear() {
/* 1142 */     this.start = this.end = null;
/* 1143 */     this.length = 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void dispose() {
/* 1154 */     for (InstructionHandle ih = this.end; ih != null; ih = ih.prev)
/*      */     {
/*      */ 
/*      */       
/* 1158 */       ih.dispose();
/*      */     }
/* 1160 */     clear();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public InstructionHandle getStart() {
/* 1166 */     return this.start;
/*      */   }
/*      */ 
/*      */   
/*      */   public InstructionHandle getEnd() {
/* 1171 */     return this.end;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getLength() {
/* 1176 */     return this.length;
/*      */   }
/*      */ 
/*      */   
/*      */   public int size() {
/* 1181 */     return this.length;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void redirectBranches(InstructionHandle old_target, InstructionHandle new_target) {
/* 1192 */     for (InstructionHandle ih = this.start; ih != null; ih = ih.next) {
/* 1193 */       Instruction i = ih.getInstruction();
/*      */       
/* 1195 */       if (i instanceof BranchInstruction) {
/* 1196 */         BranchInstruction b = (BranchInstruction)i;
/* 1197 */         InstructionHandle target = b.getTarget();
/*      */         
/* 1199 */         if (target == old_target) {
/* 1200 */           b.setTarget(new_target);
/*      */         }
/* 1202 */         if (b instanceof Select) {
/* 1203 */           InstructionHandle[] targets = ((Select)b).getTargets();
/*      */           
/* 1205 */           for (int j = 0; j < targets.length; j++) {
/* 1206 */             if (targets[j] == old_target) {
/* 1207 */               ((Select)b).setTarget(j, new_target);
/*      */             }
/*      */           } 
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
/*      */   public void redirectLocalVariables(LocalVariableGen[] lg, InstructionHandle old_target, InstructionHandle new_target) {
/* 1224 */     for (int i = 0; i < lg.length; i++) {
/* 1225 */       InstructionHandle start = lg[i].getStart();
/* 1226 */       InstructionHandle end = lg[i].getEnd();
/*      */       
/* 1228 */       if (start == old_target) {
/* 1229 */         lg[i].setStart(new_target);
/*      */       }
/* 1231 */       if (end == old_target) {
/* 1232 */         lg[i].setEnd(new_target);
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
/*      */   public void redirectExceptionHandlers(CodeExceptionGen[] exceptions, InstructionHandle old_target, InstructionHandle new_target) {
/* 1247 */     for (int i = 0; i < exceptions.length; i++) {
/* 1248 */       if (exceptions[i].getStartPC() == old_target) {
/* 1249 */         exceptions[i].setStartPC(new_target);
/*      */       }
/* 1251 */       if (exceptions[i].getEndPC() == old_target) {
/* 1252 */         exceptions[i].setEndPC(new_target);
/*      */       }
/* 1254 */       if (exceptions[i].getHandlerPC() == old_target) {
/* 1255 */         exceptions[i].setHandlerPC(new_target);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addObserver(InstructionListObserver o) {
/* 1264 */     if (this.observers == null) {
/* 1265 */       this.observers = new ArrayList();
/*      */     }
/* 1267 */     this.observers.add(o);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeObserver(InstructionListObserver o) {
/* 1273 */     if (this.observers != null) {
/* 1274 */       this.observers.remove(o);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void update() {
/* 1282 */     if (this.observers != null)
/* 1283 */       for (Iterator e = this.observers.iterator(); e.hasNext();)
/* 1284 */         ((InstructionListObserver)e.next()).notify(this);  
/*      */   }
/*      */   
/*      */   public InstructionList() {}
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/InstructionList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */