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
    $const              =   require('./gulp-constant');

/// custom node module exportation

module.exports = {
    app:   app,
    vendor:  vendor
}

/// Function declaration
/**
 * <b>Development mode:</b><br>
 * Inject all javascript files within the root of the web application
 * in the index.html. The location of the injection is denoted by tags:
 * <br> starttag: <!--app:js--> and endtag: <!--endapp-->.
 * <br> Those tags are default for module <code>gulp-inject</code>
 * @returns {*}
 */
function app() {
    return $gulp.src($const.webapp + '/index.html')
        .pipe($plumberNotifier())
        .pipe($inject($gulp.src($const.all_js)
            .pipe($naturalSort())
            .pipe($ngFilesort()),{
                name: 'app',
                relative:true
            }))
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
function vendor() {
    return $gulp.src($const.webapp + '/index.html')
        .pipe($plumberNotifier())
        .pipe($inject($gulp.src($bowerFile(),{read: false}), {
            name: 'bower',
            relative:true
        }))
        .pipe($gulp.dest($const.webapp));

}

