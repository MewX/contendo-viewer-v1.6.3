/*     */ package org.apache.bcel.verifier.structurals;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashSet;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Iterator;
/*     */ import org.apache.bcel.generic.ASTORE;
/*     */ import org.apache.bcel.generic.BranchInstruction;
/*     */ import org.apache.bcel.generic.CodeExceptionGen;
/*     */ import org.apache.bcel.generic.GotoInstruction;
/*     */ import org.apache.bcel.generic.IndexedInstruction;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionHandle;
/*     */ import org.apache.bcel.generic.JsrInstruction;
/*     */ import org.apache.bcel.generic.LocalVariableInstruction;
/*     */ import org.apache.bcel.generic.MethodGen;
/*     */ import org.apache.bcel.generic.RET;
/*     */ import org.apache.bcel.generic.Select;
/*     */ import org.apache.bcel.verifier.exc.AssertionViolatedException;
/*     */ import org.apache.bcel.verifier.exc.StructuralCodeConstraintException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Subroutines
/*     */ {
/*     */   private class SubroutineImpl
/*     */     implements Subroutine
/*     */   {
/*     */     private final int UNSET = -1;
/*     */     private int localVariable;
/*     */     private HashSet instructions;
/*     */     private HashSet theJSRs;
/*     */     private InstructionHandle theRET;
/*     */     private final Subroutines this$0;
/*     */     
/*     */     public boolean contains(InstructionHandle inst) {
/* 117 */       return this.instructions.contains(inst);
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
/*     */     public String toString() {
/* 139 */       String ret = "Subroutine: Local variable is '" + this.localVariable + "', JSRs are '" + this.theJSRs + "', RET is '" + this.theRET + "', Instructions: '" + this.instructions.toString() + "'.";
/*     */       
/* 141 */       ret = ret + " Accessed local variable slots: '";
/* 142 */       int[] alv = getAccessedLocalsIndices();
/* 143 */       for (int i = 0; i < alv.length; i++) {
/* 144 */         ret = ret + alv[i] + " ";
/*     */       }
/* 146 */       ret = ret + "'.";
/*     */       
/* 148 */       ret = ret + " Recursively (via subsub...routines) accessed local variable slots: '";
/* 149 */       alv = getRecursivelyAccessedLocalsIndices();
/* 150 */       for (int j = 0; j < alv.length; j++) {
/* 151 */         ret = ret + alv[j] + " ";
/*     */       }
/* 153 */       ret = ret + "'.";
/*     */       
/* 155 */       return ret;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void setLeavingRET() {
/* 163 */       if (this.localVariable == -1) {
/* 164 */         throw new AssertionViolatedException("setLeavingRET() called for top-level 'subroutine' or forgot to set local variable first.");
/*     */       }
/* 166 */       Iterator iter = this.instructions.iterator();
/* 167 */       InstructionHandle ret = null;
/* 168 */       while (iter.hasNext()) {
/* 169 */         InstructionHandle actual = iter.next();
/* 170 */         if (actual.getInstruction() instanceof RET) {
/* 171 */           if (ret != null) {
/* 172 */             throw new StructuralCodeConstraintException("Subroutine with more then one RET detected: '" + ret + "' and '" + actual + "'.");
/*     */           }
/*     */           
/* 175 */           ret = actual;
/*     */         } 
/*     */       } 
/*     */       
/* 179 */       if (ret == null) {
/* 180 */         throw new StructuralCodeConstraintException("Subroutine without a RET detected.");
/*     */       }
/* 182 */       if (((RET)ret.getInstruction()).getIndex() != this.localVariable) {
/* 183 */         throw new StructuralCodeConstraintException("Subroutine uses '" + ret + "' which does not match the correct local variable '" + this.localVariable + "'.");
/*     */       }
/* 185 */       this.theRET = ret;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public InstructionHandle[] getEnteringJsrInstructions() {
/* 192 */       if (this == this.this$0.TOPLEVEL) {
/* 193 */         throw new AssertionViolatedException("getLeavingRET() called on top level pseudo-subroutine.");
/*     */       }
/* 195 */       InstructionHandle[] jsrs = new InstructionHandle[this.theJSRs.size()];
/* 196 */       return (InstructionHandle[])this.theJSRs.toArray((Object[])jsrs);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void addEnteringJsrInstruction(InstructionHandle jsrInst) {
/* 203 */       if (jsrInst == null || !(jsrInst.getInstruction() instanceof JsrInstruction)) {
/* 204 */         throw new AssertionViolatedException("Expecting JsrInstruction InstructionHandle.");
/*     */       }
/* 206 */       if (this.localVariable == -1) {
/* 207 */         throw new AssertionViolatedException("Set the localVariable first!");
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 213 */       if (this.localVariable != ((ASTORE)((JsrInstruction)jsrInst.getInstruction()).getTarget().getInstruction()).getIndex()) {
/* 214 */         throw new AssertionViolatedException("Setting a wrong JsrInstruction.");
/*     */       }
/*     */       
/* 217 */       this.theJSRs.add(jsrInst);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public InstructionHandle getLeavingRET() {
/* 224 */       if (this == this.this$0.TOPLEVEL) {
/* 225 */         throw new AssertionViolatedException("getLeavingRET() called on top level pseudo-subroutine.");
/*     */       }
/* 227 */       return this.theRET;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public InstructionHandle[] getInstructions() {
/* 234 */       InstructionHandle[] ret = new InstructionHandle[this.instructions.size()];
/* 235 */       return (InstructionHandle[])this.instructions.toArray((Object[])ret);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void addInstruction(InstructionHandle ih) {
/* 244 */       if (this.theRET != null) {
/* 245 */         throw new AssertionViolatedException("All instructions must have been added before invoking setLeavingRET().");
/*     */       }
/* 247 */       this.instructions.add(ih);
/*     */     }
/*     */ 
/*     */     
/*     */     public int[] getRecursivelyAccessedLocalsIndices() {
/* 252 */       HashSet s = new HashSet();
/* 253 */       int[] lvs = getAccessedLocalsIndices();
/* 254 */       for (int j = 0; j < lvs.length; j++) {
/* 255 */         s.add(new Integer(lvs[j]));
/*     */       }
/* 257 */       _getRecursivelyAccessedLocalsIndicesHelper(s, subSubs());
/* 258 */       int[] ret = new int[s.size()];
/* 259 */       Iterator i = s.iterator();
/* 260 */       int k = -1;
/* 261 */       while (i.hasNext()) {
/* 262 */         k++;
/* 263 */         ret[k] = ((Integer)i.next()).intValue();
/*     */       } 
/* 265 */       return ret;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private void _getRecursivelyAccessedLocalsIndicesHelper(HashSet s, Subroutine[] subs) {
/* 273 */       for (int i = 0; i < subs.length; i++) {
/* 274 */         int[] lvs = subs[i].getAccessedLocalsIndices();
/* 275 */         for (int j = 0; j < lvs.length; j++) {
/* 276 */           s.add(new Integer(lvs[j]));
/*     */         }
/* 278 */         if ((subs[i].subSubs()).length != 0) {
/* 279 */           _getRecursivelyAccessedLocalsIndicesHelper(s, subs[i].subSubs());
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int[] getAccessedLocalsIndices() {
/* 289 */       HashSet acc = new HashSet();
/* 290 */       if (this.theRET == null && this != this.this$0.TOPLEVEL) {
/* 291 */         throw new AssertionViolatedException("This subroutine object must be built up completely before calculating accessed locals.");
/*     */       }
/* 293 */       Iterator i = this.instructions.iterator();
/* 294 */       while (i.hasNext()) {
/* 295 */         InstructionHandle ih = i.next();
/*     */         
/* 297 */         if (ih.getInstruction() instanceof LocalVariableInstruction || ih.getInstruction() instanceof RET) {
/* 298 */           int idx = ((IndexedInstruction)ih.getInstruction()).getIndex();
/* 299 */           acc.add(new Integer(idx));
/*     */ 
/*     */ 
/*     */           
/*     */           try {
/* 304 */             if (ih.getInstruction() instanceof LocalVariableInstruction) {
/* 305 */               int s = ((LocalVariableInstruction)ih.getInstruction()).getType(null).getSize();
/* 306 */               if (s == 2) acc.add(new Integer(idx + 1));
/*     */             
/*     */             } 
/*     */           } catch (RuntimeException re) {
/* 310 */             throw new AssertionViolatedException("Oops. BCEL did not like NULL as a ConstantPoolGen object.");
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 315 */       int[] ret = new int[acc.size()];
/* 316 */       i = (Iterator)acc.iterator();
/* 317 */       int j = -1;
/* 318 */       while (i.hasNext()) {
/* 319 */         j++;
/* 320 */         ret[j] = ((Integer)i.next()).intValue();
/*     */       } 
/* 322 */       return ret;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Subroutine[] subSubs() {
/* 329 */       HashSet h = new HashSet();
/*     */       
/* 331 */       Iterator i = this.instructions.iterator();
/* 332 */       while (i.hasNext()) {
/* 333 */         Instruction inst = ((InstructionHandle)i.next()).getInstruction();
/* 334 */         if (inst instanceof JsrInstruction) {
/* 335 */           InstructionHandle targ = ((JsrInstruction)inst).getTarget();
/* 336 */           h.add(this.this$0.getSubroutine(targ));
/*     */         } 
/*     */       } 
/* 339 */       Subroutine[] ret = new Subroutine[h.size()];
/* 340 */       return h.<Subroutine>toArray(ret);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void setLocalVariable(int i) {
/* 350 */       if (this.localVariable != -1) {
/* 351 */         throw new AssertionViolatedException("localVariable set twice.");
/*     */       }
/*     */       
/* 354 */       this.localVariable = i;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SubroutineImpl(Subroutines this$0) {
/* 361 */       this.this$0 = this$0;
/*     */       this.UNSET = -1;
/*     */       this.localVariable = -1;
/*     */       this.instructions = new HashSet();
/*     */       this.theJSRs = new HashSet();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 371 */   private Hashtable subroutines = new Hashtable();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final Subroutine TOPLEVEL;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Subroutines(MethodGen mg) {
/* 388 */     InstructionHandle[] all = mg.getInstructionList().getInstructionHandles();
/* 389 */     CodeExceptionGen[] handlers = mg.getExceptionHandlers();
/*     */ 
/*     */     
/* 392 */     this.TOPLEVEL = new SubroutineImpl(this);
/*     */ 
/*     */     
/* 395 */     HashSet sub_leaders = new HashSet();
/* 396 */     InstructionHandle ih = all[0];
/* 397 */     for (int i = 0; i < all.length; i++) {
/* 398 */       Instruction inst = all[i].getInstruction();
/* 399 */       if (inst instanceof JsrInstruction) {
/* 400 */         sub_leaders.add(((JsrInstruction)inst).getTarget());
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 405 */     Iterator iter = sub_leaders.iterator();
/* 406 */     while (iter.hasNext()) {
/* 407 */       SubroutineImpl sr = new SubroutineImpl(this);
/* 408 */       InstructionHandle astore = iter.next();
/* 409 */       sr.setLocalVariable(((ASTORE)astore.getInstruction()).getIndex());
/* 410 */       this.subroutines.put(astore, sr);
/*     */     } 
/*     */ 
/*     */     
/* 414 */     this.subroutines.put(all[0], this.TOPLEVEL);
/* 415 */     sub_leaders.add(all[0]);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 422 */     for (int j = 0; j < all.length; j++) {
/* 423 */       Instruction inst = all[j].getInstruction();
/* 424 */       if (inst instanceof JsrInstruction) {
/* 425 */         InstructionHandle leader = ((JsrInstruction)inst).getTarget();
/* 426 */         ((SubroutineImpl)getSubroutine(leader)).addEnteringJsrInstruction(all[j]);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 432 */     HashSet instructions_assigned = new HashSet();
/*     */     
/* 434 */     Hashtable colors = new Hashtable();
/*     */     
/* 436 */     iter = sub_leaders.iterator();
/* 437 */     while (iter.hasNext()) {
/*     */       
/* 439 */       InstructionHandle actual = iter.next();
/*     */       
/* 441 */       for (int m = 0; m < all.length; m++) {
/* 442 */         colors.put(all[m], Color.white);
/*     */       }
/* 444 */       colors.put(actual, Color.gray);
/*     */       
/* 446 */       ArrayList Q = new ArrayList();
/* 447 */       Q.add(actual);
/*     */ 
/*     */       
/* 450 */       if (actual == all[0]) {
/* 451 */         for (int i1 = 0; i1 < handlers.length; i1++) {
/* 452 */           colors.put(handlers[i1].getHandlerPC(), Color.gray);
/* 453 */           Q.add(handlers[i1].getHandlerPC());
/*     */         } 
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 459 */       while (Q.size() != 0) {
/* 460 */         InstructionHandle u = Q.remove(0);
/* 461 */         InstructionHandle[] successors = getSuccessors(u);
/* 462 */         for (int i1 = 0; i1 < successors.length; i1++) {
/* 463 */           if ((Color)colors.get(successors[i1]) == Color.white) {
/* 464 */             colors.put(successors[i1], Color.gray);
/* 465 */             Q.add(successors[i1]);
/*     */           } 
/*     */         } 
/* 468 */         colors.put(u, Color.black);
/*     */       } 
/*     */       
/* 471 */       for (int n = 0; n < all.length; n++) {
/* 472 */         if (colors.get(all[n]) == Color.black) {
/* 473 */           ((SubroutineImpl)((actual == all[0]) ? (SubroutineImpl)getTopLevel() : (SubroutineImpl)getSubroutine(actual))).addInstruction(all[n]);
/* 474 */           if (instructions_assigned.contains(all[n])) {
/* 475 */             throw new StructuralCodeConstraintException("Instruction '" + all[n] + "' is part of more than one subroutine (or of the top level and a subroutine).");
/*     */           }
/*     */           
/* 478 */           instructions_assigned.add(all[n]);
/*     */         } 
/*     */       } 
/*     */       
/* 482 */       if (actual != all[0]) {
/* 483 */         ((SubroutineImpl)getSubroutine(actual)).setLeavingRET();
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 489 */     for (int k = 0; k < handlers.length; k++) {
/* 490 */       InstructionHandle _protected = handlers[k].getStartPC();
/* 491 */       while (_protected != handlers[k].getEndPC().getNext()) {
/* 492 */         Enumeration subs = this.subroutines.elements();
/* 493 */         while (subs.hasMoreElements()) {
/* 494 */           Subroutine sub = subs.nextElement();
/* 495 */           if (sub != this.subroutines.get(all[0]) && 
/* 496 */             sub.contains(_protected)) {
/* 497 */             throw new StructuralCodeConstraintException("Subroutine instruction '" + _protected + "' is protected by an exception handler, '" + handlers[k] + "'. This is forbidden by the JustIce verifier due to its clear definition of subroutines.");
/*     */           }
/*     */         } 
/*     */         
/* 501 */         _protected = _protected.getNext();
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 511 */     noRecursiveCalls(getTopLevel(), new HashSet());
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
/*     */ 
/*     */   
/*     */   private void noRecursiveCalls(Subroutine sub, HashSet set) {
/* 527 */     Subroutine[] subs = sub.subSubs();
/*     */     
/* 529 */     for (int i = 0; i < subs.length; i++) {
/* 530 */       int index = ((RET)subs[i].getLeavingRET().getInstruction()).getIndex();
/*     */       
/* 532 */       if (!set.add(new Integer(index))) {
/*     */         
/* 534 */         SubroutineImpl si = (SubroutineImpl)subs[i];
/* 535 */         throw new StructuralCodeConstraintException("Subroutine with local variable '" + si.localVariable + "', JSRs '" + si.theJSRs + "', RET '" + si.theRET + "' is called by a subroutine which uses the same local variable index as itself; maybe even a recursive call? JustIce's clean definition of a subroutine forbids both.");
/*     */       } 
/*     */       
/* 538 */       noRecursiveCalls(subs[i], set);
/*     */       
/* 540 */       set.remove(new Integer(index));
/*     */     } 
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
/*     */   public Subroutine getSubroutine(InstructionHandle leader) {
/* 553 */     Subroutine ret = (Subroutine)this.subroutines.get(leader);
/*     */     
/* 555 */     if (ret == null) {
/* 556 */       throw new AssertionViolatedException("Subroutine requested for an InstructionHandle that is not a leader of a subroutine.");
/*     */     }
/*     */     
/* 559 */     if (ret == this.TOPLEVEL) {
/* 560 */       throw new AssertionViolatedException("TOPLEVEL special subroutine requested; use getTopLevel().");
/*     */     }
/*     */     
/* 563 */     return ret;
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
/*     */   
/*     */   public Subroutine subroutineOf(InstructionHandle any) {
/* 578 */     Iterator i = this.subroutines.values().iterator();
/* 579 */     while (i.hasNext()) {
/* 580 */       Subroutine s = i.next();
/* 581 */       if (s.contains(any)) return s; 
/*     */     } 
/* 583 */     System.err.println("DEBUG: Please verify '" + any + "' lies in dead code.");
/* 584 */     return null;
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
/*     */   
/*     */   public Subroutine getTopLevel() {
/* 599 */     return this.TOPLEVEL;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static InstructionHandle[] getSuccessors(InstructionHandle instruction) {
/* 608 */     InstructionHandle[] empty = new InstructionHandle[0];
/* 609 */     InstructionHandle[] single = new InstructionHandle[1];
/* 610 */     InstructionHandle[] pair = new InstructionHandle[2];
/*     */     
/* 612 */     Instruction inst = instruction.getInstruction();
/*     */     
/* 614 */     if (inst instanceof RET) {
/* 615 */       return empty;
/*     */     }
/*     */ 
/*     */     
/* 619 */     if (inst instanceof org.apache.bcel.generic.ReturnInstruction) {
/* 620 */       return empty;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 625 */     if (inst instanceof org.apache.bcel.generic.ATHROW) {
/* 626 */       return empty;
/*     */     }
/*     */ 
/*     */     
/* 630 */     if (inst instanceof JsrInstruction) {
/* 631 */       single[0] = instruction.getNext();
/* 632 */       return single;
/*     */     } 
/*     */     
/* 635 */     if (inst instanceof GotoInstruction) {
/* 636 */       single[0] = ((GotoInstruction)inst).getTarget();
/* 637 */       return single;
/*     */     } 
/*     */     
/* 640 */     if (inst instanceof BranchInstruction) {
/* 641 */       if (inst instanceof Select) {
/*     */ 
/*     */         
/* 644 */         InstructionHandle[] matchTargets = ((Select)inst).getTargets();
/* 645 */         InstructionHandle[] ret = new InstructionHandle[matchTargets.length + 1];
/* 646 */         ret[0] = ((Select)inst).getTarget();
/* 647 */         System.arraycopy(matchTargets, 0, ret, 1, matchTargets.length);
/* 648 */         return ret;
/*     */       } 
/*     */       
/* 651 */       pair[0] = instruction.getNext();
/* 652 */       pair[1] = ((BranchInstruction)inst).getTarget();
/* 653 */       return pair;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 658 */     single[0] = instruction.getNext();
/* 659 */     return single;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 666 */     return "---\n" + this.subroutines.toString() + "\n---\n";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/verifier/structurals/Subroutines.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */