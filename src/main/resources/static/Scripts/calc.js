import getDados from "./getDados.js";

window.calcResults = {};

// Definição das seções e seus campos
const sectionFields = {
  potencialQuimico: ['potencialQuimicoArea', 'potencialQuimicoChuva'],
  aguaUsada: ['aguaUsada', 'qtdAnima', 'litrosAdicionalDia'],
  perdaSolo: ['perdaSolo'],
  consumoManutencao: ['consumoFazendaValor', 'consumoFazendaAnos'],
  combustivel: ['combustivelHoras', 'combustivelTratores', 'combustivelLitros'],
  cuidadoSolo: ['soloToneladas', 'soloAnos', 'soloArea'],
  eletricidade: ['eletrica'],
  gado: ['gadoPeso', 'gadoQuantidade', 'gadoAnos'],
  maquinarios: ['maquinarioHoras', 'maquinarioValor'],
  maoObra: ['maoObraPessoas', 'maoObraHoras', 'maoObraDias'],
  racao: ['racaoSacas', 'racaoValor'],
  producaoLeite: ['producaoLeiteArea', 'producaoLeiteLitros']
};

document.addEventListener("DOMContentLoaded", () => {
  // Inicializar tooltips do Bootstrap
  const tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
  tooltipTriggerList.map(function (tooltipTriggerEl) {
    return new bootstrap.Tooltip(tooltipTriggerEl);
  });

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
          .then(result => {
            displayResult("aguaUsada", result);
            updateSectionProgress('aguaUsada');
          })
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
        getDados(`combustivel/calc?horasTratorPorAno=${horas}&qtdTrator=${tratores}&litrosPorHora=${litros}`)
          .then(result => {
            displayResult("combustivelHoras", result);
            updateSectionProgress('combustivel');
          })
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
        getDados(`solo/calc?toneladaPorHA=${toneladas}&ano=${anos}&areaDevastadaPeloGado=${area}`)
          .then(result => {
            displayResult("soloToneladas", result);
            updateSectionProgress('cuidadoSolo');
          })
          .catch(error => console.error("Erro ao calcular cuidado do solo:", error));
      }
    });
  });

  // Eletricidade
  const eletricaInput = document.getElementById("eletrica");
  if (eletricaInput) {
    eletricaInput.addEventListener("input", () => {
      const energiaPORKWH = parseFloat(eletricaInput.value);
      if (!isNaN(energiaPORKWH)) {
        getDados(`eletrica/calc?energiaPORKWH=${energiaPORKWH}`)
          .then(result => {
            displayResult("eletrica", result);
            updateSectionProgress('eletricidade');
          })
          .catch(error => console.error("Erro ao calcular eletricidade:", error));
      }
    });
  }

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
        getDados(`gado/calc?pesoKgMedia=${peso}&numeroAnimais=${quantidade}&anosVidaMedia=${anos}`)
          .then(result => {
            displayResult("gadoPeso", result);
            updateSectionProgress('gado');
          })
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
        getDados(`maoObra/calc?pessoa=${pessoas}&horasTrabalhadas=${horas}&qtdDiasTrabalhado=${dias}`)
          .then(result => {
            displayResult("maoObraPessoas", result);
            updateSectionProgress('maoObra');
          })
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
        getDados(`maquinario/calc?qtdHoraTrator=${horas}&valorHoraTrator=${valor}`)
          .then(result => {
            displayResult("maquinarioHoras", result);
            updateSectionProgress('maquinarios');
          })
          .catch(error => console.error("Erro ao calcular maquinários:", error));
      }
    });
  });

  // Perda do Solo
  const perdaSoloInput = document.getElementById("perdaSolo");
  perdaSoloInput.addEventListener("input", () => {
    const perda = parseFloat(perdaSoloInput.value);
    if (!isNaN(perda)) {
      getDados(`perdaSolo/calc?perdaDeSoloTonelada=${perda}`)
        .then(result => {
          displayResult("perdaSolo", result);
          updateSectionProgress('perdaSolo');
        })
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
          .then(result => {
            displayResult("potencialQuimicoArea", result);
            updateSectionProgress('potencialQuimico');
          })
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
          .then(result => {
            displayResult("producaoLeiteArea", result);
            updateSectionProgress('producaoLeite');
          })
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
          .then(result => {
            displayResult("racaoSacas", result);
            updateSectionProgress('racao');
          })
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
          .then(result => {
            displayResult("consumoFazendaValor", result);
            updateSectionProgress('consumoManutencao');
          })
          .catch(error => console.error("Erro ao calcular consumo e manutenção:", error));
      }
    });
  });

  // Validação de campos vazios
  const inputsNum = document.querySelectorAll('input[type="number"]');
  inputsNum.forEach((inp, i) => {
    inp.addEventListener('change', () => {
      if (parseFloat(inp.value) === 0) {
        inp.classList.add('is-invalid');
        const nxt = inputsNum[i + 1];
        if (nxt) nxt.focus();
      } else {
        inp.classList.remove('is-invalid');
      }
      
      // Atualizar barra de progresso geral
      updateProgressBar();
      
      // Atualizar barra de progresso da seção
      const section = findSectionForField(inp.id);
      if (section) {
        updateSectionProgress(section);
      }
    });
  });

  // Botão para salvar no banco de dados
  const salvarNoBancoBtn = document.getElementById("salvarNoBanco");
  if (salvarNoBancoBtn) {
    salvarNoBancoBtn.addEventListener("click", async (e) => {
      e.preventDefault();
      const faltantes = findAllIncompleteContainers();
      if (faltantes.length) {
        alert(`Por favor, preencha todos os campos das seções: ${faltantes.join(", ")}`);
        highlightMissing();
        return;
      }
      
      // Solicitar o nome da fazenda se não estiver definido
      if (!window.calcResults.nomeFazenda) {
        const nomeFazenda = prompt("Por favor, informe o nome da fazenda:");
        if (nomeFazenda && nomeFazenda.trim() !== "") {
           window.calcResults.nomeFazenda = nomeFazenda.trim();
        } else {
           alert("Nome da fazenda é obrigatório para salvar os dados.");
           return;
        }
      }
      
      // Salva os dados no servidor
      saveResultsToServer();
      
      // Feedback visual
      const btn = salvarNoBancoBtn;
      btn.disabled = true;
      btn.innerHTML = '<i class="fas fa-spinner fa-spin me-2"></i>Salvando...';
      
      setTimeout(() => {
        btn.disabled = false;
        btn.innerHTML = '<i class="fas fa-check-circle me-2"></i>Salvo com Sucesso';
        
        setTimeout(() => {
          btn.innerHTML = '<i class="fas fa-save me-2"></i>Salvar no Banco';
        }, 3000);
      }, 1500);
    });
  }

  // Botão para visualizar resultado
  const visualizarResultadoBtn = document.getElementById("visualizarResultado");
  if (visualizarResultadoBtn) {
    visualizarResultadoBtn.addEventListener("click", () => {
      window.location.href = 'resultado.html';
    });
  }

  // Inicializar barras de progresso
  updateProgressBar();
  updateAllSectionProgress();
});

/**
 * Retorna true se TODOS os inputs numéricos estiverem preenchidos
 */
function areAllInputsFilled() {
  const inputs = document.querySelectorAll("input[type='number']");
  return Array.from(inputs).every(i => i.value.trim() !== "");
}

/**
 * Retorna o título (h2) do primeiro container que tiver algum input vazio
 * ou null se tudo estiver ok.
 */
function findIncompleteContainer() {
  const containers = document.querySelectorAll(".card-body, .accordion-body");
  for (const container of containers) {
    const inputs = container.querySelectorAll("input[type='number']");
    if (Array.from(inputs).some(i => i.value.trim() === "")) {
      // pega o texto do <h5> ou, se não existir, o id do container
      const heading = container.closest('.card')?.querySelector('.card-header h5') || 
                     container.closest('.accordion-item')?.querySelector('.accordion-button');
      return heading?.textContent || container.id;
    }
  }
  return null;
}

/**
 * Retorna um array com o texto de todos os <h2> cujos inputs não estão totalmente preenchidos
 */
function findAllIncompleteContainers() {
  const containers = document.querySelectorAll(".card-body, .accordion-body");
  return Array.from(containers)
    .filter(container => {
      const inputs = container.querySelectorAll("input[type='number']");
      return Array.from(inputs).some(i => i.value.trim() === "");
    })
    .map(container => {
      const heading = container.closest('.card')?.querySelector('.card-header h5') || 
                     container.closest('.accordion-item')?.querySelector('.accordion-button');
      return heading?.textContent || container.id;
    });
}

// Função para exibir o resultado abaixo do campo correspondente e armazená-lo
function displayResult(fieldId, result) {
  // Mapeia o fieldId para a chave do grupo esperada pelo backend
  const groupMapping = {
    potencialQuimicoArea: 'potencialQuimico',
    potencialQuimicoChuva: 'potencialQuimico',
    aguaUsada: 'aguaUsada',
    qtdAnima: 'aguaUsada',
    litrosAdicionalDia: 'aguaUsada',
    soloToneladas: 'cuidadoSolo',
    soloAnos: 'cuidadoSolo',
    soloArea: 'cuidadoSolo',
    combustivelHoras: 'combustivelUsado',
    combustivelTratores: 'combustivelUsado',
    combustivelLitros: 'combustivelUsado',
    eletrica: 'eletricidade',
    gadoPeso: 'gado',
    gadoQuantidade: 'gado',
    gadoAnos: 'gado',
    maoObraPessoas: 'maoObra',
    maoObraHoras: 'maoObra',
    maoObraDias: 'maoObra',
    maquinarioHoras: 'maquinarios',
    maquinarioValor: 'maquinarios',
    racaoSacas: 'racao',
    racaoValor: 'racao',
    producaoLeiteArea: 'producaoLeite',
    producaoLeiteLitros: 'producaoLeite',
    consumoFazendaValor: 'bens',
    consumoFazendaAnos: 'bens'
  };
  const groupKey = groupMapping[fieldId] || fieldId;
  
  let resultElement = document.getElementById(`${fieldId}-result`);
  
  if (resultElement) {
    resultElement.classList.remove('d-none');
    
    if (typeof result === 'object' && result.calc !== undefined && result.ref !== undefined && result.razao !== undefined) {
      resultElement.innerHTML = `
        <div class="row">
          <div class="col-md-4">
            <strong>Calc:</strong> ${Number(result.calc).toExponential(2).toUpperCase()}
          </div>
          <div class="col-md-4">
            <strong>Ref:</strong> ${Number(result.ref).toExponential(2).toUpperCase()}
          </div>
          <div class="col-md-4">
            <strong>Razão:</strong> ${Number(result.razao).toExponential(2).toUpperCase()}
          </div>
        </div>
      `;
    } else {
      let numericResult = Number(result);
      resultElement.innerHTML = `Resultado: ${numericResult.toExponential(2).toUpperCase()}`;
    }
    
    // Atualiza objeto global agrupando os resultados conforme esperado
    if (!window.calcResults) window.calcResults = {};
    if (typeof result === 'object' && result.calc !== undefined && result.ref !== undefined && result.razao !== undefined) {
      window.calcResults[groupKey] = {
        calc: Number(result.calc),
        ref: Number(result.ref),
        razao: Number(result.razao)
      };
    }
    // Também persiste no localStorage
    localStorage.setItem("calcResults", JSON.stringify(window.calcResults));
  }
}

// Função para exibir erros no container correspondente
function displayError(fieldId, errorMessage) {
  let errorElement = document.getElementById(`${fieldId}-error`);
  
  // Localiza o container correspondente
  let containerElem = document.getElementById(fieldId) || document.querySelector(`#${fieldId}`);
  let container = containerElem ? containerElem.closest(".card-body, .accordion-body") : null;
  
  if (!container) {
    console.warn(`Container não encontrado para o campo: ${fieldId}. Criando um container padrão.`);
    container = document.createElement("div");
    container.className = "card-body";
    document.body.appendChild(container);
  }
  
  // Se o elemento de erro não existir, cria um novo
  if (!errorElement) {
    errorElement = document.createElement("div");
    errorElement.id = `${fieldId}-error`;
    errorElement.className = "alert alert-danger mt-3";
    container.appendChild(errorElement);
  }
  
  // Atualiza o texto do erro
  errorElement.textContent = `Erro (${fieldId}): ${errorMessage}`;
}

function saveResultsToServer() {
  const baseUrl = 'http://localhost:8081';

  localStorage.setItem("calcResults", JSON.stringify(window.calcResults));

  fetch(`${baseUrl}/storeResults`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(window.calcResults)
  })
  .then(res => {
    if (!res.ok) throw new Error(`Erro ao salvar relatório: ${res.statusText}`);
    return res.json();
  })
  .then(data => {
    if (data.id) {
      return fetch(`${baseUrl}/sustentabilidade/calcular/${data.id}`, {
        method: 'POST'
      });
    }
    throw new Error('ID do relatório não retornado');
  })
  .then(res => {
    if (!res.ok) throw new Error(`Erro ao calcular sustentabilidade: ${res.statusText}`);
    return res.json();
  })
  .then(sust => {
    console.log('Índices de sustentabilidade salvos:', sust);
    showToast('Dados e índices salvos com sucesso!', 'success');
  })
  .catch(error => {
    console.error(error);
    showToast(`Erro: ${error.message}`, 'danger');
  });
  
  console.log(window.calcResults);
}

// Função para destacar campos numéricos obrigatórios que estão vazios
function highlightMissing() {
  document.querySelectorAll("input[type='number']").forEach(input => {
    if (input.value.trim() === "") {
      input.classList.add("is-invalid");
    } else {
      input.classList.remove("is-invalid");
    }
  });
  
  // Atualizar progresso de todas as seções
  updateAllSectionProgress();
}

// Encontrar a seção para um campo específico
function findSectionForField(fieldId) {
  for (const [section, fields] of Object.entries(sectionFields)) {
    if (fields.includes(fieldId)) {
      return section;
    }
  }
  return null;
}

// Função para atualizar a barra de progresso geral
function updateProgressBar() {
  const totalInputs = document.querySelectorAll('input[type="number"]').length;
  const filledInputs = Array.from(document.querySelectorAll('input[type="number"]')).filter(input => 
    input.value.trim() !== '' && parseFloat(input.value) > 0
  ).length;
  const percentage = Math.round((filledInputs / totalInputs) * 100);
  
  const progressBar = document.getElementById('progressBar');
  if (progressBar) {
    progressBar.style.width = `${percentage}%`;
    progressBar.setAttribute('aria-valuenow', percentage);
    progressBar.textContent = `${percentage}%`;
    
    // Mudar cor da barra de progresso com base no percentual
    if (percentage < 33) {
      progressBar.classList.remove('bg-warning', 'bg-success');
      progressBar.classList.add('bg-danger');
    } else if (percentage < 66) {
      progressBar.classList.remove('bg-danger', 'bg-success');
      progressBar.classList.add('bg-warning');
    } else {
      progressBar.classList.remove('bg-danger', 'bg-warning');
      progressBar.classList.add('bg-success');
    }
  }
}

// Função para atualizar o progresso de uma seção específica
function updateSectionProgress(sectionId) {
  const fields = sectionFields[sectionId] || [];
  if (fields.length === 0) return;
  
  // Contar campos preenchidos
  let filledFields = 0;
  fields.forEach(fieldId => {
    const input = document.getElementById(fieldId);
    if (input && input.value.trim() !== '' && parseFloat(input.value) > 0) {
      filledFields++;
    }
  });
  
  // Calcular percentual
  const percentage = Math.round((filledFields / fields.length) * 100);
  
  // Atualizar barra de progresso da seção
  const sectionCard = document.querySelector(`.section-card[data-section="${sectionId}"]`);
  if (sectionCard) {
    const progressBar = sectionCard.querySelector('.section-status .progress-bar');
    if (progressBar) {
      progressBar.style.width = `${percentage}%`;
      progressBar.setAttribute('aria-valuenow', percentage);
      progressBar.textContent = `${percentage}%`;
      
      // Mudar cor da barra de progresso com base no percentual
      progressBar.classList.remove('bg-danger', 'bg-warning', 'bg-success');
      if (percentage < 50) {
        progressBar.classList.add('bg-danger');
      } else if (percentage < 100) {
        progressBar.classList.add('bg-warning');
      } else {
        progressBar.classList.add('bg-success');
      }
      
      // Destacar visualmente seções completamente preenchidas
      if (percentage === 100) {
        sectionCard.classList.add('section-completed');
      } else {
        sectionCard.classList.remove('section-completed');
      }
    }
  }
}

// Função para atualizar o progresso de todas as seções
function updateAllSectionProgress() {
  Object.keys(sectionFields).forEach(sectionId => {
    updateSectionProgress(sectionId);
  });
}

// Função para mostrar toast de notificação
function showToast(message, type = 'info') {
  // Verificar se o container de toasts existe
  let toastContainer = document.querySelector('.toast-container');
  if (!toastContainer) {
    toastContainer = document.createElement('div');
    toastContainer.className = 'toast-container position-fixed bottom-0 end-0 p-3';
    document.body.appendChild(toastContainer);
  }
  
  // Criar o toast
  const toastId = `toast-${Date.now()}`;
  const toast = document.createElement('div');
  toast.className = `toast align-items-center text-white bg-${type} border-0`;
  toast.id = toastId;
  toast.setAttribute('role', 'alert');
  toast.setAttribute('aria-live', 'assertive');
  toast.setAttribute('aria-atomic', 'true');
  
  toast.innerHTML = `
    <div class="d-flex">
      <div class="toast-body">
        ${message}
      </div>
      <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
    </div>
  `;
  
  toastContainer.appendChild(toast);
  
  // Inicializar e mostrar o toast
  const bsToast = new bootstrap.Toast(toast, {
    autohide: true,
    delay: 3000
  });
  bsToast.show();
  
  // Remover o toast após ser escondido
  toast.addEventListener('hidden.bs.toast', function() {
    toast.remove();
  });
}
