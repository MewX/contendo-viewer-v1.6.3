/*     */ package org.apache.pdfbox.cos;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
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
/*     */ public class COSArray
/*     */   extends COSBase
/*     */   implements Iterable<COSBase>, COSUpdateInfo
/*     */ {
/*  34 */   private final List<COSBase> objects = new ArrayList<COSBase>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean needToBeUpdated;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(COSBase object) {
/*  52 */     this.objects.add(object);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(COSObjectable object) {
/*  62 */     this.objects.add(object.getCOSObject());
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
/*     */   public void add(int i, COSBase object) {
/*  74 */     this.objects.add(i, object);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/*  82 */     this.objects.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeAll(Collection<COSBase> objectsList) {
/*  92 */     this.objects.removeAll(objectsList);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void retainAll(Collection<COSBase> objectsList) {
/* 102 */     this.objects.retainAll(objectsList);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAll(Collection<COSBase> objectsList) {
/* 112 */     this.objects.addAll(objectsList);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAll(COSArray objectList) {
/* 122 */     if (objectList != null)
/*     */     {
/* 124 */       this.objects.addAll(objectList.objects);
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
/*     */   public void addAll(int i, Collection<COSBase> objectList) {
/* 137 */     this.objects.addAll(i, objectList);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(int index, COSBase object) {
/* 148 */     this.objects.set(index, object);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(int index, int intVal) {
/* 159 */     this.objects.set(index, COSInteger.get(intVal));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(int index, COSObjectable object) {
/* 170 */     COSBase base = null;
/* 171 */     if (object != null)
/*     */     {
/* 173 */       base = object.getCOSObject();
/*     */     }
/* 175 */     this.objects.set(index, base);
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
/*     */   public COSBase getObject(int index) {
/* 188 */     Object obj = this.objects.get(index);
/* 189 */     if (obj instanceof COSObject)
/*     */     {
/* 191 */       obj = ((COSObject)obj).getObject();
/*     */     }
/* 193 */     if (obj instanceof COSNull)
/*     */     {
/* 195 */       obj = null;
/*     */     }
/* 197 */     return (COSBase)obj;
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
/*     */   public COSBase get(int index) {
/* 210 */     return this.objects.get(index);
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
/*     */   public int getInt(int index) {
/* 222 */     return getInt(index, -1);
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
/*     */   public int getInt(int index, int defaultValue) {
/* 234 */     int retval = defaultValue;
/* 235 */     if (index < size()) {
/*     */       
/* 237 */       Object obj = this.objects.get(index);
/* 238 */       if (obj instanceof COSNumber)
/*     */       {
/* 240 */         retval = ((COSNumber)obj).intValue();
/*     */       }
/*     */     } 
/* 243 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInt(int index, int value) {
/* 254 */     set(index, COSInteger.get(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(int index, String name) {
/* 264 */     set(index, COSName.getPDFName(name));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName(int index) {
/* 275 */     return getName(index, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName(int index, String defaultValue) {
/* 286 */     String retval = defaultValue;
/* 287 */     if (index < size()) {
/*     */       
/* 289 */       Object obj = this.objects.get(index);
/* 290 */       if (obj instanceof COSName)
/*     */       {
/* 292 */         retval = ((COSName)obj).getName();
/*     */       }
/*     */     } 
/* 295 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setString(int index, String string) {
/* 305 */     if (string != null) {
/*     */       
/* 307 */       set(index, new COSString(string));
/*     */     }
/*     */     else {
/*     */       
/* 311 */       set(index, (COSBase)null);
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
/*     */   public String getString(int index) {
/* 323 */     return getString(index, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getString(int index, String defaultValue) {
/* 334 */     String retval = defaultValue;
/* 335 */     if (index < size()) {
/*     */       
/* 337 */       Object obj = this.objects.get(index);
/* 338 */       if (obj instanceof COSString)
/*     */       {
/* 340 */         retval = ((COSString)obj).getString();
/*     */       }
/*     */     } 
/* 343 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 353 */     return this.objects.size();
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
/*     */   public COSBase remove(int i) {
/* 365 */     return this.objects.remove(i);
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
/*     */   public boolean remove(COSBase o) {
/* 378 */     return this.objects.remove(o);
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
/*     */   public boolean removeObject(COSBase o) {
/* 391 */     boolean removed = remove(o);
/* 392 */     if (!removed)
/*     */     {
/* 394 */       for (int i = 0; i < size(); i++) {
/*     */         
/* 396 */         COSBase entry = get(i);
/* 397 */         if (entry instanceof COSObject) {
/*     */           
/* 399 */           COSObject objEntry = (COSObject)entry;
/* 400 */           if (objEntry.getObject().equals(o))
/*     */           {
/* 402 */             return remove(entry);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/* 407 */     return removed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 416 */     return "COSArray{" + this.objects + "}";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator<COSBase> iterator() {
/* 427 */     return this.objects.iterator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int indexOf(COSBase object) {
/* 438 */     int retval = -1;
/* 439 */     for (int i = 0; retval < 0 && i < size(); i++) {
/*     */       
/* 441 */       if (get(i).equals(object))
/*     */       {
/* 443 */         retval = i;
/*     */       }
/*     */     } 
/* 446 */     return retval;
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
/*     */   public int indexOfObject(COSBase object) {
/* 458 */     int retval = -1;
/* 459 */     for (int i = 0; retval < 0 && i < size(); i++) {
/*     */       
/* 461 */       COSBase item = get(i);
/* 462 */       if (item.equals(object)) {
/*     */         
/* 464 */         retval = i;
/*     */         break;
/*     */       } 
/* 467 */       if (item instanceof COSObject && ((COSObject)item).getObject().equals(object)) {
/*     */         
/* 469 */         retval = i;
/*     */         break;
/*     */       } 
/*     */     } 
/* 473 */     return retval;
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
/*     */   public void growToSize(int size) {
/* 485 */     growToSize(size, null);
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
/*     */   public void growToSize(int size, COSBase object) {
/* 498 */     while (size() < size)
/*     */     {
/* 500 */       add(object);
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
/*     */   public Object accept(ICOSVisitor visitor) throws IOException {
/* 514 */     return visitor.visitFromArray(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isNeedToBeUpdated() {
/* 520 */     return this.needToBeUpdated;
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
/*     */   public void setNeedToBeUpdated(boolean flag) {
/* 535 */     this.needToBeUpdated = flag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] toFloatArray() {
/* 545 */     float[] retval = new float[size()];
/* 546 */     for (int i = 0; i < size(); i++) {
/*     */       
/* 548 */       COSBase base = getObject(i);
/* 549 */       retval[i] = (base instanceof COSNumber) ? ((COSNumber)base)
/* 550 */         .floatValue() : 0.0F;
/*     */     } 
/* 552 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFloatArray(float[] value) {
/* 562 */     clear();
/* 563 */     for (float aValue : value)
/*     */     {
/* 565 */       add(new COSFloat(aValue));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<? extends COSBase> toList() {
/* 576 */     List<COSBase> retList = new ArrayList<COSBase>(size());
/* 577 */     for (int i = 0; i < size(); i++)
/*     */     {
/* 579 */       retList.add(get(i));
/*     */     }
/* 581 */     return retList;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/cos/COSArray.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */