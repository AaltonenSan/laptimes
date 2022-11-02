// Logout onClick. Used to hide the href from the user.
$(document).ready(function() {
    $("#logoutLink").on("click", function(e) {
        e.preventDefault();
        document.logoutForm.submit();
    });
});