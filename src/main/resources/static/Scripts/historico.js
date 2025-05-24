document.addEventListener("DOMContentLoaded", async function() {
    const historicoContainer = document.getElementById("historico-container");
    
    // Clear the loading message
    historicoContainer.innerHTML = 
        '<p style="width: 100%; text-align: center; color: #888; font-style: italic;">Carregando histórico...</p>'; 
    
    try {
        // Fetch all sustainability records from the backend
        const response = await fetch("http://localhost:8081/sustentabilidade/all");
        
        if (!response.ok) {
            throw new Error(`Erro ao buscar dados de sustentabilidade: ${response.statusText} (Status: ${response.status})`);
        }
        
        const data = await response.json();
        
        // Clear loading message before processing
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
        
        // Sort data by date (most recent first)
        data.sort((a, b) => new Date(b.criadoEm) - new Date(a.criadoEm));
        
        // Fetch report details (including farm name) for each sustainability record
        const reportPromises = data.map(item => {
            // Ensure relatorio and relatorio.id exist before fetching
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
        
        // Clear processing message
        historicoContainer.innerHTML = '';

        // Create and append cards for each record
        data.forEach((item, index) => {
            const card = document.createElement("div");
            card.className = "historico-item";
            
            const formattedDate = new Date(item.criadoEm).toLocaleString('pt-BR');
            const farmName = item.nomeDaFazenda || "Não informado";
            
            card.innerHTML = `
                <h3>Registro #${item.id}</h3>
                <p style="text-align:center; font-weight:bold; margin-bottom:10px;">Fazenda: ${farmName}</p>
                <table>
                    <thead>
                        <tr>
                            <th>Índice</th>
                            <th>Valor</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr><td>EYR</td><td>${Number(item.eyr).toFixed(2)}</td></tr>
                        <tr><td>ELR</td><td>${Number(item.elr).toFixed(2)}</td></tr>
                        <tr><td>ESI</td><td>${Number(item.esi).toFixed(2)}</td></tr>
                        <tr><td>EIR</td><td>${Number(item.eir).toFixed(2)}</td></tr>
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
