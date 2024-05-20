const url = "http://laudai-java-env-1.eba-yyhcjp3c.us-east-1.elasticbeanstalk.com";
const sairButton = document.querySelector('[data-sair]');
const listGroup = document.querySelector('[data-list-group]');

sairButton.addEventListener("click", (e) => {
    e.preventDefault();

    // Removo o userId do sessionStorage, porque ele está realizando um 'logout'
    sessionStorage.removeItem("userId");

    // Redireciono o usuário pra página inicial do Laud.AI
    window.location.href = url;


})

async function getConsultas(id) {
    const userId = sessionStorage.getItem("userId");

    const response = await fetch(`${url}/paciente/${userId}/consulta`);
    console.log(response);

    const data = await response.json();
    console.log(data);

    data.map((consulta) => {
        const div1 = document.createElement("div");
        div1.classList.add("list-group-item", "d-flex", "gap-3", "py-3");
        div1.setAttribute("aria-current", "true");

        const div2 = document.createElement("div");
        div2.classList.add("d-flex", "gap-2", "w-100", "justify-content-between");

        const div3 = document.createElement("div");
        div3.style.textAlign = 'left';

        const exame = document.createElement("h6");
        exame.classList.add("mb-0");
        exame.innerText = consulta.nomeExame;

        const laboratorio = document.createElement("p");
        laboratorio.classList.add("mb-0", "opacity-75", "text-left");
        laboratorio.innerText = consulta.nomeLaboratorio;

        const dataHorario = document.createElement("small");
        dataHorario.classList.add("opacity-50", "text-nowrap");
        dataHorario.innerText = consulta.dataHorario;

        const link = document.createElement("a");
        link.id = consulta.id; // Será utilizado para gerar o link da requisição de delete
        link.classList.add("cancelar-button");

        const imagem = document.createElement("img");
        imagem.src = "img/cancelar.png";
        imagem.alt = `Botão para cancelar a consulta ${consulta.id}.`;
        imagem.width = "32";
        imagem.classList.add("rounded-circle", "flex-shrink-0");

        link.appendChild(imagem);

        div3.appendChild(exame);
        div3.appendChild(laboratorio);

        div2.appendChild(div3);
        div2.appendChild(dataHorario);
        div2.appendChild(link);

        div1.appendChild(div2);

        listGroup.appendChild(div1);
    })

    const listCancelarButton = document.querySelectorAll(".cancelar-button");
    listCancelarButton.forEach((button) => {
        button.addEventListener('click', (e) => {
            e.preventDefault();

            fetch(`${url}/paciente/${userId}/consulta/${button.id}`, {
                method: "DELETE",
            })

            window.location.reload();

        })
    })

}

if(sessionStorage.getItem("userId")){
    console.log("Usuário logado. Obtendo consultas...")
    getConsultas();
} else {
    console.log("Usuário não está logado. Redirecionando para a página de login...")
    window.alert("Realize o login para poder visualizar as suas consultas.");
    window.location.href = `${url}/login`
}

