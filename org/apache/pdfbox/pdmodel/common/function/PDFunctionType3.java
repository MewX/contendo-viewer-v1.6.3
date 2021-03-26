/*     */ package org.apache.pdfbox.pdmodel.common.function;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRange;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDFunctionType3
/*     */   extends PDFunction
/*     */ {
/*  33 */   private COSArray functions = null;
/*  34 */   private COSArray encode = null;
/*  35 */   private COSArray bounds = null;
/*  36 */   private PDFunction[] functionsArray = null;
/*  37 */   private float[] boundsValues = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFunctionType3(COSBase functionStream) {
/*  46 */     super(functionStream);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFunctionType() {
/*  55 */     return 3;
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
/*     */   public float[] eval(float[] input) throws IOException {
/*  67 */     PDFunction function = null;
/*  68 */     float x = input[0];
/*  69 */     PDRange domain = getDomainForInput(0);
/*     */     
/*  71 */     x = clipToRange(x, domain.getMin(), domain.getMax());
/*     */     
/*  73 */     if (this.functionsArray == null) {
/*     */       
/*  75 */       COSArray ar = getFunctions();
/*  76 */       this.functionsArray = new PDFunction[ar.size()];
/*  77 */       for (int i = 0; i < ar.size(); i++)
/*     */       {
/*  79 */         this.functionsArray[i] = PDFunction.create(ar.getObject(i));
/*     */       }
/*     */     } 
/*     */     
/*  83 */     if (this.functionsArray.length == 1) {
/*     */ 
/*     */       
/*  86 */       function = this.functionsArray[0];
/*  87 */       PDRange encRange = getEncodeForParameter(0);
/*  88 */       x = interpolate(x, domain.getMin(), domain.getMax(), encRange.getMin(), encRange.getMax());
/*     */     }
/*     */     else {
/*     */       
/*  92 */       if (this.boundsValues == null)
/*     */       {
/*  94 */         this.boundsValues = getBounds().toFloatArray();
/*     */       }
/*  96 */       int boundsSize = this.boundsValues.length;
/*     */ 
/*     */       
/*  99 */       float[] partitionValues = new float[boundsSize + 2];
/* 100 */       int partitionValuesSize = partitionValues.length;
/* 101 */       partitionValues[0] = domain.getMin();
/* 102 */       partitionValues[partitionValuesSize - 1] = domain.getMax();
/* 103 */       System.arraycopy(this.boundsValues, 0, partitionValues, 1, boundsSize);
/*     */       
/* 105 */       for (int i = 0; i < partitionValuesSize - 1; i++) {
/*     */         
/* 107 */         if (x >= partitionValues[i] && (x < partitionValues[i + 1] || (i == partitionValuesSize - 2 && x == partitionValues[i + 1]))) {
/*     */ 
/*     */           
/* 110 */           function = this.functionsArray[i];
/* 111 */           PDRange encRange = getEncodeForParameter(i);
/* 112 */           x = interpolate(x, partitionValues[i], partitionValues[i + 1], encRange.getMin(), encRange.getMax());
/*     */           break;
/*     */         } 
/*     */       } 
/* 116 */       if (function == null)
/*     */       {
/* 118 */         throw new IOException("partition not found in type 3 function");
/*     */       }
/*     */     } 
/* 121 */     float[] functionValues = { x };
/*     */     
/* 123 */     float[] functionResult = function.eval(functionValues);
/*     */     
/* 125 */     return clipToRange(functionResult);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSArray getFunctions() {
/* 135 */     if (this.functions == null)
/*     */     {
/* 137 */       this.functions = (COSArray)getCOSObject().getDictionaryObject(COSName.FUNCTIONS);
/*     */     }
/* 139 */     return this.functions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSArray getBounds() {
/* 149 */     if (this.bounds == null)
/*     */     {
/* 151 */       this.bounds = (COSArray)getCOSObject().getDictionaryObject(COSName.BOUNDS);
/*     */     }
/* 153 */     return this.bounds;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSArray getEncode() {
/* 163 */     if (this.encode == null)
/*     */     {
/* 165 */       this.encode = (COSArray)getCOSObject().getDictionaryObject(COSName.ENCODE);
/*     */     }
/* 167 */     return this.encode;
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
/*     */   private PDRange getEncodeForParameter(int n) {
/* 179 */     COSArray encodeValues = getEncode();
/* 180 */     return new PDRange(encodeValues, n);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/common/function/PDFunctionType3.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */