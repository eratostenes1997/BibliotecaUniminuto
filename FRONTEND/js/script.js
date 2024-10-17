document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('loginForm').addEventListener('submit', async function (event) {
        event.preventDefault();

        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;
        const errorMessage = document.getElementById('error-message');

        try {
            const response = await fetch('http://localhost:8080/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ username, password })
            });

            if (response.ok) {
                const result = await response.text();
                if (result === 'Login exitoso') {
                   
                    window.location.href = 'pages/libros.html';
                } else {
                    errorMessage.textContent = result;
                }
            } else {
                errorMessage.textContent = 'Error en el servidor.';
            }
        } catch (error) {
            errorMessage.textContent = 'No se pudo conectar con el servidor.';
        }
    });
});
