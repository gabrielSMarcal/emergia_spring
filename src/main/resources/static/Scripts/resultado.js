document.addEventListener("DOMContentLoaded", () => {
    // Recupera os resultados do localStorage
    const resultsString = localStorage.getItem("calcResults");
    console.log(localStorage.getItem("calcResults"));
    if (!resultsString) {
        document.body.innerHTML = "<h2>Nenhum resultado encontrado.</h2>";
        return;
    }
    const calcResults = JSON.parse(resultsString);

    // Cria uma tabela
    const table = document.createElement("table");
    table.style.borderCollapse = "collapse";
    table.style.width = "100%";
  
    // Cabeçalho da tabela
    const thead = document.createElement("thead");
    thead.innerHTML = `
        <tr>
            <th style="border: 1px solid #ccc; padding: 8px;">Cálculo</th>
            <th style="border: 1px solid #ccc; padding: 8px;">Resultado</th>
        </tr>
    `;
    table.appendChild(thead);
  
    // Corpo da tabela
    const tbody = document.createElement("tbody");
    for (const key in calcResults) {
        const tr = document.createElement("tr");
        tr.innerHTML = `
            <td style="border: 1px solid #ccc; padding: 8px;">${key}</td>
            <td style="border: 1px solid #ccc; padding: 8px;">${parseFloat(calcResults[key]).toFixed(2)}</td>
        `;
        tbody.appendChild(tr);
    }
    table.appendChild(tbody);
  
    // Adiciona a tabela a um container na página (certifique-se de ter um elemento com id "tableContainer")
    let container = document.getElementById("tableContainer");
    if (!container) {
        container = document.createElement("div");
        container.id = "tableContainer";
        document.body.appendChild(container);
    }
    container.appendChild(table);
});