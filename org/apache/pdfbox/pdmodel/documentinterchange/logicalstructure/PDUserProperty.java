/*     */ package org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.common.PDDictionaryWrapper;
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
/*     */ public class PDUserProperty
/*     */   extends PDDictionaryWrapper
/*     */ {
/*     */   private final PDUserAttributeObject userAttributeObject;
/*     */   
/*     */   public PDUserProperty(PDUserAttributeObject userAttributeObject) {
/*  41 */     this.userAttributeObject = userAttributeObject;
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
/*     */   public PDUserProperty(COSDictionary dictionary, PDUserAttributeObject userAttributeObject) {
/*  53 */     super(dictionary);
/*  54 */     this.userAttributeObject = userAttributeObject;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  65 */     return getCOSObject().getNameAsString(COSName.N);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/*  75 */     potentiallyNotifyChanged(getName(), name);
/*  76 */     getCOSObject().setName(COSName.N, name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSBase getValue() {
/*  86 */     return getCOSObject().getDictionaryObject(COSName.V);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValue(COSBase value) {
/*  96 */     potentiallyNotifyChanged(getValue(), value);
/*  97 */     getCOSObject().setItem(COSName.V, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFormattedValue() {
/* 107 */     return getCOSObject().getString(COSName.F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFormattedValue(String formattedValue) {
/* 117 */     potentiallyNotifyChanged(getFormattedValue(), formattedValue);
/* 118 */     getCOSObject().setString(COSName.F, formattedValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isHidden() {
/* 129 */     return getCOSObject().getBoolean(COSName.H, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHidden(boolean hidden) {
/* 140 */     potentiallyNotifyChanged(Boolean.valueOf(isHidden()), Boolean.valueOf(hidden));
/* 141 */     getCOSObject().setBoolean(COSName.H, hidden);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 148 */     return "Name=" + getName() + ", Value=" + 
/* 149 */       getValue() + ", FormattedValue=" + 
/* 150 */       getFormattedValue() + ", Hidden=" + 
/* 151 */       isHidden();
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
/*     */   private void potentiallyNotifyChanged(Object oldEntry, Object newEntry) {
/* 163 */     if (isEntryChanged(oldEntry, newEntry))
/*     */     {
/* 165 */       this.userAttributeObject.userPropertyChanged(this);
/*     */     }
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
/*     */   private boolean isEntryChanged(Object oldEntry, Object newEntry) {
/* 179 */     if (oldEntry == null)
/*     */     {
/* 181 */       return (newEntry != null);
/*     */     }
/* 183 */     return !oldEntry.equals(newEntry);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 189 */     int prime = 31;
/* 190 */     int result = super.hashCode();
/*     */     
/* 192 */     result = 31 * result + ((this.userAttributeObject == null) ? 0 : this.userAttributeObject.hashCode());
/* 193 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 199 */     if (this == obj)
/*     */     {
/* 201 */       return true;
/*     */     }
/* 203 */     if (!super.equals(obj))
/*     */     {
/* 205 */       return false;
/*     */     }
/* 207 */     if (getClass() != obj.getClass())
/*     */     {
/* 209 */       return false;
/*     */     }
/* 211 */     PDUserProperty other = (PDUserProperty)obj;
/* 212 */     if (this.userAttributeObject == null) {
/*     */       
/* 214 */       if (other.userAttributeObject != null)
/*     */       {
/* 216 */         return false;
/*     */       }
/*     */     }
/* 219 */     else if (!this.userAttributeObject.equals(other.userAttributeObject)) {
/*     */       
/* 221 */       return false;
/*     */     } 
/* 223 */     return true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/documentinterchange/logicalstructure/PDUserProperty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */