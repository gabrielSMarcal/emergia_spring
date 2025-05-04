document.addEventListener("DOMContentLoaded", () => {
    // Recupera os dados do localStorage
    const resultsString = localStorage.getItem("calcResults");
    if (!resultsString || resultsString === "undefined") {
        document.body.innerHTML = "<h2>Nenhum resultado encontrado.</h2>";
        return;
    }
    const calcResults = JSON.parse(resultsString);
  
    // Cria a tabela com três colunas
    const table = document.createElement("table");
    table.style.borderCollapse = "collapse";
    table.style.width = "100%";
  
    // Cabeçalho da tabela
    const thead = document.createElement("thead");
    thead.innerHTML = `
        <tr>
            <th style="border: 1px solid #ccc; padding: 8px;">Item usado</th>
            <th style="border: 1px solid #ccc; padding: 8px;">Input inserido pelo usuário</th>
            <th style="border: 1px solid #ccc; padding: 8px;">Resultado</th>
        </tr>
    `;
    table.appendChild(thead);
  
    // Corpo da tabela
    const tbody = document.createElement("tbody");
    for (const key in calcResults) {
        const data = calcResults[key];
        const tr = document.createElement("tr");
        tr.innerHTML = `
            <td style="border: 1px solid #ccc; padding: 8px;">${data.label}</td>
            <td style="border: 1px solid #ccc; padding: 8px;">${data.inputs}</td>
            <td style="border: 1px solid #ccc; padding: 8px;">${data.result}</td>
        `;
        tbody.appendChild(tr);
    }
    table.appendChild(tbody);
  
    // Insere a tabela na página
    let container = document.getElementById("tableContainer");
    if (!container) {
        container = document.createElement("div");
        container.id = "tableContainer";
        document.body.appendChild(container);
    }
    container.appendChild(table);
});