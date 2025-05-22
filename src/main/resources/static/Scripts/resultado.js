document.addEventListener("DOMContentLoaded", function() {
    (async function() {
        const container = document.getElementById("relatorio-container");
        if (!container) return;
        
        try {
            const response = await fetch("http://localhost:8081/relatorio/getLastResults");
            if (!response.ok) {
                throw new Error("Erro ao buscar dados do relatório");
            }
            const data = await response.json();
            
            // Calcular índices de sustentabilidade para o relatório mais recente
            try {
                await fetch(`http://localhost:8081/sustentabilidade/calcular/${data.id}`, {
                    method: 'POST'
                });
            } catch (e) {
                console.warn("Erro ao calcular índices de sustentabilidade", e);
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
            
            // Função para obter a porcentagem do backend
            function getBackendPercentage(category) {
                switch (category) {
                    case "ambiental":
                        return "32%";
                    case "humana":
                        return "68%";
                    default:
                        return "";
                }
            }
            
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
                        },
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
                        }
                    ]
                },
                {
                    category: "producao",
                    label: "Produção",
                    subcategories: [
                        {
                            label: "Produção de Leite",
                            calc: data.calcProducaoLeite,
                            razao: data.razaoProducaoLeite,
                            ref: data.refProducaoLeite
                        }
                    ]
                }
            ];
            
            // Preencher a tabela com os dados
            tableData.forEach(({ category, label, subcategories }) => {
                const categoryRow = document.createElement("tr");
                categoryRow.style.backgroundColor = "#e9ecef";
                categoryRow.innerHTML = `
                    <td style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">${label}</td>
                    <td style="border: 1px solid #ccc; padding: 8px;"></td>
                    <td style="border: 1px solid #ccc; padding: 8px;"></td>
                    <td style="border: 1px solid #ccc; padding: 8px;"></td>
                    <td style="border: 1px solid #ccc; padding: 8px;"></td>
                    <td style="border: 1px solid #ccc; padding: 8px;"></td>
                    <td style="border: 1px solid #ccc; padding: 8px;"></td>
                `;
                tbody.appendChild(categoryRow);
                
                if (Array.isArray(subcategories) && subcategories.length > 0 && subcategories[0].items) {
                    // Categorias com subcategorias e itens
                    let totalRef = 0;
                    
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
                                <td style="border: 1px solid #ccc; padding: 8px;">${item.calc}</td>
                                <td style="border: 1px solid #ccc; padding: 8px;">${item.razao}</td>
                                <td style="border: 1px solid #ccc; padding: 8px;">${item.ref}</td>
                                <td style="border: 1px solid #ccc; padding: 8px;"></td>
                            `;
                            tbody.appendChild(itemRow);
                            
                            subtotalRef += Number(item.ref);
                        });
                        
                        totalRef += subtotalRef;
                        
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
                    
                    const ambientalRow = document.createElement("tr");
                    ambientalRow.style.backgroundColor = "#d1ecf1";
                    ambientalRow.innerHTML = `
                        <td style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">Total ${label}</td>
                        <td style="border: 1px solid #ccc; padding: 8px;"></td>
                        <td style="border: 1px solid #ccc; padding: 8px;"></td>
                        <td style="border: 1px solid #ccc; padding: 8px;"></td>
                        <td style="border: 1px solid #ccc; padding: 8px;"></td>
                        <td style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">${totalRef.toExponential(2).toUpperCase()}</td>
                        <td style="border: 1px solid #ccc; padding: 8px; font-weight: bold;">
                            ${getBackendPercentage(category)}
                        </td>
                    `;
                    tbody.appendChild(ambientalRow);
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
            
            // --- busca e exibe sustentabilidade ---
            try {
                const resSust = await fetch("http://localhost:8081/sustentabilidade/latest");
                if (resSust.ok) {
                    const sust = await resSust.json();
                    const div = document.createElement("div");
                    div.style.marginTop = "30px";
                    div.innerHTML = `
                      <h3 style="text-align:center;">Índices de Sustentabilidade</h3>
                      <table style="margin:0 auto; border-collapse:collapse;">
                        <thead>
                          <tr style="background-color: #343a40; color: white;">
                            <th style="border:1px solid #ccc;padding:8px;">EYR</th>
                            <th style="border:1px solid #ccc;padding:8px;">ELR</th>
                            <th style="border:1px solid #ccc;padding:8px;">ESI</th>
                            <th style="border:1px solid #ccc;padding:8px;">EIR</th>
                            <th style="border:1px solid #ccc;padding:8px;">Data</th>
                          </tr>
                        </thead>
                        <tbody>
                          <tr>
                            <td style="border:1px solid #ccc;padding:8px;text-align:center;">${Number(sust.eyr).toFixed(2)}</td>
                            <td style="border:1px solid #ccc;padding:8px;text-align:center;">${Number(sust.elr).toFixed(2)}</td>
                            <td style="border:1px solid #ccc;padding:8px;text-align:center;">${Number(sust.esi).toFixed(2)}</td>
                            <td style="border:1px solid #ccc;padding:8px;text-align:center;">${Number(sust.eir).toFixed(2)}</td>
                            <td style="border:1px solid #ccc;padding:8px;text-align:center;">${new Date(sust.criadoEm).toLocaleString()}</td>
                          </tr>
                        </tbody>
                      </table>
                      <div style="text-align:center;margin-top:20px;">
                        <a href="historico.html" class="btn btn-primary" style="padding:8px 16px;background-color:#007bff;color:white;text-decoration:none;border-radius:4px;">Ver Histórico</a>
                      </div>`;
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
                labels: ["Contribuição Ambiental", "Contribuição Humana"],
                datasets: [{
                    data: [32, 68],
                    backgroundColor: ["#36a2eb", "#ff6384"],
                    hoverBackgroundColor: ["#36a2eb", "#ff6384"]
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
                            text: "Distribuição de Contribuições"
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