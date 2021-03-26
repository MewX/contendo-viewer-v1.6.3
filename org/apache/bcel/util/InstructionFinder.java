/*     */ package org.apache.bcel.util;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import org.apache.bcel.Constants;
/*     */ import org.apache.bcel.generic.ClassGenException;
/*     */ import org.apache.bcel.generic.InstructionHandle;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.regexp.RE;
/*     */ import org.apache.regexp.RESyntaxException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InstructionFinder
/*     */ {
/*     */   private static final int OFFSET = 32767;
/*     */   private static final int NO_OPCODES = 256;
/*  93 */   private static final HashMap map = new HashMap();
/*     */   
/*     */   private InstructionList il;
/*     */   
/*     */   private String il_string;
/*     */   
/*     */   private InstructionHandle[] handles;
/*     */ 
/*     */   
/*     */   public InstructionFinder(InstructionList il) {
/* 103 */     this.il = il;
/* 104 */     reread();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void reread() {
/* 111 */     int size = this.il.getLength();
/* 112 */     char[] buf = new char[size];
/* 113 */     this.handles = this.il.getInstructionHandles();
/*     */ 
/*     */     
/* 116 */     for (int i = 0; i < size; i++) {
/* 117 */       buf[i] = makeChar(this.handles[i].getInstruction().getOpcode());
/*     */     }
/* 119 */     this.il_string = new String(buf);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String mapName(String pattern) {
/* 129 */     String result = (String)map.get(pattern);
/*     */     
/* 131 */     if (result != null) {
/* 132 */       return result;
/*     */     }
/* 134 */     for (short i = 0; i < 256; i = (short)(i + 1)) {
/* 135 */       if (pattern.equals(Constants.OPCODE_NAMES[i]))
/* 136 */         return "" + makeChar(i); 
/*     */     } 
/* 138 */     throw new RuntimeException("Instruction unknown: " + pattern);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String compilePattern(String pattern) {
/* 149 */     String lower = pattern.toLowerCase();
/* 150 */     StringBuffer buf = new StringBuffer();
/* 151 */     int size = pattern.length();
/*     */     
/* 153 */     for (int i = 0; i < size; i++) {
/* 154 */       char ch = lower.charAt(i);
/*     */       
/* 156 */       if (Character.isLetterOrDigit(ch)) {
/* 157 */         StringBuffer name = new StringBuffer();
/*     */         
/* 159 */         while ((Character.isLetterOrDigit(ch) || ch == '_') && i < size) {
/* 160 */           name.append(ch);
/*     */           
/* 162 */           if (++i < size) {
/* 163 */             ch = lower.charAt(i);
/*     */             continue;
/*     */           } 
/*     */           break;
/*     */         } 
/* 168 */         i--;
/*     */         
/* 170 */         buf.append(mapName(name.toString()));
/* 171 */       } else if (!Character.isWhitespace(ch)) {
/* 172 */         buf.append(ch);
/*     */       } 
/*     */     } 
/* 175 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private InstructionHandle[] getMatch(int matched_from, int match_length) {
/* 182 */     InstructionHandle[] match = new InstructionHandle[match_length];
/* 183 */     System.arraycopy(this.handles, matched_from, match, 0, match_length);
/*     */     
/* 185 */     return match;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final Iterator search(String pattern, InstructionHandle from, CodeConstraint constraint) {
/* 218 */     String search = compilePattern(pattern);
/* 219 */     int start = -1;
/*     */     
/* 221 */     for (int i = 0; i < this.handles.length; i++) {
/* 222 */       if (this.handles[i] == from) {
/* 223 */         start = i;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 228 */     if (start == -1) {
/* 229 */       throw new ClassGenException("Instruction handle " + from + " not found in instruction list.");
/*     */     }
/*     */     try {
/* 232 */       RE regex = new RE(search);
/* 233 */       ArrayList matches = new ArrayList();
/*     */       
/* 235 */       while (start < this.il_string.length() && regex.match(this.il_string, start)) {
/* 236 */         int startExpr = regex.getParenStart(0);
/* 237 */         int endExpr = regex.getParenEnd(0);
/* 238 */         int lenExpr = regex.getParenLength(0);
/*     */         
/* 240 */         InstructionHandle[] match = getMatch(startExpr, lenExpr);
/*     */         
/* 242 */         if (constraint == null || constraint.checkCode(match))
/* 243 */           matches.add(match); 
/* 244 */         start = endExpr;
/*     */       } 
/*     */       
/* 247 */       return matches.iterator();
/*     */     } catch (RESyntaxException e) {
/* 249 */       System.err.println(e);
/*     */ 
/*     */       
/* 252 */       return null;
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
/*     */   public final Iterator search(String pattern) {
/* 264 */     return search(pattern, this.il.getStart(), null);
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
/*     */   public final Iterator search(String pattern, InstructionHandle from) {
/* 276 */     return search(pattern, from, null);
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
/*     */   public final Iterator search(String pattern, CodeConstraint constraint) {
/* 288 */     return search(pattern, this.il.getStart(), constraint);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final char makeChar(short opcode) {
/* 295 */     return (char)(opcode + 32767);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final InstructionList getInstructionList() {
/* 301 */     return this.il;
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
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/* 320 */     map.put("arithmeticinstruction", "(irem|lrem|iand|ior|ineg|isub|lneg|fneg|fmul|ldiv|fadd|lxor|frem|idiv|land|ixor|ishr|fsub|lshl|fdiv|iadd|lor|dmul|lsub|ishl|imul|lmul|lushr|dneg|iushr|lshr|ddiv|drem|dadd|ladd|dsub)");
/* 321 */     map.put("invokeinstruction", "(invokevirtual|invokeinterface|invokestatic|invokespecial)");
/* 322 */     map.put("arrayinstruction", "(baload|aastore|saload|caload|fastore|lastore|iaload|castore|iastore|aaload|bastore|sastore|faload|laload|daload|dastore)");
/* 323 */     map.put("gotoinstruction", "(goto|goto_w)");
/* 324 */     map.put("conversioninstruction", "(d2l|l2d|i2s|d2i|l2i|i2b|l2f|d2f|f2i|i2d|i2l|f2d|i2c|f2l|i2f)");
/* 325 */     map.put("localvariableinstruction", "(fstore|iinc|lload|dstore|dload|iload|aload|astore|istore|fload|lstore)");
/* 326 */     map.put("loadinstruction", "(fload|dload|lload|iload|aload)");
/* 327 */     map.put("fieldinstruction", "(getfield|putstatic|getstatic|putfield)");
/* 328 */     map.put("cpinstruction", "(ldc2_w|invokeinterface|multianewarray|putstatic|instanceof|getstatic|checkcast|getfield|invokespecial|ldc_w|invokestatic|invokevirtual|putfield|ldc|new|anewarray)");
/* 329 */     map.put("stackinstruction", "(dup2|swap|dup2_x2|pop|pop2|dup|dup2_x1|dup_x2|dup_x1)");
/* 330 */     map.put("branchinstruction", "(ifle|if_acmpne|if_icmpeq|if_acmpeq|ifnonnull|goto_w|iflt|ifnull|if_icmpne|tableswitch|if_icmple|ifeq|if_icmplt|jsr_w|if_icmpgt|ifgt|jsr|goto|ifne|ifge|lookupswitch|if_icmpge)");
/* 331 */     map.put("returninstruction", "(lreturn|ireturn|freturn|dreturn|areturn|return)");
/* 332 */     map.put("storeinstruction", "(istore|fstore|dstore|astore|lstore)");
/* 333 */     map.put("select", "(tableswitch|lookupswitch)");
/* 334 */     map.put("ifinstruction", "(ifeq|ifgt|if_icmpne|if_icmpeq|ifge|ifnull|ifne|if_icmple|if_icmpge|if_acmpeq|if_icmplt|if_acmpne|ifnonnull|iflt|if_icmpgt|ifle)");
/* 335 */     map.put("jsrinstruction", "(jsr|jsr_w)");
/* 336 */     map.put("variablelengthinstruction", "(tableswitch|jsr|goto|lookupswitch)");
/* 337 */     map.put("unconditionalbranch", "(goto|jsr|jsr_w|athrow|goto_w)");
/* 338 */     map.put("constantpushinstruction", "(dconst|bipush|sipush|fconst|iconst|lconst)");
/* 339 */     map.put("typedinstruction", "(imul|lsub|aload|fload|lor|new|aaload|fcmpg|iand|iaload|lrem|idiv|d2l|isub|dcmpg|dastore|ret|f2d|f2i|drem|iinc|i2c|checkcast|frem|lreturn|astore|lushr|daload|dneg|fastore|istore|lshl|ldiv|lstore|areturn|ishr|ldc_w|invokeinterface|aastore|lxor|ishl|l2d|i2f|return|faload|sipush|iushr|caload|instanceof|invokespecial|putfield|fmul|ireturn|laload|d2f|lneg|ixor|i2l|fdiv|lastore|multianewarray|i2b|getstatic|i2d|putstatic|fcmpl|saload|ladd|irem|dload|jsr_w|dconst|dcmpl|fsub|freturn|ldc|aconst_null|castore|lmul|ldc2_w|dadd|iconst|f2l|ddiv|dstore|land|jsr|anewarray|dmul|bipush|dsub|sastore|d2i|i2s|lshr|iadd|l2i|lload|bastore|fstore|fneg|iload|fadd|baload|fconst|ior|ineg|dreturn|l2f|lconst|getfield|invokevirtual|invokestatic|iastore)");
/* 340 */     map.put("popinstruction", "(fstore|dstore|pop|pop2|astore|putstatic|istore|lstore)");
/* 341 */     map.put("allocationinstruction", "(multianewarray|new|anewarray|newarray)");
/* 342 */     map.put("indexedinstruction", "(lload|lstore|fload|ldc2_w|invokeinterface|multianewarray|astore|dload|putstatic|instanceof|getstatic|checkcast|getfield|invokespecial|dstore|istore|iinc|ldc_w|ret|fstore|invokestatic|iload|putfield|invokevirtual|ldc|new|aload|anewarray)");
/* 343 */     map.put("pushinstruction", "(dup|lload|dup2|bipush|fload|ldc2_w|sipush|lconst|fconst|dload|getstatic|ldc_w|aconst_null|dconst|iload|ldc|iconst|aload)");
/* 344 */     map.put("stackproducer", "(imul|lsub|aload|fload|lor|new|aaload|fcmpg|iand|iaload|lrem|idiv|d2l|isub|dcmpg|dup|f2d|f2i|drem|i2c|checkcast|frem|lushr|daload|dneg|lshl|ldiv|ishr|ldc_w|invokeinterface|lxor|ishl|l2d|i2f|faload|sipush|iushr|caload|instanceof|invokespecial|fmul|laload|d2f|lneg|ixor|i2l|fdiv|getstatic|i2b|swap|i2d|dup2|fcmpl|saload|ladd|irem|dload|jsr_w|dconst|dcmpl|fsub|ldc|arraylength|aconst_null|tableswitch|lmul|ldc2_w|iconst|dadd|f2l|ddiv|land|jsr|anewarray|dmul|bipush|dsub|d2i|newarray|i2s|lshr|iadd|lload|l2i|fneg|iload|fadd|baload|fconst|lookupswitch|ior|ineg|lconst|l2f|getfield|invokevirtual|invokestatic)");
/* 345 */     map.put("stackconsumer", "(imul|lsub|lor|iflt|fcmpg|if_icmpgt|iand|ifeq|if_icmplt|lrem|ifnonnull|idiv|d2l|isub|dcmpg|dastore|if_icmpeq|f2d|f2i|drem|i2c|checkcast|frem|lreturn|astore|lushr|pop2|monitorexit|dneg|fastore|istore|lshl|ldiv|lstore|areturn|if_icmpge|ishr|monitorenter|invokeinterface|aastore|lxor|ishl|l2d|i2f|return|iushr|instanceof|invokespecial|fmul|ireturn|d2f|lneg|ixor|pop|i2l|ifnull|fdiv|lastore|i2b|if_acmpeq|ifge|swap|i2d|putstatic|fcmpl|ladd|irem|dcmpl|fsub|freturn|ifgt|castore|lmul|dadd|f2l|ddiv|dstore|land|if_icmpne|if_acmpne|dmul|dsub|sastore|ifle|d2i|i2s|lshr|iadd|l2i|bastore|fstore|fneg|fadd|ior|ineg|ifne|dreturn|l2f|if_icmple|getfield|invokevirtual|invokestatic|iastore)");
/* 346 */     map.put("exceptionthrower", "(irem|lrem|laload|putstatic|baload|dastore|areturn|getstatic|ldiv|anewarray|iastore|castore|idiv|saload|lastore|fastore|putfield|lreturn|caload|getfield|return|aastore|freturn|newarray|instanceof|multianewarray|athrow|faload|iaload|aaload|dreturn|monitorenter|checkcast|bastore|arraylength|new|invokevirtual|sastore|ldc_w|ireturn|invokespecial|monitorexit|invokeinterface|ldc|invokestatic|daload)");
/* 347 */     map.put("loadclass", "(multianewarray|invokeinterface|instanceof|invokespecial|putfield|checkcast|putstatic|invokevirtual|new|getstatic|invokestatic|getfield|anewarray)");
/* 348 */     map.put("instructiontargeter", "(ifle|if_acmpne|if_icmpeq|if_acmpeq|ifnonnull|goto_w|iflt|ifnull|if_icmpne|tableswitch|if_icmple|ifeq|if_icmplt|jsr_w|if_icmpgt|ifgt|jsr|goto|ifne|ifge|lookupswitch|if_icmpge)");
/*     */ 
/*     */     
/* 351 */     map.put("if_icmp", "(if_icmpne|if_icmpeq|if_icmple|if_icmpge|if_icmplt|if_icmpgt)");
/* 352 */     map.put("if_acmp", "(if_acmpeq|if_acmpne)");
/* 353 */     map.put("if", "(ifeq|ifne|iflt|ifge|ifgt|ifle)");
/*     */ 
/*     */     
/* 356 */     map.put("iconst", precompile((short)3, (short)8, (short)2));
/* 357 */     map.put("lconst", new String(new char[] { '(', makeChar((short)9), '|', makeChar((short)10), ')' }));
/*     */     
/* 359 */     map.put("dconst", new String(new char[] { '(', makeChar((short)14), '|', makeChar((short)15), ')' }));
/*     */     
/* 361 */     map.put("fconst", new String(new char[] { '(', makeChar((short)11), '|', makeChar((short)12), ')' }));
/*     */ 
/*     */     
/* 364 */     map.put("iload", precompile((short)26, (short)29, (short)21));
/* 365 */     map.put("dload", precompile((short)38, (short)41, (short)24));
/* 366 */     map.put("fload", precompile((short)34, (short)37, (short)23));
/* 367 */     map.put("aload", precompile((short)42, (short)45, (short)25));
/*     */     
/* 369 */     map.put("istore", precompile((short)59, (short)62, (short)54));
/* 370 */     map.put("dstore", precompile((short)71, (short)74, (short)57));
/* 371 */     map.put("fstore", precompile((short)67, (short)70, (short)56));
/* 372 */     map.put("astore", precompile((short)75, (short)78, (short)58));
/*     */ 
/*     */ 
/*     */     
/* 376 */     for (Iterator i = map.keySet().iterator(); i.hasNext(); ) {
/* 377 */       String key = i.next();
/* 378 */       String value = (String)map.get(key);
/*     */       
/* 380 */       char ch = value.charAt(1);
/* 381 */       if (ch < '翿') {
/* 382 */         map.put(key, compilePattern(value));
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 388 */     StringBuffer buf = new StringBuffer("(");
/*     */     
/* 390 */     for (short s = 0; s < 256; s = (short)(s + 1)) {
/* 391 */       if (Constants.NO_OF_OPERANDS[s] != -1) {
/* 392 */         buf.append(makeChar(s));
/*     */         
/* 394 */         if (s < 255)
/* 395 */           buf.append('|'); 
/*     */       } 
/*     */     } 
/* 398 */     buf.append(')');
/*     */     
/* 400 */     map.put("instruction", buf.toString());
/*     */   }
/*     */   
/*     */   private static String precompile(short from, short to, short extra) {
/* 404 */     StringBuffer buf = new StringBuffer("(");
/*     */     
/* 406 */     for (short i = from; i <= to; i = (short)(i + 1)) {
/* 407 */       buf.append(makeChar(i));
/* 408 */       buf.append('|');
/*     */     } 
/*     */     
/* 411 */     buf.append(makeChar(extra));
/* 412 */     buf.append(")");
/* 413 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String pattern2string(String pattern) {
/* 420 */     return pattern2string(pattern, true);
/*     */   }
/*     */   
/*     */   private static final String pattern2string(String pattern, boolean make_string) {
/* 424 */     StringBuffer buf = new StringBuffer();
/*     */     
/* 426 */     for (int i = 0; i < pattern.length(); i++) {
/* 427 */       char ch = pattern.charAt(i);
/*     */       
/* 429 */       if (ch >= '翿')
/* 430 */       { if (make_string) {
/* 431 */           buf.append(Constants.OPCODE_NAMES[ch - 32767]);
/*     */         } else {
/* 433 */           buf.append(ch - 32767);
/*     */         }  }
/* 435 */       else { buf.append(ch); }
/*     */     
/*     */     } 
/* 438 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public static interface CodeConstraint {
/*     */     boolean checkCode(InstructionHandle[] param1ArrayOfInstructionHandle);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/util/InstructionFinder.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */