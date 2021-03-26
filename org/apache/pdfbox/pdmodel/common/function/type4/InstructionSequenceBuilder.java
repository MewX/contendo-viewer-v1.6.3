/*     */ package org.apache.pdfbox.pdmodel.common.function.type4;
/*     */ 
/*     */ import java.util.Stack;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class InstructionSequenceBuilder
/*     */   extends Parser.AbstractSyntaxHandler
/*     */ {
/*  30 */   private final InstructionSequence mainSequence = new InstructionSequence();
/*  31 */   private final Stack<InstructionSequence> seqStack = new Stack<InstructionSequence>();
/*     */ 
/*     */   
/*     */   private InstructionSequenceBuilder() {
/*  35 */     this.seqStack.push(this.mainSequence);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InstructionSequence getInstructionSequence() {
/*  44 */     return this.mainSequence;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static InstructionSequence parse(CharSequence text) {
/*  55 */     InstructionSequenceBuilder builder = new InstructionSequenceBuilder();
/*  56 */     Parser.parse(text, builder);
/*  57 */     return builder.getInstructionSequence();
/*     */   }
/*     */ 
/*     */   
/*     */   private InstructionSequence getCurrentSequence() {
/*  62 */     return this.seqStack.peek();
/*     */   }
/*     */   
/*  65 */   private static final Pattern INTEGER_PATTERN = Pattern.compile("[\\+\\-]?\\d+");
/*  66 */   private static final Pattern REAL_PATTERN = Pattern.compile("[\\-]?\\d*\\.\\d*([Ee]\\-?\\d+)?");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void token(CharSequence text) {
/*  72 */     String token = text.toString();
/*  73 */     token(token);
/*     */   }
/*     */ 
/*     */   
/*     */   private void token(String token) {
/*  78 */     if ("{".equals(token)) {
/*     */       
/*  80 */       InstructionSequence child = new InstructionSequence();
/*  81 */       getCurrentSequence().addProc(child);
/*  82 */       this.seqStack.push(child);
/*     */     }
/*  84 */     else if ("}".equals(token)) {
/*     */       
/*  86 */       this.seqStack.pop();
/*     */     }
/*     */     else {
/*     */       
/*  90 */       Matcher m = INTEGER_PATTERN.matcher(token);
/*  91 */       if (m.matches()) {
/*     */         
/*  93 */         getCurrentSequence().addInteger(parseInt(token));
/*     */         
/*     */         return;
/*     */       } 
/*  97 */       m = REAL_PATTERN.matcher(token);
/*  98 */       if (m.matches()) {
/*     */         
/* 100 */         getCurrentSequence().addReal(parseReal(token));
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 106 */       getCurrentSequence().addName(token);
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
/*     */   public static int parseInt(String token) {
/* 118 */     return Integer.parseInt(token.startsWith("+") ? token.substring(1) : token);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float parseReal(String token) {
/* 128 */     return Float.parseFloat(token);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/common/function/type4/InstructionSequenceBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */