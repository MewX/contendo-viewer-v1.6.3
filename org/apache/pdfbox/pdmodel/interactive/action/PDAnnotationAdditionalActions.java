/*     */ package org.apache.pdfbox.pdmodel.interactive.action;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
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
/*     */ public class PDAnnotationAdditionalActions
/*     */   implements COSObjectable
/*     */ {
/*     */   private final COSDictionary actions;
/*     */   
/*     */   public PDAnnotationAdditionalActions() {
/*  39 */     this.actions = new COSDictionary();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAnnotationAdditionalActions(COSDictionary a) {
/*  49 */     this.actions = a;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSDictionary getCOSObject() {
/*  60 */     return this.actions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAction getE() {
/*  71 */     COSDictionary e = (COSDictionary)this.actions.getDictionaryObject("E");
/*  72 */     PDAction retval = null;
/*  73 */     if (e != null)
/*     */     {
/*  75 */       retval = PDActionFactory.createAction(e);
/*     */     }
/*  77 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setE(PDAction e) {
/*  88 */     this.actions.setItem("E", (COSObjectable)e);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAction getX() {
/*  99 */     COSDictionary x = (COSDictionary)this.actions.getDictionaryObject("X");
/* 100 */     PDAction retval = null;
/* 101 */     if (x != null)
/*     */     {
/* 103 */       retval = PDActionFactory.createAction(x);
/*     */     }
/* 105 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setX(PDAction x) {
/* 116 */     this.actions.setItem("X", (COSObjectable)x);
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
/*     */   public PDAction getD() {
/* 128 */     COSDictionary d = (COSDictionary)this.actions.getDictionaryObject(COSName.D);
/* 129 */     PDAction retval = null;
/* 130 */     if (d != null)
/*     */     {
/* 132 */       retval = PDActionFactory.createAction(d);
/*     */     }
/* 134 */     return retval;
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
/*     */   public void setD(PDAction d) {
/* 146 */     this.actions.setItem(COSName.D, (COSObjectable)d);
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
/*     */   public PDAction getU() {
/* 158 */     COSDictionary u = (COSDictionary)this.actions.getDictionaryObject("U");
/* 159 */     PDAction retval = null;
/* 160 */     if (u != null)
/*     */     {
/* 162 */       retval = PDActionFactory.createAction(u);
/*     */     }
/* 164 */     return retval;
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
/*     */   public void setU(PDAction u) {
/* 176 */     this.actions.setItem("U", (COSObjectable)u);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAction getFo() {
/* 187 */     COSDictionary fo = (COSDictionary)this.actions.getDictionaryObject("Fo");
/* 188 */     PDAction retval = null;
/* 189 */     if (fo != null)
/*     */     {
/* 191 */       retval = PDActionFactory.createAction(fo);
/*     */     }
/* 193 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFo(PDAction fo) {
/* 204 */     this.actions.setItem("Fo", (COSObjectable)fo);
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
/*     */   public PDAction getBl() {
/* 216 */     COSDictionary bl = (COSDictionary)this.actions.getDictionaryObject("Bl");
/* 217 */     PDAction retval = null;
/* 218 */     if (bl != null)
/*     */     {
/* 220 */       retval = PDActionFactory.createAction(bl);
/*     */     }
/* 222 */     return retval;
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
/*     */   public void setBl(PDAction bl) {
/* 234 */     this.actions.setItem("Bl", (COSObjectable)bl);
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
/*     */   public PDAction getPO() {
/* 247 */     COSDictionary po = (COSDictionary)this.actions.getDictionaryObject("PO");
/* 248 */     PDAction retval = null;
/* 249 */     if (po != null)
/*     */     {
/* 251 */       retval = PDActionFactory.createAction(po);
/*     */     }
/* 253 */     return retval;
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
/*     */   public void setPO(PDAction po) {
/* 266 */     this.actions.setItem("PO", (COSObjectable)po);
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
/*     */   public PDAction getPC() {
/* 278 */     COSDictionary pc = (COSDictionary)this.actions.getDictionaryObject("PC");
/* 279 */     PDAction retval = null;
/* 280 */     if (pc != null)
/*     */     {
/* 282 */       retval = PDActionFactory.createAction(pc);
/*     */     }
/* 284 */     return retval;
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
/*     */   public void setPC(PDAction pc) {
/* 296 */     this.actions.setItem("PC", (COSObjectable)pc);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAction getPV() {
/* 307 */     COSDictionary pv = (COSDictionary)this.actions.getDictionaryObject("PV");
/* 308 */     PDAction retval = null;
/* 309 */     if (pv != null)
/*     */     {
/* 311 */       retval = PDActionFactory.createAction(pv);
/*     */     }
/* 313 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPV(PDAction pv) {
/* 324 */     this.actions.setItem("PV", (COSObjectable)pv);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAction getPI() {
/* 335 */     COSDictionary pi = (COSDictionary)this.actions.getDictionaryObject("PI");
/* 336 */     PDAction retval = null;
/* 337 */     if (pi != null)
/*     */     {
/* 339 */       retval = PDActionFactory.createAction(pi);
/*     */     }
/* 341 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPI(PDAction pi) {
/* 352 */     this.actions.setItem("PI", (COSObjectable)pi);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/action/PDAnnotationAdditionalActions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */