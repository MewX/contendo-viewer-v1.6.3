/*     */ package org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSInteger;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSObject;
/*     */ import org.apache.pdfbox.pdmodel.common.COSArrayList;
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
/*     */ public abstract class PDStructureNode
/*     */   implements COSObjectable
/*     */ {
/*     */   private final COSDictionary dictionary;
/*     */   
/*     */   public static PDStructureNode create(COSDictionary node) {
/*  48 */     String type = node.getNameAsString(COSName.TYPE);
/*  49 */     if ("StructTreeRoot".equals(type))
/*     */     {
/*  51 */       return new PDStructureTreeRoot(node);
/*     */     }
/*  53 */     if (type == null || "StructElem".equals(type))
/*     */     {
/*  55 */       return new PDStructureElement(node);
/*     */     }
/*  57 */     throw new IllegalArgumentException("Dictionary must not include a Type entry with a value that is neither StructTreeRoot nor StructElem.");
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
/*  68 */     return this.dictionary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected PDStructureNode(String type) {
/*  78 */     this.dictionary = new COSDictionary();
/*  79 */     this.dictionary.setName(COSName.TYPE, type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected PDStructureNode(COSDictionary dictionary) {
/*  89 */     this.dictionary = dictionary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getType() {
/*  99 */     return getCOSObject().getNameAsString(COSName.TYPE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Object> getKids() {
/* 109 */     List<Object> kidObjects = new ArrayList();
/* 110 */     COSBase k = getCOSObject().getDictionaryObject(COSName.K);
/* 111 */     if (k instanceof COSArray) {
/*     */       
/* 113 */       for (COSBase kid : k)
/*     */       {
/* 115 */         Object kidObject = createObject(kid);
/* 116 */         if (kidObject != null)
/*     */         {
/* 118 */           kidObjects.add(kidObject);
/*     */         }
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 124 */       Object kidObject = createObject(k);
/* 125 */       if (kidObject != null)
/*     */       {
/* 127 */         kidObjects.add(kidObject);
/*     */       }
/*     */     } 
/* 130 */     return kidObjects;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setKids(List<Object> kids) {
/* 140 */     getCOSObject().setItem(COSName.K, 
/* 141 */         (COSBase)COSArrayList.converterToCOSArray(kids));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void appendKid(PDStructureElement structureElement) {
/* 151 */     appendObjectableKid(structureElement);
/* 152 */     structureElement.setParent(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void appendObjectableKid(COSObjectable objectable) {
/* 162 */     if (objectable == null) {
/*     */       return;
/*     */     }
/*     */     
/* 166 */     appendKid(objectable.getCOSObject());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void appendKid(COSBase object) {
/* 176 */     if (object == null) {
/*     */       return;
/*     */     }
/*     */     
/* 180 */     COSBase k = getCOSObject().getDictionaryObject(COSName.K);
/* 181 */     if (k == null) {
/*     */ 
/*     */       
/* 184 */       getCOSObject().setItem(COSName.K, object);
/*     */     }
/* 186 */     else if (k instanceof COSArray) {
/*     */ 
/*     */       
/* 189 */       COSArray array = (COSArray)k;
/* 190 */       array.add(object);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 195 */       COSArray array = new COSArray();
/* 196 */       array.add(k);
/* 197 */       array.add(object);
/* 198 */       getCOSObject().setItem(COSName.K, (COSBase)array);
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
/*     */   public void insertBefore(PDStructureElement newKid, Object refKid) {
/* 210 */     insertObjectableBefore(newKid, refKid);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void insertObjectableBefore(COSObjectable newKid, Object refKid) {
/* 221 */     if (newKid == null) {
/*     */       return;
/*     */     }
/*     */     
/* 225 */     insertBefore(newKid.getCOSObject(), refKid);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void insertBefore(COSBase newKid, Object refKid) {
/* 236 */     if (newKid == null || refKid == null) {
/*     */       return;
/*     */     }
/*     */     
/* 240 */     COSBase k = getCOSObject().getDictionaryObject(COSName.K);
/* 241 */     if (k == null) {
/*     */       return;
/*     */     }
/*     */     
/* 245 */     COSBase refKidBase = null;
/* 246 */     if (refKid instanceof COSObjectable) {
/*     */       
/* 248 */       refKidBase = ((COSObjectable)refKid).getCOSObject();
/*     */     }
/* 250 */     else if (refKid instanceof COSInteger) {
/*     */       
/* 252 */       refKidBase = (COSBase)refKid;
/*     */     } 
/* 254 */     if (k instanceof COSArray) {
/*     */       
/* 256 */       COSArray array = (COSArray)k;
/* 257 */       int refIndex = array.indexOfObject(refKidBase);
/* 258 */       array.add(refIndex, newKid.getCOSObject());
/*     */     }
/*     */     else {
/*     */       
/* 262 */       boolean onlyKid = k.equals(refKidBase);
/* 263 */       if (!onlyKid && k instanceof COSObject) {
/*     */         
/* 265 */         COSBase kObj = ((COSObject)k).getObject();
/* 266 */         onlyKid = kObj.equals(refKidBase);
/*     */       } 
/* 268 */       if (onlyKid) {
/*     */         
/* 270 */         COSArray array = new COSArray();
/* 271 */         array.add(newKid);
/* 272 */         array.add(refKidBase);
/* 273 */         getCOSObject().setItem(COSName.K, (COSBase)array);
/*     */       } 
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
/*     */   public boolean removeKid(PDStructureElement structureElement) {
/* 286 */     boolean removed = removeObjectableKid(structureElement);
/* 287 */     if (removed)
/*     */     {
/* 289 */       structureElement.setParent(null);
/*     */     }
/* 291 */     return removed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean removeObjectableKid(COSObjectable objectable) {
/* 302 */     if (objectable == null)
/*     */     {
/* 304 */       return false;
/*     */     }
/* 306 */     return removeKid(objectable.getCOSObject());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean removeKid(COSBase object) {
/* 317 */     if (object == null)
/*     */     {
/* 319 */       return false;
/*     */     }
/* 321 */     COSBase k = getCOSObject().getDictionaryObject(COSName.K);
/* 322 */     if (k == null)
/*     */     {
/*     */       
/* 325 */       return false;
/*     */     }
/* 327 */     if (k instanceof COSArray) {
/*     */ 
/*     */       
/* 330 */       COSArray array = (COSArray)k;
/* 331 */       boolean removed = array.removeObject(object);
/*     */       
/* 333 */       if (array.size() == 1)
/*     */       {
/* 335 */         getCOSObject().setItem(COSName.K, array.getObject(0));
/*     */       }
/* 337 */       return removed;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 342 */     boolean onlyKid = k.equals(object);
/* 343 */     if (!onlyKid && k instanceof COSObject) {
/*     */       
/* 345 */       COSBase kObj = ((COSObject)k).getObject();
/* 346 */       onlyKid = kObj.equals(object);
/*     */     } 
/* 348 */     if (onlyKid) {
/*     */       
/* 350 */       getCOSObject().setItem(COSName.K, null);
/* 351 */       return true;
/*     */     } 
/* 353 */     return false;
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
/*     */   protected Object createObject(COSBase kid) {
/* 372 */     COSDictionary kidDic = null;
/* 373 */     if (kid instanceof COSDictionary) {
/*     */       
/* 375 */       kidDic = (COSDictionary)kid;
/*     */     }
/* 377 */     else if (kid instanceof COSObject) {
/*     */       
/* 379 */       COSBase base = ((COSObject)kid).getObject();
/* 380 */       if (base instanceof COSDictionary)
/*     */       {
/* 382 */         kidDic = (COSDictionary)base;
/*     */       }
/*     */     } 
/* 385 */     if (kidDic != null)
/*     */     {
/* 387 */       return createObjectFromDic(kidDic);
/*     */     }
/* 389 */     if (kid instanceof COSInteger) {
/*     */ 
/*     */       
/* 392 */       COSInteger mcid = (COSInteger)kid;
/* 393 */       return Integer.valueOf(mcid.intValue());
/*     */     } 
/* 395 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private COSObjectable createObjectFromDic(COSDictionary kidDic) {
/* 400 */     String type = kidDic.getNameAsString(COSName.TYPE);
/* 401 */     if (type == null || "StructElem".equals(type))
/*     */     {
/*     */       
/* 404 */       return new PDStructureElement(kidDic);
/*     */     }
/* 406 */     if ("OBJR".equals(type))
/*     */     {
/*     */       
/* 409 */       return new PDObjectReference(kidDic);
/*     */     }
/* 411 */     if ("MCR".equals(type))
/*     */     {
/*     */       
/* 414 */       return new PDMarkedContentReference(kidDic);
/*     */     }
/* 416 */     return null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/documentinterchange/logicalstructure/PDStructureNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */