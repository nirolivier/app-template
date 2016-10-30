/**
 * Copyright (c) 2016 Olivier nirina.
 *
 * @author Olivier nirina
 * @since 1.0
 */

'use strict';

/// Required module declaration

var $gulp               =   require('gulp'),
    $plumberNotifier    =   require('gulp-plumber-notifier'),
    $inject             =   require('gulp-inject'),
    $naturalSort        =   require('gulp-natural-sort'),
    $ngFilesort         =   require('gulp-angular-filesort'),
    $bowerFile 	        =   require('main-bower-files'),
    $const              =   require('./gulp_modules/gulp-constant');

/// custom node module exportation

module.exports = {
    jsDev:   jsDevFn,
    jsProd:  jsProdFn
}

/// Function declaration
/**
 * <b>Development mode:</b><br>
 * Inject all javascript files within the root of the web application
 * in the index.html. The location of the injection is denoted by tags:
 * <br> starttag: <!--inject:js--> and endtag: <!--endinject-->.
 * <br> Those tags are default for module <code>gulp-inject</code>
 * @returns {*}
 */
function jsDevFn() {
    return $gulp.src($const.webapp + 'index.html')
        .pipe($inject($gulp.src($const.all_js)
            .pipe($naturalSort())
            .pipe($ngFilesort()), {relative:true}))
        .pipe($gulp.dest($const.webapp));
}

/**
 * <b>Production mode:</b><br>
 * Inject all javascript files within the root of the web application
 * in the index.html. The location of the injection is relative and denoted by tags:
 * <br> starttag: <!--prod:js--> and endtag: <!--endprod-->.
 * <br> Those tags are default for module <code>gulp-inject</code>
 * @returns {*}
 */
function jsProdFn() {
    return $gulp.src($const.webapp + 'index.html')
        .pipe($plumberNotifier())
        .pipe($inject($gulp.src($bowerFile()), {read: false}, {
            name: 'prod',
            relative:true
        }))
        .pipe($gulp.dest($const.webapp));

}

