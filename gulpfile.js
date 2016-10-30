/**
 * Copyright (c) 2016 Olivier nirina.
 *
 * @author Olivier nirina
 * @since 1.0
 */

'use strict';

/// Object requirement declaration

var $gulp            = require('gulp'),
    $changed         = require('gulp-changed'),
    $less	         = require('gulp-less'),
    $bowerFile 	     = require('main-bower-files'),
    $jshint	         = require('gulp-jshint'),
    $eslint	         = require('gulp-eslint'),
    $plumberNotifier = require('gulp-plumber-notifier'),
    $gulpIf          = require('gulp-if'),
    $esLintFix       = require('gulp-eslint-if-fixed'),
    $del             = require('del'),
    $browserSync     = require('browser-sync'),
    $const           = require('./gulp_modules/gulp-constant'),
    $taskFunction    = require('./gulp_modules/gulp-task-function');

/// Function: tasks declaration

function esLint(){
    $gulp.src([$const.all_js])
        .pipe($plumberNotifier())
        .pipe($eslint())
        .pipe($eslint.format())
        .pipe($eslint.failOnError())
}

function esLintFix(){
    $gulp.src([$const.all_js])
        .pipe($plumberNotifier())
        .pipe($eslint({
            fix: true
        }))
        .pipe($eslint.format())
        .pipe($esLintFix($const.webapp))
}


/// Gulp tasks declaration

$gulp.task('clean', $taskFunction.clean($const.to_del));
///$gulp.task('install', esLintFix);
$gulp.task('inject:jsDev', $taskFunction.injectDev($const.jsExt));
$gulp.task('inject:jsProd', $taskFunction.injectProd($const.jsExt));
$gulp.task('eslint', esLint);
$gulp.task('eslint:fix', esLintFix);
