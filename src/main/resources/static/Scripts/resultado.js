document.addEventListener("DOMContentLoaded", () => {
    // Recupera os dados do localStorage
    const resultsString = localStorage.getItem("calcResults");
    if (!resultsString || resultsString === "undefined") {
        document.body.innerHTML = "<h2>Nenhum resultado encontrado.</h2>";
        return;
    }
    const calcResults = JSON.parse(resultsString);

    // Cria uma div container para centralizar a tabela
    let container = document.getElementById("tableContainer");
    if (!container) {
        container = document.createElement("div");
        container.id = "tableContainer";
        container.className = "table-wrapper"; // Classe para estilização
        document.body.appendChild(container);
    }

    // Cria a tabela com as 4 colunas desejadas
    const table = document.createElement("table");
    table.style.borderCollapse = "collapse";
    table.style.width = "100%";
    table.style.textAlign = "center";
    table.style.fontFamily = "Arial, sans-serif";

    // Cabeçalho da tabela
    const thead = document.createElement("thead");
    thead.innerHTML = `
        <tr style="background-color: #1faa06; color: white;">
            <th style="border: 1px solid #ccc; padding: 8px;">Item</th>
            <th style="border: 1px solid #ccc; padding: 8px;">Unid./ano</th>
            <th style="border: 1px solid #ccc; padding: 8px;">Transformidade seJ/unid.</th>
            <th style="border: 1px solid #ccc; padding: 8px;">Ref Emergia Solar</th>
        </tr>
    `;
    table.appendChild(thead);

    // Corpo da tabela
    const tbody = document.createElement("tbody");

    for (const key in calcResults) {
        const data = calcResults[key];
        // Extrai os valores usando regex
        const calcMatch = data.result.match(/<strong>Calc:<\/strong>\s*([^<]+)/);
        const refMatch = data.result.match(/<strong>Ref:<\/strong>\s*([^<]+)/);
        const razaoMatch = data.result.match(/<strong>Razão:<\/strong>\s*([^<]+)/);

        const calcValue = calcMatch ? calcMatch[1].trim() : "";
        const refValue = refMatch ? refMatch[1].trim() : "";
        const razaoValue = razaoMatch ? razaoMatch[1].trim() : "";

        const tr = document.createElement("tr");
        tr.style.backgroundColor = "#f4f4f4";
        tr.innerHTML = `
            <td style="border: 1px solid #ccc; padding: 8px;">${data.label}</td>
            <td style="border: 1px solid #ccc; padding: 8px;">${calcValue}</td>
            <td style="border: 1px solid #ccc; padding: 8px;">${razaoValue}</td>
            <td style="border: 1px solid #ccc; padding: 8px;">${refValue}</td>
        `;
        tbody.appendChild(tr);
    }
    table.appendChild(tbody);

    // Insere a tabela na div container
    container.appendChild(table);
});