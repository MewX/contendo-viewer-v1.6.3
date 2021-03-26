/*     */ package org.apache.xmlgraphics.ps;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.xmlgraphics.java2d.color.ColorUtil;
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
/*     */ public class PSState
/*     */   implements Serializable
/*     */ {
/*     */   public static final String DEFAULT_DASH = "[] 0";
/*  40 */   public static final Color DEFAULT_RGB_COLOR = Color.black;
/*     */   
/*     */   private static final long serialVersionUID = -3862731539801753248L;
/*  43 */   private AffineTransform transform = new AffineTransform();
/*  44 */   private List transformConcatList = new ArrayList();
/*     */   
/*     */   private int linecap;
/*     */   private int linejoin;
/*     */   private float miterLimit;
/*  49 */   private double linewidth = 1.0D;
/*  50 */   private String dashpattern = "[] 0";
/*  51 */   private Color color = DEFAULT_RGB_COLOR;
/*     */ 
/*     */ 
/*     */   
/*     */   private String fontname;
/*     */ 
/*     */ 
/*     */   
/*     */   private float fontsize;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PSState() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PSState(PSState org, boolean copyTransforms) {
/*  70 */     this.transform = (AffineTransform)org.transform.clone();
/*  71 */     if (copyTransforms) {
/*  72 */       this.transformConcatList.addAll(org.transformConcatList);
/*     */     }
/*  74 */     this.linecap = org.linecap;
/*  75 */     this.linejoin = org.linejoin;
/*  76 */     this.miterLimit = org.miterLimit;
/*  77 */     this.linewidth = org.linewidth;
/*  78 */     this.dashpattern = org.dashpattern;
/*  79 */     this.color = org.color;
/*  80 */     this.fontname = org.fontname;
/*  81 */     this.fontsize = org.fontsize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AffineTransform getTransform() {
/*  89 */     return this.transform;
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
/*     */   public boolean checkTransform(AffineTransform tf) {
/* 102 */     return !tf.equals(this.transform);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void concatMatrix(AffineTransform transform) {
/* 110 */     this.transformConcatList.add(transform);
/* 111 */     this.transform.concatenate(transform);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean useLineCap(int value) {
/* 120 */     if (this.linecap != value) {
/* 121 */       this.linecap = value;
/* 122 */       return true;
/*     */     } 
/* 124 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean useLineJoin(int value) {
/* 134 */     if (this.linejoin != value) {
/* 135 */       this.linejoin = value;
/* 136 */       return true;
/*     */     } 
/* 138 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean useMiterLimit(float value) {
/* 148 */     if (this.miterLimit != value) {
/* 149 */       this.miterLimit = value;
/* 150 */       return true;
/*     */     } 
/* 152 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean useLineWidth(double value) {
/* 162 */     if (this.linewidth != value) {
/* 163 */       this.linewidth = value;
/* 164 */       return true;
/*     */     } 
/* 166 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean useDash(String pattern) {
/* 176 */     if (!this.dashpattern.equals(pattern)) {
/* 177 */       this.dashpattern = pattern;
/* 178 */       return true;
/*     */     } 
/* 180 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean useColor(Color value) {
/* 190 */     if (!ColorUtil.isSameColor(this.color, value)) {
/* 191 */       this.color = value;
/* 192 */       return true;
/*     */     } 
/* 194 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean useFont(String name, float size) {
/* 205 */     if (name == null) {
/* 206 */       throw new NullPointerException("font name must not be null");
/*     */     }
/* 208 */     if (this.fontname == null || !this.fontname.equals(name) || this.fontsize != size) {
/* 209 */       this.fontname = name;
/* 210 */       this.fontsize = size;
/* 211 */       return true;
/*     */     } 
/* 213 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reestablish(PSGenerator gen) throws IOException {
/* 224 */     for (int i = 0, len = this.transformConcatList.size(); i < len; i++) {
/* 225 */       gen.concatMatrix(this.transformConcatList.get(i));
/*     */     }
/* 227 */     gen.useLineCap(this.linecap);
/* 228 */     gen.useLineWidth(this.linewidth);
/* 229 */     gen.useDash(this.dashpattern);
/* 230 */     gen.useColor(this.color);
/* 231 */     if (this.fontname != null)
/* 232 */       gen.useFont(this.fontname, this.fontsize); 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/PSState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */