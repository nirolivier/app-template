/*
 * Copyright 2016 Olivier nirina
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.niro.exceptions;

/**
 * This exception is thrown when attempt to call a method that should not be
 * called.
 * 
 * @author Olivier nirina
 * @since 1.0
 */
public class IllegalCallException extends RuntimeException {

    private static final long serialVersionUID = -302997093299208597L;

    /**
     * Default object constructor of this class
     */
    public IllegalCallException() {
        super();
    }

    /**
     * Create an exception object with a given message error and the cause the
     * error.
     * 
     * @param message
     *            the error message
     * @param cause
     *            the cause the error
     */
    public IllegalCallException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Create an exception object with a given message error.
     * 
     * @param message
     *            the error message
     */
    public IllegalCallException(String message) {
        super(message);
    }

    /**
     * Create an exception object with a given message error and the cause the
     * error.
     * 
     * @param cause
     *            the cause the error
     */
    public IllegalCallException(Throwable cause) {
        super(cause);
    }

}
