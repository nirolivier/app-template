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



package com.niro.constants;

import com.niro.exceptions.IllegalCallException;

/**
 * A class that aims to provides methods for i18n.
 * @author Olivier nirina
 * @since 1.0
 */
public final class I18nConstant {
    
    public final static String AUTH_MANAGER_CONFIG_ERROR = "auth.manager.config.error" ;
    
    /**
     * Default object constructor
     */
    private I18nConstant() {
        throw new IllegalCallException("This method cannot be called");
    }

}
