const url = "http://laudai-lb.us-east-1.elasticbeanstalk.com";
const form = document.querySelector('[data-form]');
const divErros = document.querySelector('[data-erro]');

form.addEventListener("submit", async(e) => {
    e.preventDefault();

    const dados = {
        "email": e.target.elements["email"].value,
        "senha": e.target.elements["senha"].value,
    }

    let json = JSON.stringify(dados);
    console.log(json);

    autentica(json);

})

async function autentica(json) {

    const response = await fetch(`${url}/paciente/autenticar`, {
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

        divErros.innerHTML = "";
        divErros.appendChild(paragraphElement);
    } else {
        // Armazeno o id do usuário no sessionStorage para poder gerar as URL da área do paciente na pagina-inicial
        sessionStorage.setItem("userId", data.id);
        window.location.href = `${url}/pagina-inicial`;
    }


}