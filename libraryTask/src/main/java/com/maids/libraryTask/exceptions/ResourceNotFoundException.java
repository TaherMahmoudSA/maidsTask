package com.maids.libraryTask.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String resourceType) {
        super(resourceType + " not found.");
    }

    public ResourceNotFoundException(String resourceType, Throwable throwable) {
        super(resourceType + " not found.", throwable);
    }


}
