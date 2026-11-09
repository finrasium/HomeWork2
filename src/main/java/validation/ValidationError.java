package validation;

public record ValidationError(String fieldName, String annotationName,String message) {
    public ValidationError{
        if (fieldName == null || annotationName == null|| message == null){
            throw new NullPointerException("Одно из полей невалидно: обнаружен null");
        }
    }
}
