const url = "http://localhost:8080";
const sairButton = document.querySelector('[data-sair]');
const listGroup = document.querySelector('[data-list-group]');

sairButton.addEventListener("click", (e) => {
    e.preventDefault();

    // Removo o userId do sessionStorage, porque ele está realizando um 'logout'
    sessionStorage.removeItem("userId");

    // Redireciono o usuário pra página inicial do Laud.AI
    window.location.href = url;
})

sairButton.addEventListener("click", (e) => {
    e.preventDefault();

    // Remove o userId do sessionStorage, porque ele está realizando um 'logout'
    sessionStorage.removeItem("userId");

    // Redireciona o usuário para a página inicial do Laud.AI
    window.location.href = url;
});

async function getConsultas(id) {
    const userId = sessionStorage.getItem("userId");

    const response = await fetch(`${url}/laboratorio/${userId}/consulta`);
    console.log(response);



    const data = await response.json();
    console.log(data);


    data.map((consulta) => {
        document.getElementById("title").innerHTML = "Consultas - " + consulta.nomeLaboratorio;

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

        const consultaNum = document.createElement("p");
        consultaNum.classList.add("mb-0", "opacity-75", "text-left");
        consultaNum.innerText = "Consulta " + consulta.id;

        const dataHorario = document.createElement("small");
        dataHorario.classList.add("opacity-50", "text-nowrap");
        dataHorario.innerText = consulta.dataHorario;

        const nomePaciente = document.createElement("p");
        nomePaciente.classList.add("mb-0", "opacity-75", "text-left");
        nomePaciente.innerText = "Paciente: " + consulta.nomePaciente;

        const link = document.createElement("a");
        link.id = consulta.id;
        link.classList.add("imagem-button");

        const imagem = document.createElement("img");
        imagem.src = "img/teste-de-sangue.png";
        imagem.alt = `Botão redirecionar a página ${consulta.id}.`;
        imagem.width = "32";
        imagem.classList.add("rounded-circle", "flex-shrink-0");

        link.appendChild(imagem);

        div3.appendChild(consultaNum);
        div3.appendChild(exame);
        div3.appendChild(nomePaciente);

        div2.appendChild(div3);
        div2.appendChild(dataHorario);
        div2.appendChild(link);

        div1.appendChild(div2);

        listGroup.appendChild(div1);
    });

    const redirecionarButton = document.querySelectorAll(".imagem-button");
    redirecionarButton.forEach((button) => {
        button.addEventListener('click', (e) => {
            e.preventDefault();

            // Redireciono o usuário pra página inicial do Laud.AI
            window.location.href = `${url}/ver-exames.html`;
        });
    })
}

if(sessionStorage.getItem("userId")){
    console.log("Usuário logado. Obtendo consultas...");
    getConsultas();
} else {
    console.log("Usuário não está logado. Redirecionando para a página de login...");
    window.alert("Realize o login para poder visualizar as suas consultas.");
    window.location.href = `${url}/login`;
}
