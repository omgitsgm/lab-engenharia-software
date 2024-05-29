const url = "http://localhost:8080";
const sairButton = document.querySelector('[data-sair]');
const listGroup = document.querySelector('[data-list-group]');
const userId = sessionStorage.getItem("userId");


sairButton.addEventListener("click", (e) => {
    e.preventDefault();

    // Remove o userId do sessionStorage, porque ele está realizando um 'logout'
    sessionStorage.removeItem("userId");

    // Redireciona o usuário para a página inicial do Laud.AI
    window.location.href = url;
});

/*
document.addEventListener("DOMContentLoaded", function() {
    // Função para buscar dados da API
    async function carregarExames() {
        const response = await fetch(`${url}/laboratorio/${userId}/exame`);
        if (!response.ok) {
            throw new Error('Erro ao buscar dados da API');
        }
        const exames = await response.json();

        // Seleciona o elemento <select> no HTML
        const selectExame = document.getElementById('exameSelect');

        // Limpa as opções atuais (caso existam)
        selectExame.innerHTML = '';
        const option1 = document.createElement('option');
        option1.value = "";
        option1.textContent = "Selecione um exame";
        selectExame.appendChild(option1);

        // Adiciona as novas opções dinâmicas
        exames.forEach(exame => {
            const option = document.createElement('option');
            option.value = exame.id; // ajuste conforme os dados retornados pela API
            option.textContent = exame.nome; // ajuste conforme os dados retornados pela API
            selectExame.appendChild(option);
        });
    }
    

    
    // Chama a função para carregar os laboratórios quando a página for carregada
    carregarExames();
});
*/

async function getExames(id) {
    const response = await fetch(`${url}/laboratorio/${userId}/exame`);
    console.log(response);


    const data = await response.json();
    console.log(data);


    data.map((exame) => {
        const div1 = document.createElement("div");
        div1.classList.add("list-group-item", "d-flex", "gap-3", "py-3");
        div1.setAttribute("aria-current", "true");

        const div2 = document.createElement("div");
        div2.classList.add("d-flex", "gap-2", "w-100", "justify-content-between");

        const div3 = document.createElement("div");
        div3.style.textAlign = 'left';

        const exameNome = document.createElement("h6");
        exameNome.classList.add("mb-0");
        exameNome.innerText = exame.nome;

        const exameNum = document.createElement("p");
        exameNum.classList.add("mb-0", "opacity-75", "text-left");
        exameNum.innerText = "Exame " + exame.id;

        const link = document.createElement("a");
        link.id = exame.id;
        link.classList.add("imagem-button");

        const imagem = document.createElement("img");
        imagem.src = "img/teste-de-sangue.png";
        imagem.alt = `Botão para subir imagem ${exame.id}.`;
        imagem.width = "32";
        imagem.classList.add("rounded-circle", "flex-shrink-0");

        link.appendChild(imagem);

        div3.appendChild(exameNum);
        div3.appendChild(exameNome);

        div2.appendChild(div3);

        div2.appendChild(link);

        div1.appendChild(div2);

        listGroup.appendChild(div1);
    });

    // Adiciona o input de arquivo e o event listener para upload de imagem
    const listSubirImagemButton = document.querySelectorAll(".imagem-button");
    listSubirImagemButton.forEach((button) => {
        button.addEventListener('click', (e) => {
            e.preventDefault();

            const inputFile = document.createElement('input');
            inputFile.type = 'file';
            inputFile.accept = 'image/*';
            inputFile.style.display = 'none';

            inputFile.addEventListener('change', async (event) => {
                const file = event.target.files[0];
                if (file) {
                    const formData = new FormData();
                    formData.append('imagem', file);

                    try {
                        const response = await fetch(`${url}/consulta/${userId}/imagem`, {
                            method: 'PUT',
                            body: file
                        });

                        if (response.ok) {
                            window.alert('Imagem enviada com sucesso!');
                        } else {
                            window.alert('Erro ao enviar a imagem.');
                        }
                    } catch (error) {
                        console.error('Erro:', error);
                        window.alert('Erro ao enviar a imagem.');
                    }
                }
            });

            inputFile.click();
        });
    });
}

if(sessionStorage.getItem("userId")){
    console.log("Usuário logado. Obtendo consultas...");
    getExames();
} else {
    console.log("Usuário não está logado. Redirecionando para a página de login...");
    window.alert("Realize o login para poder visualizar as suas consultas.");
    window.location.href = `${url}/login`;
}
