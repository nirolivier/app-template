/**
 * Copyright (c) 2016 Olivier nirina.
 *
 * @author Olivier nirina
 * @since 1.0
 */

'use strict';

var $gulp            = require('gulp'),
    $changed         = require('gulp-changed'),
    $less	         = require('gulp-less'),
    $jshint	         = require('gulp-jshint'),
    $eslint	         = require('gulp-eslint'),
    $plumberNotifier = require('gulp-plumber-notifier'),
    $gulpIf          = require('gulp-if'),
    $esLintFix       = require('gulp-eslint-if-fixed'),
    $prefix          = require('gulp-autoprefixer'),
    $concat          = require('gulp-concat'),
    $sourcemaps      = require('gulp-sourcemaps'),
    $tsc              = require('gulp-typescript'),
        
    $bowerFile 	     = require('main-bower-files'),
    $del             = require('del'),
    $browserSync     = require('browser-sync'),

    $injector  = require('./gulp-injector'),
    $const     = require('./gulp-constant');

var tsProject = $tsc.createProject($const.tsConfig);

module.exports = {
    clean: cleanFn,
    cleanTsc: cleanTsc,
    tsc:tsc,
    esLint:esLint,
    esLintFix:esLintFix

}

function esLint(){
   return function(){
       return $gulp.src([$const.all_js])
        .pipe($plumberNotifier())
        .pipe($eslint())
        .pipe($eslint.format())
        .pipe($eslint.failOnError())
   }
}

function esLintFix(){
    return function(){
        return $gulp.src([$const.all_js])
        .pipe($plumberNotifier())
        .pipe($eslint({
            fix: true
        }))
        .pipe($eslint.format())
        .pipe($esLintFix($const.webapp))
    }
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

function tsc(){
    return function(){
        var allTs = $const.allTs;    
       $gulp.src([$const.webapp + '{,/!(bower_components)}/*.ts'])
                .pipe($plumberNotifier())
                .pipe($sourcemaps.init())                                
                .pipe(tsProject())
                .pipe($sourcemaps.write())
                .pipe($gulp.dest($const.webapp + '/dist'));
    }
}

function cleanTsc(){
    return  function(){
        $del([$const.webapp + '/dist']);      
    }
}
