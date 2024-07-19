package com.pruebaTecnica.contact_api;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.pruebaTecnica.exception.ResourceNotFoundException;

public class ResourceNotFoundExceptionTest {

    @Test
    public void testExceptionMessage() {
        String message = "Resource not found";
        ResourceNotFoundException exception = new ResourceNotFoundException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testExceptionCause() {
        String message = "Resource not found";
        ResourceNotFoundException exception = new ResourceNotFoundException(message);
        assertEquals(message, exception.getMessage());
    }
}