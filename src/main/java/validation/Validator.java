package validation;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public final class Validator {
    public static ValidationResult validate(Object object){
        if (object == null){
            throw new NullPointerException("Объект не может быть null");
        }
        List<ValidationError> errors = new ArrayList<>();
        Class<?> clazz = object.getClass();
        while (clazz != null && clazz != Object.class){
            for (Field field : clazz.getDeclaredFields()){
                if ( (Modifier.isStatic(field.getModifiers())) || field.isSynthetic()) {
                    continue;
                }
                if (!field.trySetAccessible()) {
                    throw new ValidationConfigurationException("Нет доступа к полю " + clazz.getName()+"."+field.getName());
                }
                Object value;
                try{
                    value = field.get(object);
                }catch(IllegalAccessException e){
                    throw new ValidationConfigurationException("Не получается прочитать"+ clazz.getName()+"."+field.getName(),e);
                }
                checkField(field,value,errors);
            }
            clazz = clazz.getSuperclass();
        }
        return new ValidationResult(errors);
    }
    private static void checkField(Field field, Object value, List<ValidationError> errors) {
        NotNull notNull = field.getAnnotation(NotNull.class);
        if (notNull != null){
            if (!field.getType().isPrimitive() && value == null) {
                errors.add(new ValidationError(field.getName(),"NotNull",notNull.message()));
            }
        }

        Range range = field.getAnnotation(Range.class);
        if (range!=null){
            if (Double.isNaN(range.min()) || Double.isNaN(range.max()) ||
                    Double.isInfinite(range.min()) || Double.isInfinite(range.max()) ||
                    range.min() > range.max()){
                    throw new ValidationConfigurationException("Некорректные данные @Range в поле " + field.getName());
            }
            Class<?> type = field.getType();
            boolean isNumeric = (type.isPrimitive() && type != boolean.class && type != char.class) || Number.class.isAssignableFrom(type);
            if (!isNumeric) {
                throw new ValidationConfigurationException(
                        "@Range не к числу в поле " + field.getName()
                );
            }
            if (value != null) {
                double numValue = ((Number) value).doubleValue();
                if (Double.isNaN(numValue) || numValue < range.min() || numValue > range.max()) {
                    errors.add(new ValidationError(field.getName(), "Range", range.message()));
                }
            }
        }
        Size size = field.getAnnotation(Size.class);
        if (size != null) {
            if (size.min() < 0 || size.min() > size.max()) {
                throw new ValidationConfigurationException(
                        "Некорректный @Size в поле " + field.getName()
                );
            }
            // Ошибка конфигурации: тип не строка
            if (!CharSequence.class.isAssignableFrom(field.getType())) {
                throw new ValidationConfigurationException(
                        "@Size имеет неправильный тип в поле " + field.getName()
                );
            }
            if (value != null) {
                int length = ((CharSequence) value).length();
                if (length < size.min() || length > size.max()) {
                    errors.add(new ValidationError(field.getName(), "Size", size.message()));
                }
            }
            }
        Email email = field.getAnnotation(Email.class);
        if (email!=null){
            if (!CharSequence.class.isAssignableFrom(field.getType())) {
                throw new ValidationConfigurationException(
                        "@Email неподдерживаемый тип в поле" + field.getName()
                );
            }
            if (value != null) {
                String str = value.toString();
                // ТЕПЕРЬ ВЫЗЫВАЕМ РЕГУЛЯРКУ
                if (!str.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
                    errors.add(new ValidationError(field.getName(), "Email", email.message()));
                }
            }
        }
    }

}
