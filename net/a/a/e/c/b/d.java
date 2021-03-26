/*     */ package net.a.a.e.c.b;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.a.a.e.a;
/*     */ import net.a.a.e.c.e.e;
/*     */ import net.a.a.g.i;
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.mathml.MathMLFencedElement;
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
/*     */ public final class d
/*     */   extends a
/*     */   implements MathMLFencedElement
/*     */ {
/*     */   public static final String r = "separators";
/*     */   public static final String s = "close";
/*     */   public static final String t = "open";
/*     */   public static final String u = "mfenced";
/*     */   private static final String v = "0.2em";
/*     */   private static final long w = 1L;
/*     */   
/*     */   public d(String paramString, AbstractDocument paramAbstractDocument) {
/*  70 */     super(paramString, paramAbstractDocument);
/*  71 */     a("open", "(");
/*  72 */     a("close", ")");
/*  73 */     a("separators", ",");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/*  79 */     return (Node)new d(this.nodeName, this.ownerDocument);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getOpen() {
/*  86 */     return a("open");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOpen(String paramString) {
/*  96 */     setAttribute("open", paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getClose() {
/* 103 */     return a("close");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setClose(String paramString) {
/* 113 */     setAttribute("close", paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSeparators() {
/* 122 */     StringBuilder stringBuilder = new StringBuilder();
/* 123 */     String str = a("separators");
/* 124 */     if (str != null) {
/* 125 */       for (byte b = 0; b < str.length(); b++) {
/* 126 */         char c = str.charAt(b);
/* 127 */         if (c > ' ') {
/* 128 */           stringBuilder.append(c);
/*     */         }
/*     */       } 
/*     */     }
/* 132 */     return stringBuilder.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSeparators(String paramString) {
/* 142 */     setAttribute("separators", paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected List<i> a() {
/* 148 */     int i = e();
/* 149 */     ArrayList<e> arrayList = new ArrayList(2 * i + 1);
/*     */ 
/*     */     
/* 152 */     e e1 = i();
/* 153 */     e1.setForm("prefix");
/* 154 */     e1.setTextContent(getOpen());
/*     */     
/* 156 */     arrayList.add(e1);
/* 157 */     String str = getSeparators();
/* 158 */     boolean bool = (str != null && str.length() > 0) ? true : false;
/*     */     
/* 160 */     for (byte b = 0; b < i; b++) {
/* 161 */       arrayList.add(a(b));
/*     */       
/* 163 */       if (bool && b < i - 1) {
/* 164 */         e e = (e)getOwnerDocument().createElement("mo");
/*     */         
/* 166 */         e.setSeparator("true");
/* 167 */         if (b < str.length()) {
/* 168 */           e.setTextContent(String.valueOf(str.charAt(b)));
/*     */         } else {
/* 170 */           e.setTextContent(String.valueOf(str
/* 171 */                 .charAt(str.length() - 1)));
/*     */         } 
/* 173 */         arrayList.add(e);
/*     */       } 
/*     */     } 
/* 176 */     e e2 = i();
/* 177 */     e2.setForm("postfix");
/* 178 */     e2.setTextContent(getClose());
/* 179 */     arrayList.add(e2);
/*     */     
/* 181 */     return (List)arrayList;
/*     */   }
/*     */ 
/*     */   
/*     */   private e i() {
/* 186 */     e e = (e)getOwnerDocument().createElement("mo");
/* 187 */     e.setFence("true");
/* 188 */     e.setStretchy("true");
/* 189 */     e.setRspace("0.2em");
/* 190 */     e.setLspace("0.2em");
/* 191 */     e.setSymmetric("false");
/* 192 */     return e;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/c/b/d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */