/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import java.beans.BeanInfo;
/*     */ import java.beans.IntrospectionException;
/*     */ import java.beans.Introspector;
/*     */ import java.beans.PropertyDescriptor;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.AbstractMap;
/*     */ import java.util.AbstractSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.keyvalue.AbstractMapEntry;
/*     */ import org.apache.commons.collections.list.UnmodifiableList;
/*     */ import org.apache.commons.collections.set.UnmodifiableSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BeanMap
/*     */   extends AbstractMap
/*     */   implements Cloneable
/*     */ {
/*     */   private transient Object bean;
/*  54 */   private transient HashMap readMethods = new HashMap();
/*  55 */   private transient HashMap writeMethods = new HashMap();
/*  56 */   private transient HashMap types = new HashMap();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   public static final Object[] NULL_ARGUMENTS = new Object[0];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   public static HashMap defaultTransformers = new HashMap();
/*     */   
/*     */   static {
/*  70 */     defaultTransformers.put(boolean.class, new Transformer()
/*     */         {
/*     */           public Object transform(Object input)
/*     */           {
/*  74 */             return Boolean.valueOf(input.toString());
/*     */           }
/*     */         });
/*     */     
/*  78 */     defaultTransformers.put(char.class, new Transformer()
/*     */         {
/*     */           public Object transform(Object input)
/*     */           {
/*  82 */             return new Character(input.toString().charAt(0));
/*     */           }
/*     */         });
/*     */     
/*  86 */     defaultTransformers.put(byte.class, new Transformer()
/*     */         {
/*     */           public Object transform(Object input)
/*     */           {
/*  90 */             return Byte.valueOf(input.toString());
/*     */           }
/*     */         });
/*     */     
/*  94 */     defaultTransformers.put(short.class, new Transformer()
/*     */         {
/*     */           public Object transform(Object input)
/*     */           {
/*  98 */             return Short.valueOf(input.toString());
/*     */           }
/*     */         });
/*     */     
/* 102 */     defaultTransformers.put(int.class, new Transformer()
/*     */         {
/*     */           public Object transform(Object input)
/*     */           {
/* 106 */             return Integer.valueOf(input.toString());
/*     */           }
/*     */         });
/*     */     
/* 110 */     defaultTransformers.put(long.class, new Transformer()
/*     */         {
/*     */           public Object transform(Object input)
/*     */           {
/* 114 */             return Long.valueOf(input.toString());
/*     */           }
/*     */         });
/*     */     
/* 118 */     defaultTransformers.put(float.class, new Transformer()
/*     */         {
/*     */           public Object transform(Object input)
/*     */           {
/* 122 */             return Float.valueOf(input.toString());
/*     */           }
/*     */         });
/*     */     
/* 126 */     defaultTransformers.put(double.class, new Transformer()
/*     */         {
/*     */           public Object transform(Object input)
/*     */           {
/* 130 */             return Double.valueOf(input.toString());
/*     */           }
/*     */         });
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
/*     */   public BeanMap(Object bean) {
/* 154 */     this.bean = bean;
/* 155 */     initialise();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 162 */     return "BeanMap<" + String.valueOf(this.bean) + ">";
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
/*     */ 
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 189 */     BeanMap newMap = (BeanMap)super.clone();
/*     */     
/* 191 */     if (this.bean == null)
/*     */     {
/*     */       
/* 194 */       return newMap;
/*     */     }
/*     */     
/* 197 */     Object newBean = null;
/* 198 */     Class beanClass = null;
/*     */     try {
/* 200 */       beanClass = this.bean.getClass();
/* 201 */       newBean = beanClass.newInstance();
/* 202 */     } catch (Exception e) {
/*     */       
/* 204 */       throw new CloneNotSupportedException("Unable to instantiate the underlying bean \"" + beanClass.getName() + "\": " + e);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 210 */       newMap.setBean(newBean);
/* 211 */     } catch (Exception exception) {
/* 212 */       throw new CloneNotSupportedException("Unable to set bean in the cloned bean map: " + exception);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 221 */       Iterator readableKeys = this.readMethods.keySet().iterator();
/* 222 */       while (readableKeys.hasNext()) {
/* 223 */         Object key = readableKeys.next();
/* 224 */         if (getWriteMethod(key) != null) {
/* 225 */           newMap.put(key, get(key));
/*     */         }
/*     */       } 
/* 228 */     } catch (Exception exception) {
/* 229 */       throw new CloneNotSupportedException("Unable to copy bean values to cloned bean map: " + exception);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 234 */     return newMap;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void putAllWriteable(BeanMap map) {
/* 244 */     Iterator readableKeys = map.readMethods.keySet().iterator();
/* 245 */     while (readableKeys.hasNext()) {
/* 246 */       Object key = readableKeys.next();
/* 247 */       if (getWriteMethod(key) != null) {
/* 248 */         put(key, map.get(key));
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
/*     */ 
/*     */   
/*     */   public void clear() {
/* 263 */     if (this.bean == null)
/*     */       return; 
/* 265 */     Class beanClass = null;
/*     */     try {
/* 267 */       beanClass = this.bean.getClass();
/* 268 */       this.bean = beanClass.newInstance();
/*     */     }
/* 270 */     catch (Exception e) {
/* 271 */       throw new UnsupportedOperationException("Could not create new instance of class: " + beanClass);
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
/*     */   public boolean containsKey(Object name) {
/* 291 */     Method method = getReadMethod(name);
/* 292 */     return (method != null);
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
/*     */   public boolean containsValue(Object value) {
/* 305 */     return super.containsValue(value);
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
/*     */   public Object get(Object name) {
/* 324 */     if (this.bean != null) {
/* 325 */       Method method = getReadMethod(name);
/* 326 */       if (method != null) {
/*     */         try {
/* 328 */           return method.invoke(this.bean, NULL_ARGUMENTS);
/*     */         }
/* 330 */         catch (IllegalAccessException e) {
/* 331 */           logWarn(e);
/*     */         }
/* 333 */         catch (IllegalArgumentException e) {
/* 334 */           logWarn(e);
/*     */         }
/* 336 */         catch (InvocationTargetException e) {
/* 337 */           logWarn(e);
/*     */         }
/* 339 */         catch (NullPointerException e) {
/* 340 */           logWarn(e);
/*     */         } 
/*     */       }
/*     */     } 
/* 344 */     return null;
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
/*     */   public Object put(Object name, Object value) throws IllegalArgumentException, ClassCastException {
/* 359 */     if (this.bean != null) {
/* 360 */       Object oldValue = get(name);
/* 361 */       Method method = getWriteMethod(name);
/* 362 */       if (method == null) {
/* 363 */         throw new IllegalArgumentException("The bean of type: " + this.bean.getClass().getName() + " has no property called: " + name);
/*     */       }
/*     */       try {
/* 366 */         Object[] arguments = createWriteMethodArguments(method, value);
/* 367 */         method.invoke(this.bean, arguments);
/*     */         
/* 369 */         Object newValue = get(name);
/* 370 */         firePropertyChange(name, oldValue, newValue);
/*     */       }
/* 372 */       catch (InvocationTargetException e) {
/* 373 */         logInfo(e);
/* 374 */         throw new IllegalArgumentException(e.getMessage());
/*     */       }
/* 376 */       catch (IllegalAccessException e) {
/* 377 */         logInfo(e);
/* 378 */         throw new IllegalArgumentException(e.getMessage());
/*     */       } 
/* 380 */       return oldValue;
/*     */     } 
/* 382 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 391 */     return this.readMethods.size();
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
/*     */   public Set keySet() {
/* 406 */     return UnmodifiableSet.decorate(this.readMethods.keySet());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set entrySet() {
/* 417 */     return UnmodifiableSet.decorate(new AbstractSet(this) { private final BeanMap this$0;
/*     */           public Iterator iterator() {
/* 419 */             return this.this$0.entryIterator();
/*     */           }
/*     */           public int size() {
/* 422 */             return this.this$0.readMethods.size();
/*     */           } }
/*     */       );
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection values() {
/* 434 */     ArrayList answer = new ArrayList(this.readMethods.size());
/* 435 */     for (Iterator iter = valueIterator(); iter.hasNext();) {
/* 436 */       answer.add(iter.next());
/*     */     }
/* 438 */     return UnmodifiableList.decorate(answer);
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
/*     */   public Class getType(String name) {
/* 453 */     return (Class)this.types.get(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator keyIterator() {
/* 464 */     return this.readMethods.keySet().iterator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator valueIterator() {
/* 473 */     Iterator iter = keyIterator();
/* 474 */     return new Iterator(this, iter) { private final Iterator val$iter;
/*     */         public boolean hasNext() {
/* 476 */           return this.val$iter.hasNext();
/*     */         } private final BeanMap this$0;
/*     */         public Object next() {
/* 479 */           Object key = this.val$iter.next();
/* 480 */           return this.this$0.get(key);
/*     */         }
/*     */         public void remove() {
/* 483 */           throw new UnsupportedOperationException("remove() not supported for BeanMap");
/*     */         } }
/*     */       ;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator entryIterator() {
/* 494 */     Iterator iter = keyIterator();
/* 495 */     return new Iterator(this, iter) { private final Iterator val$iter; private final BeanMap this$0;
/*     */         public boolean hasNext() {
/* 497 */           return this.val$iter.hasNext();
/*     */         }
/*     */         public Object next() {
/* 500 */           Object key = this.val$iter.next();
/* 501 */           Object value = this.this$0.get(key);
/* 502 */           return new BeanMap.MyMapEntry(this.this$0, key, value);
/*     */         }
/*     */         public void remove() {
/* 505 */           throw new UnsupportedOperationException("remove() not supported for BeanMap");
/*     */         } }
/*     */       ;
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
/*     */   public Object getBean() {
/* 521 */     return this.bean;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBean(Object newBean) {
/* 531 */     this.bean = newBean;
/* 532 */     reinitialise();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Method getReadMethod(String name) {
/* 542 */     return (Method)this.readMethods.get(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Method getWriteMethod(String name) {
/* 552 */     return (Method)this.writeMethods.get(name);
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
/*     */   protected Method getReadMethod(Object name) {
/* 568 */     return (Method)this.readMethods.get(name);
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
/*     */   protected Method getWriteMethod(Object name) {
/* 580 */     return (Method)this.writeMethods.get(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void reinitialise() {
/* 588 */     this.readMethods.clear();
/* 589 */     this.writeMethods.clear();
/* 590 */     this.types.clear();
/* 591 */     initialise();
/*     */   }
/*     */   
/*     */   private void initialise() {
/* 595 */     if (getBean() == null)
/*     */       return; 
/* 597 */     Class beanClass = getBean().getClass();
/*     */     
/*     */     try {
/* 600 */       BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);
/* 601 */       PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
/* 602 */       if (propertyDescriptors != null) {
/* 603 */         for (int i = 0; i < propertyDescriptors.length; i++) {
/* 604 */           PropertyDescriptor propertyDescriptor = propertyDescriptors[i];
/* 605 */           if (propertyDescriptor != null) {
/* 606 */             String name = propertyDescriptor.getName();
/* 607 */             Method readMethod = propertyDescriptor.getReadMethod();
/* 608 */             Method writeMethod = propertyDescriptor.getWriteMethod();
/* 609 */             Class aType = propertyDescriptor.getPropertyType();
/*     */             
/* 611 */             if (readMethod != null) {
/* 612 */               this.readMethods.put(name, readMethod);
/*     */             }
/* 614 */             if (this.writeMethods != null) {
/* 615 */               this.writeMethods.put(name, writeMethod);
/*     */             }
/* 617 */             this.types.put(name, aType);
/*     */           }
/*     */         
/*     */         } 
/*     */       }
/* 622 */     } catch (IntrospectionException e) {
/* 623 */       logWarn(e);
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
/*     */   protected void firePropertyChange(Object key, Object oldValue, Object newValue) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class MyMapEntry
/*     */     extends AbstractMapEntry
/*     */   {
/*     */     private BeanMap owner;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected MyMapEntry(BeanMap owner, Object key, Object value) {
/* 656 */       super(key, value);
/* 657 */       this.owner = owner;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object setValue(Object value) {
/* 667 */       Object key = getKey();
/* 668 */       Object oldValue = this.owner.get(key);
/*     */       
/* 670 */       this.owner.put(key, value);
/* 671 */       Object newValue = this.owner.get(key);
/* 672 */       super.setValue(newValue);
/* 673 */       return oldValue;
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
/*     */   protected Object[] createWriteMethodArguments(Method method, Object value) throws IllegalAccessException, ClassCastException {
/*     */     try {
/* 693 */       if (value != null) {
/* 694 */         Class[] types = method.getParameterTypes();
/* 695 */         if (types != null && types.length > 0) {
/* 696 */           Class paramType = types[0];
/* 697 */           if (!paramType.isAssignableFrom(value.getClass())) {
/* 698 */             value = convertType(paramType, value);
/*     */           }
/*     */         } 
/*     */       } 
/* 702 */       Object[] answer = { value };
/* 703 */       return answer;
/*     */     }
/* 705 */     catch (InvocationTargetException e) {
/* 706 */       logInfo(e);
/* 707 */       throw new IllegalArgumentException(e.getMessage());
/*     */     }
/* 709 */     catch (InstantiationException e) {
/* 710 */       logInfo(e);
/* 711 */       throw new IllegalArgumentException(e.getMessage());
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object convertType(Class newType, Object value) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
/* 750 */     Class[] types = { value.getClass() };
/*     */     try {
/* 752 */       Constructor constructor = newType.getConstructor(types);
/* 753 */       Object[] arguments = { value };
/* 754 */       return constructor.newInstance(arguments);
/*     */     }
/* 756 */     catch (NoSuchMethodException e) {
/*     */       
/* 758 */       Transformer transformer = getTypeTransformer(newType);
/* 759 */       if (transformer != null) {
/* 760 */         return transformer.transform(value);
/*     */       }
/* 762 */       return value;
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
/*     */   protected Transformer getTypeTransformer(Class aType) {
/* 774 */     return (Transformer)defaultTransformers.get(aType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void logInfo(Exception ex) {
/* 785 */     System.out.println("INFO: Exception: " + ex);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void logWarn(Exception ex) {
/* 796 */     System.out.println("WARN: Exception: " + ex);
/* 797 */     ex.printStackTrace();
/*     */   }
/*     */   
/*     */   public BeanMap() {}
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/BeanMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */