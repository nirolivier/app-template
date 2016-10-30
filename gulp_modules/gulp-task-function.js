/**
 * Copyright (c) 2016 Olivier nirina.
 *
 * @author Olivier nirina
 * @since 1.0
 */

'use strict';

var $gulp      = require('gulp'),
    $del       = require('de'),
    $injector  = require('./gulp_modules/gulp-injector'),
    $const     = require('./gulp_modules/gulp-constant');

module.exports = {
    clean: cleanFn,
    injectDev: injectDevFn,
    injectProd: injectProdFn
}

/**
 * clean
 */
function cleanFn(patterns){
    return function(){
        return $del(patterns).then(function(succes){
            console.log('Deleted files and folder\n', succes.join('\n'));
        }, function(err){
            console.log('Error while deleting files and folder\n', err.join('\n'));
        });
    }
}

function injectDevFn(ext){
    return function(){
       if(ext && ext === 'js'){
          $injector.jsDev();
       }
    }
}

function injectProdFn(ext){
    return function(){
        if(ext && ext === 'js'){
            $injector.jsProd();
        }
    }
}
