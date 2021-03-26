/*    */ package org.apache.batik.bridge;
/*    */ 
/*    */ import org.apache.batik.anim.AbstractAnimation;
/*    */ import org.apache.batik.anim.ColorAnimation;
/*    */ import org.apache.batik.anim.dom.AnimationTarget;
/*    */ import org.apache.batik.anim.values.AnimatablePaintValue;
/*    */ import org.apache.batik.anim.values.AnimatableValue;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SVGAnimateColorElementBridge
/*    */   extends SVGAnimateElementBridge
/*    */ {
/*    */   public String getLocalName() {
/* 41 */     return "animateColor";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Bridge getInstance() {
/* 48 */     return new SVGAnimateColorElementBridge();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected AbstractAnimation createAnimation(AnimationTarget target) {
/* 55 */     AnimatableValue from = parseAnimatableValue("from");
/* 56 */     AnimatableValue to = parseAnimatableValue("to");
/* 57 */     AnimatableValue by = parseAnimatableValue("by");
/* 58 */     return (AbstractAnimation)new ColorAnimation(this.timedElement, this, parseCalcMode(), parseKeyTimes(), parseKeySplines(), parseAdditive(), parseAccumulate(), parseValues(), from, to, by);
/*    */   }
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
/*    */   protected boolean canAnimateType(int type) {
/* 77 */     return (type == 6 || type == 7);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean checkValueType(AnimatableValue v) {
/* 85 */     if (v instanceof AnimatablePaintValue) {
/* 86 */       return (((AnimatablePaintValue)v).getPaintType() == 2);
/*    */     }
/*    */     
/* 89 */     return v instanceof org.apache.batik.anim.values.AnimatableColorValue;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGAnimateColorElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */