document.addEventListener("DOMContentLoaded", () => {

    async function fetchLastResultsFromServer() {
        try {
            const response = await fetch("http://localhost:8081/getLastResults");
            if (!response.ok) throw new Error("Erro ao buscar do servidor");
            return await response.json();
        } catch (e) {
            document.body.innerHTML = "<h2>Não foi possível carregar os resultados.</h2>";
            return null;
        }
    }

    async function fetchCalculoTotal() {
        try {
            const response = await fetch("http://localhost:8081/getCalculoTotal");
            if (!response.ok) throw new Error("Erro ao buscar os cálculos totais do servidor");
            return await response.json();
        } catch (e) {
            document.body.innerHTML = "<h2>Não foi possível carregar os cálculos totais.</h2>";
            return null;
        }
    }

    function adaptBackendResult(relatorio) {
        return {
            aguaUsada: relatorio.calcAguaUsada !== null ? {
                label: "Água Usada",
                calc: relatorio.calcAguaUsada,
                ref: relatorio.refAguaUsada,
                razao: relatorio.razaoAguaUsada,
                result: `<strong>Calc:</strong> ${Number(relatorio.calcAguaUsada).toExponential(2).toUpperCase()}<br>
                         <strong>Ref:</strong> ${Number(relatorio.refAguaUsada).toExponential(2).toUpperCase()}<br>
                         <strong>Razão:</strong> ${Number(relatorio.razaoAguaUsada).toExponential(2).toUpperCase()}`
            } : undefined,
            combustivel: relatorio.calcCombustivelUsado !== null ? {
                label: "Combustível",
                calc: relatorio.calcCombustivelUsado,
                ref: relatorio.refCombustivelUsado,
                razao: relatorio.razaoCombustivelUsado,
                result: `<strong>Calc:</strong> ${Number(relatorio.calcCombustivelUsado).toExponential(2).toUpperCase()}<br>
                         <strong>Ref:</strong> ${Number(relatorio.refCombustivelUsado).toExponential(2).toUpperCase()}<br>
                         <strong>Razão:</strong> ${Number(relatorio.razaoCombustivelUsado).toExponential(2).toUpperCase()}`
            } : undefined,
            solo: relatorio.calcCuidadoSolo !== null ? {
                label: "Cuidado Solo",
                calc: relatorio.calcCuidadoSolo,
                ref: relatorio.refCuidadoSolo,
                razao: relatorio.razaoCuidadoSolo,
                result: `<strong>Calc:</strong> ${Number(relatorio.calcCuidadoSolo).toExponential(2).toUpperCase()}<br>
                         <strong>Ref:</strong> ${Number(relatorio.refCuidadoSolo).toExponential(2).toUpperCase()}<br>
                         <strong>Razão:</strong> ${Number(relatorio.razaoCuidadoSolo).toExponential(2).toUpperCase()}`
            } : undefined,
            eletrica: relatorio.calcEletricidade !== null ? {
                label: "Eletricidade",
                calc: relatorio.calcEletricidade,
                ref: relatorio.refEletricidade,
                razao: relatorio.razaoEletricidade,
                result: `<strong>Calc:</strong> ${Number(relatorio.calcEletricidade).toExponential(2).toUpperCase()}<br>
                         <strong>Ref:</strong> ${Number(relatorio.refEletricidade).toExponential(2).toUpperCase()}<br>
                         <strong>Razão:</strong> ${Number(relatorio.razaoEletricidade).toExponential(2).toUpperCase()}`
            } : undefined,
            gado: relatorio.calcGado !== null ? {
                label: "Gado",
                calc: relatorio.calcGado,
                ref: relatorio.refGado,
                razao: relatorio.razaoGado,
                result: `<strong>Calc:</strong> ${Number(relatorio.calcGado).toExponential(2).toUpperCase()}<br>
                         <strong>Ref:</strong> ${Number(relatorio.refGado).toExponential(2).toUpperCase()}<br>
                         <strong>Razão:</strong> ${Number(relatorio.razaoGado).toExponential(2).toUpperCase()}`
            } : undefined,
            maquinario: relatorio.calcMaquinarios !== null ? {
                label: "Maquinário",
                calc: relatorio.calcMaquinarios,
                ref: relatorio.refMaquinarios,
                razao: relatorio.razaoMaquinarios,
                result: `<strong>Calc:</strong> ${Number(relatorio.calcMaquinarios).toExponential(2).toUpperCase()}<br>
                         <strong>Ref:</strong> ${Number(relatorio.refMaquinarios).toExponential(2).toUpperCase()}<br>
                         <strong>Razão:</strong> ${Number(relatorio.razaoMaquinarios).toExponential(2).toUpperCase()}`
            } : undefined,
            maoObra: relatorio.calcMaoObra !== null ? {
                label: "Mão de Obra",
                calc: relatorio.calcMaoObra,
                ref: relatorio.refMaoObra,
                razao: relatorio.razaoMaoObra,
                result: `<strong>Calc:</strong> ${Number(relatorio.calcMaoObra).toExponential(2).toUpperCase()}<br>
                         <strong>Ref:</strong> ${Number(relatorio.refMaoObra).toExponential(2).toUpperCase()}<br>
                         <strong>Razão:</strong> ${Number(relatorio.razaoMaoObra).toExponential(2).toUpperCase()}`
            } : undefined,
            perdaSolo: relatorio.calcPerdaSolo !== null ? {
                label: "Perda Solo",
                calc: relatorio.calcPerdaSolo,
                ref: relatorio.refPerdaSolo,
                razao: relatorio.razaoPerdaSolo,
                result: `<strong>Calc:</strong> ${Number(relatorio.calcPerdaSolo).toExponential(2).toUpperCase()}<br>
                         <strong>Ref:</strong> ${Number(relatorio.refPerdaSolo).toExponential(2).toUpperCase()}<br>
                         <strong>Razão:</strong> ${Number(relatorio.razaoPerdaSolo).toExponential(2).toUpperCase()}`
            } : undefined,
            potencialQuimico: relatorio.calcPotencialQuimico !== null ? {
                label: "Potencial Químico",
                calc: relatorio.calcPotencialQuimico,
                ref: relatorio.refPotencialQuimico,
                razao: relatorio.razaoPotencialQuimico,
                result: `<strong>Calc:</strong> ${Number(relatorio.calcPotencialQuimico).toExponential(2).toUpperCase()}<br>
                         <strong>Ref:</strong> ${Number(relatorio.refPotencialQuimico).toExponential(2).toUpperCase()}<br>
                         <strong>Razão:</strong> ${Number(relatorio.razaoPotencialQuimico).toExponential(2).toUpperCase()}`
            } : undefined,
            racao: relatorio.calcRacao !== null ? {
                label: "Ração",
                calc: relatorio.calcRacao,
                ref: relatorio.refRacao,
                razao: relatorio.razaoRacao,
                result: `<strong>Calc:</strong> ${Number(relatorio.calcRacao).toExponential(2).toUpperCase()}<br>
                         <strong>Ref:</strong> ${Number(relatorio.refRacao).toExponential(2).toUpperCase()}<br>
                         <strong>Razão:</strong> ${Number(relatorio.razaoRacao).toExponential(2).toUpperCase()}`
            } : undefined,
            producaoLeite: relatorio.calcProducaoLeite !== null ? {
                label: "Produção de Leite",
                calc: relatorio.calcProducaoLeite,
                ref: relatorio.refProducaoLeite,
                razao: relatorio.razaoProducaoLeite,
                result: `<strong>Calc:</strong> ${Number(relatorio.calcProducaoLeite).toExponential(2).toUpperCase()}<br>
                         <strong>Ref:</strong> ${Number(relatorio.refProducaoLeite).toExponential(2).toUpperCase()}<br>
                         <strong>Razão:</strong> ${Number(relatorio.razaoProducaoLeite).toExponential(2).toUpperCase()}`
            } : undefined,
            consumoFazenda: relatorio.calcBens !== null ? {
                label: "Consumo/Manutenção",
                calc: relatorio.calcBens,
                ref: relatorio.refBens,
                razao: relatorio.razaoBens,
                result: `<strong>Calc:</strong> ${Number(relatorio.calcBens).toExponential(2).toUpperCase()}<br>
                         <strong>Ref:</strong> ${Number(relatorio.refBens).toExponential(2).toUpperCase()}<br>
                         <strong>Razão:</strong> ${Number(relatorio.razaoBens).toExponential(2).toUpperCase()}`
            } : undefined
        };
    }

    (async () => {
        const relatorio = await fetchLastResultsFromServer();
        const calculoTotal = await fetchCalculoTotal();
        if (!relatorio || !calculoTotal) return;

        // Obtém ou cria o contêiner da tabela
        let container = document.getElementById("tableContainer");
        if (!container) {
            container = document.createElement("div");
            container.id = "tableContainer";
            container.className = "table-wrapper";
            document.body.appendChild(container);
        }

        // Cria o título com o nome da fazenda
        const title = document.createElement("h2");
        title.textContent = `Nome da fazenda: ${relatorio.nomeDaFazenda}`;
        title.style.textAlign = "center";
        title.style.marginBottom = "20px";
        container.insertBefore(title, container.firstChild);

        // Atualiza o gráfico usando os percentuais calculados no back-end
        const data = {
            labels: ["Renovável", "Não Renovável", "Bens", "Operações de Produção", "Produção"],
            datasets: [{
                data: [
                    Number(calculoTotal.porcentagemRenovavel).toFixed(2),
                    Number(calculoTotal.porcentagemNaoRenovavel).toFixed(2),
                    Number(calculoTotal.porcentagemBens).toFixed(2),
                    Number(calculoTotal.porcentagemOperacoesProducao).toFixed(2),
                    Number(calculoTotal.porcentagemProducao).toFixed(2)
                ],
                backgroundColor: ["#4caf50", "#f44336", "#ff9800", "#2196f3", "#9c27b0"],
                hoverOffset: 4
            }]
        };

        const config = {
            type: "pie",
            data: data,
            options: {
                plugins: {
                    legend: {
                        position: "bottom"
                    }
                }
            }
        };

        // Cria o canvas e renderiza o gráfico
        const chartContainer = document.createElement("div");
        chartContainer.style.width = "30%";
        chartContainer.style.margin = "0 auto 20px auto";
        const canvas = document.createElement("canvas");
        canvas.id = "pieChart";
        canvas.style.maxWidth = "100%";
        canvas.style.height = "auto";
        chartContainer.appendChild(canvas);
        container.insertBefore(chartContainer, title.nextSibling);
        new Chart(canvas, config);

        const adapted = adaptBackendResult(relatorio);
        Object.keys(adapted).forEach(k => adapted[k] === undefined && delete adapted[k]);
        const calcResults = adapted;

        // Cria a tabela detalhada
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

        // Agrupamento por categorias e subcategorias (os itens detalhados permanecem e os subtotais usam os percentuais do back-end)
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

        // Organiza os itens em categorias conforme a chave
        Object.keys(calcResults).forEach(key => {
            const data = calcResults[key];
            if (!data || typeof data.result !== "string") return;

            // Para os itens detalhados, deixamos a célula de percentual em branco
            if (key === "potencialQuimico") {
                categories["Contribuição Ambiental"]["Energia Renovável"].push({
                    label: data.label,
                    calc: data.result.match(/<strong>Calc:<\/strong>\s*([^<]+)/)?.[1].trim() || "",
                    ref: data.result.match(/<strong>Ref:<\/strong>\s*([^<]+)/)?.[1].trim() || "",
                    razao: data.result.match(/<strong>Razão:<\/strong>\s*([^<]+)/)?.[1].trim() || ""
                });
            }
            if (key === "aguaUsada" || key === "perdaSolo") {
                categories["Contribuição Ambiental"]["Energia Não Renovável"].push({
                    label: data.label,
                    calc: data.result.match(/<strong>Calc:<\/strong>\s*([^<]+)/)?.[1].trim() || "",
                    ref: data.result.match(/<strong>Ref:<\/strong>\s*([^<]+)/)?.[1].trim() || "",
                    razao: data.result.match(/<strong>Razão:<\/strong>\s*([^<]+)/)?.[1].trim() || ""
                });
            }
            if (key === "racao" || key === "maquinario" || key === "maoObra" ||
                key === "gado" || key === "eletrica" || key === "combustivel" || key === "solo") {
                categories["Contribuição Humana"]["Operações de Produção"].push({
                    label: data.label,
                    calc: data.result.match(/<strong>Calc:<\/strong>\s*([^<]+)/)?.[1].trim() || "",
                    ref: data.result.match(/<strong>Ref:<\/strong>\s*([^<]+)/)?.[1].trim() || "",
                    razao: data.result.match(/<strong>Razão:<\/strong>\s*([^<]+)/)?.[1].trim() || ""
                });
            }
            if (key === "consumoFazenda") {
                categories["Contribuição Humana"]["Bens"].push({
                    label: data.label,
                    calc: data.result.match(/<strong>Calc:<\/strong>\s*([^<]+)/)?.[1].trim() || "",
                    ref: data.result.match(/<strong>Ref:<\/strong>\s*([^<]+)/)?.[1].trim() || "",
                    razao: data.result.match(/<strong>Razão:<\/strong>\s*([^<]+)/)?.[1].trim() || ""
                });
            }
            if (key === "producaoLeite") {
                categories["Produção"].push({
                    label: data.label,
                    calc: data.result.match(/<strong>Calc:<\/strong>\s*([^<]+)/)?.[1].trim() || "",
                    ref: data.result.match(/<strong>Ref:<\/strong>\s*([^<]+)/)?.[1].trim() || "",
                    razao: data.result.match(/<strong>Razão:<\/strong>\s*([^<]+)/)?.[1].trim() || ""
                });
            }
        });

        // Função auxiliar para obter o percentual do back-end com base na categoria ou subcategoria
        function getBackendPercentage(category, subcategory = null) {
            if(category === "Contribuição Ambiental"){
                if(subcategory === "Energia Renovável"){
                    return Number(calculoTotal.porcentagemRenovavel).toFixed(2) + "%";
                }
                if(subcategory === "Energia Não Renovável"){
                    return Number(calculoTotal.porcentagemNaoRenovavel).toFixed(2) + "%";
                }
                // Para o subtotal ambiental
                return Number(calculoTotal.porcentagemAmbiental).toFixed(2) + "%";
            }
            if(category === "Contribuição Humana"){
                if(subcategory === "Bens"){
                    return Number(calculoTotal.porcentagemBens).toFixed(2) + "%";
                }
                if(subcategory === "Operações de Produção"){
                    return Number(calculoTotal.porcentagemOperacoesProducao).toFixed(2) + "%";
                }
            }
            if(category === "Produção"){
                return Number(calculoTotal.porcentagemProducao).toFixed(2) + "%";
            }
            return "";
        }

        // Cria as linhas da tabela com os itens e os subtotais usando os percentuais do back-end
        Object.entries(categories).forEach(([category, subcategories]) => {
            // Linha de categoria
            const categoryRow = document.createElement("tr");
            categoryRow.style.backgroundColor = "#d4edda";
            categoryRow.innerHTML = `
                <td colspan="7" style="border: 1px solid #ccc; padding: 8px; font-weight: bold; text-align: left;">
                    ${category}
                </td>
            `;
            tbody.appendChild(categoryRow);
            
            if (typeof subcategories === "object" && !Array.isArray(subcategories)) {
                Object.entries(subcategories).forEach(([subcategory, rows]) => {
                    // Linha de subcategoria
                    const subcategoryRow = document.createElement("tr");
                    subcategoryRow.style.backgroundColor = "#f0f8ff";
                    subcategoryRow.innerHTML = `
                        <td colspan="7" style="border: 1px solid #ccc; padding: 8px; font-weight: bold; text-align: left;">
                            ${subcategory}
                        </td>
                    `;
                    tbody.appendChild(subcategoryRow);

                    rows.forEach(row => {
                        // Linha detalhada – deixamos a coluna de percentual vazia
                        const tr = document.createElement("tr");
                        tr.style.backgroundColor = "#f4f4f4";
                        tr.innerHTML = `
                            <td style="border: 1px solid #ccc; padding: 8px;"></td>
                            <td style="border: 1px solid #ccc; padding: 8px;"></td>
                            <td style="border: 1px solid #ccc; padding: 8px;">${row.label}</td>
                            <td style="border: 1px solid #ccc; padding: 8px;">${row.calc}</td>
                            <td style="border: 1px solid #ccc; padding: 8px;">${row.razao}</td>
                            <td style="border: 1px solid #ccc; padding: 8px;">${row.ref}</td>
                            <td style="border: 1px solid #ccc; padding: 8px;"></td>
                        `;
                        tbody.appendChild(tr);
                    });

                    // Linha de subtotal para a subcategoria (usa o percentual do back-end)
                    if (rows.length > 0) {
                        const subTotalRef = rows.reduce((acc, curr) => acc + Number(curr.ref), 0);
                        const subTotalRow = document.createElement("tr");
                        subTotalRow.style.backgroundColor = "#e2e3e5";
                        subTotalRow.innerHTML = `
                            <td style="border: 1px solid #ccc; padding: 8px;"></td>
                            <td style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">Subtotal ${subcategory}</td>
                            <td style="border: 1px solid #ccc; padding: 8px;"></td>
                            <td style="border: 1px solid #ccc; padding: 8px;"></td>
                            <td style="border: 1px solid #ccc; padding: 8px;"></td>
                            <td style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">${subTotalRef.toExponential(2).toUpperCase()}</td>
                            <td style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">
                                ${getBackendPercentage(category, subcategory)}
                            </td>
                        `;
                        tbody.appendChild(subTotalRow);
                    }
                });
                // Linha de subtotal por categoria (para "Contribuição Ambiental" usamos o percentual consolidado)
                if (category === "Contribuição Ambiental") {
                    const ambientalRow = document.createElement("tr");
                    ambientalRow.style.backgroundColor = "#ccc";
                    ambientalRow.innerHTML = `
                        <td colspan="5" style="border: 1px solid #ccc; padding: 8px; font-weight: bold; text-align: right;">
                            Subtotal Ambiental
                        </td>
                        <td style="border: 1px solid #ccc; padding: 8px; font-weight: bold;"></td>
                        <td style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">
                            ${getBackendPercentage(category)}
                        </td>
                    `;
                    tbody.appendChild(ambientalRow);
                }
            } else {
                // Se a categoria não apresentar subcategorias (array simples)
                subcategories.forEach(row => {
                    const tr = document.createElement("tr");
                    tr.style.backgroundColor = "#f4f4f4";
                    tr.innerHTML = `
                        <td style="border: 1px solid #ccc; padding: 8px;"></td>
                        <td style="border: 1px solid #ccc; padding: 8px;"></td>
                        <td style="border: 1px solid #ccc; padding: 8px;">${row.label}</td>
                        <td style="border: 1px solid #ccc; padding: 8px;">${row.calc}</td>
                        <td style="border: 1px solid #ccc; padding: 8px;">${row.razao}</td>
                        <td style="border: 1px solid #ccc; padding: 8px;">${row.ref}</td>
                        <td style="border: 1px solid #ccc; padding: 8px;"></td>
                    `;
                    tbody.appendChild(tr);
                });
                if (subcategories.length > 0) {
                    const catSubtotalRef = subcategories.reduce((acc, curr) => acc + Number(curr.ref), 0);
                    const catSubtotalRow = document.createElement("tr");
                    catSubtotalRow.style.backgroundColor = "#e2e3e5";
                    catSubtotalRow.innerHTML = `
                        <td style="border: 1px solid #ccc; padding: 8px;"></td>
                        <td style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">Subtotal</td>
                        <td style="border: 1px solid #ccc; padding: 8px;"></td>
                        <td style="border: 1px solid #ccc; padding: 8px;"></td>
                        <td style="border: 1px solid #ccc; padding: 8px;"></td>
                        <td style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">${catSubtotalRef.toExponential(2).toUpperCase()}</td>
                        <td style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">${getBackendPercentage(category)}</td>
                    `;
                    tbody.appendChild(catSubtotalRow);
                }
            }
        });

        // Linha de total geral (100%)
        const totalRow = document.createElement("tr");
        totalRow.style.backgroundColor = "#d4edda";
        totalRow.innerHTML = `
            <td colspan="5" style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">
                Total Geral
            </td>
            <td style="border: 1px solid #ccc; padding: 8px;"></td>
            <td style="border: 1px solid #ccc; padding: 8px;">100%</td>
        `;
        tbody.appendChild(totalRow);

        table.appendChild(tbody);
        container.appendChild(table);
    })();
});