document.addEventListener("DOMContentLoaded", () => {
    const resultsString = localStorage.getItem("calcResults");
    if (!resultsString || resultsString === "undefined") {
        document.body.innerHTML = "<h2>Nenhum resultado encontrado.</h2>";
        return;
    }
    const calcResults = JSON.parse(resultsString);

    let container = document.getElementById("tableContainer");
    if (!container) {
        container = document.createElement("div");
        container.id = "tableContainer";
        container.className = "table-wrapper";
        document.body.appendChild(container);
    }

    const table = document.createElement("table");
    table.style.borderCollapse = "collapse";
    table.style.width = "100%";
    table.style.textAlign = "center";
    table.style.fontFamily = "Arial, sans-serif";

    const thead = document.createElement("thead");
    thead.innerHTML = `
        <tr style="background-color: #1faa06; color: white;">
            <th style="border: 1px solid #ccc; padding: 8px;">Item</th>
            <th style="border: 1px solid #ccc; padding: 8px;">Unid./ano</th>
            <th style="border: 1px solid #ccc; padding: 8px;">Transformidade seJ/unid.</th>
            <th style="border: 1px solid #ccc; padding: 8px;">Ref Emergia Solar</th>
            <th style="border: 1px solid #ccc; padding: 8px;">% Total de Energia</th>
        </tr>
    `;
    table.appendChild(thead);

    const tbody = document.createElement("tbody");

    let totalRefEmergiaSolar = 0;
    const rows = [];

    // Calcula o total de Ref Emergia Solar
    for (const key in calcResults) {
        const data = calcResults[key];
        const refMatch = data.result.match(/<strong>Ref:<\/strong>\s*([^<]+)/);
        const refValue = refMatch ? parseFloat(refMatch[1].trim()) : 0;
        totalRefEmergiaSolar += refValue;

        rows.push({
            label: data.label,
            calc: data.result.match(/<strong>Calc:<\/strong>\s*([^<]+)/)?.[1].trim() || "",
            ref: refValue,
            razao: data.result.match(/<strong>Razão:<\/strong>\s*([^<]+)/)?.[1].trim() || ""
        });
    }

    // Adiciona as linhas com os cálculos de porcentagem
    rows.forEach(row => {
        const percentage = ((row.ref / totalRefEmergiaSolar) * 100).toFixed(2);
        const tr = document.createElement("tr");
        tr.style.backgroundColor = "#f4f4f4";
        tr.innerHTML = `
            <td style="border: 1px solid #ccc; padding: 8px;">${row.label}</td>
            <td style="border: 1px solid #ccc; padding: 8px;">${row.calc}</td>
            <td style="border: 1px solid #ccc; padding: 8px;">${row.razao}</td>
            <td style="border: 1px solid #ccc; padding: 8px;">${row.ref.toExponential(2).toUpperCase()}</td>
            <td style="border: 1px solid #ccc; padding: 8px;">${percentage}%</td>
        `;
        tbody.appendChild(tr);
    });

    // Adiciona o subtotal e total
    const totalRow = document.createElement("tr");
    totalRow.style.backgroundColor = "#d4edda";
    totalRow.innerHTML = `
        <td style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">Total</td>
        <td style="border: 1px solid #ccc; padding: 8px;"></td>
        <td style="border: 1px solid #ccc; padding: 8px;"></td>
        <td style="border: 1px solid #ccc; padding: 8px;">${totalRefEmergiaSolar.toExponential(2).toUpperCase()}</td>
        <td style="border: 1px solid #ccc; padding: 8px;">100%</td>
    `;
    tbody.appendChild(totalRow);

    table.appendChild(tbody);
    container.appendChild(table);
});