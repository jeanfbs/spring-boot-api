package br.com.sampleapi.service.mapper;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Map;

public class ClassMerger {
    public static <T> T merge(T source, T target) {
        if (target == null) return source;
        if (source == null) return target;

        mergeFields(source.getClass(), source, target);
        return target;
    }

    private static void mergeFields(Class<?> clazz, Object source, Object target) {
        if (clazz == null || clazz.equals(Object.class)) return;

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (Modifier.isFinal(field.getModifiers())) continue;

            field.setAccessible(true);
            try {
                Object sourceValue = field.get(source);
                Object targetValue = field.get(target);

                if (sourceValue != null) {
                    if (!isPrimitiveOrWrapper(field.getType()) && !String.class.isAssignableFrom(field.getType())
                            && !Collection.class.isAssignableFrom(field.getType())
                            && !Map.class.isAssignableFrom(field.getType())) {

                        if (targetValue == null) {
                            field.set(target, sourceValue);
                        } else {
                            merge(sourceValue, targetValue);
                        }
                    } else {
                        field.set(target, sourceValue);
                    }
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Error while merging field: " + field.getName(), e);
            }
            field.setAccessible(false);
        }

        mergeFields(clazz.getSuperclass(), source, target);
    }

    private static boolean isPrimitiveOrWrapper(Class<?> type) {
        return type.isPrimitive() ||
                type.equals(Boolean.class) ||
                type.equals(Byte.class) ||
                type.equals(Character.class) ||
                type.equals(Short.class) ||
                type.equals(Integer.class) ||
                type.equals(Long.class) ||
                type.equals(Float.class) ||
                type.equals(Double.class);
    }
}
