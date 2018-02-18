$(document).ready(function() {
    $('.navbar .navbar-nav a[href="' + window.location.pathname + '"]').parent().addClass("active");
});