/**
 * Copyright (c) 2016 Olivier nirina.
 *
 * @author Olivier nirina
 * @since 1.0
 */

'use strict';

/// Object requirement declaration

var $gulp            = require('gulp'),
    $const           = require('./gulp_modules/gulp-constant'),
    $injector        = require('./gulp_modules/gulp-injector'),
    $taskFunction    = require('./gulp_modules/gulp-task-function');

/// Gulp tasks declaration

$gulp.task('clean', $taskFunction.clean($const.to_del));
$gulp.task('clean:tsc', $taskFunction.cleanTsc());
$gulp.task('clean:vendor', $taskFunction.cleanVendor());
$gulp.task('clean:inject', $taskFunction.cleanInject());
$gulp.task('tsc', $taskFunction.tsc());
$gulp.task('inject:app', $injector.app);
$gulp.task('inject:vendor', $injector.vendor);
$gulp.task('eslint', $taskFunction.esLint());
$gulp.task('eslint:fix', $taskFunction.esLintFix());
