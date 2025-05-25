document.addEventListener("DOMContentLoaded", async function() {
    const historicoContainer = document.getElementById("historico-container");

    historicoContainer.innerHTML = 
        '<p style="width: 100%; text-align: center; color: #888; font-style: italic;">Carregando histórico...</p>'; 
    
    try {
        // Buscar todos os registros de sustentabilidade
        const response = await fetch("http://localhost:8081/sustentabilidade/all");
        
        if (!response.ok) {
            throw new Error(`Erro ao buscar dados de sustentabilidade: ${response.statusText} (Status: ${response.status})`);
        }
        
        const data = await response.json();
        
        // Limpar mensagem de carregamento
        historicoContainer.innerHTML = 
            '<p style="width: 100%; text-align: center; color: #888; font-style: italic;">Processando registros...</p>';
        
        if (data.length === 0) {
            historicoContainer.innerHTML = `
                <div class="sem-registros" style="width: 100%; text-align: center; padding: 20px; background-color: #f8f9fa; border-radius: 8px; color: #666;">
                    <h3>Nenhum registro de sustentabilidade encontrado</h3>
                    <p>Realize cálculos na calculadora para gerar índices de sustentabilidade.</p>
                </div>
            `;
            return;
        }
        
        // Organizar os dados por data de criação, do mais recente para o mais antigo,
        // por ordem de ID na entidade
        data.sort((a, b) => new Date(b.criadoEm) - new Date(a.criadoEm));
        
        // Buscar detalhes do relatório para cada registro
        const reportPromises = data.map(item => {

            // Validar relatórios
            if (item.relatorio && item.relatorio.id) {
                return fetch(`http://localhost:8081/relatorio/${item.relatorio.id}`)
                    .then(res => res.ok ? res.json() : { nomeDaFazenda: "Não encontrado" })
                    .catch(err => {
                        console.error(`Erro ao buscar relatório ${item.relatorio.id}:`, err);
                        return { nomeDaFazenda: "Erro ao buscar" };
                    });
            } else {
                console.warn("Registro de sustentabilidade sem ID de relatório:", item);
                return Promise.resolve({ nomeDaFazenda: "Relatório ausente" });
            }
        });

        const reportDetails = await Promise.all(reportPromises);
        
        // Limpar mensagem de processamento
        historicoContainer.innerHTML = '';

        // Adicionar funções de análise de índices
        const analiseEYR = valor => {
            if (valor > 5) return "Muito sustentável";
            if (valor >= 2) return "Moderado";
            if (valor >= 1) return "Pouco sustentável";
            return "Muito pouco sustentável";
        };
        const analiseELR = valor => {
            if (valor < 1) return "Baixa carga ambiental";
            if (valor === 1) return "Equilibrado";
            if (valor <= 10) return "Alta carga ambiental";
            return "Insistentável.";
        };
        const analiseESI = valor => {
            if (valor > 10) return "Alta sustentabilidade";
            if (valor >= 1) return "Sustentável.";
            return "Baixa sustentabilidade";
        };
        const analiseEIR = valor => {
            if (valor < 1) return "Boa sustentabilidade";
            if (valor === 1) return "Equilíbrado";
            return "Alta dependência da economia";
        };

        // Formata número: se tiver mais de 2 dígitos antes da vírgula, usa notação científica
        const formatValor = valor => {
            const abs = Math.abs(valor);
            return abs >= 100 
                ? valor.toExponential(1) 
                : valor.toFixed(1);
        };

        // Criar e exibir os cards de histórico
        data.forEach((item, index) => {
            const card = document.createElement("div");
            card.className = "historico-item";

            card.innerHTML = `
                <h3>Registro #${item.id}</h3>
                <p style="text-align:center; font-weight:bold; margin-bottom:10px;">
                    Fazenda: ${item.nomeDaFazenda || "Não informado"}
                </p>
                <table>
                    <thead>
                        <tr>
                            <th>Índice</th>
                            <th>Valor</th>
                            <th>Análise</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>EYR</td>
                            <td>${formatValor(Number(item.eyr))}</td>
                            <td>${analiseEYR(Number(item.eyr))}</td>
                        </tr>
                        <tr>
                            <td>ELR</td>
                            <td>${formatValor(Number(item.elr))}</td>
                            <td>${analiseELR(Number(item.elr))}</td>
                        </tr>
                        <tr>
                            <td>EIR</td>
                            <td>${formatValor(Number(item.eir))}</td>
                            <td>${analiseEIR(Number(item.eir))}</td>
                        </tr>
                        <tr>
                            <td>ESI</td>
                            <td>${formatValor(Number(item.esi))}</td>
                            <td>${analiseESI(Number(item.esi))}</td>
                        </tr>
                    </tbody>
                </table>
            `;
            historicoContainer.appendChild(card);
        });
        
    } catch (error) {
        console.error("Erro ao carregar histórico:", error);
        historicoContainer.innerHTML = `
            <div class="sem-registros" style="width: 100%; text-align: center; padding: 20px; background-color: #ffebee; border: 1px solid #e57373; border-radius: 8px; color: #c62828;">
                <h3>Erro ao carregar histórico</h3>
                <p>${error.message}</p>
                <p>Verifique se o backend está em execução, se os endpoints estão corretos e se há dados no banco.</p>
            </div>
        `;
    }
});
