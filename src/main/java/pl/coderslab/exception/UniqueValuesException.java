package pl.coderslab.exception;

public class UniqueValuesException extends Exception{

    private String objectName;

    public UniqueValuesException(String objectName, String message ){
        super(message);
        this.objectName = objectName;
    }

    public String getObjectName() {
        return objectName;
    }
}
