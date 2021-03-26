/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import java.util.Dictionary;
/*     */ import java.util.Vector;
/*     */ import org.apache.bcel.generic.BranchHandle;
/*     */ import org.apache.bcel.generic.BranchInstruction;
/*     */ import org.apache.bcel.generic.GOTO_W;
/*     */ import org.apache.bcel.generic.InstructionHandle;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class TestSeq
/*     */ {
/*     */   private int _kernelType;
/*  58 */   private Vector _patterns = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   private Mode _mode = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  68 */   private Template _default = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private InstructionList _instructionList;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  78 */   private InstructionHandle _start = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TestSeq(Vector patterns, Mode mode) {
/*  84 */     this(patterns, -2, mode);
/*     */   }
/*     */   
/*     */   public TestSeq(Vector patterns, int kernelType, Mode mode) {
/*  88 */     this._patterns = patterns;
/*  89 */     this._kernelType = kernelType;
/*  90 */     this._mode = mode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  99 */     int count = this._patterns.size();
/* 100 */     StringBuffer result = new StringBuffer();
/*     */     
/* 102 */     for (int i = 0; i < count; i++) {
/* 103 */       LocationPathPattern pattern = this._patterns.elementAt(i);
/*     */ 
/*     */       
/* 106 */       if (i == 0) {
/* 107 */         result.append("Testseq for kernel " + this._kernelType).append('\n');
/*     */       }
/*     */       
/* 110 */       result.append("   pattern " + i + ": ").append(pattern.toString()).append('\n');
/*     */     } 
/*     */ 
/*     */     
/* 114 */     return result.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InstructionList getInstructionList() {
/* 121 */     return this._instructionList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getPriority() {
/* 130 */     Template template = (this._patterns.size() == 0) ? this._default : ((Pattern)this._patterns.elementAt(0)).getTemplate();
/*     */     
/* 132 */     return template.getPriority();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPosition() {
/* 140 */     Template template = (this._patterns.size() == 0) ? this._default : ((Pattern)this._patterns.elementAt(0)).getTemplate();
/*     */     
/* 142 */     return template.getPosition();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reduce() {
/* 151 */     Vector newPatterns = new Vector();
/*     */     
/* 153 */     int count = this._patterns.size();
/* 154 */     for (int i = 0; i < count; i++) {
/* 155 */       LocationPathPattern pattern = this._patterns.elementAt(i);
/*     */ 
/*     */ 
/*     */       
/* 159 */       pattern.reduceKernelPattern();
/*     */ 
/*     */       
/* 162 */       if (pattern.isWildcard()) {
/* 163 */         this._default = pattern.getTemplate();
/*     */         
/*     */         break;
/*     */       } 
/* 167 */       newPatterns.addElement(pattern);
/*     */     } 
/*     */     
/* 170 */     this._patterns = newPatterns;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void findTemplates(Dictionary templates) {
/* 179 */     if (this._default != null) {
/* 180 */       templates.put(this._default, this);
/*     */     }
/* 182 */     for (int i = 0; i < this._patterns.size(); i++) {
/* 183 */       LocationPathPattern pattern = this._patterns.elementAt(i);
/*     */       
/* 185 */       templates.put(pattern.getTemplate(), this);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private InstructionHandle getTemplateHandle(Template template) {
/* 196 */     return this._mode.getTemplateInstructionHandle(template);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private LocationPathPattern getPattern(int n) {
/* 203 */     return this._patterns.elementAt(n);
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
/*     */   public InstructionHandle compile(ClassGenerator classGen, MethodGenerator methodGen, InstructionHandle continuation) {
/* 217 */     if (this._start != null) {
/* 218 */       return this._start;
/*     */     }
/*     */ 
/*     */     
/* 222 */     int count = this._patterns.size();
/* 223 */     if (count == 0) {
/* 224 */       return this._start = getTemplateHandle(this._default);
/*     */     }
/*     */ 
/*     */     
/* 228 */     InstructionHandle fail = (this._default == null) ? continuation : getTemplateHandle(this._default);
/*     */ 
/*     */ 
/*     */     
/* 232 */     for (int n = count - 1; n >= 0; n--) {
/* 233 */       LocationPathPattern pattern = getPattern(n);
/* 234 */       Template template = pattern.getTemplate();
/* 235 */       InstructionList il = new InstructionList();
/*     */ 
/*     */       
/* 238 */       il.append(methodGen.loadCurrentNode());
/*     */ 
/*     */       
/* 241 */       InstructionList ilist = this._mode.getInstructionList(pattern);
/* 242 */       if (ilist == null) {
/* 243 */         ilist = pattern.compile(classGen, methodGen);
/* 244 */         this._mode.addInstructionList(pattern, ilist);
/*     */       } 
/*     */ 
/*     */       
/* 248 */       InstructionList copyOfilist = ilist.copy();
/*     */       
/* 250 */       FlowList trueList = pattern.getTrueList();
/* 251 */       if (trueList != null) {
/* 252 */         trueList = trueList.copyAndRedirect(ilist, copyOfilist);
/*     */       }
/* 254 */       FlowList falseList = pattern.getFalseList();
/* 255 */       if (falseList != null) {
/* 256 */         falseList = falseList.copyAndRedirect(ilist, copyOfilist);
/*     */       }
/*     */       
/* 259 */       il.append(copyOfilist);
/*     */ 
/*     */       
/* 262 */       InstructionHandle gtmpl = getTemplateHandle(template);
/* 263 */       BranchHandle branchHandle = il.append((BranchInstruction)new GOTO_W(gtmpl));
/*     */       
/* 265 */       if (trueList != null) {
/* 266 */         trueList.backPatch((InstructionHandle)branchHandle);
/*     */       }
/* 268 */       if (falseList != null) {
/* 269 */         falseList.backPatch(fail);
/*     */       }
/*     */ 
/*     */       
/* 273 */       fail = il.getStart();
/*     */ 
/*     */       
/* 276 */       if (this._instructionList != null) {
/* 277 */         il.append(this._instructionList);
/*     */       }
/*     */ 
/*     */       
/* 281 */       this._instructionList = il;
/*     */     } 
/* 283 */     return this._start = fail;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/TestSeq.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */