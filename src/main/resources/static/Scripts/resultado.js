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
            <th style="border: 1px solid #ccc; padding: 8px;">Categoria</th>
            <th style="border: 1px solid #ccc; padding: 8px;">Subcategoria</th>
            <th style="border: 1px solid #ccc; padding: 8px;">Item</th>
            <th style="border: 1px solid #ccc; padding: 8px;">Unid./ano</th>
            <th style="border: 1px solid #ccc; padding: 8px;">Transformidade seJ/unid.</th>
            <th style="border: 1px solid #ccc; padding: 8px;">Ref Emergia Solar</th>
            <th style="border: 1px solid #ccc; padding: 8px;">% Total de Energia</th>
        </tr>
    `;
    table.appendChild(thead);

    const tbody = document.createElement("tbody");

    // Agrupamento por categorias e subcategorias
    const categories = {
        "Contribuição Ambiental": {
            "Energia Renovável": [],
            "Energia Não Renovável": []
        },
        "Contribuição Humana": {
            "Bens": [],
            "Operações de Produção": []
        },
        "Produção": []
    };

    let totalRefEmergiaSolar = 0;

    // Organiza os resultados em categorias e subcategorias
    for (const key in calcResults) {
        const data = calcResults[key];
        const refMatch = data.result.match(/<strong>Ref:<\/strong>\s*([^<]+)/);
        const refValue = refMatch ? parseFloat(refMatch[1].trim()) : 0;
        totalRefEmergiaSolar += refValue;

        // Energia Renovável
        if (key === "potencialQuimico") {
            categories["Contribuição Ambiental"]["Energia Renovável"].push({
                label: data.label,
                calc: data.result.match(/<strong>Calc:<\/strong>\s*([^<]+)/)?.[1].trim() || "",
                ref: refValue,
                razao: data.result.match(/<strong>Razão:<\/strong>\s*([^<]+)/)?.[1].trim() || ""
            });
        }

        // Energia Não Renovável
        if (key === "aguaUsada" || key === "perdaSolo") {
            categories["Contribuição Ambiental"]["Energia Não Renovável"].push({
                label: data.label,
                calc: data.result.match(/<strong>Calc:<\/strong>\s*([^<]+)/)?.[1].trim() || "",
                ref: refValue,
                razao: data.result.match(/<strong>Razão:<\/strong>\s*([^<]+)/)?.[1].trim() || ""
            });
        }

        // Operações de Produção
        if (
            key === "racao" ||
            key === "maquinario" ||
            key === "maoObra" ||
            key === "gado" ||
            key === "eletrica" ||
            key === "combustivel" ||
            key === "solo"
        ) {
            categories["Contribuição Humana"]["Operações de Produção"].push({
                label: data.label,
                calc: data.result.match(/<strong>Calc:<\/strong>\s*([^<]+)/)?.[1].trim() || "",
                ref: refValue,
                razao: data.result.match(/<strong>Razão:<\/strong>\s*([^<]+)/)?.[1].trim() || ""
            });
        }

        // Bens
        if (key === "consumoFazenda") {
            categories["Contribuição Humana"]["Bens"].push({
                label: data.label,
                calc: data.result.match(/<strong>Calc:<\/strong>\s*([^<]+)/)?.[1].trim() || "",
                ref: refValue,
                razao: data.result.match(/<strong>Razão:<\/strong>\s*([^<]+)/)?.[1].trim() || ""
            });
        }

        // Produção
        if (key === "producaoLeite") {
            categories["Produção"].push({
                label: data.label,
                calc: data.result.match(/<strong>Calc:<\/strong>\s*([^<]+)/)?.[1].trim() || "",
                ref: refValue,
                razao: data.result.match(/<strong>Razão:<\/strong>\s*([^<]+)/)?.[1].trim() || ""
            });
        }
    }

    // Adiciona as linhas agrupadas por categoria e subcategoria
    for (const [category, subcategories] of Object.entries(categories)) {
        const categoryRow = document.createElement("tr");
        categoryRow.style.backgroundColor = "#d4edda";
        categoryRow.innerHTML = `
            <td colspan="7" style="border: 1px solid #ccc; padding: 8px; font-weight: bold; text-align: left;">${category}</td>
        `;
        tbody.appendChild(categoryRow);

        if (typeof subcategories === "object" && !Array.isArray(subcategories)) {
            // Para cada subcategoria
            for (const [subcategory, rows] of Object.entries(subcategories)) {
                const subcategoryRow = document.createElement("tr");
                subcategoryRow.style.backgroundColor = "#f0f8ff";
                subcategoryRow.innerHTML = `
                    <td colspan="7" style="border: 1px solid #ccc; padding: 8px; font-weight: bold; text-align: left;">${subcategory}</td>
                `;
                tbody.appendChild(subcategoryRow);

                // Adiciona cada linha (item)
                rows.forEach(row => {
                    const percentage = ((row.ref / totalRefEmergiaSolar) * 100).toFixed(2);
                    const tr = document.createElement("tr");
                    tr.style.backgroundColor = "#f4f4f4";
                    tr.innerHTML = `
                        <td style="border: 1px solid #ccc; padding: 8px;"></td>
                        <td style="border: 1px solid #ccc; padding: 8px;"></td>
                        <td style="border: 1px solid #ccc; padding: 8px;">${row.label}</td>
                        <td style="border: 1px solid #ccc; padding: 8px;">${row.calc}</td>
                        <td style="border: 1px solid #ccc; padding: 8px;">${row.razao}</td>
                        <td style="border: 1px solid #ccc; padding: 8px;">${row.ref.toExponential(2).toUpperCase()}</td>
                        <td style="border: 1px solid #ccc; padding: 8px;">${percentage}%</td>
                    `;
                    tbody.appendChild(tr);
                });

                // Adiciona a linha de subtotal para a subcategoria, se houver itens
                if (rows.length > 0) {
                    const subTotalRef = rows.reduce((acc, curr) => acc + curr.ref, 0);
                    const subPercentage = ((subTotalRef / totalRefEmergiaSolar) * 100).toFixed(2);
                    const subTotalRow = document.createElement("tr");
                    subTotalRow.style.backgroundColor = "#e2e3e5";
                    subTotalRow.innerHTML = `
                        <td style="border: 1px solid #ccc; padding: 8px;"></td>
                        <td style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">Subtotal ${subcategory}</td>
                        <td style="border: 1px solid #ccc; padding: 8px;"></td>
                        <td style="border: 1px solid #ccc; padding: 8px;"></td>
                        <td style="border: 1px solid #ccc; padding: 8px;"></td>
                        <td style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">${subTotalRef.toExponential(2).toUpperCase()}</td>
                        <td style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">${subPercentage}%</td>
                    `;
                    tbody.appendChild(subTotalRow);
                }
            }

            // Se a categoria for "Contribuição Ambiental", somamos os subtotais de "Energia Renovável" e "Energia Não Renovável"
            if (category === "Contribuição Ambiental") {
                let totalAmbientalRef = 0;
                // Somente se existirem itens em cada subcategoria
                const renovavel = subcategories["Energia Renovável"];
                const naoRenovavel = subcategories["Energia Não Renovável"];
                if (Array.isArray(renovavel)) {
                    totalAmbientalRef += renovavel.reduce((acc, curr) => acc + curr.ref, 0);
                }
                if (Array.isArray(naoRenovavel)) {
                    totalAmbientalRef += naoRenovavel.reduce((acc, curr) => acc + curr.ref, 0);
                }
                const totalAmbientalPercentage = ((totalAmbientalRef / totalRefEmergiaSolar) * 100).toFixed(2);
                const ambientalSubTotalRow = document.createElement("tr");
                ambientalSubTotalRow.style.backgroundColor = "#ccc";
                ambientalSubTotalRow.innerHTML = `
                    <td colspan="5" style="border: 1px solid #ccc; padding: 8px; font-weight: bold; text-align: right;">Subtotal Ambiental</td>
                    <td style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">${totalAmbientalRef.toExponential(2).toUpperCase()}</td>
                    <td style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">${totalAmbientalPercentage}%</td>
                `;
                tbody.appendChild(ambientalSubTotalRow);
            }
        } else {
            // Caso a categoria não esteja dividida em subcategorias (array simples)
            subcategories.forEach(row => {
                const percentage = ((row.ref / totalRefEmergiaSolar) * 100).toFixed(2);
                const tr = document.createElement("tr");
                tr.style.backgroundColor = "#f4f4f4";
                tr.innerHTML = `
                    <td style="border: 1px solid #ccc; padding: 8px;"></td>
                    <td style="border: 1px solid #ccc; padding: 8px;"></td>
                    <td style="border: 1px solid #ccc; padding: 8px;">${row.label}</td>
                    <td style="border: 1px solid #ccc; padding: 8px;">${row.calc}</td>
                    <td style="border: 1px solid #ccc; padding: 8px;">${row.razao}</td>
                    <td style="border: 1px solid #ccc; padding: 8px;">${row.ref.toExponential(2).toUpperCase()}</td>
                    <td style="border: 1px solid #ccc; padding: 8px;">${percentage}%</td>
                `;
                tbody.appendChild(tr);
            });
            if (subcategories.length > 0) {
                const catSubtotalRef = subcategories.reduce((acc, curr) => acc + curr.ref, 0);
                const catSubPercentage = ((catSubtotalRef / totalRefEmergiaSolar) * 100).toFixed(2);
                const catSubtotalRow = document.createElement("tr");
                catSubtotalRow.style.backgroundColor = "#e2e3e5";
                catSubtotalRow.innerHTML = `
                    <td style="border: 1px solid #ccc; padding: 8px;"></td>
                    <td style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">Subtotal</td>
                    <td style="border: 1px solid #ccc; padding: 8px;"></td>
                    <td style="border: 1px solid #ccc; padding: 8px;"></td>
                    <td style="border: 1px solid #ccc; padding: 8px;"></td>
                    <td style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">${catSubtotalRef.toExponential(2).toUpperCase()}</td>
                    <td style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">${catSubPercentage}%</td>
                `;
                tbody.appendChild(catSubtotalRow);
            }
        }
    }

    // Adiciona o total geral
    const totalRow = document.createElement("tr");
    totalRow.style.backgroundColor = "#d4edda";
    totalRow.innerHTML = `
        <td colspan="5" style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">Total Geral</td>
        <td style="border: 1px solid #ccc; padding: 8px;">${totalRefEmergiaSolar.toExponential(2).toUpperCase()}</td>
        <td style="border: 1px solid #ccc; padding: 8px;">100%</td>
    `;
    tbody.appendChild(totalRow);

    table.appendChild(tbody);
    container.appendChild(table);
});