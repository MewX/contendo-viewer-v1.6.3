/*     */ package org.apache.pdfbox.pdmodel.graphics.optionalcontent;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSObject;
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
/*     */ public class PDOptionalContentProperties
/*     */   implements COSObjectable
/*     */ {
/*     */   private final COSDictionary dict;
/*     */   
/*     */   public enum BaseState
/*     */   {
/*  44 */     ON((String)COSName.ON),
/*     */     
/*  46 */     OFF((String)COSName.OFF),
/*     */     
/*  48 */     UNCHANGED((String)COSName.UNCHANGED);
/*     */     
/*     */     private final COSName name;
/*     */ 
/*     */     
/*     */     BaseState(COSName value) {
/*  54 */       this.name = value;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public COSName getName() {
/*  63 */       return this.name;
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
/*     */   public PDOptionalContentProperties() {
/*  89 */     this.dict = new COSDictionary();
/*  90 */     this.dict.setItem(COSName.OCGS, (COSBase)new COSArray());
/*  91 */     COSDictionary d = new COSDictionary();
/*     */ 
/*     */     
/*  94 */     d.setString(COSName.NAME, "Top");
/*     */     
/*  96 */     this.dict.setItem(COSName.D, (COSBase)d);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDOptionalContentProperties(COSDictionary props) {
/* 105 */     this.dict = props;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSBase getCOSObject() {
/* 112 */     return (COSBase)this.dict;
/*     */   }
/*     */ 
/*     */   
/*     */   private COSArray getOCGs() {
/* 117 */     COSArray ocgs = this.dict.getCOSArray(COSName.OCGS);
/* 118 */     if (ocgs == null) {
/*     */       
/* 120 */       ocgs = new COSArray();
/* 121 */       this.dict.setItem(COSName.OCGS, (COSBase)ocgs);
/*     */     } 
/* 123 */     return ocgs;
/*     */   }
/*     */ 
/*     */   
/*     */   private COSDictionary getD() {
/* 128 */     COSBase base = this.dict.getDictionaryObject(COSName.D);
/* 129 */     if (base instanceof COSDictionary)
/*     */     {
/* 131 */       return (COSDictionary)base;
/*     */     }
/*     */     
/* 134 */     COSDictionary d = new COSDictionary();
/*     */ 
/*     */     
/* 137 */     d.setString(COSName.NAME, "Top");
/*     */ 
/*     */     
/* 140 */     this.dict.setItem(COSName.D, (COSBase)d);
/*     */     
/* 142 */     return d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDOptionalContentGroup getGroup(String name) {
/* 153 */     COSArray ocgs = getOCGs();
/* 154 */     for (COSBase o : ocgs) {
/*     */       
/* 156 */       COSDictionary ocg = toDictionary(o);
/* 157 */       String groupName = ocg.getString(COSName.NAME);
/* 158 */       if (groupName.equals(name))
/*     */       {
/* 160 */         return new PDOptionalContentGroup(ocg);
/*     */       }
/*     */     } 
/* 163 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addGroup(PDOptionalContentGroup ocg) {
/* 172 */     COSArray ocgs = getOCGs();
/* 173 */     ocgs.add((COSBase)ocg.getCOSObject());
/*     */ 
/*     */     
/* 176 */     COSArray order = (COSArray)getD().getDictionaryObject(COSName.ORDER);
/* 177 */     if (order == null) {
/*     */       
/* 179 */       order = new COSArray();
/* 180 */       getD().setItem(COSName.ORDER, (COSBase)order);
/*     */     } 
/* 182 */     order.add((COSObjectable)ocg);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<PDOptionalContentGroup> getOptionalContentGroups() {
/* 191 */     Collection<PDOptionalContentGroup> coll = new ArrayList<PDOptionalContentGroup>();
/* 192 */     COSArray ocgs = getOCGs();
/* 193 */     for (COSBase base : ocgs)
/*     */     {
/* 195 */       coll.add(new PDOptionalContentGroup(toDictionary(base)));
/*     */     }
/* 197 */     return coll;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BaseState getBaseState() {
/* 206 */     COSDictionary d = getD();
/* 207 */     COSName name = (COSName)d.getItem(COSName.BASE_STATE);
/* 208 */     return BaseState.valueOf(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBaseState(BaseState state) {
/* 217 */     COSDictionary d = getD();
/* 218 */     d.setItem(COSName.BASE_STATE, (COSBase)state.getName());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getGroupNames() {
/* 227 */     COSArray ocgs = (COSArray)this.dict.getDictionaryObject(COSName.OCGS);
/* 228 */     int size = ocgs.size();
/* 229 */     String[] groups = new String[size];
/* 230 */     for (int i = 0; i < size; i++) {
/*     */       
/* 232 */       COSBase obj = ocgs.get(i);
/* 233 */       COSDictionary ocg = toDictionary(obj);
/* 234 */       groups[i] = ocg.getString(COSName.NAME);
/*     */     } 
/* 236 */     return groups;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasGroup(String groupName) {
/* 246 */     String[] layers = getGroupNames();
/* 247 */     for (String layer : layers) {
/*     */       
/* 249 */       if (layer.equals(groupName))
/*     */       {
/* 251 */         return true;
/*     */       }
/*     */     } 
/* 254 */     return false;
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
/*     */   public boolean isGroupEnabled(String groupName) {
/* 267 */     boolean result = false;
/* 268 */     COSArray ocgs = getOCGs();
/* 269 */     for (COSBase o : ocgs) {
/*     */       
/* 271 */       COSDictionary ocg = toDictionary(o);
/* 272 */       String name = ocg.getString(COSName.NAME);
/* 273 */       if (groupName.equals(name) && isGroupEnabled(new PDOptionalContentGroup(ocg)))
/*     */       {
/* 275 */         result = true;
/*     */       }
/*     */     } 
/* 278 */     return result;
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
/*     */   public boolean isGroupEnabled(PDOptionalContentGroup group) {
/* 291 */     BaseState baseState = getBaseState();
/* 292 */     boolean enabled = !baseState.equals(BaseState.OFF);
/*     */ 
/*     */     
/* 295 */     if (group == null)
/*     */     {
/* 297 */       return enabled;
/*     */     }
/*     */     
/* 300 */     COSDictionary d = getD();
/* 301 */     COSBase base = d.getDictionaryObject(COSName.ON);
/* 302 */     if (base instanceof COSArray)
/*     */     {
/* 304 */       for (COSBase o : base) {
/*     */         
/* 306 */         COSDictionary dictionary = toDictionary(o);
/* 307 */         if (dictionary == group.getCOSObject())
/*     */         {
/* 309 */           return true;
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 314 */     base = d.getDictionaryObject(COSName.OFF);
/* 315 */     if (base instanceof COSArray)
/*     */     {
/* 317 */       for (COSBase o : base) {
/*     */         
/* 319 */         COSDictionary dictionary = toDictionary(o);
/* 320 */         if (dictionary == group.getCOSObject())
/*     */         {
/* 322 */           return false;
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 327 */     return enabled;
/*     */   }
/*     */ 
/*     */   
/*     */   private COSDictionary toDictionary(COSBase o) {
/* 332 */     if (o instanceof COSObject)
/*     */     {
/* 334 */       return (COSDictionary)((COSObject)o).getObject();
/*     */     }
/*     */ 
/*     */     
/* 338 */     return (COSDictionary)o;
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
/*     */   public boolean setGroupEnabled(String groupName, boolean enable) {
/* 352 */     boolean result = false;
/* 353 */     COSArray ocgs = getOCGs();
/* 354 */     for (COSBase o : ocgs) {
/*     */       
/* 356 */       COSDictionary ocg = toDictionary(o);
/* 357 */       String name = ocg.getString(COSName.NAME);
/* 358 */       if (groupName.equals(name) && setGroupEnabled(new PDOptionalContentGroup(ocg), enable))
/*     */       {
/* 360 */         result = true;
/*     */       }
/*     */     } 
/* 363 */     return result;
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
/*     */   public boolean setGroupEnabled(PDOptionalContentGroup group, boolean enable) {
/*     */     COSArray on, off;
/* 377 */     COSDictionary d = getD();
/* 378 */     COSBase base = d.getDictionaryObject(COSName.ON);
/* 379 */     if (!(base instanceof COSArray)) {
/*     */       
/* 381 */       on = new COSArray();
/* 382 */       d.setItem(COSName.ON, (COSBase)on);
/*     */     }
/*     */     else {
/*     */       
/* 386 */       on = (COSArray)base;
/*     */     } 
/* 388 */     base = d.getDictionaryObject(COSName.OFF);
/* 389 */     if (!(base instanceof COSArray)) {
/*     */       
/* 391 */       off = new COSArray();
/* 392 */       d.setItem(COSName.OFF, (COSBase)off);
/*     */     }
/*     */     else {
/*     */       
/* 396 */       off = (COSArray)base;
/*     */     } 
/*     */     
/* 399 */     boolean found = false;
/* 400 */     if (enable) {
/*     */       
/* 402 */       for (COSBase o : off) {
/*     */         
/* 404 */         COSDictionary groupDictionary = toDictionary(o);
/* 405 */         if (groupDictionary == group.getCOSObject()) {
/*     */ 
/*     */           
/* 408 */           off.remove(o);
/* 409 */           on.add(o);
/* 410 */           found = true;
/*     */ 
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } else {
/* 417 */       for (COSBase o : on) {
/*     */         
/* 419 */         COSDictionary groupDictionary = toDictionary(o);
/* 420 */         if (groupDictionary == group.getCOSObject()) {
/*     */ 
/*     */           
/* 423 */           on.remove(o);
/* 424 */           off.add(o);
/* 425 */           found = true;
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 430 */     if (!found)
/*     */     {
/* 432 */       if (enable) {
/*     */         
/* 434 */         on.add((COSBase)group.getCOSObject());
/*     */       }
/*     */       else {
/*     */         
/* 438 */         off.add((COSBase)group.getCOSObject());
/*     */       } 
/*     */     }
/* 441 */     return found;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/optionalcontent/PDOptionalContentProperties.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */