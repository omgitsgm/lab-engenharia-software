const url = "http://localhost:8080";
const form = document.querySelector('[data-form]');
const divErros = document.querySelector('#div-erros')

form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const dados = {
        "nome": e.target.elements["nome"].value,
        "cpf": e.target.elements["cpf"].value,
        "email": e.target.elements["email"].value,
        "senha": e.target.elements["senha"].value,
    }

    let json = JSON.stringify(dados);
    console.log("12:31")
    console.log(json);

    savePaciente(json);


})

async function savePaciente(json) {

    const response = await fetch(`${url}/paciente`, {
        method: "POST",
        body: json,
        headers: {
            "Content-type": "application/json"
        }
    });

    const data = await response.json();
    console.log(data);

    divErros.innerHTML = "";
    if(!response.ok) {
        console.log(data.title);

        const paragraphElement = document.createElement("p");
        paragraphElement.innerText = data.title;

        if(data.status === 400){
            // PRECISA EXIBIR OS CAMPOS INVÁLIDOS
        }

        divErros.appendChild(paragraphElement);
    }
}

