/*    */ package org.apache.pdfbox.pdmodel.common.function;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.apache.pdfbox.cos.COSBase;
/*    */ import org.apache.pdfbox.pdmodel.common.PDRange;
/*    */ import org.apache.pdfbox.pdmodel.common.function.type4.ExecutionContext;
/*    */ import org.apache.pdfbox.pdmodel.common.function.type4.InstructionSequence;
/*    */ import org.apache.pdfbox.pdmodel.common.function.type4.InstructionSequenceBuilder;
/*    */ import org.apache.pdfbox.pdmodel.common.function.type4.Operators;
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
/*    */ 
/*    */ public class PDFunctionType4
/*    */   extends PDFunction
/*    */ {
/* 37 */   private static final Operators OPERATORS = new Operators();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private final InstructionSequence instructions;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PDFunctionType4(COSBase functionStream) throws IOException {
/* 49 */     super(functionStream);
/* 50 */     byte[] bytes = getPDStream().toByteArray();
/* 51 */     String string = new String(bytes, "ISO-8859-1");
/* 52 */     this.instructions = InstructionSequenceBuilder.parse(string);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getFunctionType() {
/* 60 */     return 4;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float[] eval(float[] input) throws IOException {
/* 69 */     ExecutionContext context = new ExecutionContext(OPERATORS);
/* 70 */     for (int i = 0; i < input.length; i++) {
/*    */       
/* 72 */       PDRange domain = getDomainForInput(i);
/* 73 */       float value = clipToRange(input[i], domain.getMin(), domain.getMax());
/* 74 */       context.getStack().push(Float.valueOf(value));
/*    */     } 
/*    */ 
/*    */     
/* 78 */     this.instructions.execute(context);
/*    */ 
/*    */     
/* 81 */     int numberOfOutputValues = getNumberOfOutputParameters();
/* 82 */     int numberOfActualOutputValues = context.getStack().size();
/* 83 */     if (numberOfActualOutputValues < numberOfOutputValues)
/*    */     {
/* 85 */       throw new IllegalStateException("The type 4 function returned " + numberOfActualOutputValues + " values but the Range entry indicates that " + numberOfOutputValues + " values be returned.");
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 90 */     float[] outputValues = new float[numberOfOutputValues];
/* 91 */     for (int j = numberOfOutputValues - 1; j >= 0; j--) {
/*    */       
/* 93 */       PDRange range = getRangeForOutput(j);
/* 94 */       outputValues[j] = context.popReal();
/* 95 */       outputValues[j] = clipToRange(outputValues[j], range.getMin(), range.getMax());
/*    */     } 
/*    */ 
/*    */     
/* 99 */     return outputValues;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/common/function/PDFunctionType4.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */