/*     */ package org.apache.bcel.verifier.structurals;
/*     */ 
/*     */ import java.io.PrintWriter;
/*     */ import java.io.StringWriter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import java.util.Vector;
/*     */ import org.apache.bcel.Repository;
/*     */ import org.apache.bcel.classfile.JavaClass;
/*     */ import org.apache.bcel.classfile.Method;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.InstructionHandle;
/*     */ import org.apache.bcel.generic.JsrInstruction;
/*     */ import org.apache.bcel.generic.MethodGen;
/*     */ import org.apache.bcel.generic.ObjectType;
/*     */ import org.apache.bcel.generic.RET;
/*     */ import org.apache.bcel.generic.ReturnaddressType;
/*     */ import org.apache.bcel.generic.Type;
/*     */ import org.apache.bcel.verifier.PassVerifier;
/*     */ import org.apache.bcel.verifier.VerificationResult;
/*     */ import org.apache.bcel.verifier.Verifier;
/*     */ import org.apache.bcel.verifier.exc.AssertionViolatedException;
/*     */ import org.apache.bcel.verifier.exc.VerifierConstraintViolatedException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Pass3bVerifier
/*     */   extends PassVerifier
/*     */ {
/*     */   private static final boolean DEBUG = true;
/*     */   private Verifier myOwner;
/*     */   private int method_no;
/*     */   
/*     */   private static final class InstructionContextQueue
/*     */   {
/* 101 */     private Vector ics = new Vector();
/* 102 */     private Vector ecs = new Vector();
/*     */     public void add(InstructionContext ic, ArrayList executionChain) {
/* 104 */       this.ics.add(ic);
/* 105 */       this.ecs.add(executionChain);
/*     */     }
/*     */     public boolean isEmpty() {
/* 108 */       return this.ics.isEmpty();
/*     */     }
/*     */     public void remove() {
/* 111 */       remove(0);
/*     */     }
/*     */     public void remove(int i) {
/* 114 */       this.ics.remove(i);
/* 115 */       this.ecs.remove(i);
/*     */     }
/*     */     public InstructionContext getIC(int i) {
/* 118 */       return this.ics.get(i);
/*     */     }
/*     */     public ArrayList getEC(int i) {
/* 121 */       return this.ecs.get(i);
/*     */     }
/*     */     public int size() {
/* 124 */       return this.ics.size();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private InstructionContextQueue() {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Pass3bVerifier(Verifier owner, int method_no) {
/* 143 */     this.myOwner = owner;
/* 144 */     this.method_no = method_no;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void circulationPump(ControlFlowGraph cfg, InstructionContext start, Frame vanillaFrame, InstConstraintVisitor icv, ExecutionVisitor ev) {
/* 155 */     Random random = new Random();
/* 156 */     InstructionContextQueue icq = new InstructionContextQueue();
/*     */     
/* 158 */     start.execute(vanillaFrame, new ArrayList(), icv, ev);
/*     */     
/* 160 */     icq.add(start, new ArrayList());
/*     */ 
/*     */     
/* 163 */     while (!icq.isEmpty()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 173 */       InstructionContext u = icq.getIC(0);
/* 174 */       ArrayList ec = icq.getEC(0);
/* 175 */       icq.remove(0);
/*     */ 
/*     */       
/* 178 */       ArrayList oldchain = (ArrayList)ec.clone();
/* 179 */       ArrayList newchain = (ArrayList)ec.clone();
/* 180 */       newchain.add(u);
/*     */       
/* 182 */       if (u.getInstruction().getInstruction() instanceof RET) {
/*     */ 
/*     */ 
/*     */         
/* 186 */         RET ret = (RET)u.getInstruction().getInstruction();
/* 187 */         ReturnaddressType t = (ReturnaddressType)u.getOutFrame(oldchain).getLocals().get(ret.getIndex());
/* 188 */         InstructionContext theSuccessor = cfg.contextOf(t.getTarget());
/*     */ 
/*     */         
/* 191 */         InstructionContext lastJSR = null;
/* 192 */         int skip_jsr = 0;
/* 193 */         for (int ss = oldchain.size() - 1; ss >= 0; ss--) {
/* 194 */           if (skip_jsr < 0) {
/* 195 */             throw new AssertionViolatedException("More RET than JSR in execution chain?!");
/*     */           }
/*     */           
/* 198 */           if (((InstructionContext)oldchain.get(ss)).getInstruction().getInstruction() instanceof JsrInstruction) {
/* 199 */             if (skip_jsr == 0) {
/* 200 */               lastJSR = oldchain.get(ss);
/*     */               
/*     */               break;
/*     */             } 
/* 204 */             skip_jsr--;
/*     */           } 
/*     */           
/* 207 */           if (((InstructionContext)oldchain.get(ss)).getInstruction().getInstruction() instanceof RET) {
/* 208 */             skip_jsr++;
/*     */           }
/*     */         } 
/* 211 */         if (lastJSR == null) {
/* 212 */           throw new AssertionViolatedException("RET without a JSR before in ExecutionChain?! EC: '" + oldchain + "'.");
/*     */         }
/* 214 */         JsrInstruction jsr = (JsrInstruction)lastJSR.getInstruction().getInstruction();
/* 215 */         if (theSuccessor != cfg.contextOf(jsr.physicalSuccessor())) {
/* 216 */           throw new AssertionViolatedException("RET '" + u.getInstruction() + "' info inconsistent: jump back to '" + theSuccessor + "' or '" + cfg.contextOf(jsr.physicalSuccessor()) + "'?");
/*     */         }
/*     */         
/* 219 */         if (theSuccessor.execute(u.getOutFrame(oldchain), newchain, icv, ev)) {
/* 220 */           icq.add(theSuccessor, (ArrayList)newchain.clone());
/*     */         
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 226 */         InstructionContext[] succs = u.getSuccessors();
/* 227 */         for (int i = 0; i < succs.length; i++) {
/* 228 */           InstructionContext v = succs[i];
/* 229 */           if (v.execute(u.getOutFrame(oldchain), newchain, icv, ev)) {
/* 230 */             icq.add(v, (ArrayList)newchain.clone());
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 237 */       ExceptionHandler[] exc_hds = u.getExceptionHandlers();
/* 238 */       for (int s = 0; s < exc_hds.length; s++) {
/* 239 */         InstructionContext v = cfg.contextOf(exc_hds[s].getHandlerStart());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 250 */         if (v.execute(new Frame(u.getOutFrame(oldchain).getLocals(), new OperandStack(u.getOutFrame(oldchain).getStack().maxStack(), (exc_hds[s].getExceptionType() == null) ? Type.THROWABLE : exc_hds[s].getExceptionType())), new ArrayList(), icv, ev)) {
/* 251 */           icq.add(v, new ArrayList());
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 257 */     InstructionHandle ih = start.getInstruction();
/*     */     do {
/* 259 */       if (!(ih.getInstruction() instanceof org.apache.bcel.generic.ReturnInstruction) || cfg.isDead(ih))
/* 260 */         continue;  InstructionContext ic = cfg.contextOf(ih);
/* 261 */       Frame f = ic.getOutFrame(new ArrayList());
/* 262 */       LocalVariables lvs = f.getLocals();
/* 263 */       for (int i = 0; i < lvs.maxLocals(); i++) {
/* 264 */         if (lvs.get(i) instanceof UninitializedObjectType) {
/* 265 */           addMessage("Warning: ReturnInstruction '" + ic + "' may leave method with an uninitialized object in the local variables array '" + lvs + "'.");
/*     */         }
/*     */       } 
/* 268 */       OperandStack os = f.getStack();
/* 269 */       for (int j = 0; j < os.size(); j++) {
/* 270 */         if (os.peek(j) instanceof UninitializedObjectType) {
/* 271 */           addMessage("Warning: ReturnInstruction '" + ic + "' may leave method with an uninitialized object on the operand stack '" + os + "'.");
/*     */         }
/*     */       }
/*     */     
/* 275 */     } while ((ih = ih.getNext()) != null);
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
/*     */   public VerificationResult do_verify() {
/* 290 */     if (!this.myOwner.doPass3a(this.method_no).equals(VerificationResult.VR_OK)) {
/* 291 */       return VerificationResult.VR_NOTYET;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 296 */     JavaClass jc = Repository.lookupClass(this.myOwner.getClassName());
/*     */     
/* 298 */     ConstantPoolGen constantPoolGen = new ConstantPoolGen(jc.getConstantPool());
/*     */     
/* 300 */     InstConstraintVisitor icv = new InstConstraintVisitor();
/* 301 */     icv.setConstantPoolGen(constantPoolGen);
/*     */     
/* 303 */     ExecutionVisitor ev = new ExecutionVisitor();
/* 304 */     ev.setConstantPoolGen(constantPoolGen);
/*     */     
/* 306 */     Method[] methods = jc.getMethods();
/*     */ 
/*     */     
/*     */     try {
/* 310 */       MethodGen mg = new MethodGen(methods[this.method_no], this.myOwner.getClassName(), constantPoolGen);
/*     */       
/* 312 */       icv.setMethodGen(mg);
/*     */ 
/*     */       
/* 315 */       if (!mg.isAbstract() && !mg.isNative()) {
/*     */         
/* 317 */         ControlFlowGraph cfg = new ControlFlowGraph(mg);
/*     */ 
/*     */         
/* 320 */         Frame f = new Frame(mg.getMaxLocals(), mg.getMaxStack());
/* 321 */         if (!mg.isStatic()) {
/* 322 */           if (mg.getName().equals("<init>")) {
/* 323 */             Frame._this = new UninitializedObjectType(new ObjectType(jc.getClassName()));
/* 324 */             f.getLocals().set(0, (Type)Frame._this);
/*     */           } else {
/*     */             
/* 327 */             Frame._this = null;
/* 328 */             f.getLocals().set(0, (Type)new ObjectType(jc.getClassName()));
/*     */           } 
/*     */         }
/* 331 */         Type[] argtypes = mg.getArgumentTypes();
/* 332 */         int twoslotoffset = 0;
/* 333 */         for (int j = 0; j < argtypes.length; j++) {
/* 334 */           if (argtypes[j] == Type.SHORT || argtypes[j] == Type.BYTE || argtypes[j] == Type.CHAR || argtypes[j] == Type.BOOLEAN) {
/* 335 */             argtypes[j] = (Type)Type.INT;
/*     */           }
/* 337 */           f.getLocals().set(twoslotoffset + j + (mg.isStatic() ? 0 : 1), argtypes[j]);
/* 338 */           if (argtypes[j].getSize() == 2) {
/* 339 */             twoslotoffset++;
/* 340 */             f.getLocals().set(twoslotoffset + j + (mg.isStatic() ? 0 : 1), Type.UNKNOWN);
/*     */           } 
/*     */         } 
/* 343 */         circulationPump(cfg, cfg.contextOf(mg.getInstructionList().getStart()), f, icv, ev);
/*     */       } 
/*     */     } catch (VerifierConstraintViolatedException ce) {
/*     */       
/* 347 */       ce.extendMessage("Constraint violated in method '" + methods[this.method_no] + "':\n", "");
/* 348 */       return new VerificationResult(2, ce.getMessage());
/*     */     
/*     */     }
/*     */     catch (RuntimeException re) {
/*     */       
/* 353 */       StringWriter sw = new StringWriter();
/* 354 */       PrintWriter pw = new PrintWriter(sw);
/* 355 */       re.printStackTrace(pw);
/*     */       
/* 357 */       throw new AssertionViolatedException("Some RuntimeException occured while verify()ing class '" + jc.getClassName() + "', method '" + methods[this.method_no] + "'. Original RuntimeException's stack trace:\n---\n" + sw + "---\n");
/*     */     } 
/* 359 */     return VerificationResult.VR_OK;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMethodNo() {
/* 364 */     return this.method_no;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/verifier/structurals/Pass3bVerifier.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */