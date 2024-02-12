function previewImage(input) {
    let preview = document.getElementById('imagePreview');
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
        form.style.display = (form.style.display === 'none' || form.style.display === '') ? 'block' : 'none';
    }
}

function toggleLoginPopup() {
    let loginPopup = document.getElementById('login-popup');
    let body = document.body;

    if (loginPopup.style.display === 'block') {
        loginPopup.style.display = 'none';
        body.style.overflow = 'auto';
    } else {
        loginPopup.style.display = 'block';
        body.style.overflow = 'hidden';
    }
}

function toggleSignupPopup() {
    let signupPopup = document.getElementById('signup-popup');
    let body = document.body;

    if (signupPopup.style.display === 'block') {
        signupPopup.style.display = 'none';
        body.style.overflow = 'auto';
    } else {
        signupPopup.style.display = 'block';
        body.style.overflow = 'hidden';
    }
}