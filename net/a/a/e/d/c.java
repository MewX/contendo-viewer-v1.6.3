/*     */ package net.a.a.e.d;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.geom.Dimension2D;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.a.a.g.a;
/*     */ import net.a.a.g.d;
/*     */ import net.a.a.g.f;
/*     */ import net.a.a.g.g;
/*     */ import net.a.a.g.i;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
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
/*     */ public final class c
/*     */ {
/*     */   public static List<Node> a(Node paramNode) {
/*  57 */     NodeList nodeList = paramNode.getChildNodes();
/*  58 */     int i = nodeList.getLength();
/*  59 */     ArrayList<Node> arrayList = new ArrayList(i);
/*  60 */     for (byte b = 0; b < i; b++) {
/*  61 */       Node node = nodeList.item(b);
/*  62 */       arrayList.add(node);
/*     */     } 
/*  64 */     return arrayList;
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
/*     */   public static List<i> b(Node paramNode) {
/*  77 */     NodeList nodeList = paramNode.getChildNodes();
/*  78 */     int i = nodeList.getLength();
/*  79 */     ArrayList<i> arrayList = new ArrayList(i);
/*  80 */     for (byte b = 0; b < i; b++) {
/*  81 */       Node node = nodeList.item(b);
/*  82 */       if (node instanceof i) {
/*  83 */         arrayList.add((i)node);
/*     */       }
/*     */     } 
/*  86 */     return arrayList;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void a(g paramg, d paramd, Node paramNode, f paramf, Dimension2D paramDimension2D1, Dimension2D paramDimension2D2) {
/* 107 */     float f1 = (float)paramDimension2D1.getHeight();
/* 108 */     float f2 = (float)paramDimension2D2.getHeight();
/* 109 */     float f3 = (float)paramDimension2D1.getWidth();
/* 110 */     float f4 = f3;
/*     */     
/* 112 */     for (i i : b(paramNode)) {
/* 113 */       d d1 = paramg.a(i);
/* 114 */       f1 = Math.max(f1, -d1.g(paramf) + d1
/* 115 */           .b(paramf));
/* 116 */       f2 = Math.max(f2, d1.g(paramf) + d1
/* 117 */           .c(paramf));
/* 118 */       f4 = Math.max(f4, d1.f(paramf) + d1
/* 119 */           .d(paramf));
/*     */     } 
/* 121 */     paramd.c(f1 + (float)paramDimension2D1.getHeight(), paramf);
/*     */     
/* 123 */     paramd.d(f2 + 
/* 124 */         (float)paramDimension2D2.getHeight(), paramf);
/* 125 */     paramd.a((f4 + f3) / 2.0F, paramf);
/* 126 */     paramd.e(f4 + (float)paramDimension2D2.getWidth(), paramf);
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
/*     */ 
/*     */   
/*     */   public static void a(g paramg, d paramd, List<i> paramList, f paramf) {
/* 142 */     float f1 = 0.0F;
/* 143 */     float f2 = 0.0F;
/* 144 */     float f3 = 0.0F;
/* 145 */     float f4 = 0.0F;
/* 146 */     float f5 = 0.0F;
/*     */     
/* 148 */     for (i i : paramList) {
/* 149 */       d d1 = paramg.a(i);
/* 150 */       f1 = Math.max(f1, d1
/* 151 */           .b(paramf));
/* 152 */       f2 = Math.max(f2, d1
/* 153 */           .c(paramf));
/* 154 */       f4 = Math.max(f4, d1
/* 155 */           .d());
/* 156 */       f5 = Math.max(f5, d1
/* 157 */           .c());
/* 158 */       d1.a(f3, 0.0F, paramf);
/* 159 */       f3 += d1.d(paramf);
/*     */     } 
/* 161 */     paramd.c(f1, paramf);
/* 162 */     paramd.d(f2, paramf);
/* 163 */     paramd.c(f4);
/* 164 */     paramd.b(f5);
/* 165 */     paramd.a(f3 / 2.0F, paramf);
/* 166 */     paramd.e(f3, paramf);
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
/*     */ 
/*     */ 
/*     */   
/*     */   public static void a(Color paramColor, d paramd, boolean paramBoolean) {
/* 183 */     if (paramColor != null) {
/*     */       a a;
/* 185 */       if (paramBoolean) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 190 */         a = new a(paramColor, (float)Math.ceil(paramd.b(f.c)), (float)Math.ceil(paramd.c(f.c)), (float)Math.ceil(paramd.d(f.c)));
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 195 */         a = new a(paramColor, paramd.b(f.c), paramd.c(f.c), paramd.d(f.c));
/*     */       } 
/*     */ 
/*     */       
/* 199 */       paramd.e().add(0, a);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/d/c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */