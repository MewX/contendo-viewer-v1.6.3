/*     */ package org.apache.batik.ext.awt.image.renderable;
/*     */ 
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.RenderContext;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.ext.awt.image.ComponentTransferFunction;
/*     */ import org.apache.batik.ext.awt.image.DiscreteTransfer;
/*     */ import org.apache.batik.ext.awt.image.GammaTransfer;
/*     */ import org.apache.batik.ext.awt.image.IdentityTransfer;
/*     */ import org.apache.batik.ext.awt.image.LinearTransfer;
/*     */ import org.apache.batik.ext.awt.image.TableTransfer;
/*     */ import org.apache.batik.ext.awt.image.TransferFunction;
/*     */ import org.apache.batik.ext.awt.image.rendered.ComponentTransferRed;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ComponentTransferRable8Bit
/*     */   extends AbstractColorInterpolationRable
/*     */   implements ComponentTransferRable
/*     */ {
/*     */   public static final int ALPHA = 0;
/*     */   public static final int RED = 1;
/*     */   public static final int GREEN = 2;
/*     */   public static final int BLUE = 3;
/*  53 */   private ComponentTransferFunction[] functions = new ComponentTransferFunction[4];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   private TransferFunction[] txfFunc = new TransferFunction[4];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComponentTransferRable8Bit(Filter src, ComponentTransferFunction alphaFunction, ComponentTransferFunction redFunction, ComponentTransferFunction greenFunction, ComponentTransferFunction blueFunction) {
/*  68 */     super(src, (Map)null);
/*  69 */     setAlphaFunction(alphaFunction);
/*  70 */     setRedFunction(redFunction);
/*  71 */     setGreenFunction(greenFunction);
/*  72 */     setBlueFunction(blueFunction);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSource(Filter src) {
/*  79 */     init(src, (Map)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Filter getSource() {
/*  86 */     return getSources().get(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComponentTransferFunction getAlphaFunction() {
/*  93 */     return this.functions[0];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAlphaFunction(ComponentTransferFunction alphaFunction) {
/* 100 */     touch();
/* 101 */     this.functions[0] = alphaFunction;
/* 102 */     this.txfFunc[0] = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComponentTransferFunction getRedFunction() {
/* 109 */     return this.functions[1];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRedFunction(ComponentTransferFunction redFunction) {
/* 116 */     touch();
/* 117 */     this.functions[1] = redFunction;
/* 118 */     this.txfFunc[1] = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComponentTransferFunction getGreenFunction() {
/* 125 */     return this.functions[2];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGreenFunction(ComponentTransferFunction greenFunction) {
/* 132 */     touch();
/* 133 */     this.functions[2] = greenFunction;
/* 134 */     this.txfFunc[2] = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComponentTransferFunction getBlueFunction() {
/* 141 */     return this.functions[3];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlueFunction(ComponentTransferFunction blueFunction) {
/* 148 */     touch();
/* 149 */     this.functions[3] = blueFunction;
/* 150 */     this.txfFunc[3] = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RenderedImage createRendering(RenderContext rc) {
/* 157 */     RenderedImage srcRI = getSource().createRendering(rc);
/*     */     
/* 159 */     if (srcRI == null) {
/* 160 */       return null;
/*     */     }
/* 162 */     return (RenderedImage)new ComponentTransferRed(convertSourceCS(srcRI), getTransferFunctions(), rc.getRenderingHints());
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
/*     */   private TransferFunction[] getTransferFunctions() {
/* 176 */     TransferFunction[] txfFunc = new TransferFunction[4];
/* 177 */     System.arraycopy(this.txfFunc, 0, txfFunc, 0, 4);
/*     */ 
/*     */     
/* 180 */     ComponentTransferFunction[] functions = new ComponentTransferFunction[4];
/* 181 */     System.arraycopy(this.functions, 0, functions, 0, 4);
/*     */     
/* 183 */     for (int i = 0; i < 4; i++) {
/* 184 */       if (txfFunc[i] == null) {
/* 185 */         txfFunc[i] = getTransferFunction(functions[i]);
/* 186 */         synchronized (this.functions) {
/* 187 */           if (this.functions[i] == functions[i]) {
/* 188 */             this.txfFunc[i] = txfFunc[i];
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 194 */     return txfFunc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static TransferFunction getTransferFunction(ComponentTransferFunction function) {
/*     */     GammaTransfer gammaTransfer;
/* 203 */     TransferFunction txfFunc = null;
/* 204 */     if (function == null)
/* 205 */     { IdentityTransfer identityTransfer = new IdentityTransfer(); }
/*     */     else
/*     */     { IdentityTransfer identityTransfer; TableTransfer tableTransfer; DiscreteTransfer discreteTransfer;
/* 208 */       switch (function.getType())
/*     */       { case 0:
/* 210 */           return (TransferFunction)new IdentityTransfer();
/*     */         
/*     */         case 1:
/* 213 */           return (TransferFunction)new TableTransfer(tableFloatToInt(function.getTableValues()));
/*     */         
/*     */         case 2:
/* 216 */           return (TransferFunction)new DiscreteTransfer(tableFloatToInt(function.getTableValues()));
/*     */         
/*     */         case 3:
/* 219 */           return (TransferFunction)new LinearTransfer(function.getSlope(), function.getIntercept());
/*     */ 
/*     */         
/*     */         case 4:
/* 223 */           gammaTransfer = new GammaTransfer(function.getAmplitude(), function.getExponent(), function.getOffset());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 233 */           return (TransferFunction)gammaTransfer; }  throw new RuntimeException(); }  return (TransferFunction)gammaTransfer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int[] tableFloatToInt(float[] tableValues) {
/* 240 */     int[] values = new int[tableValues.length];
/* 241 */     for (int i = 0; i < tableValues.length; i++) {
/* 242 */       values[i] = (int)(tableValues[i] * 255.0F);
/*     */     }
/*     */     
/* 245 */     return values;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/ComponentTransferRable8Bit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */