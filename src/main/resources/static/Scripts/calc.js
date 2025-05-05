import getDados from "./getDados.js";

window.calcResults = {}; // Armazena os resultados globalmente

document.addEventListener("DOMContentLoaded", () => {
  // Água Usada
  const aguaInputs = {
    litros: document.getElementById("aguaUsada"),
    animais: document.getElementById("qtdAnima"),
    adicionais: document.getElementById("litrosAdicionalDia"),
  };
  Object.values(aguaInputs).forEach(input => {
    input.addEventListener("input", () => {
        const litros = parseFloat(aguaInputs.litros.value);
        const animais = parseInt(aguaInputs.animais.value);
        const adicionais = parseFloat(aguaInputs.adicionais.value);
        if (!isNaN(litros) && !isNaN(animais) && !isNaN(adicionais)) {
            // Parâmetros: litroPorDiaAnimal, qtdAnimal, litrosAdicionalDia
            getDados(`aguaUsada/calc?litroPorDiaAnimal=${litros}&qtdAnimal=${animais}&litrosAdicionalDia=${adicionais}`)
                .then(result => displayResult("aguaUsada", result))
                .catch(error => console.error("Erro ao calcular água usada:", error));
        }
    });
  });
  
  // Combustível
  const combustivelInputs = {
      horas: document.getElementById("combustivelHoras"),
      tratores: document.getElementById("combustivelTratores"),
      litros: document.getElementById("combustivelLitros"),
  };
  Object.values(combustivelInputs).forEach(input => {
      input.addEventListener("input", () => {
          const horas = parseFloat(combustivelInputs.horas.value);
          const tratores = parseInt(combustivelInputs.tratores.value);
          const litros = parseFloat(combustivelInputs.litros.value);
          if (!isNaN(horas) && !isNaN(tratores) && !isNaN(litros)) {
              // Parâmetros: horasTratorPorAno, qtdTrator, litrosPorHoras
              getDados(`combustivel/calc?horasTratorPorAno=${horas}&qtdTrator=${tratores}&litrosPorHoras=${litros}`)
                  .then(result => displayResult("combustivel", result))
                  .catch(error => console.error("Erro ao calcular combustível:", error));
          }
      });
  });

  // Cuidado do Solo
  const soloInputs = {
      toneladas: document.getElementById("soloToneladas"),
      anos: document.getElementById("soloAnos"),
      area: document.getElementById("soloArea"),
  };
  Object.values(soloInputs).forEach(input => {
      input.addEventListener("input", () => {
          const toneladas = parseFloat(soloInputs.toneladas.value);
          const anos = parseInt(soloInputs.anos.value);
          const area = parseFloat(soloInputs.area.value);
          if (!isNaN(toneladas) && !isNaN(anos) && !isNaN(area)) {
              // Parâmetros: toneladaPorHA, ano, areaDevastadaPeloGado
              getDados(`solo/calc?toneladaPorHA=${toneladas}&ano=${anos}&areaDevastadaPeloGado=${area}`)
                  .then(result => displayResult("solo", result))
                  .catch(error => console.error("Erro ao calcular cuidado do solo:", error));
          }
      });
  });

  // Eletricidade
  const eletricaInput = document.getElementById("eletrica");
  eletricaInput.addEventListener("input", () => {
      const energiaPORKWH = parseFloat(eletricaInput.value);
      if (!isNaN(energiaPORKWH)) {
          getDados(`eletrica/calc?energiaPORKWH=${energiaPORKWH}`)
              .then(result => displayResult("eletrica", result))
              .catch(error => console.error("Erro ao calcular eletricidade:", error));
      }
  });

  // Gado
  const gadoInputs = {
      peso: document.getElementById("gadoPeso"),
      quantidade: document.getElementById("gadoQuantidade"),
      anos: document.getElementById("gadoAnos"),
  };
  Object.values(gadoInputs).forEach(input => {
      input.addEventListener("input", () => {
          const peso = parseFloat(gadoInputs.peso.value);
          const quantidade = parseInt(gadoInputs.quantidade.value);
          const anos = parseFloat(gadoInputs.anos.value);
          if (!isNaN(peso) && !isNaN(quantidade) && !isNaN(anos)) {
              // Parâmetros: pesoKgMedia, numeroAnimais, anosVidaMedia
              getDados(`gado/calc?pesoKgMedia=${peso}&numeroAnimais=${quantidade}&anosVidaMedia=${anos}`)
                  .then(result => displayResult("gado", result))
                  .catch(error => console.error("Erro ao calcular gado:", error));
          }
      });
  });

  // Mão de Obra
  const maoObraInputs = {
      pessoas: document.getElementById("maoObraPessoas"),
      horas: document.getElementById("maoObraHoras"),
      dias: document.getElementById("maoObraDias"),
  };
  Object.values(maoObraInputs).forEach(input => {
      input.addEventListener("input", () => {
          const pessoas = parseInt(maoObraInputs.pessoas.value);
          const horas = parseFloat(maoObraInputs.horas.value);
          const dias = parseInt(maoObraInputs.dias.value);
          if (!isNaN(pessoas) && !isNaN(horas) && !isNaN(dias)) {
              // Parâmetros: pessoa, horasTrabalhadas, qtdDiasTrabalhado
              getDados(`maoObra/calc?pessoa=${pessoas}&horasTrabalhadas=${horas}&qtdDiasTrabalhado=${dias}`)
                  .then(result => displayResult("maoObra", result))
                  .catch(error => console.error("Erro ao calcular mão de obra:", error));
          }
      });
  });

  // Maquinários
  const maquinarioInputs = {
      horas: document.getElementById("maquinarioHoras"),
      valor: document.getElementById("maquinarioValor"),
  };
  Object.values(maquinarioInputs).forEach(input => {
      input.addEventListener("input", () => {
          const horas = parseFloat(maquinarioInputs.horas.value);
          const valor = parseFloat(maquinarioInputs.valor.value);
          if (!isNaN(horas) && !isNaN(valor)) {
              // Parâmetros: qtdHoraTrator, valorHoraTrator
              getDados(`maquinario/calc?qtdHoraTrator=${horas}&valorHoraTrator=${valor}`)
                  .then(result => displayResult("maquinario", result))
                  .catch(error => console.error("Erro ao calcular maquinários:", error));
          }
      });
  });

  // Perda do Solo
  const perdaSoloInput = document.getElementById("perdaSolo");
  perdaSoloInput.addEventListener("input", () => {
      const perda = parseFloat(perdaSoloInput.value);
      if (!isNaN(perda)) {
          // Parâmetro: perdaDeSoloTonelada
          getDados(`perdaSolo/calc?perdaDeSoloTonelada=${perda}`)
              .then(result => displayResult("perdaSolo", result))
              .catch(error => console.error("Erro ao calcular perda do solo:", error));
      }
  });

  // Potencial Químico
  const potencialQuimicoInputs = {
      area: document.getElementById("potencialQuimicoArea"),
      chuva: document.getElementById("potencialQuimicoChuva"),
  };
  Object.values(potencialQuimicoInputs).forEach(input => {
      input.addEventListener("input", () => {
          const area = parseFloat(potencialQuimicoInputs.area.value);
          const chuva = parseFloat(potencialQuimicoInputs.chuva.value);
          if (!isNaN(area) && !isNaN(chuva)) {
              // Parâmetros: haFazenda, mediaChuvaAnoMetros
              getDados(`potencialQuimico/calc?haFazenda=${area}&mediaChuvaAnoMetros=${chuva}`)
                  .then(result => displayResult("potencialQuimico", result))
                  .catch(error => console.error("Erro ao calcular potencial químico:", error));
          }
      });
  });

  // Produção de Leite
  const producaoLeiteInputs = {
      area: document.getElementById("producaoLeiteArea"),
      litros: document.getElementById("producaoLeiteLitros"),
  };
  Object.values(producaoLeiteInputs).forEach(input => {
      input.addEventListener("input", () => {
          const area = parseFloat(producaoLeiteInputs.area.value);
          const litros = parseFloat(producaoLeiteInputs.litros.value);
          if (!isNaN(area) && !isNaN(litros)) {
              getDados(`producaoLeite/calc?leitePorDia=${litros}&haFazendaLeite=${area}`)
                  .then(result => displayResult("producaoLeite", result))
                  .catch(error => console.error("Erro ao calcular produção de leite:", error));
          }
      });
  });

  // Ração
  const racaoInputs = {
      sacas: document.getElementById("racaoSacas"),
      valor: document.getElementById("racaoValor"),
  };
  Object.values(racaoInputs).forEach(input => {
      input.addEventListener("input", () => {
          const sacas = parseInt(racaoInputs.sacas.value);
          const valor = parseFloat(racaoInputs.valor.value);
          if (!isNaN(sacas) && !isNaN(valor)) {
              getDados(`racao/calc?saca=${sacas}&valorSaca=${valor}`)
                  .then(result => displayResult("racao", result))
                  .catch(error => console.error("Erro ao calcular ração:", error));
          }
      });
  });


  // Consumo e Manutenção
  const consumoInputs = {
      valor: document.getElementById("consumoFazendaValor"),
      anos: document.getElementById("consumoFazendaAnos"),
  };
  Object.values(consumoInputs).forEach(input => {
      input.addEventListener("input", () => {
          const valor = parseFloat(consumoInputs.valor.value);
          const anos = parseInt(consumoInputs.anos.value);
          if (!isNaN(valor) && !isNaN(anos)) {
              getDados(`consumoFazenda/calc?bens=${valor}&anos=${anos}`)
                  .then(result => displayResult("consumoFazenda", result))
                  .catch(error => console.error("Erro ao calcular consumo e manutenção:", error));
          }
      });
  });

  // Botão para visualizar resultado
  const visualizarBtn = document.getElementById("visualizarResultado");
  if(visualizarBtn) {
      visualizarBtn.addEventListener("click", () => {
          // Salva os resultados em localStorage
          localStorage.setItem("calcResults", JSON.stringify(window.calcResults));
          // Redireciona para a página de resultados
          window.location.href = "resultado.html";
      });
  }
});

// Função para exibir o resultado abaixo do campo correspondente e armazená-lo
function displayResult(fieldId, result) {
    let containerElem = document.getElementById(fieldId) || document.querySelector(`#${fieldId}`);
    let container = containerElem ? containerElem.closest(".container") : null;
    if (!container) {
        console.warn(`Container não encontrado para o campo: ${fieldId}. Criando container padrão.`);
        container = document.createElement("div");
        container.className = "container";
        document.body.appendChild(container);
    }
    
    let resultElement = document.getElementById(`${fieldId}-result`);
    if (!resultElement) {
        resultElement = document.createElement("div");
        resultElement.id = `${fieldId}-result`;
        resultElement.style.marginTop = "10px";
        container.appendChild(resultElement);
    }

    // Se o resultado for um objeto com as 3 propriedades, exibe todos
    if (typeof result === 'object' && result.calc !== undefined && result.ref !== undefined && result.razao !== undefined) {
        resultElement.innerHTML = `
            <strong>Calc:</strong> ${Number(result.calc).toExponential(2).toUpperCase()}<br>
            <strong>Ref:</strong> ${Number(result.ref).toExponential(2).toUpperCase()}<br>
            <strong>Razão:</strong> ${Number(result.razao).toExponential(2).toUpperCase()}
        `;
    } else {
        // fallback para resultado numérico único
        let numericResult = Number(result);
        resultElement.textContent = `Resultado (${fieldId}): ${numericResult.toExponential(2).toUpperCase()}`;
    }
    
    // Atualiza objeto global com os detalhes
    let label = container && container.querySelector('h2') ? container.querySelector('h2').textContent : fieldId;
    let inputElements = container ? container.querySelectorAll('input') : [];
    let inputsStr = Array.from(inputElements).map(inp => inp.value).join(', ');
    window.calcResults[fieldId] = {
         label: label,
         inputs: inputsStr,
         result: resultElement.innerHTML
    };
    localStorage.setItem("calcResults", JSON.stringify(window.calcResults));
}

// Função para exibir erros no container correspondente
function displayError(fieldId, errorMessage) {
    let errorElement = document.getElementById(`${fieldId}-error`);
    
    // Localiza o container correspondente
    let container = document.getElementById(fieldId)?.closest(".container");
    if (!container) {
        console.warn(`Container não encontrado para o campo: ${fieldId}. Criando um container padrão.`);
        container = document.createElement("div");
        container.className = "container";
        document.body.appendChild(container);
    }

    // Se o elemento de erro não existir, cria um novo
    if (!errorElement) {
        errorElement = document.createElement("div");
        errorElement.id = `${fieldId}-error`;
        errorElement.style.marginTop = "10px";
        errorElement.style.color = "red"; // Define a cor do texto como vermelho para destacar o erro
        container.appendChild(errorElement);
    }

    // Atualiza o texto do erro
    errorElement.textContent = `Erro (${fieldId}): ${errorMessage}`;
}

// No seu calc.js, depois de gerar os resultados:
function saveResultsToServer() {
    localStorage.setItem("calcResults", JSON.stringify(window.calcResults));
    fetch('storeResults', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(window.calcResults)
    })
    .then(response => response.json())
    .then(data => console.log('Dados salvos com sucesso', data))
    .catch(error => console.error('Erro ao salvar os dados:', error));
}
