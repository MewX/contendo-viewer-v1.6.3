/*     */ package org.apache.pdfbox.pdmodel.common;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSFloat;
/*     */ import org.apache.pdfbox.cos.COSInteger;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSNull;
/*     */ import org.apache.pdfbox.cos.COSNumber;
/*     */ import org.apache.pdfbox.cos.COSObject;
/*     */ import org.apache.pdfbox.cos.COSString;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class COSArrayList<E>
/*     */   implements List<E>
/*     */ {
/*     */   private final COSArray array;
/*     */   private final List<E> actual;
/*     */   private COSDictionary parentDict;
/*     */   private COSName dictKey;
/*     */   
/*     */   public COSArrayList() {
/*  54 */     this.array = new COSArray();
/*  55 */     this.actual = new ArrayList<E>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSArrayList(List<E> actualList, COSArray cosArray) {
/*  66 */     this.actual = actualList;
/*  67 */     this.array = cosArray;
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
/*     */   public COSArrayList(COSDictionary dictionary, COSName dictionaryKey) {
/*  80 */     this.array = new COSArray();
/*  81 */     this.actual = new ArrayList<E>();
/*  82 */     this.parentDict = dictionary;
/*  83 */     this.dictKey = dictionaryKey;
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
/*     */   public COSArrayList(E actualObject, COSBase item, COSDictionary dictionary, COSName dictionaryKey) {
/* 103 */     this.array = new COSArray();
/* 104 */     this.array.add(item);
/* 105 */     this.actual = new ArrayList<E>();
/* 106 */     this.actual.add(actualObject);
/*     */     
/* 108 */     this.parentDict = dictionary;
/* 109 */     this.dictKey = dictionaryKey;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 118 */     return this.actual.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 127 */     return this.actual.isEmpty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean contains(Object o) {
/* 136 */     return this.actual.contains(o);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator<E> iterator() {
/* 145 */     return this.actual.iterator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object[] toArray() {
/* 154 */     return this.actual.toArray();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <X> X[] toArray(X[] a) {
/* 163 */     return this.actual.toArray(a);
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
/*     */   public boolean add(E o) {
/* 175 */     if (this.parentDict != null) {
/*     */       
/* 177 */       this.parentDict.setItem(this.dictKey, (COSBase)this.array);
/*     */ 
/*     */       
/* 180 */       this.parentDict = null;
/*     */     } 
/*     */     
/* 183 */     if (o instanceof String) {
/*     */       
/* 185 */       this.array.add((COSBase)new COSString((String)o));
/*     */ 
/*     */     
/*     */     }
/* 189 */     else if (this.array != null) {
/*     */       
/* 191 */       this.array.add(((COSObjectable)o).getCOSObject());
/*     */     } 
/*     */     
/* 194 */     return this.actual.add(o);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean remove(Object o) {
/* 203 */     boolean retval = true;
/* 204 */     int index = this.actual.indexOf(o);
/* 205 */     if (index >= 0) {
/*     */       
/* 207 */       this.actual.remove(index);
/* 208 */       this.array.remove(index);
/*     */     }
/*     */     else {
/*     */       
/* 212 */       retval = false;
/*     */     } 
/* 214 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsAll(Collection<?> c) {
/* 223 */     return this.actual.containsAll(c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addAll(Collection<? extends E> c) {
/* 234 */     if (this.parentDict != null && c.size() > 0) {
/*     */       
/* 236 */       this.parentDict.setItem(this.dictKey, (COSBase)this.array);
/*     */ 
/*     */       
/* 239 */       this.parentDict = null;
/*     */     } 
/* 241 */     this.array.addAll(toCOSObjectList(c));
/* 242 */     return this.actual.addAll(c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addAll(int index, Collection<? extends E> c) {
/* 253 */     if (this.parentDict != null && c.size() > 0) {
/*     */       
/* 255 */       this.parentDict.setItem(this.dictKey, (COSBase)this.array);
/*     */ 
/*     */       
/* 258 */       this.parentDict = null;
/*     */     } 
/*     */     
/* 261 */     this.array.addAll(index, toCOSObjectList(c));
/* 262 */     return this.actual.addAll(index, c);
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
/*     */   public static List<Integer> convertIntegerCOSArrayToList(COSArray intArray) {
/* 275 */     List<Integer> retval = null;
/* 276 */     if (intArray != null) {
/*     */       
/* 278 */       List<Integer> numbers = new ArrayList<Integer>();
/* 279 */       for (int i = 0; i < intArray.size(); i++) {
/*     */         COSNumber num;
/*     */         
/* 282 */         if (intArray.get(i) instanceof COSObject) {
/*     */           
/* 284 */           num = (COSNumber)((COSObject)intArray.get(i)).getObject();
/*     */         }
/*     */         else {
/*     */           
/* 288 */           num = (COSNumber)intArray.get(i);
/*     */         } 
/* 290 */         numbers.add(Integer.valueOf(num.intValue()));
/*     */       } 
/* 292 */       retval = new COSArrayList<Integer>(numbers, intArray);
/*     */     } 
/* 294 */     return retval;
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
/*     */   public static List<Float> convertFloatCOSArrayToList(COSArray floatArray) {
/* 307 */     List<Float> retval = null;
/* 308 */     if (floatArray != null) {
/*     */       
/* 310 */       List<Float> numbers = new ArrayList<Float>(floatArray.size());
/* 311 */       for (int i = 0; i < floatArray.size(); i++) {
/*     */         
/* 313 */         COSBase base = floatArray.getObject(i);
/* 314 */         if (base instanceof COSNumber) {
/*     */           
/* 316 */           numbers.add(Float.valueOf(((COSNumber)base).floatValue()));
/*     */         }
/*     */         else {
/*     */           
/* 320 */           numbers.add(null);
/*     */         } 
/*     */       } 
/* 323 */       retval = new COSArrayList<Float>(numbers, floatArray);
/*     */     } 
/* 325 */     return retval;
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
/*     */   public static List<String> convertCOSNameCOSArrayToList(COSArray nameArray) {
/* 338 */     List<String> retval = null;
/* 339 */     if (nameArray != null) {
/*     */       
/* 341 */       List<String> names = new ArrayList<String>();
/* 342 */       for (int i = 0; i < nameArray.size(); i++)
/*     */       {
/* 344 */         names.add(((COSName)nameArray.getObject(i)).getName());
/*     */       }
/* 346 */       retval = new COSArrayList<String>(names, nameArray);
/*     */     } 
/* 348 */     return retval;
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
/*     */   public static List<String> convertCOSStringCOSArrayToList(COSArray stringArray) {
/* 361 */     List<String> retval = null;
/* 362 */     if (stringArray != null) {
/*     */       
/* 364 */       List<String> string = new ArrayList<String>();
/* 365 */       for (int i = 0; i < stringArray.size(); i++)
/*     */       {
/* 367 */         string.add(((COSString)stringArray.getObject(i)).getString());
/*     */       }
/* 369 */       retval = new COSArrayList<String>(string, stringArray);
/*     */     } 
/* 371 */     return retval;
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
/*     */   public static COSArray convertStringListToCOSNameCOSArray(List<String> strings) {
/* 384 */     COSArray retval = new COSArray();
/* 385 */     for (String string : strings)
/*     */     {
/* 387 */       retval.add((COSBase)COSName.getPDFName(string));
/*     */     }
/* 389 */     return retval;
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
/*     */   public static COSArray convertStringListToCOSStringCOSArray(List<String> strings) {
/* 402 */     COSArray retval = new COSArray();
/* 403 */     for (String string : strings)
/*     */     {
/* 405 */       retval.add((COSBase)new COSString(string));
/*     */     }
/* 407 */     return retval;
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
/*     */   public static COSArray converterToCOSArray(List<?> cosObjectableList) {
/* 421 */     COSArray array = null;
/* 422 */     if (cosObjectableList != null)
/*     */     {
/* 424 */       if (cosObjectableList instanceof COSArrayList) {
/*     */ 
/*     */         
/* 427 */         array = ((COSArrayList)cosObjectableList).array;
/*     */       }
/*     */       else {
/*     */         
/* 431 */         array = new COSArray();
/* 432 */         for (Object next : cosObjectableList) {
/*     */           
/* 434 */           if (next instanceof String) {
/*     */             
/* 436 */             array.add((COSBase)new COSString((String)next)); continue;
/*     */           } 
/* 438 */           if (next instanceof Integer || next instanceof Long) {
/*     */             
/* 440 */             array.add((COSBase)COSInteger.get(((Number)next).longValue())); continue;
/*     */           } 
/* 442 */           if (next instanceof Float || next instanceof Double) {
/*     */             
/* 444 */             array.add((COSBase)new COSFloat(((Number)next).floatValue())); continue;
/*     */           } 
/* 446 */           if (next instanceof COSObjectable) {
/*     */             
/* 448 */             COSObjectable object = (COSObjectable)next;
/* 449 */             array.add(object.getCOSObject()); continue;
/*     */           } 
/* 451 */           if (next == null) {
/*     */             
/* 453 */             array.add((COSBase)COSNull.NULL);
/*     */             
/*     */             continue;
/*     */           } 
/* 457 */           throw new IllegalArgumentException("Error: Don't know how to convert type to COSBase '" + next
/* 458 */               .getClass().getName() + "'");
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 463 */     return array;
/*     */   }
/*     */ 
/*     */   
/*     */   private List<COSBase> toCOSObjectList(Collection<?> list) {
/* 468 */     List<COSBase> cosObjects = new ArrayList<COSBase>();
/* 469 */     for (Object next : list) {
/*     */       
/* 471 */       if (next instanceof String) {
/*     */         
/* 473 */         cosObjects.add(new COSString((String)next));
/*     */         
/*     */         continue;
/*     */       } 
/* 477 */       COSObjectable cos = (COSObjectable)next;
/* 478 */       cosObjects.add(cos.getCOSObject());
/*     */     } 
/*     */     
/* 481 */     return cosObjects;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeAll(Collection<?> c) {
/* 490 */     this.array.removeAll(toCOSObjectList(c));
/* 491 */     return this.actual.removeAll(c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean retainAll(Collection<?> c) {
/* 500 */     this.array.retainAll(toCOSObjectList(c));
/* 501 */     return this.actual.retainAll(c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 512 */     if (this.parentDict != null)
/*     */     {
/* 514 */       this.parentDict.setItem(this.dictKey, null);
/*     */     }
/* 516 */     this.actual.clear();
/* 517 */     this.array.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 526 */     return this.actual.equals(o);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 535 */     return this.actual.hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public E get(int index) {
/* 544 */     return this.actual.get(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public E set(int index, E element) {
/* 554 */     if (element instanceof String) {
/*     */       
/* 556 */       COSString item = new COSString((String)element);
/* 557 */       if (this.parentDict != null && index == 0)
/*     */       {
/* 559 */         this.parentDict.setItem(this.dictKey, (COSBase)item);
/*     */       }
/* 561 */       this.array.set(index, (COSBase)item);
/*     */     }
/*     */     else {
/*     */       
/* 565 */       if (this.parentDict != null && index == 0)
/*     */       {
/* 567 */         this.parentDict.setItem(this.dictKey, ((COSObjectable)element).getCOSObject());
/*     */       }
/* 569 */       this.array.set(index, ((COSObjectable)element).getCOSObject());
/*     */     } 
/* 571 */     return this.actual.set(index, element);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(int index, E element) {
/* 582 */     if (this.parentDict != null) {
/*     */       
/* 584 */       this.parentDict.setItem(this.dictKey, (COSBase)this.array);
/*     */ 
/*     */       
/* 587 */       this.parentDict = null;
/*     */     } 
/* 589 */     this.actual.add(index, element);
/* 590 */     if (element instanceof String) {
/*     */       
/* 592 */       this.array.add(index, (COSBase)new COSString((String)element));
/*     */     }
/*     */     else {
/*     */       
/* 596 */       this.array.add(index, ((COSObjectable)element).getCOSObject());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public E remove(int index) {
/* 606 */     this.array.remove(index);
/* 607 */     return this.actual.remove(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int indexOf(Object o) {
/* 616 */     return this.actual.indexOf(o);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int lastIndexOf(Object o) {
/* 625 */     return this.actual.indexOf(o);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ListIterator<E> listIterator() {
/* 635 */     return this.actual.listIterator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ListIterator<E> listIterator(int index) {
/* 644 */     return this.actual.listIterator(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<E> subList(int fromIndex, int toIndex) {
/* 653 */     return this.actual.subList(fromIndex, toIndex);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 662 */     return "COSArrayList{" + this.array.toString() + "}";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSArray toList() {
/* 672 */     return this.array;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/common/COSArrayList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */