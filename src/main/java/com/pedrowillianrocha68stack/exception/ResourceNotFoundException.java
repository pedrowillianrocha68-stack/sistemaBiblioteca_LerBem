package com.pedrowillianrocha68stack.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String resource, Long id){
        super(resource + "não encontrado: " + id);
    }
}
