const url = "http://localhost:8080";
const sairButton = document.querySelector('[data-sair]');

sairButton.addEventListener("click", (e) => {
    e.preventDefault();

    // Removo o userId do sessionStorage, porque ele está realizando um 'logout'
    sessionStorage.removeItem("userId");

    // Redireciono o usuário pra página inicial do Laud.AI
    window.location.href = url;


})