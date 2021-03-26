/*     */ package org.apache.bcel.verifier.structurals;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Hashtable;
/*     */ import org.apache.bcel.generic.BranchInstruction;
/*     */ import org.apache.bcel.generic.GotoInstruction;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionHandle;
/*     */ import org.apache.bcel.generic.JsrInstruction;
/*     */ import org.apache.bcel.generic.MethodGen;
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
/*     */ public class ControlFlowGraph
/*     */ {
/*     */   private final MethodGen method_gen;
/*     */   private final Subroutines subroutines;
/*     */   private final ExceptionHandlers exceptionhandlers;
/*     */   
/*     */   private class InstructionContextImpl
/*     */     implements InstructionContext
/*     */   {
/*     */     private int TAG;
/*     */     private InstructionHandle instruction;
/*     */     private HashMap inFrames;
/*     */     private HashMap outFrames;
/*     */     private ArrayList executionPredecessors;
/*     */     private final ControlFlowGraph this$0;
/*     */     
/*     */     public InstructionContextImpl(ControlFlowGraph this$0, InstructionHandle inst) {
/* 110 */       this.this$0 = this$0; this.executionPredecessors = null;
/* 111 */       if (inst == null) throw new AssertionViolatedException("Cannot instantiate InstructionContextImpl from NULL.");
/*     */       
/* 113 */       this.instruction = inst;
/* 114 */       this.inFrames = new HashMap();
/* 115 */       this.outFrames = new HashMap();
/*     */     }
/*     */ 
/*     */     
/*     */     public int getTag() {
/* 120 */       return this.TAG;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setTag(int tag) {
/* 125 */       this.TAG = tag;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ExceptionHandler[] getExceptionHandlers() {
/* 132 */       return this.this$0.exceptionhandlers.getExceptionHandlers(getInstruction());
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Frame getOutFrame(ArrayList execChain) {
/* 139 */       this.executionPredecessors = execChain;
/*     */ 
/*     */ 
/*     */       
/* 143 */       InstructionContext jsr = lastExecutionJSR();
/*     */       
/* 145 */       Frame org = (Frame)this.outFrames.get(jsr);
/*     */       
/* 147 */       if (org == null) {
/* 148 */         throw new AssertionViolatedException("outFrame not set! This:\n" + this + "\nExecutionChain: " + getExecutionChain() + "\nOutFrames: '" + this.outFrames + "'.");
/*     */       }
/* 150 */       return org.getClone();
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
/*     */     public boolean execute(Frame inFrame, ArrayList execPreds, InstConstraintVisitor icv, ExecutionVisitor ev) {
/* 170 */       this.executionPredecessors = (ArrayList)execPreds.clone();
/*     */ 
/*     */       
/* 173 */       if (lastExecutionJSR() == null && this.this$0.subroutines.subroutineOf(getInstruction()) != this.this$0.subroutines.getTopLevel()) {
/* 174 */         throw new AssertionViolatedException("Huh?! Am I '" + this + "' part of a subroutine or not?");
/*     */       }
/* 176 */       if (lastExecutionJSR() != null && this.this$0.subroutines.subroutineOf(getInstruction()) == this.this$0.subroutines.getTopLevel()) {
/* 177 */         throw new AssertionViolatedException("Huh?! Am I '" + this + "' part of a subroutine or not?");
/*     */       }
/*     */       
/* 180 */       Frame inF = (Frame)this.inFrames.get(lastExecutionJSR());
/* 181 */       if (inF == null) {
/* 182 */         this.inFrames.put(lastExecutionJSR(), inFrame);
/* 183 */         inF = inFrame;
/*     */       } else {
/*     */         
/* 186 */         if (inF.equals(inFrame)) {
/* 187 */           return false;
/*     */         }
/* 189 */         if (!mergeInFrames(inFrame)) {
/* 190 */           return false;
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 197 */       Frame workingFrame = inF.getClone();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 203 */         icv.setFrame(workingFrame);
/* 204 */         getInstruction().accept(icv);
/*     */       } catch (StructuralCodeConstraintException ce) {
/*     */         
/* 207 */         ce.extendMessage("", "\nInstructionHandle: " + getInstruction() + "\n");
/* 208 */         ce.extendMessage("", "\nExecution Frame:\n" + workingFrame);
/* 209 */         extendMessageWithFlow(ce);
/* 210 */         throw ce;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 216 */       ev.setFrame(workingFrame);
/* 217 */       getInstruction().accept(ev);
/*     */       
/* 219 */       this.outFrames.put(lastExecutionJSR(), workingFrame);
/*     */       
/* 221 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 232 */       String ret = getInstruction().toString(false) + "\t[InstructionContext]";
/* 233 */       return ret;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private boolean mergeInFrames(Frame inFrame) {
/* 242 */       Frame inF = (Frame)this.inFrames.get(lastExecutionJSR());
/* 243 */       OperandStack oldstack = inF.getStack().getClone();
/* 244 */       LocalVariables oldlocals = inF.getLocals().getClone();
/*     */       try {
/* 246 */         inF.getStack().merge(inFrame.getStack());
/* 247 */         inF.getLocals().merge(inFrame.getLocals());
/*     */       } catch (StructuralCodeConstraintException sce) {
/*     */         
/* 250 */         extendMessageWithFlow(sce);
/* 251 */         throw sce;
/*     */       } 
/* 253 */       if (oldstack.equals(inF.getStack()) && oldlocals.equals(inF.getLocals()))
/*     */       {
/* 255 */         return false;
/*     */       }
/*     */       
/* 258 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private String getExecutionChain() {
/* 268 */       String s = toString();
/* 269 */       for (int i = this.executionPredecessors.size() - 1; i >= 0; i--) {
/* 270 */         s = (new StringBuffer()).append(this.executionPredecessors.get(i)).append("\n").append(s).toString();
/*     */       }
/* 272 */       return s;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private void extendMessageWithFlow(StructuralCodeConstraintException e) {
/* 282 */       String s = "Execution flow:\n";
/* 283 */       e.extendMessage("", s + getExecutionChain());
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public InstructionHandle getInstruction() {
/* 290 */       return this.instruction;
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
/*     */     private InstructionContextImpl lastExecutionJSR() {
/* 302 */       int size = this.executionPredecessors.size();
/* 303 */       int retcount = 0;
/*     */       
/* 305 */       for (int i = size - 1; i >= 0; i--) {
/* 306 */         InstructionContextImpl current = this.executionPredecessors.get(i);
/* 307 */         Instruction currentlast = current.getInstruction().getInstruction();
/* 308 */         if (currentlast instanceof org.apache.bcel.generic.RET) retcount++;
/*     */         
/* 310 */         retcount--;
/* 311 */         if (currentlast instanceof JsrInstruction && retcount == -1) return current;
/*     */       
/*     */       } 
/* 314 */       return null;
/*     */     }
/*     */ 
/*     */     
/*     */     public InstructionContext[] getSuccessors() {
/* 319 */       return this.this$0.contextsOf(_getSuccessors());
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private InstructionHandle[] _getSuccessors() {
/* 330 */       InstructionHandle[] empty = new InstructionHandle[0];
/* 331 */       InstructionHandle[] single = new InstructionHandle[1];
/* 332 */       InstructionHandle[] pair = new InstructionHandle[2];
/*     */       
/* 334 */       Instruction inst = getInstruction().getInstruction();
/*     */       
/* 336 */       if (inst instanceof org.apache.bcel.generic.RET) {
/* 337 */         Subroutine s = this.this$0.subroutines.subroutineOf(getInstruction());
/* 338 */         if (s == null) {
/* 339 */           throw new AssertionViolatedException("Asking for successors of a RET in dead code?!");
/*     */         }
/*     */         
/* 342 */         throw new AssertionViolatedException("DID YOU REALLY WANT TO ASK FOR RET'S SUCCS?");
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
/* 354 */       if (inst instanceof org.apache.bcel.generic.ReturnInstruction) {
/* 355 */         return empty;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 360 */       if (inst instanceof org.apache.bcel.generic.ATHROW) {
/* 361 */         return empty;
/*     */       }
/*     */ 
/*     */       
/* 365 */       if (inst instanceof JsrInstruction) {
/* 366 */         single[0] = ((JsrInstruction)inst).getTarget();
/* 367 */         return single;
/*     */       } 
/*     */       
/* 370 */       if (inst instanceof GotoInstruction) {
/* 371 */         single[0] = ((GotoInstruction)inst).getTarget();
/* 372 */         return single;
/*     */       } 
/*     */       
/* 375 */       if (inst instanceof BranchInstruction) {
/* 376 */         if (inst instanceof Select) {
/*     */ 
/*     */           
/* 379 */           InstructionHandle[] matchTargets = ((Select)inst).getTargets();
/* 380 */           InstructionHandle[] ret = new InstructionHandle[matchTargets.length + 1];
/* 381 */           ret[0] = ((Select)inst).getTarget();
/* 382 */           System.arraycopy(matchTargets, 0, ret, 1, matchTargets.length);
/* 383 */           return ret;
/*     */         } 
/*     */         
/* 386 */         pair[0] = getInstruction().getNext();
/* 387 */         pair[1] = ((BranchInstruction)inst).getTarget();
/* 388 */         return pair;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 393 */       single[0] = getInstruction().getNext();
/* 394 */       return single;
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
/*     */ 
/*     */ 
/*     */   
/* 409 */   private Hashtable instructionContexts = new Hashtable();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ControlFlowGraph(MethodGen method_gen) {
/* 415 */     this.subroutines = new Subroutines(method_gen);
/* 416 */     this.exceptionhandlers = new ExceptionHandlers(method_gen);
/*     */     
/* 418 */     InstructionHandle[] instructionhandles = method_gen.getInstructionList().getInstructionHandles();
/* 419 */     for (int i = 0; i < instructionhandles.length; i++) {
/* 420 */       this.instructionContexts.put(instructionhandles[i], new InstructionContextImpl(this, instructionhandles[i]));
/*     */     }
/*     */     
/* 423 */     this.method_gen = method_gen;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InstructionContext contextOf(InstructionHandle inst) {
/* 430 */     InstructionContext ic = (InstructionContext)this.instructionContexts.get(inst);
/* 431 */     if (ic == null) {
/* 432 */       throw new AssertionViolatedException("InstructionContext requested for an InstructionHandle that's not known!");
/*     */     }
/* 434 */     return ic;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InstructionContext[] contextsOf(InstructionHandle[] insts) {
/* 442 */     InstructionContext[] ret = new InstructionContext[insts.length];
/* 443 */     for (int i = 0; i < insts.length; i++) {
/* 444 */       ret[i] = contextOf(insts[i]);
/*     */     }
/* 446 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InstructionContext[] getInstructionContexts() {
/* 455 */     InstructionContext[] ret = new InstructionContext[this.instructionContexts.values().size()];
/* 456 */     return (InstructionContext[])this.instructionContexts.values().toArray((Object[])ret);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDead(InstructionHandle i) {
/* 464 */     return this.instructionContexts.containsKey(i);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/verifier/structurals/ControlFlowGraph.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */