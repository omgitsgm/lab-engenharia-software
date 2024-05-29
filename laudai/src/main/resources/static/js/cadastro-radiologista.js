const url = "http://localhost:8080";
const form = document.querySelector('[data-form]');
const divErros = document.querySelector('#div-erros')


document.addEventListener("DOMContentLoaded", function() {
    // Função para buscar dados da API
    async function carregarLaboratorios() {
        const response = await fetch(`${url}/laboratorio`);
        if (!response.ok) {
            throw new Error('Erro ao buscar dados da API');
        }
        const laboratorios = await response.json();

        // Seleciona o elemento <select> no HTML
        const selectLab = document.getElementById('lab');

        // Limpa as opções atuais (caso existam)
        selectLab.innerHTML = '';

        // Adiciona as novas opções dinâmicas
        laboratorios.forEach(lab => {
            const option = document.createElement('option');
            option.value = lab.id; // ajuste conforme os dados retornados pela API
            option.textContent = lab.nome; // ajuste conforme os dados retornados pela API
            selectLab.appendChild(option);
        });
    }
    
    // Chama a função para carregar os laboratórios quando a página for carregada
    carregarLaboratorios();
});

form.addEventListener("submit", async (e) => {
    e.preventDefault();

    var option = document.getElementById("lab");

    const dados = {
        "nome": e.target.elements["nome"].value,
        "crm": e.target.elements["crm"].value,
        "laboratorioId": option.value,
        "senha": e.target.elements["senha"].value
    }

    let json = JSON.stringify(dados);
    console.log("12:31")
    console.log(json);

    saveRadiologista(json);
})

async function saveRadiologista(json) {

    const response = await fetch(`${url}/radiologista`, {
        method: "POST",
        body: json,
        headers: {
            "Content-type": "application/json"
        }
    });

    const data = await response.json();
    console.log(data);

    if(!response.ok) {
        console.log(data.title);

        const paragraphElement = document.createElement("p");
        paragraphElement.innerText = data.title;

        if(data.status === 400){
            // PRECISA EXIBIR OS CAMPOS INVÁLIDOS
        }

        divErros.innerHTML = "";
        divErros.appendChild(paragraphElement);
    } else {
        window.alert(data.title);
        window.location.href = url;
    }
}

