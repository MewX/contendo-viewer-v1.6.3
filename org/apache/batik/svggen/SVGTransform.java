/*     */ package org.apache.batik.svggen;
/*     */ 
/*     */ import java.util.Stack;
/*     */ import org.apache.batik.ext.awt.g2d.GraphicContext;
/*     */ import org.apache.batik.ext.awt.g2d.TransformStackElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGTransform
/*     */   extends AbstractSVGConverter
/*     */ {
/*  39 */   private static double radiansToDegrees = 57.29577951308232D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGTransform(SVGGeneratorContext generatorContext) {
/*  46 */     super(generatorContext);
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
/*     */   public SVGDescriptor toSVG(GraphicContext gc) {
/*  60 */     return new SVGTransformDescriptor(toSVGTransform(gc));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String toSVGTransform(GraphicContext gc) {
/*  70 */     return toSVGTransform(gc.getTransformStack());
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
/*     */   public final String toSVGTransform(TransformStackElement[] transformStack) {
/*  84 */     int nTransforms = transformStack.length;
/*     */ 
/*     */ 
/*     */     
/*  88 */     Stack<TransformStackElement> presentation = new Stack()
/*     */       {
/*     */         public Object push(Object o)
/*     */         {
/*     */           Object element;
/*     */           
/*  94 */           if (((TransformStackElement)o).isIdentity()) {
/*     */ 
/*     */             
/*  97 */             element = pop();
/*     */           }
/*     */           else {
/*     */             
/* 101 */             super.push(o);
/* 102 */             element = null;
/*     */           } 
/* 104 */           return element;
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         public Object pop() {
/* 111 */           Object element = null;
/* 112 */           if (!empty()) {
/* 113 */             element = super.pop();
/*     */           }
/* 115 */           return element;
/*     */         }
/*     */       };
/* 118 */     boolean canConcatenate = false;
/* 119 */     int i = 0, j = 0, next = 0;
/* 120 */     TransformStackElement element = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 130 */     while (i < nTransforms) {
/*     */ 
/*     */ 
/*     */       
/* 134 */       next = i;
/* 135 */       if (element == null) {
/* 136 */         element = (TransformStackElement)transformStack[i].clone();
/* 137 */         next++;
/*     */       } 
/*     */ 
/*     */       
/* 141 */       canConcatenate = true;
/* 142 */       for (j = next; j < nTransforms; j++) {
/* 143 */         canConcatenate = element.concatenate(transformStack[j]);
/* 144 */         if (!canConcatenate) {
/*     */           break;
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 156 */       i = j;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 162 */       element = presentation.push(element);
/*     */     } 
/*     */ 
/*     */     
/* 166 */     if (element != null) {
/* 167 */       presentation.push(element);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 173 */     int nPresentations = presentation.size();
/*     */     
/* 175 */     StringBuffer transformStackBuffer = new StringBuffer(nPresentations * 8);
/* 176 */     for (i = 0; i < nPresentations; i++) {
/* 177 */       transformStackBuffer.append(convertTransform(presentation.get(i)));
/* 178 */       transformStackBuffer.append(" ");
/*     */     } 
/*     */     
/* 181 */     String transformValue = transformStackBuffer.toString().trim();
/* 182 */     return transformValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final String convertTransform(TransformStackElement transformElement) {
/* 189 */     StringBuffer transformString = new StringBuffer();
/* 190 */     double[] transformParameters = transformElement.getTransformParameters();
/* 191 */     switch (transformElement.getType().toInt()) {
/*     */       case 0:
/* 193 */         if (!transformElement.isIdentity()) {
/* 194 */           transformString.append("translate");
/* 195 */           transformString.append("(");
/* 196 */           transformString.append(doubleString(transformParameters[0]));
/* 197 */           transformString.append(",");
/* 198 */           transformString.append(doubleString(transformParameters[1]));
/* 199 */           transformString.append(")");
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 262 */         return transformString.toString();case 1: if (!transformElement.isIdentity()) { transformString.append("rotate"); transformString.append("("); transformString.append(doubleString(radiansToDegrees * transformParameters[0])); transformString.append(")"); }  return transformString.toString();case 2: if (!transformElement.isIdentity()) { transformString.append("scale"); transformString.append("("); transformString.append(doubleString(transformParameters[0])); transformString.append(","); transformString.append(doubleString(transformParameters[1])); transformString.append(")"); }  return transformString.toString();case 3: if (!transformElement.isIdentity()) { transformString.append("matrix"); transformString.append("("); transformString.append(1); transformString.append(","); transformString.append(doubleString(transformParameters[1])); transformString.append(","); transformString.append(doubleString(transformParameters[0])); transformString.append(","); transformString.append(1); transformString.append(","); transformString.append(0); transformString.append(","); transformString.append(0); transformString.append(")"); }  return transformString.toString();case 4: if (!transformElement.isIdentity()) { transformString.append("matrix"); transformString.append("("); transformString.append(doubleString(transformParameters[0])); transformString.append(","); transformString.append(doubleString(transformParameters[1])); transformString.append(","); transformString.append(doubleString(transformParameters[2])); transformString.append(","); transformString.append(doubleString(transformParameters[3])); transformString.append(","); transformString.append(doubleString(transformParameters[4])); transformString.append(","); transformString.append(doubleString(transformParameters[5])); transformString.append(")"); }  return transformString.toString();
/*     */     } 
/*     */     throw new RuntimeException();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */