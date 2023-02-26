package com.b902.watersupply;

public class UserNotFoundException extends RuntimeException{

    String resourceName;
    String fieldName;
    String value;

    public UserNotFoundException() {
    }

    public UserNotFoundException(String resourceName, String fieldName, String value){
        super(String.format("%s : %s  Not Found in Resource %s",fieldName,value,resourceName));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.value = value;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
