/*    */ package org.apache.batik.bridge;
/*    */ 
/*    */ import org.apache.batik.anim.AbstractAnimation;
/*    */ import org.apache.batik.anim.SetAnimation;
/*    */ import org.apache.batik.anim.dom.AnimationTarget;
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
/*    */ public class SVGSetElementBridge
/*    */   extends SVGAnimationElementBridge
/*    */ {
/*    */   public String getLocalName() {
/* 38 */     return "set";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Bridge getInstance() {
/* 45 */     return new SVGSetElementBridge();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected AbstractAnimation createAnimation(AnimationTarget target) {
/* 52 */     AnimatableValue to = parseAnimatableValue("to");
/* 53 */     return (AbstractAnimation)new SetAnimation(this.timedElement, this, to);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean canAnimateType(int type) {
/* 62 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean isConstantAnimation() {
/* 69 */     return true;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGSetElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */