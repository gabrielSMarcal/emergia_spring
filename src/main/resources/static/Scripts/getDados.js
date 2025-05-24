const baseURL = 'http://localhost:8081/';

export default function getDados(endpoint) {
    return fetch(`${baseURL}${endpoint}`)
        .then(responde => responde.json())
        .catch(error => {
            console.error('Erro ao buscar dados:', error);
            throw error;
        })
}