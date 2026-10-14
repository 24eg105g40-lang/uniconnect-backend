package com.educonnect.educonnectbackend.exception;

public class ResourceNotFoundException extends RuntimeException  {
        public ResourceNotFoundException(String message){
            super(message);
        }
}
