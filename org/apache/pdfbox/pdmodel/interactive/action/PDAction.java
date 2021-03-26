/*     */ package org.apache.pdfbox.pdmodel.interactive.action;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.common.COSArrayList;
/*     */ import org.apache.pdfbox.pdmodel.common.PDDestinationOrAction;
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
/*     */ public abstract class PDAction
/*     */   implements PDDestinationOrAction
/*     */ {
/*     */   public static final String TYPE = "Action";
/*     */   protected COSDictionary action;
/*     */   
/*     */   public PDAction() {
/*  52 */     this.action = new COSDictionary();
/*  53 */     setType("Action");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAction(COSDictionary a) {
/*  63 */     this.action = a;
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
/*  74 */     return this.action;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getType() {
/*  85 */     return this.action.getNameAsString(COSName.TYPE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setType(String type) {
/*  96 */     this.action.setName(COSName.TYPE, type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSubType() {
/* 106 */     return this.action.getNameAsString(COSName.S);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSubType(String s) {
/* 116 */     this.action.setName(COSName.S, s);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<PDAction> getNext() {
/*     */     COSArrayList cOSArrayList;
/* 128 */     List<PDAction> retval = null;
/* 129 */     COSBase next = this.action.getDictionaryObject(COSName.NEXT);
/* 130 */     if (next instanceof COSDictionary) {
/*     */       
/* 132 */       PDAction pdAction = PDActionFactory.createAction((COSDictionary)next);
/* 133 */       cOSArrayList = new COSArrayList(pdAction, next, this.action, COSName.NEXT);
/*     */     }
/* 135 */     else if (next instanceof COSArray) {
/*     */       
/* 137 */       COSArray array = (COSArray)next;
/* 138 */       List<PDAction> actions = new ArrayList<PDAction>();
/* 139 */       for (int i = 0; i < array.size(); i++)
/*     */       {
/* 141 */         actions.add(PDActionFactory.createAction((COSDictionary)array.getObject(i)));
/*     */       }
/* 143 */       cOSArrayList = new COSArrayList(actions, array);
/*     */     } 
/*     */     
/* 146 */     return (List<PDAction>)cOSArrayList;
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
/*     */   public void setNext(List<?> next) {
/* 158 */     this.action.setItem(COSName.NEXT, (COSBase)COSArrayList.converterToCOSArray(next));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/action/PDAction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */