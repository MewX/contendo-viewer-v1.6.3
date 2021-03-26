/*     */ package org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.pdfbox.cos.COSArray;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDUserAttributeObject
/*     */   extends PDAttributeObject
/*     */ {
/*     */   public static final String OWNER_USER_PROPERTIES = "UserProperties";
/*     */   
/*     */   public PDUserAttributeObject() {
/*  45 */     setOwner("UserProperties");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDUserAttributeObject(COSDictionary dictionary) {
/*  54 */     super(dictionary);
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
/*     */   public List<PDUserProperty> getOwnerUserProperties() {
/*  66 */     COSArray p = (COSArray)getCOSObject().getDictionaryObject(COSName.P);
/*  67 */     List<PDUserProperty> properties = new ArrayList<PDUserProperty>(p.size());
/*  68 */     for (int i = 0; i < p.size(); i++)
/*     */     {
/*  70 */       properties.add(new PDUserProperty((COSDictionary)p
/*  71 */             .getObject(i), this));
/*     */     }
/*  73 */     return properties;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUserProperties(List<PDUserProperty> userProperties) {
/*  83 */     COSArray p = new COSArray();
/*  84 */     for (PDUserProperty userProperty : userProperties)
/*     */     {
/*  86 */       p.add((COSObjectable)userProperty);
/*     */     }
/*  88 */     getCOSObject().setItem(COSName.P, (COSBase)p);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addUserProperty(PDUserProperty userProperty) {
/*  98 */     COSArray p = (COSArray)getCOSObject().getDictionaryObject(COSName.P);
/*  99 */     p.add((COSObjectable)userProperty);
/* 100 */     notifyChanged();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeUserProperty(PDUserProperty userProperty) {
/* 110 */     if (userProperty == null) {
/*     */       return;
/*     */     }
/*     */     
/* 114 */     COSArray p = (COSArray)getCOSObject().getDictionaryObject(COSName.P);
/* 115 */     p.remove((COSBase)userProperty.getCOSObject());
/* 116 */     notifyChanged();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void userPropertyChanged(PDUserProperty userProperty) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 130 */     return super.toString() + ", userProperties=" + 
/*     */       
/* 132 */       getOwnerUserProperties();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/documentinterchange/logicalstructure/PDUserAttributeObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */