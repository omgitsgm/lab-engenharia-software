const url = "http://laudai-java-env-1.eba-yyhcjp3c.us-east-1.elasticbeanstalk.com";
const sairButton = document.querySelector('[data-sair]');
const minhasConsultasButton = document.querySelector('[data-minhas-consultas]');
const agendarButton = document.querySelector('[data-agendar]');

sairButton.addEventListener("click", (e) => {
    e.preventDefault();

    // Removo o userId do sessionStorage, porque ele está realizando um 'logout'
    sessionStorage.removeItem("userId");

    // Redireciono o usuário pra página inicial do Laud.AI
    window.location.href = url;


})

minhasConsultasButton.addEventListener("click", (e) => {
    e.preventDefault();

    // Redireciono o usuário pra página inicial do Laud.AI
    window.location.href = `${url}/minhas-consultas`;


})

agendarButton.addEventListener("click", (e) => {
    e.preventDefault();

    // Redireciono o usuário pra página inicial do Laud.AI
    window.location.href = `${url}/agendar-consulta`;


})