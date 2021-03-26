/*    */ package org.apache.batik.ext.awt.image.renderable;
/*    */ 
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.RenderContext;
/*    */ import java.util.Map;
/*    */ import org.apache.batik.ext.awt.image.GraphicsUtil;
/*    */ import org.apache.batik.ext.awt.image.rendered.CachableRed;
/*    */ import org.apache.batik.ext.awt.image.rendered.ProfileRed;
/*    */ import org.apache.xmlgraphics.java2d.color.ICCColorSpaceWithIntent;
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
/*    */ public class ProfileRable
/*    */   extends AbstractRable
/*    */ {
/*    */   private ICCColorSpaceWithIntent colorSpace;
/*    */   
/*    */   public ProfileRable(Filter src, ICCColorSpaceWithIntent colorSpace) {
/* 46 */     super(src);
/* 47 */     this.colorSpace = colorSpace;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSource(Filter src) {
/* 54 */     init(src, (Map)null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Filter getSource() {
/* 61 */     return getSources().get(0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setColorSpace(ICCColorSpaceWithIntent colorSpace) {
/* 68 */     touch();
/* 69 */     this.colorSpace = colorSpace;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ICCColorSpaceWithIntent getColorSpace() {
/* 76 */     return this.colorSpace;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RenderedImage createRendering(RenderContext rc) {
/* 83 */     RenderedImage srcRI = getSource().createRendering(rc);
/*    */     
/* 85 */     if (srcRI == null) {
/* 86 */       return null;
/*    */     }
/* 88 */     CachableRed srcCR = GraphicsUtil.wrap(srcRI);
/* 89 */     return (RenderedImage)new ProfileRed(srcCR, this.colorSpace);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/ProfileRable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */