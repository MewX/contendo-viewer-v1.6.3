/*      */ package org.apache.commons.lang3.reflect;
/*      */ 
/*      */ import java.lang.reflect.Array;
/*      */ import java.lang.reflect.GenericArrayType;
/*      */ import java.lang.reflect.GenericDeclaration;
/*      */ import java.lang.reflect.ParameterizedType;
/*      */ import java.lang.reflect.Type;
/*      */ import java.lang.reflect.TypeVariable;
/*      */ import java.lang.reflect.WildcardType;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Objects;
/*      */ import java.util.Set;
/*      */ import org.apache.commons.lang3.ArrayUtils;
/*      */ import org.apache.commons.lang3.ClassUtils;
/*      */ import org.apache.commons.lang3.ObjectUtils;
/*      */ import org.apache.commons.lang3.Validate;
/*      */ import org.apache.commons.lang3.builder.Builder;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class TypeUtils
/*      */ {
/*      */   public static class WildcardTypeBuilder
/*      */     implements Builder<WildcardType>
/*      */   {
/*      */     private Type[] upperBounds;
/*      */     private Type[] lowerBounds;
/*      */     
/*      */     private WildcardTypeBuilder() {}
/*      */     
/*      */     public WildcardTypeBuilder withUpperBounds(Type... bounds) {
/*   69 */       this.upperBounds = bounds;
/*   70 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WildcardTypeBuilder withLowerBounds(Type... bounds) {
/*   79 */       this.lowerBounds = bounds;
/*   80 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WildcardType build() {
/*   88 */       return new TypeUtils.WildcardTypeImpl(this.upperBounds, this.lowerBounds);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final class GenericArrayTypeImpl
/*      */     implements GenericArrayType
/*      */   {
/*      */     private final Type componentType;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private GenericArrayTypeImpl(Type componentType) {
/*  104 */       this.componentType = componentType;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Type getGenericComponentType() {
/*  112 */       return this.componentType;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString() {
/*  120 */       return TypeUtils.toString(this);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean equals(Object obj) {
/*  128 */       return (obj == this || (obj instanceof GenericArrayType && TypeUtils.equals(this, (GenericArrayType)obj)));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int hashCode() {
/*  136 */       int result = 1072;
/*  137 */       result |= this.componentType.hashCode();
/*  138 */       return result;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static final class ParameterizedTypeImpl
/*      */     implements ParameterizedType
/*      */   {
/*      */     private final Class<?> raw;
/*      */ 
/*      */     
/*      */     private final Type useOwner;
/*      */ 
/*      */     
/*      */     private final Type[] typeArguments;
/*      */ 
/*      */ 
/*      */     
/*      */     private ParameterizedTypeImpl(Class<?> raw, Type useOwner, Type[] typeArguments) {
/*  158 */       this.raw = raw;
/*  159 */       this.useOwner = useOwner;
/*  160 */       this.typeArguments = Arrays.<Type, Type>copyOf(typeArguments, typeArguments.length, Type[].class);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Type getRawType() {
/*  168 */       return this.raw;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Type getOwnerType() {
/*  176 */       return this.useOwner;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Type[] getActualTypeArguments() {
/*  184 */       return (Type[])this.typeArguments.clone();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString() {
/*  192 */       return TypeUtils.toString(this);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean equals(Object obj) {
/*  200 */       return (obj == this || (obj instanceof ParameterizedType && TypeUtils.equals(this, (ParameterizedType)obj)));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int hashCode() {
/*  208 */       int result = 1136;
/*  209 */       result |= this.raw.hashCode();
/*  210 */       result <<= 4;
/*  211 */       result |= Objects.hashCode(this.useOwner);
/*  212 */       result <<= 8;
/*  213 */       result |= Arrays.hashCode((Object[])this.typeArguments);
/*  214 */       return result;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static final class WildcardTypeImpl
/*      */     implements WildcardType
/*      */   {
/*  223 */     private static final Type[] EMPTY_BOUNDS = new Type[0];
/*      */ 
/*      */     
/*      */     private final Type[] upperBounds;
/*      */ 
/*      */     
/*      */     private final Type[] lowerBounds;
/*      */ 
/*      */ 
/*      */     
/*      */     private WildcardTypeImpl(Type[] upperBounds, Type[] lowerBounds) {
/*  234 */       this.upperBounds = (Type[])ObjectUtils.defaultIfNull(upperBounds, EMPTY_BOUNDS);
/*  235 */       this.lowerBounds = (Type[])ObjectUtils.defaultIfNull(lowerBounds, EMPTY_BOUNDS);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Type[] getUpperBounds() {
/*  243 */       return (Type[])this.upperBounds.clone();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Type[] getLowerBounds() {
/*  251 */       return (Type[])this.lowerBounds.clone();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString() {
/*  259 */       return TypeUtils.toString(this);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean equals(Object obj) {
/*  267 */       return (obj == this || (obj instanceof WildcardType && TypeUtils.equals(this, (WildcardType)obj)));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int hashCode() {
/*  275 */       int result = 18688;
/*  276 */       result |= Arrays.hashCode((Object[])this.upperBounds);
/*  277 */       result <<= 8;
/*  278 */       result |= Arrays.hashCode((Object[])this.lowerBounds);
/*  279 */       return result;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  287 */   public static final WildcardType WILDCARD_ALL = wildcardType().withUpperBounds(new Type[] { Object.class }).build();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isAssignable(Type type, Type toType) {
/*  311 */     return isAssignable(type, toType, (Map<TypeVariable<?>, Type>)null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean isAssignable(Type type, Type toType, Map<TypeVariable<?>, Type> typeVarAssigns) {
/*  325 */     if (toType == null || toType instanceof Class) {
/*  326 */       return isAssignable(type, (Class)toType);
/*      */     }
/*      */     
/*  329 */     if (toType instanceof ParameterizedType) {
/*  330 */       return isAssignable(type, (ParameterizedType)toType, typeVarAssigns);
/*      */     }
/*      */     
/*  333 */     if (toType instanceof GenericArrayType) {
/*  334 */       return isAssignable(type, (GenericArrayType)toType, typeVarAssigns);
/*      */     }
/*      */     
/*  337 */     if (toType instanceof WildcardType) {
/*  338 */       return isAssignable(type, (WildcardType)toType, typeVarAssigns);
/*      */     }
/*      */     
/*  341 */     if (toType instanceof TypeVariable) {
/*  342 */       return isAssignable(type, (TypeVariable)toType, typeVarAssigns);
/*      */     }
/*      */     
/*  345 */     throw new IllegalStateException("found an unhandled type: " + toType);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean isAssignable(Type type, Class<?> toClass) {
/*  357 */     if (type == null)
/*      */     {
/*  359 */       return (toClass == null || !toClass.isPrimitive());
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  364 */     if (toClass == null) {
/*  365 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  369 */     if (toClass.equals(type)) {
/*  370 */       return true;
/*      */     }
/*      */     
/*  373 */     if (type instanceof Class)
/*      */     {
/*  375 */       return ClassUtils.isAssignable((Class)type, toClass);
/*      */     }
/*      */     
/*  378 */     if (type instanceof ParameterizedType)
/*      */     {
/*  380 */       return isAssignable(getRawType((ParameterizedType)type), toClass);
/*      */     }
/*      */ 
/*      */     
/*  384 */     if (type instanceof TypeVariable) {
/*      */ 
/*      */       
/*  387 */       for (Type bound : ((TypeVariable)type).getBounds()) {
/*  388 */         if (isAssignable(bound, toClass)) {
/*  389 */           return true;
/*      */         }
/*      */       } 
/*      */       
/*  393 */       return false;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  398 */     if (type instanceof GenericArrayType) {
/*  399 */       return (toClass.equals(Object.class) || (toClass
/*  400 */         .isArray() && 
/*  401 */         isAssignable(((GenericArrayType)type).getGenericComponentType(), toClass
/*  402 */           .getComponentType())));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  407 */     if (type instanceof WildcardType) {
/*  408 */       return false;
/*      */     }
/*      */     
/*  411 */     throw new IllegalStateException("found an unhandled type: " + type);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean isAssignable(Type type, ParameterizedType toParameterizedType, Map<TypeVariable<?>, Type> typeVarAssigns) {
/*  425 */     if (type == null) {
/*  426 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  431 */     if (toParameterizedType == null) {
/*  432 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  436 */     if (toParameterizedType.equals(type)) {
/*  437 */       return true;
/*      */     }
/*      */ 
/*      */     
/*  441 */     Class<?> toClass = getRawType(toParameterizedType);
/*      */ 
/*      */     
/*  444 */     Map<TypeVariable<?>, Type> fromTypeVarAssigns = getTypeArguments(type, toClass, (Map<TypeVariable<?>, Type>)null);
/*      */ 
/*      */     
/*  447 */     if (fromTypeVarAssigns == null) {
/*  448 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  454 */     if (fromTypeVarAssigns.isEmpty()) {
/*  455 */       return true;
/*      */     }
/*      */ 
/*      */     
/*  459 */     Map<TypeVariable<?>, Type> toTypeVarAssigns = getTypeArguments(toParameterizedType, toClass, typeVarAssigns);
/*      */ 
/*      */ 
/*      */     
/*  463 */     for (TypeVariable<?> var : toTypeVarAssigns.keySet()) {
/*  464 */       Type toTypeArg = unrollVariableAssignments(var, toTypeVarAssigns);
/*  465 */       Type fromTypeArg = unrollVariableAssignments(var, fromTypeVarAssigns);
/*      */       
/*  467 */       if (toTypeArg == null && fromTypeArg instanceof Class) {
/*      */         continue;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  474 */       if (fromTypeArg != null && 
/*  475 */         !toTypeArg.equals(fromTypeArg) && (!(toTypeArg instanceof WildcardType) || 
/*  476 */         !isAssignable(fromTypeArg, toTypeArg, typeVarAssigns)))
/*      */       {
/*  478 */         return false;
/*      */       }
/*      */     } 
/*  481 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Type unrollVariableAssignments(TypeVariable<?> var, Map<TypeVariable<?>, Type> typeVarAssigns) {
/*      */     Type result;
/*      */     while (true) {
/*  495 */       result = typeVarAssigns.get(var);
/*  496 */       if (result instanceof TypeVariable && !result.equals(var)) {
/*  497 */         var = (TypeVariable)result;
/*      */         continue;
/*      */       } 
/*      */       break;
/*      */     } 
/*  502 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean isAssignable(Type type, GenericArrayType toGenericArrayType, Map<TypeVariable<?>, Type> typeVarAssigns) {
/*  517 */     if (type == null) {
/*  518 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  523 */     if (toGenericArrayType == null) {
/*  524 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  528 */     if (toGenericArrayType.equals(type)) {
/*  529 */       return true;
/*      */     }
/*      */     
/*  532 */     Type toComponentType = toGenericArrayType.getGenericComponentType();
/*      */     
/*  534 */     if (type instanceof Class) {
/*  535 */       Class<?> cls = (Class)type;
/*      */ 
/*      */       
/*  538 */       return (cls.isArray() && 
/*  539 */         isAssignable(cls.getComponentType(), toComponentType, typeVarAssigns));
/*      */     } 
/*      */     
/*  542 */     if (type instanceof GenericArrayType)
/*      */     {
/*  544 */       return isAssignable(((GenericArrayType)type).getGenericComponentType(), toComponentType, typeVarAssigns);
/*      */     }
/*      */ 
/*      */     
/*  548 */     if (type instanceof WildcardType) {
/*      */       
/*  550 */       for (Type bound : getImplicitUpperBounds((WildcardType)type)) {
/*  551 */         if (isAssignable(bound, toGenericArrayType)) {
/*  552 */           return true;
/*      */         }
/*      */       } 
/*      */       
/*  556 */       return false;
/*      */     } 
/*      */     
/*  559 */     if (type instanceof TypeVariable) {
/*      */ 
/*      */       
/*  562 */       for (Type bound : getImplicitBounds((TypeVariable)type)) {
/*  563 */         if (isAssignable(bound, toGenericArrayType)) {
/*  564 */           return true;
/*      */         }
/*      */       } 
/*      */       
/*  568 */       return false;
/*      */     } 
/*      */     
/*  571 */     if (type instanceof ParameterizedType)
/*      */     {
/*      */ 
/*      */       
/*  575 */       return false;
/*      */     }
/*      */     
/*  578 */     throw new IllegalStateException("found an unhandled type: " + type);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean isAssignable(Type type, WildcardType toWildcardType, Map<TypeVariable<?>, Type> typeVarAssigns) {
/*  593 */     if (type == null) {
/*  594 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  599 */     if (toWildcardType == null) {
/*  600 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  604 */     if (toWildcardType.equals(type)) {
/*  605 */       return true;
/*      */     }
/*      */     
/*  608 */     Type[] toUpperBounds = getImplicitUpperBounds(toWildcardType);
/*  609 */     Type[] toLowerBounds = getImplicitLowerBounds(toWildcardType);
/*      */     
/*  611 */     if (type instanceof WildcardType) {
/*  612 */       WildcardType wildcardType = (WildcardType)type;
/*  613 */       Type[] upperBounds = getImplicitUpperBounds(wildcardType);
/*  614 */       Type[] lowerBounds = getImplicitLowerBounds(wildcardType);
/*      */       
/*  616 */       for (Type toBound : toUpperBounds) {
/*      */ 
/*      */         
/*  619 */         toBound = substituteTypeVariables(toBound, typeVarAssigns);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  624 */         for (Type bound : upperBounds) {
/*  625 */           if (!isAssignable(bound, toBound, typeVarAssigns)) {
/*  626 */             return false;
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/*  631 */       for (Type toBound : toLowerBounds) {
/*      */ 
/*      */         
/*  634 */         toBound = substituteTypeVariables(toBound, typeVarAssigns);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  639 */         for (Type bound : lowerBounds) {
/*  640 */           if (!isAssignable(toBound, bound, typeVarAssigns)) {
/*  641 */             return false;
/*      */           }
/*      */         } 
/*      */       } 
/*  645 */       return true;
/*      */     } 
/*      */     
/*  648 */     for (Type toBound : toUpperBounds) {
/*      */ 
/*      */       
/*  651 */       if (!isAssignable(type, substituteTypeVariables(toBound, typeVarAssigns), typeVarAssigns))
/*      */       {
/*  653 */         return false;
/*      */       }
/*      */     } 
/*      */     
/*  657 */     for (Type toBound : toLowerBounds) {
/*      */ 
/*      */       
/*  660 */       if (!isAssignable(substituteTypeVariables(toBound, typeVarAssigns), type, typeVarAssigns))
/*      */       {
/*  662 */         return false;
/*      */       }
/*      */     } 
/*  665 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean isAssignable(Type type, TypeVariable<?> toTypeVariable, Map<TypeVariable<?>, Type> typeVarAssigns) {
/*  680 */     if (type == null) {
/*  681 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  686 */     if (toTypeVariable == null) {
/*  687 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  691 */     if (toTypeVariable.equals(type)) {
/*  692 */       return true;
/*      */     }
/*      */     
/*  695 */     if (type instanceof TypeVariable) {
/*      */ 
/*      */ 
/*      */       
/*  699 */       Type[] bounds = getImplicitBounds((TypeVariable)type);
/*      */       
/*  701 */       for (Type bound : bounds) {
/*  702 */         if (isAssignable(bound, toTypeVariable, typeVarAssigns)) {
/*  703 */           return true;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  708 */     if (type instanceof Class || type instanceof ParameterizedType || type instanceof GenericArrayType || type instanceof WildcardType)
/*      */     {
/*  710 */       return false;
/*      */     }
/*      */     
/*  713 */     throw new IllegalStateException("found an unhandled type: " + type);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Type substituteTypeVariables(Type type, Map<TypeVariable<?>, Type> typeVarAssigns) {
/*  725 */     if (type instanceof TypeVariable && typeVarAssigns != null) {
/*  726 */       Type replacementType = typeVarAssigns.get(type);
/*      */       
/*  728 */       if (replacementType == null) {
/*  729 */         throw new IllegalArgumentException("missing assignment type for type variable " + type);
/*      */       }
/*      */       
/*  732 */       return replacementType;
/*      */     } 
/*  734 */     return type;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Map<TypeVariable<?>, Type> getTypeArguments(ParameterizedType type) {
/*  751 */     return getTypeArguments(type, getRawType(type), (Map<TypeVariable<?>, Type>)null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Map<TypeVariable<?>, Type> getTypeArguments(Type type, Class<?> toClass) {
/*  787 */     return getTypeArguments(type, toClass, (Map<TypeVariable<?>, Type>)null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Map<TypeVariable<?>, Type> getTypeArguments(Type type, Class<?> toClass, Map<TypeVariable<?>, Type> subtypeVarAssigns) {
/*  800 */     if (type instanceof Class) {
/*  801 */       return getTypeArguments((Class)type, toClass, subtypeVarAssigns);
/*      */     }
/*      */     
/*  804 */     if (type instanceof ParameterizedType) {
/*  805 */       return getTypeArguments((ParameterizedType)type, toClass, subtypeVarAssigns);
/*      */     }
/*      */     
/*  808 */     if (type instanceof GenericArrayType) {
/*  809 */       return getTypeArguments(((GenericArrayType)type).getGenericComponentType(), 
/*  810 */           toClass.isArray() ? toClass.getComponentType() : toClass, subtypeVarAssigns);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  815 */     if (type instanceof WildcardType) {
/*  816 */       for (Type bound : getImplicitUpperBounds((WildcardType)type)) {
/*      */         
/*  818 */         if (isAssignable(bound, toClass)) {
/*  819 */           return getTypeArguments(bound, toClass, subtypeVarAssigns);
/*      */         }
/*      */       } 
/*      */       
/*  823 */       return null;
/*      */     } 
/*      */     
/*  826 */     if (type instanceof TypeVariable) {
/*  827 */       for (Type bound : getImplicitBounds((TypeVariable)type)) {
/*      */         
/*  829 */         if (isAssignable(bound, toClass)) {
/*  830 */           return getTypeArguments(bound, toClass, subtypeVarAssigns);
/*      */         }
/*      */       } 
/*      */       
/*  834 */       return null;
/*      */     } 
/*  836 */     throw new IllegalStateException("found an unhandled type: " + type);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Map<TypeVariable<?>, Type> getTypeArguments(ParameterizedType parameterizedType, Class<?> toClass, Map<TypeVariable<?>, Type> subtypeVarAssigns) {
/*      */     Map<TypeVariable<?>, Type> typeVarAssigns;
/*  850 */     Class<?> cls = getRawType(parameterizedType);
/*      */ 
/*      */     
/*  853 */     if (!isAssignable(cls, toClass)) {
/*  854 */       return null;
/*      */     }
/*      */     
/*  857 */     Type ownerType = parameterizedType.getOwnerType();
/*      */ 
/*      */     
/*  860 */     if (ownerType instanceof ParameterizedType) {
/*      */       
/*  862 */       ParameterizedType parameterizedOwnerType = (ParameterizedType)ownerType;
/*  863 */       typeVarAssigns = getTypeArguments(parameterizedOwnerType, 
/*  864 */           getRawType(parameterizedOwnerType), subtypeVarAssigns);
/*      */     }
/*      */     else {
/*      */       
/*  868 */       typeVarAssigns = (subtypeVarAssigns == null) ? new HashMap<>() : new HashMap<>(subtypeVarAssigns);
/*      */     } 
/*      */ 
/*      */     
/*  872 */     Type[] typeArgs = parameterizedType.getActualTypeArguments();
/*      */     
/*  874 */     TypeVariable[] arrayOfTypeVariable = (TypeVariable[])cls.getTypeParameters();
/*      */ 
/*      */     
/*  877 */     for (int i = 0; i < arrayOfTypeVariable.length; i++) {
/*  878 */       Type typeArg = typeArgs[i];
/*  879 */       typeVarAssigns.put(arrayOfTypeVariable[i], typeVarAssigns.containsKey(typeArg) ? 
/*  880 */           typeVarAssigns.get(typeArg) : typeArg);
/*      */     } 
/*      */     
/*  883 */     if (toClass.equals(cls))
/*      */     {
/*  885 */       return typeVarAssigns;
/*      */     }
/*      */ 
/*      */     
/*  889 */     return getTypeArguments(getClosestParentType(cls, toClass), toClass, typeVarAssigns);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Map<TypeVariable<?>, Type> getTypeArguments(Class<?> cls, Class<?> toClass, Map<TypeVariable<?>, Type> subtypeVarAssigns) {
/*  903 */     if (!isAssignable(cls, toClass)) {
/*  904 */       return null;
/*      */     }
/*      */ 
/*      */     
/*  908 */     if (cls.isPrimitive()) {
/*      */       
/*  910 */       if (toClass.isPrimitive())
/*      */       {
/*      */         
/*  913 */         return new HashMap<>();
/*      */       }
/*      */ 
/*      */       
/*  917 */       cls = ClassUtils.primitiveToWrapper(cls);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  922 */     HashMap<TypeVariable<?>, Type> typeVarAssigns = (subtypeVarAssigns == null) ? new HashMap<>() : new HashMap<>(subtypeVarAssigns);
/*      */ 
/*      */     
/*  925 */     if (toClass.equals(cls)) {
/*  926 */       return typeVarAssigns;
/*      */     }
/*      */ 
/*      */     
/*  930 */     return getTypeArguments(getClosestParentType(cls, toClass), toClass, typeVarAssigns);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Map<TypeVariable<?>, Type> determineTypeArguments(Class<?> cls, ParameterizedType superType) {
/*  962 */     Validate.notNull(cls, "cls is null", new Object[0]);
/*  963 */     Validate.notNull(superType, "superType is null", new Object[0]);
/*      */     
/*  965 */     Class<?> superClass = getRawType(superType);
/*      */ 
/*      */     
/*  968 */     if (!isAssignable(cls, superClass)) {
/*  969 */       return null;
/*      */     }
/*      */     
/*  972 */     if (cls.equals(superClass)) {
/*  973 */       return getTypeArguments(superType, superClass, (Map<TypeVariable<?>, Type>)null);
/*      */     }
/*      */ 
/*      */     
/*  977 */     Type midType = getClosestParentType(cls, superClass);
/*      */ 
/*      */     
/*  980 */     if (midType instanceof Class) {
/*  981 */       return determineTypeArguments((Class)midType, superType);
/*      */     }
/*      */     
/*  984 */     ParameterizedType midParameterizedType = (ParameterizedType)midType;
/*  985 */     Class<?> midClass = getRawType(midParameterizedType);
/*      */ 
/*      */     
/*  988 */     Map<TypeVariable<?>, Type> typeVarAssigns = determineTypeArguments(midClass, superType);
/*      */     
/*  990 */     mapTypeVariablesToArguments(cls, midParameterizedType, typeVarAssigns);
/*      */     
/*  992 */     return typeVarAssigns;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static <T> void mapTypeVariablesToArguments(Class<T> cls, ParameterizedType parameterizedType, Map<TypeVariable<?>, Type> typeVarAssigns) {
/* 1006 */     Type ownerType = parameterizedType.getOwnerType();
/*      */     
/* 1008 */     if (ownerType instanceof ParameterizedType)
/*      */     {
/* 1010 */       mapTypeVariablesToArguments(cls, (ParameterizedType)ownerType, typeVarAssigns);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1017 */     Type[] typeArgs = parameterizedType.getActualTypeArguments();
/*      */ 
/*      */ 
/*      */     
/* 1021 */     TypeVariable[] arrayOfTypeVariable = (TypeVariable[])getRawType(parameterizedType).getTypeParameters();
/*      */ 
/*      */     
/* 1024 */     List<TypeVariable<Class<T>>> typeVarList = Arrays.asList(cls
/* 1025 */         .getTypeParameters());
/*      */     
/* 1027 */     for (int i = 0; i < typeArgs.length; i++) {
/* 1028 */       TypeVariable<?> typeVar = arrayOfTypeVariable[i];
/* 1029 */       Type typeArg = typeArgs[i];
/*      */ 
/*      */       
/* 1032 */       if (typeVarList.contains(typeArg) && typeVarAssigns
/*      */ 
/*      */         
/* 1035 */         .containsKey(typeVar))
/*      */       {
/* 1037 */         typeVarAssigns.put((TypeVariable)typeArg, typeVarAssigns.get(typeVar));
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Type getClosestParentType(Class<?> cls, Class<?> superClass) {
/* 1052 */     if (superClass.isInterface()) {
/*      */       
/* 1054 */       Type[] interfaceTypes = cls.getGenericInterfaces();
/*      */       
/* 1056 */       Type genericInterface = null;
/*      */ 
/*      */       
/* 1059 */       for (Type midType : interfaceTypes) {
/* 1060 */         Class<?> midClass = null;
/*      */         
/* 1062 */         if (midType instanceof ParameterizedType) {
/* 1063 */           midClass = getRawType((ParameterizedType)midType);
/* 1064 */         } else if (midType instanceof Class) {
/* 1065 */           midClass = (Class)midType;
/*      */         } else {
/* 1067 */           throw new IllegalStateException("Unexpected generic interface type found: " + midType);
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1073 */         if (isAssignable(midClass, superClass) && 
/* 1074 */           isAssignable(genericInterface, midClass)) {
/* 1075 */           genericInterface = midType;
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 1080 */       if (genericInterface != null) {
/* 1081 */         return genericInterface;
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1087 */     return cls.getGenericSuperclass();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isInstance(Object value, Type type) {
/* 1099 */     if (type == null) {
/* 1100 */       return false;
/*      */     }
/*      */     
/* 1103 */     return (value == null) ? ((!(type instanceof Class) || !((Class)type).isPrimitive())) : 
/* 1104 */       isAssignable(value.getClass(), type, (Map<TypeVariable<?>, Type>)null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Type[] normalizeUpperBounds(Type[] bounds) {
/* 1129 */     Validate.notNull(bounds, "null value specified for bounds array", new Object[0]);
/*      */     
/* 1131 */     if (bounds.length < 2) {
/* 1132 */       return bounds;
/*      */     }
/*      */     
/* 1135 */     Set<Type> types = new HashSet<>(bounds.length);
/*      */     
/* 1137 */     for (Type type1 : bounds) {
/* 1138 */       boolean subtypeFound = false;
/*      */       
/* 1140 */       for (Type type2 : bounds) {
/* 1141 */         if (type1 != type2 && isAssignable(type2, type1, (Map<TypeVariable<?>, Type>)null)) {
/* 1142 */           subtypeFound = true;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/* 1147 */       if (!subtypeFound) {
/* 1148 */         types.add(type1);
/*      */       }
/*      */     } 
/*      */     
/* 1152 */     return types.<Type>toArray(new Type[types.size()]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Type[] getImplicitBounds(TypeVariable<?> typeVariable) {
/* 1165 */     Validate.notNull(typeVariable, "typeVariable is null", new Object[0]);
/* 1166 */     Type[] bounds = typeVariable.getBounds();
/*      */     
/* 1168 */     (new Type[1])[0] = Object.class; return (bounds.length == 0) ? new Type[1] : normalizeUpperBounds(bounds);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Type[] getImplicitUpperBounds(WildcardType wildcardType) {
/* 1182 */     Validate.notNull(wildcardType, "wildcardType is null", new Object[0]);
/* 1183 */     Type[] bounds = wildcardType.getUpperBounds();
/*      */     
/* 1185 */     (new Type[1])[0] = Object.class; return (bounds.length == 0) ? new Type[1] : normalizeUpperBounds(bounds);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Type[] getImplicitLowerBounds(WildcardType wildcardType) {
/* 1198 */     Validate.notNull(wildcardType, "wildcardType is null", new Object[0]);
/* 1199 */     Type[] bounds = wildcardType.getLowerBounds();
/*      */     
/* 1201 */     (new Type[1])[0] = null; return (bounds.length == 0) ? new Type[1] : bounds;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean typesSatisfyVariables(Map<TypeVariable<?>, Type> typeVarAssigns) {
/* 1218 */     Validate.notNull(typeVarAssigns, "typeVarAssigns is null", new Object[0]);
/*      */ 
/*      */     
/* 1221 */     for (Map.Entry<TypeVariable<?>, Type> entry : typeVarAssigns.entrySet()) {
/* 1222 */       TypeVariable<?> typeVar = entry.getKey();
/* 1223 */       Type type = entry.getValue();
/*      */       
/* 1225 */       for (Type bound : getImplicitBounds(typeVar)) {
/* 1226 */         if (!isAssignable(type, substituteTypeVariables(bound, typeVarAssigns), typeVarAssigns))
/*      */         {
/* 1228 */           return false;
/*      */         }
/*      */       } 
/*      */     } 
/* 1232 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Class<?> getRawType(ParameterizedType parameterizedType) {
/* 1243 */     Type rawType = parameterizedType.getRawType();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1250 */     if (!(rawType instanceof Class)) {
/* 1251 */       throw new IllegalStateException("Wait... What!? Type of rawType: " + rawType);
/*      */     }
/*      */     
/* 1254 */     return (Class)rawType;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Class<?> getRawType(Type type, Type assigningType) {
/* 1270 */     if (type instanceof Class)
/*      */     {
/* 1272 */       return (Class)type;
/*      */     }
/*      */     
/* 1275 */     if (type instanceof ParameterizedType)
/*      */     {
/* 1277 */       return getRawType((ParameterizedType)type);
/*      */     }
/*      */     
/* 1280 */     if (type instanceof TypeVariable) {
/* 1281 */       if (assigningType == null) {
/* 1282 */         return null;
/*      */       }
/*      */ 
/*      */       
/* 1286 */       Object genericDeclaration = ((TypeVariable)type).getGenericDeclaration();
/*      */ 
/*      */ 
/*      */       
/* 1290 */       if (!(genericDeclaration instanceof Class)) {
/* 1291 */         return null;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1296 */       Map<TypeVariable<?>, Type> typeVarAssigns = getTypeArguments(assigningType, (Class)genericDeclaration);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1301 */       if (typeVarAssigns == null) {
/* 1302 */         return null;
/*      */       }
/*      */ 
/*      */       
/* 1306 */       Type typeArgument = typeVarAssigns.get(type);
/*      */       
/* 1308 */       if (typeArgument == null) {
/* 1309 */         return null;
/*      */       }
/*      */ 
/*      */       
/* 1313 */       return getRawType(typeArgument, assigningType);
/*      */     } 
/*      */     
/* 1316 */     if (type instanceof GenericArrayType) {
/*      */       
/* 1318 */       Class<?> rawComponentType = getRawType(((GenericArrayType)type)
/* 1319 */           .getGenericComponentType(), assigningType);
/*      */ 
/*      */       
/* 1322 */       return Array.newInstance(rawComponentType, 0).getClass();
/*      */     } 
/*      */ 
/*      */     
/* 1326 */     if (type instanceof WildcardType) {
/* 1327 */       return null;
/*      */     }
/*      */     
/* 1330 */     throw new IllegalArgumentException("unknown type: " + type);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isArrayType(Type type) {
/* 1339 */     return (type instanceof GenericArrayType || (type instanceof Class && ((Class)type).isArray()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Type getArrayComponentType(Type type) {
/* 1348 */     if (type instanceof Class) {
/* 1349 */       Class<?> clazz = (Class)type;
/* 1350 */       return clazz.isArray() ? clazz.getComponentType() : null;
/*      */     } 
/* 1352 */     if (type instanceof GenericArrayType) {
/* 1353 */       return ((GenericArrayType)type).getGenericComponentType();
/*      */     }
/* 1355 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Type unrollVariables(Map<TypeVariable<?>, Type> typeArguments, Type type) {
/* 1367 */     if (typeArguments == null) {
/* 1368 */       typeArguments = Collections.emptyMap();
/*      */     }
/* 1370 */     if (containsTypeVariables(type)) {
/* 1371 */       if (type instanceof TypeVariable) {
/* 1372 */         return unrollVariables(typeArguments, typeArguments.get(type));
/*      */       }
/* 1374 */       if (type instanceof ParameterizedType) {
/* 1375 */         Map<TypeVariable<?>, Type> parameterizedTypeArguments; ParameterizedType p = (ParameterizedType)type;
/*      */         
/* 1377 */         if (p.getOwnerType() == null) {
/* 1378 */           parameterizedTypeArguments = typeArguments;
/*      */         } else {
/* 1380 */           parameterizedTypeArguments = new HashMap<>(typeArguments);
/* 1381 */           parameterizedTypeArguments.putAll(getTypeArguments(p));
/*      */         } 
/* 1383 */         Type[] args = p.getActualTypeArguments();
/* 1384 */         for (int i = 0; i < args.length; i++) {
/* 1385 */           Type unrolled = unrollVariables(parameterizedTypeArguments, args[i]);
/* 1386 */           if (unrolled != null) {
/* 1387 */             args[i] = unrolled;
/*      */           }
/*      */         } 
/* 1390 */         return parameterizeWithOwner(p.getOwnerType(), (Class)p.getRawType(), args);
/*      */       } 
/* 1392 */       if (type instanceof WildcardType) {
/* 1393 */         WildcardType wild = (WildcardType)type;
/* 1394 */         return wildcardType().withUpperBounds(unrollBounds(typeArguments, wild.getUpperBounds()))
/* 1395 */           .withLowerBounds(unrollBounds(typeArguments, wild.getLowerBounds())).build();
/*      */       } 
/*      */     } 
/* 1398 */     return type;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Type[] unrollBounds(Map<TypeVariable<?>, Type> typeArguments, Type[] bounds) {
/* 1410 */     Type[] result = bounds;
/* 1411 */     int i = 0;
/* 1412 */     for (; i < result.length; i++) {
/* 1413 */       Type unrolled = unrollVariables(typeArguments, result[i]);
/* 1414 */       if (unrolled == null) {
/* 1415 */         result = (Type[])ArrayUtils.remove((Object[])result, i--);
/*      */       } else {
/* 1417 */         result[i] = unrolled;
/*      */       } 
/*      */     } 
/* 1420 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean containsTypeVariables(Type type) {
/* 1431 */     if (type instanceof TypeVariable) {
/* 1432 */       return true;
/*      */     }
/* 1434 */     if (type instanceof Class) {
/* 1435 */       return ((((Class)type).getTypeParameters()).length > 0);
/*      */     }
/* 1437 */     if (type instanceof ParameterizedType) {
/* 1438 */       for (Type arg : ((ParameterizedType)type).getActualTypeArguments()) {
/* 1439 */         if (containsTypeVariables(arg)) {
/* 1440 */           return true;
/*      */         }
/*      */       } 
/* 1443 */       return false;
/*      */     } 
/* 1445 */     if (type instanceof WildcardType) {
/* 1446 */       WildcardType wild = (WildcardType)type;
/* 1447 */       return (containsTypeVariables(getImplicitLowerBounds(wild)[0]) || 
/* 1448 */         containsTypeVariables(getImplicitUpperBounds(wild)[0]));
/*      */     } 
/* 1450 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final ParameterizedType parameterize(Class<?> raw, Type... typeArguments) {
/* 1462 */     return parameterizeWithOwner((Type)null, raw, typeArguments);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final ParameterizedType parameterize(Class<?> raw, Map<TypeVariable<?>, Type> typeArgMappings) {
/* 1475 */     Validate.notNull(raw, "raw class is null", new Object[0]);
/* 1476 */     Validate.notNull(typeArgMappings, "typeArgMappings is null", new Object[0]);
/* 1477 */     return parameterizeWithOwner((Type)null, raw, extractTypeArgumentsFrom(typeArgMappings, (TypeVariable<?>[])raw.getTypeParameters()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final ParameterizedType parameterizeWithOwner(Type owner, Class<?> raw, Type... typeArguments) {
/*      */     Type useOwner;
/* 1492 */     Validate.notNull(raw, "raw class is null", new Object[0]);
/*      */     
/* 1494 */     if (raw.getEnclosingClass() == null) {
/* 1495 */       Validate.isTrue((owner == null), "no owner allowed for top-level %s", new Object[] { raw });
/* 1496 */       useOwner = null;
/* 1497 */     } else if (owner == null) {
/* 1498 */       useOwner = raw.getEnclosingClass();
/*      */     } else {
/* 1500 */       Validate.isTrue(isAssignable(owner, raw.getEnclosingClass()), "%s is invalid owner type for parameterized %s", new Object[] { owner, raw });
/*      */       
/* 1502 */       useOwner = owner;
/*      */     } 
/* 1504 */     Validate.noNullElements((Object[])typeArguments, "null type argument at index %s", new Object[0]);
/* 1505 */     Validate.isTrue(((raw.getTypeParameters()).length == typeArguments.length), "invalid number of type parameters specified: expected %d, got %d", new Object[] {
/* 1506 */           Integer.valueOf((raw.getTypeParameters()).length), 
/* 1507 */           Integer.valueOf(typeArguments.length)
/*      */         });
/* 1509 */     return new ParameterizedTypeImpl(raw, useOwner, typeArguments);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final ParameterizedType parameterizeWithOwner(Type owner, Class<?> raw, Map<TypeVariable<?>, Type> typeArgMappings) {
/* 1523 */     Validate.notNull(raw, "raw class is null", new Object[0]);
/* 1524 */     Validate.notNull(typeArgMappings, "typeArgMappings is null", new Object[0]);
/* 1525 */     return parameterizeWithOwner(owner, raw, extractTypeArgumentsFrom(typeArgMappings, (TypeVariable<?>[])raw.getTypeParameters()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Type[] extractTypeArgumentsFrom(Map<TypeVariable<?>, Type> mappings, TypeVariable<?>[] variables) {
/* 1535 */     Type[] result = new Type[variables.length];
/* 1536 */     int index = 0;
/* 1537 */     for (TypeVariable<?> var : variables) {
/* 1538 */       Validate.isTrue(mappings.containsKey(var), "missing argument mapping for %s", new Object[] { toString(var) });
/* 1539 */       result[index++] = mappings.get(var);
/*      */     } 
/* 1541 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static WildcardTypeBuilder wildcardType() {
/* 1550 */     return new WildcardTypeBuilder();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static GenericArrayType genericArrayType(Type componentType) {
/* 1562 */     return new GenericArrayTypeImpl((Type)Validate.notNull(componentType, "componentType is null", new Object[0]));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean equals(Type t1, Type t2) {
/* 1574 */     if (Objects.equals(t1, t2)) {
/* 1575 */       return true;
/*      */     }
/* 1577 */     if (t1 instanceof ParameterizedType) {
/* 1578 */       return equals((ParameterizedType)t1, t2);
/*      */     }
/* 1580 */     if (t1 instanceof GenericArrayType) {
/* 1581 */       return equals((GenericArrayType)t1, t2);
/*      */     }
/* 1583 */     if (t1 instanceof WildcardType) {
/* 1584 */       return equals((WildcardType)t1, t2);
/*      */     }
/* 1586 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean equals(ParameterizedType p, Type t) {
/* 1597 */     if (t instanceof ParameterizedType) {
/* 1598 */       ParameterizedType other = (ParameterizedType)t;
/* 1599 */       if (equals(p.getRawType(), other.getRawType()) && equals(p.getOwnerType(), other.getOwnerType())) {
/* 1600 */         return equals(p.getActualTypeArguments(), other.getActualTypeArguments());
/*      */       }
/*      */     } 
/* 1603 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean equals(GenericArrayType a, Type t) {
/* 1614 */     return (t instanceof GenericArrayType && 
/* 1615 */       equals(a.getGenericComponentType(), ((GenericArrayType)t).getGenericComponentType()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean equals(WildcardType w, Type t) {
/* 1626 */     if (t instanceof WildcardType) {
/* 1627 */       WildcardType other = (WildcardType)t;
/* 1628 */       return (equals(getImplicitLowerBounds(w), getImplicitLowerBounds(other)) && 
/* 1629 */         equals(getImplicitUpperBounds(w), getImplicitUpperBounds(other)));
/*      */     } 
/* 1631 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean equals(Type[] t1, Type[] t2) {
/* 1642 */     if (t1.length == t2.length) {
/* 1643 */       for (int i = 0; i < t1.length; i++) {
/* 1644 */         if (!equals(t1[i], t2[i])) {
/* 1645 */           return false;
/*      */         }
/*      */       } 
/* 1648 */       return true;
/*      */     } 
/* 1650 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String toString(Type type) {
/* 1661 */     Validate.notNull(type);
/* 1662 */     if (type instanceof Class) {
/* 1663 */       return classToString((Class)type);
/*      */     }
/* 1665 */     if (type instanceof ParameterizedType) {
/* 1666 */       return parameterizedTypeToString((ParameterizedType)type);
/*      */     }
/* 1668 */     if (type instanceof WildcardType) {
/* 1669 */       return wildcardTypeToString((WildcardType)type);
/*      */     }
/* 1671 */     if (type instanceof TypeVariable) {
/* 1672 */       return typeVariableToString((TypeVariable)type);
/*      */     }
/* 1674 */     if (type instanceof GenericArrayType) {
/* 1675 */       return genericArrayTypeToString((GenericArrayType)type);
/*      */     }
/* 1677 */     throw new IllegalArgumentException(ObjectUtils.identityToString(type));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String toLongString(TypeVariable<?> var) {
/* 1688 */     Validate.notNull(var, "var is null", new Object[0]);
/* 1689 */     StringBuilder buf = new StringBuilder();
/* 1690 */     GenericDeclaration d = (GenericDeclaration)var.getGenericDeclaration();
/* 1691 */     if (d instanceof Class) {
/* 1692 */       Class<?> c = (Class)d;
/*      */       while (true) {
/* 1694 */         if (c.getEnclosingClass() == null) {
/* 1695 */           buf.insert(0, c.getName());
/*      */           break;
/*      */         } 
/* 1698 */         buf.insert(0, c.getSimpleName()).insert(0, '.');
/* 1699 */         c = c.getEnclosingClass();
/*      */       } 
/* 1701 */     } else if (d instanceof Type) {
/* 1702 */       buf.append(toString((Type)d));
/*      */     } else {
/* 1704 */       buf.append(d);
/*      */     } 
/* 1706 */     return buf.append(':').append(typeVariableToString(var)).toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static <T> Typed<T> wrap(final Type type) {
/* 1718 */     return new Typed<T>()
/*      */       {
/*      */         public Type getType() {
/* 1721 */           return type;
/*      */         }
/*      */       };
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static <T> Typed<T> wrap(Class<T> type) {
/* 1735 */     return wrap(type);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String classToString(Class<?> c) {
/* 1745 */     if (c.isArray()) {
/* 1746 */       return toString(c.getComponentType()) + "[]";
/*      */     }
/*      */     
/* 1749 */     StringBuilder buf = new StringBuilder();
/*      */     
/* 1751 */     if (c.getEnclosingClass() != null) {
/* 1752 */       buf.append(classToString(c.getEnclosingClass())).append('.').append(c.getSimpleName());
/*      */     } else {
/* 1754 */       buf.append(c.getName());
/*      */     } 
/* 1756 */     if ((c.getTypeParameters()).length > 0) {
/* 1757 */       buf.append('<');
/* 1758 */       appendAllTo(buf, ", ", (Object[])c.getTypeParameters());
/* 1759 */       buf.append('>');
/*      */     } 
/* 1761 */     return buf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String typeVariableToString(TypeVariable<?> v) {
/* 1771 */     StringBuilder buf = new StringBuilder(v.getName());
/* 1772 */     Type[] bounds = v.getBounds();
/* 1773 */     if (bounds.length > 0 && (bounds.length != 1 || !Object.class.equals(bounds[0]))) {
/* 1774 */       buf.append(" extends ");
/* 1775 */       appendAllTo(buf, " & ", v.getBounds());
/*      */     } 
/* 1777 */     return buf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String parameterizedTypeToString(ParameterizedType p) {
/* 1787 */     StringBuilder buf = new StringBuilder();
/*      */     
/* 1789 */     Type useOwner = p.getOwnerType();
/* 1790 */     Class<?> raw = (Class)p.getRawType();
/*      */     
/* 1792 */     if (useOwner == null) {
/* 1793 */       buf.append(raw.getName());
/*      */     } else {
/* 1795 */       if (useOwner instanceof Class) {
/* 1796 */         buf.append(((Class)useOwner).getName());
/*      */       } else {
/* 1798 */         buf.append(useOwner.toString());
/*      */       } 
/* 1800 */       buf.append('.').append(raw.getSimpleName());
/*      */     } 
/*      */     
/* 1803 */     int[] recursiveTypeIndexes = findRecursiveTypes(p);
/*      */     
/* 1805 */     if (recursiveTypeIndexes.length > 0) {
/* 1806 */       appendRecursiveTypes(buf, recursiveTypeIndexes, p.getActualTypeArguments());
/*      */     } else {
/* 1808 */       appendAllTo(buf.append('<'), ", ", p.getActualTypeArguments()).append('>');
/*      */     } 
/*      */     
/* 1811 */     return buf.toString();
/*      */   }
/*      */   
/*      */   private static void appendRecursiveTypes(StringBuilder buf, int[] recursiveTypeIndexes, Type[] argumentTypes) {
/* 1815 */     for (int i = 0; i < recursiveTypeIndexes.length; i++) {
/* 1816 */       appendAllTo(buf.append('<'), ", ", new String[] { argumentTypes[i].toString() }).append('>');
/*      */     } 
/*      */     
/* 1819 */     Type[] argumentsFiltered = (Type[])ArrayUtils.removeAll((Object[])argumentTypes, recursiveTypeIndexes);
/*      */     
/* 1821 */     if (argumentsFiltered.length > 0) {
/* 1822 */       appendAllTo(buf.append('<'), ", ", argumentsFiltered).append('>');
/*      */     }
/*      */   }
/*      */   
/*      */   private static int[] findRecursiveTypes(ParameterizedType p) {
/* 1827 */     Type[] filteredArgumentTypes = Arrays.<Type>copyOf(p.getActualTypeArguments(), (p.getActualTypeArguments()).length);
/* 1828 */     int[] indexesToRemove = new int[0];
/* 1829 */     for (int i = 0; i < filteredArgumentTypes.length; i++) {
/* 1830 */       if (filteredArgumentTypes[i] instanceof TypeVariable && 
/* 1831 */         containsVariableTypeSameParametrizedTypeBound((TypeVariable)filteredArgumentTypes[i], p)) {
/* 1832 */         indexesToRemove = ArrayUtils.add(indexesToRemove, i);
/*      */       }
/*      */     } 
/*      */     
/* 1836 */     return indexesToRemove;
/*      */   }
/*      */   
/*      */   private static boolean containsVariableTypeSameParametrizedTypeBound(TypeVariable<?> typeVariable, ParameterizedType p) {
/* 1840 */     return ArrayUtils.contains((Object[])typeVariable.getBounds(), p);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String wildcardTypeToString(WildcardType w) {
/* 1850 */     StringBuilder buf = (new StringBuilder()).append('?');
/* 1851 */     Type[] lowerBounds = w.getLowerBounds();
/* 1852 */     Type[] upperBounds = w.getUpperBounds();
/* 1853 */     if (lowerBounds.length > 1 || (lowerBounds.length == 1 && lowerBounds[0] != null)) {
/* 1854 */       appendAllTo(buf.append(" super "), " & ", lowerBounds);
/* 1855 */     } else if (upperBounds.length > 1 || (upperBounds.length == 1 && !Object.class.equals(upperBounds[0]))) {
/* 1856 */       appendAllTo(buf.append(" extends "), " & ", upperBounds);
/*      */     } 
/* 1858 */     return buf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String genericArrayTypeToString(GenericArrayType g) {
/* 1868 */     return String.format("%s[]", new Object[] { toString(g.getGenericComponentType()) });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static <T> StringBuilder appendAllTo(StringBuilder buf, String sep, T... types) {
/* 1880 */     Validate.notEmpty(Validate.noNullElements((Object[])types));
/* 1881 */     if (types.length > 0) {
/* 1882 */       buf.append(toString(types[0]));
/* 1883 */       for (int i = 1; i < types.length; i++) {
/* 1884 */         buf.append(sep).append(toString(types[i]));
/*      */       }
/*      */     } 
/* 1887 */     return buf;
/*      */   }
/*      */   
/*      */   private static <T> String toString(T object) {
/* 1891 */     return (object instanceof Type) ? toString((Type)object) : object.toString();
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/lang3/reflect/TypeUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */