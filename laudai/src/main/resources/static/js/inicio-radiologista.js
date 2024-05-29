const url = "http://localhost:8080";
const sairButton = document.querySelector('[data-sair]');
const verExamesButton = document.querySelector('[data-ver-exames]');
const subirImagemButton = document.querySelector('[data-subir-imagem]');

sairButton.addEventListener("click", (e) => {
    e.preventDefault();

    // Removo o userId do sessionStorage, porque ele está realizando um 'logout'
    sessionStorage.removeItem("userId");

    // Redireciono o usuário pra página inicial do Laud.AI
    window.location.href = url;


})

verExamesButton.addEventListener("click", (e) => {
    e.preventDefault();

    // Redireciono o usuário pra página inicial do Laud.AI
    window.location.href = `${url}/ver-exames.html`;
})

subirImagemButton.addEventListener("click", (e) => {
    e.preventDefault();

    // Redireciono o usuário pra página inicial do Laud.AI
    window.location.href = `${url}/subir-imagem.html`;
})