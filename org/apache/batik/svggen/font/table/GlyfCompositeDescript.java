/*     */ package org.apache.batik.svggen.font.table;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
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
/*     */ public class GlyfCompositeDescript
/*     */   extends GlyfDescript
/*     */ {
/*     */   private List components;
/*     */   protected boolean beingResolved;
/*     */   protected boolean resolved;
/*     */   
/*     */   public GlyfCompositeDescript(GlyfTable parentTable, ByteArrayInputStream bais) {
/*  42 */     super(parentTable, (short)-1, bais); GlyfCompositeComp comp;
/*     */     this.components = new ArrayList();
/*     */     this.beingResolved = false;
/*     */     this.resolved = false;
/*     */     do {
/*  47 */       comp = new GlyfCompositeComp(bais);
/*  48 */       this.components.add(comp);
/*  49 */     } while ((comp.getFlags() & 0x20) != 0);
/*     */ 
/*     */     
/*  52 */     if ((comp.getFlags() & 0x100) != 0) {
/*  53 */       readInstructions(bais, bais.read() << 8 | bais.read());
/*     */     }
/*     */   }
/*     */   
/*     */   public void resolve() {
/*  58 */     if (this.resolved)
/*  59 */       return;  if (this.beingResolved) {
/*  60 */       System.err.println("Circular reference in GlyfCompositeDesc");
/*     */       return;
/*     */     } 
/*  63 */     this.beingResolved = true;
/*     */     
/*  65 */     int firstIndex = 0;
/*  66 */     int firstContour = 0;
/*     */     
/*  68 */     for (Object component : this.components) {
/*  69 */       GlyfCompositeComp comp = (GlyfCompositeComp)component;
/*  70 */       comp.setFirstIndex(firstIndex);
/*  71 */       comp.setFirstContour(firstContour);
/*     */ 
/*     */       
/*  74 */       GlyfDescript desc = this.parentTable.getDescription(comp.getGlyphIndex());
/*  75 */       if (desc != null) {
/*  76 */         desc.resolve();
/*  77 */         firstIndex += desc.getPointCount();
/*  78 */         firstContour += desc.getContourCount();
/*     */       } 
/*     */     } 
/*  81 */     this.resolved = true;
/*  82 */     this.beingResolved = false;
/*     */   }
/*     */   
/*     */   public int getEndPtOfContours(int i) {
/*  86 */     GlyfCompositeComp c = getCompositeCompEndPt(i);
/*  87 */     if (c != null) {
/*  88 */       GlyphDescription gd = this.parentTable.getDescription(c.getGlyphIndex());
/*  89 */       return gd.getEndPtOfContours(i - c.getFirstContour()) + c.getFirstIndex();
/*     */     } 
/*  91 */     return 0;
/*     */   }
/*     */   
/*     */   public byte getFlags(int i) {
/*  95 */     GlyfCompositeComp c = getCompositeComp(i);
/*  96 */     if (c != null) {
/*  97 */       GlyphDescription gd = this.parentTable.getDescription(c.getGlyphIndex());
/*  98 */       return gd.getFlags(i - c.getFirstIndex());
/*     */     } 
/* 100 */     return 0;
/*     */   }
/*     */   
/*     */   public short getXCoordinate(int i) {
/* 104 */     GlyfCompositeComp c = getCompositeComp(i);
/* 105 */     if (c != null) {
/* 106 */       GlyphDescription gd = this.parentTable.getDescription(c.getGlyphIndex());
/* 107 */       int n = i - c.getFirstIndex();
/* 108 */       int x = gd.getXCoordinate(n);
/* 109 */       int y = gd.getYCoordinate(n);
/* 110 */       short x1 = (short)c.scaleX(x, y);
/* 111 */       x1 = (short)(x1 + c.getXTranslate());
/* 112 */       return x1;
/*     */     } 
/* 114 */     return 0;
/*     */   }
/*     */   
/*     */   public short getYCoordinate(int i) {
/* 118 */     GlyfCompositeComp c = getCompositeComp(i);
/* 119 */     if (c != null) {
/* 120 */       GlyphDescription gd = this.parentTable.getDescription(c.getGlyphIndex());
/* 121 */       int n = i - c.getFirstIndex();
/* 122 */       int x = gd.getXCoordinate(n);
/* 123 */       int y = gd.getYCoordinate(n);
/* 124 */       short y1 = (short)c.scaleY(x, y);
/* 125 */       y1 = (short)(y1 + c.getYTranslate());
/* 126 */       return y1;
/*     */     } 
/* 128 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean isComposite() {
/* 132 */     return true;
/*     */   }
/*     */   
/*     */   public int getPointCount() {
/* 136 */     if (!this.resolved) {
/* 137 */       System.err.println("getPointCount called on unresolved GlyfCompositeDescript");
/*     */     }
/* 139 */     GlyfCompositeComp c = this.components.get(this.components.size() - 1);
/*     */ 
/*     */     
/* 142 */     return c.getFirstIndex() + this.parentTable.getDescription(c.getGlyphIndex()).getPointCount();
/*     */   }
/*     */   
/*     */   public int getContourCount() {
/* 146 */     if (!this.resolved) {
/* 147 */       System.err.println("getContourCount called on unresolved GlyfCompositeDescript");
/*     */     }
/* 149 */     GlyfCompositeComp c = this.components.get(this.components.size() - 1);
/* 150 */     return c.getFirstContour() + this.parentTable.getDescription(c.getGlyphIndex()).getContourCount();
/*     */   }
/*     */   
/*     */   public int getComponentIndex(int i) {
/* 154 */     return ((GlyfCompositeComp)this.components.get(i)).getFirstIndex();
/*     */   }
/*     */   
/*     */   public int getComponentCount() {
/* 158 */     return this.components.size();
/*     */   }
/*     */ 
/*     */   
/*     */   protected GlyfCompositeComp getCompositeComp(int i) {
/* 163 */     for (Object component : this.components) {
/* 164 */       GlyfCompositeComp c = (GlyfCompositeComp)component;
/* 165 */       GlyphDescription gd = this.parentTable.getDescription(c.getGlyphIndex());
/* 166 */       if (c.getFirstIndex() <= i && i < c.getFirstIndex() + gd.getPointCount()) {
/* 167 */         return c;
/*     */       }
/*     */     } 
/* 170 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected GlyfCompositeComp getCompositeCompEndPt(int i) {
/* 175 */     for (Object component : this.components) {
/* 176 */       GlyfCompositeComp c = (GlyfCompositeComp)component;
/* 177 */       GlyphDescription gd = this.parentTable.getDescription(c.getGlyphIndex());
/* 178 */       if (c.getFirstContour() <= i && i < c.getFirstContour() + gd.getContourCount()) {
/* 179 */         return c;
/*     */       }
/*     */     } 
/* 182 */     return null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/GlyfCompositeDescript.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */