function previewImage(input, id) {
    let preview = document.getElementById(id);
    let file = input.files[0];
    let reader = new FileReader();

    reader.onload = function (e) {
        preview.src = e.target.result;
        preview.style.display = 'block';
    };

    if (file) {
        reader.readAsDataURL(file);
    }
}

function toggleForm(formId) {
    let form = document.getElementById(formId);
    if (form) {
        form.style.display = (form.style.display === 'none' || form.style.display === '') ? 'flex' : 'none';
    }
}

function toggleLoginPopup() {
    let loginPopup = document.getElementById('login-popup');
    let signupPopup = document.getElementById('signup-popup');
    let body = document.body;

    document.body.classList.add("remove-scrolling");

    if (loginPopup.style.display === 'block') {
        loginPopup.style.display = 'none';
        body.style.overflow = 'auto';
    } else {
        loginPopup.style.display = 'block';
        signupPopup.style.display = 'none';
        body.style.overflow = 'hidden';
    }
}

function toggleSignupPopup() {
    let signupPopup = document.getElementById('signup-popup');
    let loginPopup = document.getElementById('login-popup');
    let body = document.body;

    document.body.classList.add("remove-scrolling");

    if (signupPopup.style.display === 'block') {
        signupPopup.style.display = 'none';
        body.style.overflow = 'auto';
    } else {
        signupPopup.style.display = 'block';
        loginPopup.style.display = 'none';
        body.style.overflow = 'hidden';
    }
}

document.querySelectorAll('.download-pdf').forEach(function(link) {
    link.addEventListener('click', function(event) {
        setTimeout(function() {
            window.location.href = '/';
        }, 3000);
    });
});