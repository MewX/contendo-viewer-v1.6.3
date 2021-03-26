/*     */ package org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSInteger;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSNumber;
/*     */ import org.apache.pdfbox.cos.COSObject;
/*     */ import org.apache.pdfbox.pdmodel.PDPage;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.documentinterchange.markedcontent.PDMarkedContent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDStructureElement
/*     */   extends PDStructureNode
/*     */ {
/*     */   public static final String TYPE = "StructElem";
/*     */   
/*     */   public PDStructureElement(String structureType, PDStructureNode parent) {
/*  51 */     super("StructElem");
/*  52 */     setStructureType(structureType);
/*  53 */     setParent(parent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDStructureElement(COSDictionary dic) {
/*  63 */     super(dic);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStructureType() {
/*  74 */     return getCOSObject().getNameAsString(COSName.S);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setStructureType(String structureType) {
/*  84 */     getCOSObject().setName(COSName.S, structureType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDStructureNode getParent() {
/*  94 */     COSBase base = getCOSObject().getDictionaryObject(COSName.P);
/*  95 */     if (base instanceof COSDictionary)
/*     */     {
/*  97 */       return PDStructureNode.create((COSDictionary)base);
/*     */     }
/*  99 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setParent(PDStructureNode structureNode) {
/* 109 */     getCOSObject().setItem(COSName.P, structureNode);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getElementIdentifier() {
/* 119 */     return getCOSObject().getString(COSName.ID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setElementIdentifier(String id) {
/* 129 */     getCOSObject().setString(COSName.ID, id);
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
/*     */   public PDPage getPage() {
/* 141 */     COSBase base = getCOSObject().getDictionaryObject(COSName.PG);
/* 142 */     if (base instanceof COSDictionary)
/*     */     {
/* 144 */       return new PDPage((COSDictionary)base);
/*     */     }
/* 146 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPage(PDPage page) {
/* 157 */     getCOSObject().setItem(COSName.PG, (COSObjectable)page);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Revisions<PDAttributeObject> getAttributes() {
/* 167 */     Revisions<PDAttributeObject> attributes = new Revisions<PDAttributeObject>();
/* 168 */     COSBase a = getCOSObject().getDictionaryObject(COSName.A);
/* 169 */     if (a instanceof COSArray) {
/*     */       
/* 171 */       COSArray aa = (COSArray)a;
/* 172 */       Iterator<COSBase> it = aa.iterator();
/* 173 */       PDAttributeObject ao = null;
/* 174 */       while (it.hasNext()) {
/*     */         
/* 176 */         COSBase item = it.next();
/* 177 */         if (item instanceof COSObject)
/*     */         {
/* 179 */           item = ((COSObject)item).getObject();
/*     */         }
/* 181 */         if (item instanceof COSDictionary) {
/*     */           
/* 183 */           ao = PDAttributeObject.create((COSDictionary)item);
/* 184 */           ao.setStructureElement(this);
/* 185 */           attributes.addObject(ao, 0); continue;
/*     */         } 
/* 187 */         if (item instanceof COSInteger)
/*     */         {
/* 189 */           attributes.setRevisionNumber(ao, ((COSNumber)item).intValue());
/*     */         }
/*     */       } 
/*     */     } 
/* 193 */     if (a instanceof COSDictionary) {
/*     */       
/* 195 */       PDAttributeObject ao = PDAttributeObject.create((COSDictionary)a);
/* 196 */       ao.setStructureElement(this);
/* 197 */       attributes.addObject(ao, 0);
/*     */     } 
/* 199 */     return attributes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAttributes(Revisions<PDAttributeObject> attributes) {
/* 209 */     COSName key = COSName.A;
/* 210 */     if (attributes.size() == 1 && attributes.getRevisionNumber(0) == 0) {
/*     */       
/* 212 */       PDAttributeObject attributeObject = attributes.getObject(0);
/* 213 */       attributeObject.setStructureElement(this);
/* 214 */       getCOSObject().setItem(key, (COSObjectable)attributeObject);
/*     */       return;
/*     */     } 
/* 217 */     COSArray array = new COSArray();
/* 218 */     for (int i = 0; i < attributes.size(); i++) {
/*     */       
/* 220 */       PDAttributeObject attributeObject = attributes.getObject(i);
/* 221 */       attributeObject.setStructureElement(this);
/* 222 */       int revisionNumber = attributes.getRevisionNumber(i);
/* 223 */       if (revisionNumber < 0)
/*     */       {
/* 225 */         throw new IllegalArgumentException("The revision number shall be > -1");
/*     */       }
/* 227 */       array.add((COSObjectable)attributeObject);
/* 228 */       array.add((COSBase)COSInteger.get(revisionNumber));
/*     */     } 
/* 230 */     getCOSObject().setItem(key, (COSBase)array);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAttribute(PDAttributeObject attributeObject) {
/*     */     COSArray array;
/* 240 */     COSName key = COSName.A;
/* 241 */     attributeObject.setStructureElement(this);
/* 242 */     COSBase a = getCOSObject().getDictionaryObject(key);
/*     */     
/* 244 */     if (a instanceof COSArray) {
/*     */       
/* 246 */       array = (COSArray)a;
/*     */     }
/*     */     else {
/*     */       
/* 250 */       array = new COSArray();
/* 251 */       if (a != null) {
/*     */         
/* 253 */         array.add(a);
/* 254 */         array.add((COSBase)COSInteger.get(0L));
/*     */       } 
/*     */     } 
/* 257 */     getCOSObject().setItem(key, (COSBase)array);
/* 258 */     array.add((COSObjectable)attributeObject);
/* 259 */     array.add((COSBase)COSInteger.get(getRevisionNumber()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeAttribute(PDAttributeObject attributeObject) {
/* 269 */     COSName key = COSName.A;
/* 270 */     COSBase a = getCOSObject().getDictionaryObject(key);
/* 271 */     if (a instanceof COSArray) {
/*     */       
/* 273 */       COSArray array = (COSArray)a;
/* 274 */       array.remove((COSBase)attributeObject.getCOSObject());
/* 275 */       if (array.size() == 2 && array.getInt(1) == 0)
/*     */       {
/* 277 */         getCOSObject().setItem(key, array.getObject(0));
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 282 */       COSBase directA = a;
/* 283 */       if (a instanceof COSObject)
/*     */       {
/* 285 */         directA = ((COSObject)a).getObject();
/*     */       }
/* 287 */       if (attributeObject.getCOSObject().equals(directA))
/*     */       {
/* 289 */         getCOSObject().setItem(key, null);
/*     */       }
/*     */     } 
/* 292 */     attributeObject.setStructureElement((PDStructureElement)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attributeChanged(PDAttributeObject attributeObject) {
/* 302 */     COSName key = COSName.A;
/* 303 */     COSBase a = getCOSObject().getDictionaryObject(key);
/* 304 */     if (a instanceof COSArray) {
/*     */       
/* 306 */       COSArray array = (COSArray)a;
/* 307 */       for (int i = 0; i < array.size(); i++) {
/*     */         
/* 309 */         COSBase entry = array.getObject(i);
/* 310 */         if (entry.equals(attributeObject.getCOSObject()))
/*     */         {
/* 312 */           COSBase next = array.get(i + 1);
/* 313 */           if (next instanceof COSInteger)
/*     */           {
/* 315 */             array.set(i + 1, (COSBase)COSInteger.get(getRevisionNumber()));
/*     */           }
/*     */         }
/*     */       
/*     */       } 
/*     */     } else {
/*     */       
/* 322 */       COSArray array = new COSArray();
/* 323 */       array.add(a);
/* 324 */       array.add((COSBase)COSInteger.get(getRevisionNumber()));
/* 325 */       getCOSObject().setItem(key, (COSBase)array);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Revisions<String> getClassNames() {
/* 336 */     COSName key = COSName.C;
/* 337 */     Revisions<String> classNames = new Revisions<String>();
/* 338 */     COSBase c = getCOSObject().getDictionaryObject(key);
/* 339 */     if (c instanceof COSName)
/*     */     {
/* 341 */       classNames.addObject(((COSName)c).getName(), 0);
/*     */     }
/* 343 */     if (c instanceof COSArray) {
/*     */       
/* 345 */       COSArray array = (COSArray)c;
/* 346 */       Iterator<COSBase> it = array.iterator();
/* 347 */       String className = null;
/* 348 */       while (it.hasNext()) {
/*     */         
/* 350 */         COSBase item = it.next();
/* 351 */         if (item instanceof COSObject)
/*     */         {
/* 353 */           item = ((COSObject)item).getObject();
/*     */         }
/* 355 */         if (item instanceof COSName) {
/*     */           
/* 357 */           className = ((COSName)item).getName();
/* 358 */           classNames.addObject(className, 0); continue;
/*     */         } 
/* 360 */         if (item instanceof COSInteger)
/*     */         {
/* 362 */           classNames.setRevisionNumber(className, ((COSInteger)item).intValue());
/*     */         }
/*     */       } 
/*     */     } 
/* 366 */     return classNames;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setClassNames(Revisions<String> classNames) {
/* 376 */     if (classNames == null) {
/*     */       return;
/*     */     }
/*     */     
/* 380 */     COSName key = COSName.C;
/* 381 */     if (classNames.size() == 1 && classNames.getRevisionNumber(0) == 0) {
/*     */       
/* 383 */       String className = classNames.getObject(0);
/* 384 */       getCOSObject().setName(key, className);
/*     */       return;
/*     */     } 
/* 387 */     COSArray array = new COSArray();
/* 388 */     for (int i = 0; i < classNames.size(); i++) {
/*     */       
/* 390 */       String className = classNames.getObject(i);
/* 391 */       int revisionNumber = classNames.getRevisionNumber(i);
/* 392 */       if (revisionNumber < 0)
/*     */       {
/* 394 */         throw new IllegalArgumentException("The revision number shall be > -1");
/*     */       }
/* 396 */       array.add((COSBase)COSName.getPDFName(className));
/* 397 */       array.add((COSBase)COSInteger.get(revisionNumber));
/*     */     } 
/* 399 */     getCOSObject().setItem(key, (COSBase)array);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addClassName(String className) {
/*     */     COSArray array;
/* 409 */     if (className == null) {
/*     */       return;
/*     */     }
/*     */     
/* 413 */     COSName key = COSName.C;
/* 414 */     COSBase c = getCOSObject().getDictionaryObject(key);
/*     */     
/* 416 */     if (c instanceof COSArray) {
/*     */       
/* 418 */       array = (COSArray)c;
/*     */     }
/*     */     else {
/*     */       
/* 422 */       array = new COSArray();
/* 423 */       if (c != null) {
/*     */         
/* 425 */         array.add(c);
/* 426 */         array.add((COSBase)COSInteger.get(0L));
/*     */       } 
/*     */     } 
/* 429 */     getCOSObject().setItem(key, (COSBase)array);
/* 430 */     array.add((COSBase)COSName.getPDFName(className));
/* 431 */     array.add((COSBase)COSInteger.get(getRevisionNumber()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeClassName(String className) {
/* 441 */     if (className == null) {
/*     */       return;
/*     */     }
/*     */     
/* 445 */     COSName key = COSName.C;
/* 446 */     COSBase c = getCOSObject().getDictionaryObject(key);
/* 447 */     COSName name = COSName.getPDFName(className);
/* 448 */     if (c instanceof COSArray) {
/*     */       
/* 450 */       COSArray array = (COSArray)c;
/* 451 */       array.remove((COSBase)name);
/* 452 */       if (array.size() == 2 && array.getInt(1) == 0)
/*     */       {
/* 454 */         getCOSObject().setItem(key, array.getObject(0));
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 459 */       COSBase directC = c;
/* 460 */       if (c instanceof COSObject)
/*     */       {
/* 462 */         directC = ((COSObject)c).getObject();
/*     */       }
/* 464 */       if (name.equals(directC))
/*     */       {
/* 466 */         getCOSObject().setItem(key, null);
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
/*     */   public int getRevisionNumber() {
/* 478 */     return getCOSObject().getInt(COSName.R, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRevisionNumber(int revisionNumber) {
/* 488 */     if (revisionNumber < 0)
/*     */     {
/* 490 */       throw new IllegalArgumentException("The revision number shall be > -1");
/*     */     }
/* 492 */     getCOSObject().setInt(COSName.R, revisionNumber);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void incrementRevisionNumber() {
/* 500 */     setRevisionNumber(getRevisionNumber() + 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTitle() {
/* 510 */     return getCOSObject().getString(COSName.T);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTitle(String title) {
/* 520 */     getCOSObject().setString(COSName.T, title);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLanguage() {
/* 530 */     return getCOSObject().getString(COSName.LANG);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLanguage(String language) {
/* 540 */     getCOSObject().setString(COSName.LANG, language);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAlternateDescription() {
/* 550 */     return getCOSObject().getString(COSName.ALT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAlternateDescription(String alternateDescription) {
/* 560 */     getCOSObject().setString(COSName.ALT, alternateDescription);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getExpandedForm() {
/* 570 */     return getCOSObject().getString(COSName.E);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExpandedForm(String expandedForm) {
/* 580 */     getCOSObject().setString(COSName.E, expandedForm);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getActualText() {
/* 590 */     return getCOSObject().getString(COSName.ACTUAL_TEXT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setActualText(String actualText) {
/* 600 */     getCOSObject().setString(COSName.ACTUAL_TEXT, actualText);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStandardStructureType() {
/* 611 */     String type = getStructureType();
/* 612 */     Map<String, Object> roleMap = getRoleMap();
/* 613 */     if (roleMap.containsKey(type)) {
/*     */       
/* 615 */       Object mappedValue = getRoleMap().get(type);
/* 616 */       if (mappedValue instanceof String)
/*     */       {
/* 618 */         type = (String)mappedValue;
/*     */       }
/*     */     } 
/* 621 */     return type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void appendKid(PDMarkedContent markedContent) {
/* 631 */     if (markedContent == null) {
/*     */       return;
/*     */     }
/*     */     
/* 635 */     appendKid((COSBase)COSInteger.get(markedContent.getMCID()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void appendKid(PDMarkedContentReference markedContentReference) {
/* 645 */     appendObjectableKid(markedContentReference);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void appendKid(PDObjectReference objectReference) {
/* 655 */     appendObjectableKid(objectReference);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void insertBefore(COSInteger markedContentIdentifier, Object refKid) {
/* 666 */     insertBefore((COSBase)markedContentIdentifier, refKid);
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
/*     */   public void insertBefore(PDMarkedContentReference markedContentReference, Object refKid) {
/* 678 */     insertObjectableBefore(markedContentReference, refKid);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void insertBefore(PDObjectReference objectReference, Object refKid) {
/* 689 */     insertObjectableBefore(objectReference, refKid);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeKid(COSInteger markedContentIdentifier) {
/* 699 */     removeKid((COSBase)markedContentIdentifier);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeKid(PDMarkedContentReference markedContentReference) {
/* 709 */     removeObjectableKid(markedContentReference);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeKid(PDObjectReference objectReference) {
/* 719 */     removeObjectableKid(objectReference);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private PDStructureTreeRoot getStructureTreeRoot() {
/* 730 */     PDStructureNode parent = getParent();
/* 731 */     while (parent instanceof PDStructureElement)
/*     */     {
/* 733 */       parent = ((PDStructureElement)parent).getParent();
/*     */     }
/* 735 */     if (parent instanceof PDStructureTreeRoot)
/*     */     {
/* 737 */       return (PDStructureTreeRoot)parent;
/*     */     }
/* 739 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Map<String, Object> getRoleMap() {
/* 749 */     PDStructureTreeRoot root = getStructureTreeRoot();
/* 750 */     if (root != null)
/*     */     {
/* 752 */       return root.getRoleMap();
/*     */     }
/* 754 */     return null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/documentinterchange/logicalstructure/PDStructureElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */