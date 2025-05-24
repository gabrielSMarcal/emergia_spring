document.addEventListener("DOMContentLoaded", function() {
    (async function() {
        const container = document.getElementById("relatorio-container");
        if (!container) return;

        try {
            // 1. Buscar dados do relatório
            const response = await fetch("http://localhost:8081/getLastResults");
            if (!response.ok) throw new Error("Erro ao buscar dados do relatório");
            const data = await response.json();

            const title = document.createElement("h2");
            title.textContent = `Fazenda: ${data.nomeDaFazenda || ""}`;
            title.style.textAlign = "center";
            title.style.marginBottom = "20px";
            container.appendChild(title);

            // 2. Buscar totais do backend
            const totalResponse = await fetch("http://localhost:8081/getCalculoTotal");
            if (!totalResponse.ok) throw new Error("Erro ao buscar totais");
            const totais = await totalResponse.json();

            // Função para obter a porcentagem do backend
            function getBackendPercentage(category) {
                if (category === "ambiental") {
                    return totais.percentualAmbiental ? `${totais.percentualAmbiental.toFixed(2)}%` : "";
                }
                if (category === "humana") {
                    return totais.percentualHumana ? `${totais.percentualHumana.toFixed(2)}%` : "";
                }
                return "";
            }

            // Criar tabela de relatório
            const table = document.createElement("table");
            table.style.width = "100%";
            table.style.borderCollapse = "collapse";
            table.style.marginBottom = "20px";
            
            const thead = document.createElement("thead");
            thead.innerHTML = `
                <tr style="background-color: #343a40; color: white;">
                    <th style="border: 1px solid #ccc; padding: 8px;">Categoria</th>
                    <th style="border: 1px solid #ccc; padding: 8px;">Subcategoria</th>
                    <th style="border: 1px solid #ccc; padding: 8px;">Item</th>
                    <th style="border: 1px solid #ccc; padding: 8px;">Calculado</th>
                    <th style="border: 1px solid #ccc; padding: 8px;">Razão</th>
                    <th style="border: 1px solid #ccc; padding: 8px;">Referência</th>
                    <th style="border: 1px solid #ccc; padding: 8px;">%</th>
                </tr>
            `;
            table.appendChild(thead);
            
            const tbody = document.createElement("tbody");
            
            // Dados para a tabela
            const tableData = [
                {
                    category: "ambiental",
                    label: "Contribuição Ambiental",
                    subcategories: [
                        {
                            label: "Energia Renovável",
                            items: [
                                {
                                    label: "Potencial Químico",
                                    calc: data.calcPotencialQuimico,
                                    razao: data.razaoPotencialQuimico,
                                    ref: data.refPotencialQuimico
                                }
                            ]
                        },
                        {
                            label: "Energia Não Renovável",
                            items: [
                                {
                                    label: "Água Usada",
                                    calc: data.calcAguaUsada,
                                    razao: data.razaoAguaUsada,
                                    ref: data.refAguaUsada
                                },
                                {
                                    label: "Perda de Solo",
                                    calc: data.calcPerdaSolo,
                                    razao: data.razaoPerdaSolo,
                                    ref: data.refPerdaSolo
                                }
                            ]
                        }
                    ]
                },
                {
                    category: "humana",
                    label: "Contribuição Humana",
                    subcategories: [
                        {
                            label: "Bens",
                            items: [
                                {
                                    label: "Valor Consumo/Manutenção",
                                    calc: data.calcBens,
                                    razao: data.razaoBens,
                                    ref: data.refBens
                                }
                            ]
                        },
                        {
                            label: "Operações de Produção",
                            items: [
                                {
                                    label: "Combustível Usado",
                                    calc: data.calcCombustivelUsado,
                                    razao: data.razaoCombustivelUsado,
                                    ref: data.refCombustivelUsado
                                },
                                {
                                    label: "Eletricidade",
                                    calc: data.calcEletricidade,
                                    razao: data.razaoEletricidade,
                                    ref: data.refEletricidade
                                },
                                {
                                    label: "Gado",
                                    calc: data.calcGado,
                                    razao: data.razaoGado,
                                    ref: data.refGado
                                },
                                {
                                    label: "Mão de Obra",
                                    calc: data.calcMaoObra,
                                    razao: data.razaoMaoObra,
                                    ref: data.refMaoObra
                                },
                                {
                                    label: "Maquinários",
                                    calc: data.calcMaquinarios,
                                    razao: data.razaoMaquinarios,
                                    ref: data.refMaquinarios
                                },
                                {
                                    label: "Ração",
                                    calc: data.calcRacao,
                                    razao: data.razaoRacao,
                                    ref: data.refRacao
                                },
                                {
                                    label: "Cuidado com o Solo",
                                    calc: data.calcCuidadoSolo,
                                    razao: data.razaoCuidadoSolo,
                                    ref: data.refCuidadoSolo
                                }
                            ]
                        }
                    ]
                },
                {
                    category: "producao",
                    label: "Produção",
                    subcategories: [
                        {
                            label: "Produção",
                            items: [
                                {
                                    label: "Produção de Leite",
                                    calc: data.calcProducaoLeite,
                                    razao: data.razaoProducaoLeite,
                                    ref: data.refProducaoLeite
                                }
                            ]
                        }
                    ]
                }
            ];
            
            // Preencher a tabela com os dados
            tableData.forEach(({ category, label, subcategories }) => {
                if (Array.isArray(subcategories) && subcategories.length > 0 && subcategories[0].items) {
                    subcategories.forEach(subcategory => {
                        const subcategoryRow = document.createElement("tr");
                        subcategoryRow.style.backgroundColor = "#f8f9fa";
                        subcategoryRow.innerHTML = `
                            <td style="border: 1px solid #ccc; padding: 8px;"></td>
                            <td style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">${subcategory.label}</td>
                            <td style="border: 1px solid #ccc; padding: 8px;"></td>
                            <td style="border: 1px solid #ccc; padding: 8px;"></td>
                            <td style="border: 1px solid #ccc; padding: 8px;"></td>
                            <td style="border: 1px solid #ccc; padding: 8px;"></td>
                            <td style="border: 1px solid #ccc; padding: 8px;"></td>
                        `;
                        tbody.appendChild(subcategoryRow);

                        let subtotalRef = 0;

                        subcategory.items.forEach(item => {
                            const itemRow = document.createElement("tr");
                            itemRow.style.backgroundColor = "#ffffff";
                            itemRow.innerHTML = `
                                <td style="border: 1px solid #ccc; padding: 8px;"></td>
                                <td style="border: 1px solid #ccc; padding: 8px;"></td>
                                <td style="border: 1px solid #ccc; padding: 8px;">${item.label}</td>
                                <td style="border: 1px solid #ccc; padding: 8px;">${
                                    isFinite(item.calc) ? Number(item.calc).toExponential(2).toUpperCase() : (item.calc ?? "")
                                }</td>
                                <td style="border: 1px solid #ccc; padding: 8px;">${
                                    isFinite(item.razao) ? Number(item.razao).toExponential(2).toUpperCase() : (item.razao ?? "")
                                }</td>
                                <td style="border: 1px solid #ccc; padding: 8px;">${
                                    isFinite(item.ref) ? Number(item.ref).toExponential(2).toUpperCase() : (item.ref ?? "")
                                }</td>
                                <td style="border: 1px solid #ccc; padding: 8px;"></td>
                            `;
                            tbody.appendChild(itemRow);

                            subtotalRef += Number(item.ref);
                        });

                        // Subtotais intermediários do backend
                        if (category === "ambiental" && subcategory.label === "Energia Renovável") {
                            const renovavelRow = document.createElement("tr");
                            renovavelRow.style.backgroundColor = "#f1f8e9";
                            renovavelRow.innerHTML = `
                                <td></td>
                                <td style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">Subtotal Renovável</td>
                                <td colspan="3"></td>
                                <td style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">
                                    ${totais.totalRenovavel !== undefined ? Number(totais.totalRenovavel).toExponential(2).toUpperCase() : ""}
                                </td>
                                <td style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">
                                    ${totais.porcentagemRenovavel !== undefined ? Number(totais.porcentagemRenovavel).toFixed(2) + "%" : ""}
                                </td>
                            `;
                            tbody.appendChild(renovavelRow);
                        }
                        if (category === "ambiental" && subcategory.label === "Energia Não Renovável") {
                            const naoRenovavelRow = document.createElement("tr");
                            naoRenovavelRow.style.backgroundColor = "#fff3cd";
                            naoRenovavelRow.innerHTML = `
                                <td></td>
                                <td style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">Subtotal Não Renovável</td>
                                <td colspan="3"></td>
                                <td style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">
                                    ${totais.totalNaoRenovavel !== undefined ? Number(totais.totalNaoRenovavel).toExponential(2).toUpperCase() : ""}
                                </td>
                                <td style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">
                                    ${totais.porcentagemNaoRenovavel !== undefined ? Number(totais.porcentagemNaoRenovavel).toFixed(2) + "%" : ""}
                                </td>
                            `;
                            tbody.appendChild(naoRenovavelRow);
                        }
                        if (category === "humana" && subcategory.label === "Operações de Produção") {
                            const operacoesRow = document.createElement("tr");
                            operacoesRow.style.backgroundColor = "#e3f2fd";
                            operacoesRow.innerHTML = `
                                <td></td>
                                <td style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">Subtotal Operações de Produção</td>
                                <td colspan="3"></td>
                                <td style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">
                                    ${totais.totalOperacoesProducao !== undefined ? Number(totais.totalOperacoesProducao).toExponential(2).toUpperCase() : ""}
                                </td>
                                <td style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">
                                    ${totais.porcentagemOperacoesProducao !== undefined ? Number(totais.porcentagemOperacoesProducao).toFixed(2) + "%" : ""}
                                </td>
                            `;
                            tbody.appendChild(operacoesRow);
                        }
                        if (category === "humana" && subcategory.label === "Bens") {
                            const bensRow = document.createElement("tr");
                            bensRow.style.backgroundColor = "#fce4ec";
                            bensRow.innerHTML = `
                                <td></td>
                                <td style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">Subtotal Bens</td>
                                <td colspan="3"></td>
                                <td style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">
                                    ${totais.totalBens !== undefined ? Number(totais.totalBens).toExponential(2).toUpperCase() : ""}
                                </td>
                                <td style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">
                                    ${totais.porcentagemBens !== undefined ? Number(totais.porcentagemBens).toFixed(2) + "%" : ""}
                                </td>
                            `;
                            tbody.appendChild(bensRow);
                        }

                        // Subtotal visual (referência somada)
                        const subtotalRow = document.createElement("tr");
                        subtotalRow.style.backgroundColor = "#e2e3e5";
                        subtotalRow.innerHTML = `
                            <td style="border: 1px solid #ccc; padding: 8px;"></td>
                            <td style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">Subtotal</td>
                            <td style="border: 1px solid #ccc; padding: 8px;"></td>
                            <td style="border: 1px solid #ccc; padding: 8px;"></td>
                            <td style="border: 1px solid #ccc; padding: 8px;"></td>
                            <td style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">${subtotalRef.toExponential(2).toUpperCase()}</td>
                            <td style="border: 1px solid #ccc; padding: 8px;"></td>
                        `;
                        tbody.appendChild(subtotalRow);
                    });

                    // Subtotal final da categoria (do backend)
                    let subtotalBackend = "";
                    let percentualBackend = "";
                    if (category === "ambiental") {
                        subtotalBackend = totais.totalAmbiental;
                        percentualBackend = totais.porcentagemAmbiental;
                    } else if (category === "humana") {
                        subtotalBackend = totais.totalContribuicaoHumana;
                        percentualBackend = totais.porcentagemContribuicaoHumana;
                    } else if (category === "producao") {
                        subtotalBackend = totais.totalProducao;
                        percentualBackend = totais.porcentagemProducao;
                    }
                    const totalCatRow = document.createElement("tr");
                    totalCatRow.style.backgroundColor = "#d1ecf1";
                    totalCatRow.innerHTML = `
                        <td style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">${label}</td>
                        <td style="border: 1px solid #ccc; padding: 8px;"></td>
                        <td style="border: 1px solid #ccc; padding: 8px;"></td>
                        <td style="border: 1px solid #ccc; padding: 8px;"></td>
                        <td style="border: 1px solid #ccc; padding: 8px;"></td>
                        <td style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">
                            ${subtotalBackend !== "" ? Number(subtotalBackend).toExponential(2).toUpperCase() : ""}
                        </td>
                        <td style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">
                            ${percentualBackend !== "" ? Number(percentualBackend).toFixed(2) + "%" : ""}
                        </td>
                    `;
                    tbody.appendChild(totalCatRow);

                } else {
                    // Categoria sem subcategorias (ex: produção)
                    subcategories.forEach(row => {
                        const tr = document.createElement("tr");
                        tr.style.backgroundColor = "#f4f4f4";
                        tr.innerHTML = `
                            <td style="border: 1px solid #ccc; padding: 8px;"></td>
                            <td style="border: 1px solid #ccc; padding: 8px;"></td>
                            <td style="border: 1px solid #ccc; padding: 8px;">${row.label}</td>
                            <td style="border: 1px solid #ccc; padding: 8px;">${
                                isFinite(row.calc) ? Number(row.calc).toExponential(2).toUpperCase() : (row.calc ?? "")
                            }</td>
                            <td style="border: 1px solid #ccc; padding: 8px;">${
                                isFinite(row.razao) ? Number(row.razao).toExponential(2).toUpperCase() : (row.razao ?? "")
                            }</td>
                            <td style="border: 1px solid #ccc; padding: 8px;">${
                                isFinite(row.ref) ? Number(row.ref).toExponential(2).toUpperCase() : (row.ref ?? "")
                            }</td>
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
            
            // Linha de total geral (100%) usando o backend:
            const totalRow = document.createElement("tr");
            totalRow.style.backgroundColor = "#d4edda";
            totalRow.innerHTML = `
                <td colspan="5" style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">
                    Total Geral
                </td>
                <td style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">
                    ${totais.calculoTotal ? Number(totais.calculoTotal).toExponential(2).toUpperCase() : ""}
                </td>
                <td style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">100%</td>
            `;
            tbody.appendChild(totalRow);
            table.appendChild(tbody);
            container.appendChild(table);
            
            // --- busca e exibe sustentabilidade ---
            try {
                const resSust = await fetch("http://localhost:8081/sustentabilidade/latest");
                if (resSust.ok) {
                    const sust = await resSust.json();

                    const analiseIndice = valor => {
                        if (valor < 1.0) return "Sustentável";
                        if (valor === 1.0) return "Equilibrado";
                        if (valor > 1.0) return "Não sustentável";
                        return "-";
                    };

                    const tabelaSust = document.createElement("table");
                    tabelaSust.style.margin = "30px auto";
                    tabelaSust.style.borderCollapse = "collapse";
                    tabelaSust.innerHTML = `
                        <thead>
                            <tr style="background-color: #343a40; color: white;">
                                <th style="border:1px solid #ccc;padding:8px;">Índice</th>
                                <th style="border:1px solid #ccc;padding:8px;">Valor</th>
                                <th style="border:1px solid #ccc;padding:8px;">Análise</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td style="border:1px solid #ccc;padding:8px;">EYR</td>
                                <td style="border:1px solid #ccc;padding:8px;text-align:center;">${Number(sust.eyr).toFixed(2)}</td>
                                <td style="border:1px solid #ccc;padding:8px;text-align:center;">${analiseIndice(Number(sust.eyr))}</td>
                            </tr>
                            <tr>
                                <td style="border:1px solid #ccc;padding:8px;">ELR</td>
                                <td style="border:1px solid #ccc;padding:8px;text-align:center;">${Number(sust.elr).toFixed(2)}</td>
                                <td style="border:1px solid #ccc;padding:8px;text-align:center;">${analiseIndice(Number(sust.elr))}</td>
                            </tr>
                            <tr>
                                <td style="border:1px solid #ccc;padding:8px;">ESI</td>
                                <td style="border:1px solid #ccc;padding:8px;text-align:center;">${Number(sust.esi).toFixed(2)}</td>
                                <td style="border:1px solid #ccc;padding:8px;text-align:center;">${analiseIndice(Number(sust.esi))}</td>
                            </tr>
                            <tr>
                                <td style="border:1px solid #ccc;padding:8px;">EIR</td>
                                <td style="border:1px solid #ccc;padding:8px;text-align:center;">${Number(sust.eir).toFixed(2)}</td>
                                <td style="border:1px solid #ccc;padding:8px;text-align:center;">${analiseIndice(Number(sust.eir))}</td>
                            </tr>
                        </tbody>
                    `;

                    const div = document.createElement("div");
                    div.style.marginTop = "30px";
                    div.innerHTML = `<h3 style="text-align:center;">Índices de Sustentabilidade</h3>`;
                    div.appendChild(tabelaSust);
                    container.appendChild(div);
                }
            } catch(e) {
                console.warn("Não foi possível carregar índices de sustentabilidade", e);
            }
            
            // Adicionar gráfico
            const chartContainer = document.createElement("div");
            chartContainer.style.marginTop = "30px";
            chartContainer.style.width = "100%";
            chartContainer.style.height = "400px";
            container.appendChild(chartContainer);
            
            // Criar o gráfico utilizando canvas e seu contexto 2D
            const canvas = document.createElement("canvas");
            canvas.id = "myChart";
            canvas.style.width = "100%";
            canvas.style.height = "100%";
            chartContainer.appendChild(canvas);
            
            const ctx = canvas.getContext("2d");
            // Dados para o gráfico
            const chartData = {
                labels: [
                    "Renovável",
                    "Não Renovável",
                    "Operações de Produção",
                    "Bens",
                    "Produção"
                ],
                datasets: [{
                    data: [
                        totais.porcentagemRenovavel !== undefined ? Number(totais.porcentagemRenovavel.toFixed(2)) : 0,
                        totais.porcentagemNaoRenovavel !== undefined ? Number(totais.porcentagemNaoRenovavel.toFixed(2)) : 0,
                        totais.porcentagemOperacoesProducao !== undefined ? Number(totais.porcentagemOperacoesProducao.toFixed(2)) : 0,
                        totais.porcentagemBens !== undefined ? Number(totais.porcentagemBens.toFixed(2)) : 0,
                        totais.porcentagemProducao !== undefined ? Number(totais.porcentagemProducao.toFixed(2)) : 0
                    ],
                    backgroundColor: [
                        "#36a2eb", // Renovável
                        "#ffcd56", // Não Renovável
                        "#4bc0c0", // Operações de Produção
                        "#ff6384", // Bens
                        "#9966ff"  // Produção
                    ],
                    hoverBackgroundColor: [
                        "#36a2eb",
                        "#ffcd56",
                        "#4bc0c0",
                        "#ff6384",
                        "#9966ff"
                    ]
                }]
            };
            
            // Configuração do gráfico
            const chartConfig = {
                type: "pie",
                data: chartData,
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                    plugins: {
                        legend: {
                            position: "top",
                        },
                        title: {
                            display: true,
                            text: "Distribuição dos Subtotais"
                        }
                    }
                }
            };
            
            // Inicializar o gráfico
            new Chart(ctx, chartConfig);
            
        } catch (error) {
            console.error("Erro ao carregar dados:", error);
            container.innerHTML = `<div class="alert alert-danger">Erro ao carregar dados: ${error.message}</div>`;
        }
    })();
});