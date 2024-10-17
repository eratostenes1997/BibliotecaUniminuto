const baseUrl = 'http://localhost:8080';  


document.getElementById('crearLibroForm')?.addEventListener('submit', async function(event) {
    event.preventDefault();
    
    const titulo = document.getElementById('titulo').value;
    const autor = document.getElementById('autor').value;

    try {
        const response = await fetch(`${baseUrl}/adicionar`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ titulo, autor })
        });

        const mensaje = await response.text();
        document.getElementById('mensaje').textContent = mensaje;
    } catch (error) {
        console.error('Error al hacer la solicitud:', error);
        document.getElementById('mensaje').textContent = 'Hubo un error al conectar con el servidor.';
    }
});


document.addEventListener('DOMContentLoaded', async function() {
    const librosList = document.getElementById('librosList');
    
    if (librosList) {
        try {
            const response = await fetch(`${baseUrl}/libros`);
            const libros = await response.json();

           
            librosList.innerHTML = '';

      
            librosList.innerHTML = libros.map(libro => 
                `<tr>
                    <td>${libro.id}</td>
                    <td>${libro.titulo}</td>
                    <td>${libro.autor}</td>
                </tr>`
            ).join('');

        } catch (error) {
            console.error('Error al obtener los libros:', error);
            librosList.innerHTML = 'Hubo un error al cargar los libros.';
        }
    }
});

document.getElementById('actualizarLibroForm')?.addEventListener('submit', async function(event) {
    event.preventDefault();
    
    const id = document.getElementById('id').value;
    const titulo = document.getElementById('titulo').value;
    const autor = document.getElementById('autor').value;

    try {
        const response = await fetch(`${baseUrl}/editar/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ titulo, autor })
        });

        const mensaje = await response.text();
        document.getElementById('mensaje').textContent = mensaje;
    } catch (error) {
        console.error('Error al actualizar el libro:', error);
        document.getElementById('mensaje').textContent = 'Hubo un error al conectar con el servidor.';
    }
});


document.getElementById('eliminarLibroForm')?.addEventListener('submit', async function(event) {
    event.preventDefault();
    
    const id = document.getElementById('id').value;

    try {
        const response = await fetch(`${baseUrl}/borrar/${id}`, {
            method: 'DELETE'
        });

        const mensaje = await response.text();
        document.getElementById('mensaje').textContent = mensaje;
    } catch (error) {
        console.error('Error al eliminar el libro:', error);
        document.getElementById('mensaje').textContent = 'Hubo un error al conectar con el servidor.';
    }
});
