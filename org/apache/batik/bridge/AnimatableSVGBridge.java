/*    */ package org.apache.batik.bridge;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.LinkedList;
/*    */ import org.apache.batik.anim.dom.AnimationTarget;
/*    */ import org.apache.batik.anim.dom.AnimationTargetListener;
/*    */ import org.apache.batik.anim.dom.SVGAnimationTargetContext;
/*    */ import org.w3c.dom.Element;
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
/*    */ public abstract class AnimatableSVGBridge
/*    */   extends AbstractSVGBridge
/*    */   implements SVGAnimationTargetContext
/*    */ {
/*    */   protected Element e;
/*    */   protected BridgeContext ctx;
/*    */   protected HashMap targetListeners;
/*    */   
/*    */   public void addTargetListener(String pn, AnimationTargetListener l) {
/* 62 */     if (this.targetListeners == null) {
/* 63 */       this.targetListeners = new HashMap<Object, Object>();
/*    */     }
/* 65 */     LinkedList<AnimationTargetListener> ll = (LinkedList)this.targetListeners.get(pn);
/* 66 */     if (ll == null) {
/* 67 */       ll = new LinkedList();
/* 68 */       this.targetListeners.put(pn, ll);
/*    */     } 
/* 70 */     ll.add(l);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void removeTargetListener(String pn, AnimationTargetListener l) {
/* 77 */     LinkedList ll = (LinkedList)this.targetListeners.get(pn);
/* 78 */     ll.remove(l);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void fireBaseAttributeListeners(String pn) {
/* 86 */     if (this.targetListeners != null) {
/* 87 */       LinkedList ll = (LinkedList)this.targetListeners.get(pn);
/* 88 */       if (ll != null)
/* 89 */         for (Object aLl : ll) {
/* 90 */           AnimationTargetListener l = (AnimationTargetListener)aLl;
/*    */           
/* 92 */           l.baseValueChanged((AnimationTarget)this.e, null, pn, true);
/*    */         }  
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/AnimatableSVGBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */