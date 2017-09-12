// Include gulp
var gulp = require('gulp');
// Include plugins 
var eslint = require('gulp-eslint');
var del = require('del');
var uglify = require('gulp-uglify');
var usemin = require('gulp-usemin');
var minifyHtml = require('gulp-minify-html');
var minifycss = require('gulp-minify-css');
var rev = require('gulp-rev');
var concat = require('gulp-concat');
var rename = require('gulp-rename');
var sass = require('gulp-sass');
var less = require('gulp-less');
var browserSync = require('browser-sync').create();
var livereload = require('gulp-livereload');
var gutil = require('gulp-util');
var sourcemaps = require('gulp-sourcemaps');
var rimraf = require('rimraf');
var inject = require('gulp-inject');
var embedTemplates = require('gulp-angular-embed-templates');
var mainBowerFiles = require('main-bower-files');
var gulpFilter = require('gulp-filter');
var wiredep = require('wiredep').stream;
var runSequence = require('gulp-run-sequence');
var angularTemplateCache = require('gulp-angular-templatecache');
var addStream = require('add-stream');
var notify = require("gulp-notify");
var order = require('gulp-order');
var concatCss = require('gulp-concat-css');
var flatten = require('gulp-flatten');
var jsValidate = require('gulp-jsvalidate');
var gulpMerge = require('gulp-merge');

var swallowError = function(err) {
  gutil.log(err.toString());
  this.emit('end');
};

var prepareTemplates = function() {
  return gulp.src('app/**/*.html')
    //.pipe(minify and preprocess the template html here)
    .pipe(angularTemplateCache());
};

gulp.task('lint', function() {
  // ESLint ignores files with "node_modules" paths. 
  // So, it's best to have gulp ignore the directory as well. 
  // Also, Be sure to return the stream from the task; 
  // Otherwise, the task may end before the stream has finished. 
  return gulp.src(['**/*.js', '!node_modules/**', '!bower_components/**'])
  // eslint() attaches the lint output to the "eslint" property 
  // of the file object so it can be used by other modules. 
    .pipe(eslint())
  // eslint.format() outputs the lint results to the console. 
  // Alternatively use eslint.formatEach() (see Docs). 
    .pipe(eslint.format())
  // To have the process exit with an error code (1) on 
  // lint error, return the stream and pipe to failAfterError last. 
    .pipe(eslint.failAfterError());
});

gulp.task('browser-sync', function() {
  browserSync.reload();
});

gulp.task('build-bundle-css', function() {
  del.sync('../src/main/webapp/dist/css/bundle.min.css', {force: true});
  var cssStream = gulp.src('./app/**/*.css');
  var lessStream = gulp.src('./app/**/*.less')
    .pipe(less());
  var scssStream = gulp.src('./app/**/*.scss')
    .pipe(sass());
  var mergedStream = gulpMerge(lessStream, scssStream);
  mergedStream = gulpMerge(mergedStream, cssStream);

  return mergedStream.pipe(concat('bundle.min.css'))
    .pipe(gulp.dest("../src/main/webapp/dist/css"));
});

gulp.task('build-vendor-css', function() {
  del.sync('../src/main/webapp/dist/css/vendor.min.css', {force: true});
  var cssStream = gulp.src(mainBowerFiles('**/*.css'));
  var lessStream = gulp.src(mainBowerFiles('**/*.less'))
    .pipe(less());
  var scssStream = gulp.src(mainBowerFiles('**/*.scss'))
    .pipe(sass());
  var mergedStream = gulpMerge(lessStream, scssStream);
  mergedStream = gulpMerge(mergedStream, cssStream);

  return mergedStream.pipe(concat('vendor.min.css'))
    .pipe(gulp.dest("../src/main/webapp/dist/css"));
});

gulp.task('default', ['build-clean']);

gulp.task('build-clean', function(cb) {
  runSequence('clean', ['validate', 'build-clean-js-html', 'build-clean-vendor-js',
    'build-clean-vendor-css', 'build-clean-bundle-css', 'build-fonts', 'build-images', 'build-index'], cb);
});

gulp.task('build-fonts', function() {
  del.sync('../src/main/webapp/dist/fonts', {force: true});
  return gulp.src(mainBowerFiles('**/*.{eot,svg,ttf,woff,woff2,otf}'))
    .pipe(flatten())
    .pipe(gulp.dest('../src/main/webapp/dist/fonts/'));
});

gulp.task('build-images', function() {
  del.sync('../src/main/webapp/dist/images', {force: true});
  return gulp.src(mainBowerFiles('**/*.{png}'))
    .pipe(flatten())
    .pipe(gulp.dest('../src/main/webapp/dist/images/'));
});

gulp.task('build-index', function() {
  return gulp.src('./app/index.html')
    .pipe(gulp.dest('../src/main/webapp/dist'));
});

gulp.task('validate', function() {
  return gulp.src('./app/**/*.js')
    .pipe(order(['main/**.js', 'constants/**.js', 'services/**.js']))
    .pipe(jsValidate().on('error', swallowError));
});


gulp.task('build-clean-js-html', function(cb) {
  del.sync(['../src/main/webapp/dist/js/bundle.min.js',
    '../src/main/webapp/dist/js/bundle.min.js.map'], {force: true});
  runSequence('build-js-html', cb);
});

gulp.task('build-js-html', function() {
  return gulp.src('./app/**/*.js')
    .pipe(sourcemaps.init())
    .pipe(order(['index.js', 'indexController.js', 'constants/**.js', 'services/**.js']))
    //.pipe(embedTemplates())
    .pipe(addStream.obj(prepareTemplates()))
    .pipe(concat('bundle.min.js'))
    .pipe(uglify().on('error', swallowError))
    .pipe(sourcemaps.write('./'))
    .pipe(gulp.dest('../src/main/webapp/dist/js'));
});

gulp.task('build-clean-vendor-js', function(cb) {
  del.sync('../src/main/webapp/dist/js/vendor.min.js', {force: true});
  runSequence('build-vendor-js', cb);
});

// inject bower components
gulp.task('build-vendor-js', function() {
  var filterJS = gulpFilter('**/*.js');
  return gulp.src(mainBowerFiles(), {base: './bower_components'})
    .pipe(filterJS)
    .pipe(concat('vendor.min.js'))
    .pipe(uglify().on('error', swallowError))
    .pipe(gulp.dest("../src/main/webapp/dist/js"));
});

gulp.task('build-clean-vendor-css', function(cb) {
  del.sync('../src/main/webapp/dist/css/vendor.min.css', {force: true});
  runSequence('build-vendor-css', cb);
});

gulp.task('build-clean-bundle-css', function(cb) {
  del.sync('../src/main/webapp/dist/css/bundle.min.css', {force: true});
  runSequence('build-bundle-css', cb);
});

// inject bower components
gulp.task('build-vendor-css', function() {
  return gulp.src(mainBowerFiles('**/*.css'))
    //.pipe(concatCss('vendor.min.css')) // concatCss -> replace css source url with the relative path 
    .pipe(concat('vendor.min.css'))
    .pipe(minifycss())
    .pipe(gulp.dest("../src/main/webapp/dist/css"));
});

gulp.task("clean", function(cb) {
  rimraf('../src/main/webapp/dist', cb);
});

gulp.task('watch', function() {
  gulp.watch(['app/**/*.js', 'app/**/*.html', 'app/index.html'], function() {
    runSequence('validate', 'build-clean-js-html', 'build-index', 'browser-sync');
  });
  gulp.watch(['app/**/*.css', 'app/**/*.less', 'app/index.scss'], function() {
    runSequence('build-clean-bundle-css', 'browser-sync');
  });
});

// watch files for changes and reload
gulp.task('serve', function(cb) {
  runSequence('build-clean', 'watch', cb);
  browserSync.init({
    server: '../src/main/webapp/dist',
    port: 3000,
    notify: false
  });
});
